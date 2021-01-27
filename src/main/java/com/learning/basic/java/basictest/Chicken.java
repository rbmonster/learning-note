package com.learning.basic.java.basictest;

/**
 * <pre>
 * @Description:
 *
 * </pre>
 *
 * @version v1.0
 * @ClassName: Chicken
 * @Author: sanwu
 * @Date: 2021/1/27 10:36
 */
public class Chicken extends Food {

    @Override
    public Food cook() {
        Food food =  new Chicken();
        return  food;
    }

    public static void main(String[] args) {
        new Chicken().cook();
    }
}
