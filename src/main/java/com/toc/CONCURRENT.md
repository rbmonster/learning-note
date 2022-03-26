<a name="index">**Index**</a>

<a href="#0">java并发</a>  
&emsp;<a href="#1">1. 基本概念</a>  
&emsp;&emsp;<a href="#2">1.1. 分工</a>  
&emsp;&emsp;<a href="#3">1.2. 同步/协作</a>  
&emsp;&emsp;<a href="#4">1.3. 互斥</a>  
&emsp;<a href="#5">2. 三大问题</a>  
&emsp;&emsp;<a href="#6">2.1. 可见性</a>  
&emsp;&emsp;<a href="#7">2.2. 原子性</a>  
&emsp;&emsp;<a href="#8">2.3. 有序性</a>  
&emsp;<a href="#9">3. 用户态与内核态</a>  
&emsp;&emsp;<a href="#10">3.1. 为什么要有用户态和内核态</a>  
&emsp;&emsp;<a href="#11">3.2. 用户态与内核态的性能开销</a>  
&emsp;&emsp;<a href="#12">3.3. 避免频繁切换</a>  
&emsp;<a href="#13">4. java内存模型</a>  
&emsp;<a href="#14">5.  volatile</a>  
&emsp;&emsp;<a href="#15">5.1. 保证变量可见性</a>  
&emsp;&emsp;<a href="#16">5.2. 禁止指令重排序优化</a>  
&emsp;<a href="#17">6. synchronized</a>  
&emsp;&emsp;<a href="#18">6.1. 基本概念</a>  
&emsp;&emsp;&emsp;<a href="#19">6.1.1. synchronized关键字用法及锁范围</a>  
&emsp;&emsp;&emsp;<a href="#20">6.1.2. 使用有误例子</a>  
&emsp;&emsp;&emsp;<a href="#21">6.1.3. 其他</a>  
&emsp;&emsp;<a href="#22">6.2. synchronized关键字JVM底层原理解析</a>  
&emsp;&emsp;&emsp;<a href="#23">6.2.1. synchronized 同步语句块的情况</a>  
&emsp;&emsp;&emsp;<a href="#24">6.2.2. synchronized 修饰方法的的情况</a>  
&emsp;&emsp;<a href="#25">6.3. 对象创建与加锁</a>  
&emsp;&emsp;<a href="#26">6.4. synchronize 的锁优化</a>  
&emsp;&emsp;&emsp;<a href="#27">6.4.1. 自旋锁</a>  
&emsp;&emsp;&emsp;<a href="#28">6.4.2. 自适应锁</a>  
&emsp;&emsp;&emsp;<a href="#29">6.4.3. 锁消除</a>  
&emsp;&emsp;&emsp;<a href="#30">6.4.4. 锁粗化</a>  
&emsp;&emsp;<a href="#31">6.5. synchronize锁升级(锁膨胀)</a>  
&emsp;&emsp;&emsp;<a href="#32">6.5.1. 偏向锁</a>  
&emsp;&emsp;&emsp;<a href="#33">6.5.2. 轻量级锁</a>  
&emsp;&emsp;&emsp;<a href="#34">6.5.3. 重量级锁</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#35">6.5.3.1. 工作原理</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#36">6.5.3.2. 与ReentrantLock区别</a>  
&emsp;&emsp;&emsp;<a href="#37">6.5.4. 调试synchronize加锁过程</a>  
&emsp;<a href="#38">7. 线程安全</a>  
&emsp;&emsp;<a href="#39">7.1. java中的线程安全</a>  
&emsp;&emsp;<a href="#40">7.2. 线程安全实现</a>  
&emsp;&emsp;&emsp;<a href="#41">7.2.1. 互斥同步</a>  
&emsp;&emsp;&emsp;<a href="#42">7.2.2. 非阻塞同步</a>  
&emsp;&emsp;&emsp;<a href="#43">7.2.3. 无同步方案</a>  
&emsp;&emsp;<a href="#44">7.3. long 和 double的型变量的特殊规则</a>  
&emsp;&emsp;<a href="#45">7.4. happen-before 先行发生原则</a>  
&emsp;&emsp;<a href="#46">7.5. 线程安全的类定义</a>  
&emsp;&emsp;&emsp;<a href="#47">7.5.1. 枚举类为什么是线程安全？</a>  
&emsp;<a href="#48">8. 其他</a>  
&emsp;&emsp;<a href="#49">8.1. 为什么jdk8要在4s后开启偏向锁？</a>  
&emsp;&emsp;<a href="#50">8.2. System.out.println() 的实现使用synchronized</a>  
&emsp;&emsp;<a href="#51">8.3. 面试问题</a>  
&emsp;<a href="#52">9. 参考资料</a>  
# <a name="0">java并发</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
## <a name="1">基本概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 并发编程可以抽象成三个核心问题: 分工、同步/协作、互斥
### <a name="2">分工</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
将当前 Sprint 的 Story 拆分成「合适」大小的 Task，并且安排给「合适」的 Team Member 去完成
> 拆分的粒度太粗，导致这个任务完成难度变高，耗时长，不易与其他人配合；拆分的粒度太细，又导致任务太多，不好管理与追踪，浪费精力和资源。

