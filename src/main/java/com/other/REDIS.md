# Redis 基础
缓存基本思想：CPU Cache 缓存的是内存数据用于解决 CPU 处理速度和内存不匹配的问题，内存缓存的是硬盘数据用于解决硬盘访问速度过慢的问题。为了避免用户在请求数据的时候获取速度过于缓慢，所以我们在数据库之上增加了缓存这一层来弥补。

推荐一下一篇很顶的文章：[Redis 面霸篇：从高频问题透视核心原理](https://mp.weixin.qq.com/s/wrrXz4GoILd5hsbrYACTmA)
> 本文也参考了很多该文章资料

- [redis设计与实现压缩列表-压缩列表](https://redisbook.readthedocs.io/en/latest/compress-datastruct/ziplist.html)


## 基本数据结构
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/data-structure.jpg)

### String 字符串
使用场景：如博客的文章数量，粉丝数量。
#### 底层结构
底层结构为简单动态字符串SDS(simple dynamic String)。SDS的使用`char buf[]`的数组保存字符串数据，另外还有定义了`free`未使用的字节数量和`len`当前字节长度的属性
1. SDS中len保存这字符串的长度，保证了O(1) 时间复杂度查询字符串长度信息。
2. `free`涉及到SDS使用的**惰性释放策略**：不立即使用内存重分配来回收缩短后多出来的字节，而是使用free属性，记录字节数量。空闲出来的空间，可以让str在进行append的时候重新使用。
3. 针对缓存频繁修改的情况：SDS分配内存不仅会分配需要的空间，还会分配**额外的空间**。
   - 小于1MB的SDS每次分配与len属性同样大小的空间
   - 大于1MB的每次分配1MB

两种编码方式：
- `embstr`编码：保存的是一个字符串值，且长度<=39，则字符串对象使用的是`embstr`编码方式保存
- `raw`编码： 对`embstr`字符串执行任何**修改命令**时，程序会转换编码为`raw`。
- 中文默认占三个字符。
> 优先使用`embstr`编码的原因：
> 1. `embstr`方式在内存分配时仅会调用一次内存分配函数，而raw会调用两次。同样的释放内存要调用两次。`embstr`修改的时候，连续的内存就变成多块的内存区间。
> 2. `embstr`**保存在一块连续内存**，可以更快读取。

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

#### 相关指令
相关指令：
 - `set 'key' 'value'`
 - `get 'key'`
 - `append 'key' 'appendValue' `
 - `strlen 'key'`  查长度
```
127.0.0.1:6379> set name 'sdfasdfsdf111111111111111111111111111111111111111111111111111111111'
OK
127.0.0.1:6379> strlen name
67
127.0.0.1:6379> object encoding name
raw
127.0.0.1:6379> set name '老王'
OK
127.0.0.1:6379> strlen name
6
127.0.0.1:6379> get name
老王
127.0.0.1:6379> append name '去隔壁了'
18
127.0.0.1:6379> get name
老王去隔壁了
127.0.0.1:6379> set sim 'asdf'
OK
127.0.0.1:6379> object encoding sim
embstr
```

### List 列表
使用场景：比如twitter的关注列表，粉丝列表等都可以用Redis的list结构来实现。
#### 底层结构
Redis中的列表list，在版本3.2之前，列表底层的编码是`ziplist`和`linkedlist`实现的，但是在版本3.2之后，重新引入`quicklist`，列表的底层都由`quicklist`实现。

quickList是一个`ziplist`组成的`linkedlist`双向链表，是 `ziplist` 和 `linkedlist` 的混合体。它将`linkedlist`按段切分，每一段使用`ziplist`来紧凑存储，多个`ziplist` 之间使用双向指针串接起来。
> `quicklist`是一种既保留`ziplist`的空间高效性，又能不让其更新复杂度过高的实现。把`ziplist`和普通的双向链表结合起来。每个双链表节点中保存一个`ziplist`，然后每个`ziplist`中存一批list中的数据(具体`ziplist`大小可配置)，这样既可以避免大量链表指针带来的内存消耗，也可以避免`ziplist`更新导致的大量性能损耗，将大的`ziplist`化整为零。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/quicklist.png)

`ziplist`是Redis节省内存而开发的数据结构。在list底层的`ziplist`是一个特殊的双向链表,特殊之处在于没有维护双向指针:`prev` `next`。而是存储**上一个entry的长度**和**当前entry的长度**，通过长度推算下一个元素在什么地方。`ziplist`使用**连续的内存块**，以 O(1) 的时间复杂度在列表的两端进行 push 和 pop 操作。查找需要O(n)，是一种时间换空间的方案。
> `ziplist`每次变更的时间复杂度都非常高，因为必须要重新生成一个新的`ziplist`来作为更新后的list，如果一个list非常大且更新频繁，那就会给redis带来非常大的负担。\
> 当一个列表只有少量数据的时候，并且每个列表项要么就是小整数值，要么就是长度比较短的字符串，那么 Redis 就会使用压缩列表来做列表键的底层实现。

`linkedlist`便于在表的两端进行push和pop操作，在插入节点上复杂度很低O(1)，但是它的内存开销比较大。
> 在每个节点上除了要保存数据之外，还要额外保存两个指针；其次，双向链表的各个节点是单独的内存块，地址不连续，节点多了容易产生内存碎片。

`ziplist`与`linkedlist`优缺点对比：
- 双向链表`linkedlist`便于在表的两端进行push和pop操作，在插入节点上复杂度很低，但是它的内存开销比较大。首先，它在每个节点上除了要保存数据之外，还要额外保存两个指针；其次，双向链表的各个节点是单独的内存块，地址不连续，节点多了容易产生内存碎片。
- `ziplist`存储在一段连续的内存上，所以存储效率很高。但是，它不利于修改操作，插入和删除操作需要频繁的申请和释放内存。特别是当`ziplist`长度很长的时候，一次realloc可能会导致大批量的数据拷贝。

> 字节上节省空间
```
typedef struct listNode {
    struct listNode *prev; // 前置节点
    struct listNode *next; // 后置节点
    void *value; // 节点的值
} listNode;
// listNode:24字节
typedef struct entry{
    previous_entry_length:1,5字节(前⼀个entrylen,常为1字节)
    encoding：1,2,5(编码格式,常为1字节)
    content：保存实际数据。
}
// entry⼤概10字节
```


`ziplist` **结构及遍历过程**
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/ziplist.jpg)

当进行**从前向后**的遍历时，程序从指向节点 e1 的指针p开始，计算节点 e1 的长度（e1-size）， 然后将 p 加上 e1-size ，就将指针后移到了下一个节点 e2 ...如此反覆，直到 p 遇到 `ZIPLIST_ENTRY_END` 为止
```
                              p + e1-size + e2-size
                 p + e1-size     |
           p          |          |
           |          |          |
           V          V          V
+----------+----------+----------+----------+----------+----------+----------+
| ZIPLIST  |          |          |          |          |          | ZIPLIST  |
| ENTRY    |    e1    |    e2    |    e3    |    e4    |   ...    | ENTRY    |
| HEAD     |          |          |          |          |          | END      |
+----------+----------+----------+----------+----------+----------+----------+

           |<-------->|<-------->|
             e1-size    e2-size
             
```

当进行从后往前遍历的时候，程序从指向节点eN的指针p出发，取出eN的pre_entry_length值，然后用p减去pre_entry_length，这就将指针移动到了前一个节点eN-1...，如此反覆，直到p遇到 `ZIPLIST_ENTRY_HEAD` 为止
```
                                         p - eN.pre_entry_length
                                            |
                                            |          p
                                            |          |
                                            V          V
+----------+----------+----------+----------+----------+----------+----------+
| ZIPLIST  |          |          |          |          |          | ZIPLIST  |
| ENTRY    |    e1    |    e2    |   ...    |   eN-1   |    eN    | ENTRY    |
| HEAD     |          |          |          |          |          | END      |
+----------+----------+----------+----------+----------+----------+----------+
```

> 旧的数据规则\
> 当满足下面两条件时，使用`ziplist`。一条不满足即使用`linkedlist`
> 1. 列表对象保存的所有字符串元素的长度都小于64字节。
> 2. 列表对象保存的元素数量小于512个。

#### 相关指令
相关指令：
- `rpush 'key' 'value1' 'value2' ...` // 数据推入表尾
- `lpush 'key' 'value1' 'value2' ...` // 数据推入表头
- `lpop 'key'` //表头弹出数据
- `rpop 'key'` // 表尾弹出数据
- `llen 'key'` // 查看长度
- `lindex 'key' 0`  //定位列表相关元素的值 
- `blpop key1...keyN timeout`  // 阻塞弹出，超时返回nil
- `brpop key1...keyN timeout`  // 阻塞弹出，超时返回nil
- `brpoplpush llist testlist 10` // 取出最后一个元素，并插入到另外一个列表的头部； 如果列表没有元素阻塞
- `LINDEX list1 0`  // 获取索引小标为0的元素
- `LRANGE list1 0 2` // 取出索引小标为0～2的元素
- `LLEN key` // 返回list长度
```
127.0.0.1:6379>RPUSH blah "hello" "world" "again"

127.0.0.1:6379>OBJECT ENCODING blah
"ziplist"

127.0.0.1:6379>RPUSH blah "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"

127.0.0.1:6379>OBJECT ENCODING blah
"linkedlist"

127.0.0.1:6379> llen blah
4
127.0.0.1:6379> lpush blah 'leftvalue'
5
127.0.0.1:6379> lindex blah 0
leftvalue
127.0.0.1:6379> lpop blah
leftvalue
127.0.0.1:6379> rpop blah
again

127.0.0.1:6379> lindex blah 1
world

127.0.0.1:6379> brpoplpush llist testlist 10
asdf

127.0.0.1:6379> LINDEX list1 0
"asdf"

127.0.0.1:6379> LRANGE list1 0 2
1) "qwecvxv"
2) "sdfqwr"
3) "asdf"
```
    
### Hash 哈希 k-v

#### 底层结构
哈希对象的编码可以是 `ziplist`(压缩列表)或`hashtable`
- `ziplist`会先保存键再保存值，因此键与值总是靠在一起，其中键的方向为压缩列表的表头方向。
- 通过 "数组 + 链表" 的链地址法来解决部分 **哈希冲突**

编码转换：同时以下条件的哈希对象使用`ziplist`编码，否则使用`hashtable`
1. 哈希对象保存的所有字符串元素的长度都小于64字节。
2. 哈希对象保存的元素数量小于512个。

#### hash冲突
Redis 的哈希表使用链地址法解决hash冲突，即冲突的位置上使用单链表的连接，解决冲突的问题。

#### rehash与渐进式rehash
rehash重新散列：随着hash表的操作不断进行，哈希表保存的键值会逐渐地增多或减少，为了让哈希表的负载因子保持在一个合理的范围。

渐进式rehash：重新散列过程不是一次性操作，而是在访问节点操作时顺便的把节点的值rehash过去。
> 渐进式rehash的好处：采取分而治之的方式，将rehash键值对所需的计算工作均摊到字典的每个添加、删除、查找和更新上，从而避免集中式rehash带来的庞大计算量。

动态的负载因子：**哈希表的负载因子 = 哈希表已保存的节点/哈希表大小**
> 哈希表的负载因子用来判断是否需要对哈希表进行扩容或者收缩，扩容及收缩操作可以通过rehash实现

