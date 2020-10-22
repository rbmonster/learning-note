package com.four.server.aop;

import com.four.server.aop.entity.DeclareTest;
import com.four.server.aop.entity.DefaultUsageTracked;
import com.four.server.aop.entity.UsageTracked;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @Description:
 * http://127.0.0.1:8090/learning/parent/getUser?command=123
 * </pre>
 *
 * @version v1.0
 * @ClassName: DeclareParentsAspect
 * @Author: sanwu
 * @Date: 2020/10/22 21:42
 */
@Aspect
@Component
@Slf4j
public class DeclareParentsAspect {

    /**
     * 相当于对value中的所有类，声明一个父类
     * value中的类都可以强转成usageTrack的，进行默认的调用实现
     */
    @DeclareParents(value = "com.four.server.aop.entity.UserService+", defaultImpl = DefaultUsageTracked.class)
    public UsageTracked mixin;

    @Pointcut(value = "@annotation(declareTest)",argNames = "declareTest")
    public void declarePointCut(DeclareTest declareTest){
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~this is DeclareTest!");
    }

    @Before("declarePointCut(declareTest)")
    public void declarePointCut(JoinPoint joinPoint, DeclareTest declareTest) {
        log.info("this is declarePointCut before method");
    }
}
