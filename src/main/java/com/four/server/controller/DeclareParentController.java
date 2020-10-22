package com.four.server.controller;

import com.four.server.aop.entity.DeclareTest;
import com.four.server.aop.entity.UsageTracked;
import com.four.server.aop.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: DeclareParentController
 * @Author: sanwu
 * @Date: 2020/10/22 21:51
 */
@RestController
@RequestMapping("/parent")
public class DeclareParentController {

    @Autowired
    UserService userService;

    @DeclareTest
    @GetMapping("/getUser")
    public void getUserMsg(String command) {
        ((UsageTracked)userService).track(command);
    }
}
