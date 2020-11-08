package com.four.filterinterceptor.interceptor.method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: InterceptorConfiguration
 * @Author: sanwu
 * @Date: 2020/11/8 19:38
 */
@Configuration
public class InterceptorConfiguration {
    //注意该地址为项目具体包地址
    public static final String traceExecution = "execution(* com.four.filterinterceptor.interceptor..*.*(..))";

    /**
     * 使用 AspectJExpressionPointcut定义切点并注册
     * @return
     */
//    @Bean
//    public DefaultPointcutAdvisor defaultPointcutAdvisor2() {
//        CustomMethodInterceptor interceptor = new CustomMethodInterceptor();
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression(traceExecution);
//
//        // 配置增强类advisor
//        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
//        advisor.setPointcut(pointcut);
//        advisor.setAdvice((Advice) interceptor);
//        return advisor;
//    }
//
//    /**
//     * 使用 JdkRegexpMethodPointcut定义切点
//     * @return
//     */
//    @Bean
//    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
//        CustomMethodInterceptor interceptor = new CustomMethodInterceptor();
//        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
//        pointcut.setPattern(traceExecution);
//        // 配置增强类advisor
//        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
//        advisor.setPointcut(pointcut);
//        advisor.setAdvice((Advice) interceptor);
//        return advisor;
//    }

    /**
     * 手工新建切面切点，实质与AOP定义切点执行是相同的
     * @return
     */
    @Bean
    public Advisor pointcutAdvisor() {
        CustomMethodInterceptor interceptor = new CustomMethodInterceptor();

        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(null,CustomInterceptor.class);
        // 配置增强类advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice((Advice) interceptor);
        System.out.println(advisor.toString());
        return advisor;
    }


}
