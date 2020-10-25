package com.four.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * <pre>
 * @Description:
 * 过滤器
 * </pre>
 *
 * @version v1.0
 * @ClassName: SecondFilter
 * @Author: sanwu
 * @Date: 2020/10/25 23:35
 */
@Slf4j
@Order(2)
@WebFilter(filterName = "SecondFilter",urlPatterns = "/*")
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("this is second filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("second filter 1");
        log.info("before: {} " , servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("after: {}",  servletResponse);
        log.info("second filter 2");
    }

    @Override
    public void destroy() {

    }
}
