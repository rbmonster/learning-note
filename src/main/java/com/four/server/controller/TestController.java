package com.four.server.controller;

import com.four.server.aop.entity.LogHome;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestController
 * @Author: sanwu
 * @Date: 2020/10/17 21:47
 */

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/home")
public class TestController {

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public void forHome(@RequestBody String name, @RequestParam("dsf") String aaa, @PathVariable String a) {
        return;
    }

    @LogHome("logHome for test")
    @GetMapping(path = "/getCommand")
    public String forHome(@RequestParam("command") String command) {
        return "command is " +command;
    }

    @LogHome("logHome for command")
    @GetMapping(path = "/getCommand2")
    @Transactional
    public String forAop(@RequestParam("command1") String command1, @RequestParam("command2") String command2) {
        return "command is " +command1 +" " + command2;
    }

    @GetMapping(path = "/getCommand5")
    public String forAop2(@RequestParam("command") String command) {
        return "command is " +command;
    }

}
