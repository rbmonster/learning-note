<a name="index">**Index**</a>

<a href="#0">Redis 基础</a>  
&emsp;<a href="#1">1. 基本数据结构</a>  
&emsp;&emsp;<a href="#2">1.1. String 字符串</a>  
&emsp;&emsp;&emsp;<a href="#3">1.1.1. 底层结构</a>  
&emsp;&emsp;&emsp;<a href="#4">1.1.2. 相关指令</a>  
&emsp;&emsp;<a href="#5">1.2. List 列表</a>  
&emsp;&emsp;&emsp;<a href="#6">1.2.1. 底层结构</a>  
&emsp;&emsp;&emsp;<a href="#7">1.2.2. 相关指令</a>  
&emsp;&emsp;<a href="#8">1.3. Hash 哈希 k-v</a>  
&emsp;&emsp;&emsp;<a href="#9">1.3.1. 底层结构</a>  
&emsp;&emsp;&emsp;<a href="#10">1.3.2. hash冲突</a>  
&emsp;&emsp;&emsp;<a href="#11">1.3.3. rehash与渐进式rehash</a>  
&emsp;&emsp;&emsp;<a href="#12">1.3.4. 相关指令</a>  
&emsp;&emsp;<a href="#13">1.4. 集合对象(set)</a>  
&emsp;&emsp;&emsp;<a href="#14">1.4.1. 底层结构</a>  
&emsp;&emsp;&emsp;<a href="#15">1.4.2. 相关指令 </a>  
&emsp;&emsp;<a href="#16">1.5. 有序集合 zset</a>  
&emsp;&emsp;&emsp;<a href="#17">1.5.1. 底层结构</a>  
&emsp;&emsp;&emsp;<a href="#18">1.5.2. 相关指令</a>  
&emsp;&emsp;&emsp;<a href="#19">1.5.3. 保存固定行数的lua</a>  
&emsp;&emsp;<a href="#20">1.6. HyperLogLog </a>  
&emsp;&emsp;&emsp;<a href="#21">1.6.1. 基数计数的演进</a>  
&emsp;&emsp;&emsp;<a href="#22">1.6.2. 应用场景</a>  
&emsp;&emsp;&emsp;<a href="#23">1.6.3. 相关指令</a>  
&emsp;&emsp;<a href="#24">1.7. GeoHash </a>  
&emsp;&emsp;&emsp;<a href="#25">1.7.1. 使用注意场景</a>  
&emsp;&emsp;&emsp;<a href="#26">1.7.2. 相关指令</a>  
&emsp;&emsp;<a href="#27">1.8. 布隆过滤器 bloomFilter</a>  
&emsp;&emsp;&emsp;<a href="#28">1.8.1. 应用场景</a>  
&emsp;&emsp;&emsp;<a href="#29">1.8.2. 相关指令</a>  
&emsp;&emsp;<a href="#30">1.9. 其他命令</a>  
&emsp;<a href="#31">2. 事务</a>  
&emsp;<a href="#32">3. 持久化</a>  
&emsp;&emsp;<a href="#33">3.1. RDB持久化 （快照）</a>  
&emsp;&emsp;&emsp;<a href="#34">3.1.1. RDB文件创建与载入</a>  
&emsp;&emsp;<a href="#35">3.2. AOF持久化</a>  
&emsp;&emsp;<a href="#36">3.3. 混合持久化</a>  
&emsp;<a href="#37">4. 数据过期清理策略</a>  
&emsp;&emsp;<a href="#38">4.1. 过期键清理策略</a>  
&emsp;&emsp;<a href="#39">4.2. 内存淘汰机制</a>  
&emsp;&emsp;<a href="#40">4.3. LRU实现</a>  
&emsp;<a href="#41">5. 发布订阅模型</a>  
&emsp;&emsp;<a href="#42">5.1. 发布订阅key事件案例</a>  
&emsp;<a href="#43">6. redis实现队列</a>  
&emsp;&emsp;<a href="#44">6.1. 异步队列</a>  
&emsp;&emsp;<a href="#45">6.2. 延迟队列实现</a>  
&emsp;<a href="#46">7. 主从结构</a>  
&emsp;<a href="#47">8. Sentinel 哨兵</a>  
&emsp;&emsp;<a href="#48">8.1. Redis 哨兵通信</a>  
&emsp;&emsp;<a href="#49">8.2. 与Slave建立通信</a>  
&emsp;&emsp;<a href="#50">8.3. Redis 哨兵具备的能力</a>  
&emsp;&emsp;<a href="#51">8.4. Sentinel故障转移操作</a>  
&emsp;<a href="#52">9. Cluster 集群</a>  
&emsp;&emsp;<a href="#53">9.1. 集群下与客户端交互</a>  
&emsp;&emsp;&emsp;<a href="#54">9.1.1. MOVED错误</a>  
&emsp;&emsp;&emsp;<a href="#55">9.1.2. ASK 错误</a>  
&emsp;&emsp;&emsp;<a href="#56">9.1.3. mget批量调用</a>  
&emsp;&emsp;<a href="#57">9.2. 哈希槽 槽指派</a>  
&emsp;&emsp;&emsp;<a href="#58">9.2.1. 哈希槽</a>  
&emsp;&emsp;<a href="#59">9.3. 故障转移</a>  
&emsp;&emsp;<a href="#60">9.4. 一致性哈希</a>  
&emsp;&emsp;&emsp;<a href="#61">9.4.1. 缺陷：Hash环的数据倾斜问题</a>  
&emsp;<a href="#62">10. 项目使用Redis的场景</a>  
&emsp;<a href="#63">11. 缓存一致性</a>  
&emsp;&emsp;<a href="#64">11.1. 允许缓存与数据库存在偶尔不一致</a>  
&emsp;&emsp;&emsp;<a href="#65">11.1.1. Cache Aside Pattern（旁路缓存模式）</a>  
&emsp;&emsp;<a href="#66">11.2. Write Behind Pattern（异步缓存写入）</a>  
&emsp;<a href="#67">12. 缓存雪崩</a>  
&emsp;<a href="#68">13. 缓存穿透</a>  
&emsp;<a href="#69">14. 缓存击穿</a>  
&emsp;<a href="#70">15. LRU 实现</a>  
&emsp;<a href="#71">16. 分布式锁</a>  
&emsp;&emsp;<a href="#72">16.1. 独立实现分布式锁</a>  
&emsp;&emsp;&emsp;<a href="#73">16.1.1. 加锁</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#74">16.1.1.1. value必须要具有唯一性</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#75">16.1.1.2. SET 命令的缺陷</a>  
&emsp;&emsp;&emsp;<a href="#76">16.1.2. 释放锁</a>  
&emsp;&emsp;<a href="#77">16.2. Redisson 分布式方案</a>  
&emsp;&emsp;&emsp;<a href="#78">16.2.1. 自动延时的看门狗机制</a>  
&emsp;&emsp;&emsp;<a href="#79">16.2.2. 相关文章</a>  
&emsp;<a href="#80">17. 面试题</a>  
&emsp;&emsp;<a href="#81">17.1. Redis 为什么这么快？</a>  
&emsp;&emsp;<a href="#82">17.2. Redis 如何实现持久化？宕机后如何恢复数据？                                                                                                                        </a>  
&emsp;&emsp;&emsp;<a href="#83">17.2.1. 在生成 RDB 期间，Redis 可以同时处理写请求么？</a>  
&emsp;&emsp;<a href="#84">17.3. Redis 主从架构数据同步</a>  
&emsp;&emsp;&emsp;<a href="#85">17.3.1. 主从复制如何实现的?</a>  
&emsp;&emsp;&emsp;<a href="#86">17.3.2. 第一次同步怎么实现？</a>  
&emsp;&emsp;<a href="#87">17.4. 主从正常运行期间的同步</a>  
&emsp;&emsp;<a href="#88">17.5.  主从库间网络断开重连同步                                                                                                </a>  
&emsp;&emsp;<a href="#89">17.6. Redis热点Key</a>  
&emsp;&emsp;&emsp;<a href="#90">17.6.1. 如何解决热点Key访问过多，超过某一个主机Server的情况？</a>  
&emsp;&emsp;&emsp;<a href="#91">17.6.2. 定位热点Key</a>  
# <a name="0">Redis 基础</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
缓存基本思想：CPU Cache 缓存的是内存数据用于解决 CPU 处理速度和内存不匹配的问题，内存缓存的是硬盘数据用于解决硬盘访问速度过慢的问题。为了避免用户在请求数据的时候获取速度过于缓慢，所以我们在数据库之上增加了缓存这一层来弥补。

