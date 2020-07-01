package com.learning.design.strategy.headFirst;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: MallardDuck
 * @Author: sanwu
 * @Date: 2020/7/1 10:43
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("green duck");
    }


}
