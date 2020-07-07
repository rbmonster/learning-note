package com.learning.design.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: UserDAO
 * @Author: sanwu
 * @Date: 2020/7/8 0:02
 */
public class UserDAO implements IUserDAO{
    @Override
    public void save() {
        System.out.println("this is userDAO save~");
    }
}
