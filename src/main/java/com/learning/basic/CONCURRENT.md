# java 并发
## 基本概念
- 并发编程可以抽象成三个核心问题: 分工、同步/协作、互斥
#### 分工
- 将当前 Sprint 的 Story 拆分成「合适」大小的 Task，并且安排给「合适」的 Team Member 去完成
  - 拆分的粒度太粗，导致这个任务完成难度变高，耗时长，不易与其他人配合；拆分的粒度太细，又导致任务太多，不好管理与追踪，浪费精力和资源。
  - 关于分工，常见的 Executor，生产者-消费者模式，Fork/Join 等，这都是分工思想的体现

#### 同步/协作
- 一个线程执行完任务，如何通知后续线程执行
-  Java SDK 中 CountDownLatch 和 CyclicBarrier 就是用来解决线程协作问题的

#### 互斥
- 互斥：同一时刻，只允许一个线程访问共享变量。分工和同步强调的是性能，但是互斥是强调正确性
- 当多个线程同时访问一个共享变量/成员变量时，就可能发生不确定性，造成不确定性主要是有可见性、原子性、有序性这三大问题，而解决这些问题的核心就是互斥

### 三大问题
#### 可见性
- 定义： 一个线程对共享变量的修改，另外一个线程能够立刻看到，我们称为可见性

- Java 内存模型规定，将所有的变量都存放在 主内存中，当线程使用变量时，会把主内存里面的变量 复制 到自己的工作空间或者叫作 私有内存
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/concurrent/picture/workingmemory.jpg)
- 刷新的场景:
>1. 主内存中有变量 x，初始值为 0
>2. 线程 A 要将 x 加 1，先将 x=0 拷贝到自己的私有内存中，然后更新 x 的值
>3. 线程 A 将更新后的 x 值回刷到主内存的时间是不固定的
>4. 刚好在线程 A 没有回刷 x 到主内存时，线程 B 同样从主内存中读取 x，此时为 0，和线程 A 一样的操作，最后期盼的 x=2 就会编程 x=1

#### 原子性
- 原子性：原子（atom）指化学反应不可再分的基本微粒，原子性操作你应该能感受到其含义:

- count++ 分解为四步
- 解释一下上面的指令，
> - 16 : 获取当前 count 值，并且放入栈顶
> - 19 : 将常量 1 放入栈顶
> - 20 : 将当前栈顶中两个值相加，并把结果放入栈顶
> - 21 : 把栈顶的结果再赋值给 count

-  JDK 的 rt.jar 包中的 Unsafe 类提供了 硬件级别 的原子性操作

#### 有序性
- 对于编译期可能对语句的执行进行了优化。
- 如双重加锁检查的 instance = new Singleton()
 - 这 1 行代码转换成了 CPU 指令后又变成了 3 个，我们理解 new 对象应该是这样的:
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
- 性能：volatile变量的读操作性能与普通变量几乎没有差别，但是写操作可能会慢些，因为需要插入内存屏障指令来保证处理器不乱序执行。
> 当读一个 volatile 变量时, JMM 会把该线程对应的本地内存置为无效。线程接下来将从主内存中读取共享变量。
>- 线程在【读取】共享变量时，会先清空本地内存变量值，再从主内存获取最新值
>- 线程在【写入】共享变量时，不会把值缓存在寄存器或其他地方（就是刚刚说的所谓的「工作内存」），而是会把值刷新回主内存
- 保证变量可见性：
  - 定义：当一条线程修改了这个变量的值，新值对于其他线程是立即可见的。
  - volatile并发下是安全的？不，只能保证取值的时候是线程安全，当获取值之后做操作如果不是原子操作，很可能变量已经被其他线程改变。如多线程对volatile变量符号运算。
  - 正常的变量修改的过程中，工作内存与主内存同步是存在延迟的，也就出现了可见性的问题。
- 禁止指令重排序优化：
  - 定义：普通的变量只能保证代码执行完成获取的结果一致，而不能保证执行顺序与代码顺序一致。
  - 例子：如下双重加锁的例子，volatile保证了变量初始化完立即刷新到主内存中，能让其他线程可以获取到。
    - 而防止指令重排uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
    -  singleton = new Singleton()实际上分为： （1）为uniqueInstance 分配内存空间（2）初始化 uniqueInstance（3）将 uniqueInstance 指向分配的内存地址
    - 但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1>3>2。指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。
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

- 当我们要保护单个资源并对其进行修改其实很简单，只需按照下图分三步走：
1. 创建受保护资源 R 的锁
2. 加锁进入临界区
3. 解锁走出临界区

## synchronized
- 【进入】synchronized 块的内存语义是把在 synchronized 块内使用的变量从线程的工作内存中清除，从主内存中读取
- 【退出】synchronized 块的内存语义事把在 synchronized 块内对共享变量的修改刷新到主内存中

- 对于普通同步方法，锁的是当前实例对象，通常指 this
- 对于静态同步方法，锁的是当前类的 Class 对象，如 ThreeSync.class
- 对于同步方法块，锁的是 synchronized 括号内的对象
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
```
- 银行转账的方法，典型的忽略锁和资源的指向关系，这里的Account target并没有保护作用
```
class Account {
  private int balance;
  // 转账
  synchronized void transfer(
      Account target, int amt){
    if (this.balance > amt) {
      this.balance -= amt;
      target.balance += amt;
    }
  } 
}
```
- 一个线程可以从挂起状态变为可运行状态（也就是被唤醒），即使线程没有被其他线程调用notify()/notifyAll() 方法进行通知，或被中断，或者等待超时，这就是所谓的【虚假唤醒】。虽然虚假唤醒很少发生，但要防患于未然，做法就是不停的去测试该线程被唤醒条件是否满足
- volatile 非阻塞 ， synchronize阻塞
- volatile 与 synchronized 在处理哪些问题是相对等价的？
    - 如果写入变量值不依赖变量当前值，那么就可以用 volatile。
    - 因此在于获取值时候，
- 为什么说 volatile 是 synchronized 弱同步的方式？

-  ArrayBlockingQueue.java

![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/concurrent/picture/threadstate.jpg)
