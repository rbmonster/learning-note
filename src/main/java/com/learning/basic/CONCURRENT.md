# java并发
## 基本概念
- 并发编程可以抽象成三个核心问题: 分工、同步/协作、互斥
### 分工
将当前 Sprint 的 Story 拆分成「合适」大小的 Task，并且安排给「合适」的 Team Member 去完成
> 拆分的粒度太粗，导致这个任务完成难度变高，耗时长，不易与其他人配合；拆分的粒度太细，又导致任务太多，不好管理与追踪，浪费精力和资源。

关于分工，常见的 Executor，生产者-消费者模式，Fork/Join 等，这都是分工思想的体现

### 同步/协作
一个线程执行完任务，如何通知后续线程执行?
> Java SDK 中 CountDownLatch 和 CyclicBarrier 就是用来解决线程协作问题的

### 互斥
互斥：同一时刻，只允许一个线程访问共享变量。分工和同步强调的是性能，但是互斥是强调正确性

当多个线程同时访问一个共享变量/成员变量时，就可能发生不确定性，造成不确定性主要是有可见性、原子性、有序性这三大问题，而解决这些问题的核心就是互斥

## 三大问题
### 可见性
定义： 一个线程对共享变量的修改，另外一个线程能够立刻看到，我们称为可见性

Java 内存模型规定，将所有的变量都存放在 主内存中，当线程使用变量时，会把主内存里面的变量 复制 到自己的工作空间或者叫作 私有内存
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/workingmemory.jpg)

刷新的场景:
>1. 主内存中有变量 x，初始值为 0
>2. 线程 A 要将 x 加 1，先将 x=0 拷贝到自己的私有内存中，然后更新 x 的值
>3. 线程 A 将更新后的 x 值回刷到主内存的时间是不固定的
>4. 刚好在线程 A 没有回刷 x 到主内存时，线程 B 同样从主内存中读取 x，此时为 0，和线程 A 一样的操作，最后期盼的 x=2 就会编程 x=1

### 原子性
原子性：原子（atom）指化学反应不可再分的基本微粒，原子性操作你应该能感受到其含义:

count++ 分解为四步，解释一下字节码的指令，
> - 16 : 获取当前 count 值，并且放入栈顶
> - 19 : 将常量 1 放入栈顶
> - 20 : 将当前栈顶中两个值相加，并把结果放入栈顶
> - 21 : 把栈顶的结果再赋值给 count

-  JDK 的 rt.jar 包中的 Unsafe 类提供了 硬件级别 的原子性操作

### 有序性
对于编译期可能对语句的执行进行了优化。
- 如双重加锁检查的 `instance = new Singleton()`\
这 1 行代码转换成了 CPU 指令后又变成了 3 个，我们理解 new 对象应该是这样的:
 ```
 1. 分配一块内存 M
 2. 在内存 M 上初始化 Singleton 对象
 3. 后 M 的地址赋值给 instance 变量

但编译器擅自优化后可能就变成了这样:
1. 分配一块内存 M
2. 然后将 M 的地址赋值给 instance 变量
3. 在内存 M 上初始化 Singleton 对象
```

##  volatile变量特殊规则
性能：volatile变量的读操作性能与普通变量几乎没有差别，但是写操作可能会慢些，因为需要插入内存屏障指令来保证处理器不乱序执行。
> 当读一个 volatile 变量时, JMM 会把该线程对应的本地内存置为无效。线程接下来将从主内存中读取共享变量。\
> 线程在【读取】共享变量时，会先清空本地内存变量值，再从主内存获取最新值\
> 线程在【写入】共享变量时，不会把值缓存在寄存器或其他地方（就是刚刚说的所谓的「工作内存」），而是会把值刷新回主内存

### 保证变量可见性
定义：当一条线程修改了这个变量的值，新值对于其他线程是立即可见的。 正常的变量修改的过程中，工作内存与主内存同步是存在延迟的，也就出现了可见性的问题。
> volatile并发下是安全的？\
> 不，只能保证取值的时候是线程安全，当获取值之后做操作如果不是原子操作，很可能变量已经被其他线程改变。如多线程对volatile变量符号运算。

### 禁止指令重排序优化
定义：普通的变量只能保证代码执行完成获取的结果一致，而不能保证执行顺序与代码顺序一致。

例子：如下双重加锁的例子，volatile保证了变量初始化完立即刷新到主内存中，能让其他线程可以获取到。

