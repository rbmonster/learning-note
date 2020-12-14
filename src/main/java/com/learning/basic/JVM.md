# JVM 
## 面试题
- 基本问题
  - 介绍下 Java 内存区域（运行时数据区）
  - Java 对象的创建过程（五步，建议能默写出来并且要知道每一步虚拟机做了什么）
  - 对象的访问定位的两种方式（句柄和直接指针两种方式）

- 问题答案在文中都有提到

  - 如何判断对象是否死亡（两种方法）。
  - 简单的介绍一下强引用、软引用、弱引用、虚引用（虚引用与软引用和弱引用的区别、使用软引用能带来的好处）。
  - 如何判断一个常量是废弃常量
  - 如何判断一个类是无用的类
  - 垃圾收集有哪些算法，各自的特点？
  - HotSpot 为什么要分为新生代和老年代？
  - 常见的垃圾回收器有哪些？
  - 介绍一下 CMS,G1 收集器。
  - Minor Gc 和 Full GC 有什么不同呢？
  
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/jvm/picture/heap-detail.jpg)

## 一、虚拟机数据区
1、程序计数器
 - 定义：可以看作是当前线程所执行的字节码的行号指示器，为线程隔离的数据区。
 - java多线程切换时，每个线程独立的程序计数器，各条线程之间的计数器互不影响，独立存储，保证了线程切换后能恢复到正确的位置。
 
2、Java虚拟机栈
 - 定义：每个方法执行的时候，Java虚拟机都会同步的创建一个栈帧用于储存局部变量表、操作数栈、动态链接、方法出口等信息。每个方法被调用直至执行完毕的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。
 - 局部变量表存放了编译期可知的各种Java虚拟机基本数据类型（boolean、byte、char、short、int、float、long、double）、对象引用（reference类型）和returnAddress类型（指向一条字节码指令的地址）、
 - 在栈深度溢出或栈扩展失败时分别抛出StackOverFlowError和OutOfMemoryError的异常。 
 
3、本地方法栈
 - 定义：为虚拟机使用到的本地（Native）方法服务。
 - HotSpot直接把本方法栈和虚拟机栈合二为一。
 - 在栈深度溢出或栈扩展失败时分别抛出StackOverFlowError和OutOfMemoryError的异常。
 
4、Java堆
 - 定义:是虚拟机所管理的内存中最大的一块。Java堆是被所有线程共享的一块内存区域，在虚拟机启动时创建。
 - 参数-Xmx和-Xms 最大堆内存和最小堆内存
 ![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/jvm/picture/hotstop-heap.jpg)

5、方法区
 - 定义：是被各个线程共享的内存区域，它用于存储已被虚拟机加载的类型信息、常量、静态变量、即时编译器编译后的代码缓存等数据。
 - JDK8以前使用永久代来实现方法区（-XX:MaxPermSize 设置上限）
  - 方法区类似于接口，永久代类似于实现类的关系。使用永久代的时候，可以设置内存上限，而且不同的虚拟机的实现不一样，因此更容易遇到内存溢出的问题。
 
6、运行时常量池
 - 定义：运行时常量池是方法区的一部分。Class文件除类字段、方法、接口等描述信息外，还有一项信息是常量池表，用于存放编译期生成的各种字面量和符号引用，在类加载后存放到方法区的运行时常量池中。
 - 运行时常量池具备动态性，运行期间可以将新的常量放入池中，当无法申请到空间抛出OutOfMemoryError异常。

  
  
#### 对象的访问
 - 定义：java程序会通过栈上的reference数据来操作堆上的具体对象。具体的对象访问方式由虚拟机决定，主要有两种使用句柄和直接指针两种。
    - 使用句柄访问的话，java堆会划分一块内存作为句柄池。而句柄中分为两块指针，一个是指向对象实例的指针，一个是指向对象类型数据的指针(指向方法区)。好处为整理内存是只需要整理实例的指针。
    - 直接指针访问，对实例中包含数据的类型数据的指针(指向方法区)，好处为减少了指向实例的时间定为开销。

