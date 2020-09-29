# MySql实战45讲
## mysql整体的架构
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/mysqlprocess.jpg)
- MySQL可以分为Server层和存储引擎层两部分
  - Server层包括连接器、查询缓存、分析器、优化器、执行器等，涵盖MySQL的大多数核心服务功能，以及所有的内置函数（如日期、时间、数学和加密函数等），所有跨存储引擎的功能都在
这一层实现，比如存储过程、触发器、视图等。
  - 存储引擎层负责数据的存储和提取。其架构模式是插件式的，支持InnoDB、MyISAM、Memory等多个存储引擎。现在最常用的存储引擎是InnoDB，它从MySQL5.5.5版本开始成为了默认存储引擎

### 连接器
 1. 第一步， 你会先连接到这个数据库上， 这时候接待你的就是连接器。 连接器负责跟客户端建立连接、 获取权限、 维持和管理连接。 连接命令一般是这么写的：
  ```mysql -h$ip -P$port -u$user -p```
 2. 完成经典的TCP握手后， 连接器就要开始认证你的身份， 这个时候用的就是你输入的用户名和密码。
    - 如果用户名或密码不对， 你就会收到一个"Access denied for user"的错误， 然后客户端程序结束执行。
    - 如果用户名密码认证通过， 连接器会到权限表里面查出你拥有的权限。 之后， 这个连接里面的权限判断逻辑， 都将依赖于此时读到的权限。
    - 这就意味着， 一个用户成功建立连接后， 即使你用管理员账号对这个用户的权限做了修改， 也不会影响已经存在连接的权限。 修改完成后， 只有再新建的连接才会使用新的权限设置。连接完成后， 如果你没有后续的动作， 这个连接就处于空闲状态， 你可以在show processlist命
    令中看到它。 文本中这个图是show processlist的结果， 其中的Command列显示为“Sleep”的这一行， 就表示现在系统里面有一个空闲连接。

- 长连接与短连接
  - 长连接指连接成功后，如果客户端有请求，则一直使用同一个连接
  - 短连接指执行很少的几次查询之后，就断开连接。
  - 长连接mysql的，如果太久没有动静，mysql默认8h会自动断开，可以使用wait_timeout参数控制。

- 全部使用长连接Mysql内存涨特别快的解决方案
  1. 定期断开长连接。或者执行一个占用内存大的查询之后断开连接，之后再重连。
  2. 5.7以上版本可以通过执行mysql_reset_connection来重新初始化连接资源。 这个过程不需要重连和重新做权限验证，但是会将连接恢复到刚刚创建完时的状态

### 查询缓存
- 连接建立完成之后，会到查询缓存中查询数据。查询缓存的存储默认key为查询语句，而value为结果。
- 查询缓存弊大于利，因为只要有表更新，这个表相关的缓存就会被清除。
- query_cache_type 设置成DEMAND，可取消查询缓存。
- 显示的查询缓存数据 ``` mysql> select SQL_CACHE * from T where ID=10； ```
- mysql 8 版本直接移除了查询缓存。

### 分析器
- 主要做语法解析，并判断语法是否合规。

### 优化器
在开始执行之前， 还要先经过优化器的处理。
- 优化器是在表里面有多个索引的时候，决定使用哪个索引；或者在一个语句有多表关联（join）
的时候， 决定各个表的连接顺序。比如你执行下面这样的语句，这个语句是执行两个表的join：
```mysql> select * from t1 join t2 using(ID) where t1.c=10 and t2.d=20;```
- 既可以先从表t1里面取出c=10的记录的ID值，再根据ID值关联到表t2，再判断t2里面d的值是
否等于20。
- 也可以先从表t2里面取出d=20的记录的ID值，再根据ID值关联到t1，再判断t1里面c的值是否
等于10。
这两种执行方法的逻辑结果是一样的，但是执行的效率会有不同，而优化器的作用就是决定选择效率高的方案。

