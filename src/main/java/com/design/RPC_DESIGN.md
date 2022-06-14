# 手写RPC框架

## RPC介绍
RPC（Remote Procedure Call）远程过程调用协议\
RPC协议是假定某些传输协议的存在，如TCP或UDP，为通信程序之间携带信息数据。在OSI网络通信模型中，RPC跨越了传输层和应用层。RPC在包括网络分布式多程序在内的应用程序开发更加容易。


### RPC架构的作用

RPC框架的主要目标就是让远程服务调用更简单、透明。RPC框架负责屏蔽底层的传输方式（TCP或者UDP）、序列化方式（XML/JSON/二进制）和通信细节。开发人员在使用的时候只需要了解谁在什么位置提供了什么样的远程服务接口即可，并不需要关心底层通信细节和调用过程。
1. 从通信协议的层面，大致可以分为：
    - 基于HTTP协议的（例如基于文本的SOAP（XML）、Rest（JSON），基于二进制Hessian（Binary））；
    - 基于TCP协议的（通常会借助Mina、Netty等高性能网络框架）。
2. RPC架构分为三部分：
- 服务提供者，运行在服务器端，提供服务接口定义与服务实现类。
- 服务中心，运行在服务器端，负责将本地服务发布成远程服务，管理远程服务，提供给服务消费者使用。
- 服务消费者，运行在客户端，通过远程代理对象调用远程服务。


### RPC的性能优化
1. 性能，影响RPC性能的主要在几个方面：
- 序列化/反序列化的框架；
- 网络协议，网络模型，线程模型等。
2. 安全
RPC安全的主要在于服务接口的鉴权和访问控制支持。
   
   
### RPC与HTTP的区别

1. 传输协议：RPC是一种API，HTTP是一种无状态的网络协议。RPC可以基于HTTP协议实现，也可以直接在TCP协议上实现。
   > dubbo、cxf、（RMI远程方法调用）Hessian 都是基于TCP协议实现，避免了进行http协议的数据封装，可以实现高效的数据传输调用。
2. 数据传输格式：RPC并没有规定数据传输格式，这个格式可以任意指定，不同的RPC协议，数据格式不一定相同。
3. 调用细节：
   - RPC需要满足像调用本地服务一样调用远程服务，也就是对调用过程在API层面进行封装。
   - Http协议没有API调用层面的要求，因此请求、响应等细节需要我们自己去实现。

#### 两者优缺点及场景
RPC优缺点：
- 优势：
   1. RPC方式进行API封装的调用方式，对用户更方便，更加透明。
   2. 基于原生TCP通信，速度快，效率高。而http协议的信息封装比较臃肿。
   > 如：在通用定义的HTTP1.1协议的TCP报文中包含太多废信息
- 缺点：
   1. RPC方式需要在API层面进行封装，限制了开发的语言环境。要求服务提供方和服务消费方都必须使用统一的RPC框架。
   2. RPC框架的实现难度比较大。
   
HTTP协议优缺点：
- 优点：无需关注服务使用的编程语言，通用性强，不关心实现细节，跨平台、跨语言。
- 缺点：为保证HTTP协议的通用性，进行了较为复杂的数据封装。HTTP大部分是通过Json来实现的，字节大小和序列化耗时都比使用纯二进制传输的RPC效率低。


场景：
1. RPC主要是用在大型网站里面，因为大型网站里面系统繁多，业务线复杂，而且效率优势非常重要的一块
2. HTTP主要是用在中小型企业里面，业务线没那么繁多的情况。如果需要更加灵活，跨语言、跨平台，显然http更合适

#### 性能对比
在通用定义的HTTP1.1协议的TCP报文中包含太多废信息，我们来看看一个POST协议的大致格式
```
HTTP/1.1 200 OK 
Content-Type: text/plain
Content-Length: 137582
Expires: Thu, 05 Dec 1997 16:00:00 GMT
Last-Modified: Wed, 5 August 1996 15:55:28 GMT
Server: Apache 0.84

<html>
  <body>Hello World</body>
</html>
```
即使编码协议即Body采用的是二进制编码协议，但是报文元数据也就是Header头的键值对还是使用了文本编码，我们可以看到上面的这条协议，其实有效字节数只有30%，虽然实际应用中报文内容不会那么短，但是累计下来报头的占比也是相当可观的。

自定义报文头，可以大大精简传输内容。

