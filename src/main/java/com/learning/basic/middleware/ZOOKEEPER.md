# zookeeper
## 基本概念

ZooKeeper 可以被用作注册中心、分布式锁；
ZooKeeper 是 Hadoop 生态系统的一员；
构建 ZooKeeper 集群的时候，使用的服务器最好是奇数台。

### ZooKeeper概述
- 概念：ZooKeeper 是一个**开源的分布式协调服务**，它的设计目标是将那些复杂且容易出错的分布式一致性服务封装起来，构成一个高效可靠的原语集，并以一系列简单易用的接口提供给用户使用。

- ZooKeeper 为我们提供了高可用、高性能、稳定的分布式数据一致性解决方案，通常被用于实现诸如数据发布/订阅、负载均衡、命名服务、分布式协调/通知、集群管理、Master 选举、分布式锁和分布式队列等功能。
- ZooKeeper 将数据保存在内存中，性能是非常棒的。 在“读”多于“写”的应用程序中尤其地高性能，因为“写”会导致所有的服务器间同步状态。（“读”多于“写”是协调服务的典型场景）。

### ZooKeeper 典型应用场景
- ZooKeeper 概览中，我们介绍到使用其通常被用于实现诸如数据发布/订阅、负载均衡、命名服务、分布式协调/通知、集群管理、Master 选举、分布式锁和分布式队列等功能。

- 下面选 3 个典型的应用场景来专门说说：
1. 分布式锁 ： 通过创建唯一节点获得分布式锁，当获得锁的一方执行完相关代码或者是挂掉之后就释放锁。
2. 命名服务 ：可以通过 ZooKeeper 的顺序节点生成全局唯一 ID
3. 数据发布/订阅 ：通过 Watcher 机制 可以很方便地实现数据发布/订阅。当你将数据发布到 ZooKeeper 被监听的节点上，其他机器可通过监听 ZooKeeper 上节点的变化来实现配置的动态更新。

#### 实际开源项目的应用
2.5. 有哪些著名的开源项目用到了 ZooKeeper?
- Kafka : ZooKeeper 主要为 Kafka 提供 Broker 和 Topic 的注册以及多个 Partition 的负载均衡等功能。
- Hbase : ZooKeeper 为 Hbase 提供确保整个集群只有一个 Master 以及保存和提供 regionserver 状态信息（是否在线）等功能。
- Hadoop : ZooKeeper 为 Namenode 提供高可用支持。

## ZooKeeper 重要概念
### Data model（数据模型）
- ZooKeeper 数据模型采用层次化的多叉树形结构，每个节点上都可以存储数据（数字、字符串或者是二级制序列）。每个节点还可以拥有 N 个子节点，最上层是根节点以“/”来代表。每个数据节点在 ZooKeeper 中被称为 znode，它是 ZooKeeper 中数据的最小单元。并且，每个 znode 都一个唯一的路径标识。
- **ZooKeeper 主要是用来协调服务的，而不是用来存储业务数据的，所以不要放比较大的数据在 znode 上，ZooKeeper 给出的上限是每个结点的数据大小最大是 1M。**
- zookeeper 节点表示：
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/middleware/picture/znode.png)

### znode（数据节点）
- 每个数据节点在 ZooKeeper 中被称为 znode，它是 ZooKeeper 中数据的最小单元。
- znode 分为 4 大类：
    - 持久（PERSISTENT）节点 ：一旦创建就一直存在即使 ZooKeeper 集群宕机，直到将其删除。
    - 临时（EPHEMERAL）节点 ：临时节点的生命周期是与**客户端会话**（session）绑定的，**会话消失则节点消失**。并且，临时节点只能做叶子节点 ，不能创建子节点。
    - 持久顺序（PERSISTENT_SEQUENTIAL）节点 ：除了具有持久（PERSISTENT）节点的特性之外， 子节点的名称还具有顺序性。比如 /node1/app0000000001 、/node1/app0000000002 。
    - 临时顺序（EPHEMERAL_SEQUENTIAL）节点 ：除了具备临时（EPHEMERAL）节点的特性之外，子节点的名称还具有顺序性。
    
#### znode数据结构
每个节点由下面两部分组成：
- stat ：状态信息
- data ：节点存放的数据的具体内容

以下为客户端获取znode节点信息的内容：
- ```
  [zk: 127.0.0.1:2181(CONNECTED) 6] get /dubbo
  // 该数据节点关联的数据内容为空
  null
  // 下面是该数据节点的一些状态信息，其实就是 Stat 对象的格式化输出
  cZxid = 0x2
  ctime = Tue Nov 27 11:05:34 CST 2018
  mZxid = 0x2   // 即该节点最后一次更新时的事务 id
  mtime = Tue Nov 27 11:05:34 CST 2018
  pZxid = 0x3
  cversion = 1    // 子节点版本号，当前节点的子节点每次变化时值增加 1
  dataVersion = 0  // 数据节点内容版本号，节点创建时为 0，每更新一次节点内容(不管内容有无变化)该版本号的值增加 1
  aclVersion = 0   // 节点的 ACL 版本号，表示该节点 ACL 信息变更次数
  ephemeralOwner = 0x0
  dataLength = 0
  numChildren = 1   // 当前节点的子节点个数
  ```

