package com.learning.design.factory;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: MiFactory
 * @Author: sanwu
 * @Date: 2020/5/10 21:51
 */
public class MiFactory implements AbstractFactory {
    @Override
    public AbstractProduct makePhone() {
        return new MiPhone();
    }

    @Override
    public AbstractProduct makeComputer() {
        return new MiComputer();
    }
}
