<a name="index">**Index**</a>

<a href="#0">MySQL</a>  
&emsp;<a href="#1">1. MySQL基本架构</a>  
&emsp;&emsp;<a href="#2">1.1. Server层基本架构</a>  
&emsp;&emsp;<a href="#3">1.2. 引擎</a>  
&emsp;<a href="#4">2. 索引</a>  
&emsp;&emsp;<a href="#5">2.1. 主键索引、唯一索引、普通索引</a>  
&emsp;&emsp;&emsp;<a href="#6">2.1.1. 主键索引与普通索引区别</a>  
&emsp;&emsp;&emsp;<a href="#7">2.1.2. 唯一索引与普通索引</a>  
&emsp;&emsp;<a href="#8">2.2. 索引数据结构</a>  
&emsp;&emsp;&emsp;<a href="#9">2.2.1. B树</a>  
&emsp;&emsp;&emsp;<a href="#10">2.2.2. B+树</a>  
&emsp;&emsp;&emsp;<a href="#11">2.2.3. 为什么使用B+树数据结构</a>  
&emsp;&emsp;<a href="#12">2.3. 增删操作与索引</a>  
&emsp;&emsp;<a href="#13">2.4. 索引规则</a>  
&emsp;&emsp;&emsp;<a href="#14">2.4.1. 覆盖索引</a>  
&emsp;&emsp;&emsp;<a href="#15">2.4.2. 最左前缀原则</a>  
&emsp;&emsp;&emsp;<a href="#16">2.4.3. 索引下推</a>  
&emsp;&emsp;<a href="#17">2.5. 索引选择规则</a>  
&emsp;&emsp;&emsp;<a href="#18">2.5.1. 导致索引失效情况</a>  
&emsp;&emsp;&emsp;<a href="#19">2.5.2. 采样统计储存导致走错索引</a>  
&emsp;&emsp;<a href="#20">2.6. 添加索引技巧</a>  
&emsp;&emsp;&emsp;<a href="#21">2.6.1. 前缀索引</a>  
&emsp;&emsp;&emsp;<a href="#22">2.6.2. 倒序存储</a>  
&emsp;&emsp;&emsp;<a href="#23">2.6.3. hash字段存储</a>  
&emsp;&emsp;&emsp;<a href="#24">2.6.4. 上述添加索引方法比较</a>  
&emsp;&emsp;&emsp;<a href="#25">2.6.5. 其他</a>  
&emsp;<a href="#26">3. MySQL的锁</a>  
&emsp;&emsp;<a href="#27">3.1. 全局锁</a>  
&emsp;&emsp;<a href="#28">3.2. 表锁</a>  
&emsp;&emsp;<a href="#29">3.3. 行锁</a>  
&emsp;&emsp;&emsp;<a href="#30">3.3.1. 两阶段锁</a>  
&emsp;&emsp;<a href="#31">3.4. 死锁</a>  
&emsp;&emsp;&emsp;<a href="#32">3.4.1. 死锁策略</a>  
&emsp;&emsp;<a href="#33">3.5. 幻读(间隙锁)</a>  
&emsp;&emsp;&emsp;<a href="#34">3.5.1. 如何解决幻读？</a>  
&emsp;&emsp;&emsp;<a href="#35">3.5.2. 加锁原则</a>  
&emsp;&emsp;<a href="#36">3.6. 悲观锁</a>  
&emsp;&emsp;<a href="#37">3.7. 乐观锁</a>  
&emsp;<a href="#38">4. 事务</a>  
&emsp;<a href="#39">5. 锁与隔离级别</a>  
&emsp;&emsp;<a href="#40">5.1. 串行化</a>  
&emsp;&emsp;<a href="#41">5.2. RR可重复读（Repeatable Read）TODO</a>  
&emsp;&emsp;&emsp;<a href="#42">5.2.1. 不可重复读问题解决</a>  
&emsp;&emsp;&emsp;<a href="#43">5.2.2. 幻读解决（间隙锁+行锁）</a>  
&emsp;&emsp;<a href="#44">5.3. RC读已提交（Read Committed）</a>  
&emsp;&emsp;&emsp;<a href="#45">5.3.1. 脏读解决</a>  
&emsp;&emsp;&emsp;<a href="#46">5.3.2. 不可重复读问题出现</a>  
&emsp;&emsp;<a href="#47">5.4. RU读未提交（Read Uncommitted）</a>  
&emsp;&emsp;&emsp;<a href="#48">5.4.1. 脏读问题出现</a>  
&emsp;&emsp;<a href="#49">5.5. 多版本并发控制（Multi-Version Concurrency Control，MVCC）</a>  
&emsp;&emsp;&emsp;<a href="#50">5.5.1. 前置知识</a>  
&emsp;&emsp;&emsp;<a href="#51">5.5.2. MVCC原理</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#52">5.5.2.1. 版本链</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#53">5.5.2.2. undo log</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#54">5.5.2.3. Read View(读视图)</a>  
&emsp;&emsp;&emsp;<a href="#55">5.5.3. 隔离级别与MVCC</a>  
&emsp;&emsp;&emsp;<a href="#56">5.5.4. 参考资料</a>  
&emsp;<a href="#57">6. redo log、undo log、binlog</a>  
&emsp;&emsp;<a href="#58">6.1. undo log</a>  
&emsp;&emsp;<a href="#59">6.2. redo log</a>  
&emsp;&emsp;&emsp;<a href="#60">6.2.1. redo log 写入机制</a>  
&emsp;&emsp;<a href="#61">6.3. binlog</a>  
&emsp;&emsp;&emsp;<a href="#62">6.3.1. binlog写入机制</a>  
&emsp;&emsp;&emsp;<a href="#63">6.3.2. binlog 格式</a>  
&emsp;&emsp;<a href="#64">6.4. crash-safe的设置</a>  
&emsp;&emsp;<a href="#65">6.5. 两阶段提交</a>  
&emsp;&emsp;<a href="#66">6.6. 两种日志区别</a>  
&emsp;&emsp;<a href="#67">6.7. 两阶段提交的组提交机制执行流程</a>  
&emsp;<a href="#68">7. 实际sql的执行</a>  
&emsp;&emsp;<a href="#69">7.1. count(*)实现</a>  
&emsp;&emsp;<a href="#70">7.2. order by 处理流程</a>  
&emsp;&emsp;&emsp;<a href="#71">7.2.1. 全字段排序</a>  
&emsp;&emsp;&emsp;<a href="#72">7.2.2. rowId排序</a>  
&emsp;&emsp;<a href="#73">7.3. join的执行过程</a>  
&emsp;&emsp;&emsp;<a href="#74">7.3.1. Index Nested-Loop Join</a>  
&emsp;&emsp;&emsp;<a href="#75">7.3.2. Simple Nested-Loop Join</a>  
&emsp;&emsp;&emsp;<a href="#76">7.3.3. Block Nested-Loop Join</a>  
&emsp;&emsp;&emsp;<a href="#77">7.3.4. join语句mysql的优化</a>  
&emsp;&emsp;&emsp;<a href="#78">7.3.5. 总结</a>  
&emsp;&emsp;<a href="#79">7.4. union执行流程</a>  
&emsp;&emsp;<a href="#80">7.5. group by 执行流程</a>  
&emsp;&emsp;&emsp;<a href="#81">7.5.1. 优化-索引</a>  
&emsp;&emsp;&emsp;<a href="#82">7.5.2. 优化-直接排序</a>  
&emsp;&emsp;&emsp;<a href="#83">7.5.3. 小结</a>  
&emsp;<a href="#84">8. MySQL中的组件</a>  
&emsp;&emsp;<a href="#85">8.1. Buffer Pool</a>  
&emsp;&emsp;&emsp;<a href="#86">8.1.1. 内存管理策略</a>  
&emsp;&emsp;&emsp;<a href="#87">8.1.2. change buffer</a>  
&emsp;&emsp;&emsp;<a href="#88">8.1.3. change buffer 和 redo log</a>  
&emsp;&emsp;<a href="#89">8.2. sort buffer</a>  
&emsp;&emsp;<a href="#90">8.3. 内存临时表</a>  
&emsp;&emsp;<a href="#91">8.4. 磁盘临时表</a>  
&emsp;&emsp;<a href="#92">8.5. binlog cache</a>  
&emsp;&emsp;<a href="#93">8.6. redo log buffer</a>  
&emsp;&emsp;<a href="#94">8.7. net_buffer</a>  
&emsp;&emsp;<a href="#95">8.8. join buffer</a>  
&emsp;<a href="#96">9. 数据库设计</a>  
&emsp;&emsp;<a href="#97">9.1. 数据库设计原则</a>  
&emsp;&emsp;&emsp;<a href="#98">9.1.1. 第一范式(列不可再分)</a>  
&emsp;&emsp;&emsp;<a href="#99">9.1.2. 第二范式(确保表中的每列都和主键相关)</a>  
&emsp;&emsp;&emsp;<a href="#100">9.1.3. 第三范式(确保每列都和主键列直接相关,而不是间接相关)</a>  
&emsp;&emsp;&emsp;<a href="#101">9.1.4. 相关资料</a>  
&emsp;&emsp;<a href="#102">9.2. 表字段设计</a>  
&emsp;&emsp;&emsp;<a href="#103">9.2.1. 乐观锁字段</a>  
&emsp;&emsp;&emsp;<a href="#104">9.2.2. 通用字段</a>  
&emsp;&emsp;&emsp;<a href="#105">9.2.3. 日期字段的选择</a>  
&emsp;&emsp;<a href="#106">9.3. 实际设计问题 —— 设计部门表 </a>  
&emsp;<a href="#107">10. MySQL架构</a>  
&emsp;&emsp;<a href="#108">10.1. 主从模式(读写分离)</a>  
&emsp;&emsp;<a href="#109">10.2. 分库分表</a>  
&emsp;&emsp;&emsp;<a href="#110">10.2.1. 分库分表场景</a>  
&emsp;&emsp;&emsp;<a href="#111">10.2.2. 垂直切分</a>  
&emsp;&emsp;&emsp;<a href="#112">10.2.3. 水平切分</a>  
&emsp;&emsp;&emsp;<a href="#113">10.2.4. 分库分表的查询</a>  
&emsp;&emsp;<a href="#114">10.3. MGR(MySQL Group Replication)</a>  
&emsp;&emsp;<a href="#115">10.4. 主从数据同步</a>  
&emsp;&emsp;&emsp;<a href="#116">10.4.1. MySQL异步复制</a>  
&emsp;&emsp;&emsp;<a href="#117">10.4.2. MySQL半同步复制</a>  
&emsp;&emsp;&emsp;<a href="#118">10.4.3. MySQL组复制</a>  
&emsp;<a href="#119">11. 其他面试题</a>  
&emsp;&emsp;<a href="#120">11.1. 主键索引是否使用自增</a>  
&emsp;&emsp;<a href="#121">11.2. 临时表与内存表</a>  
&emsp;&emsp;<a href="#122">11.3. 重建索引是否合理</a>  
&emsp;&emsp;<a href="#123">11.4. mysql数据库抖动</a>  
&emsp;&emsp;<a href="#124">11.5. 读已提交和可重复读是如何实现的</a>  
&emsp;&emsp;<a href="#125">11.6. 读已提交和可重复读区别？</a>  
&emsp;&emsp;<a href="#126">11.7. 数据库数据库一致性是如何实现的？</a>  
&emsp;&emsp;<a href="#127">11.8. redoLog、undoLog、binlog区别？</a>  
&emsp;&emsp;&emsp;<a href="#128">11.8.1. 两种日志有以下三点不同</a>  
&emsp;&emsp;<a href="#129">11.9. 一个事务开启什么时候产生三种日志？</a>  
&emsp;&emsp;<a href="#130">11.10. 可重复读隔离级别下，事务中select一条记录巨慢</a>  
&emsp;&emsp;<a href="#131">11.11. 数据库什么情况会出现死锁？如何处理死锁？</a>  
&emsp;&emsp;&emsp;<a href="#132">11.11.1. 由于索引导致的死锁</a>  
&emsp;&emsp;<a href="#133">11.12. 如何优化SQL</a>  
&emsp;&emsp;&emsp;<a href="#134">11.12.1. order by 优化</a>  
&emsp;&emsp;&emsp;<a href="#135">11.12.2. sub-query 子查询</a>  
&emsp;&emsp;&emsp;<a href="#136">11.12.3. limit 优化 - 延迟关联</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#137">11.12.3.1. 相关资料</a>  
&emsp;&emsp;&emsp;<a href="#138">11.12.4. Or/And Condition</a>  
&emsp;&emsp;&emsp;<a href="#139">11.12.5. join 优化</a>  
&emsp;&emsp;<a href="#140">11.13. 数据库连接池、数据库连接线程安全的吗？</a>  
&emsp;&emsp;<a href="#141">11.14. InnoDB 和 MyIsam 数据库引擎的区别</a>  
&emsp;&emsp;<a href="#142">11.15. MySQL数据页(储存页)</a>  
&emsp;&emsp;<a href="#143">11.16. 高并发数据库读写压力大怎么处理？</a>  
&emsp;&emsp;<a href="#144">11.17. 其他</a>  
&emsp;&emsp;&emsp;<a href="#145">11.17.1. 什么是覆盖索引跟回表？</a>  
&emsp;&emsp;&emsp;<a href="#146">11.17.2. `left join,right join,inner join`,表表关联什么区别？</a>  
&emsp;&emsp;&emsp;<a href="#147">11.17.3. 大事务会有什么影响？</a>  
# <a name="0">MySQL</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">MySQL基本架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/mysqlprocess.jpg)

