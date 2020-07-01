package com.learning.design.strategy.headFirst;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: FlyWithWings
 * @Author: sanwu
 * @Date: 2020/7/1 10:34
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly with wings!");
    }
}