**扩容时机**:
1. 服务器没有执行`BGSAVE`或`BGREWRITEAOF`命令，且哈希表负载因子大于等于1
2. 服务器执行`BGSAVE`或`BGREWRITEAOF`命令，且哈希表负载因子大于等于5
> 扩容根据执行`BGSAVE`或`BGREWRITEAOF`命令是否执行，是因为上述两命令都是开启子线程进行操作，而操作系统正常都使用COW（Copy-On-Write）技术优化子线程效率。避免子线程运行时进行扩容，可以避免不必要的写操作，进而节省内存。

**收缩时机**：当哈希表负载因子小于0.1时，对哈希表进行收缩操作。

扩容过程：redis的hash使用了两个全局哈希表。开始默认使用「hashtable 0」保存键值对数据，「hashtable 1」 此刻没有分配空间。触发扩容时
1. 系统给「hashtable 1」分配的大小为第一个**大于等于** 「hashtable 0」.used*2的 2的n次方幂的值
2. 将「hashtable 0 」的数据重新映射拷贝到 「hashtable 1」中；
3. 释放「hashtable 0」的空间。                                                                                                                                           
将`hashtable 0 `的数据重新映射到 `hashtable 1 `的过程中并不是一次性的，这样会造成 Redis 阻塞，无法提供服务。而是采用了**渐进式 rehash**，每次处理客户端请求hashtable执行增删改查操作时，顺带将节点rehash到`hashtable 1`中。

> rehash进行期间，字典会同时操作`hashtable 0`与`hashtable 1`，如查找就要在两个表中查找，而新增只操作到新表。
#### 相关指令
相关指令：
- `hset 'hashName' 'key' 'value'` // 添加元素
- `hget 'hashName' 'key'`
- `hdel 'hashName' 'key'`
- `hlen 'hashName'`
- `hgetall 'hashName'` // 获取所有元素，依次按照k-v的形式展示

```
127.0.0.1:6379> HSET book name "Master C++ in 21 days"
1

127.0.0.1:6379> object encoding book
ziplist
127.0.0.1:6379> hset book fuck 'shit'
1
127.0.0.1:6379> HSET book long_long_long_long_long_long_long_long_long_long_long_decription "content"
1
127.0.0.1:6379> OBJECT ENCODING book
hashtable

127.0.0.1:6379> hget book fuck
shit

127.0.0.1:6379> hgetall book
long_long_long_long_long_long_long_long_long_long_long_decription
content
name
Master C++ in 21 days
fuck
shit

127.0.0.1:6379> hlen book
3
```
    
### 集合对象(set)
使用场景：在博客的设计中，可以非常方便的实现如共同关注、共同喜好、二度好友等功能
#### 底层结构
集合对象的编码可以是`intset` 或者 `hashtable`
> `intset`整数集合作为底层实现，包含的所有元素都被保存在整数集合里面。

编码转换条件：同时满足以下两条件时，对象使用`intset`编码否则使用`hashtable`
- 集合对象保存的所有元素都是整数值。
- 集合对象保存的元素数量不超过512个。

#### 相关指令 
相关指令：
- `sadd 'setName' 'key' 'value1' 'value2' ...`
- `scard 'setName'`  // 返回长度
- `sismember 'setName' 'key'` // 查看元素是否存在
```
127.0.0.1:6379> sadd aset 123 12323 222
3
127.0.0.1:6379> object encoding aset
intset
127.0.0.1:6379> sadd aset 'dsf'
1
127.0.0.1:6379> object encoding aset
hashtable
127.0.0.1:6379> scard aset
4
127.0.0.1:6379> srem aset 123
1
127.0.0.1:6379> sismember aset dsf
1
127.0.0.1:6379> sismember aset dsf11
0
```
### 有序集合zset
使用场景：打赏排行榜
> 面对需要展示最新列表、排行榜等场景时，数据更新频繁或者需要分页显示，优先考虑使用 Sorted Set。

#### 底层结构
有序集合的编码可以是`ziplist`或者`skiplist`
- `ziplist`按分值从小到大的进行排序，分值小的元素放在靠近表头方向，对象在前值在后，两者紧凑。
- `skiplist`编码的有序集合使用`zset`结构作为底层实现，一个`zset`结构同时包含一个字典和跳跃表
  
编码转换条件：满足以下两个条件使用`ziplist`，否则`skiplist`
- 有序集合保存的元素数量小于128个。
- 有序集合保存的所有元素成员的长度都小于64个字节。

`skiplist` 跳跃表是一种有序数据结构，它通过在每个节点中维持多个指向其他节点的索引指针，从而达到快速访问节点的目的。 **跳表在链表的基础上，增加了多层级索引**，通过索引位置的几个跳转，实现数据的快速定位。
> 跳跃表支持**平均O(logN)，最坏O(N)复杂度**的节点查找。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/skiplist.png)


```
typedef struct zskiplist {

    // 表头节点和表尾节点
    struct zskiplistNode *header, *tail;

    // 表中节点的数量
    unsigned long length;

    // 表中层数最大的节点的层数
    int level;

} zskiplist;
```

```
typedef struct zskiplistNode {

    // 后退指针
    struct zskiplistNode *backward;

    // 分值
    double score;

    // 成员对象
    robj *obj;

    // 层
    struct zskiplistLevel {

        // 前进指针
        struct zskiplistNode *forward;

        // 跨度
        unsigned int span;

    } level[];

} zskiplistNode;
```
#### 相关指令
相关指令
- `zadd 'zsetName' 'score' 'key'`
- `zcount 'zsetName' 'scoreMin' 'scoreMax'`   // 计算范围内的有的值
- `zcard 'zsetName'`  // 计算`zset`元素的数量
- `zrem 'zsetName' 'key'`  // 删除 `zset`里面的key
- `zrangebyscore delay 0  1606996111`  // 获取按score 范围内的key
```
127.0.0.1:6379> ZADD blah 1.0 www
1
127.0.0.1:6379> object encoding blah
ziplist
127.0.0.1:6379> ZADD blah 2.0 ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
1
127.0.0.1:6379> object encoding blah
skiplist

127.0.0.1:6379> zcount blah 1 222
2

127.0.0.1:6379> zcard blah
2

127.0.0.1:6379> zrem blah www
1

127.0.0.1:6379> zadd delay 1606996039 message1
(integer) 1
127.0.0.1:6379> zadd delay 1606996064 message2
(integer) 1
127.0.0.1:6379> zrangebyscore delay 0  1606996111
1) "xiaoxiao"
2) "xxiaoming"
3) "message1"
```

