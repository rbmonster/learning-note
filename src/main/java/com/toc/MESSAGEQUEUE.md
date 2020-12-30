<a name="index">**Index**</a>

<a href="#0">消息队列</a>  
&emsp;<a href="#1">1. 异步、削峰、解耦</a>  
&emsp;&emsp;<a href="#2">1.1. 异步</a>  
&emsp;&emsp;<a href="#3">1.2. 解耦</a>  
&emsp;&emsp;<a href="#4">1.3. 削峰</a>  
&emsp;<a href="#5">2. 系统复杂度</a>  
&emsp;&emsp;<a href="#6">2.1. 重复消费</a>  
&emsp;&emsp;&emsp;<a href="#7">2.1.1. 接口幂等性</a>  
&emsp;&emsp;<a href="#8">2.2. 顺序消费</a>  
&emsp;&emsp;<a href="#9">2.3. 保证消息不丢失</a>  
&emsp;&emsp;<a href="#10">2.4. 消息堆积</a>  
&emsp;&emsp;&emsp;<a href="#11">2.4.1. 原因及处理流程</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#12">2.4.1.1. 典型原因</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#13">2.4.1.2. 处理措施</a>  
&emsp;&emsp;<a href="#14">2.5. 数据一致性</a>  
&emsp;&emsp;<a href="#15">2.6. 可用性</a>  
&emsp;<a href="#16">3. 消息队列选型</a>  
&emsp;&emsp;<a href="#17">3.1. 消息队列模型</a>  
&emsp;&emsp;&emsp;<a href="#18">3.1.1. 队列模型</a>  
&emsp;&emsp;&emsp;<a href="#19">3.1.2. 发布订阅模型</a>  
<a href="#20">Kafka</a>  
&emsp;<a href="#21">1. 基本概念</a>  
&emsp;<a href="#22">2. 分布式的kafka解决节点宕机或者抖动问题</a>  
&emsp;<a href="#23">3. 消息消费细节</a>  
&emsp;<a href="#24">4. 与ZooKeeper的依赖</a>  
&emsp;&emsp;<a href="#25">4.1. Broker 注册 </a>  
&emsp;&emsp;<a href="#26">4.2. Topic 注册 </a>  
&emsp;&emsp;<a href="#27">4.3. 负载均衡 </a>  
&emsp;&emsp;<a href="#28">4.4. 消息 消费进度Offset 记录</a>  
&emsp;<a href="#29">5. 面试题</a>  
&emsp;&emsp;<a href="#30">5.1. Kafka 是什么？主要应用场景有哪些？</a>  
&emsp;&emsp;&emsp;<a href="#31">5.1.1. kafka优点</a>  
&emsp;&emsp;<a href="#32">5.2. kafka 为什么快</a>  
&emsp;&emsp;&emsp;<a href="#33">5.2.1. 顺序写磁盘</a>  
&emsp;&emsp;&emsp;<a href="#34">5.2.2. 大量使用内存页</a>  
&emsp;&emsp;&emsp;<a href="#35">5.2.3. 零拷贝技术</a>  
&emsp;&emsp;&emsp;<a href="#36">5.2.4. 消息压缩、批量发送</a>  
&emsp;&emsp;<a href="#37">5.3. Kafka 如何保证消息队列不丢失</a>  
&emsp;&emsp;<a href="#38">5.4. kafka会存在数据丢失问题</a>  
&emsp;&emsp;<a href="#39">5.5. 想要保证消息（数据）是有序的，怎么做？</a>  
&emsp;&emsp;<a href="#40">5.6. 为什么在消息队列中重复消费了数据</a>  
<a href="#41">RocketMQ</a>  
&emsp;<a href="#42">1. 基本概念</a>  
&emsp;<a href="#43">2. 基本架构</a>  
&emsp;<a href="#44">3. 优缺点</a>  
&emsp;<a href="#45">4. 事务实现</a>  
# <a name="0">消息队列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
为什么使用消息队列？
- 异步、解耦、削峰、统一管理所有的消息
- 缺点：系统复杂度增加、消息队列存在消息发送的重复消费、消息丢失等问题。

