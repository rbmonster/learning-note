
# OAUTH2

OAuth 2.0 是一个行业的标准授权协议。OAuth 2.0 专注于简化客户端开发人员，同时为 Web 应用程序，桌面应用程序，手机和客厅设备提供特定的授权流程。

OAuth 2.0 的最终目的是为第三方应用颁发一个有时效性的令牌 token。使得第三方应用能够通过该令牌获取相关的资源。常见的场景就是：第三方登录。当你想要登录某个论坛，但没有账号，而这个论坛接入了如 QQ、Facebook 等登录功能，在你使用 QQ 登录的过程中就使用的 OAuth 2.0 协议。

```
     +--------+                               +---------------+
     |        |--(A)- Authorization Request ->|   Resource    |
     |        |                               |     Owner     |
     |        |<-(B)-- Authorization Grant ---|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(C)-- Authorization Grant -->| Authorization |
     | Client |                               |     Server    |
     |        |<-(D)----- Access Token -------|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(E)----- Access Token ------>|    Resource   |
     |        |                               |     Server    |
     |        |<-(F)--- Protected Resource ---|               |
     +--------+                               +---------------+

                     Figure 1: Abstract Protocol Flow
```
该方式需要资源服务器的参与，应用场景大概是：
1. 资源拥有者（用户）需要登录客户端（APP），他选择了第三方登录。
2. 客户端（APP）重定向到第三方授权服务器。此时客户端携带了客户端标识（client_id），那么第三方就知道这是哪个客户端，资源拥有者确定（拒绝）授权后需要重定向到哪里。
3. 用户确认授权，客户端（APP）被重定向到注册时给定的 URI，并携带了第三方给定的 code。
4. 在重定向的过程中，客户端拿到 code 请求自己的后端服务器，后端服务器用 code 与 client_id、client_secret 去第三方授权服务器请求令牌，如果成功，直接请求资源服务器获取资源，整个过程，用户代理是不会拿到令牌 token 的。
5. 客户端（APP）拿到令牌 token 后就可以向第三方的资源服务器请求资源了


获取AUTHORIZATION_CODE
```
https://b.com/oauth/authorize?
  response_type=code&
  client_id=CLIENT_ID&
  redirect_uri=CALLBACK_URL&
  scope=read
```

获取Access Token
```
https://b.com/oauth/token?
 client_id=CLIENT_ID&
 client_secret=CLIENT_SECRET&
 grant_type=authorization_code&
 code=AUTHORIZATION_CODE&
 redirect_uri=CALLBACK_URL
```
## 授权类型
OAuth 2.0 列举了四种授权类型，分别用于不同的场景：

- Authorization Code（授权码 code）：服务器与客户端配合使用。
- Implicit（隐式 token）：用于移动应用程序或 Web 应用程序（在用户设备上运行的应用程序）。
- Resource Owner Password Credentials（资源所有者密码凭证 password）：资源所有者和客户端之间具有高度信任时（例如，客户端是设备的操作系统的一部分，或者是一个高度特权应用程序），以及当其他授权许可类型（例如授权码）不可用时被使用。
- Client Credentials（客户端证书 client_credentials）：当客户端代表自己表演（客户端也是资源所有者）或者基于与授权服务器事先商定的授权请求对受保护资源的访问权限时，客户端凭据被用作为授权许可。
> 注意重定向一定要用 302。


## 参考资料
- [What is the OAuth 2.0 Authorization Code Grant Type?](https://developer.okta.com/blog/2018/04/10/oauth-authorization-code-grant-type)
- [10 分钟理解什么是 OAuth 2.0 协议](https://deepzz.com/post/what-is-oauth2-protocol.html)
- [OAuth 2.0 的四种方式](http://www.ruanyifeng.com/blog/2019/04/oauth-grant-types.html)
- [GitHub OAuth 第三方登录示例教程](http://www.ruanyifeng.com/blog/2019/04/github-oauth.html)

# CAS 单点登陆
CAS的单点登陆方案