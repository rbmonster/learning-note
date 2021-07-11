package com.four.annotation.aspect;

import com.four.annotation.annotationobj.EnrichUserInfo;
import com.four.server.aop.entity.LogHome;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class EnrichUserInfoAspect {

    @Pointcut(value = "@annotation(enrichUserInfo)")
    public void annotationPointCut(EnrichUserInfo enrichUserInfo) {
        log.info("this is point cut");
    }

    @Before(value = "hasfield(@EnrichUserInfo * *)")
    public void beforeProcess(JoinPoint joinPoint, EnrichUserInfo enrichUserInfo) {
        Object target = joinPoint.getTarget();
        log.info("this is before method, {}", target);
    }
}