防止指令重排`uniqueInstance = new Singleton();` 这段代码其实是分为三步执行：
1. 为uniqueInstance 分配内存空间
2. 初始化 uniqueInstance
3. 将 uniqueInstance 指向分配的内存地址
但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1>3>2。指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。

java编译器会在生成指令系列时在适当的位置会插入 "内存屏障"指令来禁止特定类型的处理器重排序。
```
public class Singleton{
    private Singleton() {}
    private static volatile Singleton singleton = null;
    public static Singleton getSingleton() {
        if(singleton == null) {
            synchronized(Singleton.class){
                if(singleton ==null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
```

当我们要保护单个资源并对其进行修改其实很简单，只需按照下图分三步走：
1. 创建受保护资源 R 的锁
2. 加锁进入临界区
3. 解锁走出临界区

## synchronized
### 基本概念
定义：synchronized 关键字解决的是多个线程之间访问资源的同步性，synchronized关键字可以保证被它修饰的方法或者代码块在任意时刻只能有一个线程执行。
1. 【进入】synchronized 块的内存语义是把在 synchronized 块内使用的变量从线程的工作内存中清除，从主内存中读取
2. 【退出】synchronized 块的内存语义事把在 synchronized 块内对共享变量的修改刷新到主内存中

#### synchronized关键字用法及锁范围
1. 对于普通实例方法，锁的是当前实例对象，通常指 this
2. 对于静态同步方法，锁的是当前类的 Class 对象，如 ThreeSync.class
3. 对于同步代码块，锁的是 synchronized 括号内的对象
```
    // 锁方法
	public synchronized void normalSyncMethod(){
		//临界区
	}

	public static synchronized void staticSyncMethod(){
		//临界区
	}

	public void syncBlockMethod(){
		synchronized (object){
			//临界区
		}
	}

	public void syncBlockMethod(){
		synchronized (Object.class){
			//临界区
		}
	}
```
#### 使用有误例子

银行转账的方法，典型的忽略锁和资源的指向关系，这里的Account target并没有保护作用
```
class Account {
  private int balance;
  // 转账
  synchronized void transfer(Account target, int amt){
    if (this.balance > amt) {
      this.balance -= amt;
      target.balance += amt;
    }
  } 
}
```

#### 其他
> 一个线程可以从挂起状态变为可运行状态（也就是被唤醒），即使线程没有被其他线程调用notify()/notifyAll() 方法进行通知，或被中断，或者等待超时，这就是所谓的【虚假唤醒】。虽然虚假唤醒很少发生，但要防患于未然，做法就是不停的去测试该线程被唤醒条件是否满足

volatile 与 synchronize比较
1. volatile 非阻塞 ，synchronize阻塞
2. 读取驻内存值时volatile与synchronized作用跟等价

### synchronized关键字JVM底层原理解析

#### synchronized 同步语句块的情况
```
publicclass SynchronizedDemo {
	public void method() {
		synchronized (this) {
			System.out.println("synchronized 代码块");
		}
	}
}

javap -c -s -v -l SynchronizedDemo.class 反编译
```
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/synchronizeCode.jpg)
synchronized 同步语句块的实现使用的是 monitorenter 和 monitorexit 指令，其中 monitorenter 指令指向同步代码块的开始位置，monitorexit 指令则指明同步代码块的结束位置。\
当计数器为0则可以成功获取，获取后将锁计数器设为1也就是加1。相应的在执行 monitorexit 指令后，将锁计数器设为0，表明锁被释放。

#### synchronized 修饰方法的的情况
```
public class SynchronizedDemo2 {
	public synchronized void method() {
		System.out.println("synchronized 方法");
	}
}
```
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/synchronizeMethod.jpg)
方法体出现ACC_SYNCHRONIZED 标识，该标识指明了该方法是一个同步方法，JVM 通过该 ACC_SYNCHRONIZED 访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用。


### synchronize 的锁优化
#### 自旋锁
自旋锁：对于锁状态很短的线程，挂起和恢复线程是开销很大的，因此让线程执行一个忙等待（自旋），这就是自旋锁的技术

#### 自适应锁
自适应锁：自适应意味着自旋的时间不固定，由前一次在同一个锁上的自旋时间及锁的状态拥有者来决定。（如果之前自旋获得过锁，进而允许本次自旋更长时间。若很少成果获得锁，那么可能直接忽略跳过等待）
  
#### 锁消除
执行的方法体所有数据都不会逃逸出去被其他线程访问到，认为是线程私有的，便可以消除锁。

