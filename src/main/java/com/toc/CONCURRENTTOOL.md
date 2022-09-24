<a name="index">**Index**</a>

<a href="#0">Java并发相关工具类</a>  
&emsp;<a href="#1">1. AQS 相关</a>  
&emsp;&emsp;<a href="#2">1.1. AbstractQueuedSynchronizer AQS</a>  
&emsp;&emsp;&emsp;<a href="#3">1.1.1. AQS 相关方法</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#4">1.1.1.1. 获取锁的框架方法</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#5">1.1.1.2. 释放锁的框架方法</a>  
&emsp;&emsp;&emsp;<a href="#6">1.1.2. 条件队列</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#7">1.1.2.1. await方法</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#8">1.1.2.2. signal方法</a>  
&emsp;&emsp;<a href="#9">1.2. ReentrantLock</a>  
&emsp;&emsp;&emsp;<a href="#10">1.2.1. 非公平锁</a>  
&emsp;&emsp;&emsp;<a href="#11">1.2.2. 公平锁</a>  
&emsp;&emsp;<a href="#12">1.3. CountdownLatch</a>  
&emsp;&emsp;<a href="#13">1.4. CyclicBarrier</a>  
&emsp;&emsp;<a href="#14">1.5. Semaphore</a>  
&emsp;&emsp;<a href="#15">1.6. ReentrantReadWriteLock</a>  
&emsp;&emsp;<a href="#16">1.7. 独占锁与共享锁</a>  
&emsp;&emsp;<a href="#17">1.8. 线程池中的Worker(独占锁实现)</a>  
&emsp;<a href="#18">2. Atomic 原子类</a>  
&emsp;&emsp;<a href="#19">2.1. 原子类型</a>  
&emsp;&emsp;<a href="#20">2.2. 数组类型</a>  
&emsp;&emsp;<a href="#21">2.3. 引用类型</a>  
&emsp;&emsp;<a href="#22">2.4. 对象的属性修改类型</a>  
&emsp;<a href="#23">3. 线程安全集合</a>  
&emsp;&emsp;<a href="#24">3.1. blockingQueue</a>  
&emsp;&emsp;&emsp;<a href="#25">3.1.1. ArrayBlockingQueue</a>  
&emsp;&emsp;&emsp;<a href="#26">3.1.2. LinkedBlockingQueue</a>  
&emsp;&emsp;&emsp;<a href="#27">3.1.3. PriorityBlockingQueue</a>  
&emsp;&emsp;&emsp;<a href="#28">3.1.4. SynchronousQueue</a>  
&emsp;&emsp;<a href="#29">3.2. DelayQueue</a>  
&emsp;<a href="#30">4. 相关资料</a>  
# <a name="0">Java并发相关工具类</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">AQS 相关</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="2">AbstractQueuedSynchronizer AQS</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

AQS 是一个用来构建锁和同步器的框架，使用 AQS 能简单且高效地构造出应用广泛的大量的同步器。

核心工作流程：

1. 使用volatile修饰的statue变量表示共享资源的状态。如果被请求的共享资源空闲，则将当前请求资源的线程设置为有效的工作线程(`Thread exclusiveOwnerThread`)，并且将共享资源设置为锁定状态。
2. 如果被请求的共享资源被占用，那么就需要一套线程阻塞等待以及被唤醒时锁分配的机制，这个机制 AQS 是用 CLH 队列锁实现的，即将暂时获取不到锁的线程加入到队列中。
3. `CLH(Craig,Landin,and Hagersten)`队列是一个虚拟的双向队列（虚拟的双向队列即不存在队列实例，仅存在结点之间的关联关系）
4. 通过自旋+CAS获取共享资源，如果获取失败则调用调用 `native` 方法 进入 `park` 状态。

```text
/** waitStatus value to indicate thread has cancelled */
static final int CANCELLED =  1;
/** waitStatus value to indicate successor's thread needs unparking */
static final int SIGNAL    = -1;
/** waitStatus value to indicate thread is waiting on condition */
static final int CONDITION = -2;
/**
 * waitStatus value to indicate the next acquireShared should
 * unconditionally propagate
 */
static final int PROPAGATE = -3;
```

