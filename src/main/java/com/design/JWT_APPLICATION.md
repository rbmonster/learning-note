# jwt 应用

## 验证码登陆
问题：http是无状态的，使用jwt认证，那么如何设计登陆，使得用户输入正确的验证码才能登陆成功

## 递进式提交问题
问题：在日常的系统的使用中，经常会遇到填写完一个页面，点击下一页再填写另一页信息的情况。比如第一页填写用户身份证相关信息，该信息再点击写一页的时候会先进行验证，再第二页填写完毕的时候需要把第一页的信息组合起来一起提交。