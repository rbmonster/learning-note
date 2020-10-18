# learning-note
记录平常学习java 的一些知识点

- [设计模式(head first)](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/design)
- [JVM基础](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/jvm)
- [算法基础](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/algorithm)
- [Redis基础](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/redis)
- [MySql45讲](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/mysql)

- [java集合类](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/collection)
- [java并发](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/concurrent)

### 分布式文件存储系统
- FastDFS服务端有三个角色：跟踪服务器（tracker server）、存储服务器（storage server）和客户端（client）。
- tracker server：跟踪服务器，主要做调度工作，起负载均衡的作用。Tracker是FastDFS的协调者，负责管理所有的storage server和group，每个storage在启动后会连接Tracker，告知自己所属的group等信息，并保持周期性的心跳，tracker根据storage的心跳信息，建立group==&gt;[storage server list]的映射表。
- storage server：存储服务器（又称：存储节点或数据服务器），文件和文件属性（meta data）都保存到存储服务器上。Storage server直接利用OS的文件系统调用管理文件。
  - Storage server（后简称storage）以组（卷，group或volume）为单位组织，一个group内包含多台storage机器，数据互为备份，存储空间以group内容量最小的storage为准，所以建议group内的多个storage尽量配置相同，以免造成存储空间的浪费。
  - 以group为单位组织存储能方便的进行应用隔离、负载均衡、副本数定制（group内storage server数量即为该group的副本数），比如将不同应用数据存到不同的group就能隔离应用数据。
  
- 如何保证数据不丢失：
  - 在于Trunk-Server上，Trunk-Server实现空间的分配，每一次的空间分配都记录到Trunk-Binlog文件之中，并且定期（每秒）将该文件的更新同步给组内的其他Storage服务器

- 内部机制如下：
- 1、选择tracker server
  当集群中不止一个tracker server时，由于tracker之间是完全对等的关系，客户端在upload文件时可以任意选择一个trakcer。 选择存储的group 当tracker接收到upload file的请求时，会为该文件分配一个可以存储该文件的group，支持如下选择group的规则：
  1、Round robin，所有的group间轮询
  2、Specified group，指定某一个确定的group
  3、Load balance，剩余存储空间多多group优先
- 2、选择storage server
  当选定group后，tracker会在group内选择一个storage server给客户端，支持如下选择storage的规则：
  1、Round robin，在group内的所有storage间轮询
  2、First server ordered by ip，按ip排序
  3、First server ordered by priority，按优先级排序（优先级在storage上配置）
- 3、选择storage path
  当分配好storage server后，客户端将向storage发送写文件请求，storage将会为文件分配一个数据存储目录，支持如下规则：
  1、Round robin，多个存储目录间轮询
  2、剩余存储空间最多的优先
- 4、生成Fileid
  选定存储目录之后，storage会为文件生一个Fileid，由storage server ip、文件创建时间、文件大小、文件crc32和一个随机数拼接而成，然后将这个二进制串进行base64编码，转换为可打印的字符串。 选择两级目录 当选定存储目录之后，storage会为文件分配一个fileid，每个存储目录下有两级256*256的子目录，storage会按文件fileid进行两次hash（猜测），路由到其中一个子目录，然后将文件以fileid为文件名存储到该子目录下。
- 5、生成文件名
  当文件存储到某个子目录后，即认为该文件存储成功，接下来会为该文件生成一个文件名，文件名由group、存储目录、两级子目录、fileid、文件后缀名
  
### 线程池
#### 线程池创建
- 线程池的初始化：
```
/**
 * 用给定的初始参数创建一个新的ThreadPoolExecutor。
 */
public ThreadPoolExecutor(int corePoolSize,//线程池的核心线程数量
                          int maximumPoolSize,//线程池的最大线程数
                          long keepAliveTime,//当线程数大于核心线程数时，多余的空闲线程存活的最长时间
                          TimeUnit unit,//时间单位
                          BlockingQueue<Runnable> workQueue,//任务队列，用来储存等待执行任务的队列
                          ThreadFactory threadFactory,//线程工厂，用来创建线程，一般默认即可
                          RejectedExecutionHandler handler//拒绝策略，当提交的任务过多而不能及时处理时，我们可以定制策略来处理任务
                           ){
.......
}
```
- corePoolSize：核心线程数量，当有新任务在execute()方法提交时，会执行以下判断：
  - 如果运行的线程少于 corePoolSize，则创建新线程来处理任务，即使线程池中的其他线程是空闲的；
  - 如果线程池中的线程数量大于等于 corePoolSize 且小于 maximumPoolSize，则只有当workQueue满时才创建新的线程去处理任务；
  - 如果设置的corePoolSize 和 maximumPoolSize相同，则创建的线程池的大小是固定的，这时如果有新任务提交，若workQueue未满，则将请求放入workQueue中，等待有空闲的线程去从workQueue中取任务并处理；
  - 如果运行的线程数量大于等于maximumPoolSize，这时如果workQueue已经满了，则通过handler所指定的策略来处理任务
  - 所以，任务提交时，判断的顺序为 corePoolSize –> workQueue –> maximumPoolSize。