#### 保存固定行数的lua
[Redis + Lua 实现 sorted set 集合保证固定数量的数据，并保留新数据剔除旧数据](https://blog.csdn.net/cainiao1412/article/details/107483286)
  
### Bitmap
Bitmap 通过一个 bit 数组来存储特定数据的一种数据结构，每一个 bit 位都能独立包含信息，bit 是数据的最小存储单位，因此能大量节省空间。

Bitmap 的底层数据结构用的是 String 类型的 SDS 数据结构来保存位数组，Redis 把每个字节数组的 8 个 bit 位利用起来，每个 bit 位 表示一个元素的二值状态(不是 0 就是 1)。

![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/basic/bitmap.png)

#### 应用场景
参考资料：[Redis 实战篇：巧用 Bitmap 实现亿级海量数据统计](https://mp.weixin.qq.com/s/js_H86SNjY5lPSN1_6v00w)

**用 Bitmap 来判断海量用户中某个用户是否在线**

key = login_status 表示存储用户登陆状态集合数据， **将用户 ID 作为 offset**，在线就设置为 1，下线设置 0。
```
SETBIT <key> <offset> <value> // 设置用户是否已登陆
GETBIT <key> <offset>       // 获取用户是否在线
```

**用户每个月的签到情况**

key 可以设计成 `uid:sign:{userId}:{yyyyMM}`，月份的每一天的值 - 1 可以作为 offset（因为 offset 从 0 开始，所以 offset = 日期 - 1）。
```
SETBIT uid:sign:89757:202105 15 1  // 表示记录用户在 2021 年 5 月 16 号打卡
GETBIT uid:sign:89757:202105 15    // 编号 89757 用户在 2021 年 5 月 16 号是否打卡。
BITCOUNT uid:sign:89757:202105     // 统计该用户在 5 月份的打卡次数，使用 BITCOUNT 指令
BITPOS uid:sign:89757:202105 1     // 获取 userID = 89757 在 2021 年 5 月份首次打卡日期
```

**连续签到用户总数**
> 在记录了一个亿的用户连续 7 天的打卡数据，如何统计出这连续 7 天连续打卡用户总数呢？

我们把每天的日期作为 Bitmap 的 key，userId 作为 offset，若是打卡则将 offset 位置的 bit 设置成 1。key 对应的集合的每个 bit 位的数据则是一个用户在该日期的打卡记录。 一共有 7 个这样的 Bitmap，如果我们能对这 7 个 Bitmap 的对应的 bit 位做 **『与』运算**。

Redis 提供了 `BITOP operation destkey key [key ...]`这个指令用于对一个或者多个 键 = key 的 Bitmap 进行位元操作。
`opration` 可以是 `and、OR、NOT、XOR`。当 `BITOP` 处理不同长度的字符串时，较短的那个字符串所缺少的部分会被看作 0 。空的 key 也被看作是包含 0 的字符串序列。
```
// 与操作
BITOP AND destmap bitmap:01 bitmap:02 bitmap:03
// 统计 bit 位 =  1 的个数
BITCOUNT destmap
```
> 简单计算下 一个一亿个位的 Bitmap占用的内存开销，大约占 12 MB 的内存（10^8/8/1024/1024），7 天的 Bitmap 的内存开销约为 84 MB。同时我们最好给 Bitmap 设置过期时间，让 Redis 删除过期的打卡数据，节省内存。

### HyperLogLog 
是做基数统计的redis对象，故不是集合，不会保存元数据，只记录数量而不是数值。
#### 基数计数的演进
第一阶段：使用一般集合或数据结构来处理如HashSet或B+树，
> B 树最大的优势就是插入和查找效率很高，但是并没有节省内存。**数据量过大**便会导致内存占用过高。

第二阶段：BitMap
> 通过一个 bit 数组来存储特定数据的一种数据结构，每一个 bit 位都能独立包含信息，bit 是数据的最小存储单位，因此能大量节省空间。\
> 如果要统计 1 亿 个数据的基数值，大约需要的内存：10000_0000/ 8/ 1024/ 1024 ≈ 12 M, 如果用 32 bit 的 int 代表 每一个 统计的数据，大约需要内存：32 * 100_000_000/ 8/ 1024/ 1024 ≈ 381 M

第三阶段：概率算法
> HyperLogLog 的表现是惊人的，上面我们简单计算过用 bitmap 存储 1 个亿 统计数据大概需要 12 M 内存，而在 Redis 中实现的 HyperLoglog 也只需要 12 K 内存，在 标准误差 0.81% 的前提下，能够统计 2^64 个数据！
  
#### 应用场景
- 统计系统中每个按钮或功能的使用情况
- 统计注册 IP 数
- 统计每日访问 IP 数
- 统计页面实时 UV 数
- 统计在线用户数
- 统计用户每天搜索不同词条的个数

#### 相关指令
- `pfadd 'keyName'  'value1' 'value2' ...` // 添加值到某个集合
- `pfcount 'keyName'`   // 统计值
```
127.0.0.1:6379[3]> pfadd countNum '12' 'asdf' '123'
1
127.0.0.1:6379[3]> pfcount countNum
3

127.0.0.1:6379[3]> pfadd countNum '1dsaf2' 'aasdfasdf' '12sadf3'
1
127.0.0.1:6379[3]> pfcount countNum
6
```

### GeoHash 
GeoHash是用来存储地图经纬度，进而简化距离计算的一种redis对象。

GeoHash 算法将 二维的经纬度 数据映射到 一维 的整数，这样所有的元素都将在挂载到一条线上，距离靠近的二维坐标映射到一维后的点之间距离也会很接近。
- 核心思想就是把整个地球看成是一个二维的平面，然后把这个平面不断地等分成一个一个小的方格，每一个 坐标元素都位于其中的 唯一一个方格 中，等分之后的 方格越小，那么坐标也就 越精确。每个表格使用编码表示。
- 通过上面的思想，能够把任意坐标变成一串二进制的编码
  
在 Redis 中，经纬度使用 52 位的整数进行编码，放进了 `zset` 里面，`zset` 的 value 是元素的 key，score 是 GeoHash 的 52 位整数值。`zset` 的 score 虽然是浮点数，但是对于 52 位的整数值来说，它可以无损存储。
- 应用场景：附近的人、附近的餐厅、共享单车（周围的车）
#### 使用注意场景
> 如果使用 Redis 的 Geo 数据结构，它们将 全部放在一个 `zset` 集合中。在 Redis 的集群环境中，集合可能会从一个节点迁移到另一个节点，如果单个 key 的数据过大，会对集群的迁移工作造成较大的影响，在集群环境中单个 key 对应的数据量不宜超过 1M，否则会导致集群迁移出现卡顿现象，影响线上服务的正常运行。所以，这里建议 Geo 的数据使用 单独的 Redis 实例部署，不使用集群环境。\
> 如果数据量过亿甚至更大，就需要对 Geo 数据进行拆分，按国家拆分、按省拆分，按市拆分，在人口特大城市甚至可以按区拆分。这样就可以显著降低单个 `zset` 集合的大小。

#### 相关指令
- `geoadd 'geoKeyName'  '纬度' '经度' 'key'`
- `geodist 'geoKeyName' '地点key1' '地点key2' km(单位)`   // 计算两点距离
- `geopos 'geoKeyName' 'key'`    // 显示key对应经纬度
- `georadiusbymember  'geoKeyName' 'key' 20 km withdist count 3 asc `  // 计算地点 周围20公里最近的三家店显示带距离
```
127.0.0.1:6379[3]> geoadd company 116.48105 39.996794 juejin
1
127.0.0.1:6379[3]> geoadd company 116.514203 39.905409 ireader
1
127.0.0.1:6379[3]> geodist company juejin ireader km
10.5501
127.0.0.1:6379[3]> geopos company juejin
116.48104995489120483
39.99679348858259686

127.0.0.1:6379[3]>  geohash company ireader
wx4g52e1ce0
127.0.0.1:6379[3]> georadiusbymember company ireader 20 km count 3 asc
ireader
juejin
      // 带距离 
127.0.0.1:6379[3]> georadius company 116.514202 39.905409 20 km withdist count 3 asc
ireader
0.0000
juejin
10.5501
```

### 布隆过滤器 bloomFilter
布隆过滤器: 本质上是由长度为 m 的位向量或位列表（仅包含 0 或 1 位值的列表）组成，最初所有的值均设置为 0
- 向布隆过滤器中添加数据时，会使用多个 hash 函数对 key 进行运算，然后对位数组长度进行取模运算得到一个位置，每个 hash 函数都会算得一个不同的位置。再把位数组的这几个位置都置为 1 就完成了 add 操作。
- 判断数据是否存在时，同样使用多个hash函数计算key，只要有一个位为 0，说明key不存在。但是都是1，并不能说明key必定存在，可能位置都是其他元素添加导致的，因此说存在一定的误判率。
- 布隆过滤器有两关键的参数，一个是元素大小，一个是误差率。当误差率设置越小，布隆过滤器需要的空间越大。
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/basic/bloomFilter.png)
  
数据结构：**bitmap比特位的集合**。bitmap是一个以比特为基本单位的数组，如一个int类型32个比特，那我们使用比特来应用就可以节省很大的空间。

假阳性与假阴性：即给定一个元素 X ，如果布隆过滤器说它存在于集合中，那么它有**可能真的存在**，而如果布隆过滤器说它不存在，那么就**一定不存在**。
> 利用假阳性做**粗略的去重计数**判断：
> 1. 一个元素首先判断布隆过滤器是否存在？
> 2. 不存在计数+1，添加该元素到布隆过滤器中。
> 3. 存在，计数不变。

#### 应用场景
- 大数据判断是否存在：这就可以实现出上述的去重功能，如果你的服务器内存足够大的话，那么使用 HashMap 可能是一个不错的解决方案，理论上时间复杂度可以达到 O(1) 的级别，但是当数据量起来之后，还是只能考虑布隆过滤器。
- 爬虫/ 邮箱等系统的过滤：平时不知道你有没有注意到有一些正常的邮件也会被放进垃圾邮件目录中，这就是使用布隆过滤器**误判**导致的。 


**解决缓存穿透**：我们经常会把一些热点数据放在 Redis 中当作缓存，例如产品详情。通常一个请求过来之后我们会先查询缓存，而不用直接读取数据库，这是提升性能最简单也是最普遍的做法，但是如果一直请求一个**不存在的缓存**，那么此时一定不在缓存中，那就会有 **大量请求直接打到数据库** 上，造成 缓存穿透，布隆过滤器也可以用来解决此类问题。\
在查询缓存前添加一个布隆过滤器，如果布隆过滤器没有命中，则表示**查询的数据必定不在缓存中**，直接返回结果，可以避免大量请求直接查询数据库。尽管会有较小的误判率(即部分伪造请求，布隆过滤器判断存在内存中)，但是布隆过滤器可以过滤大部分无效请求，解决缓存穿透问题。
> 布隆过滤器有一个可以预判误判率的公式，查询缓存可能误判的名单存在，进行正常的查询。

应用介绍：在查询缓存的前面加一层布隆过滤器的过滤判断，布隆过滤器添加所有查询的key， 判断缓存是否存在。
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/basic/cacheQueryBloomFilter.jpg)


#### 相关指令
- `BF.RESERVE 'bfName' 0.0001 600000`  //自定义参数创建布隆过滤器 `BF.RESERVE {key} {error_rate} {capacity} [EXPANSION {expansion}] [NONSCALING]`
- `bf.add  'bfName'  'value'`  //添加元素
- `bf.exists   'bfName'  'value'`  //判断元素是否存在。
- `bf.madd 'bfName'  'value' 'value'` //批量添加
- `bf.mexists 'bfName'  'value' 'value'` // 批量判断存在
```
127.0.0.1:6379> bf.add codehole user1
(integer) 1
127.0.0.1:6379> bf.add codehole user2
(integer) 1
127.0.0.1:6379> bf.add codehole user3
(integer) 1
127.0.0.1:6379> bf.exists codehole user1
(integer) 1
127.0.0.1:6379> bf.exists codehole user2
(integer) 1
127.0.0.1:6379> bf.exists codehole user3
(integer) 1
127.0.0.1:6379> bf.exists codehole user4
(integer) 0
127.0.0.1:6379> bf.madd codehole user4 user5 user6
1) (integer) 1
2) (integer) 1
3) (integer) 1
127.0.0.1:6379> bf.mexists codehole user4 user5 user6 user7
1) (integer) 1
2) (integer) 1
3) (integer) 1
4) (integer) 0
```

#### 相关资料
- [JavaGuide-bloom-filter](https://github.com/Snailclimb/JavaGuide/blob/master/docs/dataStructures-algorithms/data-structure/bloom-filter.md)
- [Quick Start Guide for RedisBloom ](https://oss.redis.com/redisbloom/Quick_Start/)

### 布谷鸟过滤器 cuckooFilter
Bloom Filter 可能存在误报，并且无法删除元素，而Cuckoo哈希就是解决这两个问题的。

Cuckoo的哈希函数是成对的（具体的实现可以根据需求设计），每一个元素都是两个，分别映射到两个位置，一个是记录的位置，另一个是备用位置，这个备用位置是处理碰撞时用的。
如图，使用hashA 和hashB 计算对应key x的位置a和b ：
1. 当两个哈希位置有一个为空时，则插入该空位置；
2. 当两个哈希位置均不为空时，随机选择两者之一的位置上key y 踢出，并计算踢出的key y在另一个哈希值对应的位置，若为空直接插入，不为空踢出原元素插入，再对被踢出的元素重新计算，重复该过程，直到有空位置为止。
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/cuckooFilter.png)
> 挤兑循环问题：一般会对踢出操作设一个阈值，超过阈值则认为过滤器容量不足，需要对其进行扩容，解决同一元素不断添加问题。

### 相关指令
- `CF.RESERVE {key} {capacity} [BUCKETSIZE {bucketsize}] [MAXITERATIONS {maxiterations}] [EXPANSION {expansion}]`
- `CF.ADD {key} {item}`
- `CF.ADDNX {key} {item}`
- `CF.EXISTS {key} {item}`
- `CF.COUNT {key} {item}`
- `CF.EXISTS {key} {item}`
```
127.0.0.1:6379> CF.RESERVE newCuckooFilter 1000
OK
127.0.0.1:6379> cf.add newCuckooFilter 'ssswd@163.com'
(integer) 1
127.0.0.1:6379> cf.add newCuckooFilter 'ssswd@163.com'
(integer) 1
127.0.0.1:6379> cf.add newCuckooFilter 'ssswd@163.com'
(integer) 1
127.0.0.1:6379> CF.DEL newCuckooFilter 'ssswd@163.com'
(integer) 1
127.0.0.1:6379> CF.exists newCuckooFilter 'ssswd@163.com'
(integer) 1
127.0.0.1:6379> CF.DEL newCuckooFilter 'ssswd@163.com'
(integer) 1
127.0.0.1:6379> CF.exists newCuckooFilter 'ssswd@163.com'
(integer) 1
127.0.0.1:6379> CF.DEL newCuckooFilter 'ssswd@163.com'
(integer) 1
127.0.0.1:6379> CF.exists newCuckooFilter 'ssswd@163.com'
(integer) 0
```


### 其他命令
- `DEL、EXPIRE、RENAME、TYPE、OBJECT`可以对任何键执行
- 清空数据库的键：`FLUSHDB`
- 随机返回数据库中某个键：`RANDOMKEY`
- 返回数据库数量：`DBSIZE`

- `keys`：查询所有key，由于redis单线程，查询所有keys会造成阻塞。线上可以用scan指令（增量式迭代）可能会有一定的重复。
- `scan`：无阻塞的取出指定模式的key列表，客户端去重，执行时长会比key长。属于增量式迭代的命令，可能迭代过程key被修改。

## 事务
### 事务实现
Redis通过`MULTI`(开启事务)、`EXEC`（执行指令）、`WATCH`（乐观锁监控Key）、`DISCARD`（取消事务）命令来实现事务。
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
  
事务命令优先执行：事务开始后，若客户端发送的命令为EXEC、DISCARD、WATCH、MULTI四个命令其中一个，服务器会立即执行，否则执行命令入队操作。
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/transaction.png)