### 执行器
```
mysql> select * from T where ID=10;
```
- 首先判断语句是否与查询权限，若无则直接返回

- 比如我们这个例子中的表T中，ID字段没有索引，那么执行器的执行流程是这样的：
  1.调用InnoDB引擎接口取这个表的第一行，判断ID值是不是10，如果不是则跳过，如果是则将这行存在结果集中；
  2.调用引擎接口取“下一行”，重复相同的判断逻辑，直到取到这个表的最后一行。
  3.执行器将上述遍历过程中所有满足条件的行组成的记录集作为结果集返回给客户端。

- 对于有索引的表，执行的逻辑也差不多。第一次调用的是“取满足条件的第一行”这个接口，之后循环取“满足条件的下一行”这个接口，这些接口都是引擎中已经定义好的。
- 你会在数据库的慢查询日志中看到一个rows_examined的字段，表示这个语句执行过程中扫描了多少行。这个值就是在执行器每次调用引擎获取数据行的时候累加的。
- 在有些场景下，执行器调用一次，在引擎内部则扫描了多行，因此引擎扫描行数跟rows_examined并不是完全相同的。


## redo log 与 binlog
### redo log
- MySQL里经常说到的WAL技术，WAL的全称是WriteAheadLogging，它的关键点就是先写日志，再写磁盘，也就是先写粉板，等不忙的时候再写账本。
- 当有一条记录需要更新的时候， InnoDB引擎就会先把记录写到redo log（粉板） 里面， 并更新内存， 这个时候更新就算完成了。 同时， InnoDB引擎会在适当的时候， 将这个操作记录更新到磁盘里面， 而这个更新往往是在系统比较空闲的时候做， 这就像打烊以后掌柜做的事。(由于磁盘连接开销大，)
- InnoDB的redo log是固定大小的， 比如可以配置为一组4个文件， 每个文件的大小是1GB， 那么这块“粉板”总共就可以记录4GB的操作。 从头开始写， 写到末尾就又回到开头循环写， 如下面这个图所示

![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/redologwrite.jpg)

### binlog
- MySQL整体来看， 其实就有两块： 一块是Server层， 它主要做的是MySQL功能层面的事情； 还有一块是引擎层， 负责存储相关的具体事宜。 上面我们聊到的粉板redo log是InnoDB引擎特有的日志， 而Server层也有自己的日志， 称为binlog（归档日志） 。
#### 两种日志有以下三点不同。
  1. redo log是InnoDB引擎特有的； binlog是MySQL的Server层实现的， 所有引擎都可以使用。
  2. redo log是物理日志， 记录的是“在某个数据页上做了什么修改”； binlog是逻辑日志， 记录的是这个语句的原始逻辑， 比如“给ID=2这一行的c字段加1 ”。
  3. redo log是循环写的， 空间固定会用完； binlog是可以追加写入的。 “追加写”是指binlog文件写到一定大小后会切换到下一个， 并不会覆盖以前的日志。
  4. 事务提交的时候，一次性将事务中的sql语句（一个事物可能对应多个sql语句）按照一定的格式记录到binlog中。这里与redo log很明显的差异就是redo log并不一定是在事务提交的时候刷新到磁盘，redo log是在事务开始之后就开始逐步写入磁盘。

#### 一条update语句的执行流程
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/updateProcess.jpg)

- 两阶段提交：主要用于保证redo log 与binlog 的状态保持逻辑上一致。

#### 崩溃后的数据恢复阶段
  - 如果在更新或写入数据的过程中，机器出现崩溃。那么在机器在重启后，MySQL会首先去验证redolog的完整性，如果redolog中没有prepare状态的记录，则记录是完整的，就日记提交。如果redolog中存在prepare记录，那么就去验证这条redolog对应的binlog记录，如果这条binlog是完整的，那么完整提交redolog，否则执行回滚逻辑
  
### 事务隔离
- SQL标准的事务隔离级别包括：
  - 读未提交（ read uncommitted）
  - 读提交（read committed）
  - 可重复读（ repeatable read）
  - 串行化（ serializable ）
  
