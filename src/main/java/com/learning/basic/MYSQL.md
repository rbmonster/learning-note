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

## redo log、undo log、binlog
### undo log
undo log主要有两个作用：回滚和多版本控制(MVCC)

在数据修改的时候，不仅记录了redo log，还记录undo log，如果因为某些原因导致事务失败或回滚了，可以用undo log进行回滚

undo log主要存储的也是逻辑日志，比如我们要insert一条数据了，那undo log会记录的一条对应的delete日志。我们要update一条记录时，它会记录一条对应相反的update记录。

MVCC: 内部使用的一致性读快照称为Read View，在不同的隔离级别下，事务启动时或者SQL语句开始时，看到的数据快照版本可能也不同，在RR、RC隔离级别下会用到 Read view。
> 当执行sql语句的时候会创建一致性视图，保证在本事务中可以做到可重复读。


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


## 实际sql的执行
一个sql 语句mysql的执行顺序：

`from -- on  -- JOIN -- where -- group by -- having -- select -- distinct -- order by -- limit`
### count(*)实现
不同引擎中的实现
- MyISAM引擎把一个表的总行数存在了磁盘上， 因此执行count(*)的时候会直接返回这个数，效率很高；
- 而InnoDB引擎就麻烦了， 它执行count(*)的时候， 需要把数据一行一行地从引擎里面读出来， 然后累积计数
  
InnoDB中Mysql对于count(*)的优化：InnoDB是索引组织表， 主键索引树的叶子节点是数据， 而普通索引树的叶子节点是主键值。 所以， 普通索引树比主键索引树小很多。 通索引树比主键索引树小很多。 对于count(*)这样的操作， 遍历哪个索引树得到的结果逻辑上都是一样的。 因此， MySQL优化器会找到最小的那棵树来遍历。 在保证逻辑正确的前提下， 尽量减少扫描的数据量， 是数据库系统设计的通用法则之一。

show table status 命令也可以显示行数，这里的行数是基于采样统计的，并不准确。

不同count的用法：count(*)、 count(主键id)、 count(字段)和count(1)
- count()是一个聚合函数， 对于返回的结果集， 一行行地判断， 如果count函数的参数不是NULL， 累计值就加1， 否则不加。 最后返回累计值
- 对于count(主键id)来说， InnoDB引擎会遍历整张表， 把每一行的id值都取出来， 返回给server层。 server层拿到id后， 判断是不可能为空的， 就按行累加。
- 对于count(1)来说， InnoDB引擎遍历整张表， 但不取值。 server层对于返回的每一行， 放一个数字“1”进去， 判断是不可能为空的， 按行累加。
- 对于count(字段)来说：
    1. 如果这个“字段”是定义为not null的话， 一行行地从记录里面读出这个字段， 判断不能为null， 按行累加；
    2. 如果这个“字段”定义允许为null， 那么执行的时候， 判断到有可能是null， 还要把值取出来再判断一下， 不是null才累加。
- count(*)是例外， 并不会把全部字段取出来， 而是专门做了优化， 不取值。 count(*)肯定不是null， 按行累加。
- 按照效率排序的话， count(字段)<count(主键id)<count(1)≈count(*)
  
### order by 处理流程
#### 全字段排序
```
CREATE TABLE `t` (
`id` int(11) NOT NULL,
`city` varchar(16) NOT NULL,
`name` varchar(16) NOT NULL,
`age` int(11) NOT NULL,
`addr` varchar(128) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `city` (`city`)
) ENGINE=InnoDB;

select city,name,age from t where city='杭州' order by name limit 1000 ;
```
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/orderby1.jpg)
Extra这个字段中的“Using filesort”表示的就是需要排序， MySQL会给每个线程分配一块内存用于排序， 称为sort_buffer。
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/orderby2.jpg)
这个语句执行流程如下所示 ：
  1. 初始化sort_buffer， 确定放入name、 city、 age这三个字段；
  2. 从索引city找到第一个满足city='杭州’条件的主键id， 也就是图中的ID_X；
  3. 到主键id索引取出整行， 取name、 city、 age三个字段的值， 存入sort_buffer中；
  4. 从索引city取下一个记录的主键id；
  5. 重复步骤3、 4直到city的值不满足查询条件为止， 对应的主键id也就是图中的ID_Y；
  6. 对sort_buffer中的数据按照字段name做快速排序；
  7. 按照排序结果取前1000行返回给客户端
  
