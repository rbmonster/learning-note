# Redis 设计与实现

## Redis底层数据结构
### 简单动态字符串SDS
```
struct sdshdr {
    // 记录SDS所保存的字符串长度
    int len;
    // 记录buf数组中未使用字节的数量
    int free;
    // 字节数组，用于保存字符串
    char buf[];
}
```
- Redis作为数据库，经常被用于速度要求严苛、数据被频繁修改的场所，因此SDS分配内存不仅会分配需要的空间，还会分配额外的空间。
  - 小于1MB的SDS每次分配与len属性同样大小的空间
  - 大于1MB的每次分配1MB
  - 惰性释放：程序不立即使用内存重分配来回收缩短后多出来的字节，而是使用free属性，记录字节数量，静待下次使用。
- SDS的buf属性成为字节数组的原因（二进制安全）：Redis不是用这个数组来保存字符，而是保存一系列的二进制。

### 链表
- 双向链表
```
typedef struct listNode {
    // 前置节点
    struct listNode *prev;
    //后置节点
    struct listNode *next;
    // 节点的值
    void *value;
}listNode;

typedef struct list {
    listNode *head;
    listNode *tail;
    //链表所包含的节点数量
    unsigned long len;
    // 节点复制函数   
    void *(*dup)(void *ptr);
    // 节点释放函数
    void (*free)(void *ptr);
    // 节点值对比函数
    int (*match)(void *ptr,void *key);
}list;
```

### 字典
- 字典中的每个键都是独一无二的，Redis字典使用哈希表作为底层实现。
- 哈希表：
```
typedef struct dictht {
    // 哈希表数组
    dictEntry **table;
    // 哈希表大小
    unsigned long size;
    // 哈希表大小掩码，用于计算索引值
    // 总是等于size-1
    unsigned long sizemask;
    // 该哈希表已有节点的数量
    unsigned long used;
}dictht;
```
- 字典
```
typedef struct dict {
    // 类型特定函数
    dictType *type;
    // 私有数据
    void *privdata;
    // 哈希表
    dictht ht[2];
    // rehash索引
    // 当rehash不在进行时值为-1,
    int trehashidx;
}idct;
```

- 哈希算法：添加一个键值对，首先计算键的哈希值，接着根据sizemask计算出索引值。
  - 哈希算法使用的是MurmurHash 算法
 
- rehash操作：根据哈希表的负载因子确定是对哈希表的大小进行扩展或者收缩
  - 扩展操作：每次拓展ht[1]的大小为h[0]中第一个大于等于2^n的数
  - 收缩操作：大小为第一个小于等于2^n的数。
  - 在ht[1]上建立空间，使用渐进式hash，即RUD操作都在两个ht上进行，而create操作只添加到ht[1]
  - 在ht[1]上建立空间后，字典中维护一个索引计数器变量rehashidx，并将其设置为0，表示rehash工作开始。
  - 渐进式hash可以避免rehash对服务器性能造成影响。
 
### 跳跃表
- 跳跃表是一种有序的数据结构，支持O(logN)、最坏O(N)复杂度的节点的查找。
- 跳跃表是有序集合的底层实现之一。
- 跳跃表节点按照分值大小排序，分值相同时节点按照成员对象的大小排序。

### 整数集合
- 整数集合是redis用于保存整数值的集合抽象数据结构，可以保存为int16_t、int32_t、int64_t的整数值，保证集合中不会出现重复元素。
- 整数集合的底层实现是数组，数组以有序、无重复的方式保存集合元素，在有需要时根据添加元素改变数组类型。
- 整数数组只支持升级操作，不支持降级操作。

### 压缩列表
- 压缩列表是列表键和哈希键的地城实现之一。
- 压缩列表是一种为节约内存而开发的顺序性数据结构。
- 压缩列表可以包含多个节点，每个节点可以保存一个字节数组或者整数值。
- 添加新节点到压缩列表或者删除可能会引起连锁更新。

## Redis的数据对象类型
- Redis包含字符串对(String)、列表对象(list)、哈希对象(Hash)、集合对象(Set)和有序结合对象(zSet)
- Redis的对象的数据结构：
```
typedef struct redisObject{
    // 类型（根据类型区分五个对象）
    unsigned type:4;
    // 编码（记录了对象所使用的编码，也就是用了什么数据结构作为对象的底层实现）
    unsigned encoding:4；
    // 指向底层实现数据结构的指针
    void *ptr;
    //...
}
```
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/encodingCode.jpg)

