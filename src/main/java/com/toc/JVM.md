<a name="index">**Index**</a>

<a href="#0">JVM </a>  
&emsp;<a href="#1">1. 面试题</a>  
&emsp;<a href="#2">2. 虚拟机数据区</a>  
&emsp;&emsp;<a href="#3">2.1. 程序计数器</a>  
&emsp;&emsp;<a href="#4">2.2. Java虚拟机栈</a>  
&emsp;&emsp;<a href="#5">2.3. 本地方法栈</a>  
&emsp;&emsp;<a href="#6">2.4. Java堆</a>  
&emsp;&emsp;<a href="#7">2.5. 方法区</a>  
&emsp;&emsp;<a href="#8">2.6. 运行时常量池</a>  
&emsp;&emsp;<a href="#9">2.7. HotSpot 的后台线程</a>  
&emsp;&emsp;<a href="#10">2.8. 应用</a>  
&emsp;<a href="#11">3. 垃圾收集器与内存分配策略</a>  
&emsp;&emsp;<a href="#12">3.1. 判断对象是否已死的方法</a>  
&emsp;&emsp;<a href="#13">3.2. 回收方法区</a>  
&emsp;&emsp;<a href="#14">3.3. 垃圾收集算法</a>  
&emsp;&emsp;&emsp;<a href="#15">3.3.1. 标记-清除算法</a>  
&emsp;&emsp;&emsp;<a href="#16">3.3.2. 标记-复制算法</a>  
&emsp;&emsp;&emsp;<a href="#17">3.3.3. 标记-整理算法</a>  
&emsp;&emsp;<a href="#18">3.4. 新生代垃圾回收</a>  
&emsp;&emsp;&emsp;<a href="#19">3.4.1. 相关问题</a>  
&emsp;&emsp;<a href="#20">3.5. 经典的垃圾收集器</a>  
&emsp;&emsp;&emsp;<a href="#21">3.5.1. Serial收集器</a>  
&emsp;&emsp;&emsp;<a href="#22">3.5.2. Serial Old收集器</a>  
&emsp;&emsp;&emsp;<a href="#23">3.5.3. ParNew收集器</a>  
&emsp;&emsp;&emsp;<a href="#24">3.5.4. Parallel Scavenge 收集器</a>  
&emsp;&emsp;&emsp;<a href="#25">3.5.5. Parallel Old收集器</a>  
&emsp;&emsp;&emsp;<a href="#26">3.5.6. CMS(Concurrent Mark Sweep)收集器</a>  
&emsp;&emsp;&emsp;<a href="#27">3.5.7. Garbage First 收集器</a>  
&emsp;&emsp;&emsp;<a href="#28">3.5.8. CMS 与 G1 对比</a>  
&emsp;&emsp;&emsp;<a href="#29">3.5.9. 其他的垃圾收集器</a>  
&emsp;&emsp;&emsp;<a href="#30">3.5.10. 选用收集器的三个因素</a>  
&emsp;<a href="#31">4. java虚拟机监控工具</a>  
&emsp;&emsp;<a href="#32">4.1. jps</a>  
&emsp;&emsp;<a href="#33">4.2. jstat</a>  
&emsp;&emsp;<a href="#34">4.3. jinfo</a>  
&emsp;&emsp;<a href="#35">4.4. jmap 和 jhat</a>  
&emsp;&emsp;<a href="#36">4.5. jstack</a>  
&emsp;&emsp;<a href="#37">4.6. jconsole</a>  
&emsp;<a href="#38">5. 类文件</a>  
&emsp;&emsp;<a href="#39">5.1. 类文件结构</a>  
&emsp;&emsp;<a href="#40">5.2. 双亲委派模型</a>  
&emsp;&emsp;&emsp;<a href="#41">5.2.1. 自定义类加载器</a>  
&emsp;&emsp;&emsp;<a href="#42">5.2.2. 破坏双亲委派模型</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#43">5.2.2.1. JDBC破坏双亲委派模型</a>  
&emsp;&emsp;<a href="#44">5.3. 类初始化的时机</a>  
&emsp;&emsp;<a href="#45">5.4. 类的生命周期</a>  
&emsp;&emsp;&emsp;<a href="#46">5.4.1. 加载</a>  
&emsp;&emsp;&emsp;<a href="#47">5.4.2. 验证</a>  
&emsp;&emsp;&emsp;<a href="#48">5.4.3. 准备</a>  
&emsp;&emsp;&emsp;<a href="#49">5.4.4. 解析</a>  
&emsp;&emsp;&emsp;<a href="#50">5.4.5. 初始化</a>  
&emsp;&emsp;&emsp;<a href="#51">5.4.6. 卸载</a>  
&emsp;<a href="#52">6. 对象创建及使用</a>  
&emsp;&emsp;<a href="#53">6.1. 对象分配内存的方式</a>  
&emsp;&emsp;<a href="#54">6.2. 对象内存分布</a>  
&emsp;&emsp;<a href="#55">6.3. 对象的访问</a>  
&emsp;&emsp;<a href="#56">6.4. 对象引用</a>  
&emsp;<a href="#57">7. JDK编译期</a>  
&emsp;&emsp;<a href="#58">7.1. 编译期做的工作</a>  
&emsp;<a href="#59">8. 堆内存的设置要点</a>  
&emsp;<a href="#60">9. CMS + ParNew收集器的流程梳理</a>  
&emsp;&emsp;<a href="#61">9.1. young区域(年轻代)</a>  
&emsp;&emsp;<a href="#62">9.2. old区域(老年代)</a>  
&emsp;&emsp;&emsp;<a href="#63">9.2.1. CMS GC原因</a>  
&emsp;&emsp;&emsp;<a href="#64">9.2.2. CMS GC 垃圾回收模式</a>  
&emsp;&emsp;&emsp;<a href="#65">9.2.3. 其他老年代问题</a>  
&emsp;&emsp;<a href="#66">9.3. 相关文章</a>  
&emsp;<a href="#67">10. Java虚拟机内存调优</a>  
&emsp;&emsp;<a href="#68">10.1. 基本概念重述</a>  
&emsp;&emsp;&emsp;<a href="#69">10.1.1. 工具整理</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#70">10.1.1.1. 命令行终端</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#71">10.1.1.2. 可视化界面</a>  
&emsp;&emsp;<a href="#72">10.2. GC 调优目的</a>  
&emsp;&emsp;<a href="#73">10.3. GC 调优策略</a>  
&emsp;&emsp;<a href="#74">10.4. 调优指标</a>  
&emsp;&emsp;<a href="#75">10.5. 问题排查思路</a>  
&emsp;&emsp;<a href="#76">10.6. 案例</a>  
&emsp;&emsp;&emsp;<a href="#77">10.6.1. 美团技术案例（基于CMS JDK1.8）</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#78">10.6.1.1. 场景一：动态扩容引起的空间震荡</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#79">10.6.1.2. 场景二：显式 GC 的去与留 (System.gc)</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#80">10.6.1.3. 场景三：MetaSpace 区 OOM</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#81">10.6.1.4. 场景四：过早晋升 </a>  
&emsp;&emsp;&emsp;&emsp;<a href="#82">10.6.1.5. 场景五：CMS Old GC 频繁 </a>  
&emsp;&emsp;&emsp;&emsp;<a href="#83">10.6.1.6. 场景六：单次 CMS Old GC 耗时长</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#84">10.6.1.7. 场景七：内存碎片&收集器退化</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#85">10.6.1.8. 场景八：堆外内存 OOM</a>  
&emsp;&emsp;&emsp;&emsp;<a href="#86">10.6.1.9. 场景九：JNI 引发的 GC 问题</a>  
&emsp;&emsp;&emsp;<a href="#87">10.6.2. 不恰当的数据结构导致内存过大</a>  
&emsp;&emsp;&emsp;<a href="#88">10.6.3. 堆外内存导致溢出错误</a>  
&emsp;&emsp;&emsp;<a href="#89">10.6.4. 异步系统Socket连接</a>  
&emsp;&emsp;&emsp;<a href="#90">10.6.5. Evosuite 自动生成单元测试</a>  
&emsp;&emsp;&emsp;<a href="#91">10.6.6. 其他案例分析资料</a>  
&emsp;&emsp;&emsp;<a href="#92">10.6.7. 其他建议</a>  
# <a name="0">JVM </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
大部分参考周志明【深入理解Java虚拟机】
- 附上官网文档搭配食用 [java8官网文档](https://docs.oracle.com/javase/specs/jvms/se8/html/index.html)
## <a name="1">面试题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 介绍下 Java 内存区域（运行时数据区）
- Java 对象的创建过程（五步，建议能默写出来并且要知道每一步虚拟机做了什么）
- 对象的访问定位的两种方式（句柄和直接指针两种方式）
- 如何判断对象是否死亡（两种方法）。
- 简单的介绍一下强引用、软引用、弱引用、虚引用（虚引用与软引用和弱引用的区别、使用软引用能带来的好处）。
- 如何判断一个常量是废弃常量
- 如何判断一个类是无用的类
- 垃圾收集有哪些算法，各自的特点？
- HotSpot 为什么要分为新生代和老年代？
- 常见的垃圾回收器有哪些？
- 介绍一下 CMS,G1 收集器。
- Minor Gc 和 Full GC 有什么不同呢？
  
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/heap-detail.jpg)

## <a name="2">虚拟机数据区</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="3">程序计数器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：可以看作是当前线程所执行的字节码的行号指示器，为线程隔离的数据区。
- java多线程切换时，每个线程独立的程序计数器，各条线程之间的计数器互不影响，独立存储，保证了线程切换后能恢复到正确的位置。
- 唯一一个无OOM的区域
 
### <a name="4">Java虚拟机栈</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：每个方法执行的时候，Java虚拟机都会同步的创建一个栈帧用于储存局部变量表、操作数栈、动态链接、方法出口等信息。每个方法被调用直至执行完毕的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。
> 栈帧（Stack Frame）用于存储局部变量表、操作数栈、动态链接、方法出口等信息
- 局部变量表存放了编译期可知的各种Java虚拟机基本数据类型（boolean、byte、char、short、int、float、long、double）、对象引用（reference类型）和returnAddress类型（指向一条字节码指令的地址）、
- 在栈深度溢出或栈扩展失败时分别抛出StackOverFlowError和OutOfMemoryError的异常。 
 
