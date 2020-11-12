# learning-note
## day by day
- [Java基础](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic)
- [IO总结](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/io/)
- [Java集合类](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/COLLECTION.md)
- Java并发相关
  - [Java线程](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/THREAD.md)
  - [Java并发（虚拟机）](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/CONCURRENT.md)
  - [Java并发（AQS）](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/CONCURRENTTOOL.md)
- [Java虚拟机](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/JVM.md)

### Spring
- [Spring Basic(TODO)](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four)
- [Bean生命周期](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four/LIFECYCLE.md)
- [Spring 源码](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four/SOURCECODE.md)
- [拦截器与过滤器](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four/FILTERANDINTERCEPTOR.md)
- [Spring Boot加载流程](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/four/SPRINGBOOT.md)

### 其他
- [接口设计](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/design/apidesign)

### 记录平常学习java 的一些知识点
- [设计模式(head first)](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/design)
- [JVM基础](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/jvm)
  - 主要是周志明《深入理解java虚拟机》书的记录
- [算法基础](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/algorithm)
- [Redis基础](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/redis)
  - 基于Redis的设计与实现
- [MySql45讲](https://github.com/rbmonster/learning-note/tree/master/src/main/java/com/learning/mysql)
- Java编程思想总结
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
  





### redis一般用什么数据结构？
- String、hash、list、set、sortSet
更新hash是如何更新的？别的数据结构有用过吗？
```
>SET msg "hello word"
// list
>RPUSH blah "hello" "world" "again"

>HSET book name "Master C++ in 21 days"

>SADD numbers 1 3 5 

>ZADD blah 1.0 www

```


### 数据库和redis如何保证数据一致性？
- mysql 数据更新成功会生成binlog， 通过canal订阅时间，获取binlog的key，更新到redis里面。  
  - canal是阿里巴巴旗下的一款开源项目,纯Java开发。基于数据库增量日志解析
https://zhuanlan.zhihu.com/p/91770135


### 分布式事务
- 分布式事务指事务的操作位于不同的节点上，需要保证事务的 AICD 特性。
- 例如在下单场景下，库存和订单如果不在同一个节点上，就涉及分布式事务。
- 两阶段提交（Two-phase Commit，2PC），通过引入协调者（Coordinator）来协调参与者的行为，并最终决定这些参与者是否要真正执行事务。
- 第三方的MQ是支持事务消息的，比如RocketMQ，他们支持事务消息的方式也是类似于采用的二阶段提交，但是市面上一些主流的MQ都是不支持事务消息的，比如 RabbitMQ 和 Kafka 都不支持。
  - 以阿里的 RocketMQ 中间件为例，其思路大致为：
  - 第一阶段Prepared消息，会拿到消息的地址。 第二阶段执行本地事务，第三阶段通过第一阶段拿到的地址去访问消息，并修改状态。如果确认消息发送失败了RocketMQ会定期扫描消息集群中的事务消息，这时候发现了Prepared消息，它会向消息发送者确认，所以生产方需要实现一个check接口，RocketMQ会根据发送端设置的策略来决定是回滚还是继续发送确认消息。这样就保证了消息发送与本地事务同时成功或同时失败。
  
- 在XA协议中包含着两个角色：事务协调者和事务参与者。让我们来看一看他们之间的交互流程：

在XA分布式事务的第一阶段，作为事务协调者的节点会首先向所有的参与者节点发送Prepare请求。

在接到Prepare请求之后，每一个参与者节点会各自执行与事务有关的数据更新，写入Undo Log和Redo Log。如果参与者执行成功，暂时不提交事务，而是向事务协调节点返回“完成”消息。

当事务协调者接到了所有参与者的返回消息，整个分布式事务将会进入第二阶段。

接到Commit请求之后，事务参与者节点会各自进行本地的事务提交，并释放锁资源。当本地事务完成提交后，将会向事务协调者返回“完成”消息。

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