MySQL可以分为Server层和存储引擎层两部分
- Server层包括连接器、查询缓存、分析器、优化器、执行器等，涵盖MySQL的大多数核心服务功能，以及所有的内置函数（如日期、时间、数学和加密函数等），所有跨存储引擎的功能都在这一层实现，比如存储过程、触发器、视图等。
- 存储引擎层负责数据的存储和提取。其架构模式是插件式的，支持InnoDB、MyISAM、Memory等多个存储引擎。现在最常用的存储引擎是InnoDB，它从MySQL5.5.5版本开始成为了默认存储引擎

MySQL的一个设计思想：如果内存够用，就要多利用内存，尽量减少磁盘访问。
### <a name="2">Server层基本架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 连接器：登陆数据库的连接验证，完成经典的TCP握手后，连接器就要开始认证你的身份。 
```mysql -h$ip -P$port -u$user -p```
2. 查询缓存：连接建立完成之后，会到查询缓存中查询数据。查询缓存的存储默认key为查询语句，而value为结果。查询缓存弊大于利，因为只要有表更新，这个表相关的缓存就会被清除。
3. 分析器：主要做语法解析，并判断语法是否合规。
4. 优化器：对语法的执行流程进行优化，决定使用哪个索引。\
```mysql> select * from t1 join t2 using(ID) where t1.c=10 and t2.d=20;```
> 既可以先从表t1里面取出c=10的记录的ID值，再根据ID值关联到表t2，再判断t2里面d的值是否等于20。也可以先从表t2里面取出d=20的记录的ID值，再根据ID值关联到t1，再判断t1里面c的值是否等于10。\
     这两种执行方法的逻辑结果是一样的，但是执行的效率会有不同，而优化器的作用就是决定选择效率高的方案。
5. 执行器: 负责具体语句的执行，首先判断是否有权限。```mysql> select * from T where ID=10;```
> 比如我们这个例子中的表T中，ID字段没有索引，那么执行器的执行流程是这样的：
> 1. 调用InnoDB引擎接口取这个表的第一行，判断ID值是不是10，如果不是则跳过，如果是则将这行存在结果集中；
> 2. 调用引擎接口取“下一行”，重复相同的判断逻辑，直到取到这个表的最后一行。
> 3. 执行器将上述遍历过程中所有满足条件的行组成的记录集作为结果集返回给客户端。

### <a name="3">引擎</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
关于 MyISAM 和 InnoDB 的选择问题
大多数时候我们使用的都是 InnoDB 存储引擎，在某些读密集的情况下，使用 MyISAM 也是合适的。不过，前提是你的项目不介意 MyISAM 不支持事务、崩溃恢复等缺点（可是~我们一般都会介意啊！）。

> 《MySQL 高性能》上面有一句话这样写到:
不要轻易相信“MyISAM 比 InnoDB 快”之类的经验之谈，这个结论往往不是绝对的。在很多我们已知场景中，InnoDB 的速度都可以让 MyISAM 望尘莫及，尤其是用到了聚簇索引，或者需要访问的数据都可以放入内存的应用。

一般情况下InnoDB都是没有问题的，但是某些情况下你并不在乎可扩展能力和并发能力，也不需要事务支持，也不在乎崩溃后的安全恢复问题的话，选择 MyISAM 也是一个不错的选择。但是一般情况下，我们都是需要考虑到这些问题的。\
因此，对于咱们日常开发的业务系统来说，你几乎找不到什么理由再使用 MyISAM 作为自己的 MySQL 数据库的存储引擎。


## <a name="4">索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
索引的出现其实就是为了提高数据查询的效率，就像书的目录一样。 一本500页的书，对于数据库的表而言，索引其实就是它的“目录”。
> 若一张表中无主键索引，mysql会默认创建一个长度为6字节的rowid主键。

### <a name="5">主键索引、唯一索引、普通索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
索引分类：唯一索引，主键（聚集）索引，非聚集索引(普通索引)

#### <a name="6">主键索引与普通索引区别</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 一个表中只能有一个主键索引，但是可以有多个普通索引
2. 主键(聚集)索引存储记录是物理上连续存在，而非聚集索引是逻辑上的连续，物理存储并不连续
3. 查询区别：主要在于若执行的查询中需要较多的信息，普通索引会执行回表操作。
   - 如果语句是select * from T where ID=500，即主键查询方式，则只需要搜索ID这棵B+树。
   - 如果语句是select * from T where k=5，即普通索引查询方式，则需要先搜索k索引树，得到ID的值为500，再到ID索引树搜索一次。 这个过程称为回表。

回到主键索引树搜索的过程，我们称为**回表**。


图解索引结构
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/indexquery.jpg)
主键长度越小，普通索引的叶子节点就越小，普通索引占用的空间也就越小。所以，从**性能和存储空间**方面考量，自增主键往往是更合理的选择。



#### <a name="7">唯一索引与普通索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 两者查询性能差不多。
2. 主要区别在于，更新的记录目标页不在内存中时。普通索引更新会使用change Buffer。唯一索引，由于需要校验数据的唯一性，因此每次更新操作都需要读磁盘把数据载进内存，涉及IO操作。
> 在不影响数据一致性的前提下，InnoDB会将这些更新操作缓存在change buffer中，这样就不需要从磁盘中读入这个数据页了。 在下次查询需要访问这个数据页的时候，将数据页读入内存，然后执行change buffer中与这个页有关的操作。 通过这种方式就能保证这个数据逻辑的正确性

> change buffer中的操作应用到原数据页，得到最新结果的过程称为merge。 
> 1. 访问这个数据页会触发merge。
> 2. 系统有后台线程会定期merge。 
> 3. 在数据库正常关闭（shutdown） 的过程中，也会执行merge操作。

change Buffer与redo log 区别
- change Buffer主要用于减少读磁盘的次数，在必要读磁盘时再更新数据。
- redo log 则是减少内存更新后，写磁盘的次数。

merge的执行流程是这样的： 
1. 从磁盘读入数据页到内存（老版本的数据页）； 
2. 从change buffer里找出这个数据页的change buffer 记录(可能有多个），依次应用，得到新版数据页； 
3. 写redo log。这个redo log包含了数据的变更和change buffer的变更。

这时候，数据页和内存中change buffer对应的磁盘位置都还没有修改，属于脏页，之后各自刷回自己的物理数据。
### <a name="8">索引数据结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
InnoDB里面索引对应一棵B+树

#### <a name="9">B树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
B树和平衡二叉树稍有不同的是，B树属于多叉树又名平衡多路查找树

规律：
1. 排序方式：所有节点关键字是按递增次序排列，并遵循左小右大原则；
2. 子节点数：非叶节点的子节点数>1，且<=M ，且M>=2，空树除外（注：M阶代表一个树节点最多有多少个查找路径，M=M路,当M=2则是2叉树,M=3则是3叉）；
3. 所有叶子节点均在同一层、叶子节点除了包含了关键字和关键字记录的指针外也有指向其子节点的指针只不过其指针地址都为null对应下图最后一层节点的空格子;
4. 关键字数：枝节点的关键字数量大于等于ceil(m/2)-1个且小于等于M-1个（注：ceil()是个朝正无穷方向取整的函数 如ceil(1.1)结果为2);
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/Btree.jpg)

- 参考资料：[平衡二叉树、B树、B+树、B*树 理解其中一种你就都明白了](https://zhuanlan.zhihu.com/p/27700617)

#### <a name="10">B+树</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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


#### <a name="11">为什么使用B+树数据结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用B+树而不是二叉搜索树或者红黑树的原因是，由于存储介质的特性，磁盘本身存取就比主存慢很多，每次搜索的磁盘IO的开销过大，而B+树可以使用较少次的磁盘IO搜索到对象。
- B-Tree中一次检索最多需要h-1次I/O（根节点常驻内存），渐进复杂度为O(h)=O(logdN)。
- 红黑树这种结构，h明显要深的多。效率明显比B-Tree差很多。

### <a name="12">增删操作与索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
对于删除操作，InnoDB引擎只会将记录标记为删除，新数据的插入如果索引的位置刚好在删除记录，那么插入记录就会复用该位置。
> 如果我们用delete命令把整个表的数据删除呢？结果就是，所有的数据页都会被标记 为可复用。但是磁盘上，文件不会变小。

对于插入操作，如果数据页刚好进行了页分裂，那么分裂完的数据页的中都是会存在空洞的。

**解决空洞问题**，对表进行重建。
### <a name="13">索引规则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="14">覆盖索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
InnoDB存储引擎支持覆盖索引，即从辅助索引中就可以得到查询的记录，而不需要查询聚集索引中的记录。
- 如： ``` select id, b from t where b = xxx   (id为主键，b为索引)```
由于覆盖索引可以减少树的搜索次数（减少IO），显著提升查询性能，所以使用覆盖索引是一个常用的性能优化手段。

#### <a name="15">最左前缀原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
B+树这种索引结构，可以利用索引的“最左前缀”，来定位记录。  

#### <a name="16">索引下推</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
``` 
mysql> select * from tuser where name like '张%' and age=10 and ismale=1; 
```
mysql 5.6 后引入索引下推。
- 索引遍历过程中，对**索引中包含的字段先做判断**，直接过滤掉不满足条件的记录，减少回表次数
- 旧版本中会进行回表操作，取得相关信息再做判断。

### <a name="17">索引选择规则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
优化器选择索引的目的是找一个最优方案，用最小的代价执行语句。其中扫描行数、是否使用临时表、是否排序等因素都会影响优化器对索引的选择判断。


#### <a name="18">导致索引失效情况</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. where语句中包含or时，可能会导致索引失效。
> 若or的条件中包含非索引，就会只用全表扫描的。如果or的条件两边都是索引，那么会使用index_merge的优化技术。
2. where语句中索引列使用了负向查询，可能会导致索引失效
> 负向查询包括：NOT、!=、<>、!<、!>、NOT IN、NOT LIKE等。
3. 对索引列进行运算，一定会导致索引失效
> EXPLAIN SELECT * from container_load where  materiel_id+1 = 152899293852729344
4. 在索引列上使用内置函数，一定会导致索引失效\
`EXPLAIN SELECT *, SUBSTR(materiel_id,10) from container_load where SUBSTR(materiel_id,10) = 852729344`
5. like通配符可能会导致索引失效，未满足最左匹配原则。
6. 隐式类型转换导致的索引失效，如索引列user_id为varchar类型，使用int做条件关联。或者关联表字符集编码不一致。
7. 索引字段可以为null，使用is null或is not null时，可能会导致索引失效
> 默认为Null的列，存在Null值会导致mysql优化器处理起来比较复杂,当命中结果数量小于40%的时候,会走索引。
8. 联合索引未满足最左匹配原则
> (k1,k2,k3)，相当于创建了(k1)、(k1,k2)和(k1,k2,k3)三个索引
```
select * from t where k2=2;
select * from t where k3=3;
slect * from t where k2=2 and k3=3;
// 以下这条只会部分走索引
slect * from t where k1=1 and k3=3;
 ```

#### <a name="19">采样统计储存导致走错索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
扫描行数判断，mysql使用采样统计的方式来获取索引的统计信息基数。**采样统计**的方式可以减少磁盘的IO次数
>  InnoDB默认会选择N个数据页，统计这些页面上的不同值，得到一个平均值，然后乘以这个索引的页面数，就得到了这个索引的基数。\
> `analyze table t`命令，可以用来重新统计索引信息。
 
解决索引选择异常方案
- 使用force index
- 引导Mysql使用我们期望的索引，如order by b,a limit 1 与 order by b limit 1 逻辑上能保持一致，那么就能这么修改
- 在有些场景下，我们可以新建一个更合适的索引，来提供给优化器做选择，或删掉误用的索引。（较少用）


在MySQL中，有两种存储索引统计的方式，可以通过设置参数`innodb_stats_persistent`的值来选择：
- 设置为on的时候，表示统计信息会持久化存储。这时，默认的N是20，M是10。
- 设置为off的时候，表示统计信息只存储在内存中。这时，默认的N是8，M是16。 
> 由于是采样统计，所以不管N是20还是8，这个基数都是很容易不准的。

### <a name="20">添加索引技巧</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="21">前缀索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：定义字符串的一部分作为索引。
- 前缀索引可以减少索引占用的空间，但是会导致**覆盖索引的优化失效**，需要额外增加扫描次数。
> 使用前缀索引，定义好长度，就可以做到既节省空间，又不用额外增加太多的查询成本。如使用邮箱的前六位作为索引，索引的区分度好。
```
mysql> alter table SUser add index index2(email(6));
```
- 使用前缀索引很可能会损失区分度，所以你需要预先设定一个可以接受的损失比例，比如5%。 然后，在返回的L4~L7中，找出不小于 L * 95%的值，
> 使用前缀索引就用不上覆盖索引对查询性能的优化了，对于已经找到类似字段的记录，都需要回表进行扫描。这也是你在选择是否使用前缀索引时需要考虑的一个因素。

#### <a name="22">倒序存储</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
倒序存储：
1. 比如存储身份证时都倒序存储，而身份证的后六位区分度好，便于建立前缀索引.
2. like想要根据字段头部信息匹配的情况，可以存储一个正序，再存储一个逆序的字段
缺点：无法查询区间。
```shell
mysql> select field_list from t where id_card = reverse('input_id_card_string');
```

#### <a name="23">hash字段存储</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：使用Hash字段。每次插入新记录的时候，都同时用crc32()这个函数得到校验码填到一个新字段.
```
mysql> alter table t add id_card_crc int unsigned, add index(id_card_crc);
```
#### <a name="24">上述添加索引方法比较</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 直接创建完整索引，这样可能比较占用空间；
2. 创建前缀索引，节省空间，但会增加查询扫描次数，并且不能使用覆盖索引；
3. 倒序存储，再创建前缀索引，用于绕过字符串本身前缀的区分度不够的问题；
4. 创建hash字段索引，查询性能稳定，有额外的存储和计算消耗，跟第三种方式一样，都不支
持范围扫描

#### <a name="25">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 区分度低的字段不适合建索引：如性别、状态
- 联合索引字段个数不宜过度，充分权衡插入删除操作及DBA操作表的成本
- 索引组合引用、少用单列索引：单列建索引在使用中效果可能跟组合索引效果差不多，但是索引数量变多影响数据库操作
- where,on,group by,order by 后面跟着的字段建索引：这些关键字后面关联的字段常常使用，考虑建立索引


## <a name="26">MySQL的锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
根据加锁的范围，MySQL里面的锁大致可以分成全局锁、 表级锁和行锁三类。
### <a name="27">全局锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
全局锁就是对整个数据库实例加锁。MySQL提供了一个加全局读锁的方法，命令是Flush tables with read lock (FTWRL)。 
- 全局锁的典型使用场景是，做全库逻辑备份。 
- 官方自带的逻辑备份工具是mysqldump。 当mysqldump使用参数–single-transaction的时候，导数据之前就会启动一个事务，来确保拿到一致性视图。 而由于MVCC的支持，这个过程中数据是可以正常更新的。
   
### <a name="28">表锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MySQL里面表级别的锁有两种： 一种是表锁，一种是元数据锁（meta data lock，MDL)。

表锁的语法是 lock tables …read/write。 
- 举个例子, 如果在某个线程A中执行lock tables t1 read, t2 write; 这个语句，则其他线程写t1、 读写t2的语句都会被阻塞。
- InnoDB这种支持行锁的引擎，一般不使用lock tables命令来控制并发，毕竟锁住整个表的影响面还是太大。
  
另一类表级的锁是MDL（metadata lock)。 
- 当对一个表做增删改查操作的时候，加MDL读锁； 
- 当要对表做结构变更操作的时候，加MDL写锁。

