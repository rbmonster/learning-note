package com.four.server.aop;

import com.four.server.aop.entity.DeclareTest;
import com.four.server.aop.entity.LogHome;
import com.four.server.aop.entity.UsageTracked;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @Description:
 * 切面编程
 * </pre>
 *
 * @version v1.0
 * @ClassName: AnnotationAspect
 * @Author: sanwu
 * @Date: 2020/10/22 0:27
 */
@Slf4j
@Component
@Aspect
public class AnnotationAspect {



//    @Pointcut(value = "bean(declareParentController)", argNames = "aa,bb")
//    public void controllerPointCut(String aa, String bb) {
//    }

//    @Pointcut("execution(* com.four.server.controller.TestController.*(..))")



    /**
     * 基于注解的切面声明 可以指定注解与切面切点
     * @param proceedingJoinPoint
     * @param logHome
     * @return
     * @throws Throwable
     */
//    @Around("LogAspect.logProcess()&& @annotation(logHome)")
    @Around("@annotation(logHome)")
    public Object aroundProcessWithAnnotation(ProceedingJoinPoint proceedingJoinPoint, LogHome logHome) throws Throwable {
        log.info("begin Annotation around");
        Object o = proceedingJoinPoint.proceed();
        log.info("Annotation around end");
        return o;
    }

    /**
     * 基于注解的Before切面
     * @param joinPoint
     * @param logHome
     */
    @Before("@annotation(logHome)")
    public void logBeforeWithAnnotation(JoinPoint joinPoint, LogHome logHome) {
        log.info("Before with Annotation {}", logHome.value());
    }

//    /**
//     *按名称匹配参数
//     * argNames 指定参数的名称，
//     * @param command1
//     * @param command2
//     */
//    @Before(value = "controllerPointCut(command1,command2)", argNames = "command1,command2")
//    public void logBeforeWithController(String command1, String command2) {
//        log.info("Before with Annotation :{}   {}", command1, command2);
//    }
}