使用模板模式提供了 锁与同步的框架，将一些实现延迟到子类中实现。 需要具体子类实现的方法：

```text
isHeldExclusively()//该线程是否正在独占资源。只有用到condition才需要去实现它。

tryAcquire(int)//独占方式。尝试获取资源，成功则返回true，失败则返回false。

tryRelease(int)//独占方式。尝试释放资源，成功则返回true，失败则返回false。

tryAcquireShared(int)//共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余可用资源；正数表示成功，且有剩余资源。

tryReleaseShared(int)//共享方式。尝试释放资源，成功则返回true，失败则返回false。
```

比如独占锁相关的方法调用框架

```java
public class AbstractQueuedSynchronizer {
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
}
```

比如ReentrantLock 实现了独占方式的锁及用到了condition

#### <a name="3">AQS 相关方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

##### <a name="4">获取锁的框架方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/basic/aqsblocklock.jpg)

```java
public class AbstractQueuedSynchronizer {
    // 尝试获取锁
    public final void acquire(int arg) {
        // tryAcquire 需重写   acquireQueued、addWaiter为默认方法
        if (!tryAcquire(arg) &&
                acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }
}
```

addWaiter：用于添加节点到队尾

- 如果队尾节点存在直接CAS添加
- 如果队尾节点不存在，使用for自旋先**添加空的头节点**，再添加当前线程的队尾节点

```java
public class AbstractQueuedSynchronizer {

    final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (; ; ) {
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
    }
}
```

`acquireQueued` ：CLH节点休眠与被唤醒后的主要处理逻辑

1. 进入一段自旋
2. 节点正常添加到队尾后，如果当前节点的前驱为头节点，使用CAS尝试获取。获取成功后设置当前节点为头结点。之前的头节点让GC回收
3. 获取失败，则进入shouldParkAfterFailedAcquire方法。

`shouldParkAfterFailedAcquire` 方法：

1. 正常的尾节点添加，需要使用CAS先把前驱节点的状态变成signal，通过acquireQueued的自旋，再进入到挂起的状态。
2. 若前驱节点声明为取消CANCELLED状态，则需要找到非CANCELLED的前驱节点并连接上，取消的节点排除在双链表外。

parkAndCheckInterrupt方法：正常尾结点添加完成之后，进入到挂起的逻辑。

##### <a name="5">释放锁的框架方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

```java

public class AbstractQueuedSynchronizer {
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
}
```

tryRelease方法：需要自定义，正常这步已经把锁给释放了，即修改状态为0，资源当前线程为null

unparkSuccessor：资源释放后的队列抢资源逻辑

1. 定位到头结点，CAS修改状态为0
2. 从尾到头寻找最早一个需要唤醒的线程节点，独占锁模式正常定位到头结点的下一个节点。
3. 执行唤醒逻辑，线程重新进入到acquireQueued

#### <a name="6">条件队列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/basic/conditionqueue.jpg)
condition：条件队列的实现，常可以用在生产者-消费者的场景中。在所对象中内置一个newCondition方法，用于创建一个条件队列。\
condition 的方法主要就两个`await`**等待**、`signal`**唤醒**

条件队列与阻塞队列
- **阻塞队列为Lock中获取共享资源的CLH阻塞双向链表队列**，AQS中设置head和tail变量。
- 条件队列为**conditionObject中维护的一个单向链表**

```java
  public class ConditionObject implements Condition, java.io.Serializable {
    private static final long serialVersionUID = 1173984872572414699L;
    /** First node of condition queue. */
    private transient Node firstWaiter;
    /** Last node of condition queue. */
    private transient Node lastWaiter;
//      ...
}
```

> AbstractQueueSynchronizer中的Node节点，分别代表上述两数据结构的节点


条件队列流程简叙：

