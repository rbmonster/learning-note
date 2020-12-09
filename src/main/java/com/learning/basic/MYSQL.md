# MySQL

## MySQL基本架构
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/mysqlprocess.jpg)
- MySQL可以分为Server层和存储引擎层两部分
  - Server层包括连接器、查询缓存、分析器、优化器、执行器等，涵盖MySQL的大多数核心服务功能，以及所有的内置函数（如日期、时间、数学和加密函数等），所有跨存储引擎的功能都在这一层实现，比如存储过程、触发器、视图等。
  - 存储引擎层负责数据的存储和提取。其架构模式是插件式的，支持InnoDB、MyISAM、Memory等多个存储引擎。现在最常用的存储引擎是InnoDB，它从MySQL5.5.5版本开始成为了默认存储引擎

### Server层基本架构
1. 连接器：登陆数据库的连接验证，完成经典的TCP握手后，连接器就要开始认证你的身份。  ```mysql -h$ip -P$port -u$user -p```
2. 查询缓存：连接建立完成之后，会到查询缓存中查询数据。查询缓存的存储默认key为查询语句，而value为结果。查询缓存弊大于利，因为只要有表更新，这个表相关的缓存就会被清除。
3. 分析器：主要做语法解析，并判断语法是否合规。
4. 优化器：对语法的执行流程进行优化，决定使用哪个索引。
   - ```mysql> select * from t1 join t2 using(ID) where t1.c=10 and t2.d=20;```
   - > 既可以先从表t1里面取出c=10的记录的ID值，再根据ID值关联到表t2，再判断t2里面d的值是否等于20。也可以先从表t2里面取出d=20的记录的ID值，再根据ID值关联到t1，再判断t1里面c的值是否等于10。
     这两种执行方法的逻辑结果是一样的，但是执行的效率会有不同，而优化器的作用就是决定选择效率高的方案。
5. 执行器: 负责具体语句的执行，首先判断是否有权限。```mysql> select * from T where ID=10;```
   - > - 比如我们这个例子中的表T中，ID字段没有索引，那么执行器的执行流程是这样的：
     > - 1.调用InnoDB引擎接口取这个表的第一行，判断ID值是不是10，如果不是则跳过，如果是则将这行存在结果集中；
     > - 2.调用引擎接口取“下一行”，重复相同的判断逻辑，直到取到这个表的最后一行。
     > - 3.执行器将上述遍历过程中所有满足条件的行组成的记录集作为结果集返回给客户端。

## 索引
索引的出现其实就是为了提高数据查询的效率， 就像书的目录一样。 一本500页的书，对于数据库的表而言， 索引其实就是它的“目录”。
- > 若一张表中无主键索引，mysql会默认创建一个长度为6字节的rowid主键。

### 主键索引、唯一索引、普通索引
索引分类：唯一索引，主键（聚集）索引，非聚集索引(普通索引)

#### 主键索引与普通索引区别
1. 一个表中只能有一个主键索引，但是可以有多个普通索引
2. 主键(聚集)索引存储记录是物理上连续存在，而非聚集索引是逻辑上的连续，物理存储并不连续
3. 查询区别：主要在于若执行的查询中需要较多的信息，普通索引会执行回表操作。
  - 如果语句是select * from T where ID=500， 即主键查询方式， 则只需要搜索ID这棵B+树。
  - 如果语句是select * from T where k=5， 即普通索引查询方式， 则需要先搜索k索引树， 得到ID的值为500， 再到ID索引树搜索一次。 这个过程称为回表。
回到主键索引树搜索的过程， 我们称为**回表**。
  
图解索引结构
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/indexquery.jpg)
主键长度越小， 普通索引的叶子节点就越小， 普通索引占用的空间也就越小。所以，从**性能和存储空间**方面考量， 自增主键往往是更合理的选择。



#### 唯一索引与普通索引
1. 两者查询性能差不多。
2. 主要区别在于，更新的记录目标页不在内存中时。普通索引更新会使用change Buffer。唯一索引，由于需要校验数据的唯一性，因此每次更新操作都需要读磁盘把数据载进内存，涉及IO操作。
- 在不影响数据一致性的前提下， InooDB会将这些更新操作缓存在change buffer中， 这样就不需要从磁盘中读入这个数据页了。 在下次查询需要访问这个数据页的时候， 将数据页读入内存， 然后执行change buffer中与这个页有关的操作。 通过这种方式就能保证这个数据逻辑的正确性
> change buffer中的操作应用到原数据页， 得到最新结果的过程称为merge。 
> 1. 访问这个数据页会触发merge。
> 2. 系统有后台线程会定期merge。 
> 3. 在数据库正常关闭（shutdown） 的过程中，也会执行merge操作。

