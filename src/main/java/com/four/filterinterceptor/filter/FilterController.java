package com.four.filterinterceptor.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * @Description:
 * filter controller
 * http://127.0.0.1:8090/learning/filter
 * </pre>
 *
 * @version v1.0
 * @ClassName: FilterController
 * @Author: sanwu
 * @Date: 2020/10/25 23:40
 */
@RestController
@RequestMapping("/filter")
public class FilterController {

    @GetMapping
    public String doFilter() {
        return "do filter done";
    }
}
