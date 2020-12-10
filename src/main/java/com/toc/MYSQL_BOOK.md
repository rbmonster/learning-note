<a name="index">**Index**</a>

<a href="#0">MySql实战45讲</a>  
&emsp;<a href="#1">1. mysql整体的架构</a>  
&emsp;&emsp;<a href="#2">1.1. 连接器</a>  
&emsp;&emsp;<a href="#3">1.2. 查询缓存</a>  
&emsp;&emsp;<a href="#4">1.3. 分析器</a>  
&emsp;&emsp;<a href="#5">1.4. 优化器</a>  
&emsp;&emsp;<a href="#6">1.5. 执行器</a>  
&emsp;<a href="#7">2. redo log 与 binlog</a>  
&emsp;&emsp;<a href="#8">2.1. redo log</a>  
&emsp;&emsp;&emsp;<a href="#9">2.1.1. redo log 写入机制</a>  
&emsp;&emsp;<a href="#10">2.2. binlog</a>  
&emsp;&emsp;&emsp;<a href="#11">2.2.1. bin log写入机制</a>  
&emsp;&emsp;<a href="#12">2.3. 两种日志有以下三点不同。</a>  
&emsp;&emsp;<a href="#13">2.4. 一条update语句的执行流程</a>  
&emsp;&emsp;<a href="#14">2.5. 两阶段提交</a>  
&emsp;&emsp;&emsp;<a href="#15">2.5.1. 两阶段提交的实际执行流程</a>  
&emsp;<a href="#16">3. 事务隔离</a>  
&emsp;&emsp;<a href="#17">3.1. 区分隔离级别（例子）</a>  
&emsp;&emsp;<a href="#18">3.2. 事务启动的方式</a>  
&emsp;&emsp;<a href="#19">3.3. 事务隔离的实现</a>  
&emsp;&emsp;<a href="#20">3.4. 关于数据隔离的说明</a>  
&emsp;<a href="#21">4. 索引</a>  
&emsp;&emsp;<a href="#22">4.1. 唯一索引与普通索引的选择</a>  
&emsp;&emsp;<a href="#23">4.2. B树与B+树的特征</a>  
&emsp;&emsp;<a href="#24">4.3. B树</a>  
&emsp;&emsp;<a href="#25">4.4. B+树</a>  
&emsp;&emsp;<a href="#26">4.5. 索引规则</a>  
&emsp;&emsp;&emsp;<a href="#27">4.5.1. 覆盖索引</a>  
&emsp;&emsp;&emsp;<a href="#28">4.5.2. 最左前缀原则</a>  
&emsp;&emsp;&emsp;<a href="#29">4.5.3. 索引下推</a>  
&emsp;&emsp;<a href="#30">4.6. 索引选错及优化器执行逻辑</a>  
&emsp;&emsp;&emsp;<a href="#31">4.6.1. 解决索引选择异常方案</a>  
&emsp;&emsp;<a href="#32">4.7. 添加索引的技巧</a>  
&emsp;&emsp;&emsp;<a href="#33">4.7.1. 前缀索引</a>  
&emsp;&emsp;&emsp;<a href="#34">4.7.2. 一些添加索引的技巧</a>  
&emsp;&emsp;&emsp;<a href="#35">4.7.3. 添加索引的一些方式方法比较</a>  
&emsp;<a href="#36">5. MySql的锁</a>  
&emsp;&emsp;<a href="#37">5.1. 全局锁</a>  
&emsp;&emsp;<a href="#38">5.2. 表级锁</a>  
&emsp;&emsp;<a href="#39">5.3. 行级锁</a>  
&emsp;&emsp;&emsp;<a href="#40">5.3.1. 两阶段锁</a>  
&emsp;&emsp;<a href="#41">5.4. 死锁</a>  
&emsp;&emsp;<a href="#42">5.5. 幻读(间隙锁)</a>  
&emsp;&emsp;&emsp;<a href="#43">5.5.1. 如何解决幻读？</a>  
&emsp;&emsp;&emsp;<a href="#44">5.5.2. 加锁原则</a>  
&emsp;&emsp;&emsp;<a href="#45">5.5.3. 间隙锁相关实例说明</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#46">5.5.3.1. 一组next-key lock 案例</a>  
&emsp;<a href="#47">6. SQL 语句执行流程</a>  
&emsp;&emsp;<a href="#48">6.1. count(*)实现</a>  
&emsp;&emsp;<a href="#49">6.2. order by 处理流程</a>  
&emsp;&emsp;&emsp;<a href="#50">6.2.1. 全字段排序</a>  
&emsp;&emsp;&emsp;<a href="#51">6.2.2. rowId排序</a>  
&emsp;&emsp;<a href="#52">6.3. join的执行过程</a>  
&emsp;&emsp;&emsp;<a href="#53">6.3.1. Index Nested-Loop Join</a>  
&emsp;&emsp;&emsp;<a href="#54">6.3.2. Simple Nested-Loop Join</a>  
&emsp;&emsp;&emsp;<a href="#55">6.3.3. Block Nested-Loop Join</a>  
&emsp;&emsp;&emsp;<a href="#56">6.3.4. join语句mysql的优化</a>  
&emsp;&emsp;&emsp;<a href="#57">6.3.5. 总结</a>  
&emsp;&emsp;<a href="#58">6.4. union执行流程</a>  
&emsp;&emsp;&emsp;<a href="#59">6.4.1. union all</a>  
&emsp;&emsp;<a href="#60">6.5. group by 执行流程</a>  
&emsp;&emsp;&emsp;<a href="#61">6.5.1. group by 优化 ——索引</a>  
&emsp;&emsp;&emsp;<a href="#62">6.5.2. group by 优化 —— 直接排序</a>  
&emsp;<a href="#63">7. 其他</a>  
&emsp;&emsp;<a href="#64">7.1. Mysql数据库抖动</a>  
&emsp;&emsp;&emsp;<a href="#65">7.1.1. 内存不足刷脏页的情况</a>  
&emsp;&emsp;&emsp;<a href="#66">7.1.2. InnoDB刷脏页的控制策略</a>  
&emsp;&emsp;<a href="#67">7.2. 数据库表数据删除</a>  
&emsp;&emsp;&emsp;<a href="#68">7.2.1. 重建表消除数据空洞</a>  
&emsp;&emsp;<a href="#69">7.3. MySQL执行语句的一些坑</a>  
&emsp;&emsp;&emsp;<a href="#70">7.3.1. 条件字段函数操作</a>  
&emsp;&emsp;&emsp;<a href="#71">7.3.2. 隐式类型转换</a>  
&emsp;&emsp;&emsp;<a href="#72">7.3.3. 隐式字符集编码转换</a>  
&emsp;&emsp;&emsp;<a href="#73">7.3.4. 简单查询长时间不返回</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#74">7.3.4.1. 等MDL锁。</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#75">7.3.4.2. 等flush</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#76">7.3.4.3. 等行锁</a>  
&emsp;&emsp;&emsp;<a href="#77">7.3.5. 查询慢</a>  
&emsp;&emsp;<a href="#78">7.4. Mysql 短时间提升性能方法</a>  
&emsp;&emsp;<a href="#79">7.5. 主从同步流程图</a>  
&emsp;&emsp;<a href="#80">7.6. Innodb的内存管理策略LRU</a>  
# <a name="0">MySql实战45讲</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
## <a name="1">mysql整体的架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/mysqlprocess.jpg)
- MySQL可以分为Server层和存储引擎层两部分
  - Server层包括连接器、查询缓存、分析器、优化器、执行器等，涵盖MySQL的大多数核心服务功能，以及所有的内置函数（如日期、时间、数学和加密函数等），所有跨存储引擎的功能都在这一层实现，比如存储过程、触发器、视图等。
  - 存储引擎层负责数据的存储和提取。其架构模式是插件式的，支持InnoDB、MyISAM、Memory等多个存储引擎。现在最常用的存储引擎是InnoDB，它从MySQL5.5.5版本开始成为了默认存储引擎