## <a name="1">异步、削峰、解耦</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="2">异步</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 在基本的业务流程中，需要额外添加一些功能的场景。
  - 如订单流程中添加优惠券、添加积分
  - 如物流任务流程中通知船公司、铁路的场景

### <a name="3">解耦</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 额外功能的添加会导致业务流程处理的时间变长，可以使用异步如线程、线程池来实现。
  - 缺点：使用线程实现的话，每个流程都要try catch去处理异常场景，业务流程的耦合性太强，而且系统中会杂糅进调用其他系统的代码。不利于维护。

使用消息队列来实现，相当于流程操作完成，只要通知消息队列一个流程完成的消息就行了。其他业务流程订阅消息，接收到消息就会去处理对应的逻辑，降低整个流程的耦合性。

### <a name="4">削峰</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

对于大流量的请求，可以将请求放在消息队列。消息队列可以设置消息的效率频率，匹配服务器的服务能力，解决大流量请求发送服务器导致的服务器宕机问题。


## <a name="5">系统复杂度</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

问题： 重复消费、消息丢失、顺序消费

### <a name="6">重复消费</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
接口幂等性：使用相同参数重复执行，并能获得相同结果。

#### <a name="7">接口幂等性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 使用唯一标识：如订单号+业务场景这样的唯一标识。执行操作前先根据这个全局唯一ID是否存在，判断是否执行。如果不存在则把全局ID，存储到存储系统中，比如数据库、redis等。如果存在则表示该方法已经执行。存储的时候加入过期时间，防止机器宕机后，全局ID锁死导致操作无法继续

2. 去重表：适用于在业务中有唯一标的插入场景中。如支付场景中，如果一个订单只会支付一次，所以订单ID可以作为唯一标识。这时，我们就可以建一张去重表（流水表），并且把唯一标识作为唯一索引，在我们实现时，把创建支付单据和写入去去重表，放在一个事务中，如果重复创建，数据库会抛出唯一约束异常，操作就会回滚。

3. 多版本控制：适合在更新的场景中，比如我们要更新商品的名字，这时我们就可以在更新的接口中增加一个版本号，来做幂等。
  - `update goods set name=#{newName},version=#{version} where id=#{id} and version<${version}`


### <a name="8">顺序消费</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
针对顺序消费问题，只要发送者保证把消息发送到topic的相同队列中即可。
- 对于Kafka，生产者发送消息时可以指定固定Partition的topic下。
- 对于rocketmq，RocketMQ提供了MessageQueueSelector队列选择机制  Hash取模法将同一个操作的消息发送到同一个Queue里面。

同时消费者端也得保证顺序消费。如果多个消费者同时消费一个队列，一样可能出现顺序错乱的情况。

### <a name="9">保证消息不丢失</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 生产者端：不论是同步还是异步发送消息，同步和异步回调都需要做好try-catch，妥善的处理响应，如果Broker返回写入失败等错误消息，需要重试发送。当多次发送失败需要作报警，日志记录等。
- 消息队列端：存储消息阶段需要在消息刷盘之后再给生产者响应，防止突然宕机导致消息丢失。
  - 如果Broker是集群部署，有多副本机制，即消息不仅仅要写入当前Broker,还需要写入副本机中。那配置成至少写入两台机子后再给生产者响应。
- 消费端：消费者真正执行完业务逻辑之后，再发送给Broker消费成功

**消息可靠性增强了，性能就下降**

### <a name="10">消息堆积</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
主要原因 生产者的生产速度与消费者的消费速度不匹配
1. 先定位消费慢的原因，优化逻辑，如部分场景可以批量处理。
2. 增加Topic的队列数和消费者数量。

#### <a name="11">原因及处理流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

##### <a name="12">典型原因</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 实时/消费任务挂掉
2. Kafka分区数设置的不合理（太少）和消费者"消费能力"不足
3. Kafka消息的**发送不均匀**，导致分区间数据不均衡。
    1. 在使用Kafka producer消息时，可以为消息指定key和分区。
    2. 若指定了分区，那么消息会发送指定分区。
    3. 如果未指定分区但是指定了key，那么就会使用key进行hash算法计算对应的分区。要求key要均匀，否则会出现Kafka分区间数据不均衡。
    4. 若key和分区均未指定，那么将会使用轮询发送的方式。
