package com.four.filterinterceptor.filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * <pre>
 * @Description:
 * FilterApplication
 * </pre>
 *
 * @version v1.0
 * @ClassName: FilterApplication
 * @Author: sanwu
 * @Date: 2020/10/25 23:41
 */
@SpringBootApplication
@ServletComponentScan
public class FilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilterApplication.class, args);
    }
}