**如何安全地给小表加字段？**
- 在alter table语句里面设定等待时间，如果在这个指定的等待时间里面能够拿到MDL写锁最好，拿不到也不要阻塞后面的业务语句，先放弃。 之后开发人员或者DBA再通过重试命令重复这个过程。
  
### <a name="29">行锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MySQL的行锁是在引擎层由各个引擎自己实现的。 MyISAM引擎就不支持行锁，InnoDB是支持行锁的。

**InnoDB的行锁是针对索引加的锁**，不是针对记录加的锁，并且该索引不能失效，否则都会从行锁升级为表锁

添加行锁：
1. 开启事务，事务中执行更新。
2. 使用for update 数据库中的行上加一个排它锁
> select * from t lock in share mode;  // lock in share mode是加共享锁的形式。


#### <a name="30">两阶段锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
两阶段锁协议：在InnoDB事务中，行锁是在需要的时候才加上的，但并不是不需要了就立刻释放，而是要等到事务结束时才释放。 
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/linelock.jpg)
- 事务B的update语句会被阻塞，直到事务A执行commit之后，事务B才能继续执行
- 如果你的事务中需要锁多个行，要把最可能造成锁冲突、 最可能影响并发度的锁尽量往后放。

### <a name="31">死锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/deadlock.jpg)
事务A在等待事务B释放id=2的行锁，而事务B在等待事务A释放id=1的行锁。 事务A和事务B在互相等待对方的资源释放，就是进入了死锁状态。 

#### <a name="32">死锁策略</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
策略一：直接进入等待，直到超时。这个超时时间可以通过参数innodb_lock_wait_timeout来设置。在InnoDB中，innodb_lock_wait_timeout的默认值是50s

策略二：发起死锁检测，发现死锁后，主动回滚死锁链条中的某一个事务，让其他事务得以继续执行。 将参数innodb_deadlock_detect设置为on，表示开启这个逻辑。
- 弊端：判断是否存在死锁的成本会随着数据量的增长，而大量消耗CPU。假设有1000个并发线程要同时更新同一行，那么死锁检测操作就是100万这个量级的。
- 解决方案：1.确定不会出现死锁，关闭死锁检测。2.控制并发度。3.改写mysql源码。
    
### <a name="33">幻读(间隙锁)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
对“幻读”做一个说明：
1. 在可重复读隔离级别下，普通的查询是快照读，是不会看到别的事务插入的数据的。因此，幻读在“当前读”下才会出现。
2. session B的修改结果，被session A之后的select语句用“当前读”看到，不能称为幻读。 **幻读仅专指“新插入的行**”。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/phantomRead.jpg)

session A里执行了三次查询，分别是Q1、 Q2和Q3。 它们的SQL语句相同，都是select * from t where d=5 for update。 表示查所有d=5的行，而且使用的是当前读，并且加上写锁。 \
其中，Q3读到id=1这一行的现象，被称为“幻读”。 也就是说，幻读指的是一个事务在前后两次查询同一个范围的时候，后一次查询看到了前一次查询没有看到的行

幻读会导致数据一致性的问题。 锁的设计是为了保证数据的一致性。 而这个一致性，不止是数据库内部数据状态在此刻的一致性，还包含了数据和日志在逻辑上的一致性。
1. 在可重复读隔离级别下，普通的查询是快照读，是不会看到别的事务插入的数据的。 因此，幻读在“当前读”下才会出现。
2. 上面session B的修改结果，被session A之后的select语句用“当前读”看到，不能称为幻读。幻读仅专指“新插入的行”

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/phantomRead2.jpg)

> 尝试解决幻读，把所有语句都上锁，查询语句改成`select * from t for update`。但是仍然无法解决插入新语句出现的幻读现象。

#### <a name="34">如何解决幻读？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
InnoDB引入新的锁，也就是间隙锁(Gap Lock)。在一行行扫描的过程中，不仅将给行加上了行锁，还给行两边的空隙，也加上了间隙锁。

间隙锁之间的冲突：跟间隙锁存在冲突关系的，是“往这个间隙中插入一个记录”这个操作。 间隙锁之间都不存在冲突关系。

间隙锁和行锁合称next-key lock，每个next-key lock是前开后闭区间。 
- 如果用`select * from t for update`要把整个表所有记录锁起来，就形成了7个next-key lock，分别是 (-∞,0]、 (0,5]、 (5,10]、 (10,15]、 (15,20]、 (20, 25]、 (25, +supremum]。
- InnoDB给每个索引加了一个不存在的最大值supremum。

间隙锁的引入，可能会导致同样的语句锁住更大的范围，这其实是影响了并发度的。

#### <a name="35">加锁原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
总结的加锁规则里面，包含了两个“原则”、 两个“优化”和一个“bug”。
1. 原则1： 加锁的基本单位是next-keylock。 希望你还记得，next-keylock是前开后闭区间。
2. 原则2： 查找过程中访问到的对象才会加锁。
3. 优化1： 索引上的等值查询，给唯一索引加锁的时候，next-keylock退化为行锁。
4. 优化2： 索引上的等值查询，向右遍历时且最后一个值不满足等值条件的时候，next-key lock退化为间隙锁。
5. 一个bug： 唯一索引上的范围查询会访问到不满足条件的第一个值为止。

### <a name="36">悲观锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 设置自动提交为set autocommit=0。使用事务begin;/begin work;/start transaction; (三者选一就可以)
2. 使用for update，同样使用事务。

### <a name="37">乐观锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
添加一行字段当版本号字段，更新的时候带版本号。如：
`update xx set name = aaa and version = 3 where id = 'xxx' and version = 2'`

## <a name="38">事务</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
事务ACID：
- A（Atomicity): 原子性。原子性是指事务是一个不可分割的工作单位，事务中操作要么都发生，要么都不发生。
- C（Consistency): 一致性。数据库总是从一个一致性的状态转移到另一个一致性的状态。**事务执行结束后，数据库的完整性约束没有被破坏，事务执行的前后顺序都是合法数据状态。** 例如转账业务中，无论事务是否成功，转账者和收款人的总额应该是不变的。
- I（Isolation): 隔离性。事务的隔离性是多个用户并发访问数据库时，数据库为每一个用户开启的事务，不能被其他事务所干扰，多个并发事务之间要相互隔离。保证事务执行尽可能不受其他事务影响；InnoDB默认的隔离级别是RR，RR的实现主要基于锁机制、数据的隐藏列、undo log和类next-key lock机制
- D（Durability): 持久性。持久性是指一个事务一旦被提交，它对数据库中的**数据改变是永久性**的，接下来即使数据库发生故障也不应该对其有任何影响。

MySQL中有undo log(用于回滚)和redo log(用于数据写入)
- 事务的原子性是通过undo log来实现的。undo log记录的都是执行数据变更操作的逆向操作,在进行回滚的操作时候，可以从undo log文件中读取出来,然后执行，就可以将数据恢复回来。
- 事务的隔离性是通过(读写锁+MVCC)来实现的。
- 事务的持久性性是通过redo log来实现的。事务提交写log，更新到内存。通过日志保证了类似与持久到磁盘一样的效果。
- 事务的一致性，mysql中事务的原子提交，并持久化到内存，事务执行相互隔离，共同保障了事务的一致性。


## <a name="39">锁与隔离级别</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 写锁（Write Lock，也叫作排他锁，eXclusive Lock，简写为 X-Lock）：如果数据有加写锁，就只有持有写锁的事务才能对数据进行写入操作，数据加持着写锁时，其他事务不能写入数据，也不能施加读锁。
- 读锁（Read Lock，也叫作共享锁，Shared Lock，简写为 S-Lock）：多个事务可以对同一个数据添加多个读锁，数据被加上读锁后就不能再被加上写锁，所以其他事务不能对该数据进行写入，但仍然可以读取。对于持有读锁的事务，如果该数据只有它自己一个事务加了读锁，允许直接将其升级为写锁，然后写入数据。
- 范围锁（Range Lock）：对于某个范围直接加排他锁，在这个范围内的数据不能被写入。如下语句是典型的加范围锁的例子：

⚠️脏读、不可重复读、幻读本质上是由于**各种锁在不同加锁时间上⚠️组合应用所产生的结果**。以下的介绍将从锁的角度，而不是数据的现象说明四个隔离级别及相关问题。
### <a name="40">串行化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
串行化访问提供了强度最高的隔离性，如果不考虑性能优化的话，对事务所有读、写的数据全都加上读锁、写锁和范围锁即可做到可串行化

但数据库不考虑性能肯定是不行的，并发控制理论（Concurrency Control）决定了隔离程度与并发能力是相互抵触的，隔离程度越高，并发访问时的吞吐量就越低。
> 因为添加了范围锁，也就不会出现幻读问题了。

### <a name="41">RR可重复读（Repeatable Read）TODO</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
见幻读章节

有三种情况： 
1. 版本未提交，不可见； 
2. 版本已提交，但是是在视图创建后提交的，不可见； 
3. 版本已提交，而且是在视图创建前提交的，可见。