## 二、垃圾收集器与内存分配策略
- 程序计数器、虚拟机栈、本地方法栈3个区域随线程而生而灭，因此这几个区域的内存分配和回收都具备确定性，不需要过多考虑回收问题。

### 判断对象是否已死的方法
 - 引用计数法：
   - 定义：在对象中添加一个引用计数器，有一个地方引用时，计数器值加一，引用失效时减一。
   - 优点：原理简单，判定效率也很高。
   - 缺点：难以解决对象之间互相循环引用的问题。
 - 可达性分析算法：
   - 定义：通过一系列成为“GC Roots”的根对象作为起始节点集，从这些节点开始，根据引用关系向下搜索，搜索过程所走过的路径称为“引用链”。弱某对象到GC Roots间没有任何引用链相连，证明此对象是不可能再被使用的。
   - GC Roots的对象分为以下几种：
     1. 虚拟机栈中的引用对象，入线程调用方法堆栈的参数、局部变量、临时变量等。 
     2. 在方法区中类静态属性引用的对象。如Java类的引用类型静态变量。
     3. 在方法区中常量引用对象，如字符串常量池的引用。
     4. 在本地方法栈中的JNI（Native方法）引用的对象。
     5. Java虚拟机内部的引用，如基本类型对应的Class对象，一些常驻异常对象（NullPointException)等，还有系统类加载器。
     6. 所有被同步锁(synchronize关键字)持有的对象。
     7. 反映Java虚拟机内部情况的JMXBean、JVMTI中注册的回调、本地缓存代码等。
     
### 对象引用
- 强引用(Strongly Reference): Object obj = new Object()。关系存在虚拟机就不会回收。
- 软引用(Soft Reference)：用来描述一些还有用但非必须的对象。在系统要发生内存溢出会收集软引用对象，若回收完成仍内存不足，才抛出内存遗传。
- 弱引用(Weak Reference)：弱引用关联的对象只能生存到下一次垃圾收集发生为止。
- 虚引用(Phantom Reference)：最弱的引用，意义为一个对象设置虚引用关联的唯一目的是为了在该对象被收集时得到一个通知。
- 对象死亡的调用，任何一个对象都会被系统调用一次，如果对象下一次面临回收它的finalize()不会再执行。

### 回收方法区
- 方法区的回收主要是两部分内容：废弃的常量和不再使用的类型。
- 废弃的常量的例子：字符创常量进入到常量池中，但当前系统有没有任何一个字符串对象的值为“java”，则该常量就会被系统清理出常量池。
- 不在使用的类，需同时满足一下三个条件：
  - 该类的所有实例已经被回收，也就是java对重不存在该类及其任何派生的子类实例。
  - 加载该类的类加载器已经被回收。正常很难达成。如OSGi、JSP的重加载会产生。
  - 该类对应的java.lang.Class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法。
  
### 垃圾收集算法
- 弱分代假说：绝大多数对象都是朝生夕灭。
- 强分代假说：熬过越多次垃圾手机过程的对象就越难消亡。
- 跨代引用假说：存在于新生代的对象可能会引用老年代的对象。因此该假说说明的是，存在互相引用关系的对象，是应该倾向于同时生存或者同时死亡。
  - 解决方案，在新生代上建立一个全局的数据结构（记忆集），这个结构吧老年代划分成若干小块，表示出老年代的哪一块内存会存在跨代引用。之后发生Minor GC时，只有包含跨代引用的小块内存才会加入到GC Root的扫描.


#### 标记-清除算法
- 定义：算法分为两个阶段，一个阶段就是标记出所有需要回收的对象，在标记完成后，统一回收掉所有标记的对象，当然也可以反过来标记存活的对象，统一回收未标记对象。
- 缺点：
   1. 执行效率不稳定，如果java堆中包含大量对象，并且其中大部分是需要回收的，当对象的数量增长，标记跟清除的执行效率都会越来越低。
   2. 内存碎片化问题，标记跟清除之后会产生大量不连续的内存碎片，空间碎片太多可能导致后续程序在分配大对象的时候不得不触发另一次垃圾收集动作。

