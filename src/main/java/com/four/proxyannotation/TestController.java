package com.four.proxyannotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private HandlerDispatcher handlerDispatcher;

    @GetMapping
    public List<Object> test() {
        return handlerDispatcher.dispatch("test", "for test");
    }
}