关于分工，常见的 Executor，生产者-消费者模式，Fork/Join 等，这都是分工思想的体现

### <a name="3">同步/协作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
一个线程执行完任务，如何通知后续线程执行?
> Java SDK 中 CountDownLatch 和 CyclicBarrier 就是用来解决线程协作问题的

### <a name="4">互斥</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
互斥：同一时刻，只允许一个线程访问共享变量。分工和同步强调的是性能，但是互斥是强调正确性

当多个线程同时访问一个共享变量/成员变量时，就可能发生不确定性，造成不确定性主要是有可见性、原子性、有序性这三大问题，而解决这些问题的核心就是互斥

## <a name="5">三大问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="6">可见性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义： 一个线程对共享变量的修改，另外一个线程能够立刻看到，我们称为可见性

Java 内存模型规定，将所有的变量都存放在 主内存中，当线程使用变量时，会把主内存里面的变量 复制 到自己的工作空间或者叫作 私有内存
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/workingmemory.jpg)

刷新的场景:
>1. 主内存中有变量 x，初始值为 0
>2. 线程 A 要将 x 加 1，先将 x=0 拷贝到自己的私有内存中，然后更新 x 的值
>3. 线程 A 将更新后的 x 值回刷到主内存的时间是不固定的
>4. 刚好在线程 A 没有回刷 x 到主内存时，线程 B 同样从主内存中读取 x，此时为 0，和线程 A 一样的操作，最后期盼的 x=2 就会编程 x=1

### <a name="7">原子性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
原子性：原子（atom）指化学反应不可再分的基本微粒，原子性操作你应该能感受到其含义:

count++ 分解为四步，解释一下字节码的指令，
> 16 : 获取当前 count 值，并且放入栈顶\
> 19 : 将常量 1 放入栈顶\
> 20 : 将当前栈顶中两个值相加，并把结果放入栈顶\
> 21 : 把栈顶的结果再赋值给 count

-  JDK 的 rt.jar 包中的 Unsafe 类提供了 硬件级别 的原子性操作

### <a name="8">有序性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

## <a name="9">用户态与内核态</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

操作系统对程序的执行权限进行分级,分别为用户态和内核态。
- 内核态: cpu可以访问计算机所有的软硬件资源
- 用户态: cpu权限受限,只能访问到自己内存中的数据,无法访问其他资源


### <a name="10">为什么要有用户态和内核态</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
系统需要限制不同的程序之间的访问能力,防止程序获取不相同程序的内存数据,或者外围设备的数据,并发送到网络,所有cpu划分出两个权限等级用户态和内核态

内核态：用户态如果要做一些比较危险的操作直接访问硬件，很容易把硬件搞死（格式化，访问网卡，访问内存干掉、）\


