//package com.four.server.aop;
//
//import org.aspectj.lang.annotation.Aspect;
//
///**
// * <pre>
// * @Description:
// * 假设我们使用的切面类需要具有某种状态，以适用某些特殊情况的使用，比如多线程环境，此时单例的切面类就不符合我们的要求了。
// * perthis表示如果某个类的代理类符合其指定的切面表达式，那么就会为每个符合条件的目标类都声明一个切面实例；
// * pertarget表示如果某个目标类符合其指定的切面表达式，那么就会为每个符合条件的类声明一个切面实例。
// * </pre>
// *
// * @version v1.0
// * @ClassName: PerThisAspect
// * @Author: sanwu
// * @Date: 2020/10/22 23:01
// */
//@Aspect("perthis(com.four.server.controller.DeclareParentController.getUserMsg())")
//public class PerThisAspect {
//}
