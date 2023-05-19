<a name="index">**Index**</a>

<a href="#0">初识Kafka</a>  
&emsp;<a href="#1">1. 基本概念</a>  
&emsp;<a href="#2">2. 安装与配置</a>  
&emsp;&emsp;<a href="#3">2.1. 1. JDK的安装与配置</a>  
<a href="#4">解压之后当前/opt目录下生成一个名为jdk1.8.0_181的文件夹</a>  
<a href="#5">上面这个就是当前JDK8的安装目录</a>  
&emsp;&emsp;<a href="#6">0.2. 2. ZooKeeper安装与配置</a>  
<a href="#7">解压之后当前/opt目录下生成一个名为zookeeper-3.4.12的文件夹</a>  
<a href="#8">ZooKeeper服务器心跳时间，单位为ms</a>  
<a href="#9">允许follower连接并同步到leader的初始化连接时间，以tickTime的倍数来表示</a>  
<a href="#10">leader与follower心跳检测最大容忍时间，响应超过syncLimit*tickTime，leader认为</a>  
<a href="#11">follower“死掉”，从服务器列表中删除follower</a>  
<a href="#12">数据目录</a>  
<a href="#13">日志目录</a>  
<a href="#14">ZooKeeper对外服务端口</a>  
&emsp;&emsp;<a href="#15">0.3. 3. Kafka的安装与配置</a>  
<a href="#16">解压之后当前/opt目录下生成一个名为kafka_2.11-2.0.0的文件夹</a>  
<a href="#17">Kafka的根目录$KAFKA_HOME即为/opt/kafka_2.11-2.0.0，可以将Kafka_HOME添加到/etc/profile文件中，具体做法可以参考前面JDK和ZooKeeper的安装示例</a>  
<a href="#18">broker的编号，如果集群中有多个broker，则每个broker的编号需要设置的不同</a>  
<a href="#19">broker对外提供的服务入口地址</a>  
<a href="#20">存放消息日志文件的地址</a>  
<a href="#21">Kafka所需的ZooKeeper集群地址，为了方便演示，我们假设Kafka和ZooKeeper都安装在本机</a>  
<a href="#22">或者</a>  
&emsp;<a href="#23">1. 生产与消费</a>  
&emsp;<a href="#24">2. 服务端参数配置</a>  
&emsp;&emsp;<a href="#25">2.1. 1. zookeeper.connect</a>  
&emsp;&emsp;<a href="#26">2.2. 2. listeners</a>  
&emsp;&emsp;<a href="#27">2.3. 3. broker.id</a>  
&emsp;&emsp;<a href="#28">2.4. 4. log.dir和log.dirs</a>  
&emsp;&emsp;<a href="#29">2.5. 5. message.max.bytes</a>  
&emsp;<a href="#30">3. 总结</a>  
&emsp;<a href="#31">4. 生产者-客户端开发</a>  
&emsp;&emsp;<a href="#32">4.1. 客户端开发</a>  
&emsp;&emsp;<a href="#33">4.2. 必要的参数配置</a>  
&emsp;<a href="#34">5. 消息的发送</a>  
&emsp;<a href="#35">6. 序列化</a>  
<a href="#36">分区器</a>  
<a href="#37">生产者拦截器</a>  
<a href="#38">生产者客户端原理分析</a>  
&emsp;<a href="#39">1. 整体架构</a>  
&emsp;<a href="#40">2. 元数据的更新</a>  
&emsp;<a href="#41">3. 重要的生产者参数</a>  
&emsp;&emsp;<a href="#42">3.1. 1. acks</a>  
<a href="#43">或者</a>  
<a href="#44">或者</a>  
&emsp;&emsp;<a href="#45">0.2. 2. max.request.size</a>  
&emsp;&emsp;<a href="#46">0.3. 3. retries和retry.backoff.ms</a>  
&emsp;&emsp;<a href="#47">0.4. 4. compression.type</a>  
&emsp;&emsp;<a href="#48">0.5. 5. connections.max.idle.ms</a>  
&emsp;&emsp;<a href="#49">0.6. 6. linger.ms</a>  
&emsp;&emsp;<a href="#50">0.7. 7. receive.buffer.bytes</a>  
&emsp;&emsp;<a href="#51">0.8. 8. send.buffer.bytes</a>  
&emsp;&emsp;<a href="#52">0.9. 9. request.timeout.ms</a>  
&emsp;<a href="#53">1. 消费者与消费组</a>  
<a href="#54">消费者客户端开发</a>  
&emsp;<a href="#55">1. 必要的参数配置</a>  
<a href="#56">订阅主题和分区</a>  
<a href="#57">反序列化</a>  
<a href="#58">消息消费</a>  
<a href="#59">位移提交</a>  
&emsp;<a href="#60">1. 控制或关闭消费</a>  
<a href="#61">指定位移消费</a>  
<a href="#62">再均衡</a>  
<a href="#63">消费者拦截器</a>  
<a href="#64">消费者多线程实现</a>  
<a href="#65">重要的消费者参数</a>  
&emsp;<a href="#66">1. 1. fetch.min.bytes</a>  
&emsp;<a href="#67">2. 2. fetch.max.bytes</a>  
&emsp;<a href="#68">3. 3. fetch.max.wait.ms</a>  
&emsp;<a href="#69">4. 4. max.partition.fetch.bytes</a>  
&emsp;<a href="#70">5. 5. max.poll.records</a>  
&emsp;<a href="#71">6. 6. connections.max.idle.ms</a>  
&emsp;<a href="#72">7. 7. exclude.internal.topics</a>  
&emsp;<a href="#73">8. 8. receive.buffer.bytes</a>  
&emsp;<a href="#74">9. 9. send.buffer.bytes</a>  
&emsp;<a href="#75">10. 10. request.timeout.ms</a>  
&emsp;<a href="#76">11. 11. metadata.max.age.ms</a>  
&emsp;<a href="#77">12. 12. reconnect.backoff.ms</a>  
&emsp;<a href="#78">13. 13. retry.backoff.ms</a>  
&emsp;<a href="#79">14. 14. isolation.level</a>  
<a href="#80">主题与分区</a>  
<a href="#81">主题的管理</a>  
<a href="#82">创建主题</a>  
<a href="#83">分区副本的分配</a>  
<a href="#84">查看主题</a>  
<a href="#85">修改主题</a>  
<a href="#86">删除主题</a>  
<a href="#87">配置管理</a>  
<a href="#88">主题端参数</a>  
<a href="#89">初识KafkaAdminClient</a>  
&emsp;<a href="#90">1. 基本使用</a>  
&emsp;<a href="#91">2. 主题合法性验证</a>  
<a href="#92">优先副本的选举</a>  
<a href="#93">分区重分配</a>  
<a href="#94">复制限流</a>  
<a href="#95">修改副本因子</a>  
<a href="#96">如何选择合适的分区数</a>  
&emsp;<a href="#97">1. 性能测试工具</a>  
&emsp;<a href="#98">2. 分区数越多吞吐量就越高吗</a>  
&emsp;<a href="#99">3. 分区数的上限</a>  
&emsp;<a href="#100">4. 考量因素</a>  
&emsp;<a href="#101">5. 总结</a>  
<a href="#102">消费组管理</a>  
<a href="#103">消费位移管理</a>  
<a href="#104">查看当前消费组的消费位移</a>  
<a href="#105">将消费位移往前调整10，但是不执行</a>  
<a href="#106">将消费位移调整为当前位移并将结果输出到控制台，但是也不执行</a>  
<a href="#107">将消费位移再次往前调整20并输出结果，但是不执行</a>  
<a href="#108">中间步骤：将上面的输出结果保存到offsets.csv文件中</a>  
<a href="#109">通过from-file参数从offsets.csv文件中获取位移重置策略，并且执行</a>  
<a href="#110">最终消费位移都往前重置了20</a>  
<a href="#111">手动删除消息</a>  
<a href="#112">Kafka Connect</a>  
&emsp;<a href="#113">1. 独立模式</a>  
&emsp;<a href="#114">2. REST API</a>  
&emsp;<a href="#115">3. 分布式模式</a>  
<a href="#116">Kafka Mirror Maker</a>  
<a href="#117">consumer.properties的配置</a>  
<a href="#118">producer.properties的配置 </a>  
<a href="#119">Kafka Streams</a>  
<a href="#120">Kafka监控</a>  
<a href="#121">监控数据的来源</a>  
&emsp;<a href="#122">1. OneMinuteRate</a>  
&emsp;<a href="#123">2. 获取监控指标</a>  
<a href="#124">消费滞后</a>  
<a href="#125">监控指标说明</a>  
<a href="#126">监控模块</a>  
&emsp;<a href="#127">1. 总结</a>  
<a href="#128">初识Spark</a>  
&emsp;<a href="#129">1. Spark的安装及简单应用</a>  
&emsp;<a href="#130">2. Spark编程模型</a>  
&emsp;<a href="#131">3. Spark的运行结构</a>  
<a href="#132">Spark Streaming简介</a>  
<a href="#133">Kafka与Spark Streaming的整合</a>  
<a href="#134">Spark SQL</a>  
<a href="#135">Structured Streaming</a>  
<a href="#136">Kafka与Structured Streaming的整合</a>  
&emsp;<a href="#137">1. 总结</a>  
# <a name="0">初识Kafka</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka 起初是 由 LinkedIn 公司采用 Scala 语言开发的一个多分区、多副本且基于 ZooKeeper 协调的分布式消息系统，现已被捐献给 Apache 基金会。目前 Kafka 已经定位为一个分布式流式处理平台，它以高吞吐、可持久化、可水平扩展、支持流数据处理等多种特性而被广泛使用。目前越来越多的开源分布式处理系统如 Cloudera、Storm、Spark、Flink 等都支持与 Kafka 集成。

Kafka 之所以受到越来越多的青睐，与它所“扮演”的三大角色是分不开的：

- **消息系统：** Kafka 和传统的消息系统（也称作消息中间件）都具备系统解耦、冗余存储、流量削峰、缓冲、异步通信、扩展性、可恢复性等功能。与此同时，Kafka 还提供了大多数消息系统难以实现的消息顺序性保障及回溯消费的功能。
- **存储系统：** Kafka 把消息持久化到磁盘，相比于其他基于内存存储的系统而言，有效地降低了数据丢失的风险。也正是得益于 Kafka 的消息持久化功能和多副本机制，我们可以把 Kafka 作为长期的数据存储系统来使用，只需要把对应的数据保留策略设置为“永久”或启用主题的日志压缩功能即可。
- **流式处理平台：** Kafka 不仅为每个流行的流式处理框架提供了可靠的数据来源，还提供了一个完整的流式处理类库，比如窗口、连接、变换和聚合等各类操作。

## <a name="1">基本概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

一个典型的 Kafka 体系架构包括若干 Producer、若干 Broker、若干 Consumer，以及一个 ZooKeeper 集群，如下图所示。其中 ZooKeeper 是 Kafka 用来负责集群元数据的管理、控制器的选举等操作的。Producer 将消息发送到 Broker，Broker 负责将收到的消息存储到磁盘中，而 Consumer 负责从 Broker 订阅并消费消息。



![图1-1 Kafka体系结构](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/5/16949bd6279df106~tplv-t2oaga2asx-watermark.awebp)



整个 Kafka 体系结构中引入了以下3个术语：

1. **Producer：** 生产者，也就是发送消息的一方。生产者负责创建消息，然后将其投递到 Kafka 中。
2. **Consumer：** 消费者，也就是接收消息的一方。消费者连接到 Kafka 上并接收消息，进而进行相应的业务逻辑处理。
3. **Broker：** 服务代理节点。对于 Kafka 而言，Broker 可以简单地看作一个独立的 Kafka 服务节点或 Kafka 服务实例。大多数情况下也可以将 Broker 看作一台 Kafka 服务器，前提是这台服务器上只部署了一个 Kafka 实例。一个或多个 Broker 组成了一个 Kafka 集群。一般而言，我们更习惯使用首字母小写的 broker 来表示服务代理节点。

在 Kafka 中还有两个特别重要的概念—主题（Topic）与分区（Partition）。Kafka 中的消息以主题为单位进行归类，生产者负责将消息发送到特定的主题（发送到 Kafka 集群中的每一条消息都要指定一个主题），而消费者负责订阅主题并进行消费。

主题是一个逻辑上的概念，它还可以细分为多个分区，一个分区只属于单个主题，很多时候也会把分区称为主题分区（Topic-Partition）。同一主题下的不同分区包含的消息是不同的，分区在存储层面可以看作一个可追加的日志（Log）文件，消息在被追加到分区日志文件的时候都会分配一个特定的偏移量（offset）。

offset 是消息在分区中的唯一标识，Kafka 通过它来保证消息在分区内的顺序性，不过 offset 并不跨越分区，也就是说，Kafka 保证的是分区有序而不是主题有序。



![图1-2 消息追加写入](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/5/16949cc96dc79e19~tplv-t2oaga2asx-watermark.awebp)



如上图所示，主题中有4个分区，消息被顺序追加到每个分区日志文件的尾部。Kafka 中的分区可以分布在不同的服务器（broker）上，也就是说，一个主题可以横跨多个 broker，以此来提供比单个 broker 更强大的性能。

每一条消息被发送到 broker 之前，会根据分区规则选择存储到哪个具体的分区。如果分区规则设定得合理，所有的消息都可以均匀地分配到不同的分区中。如果一个主题只对应一个文件，那么这个文件所在的机器I/O将会成为这个主题的性能瓶颈，而分区解决了这个问题。在创建主题的时候可以通过指定的参数来设置分区的个数，当然也可以在主题创建完成之后去修改分区的数量，通过增加分区的数量可以实现水平扩展。

Kafka 为分区引入了多副本（Replica）机制，通过增加副本数量可以提升容灾能力。

同一分区的不同副本中保存的是相同的消息（在同一时刻，副本之间并非完全一样），副本之间是“一主多从”的关系，其中 leader 副本负责处理读写请求，follower 副本只负责与 leader 副本的消息同步。副本处于不同的 broker 中，当 leader 副本出现故障时，从 follower 副本中重新选举新的 leader 副本对外提供服务。Kafka 通过多副本机制实现了故障的自动转移，当 Kafka 集群中某个 broker 失效时仍然能保证服务可用。



![图1-3 多副本架构](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/5/16949c07c0df30dc~tplv-t2oaga2asx-watermark.awebp)



如上图所示，Kafka 集群中有4个 broker，某个主题中有3个分区，且副本因子（即副本个数）也为3，如此每个分区便有1个 leader 副本和2个 follower 副本。生产者和消费者只与 leader 副本进行交互，而 follower 副本只负责消息的同步，很多时候 follower 副本中的消息相对 leader 副本而言会有一定的滞后。

Kafka 消费端也具备一定的容灾能力。Consumer 使用拉（Pull）模式从服务端拉取消息，并且保存消费的具体位置，当消费者宕机后恢复上线时可以根据之前保存的消费位置重新拉取需要的消息进行消费，这样就不会造成消息丢失。

分区中的所有副本统称为 AR（Assigned Replicas）。所有与 leader 副本保持一定程度同步的副本（包括 leader 副本在内）组成ISR（In-Sync Replicas），ISR 集合是 AR 集合中的一个子集。消息会先发送到 leader 副本，然后 follower 副本才能从 leader 副本中拉取消息进行同步，同步期间内 follower 副本相对于 leader 副本而言会有一定程度的滞后。

前面所说的“一定程度的同步”是指可忍受的滞后范围，这个范围可以通过参数进行配置。与 leader 副本同步滞后过多的副本（不包括 leader 副本）组成 OSR（Out-of-Sync Replicas），由此可见，AR=ISR+OSR。在正常情况下，所有的 follower 副本都应该与 leader 副本保持一定程度的同步，即 AR=ISR，OSR 集合为空。

leader 副本负责维护和跟踪 ISR 集合中所有 follower 副本的滞后状态，当 follower 副本落后太多或失效时，leader 副本会把它从 ISR 集合中剔除。如果 OSR 集合中有 follower 副本“追上”了 leader 副本，那么 leader 副本会把它从 OSR 集合转移至 ISR 集合。默认情况下，当 leader 副本发生故障时，只有在 ISR 集合中的副本才有资格被选举为新的 leader，而在 OSR 集合中的副本则没有任何机会（不过这个原则也可以通过修改相应的参数配置来改变）。

ISR 与 HW 和 LEO 也有紧密的关系。HW 是 High Watermark 的缩写，俗称高水位，它标识了一个特定的消息偏移量（offset），消费者只能拉取到这个 offset 之前的消息。



![图1-4 分区中各种偏移量的说明](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/5/16949cdf7c77eeae~tplv-t2oaga2asx-watermark.awebp)



如上图所示，它代表一个日志文件，这个日志文件中有9条消息，第一条消息的 offset（LogStartOffset）为0，最后一条消息的 offset 为8，offset 为9的消息用虚线框表示，代表下一条待写入的消息。日志文件的 HW 为6，表示消费者只能拉取到 offset 在0至5之间的消息，而 offset 为6的消息对消费者而言是不可见的。

LEO 是 Log End Offset 的缩写，它标识当前日志文件中下一条待写入消息的 offset，上图中 offset 为9的位置即为当前日志文件的 LEO，LEO 的大小相当于当前日志分区中最后一条消息的 offset 值加1。分区 ISR 集合中的每个副本都会维护自身的 LEO，而 ISR 集合中最小的 LEO 即为分区的 HW，对消费者而言只能消费 HW 之前的消息。

> 注意要点：很多资料中误将上图中的 offset 为5的位置看作 HW，而把 offset 为8的位置看作 LEO，这显然是不对的。



![图1-5 写入消息（情形1）](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/5/16949d2953ddc87a~tplv-t2oaga2asx-watermark.awebp)



为了让读者更好地理解 ISR 集合，以及 HW 和 LEO 之间的关系，下面通过一个简单的示例来进行相关的说明。如上图所示，假设某个分区的 ISR 集合中有3个副本，即一个 leader 副本和2个 follower 副本，此时分区的 LEO 和 HW 都为3。消息3和消息4从生产者发出之后会被先存入 leader 副本，如下图所示。



![图1-6 写入消息（情形2）](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/5/16949d4afb601bbf~tplv-t2oaga2asx-watermark.awebp)

在消息写入 leader 副本之后，follower 副本会发送拉取请求来拉取消息3和消息4以进行消息同步。





![图1-7 写入消息（情形3）](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/5/16949d4e8e9e1aa0~tplv-t2oaga2asx-watermark.awebp)



在同步过程中，不同的 follower 副本的同步效率也不尽相同。如上图所示，在某一时刻 follower1 完全跟上了 leader 副本而 follower2 只同步了消息3，如此 leader 副本的 LEO 为5，follower1 的 LEO 为5，follower2 的 LEO 为4，那么当前分区的 HW 取最小值4，此时消费者可以消费到 offset 为0至3之间的消息。

写入消息（情形4）如下图所示，所有的副本都成功写入了消息3和消息4，整个分区的 HW 和 LEO 都变为5，因此消费者可以消费到 offset 为4的消息了。



![图1-8 写入消息（情形4）](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/5/16949d51ce4377f3~tplv-t2oaga2asx-watermark.awebp)



由此可见，Kafka 的复制机制既不是完全的同步复制，也不是单纯的异步复制。事实上，同步复制要求所有能工作的 follower 副本都复制完，这条消息才会被确认为已成功提交，这种复制方式极大地影响了性能。而在异步复制方式下，follower 副本异步地从 leader 副本中复制数据，数据只要被 leader 副本写入就被认为已经成功提交。在这种情况下，如果 follower 副本都还没有复制完而落后于 leader 副本，突然 leader 副本宕机，则会造成数据丢失。Kafka 使用的这种 ISR 的方式则有效地权衡了数据可靠性和性能之间的关系。



## <a name="2">安装与配置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

本节详细介绍 Kafka 运行环境的搭建，为了节省篇幅，本节的内容以 Linux CentOS 作为安装演示的操作系统，其他 Linux 系列的操作系统也可以参考本节的内容。具体的操作系统的信息如下：

```
[root@node1 ~]# uname -a
Linux node1 2.6.32-504.23.4.el6.x86_64 #1 SMP Tue Jun 9 20:57:37 UTC 2015 x86_64 x86_64 x86_64 GNU/Linux
[root@node1 ~]# cat /etc/issue
CentOS release 6.6 (Final)
Kernel \r on an \m
```

由第1节中第1张图可知，搭建 Kafka 运行环境还需要涉及 ZooKeeper，Kafka 和 ZooKeeper 都是运行在 JVM 之上的服务，所以还需要安装 JDK。Kafka 从2.0.0版本开始就不再支持 JDK7 及以下版本，本节就以 JDK8 为例来进行演示。

### <a name="3">1. JDK的安装与配置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

很多学习 Kafka 的读者也都是 JVM 系语言的支持者，如果你的操作系统中已经安装了 JDK8 及以上版本则可以跳过这段内容。

安装 JDK 的第一步就是下载 JDK 1.8的安装包，可以进入 Oracle 官网页面进行下载。示例中选择的安装包是 jdk-8u181-linux-x64.tar.gz，我们这里将其先复制至/opt 目录下，本书所有与安装有关的操作都在这个目录下进行。

其次将/opt 目录下的安装包解压，相关信息如下：

```
[root@node1 opt]# ll jdk-8u181-linux-x64.tar.gz 
-rw-r--r-- 1 root root 185646832 Aug 31 14:48 jdk-8u181-linux-x64.tar.gz
[root@node1 opt]# tar zxvf jdk-8u181-linux-x64.tar.gz 
# <a name="4">解压之后当前/opt目录下生成一个名为jdk1.8.0_181的文件夹</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[root@node1 opt]# cd jdk1.8.0_181/
[root@node1 jdk1.8.0_181]# pwd
/opt/jdk1.8.0_181
# <a name="5">上面这个就是当前JDK8的安装目录</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```

然后配置 JDK 的环境变量。修改/etc/profile 文件并向其中添加如下配置：

```
export JAVA_HOME=/opt/jdk1.8.0_181
export JRE_HOME=$JAVA_HOME/jre
export PATH=$PATH:$JAVA_HOME/bin
export CLASSPATH=./://$JAVA_HOME/lib:$JRE_HOME/lib
```

再执行 source /etc/profile 命令使配置生效，最后可以通过 java –version命令验证 JDK 是否已经安装配置成功。如果安装配置成功，则会正确显示出 JDK 的版本信息，参考如下：

```
[root@node1 ~]# java -version
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
```

### <a name="6">2. ZooKeeper安装与配置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

ZooKeeper 是安装 Kafka 集群的必要组件，Kafka 通过 ZooKeeper 来实施对元数据信息的管理，包括集群、broker、主题、分区等内容。

ZooKeeper 是一个开源的分布式协调服务，是 Google Chubby的一个开源实现。分布式应用程序可以基于 ZooKeeper 实现诸如数据发布/订阅、负载均衡、命名服务、分布式协调/通知、集群管理、Master 选举、配置维护等功能。

在 ZooKeeper 中共有3个角色：leader、follower 和 observer，同一时刻 ZooKeeper 集群中只会有一个 leader，其他的都是 follower 和 observer。observer 不参与投票，默认情况下 ZooKeeper 中只有 leader 和 follower 两个角色。更多相关知识可以查阅 ZooKeeper 官方网站来获得。

安装 ZooKeeper 的第一步也是下载相应的安装包，安装包可以从官网中获得，示例中使用的安装包是 zookeeper-3.4.12.tar.gz，同样将其复制到/opt 目录下，然后解压缩，参考如下：

```
[root@node1 opt]# ll zookeeper-3.4.12.tar.gz 
-rw-r--r-- 1 root root 36667596 Aug 31 15:55 zookeeper-3.4.12.tar.gz
[root@node1 opt]# tar zxvf zookeeper-3.4.12.tar.gz 
# <a name="7">解压之后当前/opt目录下生成一个名为zookeeper-3.4.12的文件夹</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[root@node1 opt]# cd zookeeper-3.4.12
[root@node1 zookeeper-3.4.12]# pwd
/opt/zookeeper-3.4.12
```

第二步，向/etc/profile 配置文件中添加如下内容，并执行 source /etc/profile 命令使配置生效：

```
export ZOOKEEPER_HOME=/opt/zookeeper-3.4.12
export PATH=$PATH:$ZOOKEEPER_HOME/bin
```

第三步，修改 ZooKeeper 的配置文件。首先进入$ZOOKEEPER_HOME/conf目录，并将zoo_sample.cfg 文件修改为 zoo.cfg：

```
[root@node1 zookeeper-3.4.12]# cd conf
[root@node1 conf]# cp zoo_sample.cfg zoo.cfg
```

然后修改 zoo.cfg 配置文件，zoo.cfg 文件的内容参考如下：

```
# <a name="8">ZooKeeper服务器心跳时间，单位为ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
tickTime=2000
# <a name="9">允许follower连接并同步到leader的初始化连接时间，以tickTime的倍数来表示</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
initLimit=10
# <a name="10">leader与follower心跳检测最大容忍时间，响应超过syncLimit*tickTime，leader认为</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
# <a name="11">follower“死掉”，从服务器列表中删除follower</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
syncLimit=5
# <a name="12">数据目录</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
dataDir=/tmp/zookeeper/data
# <a name="13">日志目录</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
dataLogDir=/tmp/zookeeper/log
# <a name="14">ZooKeeper对外服务端口</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
clientPort=2181
```

默认情况下，Linux 系统中没有/tmp/zookeeper/data 和/tmp/zookeeper/log 这两个目录，所以接下来还要创建这两个目录：

```
[root@node1 conf]# mkdir -p /tmp/zookeeper/data
[root@node1 conf]# mkdir -p /tmp/zookeeper/log
```

第四步，在${dataDir}目录（也就是/tmp/zookeeper/data）下创建一个 myid 文件，并写入一个数值，比如0。myid 文件里存放的是服务器的编号。

第五步，启动 Zookeeper 服务，详情如下：

```
[root@node1 conf]# zkServer.sh start
JMX enabled by default
Using config: /opt/zookeeper-3.4.6/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
```

可以通过 zkServer.sh status 命令查看 Zookeeper 服务状态，示例如下：

```
[root@node1 ]# zkServer.sh status
JMX enabled by default
Using config: /opt/zookeeper-3.4.12/bin/../conf/zoo.cfg
Mode: Standalone
```

以上是关于 ZooKeeper 单机模式的安装与配置，一般在生产环境中使用的都是集群模式，集群模式的配置也比较简单，相比单机模式而言只需要修改一些配置即可。下面以3台机器为例来配置一个 ZooKeeper 集群。首先在这3台机器的 /etc/hosts 文件中添加3台集群的IP地址与机器域名的映射，示例如下（3个IP地址分别对应3台机器）：

```
192.168.0.2 node1
192.168.0.3 node2
192.168.0.4 node3
```

然后在这3台机器的 zoo.cfg 文件中添加以下配置：

```
server.0=192.168.0.2:2888:3888
server.1=192.168.0.3:2888:3888
server.2=192.168.0.4:2888:3888
```

为了便于讲解上面的配置，这里抽象出一个公式，即 server.A=B:C:D。其中A是一个数字，代表服务器的编号，就是前面所说的 myid 文件里面的值。集群中每台服务器的编号都必须唯一，所以要保证每台服务器中的 myid 文件中的值不同。B代表服务器的IP地址。C表示服务器与集群中的 leader 服务器交换信息的端口。D表示选举时服务器相互通信的端口。如此，集群模式的配置就告一段落，可以在这3台机器上各自执行 zkServer.sh start 命令来启动服务。

### <a name="15">3. Kafka的安装与配置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在安装完 JDK 和 ZooKeeper 之后，就可以执行 Kafka broker 的安装了，首先也是从官网中下载安装包，示例中选用的安装包是 kafka_2.11-2.0.0.tgz，将其复制至/opt 目录下并进行解压缩，示例如下：

```
[root@node1 opt]# ll kafka_2.11-2.0.0.tgz 
-rw-r--r-- 1 root root 55751827 Jul 31 10:45 kafka_2.11-2.0.0.tgz
[root@node1 opt]# tar zxvf kafka_2.11-2.0.0.tgz
# <a name="16">解压之后当前/opt目录下生成一个名为kafka_2.11-2.0.0的文件夹</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[root@node1 opt]# cd kafka_2.11-2.0.0
[root@node1 kafka_2.11-2.0.0]#
# <a name="17">Kafka的根目录$KAFKA_HOME即为/opt/kafka_2.11-2.0.0，可以将Kafka_HOME添加到/etc/profile文件中，具体做法可以参考前面JDK和ZooKeeper的安装示例</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```

接下来需要修改 broker 的配置文件 $KAFKA_HOME/conf/server.properties。主要关注以下几个配置参数即可：

```
# <a name="18">broker的编号，如果集群中有多个broker，则每个broker的编号需要设置的不同</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
broker.id=0
# <a name="19">broker对外提供的服务入口地址</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
listeners=PLAINTEXT://localhost:9092
# <a name="20">存放消息日志文件的地址</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
log.dirs=/tmp/kafka-logs
# <a name="21">Kafka所需的ZooKeeper集群地址，为了方便演示，我们假设Kafka和ZooKeeper都安装在本机</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
zookeeper.connect=localhost:2181/kafka
```

如果是单机模式，那么修改完上述配置参数之后就可以启动服务。如果是集群模式，那么只需要对单机模式的配置文件做相应的修改即可：确保集群中每个 broker 的 broker.id 配置参数的值不一样，以及 listeners 配置参数也需要修改为与 broker 对应的IP地址或域名，之后就可以各自启动服务。注意，在启动 Kafka 服务之前同样需要确保 zookeeper.connect 参数所配置的 ZooKeeper 服务已经正确启动。

启动 Kafka 服务的方式比较简单，在$KAFKA_HOME 目录下执行下面的命令即可：

```
bin/kafka-server-start.sh config/server.properties
```

如果要在后台运行 Kafka 服务，那么可以在启动命令中加入 -daemon 参数或&字符，示例如下：

```
bin/kafka-server-start.sh –daemon config/server.properties
# <a name="22">或者</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
bin/kafka-server-start.sh config/server.properties &
```

可以通过 jps 命令查看 Kafka 服务进程是否已经启动，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# jps -l
23152 sun.tools.jps.Jps
16052 org.apache.zookeeper.server.quorum.QuorumPeerMain
22807 kafka.Kafka  # 这个就是Kafka服务端的进程
```

jps 命令只是用来确认 Kafka 服务的进程已经正常启动。它是否能够正确地对外提供服务，还需要通过发送和消费消息来进行验证，验证的过程可以参考下面的内容。

## <a name="23">生产与消费</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

由第1节的内容可知，生产者将消息发送至 Kafka 的主题中，或者更加确切地说应该是主题的分区中，而消费者也是通过订阅主题从而消费消息的。在演示生产与消费消息之前，需要创建一个主题作为消息的载体。

Kafka 提供了许多实用的脚本工具，存放在 $KAFKA_HOME 的 bin 目录下，其中与主题有关的就是 kafka-topics.sh 脚本，下面我们用它演示创建一个分区数为4、副本因子为3的主题 topic-demo，示例如下（Kafka集群模式下，broker数为3）：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost: 2181/kafka --create --topic topic-demo --replication-factor 3 --partitions 4

Created topic "topic-demo".
```

其中 --zookeeper 指定了 Kafka 所连接的 ZooKeeper 服务地址，--topic 指定了所要创建主题的名称，--replication-factor 指定了副本因子，--partitions 指定了分区个数，--create 是创建主题的动作指令。

还可以通过 --describe 展示主题的更多具体信息，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost: 2181/kafka --describe --topic topic-demo

Topic:topic-demo	PartitionCount:4	ReplicationFactor:3	Configs:
	Topic: topic-demo	Partition: 0	Leader: 2	Replicas: 2,1,0	Isr: 2,1,0
	Topic: topic-demo	Partition: 1	Leader: 0	Replicas: 0,2,1	Isr: 0,2,1
	Topic: topic-demo	Partition: 2	Leader: 1	Replicas: 1,0,2	Isr: 1,0,2
	Topic: topic-demo	Partition: 3	Leader: 2	Replicas: 2,0,1	Isr: 2,0,1
```

创建主题 topic-demo 之后我们再来检测一下 Kafka 集群是否可以正常地发送和消费消息。$KAFKA_HOME/bin 目录下还提供了两个脚本 kafka-console-producer.sh 和 kafka-console- consumer.sh，通过控制台收发消息。首先我们打开一个 shell 终端，通过 kafka-console-consumer.sh 脚本来订阅主题 topic-demo，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic-demo
```

其中 --bootstrap-server 指定了连接的 Kafka 集群地址，--topic 指定了消费者订阅的主题。目前主题 topic-demo 尚未有任何消息存入，所以此脚本还不能消费任何消息。

我们再打开一个 shell 终端，然后使用 kafka-console-producer.sh 脚本发送一条消息“Hello, Kafka!”至主题 topic-demo，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic-demo
>Hello, Kafka!
>
```

其中 --broker-list 指定了连接的 Kafka 集群地址，--topic 指定了发送消息时的主题。示例中的第二行是通过人工键入的方式输入的，按下回车键后会跳到第三行，即“>”字符处。此时原先执行 kafka-console-consumer.sh 脚本的 shell 终端中出现了刚刚输入的消息“Hello, Kafka!”，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic-demo
Hello, Kafka!
```

读者也可以通过输入一些其他自定义的消息来熟悉消息的收发及这两个脚本的用法。不过这两个脚本一般用来做一些测试类的工作，在实际应用中，不会只是简单地使用这两个脚本来做复杂的与业务逻辑相关的消息生产与消费的工作，具体的工作还需要通过编程的手段来实施。下面就以 Kafka 自身提供的 Java 客户端来演示消息的收发，与 Kafka 的 Java 客户端相关的 Maven 依赖如下：

```
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>2.0.0</version>
</dependency>
```

要往 Kafka 中写入消息，首先要创建一个生产者客户端实例并设置一些配置参数，然后构建消息的 ProducerRecord 对象，其中必须包含所要发往的主题及消息的消息体，进而再通过生产者客户端实例将消息发出，最后可以通过 close()方法来关闭生产者客户端实例并回收相应的资源。

具体的示例如代码清单2-1 所示，与脚本演示时一样，示例中仅发送一条内容为“Hello, Kafka!”的消息到主题 topic-demo。

```
//代码清单2-1 生产者客户端示例代码
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerFastStart {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers", brokerList);


        KafkaProducer<String, String> producer =
                new KafkaProducer<>(properties);
        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, "hello, Kafka!");
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}
```

对应的消费消息也比较简单，首先创建一个消费者客户端实例并配置相应的参数，然后订阅主题并消费即可，具体的示例代码如代码清单2-2所示。

```
//代码清单2-2 消费者客户端示例代码
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerFastStart {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.deserializer", 
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", 
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("bootstrap.servers", brokerList);
        //设置消费组的名称，具体的释义可以参见第3章
        properties.put("group.id", groupId);
        //创建一个消费者客户端实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        //订阅主题
        consumer.subscribe(Collections.singletonList(topic));
        //循环消费消息
        while (true) {
            ConsumerRecords<String, String> records = 
                    consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }
    }
}
```

通过这些示例，相信各位读者对 Kafka 应该有了初步的认识。这仅仅是一个开始，要正确、灵活地运用好 Kafka 还需要对它进行深入探索，包括生产者和消费者客户端的使用细节及原理、服务端的使用细节及原理、运维、监控等，每一个方面都等着读者去一一攻破。

## <a name="24">服务端参数配置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

前面的 Kafka 安装与配置的说明中只是简单地表述了几个必要的服务端参数而没有对其进行详细的介绍，并且 Kafka 服务端参数（broker configs）也并非只有这几个。Kafka 服务端还有很多参数配置，涉及使用、调优的各个方面，虽然这些参数在大多数情况下不需要更改，但了解这些参数，以及在特殊应用需求的情况下进行有针对性的调优，可以更好地利用 Kafka 为我们工作。

下面挑选一些重要的服务端参数来做细致的说明，这些参数都配置在$KAFKA_HOME/config/server.properties 文件中。

### <a name="25">1. zookeeper.connect</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

该参数指明 broker 要连接的 ZooKeeper 集群的服务地址（包含端口号），没有默认值，且此参数为必填项。可以配置为 localhost:2181，如果 ZooKeeper 集群中有多个节点，则可以用逗号将每个节点隔开，类似于 localhost1:2181,localhost2:2181,localhost3:2181 这种格式。最佳的实践方式是再加一个 chroot 路径，这样既可以明确指明该 chroot 路径下的节点是为 Kafka 所用的，也可以实现多个 Kafka 集群复用一套 ZooKeeper 集群，这样可以节省更多的硬件资源。包含 chroot 路径的配置类似于 localhost1:2181,localhost2:2181,localhost3:2181/kafka 这种，如果不指定 chroot，那么默认使用 ZooKeeper 的根路径。

### <a name="26">2. listeners</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

该参数指明 broker 监听客户端连接的地址列表，即为客户端要连接 broker 的入口地址列表，配置格式为 protocol1://hostname1:port1,protocol2://hostname2:port2，其中 protocol 代表协议类型，Kafka 当前支持的协议类型有 PLAINTEXT、SSL、SASL_SSL 等，如果未开启安全认证，则使用简单的 PLAINTEXT 即可。hostname 代表主机名，port 代表服务端口，此参数的默认值为 null。比如此参数配置为 PLAINTEXT://198.162.0.2:9092，如果有多个地址，则中间以逗号隔开。如果不指定主机名，则表示绑定默认网卡，注意有可能会绑定到127.0.0.1，这样无法对外提供服务，所以主机名最好不要为空；如果主机名是0.0.0.0，则表示绑定所有的网卡。

与此参数关联的还有 advertised.listeners，作用和 listeners 类似，默认值也为 null。不过 advertised.listeners 主要用于 IaaS（Infrastructure as a Service）环境，比如公有云上的机器通常配备有多块网卡，即包含私网网卡和公网网卡，对于这种情况而言，可以设置 advertised.listeners 参数绑定公网IP供外部客户端使用，而配置 listeners 参数来绑定私网IP地址供 broker 间通信使用。

### <a name="27">3. broker.id</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

该参数用来指定 Kafka 集群中 broker 的唯一标识，默认值为-1。如果没有设置，那么 Kafka 会自动生成一个。这个参数还和 meta.properties 文件及服务端参数 broker.id.generation. enable 和 reserved.broker.max.id 有关，相关深度解析可以参考[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)的相关内容。

### <a name="28">4. log.dir和log.dirs</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka 把所有的消息都保存在磁盘上，而这两个参数用来配置 Kafka 日志文件存放的根目录。一般情况下，log.dir 用来配置单个根目录，而 log.dirs 用来配置多个根目录（以逗号分隔），但是 Kafka 并没有对此做强制性限制，也就是说，log.dir 和 log.dirs 都可以用来配置单个或多个根目录。log.dirs 的优先级比 log.dir 高，但是如果没有配置 log.dirs，则会以 log.dir 配置为准。默认情况下只配置了 log.dir 参数，其默认值为 /tmp/kafka-logs。

### <a name="29">5. message.max.bytes</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

该参数用来指定 broker 所能接收消息的最大值，默认值为1000012（B），约等于976.6KB。如果 Producer 发送的消息大于这个参数所设置的值，那么（Producer）就会报出 RecordTooLargeException 的异常。如果需要修改这个参数，那么还要考虑 max.request.size（客户端参数）、max.message.bytes（topic端参数）等参数的影响。为了避免修改此参数而引起级联的影响，建议在修改此参数之前考虑分拆消息的可行性。

还有一些服务端参数在本节没有提及，这些参数同样非常重要，它们需要用单独的章节或者场景来描述，比如 unclean.leader.election.enable、log.segment.bytes 等参数都会在后面的章节中提及。

## <a name="30">总结</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

通过前面这2个章节的内容介绍，相信读者对 Kafka 已经有了初步的了解，接下来我们就可以正式开始研究如何正确、有效地使用 Kafka 了。



## <a name="31">生产者-客户端开发</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

从编程的角度而言，生产者就是负责向 Kafka 发送消息的应用程序。在 Kafka 的历史变迁中，一共有两个大版本的生产者客户端：第一个是于 Kafka 开源之初使用 Scala 语言编写的客户端，我们可以称之为旧生产者客户端（Old Producer）或 Scala 版生产者客户端；第二个是从 Kafka 0.9.x 版本开始推出的使用 Java 语言编写的客户端，我们可以称之为新生产者客户端（New Producer）或 Java 版生产者客户端，它弥补了旧版客户端中存在的诸多设计缺陷。

虽然 Kafka 是用 Java/Scala 语言编写的，但这并不妨碍它对于多语言的支持，在 Kafka 官网中，“[CLIENTS](https://link.juejin.cn/?target=https%3A%2F%2Fcwiki.apache.org%2Fconfluence%2Fdisplay%2FKAFKA%2FClients)”的入口提供了一份多语言的支持列表，其中包括常用的 C/C++、Python、Go 等语言，不过这些其他类语言的客户端并非由 Kafka 社区维护，如果使用则需要另行下载。本章主要针对现下流行的新生产者（Java 语言编写的）客户端做详细介绍，而旧生产者客户端已被淘汰，故不再做相应的介绍了。

### <a name="32">客户端开发</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

一个正常的生产逻辑需要具备以下几个步骤：

1. 配置生产者客户端参数及创建相应的生产者实例。
2. 构建待发送的消息。
3. 发送消息。
4. 关闭生产者实例。

代码清单2-1中已经简单对生产者客户端的编码做了一个基本演示，本节对其修改以做具体的分析，如代码清单3-1所示。

```
//代码清单3-1 生产者客户端示例代码
public class KafkaProducerAnalysis {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";

    public static Properties initConfig(){
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("client.id", "producer.client.id.demo");
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, "Hello, Kafka!");
        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

相比代码清单2-1而言，这里仅仅是让编码的逻辑显得更加“正统”一些，也更加方便下面内容的陈述。

这里有必要单独说明的是构建的消息对象 ProducerRecord，它并不是单纯意义上的消息，它包含了多个属性，原本需要发送的与业务相关的消息体只是其中的一个 value 属性，比如“Hello, Kafka!”只是 ProducerRecord 对象中的一个属性。ProducerRecord 类的定义如下（只截取成员变量）：

```
public class ProducerRecord<K, V> {
    private final String topic; //主题
    private final Integer partition; //分区号
    private final Headers headers; //消息头部
    private final K key; //键
    private final V value; //值
    private final Long timestamp; //消息的时间戳
    //省略其他成员方法和构造方法
}
```

其中 topic 和 partition 字段分别代表消息要发往的主题和分区号。headers 字段是消息的头部，Kafka 0.11.x 版本才引入这个属性，它大多用来设定一些与应用相关的信息，如无需要也可以不用设置。key 是用来指定消息的键，它不仅是消息的附加信息，还可以用来计算分区号进而可以让消息发往特定的分区。前面提及消息以主题为单位进行归类，而这个 key 可以让消息再进行二次归类，同一个 key 的消息会被划分到同一个分区中，详情参见第4节中的分区器。

有 key 的消息还可以支持日志压缩的功能。value 是指消息体，一般不为空，如果为空则表示特定的消息—墓碑消息。timestamp 是指消息的时间戳，它有 CreateTime 和 LogAppendTime 两种类型，前者表示消息创建的时间，后者表示消息追加到日志文件的时间。以上这些深入原理性的东西都会在[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)中呈现给大家。

接下来我们将按照生产逻辑的各个步骤来一一做相应分析。

### <a name="33">必要的参数配置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在创建真正的生产者实例前需要配置相应的参数，比如需要连接的 Kafka 集群地址。参照代码清单3-1中的 initConfig()方法，在 Kafka 生产者客户端 KafkaProducer 中有3个参数是必填的。

- **bootstrap.servers**：该参数用来指定生产者客户端连接 Kafka 集群所需的 broker 地址清单，具体的内容格式为 host1:port1,host2:port2，可以设置一个或多个地址，中间以逗号隔开，此参数的默认值为“”。注意这里并非需要所有的 broker 地址，因为生产者会从给定的 broker 里查找到其他 broker 的信息。不过建议至少要设置两个以上的 broker 地址信息，当其中任意一个宕机时，生产者仍然可以连接到 Kafka 集群上。
- **key.serializer** 和 **value.serializer**：broker 端接收的消息必须以字节数组（byte[]）的形式存在。代码清单3-1中生产者使用的 KafkaProducer<String, String>和 ProducerRecord<String, String> 中的泛型 <String, String> 对应的就是消息中 key 和 value 的类型，生产者客户端使用这种方式可以让代码具有良好的可读性，不过在发往 broker 之前需要将消息中对应的 key 和 value 做相应的序列化操作来转换成字节数组。key.serializer 和 value.serializer 这两个参数分别用来指定 key 和 value 序列化操作的序列化器，这两个参数无默认值。注意这里必须填写序列化器的全限定名，如代码清单3-1中的 org.apache.kafka.common.serialization.StringSerializer，单单指定 StringSerializer 是错误的，更多有关序列化的内容可以参考第4节。

注意到代码清单3-1中的 initConfig() 方法里还设置了一个参数 client.id，这个参数用来设定 KafkaProducer 对应的客户端id，默认值为“”。如果客户端不设置，则 KafkaProducer 会自动生成一个非空字符串，内容形式如“producer-1”、“producer-2”，即字符串“producer-”与数字的拼接。

KafkaProducer 中的参数众多，远非示例 initConfig()方法中的那样只有4个，开发人员可以根据业务应用的实际需求来修改这些参数的默认值，以达到灵活调配的目的。一般情况下，普通开发人员无法记住所有的参数名称，只能有个大致的印象。

在实际使用过程中，诸如“key.serializer”、“max.request.size”、“interceptor.classes”之类的字符串经常由于人为因素而书写错误。为此，我们可以直接使用客户端中的 org.apache.kafka.clients.producer.ProducerConfig 类来做一定程度上的预防措施，每个参数在 ProducerConfig 类中都有对应的名称，以代码清单3-1中的 initConfig()方法为例，引入 ProducerConfig 后的修改结果如下：

```
public static Properties initConfig(){
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
            "org.apache.kafka.common.serialization.StringSerializer");
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
            "org.apache.kafka.common.serialization.StringSerializer");
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
    return props;
}
```

注意到上面的代码中 key.serializer 和 value.serializer 参数对应类的全限定名比较长，也比较容易写错，这里通过 Java 中的技巧来做进一步的改进，相关代码如下：

```
props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
```

如此代码便简洁了许多，同时进一步降低了人为出错的可能性。在配置完参数之后，我们就可以使用它来创建一个生产者实例，示例如下：

```
KafkaProducer<String, String> producer = new KafkaProducer<>(props);
```

KafkaProducer 是线程安全的，可以在多个线程中共享单个 KafkaProducer 实例，也可以将 KafkaProducer 实例进行池化来供其他线程调用。

KafkaProducer 中有多个构造方法，比如在创建 KafkaProducer 实例时并没有设定 key.serializer 和 value.serializer 这两个配置参数，那么就需要在构造方法中添加对应的序列化器，示例如下：

```
KafkaProducer<String, String> producer = new KafkaProducer<>(props,
        new StringSerializer(), new StringSerializer());
```

其内部原理和无序列化器的构造方法一样，不过就实际应用而言，一般都选用 public KafkaProducer(Properties properties)这个构造方法来创建 KafkaProducer 实例。

## <a name="34">消息的发送</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在创建完生产者实例之后，接下来的工作就是构建消息，即创建 ProducerRecord 对象。通过代码清单3-1中我们已经了解了 ProducerRecord 的属性结构，其中 topic 属性和 value 属性是必填项，其余属性是选填项，对应的 ProducerRecord 的构造方法也有多种，参考如下：

```
public ProducerRecord(String topic, Integer partition, Long timestamp, 
                      K key, V value, Iterable<Header> headers)
public ProducerRecord(String topic, Integer partition, Long timestamp,
                      K key, V value)
public ProducerRecord(String topic, Integer partition, K key, V value, 
                      Iterable<Header> headers)
public ProducerRecord(String topic, Integer partition, K key, V value)
public ProducerRecord(String topic, K key, V value)
public ProducerRecord(String topic, V value)
```

代码清单3-1中使用的是最后一种构造方法，也是最简单的一种，这种方式相当于将 ProducerRecord 中除 topic 和 value 外的属性全部值设置为 null。在实际的应用中，还会用到其他构造方法，比如要指定 key，或者添加 headers 等。有可能会遇到这些构造方法都不满足需求的情况，需要自行添加更多的构造方法，比如下面的示例：

```
public ProducerRecord(String topic, Long timestamp,
                      V value, Iterable<Header> headers)
```

注意，针对不同的消息，需要构建不同的 ProducerRecord 对象，在实际应用中创建 ProducerRecord 对象是一个非常频繁的动作。

创建生产者实例和构建消息之后，就可以开始发送消息了。发送消息主要有三种模式：发后即忘（fire-and-forget）、同步（sync）及异步（async）。

代码清单3-1中的这种发送方式就是发后即忘，它只管往 Kafka 中发送消息而并不关心消息是否正确到达。在大多数情况下，这种发送方式没有什么问题，不过在某些时候（比如发生不可重试异常时）会造成消息的丢失。这种发送方式的性能最高，可靠性也最差。

KafkaProducer 的 send()方法并非是 void 类型，而是 Future类型，send()方法有2个重载方法，具体定义如下：

```
public Future<RecordMetadata> send(ProducerRecord<K, V> record)
public Future<RecordMetadata> send(ProducerRecord<K, V> record, 
                                   Callback callback)
```

要实现同步的发送方式，可以利用返回的 Future 对象实现，示例如下：

```
try {
    producer.send(record).get();
} catch (ExecutionException | InterruptedException e) {
    e.printStackTrace();
}
```

实际上 send() 方法本身就是异步的，send() 方法返回的 Future 对象可以使调用方稍后获得发送的结果。示例中在执行 send() 方法之后直接链式调用了 get() 方法来阻塞等待 Kafka 的响应，直到消息发送成功，或者发生异常。如果发生异常，那么就需要捕获异常并交由外层逻辑处理。

也可以在执行完 send() 方法之后不直接调用 get() 方法，比如下面的一种同步发送方式的实现：

```
try {
    Future<RecordMetadata> future = producer.send(record);
    RecordMetadata metadata = future.get();
    System.out.println(metadata.topic() + "-" +
            metadata.partition() + ":" + metadata.offset());
} catch (ExecutionException | InterruptedException e) {
    e.printStackTrace();
}
```

这样可以获取一个 RecordMetadata 对象，在 RecordMetadata 对象里包含了消息的一些元数据信息，比如当前消息的主题、分区号、分区中的偏移量（offset）、时间戳等。如果在应用代码中需要这些信息，则可以使用这个方式。如果不需要，则直接采用 producer.send(record).get() 的方式更省事。

Future 表示一个任务的生命周期，并提供了相应的方法来判断任务是否已经完成或取消，以及获取任务的结果和取消任务等。既然 KafkaProducer.send() 方法的返回值是一个 Future 类型的对象，那么完全可以用 Java 语言层面的技巧来丰富应用的实现，比如使用 Future 中的 get(long timeout, TimeUnit unit) 方法实现可超时的阻塞。

KafkaProducer 中一般会发生两种类型的异常：可重试的异常和不可重试的异常。常见的可重试异常有：NetworkException、LeaderNotAvailableException、UnknownTopicOrPartitionException、NotEnoughReplicasException、NotCoordinatorException 等。比如 NetworkException 表示网络异常，这个有可能是由于网络瞬时故障而导致的异常，可以通过重试解决；又比如 LeaderNotAvailableException 表示分区的 leader 副本不可用，这个异常通常发生在 leader 副本下线而新的 leader 副本选举完成之前，重试之后可以重新恢复。不可重试的异常，比如第2节中提及的 RecordTooLargeException 异常，暗示了所发送的消息太大，KafkaProducer 对此不会进行任何重试，直接抛出异常。

对于可重试的异常，如果配置了 retries 参数，那么只要在规定的重试次数内自行恢复了，就不会抛出异常。retries 参数的默认值为0，配置方式参考如下：

```
props.put(ProducerConfig.RETRIES_CONFIG, 10);
```

示例中配置了10次重试。如果重试了10次之后还没有恢复，那么仍会抛出异常，进而发送的外层逻辑就要处理这些异常了。

同步发送的方式可靠性高，要么消息被发送成功，要么发生异常。如果发生异常，则可以捕获并进行相应的处理，而不会像“发后即忘”的方式直接造成消息的丢失。不过同步发送的方式的性能会差很多，需要阻塞等待一条消息发送完之后才能发送下一条。

我们再来了解一下异步发送的方式，一般是在 send() 方法里指定一个 Callback 的回调函数，Kafka 在返回响应时调用该函数来实现异步的发送确认。

有读者或许会有疑问，send() 方法的返回值类型就是 Future，而 Future 本身就可以用作异步的逻辑处理。这样做不是不行，只不过 Future 里的 get() 方法在何时调用，以及怎么调用都是需要面对的问题，消息不停地发送，那么诸多消息对应的 Future 对象的处理难免会引起代码处理逻辑的混乱。使用 Callback 的方式非常简洁明了，Kafka 有响应时就会回调，要么发送成功，要么抛出异常。

异步发送方式的示例如下：

```
producer.send(record, new Callback() {
    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            exception.printStackTrace();
        } else {
            System.out.println(metadata.topic() + "-" +
                    metadata.partition() + ":" + metadata.offset());
        }
    }
});
```

示例代码中遇到异常时（exception!=null）只是做了简单的打印操作，在实际应用中应该使用更加稳妥的方式来处理，比如可以将异常记录以便日后分析，也可以做一定的处理来进行消息重发。onCompletion() 方法的两个参数是互斥的，消息发送成功时，metadata 不为 null 而 exception 为 null；消息发送异常时，metadata 为 null 而 exception 不为 null。

```
producer.send(record1, callback1);
producer.send(record2, callback2);
```

对于同一个分区而言，如果消息 record1 于 record2 之前先发送（参考上面的示例代码），那么 KafkaProducer 就可以保证对应的 callback1 在 callback2 之前调用，也就是说，回调函数的调用也可以保证分区有序。

通常，一个 KafkaProducer 不会只负责发送单条消息，更多的是发送多条消息，在发送完这些消息之后，需要调用 KafkaProducer 的 close() 方法来回收资源。下面的示例中发送了100条消息，之后就调用了 close() 方法来回收所占用的资源：

```
int i = 0;
while (i < 100) {
    ProducerRecord<String, String> record =
            new ProducerRecord<>(topic, "msg"+i++);
    try {
        producer.send(record).get();
    } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
    }
}
producer.close();
```

close() 方法会阻塞等待之前所有的发送请求完成后再关闭 KafkaProducer。与此同时，KafkaProducer 还提供了一个带超时时间的 close() 方法，具体定义如下：

```
public void close(long timeout, TimeUnit timeUnit)
```

如果调用了带超时时间 timeout 的 close() 方法，那么只会在等待 timeout 时间内来完成所有尚未完成的请求处理，然后强行退出。在实际应用中，一般使用的都是无参的 close() 方法。



## <a name="35">序列化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

生产者需要用序列化器（Serializer）把对象转换成字节数组才能通过网络发送给 Kafka。而在对侧，消费者需要用反序列化器（Deserializer）把从 Kafka 中收到的字节数组转换成相应的对象。在代码清单3-1中，为了方便，消息的 key 和 value 都使用了字符串，对应程序中的序列化器也使用了客户端自带的 org.apache.kafka.common.serialization.StringSerializer，除了用于 String 类型的序列化器，还有 ByteArray、ByteBuffer、Bytes、Double、Integer、Long 这几种类型，它们都实现了 org.apache.kafka.common.serialization.Serializer 接口，此接口有3个方法：

```
public void configure(Map<String, ?> configs, boolean isKey)
public byte[] serialize(String topic, T data)
public void close()
```

configure() 方法用来配置当前类，serialize() 方法用来执行序列化操作。而 close() 方法用来关闭当前的序列化器，一般情况下 close() 是一个空方法，如果实现了此方法，则必须确保此方法的幂等性，因为这个方法很可能会被 KafkaProducer 调用多次。

生产者使用的序列化器和消费者使用的反序列化器是需要一一对应的，如果生产者使用了某种序列化器，比如 StringSerializer，而消费者使用了另一种序列化器，比如 IntegerSerializer，那么是无法解析出想要的数据的。本节讨论的都是与生产者相关的，对于与消费者相关的反序列化器的内容请参见第9节。

下面就以 StringSerializer 为例来看看 Serializer 接口中的3个方法的使用方法，StringSerializer 类的具体实现如代码清单4-1所示。

```
//代码清单4-1 StringSerializer的代码实现
public class StringSerializer implements Serializer<String> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.serializer.encoding" :
                "value.serializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null)
            encodingValue = configs.get("serializer.encoding");
        if (encodingValue != null && encodingValue instanceof String)
            encoding = (String) encodingValue;
    }

    @Override
    public byte[] serialize(String topic, String data) {
        try {
            if (data == null)
                return null;
            else
                return data.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("Error when serializing " +
                    "string to byte[] due to unsupported encoding " + encoding);
        }
    }

    @Override
    public void close() {
        // nothing to do
    }
}
```

首先是 configure() 方法，这个方法是在创建 KafkaProducer 实例的时候调用的，主要用来确定编码类型，不过一般客户端对于 key.serializer.encoding、value.serializer. encoding 和 serializer.encoding 这几个参数都不会配置，在 KafkaProducer 的参数集合（ProducerConfig）里也没有这几个参数（它们可以看作用户自定义的参数），所以一般情况下 encoding 的值就为默认的“UTF-8”。serialize() 方法非常直观，就是将 String 类型转为 byte[] 类型。

如果 Kafka 客户端提供的几种序列化器都无法满足应用需求，则可以选择使用如 Avro、JSON、Thrift、ProtoBuf 和 Protostuff 等通用的序列化工具来实现，或者使用自定义类型的序列化器来实现。下面就以一个简单的例子来介绍自定义类型的使用方法。

假设我们要发送的消息都是 Company 对象，这个 Company 的定义很简单，只有名称 name 和地址 address，示例代码参考如下（为了构建方便，示例中使用了 lombok 工具）：

```
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    private String name;
    private String address;
}
```

下面我们再来看一下 Company 对应的序列化器 CompanySerializer，示例代码如代码清单4-2所示。

```
//代码清单4-2 自定义的序列化器CompanySerializer
public class CompanySerializer implements Serializer<Company> {
    @Override
    public void configure(Map configs, boolean isKey) {}

    @Override
    public byte[] serialize(String topic, Company data) {
        if (data == null) {
            return null;
        }
        byte[] name, address;
        try {
            if (data.getName() != null) {
                name = data.getName().getBytes("UTF-8");
            } else {
                name = new byte[0];
            }
            if (data.getAddress() != null) {
                address = data.getAddress().getBytes("UTF-8");
            } else {
                address = new byte[0];
            }
            ByteBuffer buffer = ByteBuffer.
                    allocate(4+4+name.length + address.length);
            buffer.putInt(name.length);
            buffer.put(name);
            buffer.putInt(address.length);
            buffer.put(address);
            return buffer.array();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public void close() {}
}
```

上面的这段代码的逻辑很简单，configure() 和close() 方法也都为空。与此对应的反序列化器 CompanyDeserializer 的详细实现参见第9节。

如何使用自定义的序列化器 CompanySerializer 呢？只需将 KafkaProducer 的 value.serializer 参数设置为 CompanySerializer 类的全限定名即可。假如我们要发送一个 Company 对象到 Kafka，关键代码如代码清单4-3所示。

```
//代码清单4-3 自定义序列化器使用示例
Properties properties = new Properties();
properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        CompanySerializer.class.getName());
properties.put("bootstrap.servers", brokerList);

KafkaProducer<String, Company> producer =
        new KafkaProducer<>(properties);
Company company = Company.builder().name("hiddenkafka")
        .address("China").build();
ProducerRecord<String, Company> record =
        new ProducerRecord<>(topic, company);
producer.send(record).get();
```

注意，示例中消息的 key 对应的序列化器还是 StringSerializer，这个并没有改动。其实 key.serializer 和 value.serializer 并没有太大的区别，读者可以自行修改 key 对应的序列化器，看看会不会有不一样的效果。

# <a name="36">分区器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

消息在通过 send() 方法发往 broker 的过程中，有可能需要经过拦截器（Interceptor）、序列化器（Serializer）和分区器（Partitioner）的一系列作用之后才能被真正地发往 broker。拦截器（下一章会详细介绍）一般不是必需的，而序列化器是必需的。消息经过序列化之后就需要确定它发往的分区，如果消息 ProducerRecord 中指定了 partition 字段，那么就不需要分区器的作用，因为 partition 代表的就是所要发往的分区号。

如果消息 ProducerRecord 中没有指定 partition 字段，那么就需要依赖分区器，根据 key 这个字段来计算 partition 的值。分区器的作用就是为消息分配分区。

Kafka 中提供的默认分区器是 org.apache.kafka.clients.producer.internals.DefaultPartitioner，它实现了 org.apache.kafka.clients.producer.Partitioner 接口，这个接口中定义了2个方法，具体如下所示。

```
public int partition(String topic, Object key, byte[] keyBytes, 
                     Object value, byte[] valueBytes, Cluster cluster);
public void close();
```

其中 partition() 方法用来计算分区号，返回值为 int 类型。partition() 方法中的参数分别表示主题、键、序列化后的键、值、序列化后的值，以及集群的元数据信息，通过这些信息可以实现功能丰富的分区器。close() 方法在关闭分区器的时候用来回收一些资源。

Partitioner 接口还有一个父接口 org.apache.kafka.common.Configurable，这个接口中只有一个方法：

```
void configure(Map<String, ?> configs);
```

Configurable 接口中的 configure() 方法主要用来获取配置信息及初始化数据。

在默认分区器 DefaultPartitioner 的实现中，close() 是空方法，而在 partition() 方法中定义了主要的分区分配逻辑。如果 key 不为 null，那么默认的分区器会对 key 进行哈希（采用 MurmurHash2 算法，具备高运算性能及低碰撞率），最终根据得到的哈希值来计算分区号，拥有相同 key 的消息会被写入同一个分区。如果 key 为 null，那么消息将会以轮询的方式发往主题内的各个可用分区。

> 注意：如果 key 不为 null，那么计算得到的分区号会是所有分区中的任意一个；如果 key 为 null 并且有可用分区时，那么计算得到的分区号仅为可用分区中的任意一个，注意两者之间的差别。

在不改变主题分区数量的情况下，key 与分区之间的映射可以保持不变。不过，一旦主题中增加了分区，那么就难以保证 key 与分区之间的映射关系了。

除了使用 Kafka 提供的默认分区器进行分区分配，还可以使用自定义的分区器，只需同 DefaultPartitioner 一样实现 Partitioner 接口即可。默认的分区器在 key 为 null 时不会选择非可用的分区，我们可以通过自定义的分区器 DemoPartitioner 来打破这一限制，具体的实现可以参考下面的示例代码，如代码清单4-4所示。

```
//代码清单4-4 自定义分区器实现
public class DemoPartitioner implements Partitioner {
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public int partition(String topic, Object key, byte[] keyBytes,
                         Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        if (null == keyBytes) {
            return counter.getAndIncrement() % numPartitions;
        }else
            return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
    }

    @Override public void close() {}

    @Override public void configure(Map<String, ?> configs) {}
}
```

实现自定义的 DemoPartitioner 类之后，需要通过配置参数 partitioner.class 来显式指定这个分区器。示例如下：

```
props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,
        DemoPartitioner.class.getName());
```

这个自定义分区器的实现比较简单，读者也可以根据自身业务的需求来灵活实现分配分区的计算方式，比如一般大型电商都有多个仓库，可以将仓库的名称或 ID 作为 key 来灵活地记录商品信息。

# <a name="37">生产者拦截器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

拦截器（Interceptor）是早在 Kafka 0.10.0.0 中就已经引入的一个功能，Kafka 一共有两种拦截器：生产者拦截器和消费者拦截器。本节主要讲述生产者拦截器的相关内容，有关消费者拦截器的具体细节请参考13节。

生产者拦截器既可以用来在消息发送前做一些准备工作，比如按照某个规则过滤不符合要求的消息、修改消息的内容等，也可以用来在发送回调逻辑前做一些定制化的需求，比如统计类工作。

生产者拦截器的使用也很方便，主要是自定义实现 org.apache.kafka.clients.producer. ProducerInterceptor 接口。ProducerInterceptor 接口中包含3个方法：

```
public ProducerRecord<K, V> onSend(ProducerRecord<K, V> record);
public void onAcknowledgement(RecordMetadata metadata, Exception exception);
public void close();
```

KafkaProducer 在将消息序列化和计算分区之前会调用生产者拦截器的 onSend() 方法来对消息进行相应的定制化操作。一般来说最好不要修改消息 ProducerRecord 的 topic、key 和 partition 等信息，如果要修改，则需确保对其有准确的判断，否则会与预想的效果出现偏差。比如修改 key 不仅会影响分区的计算，同样会影响 broker 端日志压缩（Log Compaction）的功能。

KafkaProducer 会在消息被应答（Acknowledgement）之前或消息发送失败时调用生产者拦截器的 onAcknowledgement() 方法，优先于用户设定的 Callback 之前执行。这个方法运行在 Producer 的I/O线程中，所以这个方法中实现的代码逻辑越简单越好，否则会影响消息的发送速度。

close() 方法主要用于在关闭拦截器时执行一些资源的清理工作。在这3个方法中抛出的异常都会被捕获并记录到日志中，但并不会再向上传递。

ProducerInterceptor 接口与 Partitioner 接口一样，它也有一个同样的父接口 Configurable，具体的内容可以参见 Partitioner 接口的相关介绍。

下面通过一个示例来演示生产者拦截器的具体用法，ProducerInterceptorPrefix 中通过 onSend() 方法来为每条消息添加一个前缀“prefix1-”，并且通过 onAcknowledgement() 方法来计算发送消息的成功率。ProducerInterceptorPrefix 类的具体实现如代码清单4-5所示。

```
//代码清单4-5生产者拦截器示例
public class ProducerInterceptorPrefix implements 
        ProducerInterceptor<String,String>{
    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;

    @Override
    public ProducerRecord<String, String> onSend(
            ProducerRecord<String, String> record) {
        String modifiedValue = "prefix1-" + record.value();
        return new ProducerRecord<>(record.topic(), 
                record.partition(), record.timestamp(),
                record.key(), modifiedValue, record.headers());
    }

    @Override
    public void onAcknowledgement(
            RecordMetadata recordMetadata, 
            Exception e) {
        if (e == null) {
            sendSuccess++;
        } else {
            sendFailure ++;
        }
    }

    @Override
    public void close() {
        double successRatio = (double)sendSuccess / (sendFailure + sendSuccess);
        System.out.println("[INFO] 发送成功率="
                + String.format("%f", successRatio * 100) + "%");
    }

    @Override
    public void configure(Map<String, ?> map) {}
}
```

实现自定义的 ProducerInterceptorPrefix 之后，需要在 KafkaProducer 的配置参数 interceptor.classes 中指定这个拦截器，此参数的默认值为“”。示例如下：

```
properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
        ProducerInterceptorPrefix.class.getName());
```

然后使用指定了 ProducerInterceptorPrefix 的生产者连续发送10条内容为“kafka”的消息，在发送完之后客户端打印出如下信息：

```
[INFO] 发送成功率=100.000000%
```

如果消费这10条消息，会发现消费了的消息都变成了“prefix1-kafka”，而不是原来的“kafka”。

KafkaProducer 中不仅可以指定一个拦截器，还可以指定多个拦截器以形成拦截链。拦截链会按照 interceptor.classes 参数配置的拦截器的顺序来一一执行（配置的时候，各个拦截器之间使用逗号隔开）。下面我们再添加一个自定义拦截器 ProducerInterceptorPrefixPlus，它只实现了 Interceptor 接口中的 onSend() 方法，主要用来为每条消息添加另一个前缀“prefix2-”，具体实现如下：

```
public ProducerRecord<String, String> onSend(
        ProducerRecord<String, String> record) {
    String modifiedValue = "prefix2-"+record.value() ;
    return new ProducerRecord<>(record.topic(),
            record.partition(), record.timestamp(),
            record.key(), modifiedValue, record.headers());
}
```

接着修改生产者的 interceptor.classes 配置，具体实现如下：

```
properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
        ProducerInterceptorPrefix.class.getName() + ","
                + ProducerInterceptorPrefixPlus.class.getName());
```

此时生产者再连续发送10条内容为“kafka”的消息，那么最终消费者消费到的是10条内容为“prefix2-prefix1-kafka”的消息。如果将 interceptor.classes 配置中的两个拦截器的位置互换：

```
properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,
        ProducerInterceptorPrefixPlus.class.getName() + ","
                + ProducerInterceptorPrefix.class.getName());
```

那么最终消费者消费到的消息为“prefix1-prefix2-kafka”。

如果拦截链中的某个拦截器的执行需要依赖于前一个拦截器的输出，那么就有可能产生“副作用”。设想一下，如果前一个拦截器由于异常而执行失败，那么这个拦截器也就跟着无法继续执行。在拦截链中，如果某个拦截器执行失败，那么下一个拦截器会接着从上一个执行成功的拦截器继续执行。



# <a name="38">生产者客户端原理分析</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在前面的章节中，我们已经了解了 KafkaProducer 的具体使用方法，而本节的内容主要是对 Kafka 生产者客户端的内部原理进行分析，通过了解生产者客户端的整体脉络可以让我们更好地使用它，避免因为一些理解上的偏差而造成使用上的错误。

## <a name="39">整体架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在上一节中提及了消息在真正发往 Kafka 之前，有可能需要经历拦截器（Interceptor）、序列化器（Serializer）和分区器（Partitioner）等一系列的作用，那么在此之后又会发生什么呢？下面我们来看一下生产者客户端的整体架构，如下图所示。



![2-1](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/5/16949dd5a85b5fdf~tplv-t2oaga2asx-watermark.awebp)



整个生产者客户端由两个线程协调运行，这两个线程分别为主线程和 Sender 线程（发送线程）。在主线程中由 KafkaProducer 创建消息，然后通过可能的拦截器、序列化器和分区器的作用之后缓存到消息累加器（RecordAccumulator，也称为消息收集器）中。Sender 线程负责从 RecordAccumulator 中获取消息并将其发送到 Kafka 中。

RecordAccumulator 主要用来缓存消息以便 Sender 线程可以批量发送，进而减少网络传输的资源消耗以提升性能。RecordAccumulator 缓存的大小可以通过生产者客户端参数 buffer.memory 配置，默认值为 33554432B，即32MB。如果生产者发送消息的速度超过发送到服务器的速度，则会导致生产者空间不足，这个时候 KafkaProducer 的 send() 方法调用要么被阻塞，要么抛出异常，这个取决于参数 max.block.ms 的配置，此参数的默认值为60000，即60秒。

主线程中发送过来的消息都会被追加到 RecordAccumulator 的某个双端队列（Deque）中，在 RecordAccumulator 的内部为每个分区都维护了一个双端队列，队列中的内容就是 ProducerBatch，即 Deque。消息写入缓存时，追加到双端队列的尾部；Sender 读取消息时，从双端队列的头部读取。注意 ProducerBatch 不是 ProducerRecord，ProducerBatch 中可以包含一至多个 ProducerRecord。通俗地说，ProducerRecord 是生产者中创建的消息，而 ProducerBatch 是指一个消息批次，ProducerRecord 会被包含在 ProducerBatch 中，这样可以使字节的使用更加紧凑。与此同时，将较小的 ProducerRecord 拼凑成一个较大的 ProducerBatch，也可以减少网络请求的次数以提升整体的吞吐量。ProducerBatch 和消息的具体格式有关，更多的详细内容可以参考[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)。如果生产者客户端需要向很多分区发送消息，则可以将 buffer.memory 参数适当调大以增加整体的吞吐量。

消息在网络上都是以字节（Byte）的形式传输的，在发送之前需要创建一块内存区域来保存对应的消息。在 Kafka 生产者客户端中，通过 java.io.ByteBuffer 实现消息内存的创建和释放。不过频繁的创建和释放是比较耗费资源的，在 RecordAccumulator 的内部还有一个 BufferPool，它主要用来实现 ByteBuffer 的复用，以实现缓存的高效利用。不过 BufferPool 只针对特定大小的 ByteBuffer 进行管理，而其他大小的 ByteBuffer 不会缓存进 BufferPool 中，这个特定的大小由 batch.size 参数来指定，默认值为16384B，即16KB。我们可以适当地调大 batch.size 参数以便多缓存一些消息。

ProducerBatch 的大小和 batch.size 参数也有着密切的关系。当一条消息（ProducerRecord）流入 RecordAccumulator 时，会先寻找与消息分区所对应的双端队列（如果没有则新建），再从这个双端队列的尾部获取一个 ProducerBatch（如果没有则新建），查看 ProducerBatch 中是否还可以写入这个 ProducerRecord，如果可以则写入，如果不可以则需要创建一个新的 ProducerBatch。在新建 ProducerBatch 时评估这条消息的大小是否超过 batch.size 参数的大小，如果不超过，那么就以 batch.size 参数的大小来创建 ProducerBatch，这样在使用完这段内存区域之后，可以通过 BufferPool 的管理来进行复用；如果超过，那么就以评估的大小来创建 ProducerBatch，这段内存区域不会被复用。

Sender 从 RecordAccumulator 中获取缓存的消息之后，会进一步将原本<分区, Deque< ProducerBatch>> 的保存形式转变成 <Node, List< ProducerBatch> 的形式，其中 Node 表示 Kafka 集群的 broker 节点。对于网络连接来说，生产者客户端是与具体的 broker 节点建立的连接，也就是向具体的 broker 节点发送消息，而并不关心消息属于哪一个分区；而对于 KafkaProducer 的应用逻辑而言，我们只关注向哪个分区中发送哪些消息，所以在这里需要做一个应用逻辑层面到网络I/O层面的转换。

在转换成 <Node, List> 的形式之后，Sender 还会进一步封装成 <Node, Request> 的形式，这样就可以将 Request 请求发往各个 Node 了，这里的 Request 是指 Kafka 的各种协议请求，对于消息发送而言就是指具体的 ProduceRequest。

请求在从 Sender 线程发往 Kafka 之前还会保存到 InFlightRequests 中，InFlightRequests 保存对象的具体形式为 Map<NodeId, Deque>，它的主要作用是缓存了已经发出去但还没有收到响应的请求（NodeId 是一个 String 类型，表示节点的 id 编号）。与此同时，InFlightRequests 还提供了许多管理类的方法，并且通过配置参数还可以限制每个连接（也就是客户端与 Node 之间的连接）最多缓存的请求数。这个配置参数为 max.in.flight.requests. per. connection，默认值为5，即每个连接最多只能缓存5个未响应的请求，超过该数值之后就不能再向这个连接发送更多的请求了，除非有缓存的请求收到了响应（Response）。通过比较 Deque 的 size 与这个参数的大小来判断对应的 Node 中是否已经堆积了很多未响应的消息，如果真是如此，那么说明这个 Node 节点负载较大或网络连接有问题，再继续向其发送请求会增大请求超时的可能。

## <a name="40">元数据的更新</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

前面提及的 InFlightRequests 还可以获得 leastLoadedNode，即所有 Node 中负载最小的那一个。这里的负载最小是通过每个 Node 在 InFlightRequests 中还未确认的请求决定的，未确认的请求越多则认为负载越大。对于下图中的 InFlightRequests 来说，图中展示了三个节点 Node0、Node1和Node2，很明显 Node1 的负载最小。也就是说，Node1 为当前的 leastLoadedNode。选择 leastLoadedNode 发送请求可以使它能够尽快发出，避免因网络拥塞等异常而影响整体的进度。leastLoadedNode 的概念可以用于多个应用场合，比如元数据请求、消费者组播协议的交互。



![图10-2 判定leastLoadedNode](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/5/16949e3b5c9456b3~tplv-t2oaga2asx-watermark.awebp)



我们使用如下的方式创建了一条消息 ProducerRecord：

```
ProducerRecord<String, String> record = 
new ProducerRecord<>(topic, "Hello, Kafka!");
```

我们只知道主题的名称，对于其他一些必要的信息却一无所知。KafkaProducer 要将此消息追加到指定主题的某个分区所对应的 leader 副本之前，首先需要知道主题的分区数量，然后经过计算得出（或者直接指定）目标分区，之后 KafkaProducer 需要知道目标分区的 leader 副本所在的 broker 节点的地址、端口等信息才能建立连接，最终才能将消息发送到 Kafka，在这一过程中所需要的信息都属于元数据信息。

在第3节中我们了解了 bootstrap.servers 参数只需要配置部分 broker 节点的地址即可，不需要配置所有 broker 节点的地址，因为客户端可以自己发现其他 broker 节点的地址，这一过程也属于元数据相关的更新操作。与此同时，分区数量及 leader 副本的分布都会动态地变化，客户端也需要动态地捕捉这些变化。

元数据是指 Kafka 集群的元数据，这些元数据具体记录了集群中有哪些主题，这些主题有哪些分区，每个分区的 leader 副本分配在哪个节点上，follower 副本分配在哪些节点上，哪些副本在 AR、ISR 等集合中，集群中有哪些节点，控制器节点又是哪一个等信息。

当客户端中没有需要使用的元数据信息时，比如没有指定的主题信息，或者超过 metadata.max.age.ms 时间没有更新元数据都会引起元数据的更新操作。客户端参数 metadata.max.age.ms 的默认值为300000，即5分钟。元数据的更新操作是在客户端内部进行的，对客户端的外部使用者不可见。当需要更新元数据时，会先挑选出 leastLoadedNode，然后向这个 Node 发送 MetadataRequest 请求来获取具体的元数据信息。这个更新操作是由 Sender 线程发起的，在创建完 MetadataRequest 之后同样会存入 InFlightRequests，之后的步骤就和发送消息时的类似。元数据虽然由 Sender 线程负责更新，但是主线程也需要读取这些信息，这里的数据同步通过 synchronized 和 final 关键字来保障。



## <a name="41">重要的生产者参数</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在 KafkaProducer 中，除了第3节提及的3个默认的客户端参数，大部分的参数都有合理的默认值，一般不需要修改它们。不过了解这些参数可以让我们更合理地使用生产者客户端，其中还有一些重要的参数涉及程序的可用性和性能，如果能够熟练掌握它们，也可以让我们在编写相关的程序时能够更好地进行性能调优与故障排查。下面挑选一些重要的参数进行讲解。

### <a name="42">1. acks</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来指定分区中必须要有多少个副本收到这条消息，之后生产者才会认为这条消息是成功写入的。acks 是生产者客户端中一个非常重要的参数，它涉及消息的可靠性和吞吐量之间的权衡。acks 参数有3种类型的值（都是字符串类型）。

- acks = 1。默认值即为1。生产者发送消息之后，只要分区的 leader 副本成功写入消息，那么它就会收到来自服务端的成功响应。如果消息无法写入 leader 副本，比如在 leader 副本崩溃、重新选举新的 leader 副本的过程中，那么生产者就会收到一个错误的响应，为了避免消息丢失，生产者可以选择重发消息。如果消息写入 leader 副本并返回成功响应给生产者，且在被其他 follower 副本拉取之前 leader 副本崩溃，那么此时消息还是会丢失，因为新选举的 leader 副本中并没有这条对应的消息。acks 设置为1，是消息可靠性和吞吐量之间的折中方案。
- acks = 0。生产者发送消息之后不需要等待任何服务端的响应。如果在消息从发送到写入 Kafka 的过程中出现某些异常，导致 Kafka 并没有收到这条消息，那么生产者也无从得知，消息也就丢失了。在其他配置环境相同的情况下，acks 设置为0可以达到最大的吞吐量。
- acks = -1 或 acks = all。生产者在消息发送之后，需要等待 ISR 中的所有副本都成功写入消息之后才能够收到来自服务端的成功响应。在其他配置环境相同的情况下，acks 设置为 -1（all） 可以达到最强的可靠性。但这并不意味着消息就一定可靠，因为ISR中可能只有 leader 副本，这样就退化成了 acks=1 的情况。要获得更高的消息可靠性需要配合 min.insync.replicas 等参数的联动，消息可靠性分析的具体内容可以参考《图解Kafka之核心原理》。

注意 acks 参数配置的值是一个字符串类型，而不是整数类型。举个例子，将 acks 参数设置为0，需要采用下面这两种形式：

```
properties.put("acks", "0");
# <a name="43">或者</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
properties.put(ProducerConfig.ACKS_CONFIG, "0");
```

而不能配置成下面这种形式：

```
properties.put("acks", 0);
# <a name="44">或者</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
properties.put(ProducerConfig.ACKS_CONFIG, 0);
```

这样会报出如下的异常：

```
org.apache.kafka.common.config.ConfigException: Invalid value 0 for configuration acks: Expected value to be a string, but it was a java.lang.Integer.
```

### <a name="45">2. max.request.size</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来限制生产者客户端能发送的消息的最大值，默认值为1048576B，即1MB。一般情况下，这个默认值就可以满足大多数的应用场景了。

笔者并不建议读者盲目地增大这个参数的配置值，尤其是在对 Kafka 整体脉络没有足够把控的时候。因为这个参数还涉及一些其他参数的联动，比如 broker 端的 message.max.bytes 参数，如果配置错误可能会引起一些不必要的异常。比如将 broker 端的 message.max.bytes 参数配置为10，而 max.request.size 参数配置为20，那么当我们发送一条大小为15B的消息时，生产者客户端就会报出如下的异常：

```
org.apache.kafka.common.errors.RecordTooLargeException: The request included a message larger than the max message size the server will accept.
```

### <a name="46">3. retries和retry.backoff.ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

retries 参数用来配置生产者重试的次数，默认值为0，即在发生异常的时候不进行任何重试动作。消息在从生产者发出到成功写入服务器之前可能发生一些临时性的异常，比如网络抖动、leader 副本的选举等，这种异常往往是可以自行恢复的，生产者可以通过配置 retries 大于0的值，以此通过内部重试来恢复而不是一味地将异常抛给生产者的应用程序。如果重试达到设定的次数，那么生产者就会放弃重试并返回异常。不过并不是所有的异常都是可以通过重试来解决的，比如消息太大，超过 max.request.size 参数配置的值时，这种方式就不可行了。

重试还和另一个参数 retry.backoff.ms 有关，这个参数的默认值为100，它用来设定两次重试之间的时间间隔，避免无效的频繁重试。在配置 retries 和 retry.backoff.ms 之前，最好先估算一下可能的异常恢复时间，这样可以设定总的重试时间大于这个异常恢复时间，以此来避免生产者过早地放弃重试。

Kafka 可以保证同一个分区中的消息是有序的。如果生产者按照一定的顺序发送消息，那么这些消息也会顺序地写入分区，进而消费者也可以按照同样的顺序消费它们。

对于某些应用来说，顺序性非常重要，比如 MySQL 的 binlog 传输，如果出现错误就会造成非常严重的后果。如果将 retries 参数配置为非零值，并且 max.in.flight.requests.per.connection 参数配置为大于1的值，那么就会出现错序的现象：如果第一批次消息写入失败，而第二批次消息写入成功，那么生产者会重试发送第一批次的消息，此时如果第一批次的消息写入成功，那么这两个批次的消息就出现了错序。一般而言，在需要保证消息顺序的场合建议把参数 max.in.flight.requests.per.connection 配置为1，而不是把 retries 配置为0，不过这样也会影响整体的吞吐。

### <a name="47">4. compression.type</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来指定消息的压缩方式，默认值为“none”，即默认情况下，消息不会被压缩。该参数还可以配置为“gzip”“snappy”和“lz4”。对消息进行压缩可以极大地减少网络传输量、降低网络I/O，从而提高整体的性能。消息压缩是一种使用时间换空间的优化方式，如果对时延有一定的要求，则不推荐对消息进行压缩。

### <a name="48">5. connections.max.idle.ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来指定在多久之后关闭闲置的连接，默认值是540000（ms），即9分钟。

### <a name="49">6. linger.ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来指定生产者发送 ProducerBatch 之前等待更多消息（ProducerRecord）加入 ProducerBatch 的时间，默认值为0。生产者客户端会在 ProducerBatch 被填满或等待时间超过 linger.ms 值时发送出去。增大这个参数的值会增加消息的延迟，但是同时能提升一定的吞吐量。这个 linger.ms 参数与 TCP 协议中的 Nagle 算法有异曲同工之妙。

### <a name="50">7. receive.buffer.bytes</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来设置 Socket 接收消息缓冲区（SO_RECBUF）的大小，默认值为32768（B），即32KB。如果设置为-1，则使用操作系统的默认值。如果 Producer 与 Kafka 处于不同的机房，则可以适地调大这个参数值。

### <a name="51">8. send.buffer.bytes</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来设置 Socket 发送消息缓冲区（SO_SNDBUF）的大小，默认值为131072（B），即128KB。与 receive.buffer.bytes 参数一样，如果设置为-1，则使用操作系统的默认值。

### <a name="52">9. request.timeout.ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来配置 Producer 等待请求响应的最长时间，默认值为30000（ms）。请求超时之后可以选择进行重试。注意这个参数需要比 broker 端参数 replica.lag.time.max.ms 的值要大，这样可以减少因客户端重试而引起的消息重复的概率。

还有一些生产者客户端的参数在本节中没有提及，这些参数同样非常重要，它们需要单独的章节或场景来描述。部分参数在前面的章节中已经提及，比如 bootstrap.servers，还有部分参数会在后面的章节中提及，比如 transactional.id。表中罗列了一份详细的参数列表以供读者参阅。

| 参 数 名 称                           | 默 认 值                                                     | 参 数 释 义                                                  |
| ------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| bootstrap.servers                     | “”                                                           | 指定连接 Kafka 集群所需的 broker 地址清单                    |
| key.serializer                        | “”                                                           | 消息中 key 对应的序列化类，需要实现 org.apache.kafka.common.serialization.Serializer 接口 |
| value.serializer                      | “”                                                           | 消息中 value 对应的序列化类，需要实现 org.apache.kafka.common.serialization.Serializer 接口 |
| buffer.memory                         | 33554432（32MB）                                             | 生产者客户端中用于缓存消息的缓冲区大小                       |
| batch.size                            | 16384（16KB）                                                | 用于指定 ProducerBatch 可以复用内存区域的大小                |
| client.id                             | “”                                                           | 用来设定 KafkaProducer 对应的客户端id                        |
| max.block.ms                          | 60000                                                        | 用来控制 KafkaProducer 中 send() 方法和 partitionsFor() 方法的阻塞时间。当生产者的发送缓冲区已满，或者没有可用的元数据时，这些方法就会阻塞 |
| partitioner.class                     | org.apache.kafka.clients.producer.internals.DefaultPartitioner | 用来指定分区器，需要实现 org.apache.kafka. clients.producer.Partitioner 接口 |
| enable.idempotence                    | false                                                        | 是否开启幂等性功能                                           |
| interceptor.classes                   | “”                                                           | 用来设定生产者拦截器，需要实现 org.apache. kafka.clients.producer. ProducerInterceptor 接口。 |
| max.in.flight.requests.per.connection | 5                                                            | 限制每个连接（也就是客户端与 Node 之间的连接）最多缓存的请求数 |
| metadata.max.age.ms                   | 300000（5分钟）                                              | 如果在这个时间内元数据没有更新的话会被强制更新               |
| transactional.id                      | null                                                         | 设置事务id，必须唯一                                         |



到目前为止主要讲述了生产者客户端的具体用法及其整体架构，主要内容包括配置参数的详解、消息的发送方式、序列化器、分区器、拦截器等。在实际应用中，一套封装良好的且灵活易用的客户端可以避免开发人员重复劳动，也提高了开发效率，还可以提高程序的健壮性和可靠性，而 Kafka 的客户端正好包含了这些特质。对于 KafkaProducer 而言，它是线程安全的，我们可以在多线程的环境中复用它，而对于下面要讲解的消费者客户端 KafkaConsumer 而言，它是非线程安全的，因为它具备了状态，具体怎么使用我们不妨继续来了解下面的内容。



## <a name="53">消费者与消费组</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

与生产者对应的是消费者，应用程序可以通过 KafkaConsumer 来订阅主题，并从订阅的主题中拉取消息。不过在使用 KafkaConsumer 消费消息之前需要先了解消费者和消费组的概念，否则无法理解如何使用 KafkaConsumer。本章首先讲解消费者与消费组之间的关系，进而再细致地讲解如何使用 KafkaConsumer。

消费者（Consumer）负责订阅 Kafka 中的主题（Topic），并且从订阅的主题上拉取消息。与其他一些消息中间件不同的是：在 Kafka 的消费理念中还有一层消费组（Consumer Group）的概念，每个消费者都有一个对应的消费组。当消息发布到主题后，只会被投递给订阅它的每个消费组中的一个消费者。



![3-1](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/6/1694ec96a0879f7b~tplv-t2oaga2asx-watermark.awebp)



如上图所示，某个主题中共有4个分区（Partition）：P0、P1、P2、P3。有两个消费组A和B都订阅了这个主题，消费组A中有4个消费者（C0、C1、C2和C3），消费组B中有2个消费者（C4和C5）。按照 Kafka 默认的规则，最后的分配结果是消费组A中的每一个消费者分配到1个分区，消费组B中的每一个消费者分配到2个分区，两个消费组之间互不影响。每个消费者只能消费所分配到的分区中的消息。换言之，每一个分区只能被一个消费组中的一个消费者所消费。



![3-2](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/6/1694ecbfce326098~tplv-t2oaga2asx-watermark.awebp)

我们再来看一下消费组内的消费者个数变化时所对应的分区分配的演变。假设目前某消费组内只有一个消费者C0，订阅了一个主题，这个主题包含7个分区：P0、P1、P2、P3、P4、P5、P6。也就是说，这个消费者C0订阅了7个分区，具体分配情形参考上图。





![3-3](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/6/1694ecebd7b5bacd~tplv-t2oaga2asx-watermark.awebp)

此时消费组内又加入了一个新的消费者C1，按照既定的逻辑，需要将原来消费者C0的部分分区分配给消费者C1消费，如上图所示。消费者C0和C1各自负责消费所分配到的分区，彼此之间并无逻辑上的干扰。





![3-4](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/6/1694ed02abf17545~tplv-t2oaga2asx-watermark.awebp)

紧接着消费组内又加入了一个新的消费者C2，消费者C0、C1和C2按照上图中的方式各自负责消费所分配到的分区。



消费者与消费组这种模型可以让整体的消费能力具备横向伸缩性，我们可以增加（或减少）消费者的个数来提高（或降低）整体的消费能力。对于分区数固定的情况，一味地增加消费者并不会让消费能力一直得到提升，如果消费者过多，出现了消费者的个数大于分区个数的情况，就会有消费者分配不到任何分区。参考下图，一共有8个消费者，7个分区，那么最后的消费者C7由于分配不到任何分区而无法消费任何消息。



![3-5](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/5/11/16aa640e9af89ff3~tplv-t2oaga2asx-watermark.awebp)



以上分配逻辑都是基于默认的分区分配策略进行分析的，可以通过消费者客户端参数 partition.assignment.strategy 来设置消费者与订阅主题之间的分区分配策略。

对于消息中间件而言，一般有两种消息投递模式：点对点（P2P，Point-to-Point）模式和发布/订阅（Pub/Sub）模式。点对点模式是基于队列的，消息生产者发送消息到队列，消息消费者从队列中接收消息。发布订阅模式定义了如何向一个内容节点发布和订阅消息，这个内容节点称为主题（Topic），主题可以认为是消息传递的中介，消息发布者将消息发布到某个主题，而消息订阅者从主题中订阅消息。主题使得消息的订阅者和发布者互相保持独立，不需要进行接触即可保证消息的传递，发布/订阅模式在消息的一对多广播时采用。Kafka 同时支持两种消息投递模式，而这正是得益于消费者与消费组模型的契合：

- 如果所有的消费者都隶属于同一个消费组，那么所有的消息都会被均衡地投递给每一个消费者，即每条消息只会被一个消费者处理，这就相当于点对点模式的应用。
- 如果所有的消费者都隶属于不同的消费组，那么所有的消息都会被广播给所有的消费者，即每条消息会被所有的消费者处理，这就相当于发布/订阅模式的应用。

消费组是一个逻辑上的概念，它将旗下的消费者归为一类，每一个消费者只隶属于一个消费组。每一个消费组都会有一个固定的名称，消费者在进行消费前需要指定其所属消费组的名称，这个可以通过消费者客户端参数 group.id 来配置，默认值为空字符串。

消费者并非逻辑上的概念，它是实际的应用实例，它可以是一个线程，也可以是一个进程。同一个消费组内的消费者既可以部署在同一台机器上，也可以部署在不同的机器上。



# <a name="54">消费者客户端开发</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在了解了消费者与消费组之间的概念之后，我们就可以着手进行消费者客户端的开发了。在 Kafka 的历史中，消费者客户端同生产者客户端一样也经历了两个大版本：第一个是于 Kafka 开源之初使用 Scala 语言编写的客户端，我们可以称之为旧消费者客户端（Old Consumer）或 Scala 消费者客户端；第二个是从 Kafka 0.9.x 版本开始推出的使用 Java 编写的客户端，我们可以称之为新消费者客户端（New Consumer）或 Java 消费者客户端，它弥补了旧客户端中存在的诸多设计缺陷

本节主要介绍目前流行的新消费者（Java 语言编写的）客户端，而旧消费者客户端已被淘汰，故不再做相应的介绍了。

一个正常的消费逻辑需要具备以下几个步骤：

1. 配置消费者客户端参数及创建相应的消费者实例。
2. 订阅主题。
3. 拉取消息并消费。
4. 提交消费位移。
5. 关闭消费者实例。

代码清单2-2中已经简单对消费者客户端的编码做了演示，本节对其稍做修改，如代码清单8-1所示。

```
//代码清单8-1 消费者客户端示例
public class KafkaConsumerAnalysis {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";
    public static final AtomicBoolean isRunning = new AtomicBoolean(true);

    public static Properties initConfig(){
        Properties props = new Properties();
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", groupId);
        props.put("client.id", "consumer.client.id.demo");
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

        try {
            while (isRunning.get()) {
                ConsumerRecords<String, String> records = 
                    consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("topic = " + record.topic() 
                            + ", partition = "+ record.partition() 
                            + ", offset = " + record.offset());
                    System.out.println("key = " + record.key()
                            + ", value = " + record.value());
                    //do something to process record.
                }
            }
        } catch (Exception e) {
            log.error("occur exception ", e);
        } finally {
            consumer.close();
        }
    }
}
```

相比于代码清单2-2而言，修改过后的代码多了一点东西，我们按照消费逻辑的各个步骤来做相应的分析。

## <a name="55">必要的参数配置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在创建真正的消费者实例之前需要做相应的参数配置，比如上一节中的设置消费者所属的消费组的名称、连接地址等。参照代码清单8-1中的 initConfig() 方法，在 Kafka 消费者客户端 KafkaConsumer 中有4个参数是必填的。

- bootstrap.servers：该参数的释义和生产者客户端 KafkaProducer 中的相同，用来指定连接 Kafka 集群所需的 broker 地址清单，具体内容形式为 host1:port1,host2:post，可以设置一个或多个地址，中间用逗号隔开，此参数的默认值为“”。注意这里并非需要设置集群中全部的 broker 地址，消费者会从现有的配置中查找到全部的 Kafka 集群成员。这里设置两个以上的 broker 地址信息，当其中任意一个宕机时，消费者仍然可以连接到 Kafka 集群上。
- group.id：消费者隶属的消费组的名称，默认值为“”。如果设置为空，则会报出异常：Exception in thread "main" org.apache.kafka.common.errors.InvalidGroupIdException: The configured groupId is invalid。一般而言，这个参数需要设置成具有一定的业务意义的名称。
- key.deserializer 和 value.deserializer：与生产者客户端 KafkaProducer 中的 key.serializer和value.serializer 参数对应。消费者从 broker 端获取的消息格式都是字节数组（byte[]）类型，所以需要执行相应的反序列化操作才能还原成原有的对象格式。这两个参数分别用来指定消息中 key 和 value 所需反序列化操作的反序列化器，这两个参数无默认值。注意这里必须填写反序列化器类的全限定名，比如示例中的 org.apache.kafka.common.serialization.StringDeserializer，单单指定 StringDeserializer 是错误的。有关更多的反序列化内容可以参考下一节。

注意到代码清单8-1中的 initConfig() 方法里还设置了一个参数 client.id，这个参数用来设定 KafkaConsumer 对应的客户端id，默认值也为“”。如果客户端不设置，则 KafkaConsumer 会自动生成一个非空字符串，内容形式如“consumer-1”、“consumer-2”，即字符串“consumer-”与数字的拼接。

KafkaConsumer 中的参数众多，远非示例 initConfig() 方法中的那样只有5个，开发人员可以根据业务应用的实际需求来修改这些参数的默认值，以达到灵活调配的目的。一般情况下，普通开发人员无法全部记住所有的参数名称，只能有个大致的印象，在实际使用过程中，诸如“key.deserializer”、“auto.offset.reset”之类的字符串经常由于人为因素而书写错误。为此，我们可以直接使用客户端中的 org.apache.kafka.clients.consumer.ConsumerConfig 类来做一定程度上的预防，每个参数在 ConsumerConfig 类中都有对应的名称，就以代码清单8-1中的 initConfig() 方法为例，引入 ConsumerConfig 后的修改结果如下：

```
    public static Properties initConfig(){
        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "client.id.demo");
        return props;
    }
```

注意到上面的代码中 key.deserializer 和 value.deserializer 参数对应类的全限定名比较长，也比较容易写错，这里通过 Java 中的技巧来做进一步的改进，相关代码如下：

```
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
```

如此代码就简洁了许多，同时也预防了人为出错的可能。在配置完参数之后，我们就可以使用它来创建一个消费者实例：

```
KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
```

本节介绍的 KafkaConsumer 配置相关的内容基本上和介绍 KafkaProducer 配置时的一样，除了配置对应的反序列化器，只多了一个必要的 group.id 参数。

# <a name="56">订阅主题和分区</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在创建好消费者之后，我们就需要为该消费者订阅相关的主题了。一个消费者可以订阅一个或多个主题，代码清单8-1中我们使用 subscribe() 方法订阅了一个主题，对于这个方法而言，既可以以集合的形式订阅多个主题，也可以以正则表达式的形式订阅特定模式的主题。subscribe 的几个重载方法如下：

```
public void subscribe(Collection<String> topics, 
    ConsumerRebalanceListener listener)
public void subscribe(Collection<String> topics)
public void subscribe(Pattern pattern, ConsumerRebalanceListener listener)
public void subscribe(Pattern pattern)
```

对于消费者使用集合的方式（subscribe(Collection)）来订阅主题而言，比较容易理解，订阅了什么主题就消费什么主题中的消息。如果前后两次订阅了不同的主题，那么消费者以最后一次的为准。

```
consumer.subscribe(Arrays.asList(topic1));
consumer.subscribe(Arrays.asList(topic2));
```

上面的示例中，最终消费者订阅的是 topic2，而不是 topic1，也不是 topic1 和 topic2 的并集。

如果消费者采用的是正则表达式的方式（subscribe(Pattern)）订阅，在之后的过程中，如果有人又创建了新的主题，并且主题的名字与正则表达式相匹配，那么这个消费者就可以消费到新添加的主题中的消息。如果应用程序需要消费多个主题，并且可以处理不同的类型，那么这种订阅方式就很有效。在 Kafka 和其他系统之间进行数据复制时，这种正则表达式的方式就显得很常见。正则表达式的方式订阅的示例如下：

```
consumer.subscribe(Pattern.compile("topic-.*"));
```

细心的读者可能观察到在 subscribe 的重载方法中有一个参数类型是 ConsumerRebalance- Listener，这个是用来设置相应的再均衡监听器的，具体的内容可以参考第13节的相关内容。

消费者不仅可以通过 KafkaConsumer.subscribe() 方法订阅主题，还可以直接订阅某些主题的特定分区，在 KafkaConsumer 中还提供了一个 assign() 方法来实现这些功能，此方法的具体定义如下：

```
public void assign(Collection<TopicPartition> partitions)
```

这个方法只接受一个参数 partitions，用来指定需要订阅的分区集合。这里补充说明一下 TopicPartition 类，在 Kafka 的客户端中，它用来表示分区，这个类的部分内容如下所示。

```
public final class TopicPartition implements Serializable {

    private final int partition;
    private final String topic;

    public TopicPartition(String topic, int partition) {
        this.partition = partition;
        this.topic = topic;
    }

    public int partition() {
        return partition;
    }

    public String topic() {
        return topic;
    }
    //省略hashCode()、equals()和toString()方法
}
```

TopicPartition 类只有2个属性：topic 和 partition，分别代表分区所属的主题和自身的分区编号，这个类可以和我们通常所说的主题—分区的概念映射起来。

我们将代码清单8-1中的 subscribe() 方法修改为 assign() 方法，这里只订阅 topic-demo 主题中分区编号为0的分区，相关代码如下：

```
consumer.assign(Arrays.asList(new TopicPartition("topic-demo", 0)));
```

有读者会有疑问：如果我们事先并不知道主题中有多少个分区怎么办？KafkaConsumer 中的 partitionsFor() 方法可以用来查询指定主题的元数据信息，partitionsFor() 方法的具体定义如下：

```
public List<PartitionInfo> partitionsFor(String topic)
```

其中 PartitionInfo 类型即为主题的分区元数据信息，此类的主要结构如下：

```
public class PartitionInfo {
    private final String topic;
    private final int partition;
    private final Node leader;
    private final Node[] replicas;
    private final Node[] inSyncReplicas;
    private final Node[] offlineReplicas;
	    //这里省略了构造函数、属性提取、toString等方法
}
```

PartitionInfo 类中的属性 topic 表示主题名称，partition 代表分区编号，leader 代表分区的 leader 副本所在的位置，replicas 代表分区的 AR 集合，inSyncReplicas 代表分区的 ISR 集合，offlineReplicas 代表分区的 OSR 集合。 通过 partitionFor() 方法的协助，我们可以通过 assign() 方法来实现订阅主题（全部分区）的功能，示例参考如下：

```
List<TopicPartition> partitions = new ArrayList<>();
List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
if (partitionInfos != null) {
    for (PartitionInfo tpInfo : partitionInfos) {
        partitions.add(new TopicPartition(tpInfo.topic(), tpInfo.partition()));
    }
}
consumer.assign(partitions);
```

既然有订阅，那么就有取消订阅，可以使用 KafkaConsumer 中的 unsubscribe() 方法来取消主题的订阅。这个方法既可以取消通过 subscribe(Collection) 方式实现的订阅，也可以取消通过 subscribe(Pattern) 方式实现的订阅，还可以取消通过 assign(Collection) 方式实现的订阅。示例代码如下：

```
consumer.unsubscribe();
```

如果将 subscribe(Collection) 或 assign(Collection) 中的集合参数设置为空集合，那么作用等同于 unsubscribe() 方法，下面示例中的三行代码的效果相同：

```
consumer.unsubscribe();
consumer.subscribe(new ArrayList<String>());
consumer.assign(new ArrayList<TopicPartition>());
```

如果没有订阅任何主题或分区，那么再继续执行消费程序的时候会报出 IllegalStateException 异常：

```
java.lang.IllegalStateException: Consumer is not subscribed to any topics or assigned any partitions
```

集合订阅的方式 subscribe(Collection)、正则表达式订阅的方式 subscribe(Pattern) 和指定分区的订阅方式 assign(Collection) 分表代表了三种不同的订阅状态：AUTO_TOPICS、AUTO_PATTERN 和 USER_ASSIGNED（如果没有订阅，那么订阅状态为 NONE）。然而这三种状态是互斥的，在一个消费者中只能使用其中的一种，否则会报出 IllegalStateException 异常：

```
java.lang.IllegalStateException: Subscription to topics, partitions and pattern are mutually exclusive.
```

通过 subscribe() 方法订阅主题具有消费者自动再均衡的功能，在多个消费者的情况下可以根据分区分配策略来自动分配各个消费者与分区的关系。当消费组内的消费者增加或减少时，分区分配关系会自动调整，以实现消费负载均衡及故障自动转移。而通过 assign() 方法订阅分区时，是不具备消费者自动均衡的功能的，其实这一点从 assign() 方法的参数中就可以看出端倪，两种类型的 subscribe() 都有 ConsumerRebalanceListener 类型参数的方法，而 assign() 方法却没有。



# <a name="57">反序列化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在第4节中我们讲述了 KafkaProducer 对应的序列化器，那么与此对应的 KafkaConsumer 就会有反序列化器。Kafka 所提供的反序列化器有 ByteBufferDeserializer、ByteArrayDeserializer、BytesDeserializer、DoubleDeserializer、FloatDeserializer、IntegerDeserializer、LongDeserializer、ShortDeserializer、StringDeserializer，它们分别用于 ByteBuffer、ByteArray、Bytes、Double、Float、Integer、Long、Short 及 String 类型的反序列化，这些序列化器也都实现了 Deserializer 接口，与 KafkaProducer 中提及的 Serializer 接口一样，Deserializer 接口也有三个方法。

- public void configure(Map<String, ?> configs, boolean isKey)：用来配置当前类。
- public byte[] deserialize(String topic, byte[] data)：用来执行反序列化。如果 data 为 null，那么处理的时候直接返回 null 而不是抛出一个异常。
- public void close()：用来关闭当前序列化器。

代码清单4-1中描述的是 Kafka 客户端自带的序列化器 StringSerializer 的具体实现，对应的反序列化器 StringDeserializer 的具体代码实现如下：

```
public class StringDeserializer implements Deserializer<String> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.deserializer.encoding" :
                "value.deserializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null)
            encodingValue = configs.get("deserializer.encoding");
        if (encodingValue != null && encodingValue instanceof String)
            encoding = (String) encodingValue;
    }

    @Override
    public String deserialize(String topic, byte[] data) {
        try {
            if (data == null)
                return null;
            else
                return new String(data, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("Error when " +
                    "deserializing byte[] to string due to " +
                    "unsupported encoding " + encoding);
        }
    }

    @Override
    public void close() {
        // nothing to do
    }
}
```

configure() 方法中也有3个参数：key.deserializer.encoding、value.deserializer. encoding 和 deserializer.encoding，用来配置反序列化的编码类型，这3个都是用户自定义的参数类型，在 KafkaConsumer 的参数集合（ConsumerConfig）中并没有它们的身影。一般情况下，也不需要配置这几个参数，如果配置了，则需要和 StringSerializer 中配置的一致。默认情况下，编码类型为“UTF-8”。上面示例代码中的 deserialize() 方法非常直观，就是把 byte[] 类型转换为 String 类型。

在代码清单4-2和代码清单4-3中，我们演示了如何通过自定义的序列化器来序列化自定义的 Company 类，这里我们再来看一看与 CompanySerializer 对应的 CompanyDeserializer 的具体实现：

```
public class CompanyDeserializer implements Deserializer<Company> {
    public void configure(Map<String, ?> configs, boolean isKey) {}

    public Company deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length < 8) {
            throw new SerializationException("Size of data received " +
                    "by DemoDeserializer is shorter than expected!");
        }
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int nameLen, addressLen;
        String name, address;

        nameLen = buffer.getInt();
        byte[] nameBytes = new byte[nameLen];
        buffer.get(nameBytes);
        addressLen = buffer.getInt();
        byte[] addressBytes = new byte[addressLen];
        buffer.get(addressBytes);

        try {
            name = new String(nameBytes, "UTF-8");
            address = new String(addressBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("Error occur when deserializing!");
        }

        return new Company(name,address);
    }

    public void close() {}
}
```

configure() 方法和 close() 方法都是空实现，而 deserializer() 方法就是将字节数组转换成对应 Company 对象。在使用自定义的反序列化器的时候只需要将相应的 value.deserializer 参数配置为 CompanyDeserializer 即可，示例如下：

```
props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        CompanyDeserializer.class.getName());
```

注意如无特殊需要，笔者还是不建议使用自定义的序列化器或反序列化器，因为这样会增加生产者与消费者之间的耦合度，在系统升级换代的时候很容易出错。自定义的类型有一个不得不面对的问题就是 KafkaProducer 和 KafkaConsumer 之间的序列化和反序列化的兼容性。对于 StringSerializer 来说，KafkaConsumer 可以顺其自然地采用 StringDeserializer，不过对于 Company 这种专用类型而言，某个上游应用采用 CompanySerializer 进行序列化之后，下游应用也必须实现对应的 CompanyDeserializer。再者，如果上游的 Company 类型改变，那么下游也需要跟着重新实现一个新的 CompanyDeserializer，后面所面临的难题可想而知。

在实际应用中，在 Kafka 提供的序列化器和反序列化器满足不了应用需求的前提下，推荐使用 Avro、JSON、Thrift、ProtoBuf 或 Protostuff 等通用的序列化工具来包装，以求尽可能实现得更加通用且前后兼容。使用通用的序列化工具也需要实现 Serializer 和 Deserializer 接口，因为 Kafka 客户端的序列化和反序列化入口必须是这两个类型。

本节的最后我们来看一下如何使用通用的序列化工具实现自定义的序列化器和反序列化器的封装。这里挑选了 Protostuff 来做演示，使用的 Protostuff 的 Maven 依赖如下：

```
<dependency>
    <groupId>io.protostuff</groupId>
    <artifactId>protostuff-core</artifactId>
    <version>1.5.4</version>
</dependency>

<dependency>
    <groupId>io.protostuff</groupId>
    <artifactId>protostuff-runtime</artifactId>
    <version>1.5.4</version>
</dependency>
```

为了简化说明，这里只展示出序列化器的 serialize() 方法和 deserialize() 方法，如下所示。

```
    //序列化器ProtostuffSerializer中的serialize()方法
    public byte[] serialize(String topic, Company data) {
        if (data == null) {
            return null;
        }
        Schema schema = (Schema) RuntimeSchema.getSchema(data.getClass());
        LinkedBuffer buffer = 
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(data, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }
    //反序列化器ProtostuffDeserializer中的deserialize()方法
    public Company deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        Schema schema = RuntimeSchema.getSchema(Company.class);
        Company ans = new Company();
        ProtostuffIOUtil.mergeFrom(data, ans, schema);
        return ans;
    }
```

接下来要做的工作就和 CompanyDeserializer 一样，这里就不一一赘述了。读者可以添加或减少 Company 类中的属性，以此查看采用通用序列化工具的前后兼容性的效能。



# <a name="58">消息消费</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka 中的消费是基于拉模式的。消息的消费一般有两种模式：推模式和拉模式。推模式是服务端主动将消息推送给消费者，而拉模式是消费者主动向服务端发起请求来拉取消息。

从代码清单8-1中可以看出，Kafka 中的消息消费是一个不断轮询的过程，消费者所要做的就是重复地调用 poll() 方法，而 poll() 方法返回的是所订阅的主题（分区）上的一组消息。

对于 poll() 方法而言，如果某些分区中没有可供消费的消息，那么此分区对应的消息拉取的结果就为空；如果订阅的所有分区中都没有可供消费的消息，那么 poll() 方法返回为空的消息集合。

poll() 方法的具体定义如下：

```
public ConsumerRecords<K, V> poll(final Duration timeout)
```

注意到 poll() 方法里还有一个超时时间参数 timeout，用来控制 poll() 方法的阻塞时间，在消费者的缓冲区里没有可用数据时会发生阻塞。注意这里 timeout 的类型是 Duration，它是 JDK8 中新增的一个与时间有关的类型。在 Kafka 2.0.0 之前的版本中，timeout 参数的类型为 long，与此类型对应的 poll() 方法的具体定义如下：

```
@Deprecated
public ConsumerRecords<K, V> poll(final long timeout)
```

poll(long) 方法中 timeout 的时间单位固定为毫秒，而 poll(Duration) 方法可以根据 Duration 中的 ofMillis()、ofSeconds()、ofMinutes()、ofHours() 等多种不同的方法指定不同的时间单位，灵活性更强。并且 poll(long) 方法也已经被标注为 @Deprecated，虽然目前还可以使用，如果条件允许的话，还是推荐使用 poll(Duration) 的方式。

timeout 的设置取决于应用程序对响应速度的要求，比如需要在多长时间内将控制权移交给执行轮询的应用线程。可以直接将 timeout 设置为0，这样 poll() 方法会立刻返回，而不管是否已经拉取到了消息。如果应用线程唯一的工作就是从 Kafka 中拉取并消费消息，则可以将这个参数设置为最大值 Long.MAX_VALUE。

消费者消费到的每条消息的类型为 ConsumerRecord（注意与 ConsumerRecords 的区别），这个和生产者发送的消息类型 ProducerRecord 相对应，不过 ConsumerRecord 中的内容更加丰富，具体的结构参考如下代码：

```
public class ConsumerRecord<K, V> {
    private final String topic;
    private final int partition;
    private final long offset;
    private final long timestamp;
    private final TimestampType timestampType;
    private final int serializedKeySize;
    private final int serializedValueSize;
    private final Headers headers;
    private final K key;
    private final V value;
    private volatile Long checksum;
	    //省略若干方法
}
```

topic 和 partition 这两个字段分别代表消息所属主题的名称和所在分区的编号。offset 表示消息在所属分区的偏移量。timestamp 表示时间戳，与此对应的 timestampType 表示时间戳的类型。timestampType 有两种类型：CreateTime和LogAppendTime，分别代表消息创建的时间戳和消息追加到日志的时间戳。headers 表示消息的头部内容。

key 和 value 分别表示消息的键和消息的值，一般业务应用要读取的就是 value，比如使用第4节中的 CompanySerializer 序列化了一个 Company 对象，然后将其存入 Kafka，那么消费到的消息中的 value 就是经过 CompanyDeserializer 反序列化后的 Company 对象。serializedKeySize 和 serializedValueSize 分别表示 key 和 value 经过序列化之后的大小，如果 key 为空，则 serializedKeySize 值为-1。同样，如果 value 为空，则 serializedValueSize 的值也会为-1。checksum 是 CRC32 的校验值。

我们在消费消息的时候可以直接对 ConsumerRecord 中感兴趣的字段进行具体的业务逻辑处理。

poll() 方法的返回值类型是 ConsumerRecords，它用来表示一次拉取操作所获得的消息集，内部包含了若干 ConsumerRecord，它提供了一个 iterator() 方法来循环遍历消息集内部的消息，iterator() 方法的定义如下：

```
public Iterator<ConsumerRecord<K, V>> iterator()
```

在代码清单8-1中，我们使用这种方法来获取消息集中的每一个 ConsumerRecord。除此之外，我们还可以按照分区维度来进行消费，这一点很有用，在手动提交位移时尤为明显，有关位移提交的内容我们会在下一节中详细陈述。ConsumerRecords 类提供了一个 records(TopicPartition) 方法来获取消息集中指定分区的消息，此方法的定义如下：

```
public List<ConsumerRecord<K, V>> records(TopicPartition partition)
```

我们不妨使用这个 records(TopicPartition) 方法来修改一下代码清单8-1中的消费逻辑，主要的示例代码如下：

```
ConsumerRecords<String, String> records = 
         consumer.poll(Duration.ofMillis(1000));
for (TopicPartition tp : records.partitions()) {
    for (ConsumerRecord<String, String> record : records.records(tp)) {
        System.out.println(record.partition()+" : "+record.value());
    }
}
```

上面示例中的 ConsumerRecords.partitions() 方法用来获取消息集中所有分区。在 ConsumerRecords 类中还提供了按照主题维度来进行消费的方法，这个方法是 records(TopicPartition) 的重载方法，具体定义如下：

```
public Iterable<ConsumerRecord<K, V>> records(String topic)
```

ConsumerRecords 类中并没提供与 partitions() 类似的 topics() 方法来查看拉取的消息集中所包含的主题列表，如果要按照主题维度来进行消费，那么只能根据消费者订阅主题时的列表来进行逻辑处理了。下面的示例演示了如何使用 ConsumerRecords 中的 record(String topic) 方法：

```
List<String> topicList = Arrays.asList(topic1, topic2);
consumer.subscribe(topicList);
try {
    while (isRunning.get()) {
        ConsumerRecords<String, String> records = 
                consumer.poll(Duration.ofMillis(1000));
        for (String topic : topicList) {
            for (ConsumerRecord<String, String> record : 
                    records.records(topic)) {
                System.out.println(record.topic() + " : " + record.value());
            }
        }
    }
}finally {
    consumer.close();
}
```

在 ConsumerRecords 类中还提供了几个方法来方便开发人员对消息集进行处理：count() 方法用来计算出消息集中的消息个数，返回类型是 int；isEmpty() 方法用来判断消息集是否为空，返回类型是 boolean；empty() 方法用来获取一个空的消息集，返回类型是 ConsumerRecords<K，V>。

到目前为止，可以简单地认为 poll() 方法只是拉取一下消息而已，但就其内部逻辑而言并不简单，它涉及消费位移、消费者协调器、组协调器、消费者的选举、分区分配的分发、再均衡的逻辑、心跳等内容，在后面的章节中会循序渐进地介绍这些内容。



# <a name="59">位移提交</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

对于 Kafka 中的分区而言，它的每条消息都有唯一的 offset，用来表示消息在分区中对应的位置。对于消费者而言，它也有一个 offset 的概念，消费者使用 offset 来表示消费到分区中某个消息所在的位置。

单词“offset”可以翻译为“偏移量”，也可以翻译为“位移”，读者可能并没有过多地在意这一点：在很多中文资料中都会交叉使用“偏移量”和“位移”这两个词，并没有很严谨地进行区分。笔者对 offset 做了一些区分：对于消息在分区中的位置，我们将 offset 称为“偏移量”；对于消费者消费到的位置，将 offset 称为“位移”，有时候也会更明确地称之为“消费位移”。

做这一区分的目的是让读者在遇到 offset 的时候可以很容易甄别出是在讲分区存储层面的内容，还是在讲消费层面的内容，如此也可以使“偏移量”和“位移”这两个中文词汇具备更加丰富的意义。当然，对于一条消息而言，它的偏移量和消费者消费它时的消费位移是相等的，在某些不需要具体划分的场景下也可以用“消息位置”或直接用“offset”这个单词来进行表述。

在每次调用 poll() 方法时，它返回的是还没有被消费过的消息集（当然这个前提是消息已经存储在 Kafka 中了，并且暂不考虑异常情况的发生），要做到这一点，就需要记录上一次消费时的消费位移。并且这个消费位移必须做持久化保存，而不是单单保存在内存中，否则消费者重启之后就无法知晓之前的消费位移。再考虑一种情况，当有新的消费者加入时，那么必然会有再均衡的动作，对于同一分区而言，它可能在再均衡动作之后分配给新的消费者，如果不持久化保存消费位移，那么这个新的消费者也无法知晓之前的消费位移。

在旧消费者客户端中，消费位移是存储在 ZooKeeper 中的。而在新消费者客户端中，消费位移存储在 Kafka 内部的主题__consumer_offsets 中。这里把将消费位移存储起来（持久化）的动作称为“提交”，消费者在消费完消息之后需要执行消费位移的提交。



![图3-6](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/8/1695ab91ecc0651c~tplv-t2oaga2asx-watermark.awebp)



参考上图中的消费位移，x表示某一次拉取操作中此分区消息的最大偏移量，假设当前消费者已经消费了x位置的消息，那么我们就可以说消费者的消费位移为x，图中也用了 lastConsumedOffset 这个单词来标识它。

不过需要非常明确的是，当前消费者需要提交的消费位移并不是x，而是x+1，对应于上图中的 position，它表示下一条需要拉取的消息的位置。读者可能看过一些相关资料，里面所讲述的内容可能是提交的消费位移就是当前所消费到的消费位移，即提交的是x，这明显是错误的。类似的错误还体现在对 LEO（Log End Offset） 的解读上。在消费者中还有一个 committed offset 的概念，它表示已经提交过的消费位移。

KafkaConsumer 类提供了 position(TopicPartition) 和 committed(TopicPartition) 两个方法来分别获取上面所说的 position 和 committed offset 的值。这两个方法的定义如下所示。

```
public long position(TopicPartition partition)
public OffsetAndMetadata committed(TopicPartition partition)
```

为了论证 lastConsumedOffset、committed offset 和 position 之间的关系，我们使用上面的这两个方法来做相关演示。我们向某个主题中分区编号为0的分区发送若干消息，之后再创建一个消费者去消费其中的消息，等待消费完这些消息之后就同步提交消费位移（调用 commitSync() 方法，这个方法的细节在下面详细介绍），最后我们观察一下 lastConsumedOffset、committed offset 和 position 的值。示例代码如代码清单11-1所示。

```
//代码清单11-1 消费位移的演示
TopicPartition tp = new TopicPartition(topic, 0);
consumer.assign(Arrays.asList(tp));
long lastConsumedOffset = -1;//当前消费到的位移
while (true) {
    ConsumerRecords<String, String> records = consumer.poll(1000);
    if (records.isEmpty()) {
        break;
    }
    List<ConsumerRecord<String, String>> partitionRecords
            = records.records(tp);
    lastConsumedOffset = partitionRecords
            .get(partitionRecords.size() - 1).offset();
    consumer.commitSync();//同步提交消费位移
}
System.out.println("comsumed offset is " + lastConsumedOffset);
OffsetAndMetadata offsetAndMetadata = consumer.committed(tp);
System.out.println("commited offset is " + offsetAndMetadata.offset());
long posititon = consumer.position(tp);
System.out.println("the offset of the next record is " + posititon);
```

示例中先通过 assign() 方法订阅了编号为0的分区，然后消费分区中的消息。示例中还通过调用 ConsumerRecords.isEmpty() 方法来判断是否已经消费完分区中的消息，以此来退出 while(true) 的循环，当然这段逻辑并不严谨，这里只是用来演示，读者切勿在实际开发中效仿。

最终的输出结果如下：

```
comsumed offset is 377
commited offset is 378
the offset of the next record is 378
```

可以看出，消费者消费到此分区消息的最大偏移量为377，对应的消费位移 lastConsumedOffset 也就是377。在消费完之后就执行同步提交，但是最终结果显示所提交的位移 committed offset 为378，并且下一次所要拉取的消息的起始偏移量 position 也为378。在本示例中，position = committed offset = lastConsumedOffset + 1，当然 position 和 committed offset 并不会一直相同，这一点会在下面的示例中有所体现。



![图3-7](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/8/1695abab385613ae~tplv-t2oaga2asx-watermark.awebp)



对于位移提交的具体时机的把握也很有讲究，有可能会造成重复消费和消息丢失的现象。参考上图，当前一次 poll() 操作所拉取的消息集为 [x+2, x+7]，x+2 代表上一次提交的消费位移，说明已经完成了 x+1 之前（包括 x+1 在内）的所有消息的消费，x+5 表示当前正在处理的位置。如果拉取到消息之后就进行了位移提交，即提交了 x+8，那么当前消费 x+5 的时候遇到了异常，在故障恢复之后，我们重新拉取的消息是从 x+8 开始的。也就是说，x+5 至 x+7 之间的消息并未能被消费，如此便发生了消息丢失的现象。

再考虑另外一种情形，位移提交的动作是在消费完所有拉取到的消息之后才执行的，那么当消费 x+5 的时候遇到了异常，在故障恢复之后，我们重新拉取的消息是从 x+2 开始的。也就是说，x+2 至 x+4 之间的消息又重新消费了一遍，故而又发生了重复消费的现象。

而实际情况还会有比这两种更加复杂的情形，比如第一次的位移提交的位置为 x+8，而下一次的位移提交的位置为 x+4，后面会做进一步的分析。

在 Kafka 中默认的消费位移的提交方式是自动提交，这个由消费者客户端参数 enable.auto.commit 配置，默认值为 true。当然这个默认的自动提交不是每消费一条消息就提交一次，而是定期提交，这个定期的周期时间由客户端参数 auto.commit.interval.ms 配置，默认值为5秒，此参数生效的前提是 enable.auto.commit 参数为 true。在代码清单8-1中并没有展示出这两个参数，说明使用的正是默认值。

在默认的方式下，消费者每隔5秒会将拉取到的每个分区中最大的消息位移进行提交。自动位移提交的动作是在 poll() 方法的逻辑里完成的，在每次真正向服务端发起拉取请求之前会检查是否可以进行位移提交，如果可以，那么就会提交上一次轮询的位移。

在 Kafka 消费的编程逻辑中位移提交是一大难点，自动提交消费位移的方式非常简便，它免去了复杂的位移提交逻辑，让编码更简洁。但随之而来的是重复消费和消息丢失的问题。假设刚刚提交完一次消费位移，然后拉取一批消息进行消费，在下一次自动提交消费位移之前，消费者崩溃了，那么又得从上一次位移提交的地方重新开始消费，这样便发生了重复消费的现象（对于再均衡的情况同样适用）。我们可以通过减小位移提交的时间间隔来减小重复消息的窗口大小，但这样并不能避免重复消费的发送，而且也会使位移提交更加频繁。



![图3-8](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/8/1695abb90894d0d4~tplv-t2oaga2asx-watermark.awebp)



按照一般思维逻辑而言，自动提交是延时提交，重复消费可以理解，那么消息丢失又是在什么情形下会发生的呢？我们来看一下上图中的情形。拉取线程A不断地拉取消息并存入本地缓存，比如在 BlockingQueue 中，另一个处理线程B从缓存中读取消息并进行相应的逻辑处理。假设目前进行到了第 y+1 次拉取，以及第m次位移提交的时候，也就是 x+6 之前的位移已经确认提交了，处理线程B却还正在消费 x+3 的消息。此时如果处理线程B发生了异常，待其恢复之后会从第m此位移提交处，也就是 x+6 的位置开始拉取消息，那么 x+3 至 x+6 之间的消息就没有得到相应的处理，这样便发生消息丢失的现象。

自动位移提交的方式在正常情况下不会发生消息丢失或重复消费的现象，但是在编程的世界里异常无可避免，与此同时，自动位移提交也无法做到精确的位移管理。在 Kafka 中还提供了手动位移提交的方式，这样可以使得开发人员对消费位移的管理控制更加灵活。很多时候并不是说拉取到消息就算消费完成，而是需要将消息写入数据库、写入本地缓存，或者是更加复杂的业务处理。在这些场景下，所有的业务处理完成才能认为消息被成功消费，手动的提交方式可以让开发人员根据程序的逻辑在合适的地方进行位移提交。开启手动提交功能的前提是消费者客户端参数 enable.auto.commit 配置为 false，示例如下：

```
props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
```

手动提交可以细分为同步提交和异步提交，对应于 KafkaConsumer 中的 commitSync() 和 commitAsync() 两种类型的方法。我们这里先讲述同步提交的方式，commitSync() 方法的定义如下：

```
public void commitSync()
```

这个方法很简单，下面使用它演示同步提交的简单用法：

```
while (isRunning.get()) {
    ConsumerRecords<String, String> records = consumer.poll(1000);
    for (ConsumerRecord<String, String> record : records) {
        //do some logical processing.
    }
    consumer.commitSync();
}
```

可以看到示例中先对拉取到的每一条消息做相应的逻辑处理，然后对整个消息集做同步提交。参考 KafkaConsumer 源码中提供的示例，针对上面的示例还可以修改为批量处理+批量提交的方式，关键代码如下：

```
final int minBatchSize = 200;
List<ConsumerRecord> buffer = new ArrayList<>();
while (isRunning.get()) {
    ConsumerRecords<String, String> records = consumer.poll(1000);
    for (ConsumerRecord<String, String> record : records) {
        buffer.add(record);
    }
    if (buffer.size() >= minBatchSize) {
        //do some logical processing with buffer.
        consumer.commitSync();
        buffer.clear();
    }
}
```

上面的示例中将拉取到的消息存入缓存 buffer，等到积累到足够多的时候，也就是示例中大于等于200个的时候，再做相应的批量处理，之后再做批量提交。这两个示例都有重复消费的问题，如果在业务逻辑处理完之后，并且在同步位移提交前，程序出现了崩溃，那么待恢复之后又只能从上一次位移提交的地方拉取消息，由此在两次位移提交的窗口中出现了重复消费的现象。

commitSync() 方法会根据 poll() 方法拉取的最新位移来进行提交（注意提交的值对应于本节第1张图中 position 的位置），只要没有发生不可恢复的错误（Unrecoverable Error），它就会阻塞消费者线程直至位移提交完成。对于不可恢复的错误，比如 CommitFailedException、WakeupException、InterruptException、AuthenticationException、AuthorizationException 等，我们可以将其捕获并做针对性的处理。

对于采用 commitSync() 的无参方法而言，它提交消费位移的频率和拉取批次消息、处理批次消息的频率是一样的，如果想寻求更细粒度的、更精准的提交，那么就需要使用 commitSync() 的另一个含参方法，具体定义如下：

```
public void commitSync(final Map<TopicPartition, OffsetAndMetadata> offsets)
```

该方法提供了一个 offsets 参数，用来提交指定分区的位移。无参的 commitSync() 方法只能提交当前批次对应的 position 值。如果需要提交一个中间值，比如业务每消费一条消息就提交一次位移，那么就可以使用这种方式，我们来看一下代码示例，如代码清单11-2所示。

```
//代码清单11-2 带参数的同步位移提交
while (isRunning.get()) {
    ConsumerRecords<String, String> records = consumer.poll(1000);
    for (ConsumerRecord<String, String> record : records) {
        //do some logical processing.
        long offset = record.offset();
        TopicPartition partition =
                new TopicPartition(record.topic(), record.partition());
        consumer.commitSync(Collections
                .singletonMap(partition, new OffsetAndMetadata(offset + 1)));
    }
}
```

在实际应用中，很少会有这种每消费一条消息就提交一次消费位移的必要场景。commitSync() 方法本身是同步执行的，会耗费一定的性能，而示例中的这种提交方式会将性能拉到一个相当低的点。更多时候是按照分区的粒度划分提交位移的界限，这里我们就要用到了第10节中提及的 ConsumerRecords 类的 partitions() 方法和 records(TopicPartition) 方法，关键示例代码如代码清单11-3所示（修改自 KafkaConsumer 源码中的示例）。

```
//代码清单11-3 按分区粒度同步提交消费位移
try {
    while (isRunning.get()) {
        ConsumerRecords<String, String> records = consumer.poll(1000);
        for (TopicPartition partition : records.partitions()) {
            List<ConsumerRecord<String, String>> partitionRecords =
                    records.records(partition);
            for (ConsumerRecord<String, String> record : partitionRecords) {
                //do some logical processing.
            }
            long lastConsumedOffset = partitionRecords
                    .get(partitionRecords.size() - 1).offset();
            consumer.commitSync(Collections.singletonMap(partition,
                    new OffsetAndMetadata(lastConsumedOffset + 1)));
        }
    }
} finally {
    consumer.close();
}
```

与 commitSync() 方法相反，异步提交的方式（commitAsync()）在执行的时候消费者线程不会被阻塞，可能在提交消费位移的结果还未返回之前就开始了新一次的拉取操作。异步提交可以使消费者的性能得到一定的增强。commitAsync 方法有三个不同的重载方法，具体定义如下：

```
public void commitAsync()
public void commitAsync(OffsetCommitCallback callback)
public void commitAsync(final Map<TopicPartition, OffsetAndMetadata> offsets,
            OffsetCommitCallback callback)
```

第一个无参的方法和第三个方法中的 offsets 都很好理解，对照 commitSync() 方法即可。关键的是这里的第二个方法和第三个方法中的 callback 参数，它提供了一个异步提交的回调方法，当位移提交完成后会回调 OffsetCommitCallback 中的 onComplete() 方法。这里采用第二个方法来演示回调函数的用法，关键代码如下：

```
while (isRunning.get()) {
    ConsumerRecords<String, String> records = consumer.poll(1000);
    for (ConsumerRecord<String, String> record : records) {
        //do some logical processing.
    }
    consumer.commitAsync(new OffsetCommitCallback() {
        @Override
        public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets,
                               Exception exception) {
            if (exception == null) {
                System.out.println(offsets);
            }else {
                log.error("fail to commit offsets {}", offsets, exception);
            }
        }
    });
}
```

commitAsync() 提交的时候同样会有失败的情况发生，那么我们应该怎么处理呢？读者有可能想到的是重试，问题的关键也就在这里了。如果某一次异步提交的消费位移为x，但是提交失败了，然后下一次又异步提交了消费位移为x+y，这次成功了。如果这里引入了重试机制，前一次的异步提交的消费位移在重试的时候提交成功了，那么此时的消费位移又变为了x。如果此时发生异常（或者再均衡），那么恢复之后的消费者（或者新的消费者）就会从x处开始消费消息，这样就发生了重复消费的问题。

为此我们可以设置一个递增的序号来维护异步提交的顺序，每次位移提交之后就增加序号相对应的值。在遇到位移提交失败需要重试的时候，可以检查所提交的位移和序号的值的大小，如果前者小于后者，则说明有更大的位移已经提交了，不需要再进行本次重试；如果两者相同，则说明可以进行重试提交。除非程序编码错误，否则不会出现前者大于后者的情况。

如果位移提交失败的情况经常发生，那么说明系统肯定出现了故障，在一般情况下，位移提交失败的情况很少发生，不重试也没有关系，后面的提交也会有成功的。重试会增加代码逻辑的复杂度，不重试会增加重复消费的概率。如果消费者异常退出，那么这个重复消费的问题就很难避免，因为这种情况下无法及时提交消费位移；如果消费者正常退出或发生再均衡的情况，那么可以在退出或再均衡执行之前使用同步提交的方式做最后的把关。

```
try {
    while (isRunning.get()) {
        //poll records and do some logical processing.
        consumer.commitAsync();
    }
} finally {
    try {
        consumer.commitSync();
    }finally {
        consumer.close();
    }
}
```

示例代码中加粗的部分是在消费者正常退出时为位移提交“把关”添加的。发生再均衡情况的“把关”会在第13节中做详细介绍。

## <a name="60">控制或关闭消费</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

KafkaConsumer 提供了对消费速度进行控制的方法，在有些应用场景下我们可能需要暂停某些分区的消费而先消费其他分区，当达到一定条件时再恢复这些分区的消费。KafkaConsumer 中使用 pause() 和 resume() 方法来分别实现暂停某些分区在拉取操作时返回数据给客户端和恢复某些分区向客户端返回数据的操作。这两个方法的具体定义如下：

```
public void pause(Collection<TopicPartition> partitions)
public void resume(Collection<TopicPartition> partitions)
```

KafkaConsumer 还提供了一个无参的 paused() 方法来返回被暂停的分区集合，此方法的具体定义如下：

```
public Set<TopicPartition> paused()
```

之前的示例展示的都是使用一个 while 循环来包裹住 poll() 方法及相应的消费逻辑，如何优雅地退出这个循环也很有考究。细心的读者可能注意到有些示例代码并不是以 while(true) 的形式做简单的包裹，而是使用 while(isRunning.get()) 的方式，这样可以通过在其他地方设定 isRunning.set(false) 来退出 while 循环。还有一种方式是调用 KafkaConsumer 的 wakeup() 方法，wakeup() 方法是 KafkaConsumer 中唯一可以从其他线程里安全调用的方法（KafkaConsumer 是非线程安全的，可以通过14节了解更多细节），调用 wakeup() 方法后可以退出 poll() 的逻辑，并抛出 WakeupException 的异常，我们也不需要处理 WakeupException 的异常，它只是一种跳出循环的方式。

跳出循环以后一定要显式地执行关闭动作以释放运行过程中占用的各种系统资源，包括内存资源、Socket 连接等。KafkaConsumer 提供了 close() 方法来实现关闭，close() 方法有三种重载方法，分别如下：

```
public void close()
public void close(Duration timeout)
@Deprecated
public void close(long timeout, TimeUnit timeUnit)
```

第二种方法是通过 timeout 参数来设定关闭方法的最长执行时间，有些内部的关闭逻辑会耗费一定的时间，比如设置了自动提交消费位移，这里还会做一次位移提交的动作；而第一种方法没有 timeout 参数，这并不意味着会无限制地等待，它内部设定了最长等待时间（30秒）；第三种方法已被标记为 @Deprecated，可以不考虑。 一个相对完整的消费程序的逻辑可以参考下面的伪代码：

```
consumer.subscribe(Arrays.asList(topic));
try {
    while (running.get()) {
        //consumer.poll(***)
        //process the record.
        //commit offset.
    }
} catch (WakeupException e) {
    // ingore the error
} catch (Exception e){
    // do some logic process.
} finally {
    // maybe commit offset.
    consumer.close();
}
```

当关闭这个消费逻辑的时候，可以调用 consumer.wakeup()，也可以调用 isRunning.set(false)。



# <a name="61">指定位移消费</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在上一节中我们讲述了如何进行消费位移的提交，正是有了消费位移的持久化，才使消费者在关闭、崩溃或者在遇到再均衡的时候，可以让接替的消费者能够根据存储的消费位移继续进行消费。

试想一下，当一个新的消费组建立的时候，它根本没有可以查找的消费位移。或者消费组内的一个新消费者订阅了一个新的主题，它也没有可以查找的消费位移。当 __consumer_offsets 主题中有关这个消费组的位移信息过期而被删除后，它也没有可以查找的消费位移。



![3-9](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/8/1695ac2e4e7fc5cf~tplv-t2oaga2asx-watermark.awebp)



在 Kafka 中每当消费者查找不到所记录的消费位移时，就会根据消费者客户端参数 auto.offset.reset 的配置来决定从何处开始进行消费，这个参数的默认值为“latest”，表示从分区末尾开始消费消息。参考上图，按照默认的配置，消费者会从9开始进行消费（9是下一条要写入消息的位置），更加确切地说是从9开始拉取消息。如果将 auto.offset.reset 参数配置为“earliest”，那么消费者会从起始处，也就是0开始消费。

举个例子，在 auto.offset.reset 参数默认的配置下，用一个新的消费组来消费主题 topic-demo 时，客户端会报出重置位移的提示信息，参考如下：

```
[2018-08-18 18:13:16,029] INFO [Consumer clientId=consumer-1, groupId=group.demo] Resetting offset for partition topic-demo-3 to offset 100. 
[2018-08-18 18:13:16,030] INFO [Consumer clientId=consumer-1, groupId=group.demo] Resetting offset for partition topic-demo-0 to offset 100. 
[2018-08-18 18:13:16,030] INFO [Consumer clientId=consumer-1, groupId=group.demo] Resetting offset for partition topic-demo-2 to offset 100. 
[2018-08-18 18:13:16,031] INFO [Consumer clientId=consumer-1, groupId=group.demo] Resetting offset for partition topic-demo-1 to offset 100. 
```

除了查找不到消费位移，位移越界也会触发 auto.offset.reset 参数的执行，这个在下面要讲述的 seek 系列的方法中会有相关的介绍。

auto.offset.reset 参数还有一个可配置的值—“none”，配置为此值就意味着出现查到不到消费位移的时候，既不从最新的消息位置处开始消费，也不从最早的消息位置处开始消费，此时会报出 NoOffsetForPartitionException 异常，示例如下：

```
org.apache.kafka.clients.consumer.NoOffsetForPartitionException: Undefined offset with no reset policy for partitions: [topic-demo-3, topic-demo-0, topic-demo-2, topic-demo-1].
```

如果能够找到消费位移，那么配置为“none”不会出现任何异常。如果配置的不是“latest”、“earliest”和“none”，则会报出 ConfigException 异常，示例如下：

```
org.apache.kafka.common.config.ConfigException: Invalid value any for configuration auto.offset.reset: String must be one of: latest, earliest, none.
```

到目前为止，我们知道消息的拉取是根据 poll() 方法中的逻辑来处理的，这个 poll() 方法中的逻辑对于普通的开发人员而言是一个黑盒，无法精确地掌控其消费的起始位置。提供的 auto.offset.reset 参数也只能在找不到消费位移或位移越界的情况下粗粒度地从开头或末尾开始消费。有些时候，我们需要一种更细粒度的掌控，可以让我们从特定的位移处开始拉取消息，而 KafkaConsumer 中的 seek() 方法正好提供了这个功能，让我们得以追前消费或回溯消费。seek() 方法的具体定义如下：

```
public void seek(TopicPartition partition, long offset)
```

seek() 方法中的参数 partition 表示分区，而 offset 参数用来指定从分区的哪个位置开始消费。seek() 方法只能重置消费者分配到的分区的消费位置，而分区的分配是在 poll() 方法的调用过程中实现的。也就是说，在执行 seek() 方法之前需要先执行一次 poll() 方法，等到分配到分区之后才可以重置消费位置。seek() 方法的使用示例如代码清单12-1所示（只列出关键代码）。

```
//代码清单12-1 seek方法的使用示例
KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(Arrays.asList(topic));
consumer.poll(Duration.ofMillis(10000));                      	①
Set<TopicPartition> assignment = consumer.assignment();    	②
for (TopicPartition tp : assignment) {
    consumer.seek(tp, 10);   	                                ③
}
while (true) {
    ConsumerRecords<String, String> records = 
            consumer.poll(Duration.ofMillis(1000));
    //consume the record.
}
```

上面示例中第③行设置了每个分区的消费位置为10。第②行中的 assignment() 方法是用来获取消费者所分配到的分区信息的，这个方法的具体定义如下：

```
public Set<TopicPartition> assignment()
```

如果我们将代码清单12-1中第①行 poll() 方法的参数设置为0，即这一行替换为：

```
consumer.poll(Duration.ofMillis(0));
```

在此之后，会发现 seek() 方法并未有任何作用。因为当 poll() 方法中的参数为0时，此方法立刻返回，那么 poll() 方法内部进行分区分配的逻辑就会来不及实施。也就是说，消费者此时并未分配到任何分区，如此第②行中的 assignment 便是一个空列表，第③行代码也不会执行。那么这里的 timeout 参数设置为多少合适呢？太短会使分配分区的动作失败，太长又有可能造成一些不必要的等待。我们可以通过 KafkaConsumer 的 assignment() 方法来判定是否分配到了相应的分区，参考下面的代码清单12-2：

```
//代码清单12-2 seek()方法的另一种使用示例
KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(Arrays.asList(topic));
Set<TopicPartition> assignment = new HashSet<>();
while (assignment.size() == 0) {//如果不为0，则说明已经成功分配到了分区
    consumer.poll(Duration.ofMillis(100));
    assignment = consumer.assignment();
}
for (TopicPartition tp : assignment) {
    consumer.seek(tp, 10);
}
while (true) {
    ConsumerRecords<String, String> records =
            consumer.poll(Duration.ofMillis(1000));
    //consume the record.
}
```

如果对未分配到的分区执行 seek() 方法，那么会报出 IllegalStateException 的异常。类似在调用 subscribe() 方法之后直接调用 seek() 方法：

```
consumer.subscribe(Arrays.asList(topic));
consumer.seek(new TopicPartition(topic,0),10);
```

会报出如下的异常：

```
java.lang.IllegalStateException: No current assignment for partition topic-demo-0
```

如果消费组内的消费者在启动的时候能够找到消费位移，除非发生位移越界，否则 auto.offset.reset 参数并不会奏效，此时如果想指定从开头或末尾开始消费，就需要 seek() 方法的帮助了，代码清单12-3用来指定从分区末尾开始消费。

```
//代码清单12-3 使用seek()方法从分区末尾消费
KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(Arrays.asList(topic));
Set<TopicPartition> assignment = new HashSet<>();
while (assignment.size() == 0) {
    consumer.poll(Duration.ofMillis(100));
    assignment = consumer.assignment();
}
Map<TopicPartition, Long> offsets = consumer.endOffsets(assignment);    ①
for (TopicPartition tp : assignment) {
    consumer.seek(tp, offsets.get(tp));							        ②
}
```

代码清单12-3中第①行的 endOffsets() 方法用来获取指定分区的末尾的消息位置，参考上图中9的位置，注意这里获取的不是8，是将要写入最新消息的位置。endOffsets 的具体方法定义如下：

```
public Map<TopicPartition, Long> endOffsets(
            Collection<TopicPartition> partitions)
public Map<TopicPartition, Long> endOffsets(
            Collection<TopicPartition> partitions,
            Duration timeout)
```

其中 partitions 参数表示分区集合，而 timeout 参数用来设置等待获取的超时时间。如果没有指定 timeout 参数的值，那么 endOffsets() 方法的等待时间由客户端参数 request.timeout.ms 来设置，默认值为30000。与 endOffsets 对应的是 beginningOffsets() 方法，一个分区的起始位置起初是0，但并不代表每时每刻都为0，因为日志清理的动作会清理旧的数据，所以分区的起始位置会自然而然地增加。beginningOffsets() 方法的具体定义如下：

```
public Map<TopicPartition, Long> beginningOffsets(
            Collection<TopicPartition> partitions)
public Map<TopicPartition, Long> beginningOffsets(
            Collection<TopicPartition> partitions,
            Duration timeout)
```

beginningOffsets() 方法中的参数内容和含义都与 endOffsets() 方法中的一样，配合这两个方法我们就可以从分区的开头或末尾开始消费。其实 KafkaConsumer 中直接提供了 seekToBeginning() 方法和 seekToEnd() 方法来实现这两个功能，这两个方法的具体定义如下：

```
public void seekToBeginning(Collection<TopicPartition> partitions)
public void seekToEnd(Collection<TopicPartition> partitions)
```

有时候我们并不知道特定的消费位置，却知道一个相关的时间点，比如我们想要消费昨天8点之后的消息，这个需求更符合正常的思维逻辑。此时我们无法直接使用 seek() 方法来追溯到相应的位置。KafkaConsumer 同样考虑到了这种情况，它提供了一个 offsetsForTimes() 方法，通过 timestamp 来查询与此对应的分区位置。

```
public Map<TopicPartition, OffsetAndTimestamp> offsetsForTimes(
            Map<TopicPartition, Long> timestampsToSearch)
public Map<TopicPartition, OffsetAndTimestamp> offsetsForTimes(
            Map<TopicPartition, Long> timestampsToSearch, 
            Duration timeout)
```

offsetsForTimes() 方法的参数 timestampsToSearch 是一个 Map 类型，key 为待查询的分区，而 value 为待查询的时间戳，该方法会返回时间戳大于等于待查询时间的第一条消息对应的位置和时间戳，对应于 OffsetAndTimestamp 中的 offset 和 timestamp 字段。

下面的示例演示了 offsetsForTimes() 和 seek() 之间的使用方法，首先通过 offsetForTimes() 方法获取一天之前的消息位置，然后使用 seek() 方法追溯到相应位置开始消费，示例中的 assignment 变量和代码清单12-3中的一样，表示消费者分配到的分区集合。

```
Map<TopicPartition, Long> timestampToSearch = new HashMap<>();
for (TopicPartition tp : assignment) {
    timestampToSearch.put(tp, System.currentTimeMillis()-1*24*3600*1000);
}
Map<TopicPartition, OffsetAndTimestamp> offsets =
        consumer.offsetsForTimes(timestampToSearch);
for (TopicPartition tp : assignment) {
    OffsetAndTimestamp offsetAndTimestamp = offsets.get(tp);
    if (offsetAndTimestamp != null) {
        consumer.seek(tp, offsetAndTimestamp.offset());
    }
}
```

前面说过位移越界也会触发 auto.offset.reset 参数的执行，位移越界是指知道消费位置却无法在实际的分区中查找到，比如想要从上图中的位置10处拉取消息时就会发生位移越界。注意拉取上图中位置9处的消息时并未越界，这个位置代表特定的含义（LEO）。我们通过 seek() 方法来演示发生位移越界时的情形，将代码清单12-3中的第②行代码修改为：

```
consumer.seek(tp, offsets.get(tp)+1);
```

此时客户端会报出如下的提示信息：

```
[2018-08-19 16:13:44,700] INFO [Consumer clientId=consumer-1, groupId=group.demo] Fetch offset 101 is out of range for partition topic-demo-3, resetting offset 
[2018-08-19 16:13:44,701] INFO [Consumer clientId=consumer-1, groupId=group.demo] Fetch offset 101 is out of range for partition topic-demo-0, resetting offset 
[2018-08-19 16:13:44,701] INFO [Consumer clientId=consumer-1, groupId=group.demo] Fetch offset 101 is out of range for partition topic-demo-2, resetting offset 
[2018-08-19 16:13:44,701] INFO [Consumer clientId=consumer-1, groupId=group.demo] Fetch offset 101 is out of range for partition topic-demo-1, resetting offset 
[2018-08-19 16:13:44,708] INFO [Consumer clientId=consumer-1, groupId=group.demo] Resetting offset for partition topic-demo-3 to offset 100. 
[2018-08-19 16:13:44,708] INFO [Consumer clientId=consumer-1, groupId=group.demo] Resetting offset for partition topic-demo-0 to offset 100. 
[2018-08-19 16:13:44,709] INFO [Consumer clientId=consumer-1, groupId=group.demo] Resetting offset for partition topic-demo-2 to offset 100. 
[2018-08-19 16:13:44,713] INFO [Consumer clientId=consumer-1, groupId=group.demo] Resetting offset for partition topic-demo-1 to offset 100. 
```

通过上面加粗的提示信息可以了解到，原本拉取位置为101（fetch offset 101），但已经越界了（out of range），所以此时会根据 auto.offset.reset 参数的默认值来将拉取位置重置（resetting offset）为100，我们也能知道此时分区 topic-demo-3 中最大的消息 offset为99。

上一节中提及了 Kafka 中的消费位移是存储在一个内部主题中的，而本节的 seek() 方法可以突破这一限制：消费位移可以保存在任意的存储介质中，例如数据库、文件系统等。以数据库为例，我们将消费位移保存在其中的一个表中，在下次消费的时候可以读取存储在数据表中的消费位移并通过 seek() 方法指向这个具体的位置，伪代码如代码清单12-4所示。

```
//代码清单12-4 消费位移保存在DB中
consumer.subscribe(Arrays.asList(topic));
//省略poll()方法及assignment的逻辑
for(TopicPartition tp: assignment){
    long offset = getOffsetFromDB(tp);//从DB中读取消费位移
    consumer.seek(tp, offset);
}
while(true){
    ConsumerRecords<String, String> records =
            consumer.poll(Duration.ofMillis(1000));
    for (TopicPartition partition : records.partitions()) {
        List<ConsumerRecord<String, String>> partitionRecords =
                records.records(partition);
        for (ConsumerRecord<String, String> record : partitionRecords) {
            //process the record.
        }
        long lastConsumedOffset = partitionRecords
                .get(partitionRecords.size() - 1).offset();
         //将消费位移存储在DB中
        storeOffsetToDB(partition, lastConsumedOffset+1);
    }
}
```

seek() 方法为我们提供了从特定位置读取消息的能力，我们可以通过这个方法来向前跳过若干消息，也可以通过这个方法来向后回溯若干消息，这样为消息的消费提供了很大的灵活性。seek() 方法也为我们提供了将消费位移保存在外部存储介质中的能力，还可以配合再均衡监听器来提供更加精准的消费能力。



# <a name="62">再均衡</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

再均衡是指分区的所属权从一个消费者转移到另一消费者的行为，它为消费组具备高可用性和伸缩性提供保障，使我们可以既方便又安全地删除消费组内的消费者或往消费组内添加消费者。不过在再均衡发生期间，消费组内的消费者是无法读取消息的。也就是说，在再均衡发生期间的这一小段时间内，消费组会变得不可用。

另外，当一个分区被重新分配给另一个消费者时，消费者当前的状态也会丢失。比如消费者消费完某个分区中的一部分消息时还没有来得及提交消费位移就发生了再均衡操作，之后这个分区又被分配给了消费组内的另一个消费者，原来被消费完的那部分消息又被重新消费一遍，也就是发生了重复消费。一般情况下，应尽量避免不必要的再均衡的发生。

第8节中在讲述 subscribe() 方法时提及再均衡监听器 ConsumerRebalanceListener，在 subscribe(Collection<String> topics, ConsumerRebalanceListener listener)和 subscribe(Pattern pattern, ConsumerRebalanceListener listener)方法中都有它的身影。再均衡监听器用来设定发生再均衡动作前后的一些准备或收尾的动作。ConsumerRebalanceListener 是一个接口，包含2个方法，具体的释义如下：

1. void onPartitionsRevoked(Collection partitions) 这个方法会在再均衡开始之前和消费者停止读取消息之后被调用。可以通过这个回调方法来处理消费位移的提交，以此来避免一些不必要的重复消费现象的发生。参数 partitions 表示再均衡前所分配到的分区。
2. void onPartitionsAssigned(Collection partitions) 这个方法会在重新分配分区之后和消费者开始读取消费之前被调用。参数 partitions 表示再均衡后所分配到的分区。

下面我们通过一个例子来演示 ConsumerRebalanceListener 的用法，具体内容如代码清单13-1所示。

```
//代码清单13-1 再均衡监听器的用法
Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        consumer.commitSync(currentOffsets);
	        currentOffsets.clear();
    }
    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        //do nothing.
    }
});

try {
    while (isRunning.get()) {
        ConsumerRecords<String, String> records =
                consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, String> record : records) {
            //process the record.
            currentOffsets.put(
                    new TopicPartition(record.topic(), record.partition()),
                    new OffsetAndMetadata(record.offset() + 1));
        }
        consumer.commitAsync(currentOffsets, null);
    }
} finally {
    consumer.close();
}
```

代码清单13-1中将消费位移暂存到一个局部变量 currentOffsets 中，这样在正常消费的时候可以通过 commitAsync() 方法来异步提交消费位移，在发生再均衡动作之前可以通过再均衡监听器的 onPartitionsRevoked() 回调执行 commitSync() 方法同步提交消费位移，以尽量避免一些不必要的重复消费。

再均衡监听器还可以配合外部存储使用。在代码清单12-4中，我们将消费位移保存在数据库中，这里可以通过再均衡监听器查找分配到的分区的消费位移，并且配合 seek() 方法来进一步优化代码逻辑，将代码清单12-4中的第一行代码修改为如下内容：

```
consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        //store offset in DB （storeOffsetToDB）
    }
    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        for(TopicPartition tp: partitions){
            consumer.seek(tp, getOffsetFromDB(tp));//从DB中读取消费位移
        }
    }
});
```

本节只是简单演示了再均衡监听器的用法，再均衡期间消费者客户端与 Kafka 服务端之间的交互逻辑及相关原理并不简单，更多的细节可以参考[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)中的相关的内容。

# <a name="63">消费者拦截器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

第4节中讲述了生产者拦截器的使用，对应的消费者也有相应的拦截器的概念。消费者拦截器主要在消费到消息或在提交消费位移时进行一些定制化的操作。

与生产者拦截器对应的，消费者拦截器需要自定义实现 org.apache.kafka.clients.consumer. ConsumerInterceptor 接口。ConsumerInterceptor 接口包含3个方法：

- public ConsumerRecords<K, V> onConsume(ConsumerRecords<K, V> records)；
- public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets)；
- public void close()。

KafkaConsumer 会在 poll() 方法返回之前调用拦截器的 onConsume() 方法来对消息进行相应的定制化操作，比如修改返回的消息内容、按照某种规则过滤消息（可能会减少 poll() 方法返回的消息的个数）。如果 onConsume() 方法中抛出异常，那么会被捕获并记录到日志中，但是异常不会再向上传递。

KafkaConsumer 会在提交完消费位移之后调用拦截器的 onCommit() 方法，可以使用这个方法来记录跟踪所提交的位移信息，比如当消费者使用 commitSync 的无参方法时，我们不知道提交的消费位移的具体细节，而使用拦截器的 onCommit() 方法却可以做到这一点。

close() 方法和 ConsumerInterceptor 的父接口中的 configure() 方法与生产者的 ProducerInterceptor 接口中的用途一样，这里就不赘述了。

在某些业务场景中会对消息设置一个有效期的属性，如果某条消息在既定的时间窗口内无法到达，那么就会被视为无效，它也就不需要再被继续处理了。下面使用消费者拦截器来实现一个简单的消息 TTL（Time to Live，即过期时间）的功能。在代码清单13-1中，自定义的消费者拦截器 ConsumerInterceptorTTL 使用消息的 timestamp 字段来判定是否过期，如果消息的时间戳与当前的时间戳相差超过10秒则判定为过期，那么这条消息也就被过滤而不投递给具体的消费者。

```
//代码清单13-1 自定义的消费者拦截器
public class ConsumerInterceptorTTL implements 
        ConsumerInterceptor<String, String> {
    private static final long EXPIRE_INTERVAL = 10 * 1000;

    @Override
    public ConsumerRecords<String, String> onConsume(
            ConsumerRecords<String, String> records) {
        long now = System.currentTimeMillis();
        Map<TopicPartition, List<ConsumerRecord<String, String>>> newRecords 
                = new HashMap<>();
        for (TopicPartition tp : records.partitions()) {
            List<ConsumerRecord<String, String>> tpRecords = 
            records.records(tp);
            List<ConsumerRecord<String, String>> newTpRecords = new ArrayList<>();
            for (ConsumerRecord<String, String> record : tpRecords) {
                if (now - record.timestamp() < EXPIRE_INTERVAL) {
                    newTpRecords.add(record);
                }
            }
            if (!newTpRecords.isEmpty()) {
                newRecords.put(tp, newTpRecords);
            }
        }
        return new ConsumerRecords<>(newRecords);
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        offsets.forEach((tp, offset) -> 
                System.out.println(tp + ":" + offset.offset()));
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> configs) {}
}
```

实现自定义的 ConsumerInterceptorTTL 之后，需要在 KafkaConsumer 中配置指定这个拦截器，这个指定的配置和 KafkaProducer 中的一样，也是通过 interceptor.classes 参数实现的，此参数的默认值为“”。示例如下：

```
props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG,
        ConsumerInterceptorTTL.class.getName());
```

我们在发送消息的时候修改 ProducerRecord 中的 timestamp 的值来使其变得超时，具体可以参考下面的示例：

```
ProducerRecord<String, String> record1 = new ProducerRecord<>(topic, 0, System
        .currentTimeMillis()-EXPIRE_INTERVAL, null, "first-expire-data");
producer.send(record1).get();

ProducerRecord<String, String> record2 = new ProducerRecord<>(topic, 0, System
        .currentTimeMillis(), null, "normal-data");
producer.send(record2).get();

ProducerRecord<String, String> record3 = new ProducerRecord<>(topic, 0, System
        .currentTimeMillis()-EXPIRE_INTERVAL, null, "last-expire-data");
producer.send(record3).get();
```

示例代码中一共发送了三条消息：“first-expire-data”“normal-data”和“last-expire-data”，其中第一条和第三条消息都被修改成超时了，那么此时消费者通过 poll() 方法只能拉取到“normal-data”这一条消息，另外两条就被过滤了。

不过使用这种功能时需要注意的是：在使用类似代码清单11-2中这种带参数的位移提交的方式时，有可能提交了错误的位移信息。在一次消息拉取的批次中，可能含有最大偏移量的消息会被消费者拦截器过滤。

在消费者中也有拦截链的概念，和生产者的拦截链一样，也是按照 interceptor.classes 参数配置的拦截器的顺序来一一执行的（配置的时候，各个拦截器之间使用逗号隔开）。同样也要提防“副作用”的发生。如果在拦截链中某个拦截器执行失败，那么下一个拦截器会接着从上一个执行成功的拦截器继续执行。



# <a name="64">消费者多线程实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

KafkaProducer 是线程安全的，然而 KafkaConsumer 却是非线程安全的。KafkaConsumer 中定义了一个 acquire() 方法，用来检测当前是否只有一个线程在操作，若有其他线程正在操作则会抛出 ConcurrentModifcationException 异常：

```
java.util.ConcurrentModificationException: KafkaConsumer is not safe for multi-threaded access.
```

KafkaConsumer 中的每个公用方法在执行所要执行的动作之前都会调用这个 acquire() 方法，只有 wakeup() 方法是个例外，具体用法可以参考第11节。acquire () 方法的具体定义如下：

```
private final AtomicLong currentThread
    = new AtomicLong(NO_CURRENT_THREAD); //KafkaConsumer中的成员变量

private void acquire() {
    long threadId = Thread.currentThread().getId();
    if (threadId != currentThread.get() &&
            !currentThread.compareAndSet(NO_CURRENT_THREAD, threadId))
        throw new ConcurrentModificationException
                ("KafkaConsumer is not safe for multi-threaded access");
    refcount.incrementAndGet();
}
```

acquire() 方法和我们通常所说的锁（synchronized、Lock 等）不同，它不会造成阻塞等待，我们可以将其看作一个轻量级锁，它仅通过线程操作计数标记的方式来检测线程是否发生了并发操作，以此保证只有一个线程在操作。acquire() 方法和 release() 方法成对出现，表示相应的加锁和解锁操作。release() 方法也很简单，具体定义如下：

```
private void release() {
    if (refcount.decrementAndGet() == 0)
        currentThread.set(NO_CURRENT_THREAD);
}
```

acquire() 方法和 release() 方法都是私有方法，因此在实际应用中不需要我们显式地调用，但了解其内部的机理之后可以促使我们正确、有效地编写相应的程序逻辑。

KafkaConsumer 非线程安全并不意味着我们在消费消息的时候只能以单线程的方式执行。如果生产者发送消息的速度大于消费者处理消息的速度，那么就会有越来越多的消息得不到及时的消费，造成了一定的延迟。除此之外，由于 Kafka 中消息保留机制的作用，有些消息有可能在被消费之前就被清理了，从而造成消息的丢失。

我们可以通过多线程的方式来实现消息消费，多线程的目的就是为了提高整体的消费能力。多线程的实现方式有多种，第一种也是最常见的方式：线程封闭，即为每个线程实例化一个 KafkaConsumer 对象，如下图所示。



![图3-10](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/6/1695133eba710aba~tplv-t2oaga2asx-watermark.awebp)



一个线程对应一个 KafkaConsumer 实例，我们可以称之为消费线程。一个消费线程可以消费一个或多个分区中的消息，所有的消费线程都隶属于同一个消费组。这种实现方式的并发度受限于分区的实际个数，根据第7节中介绍的消费者与分区数的关系，当消费线程的个数大于分区数时，就有部分消费线程一直处于空闲的状态。

与此对应的第二种方式是多个消费线程同时消费同一个分区，这个通过 assign()、seek() 等方法实现，这样可以打破原有的消费线程的个数不能超过分区数的限制，进一步提高了消费的能力。不过这种实现方式对于位移提交和顺序控制的处理就会变得非常复杂，实际应用中使用得极少，笔者也并不推荐。一般而言，分区是消费线程的最小划分单位。下面我们通过实际编码来演示第一种多线程消费实现的方式，详细示例参考如代码清单14-1所示。

```
//代码清单14-1 第一种多线程消费实现方式
public class FirstMultiConsumerThreadDemo {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";

    public static Properties initConfig(){
        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        int consumerThreadNum = 4;
        for(int i=0;i<consumerThreadNum;i++) {
            new KafkaConsumerThread(props,topic).start();
        }
    }

    public static class KafkaConsumerThread extends Thread{
        private KafkaConsumer<String, String> kafkaConsumer;

        public KafkaConsumerThread(Properties props, String topic) {
            this.kafkaConsumer = new KafkaConsumer<>(props);
            this.kafkaConsumer.subscribe(Arrays.asList(topic));
        }

        @Override
        public void run(){
            try {
                while (true) {
                    ConsumerRecords<String, String> records =
                            kafkaConsumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        //处理消息模块	①
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                kafkaConsumer.close();
            }
        }
    }
}
```

内部类 KafkaConsumerThread 代表消费线程，其内部包裹着一个独立的 KafkaConsumer 实例。通过外部类的 main() 方法来启动多个消费线程，消费线程的数量由 consumerThreadNum 变量指定。一般一个主题的分区数事先可以知晓，可以将 consumerThreadNum 设置成不大于分区数的值，如果不知道主题的分区数，那么也可以通过 KafkaConsumer 类的 partitionsFor() 方法来间接获取，进而再设置合理的 consumerThreadNum 值。

上面这种多线程的实现方式和开启多个消费进程的方式没有本质上的区别，它的优点是每个线程可以按顺序消费各个分区中的消息。缺点也很明显，每个消费线程都要维护一个独立的TCP连接，如果分区数和 consumerThreadNum 的值都很大，那么会造成不小的系统开销。



![3-11](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/6/1695136a57f02090~tplv-t2oaga2asx-watermark.awebp)



参考代码清单14-1中的第①行，如果这里对消息的处理非常迅速，那么 poll() 拉取的频次也会更高，进而整体消费的性能也会提升；相反，如果在这里对消息的处理缓慢，比如进行一个事务性操作，或者等待一个RPC的同步响应，那么 poll() 拉取的频次也会随之下降，进而造成整体消费性能的下降。一般而言，poll() 拉取消息的速度是相当快的，而整体消费的瓶颈也正是在处理消息这一块，如果我们通过一定的方式来改进这一部分，那么我们就能带动整体消费性能的提升。

参考上图，考虑第三种实现方式，将处理消息模块改成多线程的实现方式，具体实现如代码清单14-2所示。

```
//代码清单14-2 第三种多线程消费实现方式
public class ThirdMultiConsumerThreadDemo {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";

    //省略initConfig()方法，具体请参考代码清单14-1
    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaConsumerThread consumerThread = 
                new KafkaConsumerThread(props, topic,
                Runtime.getRuntime().availableProcessors());
        consumerThread.start();
    }

    public static class KafkaConsumerThread extends Thread {
        private KafkaConsumer<String, String> kafkaConsumer;
        private ExecutorService executorService;
        private int threadNumber;

        public KafkaConsumerThread(Properties props, 
                String topic, int threadNumber) {
            kafkaConsumer = new KafkaConsumer<>(props);
            kafkaConsumer.subscribe(Collections.singletonList(topic));
            this.threadNumber = threadNumber;
            executorService = new ThreadPoolExecutor(threadNumber, threadNumber,
                    0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000),
                    new ThreadPoolExecutor.CallerRunsPolicy());
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records =
                            kafkaConsumer.poll(Duration.ofMillis(100));
                    if (!records.isEmpty()) {
                        executorService.submit(new RecordsHandler(records));
                    }	 ①
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                kafkaConsumer.close();
            }
        }

    }

    public static class RecordsHandler extends Thread{
        public final ConsumerRecords<String, String> records;

        public RecordsHandler(ConsumerRecords<String, String> records) {
            this.records = records;
        }

        @Override
        public void run(){
            //处理records.
        }
    }
}
```

代码清单14-2中的 RecordHandler 类是用来处理消息的，而 KafkaConsumerThread 类对应的是一个消费线程，里面通过线程池的方式来调用 RecordHandler 处理一批批的消息。注意 KafkaConsumerThread 类中 ThreadPoolExecutor 里的最后一个参数设置的是 CallerRunsPolicy()，这样可以防止线程池的总体消费能力跟不上 poll() 拉取的能力，从而导致异常现象的发生。第三种实现方式还可以横向扩展，通过开启多个 KafkaConsumerThread 实例来进一步提升整体的消费能力。

第三种实现方式相比第一种实现方式而言，除了横向扩展的能力，还可以减少TCP连接对系统资源的消耗，不过缺点就是对于消息的顺序处理就比较困难了。在代码清单14-1中的 initConfig() 方法里笔者特意加了一个配置：

```
props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
```

这样旨在说明在具体实现的时候并没有考虑位移提交的情况。对于第一种实现方式而言，如果要做具体的位移提交，它的具体实现和第11节讲述的位移提交没有什么区别，直接在 KafkaConsumerThread 中的 run() 方法里实现即可。而对于第三种实现方式，这里引入一个共享变量 offsets 来参与提交，如下图所示。



![图3-12](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/6/1695138ead894cc1~tplv-t2oaga2asx-watermark.awebp)



每一个处理消息的 RecordHandler 类在处理完消息之后都将对应的消费位移保存到共享变量 offsets 中，KafkaConsumerThread 在每一次 poll() 方法之后都读取 offsets 中的内容并对其进行位移提交。注意在实现的过程中对 offsets 读写需要加锁处理，防止出现并发问题。并且在写入 offsets 的时候需要注意位移覆盖的问题，针对这个问题，可以将 RecordHandler 类中的 run() 方法实现改为如下内容（参考代码清单11-3）：

```
for (TopicPartition tp : records.partitions()) {
    List<ConsumerRecord<String, String>> tpRecords = records.records(tp);
    //处理tpRecords.
    long lastConsumedOffset = tpRecords.get(tpRecords.size() - 1).offset();
    synchronized (offsets) {
        if (!offsets.containsKey(tp)) {
            offsets.put(tp, new OffsetAndMetadata(lastConsumedOffset + 1));
        }else {
            long position = offsets.get(tp).offset();
            if (position < lastConsumedOffset + 1) {
                offsets.put(tp, new OffsetAndMetadata(lastConsumedOffset + 1));
            }
        }
    }
}
```

对应的位移提交实现可以添加在代码清单14-2中 KafkaConsumerThread 类的第①行代码下方，具体实现参考如下：

```
synchronized (offsets) {
    if (!offsets.isEmpty()) {
        kafkaConsumer.commitSync(offsets);
        offsets.clear();
    }
}
```

读者可以细想一下这样实现是否万无一失？其实这种位移提交的方式会有数据丢失的风险。对于同一个分区中的消息，假设一个处理线程 RecordHandler1 正在处理 offset 为0～99的消息，而另一个处理线程 RecordHandler2 已经处理完了 offset 为100～199的消息并进行了位移提交，此时如果 RecordHandler1 发生异常，则之后的消费只能从200开始而无法再次消费0～99的消息，从而造成了消息丢失的现象。这里虽然针对位移覆盖做了一定的处理，但还没有解决异常情况下的位移覆盖问题。

对此就要引入更加复杂的处理机制，这里再提供一种解决思路，参考下图，总体结构上是基于滑动窗口实现的。对于第三种实现方式而言，它所呈现的结构是通过消费者拉取分批次的消息，然后提交给多线程进行处理，而这里的滑动窗口式的实现方式是将拉取到的消息暂存起来，多个消费线程可以拉取暂存的消息，这个用于暂存消息的缓存大小即为滑动窗口的大小，总体上而言没有太多的变化，不同的是对于消费位移的把控。



![图3-13](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/6/169513be4320133f~tplv-t2oaga2asx-watermark.awebp)



如上图所示，每一个方格代表一个批次的消息，一个滑动窗口包含若干方格，startOffset 标注的是当前滑动窗口的起始位置，endOffset 标注的是末尾位置。每当 startOffset 指向的方格中的消息被消费完成，就可以提交这部分的位移，与此同时，窗口向前滑动一格，删除原来 startOffset 所指方格中对应的消息，并且拉取新的消息进入窗口。滑动窗口的大小固定，所对应的用来暂存消息的缓存大小也就固定了，这部分内存开销可控。

方格大小和滑动窗口的大小同时决定了消费线程的并发数：一个方格对应一个消费线程，对于窗口大小固定的情况，方格越小并行度越高；对于方格大小固定的情况，窗口越大并行度越高。不过，若窗口设置得过大，不仅会增大内存的开销，而且在发生异常（比如 Crash）的情况下也会引起大量的重复消费，同时还考虑线程切换的开销，建议根据实际情况设置一个合理的值，不管是对于方格还是窗口而言，过大或过小都不合适。

如果一个方格内的消息无法被标记为消费完成，那么就会造成 startOffset 的悬停。为了使窗口能够继续向前滑动，那么就需要设定一个阈值，当 startOffset 悬停一定的时间后就对这部分消息进行本地重试消费，如果重试失败就转入重试队列，如果还不奏效就转入死信队列。真实应用中无法消费的情况极少，一般是由业务代码的处理逻辑引起的，比如消息中的内容格式与业务处理的内容格式不符，无法对这条消息进行决断，这种情况可以通过优化代码逻辑或采取丢弃策略来避免。如果需要消息高度可靠，也可以将无法进行业务逻辑的消息（这类消息可以称为死信）存入磁盘、数据库或 Kafka，然后继续消费下一条消息以保证整体消费进度合理推进，之后可以通过一个额外的处理任务来分析死信进而找出异常的原因。



# <a name="65">重要的消费者参数</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在 KafkaConsumer 中，除了第8节提及的4个默认的客户端参数，大部分的参数都有合理的默认值，一般我们也不需要去修改它们。不过了解这些参数可以让我们更好地使用消费者客户端，其中还有一些重要的参数涉及程序的可用性和性能，如果能够熟练掌握它们，也可以让我们在编写相关的程序时能够更好地进行性能调优与故障排查。下面挑选一些重要的参数来做细致的讲解。

## <a name="66">1. fetch.min.bytes</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

该参数用来配置 Consumer 在一次拉取请求（调用 poll() 方法）中能从 Kafka 中拉取的最小数据量，默认值为1（B）。Kafka 在收到 Consumer 的拉取请求时，如果返回给 Consumer 的数据量小于这个参数所配置的值，那么它就需要进行等待，直到数据量满足这个参数的配置大小。可以适当调大这个参数的值以提高一定的吞吐量，不过也会造成额外的延迟（latency），对于延迟敏感的应用可能就不可取了。

## <a name="67">2. fetch.max.bytes</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

该参数与 fetch.min.bytes 参数对应，它用来配置 Consumer 在一次拉取请求中从Kafka中拉取的最大数据量，默认值为52428800（B），也就是50MB。

如果这个参数设置的值比任何一条写入 Kafka 中的消息要小，那么会不会造成无法消费呢？很多资料对此参数的解读认为是无法消费的，比如一条消息的大小为10B，而这个参数的值是1（B），既然此参数设定的值是一次拉取请求中所能拉取的最大数据量，那么显然1B<10B，所以无法拉取。这个观点是错误的，该参数设定的不是绝对的最大值，如果在第一个非空分区中拉取的第一条消息大于该值，那么该消息将仍然返回，以确保消费者继续工作。也就是说，上面问题的答案是可以正常消费。

与此相关的，Kafka 中所能接收的最大消息的大小通过服务端参数 message.max.bytes（对应于主题端参数 max.message.bytes）来设置。

## <a name="68">3. fetch.max.wait.ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数也和 fetch.min.bytes 参数有关，如果 Kafka 仅仅参考 fetch.min.bytes 参数的要求，那么有可能会一直阻塞等待而无法发送响应给 Consumer，显然这是不合理的。fetch.max.wait.ms 参数用于指定 Kafka 的等待时间，默认值为500（ms）。如果 Kafka 中没有足够多的消息而满足不了 fetch.min.bytes 参数的要求，那么最终会等待500ms。这个参数的设定和 Consumer 与 Kafka 之间的延迟也有关系，如果业务应用对延迟敏感，那么可以适当调小这个参数。

## <a name="69">4. max.partition.fetch.bytes</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来配置从每个分区里返回给 Consumer 的最大数据量，默认值为1048576（B），即1MB。这个参数与 fetch.max.bytes 参数相似，只不过前者用来限制一次拉取中每个分区的消息大小，而后者用来限制一次拉取中整体消息的大小。同样，如果这个参数设定的值比消息的大小要小，那么也不会造成无法消费，Kafka 为了保持消费逻辑的正常运转不会对此做强硬的限制。

## <a name="70">5. max.poll.records</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来配置 Consumer 在一次拉取请求中拉取的最大消息数，默认值为500（条）。如果消息的大小都比较小，则可以适当调大这个参数值来提升一定的消费速度。

## <a name="71">6. connections.max.idle.ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来指定在多久之后关闭闲置的连接，默认值是540000（ms），即9分钟。

## <a name="72">7. exclude.internal.topics</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka 中有两个内部的主题： __consumer_offsets 和 __transaction_state。exclude.internal.topics 用来指定 Kafka 中的内部主题是否可以向消费者公开，默认值为 true。如果设置为 true，那么只能使用 subscribe(Collection)的方式而不能使用 subscribe(Pattern)的方式来订阅内部主题，设置为 false 则没有这个限制。

## <a name="73">8. receive.buffer.bytes</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来设置 Socket 接收消息缓冲区（SO_RECBUF）的大小，默认值为65536（B），即64KB。如果设置为-1，则使用操作系统的默认值。如果 Consumer 与 Kafka 处于不同的机房，则可以适当调大这个参数值。

## <a name="74">9. send.buffer.bytes</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来设置Socket发送消息缓冲区（SO_SNDBUF）的大小，默认值为131072（B），即128KB。与receive.buffer.bytes参数一样，如果设置为-1，则使用操作系统的默认值。

## <a name="75">10. request.timeout.ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来配置 Consumer 等待请求响应的最长时间，默认值为30000（ms）。

## <a name="76">11. metadata.max.age.ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来配置元数据的过期时间，默认值为300000（ms），即5分钟。如果元数据在此参数所限定的时间范围内没有进行更新，则会被强制更新，即使没有任何分区变化或有新的 broker 加入。

## <a name="77">12. reconnect.backoff.ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来配置尝试重新连接指定主机之前的等待时间（也称为退避时间），避免频繁地连接主机，默认值为50（ms）。这种机制适用于消费者向 broker 发送的所有请求。

## <a name="78">13. retry.backoff.ms</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来配置尝试重新发送失败的请求到指定的主题分区之前的等待（退避）时间，避免在某些故障情况下频繁地重复发送，默认值为100（ms）。

## <a name="79">14. isolation.level</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这个参数用来配置消费者的事务隔离级别。字符串类型，有效值为“read_uncommitted”和“read_committed”，表示消费者所消费到的位置，如果设置为“read_committed”，那么消费者就会忽略事务未提交的消息，即只能消费到LSO（LastStableOffset）的位置，默认情况下为“read_uncommitted”，即可以消费到 HW（High Watermark）处的位置。有关事务和 LSO 的内容可以参考[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)的相关章节。

还有一些消费者参数在本节没有提及，这些参数同样非常重要，它们需要用单独的章节或场景中描述。部分参数在前面的章节内容中已经提及，比如 boostrap.servers；还有部分参数会在后面的[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)中提及，比如 heartbeat.interval.ms。下表罗列了部分消费者客户端的重要参数。

| 参 数 名 称                   | 默 认 值                                        | 参 数 释 义                                                  |
| ----------------------------- | ----------------------------------------------- | ------------------------------------------------------------ |
| bootstrap.servers             | “”                                              | 指定连接 Kafka 集群所需的 broker 地址清单                    |
| key.deserializer              |                                                 | 消息中 key 所对应的反序列化类，需要实现 org.apache.kafka.common.serialization.Deserializer 接口 |
| value.deserializer            |                                                 | 消息中 key 所对应的反序列化类，需要实现 org.apache.kafka.common.serialization.Deserializer 接口 |
| group.id                      | “”                                              | 此消费者所隶属的消费组的唯一标识，即消费组的名称             |
| client.id                     | “”                                              | 消费者客户端的id。                                           |
| heartbeat.interval.ms         | 3000                                            | 当使用 Kafka 的分组管理功能时，心跳到消费者协调器之间的预计时间。心跳用于确保消费者的会话保持活动状态，当有新消费者加入或离开组时方便重新平衡。该值必须比 session.timeout.ms 小，通常不高于1/3。它可以调整得更低，以控制正常重新平衡的预期时间 |
| session.timeout.ms            | 10000                                           | 组管理协议中用来检测消费者是否失效的超时时间                 |
| max.poll.interval.ms          | 300000                                          | 当通过消费组管理消费者时，该配置指定拉取消息线程最长空闲时间，若超过这个时间间隔还没有发起 poll 操作，则消费组认为该消费者已离开了消费组，将进行再均衡操作 |
| auto.offset.reset             | latest                                          | 参数值为字符串类型，有效值为“earliest”“latest”“none”，配置为其余值会报出异常 |
| enable.auto.commit            | true                                            | boolean 类型，配置是否开启自动提交消费位移的功能，默认开启   |
| auto.commit.interval.ms       | 5000                                            | 当enbale.auto.commit参数设置为 true 时才生效，表示开启自动提交消费位移功能时自动提交消费位移的时间间隔 |
| partition.assignment.strategy | org.apache.kafka.clients.consumer.RangeAssignor | 消费者的分区分配策略                                         |
| interceptor.class             | “”                                              | 用来配置消费者客户端的拦截器                                 |



从第7节至本节主要讲述了消费者和消费组的概念，以及如何正确使用 KafkaConsumer。具体的内容还包括参数配置的讲解、订阅、反序列化、位移提交、再均衡、消费者拦截器、多线程的使用。不过本章并没有同前一章的生产者一样讲述具体的原理，因为考虑到 KafkaConsumer 内部实现相对复杂，具体的原理讲述篇幅较长，故将相关的内容编排到[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)中，如果读者迫切地想要了解这部分的知识，可以直接跳转到[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)相关章节进行阅读。



# <a name="80">主题与分区</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

主题和分区是 Kafka 的两个核心概念，前面章节中讲述的生产者和消费者的设计理念所针对的都是主题和分区层面的操作。主题作为消息的归类，可以再细分为一个或多个分区，分区也可以看作对消息的二次归类。分区的划分不仅为 Kafka 提供了可伸缩性、水平扩展的功能，还通过多副本机制来为 Kafka 提供数据冗余以提高数据可靠性。

从 Kafka 的底层实现来说，主题和分区都是逻辑上的概念，分区可以有一至多个副本，每个副本对应一个日志文件，每个日志文件对应一至多个日志分段（LogSegment），每个日志分段还可以细分为索引文件、日志存储文件和快照文件等。不过对于使用 Kafka 进行消息收发的普通用户而言，了解到分区这一层面足以应对大部分的使用场景。本章只针对主题与分区这一层面的内容进行讲解，更底层的内容会在[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)中进行详述。

# <a name="81">主题的管理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

主题的管理包括创建主题、查看主题信息、修改主题和删除主题等操作。可以通过 Kafka 提供的 kafka-topics.sh 脚本来执行这些操作，这个脚本位于$KAFKA_HOME/bin/目录下，其核心代码仅有一行，具体如下：

```
exec $(dirname $0)/kafka-run-class.sh kafka.admin.TopicCommand "$@"
```

可以看到其实质上是调用了 kafka.admin.TopicCommand 类来执行主题管理的操作。

主题的管理并非只有使用 kafka-topics.sh 脚本这一种方式，我们还可以通过 KafkaAdminClient 的方式实现（这种方式实质上是通过发送 CreateTopicsRequest、DeleteTopicsRequest 等请求来实现的），甚至我们还可以通过直接操纵日志文件和 ZooKeeper 节点来实现。下面按照创建主题、查看主题信息、修改主题、删除主题的顺序来介绍其中的操作细节。

# <a name="82">创建主题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

如果 broker 端配置参数 auto.create.topics.enable 设置为 true（默认值就是 true），那么当生产者向一个尚未创建的主题发送消息时，会自动创建一个分区数为 num.partitions（默认值为1）、副本因子为 default.replication.factor（默认值为1）的主题。除此之外，当一个消费者开始从未知主题中读取消息时，或者当任意一个客户端向未知主题发送元数据请求时，都会按照配置参数 num.partitions 和 default.replication.factor 的值来创建一个相应的主题。很多时候，这种自动创建主题的行为都是非预期的。除非有特殊应用需求，否则不建议将 auto.create.topics.enable 参数设置为 true，这个参数会增加主题的管理与维护的难度。

更加推荐也更加通用的方式是通过 kafka-topics.sh 脚本来创建主题。在第2节演示消息的生产与消费时就通过这种方式创建了一个分区数为4、副本因子为3的主题 topic-demo。下面通过创建另一个主题 topic-create 来回顾一下这种创建主题的方式，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/kafka --create --topic topic-create --partitions 4 --replication-factor 2
Created topic "topic-create". #此为控制台执行的输出结果
```

上面的示例中创建了一个分区数为4、副本因子为2的主题。示例中的环境是一个包含3个 broker 节点的集群，每个节点的名称和 brokerId 的对照关系如下：

```
node1 brokerId=0
node2 brokerId=1
node3 brokerId=2
```

在执行完脚本之后，Kafka 会在 log.dir 或 log.dirs 参数所配置的目录下创建相应的主题分区，默认情况下这个目录为/tmp/kafka-logs/。我们来查看一下 node1 节点中创建的主题分区，参考如下：

```
[root@node1 kafka_2.11-2.0.0]# ls -al /tmp/kafka-logs/ | grep topic-create
drwxr-xr-x   2 root root 4096 Sep  8 15:54 topic-create-0
drwxr-xr-x   2 root root 4096 Sep  8 15:54 topic-create-1
```

可以看到 node1 节点中创建了2个文件夹 topic-create-0 和 topic-create-1，对应主题 topic-create 的2个分区编号为0和1的分区，命名方式可以概括为<topic>-<partition>。严谨地说，其实<topic>-<partition>这类文件夹对应的不是分区，分区同主题一样是一个逻辑的概念而没有物理上的存在。并且这里我们也只是看到了2个分区，而我们创建的是4个分区，其余2个分区被分配到了 node2 和 node3 节点中，参考如下：

```
[root@node2 kafka_2.11-2.0.0]# ls -al /tmp/kafka-logs/ |grep topic-create
drwxr-xr-x   2 root root   4096 Sep  8 15:49 topic-create-1
drwxr-xr-x   2 root root   4096 Sep  8 15:49 topic-create-2
drwxr-xr-x   2 root root   4096 Sep  8 15:49 topic-create-3
[root@node3 kafka_2.11-2.0.0]# ls -al /tmp/kafka-logs/ |grep topic-create
drwxr-xr-x   2 root root 4096 Sep  8 07:54 topic-create-0
drwxr-xr-x   2 root root 4096 Sep  8 07:54 topic-create-2
drwxr-xr-x   2 root root 4096 Sep  8 07:54 topic-create-3
```

三个 broker 节点一共创建了8个文件夹，这个数字8实质上是分区数4与副本因子2的乘积。每个副本（或者更确切地说应该是日志，副本与日志一一对应）才真正对应了一个命名形式如<topic>-<partition>的文件夹。

主题、分区、副本和 Log（日志）的关系如下图所示，主题和分区都是提供给上层用户的抽象，而在副本层面或更加确切地说是 Log 层面才有实际物理上的存在。同一个分区中的多个副本必须分布在不同的 broker 中，这样才能提供有效的数据冗余。对于示例中的分区数为4、副本因子为2、broker 数为3的情况下，按照2、3、3的分区副本个数分配给各个 broker 是最优的选择。再比如在分区数为3、副本因子为3，并且 broker 数同样为3的情况下，分配3、3、3的分区副本个数给各个 broker 是最优的选择，也就是每个 broker 中都拥有所有分区的一个副本。



![图4-1](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/6/169515b01842457b~tplv-t2oaga2asx-watermark.awebp)



我们不仅可以通过日志文件的根目录来查看集群中各个 broker 的分区副本的分配情况，还可以通过 ZooKeeper 客户端来获取。当创建一个主题时会在 ZooKeeper 的/brokers/topics/目录下创建一个同名的实节点，该节点中记录了该主题的分区副本分配方案。示例如下：

```
[zk: localhost:2181/kafka(CONNECTED) 2] get /brokers/topics/topic-create
{"version":1,"partitions":{"2":[1,2],"1":[0,1],"3":[2,1],"0":[2,0]}}
```

示例数据中的"2":[1,2]表示分区2分配了2个副本，分别在 brokerId 为1和2的 broker 节点中。 回顾一下第2节中提及的知识点：kafka-topics.sh 脚本中的 zookeeper、partitions、replication-factor 和 topic 这4个参数分别代表 ZooKeeper 连接地址、分区数、副本因子和主题名称。另一个 create 参数表示的是创建主题的指令类型，在 kafka-topics.sh 脚本中对应的还有 list、describe、alter 和 delete 这4个同级别的指令类型，每个类型所需要的参数也不尽相同。

还可以通过 describe 指令类型来查看分区副本的分配细节，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/kafka --describe --topic topic-create
Topic:topic-create	PartitionCount:4	ReplicationFactor:2	Configs:
    Topic: topic-create	Partition: 0	Leader: 2	Replicas: 2,0	Isr: 2,0
    Topic: topic-create	Partition: 1	Leader: 0	Replicas: 0,1	Isr: 0,1
    Topic: topic-create	Partition: 2	Leader: 1	Replicas: 1,2	Isr: 1,2
    Topic: topic-create	Partition: 3	Leader: 2	Replicas: 2,1	Isr: 2,1
```

示例中的 Topic 和 Partition 分别表示主题名称和分区号。PartitionCount 表示主题中分区的个数，ReplicationFactor 表示副本因子，而 Configs 表示创建或修改主题时指定的参数配置。Leader 表示分区的 leader 副本所对应的 brokerId，Isr 表示分区的 ISR 集合，Replicas 表示分区的所有的副本分配情况，即AR集合，其中的数字都表示的是 brokerId。

使用 kafka-topics.sh 脚本创建主题的指令格式归纳如下：

```
kafka-topics.sh -–zookeeper <String: hosts> –create –-topic [String: topic] -–partitions <Integer: # of partitions> –replication-factor <Integer: replication factor>
```

到目前为止，创建主题时的分区副本都是按照既定的内部逻辑来进行分配的。kafka-topics.sh 脚本中还提供了一个 replica-assignment 参数来手动指定分区副本的分配方案。replica-assignment 参数的用法归纳如下：

```
--replica-assignment <String: broker_id_for_part1_replica1:  broker_id_for_ part1_replica2, broker_id_for_part2_replica1:  broker_id_for_part2_replica2, …>
```

这种方式根据分区号的数值大小按照从小到大的顺序进行排列，分区与分区之间用逗号“,”隔开，分区内多个副本用冒号“:”隔开。并且在使用 replica-assignment 参数创建主题时不需要原本必备的 partitions 和 replication-factor 这两个参数。

我们可以通过 replica-assignment 参数来创建一个与主题 topic-create 相同的分配方案的主题 topic-create-same 和不同的分配方案的主题 topic-create-diff，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-create-same --replica-assignment 2:0,0:1,1:2,2:1
Created topic "topic-create-same".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/kafka --describe --topic topic-create-same
Topic:topic-create-same	PartitionCount:4	ReplicationFactor:2	Configs:
    Topic: topic-create-same	Partition: 0	Leader: 2 Replicas: 2,0	Isr: 2,0
	    Topic: topic-create-same	Partition: 1	Leader: 0 Replicas: 0,1	Isr: 0,1
	    Topic: topic-create-same	Partition: 2	Leader: 1 Replicas: 1,2	Isr: 1,2
	    Topic: topic-create-same	Partition: 3	Leader: 2 Replicas: 2,1	Isr: 2,1

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-create-diff --replica-assignment 1:2,2:0,0:1,1:0
Created topic "topic-create-diff".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-create-diff
Topic:topic-create-diff	PartitionCount:4	ReplicationFactor:2	Configs:
	    Topic: topic-create-diff	Partition: 0	Leader: 1 Replicas: 1,2	Isr: 1,2
	    Topic: topic-create-diff	Partition: 1	Leader: 2 Replicas: 2,0	Isr: 2,0
	    Topic: topic-create-diff	Partition: 2	Leader: 0 Replicas: 0,1	Isr: 0,1
	    Topic: topic-create-diff	Partition: 3	Leader: 1 Replicas: 1,0	Isr: 1,0
```

注意同一个分区内的副本不能有重复，比如指定了0:0,1:1这种，就会报出 AdminCommand- FailedException 异常，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-create-error --replica-assignment 0:0,1,1
Error while executing topic command : Partition replica lists may not contain duplicate entries: 0
[2018-09-09 11:17:02,549] ERROR kafka.common.AdminCommandFailedException: Partition replica lists may not contain duplicate entries: 0 at …(省略若干)
```

如果分区之间所指定的副本数不同，比如0:1,0,1:0这种，就会报出 AdminOperationException 异常，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-create-error --replica-assignment 0:1,0,1:0
Error while executing topic command : Partition 1 has different replication factor: [I@5e0826e7
[2018-09-09 11:17:15,684] ERROR kafka.admin.AdminOperationException: Partition 1 has different replication factor: [I@5e0826e7 at …(省略若干)
```

当然，类似0:1,,0:1,1:0这种企图跳过一个分区的行为也是不被允许的，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka200 --create --topic topic-create-error --replica-assignment 0:1,,0:1,1:0
Error while executing topic command : For input string: ""
[2018-09-09 11:17:27,767] ERROR java.lang.NumberFormatException: For input string: "" at …(省略若干)
```

在创建主题时我们还可以通过 config 参数来设置所要创建主题的相关参数，通过这个参数可以覆盖原本的默认配置。在创建主题时可以同时设置多个参数，具体的用法归纳如下：

```
--config <String:name1=value1>  --config <String:name2=value2>
```

下面的示例使用了 config 参数来创建一个主题 topic-config：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-config --replication-factor 1 --partitions 1 --config cleanup.policy=compact --config max.message.bytes=10000
Created topic "topic-config".
```

示例中设置了 cleanup.policy 参数为 compact，以及 max.message.bytes 参数为10000，这两个参数都是主题端的配置，我们再次通过 describe 指令来查看所创建的主题信息：

```
 [root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-config
Topic:topic-config	PartitionCount:1	ReplicationFactor:1	Configs:cleanup.policy=compact,max.message.bytes=10000
	Topic: topic-config	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
```

可以看到 Configs 一栏中包含了创建时所设置的参数。我们还可以通过 ZooKeeper 客户端查看所设置的参数，对应的 ZooKeeper 节点为/config/topics/[topic]，示例如下：

```
[zk: localhost:2181/kafka(CONNECTED) 7] get /config/topics/topic-config
{"version":1,"config":{"max.message.bytes":"10000","cleanup.policy":"compact"}}
```

创建主题时对于主题名称的命名方式也很有讲究。首先是不能与已经存在的主题同名，如果创建了同名的主题就会报错。我们尝试创建一个已经存在的主题 topic-create，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-create --replication-factor 1 --partitions 1
Error while executing topic command : Topic 'topic-create' already exists.
[2018-09-08 23:04:29,542] ERROR org.apache.kafka.common.errors.TopicExists- Exception: Topic 'topic-create' already exists.
 (kafka.admin.TopicCommand$)
```

通过上面的示例可以看出，在发生命名冲突时会报出 TopicExistsException 的异常信息。在 kafka-topics.sh 脚本中还提供了一个 if-not-exists 参数，如果在创建主题时带上了这个参数，那么在发生命名冲突时将不做任何处理（既不创建主题，也不报错）。如果没有发生命名冲突，那么和不带 if-not-exists 参数的行为一样正常创建主题。我们再次尝试创建一个已经存在的主题 topic-create，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-create --replication-factor 1 --partitions 1 --if-not- exists

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-create
Topic:topic-create	PartitionCount:4	ReplicationFactor:2	Configs:
	    Topic: topic-create	Partition: 0	Leader: 2	Replicas: 2,0	Isr: 2,0
	    Topic: topic-create	Partition: 1	Leader: 0	Replicas: 0,1	Isr: 0,1
	    Topic: topic-create	Partition: 2	Leader: 2	Replicas: 1,2	Isr: 2,1
	    Topic: topic-create	Partition: 3	Leader: 2	Replicas: 2,1	Isr: 2,1
```

通过上面的示例可以看出，在添加 if-not-exists 参数之后，并没有像第一次创建主题时的那样出现“Created topic "topic-create".”的提示信息。通过 describe 指令查看主题中的分区数和副本因子数，还是同第一次创建时的一样分别为4和2，也并没有被覆盖，如此便证实了 if-not-exists 参数可以在发生命名冲突时不做任何处理。在实际应用中，如果不想在创建主题的时候跳出 TopicExistsException 的异常信息，不妨试一下这个参数。

kafka-topics.sh 脚本在创建主题时还会检测是否包含“.”或“_”字符。为什么要检测这两个字符呢？因为在 Kafka 的内部做埋点时会根据主题的名称来命名 metrics 的名称，并且会将点号“.”改成下画线“_”。假设遇到一个名称为“topic.1_2”的主题，还有一个名称为“topic_1.2”的主题，那么最后的 metrics 的名称都会为“topic_1_2”，这样就发生了名称冲突。举例如下，首先创建一个以“topic.1_2”为名称的主题，提示 WARNING 警告，之后再创建“topic_1.2”时发生 InvalidTopicException 异常。

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --replication-factor 1 --partitions 1 --topic topic.1_2
WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
Created topic "topic.1_2".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --replication-factor 1 --partitions 1 --topic topic_1.2
WARNING: Due to limitations in metric names, topics with a period ('.') or underscore ('_') could collide. To avoid issues it is best to use either, but not both.
Error while executing topic command : Topic 'topic_1.2' collides with existing topics: topic.1_2
[2018-09-09 00:21:41,113] ERROR org.apache.kafka.common.errors.InvalidTopicException: Topic 'topic_1.2' collides with existing topics: topic.1_2
 (kafka.admin.TopicCommand$)
```

> 注意要点：主题的命名同样不推荐（虽然可以这样做）使用双下画线“__”开头，因为以双下画线开头的主题一般看作 Kafka 的内部主题，比如__consumer_offsets 和 __transaction_state。主题的名称必须由大小写字母、数字、点号“.”、连接线“-”、下画线“_”组成，不能为空，不能只有点号“.”，也不能只有双点号“..”，且长度不能超过249。

Kafka 从0.10.x版本开始支持指定 broker 的机架信息（机架的名称）。如果指定了机架信息，则在分区副本分配时会尽可能地让分区副本分配到不同的机架上。指定机架信息是通过 broker 端参数 broker.rack 来配置的，比如配置当前 broker 所在的机架为“RACK1”：

```
broker.rack=RACK1
```

如果一个集群中有部分 broker 指定了机架信息，并且其余的 broker 没有指定机架信息，那么在执行 kafka-topics.sh 脚本创建主题时会报出的 AdminOperationException 的异常，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-create-rack -replication-factor 1 --partitions 1
Error while executing topic command : Not all brokers have rack information. Add --disable-rack-aware in command line to make replica assignment without rack information.
[2018-09-09 14:52:32,723] ERROR kafka.admin.AdminOperationException: Not all brokers have rack information. Add --disable-rack-aware in command line to make replica assignment without rack information.
…（省略若干）
```

此时若要成功创建主题，要么将集群中的所有 broker 都加上机架信息或都去掉机架信息，要么使用 disable-rack-aware 参数来忽略机架信息，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-create-rack -replication-factor 1 --partitions 1 --disable-rack-aware
Created topic "topic-create-rack".
```

如果集群中的所有 broker 都有机架信息，那么也可以使用 disable-rack-aware 参数来忽略机架信息对分区副本的分配影响，有关分区副本的分配细节会在下一节中做详细介绍。

本节开头就提及了 kafka-topics.sh 脚本实质上是调用了 kafka.admin.TopicCommand 类，通过向 TopicCommand 类中传入一些关键参数来实现主题的管理。我们也可以直接调用 TopicCommand 类中的 main() 函数来直接管理主题，比如这里创建一个分区数为1、副本因子为1的主题 topic-create-api，如代码清单16-1所示。

```
//代码清单16-1 使用TopicCommand创建主题
public static void createTopic(){
    String[] options = new String[]{
            "--zookeeper", "localhost:2181/kafka",
            "--create",
            "--replication-factor", "1",
            "--partitions", "1",
            "--topic", "topic-create-api"
    };
    kafka.admin.TopicCommand.main(options);
}
```

使用这种方式需要添加相应的 Maven 依赖：

```
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka_2.11</artifactId>
    <version>2.0.0</version>
</dependency>
```

可以看到这种方式与使用 kafka-topics.sh 脚本的方式并无太大差别，可以使用这种方式集成到自动化管理系统中来创建相应的主题。当然这种方式也可以适用于对主题的删、改、查等操作的实现，只需修改对应的参数即可。不过更推荐使用第20节中介绍的 KafkaAdminClient 来代替这种实现方式。



# <a name="83">分区副本的分配</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

上一节中多处提及了分区副本的分配，读者对此或许有点迷惑，在生产者和消费者中也都有分区分配的概念。生产者的分区分配是指为每条消息指定其所要发往的分区，消费者中的分区分配是指为消费者指定其可以消费消息的分区，而这里的分区分配是指为集群制定创建主题时的分区副本分配方案，即在哪个 broker 中创建哪些分区的副本。

在创建主题时，如果使用了 replica-assignment 参数，那么就按照指定的方案来进行分区副本的创建；如果没有使用 replica-assignment 参数，那么就需要按照内部的逻辑来计算分配方案了。使用 kafka-topics.sh 脚本创建主题时的内部分配逻辑按照机架信息划分成两种策略：未指定机架信息和指定机架信息。如果集群中所有的 broker 节点都没有配置 broker.rack 参数，或者使用 disable-rack-aware 参数来创建主题，那么采用的就是未指定机架信息的分配策略，否则采用的就是指定机架信息的分配策略。

首先看一下未指定机架信息的分配策略，具体的实现涉及代码的逻辑细节，未指定机架信息的分配策略比较容易理解，这里通过源码来逐一进行分析。所对应的具体实现为 kafka.admin.AdminUtils.scala 文件中的 assignReplicasToBrokersRackUnaware() 方法，该方法的内容如下：

```
private def assignReplicasToBrokersRackUnaware(
    nPartitions: Int,         //分区数
    replicationFactor: Int,  //副本因子
    brokerList: Seq[Int],    //集群中broker列表
    fixedStartIndex: Int,    //起始索引，即第一个副本分配的位置，默认值为-1
    startPartitionId: Int):  //起始分区编号，默认值为-1
Map[Int, Seq[Int]] = { 
  val ret = mutable.Map[Int, Seq[Int]]() //保存分配结果的集合
  val brokerArray = brokerList.toArray    //brokerId的列表
//如果起始索引fixedStartIndex小于0，则根据broker列表长度随机生成一个，以此来保证是
//有效的brokerId
  val startIndex = if (fixedStartIndex >= 0) fixedStartIndex
    else rand.nextInt(brokerArray.length)
  //确保起始分区号不小于0
  var currentPartitionId = math.max(0, startPartitionId)
  //指定了副本的间隔，目的是为了更均匀地将副本分配到不同的broker上
  var nextReplicaShift = if (fixedStartIndex >= 0) fixedStartIndex
    else rand.nextInt(brokerArray.length)
  //轮询所有分区，将每个分区的副本分配到不同的broker上
  for (_ <- 0 until nPartitions) {
    if (currentPartitionId > 0 && (currentPartitionId % brokerArray.length == 0))
      nextReplicaShift += 1
    val firstReplicaIndex = (currentPartitionId + startIndex) % brokerArray.length
    val replicaBuffer = mutable.ArrayBuffer(brokerArray(firstReplicaIndex))
    //保存该分区所有副本分配的broker集合
    for (j <- 0 until replicationFactor - 1)
      replicaBuffer += brokerArray(
        replicaIndex(firstReplicaIndex, nextReplicaShift, 
          j, brokerArray.length)) //为其余的副本分配broker
    //保存该分区所有副本的分配信息
    ret.put(currentPartitionId, replicaBuffer)
    //继续为下一个分区分配副本
    currentPartitionId += 1
  }
  ret
}
```

该方法参数列表中的 fixedStartIndex 和 startPartitionId 值是从上游的方法中调用传下来的，都是-1，分别表示第一个副本分配的位置和起始分区编号。assignReplicasToBrokersRackUnaware () 方法的核心是遍历每个分区 partition，然后从 brokerArray（brokerId的列表）中选取 replicationFactor 个 brokerId 分配给这个 partition。

该方法首先创建一个可变的 Map用来存放该方法将要返回的结果，即分区 partition 和分配副本的映射关系。由于 fixedStartIndex 为-1，所以 startIndex 是一个随机数，用来计算一个起始分配的 brokerId，同时又因为 startPartitionId 为-1，所以 currentPartitionId 的值为0，可见默认情况下创建主题时总是从编号为0的分区依次轮询进行分配。

nextReplicaShift 表示下一次副本分配相对于前一次分配的位移量，从字面上理解有点绕口。举个例子：假设集群中有3个 broker 节点，对应于代码中的 brokerArray，创建的某个主题中有3个副本和6个分区，那么首先从 partitionId（partition的编号）为0的分区开始进行分配，假设第一次计算（由 rand.nextInt(brokerArray.length)随机产生）得到的 nextReplicaShift 值为1，第一次随机产生的 startIndex 值为2，那么 partitionId 为0的第一个副本的位置（这里指的是 brokerArray 的数组下标）firstReplicaIndex = (currentPartitionId + startIndex) % brokerArray.length= (0+2)%3=2，第二个副本的位置为 replicaIndex(firstReplicaIndex, nextReplicaShift, j, brokerArray. length) = replicaIndex(2, nextReplicaShift+1,0, 3)=?，这里引入了一个新的方法 replicaIndex()，不过这个方法很简单，具体如下：

```
private def replicaIndex(firstReplicaIndex: Int, secondReplicaShift: Int, 
                         replicaIndex: Int, nBrokers: Int): Int = {
  val shift = 1 + (secondReplicaShift + replicaIndex) % (nBrokers - 1)
  (firstReplicaIndex + shift) % nBrokers
}
```

继续计算 replicaIndex(2,nextReplicaShift+1,0,3) = replicaIndex(2,2,0,3) = (2+(1+(2+0) % (3-1) ) ) % 3 = 0。继续计算下一个副本的位置 replicaIndex(2,2,1,3) = (2+(1+(2+1)%(3-1)))%3 = 1。所以 partitionId 为0的副本分配位置列表为[2,0,1]，如果 brokerArray 正好是从0开始编号的，也正好是顺序不间断的，即 brokerArray 为[0,1,2]，那么当前 partitionId 为0的副本分配策略为[2,0,1]。如果 brokerId 不是从0开始的，也不是顺序的（有可能之前集群的其中几个 broker 下线了），最终的 brokerArray 为[2,5,8]，那么 partitionId 为0的分区的副本分配策略为[8,2,5]。为了便于说明问题，可以简单假设 brokerArray 就是[0,1,2]。

同样计算下一个分区，即 partitionId 为1的副本分配策略。此时 nextReplicaShift 的值还是2，没有满足自增的条件。这个分区的 firstReplicaIndex=(1+2)%3=0。第二个副本的位置 replicaIndex(0,2,0,3) = (0+(1+(2+0)%(3-1)))%3 = 1，第三个副本的位置 replicaIndex(0,2,1,3) = 2，最终 partitionId 为2的分区分配策略为[0,1,2]。 依次类推，更多的分配细节可以参考下面的示例，topic-test2 的分区分配策略和上面陈述的一致：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-test2 --replication-factor 3 --partitions 6
Created topic "topic-test2".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka –-describe --topic topic-test2
Topic:topic-test2   PartitionCount:6    ReplicationFactor:3 Configs:
    Topic: topic-test2  Partition: 0    Leader: 2   Replicas: 2,0,1 Isr: 2,0,1
    Topic: topic-test2  Partition: 1    Leader: 0   Replicas: 0,1,2 Isr: 0,1,2
    Topic: topic-test2  Partition: 2    Leader: 1   Replicas: 1,2,0 Isr: 1,2,0
    Topic: topic-test2  Partition: 3    Leader: 2   Replicas: 2,1,0 Isr: 2,1,0
    Topic: topic-test2  Partition: 4    Leader: 0   Replicas: 0,2,1 Isr: 0,2,1
    Topic: topic-test2  Partition: 5    Leader: 1   Replicas: 1,0,2 Isr: 1,0,2
```

我们无法预先获知 startIndex 和 nextReplicaShift 的值，因为都是随机产生的。startIndex 和 nextReplicaShift 的值可以通过最终的分区分配方案来反推，比如上面的 topic-test2，第一个分区（即 partitionId=0 的分区）的第一个副本为2，那么可由2 = (0+startIndex)%3推断出 startIndex 为2。之所以 startIndex 选择随机产生，是因为这样可以在多个主题的情况下尽可能地均匀分布分区副本，如果这里固定为一个特定值，那么每次的第一个副本都是在这个 broker 上，进而导致少数几个 broker 所分配到的分区副本过多而其余 broker 分配到的分区副本过少，最终导致负载不均衡。尤其是某些主题的副本数和分区数都比较少，甚至都为1的情况下，所有的副本都落到了那个指定的 broker 上。与此同时，在分配时位移量 nextReplicaShift 也可以更好地使分区副本分配得更加均匀。

相比较而言，指定机架信息的分配策略比未指定机架信息的分配策略要稍微复杂一些，但主体思想并没相差很多，只是将机架信息作为附加的参考项。假设目前有3个机架 rack1、rack2 和 rack3，Kafka 集群中的9个 broker 点都部署在这3个机架之上，机架与 broker 节点的对照关系如下：

```
rack1: 0, 1, 2
rack2: 3, 4, 5
rack3: 6, 7, 8
```

如果不考虑机架信息，那么对照 assignReplicasToBrokersRackUnaware() 方法里的 brokerArray 变量的值为[0, 1, 2, 3, 4, 5 6, 7, 8]。指定基架信息的 assignReplicasToBrokersRackAware() 方法里的 brokerArray 的值在这里就会被转换为[0, 3, 6, 1, 4, 7, 2, 5, 8]，显而易见，这是轮询各个机架而产生的结果，如此新的 brokerArray（确切地说是 arrangedBrokerList）中包含了简单的机架分配信息。之后的步骤也和 assignReplicasToBrokersRackUnaware() 方法类似，同样包含 startIndex、currentPartiionId、nextReplicaShift 的概念，循环为每一个分区分配副本。分配副本时，除了处理第一个副本，其余的也调用 replicaIndex() 方法来获得一个 broker，但这里和 assignReplicasToBrokersRackUnaware() 不同的是，这里不是简单地将这个 broker 添加到当前分区的副本列表之中，还要经过一层筛选，满足以下任意一个条件的 broker 不能被添加到当前分区的副本列表之中：

- 如果此 broker 所在的机架中已经存在一个 broker 拥有该分区的副本，并且还有其他的机架中没有任何一个 broker 拥有该分区的副本。
- 如果此 broker 中已经拥有该分区的副本，并且还有其他 broker 中没有该分区的副本。

当创建一个主题时，无论通过 kafka-topics.sh 脚本，还是通过其他方式（比如第30节中介绍的 KafkaAdminClient）创建主题时，实质上是在 ZooKeeper 中的/brokers/topics 节点下创建与该主题对应的子节点并写入分区副本分配方案，并且在/config/topics/节点下创建与该主题对应的子节点并写入主题相关的配置信息（这个步骤可以省略不执行）。而 Kafka 创建主题的实质性动作是交由控制器异步去完成的。

知道了 kafka-topics.sh 脚本的实质之后，我们可以直接使用 ZooKeeper 的客户端在/brokers/topics 节点下创建相应的主题节点并写入预先设定好的分配方案，这样就可以创建一个新的主题了。这种创建主题的方式还可以绕过一些原本使用 kafka-topics.sh 脚本创建主题时的一些限制，比如分区的序号可以不用从0开始连续累加了。首先我们通过 ZooKeeper 客户端创建一个除了与主题 topic-create 名称不同其余都相同的主题 topic-create-zk，示例如下：

```
[zk: localhost:2181/kafka(CONNECTED) 29] create /brokers/topics/topic-create-zk {"version":1,"partitions":{"2":[1,2],"1":[0,1],"3":[2,1],"0":[2,0]}}
Created /brokers/topics/topic-create-zk
```

通过查看主题 topic-create-zk 的分配情况，可以看到与主题 topic-create 的信息没有什么差别。

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-create-zk
Topic:topic-create-zk	PartitionCount:4	ReplicationFactor:2	Configs:
Topic: topic-create-zk	Partition: 0	Leader: 2 Replicas: 2,0	Isr: 2,0
	    Topic: topic-create-zk	Partition: 1	Leader: 0 Replicas: 0,1	Isr: 0,1
	    Topic: topic-create-zk	Partition: 2	Leader: 1 Replicas: 1,2	Isr: 1,2
	    Topic: topic-create-zk	Partition: 3	Leader: 2 Replicas: 2,1	Isr: 2,1
```

我们再创建一个另类的主题，分配情况和主题 topic-create 一样，唯独分区号已经与主题 topic-create-special 大相径庭，示例如下：

```
[zk: localhost:2181/kafka (CONNECTED) 31] create /brokers/topics/topic-create- special {"version":1,"partitions":{"10":[1,2],"21":[0,1],"33":[2,1],"40":[2,0]}}
Created /brokers/topics/topic-create-special

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-create-special
Topic:topic-create-special	PartitionCount:4	ReplicationFactor:2	Configs:
	Topic: topic-create-special	Partition: 10 Leader: 1 Replicas: 1,2	Isr: 1,2
	Topic: topic-create-special	Partition: 21 Leader: 0 Replicas: 0,1	Isr: 0,1
	Topic: topic-create-special	Partition: 33 Leader: 2 Replicas: 2,1	Isr: 2,1
	Topic: topic-create-special	Partition: 40 Leader: 2 Replicas: 2,0	Isr: 2,0
```

可以看到分区号为10、21、33和40，而通过单纯地使用 kafka-topics.sh 脚本是无法实现的。不过这种方式也只是一些实战方面上的技巧，笔者还是建议使用更加正统的 kafka-topics.sh 脚本或 KafkaAdminClient 来管理相应的主题。



# <a name="84">查看主题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

第16节中提及了 kafka-topics.sh 脚本有5种指令类型：create、list、describe、alter 和 delete。其中 list 和 describe 指令可以用来方便地查看主题信息，在前面的内容中我们已经接触过了 describe 指令的用法，本节会对其做更细致的讲述。

通过 list 指令可以查看当前所有可用的主题，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka –list
__consumer_offsets
topic-create
topic-demo
topic-config
```

前面的章节我们都是通过 describe 指令来查看单个主题信息的，如果不使用 --topic 指定主题，则会展示出所有主题的详细信息。--topic 还支持指定多个主题，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-create,topic-demo
Topic:topic-create	PartitionCount:4	ReplicationFactor:2	Configs:
	Topic: topic-create	Partition: 0	Leader: 2	Replicas: 2,0	Isr: 2,0
	Topic: topic-create	Partition: 1	Leader: 0	Replicas: 0,1	Isr: 0,1
	Topic: topic-create	Partition: 2	Leader: 2	Replicas: 1,2	Isr: 2,1
	Topic: topic-create	Partition: 3	Leader: 2	Replicas: 2,1	Isr: 2,1
Topic:topic-demo	PartitionCount:4	ReplicationFactor:3	Configs:
	Topic: topic-demo	Partition: 0	Leader: 2	Replicas: 2,1,0	Isr: 2,0,1
	Topic: topic-demo	Partition: 1	Leader: 2	Replicas: 0,2,1	Isr: 2,0,1
	Topic: topic-demo	Partition: 2	Leader: 2	Replicas: 1,0,2	Isr: 2,0,1
	Topic: topic-demo	Partition: 3	Leader: 2	Replicas: 2,0,1	Isr: 2,0,1
```

在使用 describe 指令查看主题信息时还可以额外指定 topics-with-overrides、under-replicated-partitions 和 unavailable-partitions 这三个参数来增加一些附加功能。

增加 topics-with-overrides 参数可以找出所有包含覆盖配置的主题，它只会列出包含了与集群不一样配置的主题。注意使用 topics-with-overrides 参数时只显示原本只使用 describe 指令的第一行信息，参考示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topics-with-overrides
Topic:__consumer_offsets	PartitionCount:50	ReplicationFactor:1	Configs:segment.bytes=104857600,cleanup.policy=compact,compression.type=producer
Topic:topic-config	PartitionCount:1	ReplicationFactor:1	Configs:cleanup.policy=compact,max.message.bytes=10000
```

under-replicated-partitions 和 unavailable-partitions 参数都可以找出有问题的分区。通过 under-replicated-partitions 参数可以找出所有包含失效副本的分区。包含失效副本的分区可能正在进行同步操作，也有可能同步发生异常，此时分区的 ISR 集合小于AR集合。对于通过该参数查询到的分区要重点监控，因为这很可能意味着集群中的某个 broker 已经失效或同步效率降低等。

举个例子，参照主题 topic-create 的环境，我们将集群中的 node2 节点下线，之后再通过这个参数来查看 topic-create 的信息，参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-create --under-replicated-partitions
	Topic: topic-create	Partition: 1	Leader: 0	Replicas: 0,1	Isr: 0
	Topic: topic-create	Partition: 2	Leader: 2	Replicas: 1,2	Isr: 2
	Topic: topic-create	Partition: 3	Leader: 2	Replicas: 2,1	Isr: 2
```

我们再将 node2 节点恢复，执行同样的命令，可以看到没有任何信息显示：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-create --under-replicated-partitions
```

通过 unavailable-partitions 参数可以查看主题中没有 leader 副本的分区，这些分区已经处于离线状态，对于外界的生产者和消费者来说处于不可用的状态。

举个例子，参考主题 topic-create 的环境，我们将集群中的 node2 和 node3 节点下线，之后再通过这个参数来查看 topic-create 的信息，参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-create --unavailable-partitions
	Topic: topic-create	Partition: 2	Leader: -1	Replicas: 1,2	Isr: 1
	Topic: topic-create	Partition: 3	Leader: -1	Replicas: 2,1	Isr: 1

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-create 
Topic:topic-create	PartitionCount:4	ReplicationFactor:2	Configs:
	Topic: topic-create	Partition: 0	Leader: 0	Replicas: 2,0	Isr: 0
	Topic: topic-create	Partition: 1	Leader: 0	Replicas: 0,1	Isr: 0
	Topic: topic-create	Partition: 2	Leader: -1	Replicas: 1,2	Isr: 1
	Topic: topic-create	Partition: 3	Leader: -1	Replicas: 2,1	Isr: 1
```

我们再将 node2 和 node3 恢复，执行同样的命令，可以看到没有任何信息：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-create --unavailable-partitions
```

# <a name="85">修改主题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

当一个主题被创建之后，依然允许我们对其做一定的修改，比如修改分区个数、修改配置等，这个修改的功能就是由 kafka-topics.sh 脚本中的 alter 指令提供的。

我们首先来看如何增加主题的分区数。以前面的主题 topic-config 为例，当前分区数为1，修改为3，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --alter --topic topic-config --partitions 3
WARNING: If partitions are increased for a topic that has a key, the partition logic or ordering of the messages will be affected
Adding partitions succeeded!

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/kafka --describe --topic topic-config
Topic:topic-config	PartitionCount:3	ReplicationFactor:1	Configs:
    Topic: topic-config	Partition: 0	Leader: 2	Replicas: 2	Isr: 2
    Topic: topic-config	Partition: 1	Leader: 0	Replicas: 0	Isr: 0
    Topic: topic-config	Partition: 2	Leader: 1	Replicas: 1	Isr: 1
```

注意上面提示的告警信息：当主题中的消息包含 key 时（即 key 不为 null），根据 key 计算分区的行为就会受到影响。当 topic-config 的分区数为1时，不管消息的 key 为何值，消息都会发往这一个分区；当分区数增加到3时，就会根据消息的 key 来计算分区号，原本发往分区0的消息现在有可能会发往分区1或分区2。如此还会影响既定消息的顺序，所以在增加分区数时一定要三思而后行。对于基于 key 计算的主题而言，建议在一开始就设置好分区数量，避免以后对其进行调整。

目前 Kafka 只支持增加分区数而不支持减少分区数。比如我们再将主题 topic-config 的分区数修改为1，就会报出 InvalidPartitionException 的异常，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --alter --topic topic-config --partitions 1
WARNING: If partitions are increased for a topic that has a key, the partition logic or ordering of the messages will be affected
Error while executing topic command : The number of partitions for a topic can only be increased. Topic topic-config currently has 3 partitions, 1 would not be an increase.
[2018-09-10 19:28:40,031] ERROR org.apache.kafka.common.errors.InvalidPartitionsException: The number of partitions for a topic can only be increased. Topic topic-config currently has 3 partitions, 1 would not be an increase.
 (kafka.admin.TopicCommand$)
```

> 为什么不支持减少分区？ 按照 Kafka 现有的代码逻辑，此功能完全可以实现，不过也会使代码的复杂度急剧增大。实现此功能需要考虑的因素很多，比如删除的分区中的消息该如何处理？如果随着分区一起消失则消息的可靠性得不到保障；如果需要保留则又需要考虑如何保留。直接存储到现有分区的尾部，消息的时间戳就不会递增，如此对于 Spark、Flink 这类需要消息时间戳（事件时间）的组件将会受到影响；如果分散插入现有的分区，那么在消息量很大的时候，内部的数据复制会占用很大的资源，而且在复制期间，此主题的可用性又如何得到保障？与此同时，顺序性问题、事务性问题，以及分区和副本的状态机切换问题都是不得不面对的。反观这个功能的收益点却是很低的，如果真的需要实现此类功能，则完全可以重新创建一个分区数较小的主题，然后将现有主题中的消息按照既定的逻辑复制过去即可。

在创建主题时有一个 if-not-exists 参数来忽略一些异常，在这里也有对应的参数，如果所要修改的主题不存在，可以通过 if-exists 参数来忽略异常。下面修改一个不存在的主题 topic-unknown 的分区，会报出错误信息“Topic topic-unknown does not exist”，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --alter --topic topic-unknown --partitions 4
Error while executing topic command : Topic topic-unknown does not exist on ZK path localhost:2181/kafka
[2018-09-11 11:14:55,458] ERROR java.lang.IllegalArgumentException: Topic topic-unknown does not exist on ZK path localhost:2181/kafka
	at kafka.admin.TopicCommand$.alterTopic(TopicCommand.scala:123)
	at kafka.admin.TopicCommand$.main(TopicCommand.scala:65)
	at kafka.admin.TopicCommand.main(TopicCommand.scala)
 (kafka.admin.TopicCommand$)

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --alter --topic topic-unknown --partitions 4 --if-exists
```

除了修改分区数，我们还可以使用 kafka-topics.sh 脚本的 alter 指令来变更主题的配置。在创建主题的时候我们可以通过 config 参数来设置所要创建主题的相关参数，通过这个参数可以覆盖原本的默认配置。在创建完主题之后，我们还可以通过 alter 指令配合 config 参数增加或修改一些配置以覆盖它们配置原有的值。

下面的示例中演示了将主题 topic-config 的 max.message.bytes 配置值从10000修改为20000，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper 1ocalhost:2181/ kafka --describe --topic topic-config
Topic:topic-config	PartitionCount:1	ReplicationFactor:1	Configs:cleanup.policy=compact,max.message.bytes=10000
		Topic: topic-config	Partition: 0	Leader: 2	Replicas: 2	Isr: 2

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper 1ocalhost:2181/ kafka --alter --topic topic-config --config max.message.bytes=20000
WARNING: Altering topic configuration from this script has been deprecated and may be removed in future releases.
         Going forward, please use kafka-configs.sh for this functionality
Updated config for topic "topic-config".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper 1ocalhost:2181/ kafka --describe --topic topic-config
Topic:topic-config	PartitionCount:1	ReplicationFactor:1	Configs:max.message.bytes=20000,cleanup.policy=compact
		Topic: topic-config	Partition: 0	Leader: 2	Replicas: 2	Isr: 2
```

我们再次覆盖主题 topic-config 的另一个配置 segment.bytes（看上去相当于增加动作），示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --alter --topic topic-config --config segment.bytes=1048577
WARNING: Altering topic configuration from this script has been deprecated and may be removed in future releases.
         Going forward, please use kafka-configs.sh for this functionality
Updated config for topic "topic-config".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-config
Topic:topic-config	PartitionCount:1	ReplicationFactor:1	Configs:segment.bytes=1048577,cleanup.policy=compact,max.message.bytes=20000
	Topic: topic-config	Partition: 0	Leader: 2	Replicas: 2	Isr: 2
```

我们可以通过 delete-config 参数来删除之前覆盖的配置，使其恢复原有的默认值。下面的示例将主题 topic-config 中所有修改过的3个配置都删除：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --alter --topic topic-config --delete-config segment.bytes
WARNING: Altering topic configuration from this script has been deprecated and may be removed in future releases.
         Going forward, please use kafka-configs.sh for this functionality
Updated config for topic "topic-config".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --alter --topic topic-config --delete-config max.message.bytes --delete-config cleanup.policy
WARNING: Altering topic configuration from this script has been deprecated and may be removed in future releases.
         Going forward, please use kafka-configs.sh for this functionality
Updated config for topic "topic-config".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-config
Topic:topic-config	PartitionCount:1	ReplicationFactor:1	Configs:
	Topic: topic-config	Partition: 0	Leader: 2	Replicas: 2	Isr: 2
```

注意到在变更（增、删、改）配置的操作执行之后都会提示一段告警信息，指明了使用 kafka-topics.sh 脚本的 alter 指令来变更主题配置的功能已经过时（deprecated），将在未来的版本中删除，并且推荐使用 kafka-configs.sh 脚本来实现相关功能。

# <a name="86">删除主题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

如果确定不再使用一个主题，那么最好的方式是将其删除，这样可以释放一些资源，比如磁盘、文件句柄等。kafka-topics.sh 脚本中的 delete 指令就可以用来删除主题，比如删除一个主题 topic-delete：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --delete --topic topic-delete
Topic topic-delete is marked for deletion.
Note: This will have no impact if delete.topic.enable is not set to true.
```

可以看到在执行完删除命令之后会有相关的提示信息，这个提示信息和 broker 端配置参数 delete.topic.enable 有关。必须将 delete.topic.enable 参数配置为 true 才能够删除主题，这个参数的默认值就是 true，如果配置为 false，那么删除主题的操作将会被忽略。在实际生产环境中，建议将这个参数的值设置为 true。

如果要删除的主题是 Kafka 的内部主题，那么删除时就会报错。截至 Kafka 2.0.0，Kafka 的内部一共包含2个主题，分别为__consumer_offsets和__transaction_state。下面的示例中尝试删除内部主题__consumer_offsets：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --delete --topic __consumer_offsets
Error while executing topic command : Topic __consumer_offsets is a kafka internal topic and is not allowed to be marked for deletion.
[2018-09-11 11:30:32,635] ERROR kafka.admin.AdminOperationException: Topic __consumer_offsets is a kafka internal topic and is not allowed to be marked for deletion.
…（省略若干项）
```

尝试删除一个不存在的主题也会报错。比如下面的示例中尝试删除一个不存在的主题 topic-unknown：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --delete --topic topic-unknown
Error while executing topic command : Topic topic-unknown does not exist on ZK path localhost:2181/kafka
[2018-09-11 23:43:22,186] ERROR java.lang.IllegalArgumentException: Topic topic-unknown does not exist on ZK path localhost:2181/kafka
…（省略若干项）
```

这里同 alter 指令一样，也可以通过 if-exists 参数来忽略异常，参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --delete --topic topic-unknown --if-exists
[root@node1 kafka_2.11-2.0.0]#
```

使用 kafka-topics.sh 脚本删除主题的行为本质上只是在 ZooKeeper 中的/admin/delete_topics路径下创建一个与待删除主题同名的节点，以此标记该主题为待删除的状态。与创建主题相同的是，真正删除主题的动作也是由 Kafka 的控制器负责完成的。

了解这一原理之后，我们可以直接通过 ZooKeeper 的客户端来删除主题。下面示例中使用 ZooKeeper 客户端 zkCli.sh 来删除主题 topic-delete：

```
[zk: localhost:2181/kafka (CONNECTED) 15] create /admin/delete_topics/topic- delete ""
Created /admin/delete_topics/topic-delete
```

我们还可以通过手动的方式来删除主题。主题中的元数据存储在 ZooKeeper 中的/brokers/topics和/config/topics路径下，主题中的消息数据存储在 log.dir 或 log.dirs 配置的路径下，我们只需要手动删除这些地方的内容即可。下面的示例中演示了如何删除主题 topic-delete，总共分3个步骤，第一步和第二步的顺序可以互换。

第一步，删除 ZooKeeper 中的节点/config/topics/topic-delete。

```
[zk: localhost:2181/kafka (CONNECTED) 7] delete /config/topics/topic-delete
```

第二步，删除 ZooKeeper 中的节点/brokers/topics/topic-delete 及其子节点。

```
[zk: localhost:2181/kafka (CONNECTED) 8] rmr /brokers/topics/topic-delete
```

第三步，删除集群中所有与主题 topic-delete 有关的文件。

```
#集群中的各个broker节点中执行rm -rf /tmp/kafka-logs/topic-delete*命令来删除与主题topic-delete有关的文件
[root@node1 kafka_2.11-2.0.0]# rm -rf /tmp/kafka-logs/topic-delete*
[root@node2 kafka_2.11-2.0.0]# rm -rf /tmp/kafka-logs/topic-delete*
[root@node3 kafka_2.11-2.0.0]# rm -rf /tmp/kafka-logs/topic-delete*
```

注意，删除主题是一个不可逆的操作。一旦删除之后，与其相关的所有消息数据会被全部删除，所以在执行这一操作的时候也要三思而后行。

介绍到这里，基本上 kafka-topics.sh 脚本的使用也就讲完了，为了方便读者查阅，下表列出了所有 kafka-topics.sh 脚本中的参数。读者也可以通过执行无任何参数的 kafka-topics.sh 脚本，或者执行 kafka-topics.sh –help 来查看帮助信息。

| 参 数 名 称                   | 释 义                                                        |
| ----------------------------- | ------------------------------------------------------------ |
| alter                         | 用于修改主题，包括分区数及主题的配置                         |
| config <键值对>               | 创建或修改主题时，用于设置主题级别的参数                     |
| create                        | 创建主题                                                     |
| delete                        | 删除主题                                                     |
| delete-config <配置名称>      | 删除主题级别被覆盖的配置                                     |
| describe                      | 查看主题的详细信息                                           |
| disable-rack-aware            | 创建主题时不考虑机架信息                                     |
| help                          | 打印帮助信息                                                 |
| if-exists                     | 修改或删除主题时使用，只有当主题存在时才会执行动作           |
| if-not-exists                 | 创建主题时使用，只有主题不存在时才会执行动作                 |
| list                          | 列出所有可用的主题                                           |
| partitions <分区数>           | 创建主题或增加分区时指定分区数                               |
| replica-assignment <分配方案> | 手工指定分区副本分配方案                                     |
| replication-factor <副本数>   | 创建主题时指定副本因子                                       |
| topic <主题名称>              | 指定主题名称                                                 |
| topics-with-overrides         | 使用 describe 查看主题信息时，只展示包含覆盖配置的主题       |
| unavailable-partitions        | 使用 describe 查看主题信息时，只展示包含没有 leader 副本的分区 |
| under-replicated-partitions   | 使用 describe 查看主题信息时，只展示包含失效副本的分区       |
| zookeeper                     | 指定连接的 ZooKeeper 地址信息（必填项）                      |



# <a name="87">配置管理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

kafka-configs.sh 脚本是专门用来对配置进行操作的，这里的操作是指在运行状态下修改原有的配置，如此可以达到动态变更的目的。kafka-configs.sh 脚本包含变更配置 alter 和查看配置 describe 这两种指令类型。同使用 kafka-topics.sh 脚本变更配置的原则一样，增、删、改的行为都可以看作变更操作，不过 kafka-configs.sh 脚本不仅可以支持操作主题相关的配置，还可以支持操作 broker、用户和客户端这3个类型的配置。

kafka-configs.sh 脚本使用 entity-type 参数来指定操作配置的类型，并且使用 entity-name 参数来指定操作配置的名称。比如查看主题 topic-config 的配置可以按如下方式执行：

```
bin/kafka-configs.sh --zookeeper localhost:2181/kafka --describe --entity-type topics --entity-name topic-config
```

--describe 指定了查看配置的指令动作，--entity-type 指定了查看配置的实体类型，--entity-name 指定了查看配置的实体名称。entity-type 只可以配置4个值：topics、brokers 、clients 和 users，entity-type 与 entity-name 的对应关系如下表所示。

| entity-type 的释义                | entity-name 的释义                                           |
| --------------------------------- | ------------------------------------------------------------ |
| 主题类型的配置，取值为 topics     | 指定主题的名称                                               |
| broker 类型的配置，取值为 brokers | 指定 brokerId 值，即 broker 中 broker.id 参数配置的值        |
| 客户端类型的配置，取值为 clients  | 指定 clientId 值，即 KafkaProducer 或 KafkaConsumer 的 client.id 参数配置的值 |
| 用户类型的配置，取值为 users      | 指定用户名                                                   |

使用 alter 指令变更配置时，需要配合 add-config 和 delete-config 这两个参数一起使用。add-config 参数用来实现配置的增、改，即覆盖原有的配置；delete-config 参数用来实现配置的删，即删除被覆盖的配置以恢复默认值。

下面的示例演示了 add-config 参数的用法，覆盖了主题 topic-config 的两个配置 cleanup.policy 和 max.message.bytes（示例执行之前主题 topic-config 无任何被覆盖的配置）：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost:2181/ kafka --alter --entity-type topics --entity-name topic-config --add-config  cleanup.policy=compact,max.message.bytes=10000
Completed Updating config for entity: topic 'topic-config'.

[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost:2181/ kafka --describe --entity-type topics --entity-name topic-config
Configs for topic 'topic-config' are max.message.bytes=10000,cleanup.policy= compact

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-config --topics-with-overrides
Topic:topic-config	PartitionCount:3	ReplicationFactor:1	Configs:max.message.bytes=10000,cleanup.policy=compact
```

上面示例中还使用了两种方式来查看主题 topic-config 中配置信息，注意比较这两者之间的差别。

使用 delete-config 参数删除配置时，同 add-config 参数一样支持多个配置的操作，多个配置之间用逗号“,”分隔，下面的示例中演示了如何删除上面刚刚增加的主题配置：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost:2181/ kafka --alter --entity-type topics --entity-name topic-config --delete-config  cleanup.policy,max.message.bytes
Completed Updating config for entity: topic 'topic-config'.

[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost:2181/ kafka --describe --entity-type topics --entity-name topic-config
Configs for topic 'topic-config' are
```

使用 kafka-configs.sh 脚本来变更（alter）配置时，会在 ZooKeeper 中创建一个命名形式为/config/<entity-type>/<entity-name>的节点，并将变更的配置写入这个节点，比如对于主题 topic-config 而言，对应的节点名称为/config/topics/topic-config，节点中的数据内容为：

```
[zk: localhost:2181/kafka (CONNECTED) 1] get /config/topics/topic-config
{"version":1,"config":{"cleanup.policy":"compact","max.message.bytes":"10000"}}
```

可以推导出节点内容的数据格式为：

```
{"version":1,"config":{<property-name>:<property-value>}}
```

其中 property-name 代表属性名，property-value 代表属性值。增加配置实际上是往节点内容中添加属性的键值对，修改配置是在节点内容中修改相应属性的属性值，删除配置是删除相应的属性键值对。

变更配置时还会在 ZooKeeper 中的/config/changes/节点下创建一个以“config_change_”为前缀的持久顺序节点（PERSISTENT_SEQUENTIAL），节点命名形式可以归纳为/config/changes/config_change_<seqNo>。比如示例中的主题 topic-config 与此对应的节点名称和节点内容如下：

```
[zk: localhost:2181/kafka (CONNECTED) 3] get 
      /config/changes/config_change_0000000010
{"version":2,"entity_path":"topics/topic-config"}
```

seqNo 是一个单调递增的10位数字的字符串，不足位则用0补齐。

查看（describe）配置时，就是从/config/<entity-type>/<entity-name>节点中获取相应的数据内容。如果使用 kafka-configs.sh 脚本查看配置信息时没有指定 entity-name 参数的值，则会查看 entity-type 所对应的所有配置信息。示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost:2181/ kafka --describe --entity-type topics
Configs for topic 'topic-config' are 
      cleanup.policy=compact,max.message.bytes=20000
Configs for topic 'topic-create' are 
Configs for topic '__consumer_offsets' are 
      segment.bytes=104857600,cleanup.policy=compact,compression.type=producer
Configs for topic 'topic-demo' are
```

# <a name="88">主题端参数</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

与主题相关的所有配置参数在 broker 层面都有对应参数，比如主题端参数 cleanup. policy 对应 broker 层面的 log.cleanup.policy。如果没有修改过主题的任何配置参数，那么就会使用 broker 端的对应参数作为其默认值。可以在创建主题时覆盖相应参数的默认值，也可以在创建完主题之后变更相应参数的默认值。比如在创建主题的时候没有指定 cleanup.policy 参数的值，那么就使用 log.cleanup.policy 参数所配置的值作为 cleanup.policy 的值。

与主题相关的参数也有很多，由于篇幅限制，在前面的配置变更的示例中难以一一列出所有的参数，但是从配置变更的角度而言，其操作方式都是一样的。为了便于读者查阅，下表列出了主题端参数与 broker 端参数的对照关系。

| 主题端参数                              | 释 义                                                        | 对应的 broker 端参数                     |
| --------------------------------------- | ------------------------------------------------------------ | ---------------------------------------- |
| cleanup.policy                          | 日志压缩策略。默认值为 delete，还可以配置为 compact          | log.cleanup.policy                       |
| compression.type                        | 消息的压缩类型。默认值为 producer，表示保留生产者中所使用的原始压缩类型。还可以配置为 uncompressed、snappy、lz4、gzip | compression.type                         |
| delete.retention.ms                     | 被标识为删除的数据能够保留多久。默认值为86400000，即1天      | log.cleaner.delete.retention.ms          |
| file.delete.delay.ms                    | 清理文件之前可以等待多长时间，默认值为60000，即1分钟         | log.segment.delete.delay.ms              |
| flush.messages                          | 需要收集多少消息才会将它们强制刷新到磁盘，默认值为 Long.MAX_VALUE，即让操作系统来决定。建议不要修改此参数的默认值 | log.flush.interval.messages              |
| flush.ms                                | 需要等待多久才会将消息强制刷新到磁盘，默认值为 Long.MAX_VALUE，即让操作系统来决定。建议不要修改此参数的默认值 | log.flush.interval.ms                    |
| follower.replication.throttled.replicas | 用来配置被限制速率的主题所对应的 follower 副本列表           | follower.replication.throttled.replicas  |
| index.interval.bytes                    | 用来控制添加索引项的频率。每超过这个参数所设置的消息字节数时就可以添加一个新的索引项，默认值为4096 | log.index.interval.bytes                 |
| leader.replication.throttled.replicas   | 用来配置被限制速率的主题所对应的 leader 副本列表             | leader.replication.throttled.replicas    |
| max.message.bytes                       | 消息的最大字节数，默认值为1000012                            | message.max.bytes                        |
| message.format.version                  | 消息格式的版本，默认值为 2.0-IV1                             | log.message.format.version               |
| message.timestamp.difference. max.ms    | 消息中自带的时间戳与 broker 收到消息时的时间戳之间最大的差值，默认值为 Long.MAX_VALUE。此参数只有在 meesage. timestamp.type 参数设置为 CreateTime 时才有效 | log.message.timestamp. difference.max.ms |
| message.timestamp.type                  | 消息的时间戳类型。默认值为 CreateTime，还可以设置为 LogAppendTime | log.message.timestamp. type              |
| min.cleanable.dirty.ratio               | 日志清理时的最小污浊率，默认值为0.5                          | log.cleaner.min.cleanable. ratio         |
| min.compaction.lag.ms                   | 日志再被清理前的最小保留时间，默认值为0                      | log.cleaner.min.compaction. lag.ms       |
| min.insync.replicas                     | 分区ISR集合中至少要有多少个副本，默认值为1                   | min.insync.replicas                      |
| preallocate                             | 在创建日志分段的时候是否要预分配空间，默认值为 false         | log.preallocate                          |
| retention.bytes                         | 分区中所能保留的消息总量，默认值为-1，即没有限制             | log.retention.bytes                      |
| retention.ms                            | 使用 delete 的日志清理策略时消息能够保留多长时间，默认值为604800000，即7天。如果设置为-1，则表示没有限制 | log.retention.ms                         |
| segment.bytes                           | 日志分段的最大值，默认值为1073741824，即1GB                  | log.segment.bytes                        |
| segment.index.bytes                     | 日志分段索引的最大值，默认值为10485760，即10MB               | log.index.size.max.bytes                 |
| segment.jitter.ms                       | 滚动日志分段时，在 segment.ms 的基础之上增加的随机数，默认为0 | log.roll.jitter.ms                       |
| segment.ms                              | 最长多久滚动一次日志分段，默认值为604800000，即7天           | log.roll.ms                              |
| unclean.leader.election.enable          | 是否可以从非 ISR 集合中选举 leader 副本，默认值为 false，如果设置为 true，则可能造成数据丢失 | unclean.leader.election. enable          |



# <a name="89">初识KafkaAdminClient</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

一般情况下，我们都习惯使用 kafka-topics.sh 脚本来管理主题，但有些时候我们希望将主题管理类的功能集成到公司内部的系统中，打造集管理、监控、运维、告警为一体的生态平台，那么就需要以程序调用 API 的方式去实现。本节主要介绍 KafkaAdminClient 的基本使用方式，以及采用这种调用 API 方式下的创建主题时的合法性验证。

## <a name="90">基本使用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

代码清单16-1中使用 TopicCommand 创建了一个主题，当然我们也可以用它来实现主题的删除、修改、查看等操作，实质上与使用 kafka-config.sh 脚本的方式无异。这种方式与应用程序之间的交互性非常差，且不说它的编程模型类似于拼写字符串，它本身调用的 TopicCommand 类的 main() 方法的返回值是一个 void 类，并不能提供给调用者有效的反馈信息。比如我们使用下面的方式来查看主题 topic-create 的详细信息，如代码清单20-1所示。

```
//代码清单20-1查看主题
public static void describeTopic(){
    String[] options = new String[]{
            "--zookeeper", "localhost:2181/kafka",
            "--describe",
            "--topic", "topic-create"
    };
    kafka.admin.TopicCommand.main(options);
}
```

当调用 describeTopic() 方法时，虽然我们可以在终端看到主题 topic-create 的详细信息，但方法的调用者却无法捕获这个信息，因为返回值类型为 void。对于方法的调用者而言，执行这个方法和不执行这个方法没有什么区别。

在 Kafka 0.11.0.0 版本之前，我们可以通过 kafka-core 包（Kafka 服务端代码）下的 kafka.admin.AdminClient 和 kafka.admin.AdminUtils 来实现部分 Kafka 的管理功能，但它们都已经过时了，在未来的版本中会被删除。从0.11.0.0版本开始，Kafka 提供了另一个工具类 org.apache.kafka.clients.admin.KafkaAdminClient 来作为替代方案。KafkaAdminClient 不仅可以用来管理 broker、配置和 ACL（Access Control List），还可以用来管理主题。

KafkaAdminClient 继承了 org.apache.kafka.clients.admin.AdminClient 抽象类，并提供了多种方法。篇幅限制，下面只列出与本章内容相关的一些方法。

- 创建主题：CreateTopicsResult createTopics(Collection newTopics)。
- 删除主题：DeleteTopicsResult deleteTopics(Collection topics)。
- 列出所有可用的主题：ListTopicsResult listTopics()。
- 查看主题的信息：DescribeTopicsResult describeTopics(Collection topicNames)。
- 查询配置信息：DescribeConfigsResult describeConfigs(Collection resources)。
- 修改配置信息：AlterConfigsResult alterConfigs(Map<ConfigResource, Config> configs)。
- 增加分区：CreatePartitionsResult createPartitions(Map<String, NewPartitions> newPartitions)。

下面分别介绍这些方法的具体使用方式。首先分析如何使用 KafkaAdminClient 创建一个主题，下面的示例中创建了一个分区数为4、副本因子为1的主题 topic-admin，如代码清单20-2所示。

```
//代码清单20-2 使用KafkaAdminClient创建一个主题
String brokerList =  "localhost:9092";
String topic = "topic-admin";

Properties props = new Properties();	①
props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
AdminClient client = AdminClient.create(props);		②

NewTopic newTopic = new NewTopic(topic, 4, (short) 1);	③
CreateTopicsResult result = client.
        createTopics(Collections.singleton(newTopic));	④
try {
    result.all().get();			⑤
} catch (InterruptedException | ExecutionException e) {
    e.printStackTrace();
}
client.close();					⑥
```

示例中第②行创建了一个 KafkaAdminClient 实例，实例中通过引入在第①行中建立的配置来连接 Kafka 集群。AdminClient.create() 方法实际上调用的就是 KafkaAdminClient 中的 createInternal 方法构建的 KafkaAdminClient 实例，具体定义如下：

```
public static AdminClient create(Properties props) {
    return KafkaAdminClient.createInternal(
            new AdminClientConfig(props), null);
}
```

第③行中的 NewTopic 用来设定所要创建主题的具体信息，包含创建主题时需要的主题名称、分区数和副本因子等。NewTopic 中的成员变量如下所示。

```
private final String name;	//主题名称
private final int numPartitions; 	//分区数
private final short replicationFactor; 	//副本因子
private final Map<Integer, List<Integer>> replicasAssignments; 	//分配方案
private Map<String, String> configs = null; 	//配置
```

同 kafka-topics.sh 脚本一样，可以通过指定分区数和副本因子来创建一个主题，也可以通过指定区副本的具体分配方案来创建一个主题，比如将第③行替换为下面的内容：

```
Map<Integer, List<Integer>> replicasAssignments = new HashMap<>();
replicasAssignments.put(0, Arrays.asList(0));
replicasAssignments.put(1, Arrays.asList(0));
replicasAssignments.put(2, Arrays.asList(0));
replicasAssignments.put(3, Arrays.asList(0));
NewTopic newTopic = new NewTopic(topic, replicasAssignments);
```

也可以在创建主题时指定需要覆盖的配置。比如覆盖 cleanup.policy 配置，需要在第③和第④行之间加入如下代码：

```
Map<String, String> configs = new HashMap<>();
configs.put("cleanup.policy", "compact");
newTopic.configs(configs);
```

第④行是真正的创建主题的核心。KafkaAdminClient 内部使用 Kafka 的一套自定义二进制协议来实现诸如创建主题的管理功能。它主要的实现步骤如下：

1. 客户端根据方法的调用创建相应的协议请求，比如创建主题的 createTopics 方法，其内部就是发送 CreateTopicsRequest 请求。
2. 客户端将请求发送至服务端。
3. 服务端处理相应的请求并返回响应，比如这个与 CreateTopicsRequest 请求对应的就是 CreateTopicsResponse。
4. 客户端接收相应的响应并进行解析处理。和协议相关的请求和相应的类基本都在 org.apache.kafka.common.requests 包下，AbstractRequest 和 AbstractResponse 是这些请求和响应类的两个基本父类。

有关 Kafka 的自定义协议的更多内容可以参阅[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)的相关章节。

第④行中的返回值是 CreateTopicsResult 类型，它的具体定义也很简单，如代码清单20-3所示。

```
//代码清单20-3 CreateTopicsResult的具体内容
public class CreateTopicsResult {
    private final Map<String, KafkaFuture<Void>> futures;

    CreateTopicsResult(Map<String, KafkaFuture<Void>> futures) {
        this.futures = futures;
    }

    public Map<String, KafkaFuture<Void>> values() {
        return futures;
    }

    public KafkaFuture<Void> all() {
        return KafkaFuture.allOf(futures.values()
                .toArray(new KafkaFuture[0]));
    }
}
```

CreateTopicsResult 中的方法主要还是针对成员变量 futures 的操作，futures 的类型 Map<String, KafkaFuture> 中的 key 代表主题名称，而 KafkaFuture 代表创建后的返回值类型。KafkaAdminClient 中的 createTopics() 方法可以一次性创建多个主题。KafkaFuture 是原本为了支持JDK8以下的版本而自定义实现的一个类，实现了 Future 接口，可以通过 Future.get() 方法来等待服务端的返回，参见代码清单20-2中的第⑤行。在未来的版本中，会有计划地将 KafkaFuture 替换为JDK8中引入的 CompletableFuture。

虽然这里创建主题之后的返回值类型为 Void，但并不代表所有操作的返回值类型都是 Void，比如 KafkaAdminClient 中的 listTopics() 方法的返回值为 ListTopicsResult 类型，这个 ListTopicsResult 类型内部的成员变量 future 的类型为 KafkaFuture<Map<String, TopicListing>>，这里就包含了具体的返回信息。

在使用 KafkaAdminClient 之后记得要调用 close() 方法来释放资源。

KafkaAdminClient 中的 deleteTopics()、listTopics() 及 describeTopics() 方法都很简单，读者不妨自己实践一下。下面讲一讲 describeConfigs() 和 alterConfigs() 这两个方法。首先查看刚刚创建的主题 topic-admin 的具体配置信息，如代码清单20-4所示。

```
//代码清单20-4 describeConfigs()方法的使用示例
public static void describeTopicConfig() throws ExecutionException,
        InterruptedException {
    String brokerList =  "localhost:9092";
    String topic = "topic-admin";

    Properties props = new Properties();
    props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
    props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
    AdminClient client = AdminClient.create(props);

    ConfigResource resource =
            new ConfigResource(ConfigResource.Type.TOPIC, topic);①
    DescribeConfigsResult result =
            client.describeConfigs(Collections.singleton(resource));②
    Config config = result.all().get().get(resource);③
    System.out.println(config);④
    client.close();
}
```

最终的输出结果不会只列出被覆盖的配置信息，而是会列出主题中所有的配置信息。 alterConfigs() 方法的使用方式也很简单。下面的示例中将主题 topic-admin 的 cleanup.policy 参数修改为 compact，只需将代码清单20-4中的第①至第④行替换为下面的内容即可：

```
ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, topic);
ConfigEntry entry = new ConfigEntry("cleanup.policy", "compact");
Config config = new Config(Collections.singleton(entry));
Map<ConfigResource, Config> configs = new HashMap<>();
configs.put(resource, config);
AlterConfigsResult result = client.alterConfigs(configs);
result.all().get();
```

本章的最后将演示如何使用 KafkaAdminClient 的 createPartitions() 方法来增加一个主题的分区。下面的示例将主题 topic-admin 的分区从4增加到5，只需将代码清单20-4中的第①至第④行替换为下面的内容即可：

```
NewPartitions newPartitions = NewPartitions.increaseTo(5);
Map<String, NewPartitions> newPartitionsMap = new HashMap<>();
newPartitionsMap.put(topic, newPartitions);
CreatePartitionsResult result = client.createPartitions(newPartitionsMap);
result.all().get();
```

## <a name="91">主题合法性验证</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

一般情况下，Kafka 生产环境中的 auto.create.topics.enable 参数会被设置为 false，即自动创建主题这条路会被堵住。kafka-topics.sh 脚本创建的方式一般由运维人员操作，普通用户无权过问。那么 KafkaAdminClient 就为普通用户提供了一个“口子”，或者将其集成到公司内部的资源申请、审核系统中会更加方便。

普通用户在创建主题的时候，有可能由于误操作或其他原因而创建了不符合运维规范的主题，比如命名不规范，副本因子数太低等，这些都会影响后期的系统运维。如果创建主题的操作封装在资源申请、审核系统中，那么在前端就可以根据规则过滤不符合规范的申请操作。如果用户用 KafkaAdminClient 或类似的工具创建了一个错误的主题，我们有什么办法可以做相应的规范处理呢？

Kafka broker 端有一个这样的参数：create.topic.policy.class.name，默认值为 null，它提供了一个入口用来验证主题创建的合法性。使用方式很简单，只需要自定义实现 org.apache.kafka.server.policy.CreateTopicPolicy 接口，比如下面示例中的 PolicyDemo。然后在 broker 端的配置文件 config/server.properties 中配置参数 create.topic.policy.class. name 的值为 org.apache.kafka.server.policy.PolicyDemo，最后启动服务。

PolicyDemo 的代码参考代码清单20-5，主要实现接口中的 configure()、close() 及 validate() 方法，configure() 方法会在 Kafka 服务启动的时候执行，validate() 方法用来鉴定主题参数的合法性，其在创建主题时执行，close() 方法在关闭 Kafka 服务时执行。

```
//代码清单20-5 主题合法性验证示例
public class PolicyDemo implements CreateTopicPolicy {
    public void configure(Map<String, ?> configs) {
    }

    public void close() throws Exception {
    }

    public void validate(RequestMetadata requestMetadata)
            throws PolicyViolationException {
        if (requestMetadata.numPartitions() != null || 
                requestMetadata.replicationFactor() != null) {
            if (requestMetadata.numPartitions() < 5) {
                throw new PolicyViolationException("Topic should have at " +
                        "least 5 partitions, received: "+ 
                        requestMetadata.numPartitions());
            }
            if (requestMetadata.replicationFactor() <= 1) {
                throw new PolicyViolationException("Topic should have at " +
                        "least 2 replication factor, recevied: "+ 
                        requestMetadata.replicationFactor());
            }
        }
    }
}
```

此时如果采用代码清单20-3中的方式创建一个分区数为4、副本因子为1的主题，那么客户端就出报出如下的错误：

```
java.util.concurrent.ExecutionException: org.apache.kafka.common.errors.PolicyViolationException: Topic should have at least 5 partitions, received: 4
```

相应的 Kafka 服务端的日志如下：

```
CreateTopicPolicy.RequestMetadata(topic=topic-test2, numPartitions=4, replicationFactor=1, replicasAssignments=null, configs={})
[2018-04-18 19:52:02,747] INFO [Admin Manager on Broker 0]: Error processing create topic request for topic topic-test2 with arguments (numPartitions=4, replicationFactor=1, replicasAssignments={}, configs={}) (kafka.server.AdminManager)
org.apache.kafka.common.errors.PolicyViolationException: Topic should have at least 5 partitions, received: 4
```



# <a name="92">优先副本的选举</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

分区使用多副本机制来提升可靠性，但只有 leader 副本对外提供读写服务，而 follower 副本只负责在内部进行消息的同步。如果一个分区的 leader 副本不可用，那么就意味着整个分区变得不可用，此时就需要 Kafka 从剩余的 follower 副本中挑选一个新的 leader 副本来继续对外提供服务。虽然不够严谨，但从某种程度上说，broker 节点中 leader 副本个数的多少决定了这个节点负载的高低。

在创建主题的时候，该主题的分区及副本会尽可能均匀地分布到 Kafka 集群的各个 broker 节点上，对应的 leader 副本的分配也比较均匀。比如我们使用 kafka-topics.sh 脚本创建一个分区数为3、副本因子为3的主题 topic-partitions，创建之后的分布信息如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-partitions
Topic:topic-partitions	PartitionCount:3	ReplicationFactor:3	Configs:
	Topic: topic-partitions	Partition: 0	Leader: 1	Replicas: 1,2,0	Isr: 1,2,0
	Topic: topic-partitions	Partition: 1	Leader: 2	Replicas: 2,0,1	Isr: 2,0,1
	Topic: topic-partitions	Partition: 2	Leader: 0	Replicas: 0,1,2	Isr: 0,1,2
```

可以看到 leader 副本均匀分布在 brokerId 为0、1、2的 broker 节点之中。针对同一个分区而言，同一个 broker 节点中不可能出现它的多个副本，即 Kafka 集群的一个 broker 中最多只能有它的一个副本，我们可以将 leader 副本所在的 broker 节点叫作分区的 leader 节点，而 follower 副本所在的 broker 节点叫作分区的 follower 节点。

随着时间的更替，Kafka 集群的 broker 节点不可避免地会遇到宕机或崩溃的问题，当分区的 leader 节点发生故障时，其中一个 follower 节点就会成为新的 leader 节点，这样就会导致集群的负载不均衡，从而影响整体的健壮性和稳定性。当原来的 leader 节点恢复之后重新加入集群时，它只能成为一个新的 follower 节点而不再对外提供服务。比如我们将 brokerId 为2的节点重启，那么主题 topic-partitions 新的分布信息如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-partitions
Topic:topic-partitions	PartitionCount:3	ReplicationFactor:3	Configs: 
    Topic: topic-partitions	Partition: 0	Leader: 1	Replicas: 1,2,0	Isr: 1,0,2
    Topic: topic-partitions	Partition: 1	Leader: 0	Replicas: 2,0,1	Isr: 0,1,2
    Topic: topic-partitions	Partition: 2	Leader: 0	Replicas: 0,1,2	Isr: 0,1,2
```

可以看到原本分区1的 leader 节点为2，现在变成了0，如此一来原本均衡的负载变成了失衡：节点0的负载最高，而节点2的负载最低。

为了能够有效地治理负载失衡的情况，Kafka 引入了优先副本（preferred replica）的概念。所谓的优先副本是指在AR集合列表中的第一个副本。比如上面主题 topic-partitions 中分区0的AR集合列表（Replicas）为[1,2,0]，那么分区0的优先副本即为1。理想情况下，优先副本就是该分区的leader 副本，所以也可以称之为 preferred leader。Kafka 要确保所有主题的优先副本在 Kafka 集群中均匀分布，这样就保证了所有分区的 leader 均衡分布。如果 leader 分布过于集中，就会造成集群负载不均衡。

所谓的优先副本的选举是指通过一定的方式促使优先副本选举为 leader 副本，以此来促进集群的负载均衡，这一行为也可以称为“分区平衡”。

需要注意的是，分区平衡并不意味着 Kafka 集群的负载均衡，因为还要考虑集群中的分区分配是否均衡。更进一步，每个分区的 leader 副本的负载也是各不相同的，有些 leader 副本的负载很高，比如需要承载 TPS 为30000的负荷，而有些 leader 副本只需承载个位数的负荷。也就是说，就算集群中的分区分配均衡、leader 分配均衡，也并不能确保整个集群的负载就是均衡的，还需要其他一些硬性的指标来做进一步的衡量，这个会在后面的章节中涉及，本节只探讨优先副本的选举。

在 Kafka 中可以提供分区自动平衡的功能，与此对应的 broker 端参数是 auto.leader. rebalance.enable，此参数的默认值为 true，即默认情况下此功能是开启的。如果开启分区自动平衡的功能，则 Kafka 的控制器会启动一个定时任务，这个定时任务会轮询所有的 broker 节点，计算每个 broker 节点的分区不平衡率（broker 中的不平衡率=非优先副本的 leader 个数/分区总数）是否超过 leader.imbalance.per.broker.percentage 参数配置的比值，默认值为10%，如果超过设定的比值则会自动执行优先副本的选举动作以求分区平衡。执行周期由参数 leader.imbalance.check.interval.seconds 控制，默认值为300秒，即5分钟。

不过在生产环境中不建议将 auto.leader.rebalance.enable 设置为默认的 true，因为这可能引起负面的性能问题，也有可能引起客户端一定时间的阻塞。因为执行的时间无法自主掌控，如果在关键时期（比如电商大促波峰期）执行关键任务的关卡上执行优先副本的自动选举操作，势必会有业务阻塞、频繁超时之类的风险。前面也分析过，分区及副本的均衡也不能完全确保集群整体的均衡，并且集群中一定程度上的不均衡也是可以忍受的，为防止出现关键时期“掉链子”的行为，笔者建议还是将掌控权把控在自己的手中，可以针对此类相关的埋点指标设置相应的告警，在合适的时机执行合适的操作，而这个“合适的操作”就是指手动执行分区平衡。

Kafka 中 kafka-perferred-replica-election.sh 脚本提供了对分区 leader 副本进行重新平衡的功能。优先副本的选举过程是一个安全的过程，Kafka 客户端可以自动感知分区 leader 副本的变更。下面的示例演示了 kafka-perferred-replica-election.sh 脚本的具体用法：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-preferred-replica-election.sh --zookeeper localhost:2181/kafka
 
Created preferred replica election path with topic-demo-3,__consumer_offsets-22, topic-config-1,__consumer_offsets-30,__bigdata_monitor-12,__consumer_offsets-8,__consumer_offsets-21,topic-create-0,__consumer_offsets-4,topic-demo-1,topic-partitions-1,__consumer_offsets-27,__consumer_offsets-7,__consumer_offsets-9,__consumer_offsets-46,(…省略若干)

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-partitions
Topic:topic-partitions	PartitionCount:3	ReplicationFactor:3	Configs: 
    Topic: topic-partitions	Partition: 0	Leader: 1	Replicas: 1,2,0	Isr: 1,0,2
    Topic: topic-partitions	Partition: 1	Leader: 2	Replicas: 2,0,1	Isr: 0,1,2
    Topic: topic-partitions	Partition: 2	Leader: 0	Replicas: 0,1,2	Isr: 0,1,2
```

可以看到在脚本执行之后，主题 topic-partitions 中的所有 leader 副本的分布已经和刚创建时的一样了，所有的优先副本都成为 leader 副本。

上面示例中的这种使用方式会将集群上所有的分区都执行一遍优先副本的选举操作，分区数越多打印出来的信息也就越多。leader 副本的转移也是一项高成本的工作，如果要执行的分区数很多，那么必然会对客户端造成一定的影响。如果集群中包含大量的分区，那么上面的这种使用方式有可能会失效。在优先副本的选举过程中，具体的元数据信息会被存入 ZooKeeper 的/admin/preferred_replica_election 节点，如果这些数据超过了 ZooKeeper 节点所允许的大小，那么选举就会失败。默认情况下 ZooKeeper 所允许的节点数据大小为1MB。

kafka-perferred-replica-election.sh 脚本中还提供了 path-to-json-file 参数来小批量地对部分分区执行优先副本的选举操作。通过 path-to-json-file 参数来指定一个 JSON 文件，这个 JSON 文件里保存需要执行优先副本选举的分区清单。

举个例子，我们再将集群中 brokerId 为2的节点重启，不过我们现在只想对主题 topic- partitions 执行优先副本的选举操作，那么先创建一个JSON文件，文件名假定为 election.json，文件的内容如下：

```
{
        "partitions":[
                {
                        "partition":0,
                        "topic":"topic-partitions"
                },
                {
                        "partition":1,
                        "topic":"topic-partitions"
                },
                {
                        "partition":2,
                        "topic":"topic-partitions"
                }
        ]
}
```

然后通过 kafka-perferred-replica-election.sh 脚本配合 path-to-json-file 参数来对主题 topic-partitions 执行优先副本的选举操作，具体示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-preferred-replica-election.sh --zookeeper localhost:2181/kafka --path-to-json-file election.json
Created preferred replica election path with topic-partitions-0,topic-partitions-1, topic-partitions-2
Successfully started preferred replica election for partitions Set(topic- partitions-0, topic-partitions-1, topic-partitions-2)

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-partitions
Topic:topic-partitions	PartitionCount:3	ReplicationFactor:3	Configs:
    Topic: topic-partitions	Partition: 0	Leader: 1	Replicas: 1,2,0	Isr: 1,0,2 
    Topic: topic-partitions	Partition: 1	Leader: 2	Replicas: 2,0,1	Isr: 0,1,2
    Topic: topic-partitions	Partition: 2	Leader: 0	Replicas: 0,1,2	Isr: 0,1,2
```

读者可以自行查看一下集群中的其他主题是否像之前没有使用 path-to-json-file 参数的一样也被执行了选举操作。

在实际生产环境中，一般使用 path-to-json-file 参数来分批、手动地执行优先副本的选举操作。尤其是在应对大规模的 Kafka 集群时，理应杜绝采用非 path-to-json-file 参数的选举操作方式。同时，优先副本的选举操作也要注意避开业务高峰期，以免带来性能方面的负面影响。



# <a name="93">分区重分配</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

当集群中的一个节点突然宕机下线时，如果节点上的分区是单副本的，那么这些分区就变得不可用了，在节点恢复前，相应的数据也就处于丢失状态；如果节点上的分区是多副本的，那么位于这个节点上的 leader 副本的角色会转交到集群的其他 follower 副本中。总而言之，这个节点上的分区副本都已经处于功能失效的状态，Kafka 并不会将这些失效的分区副本自动地迁移到集群中剩余的可用 broker 节点上，如果放任不管，则不仅会影响整个集群的均衡负载，还会影响整体服务的可用性和可靠性。

当要对集群中的一个节点进行有计划的下线操作时，为了保证分区及副本的合理分配，我们也希望通过某种方式能够将该节点上的分区副本迁移到其他的可用节点上。

当集群中新增 broker 节点时，只有新创建的主题分区才有可能被分配到这个节点上，而之前的主题分区并不会自动分配到新加入的节点中，因为在它们被创建时还没有这个新节点，这样新节点的负载和原先节点的负载之间严重不均衡。

为了解决上述问题，需要让分区副本再次进行合理的分配，也就是所谓的分区重分配。Kafka 提供了 kafka-reassign-partitions.sh 脚本来执行分区重分配的工作，它可以在集群扩容、broker 节点失效的场景下对分区进行迁移。 kafka-reassign-partitions.sh 脚本的使用分为3个步骤：首先创建需要一个包含主题清单的 JSON 文件，其次根据主题清单和 broker 节点清单生成一份重分配方案，最后根据这份方案执行具体的重分配动作。

下面我们通过一个具体的案例来演示 kafka-reassign-partitions.sh 脚本的用法。首先在一个由3个节点（broker 0、broker 1、broker 2）组成的集群中创建一个主题 topic-reassign，主题中包含4个分区和2个副本：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-reassign --replication-factor 2 --partitions 4
Created topic "topic-reassign".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-reassign
Topic:topic-reassign	PartitionCount:4	ReplicationFactor:2	Configs: 
    Topic: topic-reassign	Partition: 0	Leader: 0	Replicas: 0,2	Isr: 0,2
    Topic: topic-reassign	Partition: 1	Leader: 1	Replicas: 1,0	Isr: 1,0
    Topic: topic-reassign	Partition: 2	Leader: 2	Replicas: 2,1	Isr: 2,1
    Topic: topic-reassign	Partition: 3	Leader: 0	Replicas: 0,1	Isr: 0,1
```

我们可以观察到主题 topic-reassign 在3个节点中都有相应的分区副本分布。由于某种原因，我们想要下线 brokerId 为1的 broker 节点，在此之前，我们要做的就是将其上的分区副本迁移出去。使用 kafka-reassign-partitions.sh 脚本的第一步就是要创建一个 JSON 文件（文件的名称假定为 reassign.json），文件内容为要进行分区重分配的主题清单。对主题 topic-reassign 而言，示例如下：

```
{
        "topics":[
                {
                        "topic":"topic-reassign"
                }
        ],
        "version":1
}
```

第二步就是根据这个 JSON 文件和指定所要分配的 broker 节点列表来生成一份候选的重分配方案，具体内容参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-reassign-partitions.sh --zookeeper localhost:2181/kafka --generate --topics-to-move-json-file reassign.json --broker-list 0,2
Current partition replica assignment
{"version":1,"partitions":[{"topic":"topic-reassign","partition":2,"replicas":[2,1],"log_dirs":["any","any"]},{"topic":"topic-reassign","partition":1,"replicas":[1,0],"log_dirs":["any","any"]},{"topic":"topic-reassign","partition":3,"replicas":[0,1],"log_dirs":["any","any"]},{"topic":"topic-reassign","partition":0,"replicas":[0,2],"log_dirs":["any","any"]}]}

Proposed partition reassignment configuration
{"version":1,"partitions":[{"topic":"topic-reassign","partition":2,"replicas":[2,0],"log_dirs":["any","any"]},{"topic":"topic-reassign","partition":1,"replicas":[0,2],"log_dirs":["any","any"]},{"topic":"topic-reassign","partition":3,"replicas":[0,2],"log_dirs":["any","any"]},{"topic":"topic-reassign","partition":0,"replicas":[2,0],"log_dirs":["any","any"]}]}
```

上面的示例中包含4个参数，其中 zookeeper 已经很常见了，用来指定 ZooKeeper 的地址。generate 是 kafka-reassign-partitions.sh 脚本中指令类型的参数，可以类比于 kafka-topics.sh 脚本中的 create、list 等，它用来生成一个重分配的候选方案。topic-to-move-json 用来指定分区重分配对应的主题清单文件的路径，该清单文件的具体的格式可以归纳为{"topics": [{"topic": "foo"},{"topic": "foo1"}],"version": 1}。broker-list 用来指定所要分配的 broker 节点列表，比如示例中的“0,2”。

上面示例中打印出了两个 JSON 格式的内容。第一个“Current partition replica assignment”所对应的 JSON 内容为当前的分区副本分配情况，在执行分区重分配的时候最好将这个内容保存起来，以备后续的回滚操作。第二个“Proposed partition reassignment configuration”所对应的 JSON 内容为重分配的候选方案，注意这里只是生成一份可行性的方案，并没有真正执行重分配的动作。生成的可行性方案的具体算法和创建主题时的一样，这里也包含了机架信息，具体的细节可以参考17节的内容。

我们需要将第二个 JSON 内容保存在一个 JSON 文件中，假定这个文件的名称为 project.json。

第三步执行具体的重分配动作，详细参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-reassign-partitions.sh --zookeeper localhost:2181/kafka --execute --reassignment-json-file project.json 
Current partition replica assignment

{"version":1,"partitions":[{"topic":"topic-reassign","partition":2,"replicas":[2,1],"log_dirs":["any","any"]},{"topic":"topic-reassign","partition":1,"replicas":[1,0],"log_dirs":["any","any"]},{"topic":"topic-reassign","partition":3,"replicas":[0,1],"log_dirs":["any","any"]},{"topic":"topic-reassign","partition":0,"replicas":[0,2],"log_dirs":["any","any"]}]}

Save this to use as the --reassignment-json-file option during rollback
Successfully started reassignment of partitions.
```

我们再次查看主题 topic-reassign 的具体信息：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-reassign
Topic:topic-reassign	PartitionCount:4	ReplicationFactor:2	Configs: 
    Topic: topic-reassign	Partition: 0	Leader: 0	Replicas: 2,0	Isr: 0,2
    Topic: topic-reassign	Partition: 1	Leader: 0	Replicas: 0,2	Isr: 0,2
    Topic: topic-reassign	Partition: 2	Leader: 2	Replicas: 2,0	Isr: 2,0
    Topic: topic-reassign	Partition: 3	Leader: 0	Replicas: 0,2	Isr: 0,2
```

可以看到主题中的所有分区副本都只在0和2的 broker 节点上分布了。

在第三步的操作中又多了2个参数，execute 也是指令类型的参数，用来指定执行重分配的动作。reassignment-json-file 指定分区重分配方案的文件路径，对应于示例中的 project.json 文件。

除了让脚本自动生成候选方案，用户还可以自定义重分配方案，这样也就不需要执行第一步和第二步的操作了。

分区重分配的基本原理是先通过控制器为每个分区添加新副本（增加副本因子），新的副本将从分区的 leader 副本那里复制所有的数据。根据分区的大小不同，复制过程可能需要花一些时间，因为数据是通过网络复制到新副本上的。在复制完成之后，控制器将旧副本从副本清单里移除（恢复为原先的副本因子数）。注意在重分配的过程中要确保有足够的空间。

细心的读者可能观察到主题 topic-reassign 中有3个 leader 副本在 broker 0 上，而只有1个 leader 副本在 broker 2 上，这样负载就不均衡了。不过我们可以借助上一节中的 kafka-perferred-replica-election.sh 脚本来执行一次优先副本的选举动作，之后可以看到主题 topic-reassign 的具体信息已经趋于完美：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-reassign
Topic:topic-reassign	PartitionCount:4	ReplicationFactor:2	Configs: 
    Topic: topic-reassign	Partition: 0	Leader: 2	Replicas: 2,0	Isr: 0,2
    Topic: topic-reassign	Partition: 1	Leader: 0	Replicas: 0,2	Isr: 0,2
    Topic: topic-reassign	Partition: 2	Leader: 2	Replicas: 2,0	Isr: 2,0
    Topic: topic-reassign	Partition: 3	Leader: 0	Replicas: 0,2	Isr: 0,2
```

对于分区重分配而言，这里还有可选的第四步操作，即验证查看分区重分配的进度。只需将上面的 execute 替换为 verify 即可，具体示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-reassign-partitions.sh --zookeeper localhost:2181/kafka --verify --reassignment-json-file project.json 
Status of partition reassignment: 
Reassignment of partition topic-reassign-2 completed successfully
Reassignment of partition topic-reassign-1 completed successfully
Reassignment of partition topic-reassign-3 completed successfully
Reassignment of partition topic-reassign-0 completed successfully
```

分区重分配对集群的性能有很大的影响，需要占用额外的资源，比如网络和磁盘。在实际操作中，我们将降低重分配的粒度，分成多个小批次来执行，以此来将负面的影响降到最低，这一点和优先副本的选举有异曲同工之妙。 还需要注意的是，如果要将某个 broker 下线，那么在执行分区重分配动作之前最好先关闭或重启 broker。这样这个 broker 就不再是任何分区的 leader 节点了，它的分区就可以被分配给集群中的其他 broker。这样可以减少 broker 间的流量复制，以此提升重分配的性能，以及减少对集群的影响。



# <a name="94">复制限流</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在上一节中我们了解了分区重分配本质在于数据复制，先增加新的副本，然后进行数据同步，最后删除旧的副本来达到最终的目的。数据复制会占用额外的资源，如果重分配的量太大必然会严重影响整体的性能，尤其是处于业务高峰期的时候。减小重分配的粒度，以小批次的方式来操作是一种可行的解决思路。如果集群中某个主题或某个分区的流量在某段时间内特别大，那么只靠减小粒度是不足以应对的，这时就需要有一个限流的机制，可以对副本间的复制流量加以限制来保证重分配期间整体服务不会受太大的影响。

副本间的复制限流有两种实现方式：kafka-config.sh 脚本和 kafka-reassign-partitions.sh 脚本。

首先，我们讲述如何通过 kafka-config.sh 脚本来实现限流，如果对这个脚本的使用有些遗忘，则可以再回顾一下19节的内容。不过19节里只演示了主题相关的配置变更，并没有涉及其他的类型，本节的内容会与broker类型的配置相关，不妨借助这个机会再来了解一下 broker 类型的配置用法。

kafka-config.sh 脚本主要以动态配置的方式来达到限流的目的，在 broker 级别有两个与复制限流相关的配置参数：follower.replication.throttled.rate 和 leader.replication. throttled.rate，前者用于设置 follower 副本复制的速度，后者用于设置 leader 副本传输的速度，它们的单位都是 B/s。通常情况下，两者的配置值是相同的。下面的示例中将 broker 1中的 leader 副本和 follower 副本的复制速度限制在1024B/s之内，即1KB/s：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost: 2181/kafka --entity-type brokers --entity-name 1 --alter --add-config follower.replication. throttled.rate=1024,leader.replication.throttled.rate=1024
Completed Updating config for entity: brokers '1'.
```

我们再来查看一下 broker 1 中刚刚添加的配置，参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost: 2181/kafka --entity-type brokers --entity-name 1 --describe
Configs for brokers '1' are leader.replication.throttled.rate=1024,follower. replication.throttled.rate=1024
```

在19节中我们了解到变更配置时会在 ZooKeeper 中创建一个命名形式为/config/ <entity-type>/<entity-name>的节点，对于这里的示例而言，其节点就是/config/brokers/1，节点中相应的信息如下：

```
[zk: localhost:2181/kafka(CONNECTED) 6] get /config/brokers/1
{"version":1,"config":{"leader.replication.throttled.rate":"1024","follower.replication.throttled.rate":"1024"}}
```

删除刚刚添加的配置也很简单，与19节中主题类型的方式一样，参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost: 2181/kafka --entity-type brokers --entity-name 1 --alter --delete-config follower. replication.throttled.rate,leader.replication.throttled.rate
Completed Updating config for entity: brokers '1'.
```

在主题级别也有两个相关的参数来限制复制的速度：leader.replication.throttled. replicas 和 follower.replication.throttled.replicas，它们分别用来配置被限制速度的主题所对应的 leader 副本列表和 follower 副本列表。为了演示具体的用法，我们先创建一个分区数为3、副本数为2的主题 topic-throttle，并查看它的详细信息：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-throttle --replication-factor 2 --partitions 3
Created topic "topic-throttle".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-throttle
Topic:topic-throttle	PartitionCount:3	ReplicationFactor:2	Configs: 
    Topic: topic-throttle	Partition: 0	Leader: 0	Replicas: 0,1	Isr: 0,1
    Topic: topic-throttle	Partition: 1	Leader: 1	Replicas: 1,2	Isr: 1,2
    Topic: topic-throttle	Partition: 2	Leader: 2	Replicas: 2,0	Isr: 2,0
```

在上面示例中，主题 topic-throttle 的三个分区所对应的 leader 节点分别为0、1、2，即分区与代理的映射关系为0:0、1:1、2:2，而对应的 follower 节点分别为1、2、0，相关的分区与代理的映射关系为0:1、1:2、2:0，那么此主题的限流副本列表及具体的操作细节如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost: 2181/kafka --entity-type topics --entity-name topic-throttle --alter --add-config leader.replication.throttled.replicas=[0:0,1:1,2:2],follower.replication.throttled.replicas=[0:1,1:2,2:0]
Completed Updating config for entity: topic 'topic-throttle'.
```

对应的 ZooKeeper 中的/config/topics/topic-throttle 节点信息如下：

```
{"version":1,"config":{"leader.replication.throttled.replicas":"0:0,1:1,2:2","follower.replication.throttled.replicas":"0:1,1:2,2:0"}}
```

在了解了与限流相关的4个配置参数之后，我们演示一下带有限流的分区重分配的用法。首先按照上一节的步骤创建一个包含可行性方案的 project.json 文件，内容如下：

```
{"version":1,"partitions":[{"topic":"topic-throttle","partition":1,"replicas":[2,0],"log_dirs":["any","any"]},{"topic":"topic-throttle","partition":0,"replicas":[0,2],"log_dirs":["any","any"]},{"topic":"topic-throttle","partition":2,"replicas":[0,2],"log_dirs":["any","any"]}]}
```

接下来设置被限流的副本列表，这里就很有讲究了，首先看一下重分配前和分配后的分区副本布局对比，详细如下：

```
partition   重分配前的AR     分配后的预期AR
0             0,1              0,2
1             1,2              2,0
2             2,0              0,2
```

如果分区重分配会引起某个分区AR集合的变更，那么这个分区中与 leader 有关的限制会应用于重分配前的所有副本，因为任何一个副本都可能是 leader，而与 follower 有关的限制会应用于所有移动的目的地。从概念上理解会比较抽象，这里不妨举个例子，对上面的布局对比而言，分区0重分配的AR为[0,1]，重分配后的AR为[0,2]，那么这里的目的地就是新增的2。也就是说，对分区0而言，leader.replication.throttled.replicas 配置为[0:0, 0:1]，follower.replication.throttled.replicas 配置为[0:2]。同理，对于分区1而言，leader.replication.throttled.replicas 配置为[1:1,1:2]，follower.replication. throttled.replicas 配置为[1:0]。分区3的AR集合没有发生任何变化，这里可以忽略。

获取限流副本列表之后，我们就可以执行具体的操作了，详细如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost: 2181/kafka --entity-type topics --entity-name topic-throttle --alter --add-config leader.replication.throttled.replicas=[1:1,1:2,0:0,0:1],follower.replication.throttled.replicas=[1:0,0:2]
Completed Updating config for entity: topic 'topic-throttle'.
```

接下来再设置 broker 2 的复制速度为10B/s，这样在下面的操作中可以很方便地观察限流与不限流的不同：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-configs.sh --zookeeper localhost: 2181/kafka --entity-type brokers --entity-name 2 --alter --add-config follower. replication.throttled.rate=10,leader.replication.throttled.rate=10
Completed Updating config for entity: brokers '2'.
```

在执行具体的重分配操作之前，我们需要开启一个生产者并向主题 topic-throttle 中发送一批消息，这样可以方便地观察正在进行数据复制的过程。

之后我们再执行正常的分区重分配的操作，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-reassign-partitions.sh --zookeeper localhost:2181/kafka --execute --reassignment-json-file project.json
Current partition replica assignment

{"version":1,"partitions":[{"topic":"topic-throttle","partition":2,"replicas":[2,0],"log_dirs":["any","any"]},{"topic":"topic-throttle","partition":1,"replicas":[1,2],"log_dirs":["any","any"]},{"topic":"topic-throttle","partition":0,"replicas":[0,1],"log_dirs":["any","any"]}]}

Save this to use as the --reassignment-json-file option during rollback
Successfully started reassignment of partitions.
```

执行之后，可以查看执行的进度，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-reassign-partitions.sh --zookeeper localhost:2181/kafka --verify --reassignment-json-file project.json
Status of partition reassignment: 
Reassignment of partition topic-throttle-1 completed successfully
Reassignment of partition topic-throttle-0 is still in progress
Reassignment of partition topic-throttle-2 completed successfully
```

可以看到分区 topic-throttle-0 还在同步过程中，因为我们之前设置了 broker 2 的复制速度为10B/s，这样使同步变得缓慢，分区 topic-throttle-0 需要同步数据到位于 broker 2 的新增副本中。随着时间的推移，分区 topic-throttle-0 最终会变成“completed successful”的状态。

为了不影响 Kafka 本身的性能，往往对临时设置的一些限制性的配置在使用完后要及时删除，而 kafka-reassign-partitions.sh 脚本配合指令参数 verify 就可以实现这个功能，在所有的分区都重分配完成之后执行查看进度的命令时会有如下的信息：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-reassign-partitions.sh --zookeeper localhost:2181/kafka --verify --reassignment-json-file project.json
Status of partition reassignment: 
Reassignment of partition topic-throttle-1 completed successfully
Reassignment of partition topic-throttle-0 completed successfully
Reassignment of partition topic-throttle-2 completed successfully
Throttle was removed.
```

注意到最后一行信息“Throttle was removed.”，它提示了所有之前针对限流做的配置都已经被清除了，读者可以自行查看一下相应的 ZooKeeper 节点中是否还有相关的配置。

kafka-reassign-partitions.sh 脚本本身也提供了限流的功能，只需一个 throttle 参数即可，具体用法如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-reassign-partitions.sh --zookeeper localhost:2181/kafka --execute --reassignment-json-file project.json  --throttle 10
Current partition replica assignment

{"version":1,"partitions":[{"topic":"topic-throttle","partition":2,"replicas":[2,0],"log_dirs":["any","any"]},{"topic":"topic-throttle","partition":1,"replicas":[1,2],"log_dirs":["any","any"]},{"topic":"topic-throttle","partition":0,"replicas":[0,1],"log_dirs":["any","any"]}]}

Save this to use as the --reassignment-json-file option during rollback
Warning: You must run Verify periodically, until the reassignment completes, to ensure the throttle is removed. You can also alter the throttle by rerunning the Execute command passing a new value.
The inter-broker throttle limit was set to 10 B/s
Successfully started reassignment of partitions.
```

上面的信息中包含了明确的告警信息：需要周期性地执行查看进度的命令直到重分配完成，这样可以确保限流设置被移除。也就是说，使用这种方式的限流同样需要显式地执行某些操作以使在重分配完成之后可以删除限流的设置。上面的信息中还告知了目前限流的速度上限为10B/s。

如果想在重分配期间修改限制来增加吞吐量，以便完成得更快，则可以重新运行 kafka- reassign-partitions.sh 脚本的 execute 命令，使用相同的 reassignment-json-file，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-reassign-partitions.sh --zookeeper localhost:2181/kafka --execute --reassignment-json-file project.json  --throttle 1024
There is an existing assignment running.
```

这样限流的速度上限为1024B/s，可以查看对应的 ZooKeeper 节点内容：

```
[zk: localhost:2181/kafka(CONNECTED) 30] get /config/topics/topic-throttle
{"version":1,"config":{"follower.replication.throttled.replicas":"1:0,0:2","leader.replication.throttled.replicas":"1:1,1:2,0:0,0:1"}}
```

可以看到 ZooKeeper 节点内容中的限流副本列表和前面使用 kafka-config.sh 脚本时的一样。其实 kafka-reassign-partitions.sh 脚本提供的限流功能背后的实现原理就是配置与限流相关的那4个参数而已，没有什么太大的差别。不过使用 kafka-config.sh 脚本的方式来实现复制限流的功能比较烦琐，并且在手动配置限流副本列表时也比较容易出错，这里推荐大家使用 kafka-reassign-partitions.sh 脚本配合 throttle 参数的方式，方便快捷且不容易出错。



# <a name="95">修改副本因子</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

创建主题之后我们还可以修改分区的个数，同样可以修改副本因子（副本数）。修改副本因子的使用场景也很多，比如在创建主题时填写了错误的副本因子数而需要修改，再比如运行一段时间之后想要通过增加副本因子数来提高容错性和可靠性。

前面主要讲述了分区重分配的相关细节，本节中修改副本因子的功能也是通过重分配所使用的 kafka-reassign-partition.sh 脚本实现的。我们仔细观察一下上一节中的示例使用的 project.json 文件：

```
{
    "version": 1,
    "partitions": [
        {
            "topic": "topic-throttle",
            "partition": 1,
            "replicas": [
                2,
                0
            ],
            "log_dirs": [
                "any",
                "any"
            ]
        },
        {
            "topic": "topic-throttle",
            "partition": 0,
            "replicas": [
                0,
                2
            ],
            "log_dirs": [
                "any",
                "any"
            ]
        },
        {
            "topic": "topic-throttle",
            "partition": 2,
            "replicas": [
                0,
                2
            ],
            "log_dirs": [
                "any",
                "any"
            ]
        }
    ]
}
```

可以观察到 JSON 内容里的 replicas 都是2个副本，我们可以自行添加一个副本，比如对分区1而言，可以改成下面的内容：

```
{
    "topic": "topic-throttle",
    "partition": 1,
    "replicas": [
        2,
        1,
        0
    ],
    "log_dirs": [
        "any",
        "any",
        "any"
    ]
}
```

我们可以将其他分区的 replicas 内容也改成[0,1,2]，这样每个分区的副本因子就都从2增加到了3。注意增加副本因子时也要在 log_dirs中添加一个“any”，这个log_dirs 代表 Kafka 中的日志目录，对应于 broker 端的 log.dir 或 log.dirs 参数的配置值，如果不需要关注此方面的细节，那么可以简单地设置为“any”。我们将修改后的 JSON 内容保存为新的 add.json 文件。在执行 kafka-reassign-partition.sh 脚本前，主题 topic-throttle 的详细信息（副本因子为2）如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-throttle
Topic:topic-throttle    PartitionCount:3    ReplicationFactor:2 Configs:
    Topic: topic-throttle   Partition: 0    Leader: 0   Replicas: 0,1   Isr: 0,1
    Topic: topic-throttle   Partition: 1    Leader: 1   Replicas: 1,2   Isr: 2,1
    Topic: topic-throttle   Partition: 2    Leader: 2   Replicas: 2,0   Isr: 2,0
```

执行 kafka-reassign-partition.sh 脚本（execute），详细信息如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-reassign-partitions.sh --zookeeper localhost:2181/kafka --execute --reassignment-json-file add.json
Current partition replica assignment

{"version":1,"partitions":[{"topic":"topic-throttle","partition":2,"replicas":[2,0],"log_dirs":["any","any"]},{"topic":"topic-throttle","partition":1,"replicas":[1,2],"log_dirs":["any","any"]},{"topic":"topic-throttle","partition":0,"replicas":[0,1],"log_dirs":["any","any"]}]}

Save this to use as the --reassignment-json-file option during rollback
Successfully started reassignment of partitions.
```

执行之后再次查看主题 topic-throttle 的详细信息，详细信息如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-throttle
Topic:topic-throttle    PartitionCount:3    ReplicationFactor:3 Configs:
    Topic: topic-throttle   Partition: 0    Leader: 0   Replicas: 0,1,2 Isr: 0,1,2
    Topic: topic-throttle   Partition: 1    Leader: 1   Replicas: 0,1,2 Isr: 2,1,0
    Topic: topic-throttle   Partition: 2    Leader: 2   Replicas: 0,1,2 Isr: 2,0,1
```

可以看到相应的副本因子数已经增加到3了。

与修改分区数不同的是，副本数还可以减少，这个其实很好理解，最直接的方式是关闭一些 broker，不过这种手法不太正规。这里我们同样可以通过 kafka-reassign-partition.sh 脚本来减少分区的副本因子。再次修改 project.json 文件中的内容，内容参考如下：

```
{"version":1,"partitions":[{"topic":"topic-throttle","partition":2,"replicas":[0],"log_dirs":["any"]},{"topic":"topic-throttle","partition":1,"replicas":[1],"log_dirs":["any"]},{"topic":"topic-throttle","partition":0,"replicas":[2],"log_dirs":["any"]}]}
```

再次执行 kafka-reassign-partition.sh 脚本（execute）之后，主题 topic-throttle 的详细信息如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --describe --topic topic-throttle 
Topic:topic-throttle	PartitionCount:3	ReplicationFactor:1	Configs:
     Topic: topic-throttle	Partition: 0	Leader: 2	Replicas: 2	Isr: 2
     Topic: topic-throttle	Partition: 1	Leader: 1	Replicas: 1	Isr: 1
     Topic: topic-throttle	Partition: 2	Leader: 0	Replicas: 0	Isr: 0
```

可以看到主题 topic-throttle 的副本因子又被修改为1了。

细心的读者可能注意到我们执行 kafka-reassign-partition.sh 脚本（execute）所使用的候选方案都是手动修改的，在增加副本因子的时候由于整个示例集群中只有3个 broker 节点，从2增加到3只需填满副本即可。再者，示例中减少副本因子的时候改成了1，这样可以简单地把各个 broker 节点轮询一遍，如此也就不太会有负载不均衡的影响。不过在真实应用中，可能面对的是一个包含了几十个 broker 节点的集群，将副本数从2修改为5，或者从4修改为3的时候，如何进行合理的分配是一个关键的问题。

我们可以参考17节中的分区副本的分配来进行相应的计算，不过如果不是通过程序来得出结果而是通过人工去计算的，也确实比较烦琐。下面演示了如何通过程序来计算出分配方案（实质上是17节中对应的方法），如代码清单24-1所示。

```
代码清单24-1 分配方案计算（Scala）
object ComputeReplicaDistribution {
  val partitions = 3
  val replicaFactor = 2

  def main(args: Array[String]): Unit = {
    val brokerMetadatas = List(new BrokerMetadata(0, Option("rack1")),
      new BrokerMetadata(1, Option("rack1")),
      new BrokerMetadata(2, Option("rack1")))
    val replicaAssignment = AdminUtils.assignReplicasToBrokers(brokerMetadatas,
      partitions, replicaFactor)
    println(replicaAssignment)
  }
}
```

代码中计算的是集群节点为[0,1,2]、分区数为3、副本因子为2、无机架信息的分配方案，程序输出如下：

```
Map(2 -> ArrayBuffer(0, 2), 1 -> ArrayBuffer(2, 1), 0 -> ArrayBuffer(1, 0))
```

分区2对应于[0,2]，分区1对应于[2,1]，分区0对应于[1,0]，所以在一个3节点的集群中将副本因子修改为2的对应候选方案为：

```
{"version":1,"partitions":[{"topic":"topic-throttle","partition":2,"replicas":[0,2],"log_dirs":["any","any"]},{"topic":"topic-throttle","partition":1,"replicas":[2,1],"log_dirs":["any","any"]},{"topic":"topic-throttle","partition":0,"replicas":[1,0],"log_dirs":["any","any"]}]}
```



# <a name="96">如何选择合适的分区数</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

如何选择合适的分区数？这是很多 Kafka 的使用者经常面临的问题，不过对这个问题而言，似乎并没有非常权威的答案。而且这个问题显然也没有固定的答案，只能从某些角度来做具体的分析，最终还是要根据实际的业务场景、软件条件、硬件条件、负载情况等来做具体的考量。本节主要介绍与本问题相关的一些重要决策因素，使读者在遇到类似问题时能够有参考依据。

## <a name="97">性能测试工具</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在 Kafka 中，性能与分区数有着必然的关系，在设定分区数时一般也需要考虑性能的因素。对不同的硬件而言，其对应的性能也会不太一样。在实际生产环境中，我们需要了解一套硬件所对应的性能指标之后才能分配其合适的应用和负荷，所以性能测试工具必不可少。

本节要讨论的性能测试工具是 Kafka 本身提供的用于生产者性能测试的 kafka-producer- perf-test.sh 和用于消费者性能测试的 kafka-consumer-perf-test.sh。

首先我们通过一个示例来了解一下 kafka-producer-perf-test.sh 脚本的使用。我们向一个只有1个分区和1个副本的主题topic-1中发送100万条消息，并且每条消息大小为1024B，生产者对应的 acks 参数为1。详细内容参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-producer-perf-test.sh --topic topic-1 --num-records 1000000 --record-size 1024 --throughput -1 --producer-props bootstrap. servers=localhost:9092 acks=1
273616 records sent, 54723.2 records/sec (53.44 MB/sec), 468.6 ms avg latency, 544.0 max latency.
337410 records sent, 67482.0 records/sec (65.90 MB/sec), 454.4 ms avg latency, 521.0 max latency.
341910 records sent, 68382.0 records/sec (66.78 MB/sec), 449.4 ms avg latency, 478.0 max latency.
1000000 records sent, 63690.210815 records/sec (62.20 MB/sec), 456.17 ms avg latency, 544.00 ms max latency, 458 ms 50th, 517 ms 95th, 525 ms 99th, 543 ms 99.9th.
```

示例中在使用 kafka-producer-perf-test.sh 脚本时用了多一个参数，其中 topic 用来指定生产者发送消息的目标主题；num-records 用来指定发送消息的总条数；record-size 用来设置每条消息的字节数；producer-props 参数用来指定生产者的配置，可同时指定多组配置，各组配置之间以空格分隔，与 producer-props 参数对应的还有一个 producer.config 参数，它用来指定生产者的配置文件；throughput 用来进行限流控制，当设定的值小于0时不限流，当设定的值大于0时，当发送的吞吐量大于该值时就会被阻塞一段时间。下面的示例中设置了 throughout 的值为100字节，我们来看一下实际的效果：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-producer-perf-test.sh --topic topic-1 --num-records 1000000 --record-size 1024 --throughput 100 --producer-props bootstrap. servers=localhost:9092 acks=1
502 records sent, 100.3 records/sec (0.10 MB/sec), 2.5 ms avg latency, 266.0 max latency.
501 records sent, 100.0 records/sec (0.10 MB/sec), 0.9 ms avg latency, 11.0 max latency.
500 records sent, 99.9 records/sec (0.10 MB/sec), 0.8 ms avg latency, 3.0 max latency.
501 records sent, 100.2 records/sec (0.10 MB/sec), 0.7 ms avg latency, 3.0 max latency.
500 records sent, 100.0 records/sec (0.10 MB/sec), 0.7 ms avg latency, 5.0 max latency.
(…省略若干)
```

kafka-producer-perf-test.sh 脚本中还有一个有意思的参数 print-metrics，指定了这个参数时会在测试完成之后打印很多指标信息，对很多测试任务而言具有一定的参考价值。示例参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-producer-perf-test.sh --topic topic-1 --num-records 1000000 --record-size 1024 --throughput -1 --print-metrics --producer-props bootstrap.servers=localhost:9092 acks=1 
272926 records sent, 54585.2 records/sec (53.31 MB/sec), 469.6 ms avg latency, 638.0 max latency.
331020 records sent, 66204.0 records/sec (64.65 MB/sec), 463.8 ms avg latency, 507.0 max latency.
345960 records sent, 69192.0 records/sec (67.57 MB/sec), 443.8 ms avg latency, 477.0 max latency.
1000000 records sent, 63552.589768 records/sec (62.06 MB/sec), 457.73 ms avg latency, 638.00 ms max latency, 457 ms 50th, 532 ms 95th, 592 ms 99th, 633 ms 99.9th.

Metric Name                                                         Value
app-info:commit-id:{client-id=producer-1}					: 3402a8361b734732
app-info:version:{client-id=producer-1}					: 2.0.0
kafka-metrics-count:count:{client-id=producer-1}			: 94.000
producer-metrics:batch-size-avg:{client-id=producer-1}	: 15555.923
producer-metrics:batch-size-max:{client-id=producer-1}	: 15556.000
producer-metrics:batch-split-rate:{client-id=producer-1}	: 0.000
producer-metrics:batch-split-total:{client-id=producer-1}	: 0.000
producer-metrics:buffer-available-bytes:{client-id=producer-1}  	  : 33554432.000
producer-metrics:buffer-exhausted-rate:{client-id=producer-1}	  : 0.000
producer-metrics:buffer-exhausted-total:{client-id=producer-1}  : 0.000
producer-metrics:buffer-total-bytes:{client-id=producer-1}	  : 33554432.000
producer-metrics:bufferpool-wait-ratio:{client-id=producer-1}	  : 0.278
producer-metrics:bufferpool-wait-time-total:{client-id=producer-1}	  : 12481086207.000
(…省略若干)
```

kafka-producer-perf-test.sh 脚本中还有一些其他的参数，比如 payload-delimiter、transactional-id 等，读者可以自行探索一下此脚本的更多细节。

我们再来关注 kafka-producer-perf-test.sh 脚本的输出信息，以下面的一行内容为例：

```
1000000 records sent, 63690.210815 records/sec (62.20 MB/sec), 456.17 ms avg latency, 544.00 ms max latency, 458 ms 50th, 517 ms 95th, 525 ms 99th, 543 ms 99.9th.
```

records sent 表示测试时发送的消息总数；records/sec 表示以每秒发送的消息数来统计吞吐量，括号中的 MB/sec 表示以每秒发送的消息大小来统计吞吐量，注意这两者的维度；avg latency 表示消息处理的平均耗时；max latency 表示消息处理的最大耗时；50th、95th、99th 和 99.9th 分别表示 50%、95%、99% 和 99.9% 的消息处理耗时。 kafka-consumer-perf-test.sh 脚本的使用也比较简单，下面的示例简单地演示了其使用方式：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-perf-test.sh --topic topic-1 --messages 1000000 --broker-list localhost:9092
start.time, end.time, data.consumed.in.MB, MB.sec, data.consumed.in.nMsg, nMsg.sec, rebalance.time.ms, fetch.time.ms, fetch.MB.sec, fetch.nMsg.sec
2018-09-22 12:27:49:827, 2018-09-22 12:27:57:068, 976.5625, 134.8657, 1000000, 138102.4720, 105, 7136, 136.8501, 140134.5291
```

示例中只是简单地消费主题 topic-1 中的100万条消息。脚本中还包含了许多其他的参数，比如 from-latest、group、print-metrics、threads 等，篇幅限制，读者可以自行了解这些参数的使用细节。

输出结果中包含了多项信息，分别对应起始运行时间（start.time）、结束运行时间（end.time）、消费的消息总量（data.consumed.in.MB，单位为MB）、按字节大小计算的消费吞吐量（MB.sec，单位为MB/s）、消费的消息总数（data.consumed.in.nMsg）、按消息个数计算的吞吐量（nMsg.sec）、再平衡的时间（rebalance.time.ms，单位为ms）、拉取消息的持续时间（fetch.time.ms，单位为ms）、每秒拉取消息的字节大小（fetch.MB.sec，单位为MB/s）、每秒拉取消息的个数（fetch.nMsg.sec）。其中 fetch.time.ms = end.time – start.time – rebalance.time.ms。

这里只是简单地了解两个脚本的基本用法，读者还可以通过设置不同的参数来调节测试场景以获得针对当前硬件资源的一份相对比较完善的测试报告。

## <a name="98">分区数越多吞吐量就越高吗</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

分区是 Kafka 中最小的并行操作单元，对生产者而言，每一个分区的数据写入是完全可以并行化的；对消费者而言，Kafka 只允许单个分区中的消息被一个消费者线程消费，一个消费组的消费并行度完全依赖于所消费的分区数。如此看来，如果一个主题中的分区数越多，理论上所能达到的吞吐量就越大，那么事实真的如预想的一样吗？

我们使用上面介绍的性能测试工具来实际测试一下。首先分别创建分区数为1、20、50、100、200、500、1000的主题，对应的主题名称分别为topic-1、topic-20、topic-50、topic-100、topic-200、topic-500、topic-1000，所有主题的副本因子都设置为1。

消息中间件的性能一般是指吞吐量（广义来说还包括延迟）。抛开硬件资源的影响，消息写入的吞吐量还会受到消息大小、消息压缩方式、消息发送方式（同步/异步）、消息确认类型（acks）、副本因子等参数的影响，消息消费的吞吐量还会受到应用逻辑处理速度的影响。本案例中暂不考虑这些因素的影响，所有的测试除了主题的分区数不同，其余的因素都保持相同。

本次案例中使用的测试环境为一个由3台普通云主机组成的3节点的 Kafka 集群，每台云主机的内存大小为8GB、磁盘大小为40GB、4核CPU的主频为2600MHz。JVM 版本为1.8.0_112，Linux系统版本为2.6.32-504.23.4.el6.x86_64。 使用 kafka-producer-perf-test.sh 脚本分别向这些主题中发送100万条消息体大小为1KB的消息，对应的测试命令如下：

```
bin/kafka-producer-perf-test.sh --topic topic-xxx --num-records 1000000 --record-size 1024 --throughput -1 --producer-props bootstrap.servers=localhost: 9092 acks=1
```

对应的生产者性能测试结果如下图所示。不同的硬件环境，甚至不同批次的测试得到的测试结果也不会完全相同，但总体趋势还是会保持和下图中的一样。



![4-2](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/10/1696356a44b9a676~tplv-t2oaga2asx-watermark.awebp)



在上图中，我们可以看到分区数为1时吞吐量最低，随着分区数的增长，相应的吞吐量也跟着上涨。一旦分区数超过了某个阈值之后，整体的吞吐量是不升反降的。也就是说，并不是分区数越多吞吐量也越大。这里的分区数临界阈值针对不同的测试环境也会表现出不同的结果，实际应用中可以通过类似的测试案例（比如复制生产流量以便进行测试回放）来找到一个合理的临界值区间。

上面针对的是消息生产者的测试，对消息消费者而言同样有吞吐量方面的考量。使用 kafka-consumer-perf-test.sh 脚本分别消费这些主题中的100万条消息，对应的测试命令如下：

```
bin/kafka-consumer-perf-test.sh --topic topic-xxx --messages 1000000 --broker-list localhost:9092
```

消费者性能测试的结果如下图所示。与生产者性能测试相同的是，不同的测试环境或不同的测试批次所得到的测试结果也不尽相同，但总体趋势还是会保持和下图中的一样。



![4-3](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/10/1696357a5ab2bc03~tplv-t2oaga2asx-watermark.awebp)



在上图中，随着分区数的增加，相应的吞吐量也会有所增长。一旦分区数超过了某个阈值之后，整体的吞吐量也是不升反降的，同样说明了分区数越多并不会使吞吐量一直增长。

在同一套环境下，我们还可以测试一下同时往两个分区数为200的主题中发送消息的性能，假设测试结果中两个主题所对应的吞吐量分别为A和B，再测试一下只往一个分区数为200的主题中发送消息的性能，假设此次测试结果中得到的吞吐量为C，会发现A<C、B<C且A+B>C。可以发现由于共享系统资源的因素，A和B之间会彼此影响。通过A+B>C的结果，可知第一张图中 topic-200 的那个点位也并没有触及系统资源的瓶颈，发生吞吐量有所下降的结果也并非是系统资源瓶颈造成的。

本节针对分区数越多吞吐量越高这个命题进行反证，其实要证明一个观点是错误的，只需要举个反例即可，本节的内容亦是如此。不过本节并没有指明分区数越多吞吐量就越低这个观点，并且具体吞吐量的数值和走势还会和磁盘、文件系统、I/O调度策略相关。分区数越多吞吐量也就越高？网络上很多资料都认可这一观点，但实际上很多事情都会有一个临界值，当超过这个临界值之后，很多原本符合既定逻辑的走向又会变得不同。读者需要对此有清晰的认知，懂得去伪求真，实地测试验证不失为一座通向真知的桥梁。



## <a name="99">分区数的上限</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

一味地增加分区数并不能使吞吐量一直得到提升，并且分区数也并不能一直增加，如果超过默认的配置值，还会引起 Kafka 进程的崩溃。读者可以试着在一台普通的 Linux 机器上创建包含10000个分区的主题，比如在下面示例中创建一个主题 topic-bomb：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-bomb --replication-factor 1 --partitions 10000
Created topic "topic-bomb".
```

执行完成后可以检查 Kafka 的进程是否还存在（比如通过 jps 命令或 ps -aux|grep kafka 命令）。一般情况下，会发现原本运行完好的 Kafka 服务已经崩溃。此时或许会想到，创建这么多分区，是不是因为内存不够而引起的进程崩溃？我们在启动 Kafka 进程的时候将 JVM 堆设置得大一点是不是就可以解决问题了。其实不然，创建这些分区而引起的内存增长完全不足以让 Kafka“畏惧”。

为了分析真实的原因，我们可以打开 Kafka 的服务日志文件（$KAFKA_HOME/logs/ server.log）来一探究竟，会发现服务日志中出现大量的异常：

```
[2018-09-13 00:36:40,019] ERROR Error while creating log for topic-bomb-xxx in dir /tmp/kafka-logs (kafka.server.LogDirFailureChannel)
java.io.IOException: Too many open files 
     at java.io.UnixFileSystem.createFileExclusively(Native Method)
     at java.io.File.createNewFile(File.java:1012)
     at kafka.log.AbstractIndex.<init>(AbstractIndex.scala:54)
     at kafka.log.OffsetIndex.<init>(OffsetIndex.scala:53)
     at kafka.log.LogSegment$.open(LogSegment.scala:634)
     at kafka.log.Log.loadSegments(Log.scala:503)
     at kafka.log.Log.<init>(Log.scala:237)
```

异常中最关键的信息是“Too many open flies”，这是一种常见的 Linux 系统错误，通常意味着文件描述符不足，它一般发生在创建线程、创建 Socket、打开文件这些场景下。在 Linux 系统的默认设置下，这个文件描述符的个数不是很多，通过 ulimit 命令可以查看：

```
[root@node1 kafka_2.11-2.0.0]# ulimit -n
1024
[root@node1 kafka_2.11-2.0.0]# ulimit -Sn
1024
[root@node1 kafka_2.11-2.0.0]# ulimit -Hn
4096
```

ulimit 是在系统允许的情况下，提供对特定 shell 可利用的资源的控制。-H 和 -S 选项指定资源的硬限制和软限制。硬限制设定之后不能再添加，而软限制则可以增加到硬限制规定的值。如果 -H 和 -S 选项都没有指定，则软限制和硬限制同时设定。限制值可以是指定资源的数值或 hard、soft、unlimited 这些特殊值，其中 hard 代表当前硬限制，soft 代表当前软件限制，unlimited 代表不限制。如果不指定限制值，则打印指定资源的软限制值，除非指定了 -H 选项。硬限制可以在任何时候、任何进程中设置，但硬限制只能由超级用户设置。软限制是内核实际执行的限制，任何进程都可以将软限制设置为任意小于等于硬限制的值。

我们可以通过测试来验证本案例中的 Kafka 的崩溃是否是由于文件描述符的限制而引起的。下面我们在一个包含3个节点的 Kafka 集群中挑选一个节点进行具体的分析。首先通过 jps 命令查看 Kafka 进程 pid 的值：

```
[root@node1 kafka_2.11-2.0.0]# jps -l
31796 kafka.Kafka
```

查看当前 Kafka 进程所占用的文件描述符的个数（注意这个值并不是Kafka第一次启动时就需要占用的文件描述符的个数，示例中的 Kafka 环境下已经存在了若干主题）：

```
[root@node1 kafka_2.11-2.0.0]# ls /proc/31796/fd | wc -l
194
```

我们再新建一个只有一个分区的主题，并查看 Kafka 进程所占用的文件描述符的个数：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-bomb-1 --replication-factor 1 --partitions 1
Created topic "topic-bomb-1".

[root@node1 kafka_2.11-2.0.0]# ls /proc/31796/fd | wc -l
195
```

可以看到增加了一个分区，对应的也只增加了一个文件描述符。之前我们通过 ulimit命令可以看到软限制是1024，我们创建一个具有829（1024-195=829）个分区的主题：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-bomb-2 --replication-factor 1 --partitions 829
Created topic "topic-bomb-2".

[root@node1 kafka_2.11-2.0.0]# ls /proc/31796/fd | wc -l
1024
```

可以看到 Kafka 进程此时占用了1024个文件描述符，并且运行完好。这时我们还可以联想到硬限制4096这个关键数字，我们再创建一个包含3071（4096-1024=3072，这里特地少创建1个分区）个分区的主题，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-bomb-3 --replication-factor 1 --partitions 3071
Created topic "topic-bomb-3".

[root@node1 kafka_2.11-2.0.0]# ls /proc/31796/fd | wc -l
4095
```

Kafka 进程依旧完好，文件描述符占用为4095，逼近最高值4096。最后我们再次创建一个只有一个分区的主题：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/ kafka --create --topic topic-bomb-4 --replication-factor 1 --partitions 1
Created topic "topic-bomb-4".

[root@node1 kafka_2.11-2.0.0]# ls /proc/31796/fd | wc -l
ls: cannot access /proc/31796/fd: No such file or directory
0
```

此时 Kafka 已经崩溃，查看进程号时已没有相关信息。查看 Kafka 中的日志，还会发现报出前面提及的异常“java.io.IOException: Too many open files”，表明已达到上限。

如何避免这种异常情况？对于一个高并发、高性能的应用来说，1024或4096的文件描述符限制未免太少，可以适当调大这个参数。比如使用 ulimit -n 65535命令将上限提高到65535，这样足以应对大多数的应用情况，再高也完全没有必要了。

```
[root@node1 kafka_2.11-2.0.0]# ulimit -n 65535
#可以再次查看相应的软硬限制数
[root@node1 kafka_2.11-2.0.0]# ulimit -Hn
65535
[root@node1 kafka_2.11-2.0.0]# ulimit -Sn
65535
```

也可以在/etc/security/limits.conf 文件中设置，参考如下：

```
#nofile - max number of open file descriptors
root soft nofile 65535
root hard nofile 65535
```

limits.conf 文件修改之后需要重启才能生效。limits.conf 文件与 ulimit 命令的区别在于前者是针对所有用户的，而且在任何 shell 中都是生效的，即与 shell 无关，而后者只是针对特定用户的当前 shell 的设定。在修改最大文件打开数时，最好使用 limits.conf 文件来修改，通过这个文件，可以定义用户、资源类型、软硬限制等。也可以通过在/etc/profile 文件中添加 ulimit 的设置语句来使全局生效。

设置之后可以再次尝试创建10000个分区的主题，检查一下 Kafka 是否还会再次崩溃。

## <a name="100">考量因素</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

如何选择合适的分区数？一个“恰如其分”的答案就是视具体情况而定。

从吞吐量方面考虑，增加合适的分区数可以在一定程度上提升整体吞吐量，但超过对应的阈值之后吞吐量不升反降。如果应用对吞吐量有一定程度上的要求，则建议在投入生产环境之前对同款硬件资源做一个完备的吞吐量相关的测试，以找到合适的分区数阈值区间。

在创建主题之后，虽然我们还能够增加分区的个数，但基于 key 计算的主题需要严谨对待。当生产者向 Kafka 中写入基于 key 的消息时，Kafka 通过消息的 key 来计算出消息将要写入哪个具体的分区，这样具有相同 key 的数据可以写入同一个分区。Kafka 的这一功能对于一部分应用是极为重要的，比如日志压缩（Log Compaction），详细可以参考[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)；再比如对于同一个 key 的所有消息，消费者需要按消息的顺序进行有序的消费，如果分区的数量发生变化，那么有序性就得不到保证。

在创建主题时，最好能确定好分区数，这样也可以省去后期增加分区所带来的多余操作。尤其对于与 key 高关联的应用，在创建主题时可以适当地多创建一些分区，以满足未来的需求。通常情况下，可以根据未来2年内的目标吞吐量来设定分区数。当然如果应用与 key 弱关联，并且具备便捷的增加分区数的操作接口，那么也可以不用考虑那么长远的目标。

有些应用场景会要求主题中的消息都能保证顺序性，这种情况下在创建主题时可以设定分区数为1，通过分区有序性的这一特性来达到主题有序性的目的。

当然分区数也不能一味地增加，参考前面的内容，分区数会占用文件描述符，而一个进程所能支配的文件描述符是有限的，这也是通常所说的文件句柄的开销。虽然我们可以通过修改配置来增加可用文件描述符的个数，但凡事总有一个上限，在选择合适的分区数之前，最好再考量一下当前 Kafka 进程中已经使用的文件描述符的个数。

分区数的多少还会影响系统的可用性。在前面章节中，我们了解到 Kafka 通过多副本机制来实现集群的高可用和高可靠，每个分区都会有一至多个副本，每个副本分别存在于不同的 broker 节点上，并且只有 leader 副本对外提供服务。在 Kafka 集群的内部，所有的副本都采用自动化的方式进行管理，并确保所有副本中的数据都能保持一定程度上的同步。当 broker 发生故障时，leader 副本所属宿主的 broker 节点上的所有分区将暂时处于不可用的状态，此时 Kafka 会自动在其他的 follower 副本中选举出新的 leader 用于接收外部客户端的请求，整个过程由 Kafka 控制器负责完成。分区在进行 leader 角色切换的过程中会变得不可用，不过对于单个分区来说这个过程非常短暂，对用户而言可以忽略不计。如果集群中的某个 broker 节点宕机，那么就会有大量的分区需要同时进行 leader 角色切换，这个切换的过程会耗费一笔可观的时间，并且在这个时间窗口内这些分区也会变得不可用。

分区数越多也会让 Kafka 的正常启动和关闭的耗时变得越长，与此同时，主题的分区数越多不仅会增加日志清理的耗时，而且在被删除时也会耗费更多的时间。对旧版的生产者和消费者客户端而言，分区数越多，也会增加它们的开销，不过这一点在新版的生产者和消费者客户端中有效地得到了抑制。

如何选择合适的分区数？从某种意思来说，考验的是决策者的实战经验，更透彻地说，是对 Kafka 本身、业务应用、硬件资源、环境配置等多方面的考量而做出的选择。在设定完分区数，或者更确切地说是创建主题之后，还要对其追踪、监控、调优以求更好地利用它。读者看到本节的内容之前或许没有对分区数有太大的困扰，而看完本节的内容之后反而困惑了起来，其实大可不必太过惊慌，一般情况下，根据预估的吞吐量及是否与 key 相关的规则来设定分区数即可，后期可以通过增加分区数、增加 broker 或分区重分配等手段来进行改进。如果一定要给一个准则，则建议将分区数设定为集群中 broker 的倍数，即假定集群中有3个 broker 节点，可以设定分区数为3、6、9等，至于倍数的选定可以参考预估的吞吐量。不过，如果集群中的 broker 节点数有很多，比如大几十或上百、上千，那么这种准则也不太适用，在选定分区数时进一步可以引入机架等参考因素。

## <a name="101">总结</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

从16节开始到这里笔者主要讲述了 Kafka 概念中的两大核心—主题和分区。通过对主题的增删查改、配置管理等内容来了解主题相关的知识点。通过对分区副本的一系列操作及分区数设定的考量因素来理解分区相关的概念，比如优先副本、限流、分区重分配等。还介绍了 `KafkaAdminClient`、`kafka-topics.sh`、`kafka-configs.sh`、`kafka-perferred-replica-election.sh`、`kafka-reassign-partitions.sh`、`kafka-producer-perf-test.sh`和`kafka-consumer-perf-test.sh` 等脚本的具体使用，读者可以通过实地操作来加深对这些内容的理解。



# <a name="102">消费组管理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在 Kafka 中，我们可以通过 kafka-consumer-groups.sh 脚本查看或变更消费组的信息。我们可以通过 list 这个指令类型的参数来罗列出当前集群中所有的消费组名称，示例如下（这个功能对应 KafkaAdminClient 中的 listConsumerGroups() 方法）：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
console-consumer-98513
groupIdMonitor
console-consumer-49560
console-consumer-69403
console-consumer-66179
console-consumer-33348
console-consumer-82390
console-consumer-38225
```

注意，在之前的版本中还可以通过 zookeeper 参数来连接指定的 ZooKeeper 地址，因为在旧版的 Kafka 中可以将消费组的信息存储在 ZooKeeper 节点中，不过在2.0.0版本中已经将这个参数删除了，目前只能通过正统的 bootstrap-server 参数来连接 Kafka 集群以此来获取消费者的相应信息。

kafka-consumer-groups.sh 脚本还可以配合 describe 这个指令类型的参数来展示某一个消费组的详细信息，不过要完成此功能还需要配合 group 参数来一同实现，group 参数用来指定特定消费组的名称。下面的示例中展示了消费组 groupIdMonitor 的详细信息（这个功能对应 KafkaAdminClient 中的 describeConsumerGroups(Collection<String> groupIds) 方法）：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor

TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                     HOST            CLIENT-ID
topic-monitor   0          668             668             0               consumer-1-063cdec2-b525-4ba3-bbfe-db9a92e3b21d /192.168.0.2  consumer-1
topic-monitor   1          666             666             0               consumer-1-063cdec2-b525-4ba3-bbfe-db9a92e3b21d /192.168.0.2  consumer-1
topic-monitor   2          666             666             0               consumer-1-273faaf0-c950-44a8-8a11-41a116f79fd4 /192.168.0.2  consumer-1
```

在展示的结果中包含多个字段的信息，其中 TOPIC 表示消费组订阅的主题名称；PARTITION 表示对应的分区编号；CURRENT-OFFSET 表示消费组最新提交的消费位移；LOG-END-OFFSET 表示的是HW（高水位）；LAG表示消息滞后的数量，是 LOG-END-OFFSET 与 CURRENT-OFFSET 的数值之差，详细内容还可以参考32节。CUNSUMER_ID 表示消费组的成员ID，对应于 member_id；HOST 表示消费者的 host 信息；CLIENT-ID 对应于消费者客户端中的 clientId。

消费组一共有 Dead、Empty、PreparingRebalance、CompletingRebalance、Stable 这几种状态，正常情况下，一个具有消费者成员的消费组的状态为 Stable。我们可以通过 state 参数来查看消费组当前的状态，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor --state

COORDINATOR (ID)         ASSIGNMENT-STRATEGY    STATE                #MEMBERS
192.168.0.4:9092 (2)    range                     Stable               2
```

如果消费组内没有消费者，那么对应的状态为 Empty，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor --state
Consumer group 'groupIdMonitor' has no active members.

COORDINATOR (ID)         ASSIGNMENT-STRATEGY    STATE                #MEMBERS
192.168.0.4:9092 (2)                               Empty                0
```

我们还可以通过 members 参数罗列出消费组内的消费者成员信息，参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor --members

CONSUMER-ID                                     HOST            CLIENT-ID          #PARTITIONS
consumer-1-273faaf0-c950-44a8-8a11-41a116f79fd4 /192.168.0.2  consumer-1      1
consumer-1-063cdec2-b525-4ba3-bbfe-db9a92e3b21d /192.168.0.2  consumer-1      2
```

如果在此基础上再增加一个 verbose 参数，那么还会罗列出每个消费者成员的分配情况，如下所示。

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor --members --verbose

CONSUMER-ID                                     HOST            CLIENT-ID       #PARTITIONS     ASSIGNMENT
consumer-1-063cdec2-b525-4ba3-bbfe-db9a92e3b21d /192.168.0.2  consumer-1      2          topic-monitor(0,1)
consumer-1-b5bb268b-d077-4db8-b525-9d60cd0ee06b /192.168.0.2  consumer-1      1          topic-monitor(2)
```

我们可以通过 delete 这个指令类型的参数来删除一个指定的消费组，不过如果消费组中有消费者成员正在运行，则删除操作会失败，详细参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --delete --group groupIdMonitor
Error: Deletion of some consumer groups failed:
* Group 'groupIdMonitor' could not be deleted due to: NON_EMPTY_GROUP

[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --delete --group groupIdMonitor
Deletion of requested consumer groups ('groupIdMonitor') was successful.

[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor 
Error: Consumer group 'groupIdMonitor' does not exist.
```

在 KafkaAdminClient 中也有一个 deleteConsumerGroups(Collection<String> groupIds) 方法用来删除指定的消费组。

# <a name="103">消费位移管理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

kafka-consumer-groups.sh 脚本还提供了重置消费组内消费位移的功能，具体是通过 reset-offsets 这个指令类型的参数来实施的，不过实现这一功能的前提是消费组内没有正在运行的消费者成员。下面的示例将消费组中的所有分区的消费位移都置为0，详细参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092  --group groupIdMonitor --all-topics --reset-offsets --to-earliest --execute
Error: Assignments can only be reset if the group 'groupIdMonitor' is inactive, but the current state is Stable.

TOPIC                          PARTITION  NEW-OFFSET     

[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092  --group groupIdMonitor --all-topics --reset-offsets --to-earliest --execute

TOPIC                          PARTITION  NEW-OFFSET     
topic-monitor                  1          0              
topic-monitor                  0          0              
topic-monitor                  2          0              

[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor
Consumer group 'groupIdMonitor' has no active members.

TOPIC        PARTITION   CURRENT-OFFSET   LOG-END-OFFSET    LAG      CONSUMER-ID       HOST           CLIENT-ID
topic-monitor    1           0                   999             999             -               -               -
topic-monitor    0           0                  1001            1001            -               -               -
topic-monitor    2           0                  1000            1000            -               -               -
```

可以通过将 --all-topics 修改为 --topic 来实现更加细粒度的消费位移的重置，all-topics 参数指定了消费组中所有主题，而 topic 参数可以指定单个主题，甚至可以是主题中的若干分区。下面的示例将主题 topic-monitor 分区2的消费位移置为分区的末尾：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092  --group groupIdMonitor --topic topic-monitor:2 --reset-offsets --to-latest --execute

TOPIC                          PARTITION  NEW-OFFSET     
topic-monitor                  2          1000           

[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor
Consumer group 'groupIdMonitor' has no active members.

TOPIC         PARTITION    CURRENT-OFFSET   LOG-END-OFFSET   LAG          CONSUMER-ID     HOST         CLIENT-ID
topic-monitor    1               0               999             999               -               -               -
topic-monitor    0               0              1001            1001               -               -               -
topic-monitor    2           1000              1000                0               -               -               -
```

前面的两个示例中各自使用了 to-earliest 和 to-latest 参数来分别将消费位移调整到分区的开头和末尾。除此之外，kafka-consumer-groups.sh 脚本还提了更多的选择。

- by-duration <String: duration>：将消费位移调整到距离当前时间指定间隔的最早位移处。duration 的格式为“PnDTnHnMnS”。
- from-file <String: path to CSV file>：将消费位移重置到CSV文件中定义的位置。
- shift-by <Long: number-of-offsets>：把消费位移调整到当前位移 + number-of-offsets 处，number-of-offsets 的值可以为负数。
- to-current：将消费位移调整到当前位置处。
- to-datetime <String: datatime>：将消费位移调整到大于给定时间的最早位移处。datatime 的格式为“YYYY-MM-DDTHH:mm:SS.sss”。
- to-offset <Long: offset>：将消费位移调整到指定的位置。

kafka-consumer-groups.sh 脚本中还有两个参数 dry-run 和 export，dry-run 是只打印具体的调整方案而不执行，export 是将位移调整方案以 CSV 的格式输出到控制台，而 execute 才会执行真正的消费位移重置。下面的示例演示了 execute、dry-run、export、to-current、shift-by、from-file 的具体用法：

```
# <a name="104">查看当前消费组的消费位移</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor
Consumer group 'groupIdMonitor' has no active members.

TOPIC           PARTITION   CURRENT-OFFSET   LOG-END-OFFSET   LAG       CONSUMER-ID      HOST        CLIENT-ID
topic-monitor      1           999                999              0               -               -               -
topic-monitor      0          1001               1001             0               -               -               -
topic-monitor      2          1000               1000             0               -               -               -
# <a name="105">将消费位移往前调整10，但是不执行</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092  --group groupIdMonitor --topic topic-monitor --reset-offsets --shift-by -10 --dry-run

TOPIC                          PARTITION  NEW-OFFSET     
topic-monitor                  2           990            
topic-monitor                  1           989            
topic-monitor                  0           991    
# <a name="106">将消费位移调整为当前位移并将结果输出到控制台，但是也不执行</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092  --group groupIdMonitor --topic topic-monitor --reset-offsets --to-current --export -dry-run
topic-monitor,2,1000
topic-monitor,1,999
topic-monitor,0,1001
# <a name="107">将消费位移再次往前调整20并输出结果，但是不执行</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092  --group groupIdMonitor --topic topic-monitor --reset-offsets --shift-by -20 --export --dry-run
topic-monitor,2,980
topic-monitor,1,979
topic-monitor,0,981
# <a name="108">中间步骤：将上面的输出结果保存到offsets.csv文件中</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
# <a name="109">通过from-file参数从offsets.csv文件中获取位移重置策略，并且执行</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092  --group groupIdMonitor --topic topic-monitor --reset-offsets --from-file offsets.csv --execute

TOPIC                          PARTITION  NEW-OFFSET     
topic-monitor                  2          980            
topic-monitor                  1          979            
topic-monitor                  0          981            
# <a name="110">最终消费位移都往前重置了20</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[root@node1 kafka_2.11-2.0.0]# bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor
Consumer group 'groupIdMonitor' has no active members.

TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG        CONSUMER-ID     HOST            CLIENT-ID
topic-monitor   1           979                999              20              -               -               -
topic-monitor   0           981               1001              20              -               -               -
topic-monitor   2           980               1000              20              -               -               -
```

# <a name="111">手动删除消息</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

kafka-delete-records.sh 这个脚本可以用来删除指定位置前的消息。当一个分区被创建的时候，它的起始位置（logStartOffset）为0。我们可以通过 KafkaConsumer 中的 beginningOffsets() 方法来查看分区的起始位置，参考代码清单27-1：

```
//代码清单27-1 查看分区起始位置
KafkaConsumer<String, String> kafkaConsumer = createNewConsumer();
List<PartitionInfo> partitions = kafkaConsumer.partitionsFor("topic-monitor");
List<TopicPartition> tpList = partitions.stream()
        .map(pInfo -> new TopicPartition(pInfo.topic(), pInfo.partition()))
        .collect(toList());
Map<TopicPartition, Long> beginningOffsets = 
        kafkaConsumer.beginningOffsets(tpList);
System.out.println(beginningOffsets);
```

输出结果如下：

```
{topic-monitor-0=0, topic-monitor-1=0, topic-monitor-2=0}
```

下面使用 kafka-delete-records.sh 脚本来删除部分消息。在执行具体的删除动作之前需要先配置一个 JSON 文件，用来指定所要删除消息的分区及对应的位置。我们需要分别删除主题 topic-monitor 下分区0中偏移量为10、分区1中偏移量为11和分区2中偏移量为12的消息：

```
{
    "partitions": [
        {
            "topic": "topic-monitor",
            "partition": 0,
            "offset": 10
        },
                {
            "topic": "topic-monitor",
            "partition": 1,
            "offset": 11
        },
                {
            "topic": "topic-monitor",
            "partition": 2,
            "offset": 12
        }
    ],
    "version": 1
}
```

之后将这段内容保存到文件中，比如取名为 delete.json，在此之后，我们就可以通过 kafka-delete-records.sh 脚本中的 offset-json-file 参数来指定这个 JSON 文件。具体的删除操作如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-delete-records.sh --bootstrap-server localhost:9092 --offset-json-file delete.json
Executing records delete operation
Records delete operation completed:
partition: topic-monitor-0	low_watermark: 10
partition: topic-monitor-1	low_watermark: 11
partition: topic-monitor-2	low_watermark: 12
```

我们再次执行代码清单27-1，可以发现最后的运行结果已经变为：

```
{topic-monitor-0=10, topic-monitor-1=11, topic-monitor-2=12}
```

kafka-delete-records.sh 脚本内部是通过调用 KafkaAdminClient 中的 deleteRecords() 方法来实现的，这个方法的具体定义如下所示。

```
public DeleteRecordsResult deleteRecords(
        Map<TopicPartition, RecordsToDelete> recordsToDelete)
```

deleteRecords() 方法最终还需要通过发送 DeleteRecordsRequest 请求来通知 Kafka 完成相应的“删除”动作。其实 Kafka 并不会直接删除消息，它在收到 DeleteRecordsRequest 请求之后，会将指定分区的 logStartOffset 置为相应的请求值（比如分区0的偏移量10），最终的删除消息的动作还是交由日志删除任务来完成的。



# <a name="112">Kafka Connect</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka Connect 是一个工具，它为在 Kafka 和外部数据存储系统之间移动数据提供了一种可靠的且可伸缩的实现方式。Kafka Connect 可以简单快捷地将数据从 Kafka 中导入或导出，数据范围涵盖关系型数据库、日志和度量数据、Hadoop 和数据仓库、NoSQL 数据存储、搜索索引等。相对于生产者和消费者客户端而言，Kafka Connect 省掉了很多开发的工作，尤其是编码部分，这使得应用开发人员更容易上手。

Kafka Connect 有两个核心概念：Source 和 Sink。参考下图，Source 负责导入数据到 Kafka，Sink 负责从 Kafka 导出数据，它们都被称为 Connector（连接器）。



![9-1](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/10/169639a7f1a65e19~tplv-t2oaga2asx-watermark.awebp)



在 Kafka Connect 中还有两个重要的概念：Task 和 Worker。Task 是 Kafka Connect 数据模型的主角，每一个 Connector 都会协调一系列的 Task 去执行任务，Connector 可以把一项工作分割成许多 Task，然后把 Task 分发到各个 Worker 进程中去执行（分布式模式下），Task 不保存自己的状态信息，而是交给特定的 Kafka 主题去保存。Connector 和 Task 都是逻辑工作单位，必须安排在进程中执行，而在 Kafka Connect 中，这些进程就是 Worker。

Kafka Connect 提供了以下特性。

- 通用性：规范化其他数据系统与 Kafka 的集成，简化了连接器的开发、部署和管理。
- 支持独立模式（standalone）和分布式模式（distributed）。
- REST 接口：使用 REST API 提交和管理 Connector。
- 自动位移管理：自动管理位移提交，不需要开发人员干预，降低了开发成本。
- 分布式和可扩展性：Kafka Connect 基于现有的组管理协议来实现扩展 Kafka Connect 集群。
- 流式计算/批处理的集成。

## <a name="113">独立模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka 中的 connect-standalone.sh 脚本用来实现以独立的模式运行 Kafka Connect。在独立模式下所有的操作都是在一个进程中完成的，这种模式非常适合测试或功能验证的场景。由于是单进程，所以独立模式无法充分利用 Kafka 自身所提供的负载均衡和高容错等特性。

在执行这个脚本时需要指定两个配置文件：一个是用于 Worker 进程运行的相关配置文件；另一个是指定 Source 连接器或 Sink 连接器的配置文件，可以同时指定多个连接器配置，每个连接器配置文件对应一个连接器，因此要保证连接器名称全局唯一，连接器名称通过 name 参数指定。

下面我们先来了解一下 Source 连接器的用法：将文件 source.txt 中的内容通过 Source 连接器写入 Kafka 的主题 topic-connect。首先修改用于 Worker 进程运行的配置文件（$KAFKA_HOME/config/connect-standalone.properties），内容参考如下：

```
bootstrap.servers=localhost:9092
key.converter=org.apache.kafka.connect.json.JsonConverter
value.converter=org.apache.kafka.connect.json.JsonConverter
key.converter.schemas.enable=true
value.converter.schemas.enable=true
offset.storage.file.filename=/tmp/connect.offsets
offset.flush.interval.ms=10000
```

bootstrap.servers 参数用来配置与 Kafka 集群连接的地址。key.converter 和 value.converter 参数指定 Kafka 消息中 key 和 value 的格式转化类，本例中使用 JsonConverter 来将每一条消息的 key 和 value 都转化成 JSON 格式。key.converter. schemas.enable 和 value.converter.schemas.enable 参数用来指定 JSON 消息中是否可以包含 schema。offset.storage.file.filename 参数用于指定保存偏移量的文件路径。offset.flush.interval.ms 参数用于设定提交偏移量的频率。

接下来修改 Source 连接器的配置文件（$KAFKA_HOME/config/connect-file-source.properties），内容参考如下：

```
name=local-file-source
connector.class=FileStreamSource
tasks.max=1
file=/opt/kafka_2.11-2.0.0/source.txt
topic=topic-connect
```

name 参数用来配置连接器的名称。connector.class 用来设置连接器类的全限定名称，有时候设置为类名也是可以的，Kafka Connect 会在 classpath 中自动搜索这个类并加载。Kafka 中默认只提供了与文件相关的连接器，如果要实现与其他数据存储系统相连接，那么可以参考文件连接器的具体实现来自定义一套连接器，或者搜寻开源的实现，比如 Confluent 公司提供的一些产品：

- kafka-connect-elasticsearch（[github.com/confluentin…](https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Fconfluentinc%2Fkafka-connect-elasticsearch%EF%BC%89%EF%BC%9B)
- kafka-connect-jdbc（[github.com/confluentin…](https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Fconfluentinc%2Fkafka-connect-jdbc%EF%BC%89%EF%BC%9B)
- kafka-connect-hdfs（[github.com/confluentin…](https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Fconfluentinc%2Fkafka-connect-hdfs%EF%BC%89%EF%BC%9B)
- kafka-connect-storage-cloud（[github.com/confluentin…](https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Fconfluentinc%2Fkafka-connect-storage-cloud%EF%BC%89%E3%80%82)

task.max 参数指定了 Task 的数量。file 参数指定该连接器数据源文件路径，这里指定了 Kafka 根目录下的 source.txt 文件，在启动连接器前需要先创建好它。topic 参数设置连接器把数据导入哪个主题，如果该主题不存在，则连接器会自动创建，不过建议最好还是提前手工创建该主题。比如，对本例中的主题 topic-connect 而言，可以事先创建，它的详细信息如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/kafka --create --topic topic-connect --replication-factor 1 --partitions 1
Created topic "topic-connect".

[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost:2181/kafka --describe --topic topic-connect
Topic:topic-connect	PartitionCount:1	ReplicationFactor:1	Configs: 
    Topic: topic-connect	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
```

接下来就可以启动 Source 连接器了，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/connect-standalone.sh config/connect-standalone.properties config/connect-file-source.properties
```

连接器启动之后，向 source.txt 文件中输入两条句子：

```
[root@node1 kafka_2.11-2.0.0]# echo "hello kafka connect">> source.txt
[root@node1 kafka_2.11-2.0.0]# echo "hello kafka streams">> source.txt
```

之后可以观察主题 topic-connect 中是否包含这两条消息。对于这个示例，我们既可以使用 kafka-console-consumer.sh 脚本，也可以使用 kafka-dump-log.sh 脚本来查看内容。这里再来回顾一下 kafka-dump-log.sh 脚本的用法：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-dump-log.sh --files /tmp/kafka-logs/topic-connect-0/00000000000000000000.log --print-data-log
Dumping /tmp/kafka-logs/topic-connect-0/00000000000000000000.log

Starting offset: 0
offset: 0 position: 0 CreateTime: 1540368601287 isvalid: true keysize: 30 valuesize: 77 magic: 2 compresscodec: NONE producerId: -1 producerEpoch: -1 sequence: -1 isTransactional: false headerKeys: [] key: {"schema":null,"payload":null} payload: {"schema":{"type":"string","optional":false},"payload":"hello kafka connect"}
offset: 1 position: 177 CreateTime: 1540368621321 isvalid: true keysize: 30 valuesize: 77 magic: 2 compresscodec: NONE producerId: -1 producerEpoch: -1 sequence: -1 isTransactional: false headerKeys: [] key: {"schema":null,"payload":null} payload: {"schema":{"type":"string","optional":false},"payload":"hello kafka streams"}
```

可以看到主题 topic-connect 中的消息格式为 JSON 字符串并且带有对应的 schema 信息，这一点和在 config/connect-standalone.properties 配置的内容一一对应。

我们再来看一下 Sink 连接器的用法：将主题 topic-connect 中的内容通过 Sink 连接器写入文件 sink.txt。这里对 config/connect-standalone.properties 文件稍做修改，参考如下：

```
bootstrap.servers=localhost:9092
key.converter=org.apache.kafka.connect.storage.StringConverter
value.converter=org.apache.kafka.connect.storage.StringConverter
key.converter.schemas.enable=true
value.converter.schemas.enable=true
offset.storage.file.filename=/tmp/connect.offsets
offset.flush.interval.ms=10000
```

这里将 Kafka 消息中的 key 和 value 的格式转化类指定为 StringConverter。

紧接着我们再配置 Sink 连接器的配置文件（$KAFKA_HOME/config/connect-file-sink.properties），内容参考如下（注意与 Source 连接器配置的区别）：

```
name=local-file-sink
connector.class=FileStreamSink
tasks.max=1
file=/opt/kafka_2.11-2.0.0/sink.txt
topics=topic-connect
```

接下来就可以启动 Sink 连接器了，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/connect-standalone.sh 
     config/connect-standalone.properties config/connect-file-sink.properties
```

我们往主题 topic-connect 中发送一条消息：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic-connect
>hello kafka
>
```

进而就可以在 sink.txt 文件中看到这条消息：

```
[root@node1 kafka_2.11-2.0.0]# cat sink.txt 
hello kafka
```

## <a name="114">REST API</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

我们可以通过 Kafka Connect 提供的基于 REST 风格的 API 接口来管理连接器，默认端口号为8083，可以通过 Worker 进程的配置文件中的 rest.port 参数来修改端口号。Kafka Connect REST API 接口如下表所示。

| REST API                                       | 释 义                                      |
| ---------------------------------------------- | ------------------------------------------ |
| GET /                                          | 查看 Kafka 集群版本信息                    |
| GET /connectors                                | 查看当前活跃的连接器列表，显示连接器的名字 |
| POST /connectors                               | 根据指定配置，创建一个新的连接器           |
| GET /connectors/{name}                         | 查看指定连接器的信息                       |
| GET /connectors/{name}/config                  | 查看指定连接器的配置信息                   |
| PUT /connectors/{name/config                   | 修改指定连接器的配置信息                   |
| GET /connectors/{name}/statue                  | 查看指定连接器的状态                       |
| POST /connectors/{name}/restart                | 重启指定的连接器                           |
| PUT /connectors/{name}/pause                   | 暂停指定的连接器                           |
| GET /connectors/{name}/tasks                   | 查看指定连接器正在运行的 Task              |
| POST /connectors/{name}/tasks                  | 修改 Task 的配置                           |
| GET /connectors/{name}/tasks/{taskId}/status   | 查看指定连接器中指定 Task 的状态           |
| POST /connectors/{name}/tasks/{tasked}/restart | 重启指定连接器中指定的 Task                |
| DELETE /connectors/{name}                      | 删除指定的连接器                           |

简单示例如下，更多的 REST API 调用示例可以参考下一节的内容。

```
[root@node1 kafka_2.11-2.0.0]# curl http://localhost:8083/
{"version":"2.0.0","commit":"3402a8361b734732","kafka_cluster_id":"Cjr-rkl5SLClosMiOfMpqw"}

[root@node1 kafka_2.11-2.0.0]# curl http://localhost:8083/connectors
["local-file-source"]
```

## <a name="115">分布式模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

与独立模式不同，分布式模式天然地结合了 Kafka 提供的负载均衡和故障转移功能，能够自动在多个节点机器上平衡负载。不过，以分布式模式启动的连接器并不支持在启动时通过加载连接器配置文件来创建一个连接器，只能通过访问 REST API 来创建连接器。

在运行分布式模式的连接器前，同样要修改 Worker 进程的相关配置文件（$KAFKA_HOME/ config/connect-distributed.properties），内容参考如下：

```
bootstrap.servers=localhost1:9092, localhost2:9092, localhost3:9092
group.id=connect-cluster
key.converter=org.apache.kafka.connect.json.JsonConverter
value.converter=org.apache.kafka.connect.json.JsonConverter
(….省略若干)
```

之后启动分布式模式，这里的运行脚本也变成了对应的 connect-distributed.sh，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/connect-distributed.sh 
      config/connect-distributed.properties 
```

接下来创建一个 Source 连接器，此前先要设定好这个连接器的相关配置，内容如下：

```
{
    "name":"local-file-distribute-source",
    "config":{
        "topic":"topic-distribute-source",
        "connector.class":"FileStreamSource",
        "key.converter":"org.apache.kafka.connect.storage.StringConverter",
        "value.converter":"org.apache.kafka.connect.storage.StringConverter",
        "converter.internal.key.converter":
"org.apache.kafka.connect.storage.StringConverter",
        "converter.internal.value.converter":
"org.apache.kafka.connect.storage.StringConverter",
        "file":"/opt/kafka_2.11-2.0.0/distribute-source.txt"
    }
}
```

这个连接器从 distribute-source.txt 文件中读取内容进而传输到主题 topic-distribute-source 中，在创建连接器前确保 distribute-source.txt 文件和主题 topic-distribute-source 都已创建完毕。接下来调用 POST /connectors 接口来创建指定的连接器，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# curl -i -X POST -H "Content-Type:application/ json" -H "Accept:application/json" -d '{"name":"local-file-distribute-source", "config":{"topic":"topic-distribute-source","connector.class":"FileStreamSource","key.converter":"org.apache.kafka.connect.storage.StringConverter","value.converter":"org.apache.kafka.connect.storage.StringConverter","converter.internal.key.converter":"org.apache.kafka.connect.storage.StringConverter","converter.internal.value.converter":"org.apache.kafka.connect.storage.StringConverter","file":"/opt/kafka_2.11-2.0.0/distribute-source.txt"}}' http://localhost:8083/connectors
HTTP/1.1 201 Created
Date: Wed, 24 Oct 2018 09:38:12 GMT
Location: http://localhost:8083/connectors/local-file-distribute-source
Content-Type: application/json
Content-Length: 598
Server: Jetty(9.4.11.v20180605)

{"name":"local-file-distribute-source","config":{"topic":"topic-distribute-source","connector.class":"FileStreamSource","key.converter":"org.apache.kafka.connect.storage.StringConverter","value.converter":"org.apache.kafka.connect.storage.StringConverter","converter.internal.key.converter":"org.apache.kafka.connect.storage.StringConverter","converter.internal.value.converter":"org.apache.kafka.connect.storage.StringConverter","file":"/opt/kafka_2.11-2.0.0/distribute-source.txt","name":"local-file-distribute-source"},"tasks":[{"connector":"local-file-distribute-source","task":0}],"type":null}
```

接下来就可以向 distribute-source.txt 文件中写入内容，然后订阅消费主题 topic-distribute- source 中的消息来验证是否成功。在使用完毕之后，我们可以调用 DELETE /connectors/ {name} 接口来删除对应的连接器：

```
[root@node1 kafka_2.11-2.0.0]# curl -i -X DELETE http://localhost:8083/ connectors/local-file-distribute-source
HTTP/1.1 204 No Content
Date: Wed, 24 Oct 2018 09:42:47 GMT
Server: Jetty(9.4.11.v20180605)

[root@node1 kafka_2.11-2.0.0]# curl -i http://localhost:8083/connectors
HTTP/1.1 200 OK
Date: Wed, 24 Oct 2018 09:43:05 GMT
Content-Type: application/json
Content-Length: 2
Server: Jetty(9.4.11.v20180605)

[]
```

读者可以自行尝试分布式模式下 Sink 连接器的使用方法。

在向 Kafka 写入数据或从 Kafka 读取数据时，要么使用普通的生产者和消费者客户端，要么使用 Kafka Connect，那么在不同场景下到底使用哪一种呢？Kafka 客户端需要内嵌到业务应用程序里，应用程序需要经常修改以便灵活地将数据推送到 Kafka 或从 Kafka 中消费消息，适用于开发人员。如果要将 Kafka 连接到数据存储系统中，可以使用 Kafka Connect，因为在这种场景下往往也不需要修改对应的代码，适用于非开发人员，他们可以通过配置连接器的方式实现相应的功能。



# <a name="116">Kafka Mirror Maker</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka Mirror Maker 是用于在两个集群之间同步数据的一个工具，其实现原理是通过从源集群中消费消息，然后将消息生产到目标集群中，也就是普通的生产和消费消息。如果了解 RabbitMQ，那么会发现这个工具和 RabbitMQ 中的数据迁移插件 Federation/Shovel 的实现原理如出一辙。用户只需要在启动 Kafka Mirror Maker 时指定一些简单的消费端和生产端配置就可以实现准实时的数据同步。



![9-2](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/1696864261a97ef1~tplv-t2oaga2asx-watermark.awebp)



如上图所示，我们需要将集群 Cluster 1 中的消息同步到集群 Cluster 2 中。通过 Kafka Mirror Maker 做一个中间的周转站，我们就可以很容易地实现跨集群的数据同步。

在上一节中，我们了解了 Kafka Connect 的相关用法，它和 Kafka Mirror Maker 的区别在于：Kafka Connect 用于其他数据存储系统与 Kafka 之间的数据复制，而不是 Kafka 与 Kafka 之间的数据复制。在第17节中，分区重分配可以实现 Kafka 与 Kafka 之间的数据复制，它与 Kafka Mirror Maker 的区别在于它是单个集群内部的数据复制，而不是跨集群之间的数据复制。

Kafka Mirror Maker 可以在两个不同的数据中心（两个集群位于不同的数据中心）中同步（镜像）数据。我们可以在两个不同的数据中心中部署一个集群，各个数据中心持有集群中的部分 broker 节点，通过将副本分散到不同的数据中心来实现不同数据中心的数据同步。但这样有一个严重的问题，即高延迟，这个问题会严重影响 Kafka 和 ZooKeeper 的性能，还有可能引发严重的异常。

下面我们来了解一下 Kafka Mirror Maker 的用法，它具体对应 Kafka 中的 kafka-mirror-maker.sh 脚本。参考上图，我们演示从 Cluster 1 中将主题 topic-mirror 的数据同步到 Cluster 2 中，首先创建并配置两个配置文件，参考如下：

```
# <a name="117">consumer.properties的配置</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
bootstrap.servers=cluster1:9092
group.id=groupIdMirror
client.id=sourceMirror
partition.assignment.strategy=org.apache.kafka.clients.consumer.RoundRobinAssignor
# <a name="118">producer.properties的配置 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
bootstrap.servers=cluster2:9092
client.id=sinkMirror
```

consumer.properties 和 producer.properties 这两个配置文件中的配置对应消费者客户端和生产者客户端的配置，具体可以参考一下前面章节的内容。

下面就可以启动 Kafka Mirror Maker 了，参考如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-mirror-maker.sh --consumer.config consumer.properties --producer.config producer.properties --whitelist 'topic-mirror'
```

kafka-mirror-maker.sh 脚本中有多个可配置的参数，如下表所示。

| 参 数                       | 释 义                                                        |
| --------------------------- | ------------------------------------------------------------ |
| abort.on.send.failure       | 默认为 true                                                  |
| consumer.config             | 用于指定消费者的配置文件，配置文件里有两个必填的参数：boostrap.servers 和 group.id |
| consumer.rebalance.listener | 指定再均衡监听器                                             |
| help                        | 打印帮助信息                                                 |
| message.handler             | 指定消息的处理器。这个处理器会在消费者消费到消息之后且在生产者发送消息之前被调用 |
| message.handler.args        | 指定消息处理器的参数，同 message.handler 一起使用            |
| num.streams                 | 指定消费线程的数量                                           |
| offset.commit.interval.ms   | 指定消费位移提交间隔                                         |
| producer.config             | 用于指定生产者的配置文件，配置文件里唯一必填的参数是 bootstrap.servers |
| rebalance.listener.args     | 指定再均衡监听器的参数，同 consumer.rebalance.listener 一起使用 |
| whitelist                   | 指定需要复制的源集群中的主题。这个参数可以指定一个正则表达式，比如a |



注意，不要在单个集群的内部使用 Kafka Mirror Maker，否则会循环复制。如果在配置文件 consumer.properties 中配置的 bootstrap.servers 和在配置文件 producer.properties 中配置的 bootstrap.servers 的 broker 节点地址列表属于同一个集群，启动 Kafka Mirror Maker 之后，只要往主题 topic-mirror 中输入一条数据，那么这条数据会在这个主题内部无限循环复制，直至 Kafka Mirror Maker 关闭。

由于 kafka-mirror-maker.sh 脚本是启动一个生产者和一个消费者进行数据同步操作的，因此数据同步完成后，该命令依然在等待新的数据进行同步，也就是需要用户自己查看数据是否同步完成，在保证数据同步完成后手动关闭该命令。同时，用户可以在目标集群中创建主题，主题的分区数及副本因子可以与源集群中该主题对应的分区数及副本因子不一致。可以将目标集群中的 auto.create.topics.enable 参数配置为 true，以确保在同步操作时有对应的主题，不过建议在同步之前先确认是否有相关的主题，如果没有则手工创建，或者采用自定义的元数据同步工具进行创建。



![9-3](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/16968678f79fc908~tplv-t2oaga2asx-watermark.awebp)



源集群和目标集群是两个完全独立的实体。对每个主题而言，两个集群之间的分区数可能不同；就算分区数相同，那么经过消费再生产之后消息所规划到的分区号也有可能不同；就算分区数相同，消息所规划到的分区号也相同，那么消息所对应的 offset 也有可能不相同。

参考上图，源集群中由于执行了某次日志清理操作，某个分区的 logStartOffset 值变为10，而目标集群中对应分区的 logStartOffset 还是0，那么从源集群中原封不动地复制到目标集群时，同一条消息的 offset 也不会相同。如果要实现客户端生产消费的迁移（将通信链路从源集群中切换到目标集群中），在数据同步完成之后，也不可能不做任何改变就能实现完美的切换。

不过，如果能够做到源集群中的消息除 offset 外都在目标集群中一致（比如消息的分区号相同，主题的分区数相同），那么可以试着通过 kafka-consumer-group.sh 脚本重置消费位移（参考第27节）来实现合理的客户端迁移切换。或者先将生产者的链路切换到目标集群，然后等待消费者消费完源集群中的消息之后再将它的链路切换到目标集群。

kafka-mirror-maker.sh 脚本对应的实现类是 kafka.tools.MirrorMaker，它只有500多行代码，很多时候我们会把它与同类产品 uReplicator 进行对比，笔者觉得这样有失稳妥，前者的定位只是一个工具，而后者是一个完备的工程项目，它们都有各自的适用场景。不过话又说回来，uReplicator 底层也是基于 MirrorMaker 进行构建的，并针对 MirrorMaker 做了大量的调优及工程化改造，具体的内容可以参考官网介绍：[eng.uber.com/ureplicator…](https://link.juejin.cn/?target=http%3A%2F%2Feng.uber.com%2Fureplicator%2F%E3%80%82)



# <a name="119">Kafka Streams</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka 一直被认为是一个强大的消息中间件，它实现了高吞吐、高可用和低延时的消息传输能力，这让它成为流式处理系统中完美的数据来源。目前通用的一些流式处理框架如 Apache Spark、Apache Flink、Apache Storm 等都可以将 Kafka 作为可靠的数据来源。但遗憾的是，在 0.10.x 版本之前，Kafka 还并不具备任何数据处理的能力，但在此之后，Kafka Streams 应运而生。

Kafka Streams 是一个用于处理和分析数据的客户端库。它先把存储在 Kafka 中的数据进行处理和分析，然后将最终所得的数据结果回写到 Kafka 或发送到外部系统。它建立在一些非常重要的流式处理概念之上，例如适当区分事件时间和处理时间、窗口支持，以及应用程序状态的简单（高效）管理。同时，它也基于 Kafka 中的许多概念，例如通过划分主题进行扩展。此外，由于这个原因，它作为一个轻量级的库可以集成到应用程序中。这个应用程序可以根据需要独立运行、在应用程序服务器中运行、作为 Docker 容器，或者通过资源管理器（如 Mesos）进行操作。

Kafka Streams 直接解决了流式处理中的很多问题：

- 毫秒级延迟的逐个事件处理。
- 有状态的处理，包括连接（join）和聚合类操作。
- 提供了必要的流处理原语，包括高级流处理 DSL 和低级处理器 API。高级流处理 DSL 提供了常用流处理变换操作，低级处理器 API 支持客户端自定义处理器并与状态仓库交互。
- 使用类似 DataFlow 的模型对无序数据进行窗口化处理。
- 具有快速故障切换的分布式处理和容错能力。
- 无停机滚动部署。

单词统计是流式处理领域中最常见的示例，这里我们同样使用它来演示一下 Kafka Streams 的用法。在 Kafka 的代码中就包含了一个单词统计的示例程序，即 org.apache.kafka.streams. examples.wordcount.WordCountDemo，这个示例中以硬编码的形式用到了两个主题：streams-plaintext-input 和 streams-wordcount-output。为了能够使示例程序正常运行，我们需要预先准备好这两个主题，这两个主题的详细信息如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-topics.sh --zookeeper localhost: 2181/stream --describe --topic streams-wordcount-output,streams-plaintext-input
Topic:streams-plaintext-input	PartitionCount:1	ReplicationFactor:1	Configs:
	Topic: streams-plaintext-input	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
Topic:streams-wordcount-output	PartitionCount:1	ReplicationFactor:1	Configs:
	Topic: streams-wordcount-output	Partition: 0	Leader: 0	Replicas: 0	Isr: 0
```

之后我们就可以运行 WordCountDemo 这个示例了：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-run-class.sh 
     org.apache.kafka.streams.examples.wordcount.WordCountDemo
```

这个示例程序将从主题 streams-plaintext-input 中读取消息，然后对读取的消息执行单词统计，并将结果持续写入主题 streams-wordcount-output。

之后打开一个 shell 终端，并启动一个生产者来为主题 streams-plaintext-input 输入一些单词，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-console-producer.sh --broker-list localhost:9092 --topic streams-plaintext-input
>
```

之后再打开另一个 shell 终端，并启动一个消费者来消费主题 streams-wordcount -output 中的消息，示例如下：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-wordcount-output --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```

现在我们往主题 streams-plaintext-input 中输入 hello kafka streams：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-console-producer.sh --broker-list localhost:9092 --topic streams-plaintext-input
>hello kafka streams
```

通过 WordCountDemo 处理之后会在消费端看到如下的结果：

```
[root@node1 kafka_2.11-2.0.0]# bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-wordcount-output --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
hello 1
kafka 1
streams 1
```

输出结果中的第一列是消息的 key，这里表示被计数的单词，第二列是消息的 value，这里表示该单词的最新计数。

现在继续往主题 streams-plaintext-input 中输入 I love kafka streams，然后会在消费端看到有新的消息输出：

```
I 1
love 1
kafka 2
streams 2
```

最后2行打印的 kafka 2和 streams 2表示计数已经从1递增到2。每当向输入主题（streams-plaintext-input）中写入更多的单词时，将观察到新的消息被添加到输出主题（streams-wordcount-output）中，表示由 WordCount 应用程序计算出的最新计数。

下面我们通过 WordCountDemo 程序来了解一下Kafka Streams的开发方式，WordCountDemo 程序如代码清单30-1所示，对应的 Maven 依赖如下所示。

```
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-streams</artifactId>
    <version>2.0.0</version>
</dependency>
//代码清单30-1 单词统计示例
package org.apache.kafka.streams.examples.wordcount;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class WordCountDemo {
    public static void main(String[] args) {
        Properties props = new Properties();								①
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,
                "streams-wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092");
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
                Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
                Serdes.String().getClass().getName());

        StreamsBuilder builder = new StreamsBuilder();						②
        KStream<String, String> source = builder
                .stream("streams-plaintext-input");							③
        KTable<String, Long> counts = source
            .flatMapValues(value -> Arrays.asList(
                    value.toLowerCase(Locale.getDefault())
                            .split(" ")))
            .groupBy((key, value) -> value)
            .count();														④

        counts.toStream().to("streams-wordcount-output",
                Produced.with(Serdes.String(), Serdes.Long()));				⑤

        final KafkaStreams streams =
                new KafkaStreams(builder.build(), props);					⑥
        final CountDownLatch latch = new CountDownLatch(1);
        Runtime.getRuntime().addShutdownHook(
                new Thread("streams-wordcount-shutdown-hook") {
            @Override
            public void run() {
                streams.close();											⑦
                latch.countDown();
            }
        });

        try {
            streams.start();												⑧
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
        System.exit(0);
    }
}
```

第①行用于构建 Kafka Streams 的配置。每个 Kafka Streams 应用程序必须要有一个 application.id（StreamsConfig.APPLICATION_ID_CONFIG），这个 applicationId 用于协调应用实例，也用于命名内部的本地存储和相关主题。在整个 Kafka 集群中，applicationId 必须是唯一的。bootstrap.servers 参数配置的是 Kafka 集群的地址，这个参数也是必需的。default.key.serde 和 default.value.serde 分别用来设置消息的 key 和 value 的序列化器。

第②行创建了一个 KStreamBuilder 实例，在第③行中通过调用 KStreamBuilder 实例的 stream() 方法创建了一个 KStream 实例，并设定了输入主题 streams-plaintext-input。

之后在第④行中执行具体的单词统计逻辑。注意这里引入了 KStream 和 KTable 的概念，它们是 Kafka Streams 的两种基本抽象。两者的区别在于：KStream 是一个由键值对构成的抽象记录流，每个键值对是一个独立单元，即使相同的key也不会被覆盖，类似数据库的插入操作；KTable 可以理解成一个基于表主键的日志更新流，相同 key 的每条记录只保存最新的一条记录，类似数据库中基于主键的更新。

无论记录流（用 KStream 定义），还是更新日志流（用 KTable 定义），都可以从一个或多个 Kafka 主题数据源来创建。一个 KStream 可以与另一个 KStream 或 KTable 进行 Join 操作，或者聚合成一个 KTable。同样，一个 KTable 也可以转换成一个 KStream。KStream 和 KTable 都提供了一系列转换操作，每个转换操作都可以转化为一个 KStream 或 KTable 对象，将这些转换操作连接在一起就构成了一个处理器拓扑。

第⑤行中调用 toStream().to() 来将单词统计的结果写入输出主题 streams-wordcount-output。注意计算结果中的消息的 key 是 String 类型，而 value 是 Long 类型，这一点在代码中有所呈现。

最终在第⑥和第⑧行中基于拓扑和配置来订阅一个 KafkaStreams 对象，并启动 Kafka Streams 引擎。整体上而言，Kafka Streams 的程序简单易用，用户只需关心流处理转换的具体逻辑而不需要关心底层的存储等细节内容。

本节只是简单地介绍一下 Kafka Streams，让读者对 Kafka Streams 有一个大致的概念。目前流式处理领域还是 Apache Spark 和 Apache Flink 的天下，其中 Apache Spark 的市场份额占有率最大，在后面我们会详细介绍 Apache Spark（包括 Spark Streaming 和 Structured Streaming），以及它和 Kafka 的整合应用。

从第27节到这里我们主要介绍 Kafka 现有的几个应用工具，对一般用户而言，这些应用工具已经足够应对大多数的场景。不过，我们还可以利用 Kafka 现有的特性和功能来扩展一些高级应用，比如延时（迟）队列、重试队列等，读者可以在[《图解Kafka之核心原理》](https://juejin.cn/book/6844733792683458573)中查阅相关的内容。



# <a name="120">Kafka监控</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

任何应用功能再强大、性能再优越，如果没有与之匹配的监控，那么一切都是虚无缥缈的。监控不仅可以为应用提供运行时的数据作为依据参考，还可以迅速定位问题，提供预防及告警等功能，很大程度上增强了整体服务的鲁棒性。

目前的 Kafka 监控产品有很多，比如 Kafka Manager、Kafka Eagle、Kafka Monitor、KafkaOffsetMonitor、Kafka Web Console、Burrow 等，它们都有各自的优缺点。以 Kafka Manager 为例，它提供的监控功能也是相对比较完善的，在实际应用中具有很高的使用价值。但有一个遗憾就是其难以和公司内部系统平台关联，对于业务资源的使用情况、相应的预防及告警的联动无法顺利贯通。在人力、物力等条件允许的情况下，自定义一套监控系统非常有必要。

接下来几个章节的内容并不是讲述如何使用现存的一些 Kafka 监控产品，而是讲述如何自己实现一套 Kafka 的监控产品。从监控维度来看，Kafka 可以分为集群信息、broker 信息、主题信息和消费组信息四个方面。有些情况下，也可以将 ZooKeeper 的监控信息概括进来，毕竟 ZooKeeper 也是 Kafka 整体架构的一部分，不过这里并不打算讨论 ZooKeeper 的更多监控细节，本章只以 Kafka 本身为主进行探讨。以集群信息为例，它需要展示整个集群的整体面貌，其中可以囊括一些 broker 概要信息、主题概要信息和消费组概要信息等内容，下面3张图就展示的就一份关于集群层面信息的监控设计文稿。



![10-1](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/169687c711faa8ce~tplv-t2oaga2asx-watermark.awebp)



第一张图展示的是集群的一些总览信息，包括基本的主题个数、broker 节点个数、Kafka 版本、ZooKeeper 地址及版本等。图中右上角是集群中各个 broker 节点负载的占比，如果负载均衡严重失调，则会对集群整体性能及使用上造成很大的困扰。图中下半部分是一些历史曲线信息，比如整个集群的消息流入/流出速度（条/s），我们可以通过这些历史曲线来了解整个集群的运行状况。



![10-2](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/169687cee7bc9e39~tplv-t2oaga2asx-watermark.awebp)



第二张图展示的是集群中各个 broker 的必要信息，这样可以在全局上了解各个节点的运行状态，这个图的设计灵感来源于 RabbitMQ 的监控插件 rabbitmq_management，这种形式的信息概览设计得非常精巧，比如图中的 Controller 标记，代表集群中的唯一一个控制器所处的节点位置，这样一目了然。图中的每一项都可以链接到具体的 broker 信息的页面，这样可以更详细地了解每一个 broker（比如其中可以包含一些历史曲线等）。

监控一般要配套告警模块，否则只能人为地进行监控，有了告警模块可以对某些重要的监控项设定告警阈值，以便能够及时地通知相关的人员处理故障，或者也可以触发自动运维的动作，这一切都是为了更好地利用 Kafka 为应用服务，如下图所示。



![10-3](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/169687d676bd1190~tplv-t2oaga2asx-watermark.awebp)

这里只是给出一个设计的思路，以全局的视角展示一些监控信息项，至于全局的布局把握，在实现时还是要根据实际的情况来做具体的分析。至于相关的实现细节，我们不妨继续接着来看下面几个章节的内容。



# <a name="121">监控数据的来源</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

要实现一个自定义的 Kafka 监控系统，首先得知道从哪里获取监控指标。Kafka 自身提供的监控指标（包括 broker 和主题的指标，而集群层面的指标可以通过各个 broker 的指标值累加来获得）都可以通过 JMX（Java Managent Extension，Java 管理扩展）来获取，在使用 JMX 之前需要确保 Kafka 开启了 JMX 的功能（默认关闭）。Kafka 在启动时需要通过配置 JMX_PORT 来设置 JMX 的端口号并以此来开启 JMX 的功能，示例如下：

```
JMX_PORT=9999 nohup bin/kafka-server-start.sh config/server.properties &
```

开启 JMX 之后会在 ZooKeeper 的/brokers/ids/<brokerId>节点中有对应的呈现（jmx_port 字段对应的值），示例如下：

```
{"listener_security_protocol_map":{"PLAINTEXT":"PLAINTEXT"},"endpoints":["PLAINTEXT://localhost:9092"],"jmx_port":9999,"host":"localhost","timestamp":"1540025558270","port":9092,"version":4}
```

开启 JMX 功能之后，最简单的获取监控指标的方式莫过于直接使用 Java 自带的工具 JConsole 了（仅对 Java 用户而言，如果读者不喜欢这个工具，可以试一下 Kafka 自带的 kafka.tools.JmxTool），上面我们设置了 JMX 的端口号为9999（IP地址为 localhost），那么可以直接在 JConsole 中输入 service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi 或 localhost:9999 来连接 Kafka，如下图所示。



![10-4](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/1696889c7981c303~tplv-t2oaga2asx-watermark.awebp)



读者是否还记得在上一节中第2张图的右侧有一个 MsgIn 的指标，它表示当前 broker 中消息流入的速度，单位是条/s（messages/s），而下图中的 kafka.server-BrokerTopicMetrics-MessagesInPerSec-OneMinuteRate 对应的就是这个指标在一分钟内的监控数值。



![10-5](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/1696886e14027f5f~tplv-t2oaga2asx-watermark.awebp)



注意在 OneMinuteRate 同一级中还有 Count、FiveMinuteRate、FifteenMinuteRate、MeanRate、RateUnit 属性，它们与 OneMinuteRate 一起所对应的具体含义如下表所示。

| 属 性 名 称       | 属 性 含 义                                                  |
| ----------------- | ------------------------------------------------------------ |
| Count             | 消息流入的总数                                               |
| FiveMinuteRate    | 5分钟内流入的平均速度                                        |
| FifteenMinuteRate | 15分钟内流入的平均速度                                       |
| EventType         | 事件类型，对 MsgIn 而言固定为“messages”，表示消息个数；对于一些其他类型的指标，这时间类型的值会有所不同，比如对与 MessagesInPerSec 同一级别的 BytesInPerSec 而言，这个属性值为“bytes”，表示字节数 |
| OneMinuteRate     | 1分钟内流入的平均速度                                        |
| MeanRate          | 平均速度                                                     |
| RateUnit          | 时间单位，值固定为 SECONDS，即“秒”，它和 EventType 组成这个指标的单位，即 messages/s，也就是条/s |



## <a name="122">OneMinuteRate</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

OneMinuteRate 不是我们通常思维逻辑上的“一分钟内的平均速度”，而是一个受历史时刻影响的拟合值。如果通过程序计算某一个分钟内的平均速度值，那么有可能你会发现所得到的计算值与 OneMinuteRate 的值相差很大。

Kafka 是基于 Yammer Metrics 进行指标统计的，Yammer Metrics 是由 Yammer 提供的一个 Java 库，用于检测 JVM 上相关服务运行的状态，它对 OneMinuteRate 的定义如下所示。

Returns the one-minute exponentially-weighted moving average rate at which events have occurred since the meter was created.

由定义可知，OneMinuteRate 是一种指数加权移动平均值（学术上简称为[EWMA](https://link.juejin.cn/?target=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FEWMA_chart)）。在 Yammer Metrics 中关于 OneMinuteRate 的实现细节如下。 首先定义 alpha 的值：

```
private static final double M1_ALPHA = 1.0D - Math.exp(-0.08333333333333333D);
```

这个 alpha 的值大概为0.07995558537067671。OneMinuteRate 的计算代码如下：

```
long count = this.uncounted.getAndSet(0L);
double instantRate = (double)count / this.interval;
if(this.initialized) {
    this.rate += this.alpha * (instantRate - this.rate);
} else {
    this.rate = instantRate;
    this.initialized = true;
}
```

可以简化为：

```
X[n] = X[n-1] + alpha*(X[interval] - X[n-1]) = alpha * X[interval] + (1-alpha) * X[n-1];
```

其中 X[interval] 指的是在 T[n-1] 至 T[n] 时间内的真实测算值，或者可以认为是真实值。上面的公式可以换算为：

```
X[当前预估值] = alpha×X[当前真实值] + (1-alpha) ×X[上一时刻的预估值]
```

鉴于 Yammer Metrics 中的 OneMinuteRate 的 alpha 值为0.08左右，所以这个 OneMinuteRate 的值特别“倚重”历史值。

还有两个类似的值 FiveMinuteRate 和 FifteenMinuteRate，它们的计算过程与 OneMinuteRate 一样，只是 alpha 的值不一样；

```
//0.01652854617838251
private static final double M5_ALPHA = 1.0D - Math.exp(-0.016666666666666666D); 
//0.005540151995103271
private static final double M15_ALPHA = 1.0D - Math.exp(-0.005555555555555555D);
```

实际情况下，在发送速度起伏较大的时候，OneMinuteRate 的值与对应的一分钟内的真实值相差很大。如果发送速度趋于平缓并持续一段时间，那么 OneMinuteRate 的值才与真实值相匹配。读者在使用这个属性时需要熟记它背后代表的具体含义，避免在实际应用中产生偏差。

## <a name="123">获取监控指标</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

前面我们了解了如何使用工具来连接 Kafka 并获取相关的监控指标，不过我们并不能指望在监控系统中嵌入这些工具来获取监控指标。Java 自身就包含了 JMX 的连接器，通过它就可以让我们能够用编程的手段来使监控系统很容易地获取相应的监控指标值。

在通过 JMX 获取某个具体的监控指标值之前需要指定对应的 JMX 指标（MBean）名称，同样以前面的 MsgIn 为例，它对应的 MBean 名称为：

```
kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec
```

Kafka 自身提供的指标有很多（其余的会在后面的篇幅中详细说明），虽然它们的 MBean 名称一般都有规律可循，但是要记住这些内容也并非易事，在实际使用它们时可以通过 JConsole 工具来辅助获取。如下图所示，我们可以很容易找到 MessagesInPerSec（MsgIn），但要拼写成功整个 MBean 名称的话还需要费点精力，不如直接复制右侧 ObjectName 所对应的值。



![10-6](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/169688dec457cc92~tplv-t2oaga2asx-watermark.awebp)



注意，在“古老”的 Kafka 0.8.2.0 之前，MBean 名称的组织形式会有所不同，不过同样通过 JConsole 工具辅助来获取具体的 MBean 名称。代码清单31-1中给出了一个详细的示例来演示如何通过编程的手段获取 MsgIn 指标所对应的值。

```
//代码清单31-1 使用JMX来获取监控指标
import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

public class JmxConnectionDemo {
    private MBeanServerConnection conn;
    private String jmxURL;
    private String ipAndPort;

    public JmxConnectionDemo(String ipAndPort) {
        this.ipAndPort = ipAndPort;
    }
    //初始化JMX连接
    public boolean init(){
        jmxURL = "service:jmx:rmi:///jndi/rmi://" + ipAndPort + "/jmxrmi";
        try {
            JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);
            JMXConnector connector = JMXConnectorFactory
                    .connect(serviceURL, null);
            conn = connector.getMBeanServerConnection();
            if (conn == null) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    //获取MsgIn指标的值
    public double getMsgInPerSec() {
        //拼接MsgIn对应的MBean的名称
        String objectName = "kafka.server:type=BrokerTopicMetrics," +
                "name=MessagesInPerSec";
        Object val = getAttribute(objectName, "OneMinuteRate");
        if (val != null) {
            return (double) (Double) val;
        }
        return 0.0;
    }
	    //根据MBean名称和属性来获取具体的值
    private Object getAttribute(String objName, String objAttr) {
        ObjectName objectName;
        try {
            objectName = new ObjectName(objName);
            return conn.getAttribute(objectName, objAttr);
        } catch (MalformedObjectNameException | IOException |
                ReflectionException | InstanceNotFoundException |
                AttributeNotFoundException | MBeanException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

可以通过运行下面的程序来获取具体的指标值：

```
public static void main(String[] args) {
    JmxConnectionDemo jmxConnectionDemo =
            new JmxConnectionDemo("localhost:9999");
    jmxConnectionDemo.init();
    System.out.println(jmxConnectionDemo.getMsgInPerSec());
}
```

这段代码可以在 Github 上获取，请点击[这里](https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Fhiddenzzh%2Fkafka_book_demo%2Fblob%2Fmaster%2Fsrc%2Fmain%2Fjava%2Fchapter10%2FJmxConnectionDemo.java)。

上面的示例是获取了某个 broker 当前一分钟内消息流入的速度（messages/s），如果要计算整个集群的 MsgIn，那么只需将旗下的各个 broker 的 MsgIn 值累计即可。

通过 JMX 可以获取 Kafka 自身提供的运行状态指标，不过一些配置类信息（如各个 broker 的IP地址、端口号、JMX 端口号、AR 信息和 ISR 信息等）一般无法通过 JMX 来获取。对于 broker 的IP地址之类的信息，我们可以通过手动配置的方式来将其加入监控系统，但对于 AR 和 ISR 信息之类的信息，我们难以通过手动的方式来解决，这里可以借助 Kafka 连接的 ZooKeeper 来实现这些信息的获取，比如前面提及的/brokers/ids/<brokerId>节点。

除此之外，对于 Kafka 的一些硬件指标，比如 iowait、ioutil 等可以通过第三方工具如 Falcon、Zabbix 来获取。



# <a name="124">消费滞后</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

消息堆积是消息中间件的一大特色，消息中间件的流量削峰、冗余存储等功能正是得益于消息中间件的消息堆积能力。然而消息堆积是一把亦正亦邪的“双刃剑”，如果应用场合不恰当，反而会对上下游的业务造成不必要的麻烦，比如消息堆积势必会影响上下游整个调用链的时效性。在某些情况下，有些中间件如 RabbitMQ 在发生消息堆积时还会影响自身的性能。对 Kafka 而言，虽然消息堆积不会给其自身性能带来太大的困扰，但难免会影响上下游的业务，堆积过多有可能造成磁盘爆满，或者触发日志清除操作而造成消息丢失的情况。如何利用好消息堆积这把双刃剑，监控是其中关键的一步。

消息堆积是消费滞后（Lag）的一种表现形式，消息中间件中留存的消息与消费的消息之间的差值即为消息堆积量，也称为消费滞后（Lag）量。对 Kafka 的使用者而言，消费 Lag 是他们非常关心的一个指标。

![10-7](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/169689507ad50500~tplv-t2oaga2asx-watermark.awebp)

通过前面章节的内容，我们了解 logStartOffset、HW、LEO 这些分区中消息位置的概念，如上图所示。对每一个分区而言，它的 Lag 等于 HW – ConsumerOffset 的值，其中 ConsumerOffset 表示当前的消费位移。

以上针对的都是普通的情况，如果为消息引入了事务，那么 Lag 的计算方式就会有所不同。如果消费者客户端的 isolation.level 参数配置为“read_uncommitted”（默认），那么 Lag 的计算方式不受影响；如果这个参数配置为“read_committed”，那么就要引入 LSO 来进行计算了。LSO 是 LastStableOffset 的缩写，如图下图所示。对未完成的事务而言，LSO 的值等于事务中第一条消息的位置（firstUnstableOffset），对已完成的事务而言，它的值同 HW 相同，所以我们可以得出一个结论：LSO≤HW≤LEO。

![10-8](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/16968963d41fd445~tplv-t2oaga2asx-watermark.awebp)

对于分区中有未完成的事务，并且消费者客户端的 isolation.level 参数配置为“read_committed”的情况，它对应的 Lag 等于 LSO – ConsumerOffset 的值。

为了便于说明问题，在下面的陈述中如无特殊说明，Lag 的计算都针对没有事务的情况。虽然使用事务的场景远没有非事务的场景多，但读者对 LSO 的概念也要有一定的认知，避免在真正使用事务的时候对 Lag 的理解造成偏差。

要计算 Lag，首先得获取 ConsumerOffset 和 HW 的值，ConsumerOffset 保存在内部主题__consumer_offsets 中，HW 又时刻在变化，那么这两个变量该如何获取呢？在27节中我们讲述了 kafka-consumer-groups.sh 脚本的用法，这个脚本可以让我们很方便地查看消费组内每个分区所对应的 Lag，我们不妨借鉴一下它的实现方法：

- 首先通过 DescribeGroupsRequest 请求获取当前消费组的元数据信息，当然在这之前还会通过 FindCoordinatorRequest 请求查找消费组对应的 GroupCoordinator。
- 接着通过 OffsetFetchRequest 请求获取消费位移 ConsumerOffset。
- 然后通过 KafkaConsumer 的 endOffsets(Collection partitions)方法（对应于 ListOffsetRequest 请求）获取 HW（LSO）的值。
- 最后通过 HW 与 ConsumerOffset 相减得到分区的 Lag，要获得主题的总体 Lag 只需对旗下的各个分区累加即可。

除了 Lag，我们发现 kafka-consumer-groups.sh 脚本中打印的其他信息也很重要，下面的示例程序（代码清单32-1）演示了如何实现同“bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group groupIdMonitor”一样的效果，其中还包含了它对应的 TOPIC、PARTITION、CURRENT-OFFSET、Log-END-OFFSET 等信息。如果读者只想关注其中的 Lag 信息，则可以自行缩减一下代码。

代码较长，可以在 Github 上获取，请点击[这里](https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Fhiddenzzh%2Fkafka_book_demo%2Fblob%2Fmaster%2Fsrc%2Fmain%2Fjava%2Fchapter10%2FKafkaConsumerGroupService.java)。

```java
///代码清单32-1 消息堆积计算示例
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;
import java.util.concurrent.ExecutionException;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Slf4j
public class KafkaConsumerGroupService {
    private String brokerList;
    private AdminClient adminClient;
    private KafkaConsumer<String, String> kafkaConsumer;

    public KafkaConsumerGroupService(String brokerList) {
        this.brokerList = brokerList;
    }
    //初始化
    public void init(){
        Properties props = new Properties();
        props.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        adminClient = AdminClient.create(props);
        kafkaConsumer = ConsumerGroupUtils.createNewConsumer(brokerList,
                "kafkaAdminClientDemoGroupId");
    }
    //释放资源
    public void close(){
        if (adminClient != null) {
            adminClient.close();
        }
        if (kafkaConsumer != null) {
            kafkaConsumer.close();
        }
    }
    //收集消费信息的方法
    public List<PartitionAssignmentState> collectGroupAssignment(
            String group) throws ExecutionException, InterruptedException {
        //通过DescribeGroupsRequest请求获取当前消费组的元数据信息
        DescribeConsumerGroupsResult groupResult = adminClient
                .describeConsumerGroups(Collections.singleton(group));
        ConsumerGroupDescription description =
                groupResult.all().get().get(group);

        List<TopicPartition> assignedTps = new ArrayList<>();
        List<PartitionAssignmentState> rowsWithConsumer = new ArrayList<>();
        Collection<MemberDescription> members = description.members();
        if (members != null) {
            //通过OffsetFetchRequest请求获取消费位移ConsumerOffset
            ListConsumerGroupOffsetsResult offsetResult = adminClient
                    .listConsumerGroupOffsets(group);
            Map<TopicPartition, OffsetAndMetadata> offsets = offsetResult
                    .partitionsToOffsetAndMetadata().get();
            if (offsets != null && !offsets.isEmpty()) {
                String state = description.state().toString();
                if (state.equals("Stable")) {
                    rowsWithConsumer = getRowsWithConsumer(description, offsets,
                            members, assignedTps, group);
                }
            }
            List<PartitionAssignmentState> rowsWithoutConsumer =
                    getRowsWithoutConsumer(description, offsets,
                            assignedTps, group);
            rowsWithConsumer.addAll(rowsWithoutConsumer);
        }
        return rowsWithConsumer;
    }
    //有消费者成员信息的处理
    private List<PartitionAssignmentState> getRowsWithConsumer(
            ConsumerGroupDescription description,
            Map<TopicPartition, OffsetAndMetadata> offsets,
            Collection<MemberDescription> members,
            List<TopicPartition> assignedTps, String group) {
        List<PartitionAssignmentState> rowsWithConsumer = new ArrayList<>();
        for (MemberDescription member : members) {
            MemberAssignment assignment = member.assignment();
            if (assignment == null) {
                continue;
            }
            Set<TopicPartition> tpSet = assignment.topicPartitions();
            if (tpSet.isEmpty()) {
                rowsWithConsumer.add(PartitionAssignmentState.builder()
                        .group(group).coordinator(description.coordinator())
                        .consumerId(member.consumerId()).host(member.host())
                        .clientId(member.clientId()).build());

            } else {
                Map<TopicPartition, Long> logSizes =
                        kafkaConsumer.endOffsets(tpSet);
                assignedTps.addAll(tpSet);
                List<PartitionAssignmentState> tempList = tpSet.stream()
                        .sorted(comparing(TopicPartition::partition))
                        .map(tp -> getPasWithConsumer(logSizes, offsets, tp,
                                group, member, description)).collect(toList());
                rowsWithConsumer.addAll(tempList);
            }
        }
        return rowsWithConsumer;
    }

    private PartitionAssignmentState getPasWithConsumer(
            Map<TopicPartition, Long> logSizes,
            Map<TopicPartition, OffsetAndMetadata> offsets,
            TopicPartition tp, String group,
            MemberDescription member,
            ConsumerGroupDescription description) {
        long logSize = logSizes.get(tp);
        if (offsets.containsKey(tp)) {
            long offset = offsets.get(tp).offset();
            long lag = getLag(offset, logSize);
            return PartitionAssignmentState.builder().group(group)
                    .coordinator(description.coordinator()).lag(lag)
                    .topic(tp.topic()).partition(tp.partition())
                    .offset(offset).consumerId(member.consumerId())
                    .host(member.host()).clientId(member.clientId())
                    .logSize(logSize).build();
        }else {
            return PartitionAssignmentState.builder()
                    .group(group).coordinator(description.coordinator())
                    .topic(tp.topic()).partition(tp.partition())
                    .consumerId(member.consumerId()).host(member.host())
                    .clientId(member.clientId()).logSize(logSize).build();
        }
    }
    //计算Lag
    private static long getLag(long offset, long logSize) {
        long lag = logSize - offset;
        return lag < 0 ? 0 : lag;
    }
    //没有消费者成员信息的处理
    private List<PartitionAssignmentState> getRowsWithoutConsumer(
            ConsumerGroupDescription description,
            Map<TopicPartition, OffsetAndMetadata> offsets,
            List<TopicPartition> assignedTps, String group) {
        Set<TopicPartition> tpSet = offsets.keySet();

        return tpSet.stream()
                .filter(tp -> !assignedTps.contains(tp))
                .map(tp -> {
                    long logSize = 0;
                    Long endOffset = kafkaConsumer.
                            endOffsets(Collections.singleton(tp)).get(tp);
                    if (endOffset != null) {
                        logSize = endOffset;
                    }
                    long offset = offsets.get(tp).offset();
                    return PartitionAssignmentState.builder().group(group)
                            .coordinator(description.coordinator())
                            .topic(tp.topic()).partition(tp.partition())
                            .logSize(logSize).lag(getLag(offset, logSize))
                            .offset(offset).build();
                }).sorted(comparing(PartitionAssignmentState::getPartition))
                .collect(toList());
    }
}

class ConsumerGroupUtils{
    //创建KafkaConsumer实例，因为要通过KafkaConsumer.endOffsets()方法获取HW(LSO)
    static KafkaConsumer<String, String> createNewConsumer(
            String brokerUrl, String groupId) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        return new KafkaConsumer<>(props);
    }
    //打印最终的输出结果，如果要展示到页面上，则可以将List<PartitionAssignmentState> list
    //转换成JSON之类的输出到前端，然后通过页面展示
    static void printPasList(List<PartitionAssignmentState> list) {
        System.out.println(String.format("%-40s %-10s %-15s %-15s %-10s" +
                        " %-50s%-30s %s", "TOPIC", "PARTITION",
                "CURRENT-OFFSET", "LOG-END-OFFSET", "LAG",
                "CONSUMER-ID", "HOST", "CLIENT-ID"));

        list.forEach(item ->
                System.out.println(String.format("%-40s %-10s %-15s " +
                                "%-15s %-10s %-50s%-30s %s",
                        item.getTopic(), item.getPartition(), item.getOffset(),
                        item.getLogSize(), item.getLag(),
                        Optional.ofNullable(item.getConsumerId()).orElse("-"),
                        Optional.ofNullable(item.getHost()).orElse("-"),
                        Optional.ofNullable(item.getClientId()).orElse("-"))));
    }
}
//最终展示结果所需的JavaBean
@Data
@Builder
class PartitionAssignmentState {
    private String group;
    private Node coordinator;
    private String topic;
    private int partition;
    private long offset;
    private long lag;
    private String consumerId;
    private String host;
    private String clientId;
    private long logSize;
}
```

使用上面这段示例程序时需要导入与使用 Java 客户端时相同的 Maven 依赖（kafka-clients）。上面示例程序的主函数如下：

```java
public static void main(String[] args) throws ExecutionException,
        InterruptedException {
    KafkaConsumerGroupService service =
            new KafkaConsumerGroupService("localhost:9092");
    service.init();
    List<PartitionAssignmentState> list =
            service.collectGroupAssignment("groupIdMonitor");
    ConsumerGroupUtils.printPasList(list);
    service.close();
}
```

读者可以运行这个程序并对比与 kafka-consumer-groups.sh --describe 有何不同。

kafka-consumer-groups.sh 脚本的功能是通过 kafka.admin.ConsumerGroupCommand 类实现的，而上面的示例就是用 Java 语言和 KafkaAdminClient 作为辅助来重写由 Scala 语言编写的 ConsumerGroupCommand 类中的 collectGroupOffsets() 方法。代码清单32-1的代码量偏多，建议读者按照它和 collectGroupOffsets() 方法中的源码重新写一遍，相信会让你对 Kafka 的认知更加深刻。

我们可不可以直接调用collectGroupOffsets()方法而不需要这么复杂的重写过程呢？很遗憾的是不可以，这是由于collectGroupOffsets()方法中调用的PartitionAssignmentState类的权限问题（private[admin]）而导致的。

不过事情也不是绝对的，我们可以借助 jackson-module-scala 工具包来通过序列化的手段绕过 PartitionAssignmentState 类的权限问题，对应的 Maven 依赖如下：

```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.9.4</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.module</groupId>
    <artifactId>jackson-module-scala_2.11</artifactId>
    <version>2.9.5</version>
</dependency>
```

注意如果本地安装的 Scala 版本与所配置的 jackson-module-scala 版本不一致，则会报出一些异常。由于我们还会调用 Kafka 服务端（ConsumerGroupCommand 类就是服务端的代码，而不是客户端的）的代码，所以还需要导入对应的 Maven 依赖：

```
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka_2.11</artifactId>
    <version>2.0.0</version>
</dependency>
```

对应的示例如代码清单32-2所示。

```
//代码清单32-2 直接调用ConsumerGroupCommand实现
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.scala.DefaultScalaModule;
import kafka.admin.ConsumerGroupCommand;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class KafkaConsumerGroupAnother {
    public static void main(String[] args) throws IOException {
        String[] agrs = {"--describe", "--bootstrap-server", 
                "localhost:9092", "--group", "groupIdMonitor"};
        ConsumerGroupCommand.ConsumerGroupCommandOptions options =
                new ConsumerGroupCommand.ConsumerGroupCommandOptions(agrs);
        ConsumerGroupCommand.ConsumerGroupService kafkaConsumerGroupService =
                new ConsumerGroupCommand.ConsumerGroupService(options);

        ObjectMapper mapper = new ObjectMapper();
        //1. 使用jackson-module-scala_2.11
        mapper.registerModule(new DefaultScalaModule());
        //2. 反序列化时忽略对象不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, 
false);
        //3. 将Scala对象序列化成JSON字符串
        //这里原本会有权限问题，通过序列化绕过
        String source = mapper.writeValueAsString(kafkaConsumerGroupService.
                collectGroupOffsets()._2.get());
        //4. 将JSON字符串反序列化成Java对象
        List<PartitionAssignmentStateAnother> target = mapper.readValue(source,
                getCollectionType(mapper,List.class,
                        PartitionAssignmentStateAnother.class));
        //5. 排序
        target.sort((o1, o2) -> o1.getPartition() - o2.getPartition());
        //6. 打印
        //这个方法参考前面ConsumerGroupUtils的printPasList()方法
        printPasList(target); 
    }

    public static JavaType getCollectionType(ObjectMapper mapper,
                                             Class<?> collectionClass,
                                             Class<?>... elementClasses) {
        return mapper.getTypeFactory()
                .constructParametricType(collectionClass, elementClasses);
    }

@Data
@Builder
class PartitionAssignmentStateAnother {
    private String group;
    private Node coordinator;
    private String topic;
    private int partition;
    private long offset;
    private long lag;
    private String consumerId;
    private String host;
    private String clientId;
    private long logSize;

    @Data
    public static class Node{
        public int id;
        public String idString;
        public String host;
        public int port;
        public String rack;
    }
}
```

本段代码可以在 Github 上下载，请点击[这里](https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Fhiddenzzh%2Fkafka_book_demo%2Fblob%2Fmaster%2Fsrc%2Fmain%2Fjava%2Fchapter10%2FKafkaConsumerGroupAnother.java)。

在原本的代码清单32-1中，PartitionAssignmentState 中的 coordinator 类型是 Node，这个类型需要自定义，否则会报错，所以在代码清单32-2中又重写了 PartitionAssignmentState 类为 PartitionAssignmentStateAnother，读者需要注意其中的区别（建议读者跟着写一遍，这样能够深刻地体会到其中的细节问题）。如果页面中需要展示这些信息，那么我们甚至可以直接返回代码清单32-2中第3步骤的 source 字符串给页面，方便快捷。

# <a name="125">监控指标说明</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka 自身提供的 JMX 监控指标已经超过了500个，本书不可能一一将其罗列，只能挑选部分重要及常用的指标来进行说明。

上一节的第一张图中除了展示了消息流入速度（MessagesInPerSec），还展示了网络流入/流出速度，这2个指标对应的 MBean 名称如下表所示。

| 指 标 名 称              | MBean 名称                                               |
| ------------------------ | -------------------------------------------------------- |
| 网络流入速率（bytesIn）  | kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec  |
| 网络流出速率（bytesOut） | kafka.server:type=BrokerTopicMetrics,name=BytesOutPerSec |



这两个指标都是 broker 端的指标，分别对应前面章节中提及的 byteIn 和 byteOut。它们的属性列表同 MessagesInPerSec 的类似，与 MessagesInPerSec 指标不同的是，这2个指标的单位为B/s，具体的使用方式可以参考第31节。

Kafka 并没有提供类似 MessagesOutPerSec 的指标，这是为什么呢？因为消息是以批次的形式发送给消费者的，在这个过程中并不会再展开（展开会严重影响性能，如果仅仅为了统计一个普通的指标而展开，则会显得非常得不偿失）这些批次的内容来统计消息的个数，所以对 Kafka 而言，它也不知道发送了多少条消息，也就不会有类似 MessagesOutPerSec 这样的指标了。

不过在 Kafka 中有一个 TotalFetchRequestsPerSec 指标用于统计每秒拉取请求的次数，它可以从侧面反映出消息被拉取的多少。这个指标还有一个对应的 TotalProduceRequestsPerSec，用于统计每秒写入请求的次数。这2个指标对应的 MBean 名称如下表所示。

| 指 标 名 称                | MBean 名称                                                   |
| -------------------------- | ------------------------------------------------------------ |
| TotalFetchRequestsPerSec   | kafka.server:type=BrokerTopicMetrics,name=TotalFetchRequestsPerSec |
| TotalProduceRequestsPerSec | kafka.server:type=BrokerTopicMetrics,name=TotalProduceRequestsPerSec |



这些指标还有对应的与主题相关的指标，如下图所示。

![10-9](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/16968a99d05ca95c~tplv-t2oaga2asx-watermark.awebp)

主题 topic-monitor 的 MBean 名称为：

```
kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec,topic=topic-monitor
```

由此可以归纳出主题端的 BytesInPerSec 指标的 MBean 名称为：

```
kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec,topic=<topic>
```

这个规则对于其他主题端的指标同样适用。注意并不是每个 broker 端的指标都有其对应的主题端的指标，比如 ActiveControllerCount，它表示当前 broker 是否是集群的控制器。这个指标只有2个可选值，要么为0，要么为1。如果为1，则表示当前 broker 就是集群的控制器。任何时刻一个集群中有且仅有一个控制器，如果集群中所有 broker 的 ActiveControllerCount 指标之和不为1，则说明发生了异常情况，需要及时地告警以通知相关人员排查故障。ActiveControllerCount 对应于31节第二张图中的 Controller 标记，它的 MBean 名称为：

```
kafka.controller:type=KafkaController,name=ActiveControllerCount
```

与 UnderReplicatedPartitions 指标同级的还有 LeaderCount、PartitionCount、IsrExpandPerSec 和 IsrShrinksPerSec 这4个重要的指标，它们分别表征了 broker 中 leader 副本的总数、分区的总数、ISR 集合扩张速度和 ISR 集合收缩速度。这4个指标对应的 MBean 名称如下表所示。

| 指 标 名 称      | MBean 名称                                             |
| ---------------- | ------------------------------------------------------ |
| LeaderCount      | kafka.server:type=ReplicaManager,name=LeaderCount      |
| PartitionCount   | kafka.server:type=ReplicaManager,name=PartitionCount   |
| IsrShrinksPerSec | kafka.server:type=ReplicaManager,name=IsrShrinksPerSec |
| IsrExpandsPerSec | kafka.server:type=ReplicaManager,name=IsrExpandsPerSec |



对 LeaderCount 和 PartitionCount 而言，在前面的篇幅中已经有所提及，尤其是 LeaderCount，它牵涉集群的负载是否均衡。而 IsrExpandPerSec 和 IsrShrinksPerSec 这2个代表 ISR 集合变化速度的指标可以用来监测 Kafka 集群的性能问题。

对 Kafka 的客户端而言，它同样提供了可供 JMX 获取的监控指标，我们在运行 Kafka 客户端的时候同样需要显式地打开 JMX 功能，比如添加以下运行参数：

```
-Dcom.sun.management.jmxremote.port=8888
-Dcom.sun.management.jmxremote.ssl=false
-Dcom.sun.management.jmxremote.authenticate=false
```

相比于 broker 端，客户端的指标就少了很多，不过每一个客户端指标都有一个对应的 clientId，如下图所示，其中 clientIdMonitor 就是客户端的 clientId。

![10-10](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/16968ab924bc1b2f~tplv-t2oaga2asx-watermark.awebp)

Kafka 还提供了许多其他重要的指标，但笔者并不打算再多赘述，读者可以通过 JConsole 工具和 [Kafka 官方文档](https://link.juejin.cn/?target=http%3A%2F%2Fkafka.apache.org%2Fdocumentation%2F%23monitoring)来一一探索指标的奥秘。

# <a name="126">监控模块</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka 的监控架构主要分为数据采集、数据存储和数据展示这3个部分。数据采集主要指从各个数据源采集监控数据并做一些必要的运算，然后发送给数据存储模块进行存储。数据源可以是 Kafka 配套的 ZooKeeper、Kafka 自身提供的内部运行指标（通过 JMX 获取）、Kafka 内部的一些数据（比如__consumer_offset 中存储的信息，通过 Kafka 自定义协议获取）、Falcon/Zabbix 等第三方工具（或者其他类似的工具，主要用来监控集群的硬件指标）。

数据存储指将采集的原始数据经过一定的预处理后进行相应的存储，方便数据清洗（这个步骤可以省略）和数据展示。数据存储可以采用 OpenTSDB 之类的基于时间序列的数据库，方便做一些聚合计算，也可以附加采用 Redis、MySQL 等存储特定数据。

顾名思义，数据展示是将经过预处理的、存储的数据展示到监控页面上，以便提供丰富的 UI 给用户使用。当然数据展示模块也可以绕过数据存储模块直接通向数据采集模块，或者从数据源直接拉取数据。

整个监控系统的模型架构如下图所示。

![10-11](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/16968ae6826cbb3b~tplv-t2oaga2asx-watermark.awebp)

监控模块是 Kafka 生态链中的重要一环，它是查看 Kafka 运行状态的主要依据，是排查故障的重要参考，同时是触发告警的源头，以便及时预防或修复故障。下图展示了 Kafka 的某种应用生态，监控系统及监控数据的采集都在其中。

![10-12](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/16968aec8268b318~tplv-t2oaga2asx-watermark.awebp)

首先，用户在资源申请审批系统中申请所需要使用的 Kafka 资源。管理员在审批完用户的申请之后，将相应的配置信息存储在配置中心，然后由配置中心负责创建相应的 Kafka 资源（比如根据预先申请的分区数、副本因子数创建对应的主题）。在资源创建成功之后会触发数据采集模块（Collector）对监控指标进行收集，最终存入预先设定的存储模块，比如 HBase。

用户通过封装后的 SDK 进行生产消费。SDK 中除了包含原生的 Kafka 客户端的功能，还包含了与应用生态中各个其他模块的互动功能，比如监听配置中心配置的变更以便及时进行相应的处理。如果用户采用的编程语言与 SDK 的实现语言互不相通，则可以使用 Kafka REST Proxy 来作为跨语言应用的补救措施。与此同时，SDK 中也有其相应的指标，比如业务相关的消息发送和消费的速度、重试的次数等，牵涉 SDK 的地方需要自定义原本 Kafka 所没有的监控指标。

无论通过 Collector 采集的指标数据，还是 SDK 上送的指标数据，在存入存储模块之前都可以做一定的预处理，比如在 Collector 中可以根据收集到的数据对各个 broker 节点的负载进行归一化的处理，然后将处理后的计算值保存到存储模块中，进而方便页面的展示。

在上图展示的应用生态中还缺失了运维这一环，前面的章节中多多少少都提到了一些运维相关的内容。有兴趣的读者还可以关注一下 LinkedIn 开源的 [Kafka Cruise Control](https://link.juejin.cn/?target=https%3A%2F%2Fgithub.com%2Flinkedin%2Fcruise-control)——旨在使 Kafka 实现大规模自动化运维。

## <a name="127">总结</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这两节主要讲述如何自定义实现一个 Kafka 监控系统，其中包括页面整体的布局把控、监控数据的来源、监控指标的说明，以及监控模块在整个 Kafka 应用生态中所处的地位。这里并不讲述如何使用某款 Kafka 监控产品，而是给读者提供一个实现监控产品的思路。如果读者不想耗费精力实现一款监控产品而是想直接使用开源现成的，那么本章的内容也可以帮助读者更好地理解这些监控产品的实现原理。



# <a name="128">初识Spark</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Spark 是一个用来实现快速且通用的集群计算的平台。Spark 是 UC Berkeley AMP Lab（加州大学伯克利分校的AMP实验室）所开源的类 MapReduce 的通用并行框架，现在已经是 Apache 的一个顶级项目。Spark 使用 Scala 语言开发，支持 Scala、Java、Python、R 语言相关的 API，运行于 JVM 之上。Spark 基于内存计算，提高了在大数据环境下数据处理的实时性，同时保证了高容错性和高可伸缩性。Spark 适用于各种各样原先需要多种不同的分布式平台实现的场景，包括批处理、迭代计算、交互式查询、流处理等。



![12-1](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/11/1696d7247b7ec1bc~tplv-t2oaga2asx-watermark.awebp)



如上图所示，Spark 生态圈即 BDAS（伯克利数据分析栈）包含的组件有 Spark Core、Spark Streaming、Spark SQL、MLlib 和 GraphX，它们都是由AMP实验室提供的，能够无缝地继承，并提供一站式解决平台。

Spark Core 实现了 Spark 的基本功能，包含任务调度、内存管理、错误恢复，以及与存储系统交互等模块。Spark Streaming 属于 Spark Core API 的扩展，支持实时数据流的可扩展、高吞吐、容错的流处理。Spark SQL 是 Spark 的一个结构化数据处理模块，提供了 DataFrame/Dataset 的编程抽象，可以看作一个分布式查询引擎。从 Spark 2.0 开始又引入了 Structured Streaming，它是建立在 Spark SQL 之上的可扩展和高容错的流处理引擎。MLlib 是 Spark 提供的具有机器学习功能的程序库，它提供了很多种机器学习算法，包括分类、回归、聚类、协同过滤等，还提供了模型评估、数据导入等额外的功能。GraphX 是用来操作图的程序库，可以进行并行的图计算。

Spark 具有很强的适应性，能够使用 HDFS、Cassandra、HBase 等为持久层读写原生数据，资源管理采用 Mesos、YARN、Kubernetes 等集群资源管理模式，或者 Spark 自带的独立运行模式及本地运行模式。

Spark 具有一个庞大的生态圈，用于生产时还需要考虑参数调配、容错处理、监控、性能优化、存储、调度、部署等多个环节，涉及方方面面，仅以一个章节的内容是无法穷尽的。本章的主旨也并非简单地讲解 Spark，而是要讲解 Kafka 与 Spark 之间的集成细节。本章会以尽量少的篇幅让读者对 Spark 有一个初步的了解，并且会以合适的篇幅来讲解 Kafka 与 Spark Streaming 的集成，以及 Kafka 与 Structured Streaming 的集成。

## <a name="129">Spark的安装及简单应用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

下载 Spark 安装包是安装的第一步，[下载地址](https://link.juejin.cn/?target=http%3A%2F%2Fspark.apache.org%2Fdownloads.html)。截至撰写之时，Spark 的最新版本为2.3.1，我们可以从官网中选择 spark-2.3.1-bin-hadoop2.7.tgz 进行下载。

下载完成后，先将安装包复制至/opt 目录下，然后执行相应的解压缩操作，示例如下：

```
[root@node1 opt]# tar zxvf spark-2.3.1-bin-hadoop2.7.tgz 
[root@node1 opt]# mv spark-2.3.1-bin-hadoop2.7 spark
[root@node1 opt]# cd spark
[root@node1 spark]#
```

解压缩之后可以直接运行 Spark，当然前提是要安装好 JDK，并设置好环境变量 `JAVA_HOME`。进入`$SPARK_HOME/sbin` 目录下执行 `start-all.sh` 脚本启动 Spark。脚本执行后，可以通过 `jps -l` 命令查看当前运行的进程信息，示例如下：

```
[root@node1 spark]# jps -l
23353 org.apache.spark.deploy.master.Master
23452 org.apache.spark.deploy.worker.Worker
```

可以看到 Spark 启动后多了 Master 和 Worker 进程，分别代表主节点和工作节点。我们还可以通过 Spark 提供的 Web 界面来查看 Spark 的运行情况，比如可以通过 `http://localhost:8080` 查看 Master 的运行情况。

Spark 中带有交互式的 shell，可以用作即时数据分析。现在我们通过 spark-shell 来运行一个简单但又非常经典的单词统计的程序，以便可以简单地了解 Spark 的使用。首先进入`$SPARK\_HOME/bin` 目录下（SPARK_HOME 表示 Spark 安装的根目录，即本例中的`/opt/spark`）执行 `spark-shell` 命令来启动 Spark，可以通过 `--master` 参数来指定需要连接的集群。spark-shell 启动时，会看到一些启动日志，示例如下：

```
[root@node1 spark]# bin/spark-shell --master spark://localhost:7077 
2018-08-07 11:02:04 WARN  Utils:66 - Your hostname, hidden.zzh.com resolves to 
    a loopback address: 127.0.0.1; using 10.xxx.xxx.xxx instead (on interface 
    eth0)
2018-08-07 11:02:04 WARN  Utils:66 - Set SPARK_LOCAL_IP if you need to bind to 
    another address
2018-08-07 11:02:04 WARN  NativeCodeLoader:62 - Unable to load native-hadoop 
    library for your platform... using builtin-java classes where applicable
Setting default log level to "WARN".
To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use 
    setLogLevel(newLevel).
Spark context Web UI available at http:// 10.xxx.xxx.xxx:4040
Spark context available as 'sc' (master = spark://localhost:7077, app id = 
    app-20180807110212-0000).
Spark session available as 'spark'.
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 2.3.1
      /_/
         
Using Scala version 2.11.8 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_102)
Type in expressions to have them evaluated.
Type :help for more information.

scala>
```

如此便可以在“scala>”处输入我们想要输入的程序。

在将要演示的示例程序中，我们就近取材，以 `bin/spark-shell` 文件中的内容来进行单词统计。程序首先读取这个文件的内容，然后进行分词。这里的分词方法是使用空格进行分割的，最后统计单词出现的次数。下面将这些步骤进行拆分，一步步来讲解其中的细节。如无特殊说明，本章编写的示例均使用 Scala 语言。

首先通过 SparkContext（Spark 在启动时已经自动创建了一个 SparkContext 对象，是一个叫作 sc 的变量）的 textFile() 方法读取 `bin/spark-shell` 文件，参考如下：

```
scala> val rdd = sc.textFile("/opt/spark/bin/spark-shell")
rdd: org.apache.spark.rdd.RDD[String] = /opt/spark/bin/spark-shell 
    MapPartitionsRDD[3] at textFile at <console>:24
```

然后使用 `split()` 方法按照空格进行分词，之后又通过 `flatMap()` 方法对处理后的单词进行展平，展平之后使用 `map(x=>(x,1))`对每个单词计数1，参考如下：

```
scala> val wordmap = rdd.flatMap(_.split(" ")).map(x=>(x,1))
wordmap: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[5] at map at
    <console>:25
```

最后使用 `reduceByKey(_+_)` 根据 key（也就是单词）进行计数，这个过程是一个混洗（Shuffle）的过程，参考如下：

```
scala> val wordreduce = wordmap.reduceByKey(_+_)
wordreduce: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[6] at 
    reduceByKey at <console>:25
```

到这里我们便完成了单词统计，进一步地使用 take(10) 方法获取前面 10 个单词统计的结果，参考如下：

```
scala> wordreduce.take(10)
res3: Array[(String, Int)] = Array((scala,2), (!=,1), (Unless,1), (this,4), 
     (starting,1), (under,4), (its,1), (reenable,2), (-Djline.terminal=unix",1),
     (CYGWIN*),1))
```

发现结果并没有按照某种顺序进行排序，如果要看到诸如单词出现次数前 10 的内容，那么还需要对统计后的结果进行排序。

```
scala> val wordsort = 
    wordreduce.map(x=>(x._2,x._1)).sortByKey(false).map(x=>(x._2,x._1))
wordsort: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[11] at map 
    at <console>:25

scala> wordsort.take(10)
res2: Array[(String, Int)] = Array(("",91), (#,37), (the,19), (in,7), (to,7), 
     (for,6), (if,5), (then,5), (this,4), (under,4))
```

上面的代码中首先使用 `map(x=>(x._2,x._1)` 对单词统计结果的键和值进行互换，然后通过 sortByKey(false) 方法对值进行降序排序，然后再次通过 `map(x=>(x._2,x._1)` 将键和值进行互换，最终的结果按照降序排序。

## <a name="130">Spark编程模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在 Spark 中，我们通过对分布式数据集的操作来表达计算意图，这些计算会自动在集群上并行执行。这样的数据集被称为弹性分布式数据集（Resilient Distributed Dataset），简称 RDD。RDD 是 Spark 对分布式数据和计算的基本抽象。在 Spark 中，对数据的所有操作不外乎创建 RDD、转换已有 RDD，以及调用 RDD 操作进行求值。在上一节的单词统计示例中，rdd 和 wordmap 都是 MapPartitionsRDD 类型的 RDD，而 wordreduce 是 ShuffledRDD 类型的 RDD。

RDD 支持2种类型的操作：转换操作（Transformation Operation）和行动操作（Action Operation）。有些资料还会细分为创建操作、转换操作、控制操作和行动操作4种类型。转换操作会由一个 RDD 生成一个新的 RDD。行动操作会对 RDD 计算出一个结果，并把结果返回驱动器程序，或者把结果存储到外部存储系统中。转换操作和行动操作的区别在于 Spark 计算 RDD 的方式不同。虽然可以在任何时候定义新的 RDD，但 Spark 只会惰性计算这些 RDD。它们只有第一次在一个行动操作中用到时才会真正计算。下表中给出了转换操作和行动操作之间对比的更多细节。

| 类 别    | 函 数                                                        | 区 别                                                        |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 转换操作 | map、filter、groupBy、join、union、reduce、sort、partitionBy 等 | 返回值还是 RDD，不会马上提交给 Spark 集群运行                |
| 行动操作 | count、collect、take、save、show 等                          | 返回值不是 RDD，会形成 DAG 图，提交给 Spark 集群运行并立即返回结果 |



通过转换操作，从已有的 RDD 中派生出新的 RDD，Spark 会使用谱系图（Lineage Graph，很多资料也会翻译为“血统”）来记录这些不同 RDD 之间的依赖关系。Spark 需要用这些信息来按需计算每个 RDD，也可以依赖谱系图在持久化的 RDD 丢失部分数据时恢复丢失的数据。行动操作会把最终求得的结果返回驱动器程序，或者写入外部存储系统。由于行动操作需要生产实际的输出，所以它们会强制执行那些求值必须用到的 RDD 的转换操作。

Spark 中 RDD 计算是以分区（Partition）为单位的，将 RDD 划分为很多个分区分布到集群的节点中，分区的多少涉及对这个 RDD 进行并行计算的粒度。如图12-2所示，实线方框 A、B、C、D、E、F、G 都表示的是 RDD，阴影背景的矩形则表示分区。A、B、C、D、E、F、G 之间的依赖关系构成整个应用的谱系图。



![12-2](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696d7ee032c7bc5~tplv-t2oaga2asx-watermark.awebp)



依赖关系还可以分为窄依赖和宽依赖。窄依赖（Narrow Dependencies）是指每个父 RDD 的分区都至多被一个子 RDD 的分区使用，而宽依赖（Wide Dependencies）是指多个子 RDD 的分区依赖一个父 RDD 的分区。上图中，C和D之间是窄依赖，而A和B之间是宽依赖。RDD 中行动操作的执行会以宽依赖为分界来构建各个调度阶段，各个调度阶段内部的窄依赖前后链接构成流水线。图中的3个虚线方框分别代表了3个不同的调度阶段。

对于执行失败的任务，只要它对应的调度阶段的父类信息仍然可用，那么该任务就会分散到其他节点重新执行。如果某些调度阶段不可用，则重新提交相应的任务，并以并行方式计算丢失的地方。在整个作业中，如果某个任务执行缓慢，则系统会在其他节点上执行该任务的副本，并取最先得到的结果作为最终的结果。

下面就以与上一节中相同的单词统计程序为例来分析 Spark 的编程模型，与上一节中所不同的是，这里是一个完整的 Scala 程序，程序对应的 Maven 依赖如下：

```
<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-core_2.11</artifactId>
    <version>2.3.1</version>
</dependency>
```

单词统计程序如代码清单33-1所示。

```
//代码清单33-1 单词统计程序
package scala.spark.demo
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit ={
    val conf = new SparkConf().setAppName("WordCount").setMaster("local")①
    val sc = new SparkContext(conf)②
    val rdd = sc.textFile("/opt/spark-2.3.1-bin-hadoop2.7/bin/spark-shell")③
    val wordcount = rdd.flatMap(_.split(" ")).map(x=>(x,1)).reduceByKey(_+_)④
    val wordsort = wordcount.map(x=>(x._2,x._1))
      .sortByKey(false).map(x=>(x._2,x._1))⑤
    wordsort.saveAsTextFile("/tmp/spark")⑥
    sc.stop()⑦
  }
}
```

main() 方法主体的第①和第②行中首先创建一个 SparkConf 对象来配置应用程序，然后基于这个 SparkConf 创建了一个 SparkContext 对象。一旦有了 SparkContext，就可以用它来创建 RDD，第③行代码中调用 `sc.textFile()` 来创建一个代表文件中各行文本的 RDD。第④行中 `rdd.flatMap(_.split(" ")).map(x=>(x,1))`这一段内容的依赖关系是窄依赖，而 `reduceByKey(_+_)` 操作对单词进行计数时属于宽依赖。第⑥行中将排序后的结果存储起来。最后第⑦行中使用 stop() 方法来关闭应用。

在$SPARK_HOME/bin 目录中还有一个 spark-submit 脚本，用于将应用快速部署到 Spark 集群。比如这里的 WordCount 程序，当我们希望通过 spark-submit 进行部署时，只需要将应用打包成 jar 包（即下面示例中的 wordcount.jar）并上传到 Spark 集群，然后通过 spark-submit 进行部署即可，示例如下：

```
[root@node1  spark]# bin/spark-submit --class scala.spark.demo.WordCount wordcount.jar --executor-memory 1G --master spark://localhost:7077 
2018-08-06 15:39:54 WARN  NativeCodeLoader:62 - Unable to load native-hadoop 
    library for your platform... using builtin-java classes where applicable
2018-08-06 15:39:55 INFO  SparkContext:54 - Running Spark version 2.3.1
2018-08-06 15:39:55 INFO  SparkContext:54 - Submitted application: WordCount
2018-08-06 15:39:55 INFO  SecurityManager:54 - Changing view acls to: root
2018-08-06 15:39:55 INFO  SecurityManager:54 - Changing modify acls to: root
(....省略若干)
2018-08-07 12:25:47 INFO  AbstractConnector:318 - Stopped 
    Spark@6299e2c1{HTTP/1.1,[http/1.1]}{0.0.0.0:4040}
2018-08-07 12:25:47 INFO  SparkUI:54 - Stopped Spark web UI at 
    http://10.199.172.111:4040
2018-08-07 12:25:47 INFO  MapOutputTrackerMasterEndpoint:54 – 
    MapOutputTrackerMasterEndpoint stopped!
2018-08-07 12:25:47 INFO  MemoryStore:54 - MemoryStore cleared
2018-08-07 12:25:47 INFO  BlockManager:54 - BlockManager stopped
2018-08-07 12:25:47 INFO  BlockManagerMaster:54 - BlockManagerMaster stopped
2018-08-07 12:25:47 INFO  
    OutputCommitCoordinator$OutputCommitCoordinatorEndpoint:54 – 
    OutputCommitCoordinator stopped!
2018-08-06 15:46:57 INFO  SparkContext:54 - Successfully stopped SparkContext
2018-08-06 15:46:57 INFO  ShutdownHookManager:54 - Shutdown hook called
2018-08-06 15:46:57 INFO  ShutdownHookManager:54 - Deleting directory 
    /tmp/spark-fa955139-270c-4899-82b7-4959983a1cb0
2018-08-06 15:46:57 INFO  ShutdownHookManager:54 - Deleting directory 
    /tmp/spark-3f359966-2167-4bb9-863a-2d8a8d5e8fbe
```

示例中的 --class 用来指定应用程序的主类，这里为 scala.spark.demo.WordCount；--executor-memory 用来指定执行器节点的内容，这里设置为1G。最后得到的输出结果如下所示。

```
[root@node1 spark]# ls /tmp/spark
part-00000  _SUCCESS
[root@node1 spark]# cat /tmp/spark/part-00000 
(,91)
(#,37)
(the,19)
(in,7)
(to,7)
(for,6)
(if,5)
(then,5)
(under,4)
(stty,4)
(not,4)
```

## <a name="131">Spark的运行结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

在分布式环境下，Spark 集群采用的是主从架构。如下图所示，在一个 Spark 集群中，有一个节点负责中央协调，调度各个分布式工作节点，这个中央协调节点被称为驱动器（Driver）节点，与之对应的工作节点被称为执行器（Executor）节点。驱动器节点可以和大量的执行器节点进行通信，它们都作为独立的进程运行。驱动器节点和所有的执行器节点一起被称为 Spark 应用（Application)。



![12-3](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696d84210b5ab77~tplv-t2oaga2asx-watermark.awebp)



Spark 应用通过一个叫作集群管理器（Cluster Manager）的外部服务在集群中的机器上启动。Spark 自带的集群管理器被称为独立集群管理器。Spark 也能运行在 YARN、Mesos、Kubernetes 这类开源集群管理器上。

Spark 驱动器节点是执行程序中的 `main()` 方法的进程。它执行用户编写的用来创建 SparkContext、RDD，以及进行 RDD 的转换操作和行动操作的代码。其实，当启动 spark-shell 时，就启动了一个 Spark 驱动程序。驱动程序一旦停止， Spark 应用也就结束了。

驱动器程序在 Spark 应用中有两个职责：将用户程序转为任务，以及为执行器节点调度任务。

Spark 驱动器程序负责把用户程序转为多个物理执行的单元，这些单元也被称为任务（Task）。任务是 Spark 中最小的工作单元，用户程序通常要启动成百上千的独立任务。从上层来看，所有的 Spark 程序都遵循同样的结构：程序从输入数据创建一系列 RDD，再使用转换操作派生出新的 RDD，最后使用行动操作收集或存储结果 RDD 中的数据。Spark 程序其实是隐式地创建了一个由操作组成的逻辑上的有向无环图（Directed Acyclic Graph，简称DAG）。当驱动器程序运行时，它会把这个逻辑图转为物理执行计划。

有了物理执行计划之后，Spark 驱动器程序必须在各执行器进程间协调任务的调度。执行器进程启动后，会向驱动器进程注册自己。因此，驱动器进程始终对应用中所有的执行器节点有完整的记录。每个执行器节点代表一个能够处理任务和存储 RDD 数据的进程。

Spark 驱动器程序会根据当前的执行器节点集合，尝试把所有任务基于数据所在位置分配给合适的执行器进程。当任务执行时，执行器进程会把缓存数据存储起来，而驱动器进程同样会跟踪这些缓存数据的位置，并且利用这些位置信息来调度以后的任务，以尽量减少数据的网络传输。

Spark 执行器节点是一种工作进程，负责在 Spark 作业中运行任务，任务间相互独立。Spark 应用启动时，执行器节点就被同步启动，并且始终伴随整个 Spark 应用的生命周期而存在。如果执行器节点发生异常或崩溃，那么 Spark 应用也可以继续执行。执行器进程有两大作用：第一，它们负责运行组成 Spark 应用的任务，并将结果返回给驱动器进程；第二，它们通过自身的块管理器（Block Manager）为用户程序中要求缓存的 RDD 提供内存式存储。RDD 是直接缓存在执行器进程内的，因此任务可以在运行时充分利用缓存数据加速运算。

Spark 依赖于集群管理器来启动执行器节点，在某些特殊的情况下，也依赖集群管理器来启动驱动器节点。集群管理器是 Spark 中的可插拔式组件，这样既可选择 Spark 自带的独立集群管理，也可以选择前面提及的 YARN、Mesos 之类的外部集群管理器。

不论使用的是哪一种集群管理器，都可以使用 Spark 提供的统一脚本 spark-submit 将应用提交到该集群管理器上。通过不同的配置选项，spark-submit 可以连接到相应的集群管理器上，并控制应用使用的资源数量。在使用某些特定集群管理器时，spark-submit 也可以将驱动器节点运行在集群内部（比如一个 YARN 的工作节点）。但对于其他的集群管理器，驱动器节点只能被运行在本地机器上。

在集群上运行 Spark 应用的详细过程如下。

1. 用户通过 spark-submit 脚本提交应用。
2. spark-submit 脚本启动驱动器程序，调用用户定义的 `main()` 方法。
3. 驱动器程序与集群管理器通信，申请资源以启动执行器节点。
4. 集群管理器为驱动器程序启动执行器节点。
5. 驱动器执行用户应用中的操作。根据程序中定义的对 RDD 的转换操作和行动操作，驱动器节点把工作以任务的形式发送到执行器执行。
6. 任务在执行器程序中进行计算并保存结果。
7. 如果驱动器程序的 `main()` 方法退出，或者调用了 `SparkContext.stop()`，那么驱动器程序会中止执行器进程，并且通过集群管理器释放资源。



# <a name="132">Spark Streaming简介</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Spark Streaming 是 Spark 提供的对实时数据进行流式计算的组件。它是 Spark 核心 API 的一个扩展，具有吞吐量高、容错能力强的实时流数据处理系统，支持包括 Kafka、Flume、Kinesis 和 TCP 套接字等数据源，获取数据以后可以使用 map()、reduce()、join()、window() 等高级函数进行复杂算法的处理，处理结果可以存储到文件系统、数据库，或者展示到实时数据大盘等。另外，Sparking Streaming 也可以和其他组件，如 MLlib和Graphx 等结合，对实时数据进行更加复杂的处理。Spark Streaming 的数据处理流程如下图所示。



![12-4](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696d8978a5d96bb~tplv-t2oaga2asx-watermark.awebp)



和 Spark 基于 RDD 的概念很相似，Spark Streaming 使用离散化流（Discretized Stream）作为抽象表示，叫作 DStream。DStream 是随着时间推移而收到的数据的序列。在内部，每个时间区间收到的数据都作为 RDD 存在，而 DStream 是由这些 RDD 组成的序列（因此得名“离散化”）。创建出来的 DStream 支持两种操作：一种是转换操作（Transformation），会生成一个新的 DStream；另一种是输出操作（Output Operation），可以把数据写入外部系统。



![12-5](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696d8a440f10fc7~tplv-t2oaga2asx-watermark.awebp)



如上图所示，通俗一点讲，Spark Streaming 会把实时输入的数据流以时间片Δt（如1秒）为单位切分成块，每块数据代表一个 RDD。流数据的 DStream 可以看作一组 RDD 序列，通过调用 Spark 核心的作业处理这些批数据，最终得到处理后的一批批结果数据。

在开始讲解 Spark Streaming 的细节之前，让我们先来看一个简单的例子。Spark Streaming 对应的 Maven 依赖如下：

```
<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-streaming_2.11</artifactId>
    <version>2.3.1</version>
</dependency>
```

下面就以 Spark Streaming 官方提供的单词统计代码为例来分析 Spark Streaming 的相关内容，具体的代码如代码清单34-1所示。

```
//代码清单34-1 Spark Streaming示例程序
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingWordCount {
  def main(args:Array[String]): Unit ={
    val conf = new SparkConf().setMaster("local[2]").setAppName("WordCount")①
    val ssc = new StreamingContext(conf, Seconds(1))②
    val lines = ssc.socketTextStream("localhost", 9999)③
    val words = lines.flatMap(_.split(" "))④
    val pairs = words.map(word => (word, 1))⑤
    val wordCounts = pairs.reduceByKey(_ + _)⑥
    wordCounts.print()⑦
    ssc.start()⑧
    ssc.awaitTermination()⑨
  }
}
```

示例代码首先从创建 StreamingContext 开始，它是流计算功能的主要入口。StreamingContext 会在底层创建出 SparkContext，用来处理数据。StreamingContext 的构造函数还接收用来指定多长时间处理一次新数据的批次间隔（Batch Duration）作为输入，这里把它设置为1秒。接着调用 socketTextStream() 来创建基于本地9999端口上收到的文本数据的 DStream。第④行至第⑥行的内容和前面单词统计代码如出一辙，这里就不过多解释，只不过这里针对的是 DStream 的处理。第⑦行使用输出操作来将结果打印出来。

到这里为止只是设定好了要进行的计算，系统收到数据时计算就会开始。要开始接收数据就必须如第⑧行一样显式调用 StreamingContext的start() 方法。这样，Spark Streaming 就会开始把 Spark 作业不断交给下面的 SparkContext 去调度执行。执行会在另一个线程中进行，所以需要调用 awaitTermination() 方法来等待流计算完成，以防止应用退出。

示例代码中的内容是基于批次间隔的处理，这个也可以看作基于固定窗口（Fixed Window）的处理，每个窗口不会重合，固定窗口的大小就是批次间隔的大小。这里对应的转换操作也就可以看作基于固定窗口的转换操作。

Spark 安装包中自带了这个程序，所以可以直接使用如下的方式来启动这个程序：

```
[root@node1  spark]# bin/run-example streaming.NetworkWordCount localhost 9999
2018-08-06 18:06:47 WARN  NativeCodeLoader:62 - Unable to load native-hadoop 
     library for your platform... using builtin-java classes where applicable
2018-08-06 18:06:48 INFO  SparkContext:54 - Running Spark version 2.3.1
2018-08-06 18:06:48 INFO  SparkContext:54 - Submitted application: 
     NetworkWordCount 
2018-08-06 18:06:48 INFO  SecurityManager:54 - Changing view acls to: root
2018-08-06 18:06:48 INFO  SecurityManager:54 - Changing modify acls to: root
2018-08-06 18:06:48 INFO  SecurityManager:54 - Changing view acls groups to: 
(....省略若干信息)
```

接着在另一个 shell 中使用 netcat 工具来输入一句“hello world”，示例如下：

```
[root@node1 spark]# nc -lk 9999
hello world
```

可以看到在 NetworkWordCount 程序中输出如下信息：

```
-------------------------------------------
Time: 1533549417000 ms
-------------------------------------------
(hello,1)
(world,1)
```

前面已经讲过，Spark Streaming 的编程抽象是离散化流，也就是 DStream，如下图所示。它是一个 RDD 序列，每个 RDD 代表数据流中一个时间片内的数据。



![12-6](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696d8d9de567785~tplv-t2oaga2asx-watermark.awebp)



可以从外部输入源来创建 DStream，也可以对其他 DStream 应用进行转换操作得到新的 DStream。DStream 支持许多 RDD 支持的转换操作。以代码清单34-1为例，第④行代码中的 flatMap() 就是将行数据流（Lines DStream）中的 RDD 转换成单词数据流（Words DStream）中的 RDD，如下图所示。



![12-7](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696d8e79087f842~tplv-t2oaga2asx-watermark.awebp)



这些基础 RDD 的转换由 Spark 引擎计算。DStream 操作隐藏了大部分的细节，并为开发人员提供了更高级别的 API 以方便使用。

DStream 还支持输出操作，比如在示例中使用的 print()。输出操作和 RDD 的行动操作的概念类似。Spark 在行动操作中将数据写入外部系统，而 Spark Streaming 的输出操作在每个时间区间中周期性地执行，每个批次都生成输出。

除了上面提及的固定窗口的转换操作，Spark Streaming 还提供了基于滑动窗口（Sliding Window，相邻的窗口间会有重合部分）的转换操作，它会在一个比 StreamingContext 的批次间隔更长的时间范围内，通过整合多个批次的结果，计算出整个窗口的结果。

对滑动窗口操作而言，在其窗口内部会有N个批次数据，批次数据的个数由窗口间隔（Window Duration）决定，其为窗口持续的时间，在窗口操作中只有窗口间隔满足了才会触发批数据的处理。处理窗口的长度，另一个重要的参数就是滑动间隔（Slide Duration），它指的是经过多长时间窗口滑动一次形成新的窗口，滑动间隔默认情况下和批次间隔的相同，而窗口间隔一般设置得要比它们都大。需要注意的是，窗口间隔和滑动间隔的大小一定要设置为批次间隔的整数倍。



![12-8](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696d8f0a34ac27d~tplv-t2oaga2asx-watermark.awebp)



如上图所示，批次间隔是1个时间单位，窗口间隔是3个时间单位，滑动间隔是2个时间单位。对于初始的窗口 time1 至 time3，只有窗口间隔满足了才会触发数据的处理。这里需要注意的是，初始时有可能流入的数据没有撑满窗口，但是随着时间的推进，窗口最终会被撑满。每隔2个时间单位窗口滑动一次，会有新的数据流入窗口，这时窗口会移除最早的两个时间单位的数据，而与最新的两个时间单位的数据进行汇总形成新的窗口，即 time3 至 time5。

以代码清单34-1为例，如果我们想每隔10秒计算最近30秒的单词总数，那么可以将代码清单34-1中的第⑥行修改为如下语句：

```
val windowedWordCounts = pairs.reduceByKeyAndWindow((a:Int,b:Int) => (a + b), 
     Seconds(30), Seconds(10))
```

这里就涉及滑动窗口操作的两个参数：窗口间隔，也就是这里的30s；滑动间隔，也就是这里的10s。

# <a name="133">Kafka与Spark Streaming的整合</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

采用 Spark Streaming 流式处理 Kafka 中的数据，首先需要把数据从 Kafka 中接收过来，然后转换为 Spark Streaming 中的 DStream。接收数据的方式一共有两种：利用接收器 Receiver 的方式接收数据和直接从 Kafka 中读取数据。



![12-9](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696d9561dfa6c40~tplv-t2oaga2asx-watermark.awebp)



Receiver 方式通过 KafkaUtils.createStream() 方法来创建一个 DStream 对象，它不关注消费位移的处理，Receiver 方式的结构如上图所示。但这种方式在 Spark 任务执行异常时会导致数据丢失，如果要保证数据的可靠性，则需要开启预写式日志，简称 WAL（Write Ahead Logs），只有收到的数据被持久化到 WAL 之后才会更新 Kafka 中的消费位移。收到的数据和 WAL 存储位置信息被可靠地存储，如果期间出现故障，那么这些信息被用来从错误中恢复，并继续处理数据。

WAL 的方式可以保证从 Kafka 中接收的数据不被丢失。但是在某些异常情况下，一些数据被可靠地保存到了 WAL 中，但是还没有来得及更新消费位移，这样会造成 Kafka 中的数据被 Spark 拉取了不止一次。同时在 Receiver 方式中，Spark 的 RDD 分区和 Kafka 的分区并不是相关的，因此增加 Kafka 中主题的分区数并不能增加 Spark 处理的并行度，仅仅增加了接收器接收数据的并行度。

Direct 方式是从 Spark 1.3 开始引入的，它通过 KafkaUtils.createDirectStream() 方法创建一个 DStream 对象，Direct 方式的结构如下图所示。该方式中 Kafka 的一个分区与 Spark RDD 对应，通过定期扫描所订阅的 Kafka 每个主题的每个分区的最新偏移量以确定当前批处理数据偏移范围。与 Receiver 方式相比，Direct 方式不需要维护一份 WAL 数据，由 Spark Streaming 程序自己控制位移的处理，通常通过检查点机制处理消费位移，这样可以保证 Kafka 中的数据只会被 Spark 拉取一次。



![12-10](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696d95f9b00376f~tplv-t2oaga2asx-watermark.awebp)



注意使用 Direct 的方式并不意味着实现了精确一次的语义（Exactly Once Semantics），如果要达到精确一次的语义标准，则还需要配合幂等性操作或事务性操作。

在 Spark 官网中，关于 Spark Streaming 与 Kafka 集成给出了两个依赖版本，一个是基于 Kafka 0.8 之后的版本（spark-streaming-kafka-0-8），另一个是基于 Kafka 0.10 及其之后的版本（spark-streaming-kafka-0-10）。spark-streaming-kafka-0-8 版本的 Kafka 与 Spark Streaming 集成有 Receiver 方式和 Direct 方式这两种接收数据的方式，不过 spark-streaming-kafka-0-8 从 Spark 2.3.0 开始被标注为“弃用”。而 spark-streaming-kafka-0-10 版本只提供 Direct 方式，同时底层使用的是新消费者客户端 KafkaConsumer 而不是之前的旧消费者客户端，因此通过 KafkaUtils.createDirectStream() 方法构建的 DStream 数据集是 ConsumerRecord 类型。下表中给出了两个版本的更多细节对比。

| 兼容性比较        | spark-streaming-kafka-0-8 | spark-streaming-kafka-0-10 |
| ----------------- | ------------------------- | -------------------------- |
| Kafka broker 版本 | 0.8.2.1或更高             | 0.10.0或更高               |
| API 稳定性        | 弃用（Deprecated）        | 稳定（Stable）             |
| 语言支持          | Scala、Java、Python       | Scala、Java                |
| Receiver DStream  | Yes                       | No                         |
| Direct DStream    | Yes                       | Yes                        |
| SSL/TLS 支持      | No                        | Yes                        |
| Offset 提交 API   | No                        | Yes                        |
| 动态主题订阅      | No                        | Yes                        |



前面提及本节的内容是基于 Spark 2.3.1 版本的，因此下面的介绍也只基于 spark-streaming- kafka-0-10 版本做相应的陈述，更何况 spark-streaming-kafka-0-8 版本已经被弃用。spark-streaming-kafka-0-10 版本需要的 Maven 依赖如下：

```
<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-streaming-kafka-0-10_2.11</artifactId>
    <version>2.3.1</version>
</dependency>
<dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>2.0.0</version>
</dependency>
```

下面使用一个简单的例子来演示 Spark Streaming 和 Kafka 的集成。在该示例中，每秒往 Kafka 写入一个0～9之间的随机数，通过 Spark Streaming 从 Kafka 中获取数据并实时计算批次间隔内的数据的数值之和。

往 Kafka 中写入随机数的主要代码如下：

```
Random random = new Random();
while (true) {
    String msg = String.valueOf(random.nextInt(10));
    ProducerRecord<String, String> message =
            new ProducerRecord<>(topic, msg);
    producer.send(message).get();
    TimeUnit.SECONDS.sleep(1);
}
```

Kafka 与 Spark Streaming 的集成示例如代码清单34-2所示，代码中的批次间隔设置为2s。示例中的主题 topic-spark 包含4个分区。

```
//代码清单34-2 Kafka与Spark Streaming的集成示例
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies._
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies._
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingWithKafka {
  private val brokers = "localhost:9092"
  private val topic = "topic-spark"
  private val group = "group-spark"
  private val checkpointDir = "/opt/kafka/checkpoint"

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local")
      .setAppName("StreamingWithKafka")①
    val ssc = new StreamingContext(sparkConf, Seconds(2))②
    ssc.checkpoint(checkpointDir)							

    val kafkaParams = Map[String, Object](③
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers,
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> 
        classOf[StringDeserializer],
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> 
        classOf[StringDeserializer],
      ConsumerConfig.GROUP_ID_CONFIG -> group,
      ConsumerConfig.AUTO_OFFSET_RESET_CONFIG -> "latest",
      ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG -> (false:java.lang.Boolean)
    )

    val stream = KafkaUtils.createDirectStream[String, String](
      ssc, PreferConsistent, 
      Subscribe[String, String](List(topic), kafkaParams))④

    val value = stream.map(record => {⑤
      val intVal = Integer.valueOf(record.value())
      println(intVal)
      intVal
    }).reduce(_+_)
    value.print()⑥

    ssc.start
    ssc.awaitTermination
  }
}
```

第①和第②行代码在实例化 SparkConf 之后创建了 StreamingContext。创建 StreamingContext 后需要实例化一个 DStream，所以在第④行中通过 KafkaUtils.createDirectStream() 方法创建了一个。第⑤行只是简单地消费读取到的 ConsumerRecord，并执行简单的求和计算。

从 Kafka 中消费数据，这里的 Spark Streaming 本质上是一个消费者，因此 KafkaUtils.createDirectStream() 方法也需要指定 KafkaConsumer 的相关配置。KafkaUtils.createDirectStream() 方法的第一个参数好理解，方法中的第二个参数是 LocationStrategies 类型的，用来指定 Spark 执行器节点上 KafkaConsumer 的分区分配策略。

LocationStrategies 类型提供了3种策略：PerferBrokers 策略，必须保证执行器节点和 Kafka Broker 拥有相同的 host，即两者在相同的机器上，这样可以根据分区副本的 leader 节点来进行分区分配；PerferConsistent 策略，该策略将订阅主题的分区均匀地分配给所有可用的执行器，在绝大多数情况下都使用这种策略，本示例使用的也是这种策略；PerferFixed 策略，允许开发人员指定分区与 host 之间的映射关系。KafkaUtils.createDirectStream() 方法中的第三个参数是 ConsumerStrategies 类型的，用来指定 Spark 执行器节点的消费策略。

与 KafkaConsumer 订阅主题的方式对应，这里也有3种策略：Subscribe、SubscribePattern 和 Assign，分别代表通过指定集合、通过正则表达式和通过指定分区的方式进行订阅。

示例程序最直观的功能就是在每个批次间隔内（2s）读出数据（每秒1个）来进行求和，程序输出的部分结果如下所示。

```
3
4
-------------------------------------------
Time: 1533613594000 ms
-------------------------------------------
7
```

前面提到了执行器有3种消费策略，但是在代码清单34-2中只用到了 Subscribe 策略。如果要使用 SubscribePattern 策略，则可以将代码中的第④行代码修改为如下内容：

```
val stream = KafkaUtils.createDirectStream[String,String](
  ssc, PreferConsistent,
  SubscribePattern[String,String](Pattern.compile("topic-.*"),kafkaParams)
)
```

如果要使用 Assign 策略，则可以将代码中的第④行代码修改为如下内容：

```
val partitions = List(new TopicPartition(topic,0),
  new TopicPartition(topic,1),
  new TopicPartition(topic,2),
  new TopicPartition(topic,3))
val stream = KafkaUtils.createDirectStream[String,String](
  ssc, PreferConsistent,
  Assign[String, String](partitions, kafkaParams))
```

Spark Streaming 也支持从指定的位置处处理数据，前面演示的3种消费策略都可以支持，只需添加对应的参数即可。这里就以 Subscribe 策略为例来演示具体用法，可以用下面的代码替换代码清单34-2中的第④行代码，示例中的 fromOffsets 变量指定了每个分区的起始处理位置为5000：

```
val partitions = List(new TopicPartition(topic,0),
  new TopicPartition(topic,1),
  new TopicPartition(topic,2),
  new TopicPartition(topic,3))
val fromOffsets = partitions.map(partition => {
  partition -> 5000L
}).toMap
val stream = KafkaUtils.createDirectStream[String, String](
  ssc, PreferConsistent,
  Subscribe[String, String](List(topic), kafkaParams, fromOffsets))
```

代码清单34-2中只是计算了批次间隔内的数据，这样只是简单的转换操作，如果需要使用滑动窗口操作，比如计算窗口间隔为20s、滑动间隔为2s的窗口内的数值之和，那么可以将第⑤行代码修改为如下内容：

```
val value = stream.map(record=>{
  Integer.valueOf(record.value())
}).reduceByWindow(_+_, _-_,Seconds(20),Seconds(2))
```

前面说过在 Direct 方式下，Spark Streaming 会自己控制消费位移的处理，那么原本应该保存到 Kafka 中的消费位移就无法提供准确的信息了。但是在某些情况下，比如监控需求，我们又需要获取当前 Spark Streaming 正在处理的消费位移。Spark Streaming 也考虑到了这种情况，可以通过下面的程序来获取消费位移：

```
stream.foreachRDD(rdd=>{
  val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
  rdd.foreachPartition{iter=>
    val o: OffsetRange = offsetRanges(TaskContext.get.partitionId)
    println(s"${o.topic} ${o.partition} ${o.fromOffset} ${o.untilOffset}")
  }
})
```

注意需要将这段代码放在第④行之后，也就是需要在使用 KafkaUtils.createDirectStream() 方法创建 DStream 之后第一个调用，虽然 Kafka 的分区与 Spark RDD 一一对应，但是在混洗类型的方法（比如 reduceByKey()）执行之后这种对应关系就会丢失。

如果应用更加适合于批处理作业，那么在 Spark 中也可以使用 KafkaUtils.createRDD() 方法创建一个指定处理范围的 RDD。示例参考如下：

```
val offsetRanges = Array(
  OffsetRange(topic,0,0,100),
  OffsetRange(topic,1,0,100),
  OffsetRange(topic,2,0,100),
  OffsetRange(topic,3,0,100)
)
val rdd = KafkaUtils.createRDD(ssc,
  JavaConversions.mapAsJavaMap(kafkaParams),
  offsetRanges, PreferConsistent)
rdd.foreachPartition(records=>{
  records.foreach(record=>{
    println(record.topic()+":"+record.partition()+":"+ record.value())
  })
})
```

示例中的 OffsetRange 类型表示给定主题和分区中特定消息序列的下限和上限。OffsetRange(topic,0,0,100) 这行代码中标识从 topic 主题的第0个分区的偏移量0到偏移量100（不包括）的100条消息。



# <a name="134">Spark SQL</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Spark SQL 是一个用于处理结构化数据的 Spark 组件，它是在 Spark 1.0 版本开始加入 Spark 生态系统的。Spark SQL 能够利用 Spark 进行结构化数据的存储和操作，结构化数据既可以来自外部结构化数据源（Hive、JSON、Parquet、JDBC/ODBC等），也可以通过向已有 RDD 增加 Schema 的方式得到。

相比于 Spark RDD API，Spark SQL 包含了对结构化数据和在其上运算的更多信息，Spark SQL 使用这些信息进行额外的优化，使得对结构化数据的操作更高效和方便。Spark SQL 提供了多种使用的方式，包括 SQL、DataFrame API和Dataset API。

Spark SQL 用于支持 SQL 查询，Spark SQL API 的返回结果是 Dataset/DataFrame，除了 API，开发人员还可以使用命令行或 ODBC/JDBC 来执行 SQL 查询。

DataFrame 是一个分布式集合，其中数据被组织为命名的列。它在概念上等价于关系数据库中的表，但底层做了更多的优化。DataFrame 的前身是 SchemaRDD，从 Spark 1.3.0 开始 SchemaRDD 更名为 DataFrame。Dataset 是从 Spark 1.6 开始加入的，它的初衷是为了提升 RDD（强类型限制，可以使用 Lambda 函数）优化 SQL 执行引擎。Dataset 是 JVM 中的一个对象，可以作用于其他操作（map、flatMap、filter 等）。DataFrame 可以看作 Dataset[Row]，DataFrame 中的每一行类型是 Row。Dataset 相比于 DataFrame，它存储的是强类型值，而不是一个简单的 Row 对象，从某种程度上看，Dataset 可以看作 DataFrame 的一个特例。



![12-11](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696da264b02e51b~tplv-t2oaga2asx-watermark.awebp)



上图直观地体现了 RDD 与 DataFrame/Dataset 的区别。左侧的 RDD[Person] 虽然以 Person 为类型参数，但 Spark 本身不了解 Person 类的内部结构。而右侧的 DataFrame/Dataset 却提供了详细的结构信息，使得 Spark SQL 可以清楚地知道该数据集中包含哪些列，这些列的名称是什么，它们的类型又是什么。

下面我们通过一些示例来演示 Spark SQL 的基本用法，以及 DataFrame 与 Dataset 之间的细微差别。SparkSession 是一个公共入口类，我们可以通过 SparkSession.builder() 创建一个 SparkSession，相关示例如下（注：下面的示例都在 spark-shell 中运行）。

```
scala> import org.apache.spark.sql.SparkSession 
scala> val spark = SparkSession.builder()
    .appName("Spark SQL basic example").getOrCreate()
spark: org.apache.spark.sql.SparkSession = 
     org.apache.spark.sql.SparkSession@6cfd08e9

scala> import spark.implicits._ //将RDD隐式转换为DataFrame
```

在创建 SparkSession 之后，应用程序可以从已存在的 RDD 上创建 DataFrame，也可以从 Hive 表中创建，还可以从其他的 Spark 数据源中创建。下面就以$SPARK_HOME 下的 examples/src/main/resources/people.txt 文件为例来创建一个 DataFrame。people.txt 中的内容如下：

```
[root@node1 ~]# cat /opt/spark/examples/src/main/resources/people.txt
Michael, 29
Andy, 30
Justin, 19
```

创建 DataFrame 的过程如下：

```
//通过SparkContext的textFile()方法创建一个RDD
scala> val rdd = spark.sparkContext
        .textFile("/opt/spark/examples/src/main/resources/people.txt")
rdd: org.apache.spark.rdd.RDD[String] = 
        /opt/spark/examples/src/main/resources/people.txt 
        MapPartitionsRDD[1] at textFile at <console>:29
//使用case class定义Schema
scala> case class Person(name: String, age: Long)
defined class Person
//通过RDD创建一个DataFrame，这是以反射机制推断的实现方式
scala> val df = rdd.map(_.split(","))
        .map(p=>Person(p(0),p(1).trim.toInt)).toDF()
df: org.apache.spark.sql.DataFrame = [name: string, age: bigint]
//展示DataFrame中的内容
scala> df.show
+-------+---+
|   name|age|
+-------+---+
|Michael| 29|
|   Andy| 30|
| Justin| 19|
+-------+---+
```

在 Scala API 中，DataFrame 实际上是 Dataset[Row] 的别名；在 Java API 中，开发人员需要使用 Dataset<Row> 来表示 DataFrame。DataFrame 与 Dataset 之间可以进行相互转换：

```
//将DataFrame转换为Dataset
scala> val ds = df.as[Person]
ds: org.apache.spark.sql.Dataset[Person] = [name: string, age: bigint]
//将Dataset转换为DataFrame
scala> val new_df = ds.toDF()
new_df: org.apache.spark.sql.DataFrame = [name: string, age: bigint]
//Dataset是强类型的，而DataFrame不是，下面看一下两者的使用差别
scala> df.filter($"age">20).count()
res3: Long = 2
//DataFrame采用下面的方式会报错
scala> df.filter(_.age>20).count()
<console>:32: error: value age is not a member of org.apache.spark.sql.Row
       df.filter(_.age>20).count()
                   ^
scala> ds.filter(_.age>20).count()
res5: Long = 2
```

Spark SQL 允许程序执行 SQL 查询，返回 DataFrame 结果：

```
//注册临时表
scala> df.registerTempTable("people_table")
warning: there was one deprecation warning; re-run with -deprecation for details
//使用sql运行SQL表达式
scala> val result = spark.sql("SELECT name, age FROM people_table WHERE age>20")
result: org.apache.spark.sql.DataFrame = [name: string, age: bigint]
//显示查询结果
scala> result.show
+-------+---+
|   name|age|
+-------+---+
|Michael| 29|
|   Andy| 30|
+-------+---+
```

本节的内容只是让读者简单地了解 Spark SQL 的大致面貌，以便可以更好地引入下一节的内容—Structured Streaming。

# <a name="135">Structured Streaming</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Structured Streaming 是从 Spark 2.0 开始引入的一个建立在 Spark SQL 之上的可扩展和高容错的流处理引擎。有些读者可能会感到疑惑：Spark 已经有了 Spark Streaming，为什么还要新增加一个 Structured Streaming？Spark Streaming 是 Spark 早期基于 RDD 开发的流处理系统，用户使用 DStream API 来编写代码，支持高吞吐和良好的容错，其背后的主要模型是基于时间间隔的批处理。从 Spark 2.0 开始 Spark Streaming 就进入了维护模式。

Structured Streaming 并不是对 Spark Streaming 的简单改进，而是吸取了过去几年在开发 Spark SQL 和 Spark Streaming 过程中的经验教训，以及 Spark 社区的众多反馈而重新开发的全新流处理引擎，致力于为批处理和流处理提供统一的高性能 API。同时，在这个新的引擎中，我们也很容易实现之前在 Spark Streaming 中很难实现的一些功能，比如 Event Time 的支持、Stream-Stream Join、毫秒级延迟（Continuous Processing）。类似于 Dataset/DataFrame 代替 Spark Core 的 RDD 成为 Spark 用户编写批处理程序的首选，Dataset/DataFrame 也将替代 Spark Streaming 的 DStream，成为编写流处理程序的首选。



![12-12](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696da631d0cee39~tplv-t2oaga2asx-watermark.awebp)



Structured Streaming 的模型十分简洁，易于理解。如上图所示，一个流的数据源从逻辑上来说就是一个不断增长的动态表格，随着时间的推移，新数据被持续不断地添加到表格的末尾。用户可以使用 Dataset/DataFrame 或 SQL来对这个动态数据源进行实时查询。每次查询在逻辑上就是对当前的表格内容执行一次 SQL 查询。如何执行查询则是由用户通过触发器（Trigger）来设定的。用户既可以设定定期执行，也可以让查询尽可能快地执行，从而达到实时的效果。

一个流的输出有多种模式，既可以是基于整个输入执行查询后的完整结果（Complete模式），也可以选择只输出与上次查询相比的差异（Update模式），或者就是简单地追加最新的结果（Append模式）。这个模型对于熟悉 SQL 的用户来说很容易掌握，对流的查询跟查询一个表格几乎完全一样。

下面我们通过一个简单的例子来演示 Structured Streaming 的用法，Structured Streaming 是基于 Spark SQL 的，对应的 Maven 依赖也是与 Spark SQL 相关的，具体如下所示。

```
<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-sql_2.11</artifactId>
    <version>2.3.1</version>
</dependency>
```

与讲解 Spark Streaming 时的一样，这里也采用官方提供的单词统计代码进行具体的分析，Structured Streaming 使用示例如代码清单35-1所示（可以对比代码清单34-1）。

```
//代码清单35-1 Structured Streaming使用示例
import org.apache.spark.sql.SparkSession

object StructuredStreamingWordCount {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("StructuredStreamingWordCount")
      .getOrCreate() ①

    import spark.implicits._ ②

    val lines = spark.readStream
      .format("socket")
      .option("host","localhost")
      .option("port",9999)
      .load() ③
    val words = lines.as[String].flatMap(_.split(" ")) ④
    val wordCounts = words.groupBy("value").count() ⑤

    val query = wordCounts.writeStream ⑥
      .outputMode("complete")
      .format("console")
      .start()
    query.awaitTermination() ⑦
  }
}
```

第①行是程序的入口，主要用来创建一个 SparkSession 对象。第②行在讲解 Spark SQL 时也提及了，主要用来将 RDD 隐式地转换为 DataFrame。第③行从 Socket 连接中创建一个 DataFrame，lines 变量表示的是一个流文本数据的无边界表，此表包含一个列名为“value”的字符串（lines 变量的类型为 org.apache.spark.sql.DataFrame = [value: string]）。流文本中的每一行都将成为无边界表的的一行（Row）。第④行中，使用.as[String] 将 DataFrame 转换为 String 类型的 Dataset，如此我们便可以使用 flatMap() 函数将每一行切分成多个单词，所得到的 words 变量中包含了所有的单词。第⑤行通过分组来进行计数。第⑥行用来设置相应的流查询，剩下的就是实际开始接收数据并计数。这里我们使用的是 Complete 模式，也就是每次更新时会将完整的记录输出到控制台（.format(“console”)），start() 方法用来启动流式计算的运作。第⑦行用来等待查询活动的中止，防止查询还处于活动状态时无端退出。

Spark 安装包中也自带了这个程序，所以我们可以直接使用如下的方式来启动这个程序：

```
[root@node1  spark]# bin/run-example 
      org.apache.spark.examples.sql.streaming.StructuredNetworkWordCount 
      localhost 9999
```

同 Spark Streaming 中的示例一样，我们可以在另一个 shell 中使用 netcat 工具输入一句“hello world”，然后可以看到在 StructuredNetworkWordCount 程序中输出如下信息：

```
-------------------------------------------                                     
Batch: 0
-------------------------------------------
+-----+-----+
|value|count|
+-----+-----+
|hello|    1|
|world|    1|
+-----+-----+
```

很多应用程序可能需要基于事件时间来进行相关操作，事件时间（Event-time）是指数据本身内嵌的时间。比如需要每分钟获取 IoT（Internet of things，物联网）设备生成的事件数，则可能希望使用数据生成的时间（即数据中的事件时间），而不是 Spark 收到它们的时间。这个事件时间在 Structured Streaming 模型中非常自然地表现出来：

- 来自设备的每个时间都是表中的一行（Row），事件时间是该 Row 中的一个列值。这允许基于窗口的聚合（Window-based Aggregations）仅仅是事件时间列上的特殊类型的分组和聚合。例如：每分钟的事件数。
- 每个时间窗口（Time Window）是一个组，每个 Row 可以属于多个窗口/组。因此，可以在静态数据集（例如，来自收集的设备事件日志）和数据流上一致地定义基于事件时间窗口的聚合查询（Event-time-window-based Aggregation Queries），从而更加便于使用。

此外，该模型自然地处理了基于事件时间比预期晚到的数据。因为 Spark 会一直更新结果表（Result Table），因此当存在迟到数据时，Spark 可以完全控制更新旧的聚合，以及清除旧聚合以限制中间状态数据的大小。自 Spark 2.1 开始还增加了对水印（watermarking）的支持，允许用户指定迟到数据的阈值，并允许处理引擎相应地清除旧的状态。下面的示例展示的是一个基于事件时间窗口的单词统计案例：

```
import spark.implicits._

val words = ... // streaming DataFrame of schema { timestamp: Timestamp, word: String }

// Group the data by window and word and compute the count of each group
val windowedCounts = words.groupBy(
  window($"timestamp", "10 minutes", "5 minutes"),
  $"word"
).count()
```

代码示例中的窗口大小为10分钟，并且窗口每5分钟滑动一次。words 变量是一个DataFrame 类型，它包含的 schema 为{timestamp: Timestamp, word: String}，其中 timestamp 是数据内嵌的事件时间，word 指的是具体的单词。

# <a name="136">Kafka与Structured Streaming的整合</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

Kafka 与 Structured Streaming 的集成比较简单，只需要将代码清单35-1中第③行的数据源由原来的 Socket 替换成 Kafka 即可。不过在此之前需要引入相应的 Maven 依赖，具体如下所示。

```
<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-sql-kafka-0-10_2.11</artifactId>
    <version>2.3.1</version>
</dependency>
```

Kafka 与 Structured Streaming 的集成示例如代码清单35-2所示。这里 Kafka 中的测试案例数据与代码清单34-2中的一样，每秒会往 Kafka 主题 topic-spark 中写入一个0～9之间的随机数，这样本例中的 Structured Streaming 便可以消费这些随机数并进行频次统计。

```
//代码清单35-2 Kafka与Structured Streaming的集成示例
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.SparkSession

object StructuredStreamingWithKafka {
  val brokerList = "localhost:9092" //Kafka集群的地址
  val topic = "topic-spark" 		   //订阅的主题

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.master("local[2]")
      .appName("StructuredStreamingWithKafka").getOrCreate() ①

    import spark.implicits._ ②

    val df = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers",brokerList)
      .option("subscribe",topic)
      .load() ③

    val ds = df.selectExpr("CAST(value AS STRING)").as[String] ④

    val words = ds.flatMap(_.split(" ")).groupBy("value").count() ⑤

    val query = words.writeStream
      .outputMode("complete")
      .trigger(Trigger.ProcessingTime("10 seconds"))
      .format("console")
      .start() ⑥

    query.awaitTermination()
  }
}
```

示例中的第③和第④行替换了代码清单35-1的代码，即更改了数据源。上面示例代码的第③行中的 kafka.bootstrap.servers 选项表示要连接的 Kafka 集群的地址，subscribe 选项表示的是订阅模式。在 Kafka 中有三种订阅模式：集合订阅的方式（subscribe(Collection)）、正则表达式订阅的方式（subscribe(Pattern)）和指定分区的订阅方式（assign(Collection)。这里的 subscribe 选项对应集合订阅的方式，其他两种订阅方式在这里分别对应 subscribePattern 和 assign。比如可以将第③行中的.option("subscribe",topic) 替换为.option("subscribePattern", "topic.*")。

通过第④行中的 df.selectExpr("CAST(value AS STRING)") 语句可以从 df 这个 DataFrame 中挑选出想要的 value 这一列，毕竟本示例只关心 value 里的随机数并以此进行频次统计。这里的 Structured Streaming 相当于 Kafka 的消费者，也就是会消费到 ConsumerRecord 类型的数据，对应的也会有与 ConsumerRecord 相似的结构。我们可以打印出示例中 df 变量的结构类型，参考如下：

```
scala> df.printSchema
root
 |-- key: binary (nullable = true)
 |-- value: binary (nullable = true)
 |-- topic: string (nullable = true)
 |-- partition: integer (nullable = true)
 |-- offset: long (nullable = true)
 |-- timestamp: timestamp (nullable = true)
 |-- timestampType: integer (nullable = true)

scala> df.selectExpr("CAST(value AS STRING)").printSchema
root
 |-- value: string (nullable = true)
```

第④行后面的.as[String] 在讲解 Structured Streaming 中提及，它用来将 DataFrame 转换为 String 类型的 Dataset。代码清单35-2中接下去的内容就是纯粹的频次统计了，这里就不再赘述。最终的某一阶段的执行结果可以参考如下：

```
-------------------------------------------
Batch: 22
-------------------------------------------
+-----+-----+
|value|count|
+-----+-----+
|    7|   20|
|    3|   25|
|    8|   18|
|    0|   11|
|    5|   18|
|    6|   27|
|    9|   31|
|    1|   20|
|    4|   25|
|    2|   15|
+-----+-----+
```

如果进行的是一个批处理查询而不是流查询（Stream Queries），那么可以使用 startingOffsets 和 endingOffsets 这两个选项指定一个合适的偏移量范围来创建一个 DataFrame/Dataset，示例如下：

```
val df = spark
  .read
  .format("kafka")
  .option("kafka.bootstrap.servers", "host1:port1,host2:port2")
  .option("subscribe", "topic1,topic2")
  .option("startingOffsets", 
        """{"topic1":{"0":23,"1":-2},"topic2":{"0":-2}}""")
  .option("endingOffsets", 
        """{"topic1":{"0":50,"1":-1},"topic2":{"0":-1}}""")
  .load()
df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
  .as[(String, String)]
```

加粗部分的是.read 而不是前面示例中的.readStream，注意其中的区别。startingOffsets 和 endingOffsets 这两个选项的具体释义如下表所示。

| 选 项           | 取 值                                                        | 默 认 值                                     | 查 询 类 型        | 释 义                                                        |
| --------------- | ------------------------------------------------------------ | -------------------------------------------- | ------------------ | ------------------------------------------------------------ |
| startingOffsets | "earliest"、"latest"（只适用于流查询）或 JSON 字符串，比如：""" {"topicA":{"0":23,"1":-1},"topicB":{"0":-2}} """ | "latest"用于流查询，"earliest"用于批处理查询 | 流查询和批处理查询 | 当一个查询开始的时候，这个选项用来指定从哪个偏移量开始执行，"earliest"表示最早的偏移量，"latest"表示最新的偏移量，而 JSON 字符串可以为每个分区指定对应的起始偏移量。在 JSON 字符串中，-2表示最早的偏移量，-1表示最新的偏移量。注意：对应批处理查询不允许使用最新的偏移量进行查询。对流查询而言，这个选项只适用于启动一个新查询，其余情况下都是从原理查询到的偏移量处继续进行查询，在查询期间新发现的分区将从最早的偏移量处开始查询 |
| endingOffsets   | "latest"或 JSON 字符串{"topicA":{"0":23,"1":-1},"topicB":{"0":-1}} | "latest"                                     | 批处理查询         | 用来指定一个批处理查询结束时的偏移量，"latest"表示最新的偏移量，而 JSON 字符串可以为每个分区指定对应的结束偏移量。在 JSON 字符串中，-1表示最新的偏移量，而-2（最早）是不被允许的 |



可以通过在 Kafka 原生的参数前面添加一个“kafka.”的前缀来作为要配置的与 Kafka 有关的选型，比如代码清单35-2中的.option("kafka.bootstrap.servers",brokerList)所对应的就是 Kafka 客户端中的 bootstrap.servers 参数。但这一规则并不适合所有的参数，对于如下的 Kafka 参数是无法在使用 Structured Streaming 时设置的。

- group.id：每次查询时会自动创建，类似于 spark-kafka-source-8728dee8-eed1-4986- 87b2-57265d2eb099--846927976-driver-0 这种名称。
- auto.offset.reset：相关的功能由 startingOffsets 选项设定。
- key.serializer/value.serializer：总是使用 ByteArraySerializer 或 StringSerializer 进行序列化。可以使用 DataFrame 操作显式地将 key/value 序列化为字符串或字节数组。
- key.deserializer/value.deserializer：总是使用 ByteArrayDeserializer 将 key/value 反序列化为字节数组。可以使用 DataFrame 操作显式地反序列化 key/value。
- enable.auto.commit：这里不会提交任何消费位移。
- interceptor.classes：这里总是将 key 和 value 读取为字节数组，使用 ConsumerInterceptor 可能会破坏查询，因此是不安全的。

由如上信息可以看出这里既不提交消费位移，也不能设置 group.id，如此若要通过传统的方式来获取流查询的监控数据是行不通了。不过 Structured Streaming 自身提供了几种监控的手段，可以直接通过 StreamingQuery 的 status() 和 lastProgress() 方法来获取当前流查询的状态和指标。具体而言，lastProgress () 方法返回的是一个 StreamingQueryProgress 对象，如代码清单35-3所示。status() 方法返回的是一个 StreamingQueryStatus 对象，内容如下所示。

```
println(query.status)

/*  Will print something like the following.
{
  "message" : "Waiting for data to arrive",
  "isDataAvailable" : false,
  "isTriggerActive" : false
}
```

StreamingQuery 中还有一个 recentProgress() 方法用来返回最后几个进度的 StreamingQuery- Progress 对象的集合。

```
//代码清单35-3 监控指标
{
  "id" : "4d61ac30-9c32-4607-b645-4a2d303265a2",
  "runId" : "aa1f7dfb-a103-4eab-8ffa-fa0583f6e2b1",
  "name" : null,
  "timestamp" : "2018-08-14T09:13:56.376Z",
  "batchId" : 6,
  "numInputRows" : 0,
  "inputRowsPerSecond" : 0.0,
  "processedRowsPerSecond" : 0.0,
  "durationMs" : {
    "getOffset" : 1,
    "triggerExecution" : 2
  },
  "stateOperators" : [ ],
  "sources" : [ {
    "description" : "KafkaSource[Subscribe[topic-spark]]",
    "startOffset" : {
      "topic-spark" : {
        "2" : 13412,
        "1" : 13411,
        "3" : 13412,
        "0" : 13409
      }
    },
    "endOffset" : {
      "topic-spark" : {
        "2" : 13412,
        "1" : 13411,
        "3" : 13412,
        "0" : 13409
      }
    },
    "numInputRows" : 0,
    "inputRowsPerSecond" : 0.0,
    "processedRowsPerSecond" : 0.0
  } ],
  "sink" : {
    "description" : 
     "org.apache.spark.sql.execution.streaming.ConsoleSinkProvider@7706fccf"
  }
}
```

Spark 支持通过 Dropwizard 进行指标上报，对 Structured Streaming 而言，可以显式地将参数 spark.sql.streaming.metricsEnabled 设置为 true 来开启这个功能，示例如下：

```
spark.conf.set("spark.sql.streaming.metricsEnabled", "true")
// or
spark.sql("SET spark.sql.streaming.metricsEnabled=true")
```

Structure Streaming 还提供了异步的方式来监控所有的流查询，所要做的就是通过 spark.streams.addListener() 方法来添加一个自定义的 StreamingQueryListener，示例如下：

```
val spark: SparkSession = ...
spark.streams.addListener(new StreamingQueryListener() {
    override def onQueryStarted(queryStarted: QueryStartedEvent): Unit = {
        println("Query started: " + queryStarted.id)
    }
    override def onQueryTerminated(
          queryTerminated: QueryTerminatedEvent): Unit = {
        println("Query terminated: " + queryTerminated.id)
    }
    override def onQueryProgress(queryProgress: QueryProgressEvent): Unit = {
        println("Query made progress: " + queryProgress.progress)
    }
})
```

顾名思义，StreamingQueryListener 中的 onQueryStarted() 方法会在流查询开始的时候调用，而 onQueryTerminated() 方法会在流查询结束的时候调用。onQueryProgress() 方法中的 queryProgress.progress 正对应于代码清单35-3中的指标信息，流查询每处理一次进度就会调用一下这个回调方法。



![12-13](https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/12/1696dbce41d7a18c~tplv-t2oaga2asx-watermark.awebp)



我们可以通过 onQueryProgress() 方法来将流查询的指标信息传递出去，以便对此信息进行相应的处理和图形化展示。如上图所示，我们可以将指标信息发送到 Kafka 的某个内部监控主题，通过专门的数据采集模块 Metrics Collector 来拉取这些指标信息并进行相应的解析、转化、处理和存储，进而呈现在图形化展示平台为用户提供参考依据。

## <a name="137">总结</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

这3节开始我们主要讲述了 Spark 中的相关概念，包括 Spark 的整体架构、Spark 的编程模型、Spark 运行架构、Spark Streaming 和 Structured Streaming，这里使用的篇幅比介绍 Kafka Streams 时用的篇幅要多，因为笔者认为从 Spark 的角度去理解流式计算（处理），进而再去理解 Kafka Streams 要容易得多。这部分内容还重点介绍了 Spark Streaming 和 Structured Streaming 与 Kafka 的集成，这也是现实应用中使用得非常多的地方，而且也是两者结合最紧密的地方，可以让我们从另一个框架的角度去深刻地理解 Kafka 的使用。



