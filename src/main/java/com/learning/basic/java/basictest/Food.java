package com.learning.basic.java.basictest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: Food
 * @Author: sanwu
 * @Date: 2021/1/27 10:35
 */
@Slf4j
@Data
public class Food {

    private String name;
    private int id;

    public Food cook() {
        log.info("this is food cook");
        return new Food();
    }
}
