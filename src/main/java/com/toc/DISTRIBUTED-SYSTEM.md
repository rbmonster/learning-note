<a name="index">**Index**</a>

<a href="#0">分布式系统</a>  
&emsp;<a href="#1">1. 经典基础理论</a>  
&emsp;&emsp;<a href="#2">1.1. 系统设计理念</a>  
&emsp;&emsp;<a href="#3">1.2. CAP理论</a>  
&emsp;&emsp;<a href="#4">1.3. BASE理论</a>  
&emsp;<a href="#5">2. 分布式事务</a>  
&emsp;&emsp;<a href="#6">2.1. 两阶段提交 2PC(phase-commit)</a>  
&emsp;&emsp;&emsp;<a href="#7">2.1.1. 两阶段提交的问题</a>  
&emsp;&emsp;<a href="#8">2.2. 三阶段提交 3PC(phase-commit)</a>  
&emsp;&emsp;<a href="#9">2.3. paxos算法</a>  
&emsp;&emsp;<a href="#10">2.4. Raft算法</a>  
&emsp;&emsp;&emsp;<a href="#11">2.4.1. 选举流程</a>  
&emsp;&emsp;&emsp;<a href="#12">2.4.2. 数据同步</a>  
# <a name="0">分布式系统</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
## <a name="1">经典基础理论</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="2">系统设计理念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 中心化设计：分为两种角色： “领导” 和 “干活的”。类似于吉联EDS系统的中心化设计。
  - 问题1：“领导”节点的正常响应问题。可以使用主备方案解决。
  - 问题2：“领导”节点的性能瓶颈，影响请求分发。
  
- 去中心化设计：不是不要中心，而是由节点来自由选择中心。 集群的成员会自发的举行“会议”选举新的“领导”主持工作。最典型的案例就是ZooKeeper及Go语言实现的Etcd（Raft算法）。
  - 脑裂问题：指一个集群由于网络的故障，被分为至少两个彼此无法通信的单独集群，此时如果两个集群都各自工作，则可能会产生严重的数据冲突和错误。
  - 方案：规模较小的集群就“自杀”或者拒绝服务
  
### <a name="3">CAP理论</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 强一致性：系统在执行过某项操作后仍然处于一致的状态。在分布式系统中，更新操作执行成功后所有的用户都应该读到最新的值，这样的系统被认为是具有强一致性的。
- 可用性：每一个操作总是能够在一定的时间内返回结果，这里需要注意的是"一定时间内"和"返回结果"。
- 分区容错性：理解为在存在网络分区的情况下，仍然可以接受请求（满足一致性和可用性)。这里的网络分区是指由于某种原因，网络被分成若干个孤立的区域，而区域之间互不相通。
- 理论相关解释：https://www.cnblogs.com/hxsyl/p/4381980.html

### <a name="4">BASE理论</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
-  BASE 是Basically Available（基本可用） 、Soft-state（软状态） 和 Eventually Consistent（最终一致性）
- 基本可用是指分布式系统在出现不可预知故障的时候，允许损失部分可用性。但是，这绝不等价于系统不可用。
- 软状态指允许系统中的数据存在中间状态，并认为该中间状态的存在不会影响系统的整体可用性，即允许系统在不同节点的数据副本之间进行数据同步的过程存在延时。
- 最终一致性强调的是系统中所有的数据副本，在经过一段时间的同步后，最终能够达到一个一致的状态。

## <a name="5">分布式事务</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="6">两阶段提交 2PC(phase-commit)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
在两阶段提交中，主要涉及到两个角色，分别是协调者和参与者。
- 第一阶段：当要执行一个分布式事务的时候，事务发起者首先向协调者发起事务请求，然后协调者会给所有参与者发送 prepare 请求（其中包括事务内容）告诉参与者需要执行事务。如果能执行发送的事务内容那么就先执行但不提交，执行后回复。
  - 然后参与者收到 prepare 消息后，他们会开始执行事务（但不提交），并将 Undo 和 Redo 信息记入事务日志中，之后参与者就向协调者反馈是否准备好了。
- 第二阶段：提交事务或者回滚事务。比如这个时候 所有的参与者 都返回了准备好了的消息，这个时候就进行事务的提交，协调者此时会给所有的参与者发送 Commit 请求 ，当参与者收到 Commit 请求的时候会执行前面执行的事务的 提交操作 ，提交完毕之后将给协调者发送提交成功的响应。
  - 如果在第一阶段有参与者返回了为准备好的消息，那么此时协调者将会给所有参与者发送 回滚事务的 rollback 请求，参与者收到之后将会 回滚它在第一阶段所做的事务处理。
  
