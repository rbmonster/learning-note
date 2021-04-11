package com.four.unittest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: UnitController
 * @Author: sanwu
 * @Date: 2021/3/18 0:25
 */
@RestController
@RequestMapping("/unit")
public class UnitController {

    @GetMapping("/one")
    public String fixReturn() {
        return "For one";
    }

    @PostMapping("/upload")
    public List<String> upload(List<String> list) {
        System.out.println(Arrays.toString(list.toArray()));
        return Arrays.asList("success", "fail");
    }
}