> 可重复读对**事务所涉及的数据加读锁和写锁，且一直持有至事务结束**，但不再加范围锁。

#### <a name="42">不可重复读问题解决</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
可重复读实现，添加一个贯穿事务周期的读锁：
1. 事务A查询一个范围的数据，对这个范围的数据加读锁且读取后不会马上释放
2. 事务B对于范围内的数据无法施加写锁，修改存在行数据。
3. 事务A重复查询该范围内的数据，原存在行数据一致。但是范围内有新数据插入会导致幻读。

#### <a name="43">幻读解决（间隙锁+行锁）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Innodb 为解决幻读问题引入了间隙锁+行锁充当范围锁

见上述幻读章节
### <a name="44">RC读已提交（Read Committed）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

> 读已提交对事务涉及的数据加的写锁会一直持续到事务结束，但**加的读锁在查询操作完成后就马上会释放**。

隔离级别是读已提交，两次重复执行的查询结果就会不一样，原因是读已提交的隔离级别缺乏贯穿整个事务周期的读锁，无法禁止读取过的数据发生变化。
> 读已提交对事务涉及的数据加的写锁会一直持续到事务结束，但**加的读锁在查询操作完成后就马上会释放**。读已提交比可重复读弱化的地方在于不可重复读问题

#### <a name="45">脏读解决</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
例子：
1. 事务A对于事务内修改的数据T1添加写锁。
2. 事务B想读取数据T1会先加读锁，而数据T1已经被加了写锁，想加读锁就得等待事务A处理结束。

事务B处理数据T1必须等事务A结束，解决了读未提交破坏了事务隔离性问题，实现了读取的数据都是已提交的数据，不会被回滚，即读已提交。

#### <a name="46">不可重复读问题出现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
例子：
1. 事务A对于事务内修改的数据T1添加写锁，查询数据T2时候使用读锁进行查询，查询完成释放读锁。
2. 事务A事务处理期间，事务B对数据T2进行了修改，因为数据T2现在未添加任何锁，可以被加写锁进行修改。
3. 事务A重新查询数据T2，发现数据已经被改变，事务处理期间，数据不可被重复查询。


### <a name="47">RU读未提交（Read Uncommitted）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 读未提交对事务涉及的数据只加写锁，会一直持续到事务结束，但**完全不加读锁**。\
> 注意⚠️：写锁禁止其他事务施加读锁，而不是禁止事务读取数据，如果事务 T1 读取数据并不需要去加读锁的话，就会导致事务 T2 未提交的数据也马上就能被事务 T1 所读到。

#### <a name="48">脏读问题出现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

例子：
1. 事务A修改数据T1添加了写锁。
2. 事务B不使用读锁读取数据T1。
3. 事务A回滚数据。
4. 事务B基于第一次查询得到的数据T1值进行逻辑操作，而此时事务B查询到的数据为脏数据。



### <a name="49">多版本并发控制（Multi-Version Concurrency Control，MVCC）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
多版本并发控制MVCC: 是一种无锁读取的优化策略，它的“无锁”是特指读取时不需要加锁。MVCC 的基本思路是对数据库的任何修改都不会直接覆盖之前的数据，而是产生一个新版副本与老**版本**共存，以此达到读取时可以完全不加锁的目的。


#### <a name="50">前置知识</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

数据库并发场景
- 读-读：不存在 任何问题，也不需要并发控制
- 读-写：有线程安全问题，可能会造成事务隔离性问题，可能遇到**脏读，幻读，不可重复读**
- 写-写：有线程安全问题，可能会存在更新丢失问题，比如第一类更新丢失，第二类更新丢失

当前读 ：它读取的数据库记录，都是当前最新的版本，会对当前读取的数据进行**加锁**，防止其他事务修改数据。是悲观锁的一种操作。
如下操作都是当前读：
- select lock in share mode (共享锁)
- select for update (排他锁)
- update (排他锁)
- insert (排他锁)
- delete (排他锁)
- 串行化事务隔离级别

快照读 ：快照读的实现是基于多版本并发控制，即MVCC，既然是多版本，那么快照读读到的数据不一定是当前最新的数据，有可能是之前历史版本的数据。

#### <a name="51">MVCC原理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MVCC用来解决**读—写冲突**的无锁并发控制，就是为事务分配**单向增长的时间戳**。为每个数据修改保存一个版本，版本与事务时间戳相关联。
> 读操作只读取该事务开始前的数据库快照。

MVCC的实现原理：主要是版本链，undo日志 ，Read View 来实现的

实现机制：InnoDB在每行数据都增加三个隐藏字段，一个唯一行号，一个记录创建的版本号，一个记录删除的版本号。
- 创建版本号：insert操作时事务的id。
- 删除版本号：insert时为null，删除时为当前事务的id。
  当读操作时，读取的是**删除版本号为null**，或者**创建版本号最大的数据**，保证我们读取的是最新的数据
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/basic/mvcc-line.png)



##### <a name="52">版本链</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
我们数据库中的每行数据，除了我们肉眼看见的数据，还有几个隐藏字段，分别是db_trx_id、db_roll_pointer、db_row_id。
- db_trx_id ：最近修改(修改/插入)事务ID：记录创建这条记录/最后一次修改该记录的事务ID。6byte
- db_roll_pointer（版本链关键）：回滚指针，指向这条记录的上一个版本（存储于rollback segment里）。7byte
- db_row_id ：隐含的自增ID（隐藏主键），如果数据表没有主键，InnoDB会自动以db_row_id产生一个聚簇索引。 6byte
> 实际还有一个删除flag隐藏字段, 记录被更新或删除并不代表真的删除，而是删除flag变了

例子：
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/versionChain.png)
1. db_row_id是数据库默认为该行记录生成的唯一隐式主键
2. db_trx_id是当前操作该记录的事务ID
3. db_roll_pointer是一个回滚指针，用于配合undo日志，指向上一个旧版本。
每次对数据库记录进行改动，都会记录一条undo日志，每条undo日志也都有一个roll_pointer属性（INSERT操作对应的undo日志没有该属性，因为该记录并没有更早的版本）
> 对该记录每次更新后，都会将旧值放到一条undo日志中，就算是该记录的一个旧版本，随着更新次数的增多，所有的版本都会被roll_pointer属性连接成一个链表，我们把这个链表称之为版本链，版本链的头节点就是当前记录最新的值。


##### <a name="53">undo log</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
见undolog 章节

##### <a name="54">Read View(读视图)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
事务进行快照读操作的时候生产的读视图(Read View)，在该事务执行的快照读的那一刻，会生成数据库系统当前的一个快照。\
记录并维护系统当前活跃事务的ID(没有commit，当每个事务开启时，都会被分配一个ID, 这个ID是递增的，所以越新的事务，ID值越大)，是系统中当前不应该被本事务看到的其他事务id列表。


#### <a name="55">隔离级别与MVCC</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 隔离级别是可重复读：快照读总是读取undolog中小于或等于当前事务 ID 的记录，在这个前提下，如果数据仍有多个版本，则取最新（事务 ID 最大）的。
- 隔离级别是读已提交：快照读总是取最新的版本即可，即最近被 Commit 的那个版本的数据记录。
另外两个隔离级别都没有必要用到 MVCC，因为读未提交直接修改原始数据即可，其他事务查看数据的时候立刻可以看到，根本无须版本字段。可串行化本来的语义就是要阻塞其他事务的读取操作，而 MVCC 是做读取时无锁优化的，自然就不会放到一起用。


在RR隔离级别下，分析隔离级别，此时已有一个事务ID 99，其他三个事务如下，事务开启时刻，带上已生效的版本号ID

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/transactiongeli2.png)

事务A视图版本100，事务B视图版本101，事务C视图版本102
1. 更新数据都是先读后写的，而这个读，只能读当前的值，称为“当前读”（ current read） 。
2. 故事务C读到当前值为1，更新为2。事务B读到当前值被C更新为2，再更新为3。
3. 事务A查询以当前版本为例，则读到为1。
> RC隔离级别读提交事务隔离的例子，事务A读取的结果为2。
```
mysql> select k from t where id=1 lock in share mode;
mysql> select k from t where id=1 for update;
 ```

具体分析有三种情况：
1. 版本未提交，不可见；
2. 版本已提交，但是是在视图创建后提交的，不可见；
3. 版本已提交，而且是在视图创建前提交的，可见。


读提交(RC)的逻辑和可重复读(RR)的逻辑类似，它们最主要的区别是： 
1. 在可重复读隔离级别下，只需要在事务开始的时候创建一致性视图，之后事务里的其他查询 都共用这个一致性视图； 
2. 在读提交隔离级别下，每一个语句执行前都会重新算出一个新的视图。

下述场景中，读提交(RC)隔离级别下：

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/transactiongeli4.png)

事务A查询语句返回的是k=2。事务B查询结果k=3。

#### <a name="56">参考资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [全网最全的一篇数据库MVCC详解，不全我负责](https://www.php.cn/mysql-tutorials-460111.html)
- [凤凰架构-本地事务](https://icyfenix.cn/architect-perspective/general-architecture/transaction/local.html#%E5%AE%9E%E7%8E%B0%E9%9A%94%E7%A6%BB%E6%80%A7)


## <a name="57">redo log、undo log、binlog</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="58">undo log</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
undo log主要有两个作用：回滚和多版本控制(MVCC)

在数据修改的时候，不仅记录了redo log，还记录undo log，如果因为某些原因导致事务失败或回滚了，可以用undo log进行回滚。
1. 保证事务进行rollback时的原子性和一致性。undo log主要存储的也是逻辑日志，比如我们要insert一条数据了，那undo log会记录的一条对应的delete日志。我们要update一条记录时，它会记录一条对应相反的update记录。
2. **用于MVCC快照读的数据**，在MVCC多版本控制中，通过读取undo log的历史版本数据可以实现不同事务版本号都拥有自己独立的快照数据版本。


### <a name="59">redo log</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MySQL里经常说到的WAL技术，WAL的全称是WriteAheadLogging，它的关键点就是先写日志，再写磁盘，也就是先写粉板，等不忙的时候再写账本。
- 当有一条记录需要更新的时候，InnoDB引擎就会先把记录写到redo log（粉板） 里面，并更新**内存**，这个时候更新就算完成了。 同时，InnoDB引擎会在适当的时候，将这个操作记录更新到磁盘里面，而这个更新往往是在系统比较空闲的时候做，这就像打烊以后掌柜做的事。(由于磁盘连接开销大，)
- InnoDB的redo log是固定大小的，比如可以配置为一组4个文件，每个文件的大小是1GB，那么这块“粉板”总共就可以记录4GB的操作。 从头开始写，写到末尾就又回到开头循环写，如下面这个图所示

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/redologwrite.jpg)

redo log buffer ：redo log buffer就是一块内存，用来先存redo日志的。 在执行事务的时候，如insert、update会先存在buffer中。等事务commit，再一起写入redo log

#### <a name="60">redo log 写入机制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/redologwrite.png)

这三种状态分别是：
1. 存在redo log buffer中，物理上是在MySQL进程内存中，就是图中的红色部分；
2. 写到磁盘(write)，但是没有持久化（fsync)，物理上是在文件系统的page cache里面，也就是图中的黄色部分；
3. 持久化到磁盘，对应的是hard disk，也就是图中的绿色部分。
    
为了控制redo log的写入策略，InnoDB提供了innodb_flush_log_at_trx_commit参数，它有三种可能取值：
1. 设置为0的时候，表示每次事务提交时都只是把redo log留在redo log buffer中;
2. 设置为1的时候，表示每次事务提交时都将redo log直接持久化到磁盘；
3. 设置为2的时候，表示每次事务提交时都只是把redo log写到page cache。

InnoDB写盘的三种情况：
1. InnoDB有一个后台线程，每隔1秒，就会把redo log buffer中的日志，调用write写到文件系统的page cache，然后调用fsync持久化到磁盘。
2. redo log buffer占用的空间即将达到 innodb_log_buffer_size一半的时候，后台线程会主动写盘。
3. 并行的事务提交的时候，顺带将这个事务的redo log buffer持久化到磁盘。 

### <a name="61">binlog</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MySQL整体来看，其实就有两块： 一块是Server层，它主要做的是MySQL功能层面的事情； 还有一块是引擎层，负责存储相关的具体事宜。 上面我们聊到的粉板redo log是InnoDB引擎特有的日志，而Server层也有自己的日志，称为binlog（归档日志） 。

#### <a name="62">binlog写入机制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/binlogwrite.jpg)

每个线程有自己binlog cache，但是共用同一份binlog文件。
1. 图中的write，指的就是指把日志写入到文件系统的page cache，并没有把数据持久化到磁盘，所以速度比较快。
2. 图中的fsync，才是将数据持久化到磁盘的操作。 一般情况下，我们认为fsync才占磁盘的IOPS