### <a name="11">用户态与内核态的性能开销</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
当发生用户态到内核态的切换时，会发生如下过程:
1. 设置处理器至内核态。
2. 保存当前寄存器（栈指针、程序计数器、通用寄存器）。
3. 将栈指针设置指向内核栈地址。
4. 将程序计数器设置为一个事先约定的地址上，该地址上存放的是系统调用处理程序的起始地址。
5. 而之后从内核态返回用户态时，又会进行类似的工作。

用户态和内核态之间的切换有一定的开销，如果频繁发生切换势必会带来很大的开销，所以要想尽一切办法来减少切换

### <a name="12">避免频繁切换</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
因为线程的切换会导致用户态和内核态之间的切换，所以减少线程切换也会减少用户态和内核态之间的切换。那么如何减少线程切换呢？
- 无锁并发编程。多线程竞争锁时，加锁、释放锁会导致比较多的上下文切换
- CAS算法。使用CAS避免加锁，避免阻塞线程
- 使用最少的线程。避免创建不需要的线程协程。在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换


## <a name="13">java内存模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/java-memory.jpg)

java内存模型是关注在虚拟机中把变量值存储到内存和从内存取出变量这样的底层细节。
> 此处的变量包括了实例字段、静态字段和构成数组对象的元素，但是不包括局部变量和方法参数。 \
如果局部变量是一个reference类型，引用的对象在java堆中，但是reference本身在java栈的局部变量表中是线程私有。

java内存模型规定了所有变量都存储在主内存，每条线程还有自己的工作内存，线程的工作内存保存了被该线程使用的变量的主内存副本。线程对变量的所有操作都必须在工作内存中进行，而不能直接读写主内存的数据。不同线程的内存数据无法直接访问，均得通过主内存。
>  关于线程内存的复制，如果主内存有一个10MB的对象，线程会把对这个对象的引用、对象中在线程中的字段进行复制，但不会整个复制。

如果要把java的内存强行与Java内存区域做对应的话：
- 主内存 -> java 堆
- 工作内存 -> 虚拟机栈
- 从更基础层次上，主内存直接对应物理硬件的内存。

## <a name="14"> volatile</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
性能：volatile变量的读操作性能与普通变量几乎没有差别，但是写操作可能会慢些，因为需要插入内存屏障指令来保证处理器不乱序执行。
> 当读一个 volatile 变量时, JMM 会把该线程对应的本地内存置为无效。线程接下来将从主内存中读取共享变量。\
> 线程在【读取】共享变量时，会先清空本地内存变量值，再从主内存获取最新值\
> 线程在【写入】共享变量时，不会把值缓存在寄存器或其他地方（就是刚刚说的所谓的「工作内存」），而是会把值刷新回主内存

### <a name="15">保证变量可见性</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：当一条线程修改了这个变量的值，新值对于其他线程是立即可见的。 正常的变量修改的过程中，工作内存与主内存同步是存在延迟的，也就出现了可见性的问题。
> volatile并发下是安全的？\
> 不，只能保证取值的时候是线程安全，当获取值之后做操作如果不是原子操作，很可能变量已经被其他线程改变。如多线程对volatile变量符号运算。

### <a name="16">禁止指令重排序优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：普通的变量只能保证代码执行完成获取的结果一致，而不能保证执行顺序与代码顺序一致。

例子：如下双重加锁的例子，volatile保证了变量初始化完立即刷新到主内存中，能让其他线程可以获取到。

防止指令重排`uniqueInstance = new Singleton();` 这段代码其实是分为三步执行：
1. 为uniqueInstance 分配内存空间
2. 初始化 uniqueInstance
3. 将 uniqueInstance 指向分配的内存地址
但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1>3>2。指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。

java编译器会在生成指令系列时在适当的位置会插入 **"内存屏障"指令**来禁止特定类型的处理器重排序。
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

## <a name="17">synchronized</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="18">基本概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：synchronized 关键字解决的是多个线程之间访问资源的同步性，synchronized关键字可以保证被它修饰的方法或者代码块在任意时刻只能有一个线程执行。
1. 【进入】synchronized 块的内存语义是把在 synchronized 块内使用的变量从线程的工作内存中清除，从主内存中读取
2. 【退出】synchronized 块的内存语义事把在 synchronized 块内对共享变量的修改刷新到主内存中

