package com.learning.design.strategy;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Context
 * @Author: sanwu
 * @Date: 2020/5/10 22:45
 */
public class Context {
    private AbstractStrategy strategy;
    public Context(AbstractStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        strategy.doStrategy();
    }
}
