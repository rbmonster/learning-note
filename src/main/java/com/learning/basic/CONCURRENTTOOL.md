# Java并发相关工具类

## AQS 相关

### AbstractQueuedSynchronizer AQS 基础类
- AQS 是一个用来构建锁和同步器的框架，使用 AQS 能简单且高效地构造出应用广泛的大量的同步器。核心思想是
  - 如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程，并且将共享资源设置为锁定状态。
  - 如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制 AQS 是用 CLH 队列锁实现的，即将暂时获取不到锁的线程加入到队列中。
  - CLH(Craig,Landin,and Hagersten)队列是一个虚拟的双向队列（虚拟的双向队列即不存在队列实例，仅存在结点之间的关联关系）
- 使用模板模式提供了 锁与同步的框架，将一些实现延迟到子类中实现。
  - 需要具体子类实现的方法：
  - ```
    isHeldExclusively()//该线程是否正在独占资源。只有用到condition才需要去实现它。
    tryAcquire(int)//独占方式。尝试获取资源，成功则返回true，失败则返回false。
    tryRelease(int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。
    tryAcquireShared(int)//共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。
    tryReleaseShared(int)//共享方式。尝试释放资源，成功则返回true，失败则返回false。
    ```
  - 比如独占锁相关的方法调用框架
  - ```
     // 尝试获取锁
     public final void acquire(int arg) {
         // tryAcquire 需重写   acquireQueued、addWaiter为默认方法
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
     }
    
    // 释放锁
    public final boolean release(int arg) {
        // tryRelease 需重写
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0)
                unparkSuccessor(h);
            return true;
        }
        return false;
    }
    ```
  - 比如ReentrantLock 实现了独占方式的锁及用到了condition
  

#### AQS 相关方法处理说明
##### 获取锁的框架方法
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/aqsblocklock.jpg)
```
 // 尝试获取锁
 public final void acquire(int arg) {
     // tryAcquire 需重写   acquireQueued、addWaiter为默认方法
    if (!tryAcquire(arg) &&
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
 }
```

- addWaiter ：用于添加节点到队尾
  - 如果队尾节点存在直接CAS添加
  - 如果队尾节点不存在，使用for自旋先添加空的头节点，再添加当前线程的队尾节点
  
```
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
            final Node p = node.predecessor();
            // 前驱节点就是头结点直接尝试获得锁。
            // 在正常的锁释放唤醒场景，默认唤醒头结点后的第一个节点，就是在这边获取锁。
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
            // 常规尾结点添加 修改前驱节点状态以及进入挂起状态
            // 如果前驱节点为取消状态，需调整队列，将取消节点排除在外
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        // 自定义的tryAcquire 抛出异常则执行释放逻辑 
        if (failed)
            cancelAcquire(node);
    }
```
- acquireQueued ：CLH节点休眠与被唤醒后的主要处理逻辑
  - 进入一段自旋
  - 节点正常添加到队尾后，如果当前节点的前驱为头节点，使用CAS尝试获取。获取成功后设置当前节点为头结点。之前的头节点让GC回收
  - 获取失败，则进入shouldParkAfterFailedAcquire方法。
  
- shouldParkAfterFailedAcquire方法：
  - 正常的尾节点添加，需要使用CAS先把前驱节点的状态变成signal，通过acquireQueued的自旋，再进入到挂起的状态。
  - 若前驱节点声明为取消CANCELLED状态，则需要找到非CANCELLED的前驱节点并连接上，取消的节点排除在双链表外。
  
- parkAndCheckInterrupt方法：正常尾结点添加完成之后，进入到挂起的逻辑。
##### 释放锁的框架方法
```
// 释放锁
public final boolean release(int arg) {
    // tryRelease 需重写
    if (tryRelease(arg)) {
        Node h = head;
        if (h != null && h.waitStatus != 0)
            unparkSuccessor(h);
        return true;
    }
    return false;
}
```
- tryRelease方法：需要自定义，正常这步已经把锁给释放了，即修改状态为0，资源当前线程为null
- unparkSuccessor：资源释放后的队列抢资源逻辑
  - 定位到头结点，CAS修改状态为0
  - 从尾到头寻找最早一个需要唤醒的线程节点，独占锁模式正常定位到头结点的下一个节点。
  - 执行唤醒逻辑，线程重新进入到acquireQueued
  
  
#### 条件队列
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/conditionqueue.jpg)
- condition：条件队列的实现，常可以用在生产者-消费者的场景中。
  - condition 的方法主要就两个await等待、signal唤醒