#### 隔离级别的例子
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/transactionProcess.jpg)

- 若隔离级别是“读未提交”， 则V1的值就是2。 这时候事务B虽然还没有提交， 但是结果已经被A看到了。 因此， V2、 V3也都是2。
- 若隔离级别是“读提交”， 则V1是1， V2的值是2。 事务B的更新在提交后才能被A看到。 所以，V3的值也是2。
- 若隔离级别是“可重复读”， 则V1、 V2是1， V3是2。 之所以V2还是1， 遵循的就是这个要求：事务在执行期间看到的数据前后必须是一致的。
- 若隔离级别是“串行化”， 则在事务B执行“将1改成2”的时候， 会被锁住。 直到事务A提交后，事务B才可以继续执行。 所以从A的角度看， V1、 V2值是1， V3的值是2

- 可重复读的一个应用：账户余额与交易明细核对，通过开启事务，保证核对期间的交易不影响查询核对的过程。
#### 事务启动的方式
MySQL的事务启动方式有以下几种：
1. 显式启动事务语句， begin 或 start transaction。 配套的提交语句是commit， 回滚语句是rollback。
2. set autocommit=0， 这个命令会将这个线程的自动提交关掉。 意味着如果你只执行一个select语句， 这个事务就启动了， 而且并不会自动提交。 这个事务持续存在直到你主动执行commit 或 rollback 语句， 或者断开连接。

#### 事务隔离的实现
- 事务启动的时候会创建一个视图read-view。
- begin/start transaction 命令并不是一个事务的起点， 在执行到它们之后的第一个操作InnoDB表的语句， 事务才真正启动。 如果你想要马上启动一个事务， 可以使用start transaction with consistent snapshot 这个命令。
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/transactiongeli.jpg)

- 在MySQL里， 有两个“视图”的概念：
  1. 一个是view。 它是一个用查询语句定义的虚拟表， 在调用的时候执行查询语句并生成结果。创建视图的语法是create view …， 而它的查询方法与表一样。
  2. 另一个是InnoDB在实现MVCC（多版本并发控制）时用到的一致性读视图， 即consistent read view， 用于支持RC（Read Committed， 读提交） 和RR（ Repeatable Read， 可重复读） 隔离级别的实现。

- InnoDB里面每个事务有一个唯一的事务ID， 叫作transaction id。 它是在事务开始的时候向InnoDB的事务系统申请的， 是按申请顺序严格递增的。
- 每行数据也都是有多个版本的。 每次事务更新数据的时候， 都会生成一个新的数据版本， 并且把transaction id赋值给这个数据版本的事务ID， 记为row trx_id。 同时， 旧的数据版本要保留，并且在新的数据版本中， 能够有信息可以直接拿到它

- 行状态说明
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/lineState.jpg)
- 虚线框里是同一行数据的4个版本， 当前最新版本是V4， k的值是22， 它是被transaction id为25的事务更新的， 因此它的row trx_id也是25。而V1、 V2、 V3并不是物理上真实存在的， 而是每次需要的时候根据当前版本和undo log计算出来的。 
- 图中的三个虚线箭头，就是undo log； 

#### 关于数据隔离的说明
- 以可重复读为例
  - 查询语句以自己的视图版本为准。
  - 更新语句以当前的视图版本为准。


![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/transactiongeli2.png)
- 事务A视图版本100，事务B视图版本101，事务C视图版本102
1. 更新数据都是先读后写的， 而这个读， 只能读当前的值， 称为“当前读”（ current read） 。
2. 故事务C读到当前值为1，更新为2。事务B读到当前值被C更新为2，再更新为3。
3. 事务A查询以当前版本为例，则读到为1。
4. 把事务A的查询语句select * from t where id=1修改一下， 加上lock in share mode 或for update， 也都可以读到版本号是101的数据， 返回的k的值是3。
  - ```
    mysql> select k from t where id=1 lock in share mode;
    mysql> select k from t where id=1 for update;
    ```
