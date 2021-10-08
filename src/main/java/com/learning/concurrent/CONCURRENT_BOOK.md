# 线程
## 线程状态
![avatar](https://gitee.com/rbmon/file-storage/blob/main/learning-note/learning/concurrent/threadState.jpg)

- 新建（NEW）：创建后尚未启动。
- 可运行（RUNABLE）：正在 Java 虚拟机中运行。但是在操作系统层面，它可能处于运行状态，也可能等待资源调度（例如处理器资源），资源调度完成就进入运行状态。所以该状态的可运行是指可以被运行，具体有没有运行要看底层操作系统的资源调度。
- 阻塞（BLOCKED）：请求获取 monitor lock 从而进入 synchronized 函数或者代码块，但是其它线程已经占用了该 monitor lock，所以出于阻塞状态。要结束该状态进入从而 RUNABLE 需要其他线程释放 monitor lock。
- 无限期等待（WAITING）：等待其它线程显式地唤醒。
   - 阻塞和等待的区别在于，阻塞是被动的，它是在等待获取 monitor lock。而等待是主动的，通过调用  Object.wait() 等方法进入。

| 进入方法 | 退出方法 |
| --- | --- |
| 没有设置 Timeout 参数的 Object.wait() 方法 | Object.notify() / Object.notifyAll() |
| 没有设置 Timeout 参数的 Thread.join() 方法 | 被调用的线程执行完毕 |
| LockSupport.park() 方法 | LockSupport.unpark(Thread) |

- 限期等待（TIMED_WAITING）：无需等待其它线程显式地唤醒，在一定时间之后会被系统自动唤醒。
  - 调用 Thread.sleep() 方法使线程进入限期等待状态时，常常用“使一个线程睡眠”进行描述。调用 Object.wait() 方法使线程进入限期等待或者无限期等待时，常常用“挂起一个线程”进行描述。睡眠和挂起是用来描述行为，而阻塞和等待用来描述状态。

| 进入方法 | 退出方法 |
| --- | --- |
| Thread.sleep() 方法 | 时间结束 |
| 设置了 Timeout 参数的 Object.wait() 方法 | 时间结束 / Object.notify() / Object.notifyAll()  |
| 设置了 Timeout 参数的 Thread.join() 方法 | 时间结束 / 被调用的线程执行完毕 |
| LockSupport.parkNanos() 方法 | LockSupport.unpark(Thread) |
| LockSupport.parkUntil() 方法 | LockSupport.unpark(Thread) |

- 死亡（TERMINATED）：可以是线程结束任务之后自己结束，或者产生了异常而结束。

## 创建一个线程的开销
- JVM 在背后帮我们做了哪些事情：

1. 它为一个线程栈分配内存，该栈为每个线程方法调用保存一个栈帧
2. 每一栈帧由一个局部变量数组、返回值、操作数堆栈和常量池组成
3. 一些支持本机方法的 jvm 也会分配一个本机堆栈
4. 每个线程获得一个程序计数器，告诉它当前处理器执行的指令是什么
5. 系统创建一个与Java线程对应的本机线程
6. 将与线程相关的描述符添加到JVM内部数据结构中
7. 线程共享堆和方法区域

> 用数据来说明创建一个线程（即便不干什么）需要多大空间呢？答案是大约 1M 左右

> java -XX:+UnlockDiagnosticVMOptions -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics -version
- 用 Java8 的测试结果，19个线程，预留和提交的大概都是19000+KB，平均每个线程大概需要 1M 左右的大小

![avatar](https://gitee.com/rbmon/file-storage/blob/main/learning-note/learning/concurrent/threadState2.jpg)
## 线程池
### 线程池状态
线程池的5种状态：Running、ShutDown、Stop、Tidying、Terminated。
![avatar](https://gitee.com/rbmon/file-storage/blob/main/learning-note/learning/concurrent/threadPool.jpg)

- RUNNING
  1. 状态说明：线程池处在RUNNING状态时，能够接收新任务，以及对已添加的任务进行处理。 
  2. 状态切换：线程池的初始化状态是RUNNING。换句话说，线程池被一旦被创建，就处于RUNNING状态

- SHUTDOWN
  1. 状态说明：线程池处在SHUTDOWN状态时，不接收新任务，但能处理已添加的任务。 
  2. 状态切换：调用线程池的shutdown()接口时，线程池由RUNNING -> SHUTDOWN。

- STOP
  1. 状态说明：线程池处在STOP状态时，不接收新任务，不处理已添加的任务，并且会中断正在处理的任务。 
  2. 状态切换：调用线程池的shutdownNow()接口时，线程池由(RUNNING or SHUTDOWN ) -> STOP。 

- TIDYING
  1. 状态说明：当所有的任务已终止，ctl记录的”任务数量”为0，线程池会变为TIDYING状态。当线程池变为TIDYING状态时，会执行钩子函数terminated()。terminated()在ThreadPoolExecutor类中是空的，若用户想在线程池变为TIDYING时，进行相应的处理；可以通过重载terminated()函数来实现。 
  2. 状态切换：当线程池在SHUTDOWN状态下，阻塞队列为空并且线程池中执行的任务也为空时，就会由 SHUTDOWN -> TIDYING。 

- TERMINATED
  1. 状态说明：线程池彻底终止，就变成TERMINATED状态。 
  2. 状态切换：线程池处在TIDYING状态时，执行完terminated()之后，就会由 TIDYING -> TERMINATED当线程池在STOP状态下，线程池中执行的任务为空时，就会由STOP -> TIDYING。
### 线程池创建
- 线程池的初始化：
```
/**
 * 用给定的初始参数创建一个新的ThreadPoolExecutor。
 */
public ThreadPoolExecutor(int corePoolSize,//线程池的核心线程数量
                          int maximumPoolSize,//线程池的最大线程数
                          long keepAliveTime,//当线程数大于核心线程数时，多余的空闲线程存活的最长时间
                          TimeUnit unit,//时间单位
                          BlockingQueue<Runnable> workQueue,//任务队列，用来储存等待执行任务的队列
                          ThreadFactory threadFactory,//线程工厂，用来创建线程，一般默认即可
                          RejectedExecutionHandler handler//拒绝策略，当提交的任务过多而不能及时处理时，我们可以定制策略来处理任务
                           ){
.......
}
```
- corePoolSize：核心线程数量，当有新任务在execute()方法提交时，会执行以下判断：
  - 如果运行的线程少于 corePoolSize，则创建新线程来处理任务，即使线程池中的其他线程是空闲的；
  - 如果线程池中的线程数量大于等于 corePoolSize 且小于 maximumPoolSize，则只有当workQueue满时才创建新的线程去处理任务；
  - 如果设置的corePoolSize 和 maximumPoolSize相同，则创建的线程池的大小是固定的，这时如果有新任务提交，若workQueue未满，则将请求放入workQueue中，等待有空闲的线程去从workQueue中取任务并处理；
  - 如果运行的线程数量大于等于maximumPoolSize，这时如果workQueue已经满了，则通过handler所指定的策略来处理任务
  - 所以，任务提交时，判断的顺序为 corePoolSize –> workQueue –> maximumPoolSize。

- 线程池拒绝策略
  - ThreadPoolExecutor.AbortPolicy：抛出 RejectedExecutionException来拒绝新任务的处理。
  - ThreadPoolExecutor.CallerRunsPolicy：调用执行自己的线程运行任务，也就是直接在调用execute方法的线程中运行(run)被拒绝的任务，如果执行程序已关闭，则会丢弃该任务。因此这种策略会降低对于新任务提交速度，影响程序的整体性能。如果您的应用程序可以承受此延迟并且你要求任何一个任务请求都要被执行的话，你可以选择这个策略。
    - 简单的说就是用启动threadPool的线程执行新的请求。
  - ThreadPoolExecutor.DiscardPolicy： 不处理新任务，直接丢弃掉。
  - ThreadPoolExecutor.DiscardOldestPolicy： 此策略将丢弃最早的未处理的任务请求。

![avatar](https://gitee.com/rbmon/file-storage/blob/main/learning-note/learning/concurrent/threadPoolProcess.jpg)


### 阿里开发规范
- 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
  - 说明： Executors 返回的线程池对象的弊端如下：
1. FixedThreadPool 和 SingleThreadPool:允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
2. CachedThreadPool 和 ScheduledThreadPool:允许的创建线程数量为 Integer.MAX_VALUE， 可能会创建大量的线程，从而导致 OOM。

## 线程池的队列

1. SynchronousQueue（CachedThreadPool） 类似交警只是指挥车辆，并不管理车辆
  - SynchronousQueue没有容量，是无缓冲等待队列，是一个不存储元素的阻塞队列，会直接将任务交给消费者，必须等队列中的添加元素被消费后才能继续添加新的元素。超出直接corePoolSize个任务，直接创建新的线程来执行任务，直到(corePoolSize＋新建线程)> maximumPoolSize。不是核心线程就是新建线程。

2. LinkedBlockingQueue（single，fixed）类似小仓库，暂时存储任务，待系统有空的时候再取出执行
  - BlockingQueue是双缓冲队列。BlockingQueue内部使用两条队列，允许两个线程同时向队列一个存储，一个取出操作。在保证并发安全的同时，提高了队列的存取效率。LinkedBlockingQueue是一个无界缓存等待队列。当前执行的线程数量达到corePoolSize的数量时，剩余的元素会在阻塞队列里等待。（所以在使用此阻塞队列时maximumPoolSizes就相当于无效了），每个线程完全独立于其他线程。生产者和消费者使用独立的锁来控制数据的同步，即在高并发的情况下可以并行操作队列中的数据。

3. ArrayBlockingQueue
  - ArrayBlockingQueue是一个有界缓存等待队列，可以指定缓存队列的大小，当正在执行的线程数等于corePoolSize时，多余的元素缓存在ArrayBlockingQueue队列中等待有空闲的线程时继续执行，当ArrayBlockingQueue已满时，加入ArrayBlockingQueue失败，会开启新的线程去执行，当线程数已经达到最大的maximumPoolSizes时，再有新的元素尝试加入ArrayBlockingQueue时会报错
  
### 线上线程池的配置
- CPU密集: CPU密集的意思是该任务需要大量的运算，而没有阻塞，CPU一直全速运行。
  - CPU密集任务只有在真正的多核CPU上才可能得到加速(通过多线程)，而在单核CPU上，无论你开几个模拟的多线程，该任务都不可能得到加速，因为CPU总的运算能力就那些。
- IO密集型，即该任务需要大量的IO，即大量的阻塞。在单线程上运行IO密集型的任务会导致浪费大量的CPU运算能力浪费在等待。所以在IO密集型任务中使用多线程可以大大的加速程序运行，即时在单核CPU上，这种加速主要就是利用了被浪费掉的阻塞时间。
- 对于不同性质的任务来说
  - CPU密集型任务应配置尽可能小的线程，如配置CPU个数的线程数+1
    - 计算密（CPU）集型的线程恰好在某时因为发生一个页错误或者因其他原因而暂停，刚好有一个“额外”的线程，可以确保在这种情况下CPU周期不会中断工作。
  - IO密集型任务应配置尽可能多的线程，因为IO操作不占用CPU，不要让CPU闲下来，应加大线程数量，如配置两倍CPU个数+1，
    - 单核最佳线程数 = (1/CPU利用率) = 1 + (I/O耗时/CPU耗时)
    - 最佳线程数 = CPU核心数 * (1/CPU利用率) = CPU核心数 * 1 + (I/O耗时/CPU耗时)
    - CPU利用率： （CPU耗时）/ （I/O耗时/ CPU耗时）
    
- 使用以下工具来了解I/O 耗时与 CUP耗时
  - SkyWalking
  - CAT
  - zipkin
  
> 假设要求一个系统的 TPS（Transaction Per Second 或者 Task Per Second）至少为20，然后假设每个Transaction由一个线程完成，继续假设平均每个线程处理一个Transaction的时间为4s
> 如何设计线程个数，使得可以在1s内处理完20个Transaction？
- 答案：80。一般服务器的CPU核数为16或者32，如果有80个线程，那么肯定会带来太多不必要的线程上下文切换开销

> 计算操作需要5ms，DB操作需要 100ms，对于一台 8 核CPU的服务器，怎么设置线程数呢？
-  8*  （1/(5/100+5) = 168 个线程数

> 那如果DB的 QPS（Query Per Second）上限是1000，此时这个线程数又该设置为多大呢？
- 一个线程的TPS（每秒） = 1000/105
- 系统的QPS = 168 * 1000/105    >1600
- 假设一个TPS 对应一次QPS
- 答案：线程数 = 168* (1000/ 1600) > 105 

> 增加服务器核心数，与线程间的关系

![avatar](https://gitee.com/rbmon/file-storage/blob/main/learning-note/learning/concurrent/threadPoolProcess.jpg)

> 假设： 1-p=5%  而n趋近于无穷大，实际起作用的最大线程数为20。

> 临界区都是串行的，非临界区都是并行的，用单线程执行 临界区的时间/用单线程执行(临界区+非临界区)的时间 就是串行百分比
## 解决共享资源竞争

### synchronized:
- 在对象上调用其任意的synchronized方法的时候，此对象都会被加锁，此时该对象的其他synchronized方法只有等到前一个方法调用完毕，并释放了锁之后才能被调用。
- 如果一个方法在同一个对象上调用了第二个方法，后者又调用了同一对象上的另一个方法。JVM负责跟踪对象被加锁的次数，在任务第一次给对象加锁的时候，计数变为1，每当这个相同的任务在这个对象上获得锁时，技术都会增加，每当任务离开一个synchronized的方法，计数递减。当计数为0时，锁完全释放。（前提是首先获得了锁的任务才能允许继续获取多个锁）
- 针对每个类，也有一个锁（作为类的Class对象的一部分），所以synchronized static 方法可以在类的范围内防止对static数据的并发访问。
- 对比synchronized 同步方法和同步控制块的效率，同步控制块对象不加锁的时间更长，效率更高。

### lock对象 
- ReentrantLock 允许尝试着获取但最终未获取锁。
- 相比于synchronized锁，lock对象可以做到更细粒度的控制力。

### 原子性
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

## ThreadLocal
- 通常当做静态域存储，因为ThreadLocal为每个线程都分配了自己的空间。使用当前线程作为key，因此不会出现线程冲突。
- 注意不同线程在ThreadLocal存储的值为同一个对象的情况，由于引用都是同一个对象，因此会出现线程问题。

## 退出线程的方法
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
 
## daemon Thread 守护线程应用场景
- 典型的应用场景就是java的GC垃圾回收线程
- 平常在系统的应用中，同样可以用于缓存清理这方面的工作
- 固定取消过期订单。

## 线程间协作
1. wait()、notify()以及notifyAll()是基类Object的方法，尽管是线程的操作，放到基类中，便于操作所有对象。而这三个方法只能在==同步方法或者同步控制块中调用==。如果在非同步控制方法调用，程序能通过，但是运行时会得到IllegalMonitorStateException，表示调用该方法必须获得对象锁。 
2. notifyAll()的并不是唤醒所有正在等待的任务。本质是notifyALl()因特定锁而被调用时，只有等待这个锁的任务才会被唤醒。
3. 使用ReentrantLock，通过lock.newCondition()，使用lock的signAll的方法实现线程间的协作
4. 使用BlockingQueue,阻塞队列来实现线程间协作，就可以较少很多繁杂的wait和notify操作。
- 通常可以使用LinkedBlockingQueue一个无界队列，ArrayBlockingQueue一个固定尺寸的队列。SynchronousQueue 只能包含一个元素的队列。
5.管道通信，线程之间通过管道进行连接传输信息。涉及的类PipedWriter和PipedReader，两者需要建立管道连接new PipedReader(sender.getPipedWriter())。

## 死锁产生条件
1.互斥条件：任务使用的资源至少有一个不能共享。
2.至少有一个任务它必须持有一个资源，且正在等待获取当前被别的任务持有的资源。
3.资源不能被任务抢占，必须等待。（资源必须等别的任务使用完成释放）
4.必须有等待循环。（即一个任务等待其他任务的资源，一个循环的等待）

## 其他的一些工具类
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