#### 标记-复制算法
- 定义:为了解决标记算法面对大量可回收对象时执行效率低及空间碎片化的问题，该算法将内存分为两个大小相等的空间，每次只使用其中一块。当一块的内存使用完了，就将还存货的对象复制到另一块上去，然后把已使用过的空间一次性清理干净。
- 缺点：
   1. 当内存中大多数对象都是存活的，那么该算法会产生大量的复制开销。
   2. 将可用的对象内存缩小为原来的一半，空间浪费未免太多了。
   3. 老年代一般不直接使用该算法，因为老年代对象存货率较高，复制开销太大。针对100%都存活的极端情况，
   
#### 标记-整理算法
- 定义：与标记-清除算法本质区别为，移动存活的对象。而移动回收后存活的对象是一项优缺点并存的风险决策。
- 移动对象的优缺点:
  - 缺点：在老年代这种每次回收都有大量存活的区域，移动存活对象并更新所有引用这些对象的地方会是一种极其负重的工作，工作期间必须暂停用户应用程序才能进行。
  - 优点：内存规整，解决了空间碎片化问题。空间碎片化问题只能依赖更复杂的内存分配器和内存访问器来解决。
- 综合的解决方案，平常都是用标记-清除算法，直到空间碎片化已经影响到对象分配，再使用标记-整理算法。

### 经典的垃圾收集器

![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/jvm/picture/garbage-collector.jpg)

#### Serial收集器
- 定义:一个单线程的垃圾收集器，在垃圾收集时必须暂停其他所有工作线程，直到收集结束。
- 对于新生代采取复制算法暂停所有线程，对于老年代使用标记-整理算法同样暂停所有线程。
- 缺点：需要暂停用户线程。
- 优点：
  1. 简单高效、对于内存资源受限的环境，它是所有收集器里额外内存消耗最小的。
  2. 单核处理器或者处理器核心较少的环境来说，Serial由于没有线程交互的开销，可以专心做垃圾回收自然可以获得最高的单线程收集效率。

#### Serial Old收集器
- 定义：使用标记-整理算法。
- 用途：可以Parallel Scavenge收集器搭配使用，另一种是作为CMS收集器发生失败的后背方案。
  
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/jvm/picture/serial-1.jpg)

#### ParNew收集器
- 定义：Serial收集器的多线程并行版本。
- 唯一的优点仅有ParNew和Serial可以和CMS收集器配合工作，以后ParNew将会合并入CMS，成为其处理新生代的组成部分。
- 随着可以被使用的处理核心增加，ParNew对于垃圾收集时系统可以高效利用，默认开启的收集线程数和处理器核心数量相同。
- 新生代：标记复制。老年代：标记-整理
- 常使用参数：-XX:SurvivorRatio、-XX:PretenureSizeThreshold、-XX:HandlePromotionFailure

![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/jvm/picture/parNew-1.jpg)

#### Parallel Scavenge 收集器
- 定义：新生代收集器，同样基于标记-复制算法，能够并行收集的多线程收集器。特点是达到一个可控制的吞吐量。
- 吞吐量= 运行客户代码时间/(运行用户代码时间+运行垃圾收集时间)
  - 虚拟机完成用户任务及垃圾收集用了100分钟，其中垃圾收集用了1分钟，吞吐量=99%
  - -XX:MaxGCPauseMills：控制最大垃圾收集时间参数
    - 允许设置的是一个大于0的毫秒数，垃圾收集停顿时间缩短是以牺牲吞吐量和新生代空间为代价换区的。调小新生代会缩短垃圾回收时间，若调的太小会导致垃圾收集变得频繁。
  - -XX:GCTimeRatio：设置吞吐量大小时间
    - 设置的值应当是大于0小于100的整数，也就是垃圾回收时间占总时间的比率为吞吐量的倒数。
    - 设置成19，那允许垃圾回收时间为总时间的5%(1/(1+19))，默认值为99,允许最大1%的时间进行垃圾回收。
  - -XX:+UseAdaptiveSizePolicy: 开启自适应的调整策略。

#### Parallel Old收集器
- 定义：Parallel Scavenge收集器的老年版本，支持多线程并发收集，基于标记-整理算法。
- 与Parallel Scavenge搭配作为“吞吐量优先”的收集器搭配组合