1. 生产者condition，首先需要获取当前锁，调用await方法。
2. await方法中会创建**condition state** 的node 节点，加入到condition的条件单向链表中。
3. 释放当前持有的锁资源，进入循环判断节点是否进入到AQS阻塞队列中。没有则挂起等待唤醒。
4. 若有另一个线程唤醒了生产者，唤醒signal的逻辑中会把唤醒的节点，从当前条件队列中移除并添加到阻塞队列中
5. await的线程被唤醒后，进入阻塞队列的队列获取锁逻辑，如果前驱节点为头结点，尝试去获取锁资源，否则修改前驱节点为带唤醒状态。等待锁资源释放后的调用。

> 注意点：在条件队列中，等待的线程节点，即使发生了中断，节点依然会转移到阻塞队列。主要通过是唤醒之后，执行检查中断状态为true的transferAfterCancelledWait实现。\
> 这里描绘了一个场景，本来有个线程，它是排在条件队列的后面的，但是因为它被中断了，那么它会被唤醒，然后它发现自己不是被 signal 的那个，但是它会自己主动去进入到阻塞队列。

```java
static final class Node {
    static final Node SHARED = new Node();
    static final Node EXCLUSIVE = null;

    static final int CANCELLED = 1;
    // 阻塞队列中带唤醒的状态
    static final int SIGNAL = -1;
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
//... 
}
```

##### <a name="7">await方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

interruptMode 中断状态：

- `REINTERRUPT(1)`： 代表 await 返回的时候，需要重新设置中断状态
- `THROW_IE(-1)`： 代表 await 返回的时候，需要抛出 InterruptedException 异常
- `0` ：说明在 await 期间，没有发生中断

源码实现
```java
public class AbstractQueuedSynchronizer {

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
}
```

##### <a name="8">signal方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
源码实现
```java
public class AbstractQueuedSynchronizer {

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
            if ((firstWaiter = first.nextWaiter) == null)
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
}
```

### <a name="9">ReentrantLock</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

定义基于AQS实现独占锁及condition。ReentrantLock定义了抽象静态父类Sync提供基础的一些实现。

- 公平锁 `FairSync`：尝试获取资源时候，均要查看是否有等待队列，若存在等待队列则不尝试获取锁，直接添加到队列里面。实现共享资源按顺序获取。
- 非公平锁 `NonfairSync`：当线程要获取锁时，先通过两次 CAS 操作去抢锁，如果没抢到，当前线程再加入到队列中等待唤醒。
  > 非公平锁会有更好的性能，因为它的吞吐量比较大。当然，非公平锁让获取锁的时间变得更加不确定，可能会导致在阻塞队列中的线程长期处于饥饿状态。

AQS独占锁获取流程简述：

1. 非公平锁: 获取资源会先直接CAS去获取，没获取到进入acquire模板逻辑，若发现此时状态为0，会再进行一次CAS获取。
2. 公平锁: 获取资源，会先检查是否有CLH队列存在，直接返回，不进行获取的逻辑。

以下共同逻辑

1. 首先自旋+CAS操作添加当前线程节点到队尾。（包括初始化逻辑）。
2. 接着判断前驱节点是否为头结点，如果为头结点就尝试一次获取。否则就修改前驱节点的状态为signal待唤醒状态，并挂起线程。
3. 当有其他线程唤醒时，同样判断前驱是否为头结点，为头节点才获取。获取成功后，修改自身为头结点。脱离双向链表结构。
4. unlock逻辑：状态减1，当状态为零时，释放共享资源。CAS替换头结点状态为0，从尾到头找第一个状态<0的节点，唤醒线程。

#### <a name="10">非公平锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
源码实现
```java
public class ReentrantLock {
    final void lock() {
        // 调用时候先CAS获取锁
        if (compareAndSetState(0, 1))
            setExclusiveOwnerThread(Thread.currentThread());
        else
            acquire(1);
    }

    protected final boolean tryAcquire(int acquires) {
        return nonfairTryAcquire(acquires);
    }

    final boolean nonfairTryAcquire(int acquires) {
        final Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            // 如果发现状态为零，马上又去CAS抢资源
            if (compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        } else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            if (nextc < 0) // overflow
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }
}
```

#### <a name="11">公平锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

