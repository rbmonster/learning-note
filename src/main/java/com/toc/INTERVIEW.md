<a name="index">**Index**</a>
&emsp;&emsp;<a href="#0"> 读已提交和可重复读是如何实现的？</a>  
&emsp;&emsp;<a href="#1">读已提交和可重复读区别。</a>  
&emsp;&emsp;<a href="#2"> 数据库数据库一致性是如何实现的？</a>  
&emsp;&emsp;<a href="#3"> redolog、undolog、binlog区别？</a>  
&emsp;&emsp;<a href="#4"> 两种日志有以下三点不同。</a>  
&emsp;&emsp;<a href="#5">主键索引和非主键索引有什么区别？</a>  
&emsp;&emsp;<a href="#6">索引失效有哪些？</a>  
&emsp;&emsp;<a href="#7">如何优化SQL</a>  
&emsp;&emsp;<a href="#8">联合索引 </a>  
&emsp;&emsp;<a href="#9">数据库什么情况会出现死锁？如何处理死锁？</a>  
 ### <a name="0">读已提交和可重复读是如何实现的？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- ReadView：刚才说了更新操作，那查询操作呢？这才是实现不同隔离级别的关键地方
  - 当进行查询操作时，事务会生成一个ReadView，ReadView是一个事务快照，准确来说是当前时间点系统内活跃的事务列表，也就是说系统内所有未提交的事务，都会记录在这个Readview内，事务就根据它来判断哪些数据是可见的，哪些是不可见的。
  - 查询一条数据时，事务会拿到这个ReadView，去到undo log中进行判断。若查询到某一条数据：
- undo log
  - 当事务对数据行进行一次更新操作时，会把旧数据行记录在一个叫做undo log的记录中，在undo log中除了记录数据行，还会记录下该行数据的对应的创建版本号，也就是生成这行数据的事务id。并连接原纪录
    

### <a name="1">读已提交和可重复读区别。</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
 - 举两个事务线程的例子。

 ### <a name="2">数据库数据库一致性是如何实现的？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - 通过redolog 与binlog 保证事务一致性。
 ### <a name="3">redolog、undolog、binlog区别？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
 - redolog：确保事务的持久性。防止在发生故障的时间点，尚有脏页未写入磁盘，在重启mysql服务的时候，根据redo log进行重做，从而达到事务的持久性这一特性。
 - undo log：保存了事务发生之前的数据的一个版本，可以用于回滚，同时可以提供多版本并发控制下的读（MVCC），也即非锁定读
 ### <a name="4">两种日志有以下三点不同。</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
   1. redo log是InnoDB引擎特有的； binlog是MySQL的Server层实现的， 所有引擎都可以使用。
   2. redo log是物理日志， 记录的是“在某个数据页上做了什么修改”； binlog是逻辑日志， 记录的是这个语句的原始逻辑， 比如“给ID=2这一行的c字段加1 ”。
   3. redo log是循环写的， 空间固定会用完； binlog是可以追加写入的。 “追加写”是指binlog文件写到一定大小后会切换到下一个， 并不会覆盖以前的日志。
  
- binlog的作用？（说的是监控，其实主要是主从复制或者备份）
  - 用于复制，在主从复制中，从库利用主库上的binlog进行重播，实现主从同步。 
  - 用于数据库的基于时间点的还原。
 
### <a name="5">主键索引和非主键索引有什么区别？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 主键索引也被称为聚簇索引,叶子节点存放的是整行数据; 而非主键索引被称为二级索引,叶子节点存放的是主键的值.

### <a name="6">索引失效有哪些？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. like以%开头;没有最左匹配
2. 查询条件需要类型转换
3. where中索引列有运算;
4. where中索引列使用了函数;
5. 如果条件中有or，即使其中有条件带索引也不会使用(这也是为什么尽量少用or的原因)
6. mysql估计使用全表扫描要比使用索引快,则不使用索引

### <a name="7">如何优化SQL</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 通过慢查询日志获取sql语句
2. 对sql进行基本的优化，看是不是有用函数导致了索引不生效的情况。
3. 执行explain语句 查看SQL执行情况。
4. 针对未走索引的情况，可以使用强制走索引的方式
5. 针对复合索引创建顺序有误，导致了索引生效，修改索引。
6. 对于走错索引，说明mysql在统计行信息出错（由于磁盘，mysql使用采样统计的方式），通过执行analysis table。建议低峰使用。确定索引无用，可以删除干扰索引。第三种，强制走索引。。
7. 数据集过大，索引失效。

### <a name="8">联合索引 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1.商家id，2.订单时间,3.订单id，查询的时候会命中几个字段？(1个，时间会失效)
  
### <a name="9">数据库什么情况会出现死锁？如何处理死锁？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- mysql 两个事物更新条件互斥，进入循环等待。
- 使用mysql参数innodb_deadlock_detect设置为on， 表示开启这个死锁检测逻辑。
- show processlist;显示哪些线程正在运行。您也可以使用mysqladmin processlist语句得到此信息
  - 执行kill 命令
  