#### 锁粗化
同步块过于细化，导致多次获取锁，导致不必要的性能损耗，扩大锁的范围便可以解决这个问题。

### synchronize锁升级(锁膨胀)
锁膨胀的方向：**无锁->偏向锁->轻量级锁->重量级锁**，并且膨胀方向不可逆。

#### 偏向锁
 
定义：在大多数情况下，锁不存在多线程竞争，总是由同一线程多次获得，那么此时引入偏向锁。
- 作用：减少同一线程获取锁的代价。

偏向锁原理与升级过程：
1. 当线程1访问代码块并获取锁对象时，会在java对象头和栈帧中记录偏向锁的ThreadID。
2. 因为偏向锁不会主动释放锁，因此以后线程1再次获取锁时会比较当前线程的ThreadID与java对象头中的ThreadID是否相等
  - 相等就直接执行，无需CAS来加锁解锁
  - 如果不相等（其他线程需要获取锁对象，如线程2），查看线程1是否存活。
     - 不存活则锁对象被重置为无锁状态，其他线程（线程2）可以竞争将其设置为偏向锁。
     - 存活状态下则立刻查找该**线程（线程1）的栈帧信息**
        - 如果需要继续持有这个锁对象，那么会暂停该线程（线程1），撤销偏向锁，升级为轻量级锁进行后续操作。
        - 如果不再需要这个锁对象，那么将锁对象设置为无锁状态，重新进行偏向锁竞争。

第一次获取替换对象头TheadID：
1. 当一个线程访问同步块并获取锁时, 会在锁对象的对象头和栈帧中的锁记录里存储锁偏向的线程ID, 以后该线程进入和退出同步块时不需要进行CAS操作来加锁和解锁。
2. 需要简单的测试一下锁对象的对象头的MarkWord里是否存储着指向当前线程的偏向锁(线程ID是当前线程)。
   - 如果测试成功, 表示线程已经获得了锁;
   - 如果测试失败, 则需要再测试一下MarkWord中偏向锁的标识是否设置成1(表示当前是偏向锁)
      - 如果没有设置, 则使用CAS竞争锁。
      - 如果设置了, 则尝试使用CAS将锁对象的对象头的偏向锁指向当前线程.

偏向锁的撤销: 偏向锁使用了一种等到竞争出现才释放锁的机制, 所以当其他线程尝试竞争偏向锁时, 持有偏向锁的线程才会释放锁. 偏向锁的撤销需要等到全局安全点(在这个时间点上没有正在执行的字节码). \
首先会暂停持有偏向锁的线程, 然后**检查持有偏向锁的线程是否存活**, 如果线程不处于活动状态, 则将锁对象的对象头设置为无锁状态; 如果线程仍然活着, 则锁对象的对象头中的MarkWord和栈中的锁记录要么重新偏向于其它线程要么恢复到无锁状态, 最后唤醒暂停的线程(释放偏向锁的线程).

参考资料：
[Java6及以上版本对synchronized的优化](https://www.cnblogs.com/wuqinglong/p/9945618.html#%E6%97%A0%E9%94%81%E7%8A%B6%E6%80%81)


#### 轻量级锁
情况：当竞争锁对象的线程不多，并且线程持有锁的时间也不长时，那么此时引入轻量级锁。
> 轻量级锁是由偏向锁升级而来，当存在第二个线程申请同一个锁对象时(持有锁的线程依然存活)，偏向锁就会立即升级为轻量级锁。注意这里的第二个线程只是申请锁，不存在两个线程同时竞争锁，可以是一前一后地交替执行同步块。

轻量级锁：由于阻塞线程需要CPU从**用户状态转到内核状态**代价比较大，如果刚线程阻塞这个锁就被释放了时候代价太大，所以这个时候不会阻塞线程，而是通过CAS操作让它自旋等待锁对象的释放。

轻量级锁原理与升级过程：
1. 线程1获取轻量级锁时会把锁对象的对象头MarkWord复制一份到线程1的栈帧中创建的用于存储锁记录的空间（DisplacedMarkWord），然后使用CAS把对象中的内存替换为线程1存储的锁记录（DisplacedMarkWord）的地址。
2. 如果在线程1复制对象头的同时（在线程1CAS之前），线程2也准备获取锁，复制了对象头到线程2的锁记录空间中，但是在线程2 **CAS的时候**发现线程1已经把对象头换了，线程2的CAS失败，那么线程2就尝试使用自旋锁来等在线程1释放锁。

升级为重量级锁条件，达到以下两条件都会升级为重量级锁。
1. 长时间自旋会导致CPU消耗，达到一定自旋次数还没有释放锁。
2. 线程1还在执行线程2还在自旋，这时候又有线程3来竞争这个对象争锁。

#### 重量级锁
情况：当多个线程同时在竞争锁对象时，那么此时引入重量级锁。

重量级锁：阻塞所有等待竞争的线程，防止CPU空转，阻塞等待线程1释放锁后进入无锁状态重新竞争。

##### 工作原理
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/synchronizeHeavyLock.jpg)

