<a name="index">**Index**</a>

<a href="#0">Netty</a>  
&emsp;<a href="#1">1. netty 是什么</a>  
&emsp;<a href="#2">2. Reactor 模式</a>  
&emsp;&emsp;<a href="#3">2.1. 单线程Reactor</a>  
&emsp;&emsp;<a href="#4">2.2. 多线程Reactor</a>  
&emsp;&emsp;<a href="#5">2.3. 主从多线程Reactor</a>  
&emsp;<a href="#6">3. Netty的核心组件</a>  
&emsp;&emsp;<a href="#7">3.1. Channel</a>  
&emsp;&emsp;<a href="#8">3.2. EventLoop</a>  
&emsp;&emsp;<a href="#9">3.3. ChannelHandler 和 ChannelPipeline</a>  
&emsp;&emsp;<a href="#10">3.4. Bootstrap 和 ServerBootstrap 引导类</a>  
&emsp;<a href="#11">4. 什么是 TCP 粘包/拆包</a>  
&emsp;<a href="#12">5.  其他</a>  
# <a name="0">Netty</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
> TODO
## <a name="1">netty 是什么</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
 - 基于 NIO 的 RPC 框架的网络通信框架
 - 极大地简化并优化了 TCP 和 UDP 套接字服务器等网络编程
 - 支持多种协议 如 FTP，SMTP，HTTP 以及各种二进制和基于文本的传统协议。
 - 自带编解码器解决 TCP 粘包/拆包问题。
 
 
## <a name="2">Reactor 模式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Reactor模式基于事件驱动，特别适合处理海量的I/O事件

Reactor线程模型分为单线程模型、多线程模型以及主从多线程模型。
 
### <a name="3">单线程Reactor</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
单线程 Reactor 的优点是对系统资源消耗特别小，但是，没办法支撑大量请求的应用场景并且处理请求的时间可能非常慢
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/io/nettyRefactor.jpg)

### <a name="4">多线程Reactor</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
一个线程负责接受请求,一组NIO线程处理IO操作。
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/io/nettyRefactor2.png)

### <a name="5">主从多线程Reactor</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
一组NIO线程负责接受请求，一组NIO线程处理IO操作。
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/io/nettyRefactor2.jpg)


## <a name="6">Netty的核心组件</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note/learning/io/nettyAF.jpg)
Channel 为 Netty 网络操作(读写等操作)抽象类，EventLoop 负责处理注册到其上的Channel 处理 I/O 操作，两者配合参与 I/O 操作。

### <a name="7">Channel</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Channel 接口是 Netty 对网络操作抽象类，它除了包括基本的 I/O 操作，如 bind()、connect()、read()、write() 等。
比较常用的Channel接口实现类是NioServerSocketChannel（服务端）和NioSocketChannel（客户端），这两个 Channel 可以和 BIO 编程模型中的ServerSocket以及Socket两个概念对应上。

### <a name="8">EventLoop</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
EventLoop 定义了 Netty 的核心抽象，用于处理连接的生命周期中所发生的事件。负责监听网络事件并调用事件处理器进行相关 I/O 操作的处理。

EventLoopGroup 包含多个 EventLoop（每一个 EventLoop 通常内部包含一个线程）,EventLoop 处理的 I/O 事件都将在它专有的 Thread 上被处理，即 Thread 和 EventLoop 属于 1 : 1 的关系，从而保证线程安全。

客户端连接处理流程：
1. 当客户端通过 connect 方法连接服务端时，bossGroup 处理客户端连接请求。
2. 当客户端处理完成后，会将这个连接提交给 workerGroup 来处理，然后 workerGroup 负责处理其 IO 相关操作。

### <a name="9">ChannelHandler 和 ChannelPipeline</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

ChannelHandler 是消息的具体处理器。他负责处理读写操作、客户端连接等事情。

ChannelPipeline 为 ChannelHandler 的链，提供了一个容器并定义了用于沿着链传播入站和出站事件流的 API 。当 Channel 被创建时，它会被自动地分配到它专属的 ChannelPipeline。
```
  b.group(eventLoopGroup)
    .handler(new ChannelInitializer<SocketChannel>() {
        @Override
        protected void initChannel(SocketChannel ch) {
            ch.pipeline().addLast(new NettyKryoDecoder(kryoSerializer, RpcResponse.class));
            ch.pipeline().addLast(new NettyKryoEncoder(kryoSerializer, RpcRequest.class));
            ch.pipeline().addLast(new KryoClientHandler());
        }
    });
```


### <a name="10">Bootstrap 和 ServerBootstrap 引导类</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

ServerBootstrap服务端启动：
1.首先你创建了两个 NioEventLoopGroup 对象实例：bossGroup 和 workerGroup。
  - bossGroup : 用于处理客户端的 TCP 连接请求。
  - workerGroup ：负责每一条连接的具体读写数据的处理逻辑，真正负责 I/O 读写操作，交由对应的 Handler 处理。
  - 一般情况下我们会指定 bossGroup 的 线程数为 1（并发连接量不大的时候） ，workGroup 的线程数量为 CPU 核心数 *2 。另外，根据源码来看，使用 NioEventLoopGroup 类的无参构造函数设置线程数量的默认值就是 CPU 核心数 *2 。
2.接下来 我们创建了一个服务端启动引导/辅助类：ServerBootstrap，这个类将引导我们进行服务端的启动工作。
3.通过 .group() 方法给引导类 ServerBootstrap 配置两大线程组，确定了**线程模型**。

Bootstrap客户端启动：
1.创建一个 Bootstrap、NioEventLoopGroup 对象实例
2.通过 .group() 方法给引导类 Bootstrap 配置一个线程组
3.通过channel()方法给引导类 Bootstrap指定了 IO 模型为NIO
4.通过 .childHandler()给引导类创建一个ChannelInitializer ，然后指定了客户端消息的业务处理逻辑 ClientHandler 对象
5.调用 Bootstrap 类的 connect()方法进行连接，这个方法需要指定两个参数：IP、端口

## <a name="11">什么是 TCP 粘包/拆包</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
粘包与拆包：基于 TCP 发送数据的时候，出现了多个字符串“粘”在了一起或者一个字符串被“拆”开的问题。

 Netty 自带的解码器
 - LineBasedFrameDecoder : 发送端发送数据包的时候，每个数据包之间以换行符作为分隔。
 - DelimiterBasedFrameDecoder : 可以自定义分隔符解码器
 
 
 ## <a name="12">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
 Netty 长连接、心跳机制
 - TCP连接三次握手、四次挥手耗费网络资源，且多次建立连接更消耗资源。因此使用长连接的方式，为了保证长连接的有效性，引入心跳机制，在空闲时刻也可以维持连接。
 
 Netty 的零拷贝
 