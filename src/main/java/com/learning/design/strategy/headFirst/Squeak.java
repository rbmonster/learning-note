package com.learning.design.strategy.headFirst;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Squeak
 * @Author: sanwu
 * @Date: 2020/7/1 10:38
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack zhi zhi");
    }
}