### ACL权限控制
ZooKeeper 采用 ACL（AccessControlLists）策略来进行权限控制，类似于 UNIX 文件系统的权限控制。

对于 znode 操作的权限，ZooKeeper 提供了以下 5 种：
- CREATE : 能创建子节点
- READ ：能获取节点数据和列出其子节点
- WRITE : 能设置/更新节点数据
- DELETE : 能删除子节点
- ADMIN : 能设置节点 ACL 的权限

其中 CREATE 和 DELETE 这两种权限都是针对 子节点 的权限控制。
   
对于身份认证，提供了以下几种方式：
- world ： 默认方式，所有用户都可无条件访问。
- auth :不使用任何 id，代表任何已认证的用户。
- digest :用户名:密码认证方式： username:password 。
- ip : 对指定 ip 进行限制。
  
### Watcher（事件监听器）
ZooKeeper 允许用户在指定节点上注册一些 Watcher，并且在一些特定事件触发的时候，ZooKeeper 服务端会将事件通知到感兴趣的客户端上去，该机制是 **ZooKeeper 实现分布式协调服务的重要特性**。
  - 特定事件如：监听Znode节点的数据变化、监听子节点的增减变化
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/middleware/picture/zookeeperWatcher.png)

### 会话
Session 可以看作是 ZooKeeper 服务器与客户端的之间的一个 TCP 长连接。通过session
 - 客户端能够通过心跳检测与服务器保持有效的会话。
 - 能够向 ZooKeeper 服务器发送请求并接受响应。
 - 能够通过该连接接收来自服务器的 Watcher 事件通知。
 
两个重要的属性：
- sessionTimeout 代表会话的超时时间。只要在sessionTimeout规定的时间内能够重新连接上集群中任意一台服务器，那么之前创建的会话仍然有效。
- sessionID是 ZooKeeper 会话的一个重要标识，保证全局唯一。

## ZooKeeper集群
- 为了保证高可用，最好是以集群形态来部署 ZooKeeper，这样只要集群中大部分机器是可用的（能够容忍一定的机器故障）。通常 3 台服务器就可以构成一个 ZooKeeper 集群了

![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/middleware/picture/zookeeperCluster.jpg)

Server为ZooKeeper服务器，集群间通过 ZAB 协议（ZooKeeper Atomic Broadcast）来保持数据的一致性。
- 最典型集群模式： Master/Slave 模式（主备模式）。在这种模式中，通常 Master 服务器作为主服务器提供写服务，其他的 Slave 服务器从服务器通过异步复制的方式获取 Master 服务器最新的数据提供读服务。


### 集群中角色及节点选择
- Leader：为客户端提供读和写的服务，负责投票的发起和决议，更新系统状态。
- Follower：为客户端提供读服务，如果是写服务则转发给 Leader。在选举过程中参与投票。
- Observer：为客户端提供读服务器，如果是写服务则转发给 Leader。不参与选举过程中的投票，也不参与“过半写成功”策略。在不影响写性能的情况下提升集群的读性能。此角色于 ZooKeeper3.3 系列新增的角色。

具体选举的流程为Raft算法：


#### ZooKeeper 集群中的服务器状态
LOOKING ：寻找 Leader。
LEADING ：Leader 状态，对应的节点为 Leader。
FOLLOWING ：Follower 状态，对应的节点为 Follower。
OBSERVING ：Observer 状态，对应节点为 Observer，该节点不参与 Leader 选举。

### ZooKeeper 集群为啥最好奇数台？
ZooKeeper 集群在宕掉几个 ZooKeeper 服务器之后，如果剩下的 ZooKeeper 服务器个数大于宕掉的个数的话整个 ZooKeeper 才依然可用。那么2n 和 2n-1 的容忍度是一样的，都是 n-1，因此使用奇数台服务器。


### ZAB 协议
ZAB（ZooKeeper Atomic Broadcast 原子广播） 协议是为分布式协调服务 ZooKeeper 专门设计的一种支持崩溃恢复的原子广播协议。**ZooKeeper 中主要依赖 ZAB 协议来实现分布式数据一致性**，而并没有直接使用Paxos协议。

 ZAB协议包含两种基本的模式
- 消息广播 ：当集群中已经有过半的Follower服务器完成了和Leader服务器的状态同步，那么整个服务框架就可以进入消息广播模式了。 
- 崩溃恢复 ：当整个服务框架在运行过程中，当 Leader 服务器出现异常情况时，ZAB 协议就会进入恢复模式并选举产生新的Leader服务器。新Leader产生，集群其他机器就要与该服务器进行状态同步，保持数据一致。当过半服务器完成状态同步就退出恢复模式。

