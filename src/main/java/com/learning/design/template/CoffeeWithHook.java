package com.learning.design.template;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: CoffeeWithHook
 * @Author: sanwu
 * @Date: 2020/7/9 0:13
 */
public class CoffeeWithHook extends CoffeeBarWithHook {
    @Override
    void cook() {
        System.out.println("cook coffee~~");
    }
}
