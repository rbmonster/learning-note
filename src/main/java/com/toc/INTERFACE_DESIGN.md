<a name="index">**Index**</a>

<a href="#0">接口设计</a>  
&emsp;<a href="#1">1. 理论概念</a>  
&emsp;<a href="#2">2. REST系统六大设计原则</a>  
&emsp;<a href="#3">3. 衡量是否为REST</a>  
&emsp;&emsp;<a href="#4">3.1. 第0级面向过程编程</a>  
&emsp;&emsp;<a href="#5">3.2. 第1级引入资源的概念</a>  
&emsp;&emsp;<a href="#6">3.3. 第2级引入统一接口及标准动作</a>  
&emsp;&emsp;<a href="#7">3.4. 第3级 HATEOAS </a>  
&emsp;<a href="#8">4. URL 设计</a>  
&emsp;&emsp;<a href="#9">4.1. 错误的设计</a>  
&emsp;&emsp;<a href="#10">4.2. HTTP 方法，对应我们常见的 CRUD 操作：</a>  
&emsp;&emsp;<a href="#11">4.3. URL 层级</a>  
&emsp;&emsp;<a href="#12">4.4. URL 版本号</a>  
&emsp;&emsp;<a href="#13">4.5. 响应数据形式</a>  
&emsp;<a href="#14">5. 正确使用响应状态码</a>  
&emsp;<a href="#15">6. API接口设计 三板斧</a>  
&emsp;&emsp;<a href="#16">6.1. 1. entity对象使用@Valid 简化参数判断</a>  
&emsp;&emsp;<a href="#17">6.2. 2. @RestControllerAdvice +   @ExceptionHandler(MethodArgumentNotValidException.class)</a>  
&emsp;&emsp;<a href="#18">6.3. 3. 使用统一的数据返回结构 JsonResponse 包括异常类型</a>  
&emsp;&emsp;<a href="#19">6.4. 使用swagger暴露参数</a>  
&emsp;<a href="#20">7. 接口安全相关</a>  
&emsp;&emsp;<a href="#21">7.1. API Token(接口令牌)</a>  
&emsp;&emsp;<a href="#22">7.2. 攻击行为防御</a>  
&emsp;&emsp;<a href="#23">7.3. 相关资料</a>  
# <a name="0">接口设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
REST / Representational State Transfer/ 表征 状态 转移 

## <a name="1">理论概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 资源（Resource）：譬如你现在正在阅读一篇名为《REST 设计风格》的文章，这篇文章的内容本身（你可以将其理解为其蕴含的信息、数据）我们称之为“资源”。
- 表征（Representation）：指信息与用户交互时的表示形式，如服务端向浏览器返回的页面 HTML 就被称之为“表征”，也可能是PDF、Markdown、RSS 等。
- 状态（State）：客户端记住当前页面的状态，请求服务端下一个动作。服务端无需记住状态（无状态）。
- 转移（Transfer）：只有服务端拥有资源及其表征形式，服务器通过某种方式，接受当前表征的状态返回对应下个资源的表征，这就被称为“表征状态转移”。

## <a name="2">REST系统六大设计原则</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
服务端与客户端分离为前提，无状态、统一接口为核心
- **无状态（Stateless）**：REST 希望服务器不要去负责维护状态，每一次从客户端发送的请求中，应包括所有的必要的上下文信息，会话信息也由客户端负责保存维护，服务端依据客户端传递的状态来执行业务处理逻辑，驱动整个应用的状态变迁。
- **统一接口（Uniform Interface）**：REST 希望开发者**面向资源编程**，希望软件系统设计的重点放在抽象系统该有哪些资源上，而不是抽象系统该有哪些行为（服务）上。
- **服务端与客户端分离（Client-Server）**
- **可缓存（Cacheability）**：REST 希望软件系统能够如同万维网一样，允许客户端和中间的通讯传递者（譬如代理）将部分服务端的应答缓存起来。解决无状态服务对比有状态的设计，需要多次请求造成的冗余。
- 分层系统（Layered System）：客户端一般不需要知道是否直接连接到了最终的服务器，抑或连接到路径上的中间服务器。该原则的典型的应用是内容分发网络CDN
- 按需代码（Code-On-Demand）：按需代码被 Fielding 列为一条可选原则。它是指任何按照客户端（譬如浏览器）的请求，将可执行的软件程序从服务器发送到客户端的技术，按需代码赋予了客户端无需事先知道所有来自服务端的信息应该如何处理、如何运行的宽容度。

