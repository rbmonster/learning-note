# learning-note
## day by day
#### Java相关
- [Java基础](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/JAVA_BASE.md)
- [Java集合类](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/COLLECTION.md)
- [Java IO](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/toc/JAVA_IO.md)
- [Java虚拟机](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/JVM.md)

#### Java并发相关
- [Java线程](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/THREAD.md)
- [Java并发（虚拟机）](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/CONCURRENT.md)
- [Java并发（AQS）](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/CONCURRENTTOOL.md)
- [Java并发应用](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/CONCURREN_APPLICATION.md)

### Spring
- [Spring Basic(TODO)](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/SPRING.md)
- [Bean生命周期](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/LIFECYCLE.md)
- [Spring 源码](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/SOURCECODE.md)
- [拦截器与过滤器](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/FILTERANDINTERCEPTOR.md)
- [Spring Boot加载流程](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/SPRINGBOOT.md)

### 中间件
- [Redis](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/REDIS.md)
- [分布式基本概念](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/DISTRIBUTED-SYSTEM.md)
- [ZooKeeper](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/ZOOKEEPER.md)
- [消息队列(包含MQ)](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/MESSAGEQUEUE.md)

### 算法
- [算法基础](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/toc/ALGORITHM.md)
- [排序算法](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/toc/SORT_ALGORITHM.md)

### 其他
- [MySQL](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/MYSQL.md) 
- [MyBatis](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/MYBATIS.md)
- [计算机网络](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/NETWORK.md)

### 系统设计
- [接口设计](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/design/INTERFACE_DESIGN.md)
- [秒杀系统](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/design/SECONDS_KILL_DESIGN.md)
- [短连接](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/design/TINYURL.md)
- [抢红包(TODO)](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/design/SECONDKILL_REDPACKAGE.md)
- [扫码登陆](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/design/SCAN_LOGIN.md)


## 记录平常学习java 的一些知识点
- [设计模式(head first)](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/toc/CODEDESION_BOOK.md)
- [JVM基础(周志明)](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/toc/JVM_BOOK.md)
- [Redis基础(基于Redis的设计与实现)](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/toc/REDIS_BOOK.md)
- [MySql45讲](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/toc/MYSQL_BOOK.md)
- [java集合类(Java编程思想)](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/toc/COLLECTION_BOOK.md)
- [java并发(Java编程思想)](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/toc/CONCURRENT_BOOK.md)


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
  

### 分库分表有了解吗？
- 了解决由于数据量过大而导致数据库性能降低的问题，将原来独立的数据库拆分成若干数据库组成 ，将数据大表拆分成若干数据表组成，使得单一数据库、单一数据表的数据量变小，从而达到提升数据库性能的目的。
- 垂直分表定义：将一个表按照字段分成多表，每个表存储其中一部分字段。
  - 通常我们按以下原则进行垂直拆分:
  1. 把不常用的字段单独放在一张表;
  1. 把text，blob等大字段拆分出来放在附表中;
  1. 经常组合查询的列放在一张表中;
- 垂直分库是指按照业务将表进行分类，分布到不同的数据库上面，每个库可以放在不同的服务器上，它的核心理念是专库专用。
- 水平分库是把同一个表的数据按一定规则拆到不同的数据库中，每个库可以放在不同的服务器上。
  - 如将店铺ID为单数的和店铺ID为双数的商品信息分别放在两个库中。
- 水平分表是在同一个数据库内，把同一个表的数据按一定规则拆到多个表中。