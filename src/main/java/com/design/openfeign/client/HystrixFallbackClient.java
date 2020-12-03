package com.design.openfeign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: HystrixFallbackClient
 * @Author: sanwu
 * @Date: 2020/11/29 16:37
 */
@FeignClient(name = "hello", fallback = HystrixClientFallback.class)
public interface HystrixFallbackClient {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    String iFailSometimes();

}

@Component
class HystrixClientFallback implements HystrixFallbackClient {
    @Override
    public String iFailSometimes() {
        return "fallback";
    }
}