sort_buffer_size， 是MySQL为排序开辟的内存（sort_buffer） 的大小。 如果要排序的数据量小于sort_buffer_size， 排序就在内存中完成。 但如果排序数据量太大， 内存放不下， 则不得不利用磁盘临时文件辅助排序。
  - sort_buffer_size大于了需要排序的数据量的大小， number_of_tmp_files就是0，排序直接在内存完成。
  
**optimizer_trace** 是一个跟踪功能，跟踪执行的语句的解析优化执行的过程，比explain更详细。
  - number_of_tmp_files表示的是， 排序过程中使用的临时文件数。
  - sort_mode: 表示参与排序的只有name和id这两个字段
  - ```
    /* 打开optimizer_trace， 只对本线程有效 */
    SET optimizer_trace='enabled=on';
    ```
这里的排序使用的是归并排序

#### rowId排序
max_length_for_sort_data: 是MySQL中专门控制用于排序的行数据的长度的一个参数。 它的意思是， 如果单行的长度超过这个值， MySQL就认为单行太大， 要换一个算法。
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/orderby3.jpg)
- 主要体现在内存排序完毕之后要多一次查询。

对于InnoDB表来说， 执行全字段排序会减少磁盘访问， 因此会被优先选择。

在“InnoDB表”中，对于内存表，回表过程只是简单地根据数据行的位置， 直接访问内存得到数据， 根本不会导致多访问磁盘。 
> 优化器会优先考虑的，就是用于排序的行越少越好。
  
order by rand()使用了内存临时表， 内存临时表排序的时候使用了rowid排序方法。

内存临时表与磁盘临时表
- tmp_table_size这个配置限制了内存临时表的大小， 默认值是16M。 如果临时表大小超过了tmp_table_size， 那么内存临时表就会转成磁盘临时表
  
直接使用order by rand()， 这个语句需要Using temporary和 Using filesort， 查询的执行代价往往是比较大的

### join的执行过程
#### Index Nested-Loop Join
```
select * from t1 straight_join t2 on (t1.a=t2.a);
```
执行流程是这样的：
1. 从表t1中读入一行数据 R；
2. 从数据行R中， 取出a字段到表t2里去查找；
3. 取出表t2中满足条件的行， 跟R组成一行， 作为结果集的一部分；
4. 重复执行步骤1到3， 直到表t1的末尾循环结束
- 实际执行的时候使用了BAK优化，将尽可能多的驱动表数据取出放Join Buffer中，再关联查询。

流程中：
1. 对驱动表t1做了全表扫描， 这个过程需要扫描100行；
2. 而对于每一行R， 根据a字段去表t2查找， 走的是树搜索过程。 由于我们构造的数据都是一一对应的， 因此每次的搜索过程都只扫描一行， 也是总共扫描100行；
3. 所以， 整个执行流程， 总扫描行数是200。

#### Simple Nested-Loop Join
```
select * from t1 straight_join t2 on (t1.a=t2.b);
```
- 表t2的字段b上没有索引，关联查询使用全表扫描。
- SQL请求就要扫描表t2多达100次， 总共扫描100*1000=10万行。

#### Block Nested-Loop Join
将驱动表数据读入线程内存join_buffer中，同样以全表扫描，但是因为使用内存操作，速度比上述方法快。

#### join语句mysql的优化
Multi-Range Read优化
- 大多数的数据都是按照主键递增顺序插入得到的， 所以可以认为， 如果按照主键的递增顺序查询的话， 对磁盘的读比较接近顺序读， 能够提升读性能
- MRR优化思路即将查询的关联集合排序，再关联查询，提高查询效率。将随机访问改成范围访问。