#### <a name="19">synchronized关键字用法及锁范围</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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
#### <a name="20">使用有误例子</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

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

#### <a name="21">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 一个线程可以从挂起状态变为可运行状态（也就是被唤醒），即使线程没有被其他线程调用notify()/notifyAll() 方法进行通知，或被中断，或者等待超时，这就是所谓的【虚假唤醒】。虽然虚假唤醒很少发生，但要防患于未然，做法就是不停的去测试该线程被唤醒条件是否满足

volatile 与 synchronize比较
1. volatile 非阻塞 ，synchronize阻塞
2. 读取驻内存值时volatile与synchronized作用跟等价

### <a name="22">synchronized关键字JVM底层原理解析</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="23">synchronized 同步语句块的情况</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

#### <a name="24">synchronized 修饰方法的的情况</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
public class SynchronizedDemo2 {
	public synchronized void method() {
		System.out.println("synchronized 方法");
	}
}
```
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/synchronizeMethod.jpg)
方法体出现ACC_SYNCHRONIZED 标识，该标识指明了该方法是一个同步方法，JVM 通过该 ACC_SYNCHRONIZED 访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用。

### <a name="25">对象创建与加锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/objectCreate.png)

一个对象在new出来之后在内存中主要分为4个部分：
1. MarkWord这部分其实就是加锁的核心，同时还包含的对象的一些生命信息，例如是否GC、经过了几次Young GC还存活。
2. klass pointer记录了指向对象的class文件指针。
3. instance data记录了对象里面的变量数据。
4. padding作为对齐使用，对象在64位服务器版本中，规定对象内存必须要能被8字节整除，如果不能整除，那么就靠对齐来补。举个例子：new出了一个对象，内存只占用18字节，但是规定要能被8整除，所以padding=6。



### <a name="26">synchronize 的锁优化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

为什么需要进行锁优化？
> 操作系统为了系统安全分成两层，用户态和内核态 。申请锁资源的时候用户态要向操作系统老大内核态申请。Jdk1.2的时候用户需要跟内核态申请锁，然后内核态还会给用户态。这个过程是非常消耗时间的，导致早期效率特别低。有些jvm就可以处理的为什么还交给操作系统做去呢？能不能把jvm就可以完成的锁操作拉取出来提升效率，所以也就有了锁优化。

#### <a name="27">自旋锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
自旋锁：对于锁状态很短的线程，挂起和恢复线程是开销很大的，因此让线程执行一个忙等待（自旋），这就是自旋锁的技术

#### <a name="28">自适应锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
自适应锁：自适应意味着自旋的时间不固定，由前一次在同一个锁上的自旋时间及锁的状态拥有者来决定。（如果之前自旋获得过锁，进而允许本次自旋更长时间。若很少成果获得锁，那么可能直接忽略跳过等待）
  
#### <a name="29">锁消除</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
执行的方法体所有数据都不会逃逸出去被其他线程访问到，认为是线程私有的，便可以消除锁。

#### <a name="30">锁粗化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
同步块过于细化，导致多次获取锁，导致不必要的性能损耗，扩大锁的范围便可以解决这个问题。

### <a name="31">synchronize锁升级(锁膨胀)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
锁膨胀的方向：**无锁->偏向锁->轻量级锁->重量级锁**，并且膨胀方向不可逆。

#### <a name="32">偏向锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
 
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


#### <a name="33">轻量级锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
情况：当竞争锁对象的线程不多，并且线程持有锁的时间也不长时，那么此时引入轻量级锁。
> 轻量级锁是由偏向锁升级而来，当存在第二个线程申请同一个锁对象时(持有锁的线程依然存活)，偏向锁就会立即升级为轻量级锁。注意这里的第二个线程只是申请锁，不存在两个线程同时竞争锁，可以是一前一后地交替执行同步块。

轻量级锁：由于阻塞线程需要CPU从**用户状态转到内核状态**代价比较大，如果刚线程阻塞这个锁就被释放了时候代价太大，所以这个时候不会阻塞线程，而是通过CAS操作让它自旋等待锁对象的释放。

轻量级锁原理与升级过程：
1. 线程1获取轻量级锁时会把锁对象的对象头MarkWord复制一份到线程1的栈帧中创建的用于存储锁记录的空间（DisplacedMarkWord），然后使用CAS把对象中的内存替换为线程1存储的锁记录（DisplacedMarkWord）的地址。
2. 如果在线程1复制对象头的同时（在线程1CAS之前），线程2也准备获取锁，复制了对象头到线程2的锁记录空间中，但是在线程2 **CAS的时候**发现线程1已经把对象头换了，线程2的CAS失败，那么线程2就尝试使用自旋锁来等在线程1释放锁。

升级为重量级锁条件，达到以下两条件都会升级为重量级锁。
1. 长时间自旋会导致CPU消耗，CAS如果自旋10次依然没有获取到锁
2. 竞争的线程数超过cpu核数的一半，自动升级为重量级锁。

#### <a name="34">重量级锁</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
情况：当多个线程同时在竞争锁对象时，那么此时引入重量级锁。

重量级锁：阻塞所有等待竞争的线程，防止CPU空转，阻塞等待线程1释放锁后进入无锁状态重新竞争。

##### <a name="35">工作原理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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

##### <a name="36">与ReentrantLock区别</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. synchronized是JVM层面的锁；ReentrantLock是JDK层面的锁，由java代码实现
2. synchronized锁无法在代码中判断是否有所；ReentrantLock则可以通过【isLock()】判断是否获取到锁
3. synchronized是一种非公平锁；ReentrantLock既可以实现公平锁，也可以实现非公平锁
4. synchronized不可以被中断；ReentrantLock可以【lockInterruptibly】实现中断
5. 发生异常时，synchronized会自动释放锁，有javac实现；ReentrantLock需要开发者在finally中显式释放锁
6. ReentrantLock在加锁时会更灵活，可以使用【tryLock】尝试获取锁，从而避免线程阻塞


#### <a name="37">调试synchronize加锁过程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
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




## <a name="38">线程安全</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

定义：多个线程同时访问一个对象时，如果不用考虑这些线程在运行时环境的调度和交替执行，也不需要进行额外的同步，或者在调用方进行任何其他的协调操作，调用这个线程的行为都可以获得正确的结果，则这个对象是线程安全的。

### <a name="39">java中的线程安全</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
五个基本概念：
- 不可变：声明为final，没有发生this引用逃逸(this引用逃逸就是说在构造函数返回之前其他线程就持有该对象的引用，调用尚未构造完全的对象的方法可能引发错误。)
> 如：String对象时不可变的，针对其操作均会返回一个新构造的字符串对象。
- 绝对线程安全：不管运行时环境如何，调用者都不需要任何额外的同步措施。
> 如：Vector线程安全容器，对其操作当一个线程移除元素，另一个线程根据size()获得元素时，会发生越界异常，不是绝对线程安全。
- 相对线程安全：通常意义的线程安全，单次操作是线程安全。
> 如：Vector、HashTable、Collections的synchronizedCollection()
- 线程兼容：对象本身不是线程安全，但是调用时可以通过同步措施变成线程安全，如使用synchronize。
- 线程对立：指不管调用端是否采取了同步措施，都无法在多线程环境中并发使用代码，java中基本废弃相关方法。


### <a name="40">线程安全实现</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="41">互斥同步</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：并发访问时，临界区、互斥量和信号量都是常见的互斥实现方式。

1. Java的线程是直接映射到操作系统的原生内核线程之上的，因此阻塞或唤醒就不可避免的陷入用户态到核心态的转换中。
2. ReentrantLock与synchronize的区别在于多了下面几个功能：
    1. 等待可中断：等待锁的线程可选择放弃等待
    2. 公平锁：按申请锁的时间顺序获得，通过构造函数的变量实现，会明显影响吞吐量。
    3. 锁绑定多个条件：ReentrantLock可绑定多个Condition对象。

#### <a name="42">非阻塞同步</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：如果共享数据的确被征用，产生冲突再进行其他的补偿措施，如不断重试直至无竞争。又称乐观并发策略，无需把线程阻塞挂起。\
应用：乐观锁，基于冲突检测的乐观并发策略。CAS，会有ABA问题。

#### <a name="43">无同步方案</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
**无同步方案**：同步和线程安全没有必然联系。\同步只是保障存在共享数据争用时正确性的手段，如果能让一个方法本来就不涉及共享数据，那么它自然就不需要任何同步措施去保证去其正确性，因此会有一些代码天生就是线程安全的。
1. **可重入代码**(Reentrant Code)：指可以在代码执行的任何时刻终端他，转而去执行另外一段代码（包括递归调用它本身），而在控制权返回后，原来的程序不会出错，也不会对结果产生影响。
> ⚠️可重入代码的共同特性：不依赖全局变量、存储在堆上的数据和公共系统资源，用到的状态量都由参数传入，不调用非可重入的方法等。
2. 线程本地存储(Thread Local Storage)：保证共享的数据在一个线程执行。
> 相关应用：
> 1. 消费队列(生产者-消费者模式)消费的过程在一个线程进行; \
> 2. web服务器交互模型"一个请求对应一个服务器线程"; \
> 3. java中一个变量要被多线程访问使用volatile关键字;\
> 4. java中使用ThreadLocal实现线程本地存储

### <a name="44">long 和 double的型变量的特殊规则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
long和double的非原子协定：对于64位的数据类型long和double，虚拟机允许将没有被volatile修饰的64位数据的读写操作分为两次32位的操作来进行。

### <a name="45">happen-before 先行发生原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
"先行发生原则"：用于判断数据是否存在竞争，线程是否安全。

- 程序次序规则：线程按照控制流顺序执行，控制流顺序不是代码顺序，因为要考虑分支和循环。
- 管道锁定规则：一个unlock操作先行发生后面对同一个锁的lock操作。时间上先后执行。
- volatile规则：对一个变量的写操作先行发生于读动作。
- 线程启动规则：线程对象start()方法先行发生。
- 线程终止规则：线程中所有操作先行发生于对此线程的终止检测。Thread::isAlive()
- 线程中断规则：对线程的interrupt()方法的调用先行发生于中断检测。
- 对象终结规则：对象初始化完成先行发生于finalize()方法。
- 传递性: A->B, B->C  => A->C

```java
/**
 *  不符合程序次序
 *  不符合管道锁定
 *  不符合volatile
 *  不符合线程的相关原则
 *
 *  预期中A的操作时间先于B，结果应都为123
 *  结果出现不少为0的记录，此时线程不安全。
 */
