<a name="index">**Index**</a>

<a href="#0">计算机网络</a>  
&emsp;<a href="#1">1. 网络模型</a>  
&emsp;<a href="#2">2. TCP 三次握手和四次挥手</a>  
&emsp;&emsp;<a href="#3">2.1. 三次握手</a>  
&emsp;&emsp;<a href="#4">2.2. 四次挥手</a>  
&emsp;<a href="#5">3. TCP与UDP</a>  
&emsp;<a href="#6">4. 浏览器访问网站的数据传输过程</a>  
&emsp;<a href="#7">5. http 长连接与短连接</a>  
&emsp;<a href="#8">6. http 无状态、无连接</a>  
&emsp;<a href="#9">7. Cookies与 Session</a>  
&emsp;&emsp;<a href="#10">7.1. Cookies</a>  
&emsp;&emsp;&emsp;<a href="#11">7.1.1. 浏览器同源策略</a>  
&emsp;&emsp;&emsp;<a href="#12">7.1.2. Cookies 作用域</a>  
&emsp;&emsp;<a href="#13">7.2. Session</a>  
&emsp;&emsp;<a href="#14">7.3. 参考资料</a>  
&emsp;<a href="#15">8. https</a>  
&emsp;<a href="#16">9. 证书认证</a>  
&emsp;<a href="#17">10. Http2.0</a>  
&emsp;<a href="#18">11. 前端相关存储空间的使用</a>  
&emsp;&emsp;<a href="#19">11.1. cookie</a>  
&emsp;&emsp;<a href="#20">11.2. localStorage（本地存储）</a>  
&emsp;&emsp;<a href="#21">11.3. sessionStorage</a>  
&emsp;&emsp;<a href="#22">11.4. cookie、localStorage、sessionStorage区别</a>  
&emsp;&emsp;<a href="#23">11.5. 其他</a>  
&emsp;<a href="#24">12. 密码学与网络安全</a>  
&emsp;&emsp;<a href="#25">12.1. 对称加密</a>  
&emsp;&emsp;<a href="#26">12.2. 非对称密钥加密</a>  
&emsp;&emsp;<a href="#27">12.3. 单向散列加密</a>  
&emsp;&emsp;<a href="#28">12.4. 彩虹表</a>  
&emsp;&emsp;<a href="#29">12.5. 加盐</a>  
&emsp;&emsp;&emsp;<a href="#30">12.5.1. 动态盐</a>  
&emsp;&emsp;<a href="#31">12.6. 加签验签</a>  
&emsp;&emsp;<a href="#32">12.7. 相关资料</a>  
&emsp;<a href="#33">13. 网络攻击</a>  
&emsp;&emsp;<a href="#34">13.1. XSS攻击</a>  
&emsp;&emsp;<a href="#35">13.2. CSP内容安全策略</a>  
&emsp;&emsp;<a href="#36">13.3. CSRF 攻击</a>  
&emsp;&emsp;<a href="#37">13.4. 中间人攻击</a>  
&emsp;&emsp;<a href="#38">13.5. 参考资料</a>  
# <a name="0">计算机网络</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
## <a name="1">网络模型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
5层模型应用层，运输层，网络层，数据链路层，物理层组成。
- 网络层最重要的协议是 IP 协议、ping使用ICMP协议。
- 传输层最主要的协议是 TCP 和 UDP 协议。
- 应用层：HTTP、FTP、SMTP、TELNET、POP3、DNS
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/basic/network1.jpg)

7层结构明细
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/basic/network2.png)

一个请求在网络模型中的传输过程

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/networkFloat.jpeg)


## <a name="2">TCP 三次握手和四次挥手</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

