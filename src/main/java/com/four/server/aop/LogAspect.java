package com.four.server.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <pre>
 * @Description:
 * LogAspect 切面实例
 * </pre>
 *
 * @version v1.0
 * @ClassName: LogAspect
 * @Author: sanwu
 * @Date: 2020/10/21 23:21
 */
@Slf4j
@Aspect
@Component
public class LogAspect {


    /**
     * 切点便于统一管理切面声明范围
     * 指定切面的范围
     */
    @Pointcut("execution(* com.four.server.controller.TestController.*(..))")
    public void logProcess(){
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~this is logProcess!");
    }

    /**
     *
     * @param joinPoint
     */
    @Before("logProcess()")
    public void logBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();
        log.info("request path: {}" , request.getRequestURL() );
        log.info("request type: {}", request.getMethod());
        log.info("method name: {}", joinPoint.getSignature().getName());
        log.info("class path: {}", joinPoint.getSignature().getDeclaringTypeName());
        log.info("params is : {]", Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 指定多参数，参数值可以通过args获取
     * @param joinPoint
     */
    @Before("logProcess() && args(..)")
    public void logBeforeWithMulParam(JoinPoint joinPoint) {
        log.info("Before with Mul params: {}", joinPoint.getArgs());
        System.out.println();
    }

    /**
     * 执行完成返回
     * @param joinPoint
     * @param obj 名称必须和参数名称匹配
     */
    @AfterReturning(returning = "obj", pointcut = "logProcess()")
    public void logAfterReturning(JoinPoint joinPoint, Object obj) {
        log.info("after returning result is: {}",obj);
    }

    /**
     * 执行抛出异常处理
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(throwing = "e", pointcut = "logProcess()")
    public void throwsExecute(JoinPoint joinPoint, Exception e) {
        log.info("after throw new Exception: {}" ,e );
    }

    @After("logProcess()")
    public void afterInform(JoinPoint joinPoint) {
        log.info("after inform");
    }

    /**
     * 环绕型通知
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("logProcess()")
    public Object aroundProcess(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("begin around");
        Object o = proceedingJoinPoint.proceed();
        log.info("around end");
        return o;
    }


}
