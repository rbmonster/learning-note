<a name="index">**Index**</a>

<a href="#0">设计原则及架构思想</a>  
&emsp;<a href="#1">1. 编程思想</a>  
&emsp;&emsp;<a href="#2">1.1. 面向对象编程OOP</a>  
&emsp;&emsp;<a href="#3">1.2. 面向过程编程POP</a>  
&emsp;&emsp;<a href="#4">1.3. 函数式编程</a>  
&emsp;<a href="#5">2. 六大设计原则</a>  
&emsp;&emsp;<a href="#6">2.1. 单一职责原则</a>  
&emsp;&emsp;<a href="#7">2.2. 里氏替换原则</a>  
&emsp;&emsp;<a href="#8">2.3. 依赖倒置原则</a>  
&emsp;&emsp;<a href="#9">2.4. 接口隔离原则</a>  
&emsp;&emsp;<a href="#10">2.5. 迪米特法则</a>  
&emsp;&emsp;<a href="#11">2.6. 开闭原则</a>  
&emsp;<a href="#12">3. MVC 模式</a>  
&emsp;<a href="#13">4. BFF(Backend for Frontend)</a>  
&emsp;<a href="#14">5. 系统架构</a>  
&emsp;&emsp;<a href="#15">5.1. 单体</a>  
&emsp;&emsp;<a href="#16">5.2. 分布式系统</a>  
&emsp;&emsp;&emsp;<a href="#17">5.2.1. 常用技术</a>  
&emsp;&emsp;<a href="#18">5.3. SOA</a>  
&emsp;&emsp;<a href="#19">5.4. 微服务</a>  
&emsp;&emsp;&emsp;<a href="#20">5.4.1. 拆分原则</a>  
&emsp;&emsp;&emsp;<a href="#21">5.4.2. DDD 领域驱动</a>  
&emsp;<a href="#22">6. 相关资料</a>  
<a href="#23">微服务</a>  
&emsp;<a href="#24">1. 基本概念</a>  
&emsp;&emsp;<a href="#25">1.1. 负载均衡</a>  
&emsp;&emsp;<a href="#26">1.2. 缓存</a>  
&emsp;&emsp;<a href="#27">1.3. 分片/数据分区</a>  
&emsp;&emsp;<a href="#28">1.4. 代理</a>  
&emsp;&emsp;<a href="#29">1.5. 冗余</a>  
&emsp;<a href="#30">2. 限流</a>  
&emsp;&emsp;<a href="#31">2.1. 限流算法</a>  
&emsp;&emsp;<a href="#32">2.2. 分布式限流</a>  
&emsp;&emsp;<a href="#33">2.3. 实际应用</a>  
&emsp;<a href="#34">3. 熔断</a>  
&emsp;<a href="#35">4. 监控 </a>  
# <a name="0">设计原则及架构思想</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">编程思想</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="2">面向对象编程OOP</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
面向对象编程(Object Oriented Programming，OOP)思想是以现实世界中事物，建立模型体现出来的抽象思维过程。
根据抽象的模型，依照事物之间的关系及方法进行操作，以求达到**重用性**、**灵活性**和**扩展性**的设计目的。
> 面向对象编程是把构成问题的事务分解成各个对象，建立对象的目的不是为了完成一个步骤，而是**为了描叙某个事物在整个解决问题的步骤中的行为**。

OOP=对象+类+继承+多态+消息，其中核心概念是类和对象。

特点： 封装、多态、继承

优点:
- 结构清晰，程序是模块化和结构化，更加符合人类的思维方式；
- 易扩展，代码重用率高，可继承，可覆盖，可以设计出低耦合的系统；
- 易维护，系统低耦合的特点有利于减少程序的后期维护工作量。

缺点：
- 开销大，当要修改对象内部时，对象的属性不允许外部直接存取，所以要增加许多没有其他意义、只负责读或写的行为。这会为编程工作增加负担，增加运行开销，并且使程序显得臃肿。
- 性能低，由于面向更高的逻辑抽象层，使得面向对象在实现的时候，不得不做出性能上面的牺牲，计算时间和空间存储大小都开销很大。

### <a name="3">面向过程编程POP</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
面向过程编程(Procedure-Oriented Programming，简记为POP)，就是分析出解决问题所需要的步骤，然后用函数把这些步骤一步一步实现，使用的时候一个一个依次调用就可以了。