### <a name="5">本地方法栈</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：为虚拟机使用到的本地（Native）方法服务。
- HotSpot直接把本方法栈和虚拟机栈合二为一。
- 在栈深度溢出或栈扩展失败时分别抛出StackOverFlowError和OutOfMemoryError的异常。
 
### <a name="6">Java堆</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义:是虚拟机所管理的内存中最大的一块。Java堆是被所有线程共享的一块内存区域，在虚拟机启动时创建。
- 参数-Xmx和-Xms 最大堆内存和最小堆内存
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/hotstop-heap.jpg)

### <a name="7">方法区</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：是被各个线程共享的内存区域，它用于存储已被虚拟机加载的类型信息、常量、静态变量、即时编译器编译后的代码缓存等数据。
> JDK8以前使用永久代来实现方法区（-XX:MaxPermSize 设置上限）\
> 方法区类似于接口，永久代类似于实现类的关系。使用永久代的时候，可以设置内存上限，而且不同的虚拟机的实现不一样，因此更容易遇到内存溢出的问题。
 
### <a name="8">运行时常量池</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：运行时常量池是方法区的一部分。Class文件除类字段、方法、接口等描述信息外，还有一项信息是常量池表，用于存放编译期生成的各种字面量和符号引用，在类加载后存放到运行时常量池中。
- 运行时常量池具备动态性，运行期间可以将新的常量放入池中，当无法申请到空间抛出OutOfMemoryError异常。 


> 在 Java 7 之前，JVM 将 Java String Pool 放置在 永久代空间（java7方法区的实现）中，该空间具有固定大小——它不能在运行时扩展并且不符合垃圾收集条件。\
在永久代（而不是堆）中使用字符串的风险是，如果我们创建太多字符串，我们可能会从 JVM 中得到 OutOfMemory 错误。\
从 Java 7 开始，Java String Pool 存储在 Heap 空间中，由 JVM 进行垃圾回收。 这种方法的优点是降低了 OutOfMemory 错误的风险，因为未引用的字符串将从池中删除，从而释放内存。

### <a name="9">HotSpot 的后台线程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/hotspotThread.jpg)


### <a name="10">应用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```java

public class JHSDB_TestCase {

    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instantObj = new ObjectHolder();

        void foo(){
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }

    private static class ObjectHolder{}

