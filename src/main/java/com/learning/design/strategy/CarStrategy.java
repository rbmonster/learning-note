package com.learning.design.strategy;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: CarStrategy
 * @Author: sanwu
 * @Date: 2020/5/10 22:47
 */
public class CarStrategy implements AbstractStrategy {
    @Override
    public void doStrategy() {
        System.out.println("go out by cat!");
    }
}