推荐一下一篇很顶的文章：[Redis 面霸篇：从高频问题透视核心原理](https://mp.weixin.qq.com/s/wrrXz4GoILd5hsbrYACTmA)
> 本文也参考了很多改文章资料
## <a name="1">基本数据结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/data-structure.jpg)

### <a name="2">String 字符串</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用场景：如博客的文章数量，粉丝数量。
#### <a name="3">底层结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
底层结构为简单动态字符串SDS（simple dynamic String）。\
1. SDS 中 len 保存这字符串的长度，O(1) 时间复杂度查询字符串长度信息。
2. 针对缓存频繁修改的情况：SDS分配内存不仅会分配需要的空间，还会分配额外的空间。
    - 小于1MB的SDS每次分配与len属性同样大小的空间
    - 大于1MB的每次分配1MB
3. **使用惰性释放策略**：不立即使用内存重分配来回收缩短后多出来的字节，而是使用free属性，记录字节数量

编码方式：
- embstr编码：保存的是一个字符串值，且长度<=39，则字符串对象使用的是embstr编码方式保存
- raw编码： 对embstr字符串执行任何修改命令时，程序会转换编码为raw
- 中文默认占三个字符。
> 优先使用embstr编码的原因：embstr方式在内存分配时仅会调用一次内存分配函数，而raw会调用两次。embstr保存在一块连续内存在。
#### <a name="4">相关指令</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
相关指令：
 - set 'key' 'value'
 - get 'key'
 - append 'key' 'appendValue' 
 - strlen 'key'  查长度
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
### <a name="5">List 列表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用场景：比如twitter的关注列表，粉丝列表等都可以用Redis的list结构来实现。
#### <a name="6">底层结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Redis中的列表list，在版本3.2之前，列表底层的编码是ziplist和linkedlist实现的，但是在版本3.2之后，重新引入 quicklist，列表的底层都由quicklist实现。

quickList是一个ziplist组成的linkedlist双向链表。每个节点使用ziplist来保存数据，它将 linkedlist 按段切分，每一段使用 ziplist 来紧凑存储，多个 ziplist 之间使用双向指针串接起来。
- ziplist 是一个特殊的双向链表,特殊之处在于没有维护双向指针:prev next；而是存储上一个 entry的长度和 当前entry的长度，通过长度推算下一个元素在什么地方。
- ziplist使用连续的内存块。
- linkedList 便于在表的两端进行push和pop操作，在插入节点上复杂度很低，但是它的内存开销比较大。
  - 它在每个节点上除了要保存数据之外，还要额外保存两个指针；
  - 其次，双向链表的各个节点是单独的内存块，地址不连续，节点多了容易产生内存碎片。

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/ziplist.jpg)


![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/quicklist.png)


> 旧的数据规则\
> 当满足下面两条件时，使用ziplist。一条不满足即使用linkedlist
> 1. 列表对象保存的所有字符串元素的长度都小于64字节。
> 2. 列表对象保存的元素数量小于512个。

