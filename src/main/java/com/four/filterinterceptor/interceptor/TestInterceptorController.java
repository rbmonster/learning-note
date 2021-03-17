package com.four.filterinterceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * @Description:
 * TestInterceptorController
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestInterceptorController
 * @Author: sanwu
 * @Date: 2020/11/8 19:49
 */
@Slf4j
@RestController
@RequestMapping("/interceptor")
public class TestInterceptorController {

    @GetMapping
    public void test() {
        log.info("this is interceptor");
    }

    @GetMapping("/one")
    public String fixReturn() {
        return "123";
    }
}