change Buffer与redo log 区别
- change Buffer主要用于减少读磁盘的次数，在必要读磁盘时再更新数据。
- redo log 则是减少内存更新后，写磁盘的次数。

### 索引数据结构
InnoDB里面索引对应一棵B+树

#### B树
- B树和平衡二叉树稍有不同的是，B树属于多叉树又名平衡多路查找树

规律：
1. 排序方式：所有节点关键字是按递增次序排列，并遵循左小右大原则；
2. 子节点数：非叶节点的子节点数>1，且<=M ，且M>=2，空树除外（注：M阶代表一个树节点最多有多少个查找路径，M=M路,当M=2则是2叉树,M=3则是3叉）；
3. 所有叶子节点均在同一层、叶子节点除了包含了关键字和关键字记录的指针外也有指向其子节点的指针只不过其指针地址都为null对应下图最后一层节点的空格子;
4. 关键字数：枝节点的关键字数量大于等于ceil(m/2)-1个且小于等于M-1个（注：ceil()是个朝正无穷方向取整的函数 如ceil(1.1)结果为2);
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/Btree.jpg)

- B树插入与删除操作：https://zhuanlan.zhihu.com/p/27700617

#### B+树
与B树的区别
  1. 非叶子节点不保存关键字记录的指针，只进行数据索引，这样使得B+树每个非叶子节点所能保存的关键字大大增加；
  2. 叶子节点保存了父节点的所有关键字记录的指针，所有数据地址必须要到叶子节点才能获取到。所以每次数据查询的次数都一样；
  3. B+树叶子节点的关键字从小到大有序排列，左边结尾数据都会保存右边节点开始数据的指针。
  4. 非叶子节点的子节点数=关键字数（节点里面的关键字）
  
特点
1. B+树的层级更少：相较于B树B+每个非叶子节点存储的关键字数更多，同样大小的磁盘页可以容纳更多的节点元素。树的层级更少所以查询数据更快。（非叶子节点不保存数据）
2. B+树查询速度更稳定：每次查找都要找到子节点
3. B+树天然具备排序功能：B+树所有的叶子节点数据构成了一个有序链表，在查询大小区间的数据时候更方便，数据紧密性很高，缓存的命中率也会比B树高

B+树的插入与删除：https://www.cnblogs.com/nullzx/p/8729425.html
  - B+树的插入均在叶子节点上进行。
  - B+树删除子节点上进行，若存在于父节点，那么会删除父节点数据


#### 为什么使用B+树数据结构
使用B+树而不是二叉搜索树或者红黑树的原因是，由于存储介质的特性，磁盘本身存取就比主存慢很多，每次搜索的磁盘IO的开销过大，而B+树可以使用较少次的磁盘IO搜索到对象。
- B-Tree中一次检索最多需要h-1次I/O（根节点常驻内存），渐进复杂度为O(h)=O(logdN)。
- 红黑树这种结构，h明显要深的多。效率明显比B-Tree差很多。


### 索引规则

#### 覆盖索引
InnoDB存储引擎支持覆盖索引，即从辅助索引中就可以得到查询的记录，而不需要查询聚集索引中的记录。
- 如： ``` select id, b from t where b = xxx   (id为主键，b为索引)```
由于覆盖索引可以减少树的搜索次数（减少IO）， 显著提升查询性能， 所以使用覆盖索引是一个常用的性能优化手段。

#### 最左前缀原则
B+树这种索引结构，可以利用索引的“最左前缀”， 来定位记录。  

#### 索引下推
``` 
mysql> select * from tuser where name like '张%' and age=10 and ismale=1; 
```
mysql 5.6 后引入索引下推。
- 索引遍历过程中，对**索引中包含的字段先做判断**，直接过滤掉不满足条件的记录，减少回表次数
- 旧版本中会进行回表操作，取得相关信息再做判断。

### 索引选择规则
优化器选择索引的目的是找一个最优方案，用最小的代价执行语句。其中扫描行数、是否使用临时表、是否排序等因素都会影响优化器对索引的选择判断。