![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/jvm/picture/parallel.jpg)

#### CMS(Concurrent Mark Sweep)收集器
- 定义：一种以获取最短回收停顿时间为目标的收集器，工作于老年代。
- 运行过程解析：基于标记-清除算法实现，具体步骤如下，
  - 初始标记：仅仅标记一下GC Root对象能直接关联到的对象，速度很快，需要暂停所有线程。
  - 并发标记：从GC Root关联对象开始遍历整个对象图的过程，可以与用户线程共同执行。
  - 重新标记：因用户程序继续运行而导致标记产生变动的那部分对象的标记记录，通常比初始标记长远比并发标记段。
  - 并发清除：清理删除掉标记阶段判断的已经死亡的对象，由于不需要移动对象，因此可以与用户线程共同执行。
- 特点：
  - 1.对处理器资源非常敏感。CMS默认启动的回收线程数是(处理器数量+3)/4，因此弱核心数量在4个以上，占用内存不超过25%。若核心数量小于4，则占用内存过大。
  - 2.无法处理“浮动垃圾”，有可能出现并发模式失败进而导致一次Full GC。浮动垃圾为出现在标记过程结束之后产生的对象。因为CMS要支持手机过程中与用户线程并存，因此不能在老年代几乎被填满时再运行，需要预留一部分空间供并发收集的程序运行。
    - JDK5中设置CMS在老年代使用了68%便会激活，JDK6默认的设置提高到92%。当运行预留的内存无法满足程序分配新对象的需要，就会出现一次“并发失败”。后备预案为冻结用户线程，启用Serial Old进行老年代的垃圾收集。
    - -XX:CMSInitiatingOccupancyFraction 可以设置触发CMS收集的百分比。
  - 参数-XX:CMSFullGCsBeforeCompaction：作用是要求CMS收集器在执行过若干次不整理的Full GC之后，下一次先进行碎片整理(默认值为0，表示每次FullGC都进行碎片整理) 

![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/jvm/picture/cms-1.jpg)

#### Garbage First 收集器
- 定义：面向服务端应用的垃圾收集器，基于Region的堆内存布局进行垃圾收集，每一个Region都可以根据需要扮演新生代的Eden空间、Survivor空间和老年代空间。Region中海油一类特殊的Humongous区域，专门用来存储大对象，G1认为只要超过了一个Region一半的对象即可认为是大对象。对于Humongous区域，正常当做老年代一部分。
- 参数Region的大小可通过-XX:G1HeapRegionSize设定，取值范围为1M~32M，为2的N次幂。
- 用户设定的允许收集停顿时间使用参数-XX:MaxGCPauseMills指定，默认为200毫秒。调的调小会导致每次的回收集只占内存的很小一部分，收集的速度慢于分配的速度导致垃圾堆积，进而引发Full GC。正常设置为100~300毫秒之间。
- 运行步骤：
  1. 初始标记：标记GC Root对象能直接关联的对象并修改TAMS指针的值为正确的空区域。需要暂停线程，但是时间很短，借用进行Minor GC时同步完成。
  2. 并发标记：根据GC Root进行可达性分析，扫描对象图。完成扫描后，处理SATB记录下并发时有引用变动的对象。
  3. 最终标记：短暂暂停用户线程，处理并发阶段结束后，少量的SATB记录。
  4. 筛选回收：更新Region的统计数据，进行回收价值和成本的排序，根据用户期望的停顿时间来构建回收集合。回收集合的存活对象复制到空的Region，再清理旧的Region。涉及到对象移动，需要暂停用户线程，使用多线程并行完成移动。