Batched Key Access （BAK）
- 将驱动表数据取出放join_buffer中，进行排序再关联查询。
- join_buffer内存不够大时，进行多次的重复操作。

#### 总结
1. 尽量使用被驱动表的索引，即关联表的字段为索引。
2. 不能使用被驱动表的索引， 只能使用Block Nested-Loop Join算法， 这样的语句就尽量不要使用；
3. 在使用join的时候， 应该让小表做驱动表。
4. 把join 的条件写在where和写在on中区别为，一个为连接的条件。

### union执行流程
```
(select 1000 as f) union (select id from t1 order by id desc limit 2);
```
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/union.jpg)

执行流程是这样的：
1. 创建一个内存临时表， 这个临时表只有一个整型字段f， 并且f是主键字段。
2. 执行第一个子查询， 得到1000这个值， 并存入临时表中。
3. 执行第二个子查询：拿到第一行id=1000， 试图插入临时表中。 但由于1000这个值已经存在于临时表了， 违反了唯一性约束， 所以插入失败， 然后继续执行；取到第二行id=999， 插入临时表成功。
4. 从临时表中按行取出数据， 返回结果， 并删除临时表， 结果中包含两行数据分别是1000和999

#### union all
```
(select 1000 as f) union all(select id from t1 order by id desc limit 2);
```
- 与上面的区别为union all不需要除重，因此直接把查询结果放在结果集中返回。

### group by 执行流程
```
select id%10 as m, count(*) as c from t1 group by m;
```
使用explain分析sql
- Using index， 表示这个语句使用了覆盖索引， 选择了索引a， 不需要回表；
- Using temporary， 表示使用了临时表；
- Using filesort， 表示需要文件排序。

这个语句的执行流程是这样的：
1. 创建内存临时表， 表里有两个字段m和c， 主键是m；
2. 扫描表t1的索引a， 依次取出叶子节点上的id值， 计算id%10的结果， 记为x；
   1. 如果临时表中没有主键为x的行， 就插入一个记录(x,1);
   2. 如果表中有主键为x的行， 就将x这一行的c值加1；
3. 遍历完成后， 再根据字段m做排序， 得到结果集返回给客户端。

group by语句默认都会对语句进行排序，可以使用order by null 避免group by 排序。
```
select id%10 as m, count(*) as c from t1 group by m order by null;
```
#### group by 优化 ——索引
索引保证了数据有序，在group by时候，分组计数计算时一片区域的id都是连续的，整个表扫描结束时便可以拿到结果，不需要临时表也不需要排序。

#### group by 优化 —— 直接排序
确保数据量确实超过了sort buffer，可以直接强制mysql直接使用磁盘文件排序。
```
select SQL_BIG_RESULT id%100 as m, count(*) as c from t1 group by m;
```


## 数据库设计

### 数据库设计原则

#### 第一范式(列不可再分)
第一范式是最基本的范式(确保每列保持原子性)。如果数据库表中的所有字段值都是不可分解的原子值，就说明该数据库表满足了第一范式。
> 第一范式的合理遵循需要根据系统的实际需求来定。比如某些数据库系统中需要用到“地址”这个属性，本来直接将“地址”属性设计成一个数据库表的字段就行。但是如果系统经常会访问“地址”属性中的“城市”部分，那么就非要将“地址”这个属性重新拆分为省份、城市、详细地址等多个部分进行存储。



#### 第二范式(确保表中的每列都和主键相关)
第二范式需要确保数据库表中的每一列都和主键相关，而不能只与主键的某一部分相关（主要针对联合主键而言）。也就是说在一个数据库表中，一个表中只能保存一种数据，不可以把多种数据保存在同一张数据库表中。
> 如一个订单的商品明细表，不应该订单主表的ID放到商品明细表中，会导致数据冗余