#### <a name="7">两阶段提交的问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 单点故障问题，如果协调者挂了那么整个系统都处于不可用的状态了。
- 阻塞问题，即当协调者发送 prepare 请求，参与者收到之后如果能处理那么它将会进行事务的处理但并不提交，这个时候会一直占用着资源不释放，如果此时协调者挂了，那么这些资源都不会再释放了，这会极大影响性能。
- 数据不一致问题。
  - 协调者宕机。比如当第二阶段，协调者只发送了一部分的 commit 请求就挂了，那么也就意味着，收到消息的参与者会进行事务的提交，而后面没收到的则不会进行事务提交，那么这时候就会产生数据不一致性问题。
  - 脑裂问题。如果分布式节点出现网络分区，某些参与者未收到commit提交命令，就会出现一部分提交数据，而另一部分未提交数据的不一致问题。

### <a name="8">三阶段提交 3PC(phase-commit)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
三阶段提交的流程如下：
1. CanCommit阶段：协调者向所有参与者发送 CanCommit 请求，参与者收到请求后会根据自身情况查看是否能执行事务，如果可以则返回 YES 响应并进入预备状态，否则返回 NO 。
2. PreCommit阶段：协调者根据参与者返回的响应来决定是否可以进行下面的 PreCommit 操作。
   1. 如果上面参与者返回的都是 YES，那么协调者将向所有参与者发送 PreCommit 预提交请求，参与者收到预提交请求后，会进行事务的执行操作，并将 Undo 和 Redo 信息写入事务日志中 ，最后如果参与者顺利执行了事务则给协调者返回成功的响应。
   2. 如果在第一阶段协调者收到了 任何一个 NO 的信息，或者 在一定时间内 并没有收到全部的参与者的响应，那么就会中断事务，它会向所有参与者发送中断请求（abort）
3. DoCommit阶段：如果协调者收到了所有参与者在 PreCommit 阶段的 YES 响应，那么协调者将会给所有参与者发送 DoCommit 请求，**参与者收到 DoCommit 请求后则会进行事务的提交工作**，完成后则会给协调者返回响应，协调者收到所有参与者返回的事务提交成功的响应之后则完成事务。若协调者在 PreCommit 阶段 收到了任何一个 NO 或者在一定时间内没有收到所有参与者的响应 ，那么就会进行中断请求的发送，参与者收到中断请求后则会 通过上面记录的回滚日志 来进行事务的回滚操作，并向协调者反馈回滚状况，协调者收到参与者返回的消息后，中断事务。
  - > 3PC 在 DoCommit 阶段参与者如未收到协调者发送的提交事务的请求，它会在一定时间内进行事务的提交。因为这个时候我们肯定保证了在第一阶段所有的协调者全部返回了可以执行事务的响应，这个时候我们有理由相信其他系统都能进行事务的执行和提交
  
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/3PC.jpg)

- 总结：3PC 通过一系列的超时机制很好的缓解了阻塞问题，相比2PC参与者也有了超时中断机制。解决了无限阻塞及单点故障问题，但是仍然无法解决网络分区问题。
- 缺点：未解决网络分区问题：由于网络原因，协调者发送的abort响应没有及时被参与者接收到，那么参与者在等待超时之后执行了commit操作。
  
### <a name="9">paxos算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 三阶段提交的改进算法

TODO

### <a name="10">Raft算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 算法：主要用来竞选主节点。
   - 显示地址：http://thesecretlivesofdata.com/raft/

有三种节点：Follower、Candidate 和 Leader。
- Leader 会周期性的发送心跳包给 Follower。
- 每个 Follower 都设置了一个随机的竞选超时时间，一般为 150ms~300ms，如果在这个时间内没有收到 Leader 的心跳包，就会变成 Candidate，进入竞选阶段。

#### <a name="11">选举流程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 一个分布式系统的最初阶段，此时只有 Follower 没有 Leader。Node A 等待一个随机的竞选超时时间之后，没收到 Leader 发来的心跳包，因此进入竞选阶段。
2. Node A 发送投票请求给其它所有节点。其它节点会对请求进行回复，如果超过一半的节点回复了，那么该 Candidate 就会变成 Leader。
3. leader 会周期性地发送心跳包给 Follower，Follower 接收到心跳包，会重新开始计时。
4. 针对多候选人选举的情况：
   1. 如果有多个 Follower 成为 Candidate，并且所获得票数相同，那么就需要重新开始投票。
   2. 由于每个节点设置的随机竞选超时时间不同，因此下一次再次出现多个 Candidate 并获得同样票数的概率很低。
   
#### <a name="12">数据同步</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 自客户端的修改都会被传入 Leader。注意该修改还未被提交，只是写入日志中。
2. Leader 会把修改复制到所有 Follower。
3. Leader 会等待大多数的 Follower 也进行了修改，然后才将修改提交。
4. 此时 Leader 会通知的所有 Follower 让它们也提交修改，此时所有节点的值达成一致。