#### 导致索引失效情况
1. where语句中包含or时，可能会导致索引失效。
   - > 若or的条件中包含非索引，就会只用全表扫描的。如果or的条件两边都是索引，那么会使用index_merge的优化技术。
2. where语句中索引列使用了负向查询，可能会导致索引失效
   - > 负向查询包括：NOT、!=、<>、!<、!>、NOT IN、NOT LIKE等。
3. 对索引列进行运算，一定会导致索引失效
   - > EXPLAIN SELECT * from container_load where  materiel_id+1 = 152899293852729344
4. 在索引列上使用内置函数，一定会导致索引失效
   - `EXPLAIN SELECT *, SUBSTR(materiel_id,10) from container_load where SUBSTR(materiel_id,10) = 852729344`
5. like通配符可能会导致索引失效，未满足最左匹配原则。
6. 隐式类型转换导致的索引失效，如索引列user_id为varchar类型，使用int做条件关联。或者关联表字符集编码不一致。
7. 索引字段可以为null，使用is null或is not null时，可能会导致索引失效
   - 默认为Null的列，存在Null值会导致mysql优化器处理起来比较复杂,当命中结果数量小于40%的时候,会走索引。
8. 联合索引未满足最左匹配原则
   - > (k1,k2,k3)，相当于创建了(k1)、(k1,k2)和(k1,k2,k3)三个索引
```
  select * from t where k2=2;
  select * from t where k3=3;
  slect * from t where k2=2 and k3=3;
  // 以下这条只会部分走索引
  slect * from t where k1=1 and k3=3;
 ```

#### 采样统计储存导致走错索引
扫描行数判断，mysql使用采样统计的方式来获取索引的统计信息基数。**采样统计**的方式可以减少磁盘的IO次数
>  InnoDB默认会选择N个数据页， 统计这些页面上的不同值， 得到一个平均值， 然后乘以这个索引的页面数， 就得到了这个索引的基数。
- analyze table t 命令， 可以用来重新统计索引信息。

##### 解决索引选择异常方案
- 使用force index
- 引导Mysql使用我们期望的索引，如order by b,a limit 1 与 order by b limit 1 逻辑上能保持一致，那么就能这么修改
- 在有些场景下， 我们可以新建一个更合适的索引， 来提供给优化器做选择， 或删掉误用的索引。（较少用）


### 添加索引技巧

#### 前缀索引
定义：定义字符串的一部分作为索引。
- 前缀索引可以减少索引占用的空间，但是可能需要额外增加扫描次数。
> 使用前缀索引， 定义好长度， 就可以做到既节省空间， 又不用额外增加太多的查询成本。如使用身份证的后六位作为索引，索引的区分度好。
```
mysql> alter table SUser add index index2(email(6));
```
- 使用前缀索引很可能会损失区分度， 所以你需要预先设定一个可以接受的损失比例， 比如5%。 然后， 在返回的L4~L7中， 找出不小于 L * 95%的值， 
> 使用前缀索引就用不上覆盖索引对查询性能的优化了，对于已经找到类似字段的记录，都需要回表进行扫描。这也是你在选择是否使用前缀索引时需要考虑的一个因素。

#### 倒序存储
倒序存储：
1. 比如存储身份证时都倒序存储，而身份证的后六位区分度好，便于建立前缀索引.
2. like想要根据字段头部信息匹配的情况，可以存储一个正序，再存储一个逆序的字段
缺点：无法查询区间。
```
    mysql> select field_list from t where id_card = reverse('input_id_card_string');
```

#### hash字段存储
定义：使用Hash字段。每次插入新记录的时候， 都同时用crc32()这个函数得到校验码填到一个新字段.
 ```
    mysql> alter table t add id_card_crc int unsigned, add index(id_card_crc);
```
#### 上述添加索引方法比较
1. 直接创建完整索引， 这样可能比较占用空间；
2. 创建前缀索引， 节省空间， 但会增加查询扫描次数， 并且不能使用覆盖索引；
3. 倒序存储， 再创建前缀索引， 用于绕过字符串本身前缀的区分度不够的问题；
4. 创建hash字段索引， 查询性能稳定， 有额外的存储和计算消耗， 跟第三种方式一样， 都不支
持范围扫描