## <a name="3">衡量是否为REST</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="4">第0级面向过程编程</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
```
// 查询时间段医生的空闲时间
POST /appointmentService?action=query HTTP/1.1
{date: "2020-03-04", doctor: "mjones"}

// 进行预约确认
POST /appointmentService?action=comfirm HTTP/1.1
{
	appointment: {date: "2020-03-04", start:"14:00", doctor: "mjones"},
	patient: {name: icyfenix, age: 30, ……}
}
```

### <a name="5">第1级引入资源的概念</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
服务的 Endpoint 应该是一个名词而不是动词。每次请求中都应包含资源的 ID，所有操作均通过资源 ID 来进行
```
// 查询
POST /doctors/mjones HTTP/1.1
{date: "2020-03-04"}

HTTP/1.1 200 OK

[
	{id: 1234, start:"14:00", end: "14:50", doctor: "mjones"},
	{id: 5678, start:"16:00", end: "16:50", doctor: "mjones"}
]

// 预约
POST /schedules/1234 HTTP/1.1
{name: icyfenix, age: 30, ……}
```
存在问题：
1. 一是只处理了查询和预约，如想删除预约，这都需要提供新的服务接口。
2. 二是处理结果响应时，只能靠着结果中的code、message这些字段做分支判断，每一套服务都要设计可能发生错误的 code
3. 三是并没有考虑认证授权等安全方面的内容
### <a name="6">第2级引入统一接口及标准动作</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
三个问题的解决方案：
1. REST 的做法是把不同业务需求抽象为对资源的增加、修改、删除等操作
2. 使用 HTTP 协议的 Status Code，可以涵盖大多数资源操作可能出现的异常，而且 Status Code 也是可以自定义扩展
3. HTTP Header 中携带的额外认证、授权信息解决认证问题

```
// 查询
GET /doctors/mjones/schedule?date=2020-03-04&status=open HTTP/1.1

HTTP/1.1 200 OK
[
	{id: 1234, start:"14:00", end: "14:50", doctor: "mjones"},
	{id: 5678, start:"16:00", end: "16:50", doctor: "mjones"}
]

// 预约
POST /schedules/1234 HTTP/1.1
{name: icyfenix, age: 30, ……}

// 预约成功
HTTP/1.1 201 Created
Successful confirmation of appointment

// 预约失败
HTTP/1.1 409 Conflict
doctor not available
```

### <a name="7">第3级 HATEOAS </a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
问题： 如何知道一个请求之后，如何获取向下一个请求的地址？\
解决方案：除了第一个请求是有你在浏览器地址栏输入所驱动之外，其他的请求都应该能够自己描述清楚后续可能发生的状态转移，由超文本自身来驱动。
```
GET /doctors/mjones/schedule?date=2020-03-04&status=open HTTP/1.1

HTTP/1.1 200 OK

{
	schedules：[
		{
			id: 1234, start:"14:00", end: "14:50", doctor: "mjones",
			links: [
				{rel: "comfirm schedule", href: "/schedules/1234"}
			]
		},
		{
			id: 5678, start:"16:00", end: "16:50", doctor: "mjones",
			links: [
				{rel: "comfirm schedule", href: "/schedules/5678"}
			]
		}
	],
	links: [
		{rel: "doctor info", href: "/doctors/mjones/info"}
	]
```

## <a name="8">URL 设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="9">错误的设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
>  /getAllUsers
   /createNewCompany
   /updateUserInfo
   /deleteUser?name=zhangsan

> 导致问题：
> 1. 前端人员不能快速理解接口字段含义及接口字段变化
> 2. 后端人员想复用某些接口，但是不能快速从接口 URL 的定义中明确该接口的含义，需要进一步读代码确认
> 3. URL中的英文单词使用五花八门，搜索某个接口不知道具体的关键字
> 4. 请求方法动词如 POST GET 随意使用
> 5. 完成当前业务接口对接，前端人员经常会询问下一步业务流程的接口定义在哪里，对接形式是什么样的