### 字符串对象(String)
- 如果字符串保存的是一个字符串值，且长度<=39，则字符串对象使用的是embstr编码方式保存。
  - embstr方式在内存分配时仅会调用一次内存分配函数，而raw会调用两次
  - embstr字符串对象保存在一块连续的内存里面。
- 对embstr字符串执行任何修改命令时，程序会转换编码为raw
- 字符串对象是Redis五种类型的对象中唯一会被其他四种对象嵌套使用的。

```
>SET msg "hello word"

>OBJECT ENCODING msg
"embstr"

>APPEND msg ' again!'
(integer) 18

>OBJECT ENCODING msg
"raw"
```
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/stringCommand.jpg)


### 列表对象(list)
- 列表元素较少时使用压缩列表(ziplist)，而元素多的时候使用双链表(linkedlist)
  - 此处使用ziplist就是存列表连接
- 编码转换条件：同时满足一下两条件使用ziplist,否则linkedlist。
  1. 列表对象保存的所有字符串元素的长度都小于64字节。
  2. 列表对象保存的元素数量小于512个。
```
>RPUSH blah "hello" "world" "again"

>OBJECT ENCODING blah
"ziplist"

>RPUSH blah "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"

>OBJECT ENCODING blah
"linkedlist"
```
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/listCommand.jpg)


### 哈希对象(hash)
- 哈希对象的编码可以是ziplist或hashtable
  - ziplist会先保存键再保存值，因此键与值总是靠在一起，其中键的方向为压缩列表的表头方向。
- 编码转换条件：同时以下条件的哈希对象使用ziplist编码，否则使用hashtable
  1. 哈希对象保存的所有字符串元素的长度都小于64字节。
  2. 哈希对象保存的元素数量小于512个。
```
>HSET book name "Master C++ in 21 days"

>OBJECT ENCODING book
"ziplist"

>HSET book long_long_long_long_long_long_long_long_long_long_long_decription "content"

>OBJECT ENCODING book
"hashtable"
==============================================================
>EVAL "for i=1, 513 do redis.call('HSET',KEYS[1],i,i) end" 1 "numbers"

>HLEN numbers
512

>OBJECT ENCODING numbers
"hashtable"
```
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/hashCommand.jpg)


### 集合对象(set)
- 集合对象的编码可以是intset或者 hashtable
  - intset整数集合作为底层实现，包含的所有元素都被保存在整数集合里面。
  
- 编码转换条件：同时满足以下两条件时，对象使用intset编码否则使用hashtable
  - 集合对象保存的所有元素都是整数值。
  - 集合对象保存的元素数量不超过512个。

```
>SADD numbers 1 3 5 

>OBJECT ENCODING numbers
"intset"

>SADD numbers "seven"

>OBJECT ENCODING numbers
"hashtable"
```
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/setCommand.jpg)


### 有序集合对象(zset)
- 有序集合的编码可以是ziplist或者skiplist
  - ziplist按分值从小到大的进行排序，分值小的元素放在靠近表头方向，对象在前值在后，两者紧凑。
  - skiplist编码的有序集合使用zset结构作为底层实现，一个zset结构同时包含一个字典和跳跃表
   - ``` 
     typedef struct zset{
        zskiplist *zsl;
        dict *dict;
     }zset;
     ```
   - zset结构中的dict字典为有序集合创建了一个从成员到分支的映射，字典中每个键值对都保存了一个集合元素，键为集合成员，值为元素分值。
   
- 编码转换条件：满足以下两个条件使用ziplist，否则skiplist
  - 有序集合保存的元素数量小于128个。
  - 有序集合保存的所有元素成员的长度都小于64个字节。

```
>ZADD blah 1.0 www

>OBJECT ENCODING blah
"ziplist"

>ZADD blah 2.0 ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo

>OBJECT ENCODING blah
"skiplist"
```
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/zsetCommand.jpg)

### 类型检查与命令多态
- DEL、EXPIRE、RENAME、TYPE、OBJECT可以对任何键执行
- 类型检查，Redis会先检查输入键的类型是否正确，然后在执行给定的命令。
- 命令多态：由于基本类型的底层数据结构不确定，因此在执行命令时会先判断底层的数据结构类型再调用对应的命令函数。

