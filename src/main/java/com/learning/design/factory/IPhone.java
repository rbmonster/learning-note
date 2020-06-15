package com.learning.design.factory;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: IPhone
 * @Author: sanwu
 * @Date: 2020/5/9 22:16
 */
public class IPhone implements AbstractProduct {
    public IPhone(){
        this.make();
    }
    @Override
    public void make() {
        System.out.println("this is new iphone!");
    }
}