- 所等待的例子，更新结果也为3。
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/transactiongeli3.jpg)

- 读提交事务隔离的例子，事务A读取的结果为2。
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/transactiongeli4.jpg)

## 索引
- 索引的出现其实就是为了提高数据查询的效率， 就像书的目录一样。 一本500页的书，对于数据库的表而言， 索引其实就是它的“目录”。
- InnoDB里面索引对应一棵B+树
  - 使用B+树而不是二叉搜索树的原因是，由于存储介质的特性，磁盘本身存取就比主存慢很多，每次搜索的磁盘IO的开销过大，而B+树可以使用较少次的磁盘IO搜索到对象。
  - B-Tree中一次检索最多需要h-1次I/O（根节点常驻内存），渐进复杂度为O(h)=O(logdN)。
  - 红黑树这种结构，h明显要深的多。效率明显比B-Tree差很多。

- 基于主键索引和普通索引的查询有什么区别？
  - 如果语句是select * from T where ID=500， 即主键查询方式， 则只需要搜索ID这棵B+树。
  - 如果语句是select * from T where k=5， 即普通索引查询方式， 则需要先搜索k索引树， 得到ID的值为500， 再到ID索引树搜索一次。 这个过程称为回表。
  - 回到主键索引树搜索的过程， 我们称为回表。
  
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/indexquery.jpg)
- 主键长度越小， 普通索引的叶子节点就越小， 普通索引占用的空间也就越小。
  - 所以， 从性能和存储空间方面考量， 自增主键往往是更合理的选择。

- 业务字段做主键的例子，比如有些业务的场景需求是这样的：
  1. 只有一个索引；
  2. 该索引必须是唯一索引。
  - 由于没有其他索引， 所以也就不用考虑其他索引的叶子节点大小的问题。
 
#### 唯一索引与普通索引的选择
- 两者查询性能差不多。
- 主要区别在于，这个记录要更新的目标页不在内存中时。普通索引更新会使用change Buffer。唯一索引，由于需要校验数据的唯一性，因此每次更新操作都需要读磁盘把数据载进内存，涉及IO操作。
- 在不影响数据一致性的前提下， InooDB会将这些更新操作缓存在change buffer中， 这样就不需要从磁盘中读入这个数据页了。 在下次查询需要访问这个数据页的时候， 将数据页读入内存， 然后执行change buffer中与这个页有关的操作。 通过这种方式就能保证这个数据逻辑的正确性
- change buffer中的操作应用到原数据页， 得到最新结果的过程称为merge。 
  - 访问这个数据页会触发merge。
  - 系统有后台线程会定期merge。 
  - 在数据库正常关闭（shutdown） 的过程中，也会执行merge操作。
  
- change buffer的大小， 可以通过参数innodb_change_buffer_max_size来动态设置。 这个参数设置为50的时候， 表示change buffer的大小最多只能占用buffer pool的50%。
- 应用： 写多读少的业务来说， 页面在写完以后马上被访问到的概率比较小， 此时change buffer的使用效果最好。若频繁读写场景，则失去了优势。

- change Buffer与redo log 区别
  - change Buffer主要用于减少读磁盘的次数，在必要读磁盘时再更新数据。
  - redo log 则是减少内存更新后，写磁盘的次数。
 
B-树与B+树的特征

#### 覆盖索引
- InnoDB存储引擎支持覆盖索引，即从辅助索引中就可以得到查询的记录，而不需要查询聚集索引中的记录。
  - 如： ``` select id, b from t where b = xxx   (id为主键，b为索引)```
- 由于覆盖索引可以减少树的搜索次数（减少IO）， 显著提升查询性能， 所以使用覆盖索引是一个常用的性能优化手段。


#### 最左前缀原则
- B+树这种索引结构， 可以利用索引的“最左前缀”， 来定位记录。  
- 只要满足最左前缀， 就可以利用索引来加速检索。 
  - 这个最左前缀可以是联合索引的最左N个字段， 也可以是字符串索引的最左M个字符