优点：
- 流程化使得编程任务明确，在开发之前基本考虑了实现方式和最终结果，具体步骤清楚，便于节点分析。
- 效率高，面向过程强调代码的短小精悍，善于结合数据结构来开发高效率的程序。

缺点：
- 需要深入的思考，耗费精力，代码重用性低，扩展能力差，后期维护难度比较大。

### <a name="4">函数式编程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
函数式编程类似于面向过程的程序设计，但其思想更接近数学计算。允许把函数本身作为参数传入另一个函数，还允许返回一个函数。是一种抽象程度很高的编程范式，纯粹的函数式编程语言编写的函数没有变量。
> 面向过程编程体现的是解决方法的步骤，而函数式编程体现的是**数据集的映射**。

函数式编程关心**数据的映射**，命令式编程关心解决问题的步骤



## <a name="5">六大设计原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

六大设计原则主要是指：
- **单一职责原则**(Single Responsibility Principle): 一个类或接口只承担一个职责。
- **开闭原则**(Open Closed Principle): 一个软件实体如类、模块和函数应该对扩展开放，对修改关闭。
- **里氏替换原则**(Liskov Substitution Principle): 子类可以扩展父类的功能，但不能改变原有父类的功能。只要父类能出现的地方，子类就可以出现，而且替换为子类也不会产生任何错误或异常。
- **迪米特法则**(Law of Demeter)，又叫"最少知道法则" : 最少知道原则，尽量降低类与类之间的耦合
- **接口隔离原则**(Interface Segregation Principle): 建立单一接口，类之间依赖关系应该建立在最小的接口上
- **依赖倒置原则**(Dependence Inversion Principle): 面向接口编程，高层模块不应该依赖于低层模块，而应该依赖于抽象。抽象不应依赖于细节，细节应依赖于抽象
> 把这 6 个原则的首字母(里氏替换原则和迪米特法则的首字母重复，只取一个)联合起来就是：**SOLID**(稳定的)，其代表的含义也就是把这 6 个原则结合使用的好处：建立稳定、灵活、健壮的设计。

### <a name="6">单一职责原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
单一职责原则(Single Responsibility Principle)：一个类或者**一个方法只负责一项职责**，尽量做到类的只有一个行为原因引起变化。
> 该原则适用于类、接口、方法。

单一职责的好处
1. 复杂性降低，实现什么职责都有清晰明确的定义
2. 可读性高，复杂性降低，可读性自然就提高了
3. 可维护性提高，可读性提高了，那自然更容易维护了
4. 变更引起的风险降低，变更是必不可少的，如果接口的单一职责做得好，一个接口修改只对相应的实现类有影响，对其他的接口无影响，这对系统的扩展性、维护性都有非常大的帮助。


### <a name="7">里氏替换原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
里氏替换原则(LSP liskov substitution principle)：子类可以扩展父类的功能，但不能改变原有父类的功能。只要父类能出现的地方，子类就可以出现，而且替换为子类也不会产生任何错误或异常。

在面向对象的语言中，继承是必不可少的、非常优秀的语言机制，它有如下优点：
- 代码共享，减少创建类的工作量，每个子类都拥有父类的属性和方法
- 提高代码的重用性
- 子类可以形似父类，但又异于父类
- 提高代码的可扩展性
- 提高产品或项目的开放性。

继承是侵入性的。只要继承，就必须拥有父类的属性和方法。
- 降低代码的灵活性。子类会多一些父类的约束。
- 增强了耦合性。当父类的常量、变量、方法被修改时，需要考虑子类的修改。

### <a name="8">依赖倒置原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
依赖倒置原则(dependence inversion principle)：面向接口编程(通过接口作为参数实现应用场景)，高层模块不应该依赖于低层模块，而应该依赖于抽象。抽象不应依赖于细节，细节应依赖于抽象。

含义：
- 上层模块不应该依赖下层模块，两者应依赖其抽象
- 抽象不应该依赖细节，细节应该依赖抽象
> 通俗点就是说变量或者传参数，尽量使用抽象类，或者接口。抽象就是接口或者抽象类，细节就是实现类。

依赖倒置原则的使用建议：
1. 每个类尽量都有接口或抽象类，或者接口和抽象类两者都具备。
2. 变量的表面类型尽量是接口或抽象类。
3. 任何类都不应该从具体类派生。
4. 尽量不要重写基类的方法。如果基类是一个抽象类，而且这个方法已经实现了，子类尽量不要重写。
5. 结合里氏替换原则使用。