顺序号 seq（ 32 位）： 用来**标识从 TCP 源端向 TCP 目的端发送的数据字节流**，它表示在这个
报文段中的第一个数据字节的顺序号。如果将字节流看作在两个应用程序间的单向流动，则
TCP 用顺序号对每个字节进行计数。序号是 32bit 的无符号数， 序号到达 2 的 32 次方 － 1 后
又从 0 开始。 当建立一个新的连接时， SYN 标志变 1 ，顺序号字段包含由这个主机选择的该
连接的初始顺序号 ISN （ Initial Sequence Number ）。

确认号 ack（ 32 位）： 包含**发送确认的一端所期望收到的下一个顺序号**。因此，确认序号应当
是上次已成功收到数据字节顺序号加 1 。 只有 ACK 标志为 1 时确认序号字段才有效。 TCP 为
应用层提供全双工服务，这意味数据能在两个方向上独立地进行传输。因此，连接的每一端必
须保持每个方向上的传输数据顺序号。

### <a name="3">三次握手</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/basic/tcpconnect.png)

第一次握手：主机 A 发送位码为 syn＝ 1,随机产生 seq number=1234567 的数据包到服务器，主机 B
由 SYN=1 知道， A 要求建立联机；
第 二 次 握 手 ： 主 机 B 收 到 请 求 后 要 确 认 联 机 信 息 ， 向 A 发 送 ack number=( 主 机 A 的
seq+1),syn=1,ack=1,随机产生 seq=7654321 的包
第三次握手： 主机 A 收到后检查 ack number 是否正确，即第一次发送的 seq number+1,以及位码
ack 是否为 1，若正确， 主机 A 会再发送 ack number=(主机 B 的 seq+1),ack=1，主机 B 收到后确认seq 值与 ack=1 则连接建立成功。

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
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/basic/tcpdisconnect.jpg)

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

1. DNS解析。如用客户端浏览器请求这个页面： http://localhost.com:8080/index.htm 从中分解出协议名、主机名、端口、对象路径等部分
2. TCP连接。把以上部分结合本机自己的信息，封装成一个 HTTP 请求数据包，进一步封装成TCP包，建立TCP连接。
3. 发送HTTP请求
4. 服务器处理请求并返回HTTP报文
5. 浏览器解析渲染页面
6. 连接结束，服务器关闭 TCP 连接。
> 一般情况下，一旦 Web 服务器向浏览器发送了请求数据，它就要关闭 TCP 连接，然后如果浏览器或者服务器在其头信息加入了这行代码 Connection:keep-alive， TCP 连接在发送后将仍然保持打开状态

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

**客户端与服务器进行动态交互的 Web 应用程序**出现之后，HTTP 无状态的特性严重阻碍了这些应用程序的实现。**解决无状态的两个技术Session、Cookies**


## <a name="9">Cookies与 Session</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="10">Cookies</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Cookies 是**服务器发送到用户浏览器并保存在本地的一小块数据**，它会在浏览器之后向同一服务器再次发起请求时被携带上，用于告知服务端两个请求是否来自同一浏览器。
> HTTP 协议是无状态的，主要是为了让 HTTP 协议尽可能简单，使得它能够处理大量事务。HTTP/1.1 引入 Cookie 来保存状态信息。

Cookies 存储于浏览器中，只能存储 ASCII 码字符串，每次请求都会携带Cookies信息，因此会带来额外的性能开销，适合储存非敏感信息。
- 个性化设置（如用户自定义设置、主题等）
- 浏览器行为跟踪（如跟踪分析用户行为等）   
- 会话状态管理（如用户登录状态、购物车、游戏分数或其它需要记录的信息）

#### <a name="11">浏览器同源策略</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
浏览器同源策略，当以下三个相同才算同源
- 协议相同
- 域名相同
- 端口相同

比如 https://www.baidu.com
- 协议: https
- 域名: www.baidu.com
- 端口: 80
> 采用同源策略的目的：是为了保证用户信息的安全，防止恶意的网站窃取数据。设想这样一种情况：A网站是一家银行，用户登录以后，又去浏览其他网站。 如果其他网站可以读取A网站的 Cookie，会发生什么？如果 Cookie包含隐私（比如存款总额），这些信息就会泄漏。