#### 消息广播
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/middleware/picture/zabSend.jpg)
- 使用节点队列，保证follower和observer的顺序性。同时针对每个节点server也建立来了一个消息队列，使用TCP传输，保证消息的顺序性，避免消息因网络原因而导致处理顺序不一致，进而避免数据不一致问题。
- 在 ZAB 中还定义了一个 全局单调递增的事务ID ZXID，同样也是为了保证顺序性。每个消息在leader中通过其 ZXID 来进行排序 ，才能得到处理。

#### 崩溃恢复
- 类似于Raft选举 
- 接着就是数据同步。
- https://github.com/Snailclimb/JavaGuide/blob/master/docs/system-design/distributed-system/zookeeper/zookeeper-plus.md#1-%E5%A5%BD%E4%B9%85%E4%B8%8D%E8%A7%81

## 实际应用实现
### 分布式锁
- ZooKeeper保证无法重复创建同样的节点，进而保证了在高并发的情况下保证节点创建的全局唯一性 

流程：
1. 让多个客户端同时创建一个临时节点，创建成功的就说明获取到了锁 。
2. 没有获取到锁的客户端也像上面选主的非主节点创建一个 watcher 进行节点状态的监听，如果这个互斥锁被释放了（可能获取锁的客户端宕机了，或者那个客户端主动释放了锁）可以调用回调函数重新获得锁。
3. 使用监听模式，当监听的连接太多时，释放节点会导致过大的流量访问zk。

改进流程：
1. 多个客户端，建立临时顺序节点。
2. 获取锁节点下的子节点并排序，如果创建节点为第一个子节点获得锁。
3. 若不是第一个节点，监听当前节点的前序节点，等待释放消息。

#### ZK在分布式锁中实践的一些缺点
- 在创建锁和释放锁的过程中，都要动态创建、销毁瞬时节点来实现锁功能，性能没有缓存高。
- ZK中创建和删除节点只能通过Leader服务器来执行，然后将数据同步到所有的Follower机器上。
- （较少见，zk有重试机制）网络抖动可能导致ZK服务器认为连接断开，就释放临时节点，导致其他客户端获取到锁，实际连接并未断开。

#### zookeeper 同时实现 共享锁和独占锁
1. 规定所有创建节点必须有序。
2. 当你是读请求（要获取共享锁）的话，如果 没有比自己更小的节点，或比自己小的节点都是读请求 ，则可以获取到读锁，然后就可以开始读了。若存在写锁阻塞。
3. 如果是写请求，要等节点及其子节点读或写锁释放后才能获取到锁。

### 命名服务
zookeeper 是通过 树形结构 来存储数据节点的，那也就是说，对于每个节点的 全路径，它必定是唯一的，我们可以使用节点的全路径作为命名方式了。
  - 路径是我们可以自己定义的，这对于我们对有些有语意的对象的ID设置可以更加便于理解。
 
### 注册中心实现
实现：
1. 让 服务提供者 在 zookeeper 中创建一个临时节点并且将自己的**ip、port、调用方式**写入节点。
2. 当 服务消费者 需要进行调用的时候会 通过注册中心找到相应的服务的地址列表(IP端口什么的) ，并缓存到本地(方便以后调用)
3. 当消费者调用服务时，不会再去请求注册中心，而是直接通过负载均衡算法从地址列表中取一个服务提供者的服务器调用服务。
4. 当服务提供者的某台服务器宕机或下线时，相应的地址会从服务提供者地址列表中移除。同时，注册中心会将新的服务地址列表发送给服务消费者的机器并缓存在消费者本机。
  - Eureka 注册中心会先试错，然后再更新
  
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/middleware/picture/ZooKeeperConfiguration.png)


### 配置中心
- 现在有三个系统A、B、C，他们有三份配置，分别是ASystem.yml、BSystem.yml、CSystem.yml，抽取公共配置common.yml。
- 将common.yml这份配置放在ZooKeeper的Znode节点中，系统A、B、C监听着这个Znode节点有无变更，如果变更了，及时响应。

### 分布式队列
入队操作就是在 queue_fifo 下创建自增序的子节点，并把数据（队列大小）放入节点内。出队操作就是先找到 queue_fifo 下序号最下的那个节点，取出数据，然后删除此节点。

### 栅栏
线程启动时在 ZooKeeper 节点 /queue_barrier 下插入顺序临时节点，然后检查 /queue/barrier 下所有 children 节点的数量是否为所有的线程数，如果不是，则等待，如果是，则开始执行。

- 相关文章：https://zhuanlan.zhihu.com/p/59669985

## 命令行操作
- 临时节点都是会话级别的，会话断开之后，节点就删除了。
```
create /test laogong // 创建永久节点 

create -e /test laogong // 创建临时节点

create -s /test // 创建顺序节点

create -e -s /test  // 创建临时顺序节点
```