## MySQL的锁
根据加锁的范围， MySQL里面的锁大致可以分成全局锁、 表级锁和行锁三类。
### 全局锁
全局锁就是对整个数据库实例加锁。MySQL提供了一个加全局读锁的方法， 命令是Flush tables with read lock (FTWRL)。 
- 全局锁的典型使用场景是， 做全库逻辑备份。 
- 官方自带的逻辑备份工具是mysqldump。 当mysqldump使用参数–single-transaction的时候， 导数据之前就会启动一个事务， 来确保拿到一致性视图。 而由于MVCC的支持， 这个过程中数据是可以正常更新的。
   
### 表锁
MySQL里面表级别的锁有两种： 一种是表锁， 一种是元数据锁（meta data lock，MDL)。

表锁的语法是 lock tables …read/write。 
- 举个例子, 如果在某个线程A中执行lock tables t1 read, t2 write; 这个语句， 则其他线程写t1、 读写t2的语句都会被阻塞。
- InnoDB这种支持行锁的引擎， 一般不使用lock tables命令来控制并发， 毕竟锁住整个表的影响面还是太大。
  
另一类表级的锁是MDL（metadata lock)。 
- 当对一个表做增删改查操作的时候， 加MDL读锁； 
- 当要对表做结构变更操作的时候， 加MDL写锁。

**如何安全地给小表加字段？**
- 在alter table语句里面设定等待时间， 如果在这个指定的等待时间里面能够拿到MDL写锁最好， 拿不到也不要阻塞后面的业务语句， 先放弃。 之后开发人员或者DBA再通过重试命令重复这个过程。
  
### 行锁
MySQL的行锁是在引擎层由各个引擎自己实现的。 MyISAM引擎就不支持行锁，InnoDB是支持行锁的。

**InnoDB的行锁是针对索引加的锁**，不是针对记录加的锁，并且该索引不能失效，否则都会从行锁升级为表锁

添加行锁：
1. 开启事务，事务中执行更新。
2. 使用for update 数据库中的行上加一个排它锁
> select * from t lock in share mode;   
> - lock in share mode是加共享锁的形式。


#### 两阶段锁
两阶段锁协议：在InnoDB事务中， 行锁是在需要的时候才加上的， 但并不是不需要了就立刻释放， 而是要等到事务结束时才释放。 
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/linelock.jpg)
- 事务B的update语句会被阻塞， 直到事务A执行commit之后， 事务B才能继续执行
- 如果你的事务中需要锁多个行， 要把最可能造成锁冲突、 最可能影响并发度的锁尽量往后放。

### 死锁
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/deadlock.jpg)
事务A在等待事务B释放id=2的行锁， 而事务B在等待事务A释放id=1的行锁。 事务A和事务B在互相等待对方的资源释放， 就是进入了死锁状态。 

#### 死锁策略
策略一：直接进入等待，直到超时。这个超时时间可以通过参数innodb_lock_wait_timeout来设置。在InnoDB中， innodb_lock_wait_timeout的默认值是50s

策略二：发起死锁检测， 发现死锁后， 主动回滚死锁链条中的某一个事务， 让其他事务得以继续执行。 将参数innodb_deadlock_detect设置为on， 表示开启这个逻辑。
- 弊端：判断是否存在死锁的成本会随着数据量的增长，而大量消耗CPU。假设有1000个并发线程要同时更新同一行， 那么死锁检测操作就是100万这个量级的。
- 解决方案：1.确定不会出现死锁，关闭死锁检测。2.控制并发度。3.改写mysql源码。
    
### 幻读(间隙锁)
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/phantomRead.jpg)
- session A里执行了三次查询， 分别是Q1、 Q2和Q3。 它们的SQL语句相同， 都是select * from t where d=5 for update。 表示查所有d=5的行， 而且使用的是当前读， 并且加上写锁。 
- 其中， Q3读到id=1这一行的现象， 被称为“幻读”。 也就是说， 幻读指的是一个事务在前后两次查询同一个范围的时候， 后一次查询看到了前一次查询没有看到的行

幻读会导致数据一致性的问题。 锁的设计是为了保证数据的一致性。 而这个一致性， 不止是数据库内部数据状态在此刻的一致性， 还包含了数据和日志在逻辑上的一致性。
1. 在可重复读隔离级别下， 普通的查询是快照读， 是不会看到别的事务插入的数据的。 因此，幻读在“当前读”下才会出现。
2. 上面session B的修改结果， 被session A之后的select语句用“当前读”看到， 不能称为幻读。幻读仅专指“新插入的行”

