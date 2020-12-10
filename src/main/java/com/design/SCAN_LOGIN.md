# 扫码登陆

相关文章：
- https://www.cnblogs.com/lidedong/p/9715200.html

主要为三部分的交互网页、app、服务端

## 流程
- 二维码扫码后的解析地址:
``` 
https://login.m.taobao.com/qrcodeCheck.htm?lgToken=2194021d239d0914a1002491f05a41c7&tbScanOpenType=Notification
```
1. 网页： 请求服务端获取二维码图片
2. 服务端： 服务器为这个会话生成一个全局唯一的ID，URL中lgToken就是这个ID。此时和浏览器建立长连接，实时监测二维码状态，定时去请求。
3. app：扫描二维码，app弹出提示框是否确认登陆。点击确认。
4. 服务端：获取到app端用户信息结合二维码的唯一ID，重新生成令牌。返回网页的登陆态。
5. 网页：收到服务端的登陆成功通知，存储对应的token信息。


## 实现细节
### 二维码
1. 二维码生成使用UUID的方案，生成后存储在redis，带上过期时间。
2. 二维码是否生效验证，直接验证redis是否有对应的数据。

- 新建：
```
content: {data: {qrCodeStatus: "NEW", resultCode: 100}, status: 0, success: true}
```

- 扫码成功
```
{"content":{"data":{"qrCodeStatus":"SCANED","resultCode":100},"status":0,"success":true},"hasError":false}
```

- 登陆认证成功使用307跳转