- 如何安排索引内的字段顺序
  - 第一原则是， 优先考虑采用如果通过调整顺序， 可以少维护一个索引的方案。
  - 第二原则空间。比如name字段是比age字段打，那建议创建一个（name,age)的联合索引和一个(age)的单字段索引

#### 索引下推
``` 
mysql> select * from tuser where name like '张%' and age=10 and ismale=1; 
```
- mysql 5.6 后引入索引下推。
- 索引遍历过程中， 对索引中包含的字段先做判断， 直接过滤掉不满足条件的记录， 减少回表次数

### 索引选错及优化器执行逻辑
- 下面是一个索引走错的例子，图二为慢查询日志的结果，红框内容为实际扫描行数。
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/errorIndex1.jpg)
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/errorIndex2.jpg)
```
// 设置慢查询日志
set long_query_time=0;
// Q1是session B原来的查询；
select * from t where a between 10000 and 20000; /*Q1*/
//  Q2是加了force index(a)来和session B原来的查询语句执行情况对比
select * from t force index(a) where a between 10000 and 20000;/*Q2*/
```

- 优化器选择索引的目的是找一个最优方案，用最小的代价执行语句。其中扫描行数、是否使用临时表、是否排序等因素都会影响优化器对索引的选择判断。

- 扫描行数判断，mysql使用采样统计的方式来获取索引的统计信息基数。采样统计的方式可以减少磁盘的IO次数
  - InnoDB默认会选择N个数据页， 统计这些页面上的不同值， 得到一个平均值， 然后乘以这个索引的页面数， 就得到了这个索引的基数。
  - 一个索引上不同的值的个数， 我们称之为“基数”（cardinality） 
  - 当变更的数据行数超过1/M的时候， 会自动触发重新做一次索引统计。
  
- 在MySQL中， 有两种存储索引统计的方式， 可以通过设置参数innodb_stats_persistent的值来选择：
  - 设置为on的时候， 表示统计信息会持久化存储。 这时， 默认的N是20， M是10。
  - 设置为off的时候， 表示统计信息只存储在内存中。 这时， 默认的N是8， M是16
  - 由于是采样统计， 所以不管N是20还是8， 这个基数都是很容易不准的。
  - analyze table t 命令， 可以用来重新统计索引信息。 

![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/errorIndex2.jpg)
- 使用explain 语句分析SQL的扫描行数信息，rows的结果为根据采样结果预计的扫描行数。
  - 选错索引的根本原因为采样统计信息有误统计成了37116行，而由于查询所有字段，使用索引还需要根据ID回表查询其他信息，经过优化器估算，全表扫描代价低。
  - 优化器的选择方案时，使用普通索引会把回表的代价也算进去。

#### 解决索引选择异常方案
- 使用force index
- 引导Mysql使用我们期望的索引，如order by b,a limit 1 与 order by b limit 1 逻辑上能保持一致，那么就能这么修改
- 在有些场景下， 我们可以新建一个更合适的索引， 来提供给优化器做选择， 或删掉误用的索引。（较少用）

### 添加索引的技巧
#### 前缀索引
- 定义：定义字符串的一部分作为索引。
```
mysql> alter table SUser add index index2(email(6));
```
- 前缀索引可以减少索引占用的空间，但是可能需要额外增加扫描次数。
- 使用前缀索引， 定义好长度， 就可以做到既节省空间， 又不用额外增加太多的查询成本。如使用身份证的后六位作为索引，索引的区分度好。
```
mysql> select
count(distinct left(email,4)） as L4,
count(distinct left(email,5)） as L5,
count(distinct left(email,6)） as L6,
count(distinct left(email,7)） as L7,
from SUser;
```
- 使用前缀索引很可能会损失区分度， 所以你需要预先设定一个可以接受的损失比例， 比如5%。 然后， 在返回的L4~L7中， 找出不小于 L * 95%的值， 
- 使用前缀索引就用不上覆盖索引对查询性能的优化了，对于已经找到类似字段的记录，都需要回表进行扫描。这也是你在选择是否使用前缀索引时需要考虑的一个因素。

