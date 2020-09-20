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
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/redis/picture/encodingCode.jpg)

### 字符串对象
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
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/redis/picture/stringCommand.jpg)


###列表对象
- 列表元素较少时使用压缩列表(ziplist)，而元素多的时候使用双链表(linkedlist)
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
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/redis/picture/listCommand.jpg)


### 哈希对象
- 哈希对象的编码可以是ziplist或hashtable
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
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/redis/picture/hashCommand.jpg)


### 集合对象
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
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/redis/picture/setCommand.jpg)


### 有序集合对象
- 有序集合的编码可以是ziplist或者skiplist
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
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/redis/picture/zsetCommand.jpg)
