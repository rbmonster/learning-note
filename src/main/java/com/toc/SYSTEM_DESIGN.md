<a name="index">**Index**</a>

<a href="#0">设计及架构思想</a>  
&emsp;<a href="#1">1. 编程思想</a>  
&emsp;&emsp;<a href="#2">1.1. 面向对象编程(Object Oriented Programming，OOP)</a>  
&emsp;&emsp;<a href="#3">1.2. 面向过程编程</a>  
&emsp;&emsp;<a href="#4">1.3. 函数式编程</a>  
&emsp;<a href="#5">2. 六大设计原则</a>  
&emsp;&emsp;<a href="#6">2.1. 单一职责原则</a>  
&emsp;<a href="#7">3. MVC 模式</a>  
&emsp;<a href="#8">4. BFF(Backend for Frontend)</a>  
&emsp;<a href="#9">5. 系统架构</a>  
&emsp;&emsp;<a href="#10">5.1. 单体</a>  
&emsp;&emsp;<a href="#11">5.2. 集群</a>  
&emsp;&emsp;<a href="#12">5.3. 分布式</a>  
&emsp;&emsp;<a href="#13">5.4. 微服务</a>  
&emsp;&emsp;&emsp;<a href="#14">5.4.1. 拆分原则</a>  
&emsp;&emsp;&emsp;<a href="#15">5.4.2. DDD 领域驱动</a>  
&emsp;<a href="#16">6. 相关资料</a>  
# <a name="0">设计及架构思想</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

## <a name="1">编程思想</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="2">面向对象编程(Object Oriented Programming，OOP)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
面向对象编程思想是以现实世界中事物，建立模型体现出来的抽象思维过程。
根据抽象的模型，依照事物之间的关系及方法进行操作，以求达到重用性、灵活性和扩展性的设计目的。
> 面向对象编程是把构成问题的事务分解成各个对象，建立对象的目的不是为了完成一个步骤，而是为了描叙某个事物在整个解决问题的步骤中的行为。

OOP=对象+类+继承+多态+消息，其中核心概念是类和对象。

特点： 封装、多态、继承

### <a name="3">面向过程编程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
面向过程编程就是分析出解决问题所需要的步骤，然后用函数把这些步骤一步一步实现，使用的时候一个一个依次调用就可以了。

### <a name="4">函数式编程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
函数式编程类似于面向过程的程序设计，但其思想更接近数学计算。允许把函数本身作为参数传入另一个函数，还允许返回一个函数。是一种抽象程度很高的编程范式，纯粹的函数式编程语言编写的函数没有变量。
> 面向过程编程体现的是解决方法的步骤，而函数式编程体现的是数据集的映射。

## <a name="5">六大设计原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="6">单一职责原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：单一职责原则适用于类、接口、方法。

## <a name="7">MVC 模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
MVC是一种框架模式。经典MVC模式中，M是指业务模型，V是指用户界面，C则是控制器。

使用MVC的目的是将M和V的实现代码分离，从而使同一个程序可以使用不同的表现形式。其中，View的定义比较清晰，就是用户界面。

## <a name="8">BFF(Backend for Frontend)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
BFF，即 Backend For Frontend（服务于前端的后端），也就是服务器设计 API 时会考虑前端的使用，并在服务端直接进行业务逻辑的处理，又称为用户体验适配器。BFF 只是一种逻辑分层，而非一种技术

BFF 解决了什么问题？\
前端页面时常存在，某个页面需要向 backend A、backend B 以及 backend C...... 发送请求，不同服务的返回值用于渲染页面中不同的 component，即一个页面存在很多请求的场景。
有了 BFF 这一层时，我们就不需要考虑系统后端的迁移。后端发生的变化都可以在 BFF 层做一些响应的修改。

![image](https://github.com/rbmonster/file-storage/blob/main/learning-note/design/systemdesign/bff.png)

- 多端应用: 为不同的设备提供不同的 API，虽然它们可能是实现相同的功能，但因为不同设备的特殊性，它们对服务端的 API 访问也各有其特点，需要区别处理。
- 服务聚合：BFF 的出现为前端应用提供了一个对业务服务调用的聚合点，它屏蔽了复杂的服务调用链，让前端可以聚焦在所需要的数据上，而不用关注底层提供这些数据的服务。
- 认证、授权、请求记录等通用功能可以在BFF层实现，使用依赖包共享的方式实现。而引入额外的服务层可能导致的请求延迟的情况。

缺点：
- 在基础服务上多加了一层转发，带来了响应时间延迟
- 带来的代码重复和工作量增加

参考资料：
- [BFF —— Backend For Frontend](https://www.jianshu.com/p/eb1875c62ad3)
- [Pattern: Backends For Frontends](https://samnewman.io/patterns/architectural/bff/)
## <a name="9">系统架构</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="10">单体</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="11">集群</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="12">分布式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="13">微服务</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="14">拆分原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
垂直划分优先原则：应该根据业务领域对服务进行垂直划分，让团队能关注业务实现。

持续演进原则： 服务数量在非必要的情况下，应该逐步划分，持续演进，避免服务数量的爆炸性增长


#### <a name="15">DDD 领域驱动</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
详细见
[DDD设计思想](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/toc/DDD.md)

## <a name="16">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[微服务拆分方法论](https://blog.csdn.net/no_game_no_life_/article/details/103390169)
