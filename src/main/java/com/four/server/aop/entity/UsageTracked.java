package com.four.server.aop.entity;

import org.springframework.stereotype.Component;

/**
 * <pre>
 * @Description:
 * 接口
 * </pre>
 *
 * @version v1.0
 * @ClassName: UsageTracking
 * @Author: sanwu
 * @Date: 2020/10/22 21:42
 */
@Component
public interface UsageTracked {
    default void track(String msg){
        System.out.println("interface tacking " +msg);
    }
}
