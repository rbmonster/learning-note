package com.four.server;

import org.springframework.transaction.annotation.Transactional;
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
 * @ClassName: SingleController
 * @Author: sanwu
 * @Date: 2020/11/17 15:07
 */
@RestController
@RequestMapping("/home")
public class SingleController {

    @GetMapping(path = "/getCommand3")
    public String getCommand3(@RequestParam("command1") String command1, @RequestParam("command2") String command2) {
        return "command is " +command1 +" " + command2;
    }

    @Transactional
    @GetMapping(path = "/getCommand4")
    public String getCommand4(@RequestParam("command1") String command1, @RequestParam("command2") String command2) {
        return "command is " +command1 +" " + command2;
    }
}
