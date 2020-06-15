package com.learning.design.factory;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: MiComputer
 * @Author: sanwu
 * @Date: 2020/5/10 21:50
 */
public class MiComputer implements AbstractProduct {
    public MiComputer(){
        this.make();
    }
    @Override
    public void make() {
        System.out.println("this is MiComputer");
    }
}