#### 一些添加索引的技巧
- 倒序存储。比如存储身份证时都倒序存储，而身份证的后六位区分度好，便于建立前缀索引.
  - 缺点：无法查询区间。
  - ```
    mysql> select field_list from t where id_card = reverse('input_id_card_string');
    ```
- 使用Hash字段。每次插入新记录的时候， 都同时用crc32()这个函数得到校验码填到一个新字段.
  - ```
    mysql> alter table t add id_card_crc int unsigned, add index(id_card_crc);
    ```
#### 添加索引的一些方式方法比较
1. 直接创建完整索引， 这样可能比较占用空间；
2. 创建前缀索引， 节省空间， 但会增加查询扫描次数， 并且不能使用覆盖索引；
3. 倒序存储， 再创建前缀索引， 用于绕过字符串本身前缀的区分度不够的问题；
4. 创建hash字段索引， 查询性能稳定， 有额外的存储和计算消耗， 跟第三种方式一样， 都不支
持范围扫描

## 全局锁、表级锁、行级锁、死锁
### 全局锁
- 根据加锁的范围， MySQL里面的锁大致可以分成全局锁、 表级锁和行锁三类。
- 全局锁就是对整个数据库实例加锁。 MySQL提供了一个加全局读锁的方法， 命令是Flush tables with read lock (FTWRL)。 
  - 全局锁的典型使用场景是， 做全库逻辑备份。 
  - 官方自带的逻辑备份工具是mysqldump。 当mysqldump使用参数–single-transaction的时候， 导数据之前就会启动一个事务， 来确保拿到一致性视图。 而由于MVCC的支持， 这个过程中数据是可以正常更新的。
    - single-transaction方法只适用于所有的表都可以使用事务引擎的库。 
    - 对于MyISAM这种不支持事务的引擎， 就需要使用FTWRL命令。

### 表级锁
- MySQL里面表级别的锁有两种： 一种是表锁， 一种是元数据锁（meta data lock，MDL)。
- 表锁的语法是 lock tables …read/write。 
  - 举个例子, 如果在某个线程A中执行lock tables t1 read, t2 write; 这个语句， 则其他线程写t1、 读写t2的语句都会被阻塞。 同时， 线程A在执行unlock tables之前， 也只能执行读t1、 读写t2的操作。 连写t1都不允许， 自然也不能访问其他表
  - InnoDB这种支持行锁的引擎， 一般不使用lock tables命令来控制并发， 毕竟锁住整个表的影响面还是太大。
  
- 另一类表级的锁是MDL（ metadata lock)。 
  - 当对一个表做增删改查操作的时候， 加MDL读锁； 
  - 当要对表做结构变更操作的时候， 加MDL写锁。
  - 读锁之间不互斥， 因此可以有多个线程同时对一张表增删改查。
  - 读写锁之间、 写锁之间是互斥的， 用来保证变更表结构操作的安全性。 因此， 如果有两个线程要同时给一个表加字段， 其中一个要等另一个执行完才能开始执行。
  - 而当读写锁阻塞时，则后序的读锁也无法正常共享。
  
- 如何安全地给小表加字段？
  - 在alter table语句里面设定等待时间， 如果在这个指定的等待时间里面能够拿到MDL写锁最好， 拿不到也不要阻塞后面的业务语句， 先放弃。 之后开发人员或者DBA再通过重试命令重复这个过程。
  
### 行级锁
- MySQL的行锁是在引擎层由各个引擎自己实现的。 MyISAM引擎就不支持行锁，InnoDB是支持行锁的。