#### 第三范式(确保每列都和主键列直接相关,而不是间接相关)
第三范式需要确保数据表中的每一列数据都和主键直接相关，而不能间接相关。
> 如一个订单表都会有客户字段，可以把客户编码作为一个外键跟订单表建立响应关系。

#### 相关资料
- https://www.cnblogs.com/linjiqin/archive/2012/04/01/2428695.html

### 表字段设计
规则：用尽量少的存储空间来存储一个字段的数据

常用的类型：int、float、double、 decimal、varchar、char、 date、datetime等

1. 对于固定长度的字符使用varchar来进行存储，如电话号码。
2. 对于精度要求高的使用decimal，如金钱、重量相关。
3. 与时间相关的，主要根据时间的精度，如只需要存储具体的天，不需要到时分秒。
4. 业务中选择性很少的状态status、类型type等字段推荐使用tinytint或者smallint类型节省存储空间。
5. 字段设置固定长度，对一个新增的列，mysql通常会分配固定的空间大小。太大的字段会造成空间浪费。
6. 表达是与否概念的字段，必须使用 is_xxx 的方式命名，数据类型是 unsigned tinyint（ 1 表示是， 0 表示否）。
#### 乐观锁字段
使用version作为乐观锁的控制。

#### 通用字段
创建人、创建时间、修改人、修改时间

#### 日期字段的选择
 数据库日期的存储主要有三种：字符串、DataTime与Timestamp、时间戳
 
使用字符串存储时间会有如下问题：
1. 字符串占用的空间更大！
2. 字符串存储的日期比较效率比较低（逐个字符进行比对），无法用日期相关的 API 进行计算和比较。

Datetime 和 Timestamp，通常会首选 Timestamp
1. DateTime 类型没有时区信息的，而Timestamp有，具体可以通过改变时区看出差别 `set time_zone='+8:00';`
2. DateTime 类型耗费空间更大使用8个字节存储，timestamp使用4个字节，也就导致了Timestamp表示的时间范围较小。
   - DateTime ：1000-01-01 00:00:00 ~ 9999-12-31 23:59:59
   - Timestamp： 1970-01-01 00:00:01 ~ 2037-12-31 23:59:59
   
时间戳存储：
1. 使用4个字节存储，但是可读性太差
2. 同样为4个字节可表示的时间范围比Timestamp大。

### 实际设计问题 —— 设计部门表 
设计一张部门表应该有哪些必要字段？

1. 部门基本信息。
2. 父部门字段。
3. 如何关联父部门下所有子部门
    1. 方案一，加入一个path字段。用于关联查询。
    2. 方案二，使用内存组装树节点数据。


## 分库分表

### 分库分表有了解吗？
由于数据量过大而导致数据库性能降低的问题，将原来独立的数据库拆分成若干数据库组成 ，将数据大表拆分成若干数据表组成，使得单一数据库、单一数据表的数据量变小，从而达到提升数据库性能的目的。

### 垂直切分
垂直分表定义：将一个表按照字段分成多表，每个表存储其中一部分字段。通常我们按以下原则进行垂直拆分:
1. 把不常用的字段单独放在一张表;
2. 把text，blob等大字段拆分出来放在附表中;
3. 经常组合查询的列放在一张表中;
  
垂直分库是指按照业务将表进行分类，分布到不同的数据库上面，每个库可以放在不同的服务器上，它的核心理念是专库专用。
- 将表按照功能模块、关系密切程度划分出来， 部署到不同的库上。
> 例如，我们会建立定义数据库 workDB、商品数据库 payDB、用户数据库 userDB、日志数据库 logDB 等，分别用于存储项目数据定义表、商品定义表、用户数据表、日志数据表等。


### 水平切分
水平分库是把同一个表的数据按一定规则拆到不同的数据库中，每个库可以放在不同的服务器上。
- 如将店铺ID为单数的和店铺ID为双数的商品信息分别放在两个库中。