#### <a name="7">相关指令</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
相关指令：
- rpush 'key' 'value1' 'value2' ... // 数据推入表尾
- lpush 'key' 'value1' 'value2' ... // 数据推入表头
- lpop 'key' //表头弹出数据
- rpop 'key' // 表尾弹出数据
- llen 'key' // 查看长度
- lindex 'key' 0  //定位列表相关元素的值 
- blpop key1...keyN timeout  // 阻塞弹出，超时返回nil
- brpop key1...keyN timeout  // 阻塞弹出，超时返回nil
- brpoplpush llist testlist 10 // 取出最后一个元素，并插入到另外一个列表的头部； 如果列表没有元素阻塞
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
```
    
### <a name="8">Hash 哈希 k-v</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="9">底层结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
哈希对象的编码可以是 ziplist（压缩列表） 或hashtable
- ziplist会先保存键再保存值，因此键与值总是靠在一起，其中键的方向为压缩列表的表头方向。
- 通过 "数组 + 链表" 的链地址法来解决部分 **哈希冲突**

编码转换：同时以下条件的哈希对象使用ziplist编码，否则使用hashtable
1. 哈希对象保存的所有字符串元素的长度都小于64字节。
2. 哈希对象保存的元素数量小于512个。


#### <a name="10">hash冲突</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Redis 的哈希表使用链地址法解决hash冲突，即冲突的位置上使用单链表的接口连接，解决冲突的问题。

#### <a name="11">rehash与渐进式rehash</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

哈希表的负载因子：
> 负载因子 = 哈希表已保存的节点/哈希表大小\

哈希表的负载因子用来判断是否需要对哈希表进行扩容或者收缩，扩容及收缩操作可以通过rehash实现

**扩容**:
1. 服务器没有执行BGSAVE或BGREWRITEAOF命令，且哈希表负载因子大于等于1
2. 服务器执行BGSAVE或BGREWRITEAOF命令，且哈希表负载因子大于等于5
> 扩容根据执行BGSAVE或BGREWRITEAOF命令是否执行，是因为上述两命令都是开启子线程进行操作，而操作系统正常都使用COW（Copy-On-Write）技术优化子线程效率。避免子线程运行时进行扩容，可以避免不必要的写操作，进而节省内存。

**收缩**：当哈希表负载因子小于0.1时，对哈希表进行收缩操作。

扩容过程：redis的hash使用了两个全局哈希表。开始默认使用「hashtable 0」保存键值对数据，「hashtable 1」 此刻没有分配空间。触发扩容时
1. 系统给「hashtable 1」分配 「hashtable 0」.used*2的大小，取大于等于2的n次方幂的值
2. 将「hashtable 0 」的数据重新映射拷贝到 「hashtable 1」中；
3. 释放「hashtable 0」的空间。                                                                                                                                           
将`hashtable 0 ` 的数据重新映射到 `hashtable 1 `的过程中并不是一次性的，这样会造成 Redis 阻塞，无法提供服务。而是采用了渐进式 rehash，每次处理客户端请求hashtable执行增删改查操作时，顺带将节点rehash到`hashtable 1` 中。

> rehash进行期间，字典会同时操作`hashtable 0`与`hashtable 1`，如查找就要在两个表中查找，而新增只操作到新表
#### <a name="12">相关指令</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
相关指令：
- hset 'hashName' 'key' 'value' // 添加元素
- hget 'hashName' 'key'
- hdel 'hashName' 'key'
- hlen 'hashName'
- hgetall 'hashName' // 获取所有元素，依次按照k-v的形式展示

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
    
### <a name="13">集合对象(set)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用场景：在博客的设计中，可以非常方便的实现如共同关注、共同喜好、二度好友等功能
#### <a name="14">底层结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
集合对象的编码可以是intset 或者 hashtable
> intset整数集合作为底层实现，包含的所有元素都被保存在整数集合里面。

编码转换条件：同时满足以下两条件时，对象使用intset编码否则使用hashtable
- 集合对象保存的所有元素都是整数值。
- 集合对象保存的元素数量不超过512个。

#### <a name="15">相关指令 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
相关指令：
- sadd 'setName' 'key' 'value1' 'value2' ...
- scard 'setName'  // 返回长度
- sismember 'setName' 'key' // 查看元素是否存在
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
### <a name="16">有序集合 zset</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用场景： 打赏排行榜

#### <a name="17">底层结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
有序集合的编码可以是ziplist或者skiplist
- ziplist按分值从小到大的进行排序，分值小的元素放在靠近表头方向，对象在前值在后，两者紧凑。
- skiplist编码的有序集合使用zset结构作为底层实现，一个zset结构同时包含一个字典和跳跃表
  
编码转换条件：满足以下两个条件使用ziplist，否则skiplist
- 有序集合保存的元素数量小于128个。
- 有序集合保存的所有元素成员的长度都小于64个字节。

skipList 跳跃表是一种有序数据结构，它通过在每个节点中维持多个指向其他节点的指针，从而达到快速访问节点的目的。
跳表在链表的基础上，增加了多层级索引，通过索引位置的几个跳转，实现数据的快速定位。
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/skiplist.png)

#### <a name="18">相关指令</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
相关指令
- zadd 'zsetName' 'score' 'key'
- zcount 'zsetName' 'scoreMin' 'scoreMax'   // 计算范围内的有的值
- zcard 'zsetName'  // 计算zset元素的数量
- zrem 'zsetName' 'key'  // 删除zset 里面的key
- zrangebyscore delay 0  1606996111  // 获取按score 范围内的key
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

#### <a name="19">保存固定行数的lua</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[Redis + Lua 实现 sorted set 集合保证固定数量的数据，并保留新数据剔除旧数据](https://blog.csdn.net/cainiao1412/article/details/107483286)
  
### <a name="20">HyperLogLog </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
是做基数统计的redis对象，故不是集合，不会保存元数据，只记录数量而不是数值。
#### <a name="21">基数计数的演进</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
第一阶段：使用一般集合或数据结构来处理如HashSet或B+树，
> B 树最大的优势就是插入和查找效率很高，但是并没有节省内存。**数据量过大**便会导致内存占用过高。

第二阶段：BitMap
> 通过一个 bit 数组来存储特定数据的一种数据结构，每一个 bit 位都能独立包含信息，bit 是数据的最小存储单位，因此能大量节省空间。\
> 如果要统计 1 亿 个数据的基数值，大约需要的内存：10000_0000/ 8/ 1024/ 1024 ≈ 12 M, 如果用 32 bit 的 int 代表 每一个 统计的数据，大约需要内存：32 * 100_000_000/ 8/ 1024/ 1024 ≈ 381 M

第三阶段：概率算法
> HyperLogLog 的表现是惊人的，上面我们简单计算过用 bitmap 存储 1 个亿 统计数据大概需要 12 M 内存，而在 Redis 中实现的 HyperLoglog 也只需要 12 K 内存，在 标准误差 0.81% 的前提下，能够统计 2^64 个数据！
  
#### <a name="22">应用场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 统计系统中每个按钮或功能的使用情况
- 统计注册 IP 数
- 统计每日访问 IP 数
- 统计页面实时 UV 数
- 统计在线用户数
- 统计用户每天搜索不同词条的个数

#### <a name="23">相关指令</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- pfadd 'keyName'  'value1' 'value2' ... // 添加值到某个集合
- pfcount 'keyName'   // 统计值
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

### <a name="24">GeoHash </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
GeoHash是用来存储地图经纬度，进而简化距离计算的一种redis对象。

GeoHash 算法将 二维的经纬度 数据映射到 一维 的整数，这样所有的元素都将在挂载到一条线上，距离靠近的二维坐标映射到一维后的点之间距离也会很接近。
- 核心思想就是把整个地球看成是一个二维的平面，然后把这个平面不断地等分成一个一个小的方格，每一个 坐标元素都位于其中的 唯一一个方格 中，等分之后的 方格越小，那么坐标也就 越精确。每个表格使用编码表示。
- 通过上面的思想，能够把任意坐标变成一串二进制的编码
  
在 Redis 中，经纬度使用 52 位的整数进行编码，放进了 zset 里面，zset 的 value 是元素的 key，score 是 GeoHash 的 52 位整数值。zset 的 score 虽然是浮点数，但是对于 52 位的整数值来说，它可以无损存储。
- 应用场景：附近的人、附近的餐厅、共享单车（周围的车）
#### <a name="25">使用注意场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 如果使用 Redis 的 Geo 数据结构，它们将 全部放在一个 zset 集合中。在 Redis 的集群环境中，集合可能会从一个节点迁移到另一个节点，如果单个 key 的数据过大，会对集群的迁移工作造成较大的影响，在集群环境中单个 key 对应的数据量不宜超过 1M，否则会导致集群迁移出现卡顿现象，影响线上服务的正常运行。所以，这里建议 Geo 的数据使用 单独的 Redis 实例部署，不使用集群环境。\
> 如果数据量过亿甚至更大，就需要对 Geo 数据进行拆分，按国家拆分、按省拆分，按市拆分，在人口特大城市甚至可以按区拆分。这样就可以显著降低单个 zset 集合的大小。

#### <a name="26">相关指令</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- geoadd 'geoKeyName'  '纬度' '经度' 'key'
- geodist 'geoKeyName' '地点key1' '地点key2' km(单位)   // 计算两点距离
- geopos 'geoKeyName' 'key'    // 显示key对应经纬度
- georadiusbymember  'geoKeyName' 'key' 20 km withdist count 3 asc   // 计算地点 周围20公里最近的三家店显示带距离
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

### <a name="27">布隆过滤器 bloomFilter</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
布隆过滤器 本质上 是由长度为 m 的位向量或位列表（仅包含 0 或 1 位值的列表）组成，最初所有的值均设置为 0
- 向布隆过滤器中添加数据时，会使用 多个 hash 函数对 key 进行运算，然后对位数组长度进行取模运算得到一个位置，每个 hash 函数都会算得一个不同的位置。再把位数组的这几个位置都置为 1 就完成了 add 操作。
- 判断数据是否存在时，同样使用多个hash函数计算key，只要有一个位为 0，说明key不存在。但是都是1，并不能说明key必定存在，可能位置都是其他元素添加导致的，因此说存在一定的误判率。
- 布隆过滤器有两关键的参数，一个是元素大小，一个是误差率。当误差率设置越小，布隆过滤器需要的空间越大。
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/bloomFilter.png)
  
数据结构： bitmap 比特位的集合。bitmap是一个以比特为基本单位的数组，如一个int类型32个比特，那我们使用比特来应用就可以节省很大的空间。

相关资料：
[JavaGuide-bloom-filter](https://github.com/Snailclimb/JavaGuide/blob/master/docs/dataStructures-algorithms/data-structure/bloom-filter.md)

#### <a name="28">应用场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 大数据判断是否存在：这就可以实现出上述的去重功能，如果你的服务器内存足够大的话，那么使用 HashMap 可能是一个不错的解决方案，理论上时间复杂度可以达到 O(1 的级别，但是当数据量起来之后，还是只能考虑布隆过滤器。
- 解决缓存穿透：我们经常会把一些热点数据放在 Redis 中当作缓存，例如产品详情。通常一个请求过来之后我们会先查询缓存，而不用直接读取数据库，这是提升性能最简单也是最普遍的做法，但是 如果一直请求一个不存在的缓存，那么此时一定不存在缓存，那就会有 大量请求直接打到数据库 上，造成 缓存穿透，布隆过滤器也可以用来解决此类问题。
> 布隆过滤器有一个可以预判误判率的公式，查询缓存可能误判的名单存在，进行正常的查询。
- 爬虫/ 邮箱等系统的过滤：平时不知道你有没有注意到有一些正常的邮件也会被放进垃圾邮件目录中，这就是使用布隆过滤器 误判 导致的。 
- 应用介绍：在查询缓存的前面加一层布隆过滤器的过滤判断，判断缓存是否存在。
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/cacheQueryBloomFilter.jpg)

 
#### <a name="29">相关指令</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- bf.add  'bfName'  'value'   //添加元素
- bf.exists   'bfName'  'value'   //判断元素是否存在。
- bf.madd 'bfName'  'value' 'value'  //批量添加
- bf.mexists 'bfName'  'value' 'value'  // 批量判断存在
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
 
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/cacheQueryNormal.jpg)
  

### <a name="30">其他命令</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- DEL、EXPIRE、RENAME、TYPE、OBJECT可以对任何键执行
- 清空数据库的键:FLUSHDB
- 随机返回数据库中某个键：RANDOMKEY
- 返回数据库数量：DBSIZE

- keys 查询所有key，由于redis单线程，查询所有keys会造成阻塞。线上可以用scan指令（增量式迭代）可能会有一定的重复。
- scan 无阻塞的取出指定模式的key列表，客户端去重，执行时长会比key长。属于增量式迭代的命令，可能迭代过程key被修改。

## <a name="31">事务</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Redis通过MULTI(开启事务)、EXEC（执行指令）、WATCH（乐观锁监控Key）、DISCARD（取消事务）命令来实现事务。
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
  
事务开始后，若客户端发送的命令为EXEC、DISCARD、WATCH、MULTI四个命令其中一个，服务器会立即执行，否则执行命令入队操作。
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/transaction.png)

- MULTI命令标志着事务的开始
- EXEC命令会让服务器立即执行事务队列语句。
- WATCH为一个乐观锁实现，如果事务执行前，key被改动，事务中断。

一个执行失败的例子
```
>WATCH "name"