### <a name="10">HTTP 方法，对应我们常见的 CRUD 操作：</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  1. POST：新建（Create）
  2. GET：读取（Read）
  3. PUT：更新（Update）
  4. PATCH：更新（Update），通常部分更新，也很少用到。对资源进行部分修改，PUT 也可以用于修改资源，但是只能完全替代原始资源，PATCH 允许部分修改。
  5. DELETE：删除（Delete）
  6. HEAD：获取报文首部和 GET 方法类似，但是不返回报文实体主体部分。主要用于确认 URL 的有效性以及资源更新的日期时间等。
  7. OPTIONS：查询支持的方法，查询指定的 URL 能够支持的方法。会返回 Allow: GET, POST, HEAD, OPTIONS 这样的内容。
  8. TRACE：追踪路径。服务器会将通信路径返回给客户端。发送请求时，在 Max-Forwards 首部字段中填入数值，每经过一个服务器就会减 1，当数值为 0 时就停止传输。
  9. CONNECT：要求在与代理服务器通信时建立隧道，使用 SSL（Secure Sockets Layer，安全套接层）和 TLS（Transport Layer Security，传输层安全）协议把通信内容加密后经网络隧道传输。
     
- 有些客户端只能使用GET和POST这两种方法。服务器必须接受POST模拟其他三个方法（PUT、PATCH、DELETE）。这时，客户端发出的 HTTP 请求，要加上X-HTTP-Method-Override属性，告诉服务器应该使用哪一个动词，覆盖POST方法。
```
POST /users/12 HTTP/1.1
X-HTTP-Method-Override: PUT
```

- 正确设计：动词 + 名词

|资源|POST（Create）	|GET（Read）|PUT（Update）|	DELETE （Delete）|
|----|----|----|----|----|
|/users	|创建新用户|	查询所有用户	|批量更新用户|	删除所有用户|
|/users/12	|方法不被允许（405）|	查询指定用户|	更新指定用户	|删除指定用户|
```
POST /users
GET /users
PUT /users
DELETE /users
GET /users/12
PUT /users/12
DELETE /users/12
```

### <a name="11">URL 层级</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 查找 id 是 12 的用户的所有帖子， 如何设计这个 URL?
```
GET /users/12/posts
GET /posts?userId=12
```
- 推荐第二种方式，主体名词 posts 资源明显，其他过滤条件也更容易扩展，比如 /posts?userName=zhangsan，我们可以复用同样的接口

### <a name="12">URL 版本号</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

- 例如下面两个版本地址：
> http://api.yourservice.com/v1/schools/清华
 http://api.yourservice.com/v2/schools/清华

在API上加入版本信息可以有效的使用户访问正确的API，v2是新开发功能，开发阶段让所有用户访问v1，等开发完成统一切到v2。这样可以有效地跨版本访问，例如在v2版本，还需要访问v1版本的一些接口。

### <a name="13">响应数据形式</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
HTTP 头的ACCEPT属性也要设成application/json- API 返回的数据格式，不应该是纯文本，而应该是一个 JSON 对象，因为这样才能返回标准的结构化数据。所以，服务器回应的 HTTP 头的Content-Type属性要设为application/json。同时客户端也应作出相应的配合，客户端请求时，也要明确告诉服务器，可以接受 JSON 格式，即请求的 

## <a name="14">正确使用响应状态码</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 错误示范， 返回200 实际状态表示出差
```
HTTP/1.1 200 OK
Content-Type: application/json

{
    "status": "-1",
    "result": {
        "error": "分数应小于150"
    }
}
```
- 2xx(成功类别)
> 200 Ok表示GET，PUT或POST成功的标准HTTP响应。

> 201 Created每当创建新实例时，都应返回此状态代码。例如，使用POST方法创建新实例时，应始返回201状态代码。

> 204 No Content表示请求已成功处理，但未返回任何内容。

> 206 Partial Content ：表示客户端进行了范围请求，响应报文包含由 Content-Range 指定范围的实体内容。
- 3xx（重定向类别）
> 301 永久重定向 :浏览器会存储重定向地址信息

> 302 临时重定向，HTTP1.0的状态码，HTTP1.1也有保留。 

> 303 临时重定向，HTTP1.1的状态码// 

> 304 Not Modified表示客户端已在其缓存中有响应，因此无需再次传输相同的数据。
  