```
KafkaTemplate.send​(ProducerRecord<K,​V> record)
```

##### <a name="13">处理措施</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 临时处理措施：同时增加kafka的服务器与消费者，增加partition，同时增加消费能力。

上述典型原因处理方法：
1. 实时/消费任务挂掉
    1. 任务重新启动后直接消费最新的消息，对于"滞后"的历史数据采用离线程序进行"补漏"。
    2. 任务启动从上次提交offset处开始消费处理
2. Kafka分区数设置的不合理(TODO) 
    - 增加分区或者重新评估分区设置 
3. 消息发送不均匀，合理设置
    - 在Kafka producer处，给key加随机后缀，使其均衡。 
    - 评估顺序消费的能力。

### <a name="14">数据一致性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
使用分布式事务解决消息发送后，一个消息被不同系统分别监听可能导致的 数据不一致问题。
  - 如把下单，优惠券，积分。。。都放在一个事务里面一样，要成功一起成功，要失败一起失败。
  
### <a name="15">可用性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
如何保证消息队列可用


## <a name="16">消息队列选型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
目前主要的消息队列有 ActiveMQ、RabbitMq、Kafka、RocketMq，用的比较多的是Kafka和RocketMq两个，主要这两个都支持10万级的高吞吐量，而且相应的开发社区活跃，遇到源码问题便于维护。

### <a name="17">消息队列模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
消息队列有两种模型：队列模型和发布/订阅模型。

#### <a name="18">队列模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
消费者之间是竞争关系，即每条消息只能被一个消费者消费。
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/middleware/picture/queueModel.jpg)

#### <a name="19">发布订阅模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
解决一条消息能被多个消费者消费的问题，消息发往一个Topic主题中，所有订阅了这个 Topic 的订阅者都能消费这条消息。
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/middleware/picture/queueModel2.jpg)


**发布/订阅模型兼容队列模型，即只有一个消费者的情况下和队列模型基本一致**
- 对应的kafka一个topic可以仅指定一组消费组。

- RabbitMQ 采用队列模型，RocketMQ和Kafka 采用发布/订阅模型

# <a name="20">Kafka</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
## <a name="21">基本概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
生产者Producer：发布消息的对象称之为话题生产者(Kafka topic producer)

消费者Consumer：订阅消息并处理发布的消息的种子的对象称之为话题消费者(consumers)
  - 消费者可以通过设置消息位移（offset）来控制自己想要获取的数据，比如可以从头读取，最新数据读取，重读读取等功能

话题Topic(具体的队列)： Kafka将消息种子(Feed)分门别类， 每一类的消息称之为话题(Topic).

代理Broker：已发布的消息保存在一组服务器中，称之为Kafka集群。集群中的每一个服务器都是一个代理(Broker). 


Partition(分区)：为了提高一个队列(topic)的吞吐量，Kafka会把topic进行分区(Partition)。Topic由一个或多个partition（分区）组成，生产者的消息可以指定或者由系统根据算法分配到指定分区。
  - 其中每个partition中的消息是有序的，但相互之间的顺序就不能保证了，若Topic有多个partition，生产者的消息可以指定或者由系统根据算法分配到指定分区，若你需要所有消息都是有序的，那么你最好只用一个分区。


消费组Consumers：一群消费者的集合，向Topic订阅消费消息的单位是Consumers。
  - 假如所有消费者都在**同一个消费者组**中，那么它们将协同消费订阅Topic的部分消息（根据分区与消费者的数量分配），保存负载平衡；
  - 假如所有消费者都在不同的消费者组中，并且订阅了同个Topic，那么它们将可以消费Topic的**所有消息**；
  - > 1. 若消费者数小于partition数，且消费者数为一个，那么它就消费所有消息；
    > 2. 若消费者数小于partition数，假设消费者数为N，partition数为M，那么每个消费者能消费的分区数为M/N或M/N+1；
    > 3. 若消费者数等于partition数，那么每个消费者都会均等分配到一个分区的消息；
    > 4. 若消费者数大于partition数，则将会出现部分消费者得不到消息分区，出现空闲的情况；
  - 消费者读消息的系统处理流程：正常的读磁盘数据是需要将内核态数据拷贝到用户态的，而Kafka 通过调用`sendfile()`直接从内核空间（DMA的）到内核空间（Socket的），少做了一步拷贝的操作。