>MULTI 
>SET "name" "peter"
>EXEC
(nil)
```
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/optiLock.jpg)

redis 事务的ACID
- 原子性：事务的多个操作当成一个整体来执行，要么全部执行，要么都不执行。
- 一致性：事务执行前是“一致”的，执行后也是“一致”的。“一致”指的是符合数据库本身的定义和要求，没有包含非法或无效的错误数据。
- 隔离性：并发执行和串行执行结果一致。Redis事务总是以串行执行，因此保证了隔离性。
- 耐久性：一个事务执行完毕，结果会被保存到硬盘中，停机不丢失。
  
redis不保证原子性：Redis 同一个事务中如果有一条命令执行失败，其后的命令仍然会被执行，没有回滚
> Redis开发者们觉得没必要支持回滚，这样更简单便捷并且性能更好。Redis开发者觉得即使命令执行错误也应该在开发过程中就被发现而不是生产过程中。
  
## <a name="32">持久化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="33">RDB持久化 （快照）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
RDB是对 Redis 中的数据执行周期性的持久化，非常适合做冷备。\
RDB持久化可以手工执行，也可以根据服务器配置选项定期执行，该功能可以将某个时间点上的数据库状态保存在RDB文件中。

#### <a name="34">RDB文件创建与载入</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- SAVE命令，阻塞Redis服务器进程，直到RDB文件创建完毕为止。
- BGSAVE命令会派生出一个子进程，然后由子进程创建RDB文件，不会阻塞主线程。为保证拷贝的数据一致性，使用了操作系统的COW机制。类似CopyOnWriteList的实现。

### <a name="35">AOF持久化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
AOF持久化保存数据库的方法是将服务器执行的命令保存到AOF文件中。通过fsync异步将命令写到日志

持久化的三个过程：命令追加、文件写入、文件同步
- 命令追加即将执行的命令追加到AOF文件中。
- 文件写入使用缓存区实现
- 文件同步分为always、everysec、no三个选项。 安全级别从高到低、效率从低到高
  - always每次执行均写入安全性高效率低。
  - everysec每隔一秒子线程对AOF文件进行同步。理论只会丢失一秒数据。
  - no 何时同步由操作系统控制，写入的速度长，因为累积了数据在缓冲区，效率与上一种类似。

> AOF重写，指的是对命令进行压缩，将RPUSH、LPOP的类似命令进行压缩，减少AOF文件大小

### <a name="36">混合持久化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
混合持久化：混合RDB和AOF持久化。解决单单使用AOF持久化，重启时缓存恢复速度过慢的问题

## <a name="37">数据过期清理策略</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="38">过期键清理策略</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
过期键的删除策略
- 定时删除，为每个过期键建立一个timer，缺点占用CPU
- 惰性删除，键获取的时候判断过期再清除，对内存不友好。
- Redis使用惰性删除和定期删除结合的方式配合使用。
- 定期删除，即根据设定执行时长和操作频率清理，缺点难以确定。
  > Redis 底层会通过限制删除操作执行的时长和频率来减少删除操作对CPU时间的影响，默认100ms就随机抽一些设置了过期时间的key，不会扫描全部的过期键，因为开销过大。

redis在内存空间不足的时候，为了保证命中率，就会选择一定的数据淘汰策略——**内存淘汰机制（过期键的补充措施）**

### <a name="39">内存淘汰机制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
内存淘汰机制：八种大体上可以分为4中，lru（最近最少使用）、lfu（最少使用频率）、random（随机）、ttl（根据生存时间，快过期）。
1. volatile-lru：从已设置过期时间的数据集中挑选最近最少使用的数据淘汰。
2. volatile-ttl：从已设置过期时间的数据集中挑选将要过期的数据淘汰。
3. volatile-random：从已设置过期时间的数据集中任意选择数据淘汰。
4. volatile-lfu：从已设置过期时间的数据集挑选使用频率最低的数据淘汰。
5. allkeys-lru：从数据集中挑选最近最少使用的数据淘汰
6. allkeys-lfu：从数据集中挑选使用频率最低的数据淘汰。
7. allkeys-random：从数据集（server.db[i].dict）中任意选择数据淘汰
8. no-enviction（驱逐）：禁止驱逐数据，这也是默认策略。意思是当内存不足以容纳新入数据时，新写入操作就会报错，请求可以继续进行，线上任务也不能持续进行，采用no-enviction策略可以保证数据不被丢失。


### <a name="40">LRU实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
常规的LRU算法会维护一个双向链表，用来表示访问关系，且需要额外的存储存放 next 和 prev 指针，牺牲比较大的存储空间。

Redis的实现LRU会维护一个全局的LRU时钟，并且每个键中也有一个时钟，每次访问键的时候更新时钟值。

淘汰过程：Redis会基于server.maxmemory_samples配置选取固定数目的key，然后比较它们的lru访问时间，然后淘汰最近最久没有访问的key，maxmemory_samples的值越大，Redis的近似LRU算法就越接近于严格LRU算法，但是相应消耗也变高，对性能有一定影响，样本值默认为5。

## <a name="41">发布订阅模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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
  

### <a name="42">发布订阅key事件案例</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
客户端1：
```
127.0.0.1:6379> PSUBSCRIBE '__key*__:*'
Reading messages... (press Ctrl-C to quit)
1) "psubscribe"
2) "__key*__:*"
3) (integer) 1
1) "pmessage"
2) "__key*__:*"
3) "__keyevent@0__:expired"
4) "aa"
```

客户端2：
```
127.0.0.1:6379> PSUBSCRIBE '__key*__:*'
Reading messages... (press Ctrl-C to quit)
1) "psubscribe"
2) "__key*__:*"
3) (integer) 1
1) "pmessage"
2) "__key*__:*"
3) "__keyevent@0__:expired"
4) "aa"
```
  
相关文章：
- [Keyspace subscription event name for EXPIRE does not match documentation](https://github.com/redis/redis/issues/1855)
- [SUBSCRIBE channel](https://redis.io/commands/subscribe)

## <a name="43">redis实现队列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="44">异步队列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
list结构做队列，rpush生产消息，lpop消费消息。当lpop无消息的时候，程序sleep一会重试。
  - 针对sleep改进，使用blpop指令，阻塞弹出消息。
  
pub/sub主题订阅者模式，可以实现1：N的消息队列，即生产一个消息，N个通道消费消息
- 当消费者下线后，消息可能丢失
  
### <a name="45">延迟队列实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用zSet实现，拿时间戳当score，消息当成key，使用zadd指令生产消息。
而消费者使用zrangebyscore来获取N秒之前数据进行轮询处理。



## <a name="46">主从结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
设置主服务器指令： **SLAVEOF 127.0.0.1 6379**

主从复制，主要两个命令SYNC、PSYNC
- SYNC：主服务器开启BGSAVE生成RDB文件，生成之后发送从服务器，占网络资源。从服务器主进程执行载入，阻塞无法处理命令请求。
- PSYNC：根据主从维护的复制偏移量，是否存在偏移量之后的数据，如果存在，则进行部分重同步操作。否则执行完整重同步。


## <a name="47">Sentinel 哨兵</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Sentinel是Redis的一个高可用的解决方案：由一个或者多个Sentinel实例组成Sentinel系统。

启动命令：
```
redis-sentinel /path/to/your/sentinel.conf
or
redis-server /path/to/your/sentinel.conf
```
### <a name="48">Redis 哨兵通信</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
哨兵与 master 建立通信，利用 master 提供发布/订阅机制发布自己的信息，比如身高体重、是否单身、IP、端口……

master 有一个 `__sentinel__:hello` 的专用通道，用于哨兵之间发布和订阅消息。这就好比是` __sentinel__:hello` 通信群，哨兵利用 master 建立的群组发布自己的消息，同时关注其他哨兵发布的消息。

### <a name="49">与Slave建立通信</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Sentinel默认每十秒一次的频率，通过命令连接向被监视的主服务器发送INFO命令，通过分析INFO命令的响应，可以获得两方面的信息：
1. 主服务器master本身的新信息
2. 关于主服务器属下所有从服务器的信息。哨兵根据 master 响应的 slave 名单信息与每一个 salve 建立连接，并且根据这个连接持续监控哨兵。

### <a name="50">Redis 哨兵具备的能力</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 监控：持续监控 master 、slave 是否处于预期工作状态。
2. 自动切换主库：当 Master 运行故障，哨兵启动自动故障恢复流程：从 slave 中选择一台作为新 master。
3. 通知：让 slave 执行 replicaof ，与新的 master 同步；并且通知客户端与新 master 建立连接。

### <a name="51">Sentinel故障转移操作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 当一个主服务下线时，各个Sentinel会选举一个领头Sentinel执行故障转移。
  - 主要根据Raft领头选举算法实现
2. Sentinel系统选择一个server1属下的从服务器，并将这个从服务器升级成新主服务器。从服务器的选择如下：
    1. 删除下线或断开连接的从服务。 
    2. 删除5s无回复从服务。
    3. 按照复制偏移量排名，最大的表示具有最新的数据信息。相同偏移量则按照ID从小到大选取。
3. Sentinel系统向Server1属下的从服务器发送新的复制指令，让其成为新主服务器的从服务。当复制完成，故障转移完毕。
4. Sentinel系统继续监视下线的server1，当其重新上线时设置成主服务器的从服务。
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/sentinel.jpg)


## <a name="52">Cluster 集群</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Redis集群是Redis提供的分布式数据库方案，集群通过分片实现数据共享，并提供复制和故障转移功能。主要解决了大数据量存储导致的各种慢问题，同时也便于横向拓展。

在 Redis cluster 架构下，每个 Redis 要放开两个端口号，比如一个是 6379，另外一个就是 加 1w 的端口号，比如 16379
> 16379 端口号是用来进行节点间通信的，也就是 cluster bus 的东西，cluster bus 的通信，用来进行故障检测、配置更新、故障转移授权。

集群建立
```
// 向节点7001发送命令，将节点7001添加到7000集群
127.0.0.1:7000>CLUSTER MEET 127.0.0.1 7001