![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/phantomRead2.jpg)
- 尝试解决幻读，把所有语句都上锁，查询语句改成select * from t for update。但是仍然无法解决插入新语句出现的幻读现象。

#### 如何解决幻读？
InnoDB引入新的锁， 也就是间隙锁(Gap Lock)。在一行行扫描的过程中， 不仅将给行加上了行锁， 还给行两边的空隙， 也加上了间隙锁。

间隙锁之间的冲突：跟间隙锁存在冲突关系的，是“往这个间隙中插入一个记录”这个操作。 间隙锁之间都不存在冲突关系。

间隙锁和行锁合称next-key lock， 每个next-key lock是前开后闭区间。 
- 如果用select * from t for update要把整个表所有记录锁起来， 就形成了7个next-key lock， 分别是 (-∞,0]、 (0,5]、 (5,10]、 (10,15]、 (15,20]、 (20, 25]、 (25, +supremum]。
- InnoDB给每个索引加了一个不存在的最大值supremum。

间隙锁的引入， 可能会导致同样的语句锁住更大的范围， 这其实是影响了并发度的。

#### 加锁原则
总结的加锁规则里面， 包含了两个“原则”、 两个“优化”和一个“bug”。
1. 原则1： 加锁的基本单位是next-keylock。 希望你还记得， next-keylock是前开后闭区间。
2. 原则2： 查找过程中访问到的对象才会加锁。
3. 优化1： 索引上的等值查询， 给唯一索引加锁的时候， next-keylock退化为行锁。
4. 优化2： 索引上的等值查询， 向右遍历时且最后一个值不满足等值条件的时候， next-key lock退化为间隙锁。
5. 一个bug： 唯一索引上的范围查询会访问到不满足条件的第一个值为止。

### 悲观锁
1. 设置自动提交为set autocommit=0。使用事务begin;/begin work;/start transaction; (三者选一就可以)
2. 使用for update，同样使用事务。

### 乐观锁
添加一行字段当版本号字段，更新的时候带版本号。如：
`update xx set name = aaa and version = 3 where id = 'xxx' and version = 2'`

## redo log 与 binlog


### redo log
MySQL里经常说到的WAL技术，WAL的全称是WriteAheadLogging，它的关键点就是先写日志，再写磁盘，也就是先写粉板，等不忙的时候再写账本。
- 当有一条记录需要更新的时候， InnoDB引擎就会先把记录写到redo log（粉板） 里面， 并更新内存， 这个时候更新就算完成了。 同时， InnoDB引擎会在适当的时候， 将这个操作记录更新到磁盘里面， 而这个更新往往是在系统比较空闲的时候做， 这就像打烊以后掌柜做的事。(由于磁盘连接开销大，)
- InnoDB的redo log是固定大小的， 比如可以配置为一组4个文件， 每个文件的大小是1GB， 那么这块“粉板”总共就可以记录4GB的操作。 从头开始写， 写到末尾就又回到开头循环写， 如下面这个图所示

![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/redologwrite.jpg)

redo log buffer ：redo log buffer就是一块内存， 用来先存redo日志的。 在执行事务的时候，如insert、update会先存在buffer中。等事务commit，再一起写入redo log

#### redo log 写入机制
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/redologwrite.png)
这三种状态分别是：
1. 存在redo log buffer中， 物理上是在MySQL进程内存中， 就是图中的红色部分；
2. 写到磁盘(write)， 但是没有持久化（fsync)， 物理上是在文件系统的page cache里面， 也就是图中的黄色部分；
3. 持久化到磁盘， 对应的是hard disk， 也就是图中的绿色部分。
    
为了控制redo log的写入策略， InnoDB提供了innodb_flush_log_at_trx_commit参数， 它有三种可能取值：
1. 设置为0的时候， 表示每次事务提交时都只是把redo log留在redo log buffer中;
2. 设置为1的时候， 表示每次事务提交时都将redo log直接持久化到磁盘；
3. 设置为2的时候， 表示每次事务提交时都只是把redo log写到page cache。