write 和fsync的时机，是由参数sync_binlog控制的： 
1. sync_binlog=0的时候，表示每次提交事务都只write，不fsync； 
2. sync_binlog=1的时候，表示每次提交事务都会执行fsync； 
3. sync_binlog=N(N>1)的时候，表示每次提交事务都write，但累积N个事务后才fsync。


#### <a name="63">binlog 格式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
binlog 三种格式，主要区别于在存储binlog的格式区别
1. statement，记录着真实执行的sql原文，部分语句记录时会先设置上下文信息，如`insert`语句调用`now()`函数，log中会先记录一条`set timestamp`的信息。
2. row，将执行的sql映射成对应的event事件，如删除sql会记录着真实删除的ID信息，不会导致主备删除不同行的情况。
3. mixed 上面两种的混合，row格式的缺点是，很占空间。MySQL自己会判断这条SQL语句是否可能引起主备不一致，如果有可能，就用row格式，否则就用statement格式。
> 现在越来越多的场景要求把MySQL的binlog格式设置成row，有利于数据恢复。
> 1. 如执行完一条delete语句以后，发现删错数据了，可以直接把 binlog中记录的delete语句转成insert，把被错删的数据插入回去就可以恢复了。
> 2. 如果执行的是update语句的话，binlog里面会记录修改前整行的数据和修改后的整行数据。

>而使用statement，需要注意语句的上下文信息，以防恢复有误。同样的mixed模式下，部分语句转成statement也会导致恢复的过程较难操作的情况



### <a name="64">crash-safe的设置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
redo log用于保证crash-safe能力。innodb_flush_log_at_trx_commit这个参数设置成1的时候，表示每次事务的redo log都直接持久化到磁盘，建议设置成1，这样可以保证 MySQL异常重启之后数据不丢失。 \
sync_binlog这个参数设置成1的时候，表示每次事务的binlog都持久化到磁盘。这个参数也建议你设置成1，这样可以保证MySQL异常重启之后binlog不丢失。

这就是通常说的MySQL的“双1”配置，指的就是sync_binlog和innodb_flush_log_at_trx_commit都设 置成 1。也就是说，一个事务完整提交前，需要等待两次刷盘，一次是**redo log（prepare 阶段）**，一次是binlog。

### <a name="65">两阶段提交</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/twocommit.jpg)

两阶段提交：主要用于保证redo log 与binlog 的状态保持逻辑上一致。

图中 两个“commit”的概念： 
“commit语句”，是指MySQL语法中，用于提交一个事务的命令。一般跟begin/start transaction配对使用。\
图中用到的这个“commit步骤”，指的是事务提交过程中的一个小步骤，也是最后一步。 当这个步骤执行完成后，这个事务就提交完成了。
“commit语句”执行的时候，会包含“commit 步骤

**崩溃后的数据恢复阶段**:
如果在更新或写入数据的过程中，机器出现崩溃。那么在机器在重启后，MySQL会首先去验证redo log的完整性，如果redo log中没有prepare状态的记录，则记录是完整的，就日记提交。如果redolog中存在prepare记录，那么就去验证这条redolog对应的binlog记录，如果这条binlog是完整的，那么完整提交redo log，否则执行回滚逻辑

**崩溃恢复时的判断规则**:
1. 如果redo log里面的事务是完整的，也就是已经有了commit标识，则直接提交；
2. 如果redo log里面的事务只有完整的prepare，则判断对应的事务binlog是否存在并完整：
   - 如果是，则提交事务；
   - 否则，回滚事务。
- 如果碰到既有prepare、 又有commit的redo log，就直接提交；
- 如果碰到只有prepare、 而没有commit的redo log，就拿着XID去binlog找对应的事务。
> 一个事务的binlog是有完整格式的： \
> statement格式的binlog，最后会有COMMIT；\
> row格式的binlog，最后会有一个XID event。

**为何设计两阶段提交?**
> redo log 日志在innodb引擎端，而binlog在server层。涉及到两个不同服务的交互，又要保证数据一致性问题，这就是一个典型的分布式问题。\
> 2PC保证了事务在引擎层（redo）和server层（binlog）之间的原子性。其中binlog作为XA协调器，即以binlog是否成功写入磁盘作为事务提交的标志（innodb commit标志并不是事务成功与否的标志）

**为何需要两个日志**
1. 只使用binlog的话，无法保证InnoDB的崩溃恢复。历史原因的话，是InnoDB并不是MySQL的原生存储引擎。MySQL的原生引擎是 MyISAM，设计之初就有没有支持崩溃恢复。
    > binlog没有能力恢复“数据页”。InnoDB引擎使用的是WAL技术，执行事务的时候，写完内存和日志，事务就算完成了。 如果之后崩溃，要依赖于日志来恢复数据页。\
   binlog write这个位置发生崩溃的话，之前的事务1也是可能丢失了的，而且是数据页级的丢失。此时，**binlog里面并没有记录数据页的更新细节**，是补全丢失的数据。
2. 只使用redo log，可以保证crash-safe。
    > binlog作为MySQL一开始就有的功能，被用在了很多地方。其中，MySQL系统高可用的基础，就是binlog复制\
    很多公司有异构系统（比如一些数据分析系统） ，这些系统就靠消费MySQL的binlog来更新自己的数据。

参考文章：
- [MySQL 2PC & Group Commit](https://segmentfault.com/a/1190000014810628)
- [MySQL InnoDB Crash Safe](https://www.modb.pro/db/74297): 崩溃恢复流程
### <a name="66">两种日志区别</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. redo log是InnoDB引擎特有的； binlog是MySQL的Server层实现的，所有引擎都可以使用。
2. redo log是物理日志，记录的是“在某个数据页上做了什么修改”； binlog是逻辑日志，记录的是这个语句的原始逻辑，比如“给ID=2这一行的c字段加1 ”。
3. redo log是循环写的，空间固定会用完； binlog是可以追加写入的。 “追加写”是指binlog文件写到一定大小后会切换到下一个，并不会覆盖以前的日志。
4. 事务提交的时候，一次性将事务中的sql语句（一个事物可能对应多个sql语句）按照一定的格式记录到binlog中。这里与redo log很明显的差异就是redo log并不一定是在事务提交的时候刷新到磁盘，redo log是在事务开始之后就开始逐步写入磁盘。

    
### <a name="67">两阶段提交的组提交机制执行流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
crash-safe的参数设置会导致频繁的刷盘，在高TPS下容易达到磁盘能力的瓶颈。\
MySQL为了让组提交的效果更好，把redo log做fsync的时间拖到了binlog write 之后，整体的提交流程改成如下：
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/actualWrite.jpg)

WAL机制主要得益于两个方面：
1. redo log 和 binlog都是顺序写，磁盘的顺序写比随机写速度要快；
2. **组提交机制**，可以大幅度降低磁盘的`IOPS`消耗。


## <a name="68">实际sql的执行</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
一个sql 语句mysql的执行顺序：
`from -- on  -- JOIN -- where -- group by -- having -- select -- distinct -- order by -- limit`

```
 (8) SELECT (9) DISTINCT <select_list>
 (1) FROM <left_table>
 (3) <join_type> JOIN <right_table>
 (2) ON <join_condition>
 (4) WHERE <where_condition>
 (5) GROUP <group_by_list>
 (6) WITH {CUBE|ROLLUP}
 (7) HAVING <having_condition>
 (10) ORDER BY <order_by_list>
 (11) LIMIT <limit_number>
```

执行查询的语句是 `select id from T where k=5`。这个查询语句在索引树上查找的过程:\
先是通过B+树从树根开始，按层搜索到叶子节点，也就是对应的数据页，然后可以认为数据页内部通过**二分法**来定位记录。



### <a name="69">count(*)实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
不同引擎中的实现
- MyISAM引擎把一个表的总行数存在了磁盘上，因此执行count(*)的时候会直接返回这个数，效率很高；
- InnoDB引擎执行count(*)的时候，需要把数据一行一行地从引擎里面读出来，然后累积计数
> InnoDB中Mysql对于count(*)的优化：InnoDB是索引组织表，主键索引树的叶子节点是数据，而普通索引树的叶子节点是主键值。 所以，普通索引树比主键索引树小很多。普通索引树比主键索引树小很多。对于count(*)这样的操作，遍历哪个索引树得到的结果逻辑上都是一样的。因此，MySQL优化器会找到最小的那棵树来遍历。在保证逻辑正确的前提下，尽量减少扫描的数据量，是数据库系统设计的通用法则之一。

show table status 命令也可以显示行数，这里的行数是基于采样统计的，并不准确。

不同count的用法：`count(*)、 count(主键id)、 count(字段)`和`count(1)`
- `count()`是一个聚合函数，对于返回的结果集，一行行地判断，如果count函数的参数不是NULL，累计值就加1，否则不加。最后返回累计值
- `count(主键id)`: InnoDB引擎会遍历整张表，把每一行的id值都取出来，返回给server层。server层拿到id后，判断是不可能为空的，就按行累加。
- `count(1)`: InnoDB引擎遍历整张表，但不取值。server层对于返回的每一行，放一个数字“1”进去，判断是不可能为空的，按行累加。
- `count(字段)`:
    1. 如果这个“字段”是定义为not null的话，一行行地从记录里面读出这个字段，判断不能为null，按行累加；
    2. 如果这个“字段”定义允许为null，那么执行的时候，判断到有可能是null，还要把值取出来再判断一下，不是null才累加。
- `count(*)`是例外，并不会把全部字段取出来，而是专门做了优化，不取值。count(*)肯定不是null，按行累加。
> 按照效率排序的话，count(字段)<count(主键id)<count(1)≈count(*)
  
### <a name="70">order by 处理流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="71">全字段排序</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
全字段排序，按字面意思就是按字段进行排序的一种处理方式。
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
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/orderby1.jpg)
> Extra这个字段中的`Using filesort`表示的就是需要排序，MySQL会给每个线程分配一块内存用于排序，称为sort_buffer。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/orderby2.jpg)
这个语句执行流程如下所示 ：
1. 初始化`sort_buffer`，确定放入`name、city、age`这三个字段；
2. 从索引city找到第一个满足`city='杭州’`条件的主键id，也就是图中的ID_X；
3. 到主键id索引取出整行，取`name、city、age`三个字段的值，存入`sort_buffer`中；
4. 从索引city取下一个记录的主键id；
5. 重复步骤3、4直到city的值不满足查询条件为止，对应的主键id也就是图中的ID_Y；
6. 对sort_buffer中的数据按照字段name做快速排序；
7. 按照排序结果取前1000行返回给客户端
> 这里的排序使用的是归并排序


`sort_buffer_size`: 是MySQL为排序开辟的内存(sort_buffer)的大小。 如果要排序的数据量小于`sort_buffer_size`，排序就在内存中完成。但如果排序数据量太大，内存放不下，则不得不利用磁盘临时文件辅助排序。
> sort_buffer_size大于了需要排序的数据量的大小，number_of_tmp_files就是0，排序直接在内存完成。
  
**optimizer_trace** 是一个跟踪功能，跟踪执行的语句的解析优化执行的过程，比explain更详细。
  - number_of_tmp_files表示的是，排序过程中使用的临时文件数。
  - sort_mode: 表示参与排序的只有name和id这两个字段
```
/* 打开optimizer_trace，只对本线程有效 */
SET optimizer_trace='enabled=on';
```


#### <a name="72">rowId排序</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
`max_length_for_sort_data`: 是MySQL中专门控制用于排序的行数据的长度的一个参数。它的意思是，如果单行的长度超过这个值，MySQL就认为单行太大，要换一个算法。
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/orderby3.jpg)
> 与全字段排序的区别，主要体现在内存排序完毕之后要多一次查询。因为排序的字段超过了`max_length_for_sort_data`的限制。

对于InnoDB表来说，执行全字段排序会减少磁盘访问，因此会被优先选择。在“InnoDB表”中，对于内存表，回表过程只是简单地根据数据行的位置，直接访问内存得到数据，根本不会导致多访问磁盘。 
> 优化器会优先考虑的，就是用于排序的行越少越好。

内存临时表与磁盘临时表
> tmp_table_size这个配置限制了内存临时表的大小，默认值是16M。 如果临时表大小超过了tmp_table_size，那么内存临时表就会转成磁盘临时表

order by rand()使用了内存临时表，内存临时表排序的时候使用了`rowid排序`方法。
直接使用order by rand()，这个语句需要Using temporary和 Using filesort，查询的执行代价往往是比较大的

### <a name="73">join的执行过程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
以下为三种join算法，主要的区别在于连接的字段是否有索引
#### <a name="74">Index Nested-Loop Join</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/indexJoin.png)