```java
public class ReentrantLock {
    protected final boolean tryAcquire(int acquires) {
        final Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            // 这边存在队就加入队列
            if (!hasQueuedPredecessors() &&
                    compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        } else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            if (nextc < 0)
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }
}
```

### <a name="12">CountdownLatch</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

定义：实现了AQS的共享锁，初始化的时候设置了AQS的state的数量。主要方法是await 和 countdown方法

`await`实际调用AQS的acquireShared模板方法

- 如果state为0，表示数全部被countdown了，不阻塞方法。
- 数量不为0，新建的Node节点添加CLH队列中，更新前缀节点为-1。

`countdown()` 方法：CAS+自旋扣减statue状态。当状态为0时，唤醒await等待的线程。

- countdown 使用自旋加CAS更新状态，状态为0时，更新等待队列头结点为0，唤醒头结点后的第一个待唤醒节点。
- 唤醒后的节点自己设置为头节点，更新状态为0，并依次唤醒后序节点。

countDown **Demo**:

```java
public class TestCountdownLatch {
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": countDown complete");
        };

        new Thread(runnable).start();
        new Thread(runnable).start();

        TimeUnit.SECONDS.sleep(1);
        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.await();
    }
}

```

### <a name="13">CyclicBarrier</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

CyclicBarrier (可重复使用的栅栏): 内部使用ReentrantLock 非公平锁，每次await时，加锁扣减数量，使用condition的await等待唤醒。CountDownLatch 基于 AQS 的共享模式的使用，而 CyclicBarrier 基于 Condition 来实现。
> 数量扣减为0时，如果有定义栅栏开始的方法则执行，并调用condition的signAll，条件单链表逐个唤醒。 generation 代表栅栏重复使用的一代或者一个周期。

![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/basic/cyclicBarrier.jpg)

什么时候栅栏会被打破，总结如下：

- 中断，如果某个等待的线程发生了中断，那么会打破栅栏，同时抛出 InterruptedException 异常；
- 超时，打破栅栏，同时抛出 TimeoutException 异常；
- 指定执行的操作抛出了异常。

源码实现：
```java
public class CyclicBarrier {
    public int await() throws InterruptedException, BrokenBarrierException {
        try {
            return dowait(false, 0L);
        } catch (TimeoutException toe) {
            throw new Error(toe); // cannot happen
        }
    }

    private int dowait(boolean timed, long nanos)
            throws InterruptedException, BrokenBarrierException,
            TimeoutException {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            final Generation g = generation;
            // index 是这个 await 方法的返回值
            int index = --count;
            // 如果等于 0，说明所有的线程都到栅栏上了，准备通过
            if (index == 0) {  // tripped
                boolean ranAction = false;
                try {
                    // 如果在初始化的时候，指定了通过栅栏前需要执行的操作，在这里会得到执行
                    final Runnable command = barrierCommand;
                    if (command != null)
                        command.run();
                    // 如果 ranAction 为 true，说明执行 command.run() 的时候，没有发生异常退出的情况
                    ranAction = true;
                    // !!!!!! 唤醒等待的线程，然后开启新的一代 
                    nextGeneration();
                    return 0;
                } finally {
                    if (!ranAction)
                        // 进到这里，说明执行指定操作的时候，发生了异常，那么需要打破栅栏
                        // 之前我们说了，打破栅栏意味着唤醒所有等待的线程，设置 broken 为 true，重置 count 为 parties
                        breakBarrier();
                }
            }

            // 如果是最后一个线程调用 await，那么上面就返回了
            // 下面的操作是给那些不是最后一个到达栅栏的线程执行的
            for (; ; ) {
                try {
                    // 如果带有超时机制，  调用带超时的 Condition 的 await 方法等待，直到最后一个线程调用 await
                    if (!timed)
                        trip.await();
                    else if (nanos > 0L)
                        nanos = trip.awaitNanos(nanos);
                } catch (InterruptedException ie) {
                    // 如果到这里，说明等待的线程在 await（是 Condition 的 await）的时候被中断
                    if (g == generation && !g.broken) {
                        breakBarrier();
                        throw ie;
                    } else {
                        // We're about to finish waiting even if we had not
                        // been interrupted, so this interrupt is deemed to
                        // "belong" to subsequent execution.
                        Thread.currentThread().interrupt();
                    }
                }

                //              ...
            }
        } finally {
            lock.unlock();
        }
    }
}
```

