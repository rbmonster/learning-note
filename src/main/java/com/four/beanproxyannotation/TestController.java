package com.four.beanproxyannotation;

import com.four.beanproxyannotation.testinterface.TestBean;
import com.four.beanproxyannotation.testinterface.TestBeanProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private TestBean testBean;

    @GetMapping
    public void dispatch(String str) {
        testBean.dispatch(str);
    }
}