### 内存回收
- Redis的内存回收使用的是引用计数的方式进行数据回收。对象的引用计数值为0时，对象所占用的内存会释放。

### 对象共享
- Redis数据库初始化时会创建一万个字符串对象，这些对象包含了从0到9999的所有整数值，当数据库需要用到这些字符串对象时，会共享这部分对象而不是新创建对象。
- Redis 对象不共享字符串对象，因为整数性字符串对象的验证操作是O(1)，而字符串对象的验证操作是O(N)。为了节省内存反而会造成大量的CPU浪费，得不偿失。
- 对象共享过程：
  - 将数据库的值指向一个现有的值对象。
  - 将被共享的值对象的引用计数增1。
```
>SET A 100
OK

>OBJECT REFCOUNT A
(integer)2
```
### 对象的空转时间
- redisObject的结构最后一个属性为lru属性，该属性记录了对象最后一次被命令层序访问的时间
- 当内存开启maxmemory选项时，且回收算法是volatile-lru 或者 allkeys-lru，超过maxmemory设置上限值的部分，空转时间较高的会被服务器优先释放，回收内存。
```
>SET msg "hello world"

>OBJECT LDLETIME msg     (打印空转时间)
20
```

## 数据库
- Redis默认会创建16个数据库
- 可以使用SELECT 选择目标数据库。
  - ```
    >SET msg "hello world"
    >GET msg 
    "hello world"
    >SELECT 2
    
    >GET msg
    (nil)
    ```
### 数据库键空间
- Redis 是一个键值对的数据库服务器。服务器中每个数据库都由一个redisDb结构的dict字典保存数据库中所有键值对，称为键空间。
  - 键空间的键也就是数据库的键，每个键都是字符串对象。
  - 键空间的值也就是数据库的值，每个值可以是五中基本类型对象。
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/databaseDict.jpg)  

- 清空数据库的键:FLUSHDB
- 随机返回数据库中某个键：RANDOMKEY
- 返回数据库数量：DBSIZE

### 过期键
- 设置键的生存时间(TTL time to live)或过期时间(PTTL)
  - EXPIRE: 将键key的生存时间设成ttl秒。
  - PEXPIRE: 将键key的生存时间设成ttl毫秒。
  - EXPIREAT: 将键key的过期时间设置成timestamp所指的秒数时间戳。
  - PEXPIREAT: 将键key 的过期时间设置成timestamp所指的毫秒数。
```
>SET key value

>EXPIRE key 5

>GET key
"value"
// 5s later
>GET key
(nil)
```
- 过期字典的键空间为expires字典，保存了所有键的过期时间
  - 过期字典的键是一个指针，指向键空间的具体的键对象（数据库键）
  - 过期字典的值为long类型的整数，键的保存了数据库的过期时间。
  
- 移除过期时间 PRESIST
  - ```
    >PEXPIREAT message 1391234440000  // 设置过期时间
    
    >TTL message  // 查询过期时间，以秒为单位返回键的剩余生存时间
    
    >PTTL message // 以毫秒为但会返回键的剩余生存时间
    
    >PERSIST message // 移除过期时间
    
    >TTL message 
    -1 
    ```
- 过期键的判定
  - 检查键是否存在于过期字典，如果存在取得过期时间
  - 检查当前的UNIX时间戳是否大于键的过期时间，如果是表示已过期，否则键未过期
  
- 过期键的删除策略
  - 定时删除，为每个过期键建立一个timer，缺点占用CPU
  - 惰性删除，键获取的时候判断过期再清除，对内存不友好。
  - 定期删除，即根据设定执行时长和操作频率清理。缺点难以确定。
  - Redis使用惰性删除和定期删除结合的方式配合使用。
  
- RDB模式对过期键的处理
  - 生成RDB文件时不保存已过期的键
  - 载入文件时，若是主服务器运行，已过期的文件不再载入。从服务器运行，全部载入。
  
- AOF持久化运行时，如果某个键过期，执行以下三动作。（针对主服务器运行）
  - 1.从数据库删除键。
  - 2.追加一条DEL message命令到AOF文件。
  - 3.向执行GET命令的客户端返回空回复。

- Redis可配置对于数据键发生修改时，发送通知事件。

## RDB 持久化
- 解决服务器退出，数据库中数据库状态消失的问题。
- RDB持久化可以手工执行，也可以根据服务器配置选项定期执行，该功能可以将某个时间点上的数据库状态保存在RDB文件中。