- G1整体是基于标记-整理算法实现的收集器，但从局部优势基于标记-复制算法实现。
- 特点:
  1. 避免在整个Java堆进行全区域的垃圾回收，而是让G1跟踪每个Region的垃圾回收的价值及回收所需的时间，在后台维护一个优先级表。根据用户设定的允许收集停顿时间，优先回收价值收益最大的Region。(使用参数-XX:MaxGCPauseMills指定)
  2. G1收集器每个Region都需要自己的记忆集，记录跨区域引用，因此比其他收集器要耗费内存，大约为java堆内存容量10%~20%。
  3. 通过在Region中划分空间(使用两TAMS指针，标记一块区域)用于并发回收的新对象分配，解决并发标记阶段与用户线程互不干扰。同样若内存分配速度大于内存回收速度，也许冻结用户线程Full GC。
  4. CMS使用增量更新算法，而G1使用原始快照(SATB)算法来解决，用户线程改变对象的引用关系，不打破原有的对象图结构，防止标记错误。
  5. 可靠停顿预测模型的建立：根据每个Region的回收成本，分析出收集的平均值、标准偏差、置信度等统计信息。
- 缺点：内存占用过高，在小内存应用上CMS的表现大于G1。
  
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/jvm/picture/g1.jpg)
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/jvm/picture/g1-memory.jpg)

#### 其他的垃圾收集器
- Shenandoah 收集器：仅存在OpenJdk，区别G1的特点为支持并发整理，使用转发指针和读屏障实现。
- ZGC 收集器：Region具有动态性，并分为大中小三个Region，使用染色指针技术实现并发整理算法。
- Epsilon收集器：无操作收集器。

#### 选用收集器的三个因素
1. 如果是数据分析、科学计算类任务，目标是尽快可以算出结果，那么吞吐量为主要关注点。如果为SLA应用，停顿时间直接影响任务质量，严重甚至会导致事务超时，那么延迟是主要的关注点。
2. 使用运行的基础设施的指标。
3. JDK对应的版本。

## 三、java虚拟机监控工具
### jps
- jps (JVM Process Status）: 类似 UNIX 的 ps 命令。用户查看所有 Java 进程的启动类、传入参数和 Java 虚拟机参数等信息；
  - ```
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
### jstat
- jstat（ JVM Statistics Monitoring Tool）: 用于收集 HotSpot 虚拟机各方面的运行数据;
  - jstat -gc -h3 31736 1000 10表示分析进程 id 为 31736 的 gc 情况，每隔 1000ms 打印一次记录，打印 10 次停止，每 3 行后打印指标头部。
  - ```
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
### jinfo
- jinfo (Configuration Info for Java) : Configuration Info forJava,显示虚拟机配置信息;
  - ```
    C:\Users\SnailClimb>jinfo  -flag MaxHeapSize 17340
    -XX:MaxHeapSize=2124414976
    C:\Users\SnailClimb>jinfo  -flag PrintGC 17340
    -XX:-PrintGC
    ```
### jmap 和 jhat
jmap (Memory Map for Java) :生成堆转储快照;
  - ```
    [root@iZuf6ee30yhz3x9bqf63clZ apache-tomcat-8.5.31]# jmap -dump:format=b,file=elasticfoam.bin 2903
    Dumping heap to /usr/local/apache-tomcat-8.5.31/elasticfoam.bin ...
    Heap dump file created
    ```

jhat (JVM Heap Dump Browser ) : 用于分析 heapdump 文件，它会建立一个 HTTP/HTML 服务器，让用户可以在浏览器上查看分析结果;
  - 与上面的jmap配合使用，分析heapdump的堆信息，会生成具体的服务器。
  - ```
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
### jstack
jstack (Stack Trace for Java):生成虚拟机当前时刻的线程快照，线程快照就是当前虚拟机内每一条线程正在执行的方法堆栈的集合。
  - ```
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
    
- 一个linux的排除高CUP线程的排查案例
- ```
  top -c //查看所有进程
  top -Hp xxx（PID）  // 查看进程具体的线程ID cup情况
  jstack -l pid > filename // 输出当前快照
  cat filename| grep '线程ID（16进制）' -C 8     // 查找匹配线程，-C 查看前后多少行数据
  ```
### jconsole
JConsole:Java 监视与管理控制台，很强大，可以检测死锁，查看堆的内存释放情况。
- 如果需要使用 JConsole 连接远程进程，可以在远程 Java 程序启动时加上下面这些参数:
```
-Djava.rmi.server.hostname=外网访问 ip 地址 
-Dcom.sun.management.jmxremote.port=60001   //监控的端口号
-Dcom.sun.management.jmxremote.authenticate=false   //关闭认证
-Dcom.sun.management.jmxremote.ssl=false
```

## 四、类文件

### 类文件结构
-  方法体出现ACC_SYNCHRONIZED 标识，该标识指明了该方法是一个同步方法，JVM 通过该 ACC_SYNCHRONIZED 访问标志来辨别一个方法是否声明为同步方法，从而执行相应的同步调用。
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/synchronizeMethod.jpg)
- 方法体对应的访问范围
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/classArea.jpg)