### <a name="2">连接器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

### <a name="3">查询缓存</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 连接建立完成之后，会到查询缓存中查询数据。查询缓存的存储默认key为查询语句，而value为结果。
- 查询缓存弊大于利，因为只要有表更新，这个表相关的缓存就会被清除。
- query_cache_type 设置成DEMAND，可取消查询缓存。
- 显示的查询缓存数据 ``` mysql> select SQL_CACHE * from T where ID=10； ```
- mysql 8 版本直接移除了查询缓存。

### <a name="4">分析器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 主要做语法解析，并判断语法是否合规。

### <a name="5">优化器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
在开始执行之前， 还要先经过优化器的处理。
- 优化器是在表里面有多个索引的时候，决定使用哪个索引；或者在一个语句有多表关联（join）的时候， 决定各个表的连接顺序。比如你执行下面这样的语句，这个语句是执行两个表的join：
```mysql> select * from t1 join t2 using(ID) where t1.c=10 and t2.d=20;```
- 既可以先从表t1里面取出c=10的记录的ID值，再根据ID值关联到表t2，再判断t2里面d的值是否等于20。
- 也可以先从表t2里面取出d=20的记录的ID值，再根据ID值关联到t1，再判断t1里面c的值是否
等于10。
这两种执行方法的逻辑结果是一样的，但是执行的效率会有不同，而优化器的作用就是决定选择效率高的方案。