offset(消费进度):表示消费者的消费进度，offset在broker以内部topic(__consumer_offsets)的方式来保存起来。

## <a name="22">分布式的kafka解决节点宕机或者抖动问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/middleware/picture/kafkaStructure.jpg)
- 红色块的partition代表的是主分区，紫色的partition块代表的是备份分区。生产者往topic丢数据，是与主分区交互，消费者消费topic的数据，也是与主分区交互。
- 备份分区仅仅用作于备份，不做读写。如果某个Broker挂了，那就会选举出其他Broker的partition来作为主分区，这就实现了高可用。

**partition持久化**(解决宕机消息丢失)：Kafka是将partition的数据写在磁盘的(消息日志)，不过Kafka只允许追加写入(顺序访问)，避免缓慢的随机 I/O 操作。写入时先缓存一部分，等到足够多数据量或等待一定的时间再批量写入(flush)。


## <a name="23">消息消费细节</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Kafka 使用拉取得方式消费消息。通过建立`长轮询`的模式来拉取消费。

消费者去 Broker 拉消息，定义了一个超时时间，也就是说消费者去请求消息，如果有的话马上返回消息，如果没有的话消费者等着直到超时，然后再次发起拉消息请求。
最后调用的就是 Kafka 包装过的 selector，而最终会调用 Java nio 的 select(timeout)。

大流程： 消费者等待消息，当有消息的时候 Broker 会直接返回消息，如果没有消息都会采取延迟处理的策略，并且为了保证消息的及时性，在对应队列或者分区有新消息到来的时候都**会提醒消息来了**，及时返回消息。

拉取消费的方式的优势：
1. 为broker减压，减少broker的工作量。
2. 消费者端实现各式各样，使用拉取的方式，消费者端更灵活。

## <a name="24">与ZooKeeper的依赖</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 探测broker和consumer的添加或移除。

- 负责维护所有partition的领导者/从属者关系（主分区和备份分区），如果主分区挂了，需要选举出备份分区作为主分区。

- 维护topic、partition等元配置信息


### <a name="25">Broker 注册 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Broker是分布式部署并且相互之间相互独立，但是需要有一个注册系统能够将整个集群中的Broker管理起来

在 Zookeeper 上会有一个专门用来进行 **Broker 服务器列表记录的节点**。每个 Broker 在启动时，都会到 Zookeeper 上进行注册，即到/brokers/ids 下创建属于自己的节点。每个 Broker 就会将自己的 IP 地址和端口等信息记录到该节点中去

### <a name="26">Topic 注册 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
在 Kafka 中，同一个Topic 的消息会被分成多个分区并将其分布在多个 Broker 上，这些分区信息及与 Broker 的对应关系也都是由 Zookeeper 在维护。比如我创建了一个名字为 my-topic 的主题并且它有两个分区，对应到 zookeeper 中会创建这些文件夹：/brokers/topics/my-topic/Partitions/0、/brokers/topics/my-topic/Partitions/1

### <a name="27">负载均衡 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Kafka 通过给特定 Topic 指定多个 Partition, 而各个 Partition 可以分布在不同的 Broker 上, 这样便能提供比较好的并发能力。 对于同一个 Topic 的不同 Partition，Kafka 会尽力将这些 Partition 分布到不同的 Broker 服务器上。当生产者产生消息后也会尽量投递到不同 Broker 的 Partition 里面。当 Consumer 消费的时候，Zookeeper 可以根据当前的 Partition 数量以及 Consumer 数量来实现动态负载均衡。

### <a name="28">消息 消费进度Offset 记录</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
在消费者对指定消息分区进行消息消费的过程中，需要定时地将分区消息的消费进度Offset记录到Zookeeper上，以便在该消费者进行重启或者其他消费者重新接管该消息分区的消息消费后，能够从之前的进度开始继续进行消息消费。Offset在Zookeeper中由一个专门节点进行记录，其节点路径为:

