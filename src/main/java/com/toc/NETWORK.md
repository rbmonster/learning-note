<a name="index">**Index**</a>
<a href="#0">计算机网络</a>  
&emsp;<a href="#1">网络模型</a>  
&emsp;<a href="#2">TCP 三次握手和四次挥手</a>  
&emsp;&emsp;<a href="#3">三次握手</a>  
&emsp;&emsp;<a href="#4">四次挥手</a>  
&emsp;<a href="#5">TCP与UDP</a>  
&emsp;<a href="#6">浏览器访问网站的数据传输过程</a>  
&emsp;<a href="#7">http 长连接与短连接</a>  
&emsp;<a href="#8">http 无状态、无连接</a>  
&emsp;<a href="#9">Cookies与 Session （TODO）</a>  
&emsp;&emsp;<a href="#10">Cookies</a>  
&emsp;&emsp;<a href="#11">Session</a>  
&emsp;<a href="#12">https</a>  
&emsp;&emsp;<a href="#13">加密方式</a>  
&emsp;<a href="#14">证书认证</a>  
&emsp;<a href="#15">Http2.0</a>  
# <a name="0">计算机网络</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
## <a name="1">网络模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
5层模型应用层，运输层，网络层，数据链路层，物理层组成。
- 网络层最重要的协议是 IP 协议、ping使用ICMP协议。
- 传输层最主要的协议是 TCP 和 UDP 协议。
- 应用层：HTTP、FTP、SMTP、TELNET、POP3、DNS
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/network1.jpg)

7层结构明细
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/network2.png)


## <a name="2">TCP 三次握手和四次挥手</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="3">三次握手</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/tcpconnect.png)

1. 客户端 – 发送带有 SYN 标志的数据包 – 一次握手 – 服务端
2. 服务端 – 发送带有 SYN/ACK 标志的数据包 – 二次握手 – 客户端
3. 客户端 – 发送带有带有 ACK 标志的数据包 – 三次握手 – 服务端


为什么不两次握手？
**两次握手的问题在于服务器端不知道SYN的有效性** 
- 第一次握手发送SYN数据包，因为网络原因超时，而客户端重新发起一次SYN数据包。则服务端收到两次SYN数据包，会认为要开启两次连接，当然第一次延迟的连接客户端不会接受，服务端会一直维持着这个资源，会造成浪费。
- 同理第二次握手服务端返回的时候，因为网络原因造成超时，重新发起一次SYN数据包也会造成同样的问题。

为什么使用三次握手？
- 三次握手最主要的目的就是双方确认自己与对方的发送与接收是正常的。为了防止已失效的连接请求报文段突然又传送到了服务端，因而产生错误。
- 服务端会在最后一次握手接收到客户端发送的ACK数据包，再分配连接资源。
- 如果是三次握手，服务器端会等待客户端的第三次握手，如果第三次握手迟迟不来，服务器端就会释放相关资源。而客户端虽然开启连接了，通过通信就会发现收到服务器端的RST应答。
- 本质上就是减少服务端无用资源的创建，确保建立可靠的通信信道。
> 为了实现可靠数据传输， TCP 协议的通信双方，都必须维护一个序列号。 三次握手的过程即是通信双方相互告知序列号起始值， 并确认对方已经收到了序列号起始值的必经步骤。


### <a name="4">四次挥手</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/learning-note/blob/master/src/main/java/com/learning/basic/picture/tcpdisconnect.png)

断开一个 TCP 连接需要“四次挥手”：
- 客户端 - 发送一个 FIN、seq数据包，用来关闭客户端到服务器的数据传送
- 服务器 - 收到这个 FIN、seq数据包，它发回一个 ACK，**确认序号为收到的序号加1** 。和 SYN 一样，一个 FIN 将占用一个序号
- 服务器 - 关闭与客户端的连接，发送一个FIN、ack数据包给客户端
- 客户端 - 发回 ACK 报文确认，并将确认序号设置为收到序号加1

断开连接需要四次挥手的原因
1. 前两次握手后，服务端可能还有些请求还在处理，等请求处理完成之后，再进行后两次挥手。
2. 每次发送，都有发送seq序号，保证请求的有效性。