    public static void main(String[] args) {
        Test test = new JHSDB_TestCase.Test();
        test.foo();
    }
}
```
staticObj、instantObj、localObj三个变量本身(**而不是它们所指向的对象**)存放在哪里？\
staticObj随着Test的信息类型存放在方法区，instantObj随着Test对象存放在java堆，localObj则存放在foo()方法栈帧的局部变量表。
- staticObj对象与class对象存放在一起，存储于Eden Java堆中。（JDK7及以后的版本）
- instantObj对象存放在Eden Java堆中
- localObj对象存放在Eden Java堆中

## <a name="11">垃圾收集器与内存分配策略</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
程序计数器、虚拟机栈、本地方法栈3个区域随线程而生而灭，因此这几个区域的内存分配和回收都具备确定性，不需要过多考虑回收问题。

### <a name="12">判断对象是否已死的方法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
引用计数法：
- 定义：在对象中添加一个引用计数器，有一个地方引用时，计数器值加一，引用失效时减一。
- 优点：原理简单，判定效率也很高。
- 缺点：难以解决对象之间互相循环引用的问题。

可达性分析算法：
- 定义：通过一系列成为“GC Roots”的根对象作为起始节点集，从这些节点开始，根据引用关系向下搜索，搜索过程所走过的路径称为“引用链”。弱某对象到GC Roots间没有任何引用链相连，证明此对象是不可能再被使用的。
- GC Roots的对象分为以下几种：
     1. 虚拟机栈中的引用对象，入线程调用方法堆栈的参数、局部变量、临时变量等。 
     2. 在方法区中类静态属性引用的对象。如Java类的引用类型静态变量。
     3. 在方法区中常量引用对象，如字符串常量池的引用。
     4. 在本地方法栈中的JNI（Native方法）引用的对象。
     5. Java虚拟机内部的引用，如基本类型对应的Class对象，一些常驻异常对象（NullPointException)等，还有系统类加载器。
     6. 所有被同步锁(synchronize关键字)持有的对象。
     7. 反映Java虚拟机内部情况的JMXBean、JVMTI中注册的回调、本地缓存代码等。

### <a name="13">回收方法区</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
方法区的回收主要是两部分内容：废弃的常量和不再使用的类型。
- 废弃的常量的例子：字符创常量进入到常量池中，但当前系统有没有任何一个字符串对象的值为“java”，则该常量就会被系统清理出常量池。
- 不在使用的类，需同时满足一下三个条件：
  - 该类的所有实例已经被回收，也就是java对重不存在该类及其任何派生的子类实例。
  - 加载该类的类加载器已经被回收。正常很难达成。如OSGi、JSP的重加载会产生。
  - 该类对应的java.lang.Class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法。
  
### <a name="14">垃圾收集算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
弱分代假说：绝大多数对象都是朝生夕灭。\
强分代假说：熬过越多次垃圾手机过程的对象就越难消亡。\
跨代引用假说：存在于新生代的对象可能会引用老年代的对象。因此该假说说明的是，存在互相引用关系的对象，是应该倾向于同时生存或者同时死亡。
- 解决方案，在新生代上建立一个全局的数据结构（记忆集），这个结构吧老年代划分成若干小块，表示出老年代的哪一块内存会存在跨代引用。之后发生Minor GC时，只有包含跨代引用的小块内存才会加入到GC Root的扫描.


#### <a name="15">标记-清除算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：算法分为两个阶段，一个阶段就是标记出所有需要回收的对象，在标记完成后，统一回收掉所有标记的对象，当然也可以反过来标记存活的对象，统一回收未标记对象。

缺点：
1. 执行效率不稳定，如果java堆中包含大量对象，并且其中大部分是需要回收的，当对象的数量增长，标记跟清除的执行效率都会越来越低。
2. 内存碎片化问题，标记跟清除之后会产生大量不连续的内存碎片，空间碎片太多可能导致后续程序在分配大对象的时候不得不触发另一次垃圾收集动作。

#### <a name="16">标记-复制算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义:为了解决标记算法面对大量可回收对象时执行效率低及空间碎片化的问题，该算法将内存分为两个大小相等的空间，每次只使用其中一块。当一块的内存使用完了，就将还存货的对象复制到另一块上去，然后把已使用过的空间一次性清理干净。

缺点：
1. 当内存中大多数对象都是存活的，那么该算法会产生大量的复制开销。
2. 将可用的对象内存缩小为原来的一半，空间浪费未免太多了。
3. 老年代一般不直接使用该算法，因为老年代对象存货率较高，复制开销太大。针对100%都存活的极端情况，
   
#### <a name="17">标记-整理算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：与标记-清除算法本质区别为，移动存活的对象。而移动回收后存活的对象是一项优缺点并存的风险决策。

移动对象的优缺点:
- 缺点：在老年代这种每次回收都有大量存活的区域，移动存活对象并更新所有引用这些对象的地方会是一种极其负重的工作，工作期间必须暂停用户应用程序才能进行。
- 优点：内存规整，解决了空间碎片化问题。空间碎片化问题只能依赖更复杂的内存分配器和内存访问器来解决。
- 综合的解决方案，平常都是用标记-清除算法，直到空间碎片化已经影响到对象分配，再使用标记-整理算法。


### <a name="18">新生代垃圾回收</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. eden、 survivor From 复制到 survivor To，年龄+1。
> 首先，把 Eden 和 survivor From 区域中存活的对象复制到 survivor To 区域（如果有对象的年龄以及达到了老年的标准，则赋值到老年代区），同时把这些对象的年龄+1（如果 ServicorTo 不够位置了就放到老年区）；
2. 清空 eden、 survivor From。
> 然后，清空 Eden 和 survivor From 中的对象；
3. survivor To 和 survivor From 互换
> 最后， survivor To 和 survivor From 互换，原 survivor To 成为下一次 GC 时的 survivor From区。
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/edenGc.jpg)


#### <a name="19">相关问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
为什么需要Survivor区域？
> 防止对象过早进入Old，导致Old频繁Full GC。Survivor具有预筛选保证，只有对象到一定岁数才会送往老年代，Survivor区可以减少被送到老年代的对象，进而减少Full GC发生。

为什么需要两个Survivor区域？
> 如果只有一个Survivor，每次垃圾回收年龄+1，会有部分对象进入老年代，导致Survivor的空间变成碎片化空间，最后触发minor gc。使用两个Survivor并进行交换，就保证了两个Survivor区，一个为空，另一个是非空且无碎片保存的。


- https://blog.csdn.net/weixin_33881050/article/details/91448770



### <a name="20">经典的垃圾收集器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/garbage-collector.jpg)

#### <a name="21">Serial收集器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义:一个单线程的垃圾收集器，在垃圾收集时必须暂停其他所有工作线程，直到收集结束。
- 对于新生代采取复制算法暂停所有线程，对于老年代使用标记-整理算法同样暂停所有线程。
- 缺点：需要暂停用户线程。
- 优点：
  1. 简单高效、对于内存资源受限的环境，它是所有收集器里额外内存消耗最小的。
  2. 单核处理器或者处理器核心较少的环境来说，Serial由于没有线程交互的开销，可以专心做垃圾回收自然可以获得最高的单线程收集效率。

#### <a name="22">Serial Old收集器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：使用标记-整理算法。
- 用途：可以Parallel Scavenge收集器搭配使用，另一种是作为CMS收集器发生失败的后背方案。
  
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/serial-1.jpg)

#### <a name="23">ParNew收集器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 定义：Serial收集器的多线程并行版本。
- 唯一的优点仅有ParNew和Serial可以和CMS收集器配合工作，以后ParNew将会合并入CMS，成为其处理新生代的组成部分。
- 随着可以被使用的处理核心增加，ParNew对于垃圾收集时系统可以高效利用，默认开启的收集线程数和处理器核心数量相同。
- 新生代：标记复制。老年代：标记-整理
- 常使用参数：-XX:SurvivorRatio、-XX:PretenureSizeThreshold、-XX:HandlePromotionFailure

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/parNew-1.jpg)

#### <a name="24">Parallel Scavenge 收集器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：新生代收集器，同样基于标记-复制算法，能够并行收集的多线程收集器。特点是达到一个可控制的吞吐量。
`吞吐量= 运行客户代码时间/(运行用户代码时间+运行垃圾收集时间)`

虚拟机完成用户任务及垃圾收集用了100分钟，其中垃圾收集用了1分钟，吞吐量=99%
- -XX:MaxGCPauseMills：控制最大垃圾收集时间参数
> 允许设置的是一个大于0的毫秒数，垃圾收集停顿时间缩短是以牺牲吞吐量和新生代空间为代价换区的。调小新生代会缩短垃圾回收时间，若调的太小会导致垃圾收集变得频繁。
- -XX:GCTimeRatio：设置吞吐量大小时间
    - 设置的值应当是大于0小于100的整数，也就是垃圾回收时间占总时间的比率为吞吐量的倒数。
    - 设置成19，那允许垃圾回收时间为总时间的5%(1/(1+19))，默认值为99,允许最大1%的时间进行垃圾回收。
- -XX:+UseAdaptiveSizePolicy: 开启自适应的调整策略。

#### <a name="25">Parallel Old收集器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：Parallel Scavenge收集器的老年版本，支持多线程并发收集，基于标记-整理算法。
- 与Parallel Scavenge搭配作为“吞吐量优先”的收集器搭配组合

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/parallel.jpg)

#### <a name="26">CMS(Concurrent Mark Sweep)收集器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：一种以获取最短回收停顿时间为目标的收集器，工作于老年代。

运行过程解析：基于标记-清除算法实现，具体步骤如下，
- 初始标记：仅仅标记一下GC Root对象能直接关联到的对象，速度很快，需要暂停所有线程。
- 并发标记：从GC Root关联对象开始遍历整个对象图的过程，可以与用户线程共同执行。
- 重新标记：因用户程序继续运行而导致标记产生变动的那部分对象的标记记录，通常比初始标记长远比并发标记段。
- 并发清除：清理删除掉标记阶段判断的已经死亡的对象，由于不需要移动对象，因此可以与用户线程共同执行。

特点：
1. 对处理器资源非常敏感。CMS默认启动的回收线程数是(处理器数量+3)/4，因此弱核心数量在4个以上，占用内存不超过25%。若核心数量小于4，则占用内存过大。
2. 无法处理“浮动垃圾”，有可能出现并发模式失败进而导致一次Full GC。浮动垃圾为出现在标记过程结束之后产生的对象。因为CMS要支持手机过程中与用户线程并存，因此不能在老年代几乎被填满时再运行，需要预留一部分空间供并发收集的程序运行。
    - JDK5中设置CMS在老年代使用了68%便会激活，JDK6默认的设置提高到92%。当运行预留的内存无法满足程序分配新对象的需要，就会出现一次“并发失败”。后备预案为冻结用户线程，启用Serial Old进行老年代的垃圾收集。
    - `-XX:CMSInitiatingOccupancyFraction `可以设置触发CMS收集的百分比。
> 并发收集失败：收集过程中，老年代被填满；收集完成后，收集的空间仍然无法满足被使用；浮动垃圾
- 参数-XX:CMSFullGCsBeforeCompaction：作用是要求CMS收集器在执行过若干次不整理的Full GC之后，下一次先进行碎片整理(默认值为0，表示每次FullGC都进行碎片整理)
  
[CMS官网说明](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/cms.html#concurrent_mode_failure)

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/cms-1.jpg)

#### <a name="27">Garbage First 收集器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：面向服务端应用的垃圾收集器，基于Region的堆内存布局进行垃圾收集，每一个Region都可以根据需要扮演新生代的Eden空间、Survivor空间和老年代空间。Region中还有一类特殊的Humongous区域，专门用来存储大对象，G1认为只要超过了一个Region一半的对象即可认为是大对象。对于Humongous区域，正常当做老年代一部分。

- 参数Region的大小可通过-XX:G1HeapRegionSize设定，取值范围为1M~32M，为2的N次幂。
- 用户设定的允许收集停顿时间使用参数-XX:MaxGCPauseMills指定，默认为200毫秒。调的调小会导致每次的回收集只占内存的很小一部分，收集的速度慢于分配的速度导致垃圾堆积，进而引发Full GC。正常设置为100~300毫秒之间。

运行步骤：
1. 初始标记：标记GC Root对象能直接关联的对象并修改TAMS指针的值为正确的空区域。需要暂停线程，但是时间很短，借用进行Minor GC时同步完成。
2. 并发标记：根据GC Root进行可达性分析，扫描对象图。完成扫描后，处理SATB记录下并发时有引用变动的对象。
3. 最终标记：短暂暂停用户线程，处理并发阶段结束后，少量的SATB记录。
4. 筛选回收：更新Region的统计数据，进行回收价值和成本的排序，根据用户期望的停顿时间来构建回收集合。回收集合的存活对象复制到空的Region，再清理旧的Region。涉及到对象移动，需要暂停用户线程，使用多线程并行完成移动。

G1整体是基于标记-整理算法实现的收集器，但从局部优势基于标记-复制算法实现。在执行标记整理的时候，还进行了压缩的工作，这是之前的垃圾收集器都没有的。

特点:
1. 避免在整个Java堆进行全区域的垃圾回收，而是让G1跟踪每个Region的垃圾回收的价值及回收所需的时间，在后台维护一个优先级表。根据用户设定的允许收集停顿时间，优先回收价值收益最大的Region。(使用参数-XX:MaxGCPauseMills指定)
2. G1收集器每个Region都需要自己的记忆集，记录跨区域引用，因此比其他收集器要耗费内存，大约为java堆内存容量10%~20%。
3. 通过在Region中划分空间(使用两TAMS指针，标记一块区域)用于并发回收的新对象分配，解决并发标记阶段与用户线程互不干扰。同样若内存分配速度大于内存回收速度，也许冻结用户线程Full GC。
4. CMS使用增量更新算法，而G1使用原始快照(SATB)算法来解决，用户线程改变对象的引用关系，不打破原有的对象图结构，防止标记错误。
5. 通过可靠停顿预测模型的建立：根据每个Region的回收成本，分析出收集的平均值、标准偏差、置信度等统计信息。
- 缺点：内存占用过高，在小内存应用上CMS的表现大于G1。
  
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/g1.jpg)
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/g1-memory.jpg)

[G1官网说明](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/g1_gc.html)
#### <a name="28">CMS 与 G1 对比</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
G1计划作为并发标记扫描收集器（CMS）的长期替代品。
1. 垃圾回收理念不同：CMS基于分代收集理念设计。G1基于分区收集理念设计。
2. 整理：G1在GC的时候都会做垃圾的碎片整理，而CMS收集器只在Full GC STW时才会做内存压缩整理。
3. 可停顿时间：G1是一种兼顾吞吐量和停顿时间的 GC 实现，其可靠停顿预测模型可以设定目标收集停顿时间，可以实现更短的GC停顿。
4. 对象记录算法：对于对象记录CMS使用增量更新算法，而G1使用原始快照(SATB,snapshot-at-the-beginning)记录存活对象。
5. 收集方式：G1使用混合收集的方式。G1可以扫描年轻代和一小部分老年代，但这意味着比简单地只扫描老年代、完全的快得多。
6. String重复数据删除。G1可以配置针对String的重复数据进行删除，而重复的数据将指向同一个char[] array。`-XX:+UseStringDeduplication`

- CMS对处理器资源非常敏感。CMS默认启动的回收线程数是(处理器数量+3)/4，因此弱核心数量在4个以上，占用内存不超过25%。若核心数量小于4，则占用内存过大。
- G1针对具有大内存的多处理器机器，因为其`Remembered Sets`的记忆集的设计，需要占用更多内存。

[what’s new in Java 8](https://www.overops.com/blog/garbage-collectors-serial-vs-parallel-vs-cms-vs-the-g1-and-whats-new-in-java-8/)
#### <a name="29">其他的垃圾收集器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- Shenandoah 收集器：仅存在OpenJdk，区别G1的特点为支持并发整理，使用转发指针和读屏障实现。
- ZGC 收集器：Region具有动态性，并分为大中小三个Region，使用染色指针技术实现并发整理算法。
- Epsilon收集器：无操作收集器。

#### <a name="30">选用收集器的三个因素</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 如果是数据分析、科学计算类任务，目标是尽快可以算出结果，那么吞吐量为主要关注点。如果为SLA应用，停顿时间直接影响任务质量，严重甚至会导致事务超时，那么延迟是主要的关注点。
2. 使用运行的基础设施的指标。
3. JDK对应的版本。

## <a name="31">java虚拟机监控工具</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="32">jps</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
jps (JVM Process Status）: 类似 UNIX 的 ps 命令。用户查看所有 Java 进程的启动类、传入参数和 Java 虚拟机参数等信息；
```
[root@iZuf6ee30yhz3x9bqf63clZ apache-tomcat-8.5.31]# jps -l
3796 sun.tools.jps.Jps
2903 org.apache.catalina.startup.Bootstrap
[root@iZuf6ee30yhz3x9bqf63clZ apache-tomcat-8.5.31]# jps -m
3811 Jps -m
2903 Bootstrap start
[root@iZuf6ee30yhz3x9bqf63clZ apache-tomcat-8.5.31]# jps -v
3828 Jps -Dapplication.home=/usr/java/jdk1.8.0_221-amd64 -Xms8m
2903 Bootstrap -Djava.util.logging.config.file=/usr/local/apache-tomcat-8.5.31/conf/logging.properties -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djdk.tls.ephemeralDHKeySize=2048 -Djava.protocol.handler.pkgs=org.apache.catalina.webresources -Dorg.apache.catalina.security.SecurityListener.UMASK=0027 -Dignore.endorsed.dirs= -Dcatalina.base=/usr/local/apache-tomcat-8.5.31 -Dcatalina.home=/usr/local/apache-tomcat-8.5.31 -Djava.io.tmpdir=/usr/local/apache-tomcat-8.5.31/temp
```
### <a name="33">jstat</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
jstat（ JVM Statistics Monitoring Tool）: 用于收集 HotSpot 虚拟机各方面的运行数据;

jstat -gc -h3 31736 1000 10表示分析进程 id 为 31736 的 gc 情况，每隔 1000ms 打印一次记录，打印 10 次停止，每 3 行后打印指标头部。
 ```
