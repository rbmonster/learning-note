package com.design.readwritedb.aop;

import com.design.readwritedb.router.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: DBAopConfiguration
 * @Author: sanwu
 * @Date: 2020/12/17 16:36
 */
@Slf4j
@Component
@Aspect
public class DBAopConfiguration {

    @Pointcut("@annotation(DS)")
    public void dbAspect(){
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~this is logProcess!");
    }

    @Before("dbAspect()")
    public void changeDB(JoinPoint joinPoint){
        log.info("this is before");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DS annotation = AnnotationUtils.findAnnotation(signature.getMethod(), DS.class);
        if (Objects.nonNull(annotation)) {
            DataSourceContextHolder.putDataSource(annotation.name().getName());
        }
    }
}