## <a name="5">TCP与UDP</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
TCP协议（可靠协议）：面向连接，传输可靠以字节流形式顺序传输。位于IP层上方，能够让连接的两端确认请求接收、请求重传丢失或被破坏的包，允许接收端的包以发送时的顺序重新组合在一起。

UDP协议（不可靠协议）：无连接的不可靠传输，以数据报文段形式传输。不保证包会到达目的地，也不保证包会以发送时相同的顺序到达。 
  - 例：视频传输中丢失某一两个数据为不会造成质量太多下降。用TCP要等丢失的数据重传耗费时间。而用UDP效率较高，可以在应用层的UDP数据流中建立纠错码，来解决数据丢失问题。
  
## <a name="6">浏览器访问网站的数据传输过程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
几个过程:

1. DNS解析
2. TCP连接
3. 发送HTTP请求
4. 服务器处理请求并返回HTTP报文
5. 浏览器解析渲染页面
6. 连接结束

## <a name="7">http 长连接与短连接</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- HTTP/1.0中默认使用短连接。
- 从HTTP/1.1起，默认使用长连接，用以保持连接特性。使用长连接的HTTP协议，会在响应头加入这行代码：
  - `Connection:keep-alive`
  - Keep-Alive不会永久保持连接，它有一个保持时间，可以在不同的服务器软件（如Apache）中设定这个时间。
  
长连接可以省去较多的TCP建立和关闭的操作，减少浪费，节约时间。
  - 缺点：Client与server之间的连接如果一直不关闭的话，随着客户端连接越来越多，server早晚会因为长连接消耗完资源。
  - 使用场景：长连接多用于操作频繁，点对点的通讯，而且连接数不能太多情况。例如：数据库的连接用长连接， 如果用短连接频繁的通信会造成socket错误，而且频繁的socket 创建也是对资源的浪费。 

短连接对于服务器来说管理较为简单，存在的连接都是有用的连接，不需要额外的控制手段。但如果客户请求频繁，将在TCP的建立和关闭操作上浪费时间和带宽。
  - 场景：WEB网站的http服务一般都用短链接，因为长连接对于服务端来说会耗费一定的资源。像WEB网站这么频繁的成千上万甚至上亿客户端的连接用短连接会更省一些资源。
  
## <a name="8">http 无状态、无连接</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
**无连接**的含义是限制每次连接只处理一个请求。服务器处理完客户的请求，并收到客户的应答后，即断开连接。采用这种方式可以节省传输时间。
- 服务器需要处理同时面向全世界数十万、上百万客户端的网页访问，两次传送的数据关联性很低。因此 HTTP 的设计者有意利用这种特点将协议设计为请求时建连接、请求完释放连接，以尽快将资源释放出来服务其他客户端。
- Keep-Alive 功能使客户端到服务器端的连接持续有效，当出现对服务器的后继请求时，Keep-Alive 功能避免了建立或者重新建立连接。

**无状态**是指我们给服务器发送 HTTP 请求之后，服务器根据请求，会给我们发送数据过来，但是发送完不会记录任何信息。
- HTTP 是一个无状态协议，这意味着每个请求都是独立的，Keep-Alive 没能改变这个结果。
- 缺点：缺点在于每次请求会传输大量重复的内容信息，缺少状态意味着如果后续处理需要前面的信息，则它必须重传，这样可能导致每次连接传送的数据量增大。
- 优点：在于解放了服务器，每一次请求“点到为止”不会造成不必要连接占用。

**客户端与服务器进行动态交互的 Web 应用程序**出现之后，HTTP 无状态的特性严重阻碍了这些应用程序的实现。解决无状态的两个技术Session、Cookies


## <a name="9">Cookies与 Session （TODO）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="10">Cookies</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Cookies 是**服务器发送到用户浏览器并保存在本地的一小块数据**，它会在浏览器之后向同一服务器再次发起请求时被携带上，用于告知服务端两个请求是否来自同一浏览器。
> HTTP 协议是无状态的，主要是为了让 HTTP 协议尽可能简单，使得它能够处理大量事务。HTTP/1.1 引入 Cookie 来保存状态信息。

