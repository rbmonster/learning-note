package com.learning.design.decorator;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Coffee
 * @Author: sanwu
 * @Date: 2020/7/8 0:30
 */
public class Coffee implements Drink {
    @Override
    public float getPrice() {
        return 3f;
    }

    @Override
    public String getDescription() {
        return "coffee";
    }
}