CyclicBarrier **Demo**

```java

public class TestCyclicBarrier {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> log.info("barrier on going"));

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            try {
                log.info("{} cyclicBarrier await, parties:{}, waitingNumber:{}", name, cyclicBarrier.getParties(), cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            log.info("{} : start to work", name);
        };

        workProcess(runnable);
        TimeUnit.SECONDS.sleep(2);
        log.info("=============  next loop ===========");
        cyclicBarrier.reset();
        workProcess(runnable);

    }

    private static void workProcess(Runnable runnable) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            new Thread(runnable).start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

// output ~
```

### <a name="14">Semaphore</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

基于AQS区分公平锁与非公平锁

- acquire：获取锁CAS+ 自旋 获取锁，如果发现资源为0，进入队列等待。
- release：自旋+CAS释放锁，如果释放成功，唤醒队列的节点起来获取。

```text
public Semaphore(int permits, boolean fair) {
    sync = fair ? new FairSync(permits) : new NonfairSync(permits);
}
```

Semaphore Demo
```java
@Slf4j
public class TestSemaphore {

    private static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        createThread1();
        createThread2();
        createThread3();
    }

    private static void createThread1() {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    semaphore.acquire(2);
                    log.info("AAA-"+semaphore.availablePermits());
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(2);
                }
            }
        }).start();
    }

    private static void createThread2() {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    semaphore.acquire();
                    log.info("BBB-"+ semaphore.availablePermits());
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }
        }).start();
    }

    private static void createThread3() {
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    semaphore.acquire(3);
                    log.info("CCC-"+ semaphore.availablePermits());
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(3);
                }
            }
        }).start();
    }
}
```

### <a name="15">ReentrantReadWriteLock</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

内部分别定义了读锁与写锁。

**读锁共享锁实现**。若当前头结点为状态0，则更新状态为propagate，保证共享锁的传播特性。
```java
public class ReentrantReadWriteLock {
    //  private void doAcquireShared() // 获取共享锁方法
    protected final int tryAcquireShared(int unused) {

        Thread current = Thread.currentThread();
        int c = getState();
        if (exclusiveCount(c) != 0 &&
                getExclusiveOwnerThread() != current)
            return -1;
        int r = sharedCount(c);
        if (!readerShouldBlock() &&
                r < MAX_COUNT &&
                // 设置最大共享线程数为  SHARED_UNIT    = (1 << 16)  65,536
                compareAndSetState(c, c + SHARED_UNIT)) {
        }
        //    ...
    }
}
```

- 写锁独占锁实现。

### <a name="16">独占锁与共享锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

独占锁：独占锁模式下，每次只能有一个线程能持有锁， ReentrantLock 就是以独占方式实现的互斥锁。
> 独占锁是一种悲观保守的加锁策略，它避免了读/读冲突，如果某个只读线程获取锁，则其他读线程都只能等待，这种情况下就限制了不必要的并发性，因为读操作并不会影响数据的一致性。

共享锁则允许多个线程同时获取锁，并发访问 共享资源，如： ReadWriteLock。 共享锁则是一种乐观锁，它放宽了加锁策略，允许多个执行读操作的线程同时访问共享资源。

1. AQS 的内部类 Node 定义了两个常量 SHARED 和 EXCLUSIVE，他们分别标识 AQS 队列中等待线程的锁获取模式。
2. java 的并发包中提供了 ReadWriteLock，读-写锁。它允许一个资源可以被多个读操作访问，或者被一个 写操作访问，但两者不能同时进行。

### <a name="17">线程池中的Worker(独占锁实现)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

详见Java线程章节

## <a name="18">Atomic 原子类</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

原子类主要基于CAS操作实现，同时使用 volatile 保证可见性。

### <a name="19">原子类型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

使用原子的方式更新基本类型

