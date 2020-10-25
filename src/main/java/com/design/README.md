### 接口设计
- Representational State Transfer（REST）。REST的概念是将API结构分离为操作和资源，使用HTTP方法GET、DELETE、POST和PUT操作资源。


#### 一些概念
- 资源：资源是某种东西的对象或表示，它具有一些与之相关的数据，并且可以有一组方法对其进行操作。 例如, 动物，学校和员工是资源; 删除，添加，更新是对这些资源执行的相关操作
- 集合：集合是资源集合，例如，公司是公司资源的集合
- URL：URL（统一资源定位符）是可以通过其定位资源的路径，并且可以对其执行某些操作
 
### URL 设计
#### 错误的设计
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

#### 5 种 HTTP 方法，对应我们常见的 CRUD 操作：
  1. POST：新建（Create）
  2. GET：读取（Read）
  3. PUT：更新（Update）
  4. PATCH：更新（Update），通常不分更新，也很少用到
  5. DELETE：删除（Delete）

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

#### URL 层级
- 查找 id 是 12 的用户的所有帖子， 如何设计这个 URL?
```
GET /users/12/posts
GET /posts?userId=12
```
- 推荐第二种方式，主体名词 posts 资源明显，其他过滤条件也更容易扩展，比如 /posts?userName=zhangsan，我们可以复用同样的接口

#### URL 版本号

- 例如下面两个版本地址：
> http://api.yourservice.com/v1/schools/清华
 http://api.yourservice.com/v2/schools/清华

在API上加入版本信息可以有效的使用户访问正确的API，v2是新开发功能，开发阶段让所有用户访问v1，等开发完成统一切到v2。这样可以有效地跨版本访问，例如在v2版本，还需要访问v1版本的一些接口。

#### 响应数据形式
HTTP 头的ACCEPT属性也要设成application/json- API 返回的数据格式，不应该是纯文本，而应该是一个 JSON 对象，因为这样才能返回标准的结构化数据。所以，服务器回应的 HTTP 头的Content-Type属性要设为application/json。同时客户端也应作出相应的配合，客户端请求时，也要明确告诉服务器，可以接受 JSON 格式，即请求的 

#### 正确使用响应状态码
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
  
- 3xx（重定向类别）
> 304 Not Modified表示客户端已在其缓存中有响应，因此无需再次传输相同的数据。
  
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


### API接口设计 三板斧

##### 1. entity对象使用@Valid 简化参数判断
  - BindingResult 获取参数的错误类型
  
##### 2. @RestControllerAdvice +   @ExceptionHandler(MethodArgumentNotValidException.class)
  - 封装异常的返回类型
  
##### 3. 使用统一的数据返回结构JsonResponse 包括异常类型
 - 继承接口ResponseBodyAdvice+ @RestControllerAdvice 指定包范围
 - 根据HttpMethodType 和 统一返回对象的code 生成对应的HttpStatus
 
##### 使用swagger八路参数
 - Swagger是一种广泛使用的工具来用来记录与呈现 REST API，它提供了一种探索特定 API 使用的方法，因此允许开发人员理解底层的语义行为。