### RDB文件创建与载入
- SAVE命令，阻塞Redis服务器进程，直到RDB文件创建完毕为止。
- BGSAVE命令会派生出一个子进程，然后由子进程创建EDB文件。

- 因为AOF文件的更新频率比RDB文件高，因此如果开启了AOF持久化功能，服务器优先使用AOF文件还原数据。
- 只有在AOF持久化功能关闭的时候，服务器才会使用RDB恢复数据库。

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/RDBLoad.jpg)  

- 自动保存功能
```
save 900 1 
save 300 10
save 60 10000
// 分别表示
- 服务器900秒内，对数据库进行了至少1次修改
- 服务器300秒内，对数据库进行了至少10次修改
- 服务器60秒内，对数据库进行了至少10000次修改
```
- 上述功能主要通过dirty计数器和lastsave属性实现
  - dirty计数器：距离上一次成果执行SAVE命令或者BGSAVE命令后，服务器进行了多少次修改。
  - lastsave属性，记录上一次成功RDB的时间。
  
- RDB文件结构（TODO暂时不需要了解）
  - |REDIS|db_version|database|EOF|check_sum|
  
## AOF持久化
- AOF持久化保存数据库的方法是将服务器执行的命令保存到AOF文件中。

- 持久化的三个过程：命令追加、文件写入、文件同步
  - 命令追加即将执行的命令追加到AOF文件中。
  - 文件写入使用缓存区实现
  - 文件同步分为always、everysec、no三个选项。 安全级别从高到低、效率从低到高
    - always每次执行均写入安全性高效率低。
    - everysec每隔一秒子线程对AOF文件进行同步。理论只会丢失一秒数据。
    - no 何时同步由操作系统控制，写入的速度长，因为累积了数据在缓冲区，效率与上一种类似。
    
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/AOFLoad.jpg)  
 
- AOF重写，指的是对命令进行压缩，将RPUSH、LPOP的类似命令进行压缩，减少AOF文件大小

## 事件
- Redis服务器是一个事件驱动程序，服务器需要处理一下两事件。
  - 文件事件：服务器通过Socket与客户端连接
  - 时间事件：服务器中的一些操作，需要在特定时间点执行。
  
- Redis的文件事件基于Reactor模式开发（反应器模式）
  - 单线程，I/O多路复用
  - I/O多路复用程序将产生的Socket事件放到队列中，以有序、同步的方式发送。
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/fileProcesser.jpg)  

- 客户端与服务器的通信过程
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/client2server.jpg)

- 时间事件（2.8版本的redis只有周期性事件，没有定时事件）
  - 服务器将所有时间事件放在一个无序链表中，每当时间事件执行器运行时，遍历整个链表。
  
## 客户端的关闭
- 客户端进程退出或杀死
- 客户端发送不符合协议的命令请求
- 客户端成了CLIENT KILL命令的目标
- 用户为server设置了timeout选项，时间超时时客户端会关闭。
- 客户端发送的请求超过了输出缓冲区的限制大小（1GB）
- 服务器要发送给客户端的返回命令请求大小超过了输出缓冲区的限制

## 服务器
- 命令请求的执行过程
  1. 向服务器发送命令请求SET KEY VALUE.
  2. 服务接收命令请求，在数据库中设置操作，并产生命令回复OK
  3. 服务器将回复发送给客户端。
  4. 客户端接收命令打印OK
  
- 每个Redis对象都会有一个lru属性，保存了对象最后一次被命令访问的时间。

## 复制
- 设置的操作流程
  1. 设置主服务器的地址和端口。>SLAVEOF 127.0.0.1 6379
  2. 建立socket连接
  3. 发送ping命令
    - 作用：1）与主服务器建立通信。2）检测主服务是否可正常处理请求
  4. 身份验证。主从服务器是否均配置了masterauth选项。
    - ![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/masterauth.jpg)
  5. 发送端口信息。从服务器发送需要从服务器端口信息。
  6. 开始同步
  7. 命令传播
    - 命令传播阶段，从服务器每秒默认发送 >REPLCONF ACK <replication_offset> 命令
    - replication_offset是从服务器当前的复制偏移量
    - 作用：
      - 检测主从服务器的网络连接状态
      - 辅助实现min-slave选项。（主要用于防止主服务器在不安全状况下写命令）
      - 检测命令丢失。
  