### <a name="6">执行器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
mysql> select * from T where ID=10;
```
- 首先判断语句是否与查询权限，若无则直接返回

- 比如我们这个例子中的表T中，ID字段没有索引，那么执行器的执行流程是这样的：
  1.调用InnoDB引擎接口取这个表的第一行，判断ID值是不是10，如果不是则跳过，如果是则将这行存在结果集中；
  2.调用引擎接口取“下一行”，重复相同的判断逻辑，直到取到这个表的最后一行。
  3.执行器将上述遍历过程中所有满足条件的行组成的记录集作为结果集返回给客户端。

对于有索引的表，执行的逻辑也差不多。第一次调用的是“取满足条件的第一行”这个接口，之后循环取“满足条件的下一行”这个接口，这些接口都是引擎中已经定义好的。
你会在数据库的慢查询日志中看到一个rows_examined的字段，表示这个语句执行过程中扫描了多少行。这个值就是在执行器每次调用引擎获取数据行的时候累加的。
- 在有些场景下，执行器调用一次，在引擎内部则扫描了多行，因此引擎扫描行数跟rows_examined并不是完全相同的。


## <a name="7">redo log 与 binlog</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="8">redo log</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

MySQL里经常说到的WAL技术，WAL的全称是WriteAheadLogging，它的关键点就是先写日志，再写磁盘，也就是先写粉板，等不忙的时候再写账本。
- 当有一条记录需要更新的时候， InnoDB引擎就会先把记录写到redo log（粉板） 里面， 并更新内存， 这个时候更新就算完成了。 同时， InnoDB引擎会在适当的时候， 将这个操作记录更新到磁盘里面， 而这个更新往往是在系统比较空闲的时候做， 这就像打烊以后掌柜做的事。(由于磁盘连接开销大，)
- InnoDB的redo log是固定大小的， 比如可以配置为一组4个文件， 每个文件的大小是1GB， 那么这块“粉板”总共就可以记录4GB的操作。 从头开始写， 写到末尾就又回到开头循环写， 如下面这个图所示

![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/redologwrite.jpg)

redo log buffer ：redo log buffer就是一块内存， 用来先存redo日志的。 在执行事务的时候，如insert、update会先存在buffer中。等事务commit，再一起写入redo log

#### <a name="9">redo log 写入机制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

### <a name="10">binlog</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MySQL整体来看， 其实就有两块： 一块是Server层， 它主要做的是MySQL功能层面的事情； 还有一块是引擎层， 负责存储相关的具体事宜。 上面我们聊到的粉板redo log是InnoDB引擎特有的日志， 而Server层也有自己的日志， 称为binlog（归档日志） 。

#### <a name="11">bin log写入机制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/binlogwrite.jpg)
每个线程有自己binlog cache， 但是共用同一份binlog文件。
1. 图中的write， 指的就是指把日志写入到文件系统的page cache， 并没有把数据持久化到磁盘， 所以速度比较快。
2. 图中的fsync， 才是将数据持久化到磁盘的操作。 一般情况下， 我们认为fsync才占磁盘的IOPS

write 和fsync的时机， 是由参数sync_binlog控制的：
1. sync_binlog=0的时候， 表示每次提交事务都只write， 不fsync；
2. sync_binlog=1的时候， 表示每次提交事务都会执行fsync；
3. sync_binlog=N(N>1)的时候， 表示每次提交事务都write， 但累积N个事务后才fsync。
  
bin log 三种数据格式，主要区别于在存储bin log 的格式区别  越来越多的场景要求把MySQL的binlog格式设置成row，有利于恢复数据。
1. statement 
2. row
3. mix 上面两种的混合

### <a name="12">两种日志有以下三点不同。</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. redo log是InnoDB引擎特有的； binlog是MySQL的Server层实现的， 所有引擎都可以使用。
2. redo log是物理日志， 记录的是“在某个数据页上做了什么修改”； binlog是逻辑日志， 记录的是这个语句的原始逻辑， 比如“给ID=2这一行的c字段加1 ”。
3. redo log是循环写的， 空间固定会用完； binlog是可以追加写入的。 “追加写”是指binlog文件写到一定大小后会切换到下一个， 并不会覆盖以前的日志。
4. 事务提交的时候，一次性将事务中的sql语句（一个事物可能对应多个sql语句）按照一定的格式记录到binlog中。这里与redo log很明显的差异就是redo log并不一定是在事务提交的时候刷新到磁盘，redo log是在事务开始之后就开始逐步写入磁盘。

### <a name="13">一条update语句的执行流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/updateProcess.jpg)

### <a name="14">两阶段提交</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/twocommit.jpg)
两阶段提交：主要用于保证redo log 与binlog 的状态保持逻辑上一致。

图中 两个“commit”的概念：
- “commit语句”， 是指MySQL语法中， 用于提交一个事务的命令。 一般跟begin/start transaction 配对使用。
- 图中用到的这个“commit步骤”， 指的是事务提交过程中的一个小步骤， 也是最后一步。 当这个步骤执行完成后， 这个事务就提交完成了。
- “commit语句”执行的时候， 会包含“commit 步骤

崩溃后的数据恢复阶段
- 如果在更新或写入数据的过程中，机器出现崩溃。那么在机器在重启后，MySQL会首先去验证redolog的完整性，如果redolog中没有prepare状态的记录，则记录是完整的，就日记提交。如果redolog中存在prepare记录，那么就去验证这条redolog对应的binlog记录，如果这条binlog是完整的，那么完整提交redolog，否则执行回滚逻辑
- 崩溃恢复时的判断规则。
    1. 如果redo log里面的事务是完整的， 也就是已经有了commit标识， 则直接提交；
    2. 如果redo log里面的事务只有完整的prepare， 则判断对应的事务binlog是否存在并完整：
       - 如果是， 则提交事务；
       - 否则， 回滚事务。
    - 如果碰到既有prepare、 又有commit的redo log， 就直接提交；
    - 如果碰到只有parepare、 而没有commit的redo log， 就拿着XID去binlog找对应的事务。
         
一个事务的binlog是有完整格式的：
- statement格式的binlog， 最后会有COMMIT；
- row格式的binlog， 最后会有一个XID event
 
为何需要两个日志
- 只使用binlog的话，相当于一个update语句： => binlog write ->commit ->binlog write -> commit
    - 若崩溃在binlog write的阶段，就是crash-unsafe
- 只使用redo log，可以保证crash-safe。
    - binlog作为MySQL一开始就有的功能， 被用在了很多地方。其中， MySQL系统高可用的基础， 就是binlog复制
    - 很多公司有异构系统（比如一些数据分析系统） ， 这些系统就靠消费MySQL的binlog来更新自己的数据。 
    
    
#### <a name="15">两阶段提交的实际执行流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/actualWrite.jpg)
WAL机制主要得益于两个方面：
1. redo log 和 binlog都是顺序写， 磁盘的顺序写比随机写速度要快；
2. 组提交机制， 可以大幅度降低磁盘的IOPS消耗。

  
## <a name="16">事务隔离</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- SQL标准的事务隔离级别包括：
  - 读未提交（ read uncommitted）
  - 读提交（read committed）
  - 可重复读（ repeatable read）
  - 串行化（ serializable ）
  
### <a name="17">区分隔离级别（例子）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/transactionProcess.jpg)

- 若隔离级别是“读未提交”， 则V1的值就是2。 这时候事务B虽然还没有提交， 但是结果已经被A看到了。 因此， V2、 V3也都是2。
- 若隔离级别是“读提交”， 则V1是1， V2的值是2。 事务B的更新在提交后才能被A看到。 所以，V3的值也是2。
- 若隔离级别是“可重复读”， 则V1、 V2是1， V3是2。 之所以V2还是1， 遵循的就是这个要求：事务在执行期间看到的数据前后必须是一致的。
- 若隔离级别是“串行化”， 则在事务B执行“将1改成2”的时候， 会被锁住。 直到事务A提交后，事务B才可以继续执行。 所以从A的角度看， V1、 V2值是1， V3的值是2

- 可重复读的一个应用：账户余额与交易明细核对，通过开启事务，保证核对期间的交易不影响查询核对的过程。
### <a name="18">事务启动的方式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MySQL的事务启动方式有以下几种：
1. 显式启动事务语句， begin 或 start transaction。 配套的提交语句是commit， 回滚语句是rollback。
2. set autocommit=0， 这个命令会将这个线程的自动提交关掉。 意味着如果你只执行一个select语句， 这个事务就启动了， 而且并不会自动提交。 这个事务持续存在直到你主动执行commit 或 rollback 语句， 或者断开连接。

### <a name="19">事务隔离的实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

### <a name="20">关于数据隔离的说明</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

## <a name="21">索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 索引的出现其实就是为了提高数据查询的效率， 就像书的目录一样。 一本500页的书，对于数据库的表而言， 索引其实就是它的“目录”。
- 若一张表中无主键索引，mysql会默认创建一个长度为6字节的rowid主键。

InnoDB里面索引对应一棵B+树
  - 使用B+树而不是二叉搜索树的原因是，由于存储介质的特性，磁盘本身存取就比主存慢很多，每次搜索的磁盘IO的开销过大，而B+树可以使用较少次的磁盘IO搜索到对象。
  - B-Tree中一次检索最多需要h-1次I/O（根节点常驻内存），渐进复杂度为O(h)=O(logdN)。
  - 红黑树这种结构，h明显要深的多。效率明显比B-Tree差很多。

基于主键索引和普通索引的查询有什么区别？
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
 
### <a name="22">唯一索引与普通索引的选择</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 两者查询性能差不多。
2. 主要区别在于，这个记录要更新的目标页不在内存中时。普通索引更新会使用change Buffer。唯一索引，由于需要校验数据的唯一性，因此每次更新操作都需要读磁盘把数据载进内存，涉及IO操作。
- 在不影响数据一致性的前提下， InooDB会将这些更新操作缓存在change buffer中， 这样就不需要从磁盘中读入这个数据页了。 在下次查询需要访问这个数据页的时候， 将数据页读入内存， 然后执行change buffer中与这个页有关的操作。 通过这种方式就能保证这个数据逻辑的正确性
- change buffer中的操作应用到原数据页， 得到最新结果的过程称为merge。 
  - 访问这个数据页会触发merge。
  - 系统有后台线程会定期merge。 
  - 在数据库正常关闭（shutdown） 的过程中，也会执行merge操作。
  
- change buffer的大小， 可以通过参数innodb_change_buffer_max_size来动态设置。 这个参数设置为50的时候， 表示change buffer的大小最多只能占用buffer pool的50%。
- 应用： 写多读少的业务来说， 页面在写完以后马上被访问到的概率比较小， 此时change buffer的使用效果最好。若频繁读写场景，则失去了优势。

change Buffer与redo log 区别
  - change Buffer主要用于减少读磁盘的次数，在必要读磁盘时再更新数据。
  - redo log 则是减少内存更新后，写磁盘的次数。
 
### <a name="23">B树与B+树的特征</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="24">B树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- B树和平衡二叉树稍有不同的是，B树属于多叉树又名平衡多路查找树

规律：
1. 排序方式：所有节点关键字是按递增次序排列，并遵循左小右大原则；
2. 子节点数：非叶节点的子节点数>1，且<=M ，且M>=2，空树除外（注：M阶代表一个树节点最多有多少个查找路径，M=M路,当M=2则是2叉树,M=3则是3叉）；
3. 所有叶子节点均在同一层、叶子节点除了包含了关键字和关键字记录的指针外也有指向其子节点的指针只不过其指针地址都为null对应下图最后一层节点的空格子;
4. 关键字数：枝节点的关键字数量大于等于ceil(m/2)-1个且小于等于M-1个（注：ceil()是个朝正无穷方向取整的函数 如ceil(1.1)结果为2);
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/Btree.jpg)

- B树插入与删除操作：https://zhuanlan.zhihu.com/p/27700617

### <a name="25">B+树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

### <a name="26">索引规则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="27">覆盖索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
InnoDB存储引擎支持覆盖索引，即从辅助索引中就可以得到查询的记录，而不需要查询聚集索引中的记录。
- 如： ``` select id, b from t where b = xxx   (id为主键，b为索引)```
由于覆盖索引可以减少树的搜索次数（减少IO）， 显著提升查询性能， 所以使用覆盖索引是一个常用的性能优化手段。


#### <a name="28">最左前缀原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
B+树这种索引结构，可以利用索引的“最左前缀”， 来定位记录。  
只要满足最左前缀， 就可以利用索引来加速检索。 这个最左前缀可以是联合索引的最左N个字段， 也可以是字符串索引的最左M个字符

如何安排索引内的字段顺序
- 第一原则是优先考虑采用如果通过调整顺序，可以少维护一个索引的方案。
- 第二原则是空间，比如name字段是比age字段大，那建议创建一个（name,age)的联合索引和一个(age)的单字段索引

#### <a name="29">索引下推</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
``` 
mysql> select * from tuser where name like '张%' and age=10 and ismale=1; 
```
mysql 5.6 后引入索引下推。
- 索引遍历过程中，对**索引中包含的字段先做判断**，直接过滤掉不满足条件的记录，减少回表次数
- 旧版本中会进行回表操作，取得相关信息再做判断。

### <a name="30">索引选错及优化器执行逻辑</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

优化器选择索引的目的是找一个最优方案，用最小的代价执行语句。其中扫描行数、是否使用临时表、是否排序等因素都会影响优化器对索引的选择判断。

扫描行数判断，mysql使用采样统计的方式来获取索引的统计信息基数。采样统计的方式可以减少磁盘的IO次数
- InnoDB默认会选择N个数据页， 统计这些页面上的不同值， 得到一个平均值， 然后乘以这个索引的页面数， 就得到了这个索引的基数。
- 一个索引上不同的值的个数， 我们称之为“基数”（cardinality） 
- 当变更的数据行数超过1/M的时候， 会自动触发重新做一次索引统计。
  
在MySQL中， 有两种存储索引统计的方式， 可以通过设置参数innodb_stats_persistent的值来选择：
- 设置为on的时候， 表示统计信息会持久化存储。 这时， 默认的N是20， M是10。
- 设置为off的时候， 表示统计信息只存储在内存中。 这时， 默认的N是8， M是16
- 由于是采样统计， 所以不管N是20还是8， 这个基数都是很容易不准的。
- analyze table t 命令， 可以用来重新统计索引信息。 

![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/errorIndex2.jpg)
使用explain 语句分析SQL的扫描行数信息，rows的结果为根据采样结果预计的扫描行数。
- 选错索引的根本原因为采样统计信息有误统计成了37116行，而由于查询所有字段，使用索引还需要根据ID回表查询其他信息，经过优化器估算，全表扫描代价低。
- 优化器的选择方案时，使用普通索引会把回表的代价也算进去。

#### <a name="31">解决索引选择异常方案</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 使用force index
- 引导Mysql使用我们期望的索引，如order by b,a limit 1 与 order by b limit 1 逻辑上能保持一致，那么就能这么修改
- 在有些场景下， 我们可以新建一个更合适的索引， 来提供给优化器做选择， 或删掉误用的索引。（较少用）

### <a name="32">添加索引的技巧</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="33">前缀索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

#### <a name="34">一些添加索引的技巧</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 倒序存储。比如存储身份证时都倒序存储，而身份证的后六位区分度好，便于建立前缀索引.
  - 缺点：无法查询区间。
  - ```
    mysql> select field_list from t where id_card = reverse('input_id_card_string');
    ```
- 使用Hash字段。每次插入新记录的时候， 都同时用crc32()这个函数得到校验码填到一个新字段.
  - ```
    mysql> alter table t add id_card_crc int unsigned, add index(id_card_crc);
    ```
#### <a name="35">添加索引的一些方式方法比较</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 直接创建完整索引， 这样可能比较占用空间；
2. 创建前缀索引， 节省空间， 但会增加查询扫描次数， 并且不能使用覆盖索引；
3. 倒序存储， 再创建前缀索引， 用于绕过字符串本身前缀的区分度不够的问题；
4. 创建hash字段索引， 查询性能稳定， 有额外的存储和计算消耗， 跟第三种方式一样， 都不支
持范围扫描

## <a name="36">MySql的锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="37">全局锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
根据加锁的范围， MySQL里面的锁大致可以分成全局锁、 表级锁和行锁三类。

全局锁就是对整个数据库实例加锁。MySQL提供了一个加全局读锁的方法， 命令是Flush tables with read lock (FTWRL)。 
- 全局锁的典型使用场景是， 做全库逻辑备份。 
- 官方自带的逻辑备份工具是mysqldump。 当mysqldump使用参数–single-transaction的时候， 导数据之前就会启动一个事务， 来确保拿到一致性视图。 而由于MVCC的支持， 这个过程中数据是可以正常更新的。
    - single-transaction方法只适用于所有的表都可以使用事务引擎的库。 
    - 对于MyISAM这种不支持事务的引擎， 就需要使用FTWRL命令。

### <a name="38">表级锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MySQL里面表级别的锁有两种： 一种是表锁， 一种是元数据锁（meta data lock，MDL)。

表锁的语法是 lock tables …read/write。 
- 举个例子, 如果在某个线程A中执行lock tables t1 read, t2 write; 这个语句， 则其他线程写t1、 读写t2的语句都会被阻塞。 同时， 线程A在执行unlock tables之前， 也只能执行读t1、 读写t2的操作。 连写t1都不允许， 自然也不能访问其他表
- InnoDB这种支持行锁的引擎， 一般不使用lock tables命令来控制并发， 毕竟锁住整个表的影响面还是太大。
  
另一类表级的锁是MDL（metadata lock)。 
- 当对一个表做增删改查操作的时候， 加MDL读锁； 
- 当要对表做结构变更操作的时候， 加MDL写锁。
- 读锁之间不互斥， 因此可以有多个线程同时对一张表增删改查。
- 读写锁之间、 写锁之间是互斥的， 用来保证变更表结构操作的安全性。 因此， 如果有两个线程要同时给一个表加字段， 其中一个要等另一个执行完才能开始执行。
- 而当读写锁阻塞时，则后序的读锁也无法正常共享。
  
如何安全地给小表加字段？
- 在alter table语句里面设定等待时间， 如果在这个指定的等待时间里面能够拿到MDL写锁最好， 拿不到也不要阻塞后面的业务语句， 先放弃。 之后开发人员或者DBA再通过重试命令重复这个过程。
  
### <a name="39">行级锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MySQL的行锁是在引擎层由各个引擎自己实现的。 MyISAM引擎就不支持行锁，InnoDB是支持行锁的。

InnoDB的行锁是针对索引加的锁，不是针对记录加的锁，并且该索引不能失效，否则都会从行锁升级为表锁

#### <a name="40">两阶段锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/linelock.jpg)
- 事务B的update语句会被阻塞， 直到事务A执行commit之后， 事务B才能继续执行
- 在InnoDB事务中， 行锁是在需要的时候才加上的， 但并不是不需要了就立刻释放， 而是要等到事务结束时才释放。 这个就是两阶段锁协议
- 如果你的事务中需要锁多个行， 要把最可能造成锁冲突、 最可能影响并发度的锁尽量往后放。

### <a name="41">死锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/deadlock.jpg)
事务A在等待事务B释放id=2的行锁， 而事务B在等待事务A释放id=1的行锁。 事务A和事务B在互相等待对方的资源释放， 就是进入了死锁状态。 
- 一种策略是， 直接进入等待， 直到超时。 这个超时时间可以通过参数innodb_lock_wait_timeout来设置。在InnoDB中， innodb_lock_wait_timeout的默认值是50s
- 另一种策略是， 发起死锁检测， 发现死锁后， 主动回滚死锁链条中的某一个事务， 让其他事务得以继续执行。 将参数innodb_deadlock_detect设置为on， 表示开启这个逻辑。
    - 弊端：判断是否存在死锁的成本会随着数据量的增长，而大量消耗CPU。假设有1000个并发线程要同时更新同一行， 那么死锁检测操作就是100万这个量级的。
    - 解决方案：1.确定不会出现死锁，关闭死锁检测。2.控制并发度。3.改写mysql源码。
    
### <a name="42">幻读(间隙锁)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/phantomRead.jpg)
- session A里执行了三次查询， 分别是Q1、 Q2和Q3。 它们的SQL语句相同， 都是select * from t where d=5 for update。 表示查所有d=5的行， 而且使用的是当前读， 并且加上写锁。 
- 其中， Q3读到id=1这一行的现象， 被称为“幻读”。 也就是说， 幻读指的是一个事务在前后两次查询同一个范围的时候， 后一次查询看到了前一次查询没有看到的行
- 幻读会导致数据一致性的问题。 锁的设计是为了保证数据的一致性。 而这个一致性， 不止是数据库内部数据状态在此刻的一致性， 还包含了数据和日志在逻辑上的一致性。
  1. 在可重复读隔离级别下， 普通的查询是快照读， 是不会看到别的事务插入的数据的。 因此，幻读在“当前读”下才会出现。
  2. 上面session B的修改结果， 被session A之后的select语句用“当前读”看到， 不能称为幻读。幻读仅专指“新插入的行”

![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/phantomRead2.jpg)
- 尝试解决幻读，把所有语句都上锁，查询语句改成select * from t for update。但是仍然无法解决插入新语句出现的幻读现象。

#### <a name="43">如何解决幻读？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- InnoDB引入新的锁， 也就是间隙锁(Gap Lock)。在一行行扫描的过程中， 不仅将给行加上了行锁， 还给行两边的空隙， 也加上了间隙锁。

间隙锁之间的冲突：跟间隙锁存在冲突关系的， 是“往这个间隙中插入一个记录”这个操作。 间隙锁之间都不存在冲突关系。

间隙锁和行锁合称next-key lock， 每个next-key lock是前开后闭区间。 
- 如果用select * from t for update要把整个表所有记录锁起来， 就形成了7个next-key lock， 分别是 (-∞,0]、 (0,5]、 (5,10]、 (10,15]、 (15,20]、 (20, 25]、 (25, +supremum]。
- InnoDB给每个索引加了一个不存在的最大值supremum。

间隙锁的引入， 可能会导致同样的语句锁住更大的范围， 这其实是影响了并发度的。

#### <a name="44">加锁原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
总结的加锁规则里面， 包含了两个“原则”、 两个“优化”和一个“bug”。
1. 原则1： 加锁的基本单位是next-keylock。 希望你还记得， next-keylock是前开后闭区间。
2. 原则2： 查找过程中访问到的对象才会加锁。
3. 优化1： 索引上的等值查询， 给唯一索引加锁的时候， next-keylock退化为行锁。
4. 优化2： 索引上的等值查询， 向右遍历时且最后一个值不满足等值条件的时候， next-key lock退化为间隙锁。
5. 一个bug： 唯一索引上的范围查询会访问到不满足条件的第一个值为止。

#### <a name="45">间隙锁相关实例说明</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 一个next Key Lock 的加锁例子
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/phantomRead3.jpg)
1. 开始执行的时候， 要找到第一个id=10的行， 因此本该是next-keylock(5,10]。 根据优化1，主键id上的等值条件， 退化成行锁， 只加了id=10这一行的行锁。
2. 范围查找就往后继续找， 找到id=15这一行停下来， 因此需要加next-keylock(10,15]。所以， session A这时候锁的范围就是主键索引上， 行锁id=10和next-keylock(10,15]。

- 死锁案例（间隙锁不互斥导致）
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/phantomRead4.jpg)
1. session A 启动事务后执行查询语句加lock in share mode， 在索引c上加了next-key lock(5,10] 和间隙锁(10,15)；
2. session B 的update语句也要在索引c上加next-keylock(5,10] ， 进入锁等待；
3. 然后session A要再插入(8,8,8)这一行， 被session B的间隙锁锁住。 由于出现了死锁， InnoDB让session B回滚
- session B的“加next-keylock(5,10] ”操作， 实际上分成了两步， 先是加(5,10)的间隙锁， 加锁成功； 然后加c=10的行锁， 这时候才被锁住的。


##### <a name="46">一组next-key lock 案例</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 初始表数据
```
CREATE TABLE `t` (
`id` int(11) NOT NULL,
`c` int(11) DEFAULT NULL,
`d` int(11) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `c` (`c`)
) ENGINE=InnoDB;
//
insert into t values(0,0,0),(5,5,5),
(10,10,10),(15,15,15),(20,20,20),(25,25,25);
```

- 不等号条件里的等值查询
```
begin;
select * from t where id>9 and id<12 order by id desc for update;
```
  - 主键索引上的 (0,5]、 (5,10]和(10, 15)。
  - id=15这一行， 并没有被加上行锁，这用到了优化2，即索引上的等值查询， 向右遍历的时候id=15不满足条件， 所以next-keylock退化为了间隙锁 (10, 15)。

- 等值查询的过程
```
begin;
select id from t where c in(5,20,10) lock in share mode;
```
  - 查找c=5的时候， 先锁住了(0,5]。 但是因为c不是唯一索引， 为了确认还有没有别的记录c=5，就要向右遍历， 找到c=10才确认没有了， 这个过程满足优化2， 所以加了间隙锁(5,10)。同样的， 执行c=10这个逻辑的时候， 加锁的范围是(5,10] 和 (10,15)； 执行c=20这个逻辑的时候， 加锁的范围是(15,20] 和 (20,25)
  - 这个加锁范围， 从(5,25)中去掉c=15的行锁.
  
- order by 加锁过程
```
select id from t where c in(5,20,10) order by c desc for update;
```
  - 由于语句里面是order byc desc， 这三个记录锁的加锁顺序， 是先锁
    c=20， 然后c=10， 最后是c=5
    
- 锁范围增长
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/nextKeyLockExtend.jpg)
- 由于session A并没有锁住c=10这个记录， 所以session B删除id=10这一行是可以的。 但是之后， session B再想insert id=10这一行回去就不行了
- 由于delete操作把id=10这一行删掉了， 原来的两个间隙(5,10)、 (10,15）变成了一个(5,15)

## <a name="47">SQL 语句执行流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="48">count(*)实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
不同引擎中的实现
- MyISAM引擎把一个表的总行数存在了磁盘上， 因此执行count(*)的时候会直接返回这个数，效率很高；
- 而InnoDB引擎就麻烦了， 它执行count(*)的时候， 需要把数据一行一行地从引擎里面读出来， 然后累积计数
  
InnoDB中Mysql对于count(*)的优化：InnoDB是索引组织表， 主键索引树的叶子节点是数据， 而普通索引树的叶子节点是主键值。 所以， 普通索引树比主键索引树小很多。 通索引树比主键索引树小很多。 对于count(*)这样的操作， 遍历哪个索引树得到的结果逻辑上都是一样的。 因此， MySQL优化器会找到最小的那棵树来遍历。 在保证逻辑正确的前提下， 尽量减少扫描的数据量， 是数据库系统设计的通用法则之一。

show table status 命令也可以显示行数，这里的行数是基于采样统计的，并不准确。

使用Redis缓存记录的行数，由于无法控制并发的执行时刻，会出现，读取的行数不一致的情况。比如数据库已插入，而Redis未增加计数。

不同count的用法：count(*)、 count(主键id)、 count(字段)和count(1)
- count()是一个聚合函数， 对于返回的结果集， 一行行地判断， 如果count函数的参数不是NULL， 累计值就加1， 否则不加。 最后返回累计值
- 对于count(主键id)来说， InnoDB引擎会遍历整张表， 把每一行的id值都取出来， 返回给server层。 server层拿到id后， 判断是不可能为空的， 就按行累加。
- 对于count(1)来说， InnoDB引擎遍历整张表， 但不取值。 server层对于返回的每一行， 放一个数字“1”进去， 判断是不可能为空的， 按行累加。
- 对于count(字段)来说：
    1. 如果这个“字段”是定义为not null的话， 一行行地从记录里面读出这个字段， 判断不能为null， 按行累加；
    2. 如果这个“字段”定义允许为null， 那么执行的时候， 判断到有可能是null， 还要把值取出来再判断一下， 不是null才累加。
- count(*)是例外， 并不会把全部字段取出来， 而是专门做了优化， 不取值。 count(*)肯定不是null， 按行累加。
- 按照效率排序的话， count(字段)<count(主键id)<count(1)≈count(*)
  
### <a name="49">order by 处理流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="50">全字段排序</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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
  
optimizer_trace 是一个跟踪功能，跟踪执行的语句的解析优化执行的过程，比explain更详细。
  - number_of_tmp_files表示的是， 排序过程中使用的临时文件数。
  - sort_mode: 表示参与排序的只有name和id这两个字段
  - ```
    /* 打开optimizer_trace， 只对本线程有效 */
    SET optimizer_trace='enabled=on';
    ```
这里的排序使用的是归并排序

#### <a name="51">rowId排序</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
max_length_for_sort_data :是MySQL中专门控制用于排序的行数据的长度的一个参数。 它的意思是， 如果单行的长度超过这个值， MySQL就认为单行太大， 要换一个算法。
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/orderby3.jpg)
- 主要体现在内存排序完毕之后要多一次查询。

对于InnoDB表来说， 执行全字段排序会减少磁盘访问， 因此会被优先选择。

在“InnoDB表”中，对于内存表，回表过程只是简单地根据数据行的位置， 直接访问内存得到数据， 根本不会导致多访问磁盘。 
> 优化器会优先考虑的，就是用于排序的行越少越好。
  
order by rand()使用了内存临时表， 内存临时表排序的时候使用了rowid排序方法。

内存临时表与磁盘临时表
- tmp_table_size这个配置限制了内存临时表的大小， 默认值是16M。 如果临时表大小超过了tmp_table_size， 那么内存临时表就会转成磁盘临时表
  
直接使用order byrand()， 这个语句需要Using temporary和 Using filesort， 查询的执行代价往往是比较大的

### <a name="52">join的执行过程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="53">Index Nested-Loop Join</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

#### <a name="54">Simple Nested-Loop Join</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
select * from t1 straight_join t2 on (t1.a=t2.b);
```
- 表t2的字段b上没有索引，关联查询使用全表扫描。
- SQL请求就要扫描表t2多达100次， 总共扫描100*1000=10万行。