- `AtomicInteger`：整型原子类
- `AtomicLong`：长整型原子类
- `AtomicBoolean` ：布尔型原子类

`AtomicInteger` 类常用方法

```text
public final int get() //获取当前的值

public final int getAndSet(int newValue)//获取当前的值，并设置新的值

public final int getAndIncrement()//获取当前的值，并自增

public final int getAndDecrement() //获取当前的值，并自减

public final int getAndAdd(int delta) //获取当前的值，并加上预期的值

boolean compareAndSet(int expect, int update) //如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update）,添加失败返回false

public final void lazySet(int newValue)//最终设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
```

### <a name="20">数组类型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

数组类型:使用原子的方式更新数组里的某个元素

- `AtomicIntegerArray`：整型数组原子类
- `AtomicLongArray`：长整型数组原子类
- `AtomicReferenceArray` ：引用类型数组原子类

`AtomicIntegerArray` 类常用方法

```text
public final int get(int i) //获取 index=i 位置元素的值

public final int getAndSet(int i, int newValue)//返回 index=i 位置的当前的值，并将其设置为新值：newValue

public final int getAndIncrement(int i)//获取 index=i 位置元素的值，并让该位置的元素自增

public final int getAndDecrement(int i) //获取 index=i 位置元素的值，并让该位置的元素自减

public final int getAndAdd(int i, int delta) //获取 index=i 位置元素的值，并加上预期的值

boolean compareAndSet(int i, int expect, int update) //如果输入的数值等于预期值，则以原子方式将 index=i 位置的元素值设置为输入值（update）

public final void lazySet(int i, int newValue)//最终 将index=i 位置的元素设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
```

### <a name="21">引用类型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

引用类型

- `AtomicReference`：引用类型原子类
- `AtomicMarkableReference`：原子更新带有标记的引用类型。该类将 boolean 标记与引用关联起来，也可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。
- `AtomicStampedReference` ：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，可用于解决原子的更新数据和数据的版本号，可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。

AtomicReference 类使用示例

```java
public class Solution {
    public void test() {
        AtomicReference<Person> ar = new AtomicReference<Person>();
        Person person = new Person("SnailClimb", 22);
        ar.set(person);
        Person updatePerson = new Person("Daisy", 20);
        ar.compareAndSet(person, updatePerson);

        System.out.println(ar.get().getName());
        System.out.println(ar.get().getAge());
    }
}
```

AtomicStampedReference 类使用示例

```text
// 实例化、取当前值和 stamp 值
final Integer initialRef = 0, initialStamp = 0;
final AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(initialRef, initialStamp);

 // compare and set 操作
final Integer newReference = 666, newStamp = 999;
final boolean casResult = asr.compareAndSet(initialRef, newReference, initialStamp, newStamp);
```

### <a name="22">对象的属性修改类型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

如果需要原子更新某个类里的某个字段时，需要用到对象的属性修改类型原子类。

- `AtomicIntegerFieldUpdater`:原子更新整型字段的更新器
- `AtomicLongFieldUpdater`：原子更新长整型字段的更新器
- `AtomicReferenceFieldUpdater`：原子更新引用类型里的字段

```text
AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

User user = new User("Java", 22);
System.out.println(a.getAndIncrement(user));// 22
```

## <a name="23">线程安全集合</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- `ConcurrentHashMap`: 线程安全的 HashMap
- `CopyOnWriteArrayList`: 线程安全的 List，在读多写少的场合性能非常好，远远好于 Vector.
- `ConcurrentLinkedQueue`: 高效的并发队列，使用链表实现。可以看做一个线程安全的 LinkedList，这是一个非阻塞队列。
- `BlockingQueue`: 这是一个接口，JDK 内部通过链表、数组等方式实现了这个接口。表示阻塞队列，非常适合用于作为数据共享的通道。
- `ConcurrentSkipListMap`: 跳表的实现。这是一个 Map，使用跳表的数据结构进行快速查找。

### <a name="24">blockingQueue</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

