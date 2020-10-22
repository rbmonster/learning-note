package com.four.server.aop.entity;

import com.four.server.aop.entity.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: DefaultUserService
 * @Author: sanwu
 * @Date: 2020/10/22 21:53
 */
@Slf4j
@Component
public class DefaultUserService implements UserService {
    @Override
    public void getMsg(String msg) {
        log.info("user service use default msg: {}", msg);
    }
}