#### <a name="55">Block Nested-Loop Join</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
将驱动表数据读入线程内存join_buffer中，同样以全表扫描，但是因为使用内存操作，速度比上述方法快。

#### <a name="56">join语句mysql的优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Multi-Range Read优化
- 大多数的数据都是按照主键递增顺序插入得到的， 所以可以认为， 如果按照主键的递增顺序查询的话， 对磁盘的读比较接近顺序读， 能够提升读性能
- MRR优化思路即将查询的关联集合排序，再关联查询，提高查询效率。将随机访问改成范围访问。

Batched Key Access （BAK）
- 将驱动表数据取出放join_buffer中，进行排序再关联查询。
- join_buffer内存不够大时，进行多次的重复操作。

#### <a name="57">总结</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 尽量使用被驱动表的索引，即关联表的字段为索引。
2. 不能使用被驱动表的索引， 只能使用Block Nested-Loop Join算法， 这样的语句就尽量不要使用；
3. 在使用join的时候， 应该让小表做驱动表。
4. 把join 的条件写在where和写在on中区别为，一个为连接的条件。

### <a name="58">union执行流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
(select 1000 as f) union (select id from t1 order by id desc limit 2);
```
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/union.jpg)

执行流程是这样的：
1. 创建一个内存临时表， 这个临时表只有一个整型字段f， 并且f是主键字段。
2. 执行第一个子查询， 得到1000这个值， 并存入临时表中。
3. 执行第二个子查询：拿到第一行id=1000， 试图插入临时表中。 但由于1000这个值已经存在于临时表了， 违反了唯一性约束， 所以插入失败， 然后继续执行；取到第二行id=999， 插入临时表成功。
4. 从临时表中按行取出数据， 返回结果， 并删除临时表， 结果中包含两行数据分别是1000和999

#### <a name="59">union all</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
(select 1000 as f) union all(select id from t1 order by id desc limit 2);
```
- 与上面的区别为union all不需要除重，因此直接把查询结果放在结果集中返回。