- 主从复制，主要两个命令SYNC、PSYNC
  - SYNC：主服务器开启BGSAVE生成RDB文件，生成之后发送从服务器，占网络资源。从服务器主进程执行载入，阻塞无法处理命令请求。
  - PSYNC：根据主从维护的复制偏移量，判断复制积压缓冲区（主服务器存储写命令队列的地方）是否存在偏移量之后的数据，如果存在，则进行部分重同步操作。否则执行完整重同步。

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/slaveCopy.jpg)

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/psync.jpg)

## Sentinel 哨兵
- Sentinel是Redis的一个高可用的解决方案：由一个或者多个Sentinel实例组成Sentinel系统。
- 启动命令：
```
redis-sentinel /path/to/your/sentinel.conf
or
redis-server /path/to/your/sentinel.conf
```

- Sentinel故障转移操作
  1. 当一个主服务下线时，各个Sentinel会选举一个领头Sentinel执行故障转移。
    - 主要根据Raft领头选举算法实现
  2. Sentinel系统选择一个server1属下的从服务器，并将这个从服务器升级成新主服务器。
    - 从服务器的选择：1)删除下线或断开连接的从服务。2)删除5s无回复从服务。3)按照复制偏移量排名，最大的表示具有最新的数据信息。相同偏移量则按照ID从小到大选取。
  3. Sentinel系统向Server1属下的从服务器发送新的复制指令，让其成为新主服务器的从服务。当复制完成，故障转移完毕。
  4. Sentinel系统继续监视下线的server1，当其重新上线时设置成主服务器的从服务。
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/sentinel.jpg)

- Sentinel服务器有其专用的服务器代码
  - 仅支持PING、SENTINEL、INFO、SUBSCRIBE、UNSUBSCRIBE、PSUBSCRIBE和PUNSUBSCRIBE七个操作。
  
- 创建连向主服务器的连接
  - 命令连接：用于向主服务器发送命令与主服务器通信。
  - 订阅连接：sentinel：hello频道，用于记录主服务器的回复消息，防止客户端断线的回复信息丢失。
  
- Sentinel与主服务器默认十秒频率发送INFO命令。用于更新整个主从的结构信息。
- Sentinel每2秒向所监视的服务器发送PUBLISH 信息。（具体干嘛的没懂，估计也是监测用）
- Sentinel每1秒向主从及其他Sentinel发送PING命令，用于判断实例是否在线。

- 客观下线：当一个Sentinel判断一个服务器下线时，会询问其他的Sentinel是否真的是下线。

## 集群
- Redis集群是Redis提供的分布式数据库方案，集群通过分片实现数据共享，并提供复制和故障转移功能。
- 建立一个集群 至少需要三主三从六台服务器。
- 集群建立
```
// 向节点7001发送命令，将节点7001添加到7000集群
127.0.0.1:7000>CLUSTER MEET 127.0.0.1 7001

127.0.0.1:7000>CLUSTER NODES

127.0.0.1:7000>CLUSTER MEET 127.0.0.1 7002
```
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/cluster.jpg)

- 槽指派：Redis集群通过分片的方式保存数据库的键值对。集群的整个数据库被分成16384个槽slot
  - 对数据库的16384个槽进行指派之后，集群就处于上线状态。
  - 在获取数据库键时，便需要对键进行计算，再获取对应的槽位，并判断当前数据库是否为负责键所在槽的节点。
```
127.0.0.1:7000> CLUSTER ADDSLOTS 0 1 2 3 4 ...5000

127.0.0.1:7001 > CLUSTER ADDSLOTS 5000 5001 5002 5003 5004 ... 10000

127.0.0.1:7002 > CLUSTER ADDSLOTS 10001 10002 10003 ... 16383

// 查看给定键属于哪个槽
127.0.0.1:7000> CLUSTER KETSLOT "msg"

// 第一次向节点7000发送set返回MOVE错误，并向7001节点执行set指令。
127.0.0.1:7000> SET msg "heppy"
->redirected to slot [6257] located at 127.0.0.1:7001
OK
```

- 节点数据库和单机数据库在数据库方面的一个区别是，节点只能使用0号数据库，而单机Redis服务器则没有这个限制。 
- 重新分片：在重新分片的过程中，集群不需要下线，并且源节点和目标节点都可以继续处理命令请求。
  - 迁移过程中获取键可能会出现ASK错误（重新分片的一种临时措施）
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/askError.jpg)
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/slotReadd.jpg)

