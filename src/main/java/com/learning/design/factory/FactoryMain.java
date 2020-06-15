package com.learning.design.factory;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: FactoryMain
 * @Author: sanwu
 * @Date: 2020/5/10 21:52
 */
public class FactoryMain {
    public static void main(String[] args) {
        AbstractFactory appleFactory = new AppleFactory();
        AbstractFactory miFactory = new MiFactory();
        IPhone iPhone = (IPhone) appleFactory.makePhone();

    }
}