`select * from t1 straight_join t2 on (t1.a=t2.a);`  t1与t2的a字段均有索引。
执行流程：
1. 从表t1中读入一行数据 R；
2. 从数据行R中，取出a字段到表t2里去查找；
3. 取出表t2中满足条件的行，跟R组成一行，作为结果集的一部分；
4. 重复执行步骤1到3，直到表t1的末尾循环结束
> 实际执行的时候使用了**BAK优化**，将尽可能多的驱动表数据取出放Join Buffer中，再关联查询。\
> BAK优化: 见下部分

流程中：
1. 对驱动表t1做了全表扫描，这个过程需要扫描100行；
2. 而对于每一行R，根据a字段去表t2查找，走的是树搜索过程。由于构造的数据都是一一对应的，因此每次的搜索过程都只扫描一行，也是总共扫描100行；
3. 所以，整个执行流程，总扫描行数是200。

结论：
1. 使用join语句，性能比强行拆成多个单表执行SQL语句的性能要好； 
2. 如果使用join语句的话，需要**让小表做驱动表**。 
> 注意，这个结论的前提是“可以使用被驱动表的索引”。
#### <a name="75">Simple Nested-Loop Join</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
`select * from t1 straight_join t2 on (t1.a=t2.b);` 表t2的**字段b上没有索引**，关联查询使用全表扫描。

查询过程与上述`index join`相同，从t1拿定位一行数据后，接着就去t2表根据连接字段查询，SQL请求就要扫描表t2多达100次，总共扫描100*1000=10万行。

#### <a name="76">Block Nested-Loop Join</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
执行过程：
将驱动表数据读入线程内存`join_buffer`中，同样以全表扫描，但是因为使用内存操作，速度比`Simple join`的方法快。
1. 把表t1的数据读入线程内存join_buffer中，由于这个语句中写的是`select *`，因此是把整个表t1放入了内存； 
2. 扫描表t2，把表t2中的每一行取出来，跟join_buffer中的数据做对比，满足join条件的，作为结果集的一部分返回。

如果t1的数据超过了join buffer的限制，那整个执行流程如下，处理完部分数据先放到结果集中：
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/blockJoin.png)

#### <a name="77">join语句mysql的优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
`Multi-Range Read`优化
- 大多数的数据都是按照主键递增顺序插入得到的，所以可以认为，如果按照主键的递增顺序查询的话，对磁盘的读比较接近顺序读，能够提升读性能
- MRR优化思路即将查询的关联集合排序，再关联查询，提高查询效率。将**随机访问改成范围访问**。

`Batched Key Access`(BAK)
- 将驱动表数据取出放join_buffer中，进行排序再关联查询。
- join_buffer内存不够大时，进行多次的重复操作。

#### <a name="78">总结</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 尽量使用被驱动表的索引，即关联表的字段为索引。
2. 不能使用被驱动表的索引，只能使用`Block Nested-Loop Join`算法，这样的语句就尽量不要使用；
3. 在使用join的时候，应该让**小表做驱动表**，连接表按各自条件进行过滤后，数据量小的为小表
4. 把join的条件写在where和写在on中区别为：一个为连接的条件，一个为过滤的条件。

### <a name="79">union执行流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/union1.png)

`(select 1000 as f) union (select id from t1 order by id desc limit 2);`\
执行流程：
1. 创建一个内存临时表，这个临时表只有一个整型字段f，并且f是主键字段。
2. 执行第一个子查询，得到1000这个值，并存入临时表中。
3. 执行第二个子查询：拿到第一行id=1000，试图插入临时表中。但由于1000这个值已经存在于临时表了，违反了唯一性约束，所以插入失败，然后继续执行；取到第二行id=999，插入临时表成功。
4. 从临时表中按行取出数据，返回结果，并删除临时表，结果中包含两行数据分别是1000和999

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/union.jpg)

**union all**: 
`(select 1000 as f) union all(select id from t1 order by id desc limit 2);`\
与上面`union`的区别为`union all`不需要除重，因此直接把查询结果放在结果集中返回。

### <a name="80">group by 执行流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
`select id%10 as m, count(*) as c from t1 group by m;`\
使用explain分析sql
- `Using index`，表示这个语句使用了覆盖索引，选择了索引a，不需要回表；
- `Using temporary`，表示使用了临时表；
- `Using filesort`，表示需要文件排序。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/groupby.png)

这个语句的执行流程：
1. 创建内存临时表，表里有两个字段m和c，主键是m；
2. 扫描表t1的索引a，依次取出叶子节点上的id值，计算id%10的结果，记为x；
   1. 如果临时表中没有主键为x的行，就插入一个记录(x,1);
   2. 如果表中有主键为x的行，就将x这一行的c值加1；
3. 遍历完成后，再根据字段m做排序，得到结果集返回给客户端。

group by语句默认都会对语句进行排序，可以使用order by null 避免group by 排序。\
`select id%10 as m, count(*) as c from t1 group by m order by null;`
#### <a name="81">优化-索引</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
索引保证了数据有序，在group by时候，分组计数计算时一片区域的id都是连续的，整个表扫描结束时便可以拿到结果，不需要临时表也不需要排序。

#### <a name="82">优化-直接排序</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
确保数据量确实超过了`sort buffer`，可以直接强制mysql直接使用磁盘文件排序。\
`select SQL_BIG_RESULT id%100 as m, count(*) as c from t1 group by m;`

#### <a name="83">小结</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 如果对group by语句的结果没有排序要求，要在语句后面加 order by null； 
2. 尽量让group by过程用上表的索引，确认方法是explain结果里没有Using temporary和 Using filesort； 
3. 如果group by需要统计的数据量不大，尽量只使用内存临时表；也可以通过适当调大 tmp_table_size参数，来避免用到磁盘临时表； 
4. 如果数据量实在太大，使用SQL_BIG_RESULT这个提示，来告诉优化器直接使用排序算法 得到group by的结果。

## <a name="84">MySQL中的组件</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="85">Buffer Pool</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
mysql的数据都是存放在磁盘中的，如果每次查询数据都从磁盘读取，性能较低，`Buffer Pool`就是一层缓存池，来优化查询效率的。
> 内存的数据页是在Buffer Pool(BP)中管理的，在WAL里`Buffer Pool`起到了**加速更新**的作用。而 实际上，`Buffer Pool`还有一个更重要的作用，就是加速查询。

在change buffer存储了一条更新操作后，如果刚好有一条查询，change buffer会把修改应用到内存页上，这时候内存数据页的结果是最新的，直接读内存页就可以了。
> Buffer Pool对查询的加速效果，依赖于一个重要的指标，即：内存命中率。\
> InnoDB `Buffer Pool`的大小是由参数`innodb_buffer_pool_size`确定的，一般建议设置成可用物理 内存的60%~80%。\

#### <a name="86">内存管理策略</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
InnoDB内存管理用的是最近最少使用(Least RecentlyUsed, LRU)算法。

InnoDB管理Buffer Pool的LRU算法，是用链表来实现的。在InnoDB实现上，按照5:3的比例把整个LRU链表分成了young区域和old区域。
> 这个策略，就是为了处理类似全表扫描的操作量身定制的。防止一个对历史数据大表的全表扫描，而导致Buffer Pool的内存命中率急剧下降

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/lru.png)

执行流程：
1. 访问数据页P3，P3位于young区域，移动到队头
2. 访问不在链表中的数据页，淘汰old区域队尾的Pm，但是新插入的数据页Px，插入的位置为LRU_old处。
3. 处于old区域的数据页，每次被访问的时候都要做下面这个判断： 
   - 若这个数据页在LRU链表中存在的时间超过了1秒，就把它移动到链表头部； 
   - 如果这个数据页在LRU链表中存在的时间短于1秒，位置保持不变。1秒这个时间，是由 参数innodb_old_blocks_time控制的。其默认值是1000，单位毫秒。

#### <a name="87">change buffer</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
change buffer(写缓存)：在MySQL5.5之前，叫插入缓冲(insert buffer)，只针对insert做了优化；现在对delete和update也有效，叫做写缓冲(change buffer)。

**作用场景**：\
当需要更新一个数据页时，如果数据页在内存中就直接更新，而如果这个数据页还没有在内存中的话，在不影响数据一致性的前提下，InooDB会将这些更新操作缓存在change buffer中，这样就不需要从磁盘中读入这个数据页了。在下次查询需要访问这个数据页的时候，将数据页读入内存，然后执行change buffer中与这个页有关的操作。\
通过这种方式就能保证这个数据逻辑的正确性。需要说明的是，虽然名字叫作`change buffer`，实际上它是可以持久化的数据。也就是说，change buffer在内存中有拷贝，也会被写入到磁盘上。

**Merge过程**：\
将change buffer中的操作应用到原数据页，得到最新结果的过程称为merge。除了访问这个数据页会触发merge外，系统有后台线程会定期merge。在数据库正常关闭（shutdown）的过程中，也会执行merge操作。\
merge的时候是真正进行数据更新的时刻，而change buffer的主要目的就是将记录的变更动作缓存下来，所以在一个**数据页**做merge之前，change buffer记录的变更越多（也就是这个页面 上要更新的次数越多），收益就越大。
> 对于写多读少的业务来说，页面在写完以后马上被访问到的概率比较小，此时change buffer的使用效果最好。这种业务模型常见的就是账单类、日志类的系统。

`change buffer`用的是`buffer pool`里的内存，因此不能无限增大。change buffer的大小，可以通 过参数innodb_change_buffer_max_size来动态设置。这个参数设置为50的时候，表示change buffer的大小最多只能占用buffer pool的50%。

一个更新的记录不在内存页中，这时，InnoDB的处理流程如下： 
1. 对于唯一索引来说，需要将数据页读入内存，判断到没有冲突，插入这个值，语句执行结束；
2. 对于普通索引来说，则是将更新记录在change buffer，语句执行就结束了。
> 将数据从磁盘读入内存涉及随机IO的访问，是数据库里面成本最高的操作之一。
> change buffer 因为减少了随机磁盘访问，所以对更新性能的提升是会很明显的。

#### <a name="88">change buffer 和 redo log</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/changebuffer.png)

```
mysql> insert into t(id,k) values(id1,k1),(id2,k2);
```
图中涉及了四个部分：内存、redo log（ib_log_fileX）、 数据表空间 （t.ibd）、系统表空间（ibdata1）。
1. Page 1在内存中，直接更新内存； 
2. Page 2没有在内存中，就在内存的change buffer区域，记录下“我要往Page 2插入一行”这个 信息
3. 将上述两个动作记入redo log中（图中3和4）。


### <a name="89">sort buffer</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
sort_buffer_size: 是MySQL为排序开辟的内存（`sort_buffer`） 的大小。 `如果要排序的数据量小于sort_buffer_size`，排序就在内存中完成。 但如果排序数据量太大内存放不下，则不得不利用磁盘临时文件辅助排序。
> sort_buffer_size大于了需要排序的数据量的大小，number_of_tmp_files就是0，排序直接在内存完成。

具体可见order by排序章节

### <a name="90">内存临时表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用`explain`关键字分析中：`Extra`字段显示`Using temporary`，表示的是需要使用临时表；`Using filesort`，表示的是需要执行 排序操作。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/orderTmpMemoryTable.png)

在order by 排序中，若数据超过sort buffer的大小，那么就会考虑使用内存临时表\
`select word from words order by rand() limit 3;`
1. 创建一个临时表。这个临时表使用的是memory引擎，这个表没有建索引。 
2. 从words表中，按主键顺序取出所有的word值。对于每一个word值，调用rand()函数生成一 个大于0小于1的随机小数，并把这个随机小数和word分别存入临时表的R和W字段中，到此，扫描行数是10000。
3. 现在临时表有10000行数据了，接下来你要在这个没有索引的内存临时表上，按照字段R排序。 
4. 初始化 sort_buffer。sort_buffer中有两个字段，一个是double类型，另一个是整型。 
5. 从内存临时表中一行一行地取出R值和位置信息，分别存入sort_buffer中的两个字段里。这个过程要对内存临时表做全表扫描，此时 扫描行数增加10000，变成了20000。 
6. 在sort_buffer中根据R的值进行排序。注意，这个过程没有涉及到表操作，所以不会增加扫 描行数。 
7. 排序完成后，取出前三个结果的位置信息，依次到内存临时表中取出word值，返回给客户端。这个过程中，访问了表的三行数据，总扫描行数变成了20003。


### <a name="91">磁盘临时表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
**内存临时表切换成磁盘临时表**的参数：`tmp_table_size`这个配置限制了内存临时表的大小，默认值是16M。如果临时表大 小超过了tmp_table_size，那么内存临时表就会转成磁盘临时表。\
磁盘临时表使用的引擎默认是InnoDB，是由参数`internal_tmp_disk_storage_engine`控制的。 当使用磁盘临时表的时候，对应的就是一个没有显式索引的InnoDB表的排序过程。