### <a name="9">接口隔离原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
接口隔离原则(Interface Segregation Principle): 建立单一接口(扩展为类也是一种接口，一切皆接口)

定义：
- 客户端不应该依赖它不需要的接口；
- 类之间依赖关系应该建立在最小的接口上；
> 接口的设计粒度越小，系统越灵活，但是灵活的同时结构复杂性提高，开发难度也会变大，维护性降低。如一个臃肿的接口拆分为三个独立的接口所依赖的原则就是接口隔离原则

### <a name="10">迪米特法则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
迪米特原则(law of demeter LOD)：最少知道原则，尽量降低类与类之间的耦合

迪米特法则的核心观念就是类间解耦，弱耦合，只有弱耦合了以后，类的复用率才可以提升上去。
> 如果一个方法放在本类中，既不增加类间关系，也对本类不产生负面影响，那就放置在本类中。

### <a name="11">开闭原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
开闭原则(open closed principle)： 指一个软件实体如类、模块和函数应该对扩展开放，对修改关闭。

为什么要用开闭原则
1. 开闭原则非常著名，只要是做面向对象编程的，在开发时都会提及开闭原则。
2. 开闭原则是最基础的一个原则，前面介绍的5个原则都是开闭原则的具体形态，而开闭原则才是其精神领袖。
3. 开闭原则提高了复用性，以及可维护性。

## <a name="12">MVC 模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MVC是软件工程中的一种软件架构模式，把软件系统分为三个基本部分：模型（Model）、视图（View）和控制器（Controller）。经典MVC模式中，M是指业务模型，V是指用户界面，C则是控制器。
> MVC模式的目的是实现一种动态的程式设计，使后续对程序的修改和扩展简化，并且使程序某一部分的重复利用成为可能。除此之外，此模式透过对复杂度的简化，使程序结构更加直观。\
> 使用MVC的目的是将M和V的实现代码分离，从而使同一个程序可以使用不同的表现形式。其中，View的定义比较清晰，就是用户界面。

- 模型(Model) - 程序员编写程序应有的功能(实现算法等等)、数据库专家进行数据管理和数据库设计(可以实现具体的功能)。
- 视图(View) - 界面设计人员进行图形界面设计。
- 控制器(Controller) - 负责转发请求，对请求进行处理。

## <a name="13">BFF(Backend for Frontend)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
BFF，即 Backend For Frontend(服务于前端的后端)，也就是服务器设计 API 时会考虑前端的使用，并在服务端直接进行业务逻辑的处理，又称为用户体验适配器。BFF 只是一种逻辑分层，而非一种技术

BFF 解决了什么问题？\
前端页面时常存在，某个页面需要向 `backend A`、`backend B` 以及 `backend C`...... 发送请求，不同服务的返回值用于渲染页面中不同的 `component`，即一个页面存在很多请求的场景。
有了 BFF 这一层时，我们就不需要考虑系统后端的迁移。后端发生的变化都可以在 BFF 层做一些响应的修改。

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/systemdesign/bff.png)

- 多端应用: 为不同的设备提供不同的 API，虽然它们可能是实现相同的功能，但因为不同设备的特殊性，它们对服务端的 API 访问也各有其特点，需要区别处理。
- 服务聚合：BFF 的出现为前端应用提供了一个对业务服务调用的聚合点，它屏蔽了复杂的服务调用链，让前端可以聚焦在所需要的数据上，而不用关注底层提供这些数据的服务。
- 认证、授权、请求记录等通用功能可以在BFF层实现，使用依赖包共享的方式实现。而引入额外的服务层可能导致的请求延迟的情况。

缺点：
- 在基础服务上多加了一层转发，带来了响应时间延迟
- 带来的代码重复和工作量增加

