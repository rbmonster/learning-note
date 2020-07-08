package com.learning.design.template;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: MilkWithHook
 * @Author: sanwu
 * @Date: 2020/7/9 0:14
 */
public class MilkWithHook extends CoffeeBarWithHook {
    @Override
    void cook() {
        System.out.println("cook milk~");
    }
}
