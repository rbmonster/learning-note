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
  
### ReentrantLock
- 定义基于AQS实现独占锁及condition

- 公平锁：尝试获取资源时候，均要查看是否有等待队列，若存在等待队列则不尝试获取锁，直接添加到队列里面。实现共享资源按顺序获取。
- 非公平锁：当线程要获取锁时，先通过两次 CAS 操作去抢锁，如果没抢到，当前线程再加入到队列中等待唤醒。