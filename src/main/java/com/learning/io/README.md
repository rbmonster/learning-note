# Java IO 

- Java 的 I/O 大概可以分成以下几类：

1. 磁盘操作：File
2. 字节操作：InputStream 和 OutputStream
3. 字符操作：Reader 和 Writer
4. 对象操作：Serializable
5. 网络操作：Socket
6.  新的输入/输出：NIO

- InputStream的作用是用来表示那些从不同数据源产生输入的类。
1. 字节数组
2. String对象
3. 文件
4. "管道“，工作方式与实际管道类似，即一端输入另一端输出
5. 其他数据源，如Internet连接等

- Java I/O 使用了装饰者模式来实现。以 InputStream 为例，

- InputStream 是抽象组件；
- FileInputStream 是 InputStream 的子类，属于具体组件，提供了字节流的输入操作；
- FilterInputStream 属于抽象装饰者，装饰者用于装饰组件，为组件提供额外的功能。例如 BufferedInputStream 为 FileInputStream 提供缓存的功能。

![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/io/picture/inputStreamStructure.jpg)

### 编码与解码
- 编码就是把字符转换为字节，而解码是把字节重新组合成字符。
    - 如果编码和解码过程使用不同的编码方式那么就出现了乱码。
    - GBK 编码中，中文字符占 2 个字节，英文字符占 1 个字节；
    - UTF-8 编码中，中文字符占 3 个字节，英文字符占 1 个字节；
    - UTF-16be 编码中，中文字符和英文字符都占 2 个字节。

- String 编码转换
```
String str1 = "中文";
byte[] bytes = str1.getBytes("UTF-8");
String str2 = new String(bytes, "UTF-8");
System.out.println(str2);
```

- Reader 与 Writer
  - 不管是磁盘还是网络传输，最小的存储单元都是字节，而不是字符。但是在程序中操作的通常是字符形式的数据，因此需要提供对字符进行操作的方法。
  - InputStreamReader 实现从字节流解码成字符流；
  - OutputStreamWriter 实现字符流编码成为字节流。
  
### Java 中的网络支持

- InetAddress：用于表示网络上的硬件资源，即 IP 地址；
- URL：统一资源定位符；
- Sockets：使用 TCP 协议实现网络通信；
- Datagram：使用 UDP 协议实现网络通信。


#### BIO 阻塞IO
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/io/picture/socket.jpg)
- 在不考虑多线程的情况下，BIO是无法处理多个客户端请求的。
- BIO 通信模型 的服务端，通常由一个独立的 Acceptor 线程负责监听客户端的连接。
- 多线程情况下对于服务端，服务端只能用线程开启多个线程与客户端建立连接。

BIO多线程情况下的缺点：
1. 线程的创建和销毁成本很高，在Linux这样的操作系统中，线程本质上就是一个进程。创建和销毁都是重量级的系统函数。 
2. 线程本身占用较大内存，像Java的线程栈，一般至少分配512K～1M的空间，如果系统中的线程数过千，恐怕整个JVM的内存都会被吃掉一半。 
3. 线程的切换成本是很高的。操作系统发生线程切换的时候，需要保留线程的上下文，然后执行系统调用。如果线程数过高，可能执行线程切换的时间甚至会大于线程执行的时间，这时候带来的表现往往是系统load偏高、CPU 使用率特别高（超过20%以上)，导致系统几乎陷入不可用的状态。 
4. 容易造成锯齿状的系统负载。因为系统负载是用活动线程数或CPU核心数，一旦线程数量高但外部网络环境不是很稳定，就很容易造成大量请求的结果同时返回，激活大量阻塞线程从而使系统负载压力过大。

#### 常见I/O模型对比
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/io/picture/ioModel.jpg)

- 所有的系统I/O都分为两个阶段：等待就绪和操作。
  - 举例来说，读函数，分为等f待系统可读和真正的读；
  - 同理，写函数分为等待网卡可以写和真正的写。

- 需要说明的是等待就绪的阻塞是不使用CPU的，是在“空等”；
- 而真正的读写操作的阻塞是使用CPU的，真正在”干活”，而且这个过程非常快，属于memory copy，带宽通常在1GB/s级别以上，可以理解为基本不耗时。

#### NIO
```
interface ChannelHandler{
      void channelReadable(Channel channel);
      void channelWritable(Channel channel);
   }
   class Channel{
     Socket socket;
     Event event;//读，写或者连接
   }

   //IO线程主循环:
   class IoThread extends Thread{
   public void run(){
   Channel channel;
   while(channel=Selector.select()){//选择就绪的事件和对应的连接
      if(channel.event==accept){
         registerNewChannelHandler(channel);//如果是新连接，则注册一个新的读写处理器
      }
      if(channel.event==write){
         getChannelHandler(channel).channelWritable(channel);//如果可以写，则执行写事件
      }
      if(channel.event==read){
          getChannelHandler(channel).channelReadable(channel);//如果可以读，则执行读事件
      }
    }
   }
   Map<Channel，ChannelHandler> handlerMap;//所有channel的对应事件处理器
```
- NIO的主要事件有几个：读就绪、写就绪、有新连接到来。
  - 首先需要注册当这几个事件到来的时候所对应的处理器。
  - 然后在合适的时机告诉事件选择器：我对这个事件感兴趣。
  - 其次，用一个死循环选择就绪的事件，会执行系统调用，还会阻塞的等待新事件的到来。（系统调用指的是操作系统的函数调用，Linux 2.6之前是select、poll，2.6之后是epoll，Windows是IOCP）
  - 新事件到来的时候，会在selector上注册标记位，标示可读、可写或者有连接到来。
  - 注意，select是阻塞的，无论是通过操作系统的通知（epoll）还是不停的轮询(select，poll)，这个函数是阻塞的。