- 复制与故障转移
  - 设置从节点
  - ```
    > CLUSTER REPLICATE 127.0.0.1:7001
    ```
  - 集群中每个节点都会定期的向集群中的其他节点发送PING消息，以检测对方是否在线。
    - 若出现疑似下线的情况，集群中的各个节点会互相交换信息，已确定节点的状态。
  - 故障转移
    1. 从主节点的从节点选择一个从节点，使用Raft领头选举方式实现。
    2. 被选择的从节点执行SLAVEOF no one命令，成为新的主节点。
    3. 新的主节点撤销已下线节点的槽指派，并指派向自己。
    4. 新的主节点在集群中发送PONG消息，通知其他节点该节点变成主节点。
    5. 新主节点开始接受和处理指派槽的消息。
    
## 发布与订阅
```
//查看服务器目前订阅的通道
>PUBSUB CHANNELS
//正则匹配服务器通道
>PUBSUB CHANNELS "new.[is]"

// 订阅 new.it 通道
>SUBSCRIBE "new.it"

//取消订阅
>UNSUBSCRIBE "new.it"

//发布消息
>PUBLISH "new.it" "hello"
```
- 发送消息
  1. 将消息发送给channel频道的所有订阅者。
  2. 如果有一个或者多个模式patten与channel匹配，那么将message发送给patten的订阅者。
  
## 事务
- Redis通过MULTI、EXEC、WATCH等命令来实现事务。
```
>MULTI
QUEUED

>SET "name" "werwer"
QUEUED

>GET "name"
QUEUED

>SET "author" "Peter"
QUEUED

>GET "AUTHOR
QUEUED

>EXEC

1)OK
2)"werwer"
3)OK
4)"Peter"
```
- 事务从开始到结束经历三个阶段
  1. 事务入队
  2. 命令入队
  3. 事务执行
  - 事务命令使用队列实现。
  
- 事务开始后，若客户端发送的命令为EXEC、DISCARD、WATCH、MULTI四个命令其中一个，服务器会立即执行，否则执行命令入队操作。
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/transaction.png)

- MULTI命令标志着事务的开始
- EXEC命令会让服务器立即执行事务队列语句。
- WATCH为一个乐观锁实现。
  - 一个执行失败的例子
  - ```
    >WATCH "name"
    
    >MULTI 
    >SET "name" "peter"
    >EXEC
    (nil)
    ```
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/optiLock.jpg)

- redis 事务的ACID
  - 原子性：事务的多个操作当成一个整体来执行，要么全部执行，要么都不执行。
  - 一致性：事务执行前是“一致”的，执行后也是“一致”的。“一致”指的是符合数据库本身的定义和要求，没有包含非法或无效的错误数据。
  - 隔离性：并发执行和串行执行结果一致。Redis事务总是以串行执行，因此保证了隔离性。
  - 耐久性：一个事务执行完毕，结果会被保存到硬盘中，停机不丢失。
  
## Lua脚本
- 客户端可执行的脚本语言
```
>EVAL "return 'hello world'" 0
```

## 排序
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/sort.jpg)
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/sort2.jpg)
- SORT <key> :对一个包含数字值的键key进行排序
- SORT <key> ALPHA: 使用ALPHA选项可以对字符串值的键进行排序。
- SORT <key> ASC(DESC) :升序与降序。
- BY 指定某些字符串键或者某个哈希键所包含的某些域作为元素的权重，对一个键进行排序。
- LIMIT限制返回指定的一部分数据
  - ```
    >SORT alphabet ALPHA LIMIT 0 4
    ```
- GET可以用于排序之后返回指定的某些建的值
  - ```
    >SORT students ALPHA GET *-name
    // 返回students排序后对应的值 name
    ```
- STORE保存排序结果
  - ```
    >SORT students ALPHA STORE sorted_students
    // 保存结果在sorted_students中
    ```
    
## 二进制位数据
- Redis 提供SETBIT、GETBIT、BITCOUNT、BITOP四个命令用于处理二进制位数组。

## 慢查询日志
- slowlog-log-slower-than选项：指定超过多少微妙记录到慢查询日志上。
- slowlog-max-len选项：指定服务器最多保存多少条慢查询日志。