水平分表是在同一个数据库内，把同一个表的数据按一定规则拆到多个表中。

## 其他面试题

### mysql数据库抖动
- 当内存数据页跟磁盘数据页内容不一致的时候， 我们称这个内存页为“脏页”。 
- 在内存数据写入到磁盘后， 内存和磁盘上的数据页的内容就一致了， 称为“干净页”。

Mysql 数据库抖动可能就是在刷“脏页”。两种触发刷脏页（flush）的方法
- 第一种：对应的就是InnoDB的redo log写满了。 这时候系统会停止所有更新操作， 把checkpoint往前推进， redo log留出空间可以继续写。
- 第二种：系统的内存需要新的内存页，这时候需要淘汰一些内存也。这如果是脏页，就会把脏页刷到内存中，然后淘汰脏页。
    - 为什么不直接淘汰脏页，等新数据读取的时候再应用redo log？ 主要为了保证状态统一，内存的数据存在则肯定是最新的，内存没有则文件肯定是最新的。
- 第三种：Mysql认为系统空闲时，刷脏页。
- 第四种：MySql关闭时刷脏页。


### 读已提交和可重复读是如何实现的
1. ReadView：
    - 当进行查询操作时，事务会生成一个ReadView，ReadView是一个事务快照，准确来说是当前时间点系统内活跃的事务列表，也就是说系统内所有未提交的事务，都会记录在这个Readview内，事务就根据它来判断哪些数据是可见的，哪些是不可见的。
    - 查询一条数据时，事务会拿到这个ReadView，去到undo log中进行判断。若查询到某一条数据：
2. undo log
    - 当事务对数据行进行一次更新操作时，会把旧数据行记录在一个叫做undo log的记录中，在undo log中除了记录数据行，还会记录下该行数据的对应的创建版本号，也就是生成这行数据的事务id。并连接原纪录

### 读已提交和可重复读区别。
- 举两个事务线程的例子。
     
### 数据库数据库一致性是如何实现的？
- 通过redolog 与binlog 两阶段提交 保证事务一致性。

### redolog、undolog、binlog区别？
- redolog：确保事务的持久性。防止在发生故障的时间点，尚有脏页未写入磁盘，在重启mysql服务的时候，根据redo log进行重做，从而达到事务的持久性这一特性。
- undo log：保存了事务发生之前的数据的一个版本，可以用于回滚，同时可以提供多版本并发控制下的读（MVCC），也即非锁定读

### 两种日志有以下三点不同。
1. redo log是InnoDB引擎特有的； binlog是MySQL的Server层实现的， 所有引擎都可以使用。
2. redo log是物理日志， 记录的是“在某个数据页上做了什么修改”； binlog是逻辑日志， 记录的是这个语句的原始逻辑， 比如“给ID=2这一行的c字段加1 ”。
3. redo log是循环写的， 空间固定会用完； binlog是可以追加写入的。 “追加写”是指binlog文件写到一定大小后会切换到下一个， 并不会覆盖以前的日志。
  
- binlog的作用？（说的是监控，其实主要是主从复制或者备份）
  - 用于复制，在主从复制中，从库利用主库上的binlog进行重播，实现主从同步。 
  - 用于数据库的基于时间点的还原。
  
### 数据库什么情况会出现死锁？如何处理死锁？
mysql 两个事物更新条件互斥，进入循环等待。

使用mysql参数innodb_deadlock_detect设置为on， 表示开启这个死锁检测逻辑。

show processlist;显示哪些线程正在运行。您也可以使用mysqladmin processlist语句得到此信息
  - 执行kill 命令