Cookies 存储于浏览器中，只能存储 ASCII 码字符串，每次请求都会携带Cookies信息，因此会带来额外的性能开销，适合储存非敏感信息。
- 个性化设置（如用户自定义设置、主题等）
- 浏览器行为跟踪（如跟踪分析用户行为等）   
- 会话状态管理（如用户登录状态、购物车、游戏分数或其它需要记录的信息）

### <a name="11">Session</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：当用户访问服务器否个网页的时候，服务器会在的内存里开辟一块内存，这块内存就叫做session空间。

Session对象存储特定用户会话所需的属性及配置信息。这样，当用户在应用程序的Web页之间跳转时，存储在Session对象中的变量将不会丢失，而是在整个用户会话中一直存在下去。

当用户请求来自应用程序的 Web页时，如果该用户还没有会话，则Web服务器将自动创建一个 Session对象。当会话过期或被放弃后，服务器将终止该会话。Session 对象最常见的一个用法就是存储用户的首选项。

Session通常用于执行以下操作
- 存储需要在整个用户会话过程中保持其状态的信息，例如登录信息或用户浏览Web应用程序时需要的其它信息。
- 存储只需要在页面重新加载过程中或按功能分组的一组页之间保持其状态的对象。
- Session的作用就是它在Web服务器上保持用户的状态信息供在任何时间从任何设备上的页面进行访问。因为浏览器不需要存储任何这种信息，所以可以使用任何浏览器，即使是像Pad或手机这样的浏览器设备。


## <a name="12">https</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

HTTP 有以下安全性问题：

- 使用明文进行通信，内容可能会被窃听；
- 不验证通信方的身份，通信方的身份有可能遭遇伪装；
- 无法证明报文的完整性，报文有可能遭篡改。

HTTPS 并不是新协议，而是让 HTTP 先和 SSL（Secure Sockets Layer）通信，再由 SSL 和 TCP 通信，也就是说 HTTPS 使用了隧道进行通信。

通过使用 SSL，HTTPS 具有了加密（防窃听）、认证（防伪装）和完整性保护（防篡改）。

Https采用混合的加密机制。
1. 第一阶段使用非对称加密方式，传递对称加密所需的secret Key。
2. 第二阶段使用对称加密的方式，进行消息传输

### <a name="13">加密方式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
对称密钥加密：加密和解密使用同一密钥。
- 优点：运算速度快；
- 缺点：无法安全地将密钥传输给通信方。
非对称密钥加密，又称公开密钥加密，加密和解密使用不同的密钥。
  - 接收方发送公开的秘钥，公开的秘钥用于加密。接收到消息之后，接收方使用私有秘钥进行解密。
  - 优点：可以更安全地将公开密钥传输给通信发送方；
  - 缺点：运算速度慢。
  
  
## <a name="14">证书认证</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
数字证书认证机构（CA，Certificate Authority）是客户端与服务器双方都可信赖的第三方机构。

进行 HTTPS 通信时，服务器会把证书发送给客户端。客户端取得其中的公开密钥之后，先使用数字签名进行验证，如果验证通过，就可以开始通信了。

## <a name="15">Http2.0</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 多路复用：允许同时通过单一的 HTTP/2 连接发起多重的请求-响应消息。
  > 在 HTTP/1.1 协议中 「浏览器客户端在同一时间，针对同一域名下的请求有一定数量限制。超过限制数目的请求会被阻塞」。
- 二进制分帧：在 应用层(HTTP/2)和传输层(TCP or UDP)之间增加一个二进制分帧层。HTTP/2 通信都在一个连接上完成，这个连接可以承载任意数量的双向数据流。

总结：
1. 单连接多资源的方式,减少服务端的链接压力,内存占用更少,连接吞吐量更大 
2. 由于 TCP 连接的减少而使网络拥塞状况得以改善,同时慢启动时间的减少,使拥塞和丢包恢复速度更快
