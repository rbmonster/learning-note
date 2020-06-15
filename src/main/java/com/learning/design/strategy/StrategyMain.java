package com.learning.design.strategy;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: StrategyMain
 * @Author: sanwu
 * @Date: 2020/5/10 22:49
 */
public class StrategyMain {
    public static void main(String[] args) {
        Context carContext = new Context(new CarStrategy());
        carContext.execute();
        Context trainContext = new Context(new TrainStrategy());
        trainContext.execute();
    }
}