/consumers/[group_id]/offsets/[topic]/[broker_id-partition_id]

节点内容就是Offset的值。



## <a name="29">面试题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="30">Kafka 是什么？主要应用场景有哪些？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Kafka 是一个分布式流式处理平台。具有三个关键功能：
- 消息队列：发布和订阅消息流，这个功能类似于消息队列，这也是 Kafka 也被归类为消息队列的原因。
- 容错的持久方式存储记录消息流： Kafka 会把消息持久化到磁盘，有效避免了消息丢失的风险·。
- 流式处理平台： 在消息发布的时候进行处理，Kafka 提供了一个完整的流式处理类库。

Kafka 主要有两大应用场景：
- 消息队列 ：建立实时流数据管道，以可靠地在系统或应用程序之间获取数据。
- 数据处理： 构建实时的流数据处理程序来转换或处理数据流。

和其他消息队列相比,Kafka的优势在哪里？
- 极致的性能 ：基于 Scala 和 Java 语言开发，设计中大量使用了批量处理和异步的思想，最高可以每秒处理千万级别的消息。
- 生态系统兼容性无可匹敌 ：Kafka 与周边生态系统的兼容性是最好的没有之一，尤其在大数据和流计算领域。

#### <a name="31">kafka优点</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1、Kafka操作的是序列文件I / O（序列文件的特征是按顺序写，按顺序读），为保证顺序，Kafka强制点对点的按顺序传递消息，这意味着，一个consumer在消息流（或分区）中只有一个位置。

2、Kafka不保存消息的状态，即消息是否被“消费”。一般的消息系统需要保存消息的状态，并且还需要以随机访问的形式更新消息的状态。而Kafka 的做法是保存Consumer在Topic分区中的位置offset，在offset之前的消息是已被“消费”的，在offset之后则为未“消费”的，并且offset是可以任意移动的，这样就消除了大部分的随机IO。

3、Kafka支持点对点的批量消息传递。

4、Kafka的消息存储在OS pagecache（页缓存，page cache的大小为一页，通常为4K，在Linux读写文件时，它用于缓存文件的逻辑内容，从而加快对磁盘上映像和数据的访问）。

### <a name="32">kafka 为什么快</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 顺序写磁盘、大量使用内存页 、零拷贝技术的使用、消息压缩及批量发送

#### <a name="33">顺序写磁盘</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
在顺序读写的情况下，磁盘的顺序读写速度和内存持平。因为硬盘是机械结构，每次读写都会寻址->写入，其中寻址是一个“机械动作”，它是最耗时的。而且 Linux 对于磁盘的读写优化也比较多，包括 read-ahead 和 write-behind，磁盘缓存等。

使用磁盘操作有以下几个好处：

- 磁盘顺序读写速度超过内存随机读写。
- JVM 的 GC 效率低，内存占用大。使用磁盘可以避免这一问题。
- 系统冷启动后，磁盘缓存依然可用。

为了避免磁盘被撑满的情况，Kakfa 提供了两种策略来删除数据：
- 「基于时间」 （默认七天）
- 「基于 Partition 文件大小」

#### <a name="34">大量使用内存页</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
mmf （Memory Mapped Files）直接利用操作系统的Page来实现文件到物理内存的映射，完成之后对物理内存的操作会直接同步到硬盘。mmf 通过内存映射的方式大大提高了IO速率，省去了用户空间到内核空间的复制。

#### <a name="35">零拷贝技术</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
传统 Read/Write 方式进行网络文件传输，在传输过程中，文件数据实际上是经过了四次 Copy 操作，其具体流程细节如下：

1. 调用 Read 函数，文件数据被 Copy 到内核缓冲区。
2. Read 函数返回，文件数据从内核缓冲区 Copy 到用户缓冲区
3. Write 函数调用，将文件数据从用户缓冲区 Copy 到内核与 Socket 相关的缓冲区。
4. 数据从 Socket 缓冲区 Copy 到相关协议引擎。
`硬盘—>内核 buf—>用户 buf—>Socket 相关缓冲区—>协议引擎`