### <a name="60">group by 执行流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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
#### <a name="61">group by 优化 ——索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
索引保证了数据有序，在group by时候，分组计数计算时一片区域的id都是连续的，整个表扫描结束时便可以拿到结果，不需要临时表也不需要排序。

#### <a name="62">group by 优化 —— 直接排序</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
确保数据量确实超过了sort buffer，可以直接强制mysql直接使用磁盘文件排序。
```
select SQL_BIG_RESULT id%100 as m, count(*) as c from t1 group by m;
```

  
## <a name="63">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="64">Mysql数据库抖动</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 当内存数据页跟磁盘数据页内容不一致的时候， 我们称这个内存页为“脏页”。 
- 在内存数据写入到磁盘后， 内存和磁盘上的数据页的内容就一致了， 称为“干净页”。

![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/redologFlush.jpg)
Mysql 数据库抖动可能就是在刷“脏页”。两种触发刷脏页（flush）的方法
- 第一种：对应的就是InnoDB的redo log写满了。 这时候系统会停止所有更新操作， 把checkpoint往前推进， redo log留出空间可以继续写。
- 第二种：系统的内存需要新的内存页，这时候需要淘汰一些内存也。这如果是脏页，就会把脏页刷到内存中，然后淘汰脏页。
    - 为什么不直接淘汰脏页，等新数据读取的时候再应用redo log？ 主要为了保证状态统一，内存的数据存在则肯定是最新的，内存没有则文件肯定是最新的。
