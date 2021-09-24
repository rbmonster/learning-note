package com.four.beanproxyannotation.testinterface;

import com.four.beanproxyannotation.annotation.ProxyBean;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ProxyBean(args = {"hehe"})
public class TestBeanProxy implements TestBean{

    public void dispatch(String str) {
        log.info("dispatch str: {}", str);
    }
}