Sendfile 系统调用则提供了一种减少以上多次 Copy，提升文件传输性能的方法。以简化网络上和两个本地文件之间的数据传输。

#### <a name="36">消息压缩、批量发送</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
批量发送：Kafka允许进行批量发送消息，producter发送消息的时候，可以将消息缓存在本地，等到了固定条件发送到 Kafka 。
数据压缩：，Producer可以通过GZIP或Snappy格式对消息集合进行压缩。压缩的好处就是减少传输的数据量，减轻对网络传输的压力。


### <a name="37">Kafka 如何保证消息队列不丢失</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

ACK 机制：Kafka 采用的是至少一次（At least once），消息不会丢，但是可能会重复传输。 acks 的默认值即为1，代表我们的消息被leader副本接收之后就算被成功发送。我们可以配置 acks = all ，代表则所有副本都要接收到该消息之后该消息才算真正成功被发送。
  - 即保证消息不丢失的，设置消息持久化后再返回消息发送成功响应。
  - ```
    //设置等待acks返回的机制，有三个值
    // 0：不等待返回的acks（可能会丢数据，因为发送消息没有了失败重试机制，但是这是最低延迟）
    // 1：消息发送给kafka分区中的leader后就返回（如果follower没有同步完成leader就宕机了，就会丢数据）
    // -1（默认）：等待所有follower同步完消息后再发送（绝对不会丢数据）
    spring.kafka.producer.acks=-1
    ```

设置分区:为了保证 leader 副本能有 follower 副本能同步消息，我们一般会为 topic 设置 replication.factor >= 3。保证每个 分区(partition) 至少有 3 个副本.

关闭 unclean leader 选举: leader 副本发生故障时， follower 副本与leader副本 同步程度不一致的副本不会加入选举。
  - `unclean.leader.election.enable = false`

生产者端：
  1. 进行本地buffer，批量发送消息。设置`producer.type=async` 表示消息批量发送。默认为sync
  2. 设置至少写入到多个副本才算成功，也是提升数据持久性的一个参数，与acks配合使用。`min.insync.replicas> 1`
  
消费者端：Kafka consumer默认是自动提交位移的，设置` enable.auto.commit=false`关闭自动提交位移，在消息被完整处理之后再手动提交位移，保证消息不丢失。
```

// 自动提交offset
spring.kafka.consumer.enable-auto-commit=false
```

---

### <a name="38">kafka会存在数据丢失问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 数据在消息队列是如何持久化(磁盘？数据库？Redis？分布式文件系统？)
- Kafka会将partition以消息日志的方式(落磁盘)存储起来，通过 顺序访问IO和缓存(等到一定的量或时间)才真正把数据写到磁盘上，来提高速度。

### <a name="39">想要保证消息（数据）是有序的，怎么做？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- Kafka会将数据写到partition，单个partition的写入是有顺序的。如果要保证全局有序，那只能写入一个partition中。如果要消费也有序，消费者也只能有一个。

### <a name="40">为什么在消息队列中重复消费了数据</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 凡是分布式就无法避免网络抖动/机器宕机等问题的发生，很有可能消费者A读取了数据，还没来得及消费，就挂掉了。Zookeeper发现消费者A挂了，让消费者B去消费原本消费者A的分区，等消费者A重连的时候，发现已经重复消费同一条数据了。(各种各样的情况，消费者超时等等都有可能…)
  
如果业务上不允许重复消费的问题，最好消费者那端做业务上的校验（如果已经消费过了，就不消费了）
  
# <a name="41">RocketMQ</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="42">基本概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
消息生产者（Producer）：负责生产消息，一般由业务系统负责生产消息。RocketMQ提供多种发送方式，同步发送、异步发送、顺序发送、单向发送。同步和异步方式均需要Broker返回确认信息，单向发送不需要。

