package com.four.filterinterceptor.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * @Description:
 * 注册拦截器
 * </pre>
 *
 * @version v1.0
 * @ClassName: WebAppConfigurer
 * @Author: sanwu
 * @Date: 2020/11/8 18:40
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    /**
     *
     *  注入自定义拦截器
     * @Title: addInterceptors
     * @Description: 先add的拦截器会越靠外，即越靠近浏览器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/**");//拦截所有请求
    }
}
