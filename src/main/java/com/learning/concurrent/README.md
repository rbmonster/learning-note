### 解决共享资源竞争

#### synchronized:
- 在对象上调用其任意的synchronized方法的时候，此对象都会被加锁，此时该对象的其他synchronized方法只有等到前一个方法调用完毕，并释放了锁之后才能被调用。
- 如果一个方法在同一个对象上调用了第二个方法，后者又调用了同一对象上的另一个方法。JVM负责跟踪对象被加锁的次数，在任务第一次给对象加锁的时候，计数变为1，每当这个相同的任务在这个对象上获得锁时，技术都会增加，每当任务离开一个synchronized的方法，计数递减。当计数为0时，锁完全释放。（前提是首先获得了锁的任务才能允许继续获取多个锁）
- 针对每个类，也有一个锁（作为类的Class对象的一部分），所以synchronized static 方法可以在类的范围内防止对static数据的并发访问。
- 对比synchronized 同步方法和同步控制块的效率，同步控制块对象不加锁的时间更长，效率更高。

#### lock对象 
- ReentrantLock 允许尝试着获取但最终未获取锁。
- 相比于synchronized锁，lock对象可以做到更细粒度的控制力。

#### 原子性
- 原子操作是不能被线程调度机制中断的操作，一旦操作开始，那么它一定可以在可能发生的，线程上下文切换操作之前执行完毕。
- 原子性可以应用于除long和double之外的所有基本类型之上的基本操作。
- 因为JVM可以将64位（long和double变量）的读取和写入当做两个分离的32位操作来执行，这就产生了在一个读取和写入操作中间发生上下文切换，从而导致结果有误。当然加上volatile，就能获得原子性。
- java中i++操作不是原子性的。具体看如下JVM指令结果
```
public class Atomicity {
  int i;
  void f1() { i++; }
  void f2() { i += 3; }
} /* Output: (Sample)
...
void f1();
  Code:
   0:        aload_0
   1:        dup
   2:        getfield        #2; //Field i:I
   5:        iconst_1
   6:        iadd
   7:        putfield        #2; //Field i:I
   10:        return

void f2();
  Code:
   0:        aload_0
   1:        dup
   2:        getfield        #2; //Field i:I
   5:        iconst_3
   6:        iadd
   7:        putfield        #2; //Field i:I
   10:        return
*///:~
```

### 可见性
- 一个任务作出修改，对其他任务可能是不可视的。
- volatile关键字确保了应用中的可见性。如果一个域被声明为volatile，那么只要对这个域进行了写操作，其他所有的读操作均可以看到这个修改。即便使用了本地缓存，情况也一样，volatile域会立即被写入到主存中，而读取操作就发生在主存中。
- 将一个域标记为volatile，将告诉编译器，不需要执行任务移除和写入操作的优化。
- Collections.synchronizedCollection() 返回同步的集合类，即方法均用对象锁。
- java内存可见性的实现，读取从主内存刷新，修改同步主内存。
- synchronize和finial可以实现可见性。

### 有序性
- 定义：本线程中，执行结果一致，但其他线程观察其过程是无序的。指的是“指令重排序”和“工作内存与主内存同步延迟”的现象。

#### ThreadLocal
- 通常当做静态域存储，因为ThreadLocal为每个线程都分配了自己的空间。使用当前线程作为key，因此不会出现线程冲突。
- 注意不同线程在ThreadLocal存储的值为同一个对象的情况，由于引用都是同一个对象，因此会出现线程问题。

#### 退出线程的方法
- 与synchronized相关
1. 线程中使用一个静态的volatile的标志判断退出。
2. 调用Executors的submit方法，获取线程上下文对象Future，调用cancel方法。（注：无法中断正在试图获取synchronized锁或者试图执行I/O操作的线程）IO的中断，关闭底层资源之后，任务将解除阻塞。如socket连接，调用socket的close 或者 system.in 的输入连接调用in.close().
3. 调用ExecutorService的shutdown的方法。
4. 通过检查中断状态Thread.interrupted()，主动调用Thread的interrupt方法实现。（注意处理的时候，如果线程已经调用了interrupt()，如果再调用sleep方法，将抛出interruptException的异常）
- ReentrantLock调用锁的lockInterruptibly()方法，
- 1）lock(), 拿不到lock就不罢休，不然线程就一直block。 比较无赖的做法。
- 2）tryLock()，马上返回，拿到lock就返回true，不然返回false。 比较潇洒的做法。
  带时间限制的tryLock()，拿不到lock，就等一段时间，超时返回false。比较聪明的做法。
