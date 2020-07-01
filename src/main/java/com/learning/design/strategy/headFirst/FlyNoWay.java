package com.learning.design.strategy.headFirst;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: FlyNoWay
 * @Author: sanwu
 * @Date: 2020/7/1 10:35
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly no way");
    }
}