- `MULTI`命令标志着事务的开始
- `EXEC`命令会让服务器立即执行事务队列语句。
- `WATCH`为一个乐观锁实现，如果事务执行前，key被改动，事务中断。

一个执行失败的例子
```
>WATCH "name"

>MULTI 
>SET "name" "peter"
>EXEC
(nil)
```
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/optiLock.jpg)

### redis 事务的ACID

- A（Atomicity): 原子性。原子性是指事务是一个不可分割的工作单位，事务中操作要么都发生，要么都不发生。
- C（Consistency): 一致性。数据库总是从一个一致性的状态转移到另一个一致性的状态。**事务执行结束后，数据库的完整性约束没有被破坏，事务执行的前后顺序都是合法数据状态。**
- I（Isolation): 隔离性。事务的隔离性是多个用户并发访问数据库时，数据库为每一个用户开启的事务，不能被其他事务所干扰，多个并发事务之间要相互隔离。保证事务执行尽可能不受其他事务影响；InnoDB默认的隔离级别是RR，RR的实现主要基于锁机制、数据的隐藏列、undo log和类next-key lock机制
- D（Durability): 持久性。持久性是指一个事务一旦被提交，它对数据库中的**数据改变是永久性**的，接下来即使数据库发生故障也不应该对其有任何影响。


redis对于ACID的支持及理解：
- 原子性：redis不保证原子性
- 一致性：由于Redis不保证事务的原子性，并会执行部分指令，因此事务的业务数据一致性也无法保证。
- 隔离性：Redis通过Watch机制，来保障数据的隔离性。**如果数据修改了，就放弃事务执行，避免事务的隔离性被破坏。**另外因为redis使用单线程的方式来执行事务(以及事务队列中的命令)。
- 持久性：Redis无论使用RDB还是AOF都无法完全保证持久性。

redis不保证原子性说明：Redis 同一个事务中如果有一条命令执行失败，其后的命令仍然会被执行，没有回滚。\
Redis的事务和一般关系型数据库事务最大的区别在于Redis不支持事务回滚机制。即使事务队列中的某个命令在执行中出现了错误，整个事务也会继续执行下去，直到事务队列中的命令执行完毕。

> Redis开发者们觉得没必要支持回滚，这样更简单便捷并且性能更好。Redis开发者觉得即使命令执行错误也应该在开发过程中就被发现而不是生产过程中。

## 持久化

### RDB持久化 （快照）
RDB是对 Redis 中的数据执行周期性的持久化，非常适合做冷备。RDB是把当前内存中的是把当前内存中的**数据集快照**写入磁盘，也就是 Snapshot 快照（数据库中所有键值对数据）。写入磁盘，也就是 Snapshot 快照（数据库中所有键值对数据）。\
RDB持久化可以手工执行，也可以根据服务器配置选项定期执行，该功能可以将某个时间点上的数据库状态保存在RDB文件中。

#### RDB文件创建与载入
- `SAVE`命令，阻塞Redis服务器进程，直到RDB文件创建完毕为止。
- `BGSAVE`命令会派生出一个子进程，然后由子进程创建RDB文件，不会阻塞主线程。为保证拷贝的数据一致性，使用了**操作系统的COW机制**。类似CopyOnWriteList的实现。

### AOF持久化
AOF持久化保存数据库的方法是将`服务器执行的命令`保存到AOF文件中。通过fsync异步将命令写到日志

持久化的三个过程：命令追加、文件写入、文件同步
- 命令追加即将执行的命令追加到AOF文件中。
- 文件写入使用缓存区实现
- 文件同步分为`always`、`everysec`、`no`三个选项。 安全级别从高到低、效率从低到高
  - `always`每次执行均写入安全性高效率低。
  - `everysec`每隔一秒子线程对AOF文件进行同步。理论只会丢失一秒数据。
  - `no`何时同步由操作系统控制，写入的速度长，因为累积了数据在缓冲区，效率与上一种类似。

> AOF重写，指的是对命令进行压缩，将RPUSH、LPOP的类似命令进行压缩，减少AOF文件大小

### 混合持久化
混合持久化：混合RDB和AOF持久化。解决单单使用AOF持久化，重启时缓存恢复速度过慢的问题

## 数据过期清理策略

### 过期键清理策略
> Redis使用惰性删除和定期删除结合的方式配合使用。

过期键的删除策略
- 定时删除，为每个过期键建立一个timer，缺点占用CPU
- 惰性删除，键获取的时候判断过期再清除，对内存不友好。
- 定期删除，即根据设定执行时长和操作频率清理，缺点难以确定。
  > Redis 底层会通过限制删除操作执行的时长和频率来减少删除操作对CPU时间的影响，默认100ms就随机抽一些设置了过期时间的key，不会扫描全部的过期键，因为开销过大。

redis在内存空间不足的时候，为了保证命中率，就会选择一定的数据淘汰策略——**内存淘汰机制（过期键的补充措施）**

### 内存淘汰机制
内存淘汰机制：八种大体上可以分为4种，`lru`（最近最少使用）、`lfu`（最少使用频率）、`random`（随机）、`ttl`（根据生存时间，快过期）。
1. `volatile-lru`：从已设置过期时间的数据集中挑选最近最少使用的数据淘汰。
2. `volatile-ttl`：从已设置过期时间的数据集中挑选将**要过期的数据淘汰**。
3. `volatile-random`：从已设置过期时间的数据集中任意选择数据淘汰。
4. `volatile-lfu`：从已设置过期时间的数据集挑选使用频率最低的数据淘汰。
5. `allkeys-lru`：从数据集中挑选最近最少使用的数据淘汰
6. `allkeys-lfu`：从数据集中挑选使用频率最低的数据淘汰。
7. `allkeys-random`：从数据集（`server.db[i].dict`）中任意选择数据淘汰
8. `no-enviction`（驱逐）：禁止驱逐数据，这也是默认策略。意思是当内存不足以容纳新入数据时，新写入操作就会报错，请求可以继续进行，线上任务也不能持续进行，采用`no-enviction`策略可以保证数据不被丢失。

### LRU实现
常规的LRU算法会维护一个双向链表，用来表示访问关系，且需要额外的存储存放 next 和 prev 指针，牺牲比较大的存储空间。

Redis的实现LRU会维护一个全局的LRU时钟，并且每个键中也有一个时钟，每次访问键的时候更新时钟值。

淘汰过程：Redis会基于`server.maxmemory_samples`配置随机选取固定数目的key，然后比较它们的lru访问时间，然后淘汰最近最久没有访问的key，`maxmemory_samples`的值越大，Redis的近似LRU算法就越接近于严格LRU算法，但是相应消耗也变高，对性能有一定影响，样本值默认为5。

## 发布订阅模型

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
发送消息
1. 将消息发送给channel频道的所有订阅者。
2. 如果有一个或者多个模式patten与channel匹配，那么将message发送给patten的订阅者。
  

### 发布订阅key事件案例
客户端1：
```
127.0.0.1:6379> PSUBSCRIBE '__key*__:*'
Reading messages... (press Ctrl-C to quit)
1) "psubscribe"
2) "__key*__:*"
3) (integer) 1
1) "pmessage"
2) "__key*__:*"
3) "__keyevent@0__:expire"
4) "xiaowang"
1) "pmessage"
2) "__key*__:*"
3) "__keyevent@0__:expired"
4) "xiaowang"
```

客户端2：
```
127.0.0.1:6379> config set notify-keyspace-events Exg
OK
127.0.0.1:6379> set xiaowang hello ex 1
```
  
相关文章：
- [Keyspace subscription event name for EXPIRE does not match documentation](https://github.com/redis/redis/issues/1855)
- [SUBSCRIBE channel](https://redis.io/commands/subscribe)

## redis实现队列
### 异步队列
list结构做队列，`rpush`生产消息，`lpop`消费消息。当`lpop`无消息的时候，程序sleep一会重试。
> 针对sleep改进，使用blpop指令，阻塞弹出消息。
  
pub/sub主题订阅者模式，可以实现`1:N`的消息队列，即生产一个消息，N个通道消费消息
> 当消费者下线后，消息可能丢失
  
### 延迟队列实现
使用`zset`实现，拿时间戳当score，消息当成key，使用`zadd`指令生产消息。
而消费者使用`zrangebyscore`来获取N秒之前数据进行轮询处理。



## 主从结构
设置主服务器指令： **`SLAVEOF 127.0.0.1 6379`**

主从复制，主要两个命令`SYNC`、`PSYNC`
- `SYNC`：主服务器开启`BGSAVE`生成RDB文件，生成之后发送从服务器，占网络资源。从服务器主进程执行载入，阻塞无法处理命令请求。
- `PSYNC`：根据主从维护的复制偏移量，是否存在偏移量之后的数据，如果存在，则进行部分重同步操作。否则执行完整重同步。

主从同步流程：
1. 建立连接：从库会和主库建立连接，从库执行 `replicaof` 并发送 `psync` 命令并告诉主库即将进行同步，主库确认回复后，主从库间就开始同步了。
2. 主库同步数据给从库：master 执行 `BGSAVE`命令生成 RDB 文件，并将文件发送给从库，**同时主库为每一个 slave 开辟一块 replication buffer 缓冲区记录从生成 RDB 文件开始收到的所有写命令**。从库保存 RDB 并清空数据库再加载 RDB 数据到内存中。
3. 发送 RDB 之后接收到的新写命令到从库：在生成 RDB 文件之后的写操作并没有记录到刚刚的 RDB 文件中，为了保证主从库数据的一致性，所以主库会在内存中使用一个叫 replication buffer 记录 RDB 文件生成后的所有写操作。并将里面的数据发送到 slave。

![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/master-salve.png)


## Sentinel 哨兵
Sentinel是Redis的一个高可用的解决方案：由一个或者多个Sentinel实例组成Sentinel系统。
> 哨兵是 Redis 的一种运行模式，它**专注于对 Redis 实例（主节点、从节点）运行状态的监控，并能够在主节点发生故障时通过一系列的机制实现选主及主从切换，实现故障转移，确保整个Redis系统的可用性**。

启动命令：
```
redis-sentinel /path/to/your/sentinel.conf
or
redis-server /path/to/your/sentinel.conf
```

### Redis 哨兵具备的能力
Redis 哨兵具备的能力有如下几个：
- **监控**：持续监控 master 、slave 是否处于预期工作状态。
- **自动切换主库**：当 Master 运行故障，哨兵启动自动故障恢复流程：从 slave 中选择一台作为新 master。
- **通知**：让 slave 执行 `replicaof` ，与新的 master 同步；并且通知客户端与新 master 建立连接。


### Redis 哨兵通信
哨兵与 master 建立通信，利用 master 提供发布/订阅机制发布自己的信息，类似的基本信息比如*身高体重、是否单身、IP、端口……*

master 有一个 `__sentinel__:hello`的专用通道，用于哨兵之间发布和订阅消息。这就好比是`__sentinel__:hello`通信群，哨兵利用 master 建立的群组发布自己的消息，同时关注其他哨兵发布的消息。

### 与Slave建立通信
Sentinel默认每十秒一次的频率，通过命令连接向被监视的主服务器发送INFO命令，通过分析INFO命令的响应，可以获得两方面的信息：
1. 主服务器master本身的新信息
2. 关于主服务器属下所有从服务器的信息。哨兵根据 master 响应的 slave 名单信息与每一个 salve 建立连接，并且根据这个连接持续监控哨兵。

### Sentinel故障转移操作
1. 当一个主服务下线时，各个Sentinel会选举一个领头Sentinel执行故障转移。主要根据Raft领头选举算法实现
2. Sentinel系统选择一个server1属下的从服务器，并将这个从服务器升级成新主服务器。从服务器的选择如下：
    1. 删除下线或断开连接的从服务。 
    2. 删除5s无回复从服务。
    3. 按照复制偏移量排名，最大的表示具有最新的数据信息。相同偏移量则按照ID从小到大选取。
3. Sentinel系统向Server1属下的从服务器发送新的复制指令，让其成为新主服务器的从服务。当复制完成，故障转移完毕。
4. Sentinel系统继续监视下线的server1，当其重新上线时设置成主服务器的从服务。
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/sentinel.jpg)


## Cluster 集群
Redis集群是Redis提供的分布式数据库方案，集群通过分片实现数据共享，并提供复制和故障转移功能。主要解决了大数据量存储导致的各种慢问题，同时也便于横向拓展。

在 Redis cluster 架构下，每个 Redis 要放开两个端口号，比如一个是 6379，另外一个就是加1w的端口号，比如 16379
> 16379 端口号是用来进行节点间通信的，也就是`cluster bus`的东西，`cluster bus`的通信，用来进行**故障检测、配置更新、故障转移授权**。
> 集群之间通过Gossip协议相互交互集群信息，最后每个节点都保存着其他节点的 slots 分配情况。

集群建立
```
// 向节点7001发送命令，将节点7001添加到7000集群
127.0.0.1:7000>CLUSTER MEET 127.0.0.1 7001

127.0.0.1:7000>CLUSTER NODES

127.0.0.1:7000>CLUSTER MEET 127.0.0.1 7002
```
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/cluster.jpg)