### 类初始化的时机
- Java 虚拟机规范没有强制约束类加载过程的第一阶段（即：加载）什么时候开始，但对于“初始化”阶段，有着严格的规定。有且仅有 5 种情况必须立即对类进行“初始化”：
  - 在遇到 new、putstatic、getstatic、invokestatic 字节码指令时，如果类尚未初始化，则需要先触发其初始化。
  - 对类进行反射调用时，如果类还没有初始化，则需要先触发其初始化。
  - 初始化一个类时，如果其父类还没有初始化，则需要先初始化父类。
  - 虚拟机启动时，用于需要指定一个包含 main() 方法的主类，虚拟机会先初始化这个主类。
  - 当使用 JDK 1.7 的动态语言支持时，如果一个 java.lang.invoke.MethodHandle 实例最后的解析结果为 REF_getStatic、REF_putStatic、REF_invokeStatic 的方法句柄，并且这个方法句柄所对应的类还没初始化，则需要先触发其初始化。
- 这 5 种场景中的行为称为对一个类进行主动引用，除此之外，其它所有引用类的方式都不会触发初始化，称为被动引用。

### 类的生命周期
- 类的生命周期： 加载、连接[验证、准备、解析]、初始化、使用、卸载。
#### 加载
- 类加载过程的第一步，主要完成下面3件事情：
  - 通过全类名获取定义此类的二进制字节流
  - 将字节流所代表的静态存储结构转换为方法区的运行时数据结构
  - 在内存中生成一个代表该类的 Class 对象,作为方法区这些数据的访问入口

#### 验证
- 验证的范围：文件格式、元数据、字节码、符号引用验证

#### 准备
- 准备阶段是正式为类变量分配内存并设置类变量初始值的阶段，这些内存都将在方法区中分配。对于该阶段有以下几点需要注意：
  - 进行内存分配的仅包括类变量（static），而不包括实例变量
  - 这里所设置的初始值"通常情况"下是数据类型默认的零值（如0、0L、null、false等）
#### 解析
- 解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程。解析动作主要针对类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用限定符7类符号引用进行。

#### 初始化

- 虚拟机严格规范了有且只有5种情况下，必须对类进行初始化(只有主动去使用类才会初始化类)：
1. 当遇到 new 、 getstatic、putstatic或invokestatic 这4条直接码指令时，比如 new 一个类，读取一个静态字段(未被 final 修饰)、或调用一个类的静态方法时。
  - 当jvm执行new指令时会初始化类。即当程序创建一个类的实例对象。
  - 当jvm执行getstatic指令时会初始化类。即程序访问类的静态变量(不是静态常量，常量会被加载到运行时常量池)。
  - 当jvm执行putstatic指令时会初始化类。即程序给类的静态变量赋值。
  - 当jvm执行invokestatic指令时会初始化类。即程序调用类的静态方法。
2. 使用 java.lang.reflect 包的方法对类进行反射调用时如Class.forname("..."),newInstance()等等。 ，如果类没初始化，需要触发其初始化。
3. 初始化一个类，如果其父类还未初始化，则先触发该父类的初始化。
4. 当虚拟机启动时，用户需要定义一个要执行的主类 (包含 main 方法的那个类)，虚拟机会先初始化这个类。
5. MethodHandle和VarHandle可以看作是轻量级的反射调用机制，而要想使用这2个调用， 就必须先使用findStaticVarHandle来初始化要调用的类。
6. 当一个接口中定义了JDK8新加入的默认方法（被default关键字修饰的接口方法）时，如果有这个接口的实现类发生了初始化，那该接口要在其之前被初始化。


#### 卸载
- 卸载类即该类的Class对象被GC。