- 线程池拒绝策略
  - ThreadPoolExecutor.AbortPolicy：抛出 RejectedExecutionException来拒绝新任务的处理。
  - ThreadPoolExecutor.CallerRunsPolicy：调用执行自己的线程运行任务，也就是直接在调用execute方法的线程中运行(run)被拒绝的任务，如果执行程序已关闭，则会丢弃该任务。因此这种策略会降低对于新任务提交速度，影响程序的整体性能。如果您的应用程序可以承受此延迟并且你要求任何一个任务请求都要被执行的话，你可以选择这个策略。
    - 简单的说就是用启动threadPool的线程执行新的请求。
  - ThreadPoolExecutor.DiscardPolicy： 不处理新任务，直接丢弃掉。
  - ThreadPoolExecutor.DiscardOldestPolicy： 此策略将丢弃最早的未处理的任务请求。

![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/threadPoolProcess.jpg)

- 任务缓存队列
  - 在前面我们多次提到了任务缓存队列，即workQueue，它用来存放等待执行的任务。
  - workQueue的类型为BlockingQueue<Runnable>，通常可以取下面三种类型：

1）有界任务队列ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；

2）无界任务队列LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；

3）直接提交队列synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。

- 线上线程池的配置：


### Map 相关：
  - HashMap: 给予散列表实现。可以通过构造器设置容量和负载因子，以调整容器的性能
  - LinkedHashMap：类似HashMap，但是迭代访问时，取得“键值对”的顺序是按其插入对的顺序，或者是最近最少使用(LRU)的次序。
    - 在构造器可以指定参数为new LinkedHashMap<>(initialCapacity, loadFactor, true),initialCapacity为初始容量，loadFactor为加载因子，true表示使用LRU访问。
    - 初始容量是创建哈希表时的容量，加载因子是哈希表在其容量自动增加之前可以达到多满的一种尺度，它衡量的是一个散列表的空间的使用程度，负载因子越大表示散列表的装填程度越高，反之愈小。
  - TreeMap: 基于红黑树的实现。“键”或“键值对”的次序是由Comparable或Comparator决定的。TreeMap是唯一带有subMap()方法的Map，可以返回一个子树。
  - WeakHashMap： 弱键映射，允许设释放射所指对象。被垃圾收集器回收。
  - ConcurrentHashMap: 线程安全的Map.
  - IdentityHashMap：使用==代替equals()对“键”进行比较的散列映射。
  - sortedMap: 排序的Map，现阶段TreeMap是其唯一实现。
  - EnumMap:要求键必须来自一个Enum。
  - 散列陷阱：hashCode的生成应该保持在不同环境下生成的hashcode是不变的，否则就会造成放入HashMap中后，无法正常取出。
  - HashMap的性能因子
    - 容量：表中的桶位数。
    - 初始容量：表在创建时拥有的桶位数。允许在初始化时指定。
    - 尺寸：表中当前存储的项数。
    - 负载因子： 尺寸/容量。空表时因子值为0，半满时值为0.5。负载轻的表产生的冲突的可能性最小，因此HashMap和HashSet都具有允许你再指定负载因子的构造器，表示达到该负载因子水平时，容器将自动增加容量，使容器的容量大致加倍，并重新分布到新的桶位集中。
    - 再散列：达到该负载因子水平时，容器将自动增加容量，使容器的容量大致加倍，并重新分布到新的桶位集中（再散列）。
    - HashMap中的默认负载因子为0.75，这个因子在时间和空间代价之间达到了平衡。

- HashMap 数据结构
- resize会出现的问题
- 初始化100个元素大小如何设置


### Spring aop了解
5.spring aop有了解吗？spring事务有哪些？同一个方法无事务的方法调用有事务的方法会出现什么情况？

6.事务注解另一个属性，事务隔离级别有了解吗？读已提交核和可重复读诗如何实现的？读已提交和可重复读区别。
  数据库数据库一致性是如何实现的？redolog、undolog、binlog区别？binlog的作用？（说的是监控，其实主要是主从复制或者备份）
  
7.主键索引和非主键索引有什么区别？索引失效有哪些？
  联合索引1.商家id，2.订单时间,3.订单id，查询的时候会命中几个字段？(1个，时间会失效)
  
8.数据库什么情况会出现死锁？如何处理死锁？

9.redis一般用什么数据结构？更新hash是如何更新的？别的数据结构有用过吗？

10.数据库和redis如何保证数据一致性？加入新增数据库成功，然后更新redis失败怎么解决？

11.分布式事务，分库分表有了解吗？