如果不是同源的缺点：
- Cookie、LocalStorage和IndexDB 无法读取
- DOM无法获得
- AJAX请求不能发送

#### <a name="12">Cookies 作用域</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
Cookie有两个很重要的属性:Domain和Path，用来指示此Cookie的作用域：

Domain告诉浏览器当前要添加的Cookie的域名归属，如果没有明确指明则默认为当前域名。
> 比如通过访问`www.vinceruan.info`添加的Cookie的域名默认就是`www.vinceruan.info`,通过访问`blog.vinceruan.info`所生成的Cookie的域名就是`blog.vinceruan.info`
- 在父域名上设置cookie，其子域名都可以共享获得cookie。比如在`zydya.com`设置cookie值，`blog.zyday.com`子域名也可以获取到。

Path告诉浏览器当前要添加的Cookie的路径归属，如果没有明确指明则默认为当前路径
> 比如通过访问www.vinceruan.info/java/hotspot.html添加的Cookie的默认路径就是/java/,通过blog.vinceruan.info/java/hotspot.html生成的Cookie的路径也是/java/
### <a name="13">Session</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
定义：当用户访问服务器否个网页的时候，服务器会在的内存里开辟一块内存，这块内存就叫做session空间。

Session对象存储特定用户会话所需的属性及配置信息。这样，当用户在应用程序的Web页之间跳转时，存储在Session对象中的变量将不会丢失，而是在整个用户会话中一直存在下去。

当用户请求来自应用程序的 Web页时，如果该用户还没有会话，则Web服务器将自动创建一个 Session对象。当会话过期或被放弃后，服务器将终止该会话。Session 对象最常见的一个用法就是存储用户的首选项。

Session通常用于执行以下操作
- 存储需要在整个用户会话过程中保持其状态的信息，例如登录信息或用户浏览Web应用程序时需要的其它信息。
- 存储只需要在页面重新加载过程中或按功能分组的一组页之间保持其状态的对象。
- Session的作用就是它在Web服务器上保持用户的状态信息供在任何时间从任何设备上的页面进行访问。因为浏览器不需要存储任何这种信息，所以可以使用任何浏览器，即使是像Pad或手机这样的浏览器设备。

