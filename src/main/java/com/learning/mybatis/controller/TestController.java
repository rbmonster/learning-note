package com.learning.mybatis.controller;

import com.learning.mybatis.dao.DemoMapper;
import com.learning.mybatis.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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


    @GetMapping("/param")
    public List<Demo> selectWithoutParam() {
        return demoMapper.selectWithoutParam(6562302976415772672L, "李四1");
    }


    @GetMapping("/paramTest")
    public List<Demo> selectWithoutParamByEntity() {
        Demo demo = new Demo();
        demo.setDemoId(6562302976415772672L);
        demo.setDemoName("李四1");
        return demoMapper.selectWithoutParamByEntity(demo);
    }

    @GetMapping("/paramTestTwo")
    public List<Demo> selectWithoutParamByEntityTwo() {
        Demo demo1 = new Demo();
        demo1.setDemoId(6562302976415772672L);
        demo1.setDemoName("李四1");
        Demo demo2 = new Demo();
        demo2.setDemoId(6562302976415772672L);
        demo2.setDemoName("李四1");

        return demoMapper.selectWithoutParamByEntityTwo(demo1, demo2);
    }

}
