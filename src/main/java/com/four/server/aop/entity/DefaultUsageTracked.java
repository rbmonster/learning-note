package com.four.server.aop.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @Description:
 * 接口实现
 * </pre>
 *
 * @version v1.0
 * @ClassName: DefaultUsageTracked
 * @Author: sanwu
 * @Date: 2020/10/22 21:44
 */
@Slf4j
@Component
public class DefaultUsageTracked implements UsageTracked {
    @Override
    public void track(String msg) {
        log.info("usage track send msg: {}" , msg);
    }
}