### <a name="14">参考资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [Java Guide 授权与认证](https://github.com/Snailclimb/JavaGuide/blob/master/docs/system-design/authority-certification/basis-of-authority-certification.md)
- [浏览器同源策略及Cookie的作用域](https://www.cnblogs.com/liaojie970/p/7606168.html)

## <a name="15">https</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

HTTP 有以下安全性问题：

- 使用明文进行通信，内容可能会被窃听；
- 不验证通信方的身份，通信方的身份有可能遭遇伪装；
- 无法证明报文的完整性，报文有可能遭篡改。

HTTPS 并不是新协议，而是让 HTTP 先和 SSL（Secure Sockets Layer）通信，再由 SSL 和 TCP 通信，也就是说 HTTPS 使用了隧道进行通信。

通过使用 SSL，HTTPS 具有了加密（防窃听）、认证（防伪装）和完整性保护（防篡改）。

Https采用混合的加密机制。
1. 第一阶段使用非对称加密方式，**传递对称加密**所需的客户端及服务端的会话秘钥。
   1. 客户端收到非对称加密公钥，经过CA认证。生成客户端的RSA非对称加密**公私钥**及客户端会话秘钥。
   2. 客户端使用服务端非对称公钥(asymmetric-public-sever)加密: 客户端RSA公钥 + 客户端会话秘钥
   3. 服务器使用私钥解密获取客户端RSA公钥 + 客户端会话秘钥，服务器生成 服务器会话秘钥。
   4. 服务端使用客户端RSA公钥加密，传输：服务端会话秘钥
   5. 客户端使用自己的RSA秘钥解密，获取服务端会话秘钥。
2. 第二阶段使用对称加密的方式，进行消息传输。第一阶段通过非对称加密传输的方式，客户端及服务端都获取了对称加密所需的秘钥。

![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/basic/HttpsFlow.png)


## <a name="16">证书认证</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
数字证书认证机构（CA，Certificate Authority）是客户端与服务器双方都可信赖的第三方机构。

进行 HTTPS 通信时，服务器会把证书发送给客户端。客户端取得其中的公开密钥之后，先使用数字签名进行验证，如果验证通过，就可以开始通信了。

## <a name="17">Http2.0</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 多路复用：允许同时通过单一的 HTTP/2 连接发起多重的请求-响应消息。
  > 在 HTTP/1.1 协议中 「浏览器客户端在同一时间，针对同一域名下的请求有一定数量限制。超过限制数目的请求会被阻塞」。
- 二进制分帧：在 应用层(HTTP/2)和传输层(TCP or UDP)之间增加一个二进制分帧层。HTTP/2 通信都在一个连接上完成，这个连接可以承载任意数量的双向数据流。

总结：
1. 单连接多资源的方式,减少服务端的链接压力,内存占用更少,连接吞吐量更大 
2. 由于 TCP 连接的减少而使网络拥塞状况得以改善,同时慢启动时间的减少,使拥塞和丢包恢复速度更快


## <a name="18">前端相关存储空间的使用</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="19">cookie</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
作用：cookie是纯文本，没有可执行代码。存储数据，当用户访问了某个网站（网页）的时候，我们就可以通过cookie来向访问者电脑上存储数据，或者某些网站为了辨别用户身份、进行session跟踪而储存在用户本地终端上的数据（通常经过加密）

如何工作：当网页要发http请求时，**浏览器会先检查是否有相应的cookie，有则自动添加在request header中的cookie字段中。这些是浏览器自动帮我们做的，而且每一次http请求浏览器都会自动帮我们做。** 这个特点很重要，因为这关系到“什么样的数据适合存储在cookie中”。

### <a name="20">localStorage（本地存储）</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

HTML5新方法，不过IE8及以上浏览器都兼容。
特点：
1. 生命周期：持久化的本地存储，除非主动删除数据，否则数据是永远不会过期的。
2. 存储的信息在同一域中是共享的。
3. 当本页操作（新增、修改、删除）了localStorage的时候，本页面不会触发storage事件,但是别的页面会触发storage事件。
4. 大小：据说是5M（跟浏览器厂商有关系）
5. 在非IE下的浏览中可以本地打开。IE浏览器要在服务器中打开。
6. localStorage本质上是对字符串的读取，如果存储内容多的话会消耗内存空间，会导致页面变卡
7. localStorage受同源策略的限制


### <a name="21">sessionStorage</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
其实跟localStorage差不多，也是本地存储，会话本地存储

特点：
用于本地存储一个会话（session）中的数据，这些数据**只有在同一个会话中的页面才能访问并且当会话结束后数据也随之销毁**。因此sessionStorage不是一种持久化的本地存储，仅仅是会话级别的存储。也就是说只要这个浏览器窗口没有关闭，即使刷新页面或进入同源另一页面，数据仍然存在。关闭窗口后，sessionStorage即被销毁，或者在新窗口打开同源的另一个页面，sessionStorage也是没有的。

### <a name="22">cookie、localStorage、sessionStorage区别</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
localStorage只要在相同的协议、相同的主机名、相同的端口下，就能读取/修改到同一份localStorage数据。

sessionStorage比localStorage更严苛一点，除了协议、主机名、端口外，还要求在同一窗口（也就是浏览器的标签页）下。

localStorage是永久存储，除非手动删除。

sessionStorage当会话结束（当前页面关闭的时候，自动销毁）

cookie的数据会在每一次发送http请求的时候，同时发送给服务器而localStorage、sessionStorage不会。

### <a name="23">其他</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- web SQL database
- indexedDB


## <a name="24">密码学与网络安全</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="25">对称加密</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
对称密钥加密：加密和解密使用同一密钥。

- 优点：加密解密的速度比较快，适合数据比较长时的使用。
- 缺点：密钥传输的过程不安全，且容易被破解，密钥管理也比较麻烦。
  
常用的算法包括：
> - DES（Data Encryption Standard）：数据加密标准，速度较快，适用于加密大量数据的场合。
> - 3DES（Triple DES）：是基于DES，对一块数据用三个不同的密钥进行三次加密，强度更高。
> - AES（Advanced Encryption Standard）：高级加密标准，是下一代的加密算法标准，速度快，安全级别高；

### <a name="26">非对称密钥加密</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

非对称密钥加密，又称公开密钥加密，加密和解密使用不同的密钥。 接收方发送公开的秘钥，公开的秘钥用于加密。接收到消息之后，接收方使用私有秘钥进行解密。
- 优点：可以更安全地将公开数据传输给通信发送方；
- 缺点：
    - 非对称加密算法的运行速度比对称加密算法的速度慢很多
    - 公钥传输的过程不安全，易被窃取和替换

常用的算法包括：
> - RSA：由 RSA 公司发明，是一个支持变长密钥的公共密钥算法，需要加密的文件块的长度也是可变的;
> - DSA（Digital Signature Algorithm）：数字签名算法，是一种标准的 DSS（数字签名标准）;
> - ECC（Elliptic Curves Cryptography）：椭圆曲线密码编码学。

### <a name="27">单向散列加密</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
单向加密：不可逆加密，根据密文无法解析出原文，适用于数据校验的场景，例如登录密码。

常见的算法：
> - MD5（Message Algorithm（消息摘要算法第五版），最常用的单向加密算法，可将原文加密为固定长度的密文，压缩性高，安全系数不高。通常将MD5产生的字节数组在进行一次BASE64编码，以提高算法深度。
> - SHA(Secure Hash Algorithm，安全散列算法），数字签名等密码学应用中重要的工具，被广泛地应用于电子商务等信息安全领域。虽然SHA与MD5通过碰撞法都被破解了，但是SHA仍然是公认的安全加密算法，较之MD5更为安全。常见的算法如SHA256是SHA-2下细分出的一种算法。
> - HMAC(Hash Message Authentication Code，散列消息鉴别码，基于密钥的Hash算法的认证协议。消息鉴别码实现鉴别的原理是，用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。接收方利用与发送方共享的密钥进行鉴别认证等。

### <a name="28">彩虹表</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
彩虹表是一个用于加密散列函数逆运算的预先计算好的表，常用于破解加密过的密码散列。 
> 彩虹表常常用于破解长度固定且包含的字符范围固定的密码（如信用卡、数字等）。这是以空间换时间（英语：space-time tradeoff）的典型实践，比暴力破解（Brute-force attack）使用的时间更少，空间更多；但与储存密码空间中的每一个密码及其对应的哈希值（Hash）实现的查找表相比，其花费的时间更多，空间更少。使用加盐的密钥派生函数可以使这种攻击难以实现。

### <a name="29">加盐</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
盐（Salt），在密码学中，是指在散列之前将散列内容（例如：密码）的任意固定位置插入特定的字符串。这个在散列中加入字符串的方式称为“加盐”。其作用是让加盐后的散列结果和没有加盐的结果不相同，在不同的应用情景中，这个处理可以增加额外的安全性。


实现原理

加盐的实现过程通常是在需要散列的字段的特定位置增加特定的字符，打乱原始的字串，使其生成的散列结果产生变化。

比如，用户使用了一个密码：`x7faqgjw`

经过SHA散列后，可以得出结果：`58ecbf2b3136ceda7fddfd986ba8bd8d59b2d73779691e839f3f176ce2c04b84`

但是由于用户密码位数不足，短密码的散列结果很容易被彩虹表破解，因此，在用户的密码末尾添加特定字串：
x7faqgjw**abcdefghijklmnopqrstuvwxyz**

因此，加盐后的密码位数更长了，散列的结果也发生了变化： `7b5001a5a8bcdcfa1b64d41f6339cfa7a5c0eca04cca6ff6a6c1d6aad17794cc`

以上就是加盐过程的简单描述，在实际使用过程中，还需要通过特定位数插入、倒序或多种方法对原始密码进行固定的加盐处理，使得散列的结果更加不容易被破解或轻易得到原始密码，比如： x7**a**fa**b**qg**c**jw

#### <a name="30">动态盐</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
对于静态的加盐
1. 基于特定的静态盐，可以生成特定的彩虹表.
2. 对静态盐处理过的散列密码，可以按出现频率进行排序，最常出现的散列密码，对应的明文密码必然还是那些111111、123456之类的。

因此，对于重要的账号密码，我们需要采用动态盐来对密码进行混淆。一个常用做法就是把账号名进行MD5之后，作为动态盐。则上述两种攻击将失效。
> 比如，用户名是wsq，对应的md5是4572381974328f9c作为动态盐，密码是123456，加上动态盐之后为4572381974328f9c123456，再MD5是d16e970d6e5b95b9。则最终的计算结果是无法猜测的（unguessable）

对于动态盐，可以使用多个加密算法混合保证密码难以破解。

### <a name="31">加签验签</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
加签验签的操作是为了防止报文传输的过程中报文被篡改。**加签与验签是用来证明身份**

加签：用Hash函数把原始报文生成报文摘要，然后用私钥对这个摘要进行加密，就得到这个报文对应的数字签名。通常来说呢，请求方会把「数字签名和报文原文」一并发送给接收方。
> 原始报文->(hash)报文摘要->(私钥加密)数字签名

验签：接收方拿到原始报文和数字签名后，用「同一个Hash函数」从报文中生成摘要A。另外，用对方提供的公钥对数字签名进行解密，得到摘要B，对比A和B是否相同，就可以得知报文有没有被篡改过。
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/other/Endorsement.png)

**假如整个公私钥传递的过程都被人窃取了，但是窃取人仍然不知道报文的hash算法，伪造不了报文。**
### <a name="32">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [Https/SSL/对称加密/非对称加密](https://blog.csdn.net/user11223344abc/article/details/83658812#1Https_1)
- [程序员必备基础：加签验签](https://cloud.tencent.com/developer/article/1665989)

## <a name="33">网络攻击</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="34">XSS攻击</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
XSS攻击是Web攻击中最常见的攻击方法之一，它是通过对网页注入可执行代码且成功地被浏览器执行，达到攻击的目的，形成了一次有效XSS攻击。
> 一旦攻击成功，它可以获取用户的联系人列表，然后向联系人发送虚假诈骗信息，可以删除用户的日志等等，有时候还和其他攻击方式同时实 施比如SQL注入攻击服务器和数据库、Click劫持、相对链接劫持等实施钓鱼，它带来的危害是巨 大的，是web安全的头号大敌。

举例 我们知道留言板通常的任务就是把用户留言的内容展示出来。正常情况下，用户的留言都是正常的语言文字，留言板显示的内容也就没毛病。然而这个时候如果有人不按套路出牌，在留言内容中丢进去一行：

`<script>alert("hey!you are attacked")</script>`
那么别的用户加载到留言板的时候，就会出现这个弹出框

- 其他可以干的事情
攻击者当然不会仅仅弹出一个alert框，既然可以插入脚本，那么也可以做一些其他的事情，例如：
1. 窃取网页浏览中的cookie值，为接下来的CSRF做准备
2. 劫持流量实现恶意跳转
3. 注入脚本获得其他信息

### <a name="35">CSP内容安全策略</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
内容安全策略 (CSP) 是一个额外的安全层，用于检测并削弱某些特定类型的攻击，包括跨站脚本 (XSS) 和数据注入攻击等。无论是数据盗取、网站内容污染还是散发恶意软件，这些攻击都是主要的手段；

遵循CSP,在网站的http头部定义了 Content-Security-Policy：
```
Response Header :


cache-control: must-revalidate, proxy-revalidate, no-cache, no-store
content-encoding: gzip
content-security-policy: default-src * blob:; img-src * data: blob: resource: t.captcha.qq.com cstaticdun.126.net necaptcha.nosdn.127.net; connect-src * wss: blob: resource:; frame-src 'self' *.zhihu.com mailto: tel: weixin: *.vzuu.com mo.m.taobao.com getpocket.com note.youdao.com safari-extension://com.evernote.safari.clipper-Q79WDW8YH9 zhihujs: captcha.guard.qcloud.com pos.baidu.com dup.baidustatic.com openapi.baidu.com wappass.baidu.com passport.baidu.com *.cme.qcloud.com vs-cdn.tencent-cloud.com t.captcha.qq.com c.dun.163.com; script-src 'self' blob: *.zhihu.com g.alicdn.com qzonestyle.gtimg.cn res.wx.qq.com open.mobile.qq.com 'unsafe-eval' unpkg.zhimg.com unicom.zhimg.com resource: captcha.gtimg.com captcha.guard.qcloud.com pagead2.googlesyndication.com cpro.baidustatic.com pos.baidu.com dup.baidustatic.com i.hao61.net 'nonce-36511b51-79fb-4260-9331-e201ad1f3e69' hm.baidu.com zz.bdstatic.com b.bdstatic.com imgcache.qq.com vs-cdn.tencent-cloud.com ssl.captcha.qq.com t.captcha.qq.com cstaticdun.126.net c.dun.163.com ac.dun.163.com/ acstatic-dun.126.net; style-src 'self' 'unsafe-inline' *.zhihu.com unicom.zhimg.com resource: captcha.gtimg.com ssl.captcha.qq.com t.captcha.qq.com cstaticdun.126.net c.dun.163.com ac.dun.163.com/ acstatic-dun.126.net
content-type: text/html; charset=utf-8
date: Sun, 16 May 2021 09:13:18 GMT
expires: 0
pragma: no-cache
referrer-policy: no-referrer-when-downgrade
server: CLOUD ELB 1.0.0
```
CSP 本质上是建立白名单，规定了浏览器只能够执行特定来源的代码;那么即使发生了xss攻击，也不会加载来源不明的第三方脚本；

### <a name="36">CSRF 攻击</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

跨站请求伪造（英语：Cross-site request forgery），也被称为 one-click attack 或者 session riding，通常缩写为 CSRF 或者 XSRF， 是一种挟制用户在当前已登录的Web应用程序上执行非本意的操作的攻击方法。跟跨网站指令码（XSS）相比，XSS 利用的是用户对指定网站的信任，CSRF 利用的是网站对用户网页浏览器的信任。

同一个浏览器下
假如一家银行用以执行转帐操作的URL地址如下：

`http://www.examplebank.com/withdraw?account=AccoutName&amount=1000&for=PayeeName`
那么，一个恶意攻击者可以在另一个网站上放置如下代码：

`<img src="http://www.examplebank.com/withdraw?account=Alice&amount=1000&for=Badman">`
如果有账户名为Alice的用户访问了恶意站点，而她之前刚访问过银行不久，登录信息尚未过期，那么她就会损失1000资金。



### <a name="37">中间人攻击</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
中间人攻击(Man-in-the-MiddleAttack，简称“MITM攻击”)是指攻击者与通讯的两端分别创建独立的联系，并交换其所收到的数据，使通讯的两端认为他们正在通过一个私密的连接与对方 直接对话，但事实上整个会话都被攻击者完全控制。
> 两端的通讯请求都被窃听，并使用自己的公私钥当成对方的公私钥传递给另一端。


### <a name="38">参考资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
[浅谈网络安全--xss、csrf、csp](https://zhuanlan.zhihu.com/p/47678785)