package com.learning.design.strategy.headFirst;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Quack
 * @Author: sanwu
 * @Date: 2020/7/1 10:37
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("quack gua gua gua");
    }
}