- 3）lockInterruptibly()就稍微难理解一些。
  先说说线程的打扰机制，每个线程都有一个 打扰 标志。这里分两种情况，
  - 线程在sleep或wait,join， 此时如果别的进程调用此进程的 interrupt（）方法，此线程会被唤醒并被要求处理InterruptedException；(thread在做IO操作时也可能有类似行为，见java thread api)
  - 此线程在运行中， 则不会收到提醒。但是 此线程的 “打扰标志”会被设置， 可以通过isInterrupted()查看并 作出处理。
  - 结论：lockInterruptibly()和上面的第一种情况是一样的， 线程在请求lock并被阻塞时，如果被interrupt，则“此线程会被唤醒并被要求处理InterruptedException”。并且如果线程已经被interrupt，再使用lockInterruptibly的时候，此线程也会被要求处理interruptedException
 
### daemon Thread 守护线程应用场景
- 典型的应用场景就是java的GC垃圾回收线程
- 平常在系统的应用中，同样可以用于缓存清理这方面的工作
- 固定取消过期订单。

### 线程间协作
1. wait()、notify()以及notifyAll()是基类Object的方法，尽管是线程的操作，放到基类中，便于操作所有对象。而这三个方法只能在==同步方法或者同步控制块中调用==。如果在非同步控制方法调用，程序能通过，但是运行时会得到IllegalMonitorStateException，表示调用该方法必须获得对象锁。 
2. notifyAll()的并不是唤醒所有正在等待的任务。本质是notifyALl()因特定锁而被调用时，只有等待这个锁的任务才会被唤醒。
3. 使用ReentrantLock，通过lock.newCondition()，使用lock的signAll的方法实现线程间的协作
4. 使用BlockingQueue,阻塞队列来实现线程间协作，就可以较少很多繁杂的wait和notify操作。
- 通常可以使用LinkedBlockingQueue一个无届队列，ArrayBlockingQueue一个固定尺寸的队列。SynchronousQueue 只能包含一个元素的队列。
5.管道通信，线程之间通过管道进行连接传输信息。涉及的类PipedWriter和PipedReader，两者需要建立管道连接new PipedReader(sender.getPipedWriter())。

### 死锁产生条件
1.互斥条件：任务使用的资源至少有一个不能共享。
2.至少有一个任务它必须持有一个资源，且正在等待获取当前被别的任务持有的资源。
3.资源不能被任务抢占，必须等待。（资源必须等别的任务使用完成释放）
4.必须有等待循环。（即一个任务等待其他任务的资源，一个循环的等待）

### 其他的一些工具类
- CountDownLatch计时器，主要方法countDown()、await()
- CyclicBarrier 栅栏，主要方法await(), 实例化可指定拦截的数量，和一个栅栏动作，一个自动执行的线程。
- DelayQueue 无界的BlockingQueue，用于放置实现了Delayed接口（在一段时间后只运行一次，其中的对象只能在其到期时才能从队列中取走）的对象
- PriorityBlockingQueue基础的优先级队列，对象必须实现compare接口，用于优先级排序
- ScheduleExecutor定时线程定时服务
- Semaphore 信号量，设计在某个时刻只允许n个任务访问资源，常用方法acquire()、release()
- Exchanger 两任务交换对象的栅栏，执行Exchange方法的时候阻塞，直到交换获得对象。

### 性能对比
- synchronized在经过一定阀值后，需要的开销会出现爆发性的增长。因此在实际的性能调优时，使用Lock替换。
- 由于Atomic对象具有并发的性能优势，在某些场景合适使用时，优先考虑。
- 乐观加锁： Atomic类中使用compareAndSet() 方法实现。
- 读写锁ReentrantReadWriteLock：适用于向数据结构相对不频繁写入，但多个任务经常要读取这个数据的情况进行了优化。
- 使用Callable替换 runnable， 通过判断Future对象的是否isDone，来获取结果