当使用磁盘临时表的时候，对应的就是一个没有显式索引的InnoDB表的排序过程。而临时文件的排序过程，就是归并排序算法。

### <a name="92">binlog cache</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
系统给binlog cache分配了一片内存，每个线程一个，参数 binlog_cache_size用于控制单个线程 内binlog cache所占内存的大小。如果超过了这个参数规定的大小，就要暂存到磁盘。

### <a name="93">redo log buffer</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="94">net_buffer</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：当MySQL开始产生可以返回的结果集，会在通过网络返回给客户端请求线程之前，会先暂存在通过 net_buffer_size 所设置的缓冲区中，等满足一定大小的时候才开始向客户端发送，以提高网络传输效率。

这块内存的大小是由参数net_buffer_length定义的，默认是 16k。
`mysql -h$host -P$port -u$user -p$pwd -e "select * from db1.t" > $target_file`

### <a name="95">join buffer</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
join_buffer的大小是由参数join_buffer_size设定的，默认值是256k。如果放不下驱动表的所有数据，策略很简单就是分段放。

## <a name="96">数据库设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="97">数据库设计原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="98">第一范式(列不可再分)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
第一范式是最基本的范式(确保每列保持原子性)。如果数据库表中的所有字段值都是不可分解的原子值，就说明该数据库表满足了第一范式。
> 第一范式的合理遵循需要根据系统的实际需求来定。比如某些数据库系统中需要用到“地址”这个属性，本来直接将“地址”属性设计成一个数据库表的字段就行。但是如果系统经常会访问“地址”属性中的“城市”部分，那么就非要将“地址”这个属性重新拆分为省份、城市、详细地址等多个部分进行存储。

#### <a name="99">第二范式(确保表中的每列都和主键相关)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
第二范式需要确保数据库表中的每一列都和主键相关，而不能只与主键的某一部分相关（主要针对联合主键而言）。也就是说在一个数据库表中，一个表中只能保存一种数据，不可以把多种数据保存在同一张数据库表中。
> 如一个订单的商品明细表，不应该订单主表的ID放到商品明细表中，会导致数据冗余

#### <a name="100">第三范式(确保每列都和主键列直接相关,而不是间接相关)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
第三范式需要确保数据表中的每一列数据都和主键直接相关，而不能间接相关。
> 如一个订单表都会有客户字段，可以把客户编码作为一个外键跟订单表建立响应关系。

#### <a name="101">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[数据库设计三大范式](https://www.cnblogs.com/linjiqin/archive/2012/04/01/2428695.html)

### <a name="102">表字段设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
规则：用尽量少的存储空间来存储一个字段的数据

常用的类型：`int、float、double、 decimal、varchar、char、 date、datetime`等
1. 对于固定长度的字符使用varchar来进行存储，如电话号码。
2. 对于精度要求高的使用decimal，如金钱、重量相关。
3. 与时间相关的，主要根据时间的精度，如只需要存储具体的天，不需要到时分秒。
4. 业务中选择性很少的状态status、类型type等字段推荐使用tinytint或者smallint类型节省存储空间。
5. 字段设置固定长度，对一个新增的列，mysql通常会分配固定的空间大小。太大的字段会造成空间浪费。
6. 表达是与否概念的字段，必须使用 is_xxx 的方式命名，数据类型是 unsigned tinyint（ 1 表示是，0 表示否）。
#### <a name="103">乐观锁字段</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用version作为乐观锁的控制。

#### <a name="104">通用字段</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
创建人、创建时间、修改人、修改时间

#### <a name="105">日期字段的选择</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

### <a name="106">实际设计问题 —— 设计部门表 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
设计一张部门表应该有哪些必要字段？

1. 部门基本信息。
2. 父部门字段。
3. 如何关联父部门下所有子部门
    1. 方案一，加入一个path字段。用于关联查询。
    2. 方案二，使用内存组装树节点数据。


## <a name="107">MySQL架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="108">主从模式(读写分离)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
读写分离的主要目标就是分摊主库的压力

客户端直连

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/readwrite1.png)

上述的结构是客户端(client)主动做负载均衡，这种模式下一般会把数据库的连接信息放在客户端的连接层。也就是说，由客户端来选择后端数据库进行查询。
- 优点：客户端直连方案，因为少了一层proxy转发，所以查询性能稍微好一点儿，并且整体架构简单，排查问题更方便。
- 缺点：在出现主备切换、库迁移等操作的时候，客户端都会感知到，并且需要调整数据库连接信息。


客户端连接代理proxy

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/readwrite2.png)

在MySQL和客户端之间有一个中间代理层proxy，客户端只连接proxy，由proxy根据请求类型和上下文决定请求的分发路由。
- 优点：客户端不需要关注后端细节，连接维护、后端信息维 护等工作，都是由proxy完成的。
- 缺点：proxy 也需要有高可用架构。带proxy架构的整体系统复杂度就更高了。

读写数据不一致问题：
1. **强制走主库方案**：查询请求做分类，对于必须要拿到最新结果的请求，强制将其发到主库上。对于可以读到旧数据的请求，才将其发到从库上。
2. **判断主备无延迟方案**：查询前先判断主从同步情况，确定同步完成再进行查询。
3. 等主库点位方案及等待GTID的方案：利用sql在从库执行，判断数据最新直接返回，若等待超时数据未同步，在主库直接执行或超时返回。


### <a name="109">分库分表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="110">分库分表场景</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
由于数据量过大而导致数据库性能降低的问题，将原来独立的数据库拆分成若干数据库组成 ，将数据大表拆分成若干数据表组成，使得单一数据库、单一数据表的数据量变小，从而达到提升数据库性能的目的。

#### <a name="111">垂直切分</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
垂直分表定义：将一个表按照字段分成多表，每个表存储其中一部分字段。通常我们按以下原则进行垂直拆分:
1. 把不常用的字段单独放在一张表;
2. 把text，blob等大字段拆分出来放在附表中;
3. 经常组合查询的列放在一张表中;

垂直分库是指按照业务将表进行分类，分布到不同的数据库上面，每个库可以放在不同的服务器上，它的核心理念是专库专用。
> 将表按照功能模块、关系密切程度划分出来，部署到不同的库上。\
> 例如，我们会建立定义数据库 workDB、商品数据库 payDB、用户数据库 userDB、日志数据库 logDB 等，分别用于存储项目数据定义表、商品定义表、用户数据表、日志数据表等。


#### <a name="112">水平切分</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
水平分库是把同一个表的数据按一定规则拆到不同的数据库中，每个库可以放在不同的服务器上。
> 如将店铺ID为单数的和店铺ID为双数的商品信息分别放在两个库中。

水平分表是在同一个数据库内，把同一个表的数据按一定规则拆到多个表中。

#### <a name="113">分库分表的查询</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
一般分库分表的场景，就是要把一个逻辑上的大表分散到不同的数据库实例上。比如。将一个大 表ht，按照**字段f**，拆分成1024个分表，然后分布到32个数据库实例上。一般情况下，这种分库分表系统都有一个中间层proxy。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/other/mysql/shardTable.png)

`select v from ht where f=N;`\
通过分表规则（比如，N%1024)来确认需要的数据被放在了哪个分表上。这种语句只需要访问一个分表

`select v from ht where k >= M order by t_modified desc limit 100;`
由于查询条件里面没有用到分区字段f，只能到所有的分区中去查找满足条件的所有 行，然后统一做order by的操作。

思路1：在proxy层的进程代码中实现排序。\
缺点：
1. 需要的开发工作量比较大。比如group by，甚至join这样的操作，对中间层的开发能力要求比较高
2. 对proxy端的压力比较大，尤其是很容易出现内存不够用和CPU瓶颈的问题。

思路2：把各个分库拿到的数据，汇总到一个MySQL实例的一个表中，然后在这个汇总实例上做逻辑操作。\
该思路用到了临时表的使用。


### <a name="114">MGR(MySQL Group Replication)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MGR(MySQL Group Replication)是 MySQL 自带的一个插件，可以灵活部署。
> 在MGR出现之前，用户常见的MySQL高可用方式，无论怎么变化架构，本质就是Master-Slave架构。MySQL 5.7版本开始支持无损半同步复制（lossless semi-sync replication），从而进一步提高数据复制的强一致性。\
> MySQL MGR 集群是多个 MySQL Server 节点共同组成的分布式集群，每个 Server 都有完整的副本，它是基于 ROW 格式的二进制日志文件和 GTID 特性。

1. MGR的定义: MGR是具备强大的分布式协调能力，可用于创建弹性、高可用性、高容错的复制拓扑的一个MySQL插件。
2. 通讯协议: 基于Paxos算法的GCS原子广播协议，保证了一条事务在集群内要么在全部节点上提交，要么全部回滚。
3. 组成员资格: MGR内部提供一个视图服务，集群节点之间相互交换各自的视图信息，从而且实现集群整体的稳态。
4. 数据一致性: MGR内部实现了一套不同事务之间修改数据的冲突认证检测机制。在集群的所有节点当中进行一个冲突认证检测，反之，通过冲突认证检测的事务即可提交成功。


### <a name="115">主从数据同步</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