```
ContentionList：所有请求锁的线程将被首先放在该竞争队列。

EntryList：ContentList中有资格成为候选人的线程被移到EntryList中。主要是为了减少对Contention List的并发访问，因为既会添加新线程到队尾，也会从队尾取线程。

WaitSet：存放那些调用wait()方法被阻塞的线程

OnDeck：任何时刻最多只能有一个线程竞争锁，该线程成为OnDeck

Owner：获得锁的线程
```
1. 步骤1是线程在进入Contention List时阻塞等待之前，程会先尝试自旋使用CAS操作获取锁，如果获取不到就进入Contention List队列的尾部。
2. 步骤2是Owner线程在解锁时，如果Entry List为空，那么会**先将Contention List中队列尾部的部分线程移动到Entry List**
3. 步骤3是Owner线程在解锁时，如果Entry List不为空，从Entry List中取一个线程，让它成为OnDeck线程，Owner线程并不直接把锁传递给OnDeck线程，而是把锁竞争的权利交给OnDeck，OnDeck需要重新竞争锁，JVM中这种选择行为称为 “竞争切换”。（主要是与还没有进入到ContentionList，还在自旋获取重量级锁的线程竞争）
4. 步骤4就是OnDeck线程获取到锁，成为Owner线程进行执行。
5. 步骤5就是Owner线程调用锁对象的wait（）方法进行等待，会移动到Wait Set中，并且会释放CPU资源，也同时释放锁，
6. 步骤6.就是当其他线程调用锁对象的notify（）方法，之前调用wait方法等待的这个线程才会从Wait Set移动到Entry List，等待获取锁。

> Synchronized 是非公平锁。 Synchronized 在线程进入 ContentionList 时， 等待的线程会先尝试自旋获取锁，如果获取不到就进入 ContentionList，这明显对于已经进入队列的线程是不公平的，还有一个不公平的事情就是自旋获取锁的线程还可能直接抢占 OnDeck 线程的锁资源\
> 可重入锁：在Monitor中其实还有一个计数器，主要是用来记录重入次数的，当计数器为0时，表示没有任何线程持有锁，当某线程获取锁时，计算器则加1，若当前线程再次获取锁时，计数器则会再次递增，

##### 与ReentrantLock区别
1. synchronized是JVM层面的锁；ReentrantLock是JDK层面的锁，由java代码实现
2. synchronized锁无法在代码中判断是否有所；ReentrantLock则可以通过【isLock()】判断是否获取到锁
3. synchronized是一种非公平锁；ReentrantLock既可以实现公平锁，也可以实现非公平锁
4. synchronized不可以被中断；ReentrantLock可以【lockInterruptibly】实现中断
5. 发生异常时，synchronized会自动释放锁，有javac实现；ReentrantLock需要开发者在finally中显式释放锁
6. ReentrantLock在加锁时会更灵活，可以使用【tryLock】尝试获取锁，从而避免线程阻塞


#### 调试synchronize加锁过程
```
<dependency>
    <groupId>org.openjdk.jol</groupId>
    <artifactId>jol-core</artifactId>
    <version>0.11</version>
</dependency>

// 从字节码层面 输出对象头信息
public static void main(String[] args) {
    final Object o = new Object();
    System.out.println(ClassLayout.parseInstance(o).toPrintable());

    synchronized (o) {
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
```

#### 相关资料
[JavaJDK1.6 之后对锁优化引入锁膨胀、锁消除、锁粗化、自旋锁、偏向锁、轻量级锁、重量级锁详解](https://mp.weixin.qq.com/s/WwOl8-4IAdcItWg63HI3tw)

重量级锁：
- [【大厂面试07期】说一说你对synchronized锁的理解？](https://www.xiaoheidiannao.com/13829.html)
- [【Java】唠唠synchronized中的重量级锁](https://www.cnblogs.com/boluopabo/archive/2004/01/13/13086172.html)

