package com.learning.design.factory;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: MiPhone
 * @Author: sanwu
 * @Date: 2020/5/10 21:49
 */
public class MiPhone implements AbstractProduct{
    public MiPhone(){
        this.make();
    }
    @Override
    public void make() {
        System.out.println("this is new MiPhone!");
    }
}
