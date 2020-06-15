package com.learning.design.factory;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Computer
 * @Author: sanwu
 * @Date: 2020/5/9 22:16
 */
public class IMac implements AbstractProduct {
    public IMac(){
        this.make();
    }
    @Override
    public void make() {
        System.out.println("this is new IMac!");
    }
}