InnoDB写盘的三种情况：
1. InnoDB有一个后台线程， 每隔1秒， 就会把redo log buffer中的日志， 调用write写到文件系统的page cache， 然后调用fsync持久化到磁盘。
2. redo log buffer占用的空间即将达到 innodb_log_buffer_size一半的时候，后台线程会主动写盘。
3. 并行的事务提交的时候， 顺带将这个事务的redo log buffer持久化到磁盘。 

### binlog
MySQL整体来看， 其实就有两块： 一块是Server层， 它主要做的是MySQL功能层面的事情； 还有一块是引擎层， 负责存储相关的具体事宜。 上面我们聊到的粉板redo log是InnoDB引擎特有的日志， 而Server层也有自己的日志， 称为binlog（归档日志） 。

#### bin log写入机制
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/binlogwrite.jpg)
每个线程有自己binlog cache， 但是共用同一份binlog文件。
1. 图中的write， 指的就是指把日志写入到文件系统的page cache， 并没有把数据持久化到磁盘， 所以速度比较快。
2. 图中的fsync， 才是将数据持久化到磁盘的操作。 一般情况下，我们认为fsync才占磁盘的IOPS
  
bin log 三种数据格式，主要区别于在存储bin log 的格式区别  越来越多的场景要求把MySQL的binlog格式设置成row，有利于恢复数据。
1. statement 
2. row
3. mix 上面两种的混合

### 两种日志区别
1. redo log是InnoDB引擎特有的； binlog是MySQL的Server层实现的， 所有引擎都可以使用。
2. redo log是物理日志， 记录的是“在某个数据页上做了什么修改”； binlog是逻辑日志， 记录的是这个语句的原始逻辑， 比如“给ID=2这一行的c字段加1 ”。
3. redo log是循环写的， 空间固定会用完； binlog是可以追加写入的。 “追加写”是指binlog文件写到一定大小后会切换到下一个， 并不会覆盖以前的日志。
4. 事务提交的时候，一次性将事务中的sql语句（一个事物可能对应多个sql语句）按照一定的格式记录到binlog中。这里与redo log很明显的差异就是redo log并不一定是在事务提交的时候刷新到磁盘，redo log是在事务开始之后就开始逐步写入磁盘。

### 两阶段提交
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/twocommit.jpg)
两阶段提交：主要用于保证redo log 与binlog 的状态保持逻辑上一致。

图中 两个“commit”的概念：
- “commit语句”， 是指MySQL语法中， 用于提交一个事务的命令。 一般跟begin/start transaction 配对使用。
- 图中用到的这个“commit步骤”， 指的是事务提交过程中的一个小步骤， 也是最后一步。 当这个步骤执行完成后， 这个事务就提交完成了。
- “commit语句”执行的时候， 会包含“commit 步骤

崩溃后的数据恢复阶段
- 如果在更新或写入数据的过程中，机器出现崩溃。那么在机器在重启后，MySQL会首先去验证redo log的完整性，如果redo log中没有prepare状态的记录，则记录是完整的，就日记提交。如果redolog中存在prepare记录，那么就去验证这条redolog对应的binlog记录，如果这条binlog是完整的，那么完整提交redo log，否则执行回滚逻辑
- 崩溃恢复时的判断规则。
    1. 如果redo log里面的事务是完整的， 也就是已经有了commit标识， 则直接提交；
    2. 如果redo log里面的事务只有完整的prepare， 则判断对应的事务binlog是否存在并完整：
       - 如果是， 则提交事务；
       - 否则， 回滚事务。
    - 如果碰到既有prepare、 又有commit的redo log， 就直接提交；
    - 如果碰到只有prepare、 而没有commit的redo log， 就拿着XID去binlog找对应的事务。
         
 
#### 为何需要两个日志
1. 只使用binlog的话，相当于一个update语句： => binlog write ->commit ->binlog write -> commit
    - 若崩溃在binlog write的阶段，就是crash-unsafe
2. 只使用redo log，可以保证crash-safe。
    - binlog作为MySQL一开始就有的功能， 被用在了很多地方。其中， MySQL系统高可用的基础， 就是binlog复制
    - 很多公司有异构系统（比如一些数据分析系统） ， 这些系统就靠消费MySQL的binlog来更新自己的数据。 
    
    
#### 两阶段提交的实际执行流程
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/actualWrite.jpg)
WAL机制主要得益于两个方面：
1. redo log 和 binlog都是顺序写， 磁盘的顺序写比随机写速度要快；
2. 组提交机制， 可以大幅度降低磁盘的IOPS消耗。