#### 两阶段锁
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/linelock.jpg)
- 事务B的update语句会被阻塞， 直到事务A执行commit之后， 事务B才能继续执行
- 在InnoDB事务中， 行锁是在需要的时候才加上的， 但并不是不需要了就立刻释放， 而是要等到事务结束时才释放。 这个就是两阶段锁协议
- 如果你的事务中需要锁多个行， 要把最可能造成锁冲突、 最可能影响并发度的锁尽量往后放。

### 死锁
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/deadlock.jpg)
- 事务A在等待事务B释放id=2的行锁， 而事务B在等待事务A释放id=1的行锁。 事务A和事务B在互相等待对方的资源释放， 就是进入了死锁状态。 
  - 一种策略是， 直接进入等待， 直到超时。 这个超时时间可以通过参数innodb_lock_wait_timeout来设置。在InnoDB中， innodb_lock_wait_timeout的默认值是50s
  - 另一种策略是， 发起死锁检测， 发现死锁后， 主动回滚死锁链条中的某一个事务， 让其他事务得以继续执行。 将参数innodb_deadlock_detect设置为on， 表示开启这个逻辑。
    - 弊端：判断是否存在死锁的成本会随着数据量的增长，而大量消耗CPU。假设有1000个并发线程要同时更新同一行， 那么死锁检测操作就是100万这个量级的。
    - 解决方案：1.确定不会出现死锁，关闭死锁检测。2.控制并发度。3.改写mysql源码。
    

## Mysql数据库抖动
- 当内存数据页跟磁盘数据页内容不一致的时候， 我们称这个内存页为“脏页”。 
- 在内存数据写入到磁盘后， 内存和磁盘上的数据页的内容就一致了， 称为“干净页”。

![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/redologFlush.jpg)
- Mysql 数据库抖动可能就是在刷“脏页”。两种触发刷脏页（flush）的方法
  - 第一种：对应的就是InnoDB的redo log写满了。 这时候系统会停止所有更新操作， 把checkpoint往前推进， redo log留出空间可以继续写。
  - 第二种：系统的内存需要新的内存页，这时候需要淘汰一些内存也。这如果是脏页，就会把脏页刷到内存中，然后淘汰脏页。
    - 为什么不直接淘汰脏页，等新数据读取的时候再应用redo log？ 主要为了保证状态统一，内存的数据存在则肯定是最新的，内存没有则文件肯定是最新的。
  - 第三种：Mysql认为系统空闲时，刷脏页。
  - 第四种：MySql关闭时刷脏页。
  
### 内存不足刷脏页的情况
- 缓冲池中的内存页有三种状态：
  - 第一种是， 还没有使用的；
  - 第二种是， 使用了并且是干净页；
  - 第三种是， 使用了并且是脏页。
  
- 如果要淘汰的是一个干净页， 就直接释放出来复用； 但如果是脏页呢， 就必须将脏页先刷到磁盘， 变成干净页后才能复用。
  
#### InnoDB刷脏页的控制策略
  - innodb_io_capacity这个参数，会告诉InnoDB你的磁盘能力。
    - 建议设置成磁盘的IOPS。 磁盘的IOPS可以通过fio这个工具来测试
  - 参数设置过低会导致InnoDB认为这个系统的能力就这么差， 所以刷脏页刷得特别慢， 甚至比脏页生成的速度还慢， 这样就造成了脏页累积， 影响了查询和更新性能
  - 参数设置太高会影响除Mysql外的服务响应。
  
- InnoDB的刷盘速度就是要参考这两个因素： 一个是脏页比例， 一个是redo log写盘速度。
  - 参数innodb_max_dirty_pages_pct是脏页比例上限， 默认值是75%。 InnoDB会根据当前的脏页比例（假设为M） ，算出一个范围在0到100之间的数字F(M)
  - InnoDB每次写入的日志都有一个序号， 当前写入的序号跟checkpoint对应的序号之间的差值，我们假设为N，计算出F(N)。 
  - 上述算得的F1(M)和F2(N)两个值， 取其中较大的值记为R， 之后引擎就可以按 照innodb_io_capacity定义的能力乘以R%来控制刷脏页的速度。
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/memoryflash.jpg)