- 卸载类需要满足3个要求:
  - 该类的所有的实例对象都已被GC，也就是说堆不存在该类的实例对象。
  - 该类没有在其他任何地方被引用
  - 该类的类加载器的实例已被GC

### 虚拟机中对象的创建
 ![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/objcreate.jpg)

- 对象的创建
1. 类加载检查：虚拟机遇到new命令，先检查是否能在常量池定位到一个类的引用，检查这个符号代表的类是否已被加载、解析和初始化过。
2. 分配内存：检查通过，在java堆中分配对象内存，分为整齐空间与交错空间的两种分配。
    - 整齐空间只要移动指针即可“指针碰撞”。（指针碰撞：整理过内存用一个指针标记内存使用过的范围，后序分配内存只需要移动指针。）
      - 比如Serial、ParNew垃圾回收器
    - 错乱空间，分配内存方式，根据虚拟机列表上的空闲空间list，选定需要分配的内存更新列表，这种分配内存的方式为“空闲列表”。
    - **内存分配并发解决方案**
      - CAS+失败重试
      - TLAB(Thread local Allocation Buffer) ，即线程预先在堆中分配一块内存。
3. 初始化零值：内存分配完成后，虚拟机需要将分配到的内存空间都初始化为零值，这一步操作保证了对象的实例字段在 Java 代码中可以不赋初始值就直接使用。
4. 设置对象头：进行对象的必要设置如那个类的示例、hashcode、GC分代年龄等信息，这些信息存放在对象头中。
5. 上数工作完成之后，java开始调用对象的构造函数。

  
  
## Java虚拟机内存调优
 ![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/jvmParameter.jpg)
 ![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/jvmGCType.jpg)

### GC 调优目的
将转移到老年代的对象数量降低到最小； 减少 GC 的执行时间。

### GC 调优策略
**策略 1**：将新对象预留在新生代，由于 Full GC 的成本远高于 Minor GC，因此尽可能将对象分配在新生代是明智的做法，实际项目中根据 GC 日志分析新生代空间大小分配是否合理，适当通过“-Xmn”命令调节新生代大小，最大限度降低新对象直接进入老年代的情况。

**策略 2**：大对象进入老年代，虽然大部分情况下，将对象分配在新生代是合理的。但是对于大对象这种做法却值得商榷，大对象如果首次在新生代分配可能会出现空间不足导致很多年龄不够的小对象被分配的老年代，破坏新生代的对象结构，可能会出现频繁的 full gc。因此，对于大对象，可以设置直接进入老年代（当然短命的大对象对于垃圾回收来说简直就是噩梦）。-XX:PretenureSizeThreshold 可以设置直接进入老年代的对象大小。

**策略 3**：合理设置进入老年代对象的年龄，-XX:MaxTenuringThreshold 设置对象进入老年代的年龄大小，减少老年代的内存占用，降低 full gc 发生的频率。

**策略 4**：设置稳定的堆大小，堆大小设置有两个参数：-Xms 初始化堆大小，-Xmx 最大堆大小。

**策略5**：注意： 如果满足下面的指标，则一般不需要进行 GC 优化：
>MinorGC 执行时间不到50ms； Minor GC 执行不频繁，约10秒一次； Full GC 执行时间不到1s； Full GC 执行频率不算频繁，不低于10分钟1次。

#### 案例
##### 不恰当的数据结构导致内存过大
  - -Xms4g -Xmx8g -Xmn1g 使用ParNew + CMS组合。
  - 业务上需要10min加载80MB的数据到内存，会产生100W HashMap entry
  - Minor GC超过500ms，因为新生代使用了标记复制算法
  - 不从修改程序，仅从GC调优，可以直接去掉SurvivorRatio，让新生代存活的对象一次Minor GC就进入到老年代
    - -XX:SurvivorRatio=65536 -XX:MaxTenuringThreshold=0（或者-XX:+AlwaysTenure)
##### 堆外内存导致溢出错误
- NIO使用直接内存复制，而虚拟机中最大最小内存直接设值成系统内存大小了

##### 异步系统Socket连接
- Socket 使用BIO连接异步处理，导致了系统连接数过多，进而虚拟机崩溃