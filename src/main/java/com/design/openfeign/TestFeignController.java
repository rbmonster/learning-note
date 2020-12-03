package com.design.openfeign;

import com.design.openfeign.client.HystrixFallbackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestFeignController
 * @Author: sanwu
 * @Date: 2020/11/29 17:17
 */
@RestController
@RequestMapping("/feign")
public class TestFeignController {

    @Autowired
    HystrixFallbackClient hystrixFallbackClient;

    @GetMapping("/fallback")
    public String getFallback() {
        String s = hystrixFallbackClient.iFailSometimes();
        return s;
    }
}