### 如何优化SQL
1. 慢sql获取：通过慢查询日志获取sql语句
2. 对sql进行基本的优化
    1. 针对select 部分，查看是否内连子查询、使用自定义函数、或者返回text的字段导致数据量过大消耗网络及IO
    2. 针对join部分，查看是否可以减少连接的数据集。
    3. 针对where部分，主要看索引列是否存在失效的情况，如使用
        1. 负向查询
        2. 索引列使用运算或者函数
        3. like不匹配最左前缀原则
        4. or关联非索引（无法使用合并索引优化）
        5. 隐式关联导致索引失效
        6. 联合索引过滤顺序有误
        7. mysql优化器分析有误，不走索引（重新执行分析或者牵制走索引）
    4. group by部分，查看是否可以加索引，若无法优化可考虑冗余索引字段
    5. order by部分，检查字段排序顺序，是否与联合索引一致
    6. limit m,n要慎重， m的值越高，sql消耗时间越长
3. 执行explain语句 查看SQL执行情况。
4. 针对未走索引的情况，可以使用强制走索引的方式
5. 针对复合索引创建顺序有误，导致了索引生效，修改索引。
6. 对于走错索引，说明mysql在统计行信息出错（由于磁盘，mysql使用采样统计的方式），通过执行analysis table。建议低峰使用。确定索引无用，可以删除干扰索引。第三种，强制走索引。。
7. 数据集过大，索引失效。

explain 分析例子
``` 
+----+-------------+-------+------------+--------+------------------+------------------+---------+----------------------------------+------+----------+----------------------------------------------------+
| id | select_type | table | partitions | type   | possible_keys    | key              | key_len | ref                              | rows | filtered | Extra                                              |
+----+-------------+-------+------------+--------+------------------+------------------+---------+----------------------------------+------+----------+----------------------------------------------------+
|  1 | SIMPLE      | ct    | NULL       | ALL    | NULL             | NULL             | NULL    | NULL                             |  353 |   100.00 | Using where                                        |
|  1 | SIMPLE      | mo    | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | ct.organization_id           |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | cm    | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | ct.container_master_id       |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | cei   | NULL       | ref    | idx_container_id | idx_container_id | 9       | ct.container_id              |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | ctp   | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | cei.transfer_plan_id         |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | mw    | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | ct.current_warehouse_id      |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | cl    | NULL       | ref    | idx_container_id | idx_container_id | 9       | ct.container_id              |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | mm    | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | cl.materiel_id               |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | qq    | NULL       | ALL    | NULL             | NULL             | NULL    | NULL                         |  123 |   100.00 | Using where; Using join buffer (Block Nested Loop) |
|  1 | SIMPLE      | pg    | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | qq.product_grade_id          |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | cli   | NULL       | ref    | idx_container_id | idx_container_id | 9       | ct.container_id              |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | ctr   | NULL       | ref    | idx_container_id | idx_container_id | 9       | ct.container_id              |    1 |   100.00 | Using where                                        |
|  1 | SIMPLE      | cso   | NULL       | ref    | idx_container_id | idx_container_id | 9       | ct.container_id              |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | tsb   | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | cso.tran_sea_bill_id         |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | tspn  | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | tsb.sea_plan_id              |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | suser | NULL       | ref    | uk_user_name     | uk_user_name     | 63      | tspn.book_space_user         |    1 |   100.00 | Using where; Using index                           |
|  1 | SIMPLE      | tspc  | NULL       | ALL    | idx_container_id | NULL             | NULL    | NULL                         |    2 |   100.00 | Using where; Using join buffer (Block Nested Loop) |
|  1 | SIMPLE      | tspd  | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | tspc.tran_sub_plan_detail_id |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | tsp   | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | tspd.tran_sub_plan_id        |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | csm   | NULL       | ref    | idx_container_id | idx_container_id | 9       | ct.container_id              |    1 |   100.00 | NULL                                               |
|  1 | SIMPLE      | yjtsp | NULL       | eq_ref | PRIMARY          | PRIMARY          | 8       | csm.sea_plan_id              |    1 |   100.00 | NULL                                               |
+----+-------------+-------+------------+--------+------------------+------------------+---------+----------------------------------+------+----------+----------------------------------------------------+

```

- 相关资料：https://mp.weixin.qq.com/s/nEmN4S9JOTVGj5IHyfNtCw



