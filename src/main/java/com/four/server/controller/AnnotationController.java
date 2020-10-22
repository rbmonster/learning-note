package com.four.server.controller;

import com.four.server.aop.entity.LogHome;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: AnnotationController
 * @Author: sanwu
 * @Date: 2020/10/22 1:08
 */
@RequestMapping("/annotation")
@RestController
public class AnnotationController {

    @LogHome("log home value")
    @GetMapping("/getCommand")
    public String getCommand(@RequestParam("command")String command) {
        return "annotation get command "+ command;
    }
}