### 哈希槽与槽指派
**哈希槽**：Redis 集群并没有直接使用一致性哈希，而是使用了**哈希槽**(slot)的概念。没有使用Hash算法，而是使用了crc16校验算法。槽位其实就是一个个的空间的单位。
> 每个key经过crc16校验算法计算，会落在对应的哈希槽上，便可以定位到节点的redis\
> Cluster 还允许用户强制某个 key 挂在特定槽位上，通过在 key 字符串里面嵌入 `tag` 标记，这就可以强制 key 所挂在的槽位等于 `tag` 所在的槽位。手动指定`tag`保证了大数据量的缓存不会落到同一个集群节点上。

**槽指派**：Redis集群通过分片的方式保存数据库的键值对。集群的整个数据库被分成16384个槽slot
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

1. 节点数据库和单机数据库在数据库方面的一个区别是，**节点只能使用0号数据库**，而单机Redis服务器则没有这个限制。
2. 重新分片：在重新分片的过程中，集群不需要下线，并且源节点和目标节点都可以继续处理命令请求。
> 迁移过程中获取键可能会出现ASK错误（重新分片的一种临时措施）

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/askError.jpg)
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/slotReadd.jpg)


#### 一致性哈希概念
一致性哈希解决问题：定位节点用传统的`key%节点数`取模，会导致每次在新增和删除节点的时候，都要根据key的定位做大量的数据迁移。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/constant-hash.png)


查询如何定位到对应的服务器位置？
> 使用hash算法定位。正常哈希算法算出来的值都是int值，而int的最小值是-2^31，最大值是2^31-1。意味着任何通过哈希取模之后的无符号值都会在 0 ~ 2^31-1范围之间，共2^32个数

原理：
1. 由于hash值都落在0~2^32的区间，一致性Hash算法将整个哈希值空间组织成一个虚拟的圆环。
2. 服务器根据IP地址进行hash计算，定位到环上的某一点。
3. 用户的 IP 使用上面相同的函数 Hash 计算出哈希值，并确定此数据在环上的位置，从此位置沿环 顺时针行走，遇到的第一台服务器就是其应该定位到的服务器。

缺陷：Hash环的数据倾斜问题
> 当4个服务节点时，我们并不能保证4个服务节点刚好均匀的落在时钟的 12、3、6、9点上。

解决方案：设置"虚拟节点"，即在服务器IP或者主机名后加上后缀
> 如：服务器1的 IP 是 192.168.32.132，那么虚拟服务器节点在环形空间的位置就是hash("192.168.32.132#A") % 2^32\
该实现使用同时数据定位算法，只是多了一步虚拟节点到实际节点的映射，这样就解决了服务节点少时数据倾斜的问题。\
在实际应用中，通常将虚拟节点数设置为32甚至更大，因此即使很少的服务节点也能做到相对均匀的数据分布。

**一致性哈希算法并不能杜绝数据迁移**的问题，但是可以**有效避免数据的全量迁移**，需要迁移的只是更改的节点和它的上游节点它们两个节点之间的那部分数据。

相关文章：[分布式系统中一致性哈希算法](https://www.cnblogs.com/jajian/p/10896624.html)

#### 哈希槽相关问题
redis cluster为什么没有使用一致性hash算法，而是使用了哈希槽预分片？
1. 当发生扩容时候，哈希槽采用灵活的可配置映射表，可以随意组织映射到新增server上面的slot数，比一致性hash的算法更灵活方便；同时也给开发人员手工配置更大的简洁性。
2. 在数据迁移时，一致性hash 需要算哪些key是落在新增服务节点的数据，然后迁移这部分数据，哈希槽则直接将一个slot对应的数据全部迁移，算法明确以及实现更简单。

redis的hash槽为什么是16384(2^14)个卡槽，而不是65536(2^16)个？
1. 如果槽位为65536，发送心跳信息的消息头达8k，发送的心跳包过于庞大。8k的由来(集群节点发送PONG信息的时候，该消息包含了一个`myslots`的char数组，长度为`16383/8`，占用大小为`16384÷8÷1024=2kb`，若槽指定为65536则需要的大小则为8k)
2. redis的集群主节点数量基本不可能超过1000个。集群节点越多，心跳包的消息体内携带的数据越多。如果节点过1000个，也会导致网络拥堵。因此redis作者，不建议redis cluster节点数量超过1000个。 那么，对于节点数在1000以内的redis cluster集群，16384个槽位够用了。没有必要拓展到65536个。
3. 槽位越小，节点少的情况下，压缩率高。
> Redis主节点的配置信息中，它所负责的哈希槽是通过一张bitmap的形式来保存的，在传输过程中，会对bitmap进行压缩，但是如果bitmap的填充率`slots/N`很高的话(N表示节点数)，bitmap的压缩率就很低。

参考文章: [Redis 如何路由数据](https://juejin.cn/post/6959080907948949512)

### 集群下与客户端交互
Redis Cluster 属于服务端分片的方式。Redis 实例会把自己的哈希槽信息发给和它相连接的其它实例，来完成哈希槽分配信息的扩散。当实例之间相互连接后，每个实例就有所有哈希槽的映射关系了
1. 客户端收到哈希槽信息后，会把**哈希槽信息缓存在本地**。当客户端请求键值对时，会先计算键所对应的哈希槽，然后就可以给相应的实例发送请求了。
2. 当 Cluster 有实例增减或者负载均衡之后。实例可以通过互相传递消息来获得最新的哈希槽分配信息。而客户端需要通过 Redis Cluster 的「重定向机制」来重新获取新实例的访问地址。

#### MOVED错误

键命令执行步骤主要分两步：
1. **计算槽**。Redis首先需要计算键所对应的槽。根据键的有效部分使用CRC16函数计算出散列值，再取对16383的余数，使每个键都可以映射到0~16383槽范围内。如指令`127.0.0.1:6379> cluster keyslot key:test:111`
2. **槽节点查找**。Redis计算得到键对应的槽后，需要查找槽所对应的节点。集群内通过消息交换每个节点都会知道所有节点的槽信息，内部保存在`clusterState`结构中。
**若节点的槽不是当前节点，返回MOVED重定向错误。**

MOVED重定向（**负载均衡，数据已经迁移到其他实例上**）: 在集群模式下，Redis接收任何键相关命令时首先计算键对应的槽，再根据槽找出所对应的节点，如果节点是自身，则处理键命令；否则回复MOVED重定向错误，通知客户端请求正确的节点。
> **客户端还会更新本地缓存，将该 slot 与 Redis 实例对应关系更新正确。**
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/redis-move.jpg)

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/cluster-move.png)

```
// 连接redis集群 计算集群定位的值
127.0.0.1:6379> cluster keyslot key:test:1
(integer) 5191
127.0.0.1:6379> cluster nodes
cfb28ef1deee4e0fa78da86abe5d24566744411e 127.0.0.1:6379 myself,master - 0 0 10 connected
1366-4095 4097-5461 12288-13652
...

// 由于键对应槽是9252，不属于6379节点，则回复MOVED {slot} {ip} {port}格式重定向信息：
127.0.0.1:6379> set key:test:2 value-2
(error) MOVED 9252 127.0.0.1:6380
// 计算槽应该落在哪个区域
127.0.0.1:6379> cluster keyslot key:test:2
(integer) 9252
```

> 使用redis-cli命令时，可以加入-c参数支持自动重定向，简化手动发起重定向操作，如下所示：\
> redis-cli自动帮我们连接到正确的节点执行命令，这个过程是在redis-cli内部维护，实质上是client端接到MOVED信息之后再次发起请 求，并不在Redis节点中完成请求转发，如下图所示
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/redisClient-move.jpg)

```
#redis-cli -p 6379 -c
127.0.0.1:6379> set key:test:2 value-2
-> Redirected to slot [9252] located at 127.0.0.1:6380
OK
```

#### ASK 错误
ASK重定向：在线迁移槽（slot）的过程中，客户端向slot发送请求，若键对象不存在，则可能存在于目标节点，这时源节点会回复 ASK重定向异常。\
格式如下：`(error) ASK {slot} {targetIP}:{targetPort}`
> 客户端从ASK重定向异常提取出目标节点信息，发送asking命令到目标节点打开客户端连接标识，再执行键命令。如果存在则执行，不存在则返 回不存在信息\
> **ASK 错误指令并不会更新客户端缓存的哈希槽分配信息。**

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/redis-ask.png)

#### mget批量调用
hash_tag: 提供不同的键可以具备相同slot的功能，常用于Redis IO优化
> 例如在集群模式下使用`mget`等命令优化批量调用时，键列表必须具有相同的slot，否则会报错。这时可以利用hash_tag让不同的键具有相同的slot达到优化的目的。命令如下：
```
127.0.0.1:6379> cluster keyslot key:test:111
(integer) 10050
127.0.0.1:6379> cluster keyslot key:{hash_tag}:111
(integer) 2515
127.0.0.1:6379> cluster keyslot key:{hash_tag}:222
(integer) 2515

127.0.0.1:6385> mget user:10086:frends user:10086:videos
(error) CROSSSLOT Keys in request don't hash to the same slot
127.0.0.1:6385> mget user:{10086}:friends user:{10086}:videos
1) "friends"
2) "videos"

```

