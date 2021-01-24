package com.learning.mybatis.controller;

import com.learning.mybatis.dao.DemoMapper;
import com.learning.mybatis.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestController
 * @Author: sanwu
 * @Date: 2021/1/23 17:23
 */
@RestController
public class TestController {

    private final DemoMapper demoMapper;

    @Autowired
    public TestController(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }


    @GetMapping("/mybatis")
    public Demo updateTestInterceptor() {
        String id = "6562301868079329280";
        Random random = new Random();
        Demo demo = demoMapper.selectByPrimaryKey(Long.valueOf(id));
        demo.setDemoName("hhe" + random.nextInt(10000));
        demoMapper.updateByPrimaryKeySelective(demo);
        return demo;
    }
}