- 第三种：Mysql认为系统空闲时，刷脏页。
- 第四种：MySql关闭时刷脏页。
  
#### <a name="65">内存不足刷脏页的情况</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
缓冲池中的内存页有三种状态：
- 第一种是， 还没有使用的；
- 第二种是， 使用了并且是干净页；
- 第三种是， 使用了并且是脏页。
  
- 如果要淘汰的是一个干净页， 就直接释放出来复用； 但如果是脏页呢， 就必须将脏页先刷到磁盘， 变成干净页后才能复用。
  
#### <a name="66">InnoDB刷脏页的控制策略</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - innodb_io_capacity这个参数，会告诉InnoDB你的磁盘能力。
    - 建议设置成磁盘的IOPS。 磁盘的IOPS可以通过fio这个工具来测试
  - 参数设置过低会导致InnoDB认为这个系统的能力就这么差， 所以刷脏页刷得特别慢， 甚至比脏页生成的速度还慢， 这样就造成了脏页累积， 影响了查询和更新性能
  - 参数设置太高会影响除Mysql外的服务响应。
  
- InnoDB的刷盘速度就是要参考这两个因素： 一个是脏页比例， 一个是redo log写盘速度。
  - 参数innodb_max_dirty_pages_pct是脏页比例上限， 默认值是75%。 InnoDB会根据当前的脏页比例（假设为M） ，算出一个范围在0到100之间的数字F(M)
  - InnoDB每次写入的日志都有一个序号， 当前写入的序号跟checkpoint对应的序号之间的差值，我们假设为N，计算出F(N)。 
  - 上述算得的F1(M)和F2(N)两个值， 取其中较大的值记为R， 之后引擎就可以按 照innodb_io_capacity定义的能力乘以R%来控制刷脏页的速度。
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/memoryflash.jpg)