public class HappenBefore {

    private class Apple{
        private  int value = 0;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

    protected class SetThread implements Runnable {
        private Apple apple;
        public SetThread(Apple apple) {
            this.apple = apple;
        }

        @Override
        public void run() {
            apple.setValue(123);
        }
    }

    protected class GetThread implements Runnable {
        private Apple apple;

        public GetThread(Apple apple) {
            this.apple = apple;
        }

        @Override
        public void run() {
            System.out.println(apple.getValue());
        }
    }

    public void testSetAndGet() {
        Apple apple = new Apple();
        Thread thread1 =new Thread(new SetThread(apple));
        Thread thread2 = new Thread(new GetThread(apple));
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        HappenBefore happenBefore = new HappenBefore();
        for (int i = 0; i < 100; i++) {
            happenBefore.testSetAndGet();
        }
    }
}
```

### <a name="46">线程安全的类定义</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 无状态的类：没有任何成员变量的类，如无任何方法的枚举类型。
2. 让类不可变
    1. 加final关键字
    2. 不提供修改成员变量，也不提供获取成员变量方法
3. 使用volatile，保证类的可见性，不能保证线程安全。适合一写多读的场景
4. 加锁和CAS，使用synchronized、lock、原子变量AtomicInteger等
    1. 如StringBuffer 修改的方法都使用synchronize修饰。
    2. 如concurrentHashMap 使用自旋加CAS修改。
    3. 使用Atomic包的基本类型，如AtomicInteger、AtomicReference、AtmoicStampReference修饰变量。
5. 编写可重入代码，具体定义见上文

#### <a name="47">枚举类为什么是线程安全？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

普通的一个枚举类
```
public enum t {
    SPRING,SUMMER,AUTUMN,WINTER;
}
```

反编译后的代码
```
public final class T extends Enum
{
    private T(String s, int i)
    {
        super(s, i);
    }
    public static T[] values()
    {
        T at[];
        int i;
        T at1[];
        System.arraycopy(at = ENUM$VALUES, 0, at1 = new T[i = at.length], 0, i);
        return at1;
    }

