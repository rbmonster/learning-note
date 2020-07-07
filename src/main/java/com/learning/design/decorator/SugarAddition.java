package com.learning.design.decorator;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: SugarAddition
 * @Author: sanwu
 * @Date: 2020/7/8 0:38
 */
public class SugarAddition extends DrinkAddition {

    public SugarAddition(Drink drink) {
        super(drink);
    }

    @Override
    public float getPrice() {
        return super.getPrice()+0.1f;
    }


    @Override
    public String getDescription() {
        return super.getDescription() + " add sugar ";
    }
}
