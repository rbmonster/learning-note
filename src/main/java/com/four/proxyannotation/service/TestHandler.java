package com.four.proxyannotation.service;

import com.four.proxyannotation.annotation.HandlerType;
import com.four.proxyannotation.annotation.IHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@HandlerType("test")
public class TestHandler implements IHandler<String, String> {
    @Override
    public String handle(String r) {
        log.info("handler :{}", r);
        return "handler complete";
    }
}