参考资料：[MySQL5.7新特性--官方高可用方案MGR介绍](https://www.cnblogs.com/luoahong/articles/8043035.html)
#### <a name="116">MySQL异步复制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
master事务的提交不需要经过slave的确认。\
master不关心slave是否接收到master的binlog。slave接收到master的binlog后先写relay log，最后异步地去执行relay log中的sql应用到自身。
由于master的提交不需要确保slave relay log是否被正确接受，当slave接受master binlog失败或者relay log应用失败，master无法感知。

#### <a name="117">MySQL半同步复制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
在master事务的commit之前，必须确保一个slave收到relay log并且响应给master以后，才能进行事务的commit。但是slave对于relay log的应用仍然是异步进行的

#### <a name="118">MySQL组复制</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
基于传统异步复制和半同步复制的缺陷——数据的一致性问题无法保证

## <a name="119">其他面试题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="120">主键索引是否使用自增</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用自增好处：
1. 记录写入速度快：每次插入一条新记录，都是追加操作，都不涉及到挪动其他记录，也不会触发叶子节点的分裂。而有业务逻辑的字段做主键，则往往不容易保证有序插入，这样写数据成本相对较高。
2. 存储空间的角度：非主键索引的叶子节点上都是主键的值，主键长度越小，普通索引的叶子节点就越小，普通索引占用的空间也就越小。
> 从性能和存储空间方面考量，自增主键往往是更合理的选择。

适合用业务字段直接做主键的场景，典型的KV场景。\
比如，有些业务的场景需求是这样的：
1. 只有一个索引； 
2. 该索引必须是唯一索引。
> 由于没有其他索引，所以也就不用考虑其他索引的叶子节点大小的问题。

### <a name="121">临时表与内存表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
内存表，指的是使用Memory引擎的表
1. 建表语法是`create table …engine=memory`。
2. 这种表的数据都保存在内存里，系统重启的时候会被清空，但是表结构还在。

临时表，可以使用各种引擎类型。如果是使用InnoDB引擎或者MyISAM引擎的临时表，写数据的时候是写到磁盘上的。当然，临时表也可以使用Memory引擎。
1. 建表语法是`create temporary table …。`
2. 一个临时表只能被创建它的session访问，对其他线程不可见。
3. 在一个session中，session A内有同名的临时表和普通表的时候，show create语句，以及增删改查语句访问的 是临时表。

### <a name="122">重建索引是否合理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

```
alter table T drop index k; 
alter table T add index(k);
```
索引可能因为删除，或者页分 裂等原因，导致数据页有空洞，重建索引的过程会创建一个新的索引，把数据按顺序插入，这样 页面的利用率最高，也就是索引更紧凑、更省空间。
但是，重建主键的过程不合理。不论是删 除主键还是创建主键，都会将整个表重建。
> 这两个语句，你可以用这个语句代替 ： alter table Tengine=InnoDB

### <a name="123">mysql数据库抖动</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
当内存数据页跟磁盘数据页内容不一致的时候，我们称这个内存页为“脏页”。 \
在内存数据写入到磁盘后，内存和磁盘上的数据页的内容就一致了，称为“干净页”。

Mysql 数据库抖动可能就是在刷“脏页”。两种触发刷脏页（flush）的方法
1. 对应的就是InnoDB的redo log写满了。 这时候系统会停止所有更新操作，把checkpoint往前推进，redo log留出空间可以继续写。
2. 当需要新的内存页，而内存不够用的时候，系统的内存需要新的内存页，这时候需要淘汰一些内存页。这如果是脏页，就会把脏页刷到内存中，然后淘汰脏页。
> 为什么不直接淘汰脏页，等新数据读取的时候再应用redo log？ 主要为了保证状态统一，内存的数据存在则肯定是最新的，内存没有则文件肯定是最新的。
3. Mysql认为系统空闲时，刷脏页。
4. MySql关闭时刷脏页。

InnoDB刷脏页的控制策略

### <a name="124">读已提交和可重复读是如何实现的</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. ReadView：\
当进行查询操作时，事务会生成一个ReadView，ReadView是一个事务快照，准确来说是当前时间点系统内活跃的事务列表，也就是说系统内所有未提交的事务，都会记录在这个ReadView内，事务就根据它来判断哪些数据是可见的，哪些是不可见的。\
查询一条数据时，事务会拿到这个ReadView，去到undo log中进行判断。若查询到某一条数据：
2. undo log: 当事务对数据行进行一次更新操作时，会把旧数据行记录在一个叫做undo log的记录中，在undo log中除了记录数据行，还会记录下该行数据的对应的创建版本号，也就是生成这行数据的事务id。并连接原纪录

### <a name="125">读已提交和可重复读区别？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
举两个事务线程的例子。
     
### <a name="126">数据库数据库一致性是如何实现的？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
通过redoLog 与binlog 两阶段提交 保证事务一致性。

### <a name="127">redoLog、undoLog、binlog区别？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
binlog是Mysql数据库的日志，而redoLog与undoLog是innodb引擎才有的日志
- redoLog：确保事务的持久性。防止在发生故障的时间点，尚有脏页未写入磁盘，在重启mysql服务的时候，根据redo log进行重做，从而达到事务的持久性这一特性。
- undoLog：保存了事务发生之前的数据的一个版本，可以用于回滚，同时可以提供多版本并发控制下的读（MVCC），也即非锁定读

#### <a name="128">两种日志有以下三点不同</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. redoLog是InnoDB引擎特有的； binlog是MySQL的Server层实现的，所有引擎都可以使用。
2. redoLog是物理日志，记录的是“在某个数据页上做了什么修改”； binlog是逻辑日志，记录的是这个语句的原始逻辑，比如“给ID=2这一行的c字段加1 ”。
3. redoLog是循环写的，空间固定会用完； binlog是可以追加写入的。 “追加写”是指binlog文件写到一定大小后会切换到下一个，并不会覆盖以前的日志。
  
binlog的作用？（说的是监控，其实主要是主从复制或者备份）
- 用于复制，在主从复制中，从库利用主库上的binlog进行重播，实现主从同步。 
- 用于数据库的基于时间点的还原。

### <a name="129">一个事务开启什么时候产生三种日志？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 事务开启
2. 事务中执行修改数据操作：update、delete、insert
3. undo log记录日志
4. 事务提交
5. 执行redo log与binlog配合的两阶段提交

若事务回滚，根据undo log回滚更新请求。

### <a name="130">可重复读隔离级别下，事务中select一条记录巨慢</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
session A：
```
start transaction;
// 时刻A
select * from t where id =1; // sql1

select * from t where id = 1 lock in share mode; // sql2

```

session B:
```
update t set c=c+1 where id =1 // 执行1万次
```
session B更新完100万次，生成了100万个回滚日志(undo log)。因此第一条查询sql1需要从一条条的undo log回找，查询效率极低。而第二条sql2是当前读，直接定位到对应的结果。

### <a name="131">数据库什么情况会出现死锁？如何处理死锁？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
mysql 两个事物更新条件互斥，进入循环等待。

使用mysql参数innodb_deadlock_detect设置为on，表示开启这个死锁检测逻辑。

show processlist;显示哪些线程正在运行。您也可以使用mysqladmin processlist语句得到此信息
> 执行kill 命令

#### <a name="132">由于索引导致的死锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
InnoDB 引擎，更新操作默认会加行级锁，行级锁会对索引加锁。
如果更新语句使用多个索引，行锁会先锁非聚簇索引，再锁聚簇索引。
> 如果两个事务中的 SQL 用到了不同的非聚簇索引或者一个用了一个没有使用（即使用索引的情况不同），这样的话就会导致这两个事务加行锁的顺序不一致，形成了多个事务之间资源（行锁）的循环等待，导致死锁。

例子：
事务1用到的 SQL 语句：\
`UPDATE authorized_user SET status = 1 WHERE username = 'wcy'`\
事务1获取的锁：`X lock on (669,13359,1176) index_username`\
事务1等待的锁：`X lock on (669,937,136) PRIMARY`

事务2用到的 SQL 语句：\
`UPDATE authorized_user SET username = 'wcy100' WHERE id = 1`\
事务2获取的锁：`X lock on (669,937,136) PRIMARY`\
事务2等待的锁：`X lock on (669,13359,1176) index_username`

事务1的 update 语句正常的执行步骤如下：
1. 由于用到了非聚簇索引，首先需要获取 index_username 上的行级锁。
2. 紧接着根据主键进行更新，所以需要获取 PRIMAEY 上的行级锁。
3. 更新完毕后，提交，并释放所有锁。\
如果在步骤1和2之间突然插入事务2的这条 SQL 语句：\
`UPDATE authorized_user SET username = 'wcy100' WHERE id = 1,` \
这条语句会先锁住聚簇索引，然后由于其更新的字段 username 有非聚簇索引，所以这条语句需要锁住 index_username。这样就出现了死锁。

解决方案：都是用主键更新


### <a name="133">如何优化SQL</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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
    6. limit m,n要慎重，m的值越高，sql消耗时间越长。需要使用延迟关联优化。
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

- 相关资料：[敖丙工作以来总结的大厂SQL调优姿势](https://mp.weixin.qq.com/s/nEmN4S9JOTVGj5IHyfNtCw)

#### <a name="134">order by 优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Order by查询的两种情况：
1. Using index，是针对查询优化器的两种行为来去区分的。Using index就是说MySQL它可以直接通过索引去返回有序
的记录，而不需要去经过额外的排序的操作；
2. Using filesort需要去做额外的排序，在某些特殊的情况下，可能还会出现临时表排序的情况。

优化目标：尽量通过索引来避免额外的排序，减少CPU资源的消耗。\
主要优化策略：
1. Where条件和Order by使用相同的索引；
2. Order by的顺序和索引顺序相同；
3. Order by 的字段同为升序或降序。

**当无法避免Filesort操作时，优化思路就是让Filesort的操作更快。**

优化策略：
1. 适当调大max_length_for_sort_data这个参数的值，让优化器更倾向于选择一次扫描算法。一次性取出满足条件的所有记录；
2. 只使用必要的字段，不要使用Select *的写法；
3. 适当加大sort_buffer_size这个参数的值，避免磁盘排序的出现（线程参数，不要设置过大）。

#### <a name="135">sub-query 子查询</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
对于子查询，一般的优化策略是做等价改写

sub-query优化总结：
1. 子查询会用到临时表，需尽量避免；
2. 可以使用效率更高的Join查询来替代。

优化策略：等价改写、反嵌套。

例子：
`select * from customer where customer_id not in (select customer_id from payment)`\
改写成：\
`select * from customer a left join payment b on a.customer_id=b.customer_id where b.customer_id is null`
#### <a name="136">limit 优化 - 延迟关联</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
分页查询，就是将过多的结果在有限的界面上分多页来显示。\
其实质是每次查询只返回有限行，翻页一次执行一次。\
优化目标
1. 消除排序；
2. 避免扫描到大量不需要的记录。

优化策略： 
1. 覆盖索引
2. **延迟关联优化：**

延迟优化案例\
通过子查询关联，子查询先把对应的主键id查询出来，再进行主表关联
```
select * from user inner join 
(select id from user limit 10000000 10)  a on user.id = a.id

```

小案例：常规查询
```
select * from test_table 
where merchant_id = 43 and status = 'SUCCESS' 
order by salary_id desc limit 900000,10;

10 rows in set (0.82 sec)
```

延迟关联优化:通过id查询减少回表次数
```
SELECT *
FROM test_table a
INNER JOIN
  (SELECT salary_id
   FROM test_table
   WHERE merchant_id = 43
     AND STATUS = 'SUCCESS'
   LIMIT 900000,
         10) b ON a.salary_id = b.salary_id;

10 rows in set (0.52 sec)
```


##### <a name="137">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[mysql优化：覆盖索引（延迟关联）](https://cloud.tencent.com/developer/article/1446974?from=information.detail.mysql%20%E5%BB%B6%E8%BF%9F%E5%85%B3%E8%81%94)


#### <a name="138">Or/And Condition</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
处理策略
1. And子句多个条件中拥有一个过滤性较高的索引即可;
2. Or条件前后字段均要创建索引;
3. 为最常用的And组合条件创建复合索引。

#### <a name="139">join 优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
对于explain中出现的 `Using join buffer (Block Nested Loop)`当两张表关联，如果不能够通过索引去做关联条件的匹配，这时候就会产生join_buffer的使用。
1. 关联字段索引：每层内部循环仅获取需要关心的数据。
2. **小表驱动原则**：外层循环的结果集尽量小，目的是为了减少循环的次数。
   
**关联字段索引的必要性**


### <a name="140">数据库连接池、数据库连接线程安全的吗？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

数据库连接池就用用来保存数据库连接的一个池子。每当我们的业务代码需要和数据库进行交互时，就从这个池子里面取出一个数据库连接，然后在这个连接上进行查增删改操作。使用结束后，业务代码再将这个连接归还给这个池子，然后这个连接就可以被其他业务代码继续使用了。
从过程中可以看出，数据库连接池是可以在多个线程中使用的，每个线程获取不同的数据库连接。因此是线程安全的。

单个数据库连接肯定不是线程安全的，这就是需要实现数据库事务的原因。


### <a name="141">InnoDB 和 MyIsam 数据库引擎的区别</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 事务处理：MyISAM是非事务安全型的，而InnoDB是事务安全型的（支持事务处理等高级处理）；
2. 外键支持: myisam表不支持外键，而InnoDB支持
3. 锁机制不同：MyISAM是表级锁，而InnoDB是行级锁；
4. select查询的区别：
    - count优化： MyISAM只要简单的读出保存好的行数，InnoDB要选择表的索引进而计算行数计算。
    - 缓存区别：InnoDB要缓存数据块，MyISAM只缓存索引，加载索引更快
    - InnoDB要维护MVCC一致
5. 数据存储方式不同：
    - MyISAM索引文件和数据文件是分离的（`.myi`索引文件和`.myd`数据文件）
    - InnoDB的数据文件按主键聚集（`.idb`数据文件）
    - 由于数据存储的方式区别，MyISAM主键索引是非聚簇索引，InnoDB主键索引是聚簇索引；
6. 崩溃恢复，InnoDB特有的redo log 可以保证崩溃安全，
> - DELETE FROM table时，InnoDB不会重新建立表，而是一行一行的删除。
> - 索引的支持:InnoDB不支持FULLTEXT类型的索引
> - InnoDB表的行锁也不是绝对的，假如在执行一个SQL语句时MySQL不能确定要扫描的范围，InnoDB表同样会锁全表，例如update table set num=1 where name like “%aaa%”


应用场景
- MyISAM适合：(1)做很多count 的计算；(2)插入不频繁，查询非常频繁；(3)没有事务。
- InnoDB适合：(1)可靠性要求比较高，或者要求事务；(2)表更新和查询都相当的频繁，并且行锁定的机会比较大的情况。


### <a name="142">MySQL数据页(储存页)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

默认情况下，表空间中的页大小都为 16KB，当然也可以通过改变 innodb_page_size 选项对默认大小进行修改，需要注意的是不同的页大小最终也会导致区大小的不同：

相关文章： [mysql数据页存储与磁盘之间的关系](https://blog.csdn.net/zztisgood/article/details/84783370)

### <a name="143">高并发数据库读写压力大怎么处理？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 数据库加机器的性能，比如2G变成4G内存，增加网络带宽。
2. 进行数据库读写分离（主从数据一致性问题）
3. 对数据库横向拓展，进行分库分表（分布式事务问题、分库数据读取问题）
    - 表水平拆分、垂直拆分
    - 数据库分库
4. 热点数据的数据库，可以使用缓存数据库来支撑，再异步将数据更新到数据库

### <a name="144">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="145">什么是覆盖索引跟回表？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
见索引规则

#### <a name="146">`left join,right join,inner join`,表表关联什么区别？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> left join：保留左表所有结果，未匹配的右表记录字段为null
> right join：保留右表结果
> inner join：两表匹配结果的交集
> 表表关联：查询结构跟inner join结果一致


#### <a name="147">大事务会有什么影响？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 事务过大，可能导致binlog写入的过慢，因为binlog都是一次性写入。
2. 大事务执行时间过长，会导致同步给从库的时间产生了延迟。
> 因此不要一次性的用delete语句删除太多数据。其实，这就是一个典型的大事务场景。可以使用循环分批删除的方式，减小事务。