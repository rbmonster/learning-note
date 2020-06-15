package com.learning.design.factory;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: PCFactory
 * @Author: sanwu
 * @Date: 2020/5/9 22:17
 */
public class AppleFactory implements AbstractFactory {


    @Override
    public AbstractProduct makePhone() {
        return new IPhone();
    }

    @Override
    public AbstractProduct makeComputer() {
        return new IMac();
    }
}