消息消费者（Consumer）：负责消费消息，一般是后台系统负责异步消费。
  - 提供了两种消费形式：拉取式消费、推动式消费。
    - 拉取式消费：应用通常主动调用Consumer的拉消息方法从Broker服务器拉消息、主动权由应用控制。
    - 推动式消费：Broker收到数据后会主动推送给消费端，该消费模式一般实时性较高。
  - 提供两种消费模式：集群消费和广播消费
    - 集群消费模式下,相同Consumer Group的每个Consumer实例平均分摊消息。
    - 广播消费模式下，相同Consumer Group的每个Consumer实例都接收全量的消息。

主题（Topic）：表示一类消息的集合，每个主题包含若干条消息，每条消息只能属于一个主题，是RocketMQ进行消息订阅的基本单位。

代理服务器（Broker Server）：消息中转角色，负责存储消息、转发消息。代理服务器在RocketMQ系统中负责接收从生产者发送来的消息并存储、同时为消费者的拉取请求作准备。代理服务器也**存储消息相关的元数据，包括消费者组、消费进度偏移和主题和队列消息**等

名字服务（Name Server）：名称服务充当路由消息的提供者。生产者或消费者能够通过名字服务查找各主题相应的Broker IP列表。多个Namesrv实例组成集群，但相互独立，没有信息交换。

生产者组（Producer Group）：同一类Producer的集合，这类Producer发送同一类消息且发送逻辑一致。如果发送的是事务消息且原始生产者在发送之后崩溃，则Broker服务器会联系同一生产者组的其他生产者实例以提交或回溯消费。

消费者组（Consumer Group）：同一类Consumer的集合，这类Consumer通常消费同一类消息且消费逻辑一致。消费者组使得在消息消费方面，实现负载均衡和容错的目标变得非常容易。要注意的是，消费者组的消费者实例必须订阅完全相同的Topic。RocketMQ 支持两种消息模式：集群消费和广播消费。
- 标签（Tag）：为消息设置的标志，用于同一主题下区分不同类型的消息。来自同一业务单元的消息，可以根据不同业务目的在同一主题下设置不同标签。标签能够有效地保持代码的清晰度和连贯性，并优化RocketMQ提供的查询系统。

## <a name="43">基本架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/middleware/picture/rocketmqAC.jpg)
主要包含四部分Producer、Consumer、Broker、NameServer

- Producer与NameServer集群中的其中一个节点（随机选择）建立长连接，定期从NameServer获取Topic路由信息，并向**提供Topic 服务的Master建立长连接**，且定时向Master发送心跳。
- Consumer与NameServer集群中的其中一个节点（随机选择）建立长连接，定期从NameServer获取Topic路由信息，并向**提供Topic服务的Master、Slave建立长连接**，且定时向Master、Slave发送心跳。

## <a name="44">优缺点</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 单机吞吐量：十万级
- 可用性：非常高，分布式架构
- 支持10亿级别的消息堆积，不会因为堆积导致性能下降
- 源码是java，我们可以自己阅读源码，定制自己公司的MQ，可以掌控
- 天生为金融互联网领域而生，支持可靠性要求很高的场景，尤其是电商里面的订单扣款，以及业务削峰，在大量交易涌入时，后端可能无法及时处理的情况，经历阿里双十一考验。

缺点：
- 支持的客户端语言不多，目前是java及c++，其中c++不成熟
- 社区活跃度不是特别活跃那种
- 没有在 mq 核心中去实现JMS等接口，有些系统要迁移需要修改大量代码

## <a name="45">事务实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
RocketMQ采用了2PC的思想来实现了提交事务消息，同时增加一个补偿逻辑来处理二阶段超时或者失败的消息。
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/middleware/picture/rocketMqTransaction.jpg)

2pc主要缺点，协调者故障、阻塞、宕机，导致commit消息未全部发送完毕，参与者预提交事务消息请求阻塞。rocketmq引入参与者的回查补偿机制，解决该问题。

- 补偿流程：

(1) 对没有Commit/Rollback的事务消息（pending状态的消息），从服务端发起一次“回查”

(2) Producer收到回查消息，检查回查消息对应的本地事务的状态

(3) 根据本地事务状态，重新Commit或者Rollback

其中，补偿阶段用于解决消息Commit或者Rollback发生超时或者失败的情况。
