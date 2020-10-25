package com.four.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * <pre>
 * @Description:
 * FirstFilter
 * </pre>
 *
 * @version v1.0
 * @ClassName: FirstFilter
 * @Author: sanwu
 * @Date: 2020/10/25 23:38
 */
@Slf4j
@Order(2)
@WebFilter(filterName = "FirstFilter",urlPatterns = "/*")
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("this is first filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("FirstFilter  1");
        log.info("before: {} " , servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("after: {}",  servletResponse);
        log.info("First filter 2");
    }

    @Override
    public void destroy() {

    }
}