### <a name="67">数据库表数据删除</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
参数innodb_file_per_table
- OFF表示的是， 表的数据放在系统共享表空间， 也就是跟数据字典放在一起.
- ON表示的是， 每个InnoDB表数据存储在一个以 .ibd为后缀的文件中。
- 将innodb_file_per_table设置为ON，文件的存储形式便于管理。
  
delete命令其实只是把记录的位置， 或者数据页标记为了“可复用”， 但磁盘文件的大小是不会变的。 通过delete命令是不能回收表空间的

删除数据会造成空洞， 插入数据也会。主要体现在插入数据出现页分裂，那么分裂完成的页势必存在空洞位置。

#### <a name="68">重建表消除数据空洞</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/rebuildTable.jpg)
```
alter table A engine=InnoDB
```
Online DDL重建表的流程:
1. 建立一个临时文件， 扫描表A主键的所有数据页；
2. 用数据页中表A的记录生成B+树， 存储到临时文件中；
3. 生成临时文件的过程中， 将所有对A的操作记录在一个日志文件（row log） 中， 对应的是图中state2的状态；
4. 临时文件生成后， 将日志文件中的操作应用到临时文件， 得到一个逻辑数据上与表A相同的数据文件， 对应的就是图中state3的状态；
5. 用临时文件替换表A的数据文件。
- alter语句在启动的时候需要获取MDL写锁， 但是这个写锁在真正拷贝数据之前就退化成读锁了。同时禁止其他线程对这个表同时做DDL。
- 在图4中， 根据表A重建出来的数据是放在“tmp_file”里的， 这个临时文件是InnoDB在内部创建出来的。 整个DDL过程都在InnoDB内部完成。 对于server层来说， 没有把数据挪动到临时表， 是一个“原地”操作， 这就是“inplace”名称的来源。