[root@iZuf6ee30yhz3x9bqf63clZ apache-tomcat-8.5.31]# jstat -gc -h3 2903 1000 10
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT   
4160.0 4160.0 2589.7  0.0   33792.0   8242.5   84096.0    55749.8   61440.0 60173.8 7424.0 7132.2    306    1.047   7      0.345    1.393
4160.0 4160.0 2589.7  0.0   33792.0   8242.5   84096.0    55749.8   61440.0 60173.8 7424.0 7132.2    306    1.047   7      0.345    1.393
4160.0 4160.0 2589.7  0.0   33792.0   8242.5   84096.0    55749.8   61440.0 60173.8 7424.0 7132.2    306    1.047   7      0.345    1.393
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT   
4160.0 4160.0 2589.7  0.0   33792.0   8242.5   84096.0    55749.8   61440.0 60173.8 7424.0 7132.2    306    1.047   7      0.345    1.393
4160.0 4160.0 2589.7  0.0   33792.0   8242.5   84096.0    55749.8   61440.0 60173.8 7424.0 7132.2    306    1.047   7      0.345    1.393
4160.0 4160.0 2589.7  0.0   33792.0   8242.5   84096.0    55749.8   61440.0 60173.8 7424.0 7132.2    306    1.047   7      0.345    1.393
```

- 相关资料：https://www.xttblog.com/?p=3175
### <a name="34">jinfo</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
jinfo (Configuration Info for Java) : Configuration Info forJava,显示虚拟机配置信息;
 ```
C:\Users\SnailClimb>jinfo  -flag MaxHeapSize 17340
-XX:MaxHeapSize=2124414976
C:\Users\SnailClimb>jinfo  -flag PrintGC 17340
-XX:-PrintGC
```
### <a name="35">jmap 和 jhat</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
jmap (Memory Map for Java) :生成堆转储快照;
```
[root@iZuf6ee30yhz3x9bqf63clZ apache-tomcat-8.5.31]# jmap -dump:format=b,file=elasticfoam.bin 2903
Dumping heap to /usr/local/apache-tomcat-8.5.31/elasticfoam.bin ...
Heap dump file created
```

jhat (JVM Heap Dump Browser ) : 用于分析 heapdump 文件，它会建立一个 HTTP/HTML 服务器，让用户可以在浏览器上查看分析结果;\
与上面的jmap配合使用，分析heapdump的堆信息，会生成具体的服务器。
```
[root@iZuf6ee30yhz3x9bqf63clZ apache-tomcat-8.5.31]# jhat elasticfoam.bin 
Reading from elasticfoam.bin...
Dump file created Sat Nov 07 14:02:33 CST 2020
Snapshot read, resolving...
Resolving 131419 objects...
Chasing references, expect 26 dots..........................
Eliminating duplicate references..........................
Snapshot resolved.
Started HTTP server on port 7000
Server is ready.
```
### <a name="36">jstack</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
jstack (Stack Trace for Java):生成虚拟机当前时刻的线程快照，线程快照就是当前虚拟机内每一条线程正在执行的方法堆栈的集合。

```
Found one Java-level deadlock:
=============================
"线程 2":
  waiting to lock monitor 0x000000000333e668 (object 0x00000000d5efe1c0, a java.lang.Object),
  which is held by "线程 1"
"线程 1":
  waiting to lock monitor 0x000000000333be88 (object 0x00000000d5efe1d0, a java.lang.Object),
  which is held by "线程 2"

Java stack information for the threads listed above:
===================================================
"线程 2":
        at DeadLockDemo.lambda$main$1(DeadLockDemo.java:31)
        - waiting to lock <0x00000000d5efe1c0> (a java.lang.Object)
        - locked <0x00000000d5efe1d0> (a java.lang.Object)
        at DeadLockDemo$$Lambda$2/1078694789.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:748)
"线程 1":
        at DeadLockDemo.lambda$main$0(DeadLockDemo.java:16)
        - waiting to lock <0x00000000d5efe1d0> (a java.lang.Object)
        - locked <0x00000000d5efe1c0> (a java.lang.Object)
        at DeadLockDemo$$Lambda$1/1324119927.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:748)
Found 1 deadlock.
```
    
一个linux的排除高CUP线程的排查案例
 ```
top -c //查看所有进程
top -Hp xxx（PID）  // 查看进程具体的线程ID cup情况
jstack -l pid > filename // 输出当前快照
cat filename| grep '线程ID（16进制）' -C 8     // 查找匹配线程，-C 查看前后多少行数据
```
### <a name="37">jconsole</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
JConsole:Java 监视与管理控制台，很强大，可以检测死锁，查看堆的内存释放情况。
- 如果需要使用 JConsole 连接远程进程，可以在远程 Java 程序启动时加上下面这些参数:
```
-Djava.rmi.server.hostname=外网访问 ip 地址 
-Dcom.sun.management.jmxremote.port=60001   //监控的端口号
-Dcom.sun.management.jmxremote.authenticate=false   //关闭认证
-Dcom.sun.management.jmxremote.ssl=false
```

## <a name="38">类文件</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="39">类文件结构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
-  方法体出现ACC_SYNCHRONIZED 标识，该标识指明了该方法是一个同步方法，JVM 通过该 ACC_SYNCHRONIZED 访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用。
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/synchronizeMethod.jpg)
- 方法体对应的访问范围
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/classArea.jpg)

### <a name="40">双亲委派模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
站在虚拟机角度，只存在两种不同的类加载器：
1. 启动类加载器BootStrap ClassLoader，由虚拟机实现，是虚拟机自身一部分。
2. 其他所有的类加载器，由Java语言实现，独立于虚拟机之外，都是继承自抽象类java.lang.ClassLoader。

java相关的三层类加载器
- 启动类加载器BootStrap ClassLoader：负责加载存放在<JAVA HOME>\lib目录，或者被-Xbootclaspath参数，启动类加载器无法被Java程序直接引用，用户在编写自定义类加载器时，需要需要给引导类加载器去处理，那直接使用null替代即可。
- 扩展类加载器Extension ClassLoader：负责加载<JAVA HOME>\lib\ext目录，或者被java.ext.dirs系统变量所指定的目录中所有的类库。
- 应用程序类加载器Application ClassLoader：负责加载用户类路径ClassPath上所有的类库。

双亲委派模型加载过程：
1. 如果一个类加载器接收到类加载请求，它首先不会自己尝试加载这个类，而是把请求委托到父类执行。
2. 每一层次的类加载器都会委托其父类加载器去完成，最终传到最顶层的启动类加载器中。
3. 只有当所有父加载器都无法自己完成这个类加载请求，子加载器才会进行加载。
- 作用：因为这样可以避免重复加载，当父亲已经加载了该类的时候，就没有必要 ClassLoader 再加载一次。考虑到安全因素，我们试想一下，如果不使用这种委托模式，那我们就可以随时使用自定义的String来动态替代java核心api中定义的类型，这样会存在非常大的安全隐患，而双亲委托的方式，就可以避免这种情况，因为String 已经在启动时就被引导类加载器（Bootstrcp ClassLoader）加载，所以用户自定义的ClassLoader永远也无法加载一个自己写的String，除非你改变 JDK 中 ClassLoader 搜索类的默认算法。
![avatar](http://ww1.sinaimg.cn/large/8dc363e6ly1g2fwftq83rj20jg0dz3z6.jpg)

相关代码：
```
    protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
```

#### <a name="41">自定义类加载器</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 加载非classpath下的类，从非标准的来源加载代码
2. 加载加密过的类文件，使用秘钥进行解密。
3. 热部署，简单粗暴的方法是自定义类加载器，加载目录外的类对象。使用定时任务或者触发起的方法，每次创建新的类加载器。
```
public class MyClassLoader extends ClassLoader {
 