- 条件队列与阻塞队列
  - 阻塞队列为Lock中获取共享资源的CLH阻塞双向链表队列
  - 条件队列为conditionObject中维护的一个单向链表
- AbstractQueueSynchronizer中的Node节点，分别代表上述两数据结构的节点

- 条件队列流程简叙： 
    1. 生产者condition，首先需要获取当前锁，调用await方法。
    2. await方法中会创建condition state 的node 节点，加入到condition的条件单向链表中。
    3. 释放当前持有的锁资源，进入循环判断节点是否进入到AQS阻塞队列中。没有则挂起等待唤醒。
    4. 若有另一个线程唤醒了生产者，唤醒signal的逻辑中会把唤醒的节点，从当前条件队列中移除并添加到阻塞队列中
    5. await的线程被唤醒后，进入阻塞队列的队列获取锁逻辑，如果前驱节点为头结点，尝试去获取锁资源，否则修改前驱节点为带唤醒状态。等待锁资源释放后的调用。
- 注意点：在条件队列中，等待的线程节点，即使发生了中断，节点依然会转移到阻塞队列。主要通过是唤醒之后，执行检查中断状态为true的transferAfterCancelledWait实现。
  - 这里描绘了一个场景，本来有个线程，它是排在条件队列的后面的，但是因为它被中断了，那么它会被唤醒，然后它发现自己不是被 signal 的那个，但是它会自己主动去进入到阻塞队列。
  - 全部加入阻塞队列以及使用状态表示，主要用于区分节点是，是在发生中断sign前还是sign后。
```
static final class Node {
        static final Node SHARED = new Node();
        static final Node EXCLUSIVE = null;

        static final int CANCELLED =  1;
        // 阻塞队列中带唤醒的状态
        static final int SIGNAL    = -1;
        // 代表节点为条件队列节点
        static final int CONDITION = -2;

        static final int PROPAGATE = -3;

        volatile int waitStatus;

        // 双向链表的实现
        volatile Node prev;
        volatile Node next;

        volatile Thread thread;

        //  条件队列的实现
        Node nextWaiter;
... 
}
```
##### await方法
- interruptMode 中断状态：
    - REINTERRUPT(1)： 代表 await 返回的时候，需要重新设置中断状态
    - THROW_IE(-1)： 代表 await 返回的时候，需要抛出 InterruptedException 异常
    - 0 ：说明在 await 期间，没有发生中断
```
   public final void await() throws InterruptedException {
        // 检查当前线程是不是中断
        if (Thread.interrupted())
            throw new InterruptedException();
        // 添加节点到条件队列结尾，单链表结尾。（里面涉及一些链表添加逻辑）
        // 节点直接设置为condition节点
        Node node = addConditionWaiter();
        // 释放当前的共享资源锁，如果不是获取到锁的线程就会抛IllegalMonitorStateException
        // 这里的释放是把状态直接改成0
        int savedState = fullyRelease(node);
        int interruptMode = 0;
        // 判断当前节点在上述操作期间是不是已经被唤醒进入到了 阻塞队列
        while (!isOnSyncQueue(node)) {
            // 线程挂起
            LockSupport.park(this);
            // 此处唤醒有几种情况
            // 1. 常规路径。signal -> 转移节点到阻塞队列 -> 获取了锁（unpark）
            // 2. signal 的时候我们说过，转移以后的前驱节点取消了，或者对前驱节点的CAS操作失败了
            // 3. 假唤醒。这个也是存在的，和 Object.wait() 类似
            // 4. 线程中断。在 park 的时候，另外一个线程对这个线程进行了中断
            // checkInterruptWhileWaiting：根据中断状态，设定对应的interruptMode，没有直接抛出异常
            if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                break;
        }
        // 对于前驱节点cancel的情况，调整链表排除cancel，并等待唤醒
        // 对于常规唤醒，进行锁争抢，找不到就挂起
        if (acquireQueued(node, savedState) && interruptMode != THROW_IE)
            interruptMode = REINTERRUPT;
        // Node节点抢占到资源之后，清理单链表的节点情况
        // 此处适用于在sign前发生异常的场景
        if (node.nextWaiter != null) // clean up if cancelled
            unlinkCancelledWaiters();
        // 根据中断模式，若需要重置状态或者抛出异常，都在这边处理
        if (interruptMode != 0)
            reportInterruptAfterWait(interruptMode);
    }
```