参考资料：
- [BFF —— Backend For Frontend](https://www.jianshu.com/p/eb1875c62ad3)
- [Pattern: Backends For Frontends](https://samnewman.io/patterns/architectural/bff/)
## <a name="14">系统架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/distribution-sys.png)

### <a name="15">单体</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

“单体”只是表明系统中主要的过程调用都是**进程内调用**，不会发生**进程间通信**，仅此而已。

对于小型系统——即由单台机器就足以支撑其良好运行的系统，单体不仅易于开发、易于测试、易于部署，且由于系统中各个功能、模块、方法的调用过程都是进程内调用，不会发生进程间通信（Inter-Process Communication，IPC）。
> 单体系统的不足，必须基于软件的性能需求超过了单机，软件的开发人员规模明显超过了“2 Pizza Team”范畴的前提下才有讨论的价值

### <a name="16">分布式系统</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

布式系统（Distributed system）如何进行计算。分布式系统是一组电脑，透过网络相互连接传递消息与通信后并协调它们的行为而形成的系统。组件之间彼此进行交互以实现一个共同的目标。把需要进行大量计算的工程数据分割成小块，由多台计算机分别计算，再上传运算结果后，将结果统一合并得出数据结论的科学。分布式系统的例子来自有所不同的面向服务的架构，大型多人在线游戏，对等网络应用。



分布式系统存在的问题：
1. 异构系统不标准问题。主要体现在通信协议不标准、数据格式不标准、运维跟开发方式不标准(比如服务发布打包使用自定的流程进行)
2. 系统架构中的服务性依赖问题。非关键业务被关键业务所依赖，也变成了关键业务。另外整个系统还存在"木桶效应"的问题，由最差的服务决定。
3. 故障发生的概率更大。虽然服务相互隔离，但是机器多，系统架构复杂，出故障频率也会增大。
4. 多层架构的系统复杂度更大。通常系统可以分为四层：基础层(机器、网络和存储设备等)、平台层(即中间件层如Mysql、kafka、redis等软件)、应用层(即我们部署的应用服务)、接入层(网关、负载均衡、CDN、DNS等)



#### <a name="17">常用技术</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

TODO 分布式系统技术栈

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ds-1.png)

![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/design/ds-2.png)



### <a name="18">SOA</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

SOA 架构（Service-Oriented Architecture）: 面向服务的架构是一次具体地、系统性地成功解决分布式服务主要问题的架构模式。

SOA的架构经常利用一个被称为企业服务总线（Enterprise Service Bus，ESB）的消息管道来实现各个子系统之间的通信交互，令各服务间在 ESB 调度下无须相互依赖却能相互通信，既带来了**服务松耦合**的好处，也为以后可以进一步实施业务流程编排（Business Process Management，BPM）提供了基础；

通过ESB 通信的时候，明确了采用 SOAP 作为远程调用的协议，依靠 SOAP 协议族（WSDL、UDDI 和一大票 WS-*协议）来完成服务的发布、发现和治理。


### <a name="19">微服务</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
**微服务是一种通过多个小型服务组合来构建单个应用的架构风格，这些服务围绕业务能力而非特定的技术标准来构建。各个服务可以采用不同的编程语言，不同的数据存储技术，运行在不同的进程之中。服务采取轻量级的通信机制和自动化的部署机制实现通信与运维。**

**微服务的九个核心的业务与技术特征**:
- 围绕业务能力构建（Organized around Business Capability）
- 分散治理（Decentralized Governance）
- 通过服务来实现独立自治的组件（Componentization via Services）
- 产品化思维（Products not Projects）
- 数据去中心化（Decentralized Data Management）
- 强终端弱管道（Smart Endpoint and Dumb Pipe）
- 容错性设计（Design for Failure）
- 演进式设计（Evolutionary Design）
- 基础设施自动化（Infrastructure Automation）



#### <a name="20">拆分原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
垂直划分优先原则：应该根据业务领域对服务进行垂直划分，让团队能关注业务实现。

持续演进原则： 服务数量在非必要的情况下，应该逐步划分，持续演进，避免服务数量的爆炸性增长


#### <a name="21">DDD 领域驱动</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
详细见
[DDD设计思想](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/DDD.md)

## <a name="22">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[微服务拆分方法论](https://blog.csdn.net/no_game_no_life_/article/details/103390169)


# <a name="23">微服务</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

**主要特点**

**可扩展性**：满足一个系统增长和管理增加的需求的能力。
- 能够持续发展以支持不断增长的业务量。
- 横向扩展：通过在资源池中增加更多的服务器。
- 垂直扩展：通过向现有服务器添加更多资源（CPU、内存、存储等）。这种方法伴随着停机时间和一个上限。

**可靠性**：可靠性是指一个系统在一定时期内发生故障的概率。
- 如果一个分布式系统在一个或多个组件发生故障时仍能继续提供服务，那么它就是可靠的。
- 可靠性是通过组件和数据的冗余来实现的（消除每一个单一的故障点）

**可用性**：指一个系统在特定时期内保持运行以执行其必要功能的时间。 以一个系统在正常条件下保持运行的时间百分比来衡量。
- 一个可靠的系统是可用的。
- 一个可用的系统不一定可靠。
- 一个有安全漏洞的系统在没有安全攻击时是可用的。

**效率**
- 延迟：响应时间，获得第一条数据的延迟。
- 带宽：吞吐量，在一定时间内交付的数据量。

**可服务性/可管理性**
- 系统易于操作和维护。
- 一个系统被修理或维护的复杂度和花费的时间成本。


## <a name="24">基本概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="25">负载均衡</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：通过某种负载分担技术，将外部发送过来的请求均匀分配到对称结构中的某一台服务器上，而接收到请求的服务器独立地回应客户的请求。

负载均衡的位置：
- 在用户和网络服务器之间
- 在网络服务器和内部平台层（应用服务器、缓存服务器）之间
- 在内部平台层和数据库之间

负载均衡方式：软件负载和硬件负载
- 软件负载均衡：负载均衡软件有nginx、LVS、HAproxy
- 硬件负载均衡：Array，F5
> 硬件负载均衡解决方案是直接在服务器和外部网络间安装负载均衡设备，这种设备我们通常称之为负载均衡器，由于专门的设备完成网络请求转发的任务，独立于操作系统，整体性能高，负载均衡策略多样化，流量管理智能化。

负载均衡算法：
- 随机算法
- 轮询算法：简单轮询算法、加权轮询算法
- 最少的连接
- 最短的响应时间
- 最小的带宽
- 圆周率
- IP哈希

### <a name="26">缓存</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
局部性原则：最近访问的数据很可能会被再次访问

缓存分为：
- 服务器缓存
- 分布式缓存
- 全局缓存

### <a name="27">分片/数据分区</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

分片：
- 水平分片：基于范围的分片，把不同的行放到不同的表中。
- 垂直分片：区分功能数据，将特定功能的数据分到它们自己的服务器上。

分区：
- 基于键或哈希的分区：对条目的一些关键属性应用哈希函数，以获得分区号码。
- 列表式分区：每个分区都被分配了一个值的列表。
- 轮流分区：有了n个分区，i元组被分配到分区i % n。
- 复合分区：混用多种分区方式，如一致性散列是散列和列表分区的复合。密钥 -> 通过散列减少密钥空间 -> 列表 -> 分区。

分片的常见问题：大多数问题因素是由于跨越多个表或同一表中的多条行的操作将不再在同一服务器上运行。
- 效率问题：由于数据必须从多个服务器或多张表中获取，效率降低。
- 完整性问题：难以执行数据完整性约束（如外键）。
- 数据分布不均匀问题。

### <a name="28">代理</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
代理服务器是位于客户和后端服务器之间的一个中间硬件/软件。
- 过滤请求
- 记录请求
- 转换请求（加密、压缩等）。
- 缓存
- 批量请求
- 折叠转发：使同一URI的多个客户端请求作为一个请求被处理到后端服务器。 对存储空间上相距较近的数据进行折叠式请求，以尽量减少读取的次数

### <a name="29">冗余</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
冗余：关键数据或服务的重复，旨在提高系统的可靠性。
- 服务器故障转移：移除单点故障并提供备份（如服务器故障转移）。
- 无共享的架构
  - 每个节点可以相互独立运行。
  - 没有管理状态或协调活动的中央服务。
  - 无状态：无需特殊条件或知识就可以增加新的服务器。
  - 没有单点故障。

## <a name="30">限流</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

流量统计指标：
- 每秒事务数（Transactions per Second，TPS）：TPS 是衡量信息系统吞吐量的最终标准。
- 每秒查询数（Queries per Second，QPS）：QPS 是指一台服务器能够响应的查询次数。
- 每秒请求数（Hits per Second，HPS）：HPS 是指每秒从客户端发向服务端的请求数
> 如果只要一个请求就能完成一笔业务，那 HPS 与 TPS 是等价的，但在分布式系统中，一个请求的响应往往要由后台多个服务节点共同协作来完成。

> 以上这三个指标都是基于调用计数的指标，在整体目标上我们当然最希望能够基于 TPS 来限流，因为信息系统最终是为人类用户来提供服务的，用户不关心业务到底是由多少个请求、多少个后台查询共同协作来实现。但是，系统的业务五花八门，不同的业务操作对系统的压力往往差异巨大，不具备可比性；而更关键的是，流量控制是针对用户实际操作场景来限流的，这不同于压力测试场景中无间隙（最多有些集合点）的全自动化操作，真实业务操作的耗时无可避免地受限于用户交互带来的不确定性，譬如前面例子中的“扫描支付二维码”这个步骤，如果用户掏出手机扫描二维码前先顺便回了两条短信息，那整个付款操作就要持续更长时间。此时，如果按照业务开始时计数器加 1，业务结束时计数器减 1，通过限制最大 TPS 来限流的话，就不能准确地反应出系统所承受的压力，所以直接针对 TPS 来限流实际上是很难操作的。

> 目前，主流系统大多倾向使用 HPS 作为首选的限流指标，它是相对容易观察统计的，而且能够在一定程度上反应系统当前以及接下来一段时间的压力。但**限流指标并不存在任何必须遵循的权威法则**，**根据系统的实际需要**，哪怕完全不选择基于调用计数的指标都是有可能的。譬如下载、视频、直播等 I/O 密集型系统，往往会把每次请求和响应报文的大小，而不是调用次数作为限流指标，譬如只允许单位时间通过 100MB 的流量。又譬如网络游戏等基于长连接的应用，可能会把登陆用户数作为限流指标，热门的网游往往超过一定用户数就会让你在登陆前排队等候。


### <a name="31">限流算法</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

流量计数器模式：设置一个计算器，根据当前时刻的流量计数结果是否超过阈值来决定是否限流，如控制任何一秒内，发现超过 80 次业务请求就直接拒绝掉超额部分。
> 缺陷：可能存在两个时间间隔总流量大于限制请求数情况，如前半秒+后半秒的时间区间大于限制数。以及超时误杀。(详细见周老师凤凰架构)


滑动时间窗模式

漏桶模式

令牌桶模式

TODO

### <a name="32">分布式限流</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

微服务架构下，上述的限流算法就最多只能应用于集群最入口处的网关上，对整个服务集群进行流量控制，而无法细粒度地管理流量在内部微服务节点中的流转情况。所以，我们把前面介绍的限流模式都统称为单机限流，把能够精细控制分布式集群中每个服务消耗量的限流算法称为分布式限流。


一种常见的简单分布式限流方法是将所有服务的统计结果都存入集中式缓存（如 Redis）中，以实现在集群内的共享，并通过分布式锁、信号量等机制，解决这些数据的读写访问时并发控制的问题。
在可以共享统计数据的前提下，原本用于单机的限流模式理论上也是可以应用于分布式环境中的，可是其代价也显而易见：每次服务调用都必须要额外增加一次网络开销，所以这种方法的效率肯定是不高的，流量压力大时，限流本身反倒会显著降低系统的处理能力。


### <a name="33">实际应用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

```properties
zuul.ratelimit.add-response-headers = false
zuul.ratelimit.behind-proxy = true
zuul.ratelimit.default-policy-list[0].block-interval = 86400
zuul.ratelimit.default-policy-list[0].block-on-match = true
zuul.ratelimit.default-policy-list[0].limit = 1000
zuul.ratelimit.default-policy-list[0].quota = 
zuul.ratelimit.default-policy-list[0].refresh-interval = 60
zuul.ratelimit.default-policy-list[0].type[0] = origin
zuul.ratelimit.default-policy-list[1].block-interval = 86400
zuul.ratelimit.default-policy-list[1].block-on-match = true
zuul.ratelimit.default-policy-list[1].limit = 500
zuul.ratelimit.default-policy-list[1].refresh-interval = 60
zuul.ratelimit.default-policy-list[1].type[0] = user
zuul.ratelimit.enabled = false
zuul.ratelimit.ignore-url-list[0] = /api/v1/user/v-code/captcha/verify
zuul.ratelimit.key-prefix = zuul
zuul.ratelimit.monitor = true
zuul.ratelimit.policy-list.weimei[0].limit = 50
zuul.ratelimit.policy-list.weimei[0].refresh-interval = 60
zuul.ratelimit.policy-list.weimei[0].type[0] = origin
zuul.ratelimit.repository = REDIS
zuul.ratelimit.verify-path = /api/v1/user/v-code/captcha/verify
```

## <a name="34">熔断</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>



## <a name="35">监控 </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
// TODO 全栈监控 

SLA 

MTTA