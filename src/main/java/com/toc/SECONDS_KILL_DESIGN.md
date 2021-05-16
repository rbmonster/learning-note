<a name="index">**Index**</a>

<a href="#0">秒杀系统</a>  
&emsp;<a href="#1">1. 前端</a>  
&emsp;&emsp;<a href="#2">1.1. CDN加速、资源静态化</a>  
&emsp;&emsp;<a href="#3">1.2. 秒杀链接加密</a>  
&emsp;&emsp;<a href="#4">1.3. 前端限流</a>  
&emsp;&emsp;<a href="#5">1.4. 扩容限流 —— nginx</a>  
&emsp;&emsp;&emsp;<a href="#6">1.4.1. 扩容</a>  
&emsp;&emsp;&emsp;<a href="#7">1.4.2. 限流</a>  
<a href="#8">          # 一个地址最多可以存在10个链接</a>  
&emsp;<a href="#9">1. 反作弊</a>  
&emsp;&emsp;<a href="#10">1.1. 风控</a>  
&emsp;&emsp;<a href="#11">1.2. 多个账号，一次性发送多个请求</a>  
&emsp;<a href="#12">2. 后端</a>  
&emsp;&emsp;<a href="#13">2.1. 服务单一职责</a>  
&emsp;&emsp;<a href="#14">2.2. 接口设计</a>  
&emsp;&emsp;<a href="#15">2.3. Redis 集群</a>  
&emsp;&emsp;<a href="#16">2.4. 消息队列</a>  
&emsp;&emsp;<a href="#17">2.5. 数据库</a>  
&emsp;<a href="#18">3. 相关资料</a>  
# <a name="0">秒杀系统</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
![avatar](https://github.com/rbmonster/file-storage/blob/main/learning-note/design/secondKill.jpg)

## <a name="1">前端</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
秒杀场景入口商城网页、H5、APP、小程序

### <a name="2">CDN加速、资源静态化</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
CDN(Content Delivery Network)的本质上是将媒体资源，动静态图片（Flash），HTML，CSS，JS等等内容缓存到距离你更近的IDC(互联网数据中心Internet Data Center)，从而让用户进行共享资源，实现缩减站点间的响应时间等等需求，而网游加速器的本质则是通过建立高带宽机房，架设多节点服务器来为用户进行加速。
> CDN是构建在现有网络基础之上的智能虚拟网络，依靠部署在各地的边缘服务器，通过中心平台的负载均衡、内容分发、调度等功能模块，使用户就近获取所需内容，降低网络拥塞，提高用户访问响应速度和命中率。CDN的关键技术主要有**内容存储和分发技术**。

### <a name="3">秒杀链接加密</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. 链接置灰。
  - > 前端配合定时去请求你的后端服务器，获取最新的北京时间，到时间点再给按钮可用状态。
  - > 或者使用前端的代码使用js脚本实现
2. URL动态化，前后端使用一样的加密规则进行不可逆加密。
  - > 通过MD5之类的摘要算法加密随机的字符串去做url，然后通过前端代码获取url后台校验才能通过。


### <a name="4">前端限流</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. IP限流。一般都会采用IP级别的限流，即针对某一个IP，限制单位时间内发起请求数量。


### <a name="5">扩容限流 —— nginx</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

#### <a name="6">扩容</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 添加nginx机器及后端服务器，增加系统的并发能力

#### <a name="7">限流</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
IP 限流

> 实际上真正的企业架构中，在 Nginx 的上面一层还会有一层 lvs(Linux Virtual Server)，简单解释就是能够在网络第 4  层对 Nginx 服务的 ip 进行负载均衡
nginx限制IP的连接和并发分别有两个模块：
- limit_req_zone：用来限制单位时间内的请求数，即速率限制，采用的漏桶算法 "leaky bucket"。
- limit_conn_zone：用来限制同一时间连接数，即并发限制。
- ngx_http_limit_conn_module 参数：这个模块用来限制**单个IP的**请求数。并非所有的连接都被计数。只有在服务器处理了请求并且已经读取了整个请求头时，连接才被计数。

```
  limit_conn_zone $binary_remote_addr zone=addr:10m;
  limit_req_zone $binary_remote_addr zone=one:10m rate=5r/s;
  ...

  server {
    ...

    location / {
      proxy_set_header Host $http_host;
      proxy_set_header X-Real_IP $remote_addr;
      proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
      #limit
      limit_conn addr 3;
      limit_req zone=one burst=5;
    }
```

令牌桶使用：
- http模块下面设置请求的进入速度，下图是每秒5个请求进入（每秒分发5个令牌）
```
limit_req_zone $binary_remote_addr zone=req_one:10m rate=5r/s;

server {
        ...
        location / {
            ... 
            
            # <a name="8">一个地址最多可以存在10个链接</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
            limit_req zone=req_one burst=50 nodelay;  # 每个地址每秒只能请求一次，burst=120 一共有120块令牌，并且每秒钟只新增1块令牌，120块令牌发完后，多出来的请求就会返回503
      
        }
}
```

- 过滤请求直接转发到固定页面
      
相关资料:
- https://www.cnblogs.com/biglittleant/p/8979915.html
- https://blog.csdn.net/u012416045/article/details/104981097

## <a name="9">反作弊</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
### <a name="10">风控</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
针对黄牛养真实用户号进行秒杀的场景，如果使用风管分析出来这个用户是真实用户的概率没有其他用户概率大，那就认为他是机器了，丢弃他的请求。

### <a name="11">多个账号，一次性发送多个请求</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

1.弹出验证码，最核心的追求，就是分辨出真实用户
2.直接禁止IP，实际上是有些粗暴的


## <a name="12">后端</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>

### <a name="13">服务单一职责</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
抽象出秒杀的微服务。保证最坏情况，秒杀服务崩溃也不影响其他服务的使用。
> 同理秒杀专用的数据库


### <a name="14">接口设计</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- 限流：限制接口请求的流量

- 降级：

- 熔断

- 隔离：

### <a name="15">Redis 集群</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
1. Redis集群，主从同步、读写分离，哨兵，并且开启持久化，保证服务高可用。
2. 库存预热：使用lua脚本提前把商品的库存加载到Redis中。

方案一： 对于库存更新使用Redis的乐观锁事务：
```
watch xxx
multi

exec
```

```
Transaction transaction = jedis.multi();
transaction.decr(productCountStr);
transaction.sadd(productUserStr, uid);
List<Object> exec = transaction.exec();

if(exec == null || exec.size() == 0) {
    System.out.println("秒杀失败，稍后重试");
    JedisPollTool.distroy(jedisPool, jedis);
    return false;
}
JedisPollTool.distroy(jedisPool, jedis);
System.out.println(uid + "秒杀成功");
return true;

```
方案二： 使用分布式锁悲观锁。


### <a name="16">消息队列</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
库存扣减成功的请求，削峰填谷使用MQ发送订单请求，更新数据库。

- 未及时支付或取消这笔订单重新更新库存，前端提示用户过阵子再试。


### <a name="17">数据库</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
数据库sql更新使用 stock > 0 的约束条件，防止库存超卖。


## <a name="18">相关资料</a><a style="float:right;text-decoration:none;" href="#index">[Top]</a>
- redis 秒杀：https://blog.csdn.net/cong____cong/article/details/105566983