#### signal方法
```
public final void signal() {
     // 自定义方法，主要用于判断是否获取到监听器对象，如果没有抛出IllegalMonitorStateException
    if (!isHeldExclusively())
        throw new IllegalMonitorStateException();
    Node first = firstWaiter;
    if (first != null)
        doSignal(first);
}

  private void doSignal(Node first) {
    do {
        // 判断是不是唯一节点
        if ( (firstWaiter = first.nextWaiter) == null)
            lastWaiter = null;
        // 当前节点要进入阻塞队列了，断开条件队列连接
        first.nextWaiter = null;
    } while (!transferForSignal(first) &&
             (first = firstWaiter) != null);
}

// 唤醒节点的主要操作
 final boolean transferForSignal(Node node) {
    // 如果当前节点不是条件节点，说明已经被唤醒了，返回上层调用下一个条件队列节点
    if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
        return false;

    // 正常修改状态，节点加入阻塞队列，返回前驱节点
    Node p = enq(node);
    int ws = p.waitStatus;
    // 前驱节点的状态为取消，或者不是队尾0的状态唤醒当前节点
    if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
        LockSupport.unpark(node.thread);
    return true;
}
```

### ReentrantLock
- 定义基于AQS实现独占锁及condition
- Sync 抽象静态父类提供基础的一些实现。
- 公平锁 FairSync：尝试获取资源时候，均要查看是否有等待队列，若存在等待队列则不尝试获取锁，直接添加到队列里面。实现共享资源按顺序获取。
- 非公平锁 NonfairSync：当线程要获取锁时，先通过两次 CAS 操作去抢锁，如果没抢到，当前线程再加入到队列中等待唤醒。
  - 非公平锁会有更好的性能，因为它的吞吐量比较大。当然，非公平锁让获取锁的时间变得更加不确定，可能会导致在阻塞队列中的线程长期处于饥饿状态。

- AQS独占锁获取流程简述：
  - 非公平锁:获取资源会先直接CAS去获取，没获取到进入acquire模板逻辑，若发现此时状态为0，会再进行一次CAS获取。
  - 公平锁获取资源，会先检查是否有CLH队列存在，直接返回，不进行获取的逻辑。
  - 以下共同逻辑，首先自旋+CAS操作添加当前线程节点到队尾。（包括初始化逻辑）。
  - 接着判断前驱节点是否为头结点，如果为头结点就尝试一次获取。否则就修改前驱节点的状态为signal待唤醒状态，并挂起线程。
  - 当有其他线程唤醒时，同样判断前驱是否为头结点，为头节点才获取。获取成功后，修改自身为头结点。脱离双向链表结构。
  - unlock逻辑：状态减1，当状态为零时，释放共享资源。CAS替换头结点状态为0，从尾到头找第一个状态<0的节点，唤醒线程。
  
### countdownLatch
- 实现了AQS的共享锁，初始化的时候设置了AQS的state的数量
- await实际调用AQS的acquireShared模板方法
  - 如果state为0，表示数全部被countdown了，不阻塞方法。
  - 数量不为0，新建的Node节点添加CLH队列中。更新前缀节点为-1。
- countdown使用自旋加CAS更新状态，状态为0时，更新等待队列头结点为0，唤醒头结点后的第一个待唤醒节点。
  - 唤醒后的节点自己设置为头节点，更新状态为0，并依次唤醒后序节点。若已唤醒其他节点更新状态为propagate。
  
### CyclicBarrier
- 内部使用ReentrantLock 非公平锁，每次await时，加锁扣减数量，使用condition的await等待唤醒。
  - 数量扣减为0时，如果有定义栅栏开始的方法则执行，并调用condition的signAll,条件单链表逐个唤醒。
  - generation 代表栅栏重复使用的一代或者一个周期。
- 什么时候栅栏会被打破，总结如下：
  - 中断，我们说了，如果某个等待的线程发生了中断，那么会打破栅栏，同时抛出 InterruptedException 异常；
  - 超时，打破栅栏，同时抛出 TimeoutException 异常；
  - 指定执行的操作抛出了异常。
 
### Semaphore 
- 基于AQS区分公平锁与非公平锁
- acquire：获取锁CAS+ 自旋 获取锁，如果发现资源为0，进入队列等待。
- release：自旋+CAS释放锁，如果释放成功，唤醒队列的节点起来获取。

### ReentrantReadWriteLock
- 内部分别定义了读锁与写锁。
- 读锁共享锁实现。
- 写锁独占锁实现。