- NIO由原来的阻塞读写（占用线程）变成了单线程轮询事件，找到可以进行读写的网络描述符进行读写。除了事件的轮询是阻塞的（没有可干的事情必须要阻塞），剩余的I/O操作都是纯CPU操作，没有必要开启多线程。

- Java的Selector对于Linux系统来说，有一个致命限制：同一个channel的select不能被并发的调用。因此，如果有多个I/O线程，必须保证：一个socket只能属于一个IoThread，而一个IoThread可以管理多个socket。

##### 与BIO区别
- Non-blocking IO（非阻塞IO）：IO流是阻塞的，NIO流是不阻塞的。
- Buffer(缓冲区) IO 面向流(Stream oriented)，而 NIO 面向缓冲区(Buffer oriented)。
  1. 在面向流的I/O中·可以将数据直接写入或者将数据直接读到 Stream 对象中。虽然 Stream 中也有 Buffer 开头的扩展类，但只是流的包装类，还是从流读到缓冲区，而 NIO 却是直接读到 Buffer 中进行操作。
  2. 在NIO厍中，所有数据都是用缓冲区处理的。在读取数据时，它是直接读到缓冲区中的; 在写入数据时，写入到缓冲区中。任何时候访问NIO中的数据，都是通过缓冲区进行操作。
- Channel (通道):NIO 通过Channel（通道） 进行读写。通道是双向的，可读也可写，而流的读写是单向的。
- Selector (选择器) :NIO有选择器，而IO没有。


#### 信号驱动IO
- 在通道中安装一个信号器：映射到Linux操作系统中，这就是信号驱动IO。应用进程在读取文件时通知内核，如果某个 socket 的某个事件发生时，请向我发一个信号。

#### IO复用
- IO复用模型：多个进程的IO可以注册到同一个管道上，这个管道会统一和内核进行交互。当管道中的某一个请求需要的数据准备好之后，进程再把对应的数据拷贝到用户空间中。

- 阻塞IO模型、非阻塞IO模型、IO复用模型和信号驱动IO模型都是同步的IO模型。原因是因为，无论以上那种模型，真正的数据拷贝过程，都是同步进行的。
- 把钓鱼过程，可以拆分为两个步骤：1、鱼咬钩（数据准备）。2、把鱼钓起来放进鱼篓里（数据拷贝）。
  - 烧水的报警器一响，整个烧水过程就完成了。水已经是开水了。 
  - 钓鱼的报警器一响，只能说明鱼儿已经咬钩了，但是还没有真正的钓上来。

### Proactor与Reactor

- 在Reactor中实现读
1. 注册读就绪事件和相应的事件处理器。
2. 事件分发器等待事件。
3. 事件到来，激活分发器，分发器调用事件对应的处理器。
4. 事件处理器完成实际的读操作，处理读到的数据，注册新的事件，然后返还控制权。

- 在Proactor中实现读：
1. 处理器发起异步读操作（注意：操作系统必须支持异步IO）。在这种情况下，处理器无视IO就绪事件，它关注的是完成事件。
2. 事件分发器等待操作完成事件。
3. 在分发器等待过程中，操作系统利用并行的内核线程执行实际的读操作，并将结果数据存入用户自定义缓冲区，最后通知事件分发器读操作完成。
4. 事件分发器呼唤处理器。
5. 事件处理器处理用户自定义缓冲区中的数据，然后启动一个新的异步操作，并将控制权返回事件分发器。

- 两者也有相同点：事件分发器负责提交IO操作（异步)、查询设备是否可操作（同步)，然后当条件满足时，就回调handler；
- 不同点在于，异步情况下（Proactor)，当回调handler时，表示I/O操作已经完成；同步情况下（Reactor)，回调handler时，表示I/O设备可以进行某个操作（can read 或 can write)。


### RMI 远程方法调用
- java支持，最早的远程调用，使用Remote接口，同时实现类别需要继承UnicastRemoteObject 
- 通过Registry，注册发现远程方法，并调用接口。



### netty
- Netty 是一个 基于 NIO 的 client-server(客户端服务器)框架，使用它可以快速简单地开发网络应用程序。
  - 它极大地简化并优化了 TCP 和 UDP 套接字服务器等网络编程,并且性能以及安全性等很多方面甚至都要更好。
  - 支持多种协议 如 FTP，SMTP，HTTP 以及各种二进制和基于文本的传统协议。
  
- 支持多个交互模型
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/io/picture/netty.jpg)
- Reactor分成两部分，mainReactor负责监听server socket，accept新连接；并将建立的socket分派给subReactor。subReactor负责多路分离已连接的socket，读写网络数据，对业务处理功能，其扔给worker线程池完成。

- Netty中的事件分为Inbond事件和Outbound事件。
  - Inbound事件通常由I/O线程触发，如TCP链路建立事件、链路关闭事件、读事件、异常通知事件等。
  - Outbound事件通常是用户主动发起的网络I/O操作，如用户发起的连接操作、绑定操作、消息发送等。

- 相比NIO ：
  - NIO在面对断连重连、包丢失、粘包等问题时处理过程非常复杂。Netty的出现正是为了解决这些问题。
  - 解决了JDK 的 NIO 底层由 epoll 实现，该实现饱受诟病的空轮询 bug 会导致 cpu 飙升 100%
  - 通过代码封装，简化了服务端与客户端的代码交互。
  - 数据直接复制到directBuffer的工作缓冲区