> 307临时重定向，HTTP1.1的状态码 


- 4xx（客户端错误类别）
  
  这些状态代码表示客户端已提出错误请求。
> 400 Bad Request表示未处理客户端的请求，因为服务器无法理解客户端要求的内容。
  
> 401 Unauthorized表示不允许客户端访问资源，并应使用所需凭据重新请求。
  
> 403 Forbidden表示请求有效且客户端已通过身份验证，但不允许客户端出于任何原因访问该页面或资源。例如，有时不允许授权客户端访问服务器上的目录。
    
> 404 Not Found表示请求的资源现在不可用。
  
> 410 Gone表示已移动的请求资源不再可用。
  
- 5xx（服务器错误类别）
  
> 500内部服务器错误表示请求有效，但服务器完全混淆，并要求服务器提供某些意外情况。
  
> 503 Service Unavailable大多数情况下表示服务器已关闭或无法接收和处理请求，例如服务器正在进行维护。


**状态码范围：**

| 2xx：成功 | 3xx：重定向    | 4xx：客户端错误  | 5xx：服务器错误 |
| --------- | -------------- | ---------------- | --------------- |
| 200 成功  | 301 永久重定向 | 400 错误请求     | 500 服务器错误  |
| 201 创建  | 304 资源未修改 | 401 未授权       | 502 网关错误    |
| 204 No Content表示请求已成功处理，但未返回任何内容    |     | 403 禁止访问     | 504 网关超时    |
|           |                | 404 未找到       |                 |
|           |                | 405 请求方法不对 |                 |
## <a name="15">API接口设计 三板斧</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="16">1. entity对象使用@Valid 简化参数判断</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - BindingResult 获取参数的错误类型
  
### <a name="17">2. @RestControllerAdvice +   @ExceptionHandler(MethodArgumentNotValidException.class)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
  - 封装异常的返回类型
  
### <a name="18">3. 使用统一的数据返回结构 JsonResponse 包括异常类型</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
 - 继承接口ResponseBodyAdvice+ @RestControllerAdvice 指定包范围
 - 根据HttpMethodType 和 统一返回对象的code 生成对应的HttpStatus
 - RestControllerAdvice顾名思义，就是声明了范围内的RestController的建议处理控制
 
### <a name="19">使用swagger暴露参数</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
 - Swagger是一种广泛使用的工具来用来记录与呈现 REST API，它提供了一种探索特定 API 使用的方法，因此允许开发人员理解底层的语义行为。
 
## <a name="20">接口安全相关</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="21">API Token(接口令牌)</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

1. 接口调用方(客户端)向接口提供方(服务器)申请接口调用账号，申请成功后，接口提供方会给接口调用方一个appId和一个key参数
2. 客户端携带参数appId、timestamp、sign去调用服务器端的API token，其中sign=加密(appId + timestamp + key)
3. 客户端拿着api_token 去访问不需要登录就能访问的接口
4. 当访问用户需要登录的接口时，客户端跳转到登录页面，通过用户名和密码调用登录接口，登录接口会返回一个usertoken, 客户端拿着usertoken 去访问需要登录才能访问的接口
> sign的作用是防止参数被篡改，客户端调用服务端时需要传递sign参数，服务器响应客户端时也可以返回一个sign用于客户度校验返回的值是否被非法篡改了。客户端传的sign和服务器端响应的sign算法可能会不同。

验证参数放置在请求头
```
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String timestamp = request.getHeader("timestamp");
        // 随机字符串
        String nonce = request.getHeader("nonce");
        String sign = request.getHeader("sign");
    }
```


### <a name="22">攻击行为防御</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 错误达到3次，验证码或者滑块验证
2. 错误达到10次，手机验证码验证登陆。
3. IP限制
> 不能直接根据错误次数封死账号登陆，可能会导致整个系统的账户都被黑客暴力破解至无法登陆。



### <a name="23">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- [接口安全设计](https://mp.weixin.qq.com/s/Az17l4SJXvcbXNu4A1j1Xg)
- [接口可能遇到的攻击](https://mp.weixin.qq.com/s/j0wjQLwkcXnRx7YTU6Lssg)
- [凤凰架构-REST 设计风格](https://icyfenix.cn/architect-perspective/general-architecture/api-style/rest.html)