阻塞队列方法区分：
- `take()`和`put()`是**阻塞的获取和存储元素**的方法，
- `poll()`和`offer()`是**不阻塞的获取元素和存储元素**的方法，并且poll和offer可以指定超时时间。
- `add()`和`remove()`存取元素，队列满时add抛异常，队列空时remove抛异常

#### <a name="25">ArrayBlockingQueue</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

`ArrayBlockingQueue` 是 BlockingQueue 接口的有界队列实现类，底层采用数组来实现。
`ArrayBlockingQueue` 一旦创建，容量不能改变。其并发控制采用可重入锁来控制，不管是插入操作还是读取操作，都需要获取到锁才能进行操作。

`ArrayBlockingQueue` 默认情况下不能保证线程访问队列的公平性。因为底层使用一个ReentrantLock，因此可以设置公平锁和非公平锁。

#### <a name="26">LinkedBlockingQueue</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

`LinkedBlockingQueue` 底层基于单向链表实现的阻塞队列，可以当做**无界队列也可以当做有界队列**来使用，同样满足 FIFO 的特性。 而 LinkedBlockingQueue
> 之所以能够高效的处理并发数据，还因为其对于生产者端和消费者端分别采用了独立的锁来控制数据同步，这也意味着在高并发的情况下生产者和消费者可以并行地操作队列中的数据，以此来提高整个队列的并发性能。\
> 使用两个ReentrantLock，takeLock和putLock两把锁，分别用于阻塞队列的读写线程，也就是说，读线程和写线程可以同时运行，在多线程高并发场景，应该可以有更高的吞吐量，性能比单锁更高。

#### <a name="27">PriorityBlockingQueue</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

`PriorityBlockingQueue` 是一个支持优先级的无界阻塞队列。默认情况下元素采用自然顺序进行排序，也可以通过自定义类实现 compareTo() 方法来指定元素排序规则，或者初始化时通过构造器参数 `Comparator` 来指定排序规则。

`PriorityBlockingQueue` 并发控制采用的是 `ReentrantLock`，队列为**无界队列**

#### <a name="28">SynchronousQueue</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

`SynchronousQueue` 是一个不存储元素的阻塞队列。每一个 put 操作必须等待一个 take 操作，否则不能继续添加元素。
> 队列本身并不存储任何元素，非常适合于传递性场景,比如在一个线程中使用的数据，传递给另外一个线程使用 ， SynchronousQueue 的吞吐量高于 LinkedBlockingQueue 和ArrayBlockingQueue。

### <a name="29">DelayQueue</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

`DelayQueue` 延迟队列实现使用数据结构使用PriorityQueue，**线程安全协作**使用的是ReentrantLock 与 Condition 条件队列实现。关键的实现在take方法的`available.awaitNanos(delay);`
> 队列中的元素必须是Delayed的实现类\
> 延迟队列：可应用于缓存失效及定时任务中。

`take()` **方法源码**

```java
public class TestDelayQueue {
    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            for ( ; ; ) {
                E first = q.peek();
                if (first == null)     // 一开始队列为空或者队列消费为空
                    available.await();
                else {
                    // 获取队头的延迟元素的等待时间
                    long delay = first.getDelay(NANOSECONDS);
                    if (delay <= 0)
                        return q.poll();   // 自旋方法的返回出口
                    first = null; // don't retain ref while waiting
                    if (leader != null)    // leader
                        available.await();
                    else {
                        Thread thisThread = Thread.currentThread();
                        leader = thisThread;
                        try {
                            // 关键实现 结合一开始获取的等待时间，唤醒使用
                            available.awaitNanos(delay);
                        } finally {
                            if (leader == thisThread)
                                leader = null;
                        }
                    }
                }
            }
        } finally {
            // 当返回元素退出自旋的时候 唤醒条件队列下一个节点
            if (leader == null && q.peek() != null)
                available.signal();
            lock.unlock();
        }
    }
}
```

## <a name="30">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- [Java多线程进阶（三六）—— J.U.C之collections框架：DelayQueue](https://segmentfault.com/a/1190000016388106)
- [一行一行源码分析清楚 AbstractQueuedSynchronizer (三)](https://www.javadoop.com/post/AbstractQueuedSynchronizer-3)