这里的效率优势仅仅是针对 HTTP1.1协议来说的，HTTP2.0协议已经有优化了编码效率，而且也支持了多路复用等等高性能的能力，例如GRPC这种RPC库就是使用了HTTP2.0协议。所以其实在性能上，两者的差距已经不在那么巨大。但仅针对于HTTP1.1协议与RPC协议来对比，后者的性能能够达到前者的10倍甚至100倍。

### 参考资料
- [RPC数据通信](https://www.cnblogs.com/klb561/p/9142529.html)
- [Rpc和Http的区别](https://zhuanlan.zhihu.com/p/110424162#:~:text=1.2.%E8%AE%A4%E8%AF%86Http%201%20RPC%E5%B9%B6%E6%B2%A1%E6%9C%89%E8%A7%84%E5%AE%9A%E6%95%B0%E6%8D%AE%E4%BC%A0%E8%BE%93%E6%A0%BC%E5%BC%8F%EF%BC%8C%E8%BF%99%E4%B8%AA%E6%A0%BC%E5%BC%8F%E5%8F%AF%E4%BB%A5%E4%BB%BB%E6%84%8F%E6%8C%87%E5%AE%9A%EF%BC%8C%E4%B8%8D%E5%90%8C%E7%9A%84RPC%E5%8D%8F%E8%AE%AE%EF%BC%8C%E6%95%B0%E6%8D%AE%E6%A0%BC%E5%BC%8F%E4%B8%8D%E4%B8%80%E5%AE%9A%E7%9B%B8%E5%90%8C%E3%80%82%202%20Http%E4%B8%AD%E8%BF%98%E5%AE%9A%E4%B9%89%E4%BA%86%E8%B5%84%E6%BA%90%E5%AE%9A%E4%BD%8D%E7%9A%84%E8%B7%AF%E5%BE%84%EF%BC%8CRPC%E4%B8%AD%E5%B9%B6%E4%B8%8D%E9%9C%80%E8%A6%81,3%20%E6%9C%80%E9%87%8D%E8%A6%81%E7%9A%84%E4%B8%80%E7%82%B9%EF%BC%9ARPC%E9%9C%80%E8%A6%81%E6%BB%A1%E8%B6%B3%E5%83%8F%E8%B0%83%E7%94%A8%E6%9C%AC%E5%9C%B0%E6%9C%8D%E5%8A%A1%E4%B8%80%E6%A0%B7%E8%B0%83%E7%94%A8%E8%BF%9C%E7%A8%8B%E6%9C%8D%E5%8A%A1%EF%BC%8C%E4%B9%9F%E5%B0%B1%E6%98%AF%E5%AF%B9%E8%B0%83%E7%94%A8%E8%BF%87%E7%A8%8B%E5%9C%A8API%E5%B1%82%E9%9D%A2%E8%BF%9B%E8%A1%8C%E5%B0%81%E8%A3%85%E3%80%82Http%E5%8D%8F%E8%AE%AE%E6%B2%A1%E6%9C%89%E8%BF%99%E6%A0%B7%E7%9A%84%E8%A6%81%E6%B1%82%EF%BC%8C%E5%9B%A0%E6%AD%A4%E8%AF%B7%E6%B1%82%E3%80%81%E5%93%8D%E5%BA%94%E7%AD%89%E7%BB%86%E8%8A%82%E9%9C%80%E8%A6%81%E6%88%91%E4%BB%AC%E8%87%AA%E5%B7%B1%E5%8E%BB%E5%AE%9E%E7%8E%B0%E3%80%82%204%20%E4%BC%98%E7%82%B9%EF%BC%9ARPC%E6%96%B9%E5%BC%8F%E6%9B%B4%E5%8A%A0%E9%80%8F%E6%98%8E%EF%BC%8C%E5%AF%B9%E7%94%A8%E6%88%B7%E6%9B%B4%E6%96%B9%E4%BE%BF%E3%80%82Http%E6%96%B9%E5%BC%8F%E6%9B%B4%E7%81%B5%E6%B4%BB%EF%BC%8C%E6%B2%A1%E6%9C%89%E8%A7%84%E5%AE%9AAPI%E5%92%8C%E8%AF%AD%E8%A8%80%EF%BC%8C%E8%B7%A8%E8%AF%AD%E8%A8%80%E3%80%81%E8%B7%A8%E5%B9%B3%E5%8F%B0%205%20%E7%BC%BA%E7%82%B9%EF%BC%9ARPC%E6%96%B9%E5%BC%8F%E9%9C%80%E8%A6%81%E5%9C%A8API%E5%B1%82%E9%9D%A2%E8%BF%9B%E8%A1%8C%E5%B0%81%E8%A3%85%EF%BC%8C%E9%99%90%E5%88%B6%E4%BA%86%E5%BC%80%E5%8F%91%E7%9A%84%E8%AF%AD%E8%A8%80%E7%8E%AF%E5%A2%83%E3%80%82)
- 效率对比：[HTTP与RPC之间的区别](https://developers.weixin.qq.com/community/develop/article/doc/00064c08ca42d87d033c2f17656413)
- 框架横向对比：[RPC简介及框架选择](https://www.jianshu.com/p/b0343bfd216e)

## RPC框架分析
一款分布式RPC框架离不开三个基本要素：
- 服务提供者 Service Provider，其需要对外提供服务接口，它需要在应用启动时连接注册中心，将服务名及其服务元数据发往注册中心。
- 注册中心 Registry。主要是用来完成服务注册和发现的工作。
- 服务消费者 Service Consumer，客户端需要有从注册中心获取服务的基本能力，它需要在应用启动时，扫描依赖的RPC服务，并为其生成代理调用对象，同时从注册中心拉取服务元数据存入本地缓存，然后发起监听各服务的变动做到及时更新缓存。

围绕上面三个基本要素可以进一步扩展服务路由、负载均衡、服务熔断降级、序列化协议、通信协议等等。
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note//other/rpc.png)


### 技术选型
#### 注册中心
目前成熟的注册中心有Zookeeper，Nacos，Consul，Eureka，它们的主要比较如下：
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note//other/rpcRegister.png)

#### IO通信框架 
Netty、MINA等等均可以作为底层通信框架，目前使用Netty作为通信框架的居多

#### 通信协议
TCP通信过程中会根据TCP缓冲区的实际情况进行包的划分，所以在业务上认为一个完整的包可能会被TCP拆分成多个包进行发送，也有可能把多个小的包封装成一个大的数据包发送，这就是所谓的TCP粘包和拆包问题。所以需要对发送的数据包封装到一种通信协议里。

业界的主流协议的解决方案可以归纳如下：
1. 消息定长，例如每个报文的大小为固定长度100字节，如果不够用空格补足。
2. 在包尾特殊结束符进行分割。
3. 将消息分为消息头和消息体，消息头中包含表示消息总长度（或者消息体长度）的字段。

很明显1，2都有些局限性，方案一造成了传输的浪费，方案二作为切割的特殊处理方式首先不优雅，其次若特殊字符用于传输则会导致切割问题

#### 序列化协议
目前有多种的序列化协议如JavaSerializer、Protobuf及Hessian。建议选用Protobuf，其序列化后码流小性能高，非常适合RPC调用，Google自家的gRPC也是用其作为通信协议。

### 参考架构
![image](https://raw.githubusercontent.com/rbmonster/file-storage/main/learning-note//other/rpcDesign.png)

## Java基于Spring实现思路
注册中心使用zookeeper，通信框架使用Netty

1. 项目架构.
   1. 封装通用的rpc-spring-starter的底层jar包，用于其他需要rpc框架的项目引用。
   2. 服务间接口调用接口单独出interface，供 serviceProvider与 serviceConsumer引用
2. spring-starter提供@RpcProvider与@RpcConsumer注解，@RpcProvider用于标注与类上，而@RpcConsumer用于标注于字段上
3. 在spring上下文 RefreshEvent中，根据获serviceId注册zookeeper持久节点，取所有@RpcProvider注解，注册临时节点于serviceId下。对应的内容为调用的nettyServer的host、port、及调用类、版本。
4. 服务信息注册完成后，拉取其他节点的注册信息，根据同样的路径解析，缓存到本地的ServiceMateDataCache
5. 启动Netty的server服务器
4. 使用动态代理，在spring初始化过程中对@RpcConsumer注解的字段，注入代理对象。代理对象中对方法进行拦截，包装成rpcRequest使用netty传输。
6. NettyServer接收到rpcRequest，获取rpcRequest中的，class、method、parameter、parameterClass，使用反射调用本地的serviceProvider
7. 调用结果封装成rpcResponse进行返回，包含成功标志、data、errorMsg
8. 动态代理获取到调用rpcResponse，获取data，转换成对应的对象作为方法调用返回结果。

## 参考资料
- [自己动手从0开始实现一个分布式RPC框架](https://mp.weixin.qq.com/s/yaIOCfEigkQMm2kt6I7Orw)
- [手写 RPC 框架](https://www.jianshu.com/p/096dbda7d528)