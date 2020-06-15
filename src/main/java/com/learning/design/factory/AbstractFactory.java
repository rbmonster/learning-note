package com.learning.design.factory;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: AbstractFactory
 * @Author: sanwu
 * @Date: 2020/5/9 22:14
 */
public interface AbstractFactory {

    AbstractProduct makePhone();

    AbstractProduct makeComputer();
}
