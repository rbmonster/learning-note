package com.four.beanproxyannotation.conf;

import com.four.beanproxyannotation.annotation.ProxyBean;
import com.four.proxyannotation.annotation.HandlerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class ProxyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ProxyBean proxyBean = bean.getClass().getDeclaredAnnotation(ProxyBean.class);
        if (Objects.nonNull(proxyBean)){
            Object o = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), (proxy, method, args) -> {
                String[] proxyBeanArgs = proxyBean.args();
                log.info("dynamic proxy args: {}", Arrays.toString(proxyBeanArgs));
                return method.invoke(bean, args);
            });
            return o;
        }
        return bean;
    }
}
