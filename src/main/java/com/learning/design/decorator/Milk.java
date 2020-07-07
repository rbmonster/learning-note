package com.learning.design.decorator;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Milk
 * @Author: sanwu
 * @Date: 2020/7/8 0:31
 */
public class Milk implements Drink {
    @Override
    public float getPrice() {
        return 2f;
    }

    @Override
    public String getDescription() {
        return "this is milk";
    }
}
