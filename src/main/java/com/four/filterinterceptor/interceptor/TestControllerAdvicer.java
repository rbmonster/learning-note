package com.four.filterinterceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: TestControllerAdvicer
 * @Author: sanwu
 * @Date: 2021/1/12 14:53
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.four.filterinterceptor.interceptor")
public class TestControllerAdvicer {

    @ExceptionHandler(RuntimeException.class)
    public String handler(RuntimeException e) {
        log.info("this is handler exception process");
        return e.getMessage();
    }
}