相关资料：
[集群之（请求路由：请求重定向(MOVED)、ASK重定向）](https://blog.csdn.net/qq_41453285/article/details/106463895)

### 故障转移
复制与故障转移: 设置从节点
```
> CLUSTER REPLICATE 127.0.0.1:7001
```
集群中每个节点都会定期的向集群中的其他节点发送PING消息，以检测对方是否在线。\
若出现疑似下线的情况，集群中的各个节点会采用 `Gossip` 协议来广播自己的状态以及自己对整个集群认知的改变，互相交换信息，以确定节点的状态。

故障转移
1. 从主节点的从节点选择一个从节点，使用**Raft领头选举方式**实现。
2. 被选择的从节点执行`SLAVEOF no one`命令，成为新的主节点。
3. 新的主节点撤销已下线节点的槽指派，并指派向自己。
4. 新的主节点在集群中发送PONG消息，通知其他节点该节点变成主节点。
5. 新主节点开始接受和处理指派槽的消息。

## 项目使用Redis的场景

MySQL 数据库对于并发的场景天然支持不好，单机支撑到 2000QPS 也开始容易报警了。 **MySQL 这类的数据库的 QPS 大概都在 1w 左右（4 核 8g）**

**redis的高性能**：比如在计算集装箱的保证金的时候，需要管理到运输合同、运输任务、车队的信息，计算需要多次查询mysql数据库动态汇总出结果。那针对相关的这种情况就可以把计算结果放缓存中，若没有出现数据变更的情况，就直接查缓存，减轻数据库压力。
  
**redis高并发**：系统高峰时间段，业务人员的业务操作比较集中，单单使用mysql数据库，在系统业务操作高峰时间数据库压力较大。

redis 分布式锁：保证集群之间的资源同步。

## 缓存一致性
### Cache Aside Pattern（旁路缓存模式）
- 写：更新 DB，然后直接删除缓存 cache 。
- 读：从 cache 中读取数据，读取到就直接返回，读取不到的话，就从 DB 中取数据返回，然后再把数据放到 cache 中。
> Cache Aside Pattern 是我们平时使用比较多的一个缓存读写模式，比较适合读请求比较多的场景。
  
为什么是删除缓存，而不是更新缓存？
> 更新缓存的代价有时候是很高，如比较复杂的缓存数据计算的场景，更新缓存的逻辑及操作造成的开销就比较大。再且如果频繁更新数据库，就要频繁的更新缓存，相当于每次更新都要double的一个程序开销，增加更新的复杂度。 而部分缓存数据，可能访问的频率不是很高，等访问的时候再更新缓存信息可以大幅的降低开销。 \
其实删除缓存，而不是更新缓存，就是一个 lazy 计算的思想。类似于redis的key过期处理策略。
> 解决更新数据库后，可能**缓存删除失败的脏数据情况**，可以使用**双删的策略**，即删缓存-更新数据库-删缓存。该策略仍然可能最后一步删除失败导致脏数据。

**数据库和缓存数据强一致场景**：
更新DB的时候同样更新cache，不过需要加一个锁/分布式锁来保证更新cache的时候不存在线程安全问题。另外做锁等待的时候可能出现大批量请求的情况，还需要做好限流来保障我们的服务运行。

延迟双删： 策略是分布式系统中存储和缓存数据保持一致性的常用策略，但它不是强一致。
```
def update_data(key, obj):
    del_cache(key)     # 删除 redis 缓存数据。
    update_db(obj)     # 更新数据库数据。
    logic_sleep(_time) # 当前逻辑延时执行。
    del_cache(key)     # 删除 redis 缓存数据。
```
> 为什么要延时呢？因为 mysql 和 redis 主从节点数据不是实时同步的，同步数据需要时间。

队列解决方案：读请求和写请求串行化，串到一个内存队列里去。设定一个数据的唯一标识，双删+更新操作是发送到队列处理。若读请求过来，发现无缓存，想更新缓存也发送到队列处理。\
该解决方案，最大的风险点在于说，可能数据更新很频繁，导致队列中积压了大量更新操作在里面，然后读请求会发生大量的超时
> 风险点方案1：加机器分摊更新请求，按照更新请求编写nginx的hash路由，路由到相同服务实例。\
> 风险点方案2：模拟线上环境，内存队列可能会挤压多少更新操作

串行化可以保证一定不会出现不一致的情况，但是它也会导致系统的吞吐量大幅度降低，用比正常情况下多几倍的机器去支撑线上的一个请求。
 
### Read/Write Through Pattern（读写穿透）
Read/Write Through Pattern 中服务端把 cache 视为主要数据存储，从中读取数据并将数据写入其中。**cache 服务负责将此数据读取和写入 DB**，**从而减轻了应用程序的职责**。

### Write Behind Pattern（异步缓存写入）
使用阿里巴巴的canal，订阅mysql的binlog日志，通过解析日志，更新缓存信息。解决上述缓存删除后，出现缓存穿透的问题。

### 缓存并发竞争问题
**多客户端同时并发写一个 key**，可能本来应该先到的数据后到了，导致数据版本错了；或者是多客户端同时获取一个 key，修改值之后再写回去，只要顺序错了，数据就错了。

解决方法：
1. 写缓存加锁
2. 缓存加版本号判断，确保写入的是最新版本。

## 缓存雪崩
缓存雪崩：指缓存由于某些原因(比如 宕机、cache服务挂了或者大量过期)整体`crash`掉了,导致大量请求到达后端数据库,从而导致数据库崩溃,整个系统崩溃,发生灾难。

针对缓存雪崩的处理措施
- 事前：Redis 高可用，主从+哨兵，Redis cluster，避免全盘崩溃。
- 事中：本地 ehcache 缓存 + hystrix 限流&降级，避免 MySQL 被打死。 **针对key值大批量过期的情况，可以设置不同的失效时间比如随机设置缓存的失效时间。**
- 事后：Redis 持久化，一旦重启，自动从磁盘上加载数据，快速恢复缓存数据
 
本地缓存+hystrix的措施 
- 保证数据库绝对不会死，限流组件确保了每秒只有多少个请求能通过。
- 对用户来说，部分请求都是可以被处理的。系统没死，对用户来说，可能就是点击几次刷不出来页面，但是多点几次，就可以刷出来了。

## 缓存穿透
缓存穿透：指缓存和数据库中都没有的数据，而有恶意攻击者不断发起请求，如发起为id为“-1”的数据或id为特别大不存在的数据，导致数据库压力过大。

解决方案1：
1. 接口层增加校验，如用户鉴权校验，id做基础校验，id<=0的直接拦截；
2. 缓存取不到的数据，在数据库中也没有取到，这时也可以将key-value对写为key-null，并设置缓存有效时间，可以设置短点如30秒，这样可以防止攻击用户反复用同一个id暴力攻击。
 
解决方案2： 使用布隆过滤器
  
## 缓存击穿
缓存击穿：缓存击穿，就是说某个key非常热点，访问非常频繁，处于集中式高并发访问的情况，当这个 key 在失效的瞬间，大量的请求就击穿了缓存，直接请求数据库，就像是在一道屏障上凿开了一个洞。

解决措施：
- 若缓存的数据是基本不会发生更新的，则可尝试将该热点数据设置为永不过期。
- 若缓存的数据更新不频繁，且缓存刷新的整个流程耗时较少的情况下，则可以采用基于 Redis、zookeeper 等分布式中间件的分布式互斥锁，或者本地互斥锁以保证仅少量的请求能请求数据库并重新构建缓存，其余线程则在锁释放后能访问到新缓存。
- 若缓存的数据更新频繁或者在缓存刷新的流程耗时较长的情况下，可以利用定时线程在缓存过期前主动地重新构建缓存或者延后缓存的过期时间，以保证所有的请求能一直访问到对应的缓存。
    

## 内存碎片
Redis 内存碎片产生比较常见的 2 个原因：
1. Redis 存储存储数据的时候向操作系统申请的内存空间可能会大于数据实际需要的存储空间。
2. 频繁修改 Redis 中的数据也会产生内存碎片。

使用`info memory` 命令即可查看 Redis 内存相关的信息。
> edis4.0-RC3 版本以后自带了内存整理，可以避免内存碎片率过大的问题。 直接通过 `config set` 命令将 `activedefrag` 配置项设置为 yes 即可。

## LRU Java实现

```
public class LRU<K, V> implements Iterable<K> {

    private Node head;
    private Node tail;
    private HashMap<K, Node> map;
    private int maxSize;

    private class Node {

        Node pre;
        Node next;
        K k;
        V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }


    public LRU(int maxSize) {

        this.maxSize = maxSize;
        this.map = new HashMap<>(maxSize * 4 / 3);

        head = new Node(null, null);
        tail = new Node(null, null);

        head.next = tail;
        tail.pre = head;
    }


    public V get(K key) {

        if (!map.containsKey(key)) {
            return null;
        }

        Node node = map.get(key);
        unlink(node);
        appendHead(node);

        return node.v;
    }


    public void put(K key, V value) {

        if (map.containsKey(key)) {
            Node node = map.get(key);
            unlink(node);
        }

        Node node = new Node(key, value);
        map.put(key, node);
        appendHead(node);

        if (map.size() > maxSize) {
            Node toRemove = removeTail();
            map.remove(toRemove.k);
        }
    }


    private void unlink(Node node) {

        Node pre = node.pre;
        Node next = node.next;

        pre.next = next;
        next.pre = pre;

        node.pre = null;
        node.next = null;
    }


    private void appendHead(Node node) {
        Node next = head.next;
        node.next = next;
        next.pre = node;
        node.pre = head;
        head.next = node;
    }


    private Node removeTail() {

        Node node = tail.pre;

        Node pre = node.pre;
        tail.pre = pre;
        pre.next = tail;

        node.pre = null;
        node.next = null;

        return node;
    }


    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {
            private Node cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public K next() {
                Node node = cur;
                cur = cur.next;
                return node.k;
            }
        };
    }
}
```

## 分布式锁

### 独立实现分布式锁
#### 加锁
通过指令SET结合过期时间一起使用，并设置过期时间，防止线程挂了导致锁未释放
```
SET key value[EX seconds][PX milliseconds][NX|XX]
```
- EX seconds: 设定过期时间，单位为秒
- PX milliseconds: 设定过期时间，单位为毫秒
- NX: 仅当key不存在时设置值
- XX: 仅当key存在时设置值

实操：
```
127.0.0.1:6379> set ad firethehole nx ex 10
OK
127.0.0.1:6379> keys *
1) "ad"
2) "mes"
127.0.0.1:6379> get ad
"firethehole"
```

##### value必须要具有唯一性
假如value不是随机字符串，而是一个固定值，那么就可能存在下面的问题：
1. 客户端1获取锁成功
2. 客户端1在某个操作上阻塞了太长时间
3. 设置的key过期了，锁自动释放了
4. 客户端2获取到了对应同一个资源的锁
5. 客户端1从阻塞中恢复过来，因为value值一样，所以执行释放锁操作时就会释放掉客户端2持有的锁，这样就会造成问题
> 简而言之，就是A线程锁过期，后序导致对锁的异常释放。

##### SET 命令的缺陷
加锁后主节点出现故障，锁数据未同步，导致加锁失败，其他节点获得锁。

具体流程 : A客户端在Redis的master节点上拿到了锁，但是这个加锁的key还没有同步到slave节点，master故障，发生故障转移，一个slave节点升级为master节点，B客户端也可以获取同个key的锁，但客户端A也已经拿到锁了，这就导致多个客户端都拿到锁。

#### 释放锁
释放锁时需要验证value值，也就是说我们在获取锁的时候需要设置一个value，**不能直接用del key这种粗暴的方式，因为直接del key任何客户端都可以进行解锁了**，所以解锁时，我们需要判断锁是否是自己的，基于value值来判断，代码如下：

使用Lua脚本的方式，尽量保证原子性。
```
public boolean releaseLock_with_lua(String key,String value) {
    String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
            "return redis.call('del',KEYS[1]) else return 0 end";
    return jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
}
```

### Redisson 分布式方案
redisson是在redis基础上实现的一套开源解决方案，提供了分布式的相关实现及RedLock的分布式锁实现。
- 该方案的缺点为假设master加锁完成后，未与slave同步便宕机了，那么就会出现加锁失效的情况

原理：生成唯一的Value，即UUID+threadId。获取锁时向一个redis集群实例发送的LUA脚本命令，解锁同理。
> 如果该客户端面对的是一个redis cluster集群，他首先会根据hash节点选择一台机器\
> lua脚本本质上的命令： `hset myLock 8743c9c0-0795-4907-87fd-6c719a6b4586:1 1`
```
<T> RFuture<T> tryLockInnerAsync(long leaseTime, TimeUnit unit, long threadId, RedisStrictCommand<T> command) {
    internalLockLeaseTime = unit.toMillis(leaseTime);
    // 获取锁时向5个redis实例发送的命令
    return commandExecutor.evalWriteAsync(getName(), LongCodec.INSTANCE, command,
              // 首先分布式锁的KEY不能存在，如果确实不存在，那么执行hset命令（hset REDLOCK_KEY uuid+threadId 1），并通过pexpire设置失效时间（也是锁的租约时间）
              "if (redis.call('exists', KEYS[1]) == 0) then " +
                  "redis.call('hset', KEYS[1], ARGV[2], 1); " +
                  "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                  "return nil; " +
              "end; " +
              // 如果分布式锁的KEY已经存在，并且value也匹配，表示是当前线程持有的锁，那么重入次数加1，并且设置失效时间
              "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
                  "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
                  "redis.call('pexpire', KEYS[1], ARGV[1]); " +
                  "return nil; " +
              "end; " +
              // 获取分布式锁的KEY的失效时间毫秒数
              "return redis.call('pttl', KEYS[1]);",
              // 这三个参数分别对应KEYS[1]，ARGV[1]和ARGV[2]
                Collections.<Object>singletonList(getName()), internalLockLeaseTime, getLockName(threadId));
}
```

#### 自动延时的看门狗机制
针对过期时间的设置，假设业务还未处理完，锁已过期，Redisson会启动监控线程查看业务执行状态，再重新设置过期时间

watch dog自动延期机制 :只要客户端1一旦加锁成功，就会启动一个watch dog看门狗，他是一个后台线程，会每隔10秒检查一下，如果客户端1还持有锁key，那么就会不断的延长锁key的生存时间。

```
    private <T> RFuture<Long> tryAcquireAsync(long waitTime, long leaseTime, TimeUnit unit, long threadId) {
        if (leaseTime != -1) {
            return tryLockInnerAsync(waitTime, leaseTime, unit, threadId, RedisCommands.EVAL_LONG);
        }
        RFuture<Long> ttlRemainingFuture = tryLockInnerAsync(waitTime,
                                                commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout(),
                                                TimeUnit.MILLISECONDS, threadId, RedisCommands.EVAL_LONG);
        ttlRemainingFuture.onComplete((ttlRemaining, e) -> {
            if (e != null) {
                return;
            }

            // lock acquired
            if (ttlRemaining == null) {
                scheduleExpirationRenewal(threadId);
            }
        });
        return ttlRemainingFuture;
    }

```
#### 相关文章
- [基于Redis的分布式锁实现](https://juejin.cn/post/6844903830442737671#heading-10)
- [Redisson实现Redis分布式锁的原理](https://www.cnblogs.com/AnXinliang/p/10019389.html)


### RedissonRedLock
Redisson的锁会出现故障未同步而加锁失效问题，为了解决该问题Redis作者提出了一个RedLock算法的解决方案

首先假设Redis的架构有N个Master Redis节\
加锁步骤：
1. 获取当前时间的毫秒数
2. 按顺序尝试在N个Redis节点上获取锁，使用相同的key 作为键，随机数作为值。在尝试在每个Redis节点上获取锁时，设置一个超时时间，这个超时时间需要比总的锁的自动超时时间小。例如，自动释放时间为10秒，那么连接超时的时间可以设置为5-50毫秒。这样可以防止客户端长时间与处于故障状态的Redis节点通信时保持阻塞状态：如果一个Redis节点处于故障状态，我们需要尽快与下一个节点进行通信。
3. 客户端用当前时间减去开始加锁获取的时间为获取锁花费的总时间。如果在(N/2+1)个节点上加锁成功，且加锁花费的时间少于锁的有效时间，那么这个锁被认为是获取成功。
4. 如果锁获取成功，那么有效期为初始有效时间-加锁花费总时间
5. 如果加锁失败，那么客户端会对所有的示例节点执行解锁逻辑。

要实现分布式锁，Redis官网介绍了三个必须要保证的特性：
- 安全特性：互斥。任意时刻都只能有一个客户端能够持有锁。
- 活跃性A：无死锁。即使在持有锁的客户端崩溃，或者出现网络分区的情况下，依然能够获取锁。
- 活跃性B：容错。只要多数Redis节点是存活状态，客户端就能申请锁或释放锁。

参考文章：
- [Distributed locks with Redis(官网英文版解释)](https://redis.io/topics/distlock)
- [Redisson 实现RedLock详解](https://juejin.cn/post/6927204732704391175)

## Redis集合类型数据的统计模式
参考资料：[Redis集合类型数据的统计模式](https://blog.csdn.net/ggh0314/article/details/116941724)

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/counting-summary.png)

### 聚合统计
聚合统计指统计多个集合元素的聚合结果，包括：交集统计、差集统计、并集统计。做聚合统计主要使用 Set 类型

> 统计手机App每天的新增用户数和第二天的留存用户数，可以用一个集合记录所有登录过 App 的用户 ID，用另一个集合记录每一天登录过 App 的用户 ID。再对这两个集合做聚合统计。\
> 在统计每天的新增用户时，只用计算每日用户 Set 和累计用户 Set 的差集就行。

Set 的差集、并集和交集的计算复杂度较高，在数据量较大的情况下，如果直接执行这些计算，会导致 Redis 实例阻塞。所以，小建议是：可以从主从集群中选择一个从库，让它专门负责聚合计算，或者是把数据读取到客户端，在客户端来完成聚合统 计，这样就可以规避阻塞主库实例和其他从库实例的风险了。

### 排序统计
在 Redis 常用的 4 个集合类型中（List、Hash、Set、Sorted Set），**List 和 Sorted Set 就属于有序集合。**\
List 是按照元素进入 List 的顺序进行排序的，Sorted Set 可以根据元素的权重来排序，可以自己来决定每个元素的权重值。
> 比如说可以根据元素插入 Sorted Set 的时间确定权重值，先插入的元素权重小，后插入的元素权重大。

使用list进行排序统计的时候，如果遇到分页的场景，在分页的过程中，又有新的元素`lpush`，那么会出现分页不准确的情况。
> list 分页使用命令： `LRANGE product1 3 5`

Sorted Set 它是根据元素的实际权重来排序和获取数据的，只要分页的时候使用固定权重进行记录获取就不会出现问题。
> `ZRANGEBYSCORE comments N-9 N`

### 值状态统计
二进制值状态统计，二值状态就是指集合元素的取值就只有 0 和 1 两种。
> 在签到打卡的场景中，只用记录签到（1）或未签到（0）是非常典型的二值状态，在签到统计时，每个用户一天的签到用 1 个 bit 位就能表示，一个月（假设是 31 天）的签到情况用 31 个 bit 位就可以，而一年的签到也只需要用 365 个 bit 位，根本不用太复杂的集合类型。可以选择 Bitmap。这是 Redis 提供的扩展数据类型。

统计 1 亿个用户连续 10 天的签到情况，可以把每天的日期作为 key，每个 key 对应一个 1 亿位的 Bitmap，每一个 bit 对应一个用户当天的签到情况。对 10 个 Bitmap 做“与”操作，得到的结果也是一个 Bitmap。只有 10 天都签到的用户对应的 bit 位上的值才会是 1。可以用 `BITCOUNT` 统计下 Bitmap 中的 1 的个数，这就是**连续签到 10 天的用户总数**了。
> 每天使用 1 个 1 亿位的 Bitmap，大约占 12MB 的内存（10^8/8/1024/1024），10 天的 Bitmap 的内存开销约为 120MB，内存压力不算太大。在实际应用时，最好对 Bitmap 设置过期时间，让 Redis 自动删除不再需要的签到记录，以节省内存开销。


### 基数统计
基数统计指统计一个集合中不重复的元素个数（统计网页的 UV）。

HyperLogLog 是一种用于统计基数的数据集合类型，它的最大优势就在于，当集合元素数量非常多时，它计算基数所需的空间总是固定的，而且还很小。

统计 UV 时，用 `PFADD` 命令把访问页面的每个用户都添加到 HyperLogLog 中。
> `PFADD page1:uv user1 user2 user3 user4 user5`

用 `PFCOUNT` 命令直接获得 page1 的 UV 值了，返回 HyperLogLog 的统计结果。
> `PFCOUNT page1:uv`

HyperLogLog 的统计规则是基于概率完成的，给出的统计结果是有一定误差的，标准误算率是 0.81%。使用HyperLogLog 统计的 UV 是 100 万，但实际的 UV 可能是 101 万。虽然误差率不算大， 但是如果需要精确统计结果的话，最好还是继续用 Set 或 Hash 类型。

## 面试题
### Redis 为什么这么快？
1. 基于内存实现。Redis 是基于内存的数据库，不论读写操作都是在内存上完成的，跟磁盘数据库相比，读写的速度快非常多
2. 高效的数据结构。不同的数据类型底层使用了一种或者多种数据结构来支撑，目的就是为了追求更快的速度。
    > String->SDS、List->linkedList,zipList、Hash->zipList,hashtable、Set->hashtable,intSet、SortedSet->zipList,skipList。
3. 单线程模型。单线程指的是 Redis 键值对读写指令的执行是单线程。 Redis 是基于内存的操作，CPU 不是 Redis 的瓶颈，Redis 的瓶颈最有可能是机器内存的大小或者网络带宽。
    1. 好处: 不会因为线程创建导致的性能消耗；
    2. 好处: 避免上下文切换引起的 CPU 消耗，没有多线程切换的开销；
    3. 好处: 避免了线程之间的竞争问题，比如添加锁、释放锁、死锁等，不需要考虑各种锁问题。
    > Redis 的单线程指的是 Redis 的网络 IO （6.x 版本后网络 IO 使用多线程）以及键值对指令读写是由一个线程来执行的。\
    > 使用单线程的原因官方答案：因为 Redis 是基于内存的操作，CPU 不是 Redis 的瓶颈，Redis 的瓶颈最有可能是机器内存的大小或者网络带宽。
4. I/O 多路复用模型。Redis 采用 I/O 多路复用技术，并发处理连接。采用了 `epoll` + 自己实现的简单的事件框架。epoll 中的读、写、关闭、连接都转化成了事件，然后利用 epoll 的多路复用特性，不在 IO 上浪费时间。                                                                                                                                                                                                                         
    > Redis 线程不会阻塞在某一个特定的监听或已连接套接字上，也就是说，不会阻塞在某一个特定的客户端请求处理上。正因为此，Redis 可以同时和多个客户端连接并处理请求，从而提升并发性。
5. Redis 全局 `hash` 字典，Redis 整体就是一个哈希表来保存所有的键值对。当我们在 Redis 中创建一个键值对时，至少创建两个对象，一个对象是用做键值对的键对象，另一个是键值对的值对象。而哈希表的时间复杂度是 O(1)，只需要计算每个键的哈希值，便知道对应的Value。
    > Hash 冲突: Redis 通过链式哈希解决冲突：也就是同一个hashtable的index里面的元素使用链表保存。                                                                                                                                                                                                                                                                                                                                    
     渐进式 rehash: Redis 为了追求快，使用了两个全局哈希表。开始默认使用 「hash 表 1 」保存键值对数据，「hash 表 2」 此刻没有分配空间。
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/readwrite.png)

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/globalHash.jpg)
                                                                                                                                     
### Redis 如何实现持久化？宕机后如何恢复数据？                                                                                                                        
RDB是对 Redis 中的数据执行周期性的持久化，非常适合做冷备。有两个严重性能开销：
1. 频繁生成 RDB 文件写入磁盘，磁盘压力过大。会出现上一个 RDB 还未执行完，下一个又开始生成，陷入死循环。
2. `fork` 出 `BGSAVE` 子进程会阻塞主线程，主线程的内存越大，阻塞时间越长。
AOF持久化保存数据库的方法是将服务器执行的命令保存到AOF文件中，通过fsync异步将命令写到日志。恢复时对一个空的 Redis 实例顺序执行所有的指令，也就是「重放」，来恢复 Redis 当前实例的内存数据结构的状态。因此使用AOF恢复也比较耗时，因为要对一个个指令进行重新执行。
> Redis 提供的 AOF 配置项appendfsync写回策略直接决定 AOF 持久化功能的效率和安全性。always：同步写回、everysec：每秒写回、no： 操作系统控制
> AOF 重写机制主要用于对AOF的指令日志进行优化瘦身，将重复的操作进行归集优化，减少AOF的指令日志。

Redis 4.0 为了解决这个问题，带来了一个新的持久化选项——混合持久化。将 rdb 文件的内容和增量的 AOF 日志文件存在一起。这里的 AOF 日志不再是全量的日志，而是自持久化开始到持久化结束的这段时间发生的增量 AOF 日志

#### 在生成 RDB 期间，Redis 可以同时处理写请求么？
Redis 使用操作系统的多进程写时复制技术 **COW(Copy On Write)** 来实现快照持久化，保证数据一致性.\
Redis 在持久化时会调用 glibc 的函数fork产生一个子进程，快照持久化完全交给子进程来处理，父进程继续处理客户端请求。\
当主线程执行写指令修改数据的时候，这个数据就会复制一份副本， `BGSAVE` 子进程读取这个副本数据写到 RDB 文件。

### Redis 主从架构数据同步
Redis 提供了主从模式，通过主从复制，将数据冗余一份复制到其他 Redis 服务器。

#### 主从复制如何实现的?
同步分为三种情况：
1. 第一次主从库全量复制；
2. 主从正常运行期间的同步；
3. 主从库间网络断开重连同步。

#### 第一次同步怎么实现？
见主从主从架构章节

#### 主从正常运行期间的同步
replication_buffer：对于客户端或从库与redis通信，redis都会分配一个内存buffer进行数据交互，redis先把数据先入这个buffer中，然后再把buffer中的数据发送出去，所以主从在增量同步时，保证主从数据一致。
> 如果replication buffer写满了怎么办呢？replication buffer是为每个客户端分配的，如果写满了，无论客户端是普通客户端还是从库，只能断开跟这个客户端的连接了。这样从库全量同步失败，只能再次尝试全量同步。

当主从库完成了全量复制，它们之间就会一直维护一个网络连接，主库会通过这个连接将后续陆续收到的命令操作再同步给从库，这个过程也称为基于长连接的命令传播，使用长连接的目的就是避免频繁建立连接导致的开销。                                                                                                            
####  主从库间网络断开重连同步                                                                                                
从 Redis 2.8 开始，网络断了之后，主从库会采用增量复制的方式继续同步，只将中断期间主节点执行的写命令发送给从节点，与全量复制相比更加高效。

**`repl_backlog_buffer`**: 为了解决从库断连后找不到主从差异数据而设立的环形缓冲区，从而避免全量同步带来的性能开销。在redis.conf配置文件中可以设置大小，如果从库断开时间过长，repl_backlog_buffer环形缓冲区会被主库的写命令覆盖，那么从库重连后只能全量同步，所以repl_backlog_size配置尽量大一点可以降低从库连接后全量同步的频率。

master 使用 `master_repl_offset`记录自己写到的位置偏移量，slave 则使用 `slave_repl_offset`记录已经读取到的偏移量

当主从断开重连后，slave 会先发送 `psync` 命令给 master，同时将自己的 runID，`slave_repl_offset`发送给 master。

master 只需要把 master_repl_offset与 slave_repl_offset之间的命令同步给从库即可。
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/master-salve-offline.png)
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/redis/master-salve-offline-copy.png)
     
### 大Key与热Key
#### 大Key

大Key：通常以Key的大小和Key中成员的数量来综合判定，例如：
- Key本身的**数据量过大**：一个String类型的Key，它的值为5 MB。
- Key中的**成员数过多**：一个ZSET类型的Key，它的成员数量为10,000个。
- Key中成员的**数据量过大**：一个Hash类型的Key，它的成员数量虽然只有1,000个但这些成员的Value（值）总大小为100 MB。

**大Key引发问题**：
- 客户端执行命令的时长变慢。
- Redis内存达到maxmemory参数定义的上限引发操作阻塞或重要的Key被逐出，甚至引发内存溢出（Out Of Memory）。
- 集群架构下，分片内存使用不平衡。某个数据分片的内存使用率远超其他数据分片，无法使数据分片的内存资源达到均衡。
- 对大Key执行读请求，会使Redis实例的带宽使用率被占满，导致自身服务变慢，同时易波及相关的服务。
- 对大Key执行删除操作，易造成主库较长时间的阻塞，进而可能引发同步中断或主从切换。

**产生原因**：
- 在不适用的场景下使用Redis，易造成Key的value过大，如使用String类型的Key存放大体积二进制文件型数据；
- 业务上线前规划设计不足，没有对Key中的成员进行合理的拆分，造成个别Key中的成员数量过多；
- 未定期清理无效数据，造成如HASH类型Key中的成员持续不断地增加；
- 使用LIST类型Key的业务消费侧发生代码故障，造成对应Key的成员只增不减。

**优化措施**：
- 对大Key进行拆分：例如将含有数万成员的一个HASH Key拆分为多个HASH Key，并确保每个Key的成员数量在合理范围。在Redis集群架构中，拆分大Key能对数据分片间的内存平衡起到显著作用。
- 对大Key进行清理：将不适用Redis能力的数据存至其它存储，并在Redis中删除此类数据。
- 选用其他的数据结构去支撑，如部分场景下set可替换成布隆过滤器。


**如何删除大Key**
- 渐进式删除： hash结构 `hscan` 再 `hdel`删除，`list`、`zset`、`set`同样如此
- `UNLINK` (4.0版本以后)：在所有命名空间中把 key 删掉，立即返回，不阻塞。后台线程执行真正的释放空间的操作。 `UNLINK` 不是立即释放空间。

#### 热Key
热点Key：某一件商品被数万次点击、购买时，会形成一个较大的需求量，这种情况下就会产生一个单一的Key，这样就会引起一个热点；同理，当被大量刊发、浏览的热点新闻，热点评论等也会产生热点；另外，在服务端读数据进行访问时，往往会对数据进行分片切分，此类过程中会在某一主机Server上对相应的Key进行访问，当访问超过主机Server极限时，就会导致热点Key问题的产生。

热Key：通常以其接收到的Key被请求频率来判定，例如：
- QPS集中在特定的Key：Redis实例的总QPS（每秒查询率）为10,000，而其中一个Key的每秒访问量达到了7,000。
- 带宽使用率集中在特定的Key：对一个拥有上千个成员且总大小为1 MB的HASH Key每秒发送大量的HGETALL操作请求。
- CPU使用时间占比集中在特定的Key：对一个拥有数万个成员的Key（ZSET类型）每秒发送大量的ZRANGE操作请求。

**热Key产生原因**：预期外的访问量陡增，如突然出现的爆款商品、访问量暴涨的热点新闻、直播间某主播搞活动带来的大量刷屏点赞、游戏中某区域发生多个工会之间的战斗涉及大量玩家等。


优化措施：
- 在Redis集群架构中对热Key进行复制。即将热点Key+随机数，随机分配至Redis其他节点中。这样访问热点key的时候就不会全部命中到一台机器上了。
    > 在Redis集群架构中，由于热Key的迁移粒度问题，无法将请求分散至其他数据分片，导致单个数据分片的压力无法下降。此时，可以将对应热Key进行复制并迁移至其他数据分片，例如将热Key foo复制出3个内容完全一样的Key并名为foo2、foo3、foo4，将这三个Key迁移到其他数据分片来解决单个数据分片的热Key压力。
- 使用读写分离架构
    > 如果热Key的产生来自于读请求，您可以将实例改造成读写分离架构来降低每个数据分片的读请求压力，甚至可以不断地增加从节点。
- 服务端缓存：即将热点数据缓存至服务端的内存中。利用Redis自带的消息通知机制，保证Redis和服务端热点Key的数据一致性



定位热点Key
1. 凭借经验，进行预估：例如提前知道了某个活动的开启，那么就将此Key作为热点Key
2. 客户端收集：在操作Redis之前对数据进行统计
3. 抓包进行评估：Redis使用TCP协议与客户端进行通信，通信协议采用的是RESP，所以能进行拦截包进行解析
4. 在proxy层，对每一个 redis 请求进行收集上报
5. Redis自带命令查询：Redis4.0.4版本提供了redis-cli –hotkeys就能找出热点Key


#### 定位热Key及大Key

- 实时Top Key统计（推荐）：阿里的云数据库Redis集成了DAS的Key分析功能，可实时展示实例中的大Key和热Key信息，同时支持查看4天内的大Key和热Key历史信息。
- 通过redis-cli的bigkeys和hotkeys参数查找大Key和热Key

通过RDB文件分析
- 离线全量Key分析
- 通过redis-rdb-tools工具以定制化方式找出大Key

手动定位：
- 通过Redis内置命令对目标Key进行分析：即分别对不同数据类型进行`strlen` 、`llen`等指令判断
- 通过业务层定位热Key

日志观察：
- 通过MONITOR命令找出热Key：Redis的MONITOR命令能够忠实地打印Redis中的所有请求，包括时间信息、Client信息、命令以及Key信息。

#### 参考资料
- [发现并处理Redis的大Key和热Key](https://help.aliyun.com/document_detail/353223.html)
- [关于Redis热点key的一些思考](http://modouxiansheng.top/2019/07/10/%E4%B8%8D%E5%AD%A6%E6%97%A0%E6%95%B0-%E5%85%B3%E4%BA%8ERedis%E7%83%AD%E7%82%B9key%E7%9A%84%E4%B8%80%E4%BA%9B%E6%80%9D%E8%80%83-2019/)
