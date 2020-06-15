package com.learning.design.strategy;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: TranStrategy
 * @Author: sanwu
 * @Date: 2020/5/10 22:46
 */
public class TrainStrategy implements AbstractStrategy {
    @Override
    public void doStrategy() {
        System.out.println("go out by train!");
    }
}