127.0.0.1:7000>CLUSTER NODES

127.0.0.1:7000>CLUSTER MEET 127.0.0.1 7002
```
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/cluster.jpg)

### <a name="53">集群下与客户端交互</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="54">MOVED错误</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

键命令执行步骤主要分两步：
1. 计算槽。Redis首先需要计算键所对应的槽。根据键的有效部分使用CRC16函数计算出散列值，再取对16383的余数，使每个键都可以映射到0~16383槽范围内。如指令`127.0.0.1:6379> cluster keyslot key:test:111`
2. 槽节点查找。Redis计算得到键对应的槽后，需要查找槽所对应的节点。集群内通过消息交换每个节点都会知道所有节点的槽信息，内部保存在clusterState结构中。
3. 若节点的槽不是当前节点，返回MOVED重定向错误。

MOVED重定向: 在集群模式下，Redis接收任何键相关命令时首先计算键对应的槽，再根据槽找出所对应的节点，如果节点是自身，则处理键命令；否则回复MOVED重定向错误，通知客户端请求正确的节点。
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/redis-move.jpg)

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
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/redisClient-move.jpg)

```
#redis-cli -p 6379 -c
127.0.0.1:6379> set key:test:2 value-2
-> Redirected to slot [9252] located at 127.0.0.1:6380
OK
```

#### <a name="55">ASK 错误</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
ASK重定向：在线迁移槽（slot）的过程中，客户端向slot发送请求，若键对象不存在，则可能存在于目标节点，这时源节点会回复 ASK重定向异常。\
格式如下：(error) ASK {slot} {targetIP}:{targetPort}
> 客户端从ASK重定向异常提取出目标节点信息，发送asking命令到目标节点打开客户端连接标识，再执行键命令。如果存在则执行，不存在则返 回不存在信息
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/redis-ask.png)

#### <a name="56">mget批量调用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
hash_tag: 提供不同的键可以具备相同slot的功能，常用于Redis IO优化
> 例如在集群模式下使用mget等命令优化批量调用时，键列表必须具有相同的slot，否则会报错。这时可以利用hash_tag让不同的键具有相同的slot达到优化的目的。命令如下：
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

### <a name="57">哈希槽 槽指派</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
槽指派：Redis集群通过分片的方式保存数据库的键值对。集群的整个数据库被分成16384个槽slot
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
  - 迁移过程中获取键可能会出现ASK错误（重新分片的一种临时措施）
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/askError.jpg)
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/slotReadd.jpg)

#### <a name="58">哈希槽</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Redis 集群并没有直接使用一致性哈希，而是使用了哈希槽 （slot） 的概念。没有使用Hash算法，而是使用了crc16校验算法。槽位其实就是一个个的空间的单位。
> 每个key经过crc16校验算法计算，会落在对应的哈希槽上，便可以定位到节点的redis
### <a name="59">故障转移</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
复制与故障转移\
设置从节点
```
> CLUSTER REPLICATE 127.0.0.1:7001
```
集群中每个节点都会定期的向集群中的其他节点发送PING消息，以检测对方是否在线。\
若出现疑似下线的情况，集群中的各个节点会采用 Gossip 协议来广播自己的状态以及自己对整个集群认知的改变，互相交换信息，以确定节点的状态。

故障转移
1. 从主节点的从节点选择一个从节点，使用Raft领头选举方式实现。
2. 被选择的从节点执行SLAVEOF no one命令，成为新的主节点。
3. 新的主节点撤销已下线节点的槽指派，并指派向自己。
4. 新的主节点在集群中发送PONG消息，通知其他节点该节点变成主节点。
5. 新主节点开始接受和处理指派槽的消息。

### <a name="60">一致性哈希</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
一致性哈希解决问题：定位节点用传统的key%节点数取模，会导致每次在新增和删除节点的时候，都要根据key的定位做大量的数据迁移。

查询如何定位到对应的服务器位置？
> 使用hash算法定位。正常哈希算法算出来的值都是int值，而int 的最小值是-2^31，最大值是2^31-1。意味着任何通过哈希取模之后的无符号值都会在 0 ~ 2^31-1范围之间，共2^32个数

原理：
1. 由于hash值都落在0~2^32的区间，一致性Hash算法将整个哈希值空间组织成一个虚拟的圆环。
2. 服务器根据IP地址进行hash计算，定位到环上的某一点。
3. 用户的 IP 使用上面相同的函数 Hash 计算出哈希值，并确定此数据在环上的位置，从此位置沿环 顺时针行走，遇到的第一台服务器就是其应该定位到的服务器。

#### <a name="61">缺陷：Hash环的数据倾斜问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
当4个服务节点时，我们并不能保证4个服务节点刚好均匀的落在时钟的 12、3、6、9点上。

解决方案：设置"虚拟节点"，即在服务器IP或者主机名后加上后缀
- 如 服务器1 的 IP 是 192.168.32.132，那么虚拟服务器节点在环形空间的位置就是hash("192.168.32.132#A") % 2^32
- 使用同时数据定位算法，只是多了一步虚拟节点到实际节点的映射，这样就解决了服务节点少时数据倾斜的问题。
- 在实际应用中，通常将虚拟节点数设置为32甚至更大，因此即使很少的服务节点也能做到相对均匀的数据分布。
  
一致性哈希算法并不能杜绝数据迁移的问题，但是可以有效避免数据的全量迁移，需要迁移的只是更改的节点和它的上游节点它们两个节点之间的那部分数据。

相关文章：[分布式系统中一致性哈希算法](https://www.cnblogs.com/jajian/p/10896624.html)


## <a name="62">项目使用Redis的场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MySQL 数据库对于并发的场景天然支持不好，单机支撑到 2000QPS 也开始容易报警了。
> MySQL 这类的数据库的 QPS 大概都在 1w 左右（4 核 8g）

**redis的高性能**：比如在计算集装箱的保证金的时候，需要管理到运输合同、运输任务、车队的信息，计算需要多次查询mysql数据库动态汇总出结果。那针对相关的这种情况就可以把计算结果放缓存中，若没有出现数据变更的情况，就直接查缓存，减轻数据库压力。
> 针对系统物料、规格、港口、车队人员信息这种日常不经常更新的数据，也可以放在缓存中。
  
**redis高并发**：系统在下午高峰时间段，外部车队、船公司、内部的工厂及业务人员的业务操作比较集中，单单使用mysql数据库，在系统业务操作高峰时间数据库压力较大。

redis 分布式锁：保证集群之间的资源同步。

## <a name="63">缓存一致性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="64">允许缓存与数据库存在偶尔不一致</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="65">Cache Aside Pattern（旁路缓存模式）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 写：更新 DB，然后直接删除缓存 cache 。
- 读：从 cache 中读取数据，读取到就直接返回，读取不到的话，就从 DB 中取数据返回，然后再把数据放到 cache 中。
> Cache Aside Pattern 是我们平时使用比较多的一个缓存读写模式，比较适合读请求比较多的场景。
  
为什么是删除缓存，而不是更新缓存？
- 更新缓存的代价有时候是很高，如比较复杂的缓存数据计算的场景，更新缓存的逻辑及操作造成的开销就比较大。再且如果频繁更新数据库，就要频繁的更新缓存，相当于每次更新都要double的一个程序开销，增加更新的复杂度。
- 而部分缓存数据，可能访问的频率不是很高，等访问的时候再更新缓存信息可以大幅的降低开销。
- 其实删除缓存，而不是更新缓存，就是一个 lazy 计算的思想。类似于redis的key过期处理策略。
> 解决更新数据库后，可能缓存删除失败的脏数据情况，可以使用双删的策略，即删缓存-更新数据库-删缓存。该策略仍然可能最后一步删除失败导致脏数据。

队列解决方案：读请求和写请求串行化，串到一个内存队列里去。设定一个数据的唯一标识，双删+更新操作是发送到队列处理。若读请求过来，发现无缓存，想更新缓存也发送到队列处理。\
该解决方案，最大的风险点在于说，可能数据更新很频繁，导致队列中积压了大量更新操作在里面，然后读请求会发生大量的超时
> 风险点方案1：加机器分摊更新请求，按照更新请求编写nginx的hash路由，路由到相同服务实例。\
> 风险点方案2：模拟线上环境，内存队列可能会挤压多少更新操作

串行化可以保证一定不会出现不一致的情况，但是它也会导致系统的吞吐量大幅度降低，用比正常情况下多几倍的机器去支撑线上的一个请求。
 
### <a name="66">Write Behind Pattern（异步缓存写入）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用阿里巴巴的canal，订阅mysql的binlog日志，通过解析日志，更新缓存信息。解决上述缓存删除后，出现缓存穿透的问题。

## <a name="67">缓存雪崩</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
缓存雪崩：指缓存由于某些原因(比如 宕机、cache服务挂了或者大量过期)整体crash掉了,导致大量请求到达后端数据库,从而导致数据库崩溃,整个系统崩溃,发生灾难。

针对缓存雪崩的处理措施
- 事前：Redis 高可用，主从+哨兵，Redis cluster，避免全盘崩溃。
- 事中：本地 ehcache 缓存 + hystrix 限流&降级，避免 MySQL 被打死。
  - 针对key值大批量过期的情况，可以设置不同的失效时间比如随机设置缓存的失效时间。
- 事后：Redis 持久化，一旦重启，自动从磁盘上加载数据，快速恢复缓存数据
 
本地缓存+hystrix的措施 
- 保证数据库绝对不会死，限流组件确保了每秒只有多少个请求能通过。
- 对用户来说，部分请求都是可以被处理的。系统没死，对用户来说，可能就是点击几次刷不出来页面，但是多点几次，就可以刷出来了。


## <a name="68">缓存穿透</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
缓存穿透：指缓存和数据库中都没有的数据，而有恶意攻击者不断发起请求，如发起为id为“-1”的数据或id为特别大不存在的数据，导致数据库压力过大。

解决方案：
1. 接口层增加校验，如用户鉴权校验，id做基础校验，id<=0的直接拦截；
2. 缓存取不到的数据，在数据库中也没有取到，这时也可以将key-value对写为key-null，并设置缓存有效时间，可以设置短点如30秒，这样可以防止攻击用户反复用同一个id暴力攻击。
 
解决方案2： 使用布隆过滤器
  
## <a name="69">缓存击穿</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
缓存击穿：缓存击穿，就是说某个 key 非常热点，访问非常频繁，处于集中式高并发访问的情况，当这个 key 在失效的瞬间，大量的请求就击穿了缓存，直接请求数据库，就像是在一道屏障上凿开了一个洞。

解决措施：
- 若缓存的数据是基本不会发生更新的，则可尝试将该热点数据设置为永不过期。
- 若缓存的数据更新不频繁，且缓存刷新的整个流程耗时较少的情况下，则可以采用基于 Redis、zookeeper 等分布式中间件的分布式互斥锁，或者本地互斥锁以保证仅少量的请求能请求数据库并重新构建缓存，其余线程则在锁释放后能访问到新缓存。
- 若缓存的数据更新频繁或者在缓存刷新的流程耗时较长的情况下，可以利用定时线程在缓存过期前主动地重新构建缓存或者延后缓存的过期时间，以保证所有的请求能一直访问到对应的缓存。
    
## <a name="70">LRU 实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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

## <a name="71">分布式锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="72">独立实现分布式锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="73">加锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

##### <a name="74">value必须要具有唯一性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
假如value不是随机字符串，而是一个固定值，那么就可能存在下面的问题：
1. 客户端1获取锁成功
2. 客户端1在某个操作上阻塞了太长时间
3. 设置的key过期了，锁自动释放了
4. 客户端2获取到了对应同一个资源的锁
5. 客户端1从阻塞中恢复过来，因为value值一样，所以执行释放锁操作时就会释放掉客户端2持有的锁，这样就会造成问题
> 简而言之，就是A线程锁过期，后序导致对锁的异常释放。

##### <a name="75">SET 命令的缺陷</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
加锁后主节点出现故障，锁数据未同步，导致加锁失败，其他节点获得锁。

具体流程 : A客户端在Redis的master节点上拿到了锁，但是这个加锁的key还没有同步到slave节点，master故障，发生故障转移，一个slave节点升级为master节点，B客户端也可以获取同个key的锁，但客户端A也已经拿到锁了，这就导致多个客户端都拿到锁。

#### <a name="76">释放锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
释放锁时需要验证value值，也就是说我们在获取锁的时候需要设置一个value，**不能直接用del key这种粗暴的方式，因为直接del key任何客户端都可以进行解锁了**，所以解锁时，我们需要判断锁是否是自己的，基于value值来判断，代码如下：

使用Lua脚本的方式，尽量保证原子性。
```
public boolean releaseLock_with_lua(String key,String value) {
    String luaScript = "if redis.call('get',KEYS[1]) == ARGV[1] then " +
            "return redis.call('del',KEYS[1]) else return 0 end";
    return jedis.eval(luaScript, Collections.singletonList(key), Collections.singletonList(value)).equals(1L);
}
```

### <a name="77">Redisson 分布式方案</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
redisson是在redis基础上实现的一套开源解决方案，提供了分布式的相关实现及RedLock的分布式锁实现。

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

#### <a name="78">自动延时的看门狗机制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
针对过期时间的设置，假设业务还未处理完，锁已过期，Redisson会启动监控线程查看业务执行状态，再重新设置过期时间

watch dog自动延期机制 :只要客户端1一旦加锁成功，就会启动一个watch dog看门狗，他是一个后台线程，会每隔10秒检查一下，如果客户端1还持有锁key，那么就会不断的延长锁key的生存时间。

#### <a name="79">相关文章</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [基于Redis的分布式锁实现](https://juejin.cn/post/6844903830442737671#heading-10)
- [Redisson实现Redis分布式锁的原理](https://www.cnblogs.com/AnXinliang/p/10019389.html)

## <a name="80">面试题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="81">Redis 为什么这么快？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 基于内存实现。Redis 是基于内存的数据库，不论读写操作都是在内存上完成的，跟磁盘数据库相比，读写的速度快非常多
2. 高效的数据结构。不同的数据类型底层使用了一种或者多种数据结构来支撑，目的就是为了追求更快的速度。
    > String->SDS、List->linkedList,zipList、Hash->zipList,hashtable、Set->hashtable,intSet、SortedSet->zipList,skipList。
3. 单线程模型。单线程指的是 Redis 键值对读写指令的执行是单线程。 Redis 是基于内存的操作，CPU 不是 Redis 的瓶颈，Redis 的瓶颈最有可能是机器内存的大小或者网络带宽。
    1. 好处: 不会因为线程创建导致的性能消耗；
    2. 好处: 避免上下文切换引起的 CPU 消耗，没有多线程切换的开销；
    3. 好处: 避免了线程之间的竞争问题，比如添加锁、释放锁、死锁等，不需要考虑各种锁问题。
    > Redis 的单线程指的是 Redis 的网络 IO （6.x 版本后网络 IO 使用多线程）以及键值对指令读写是由一个线程来执行的。
4. I/O 多路复用模型。Redis 采用 I/O 多路复用技术，并发处理连接。采用了 epoll + 自己实现的简单的事件框架。epoll 中的读、写、关闭、连接都转化成了事件，然后利用 epoll 的多路复用特性，不在 IO 上浪费时间。                                                                                                                                                                                                                         
    > Redis 线程不会阻塞在某一个特定的监听或已连接套接字上，也就是说，不会阻塞在某一个特定的客户端请求处理上。正因为此，Redis 可以同时和多个客户端连接并处理请求，从而提升并发性。
5. Redis 全局 hash 字典，Redis 整体就是一个 哈希表来保存所有的键值对。当我们在 Redis 中创建一个键值对时，至少创建两个对象，一个对象是用做键值对的键对象，另一个是键值对的值对象。而哈希表的时间复杂度是 O(1)，只需要计算每个键的哈希值，便知道对应的Value。
    > Hash 冲突: Redis 通过链式哈希解决冲突：也就是同一个hashtable的index里面的元素使用链表保存。                                                                                                                                                                                                                                                                                                                                    
     渐进式 rehash: Redis 为了追求快，使用了两个全局哈希表。开始默认使用 「hash 表 1 」保存键值对数据，「hash 表 2」 此刻没有分配空间。
![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/readwrite.png)

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/globalHash.jpg)
                                                                                                                                     
### <a name="82">Redis 如何实现持久化？宕机后如何恢复数据？                                                                                                                        </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
RDB是对 Redis 中的数据执行周期性的持久化，非常适合做冷备。有两个严重性能开销：
    1. 频繁生成 RDB 文件写入磁盘，磁盘压力过大。会出现上一个 RDB 还未执行完，下一个又开始生成，陷入死循环。
    2. fork 出 bgsave 子进程会阻塞主线程，主线程的内存越大，阻塞时间越长。
AOF持久化保存数据库的方法是将服务器执行的命令保存到AOF文件中，通过fsync异步将命令写到日志。恢复时对一个空的 Redis 实例顺序执行所有的指令，也就是「重放」，来恢复 Redis 当前实例的内存数据结构的状态。因此使用AOF恢复也比较耗时，因为要对一个个指令进行重新执行。
> Redis 提供的 AOF 配置项appendfsync写回策略直接决定 AOF 持久化功能的效率和安全性。always：同步写回、everysec：每秒写回、no： 操作系统控制
> AOF 重写机制主要用于对AOF的指令日志进行优化瘦身，将重复的操作进行归集优化，减少AOF的指令日志。


Redis 4.0 为了解决这个问题，带来了一个新的持久化选项——混合持久化。将 rdb 文件的内容和增量的 AOF 日志文件存在一起。这里的 AOF 日志不再是全量的日志，而是自持久化开始到持久化结束的这段时间发生的增量 AOF 日志

#### <a name="83">在生成 RDB 期间，Redis 可以同时处理写请求么？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Redis 使用操作系统的多进程写时复制技术 **COW(Copy On Write)** 来实现快照持久化，保证数据一致性.\
Redis 在持久化时会调用 glibc 的函数fork产生一个子进程，快照持久化完全交给子进程来处理，父进程继续处理客户端请求。\
当主线程执行写指令修改数据的时候，这个数据就会复制一份副本， bgsave 子进程读取这个副本数据写到 RDB 文件。

### <a name="84">Redis 主从架构数据同步</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Redis 提供了主从模式，通过主从复制，将数据冗余一份复制到其他 Redis 服务器。

#### <a name="85">主从复制如何实现的?</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
同步分为三种情况：
1. 第一次主从库全量复制；
2. 主从正常运行期间的同步；
3. 主从库间网络断开重连同步。

#### <a name="86">第一次同步怎么实现？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 建立连接：从库会和主库建立连接，从库执行 replicaof 并发送 psync 命令并告诉主库即将进行同步，主库确认回复后，主从库间就开始同步了。
2. 主库同步数据给从库：master 执行 bgsave命令生成 RDB 文件，并将文件发送给从库，**同时主库为每一个 slave 开辟一块 replication buffer 缓冲区记录从生成 RDB 文件开始收到的所有写命令**。从库保存 RDB 并清空数据库再加载 RDB 数据到内存中。
3. 发送 RDB 之后接收到的新写命令到从库：在生成 RDB 文件之后的写操作并没有记录到刚刚的 RDB 文件中，为了保证主从库数据的一致性，所以主库会在内存中使用一个叫 replication buffer 记录 RDB 文件生成后的所有写操作。并将里面的数据发送到 slave。

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/master-salve.png)

### <a name="87">主从正常运行期间的同步</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
replication_buffer：对于客户端或从库与redis通信，redis都会分配一个内存buffer进行数据交互，redis先把数据先入这个buffer中，然后再把buffer中的数据发送出去，所以主从在增量同步时，保证主从数据一致。
> 如果replication buffer写满了怎么办呢？replication buffer是为每个客户端分配的，如果写满了，无论客户端是普通客户端还是从库，只能断开跟这个客户端的连接了。这样从库全量同步失败，只能再次尝试全量同步。

当主从库完成了全量复制，它们之间就会一直维护一个网络连接，主库会通过这个连接将后续陆续收到的命令操作再同步给从库，这个过程也称为基于长连接的命令传播，使用长连接的目的就是避免频繁建立连接导致的开销。                                                                                                            
### <a name="88"> 主从库间网络断开重连同步                                                                                                </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
从 Redis 2.8 开始，网络断了之后，主从库会采用增量复制的方式继续同步，只将中断期间主节点执行的写命令发送给从节点，与全量复制相比更加高效。

**repl_backlog_buffer**: 为了解决从库断连后找不到主从差异数据而设立的环形缓冲区，从而避免全量同步带来的性能开销。在redis.conf配置文件中可以设置大小，如果从库断开时间过长，repl_backlog_buffer环形缓冲区会被主库的写命令覆盖，那么从库重连后只能全量同步，所以repl_backlog_size配置尽量大一点可以降低从库连接后全量同步的频率。

master 使用 `master_repl_offset`记录自己写到的位置偏移量，slave 则使用 `slave_repl_offset`记录已经读取到的偏移量

当主从断开重连后，slave 会先发送 psync 命令给 master，同时将自己的 runID，`slave_repl_offset`发送给 master。

master 只需要把 master_repl_offset与 slave_repl_offset之间的命令同步给从库即可。
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/master-salve-offline.png)
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/redis/master-salve-offline-copy.png)
     
### <a name="89">Redis热点Key</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
热点Key：某一件商品被数万次点击、购买时，会形成一个较大的需求量，这种情况下就会产生一个单一的Key，这样就会引起一个热点；同理，当被大量刊发、浏览的热点新闻，热点评论等也会产生热点；另外，在服务端读数据进行访问时，往往会对数据进行分片切分，此类过程中会在某一主机Server上对相应的Key进行访问，当访问超过主机Server极限时，就会导致热点Key问题的产生。

#### <a name="90">如何解决热点Key访问过多，超过某一个主机Server的情况？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 服务端缓存：即将热点数据缓存至服务端的内存中。利用Redis自带的消息通知机制，保证Redis和服务端热点Key的数据一致性
2. 备份热点Key：即将热点Key+随机数，随机分配至Redis其他节点中。这样访问热点key的时候就不会全部命中到一台机器上了。
> 使用公式CRC16(key) % 16384来计算Key属于哪个槽

#### <a name="91">定位热点Key</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 凭借经验，进行预估：例如提前知道了某个活动的开启，那么就将此Key作为热点Key
2. 客户端收集：在操作Redis之前对数据进行统计
3. 抓包进行评估：Redis使用TCP协议与客户端进行通信，通信协议采用的是RESP，所以能进行拦截包进行解析
4. 在proxy层，对每一个 redis 请求进行收集上报
5. Redis自带命令查询：Redis4.0.4版本提供了redis-cli –hotkeys就能找出热点Key

参考资料：[关于Redis热点key的一些思考](http://modouxiansheng.top/2019/07/10/%E4%B8%8D%E5%AD%A6%E6%97%A0%E6%95%B0-%E5%85%B3%E4%BA%8ERedis%E7%83%AD%E7%82%B9key%E7%9A%84%E4%B8%80%E4%BA%9B%E6%80%9D%E8%80%83-2019/)