### <a name="69">MySQL执行语句的一些坑</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="70">条件字段函数操作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
mysql> select count(*) from tradelog where month(t_modified)=7;
```
对索引字段做函数操作， 可能会破坏索引值的有序性， 因此优化器就决定放弃走树搜索功能。

例子里， 放弃了树搜索功能， 优化器可以选择遍历主键索引， 也可以选择遍历索引t_modified， 优化器对比索引大小后发现， 索引t_modified更小， 遍历这个索引比遍历主键索引来得更快。 因此最终还是会选择索引t_modified。

#### <a name="71">隐式类型转换</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
// tradeid的字段类型是varchar(32)
mysql> select * from tradelog where tradeid=110717;
// 对于优化器会变成
mysql> select * from tradelog where CAST(tradid AS signed int) = 110717;
```
这条语句触发了我们上面说到的规则： 对索引字段做函数操作， 优化器会放弃走树搜索功能。

#### <a name="72">隐式字符集编码转换</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
表间字符集不同导致索引失效，连接过程中要求在被驱动表的索引字段上加函数操作
- 如两个表的字符集不同， 一个是utf8， 一个是utf8mb4， 所以做表连接查询的时候用不上关联字段的索引。 utf8mb4是utf8的超集。
- 较常见的优化方法是， 把trade_detail表上的tradeid字段的字符集也改成utf8mb4
- 如果数据量比较大， 或者业务上暂时不能做这个DDL的话， 那就只能采用修改SQL语句的方法了
    - 如转换集合CONVERT($R4.tradeid.value USING utf8mb4);
    - 或者将转换函数作用在连接的值上，解决了函数作用于索引上导致索引失效的。
    
#### <a name="73">简单查询长时间不返回</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

##### <a name="74">等MDL锁。</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 使用show processlist命令查看Waiting for table metadata lock
- 通过查询sys.schema_table_lock_waits这张表， 我们就可以直接找出造成阻塞的process id， 把这个连接用kill 命令断开即可
    - ```
      mysql> select blocking_pid from sys.schema_table_lock_waits 
      ```

##### <a name="75">等flush</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - ```
    flush tables t with read lock;
    flush tables with read lock;
    ```
  - 两个flush table的语句
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/longquery.jpg)

##### <a name="76">等行锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
mysql> select * from t where id=1 lock in share mode;
```
Session A 开启事务执行更新还未提交事务。Session B 使用该语句查询时就会等待行锁释放。

sys.innodb_lock_waits 表：可以用来查询行锁的占用情况
```
mysql> select * from t sys.innodb_lock_waits where locked_table=`'test'.'t'`\G
```

#### <a name="77">查询慢</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/slowquery1.jpg)

![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/slowquery2.jpg)
```
//session A
select * from t where id=1

//session B
select * from t where id=1 lock in share mode
```
session B更新完100万次， 生成了100万个回滚日志(undo log)。带lock in share mode的SQL语句， 是当前读， 因此会直接读到1000001这个结果， 所以速度很快； 而select * from t where id=1这个语句， 是一致性读， 因此需要从1000001开始， 依次执行undo log， 执行了100万次以后， 才将1这个结果返回。


### <a name="78">Mysql 短时间提升性能方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用短连接的风险：短时间连接暴增
- 第一种方法： 先处理掉那些占着连接但是不工作的线程
- 第二种方法： 减少连接过程的消耗。（让数据库跳过权限验证阶段） 不推荐

慢查询导致的性能问题
1. 索引未设计好。若使用主从可以再从库执行索引，再进行主从切换。
2. 另一种查询问题，语句没写好
- 应急方案使用：使用查询重写功能， 给原来的语句加上force index，
- 提前发现：在上线前回归测试，使用slow log 记录
    
QPS突增
 1. 一种是由全新业务的bug导致的。 假设你的DB运维是比较规范的，从数据库端直接把白名单去掉
 2. 如果这个新功能使用的是单独的数据库用户， 可以用管理员账号把这个用户删掉， 然后断开现有连接。 这样， 这个新功能的连接不成功， 由它引发的QPS就会变成0。
 3. 如果这个新增的功能跟主体功能是部署在一起的， 那么我们只能通过处理语句来限制。可能造成“误伤”。
 
### <a name="79">主从同步流程图</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/masterSlave.jpg)
一个事务日志同步的完整过程是这样的：
1. 在备库B上通过change master命令， 设置主库A的IP、 端口、 用户名、 密码， 以及要从哪个位置开始请求binlog， 这个位置包含文件名和日志偏移量。
2. 在备库B上执行start slave命令， 这时候备库会启动两个线程， 就是图中的io_thread和sql_thread。 其中io_thread负责与主库建立连接。
3. 主库A校验完用户名、 密码后， 开始按照备库B传过来的位置， 从本地读取binlog， 发给B。
4. 备库B拿到binlog后， 写到本地文件， 称为中转日志（relaylog） 。
5. sql_thread读取中转日志， 解析出日志里的命令， 并执行。



### <a name="80">Innodb的内存管理策略LRU</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/LRU1.jpg)
InnoDB管理Buffer Pool的LRU算法， 是用链表来实现的。
1. 在图中的状态1里， 链表头部是P1， 表示P1是最近刚刚被访问过的数据页。
2. 状态2 表示刚访问过P3，移到表头
3. 若有新数据则添加到表头，若内存已满，移除表尾的数据。

innoDB对LRU改进，防止大数据量查询导致，内存的数据命中率突然下降过快。
![image](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/mysql/picture/LRU2.jpg)
在InnoDB实现上， 按照5:3的比例把整个LRU链表分成了young区域和old区域。 图中LRU_old指向的就是old区域的第一个位置， 是整个链表的5/8处。 也就是说， 靠近链表头部的5/8是young区域， 靠近链表尾部的3/8是old区域。

young区域的数据和之前的算法一致，而针对新数据都是插入到old区域，因此young区域的数据不受影响，保证了业务的数据命中率。

处于old区域的数据页， 每次被访问的时候都要做下面这个判断：
  - 若这个数据页在LRU链表中存在的时间超过了1秒， 就把它移动到链表头部；
  - 如果这个数据页在LRU链表中存在的时间短于1秒， 位置保持不变。 1秒这个时间， 是由参数innodb_old_blocks_time控制的。 其默认值是1000， 单位毫秒