    public static T valueOf(String s)
    {
        return (T)Enum.valueOf(demo/T, s);
    }

    public static final T SPRING;
    public static final T SUMMER;
    public static final T AUTUMN;
    public static final T WINTER;
    private static final T ENUM$VALUES[];
    static
    {
        SPRING = new T("SPRING", 0);
        SUMMER = new T("SUMMER", 1);
        AUTUMN = new T("AUTUMN", 2);
        WINTER = new T("WINTER", 3);
        ENUM$VALUES = (new T[] {
            SPRING, SUMMER, AUTUMN, WINTER
        });
    }
}
```

1. `public final class T extends Enum`，说明，该类是继承了Enum类的，同时final关键字告诉我们，这个类也是不能被继承的。
2. 类中的几个属性和方法都是static final类型的，说明static类型的属性会在类被加载之后被初始化便不可修改。
> 创建一个enum类型是线程安全的。


相关资料：[深度分析Java的枚举类型—-枚举的线程安全性及序列化问题](https://www.cnblogs.com/z00377750/p/9177097.html)


## <a name="48">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="49">为什么jdk8要在4s后开启偏向锁？</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
在刚开始执行代码时，一定有好多线程来抢锁，如果开了偏向锁效率反而降低。
> 通过配置参数-XX:-UseBiasedLocking = false来禁用偏向锁。jdk15之后默认已经禁用了偏向锁。

> 偏向锁开启的过程中是STW（Stop The World）也就是需要暂停所有线程，详细见JVM章节末尾s


### <a name="50">System.out.println() 的实现使用synchronized</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
我们常使用的System.out.println、StringBuffer，虽然底层加了syn锁，但是基本没有多线程竞争的情况。

```
public void println(String x) {
    synchronized (this) {
        print(x);
        newLine();
    }
}
```


### <a name="51">面试问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 对象在内存中的内存布局是什么样的？
- 描述synchronized和ReentrantLock的底层实现和重入的底层原理。
- 谈谈AQS，为什么AQS底层是CAS+volatile？
- 描述下锁的四种状态和锁升级过程？
- Object  o = new Object() 在内存中占用多少字节？
- 自旋锁是不是一定比重量级锁效率高？
- 打开偏向锁是否效率一定会提升？
- 重量级锁到底重在哪里？
- 重量级锁什么时候比轻量级锁效率高，同样反之呢？


## <a name="52">参考资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

[JavaJDK1.6 之后对锁优化引入锁膨胀、锁消除、锁粗化、自旋锁、偏向锁、轻量级锁、重量级锁详解](https://mp.weixin.qq.com/s/WwOl8-4IAdcItWg63HI3tw)

重量级锁：
- [【大厂面试07期】说一说你对synchronized锁的理解？](https://www.xiaoheidiannao.com/13829.html)
- [【Java】唠唠synchronized中的重量级锁](https://www.cnblogs.com/boluopabo/archive/2004/01/13/13086172.html)
- [谈谈JVM内部锁升级过程](https://mp.weixin.qq.com/s/2yxexZUr5MWdMZ02GCSwdA)