    private String classPath;
 
    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }
 
    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }
 
    @Override
    protected Class<?> findClass(String name) {
        byte[] data = new byte[0];
        try {
            data = loadByte(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defineClass(name, data, 0, data.length);
    }
}
```

#### <a name="42">破坏双亲委派模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
破坏双亲委派模型，就是要实现自己的ClassLoader重写loadClass，在方法中重写自己加载的逻辑。这样类加载过程中就不会通过委派父类加载的方式进行加载数据。

##### <a name="43">JDBC破坏双亲委派模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
不破坏双亲委派模型的情况（不使用JNDI服务）
```
// 1.加载数据访问驱动
Class.forName("com.mysql.cj.jdbc.Driver");
//2.连接到数据"库"上去
Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=GBK", "root", "");
```

JDBC需要破坏双亲委派模式
>  原生的JDBC中的类是放在**rt.jar包**(对应由启用类加载器BootStrapClassLoader)的，是由启动类加载器进行类加载的，在JDBC中的Driver类中需要动态去加载不同数据库类型的Driver类，而mysql-connector-.jar中的Driver类是用户自己写的代码，那启动类加载器肯定是不能进行加载的。这就是双亲委派模型的局限性了，父级加载器无法加载子级类加载器路径中的类。

> 在JDBC4.0以后，开始支持使用SPI(Service Provider Interface)的方式来注册这个Driver，具体做法就是在mysql的jar包中的META-INF/services/java.sql.Driver 文件中指明当前使用的Driver是哪个，然后使用如下：\
> `Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=GBK", "root", "");`

如何解决父加载器无法加载子级类加载器路径中的类？
> 引入线程上下文件类加载器(Thread Context ClassLoader).在mysql jdbc连接中获取当前的类加载器，这就破坏的双亲委派的类加载过程。
```
private static Connection getConnection(
        String url, java.util.Properties info, Class<?> caller) throws SQLException {
        /*
         * When callerCl is null, we should check the application's
         * (which is invoking this class indirectly)
         * classloader, so that the JDBC driver class outside rt.jar
         * can be loaded from here.
         */
        //callerCL为空的时候，其实说明这个ClassLoader是启动类加载器，但是这个启动类加载并不能识别rt.jar之外的类，这个时候就把callerCL赋值为Thread.currentThread().getContextClassLoader();也就是应用程序启动类
        ClassLoader callerCL = caller != null ? caller.getClassLoader() : null;
        synchronized(DriverManager.class) {
            // synchronize loading of the correct classloader.
            if (callerCL == null) {
                callerCL = Thread.currentThread().getContextClassLoader();
            }
        }
    }
```

### <a name="44">类初始化的时机</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

虚拟机严格规范了有且只有6种情况下，必须对类进行初始化(只有主动去使用类才会初始化类)：
1. 当遇到 new、getstatic、putstatic或invokestatic 这4条直接码指令时，比如 new 一个类，读取一个静态字段(未被 final 修饰)、或调用一个类的静态方法时。
- 当jvm执行new指令时会初始化类。即当程序创建一个类的实例对象。
- 当jvm执行getstatic指令时会初始化类。即程序访问类的静态变量(不是静态常量，常量会被加载到运行时常量池)。
- 当jvm执行putstatic指令时会初始化类。即程序给类的静态变量赋值。
- 当jvm执行invokestatic指令时会初始化类。即程序调用类的静态方法。
2. 使用 java.lang.reflect 包的方法对类进行反射调用时如Class.forname("..."),newInstance()等等。 ，如果类没初始化，需要触发其初始化。
3. 初始化一个类，如果其父类还未初始化，则先触发该父类的初始化。
4. 当虚拟机启动时，用户需要定义一个要执行的主类 (包含 main 方法的那个类)，虚拟机会先初始化这个类。
5. MethodHandle和VarHandle可以看作是轻量级的反射调用机制，而要想使用这2个调用， 就必须先使用findStaticVarHandle来初始化要调用的类。
6. 当一个接口中定义了JDK8新加入的默认方法（被default关键字修饰的接口方法）时，如果有这个接口的实现类发生了初始化，那该接口要在其之前被初始化。


### <a name="45">类的生命周期</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
类的生命周期： 加载、连接[验证、准备、解析]、初始化、使用、卸载。
#### <a name="46">加载</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
类加载过程的第一步，主要完成下面3件事情：
1. 通过全类名获取定义此类的二进制字节流
2. 将字节流所代表的静态存储结构转换为方法区的运行时数据结构
3. 在内存中生成一个代表该类的 Class 对象,作为方法区这些数据的访问入口

#### <a name="47">验证</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
验证的范围：文件格式、元数据、字节码、符号引用验证

#### <a name="48">准备</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
准备阶段是正式为类变量(即静态变量)分配内存并设置类变量初始值的阶段，jdk8中这些内存都将在java堆中分配。对于该阶段有以下几点需要注意：
- 进行内存分配的仅包括类变量（static），而不包括实例变量
- 这里所设置的初始值"通常情况"下是数据类型默认的零值（如0、0L、null、false等）

```
public static int v = 8080;
// 实际上变量 v 在准备阶段过后的初始值为 0 而不是 8080， 将 v 赋值为 8080 的 put static 指令是程序被编译后， 存放于类构造器<client>方法之中

public static final int v = 8080;
// 在编译阶段会为 v 生成 ConstantValue 属性，在准备阶段虚拟机会根据 ConstantValue 属性将 v赋值为 8080

```

#### <a name="49">解析</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程。解析动作主要针对类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用限定符7类符号引用进行。


以方法解析为例：
1. 解析出方法表的class_index项中索引的方法所属的类或接口的符号引用。
2. 在类C中查找是否有简单名称和描述符都与目标相匹配的方法，如果存在返回直接引用。
3. 同上在类C的父类查找直接引用。
4. 上述简述了类的查找，具体细节见书本。返回直接引用后，会验证方法的访问权限，即`private、protected、public`，如果发现不具备方法的访问级别，抛出`IllegalAccessError`异常。

#### <a name="50">初始化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
类的初始化阶段是类加载过程的最后一个步骤，这个阶段Java虚拟机才开始真正执行类中编写的java程序，将主导权移交给应用程序。

在准备阶段已经赋初始化零值的变量，在初始化阶段，会根据程序去初始化类变量和其他资源。\
初始化阶段就是执行类构造器`<clinit>()`方法的过程。该方法是由编译器收集类中的所有类变量的赋值动作和静态语句块（static{}块）中的语句合并产生的。

#### <a name="51">卸载</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
卸载类即该类的Class对象被GC。

卸载类需要满足3个要求:
- 该类的所有的实例对象都已被GC，也就是说堆不存在该类的实例对象。
- 该类没有在其他任何地方被引用
- 该类的类加载器的实例已被GC


## <a name="52">对象创建及使用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/objcreate.jpg)

对象的创建
1. 类加载检查：虚拟机遇到new命令，先检查是否能在常量池定位到一个类的引用，检查这个符号代表的类是否已被加载、解析和初始化过。
2. 分配内存：检查通过，在java堆中分配对象内存，具体看对象的内存分配。
3. 初始化零值：内存分配完成后，虚拟机需要将分配到的内存空间都初始化为零值，这一步操作保证了对象的实例字段在 Java 代码中可以不赋初始值就直接使用。
4. 设置对象头：进行对象的必要设置如那个类的示例、hashcode、GC分代年龄等信息，这些信息存放在对象头中。
5. 上述工作完成之后，java开始调用对象的构造函数。

### <a name="53">对象分配内存的方式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 规整空间：指针碰撞，整理过内存用一个指针标记内存使用过的范围，后序分配内存只需要移动指针，仅把指针向空闲空间移动一段与对象大小相等的距离。
- 碎片空间：空闲链表(free list)，通过额外的维护的列表存储记录空闲的地址，将随机 IO 变为顺序 IO，但带来了额外的空间消耗。
- 本地线程分配缓冲(Thread Local Allocation Buffer,TLAB) ，每个线程在Java堆中预先分配一小块内存，基于 CAS 的独享线程（Mutator Threads）可以优先将对象分配在 Eden 中的一块内存，因为是 Java 线程独享的内存区没有锁竞争，所以分配速度更快，每个 TLAB 都是一个线程独享的。

**内存分配并发解决方案**
- CAS+失败重试
- TLAB(Thread local Allocation Buffer) ，即线程预先在堆中分配一块内存。


### <a name="54">对象内存分布</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/jvm/object-head.jpg)
对象在堆内存中的存储布局可以分为三部分：对象头、实例数据（对象有效信息）和对齐填充（仅起占位符作用）\

Hotspot的对象头包括两部分信息：
1. 第一部分：存储对象自身的运行数据，如HashCode、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID等。
2. 第二部分：类型指针，即对象指向它的类型愿数据的指针。

### <a name="55">对象的访问</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：java程序会通过栈上的reference数据来操作堆上的具体对象。具体的对象访问方式由虚拟机决定，主要有两种使用句柄和直接指针两种。
- 使用句柄访问的话，java堆会划分一块内存作为句柄池。引用会指向句柄，而句柄中分为两块指针，一个是指向对象实例的指针，一个是指向对象类型数据的指针(指向方法区)。好处为整理内存是只需要整理实例的指针。
- 直接指针访问，引用直接指向堆中的对象实例，而对象实例中包含数据的类型数据的指针(指向方法区)，好处为减少了指向实例的时间定为开销。

HotSpot虚拟机主要使用第二种方式进行访问。

### <a name="56">对象引用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 强引用(Strongly Reference): Object obj = new Object()。关系存在虚拟机就不会回收。
- 软引用(Soft Reference)：用来描述一些还有用但非必须的对象。在系统要发生内存溢出会收集软引用对象，若回收完成仍内存不足，才抛出内存遗传。软引用可用于实现内存敏感缓存，其中内存管理是一个非常重要的因素。
- 弱引用(Weak Reference)：弱引用关联的对象只能生存到下一次垃圾收集发生为止。
- 虚引用(Phantom Reference)：最弱的引用，意义为一个对象设置虚引用关联的唯一目的是为了在该对象被收集时得到一个通知。

对象死亡的调用，任何一个对象都会被系统调用一次，如果对象下一次面临回收它的finalize()不会再执行。

- [Soft References in Java](https://www.baeldung.com/java-soft-references)
- [Weak References in Java](https://www.baeldung.com/java-weak-reference)
- [Phantom References in Java](https://www.baeldung.com/java-phantom-reference)

```java
public class WeakReferenceTest {

    public static void main(String[] args) {
        Object referent = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        WeakReference weakReference1 = new WeakReference<>(referent);
        WeakReference weakReference2 = new WeakReference<>(referent, referenceQueue);

        referent = null;
        System.gc();

        Object referent2 = weakReference1.get();
        System.out.println("after gc, reference get result: " + referent2);
    }
}
```

```java

public class PhantomReferenceTest {

    public static void main(String[] args) {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        List<LargeObjectFinalizer> references = new ArrayList<>();
        List<Object> largeObjects = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            Object largeObject = new Object();
            largeObjects.add(largeObject);
            references.add(new LargeObjectFinalizer(largeObject, referenceQueue));
        }

        largeObjects = null;
        System.gc();

        Reference<?> referenceFromQueue;
        for (PhantomReference<Object> reference : references) {
            System.out.println(reference.isEnqueued());
            // 此处获取为空
            System.out.println("get result" + reference.get());
        }

        while ((referenceFromQueue = referenceQueue.poll()) != null) {
            ((LargeObjectFinalizer)referenceFromQueue).finalizeResources();
            referenceFromQueue.clear();
        }
    }
}

class LargeObjectFinalizer extends PhantomReference<Object> {

    public LargeObjectFinalizer(
            Object referent, ReferenceQueue<? super Object> q) {
        super(referent, q);
    }

    public void finalizeResources() {
        // free resources
        System.out.println("clearing ...");
    }
}
```
## <a name="57">JDK编译期</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="58">编译期做的工作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

1. 默认构造器： 经过编译的代码,可以看到在编译阶段，如果我们没有添加构造器。那么Java编译器会为我们添加一个无参构造方法。
2. 自动拆装箱
3. 泛型与类型擦除
4. foreach优化成Iterator
5. `String... args` 可变参数优化
6. switch支持case使用字符串及枚举类型优化，优化成hashcode匹配。
7. 枚举，优化成final class
8. try-with-resources 优化，自动在finally中加入close语句
9. 重写的优化，子类重写方法中会新增一个桥接方法。
10. 匿名内部类：生成final 修饰的类


相关资料： [Java编译期处理](https://blog.csdn.net/gyhdxwang/article/details/104396476)

## <a name="59">堆内存的设置要点</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 新生代的内存大小设置建议：Sun官方推荐配置为整个堆的3/8。
2. 服务器的内存需要预留一部分给永久代、线程栈及NIO

内存分配问题: 省略比较小的区域，可以总结JVM占用的内存：
> JVM内存 ≈ Java永久代 ＋ Java堆(新生代和老年代) ＋ 线程栈＋ Java NIO

假设原来的内存分配是：6G(java堆) ＋ 600M(监控) ＋ 800M(系统)，剩余大约600m内存未分配。

现在分析这600M内存的分配情况：
1. Linux保留大约200M，这部分是Linux正常运行的需要，
2. Java服务的线程数量是160个，JVM默认的线程栈大小是1M，因此使用160M内存，
3. Java NIO buffer，通过JMX查到最多占用了200m，
4. Java服务使用NIO大量读写文件，需要使用PageCache，正如前面分析，这个暂时不好定量估算大小。
前三项加起来已经560M，因此可以断定Linux物理内存不够使用。


以下是sun公司的性能优化白皮书中提到的几个例子： 
1．对于吞吐量的调优。机器配置：4G的内存，32个线程并发能力。 
```
java -Xmx3800m -Xms3800m -Xmn2g -Xss128k -XX:+UseParallelGC -XX:ParallelGCThreads=20 

-Xmx3800m -Xms3800m 配置了最大Java Heap来充分利用系统内存。 
-Xmn2g 创建足够大的青年代（可以并行被回收）充分利用系统内存，防止将短期对象复制到老年代。 
-Xss128 减少默认最大的线程栈大小，提供更多的处理虚拟内存地址空间被进程使用。 
-XX:+UseParallelGC 采用并行垃圾收集器对年青代的内存进行收集，提高效率。 
-XX:ParallelGCThreads=20 减少垃圾收集线程，默认是和服务器可支持的线程最大并发数相同，往往不需要配置到最大值。 
```
2．尝试采用对老年代并行收集 
```
java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:+UseParallelGC -XX:ParallelGCThreads=20 -XX:+UseParallelOldGC 

-Xmx3550m -Xms3550m 内存分配被减小，因为ParallelOldGC会增加对于Native Heap的需求，因此需要减小Java Heap来满足需求。 
-XX:+UseParallelOldGC 采用对于老年代并发收集的策略，可以提高收集效率。 
```

3．提高吞吐量，减少应用停顿时间 
```
java -Xmx3550m -Xms3550m -Xmn2g -Xss128k -XX:ParallelGCThreads=20 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:SurvivorRatio=8 -XX:TargetSurvivorRatio=90 -XX:MaxTenuringThreshold=31 

-XX:+UseConcMarkSweepGC -XX:+UseParNewGC 选择了并发标记交换收集器，它可以并发执行收集操作，降低应用停止时间，同时它也是并行处理模式，可以有效地利用多处理器的系统的多进程处理。 
-XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=31 表示在青年代中Eden和Survivor比例，设置增加了Survivor的大小，越大的survivor空间可以允许短期对象尽量在年青代消亡。 
-XX:TargetSurvivorRatio=90 允许90%的空间被占用，超过默认的50%，提高对于survivor的使用率。
```

相关文章：[看完你还敢说你懂JVM吗？](https://zhuanlan.zhihu.com/p/61049063?utm_source=wechat_session)

## <a name="60">CMS + ParNew收集器的流程梳理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="61">young区域(年轻代)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 对象首先分配到Eden，分配满了触发Young Gc(Minor Gc)，基于复制交换算法，晋升对象年龄+1。
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/edenGc.jpg)
2. 年龄到达老年代门槛的晋升老年代。
    - 动态对象年龄判断：如果在 Survivor 空间中相同年龄所有对象大小的总和大于 Survivor 空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代，无须等到 -XX:MaxTenuringThreshold 中要求的年龄。
    - 晋升失败触发FULL GC的两种场景：
        1. 空间分配担保失败：在进行 Young GC 时，Survivor 放不下，对象只能放入 Old，但此时 Old 也放不下。
        2. 内存碎片导致的 Promotion Failed，Young GC 以为 Old 有足够的空间，结果到分配时，晋级的大对象找不到连续的空间存放。

特殊：
1. 大对象通过直接进入老年代。
2. 动态对象年龄判断：如果在 Survivor 空间中相同年龄所有对象大小的总和大于 Survivor 空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代，无须等到 -XX:MaxTenuringThreshold 中要求的年龄。
3. 空间分配担保：当 Survivor 空间不足以容纳一次 Minor GC 之后存活的对象时，就需要依赖其他内存区域(实际上大多数情况下就是老年代) 进行分配担保，survior区无法容纳的对象直接晋升到老年代。。
    > 在发生 Minor GC 之前，虚拟机必须先检查老年代最大可用的连续空间是否大于新生代所有对象总空间，如果这个条件成立，那这一次 Minor GC 可以确保是安全的。如果不成立，则虚拟机会先查看 - XX:HandlePromotionFailure 参数的设置值是否允许担保失败 (Handle Promotion Failure)；如果允许，那会继续检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小，如果大于，将尝试进行一次 Minor GC，尽管这次 Minor GC 是有风险的；如果小于，或者-XX: HandlePromotionFailure设置不允许冒险，那这时就要改为进行一次 Full GC。


### <a name="62">old区域(老年代)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="63">CMS GC原因</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
触发 CMS GC有：
1. Old 区达到回收阈值
2. MetaSpace 空间不足
3. Young 区晋升失败
4. 大对象担保失败
5. 扩容缩容
6. 显式调用System.gc
7. 并发模式失败(浮动垃圾导致)

#### <a name="64">CMS GC 垃圾回收模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
CMS GC 的垃圾回收共分为 Background 和 Foreground 两种模式，
- Background： 正常的CMS收集过程，初始标记、并发标记、重新标记、标记清除
- Foreground： 会进行一次压缩式 GC，使用 MSC（Mark-Sweep-Compact）做 Full GC。收集的范围是 Java 堆的 Young 区和 Old 区以及 MetaSpace，会带来非常长的 STW。


CMS 在Background回收的过程中，STW 的阶段主要是 Init Mark 和 Final Remark 这两个阶段
- 初始标记 Init Mark ： 整个过程比较简单，从 GC Root 出发标记 Old 中的对象，处理完成后借助 BitMap 处理下 Young 区对 Old 区的引用，整个过程基本都比较快，很少会有较大的停顿。
- 最终标记 Final Remark ：Final Remark 的开始阶段与 Init Mark 处理的流程相同，但是后续多了 **Card Table 遍历**、**Reference 实例的清理**并将其加入到 Reference 维护的 pend_list 中，如果要收集元数据信息，还要清理 SystemDictionary、CodeCache、SymbolTable、StringTable 等组件中不再使用的资源
> Final Remark 是最终的第二次标记，这种情况只有在 Background GC 执行了 InitialMarking 步骤的情形下才会执行,如果是 Foreground GC 执行的 InitialMarking 步骤则不需要再次执行 FinalRemark。


并发的 CMS GC 算法，退化为 Foreground 单线程串行 GC 模式，STW 时间超长，有时会长达十几秒。其中 CMS 收集器退化后单线程串行 GC 算法有两种：
- 带压缩动作的算法，称为 MSC，上面我们介绍过，使用标记-清理-压缩，单线程全暂停的方式，对整个堆进行垃圾收集，也就是真正意义上的 Full GC，暂停时间要长于普通 CMS。
- 不带压缩动作的算法，收集 Old 区，和普通的 CMS 算法比较相似，暂停时间相对 MSC 算法短一些。

#### <a name="65">其他老年代问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
 CMS 无法处理浮动垃圾（Floating Garbage）。CMS 的并发清理阶段，应用还在运行，因此不断有新的垃圾产生，而这些垃圾不在这次清理标记的范畴里，无法在本次 GC 被清除掉，这些就是浮动垃圾，除此之外在 Remark 之前那些断开引用脱离了读写屏障控制的对象也算浮动垃圾。

### <a name="66">相关文章</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[图解垃圾回收](https://www.cnblogs.com/hynblogs/p/12292345.html)

## <a name="67">Java虚拟机内存调优</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
 ![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/jvmParameter.jpg)
 ![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/jvmGCType.jpg)


### <a name="68">基本概念重述</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> 跨代引用解决方案

Card Table：中文翻译为卡表，主要是用来标记卡页的状态，每个卡表项对应一个卡页。当卡页中一个对象引用有写操作时，写屏障将会标记对象所在的卡表状态改为 dirty，卡表的本质是用来解决跨代引用的问题。

> 内存分配
1. **TLAB**：Thread Local Allocation Buffer 的简写，基于 CAS 的独享线程（Mutator Threads）可以优先将对象分配在 Eden 中的一块内存，因为是 Java 线程独享的内存区没有锁竞争，所以分配速度更快，每个 TLAB 都是一个线程独享的。
2. CAS+失败重试

Mutator：生产垃圾的角色，也就是我们的应用程序，垃圾制造者，通过 Allocator 进行 allocate 和 free。

#### <a name="69">工具整理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

##### <a name="70">命令行终端</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

标准终端类：jps、jinfo、jstat、jstack、jmap

功能整合类：jcmd、vjtools、arthas、greys

##### <a name="71">可视化界面</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

简易：JConsole、JVisualvm、HA、GCHisto、GCViewer

进阶：MAT、JProfiler

命令行推荐 Arthas ，可视化界面推荐 JProfiler，此外还有一些在线的平台 gceasy、heaphero、fastthread ，美团内部的 Scalpel（一款自研的 JVM 问题诊断工具，暂时未开源）也比较好用。


### <a name="72">GC 调优目的</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
将转移到老年代的对象数量降低到最小； 减少 GC 的执行时间。

### <a name="73">GC 调优策略</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
**策略 1**：将新对象预留在新生代，由于 Full GC 的成本远高于 Minor GC，因此尽可能将对象分配在新生代是明智的做法，实际项目中根据 GC 日志分析新生代空间大小分配是否合理，适当通过“-Xmn”命令调节新生代大小，最大限度降低新对象直接进入老年代的情况。

**策略 2**：大对象进入老年代，虽然大部分情况下，将对象分配在新生代是合理的。但是对于大对象这种做法却值得商榷，大对象如果首次在新生代分配可能会出现空间不足导致很多年龄不够的小对象被分配的老年代，破坏新生代的对象结构，可能会出现频繁的 full gc。因此，对于大对象，可以设置直接进入老年代（当然短命的大对象对于垃圾回收来说简直就是噩梦）。-XX:PretenureSizeThreshold 可以设置直接进入老年代的对象大小。

**策略 3**：合理设置进入老年代对象的年龄，-XX:MaxTenuringThreshold 设置对象进入老年代的年龄大小，减少老年代的内存占用，降低 full gc 发生的频率。
> 为什么从Young GC的对象最多经历15次Young GC还存活就会进入Old区（年龄是可以调的，默认是15）hotspots的markword的图中，用了4个bit去表示分代年龄，那么能表示的最大范围就是0-15。

**策略 4**：设置稳定的堆大小，堆大小设置有两个参数：-Xms 初始化堆大小，-Xmx 最大堆大小。

**策略5**：注意： 如果满足下面的指标，则一般不需要进行 GC 优化：
>MinorGC 执行时间不到50ms； Minor GC 执行不频繁，约10秒一次； Full GC 执行时间不到1s； Full GC 执行频率不算频繁，不低于10分钟1次。


### <a name="74">调优指标</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 延迟（Latency）：也可以理解为最大停顿时间，即垃圾收集过程中一次 STW 的最长时间，越短越好，一定程度上可以接受频次的增大，GC 技术的主要发展方向。

- 吞吐量（Throughput）：应用系统的生命周期内，由于 GC 线程会占用 Mutator 当前可用的 CPU 时钟周期，吞吐量即为 Mutator 有效花费的时间占系统总运行时间的百分比，例如系统运行了 100 min，GC 耗时 1 min，则系统吞吐量为 99%，吞吐量优先的收集器可以接受较长的停顿。

### <a name="75">问题排查思路</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
四种分析思路

- 时序分析：先发生的事件是根因的概率更大，通过监控手段分析各个指标的异常时间点，还原事件时间线，如先观察到 CPU 负载高（要有足够的时间 Gap），那么整个问题影响链就可能是：
> CPU 负载高 -> 慢查询增多 -> GC 耗时增大 -> 线程Block增多 -> RT 上涨。

- 概率分析：使用统计概率学，结合历史问题的经验进行推断，由近到远按类型分析，如过往慢查的问题比较多，那么整个问题影响链就可能是：
> 慢查询增多 -> GC 耗时增大 ->  CPU 负载高   -> 线程 Block 增多 -> RT上涨。

- 实验分析：通过故障演练等方式对问题现场进行模拟，触发其中部分条件（一个或多个），观察是否会发生问题，如只触发线程 Block 就会发生问题，那么整个问题影响链就可能是：
> 线程Block增多  -> CPU 负载高  -> 慢查询增多  -> GC 耗时增大 ->  RT 上涨。

- 反证分析：对其中某一表象进行反证分析，即判断表象的发不发生跟结果是否有相关性，例如我们从整个集群的角度观察到某些节点慢查和 CPU 都正常，但也出了问题，那么整个问题影响链就可能是：
> GC 耗时增大 -> 线程 Block 增多 ->  RT 上涨。

### <a name="76">案例</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
#### <a name="77">美团技术案例（基于CMS JDK1.8）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/gcProcess.jpg)

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/learning/basic/localIssue.png)


##### <a name="78">场景一：动态扩容引起的空间震荡</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
服务刚刚启动时 GC 次数较多，最大空间剩余很多但是依然发生 GC， GC Cause 一般为 Allocation Failure，且在 GC 日志中会观察到经历一次 GC ，堆内各个空间的大小会被调整。

解决：尽量将成对出现的空间大小配置参数设置成固定的，
> 如 -Xms 和 -Xmx，-XX:MaxNewSize 和 -XX:NewSize，-XX:MetaSpaceSize 和 -XX:MaxMetaSpaceSize 等。

策略：保证 Java 虚拟机的堆是稳定的，避免弹性伸缩带来的额外 GC 消耗，确保 -Xms 和 -Xmx 设置的是一个值（即初始值和最大值一致），获得一个稳定的堆，同理在 MetaSpace 区也有类似的问题
> 在不追求停顿时间的情况下震荡的空间也是有利的，可以动态地伸缩以节省空间，例如作为富客户端的 Java 应用。

##### <a name="79">场景二：显式 GC 的去与留 (System.gc)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
除了扩容缩容会触发 CMS GC 之外，还有
 1. Old 区达到回收阈值.
 2. MetaSpace 空间不足
 3. Young 区晋升失败
 4. 大对象担保失败等几种触发条件

如果以上均不是GC发生的原因，那么就是代码中调用了 `System.gc `方法。

增加 -XX:+DisableExplicitGC 参数后，`System.gc `这个方法变成了一个空方法

CMS GC 共分为 Background 和 Foreground 两种模式，
- Background： 正常的CMS收集过程，初始标记、并发标记、重新标记、标记清除
- Foreground： 会进行一次压缩式 GC，使用 MSC（Mark-Sweep-Compact）做 Full GC。收集的范围是 Java 堆的 Young 区和 Old 区以及 MetaSpace，会带来非常长的 STW。


保留 `System.gc`：在显示触发System.gc会使用Foreground模式对Old区域进行垃圾收集造成，长时间的STW。

去掉 `System.gc`：DirectByteBuffer直接内存在分配空间会显式调用 System.gc ，希望通过 Full GC 来强迫已经无用的 DirectByteBuffer 对象释放掉它们关联的 Native Memory。若禁用`System.gc`，会导致已经晋升到 Old 的 DirectByteBuffer 关联的 Native Memory 得不到及时释放，于是就有发生 Direct Memory 的 OOM。
> -XX:+DisableExplicitGC 可以用于禁用System.gc


**策略**
因为DirectByteBuffer经常用于Netty 等各种 NIO 框架使用，所以不应该去除`System.gc`，可以使用参数改变System.gc的触发类型为Background，该模式也会触发old的DirectByteMemory 的清理工作。
> -XX:+ExplicitGCInvokesConcurrent  和 -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses  参数来将 System.gc 的触发类型从 Foreground 改为 Background

> 不止 CMS，在 G1 或 ZGC中开启 ExplicitGCInvokesConcurrent 模式，都会采用高性能的并发收集方式进行收集，不过还是建议在代码规范方面也要做好约束，规范好 System.gc 的使用。

##### <a name="80">场景三：MetaSpace 区 OOM</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
现象：JVM 在启动后或者某个时间点开始，MetaSpace 的已使用大小在持续增长，同时每次 GC 也无法释放，调大 MetaSpace 空间也无法彻底解决。

--- 
MetaSpace 主要由 Klass Metaspace 和 NoKlass Metaspace 两大部分组成。
- Klass MetaSpace：就是用来存 Klass 的，就是 Class 文件在 JVM 里的运行时数据结构.\
> 这部分默认放在 Compressed Class Pointer Space 中，是一块连续的内存区域，紧接着 Heap。Compressed Class Pointer Space 不是必须有的，如果设置了 -XX:-UseCompressedClassPointers，或者 -Xmx 设置大于 32 G，就不会有这块内存，这种情况下 Klass 都会存在 NoKlass Metaspace 里。
- NoKlass MetaSpace：专门来存 Klass 相关的其他的内容，比如 Method，**ConstantPool** 常量池等，可以由多块不连续的内存组成。虽然叫做 NoKlass Metaspace，但是也其实可以存 Klass 的内容。

MetaSpace 内存管理：类和其元数据的生命周期与其对应的类加载器相同，只要类的类加载器是存活的，在 Metaspace 中的类元数据也是存活的，不能被回收。每个加载器有单独的存储空间，通过 ClassLoaderMetaspace 来进行管理 SpaceManager* 的指针，相互隔离的

MetaSpace 弹性伸缩：由于 MetaSpace 空间和 Heap 并不在一起，所以这块的空间可以不用设置或者单独设置，一般情况下避免 MetaSpace 耗尽 VM 内存都会设置一个 MaxMetaSpaceSize

---

问题原因：为了避免弹性伸缩带来的额外 GC 消耗，我们会将 -XX:MetaSpaceSize 和 -XX:MaxMetaSpaceSize 两个值设置为固定的，但是这样也会导致在空间不够的时候无法扩容，然后频繁地触发 GC，最终 OOM。
> 经常会出问题的几个点有 Orika 的 classMap、JSON 的 ASMSerializer、Groovy 动态加载类等，基本都集中在反射、Javasisit 字节码增强、CGLIB 动态代理、OSGi 自定义类加载器等的技术点上。


策略： 给 MetaSpace 区的使用率加一个监控，如果指标有波动提前发现并解决问题。


##### <a name="81">场景四：过早晋升 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
现象：
1. 分配速率接近于晋升速率，对象晋升年龄较小
2. Full GC 比较频繁，且经历过一次 GC 之后 Old 区的变化比例非常大。

原因：
1. Young/Eden 区过小：过小的直接后果就是 Eden 被装满的时间变短，本应该回收的对象参与了 GC 并晋升， copying 耗时远大于 mark，也就是 Young GC 耗时本质上就是 copy 的时间
2. 分配速率过大：可以观察出问题前后 Mutator 的分配速率，如果有明显波动可以尝试观察网卡流量、存储类中间件慢查询日志等信息，看是否有大量数据被加载到内存中。


设定固定的 MaxTenuringThreshold 值作为晋升条件：
- MaxTenuringThreshold 如果设置得过大，原本应该晋升的对象一直停留在 Survivor 区，直到 Survivor 区溢出，一旦溢出发生。Eden + Survivor 中对象将不再依据年龄全部提升到 Old 区，这样对象老化的机制就失效了。
- MaxTenuringThreshold 如果设置得过小，过早晋升即对象不能在 Young 区充分被回收，大量短期对象被晋升到 Old 区，Old 区空间迅速增长，引起频繁的 Major GC，分代回收失去了意义，严重影响 GC 性能。
> 未设置情况，Hotspot 会使用动态计算的方式来调整晋升的阈值：
> Hotspot 遍历所有对象时，从所有年龄为 0 的对象占用的空间开始累加，如果加上年龄等于 n 的所有对象的空间之后，使用 Survivor 区的条件值（TargetSurvivorRatio / 100，TargetSurvivorRatio 默认值为 50）进行判断，若大于这个值则结束循环，将 n 和 MaxTenuringThreshold 比较，若 n 小，则阈值为 n，若 n 大，则只能去设置最大阈值为 MaxTenuringThreshold。动态年龄触发后导致更多的对象进入了 Old 区，造成资源浪费。


策略：
1. Young/Eden 区过小：调整堆分区内存，一般情况下 Old 的大小应当为活跃对象的 2~3 倍左右，考虑到浮动垃圾问题最好在 3 倍左右，剩下的都可以分给 Young 区。
    - > 如何设置Survivor面积，可以自己推算。
2. 分配速率过大： 
- 偶发较大：通过内存分析工具找到问题代码，从业务逻辑上做一些优化。
- 一直较大：当前的 Collector 已经不满足 Mutator 的期望了，这种情况要么扩容 Mutator 的 VM，要么调整 GC 收集器类型或加大空间。

##### <a name="82">场景五：CMS Old GC 频繁 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
现象：Old 区频繁的做 CMS GC，但是每次耗时不是特别长，整体最大 STW 也在可接受范围内，但由于 GC 太频繁导致吞吐下降比较多。
 
> 描述过于抽象，见文章

##### <a name="83">场景六：单次 CMS Old GC 耗时长</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
CMS 在回收的过程中，STW 的阶段主要是 Init Mark 和 Final Remark 这两个阶段
- 初始标记 Init Mark ： 整个过程比较简单，从 GC Root 出发标记 Old 中的对象，处理完成后借助 BitMap 处理下 Young 区对 Old 区的引用，整个过程基本都比较快，很少会有较大的停顿。
- 最终标记 Final Remark ：Final Remark 的开始阶段与 Init Mark 处理的流程相同，但是后续多了 **Card Table 遍历**、**Reference 实例的清理**并将其加入到 Reference 维护的 pend_list 中，如果要收集元数据信息，还要清理 SystemDictionary、CodeCache、SymbolTable、StringTable 等组件中不再使用的资源
> Final Remark 是最终的第二次标记，这种情况只有在 Background GC 执行了 InitialMarking 步骤的情形下才会执行,如果是 Foreground GC 执行的 InitialMarking 步骤则不需要再次执行 FinalRemark。

由上述过程可以推断，大部分出问题的耗时都是出现在最终标记中。处理思路如下：
1. 分析Reference 处理和元数据处理 real 耗时是否正常，一般来说最容易出问题的地方就是 Reference 中的 FinalReference 和元数据信息处理中的 scrub symbol table 两个阶段。
2. 需要通过` -XX:+PrintReferenceGC` 参数开启。基本在日志里面就能定位到大概是哪个方向出了问题，耗时超过 10% 的就需要关注
    - 对 FinalReference 的分析
        - > 经常会出现问题的几个点有 Socket 的 SocksSocketImpl 、Jersey 的 ClientRuntime、MySQL 的 ConnectionImpl 等等。
    - scrub symbol table 表示清理元数据符号引用耗时，观察 MetaSpace 区的历史使用峰值，看是否有使用动态类加载或者 DSL 处理等。
        - > 如果MateSpace 数据没啥变化，可以通过 -XX:-CMSClassUnloadingEnabled 来避免 MetaSpace 的处理。
 
##### <a name="84">场景七：内存碎片&收集器退化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
现象：
并发的 CMS GC 算法，退化为 Foreground 单线程串行 GC 模式，STW 时间超长，有时会长达十几秒。其中 CMS 收集器退化后单线程串行 GC 算法有两种：
- 带压缩动作的算法，称为 MSC，上面我们介绍过，使用标记-清理-压缩，单线程全暂停的方式，对整个堆进行垃圾收集，也就是真正意义上的 Full GC，暂停时间要长于普通 CMS。
- 不带压缩动作的算法，收集 Old 区，和普通的 CMS 算法比较相似，暂停时间相对 MSC 算法短一些。

原因：
1. 晋升失败：
    1. 在进行 Young GC 时，Survivor 放不下，对象只能放入 Old，但此时 Old 也放不下。
        > 发生的条件是很苛刻
    2. 另有一种情况就是内存碎片导致的 Promotion Failed，Young GC 以为 Old 有足够的空间，结果到分配时，晋级的大对象找不到连续的空间存放。
        - 碎片带来了两个问题：
            - 空间分配效率较低：连续空间使用指针碰撞，而有大量碎片的空闲链表则需要逐个访问 freelist 中的项来访问
            - 空间利用效率变低: Young 区晋升的对象大小大于了连续空间的大小，那么将会触发 Promotion Failed ，即使整个 Old 区的容量是足够的，但由于其不连续，也无法存放新对象
 
2. 增量收集担保失败: 分配内存失败后，会判断统计得到的 Young GC 晋升到 Old 的平均大小,，以及当前 Young 区已使用的大小也就是最大可能晋升的对象大小，是否大于 Old 区的剩余空间。只要 CMS 的剩余空间比前两者的任意一者大，CMS 就认为晋升还是安全的，反之不安全，进行FULL GC。
3. 显式 GC： System.gc
4. 并发模式失败（Concurrent Mode Failure）:在 GC 日志中经常能看到 Concurrent Mode Failure 关键字。这种是由于并发 Background CMS GC 正在执行，同时又有 Young GC 晋升的对象要放入到了 Old 区中，而此时 Old 区空间不足造成的。
> 概率较高，主要是由于 CMS 无法处理浮动垃圾（Floating Garbage）引起的。CMS 的并发清理阶段，Mutator 还在运行，因此不断有新的垃圾产生，而这些垃圾不在这次清理标记的范畴里，无法在本次 GC 被清除掉，这些就是浮动垃圾，除此之外在 Remark 之前那些断开引用脱离了读写屏障控制的对象也算浮动垃圾。

策略
- 内存碎片：通过配置 -XX:UseCMSCompactAtFullCollection=true 来控制 Full GC的过程中是否进行空间的整理（默认开启，注意是Full GC，不是普通CMS GC），以及 -XX: CMSFullGCsBeforeCompaction=n 来控制多少次 Full GC 后进行一次压缩。
- 增量收集：降低触发 CMS GC 的阈值，即参数 -XX:CMSInitiatingOccupancyFraction 的值，让 CMS GC 尽早执行，以保证有足够的连续空间，也减少 Old 区空间的使用大小，另外需要使用 -XX:+UseCMSInitiatingOccupancyOnly 来配合使用，不然 JVM 仅在第一次使用设定值，后续则自动调整。
- 浮动垃圾：视情况控制每次晋升对象的大小，或者缩短每次 CMS GC 的时间，必要时可调节 NewRatio 的值。另外就是使用 -XX:+CMSScavengeBeforeRemark 在过程中提前触发一次 Young GC，防止后续晋升过多对象。


##### <a name="85">场景八：堆外内存 OOM</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
现象： 内存使用率不断上升，甚至开始使用 SWAP 内存，同时可能出现 GC 时间飙升，线程被 Block 等现象，通过 top 命令发现 Java 进程的 RES 甚至超过了 -Xmx 的大小。

JVM 的堆外内存泄漏，主要有两种的原因：
1. 通过 UnSafe#allocateMemory，ByteBuffer#allocateDirect 主动申请了堆外内存而没有释放，常见于 NIO、Netty 等相关组件。
2. 代码中有通过 JNI 调用 Native Code 申请的内存没有释放。

策略： 在项目中添加 -XX:NativeMemoryTracking=detail JVM参数后重启项目（需要注意的是，打开 NMT 会带来 5%~10% 的性能损耗）。使用命令 jcmd pid VM.native_memory detail 查看内存分布。
    

##### <a name="86">场景九：JNI 引发的 GC 问题</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

> 太抽象了

#### <a name="87">不恰当的数据结构导致内存过大</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
场景：-Xms4g -Xmx8g -Xmn1g 使用ParNew + CMS组合。业务上需要10min加载80MB的数据到内存，会产生100W HashMap entry， Minor GC超过500ms，因为新生代使用了标记复制算法\

方案：不从修改程序，仅从GC调优，可以直接去掉SurvivorRatio，让新生代存活的对象一次Minor GC就进入到老年代` -XX:SurvivorRatio=65536 -XX:MaxTenuringThreshold=0`（或者-XX:+AlwaysTenure)
#### <a name="88">堆外内存导致溢出错误</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
NIO使用直接内存复制，而虚拟机中最大最小内存直接设值成系统内存大小了

#### <a name="89">异步系统Socket连接</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Socket 使用BIO连接异步处理，导致了系统连接数过多，进而虚拟机崩溃

#### <a name="90">Evosuite 自动生成单元测试</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
表现：maven build时候单元测试需要一个多小时。

排查：
1. jstat -gc pid 结合日志观察GC情况。
2. jstack -l pid 刷具体的运行线程。
3. jmap -heap pid 导出堆的分配情况

原因为Evosuite自动生成的test中存在：
1. StringUtils的expend测试，延长字符串到1610613374长度。jdk8，String内部使用char数组。
2. 调用Util类分配694225808 长度的ArrayList 数组空间，并分配元素。
3. 死循环线程

java进程垃圾回收器使用ParallelGC，新生代使用标记复制算法，老年代标记整理。
`s0：1g  s1：3g   eden：3g    old：10g `

结果：
YGC出现大量复制工作，很耗费时间。每次分配的空间过大，经常需要FGC来分配空间。

解决处理：死循环线程、修改自动生成的test分配合理内存。

#### <a name="91">其他案例分析资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [JSP引起的老年代不回收场景](https://blog.csdn.net/u012948161/article/details/102983795?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.control)
- [native memory](http://mahaijin.github.io/2015/04/27/JVM%E7%9A%84Heap%20Memory%E5%92%8CNative%20Memory/)
- [美团：Java中9种常见的CMS GC问题分析与解决](https://mp.weixin.qq.com/s/RFwXYdzeRkTG5uaebVoLQw)

#### <a name="92">其他建议</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 禁用偏向锁：偏向锁在只有一个线程使用到该锁的时候效率很高，但是在竞争激烈情况会升级成轻量级锁，此时就需要先消除偏向锁，这个过程是 STW 的。
    > 在已知并发激烈的前提下，一般会禁用偏向锁 -XX:-UseBiasedLocking 来提高性能。
2. 主动式 GC： 观测 Old 区的使用情况，即将到达阈值时将应用服务摘掉流量，手动触发一次 Major GC。必要时引入，会影响系统健壮性。
3. 虚拟内存：启动初期有些操作系统（例如 Linux）并没有真正分配物理内存给 JVM ，而是在虚拟内存中分配，使用的时候才会在物理内存中分配内存页，这样也会导致 GC 时间较长。
    > 这种情况可以添加 -XX:+AlwaysPreTouch 参数，让 VM 在 commit 内存时跑个循环来强制保证申请的内存真的 commit，避免运行时触发缺页异常。
