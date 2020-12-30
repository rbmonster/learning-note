# 前端相关

## 前端存储空间

### cookie
作用：cookie是纯文本，没有可执行代码。存储数据，当用户访问了某个网站（网页）的时候，我们就可以通过cookie来向访问者电脑上存储数据，或者某些网站为了辨别用户身份、进行session跟踪而储存在用户本地终端上的数据（通常经过加密）

如何工作：当网页要发http请求时，浏览器会先检查是否有相应的cookie，有则自动添加在request header中的cookie字段中。这些是浏览器自动帮我们做的，而且每一次http请求浏览器都会自动帮我们做。这个特点很重要，因为这关系到“什么样的数据适合存储在cookie中”。

### localStorage（本地存储）

HTML5新方法，不过IE8及以上浏览器都兼容。
特点：
1. 生命周期：持久化的本地存储，除非主动删除数据，否则数据是永远不会过期的。
2. 存储的信息在同一域中是共享的。
3. 当本页操作（新增、修改、删除）了localStorage的时候，本页面不会触发storage事件,但是别的页面会触发storage事件。
4. 大小：据说是5M（跟浏览器厂商有关系）
5. 在非IE下的浏览中可以本地打开。IE浏览器要在服务器中打开。
6. localStorage本质上是对字符串的读取，如果存储内容多的话会消耗内存空间，会导致页面变卡
7. localStorage受同源策略的限制



### sessionStorage 
其实跟localStorage差不多，也是本地存储，会话本地存储

特点：
用于本地存储一个会话（session）中的数据，这些数据**只有在同一个会话中的页面才能访问并且当会话结束后数据也随之销毁**。因此sessionStorage不是一种持久化的本地存储，仅仅是会话级别的存储。也就是说只要这个浏览器窗口没有关闭，即使刷新页面或进入同源另一页面，数据仍然存在。关闭窗口后，sessionStorage即被销毁，或者在新窗口打开同源的另一个页面，sessionStorage也是没有的。

### cookie、localStorage、sessionStorage区别
localStorage只要在相同的协议、相同的主机名、相同的端口下，就能读取/修改到同一份localStorage数据。

sessionStorage比localStorage更严苛一点，除了协议、主机名、端口外，还要求在同一窗口（也就是浏览器的标签页）下。

localStorage是永久存储，除非手动删除。

sessionStorage当会话结束（当前页面关闭的时候，自动销毁）

cookie的数据会在每一次发送http请求的时候，同时发送给服务器而localStorage、sessionStorage不会。

### 其他
- web SQL database
- indexedDB

### 相关文章
- https://www.cnblogs.com/younggao/p/8127321.html