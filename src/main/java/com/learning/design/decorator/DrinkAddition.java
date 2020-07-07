package com.learning.design.decorator;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: DrinkAddition
 * @Author: sanwu
 * @Date: 2020/7/8 0:34
 */
public abstract class DrinkAddition implements Drink {

    private Drink drink;
    public DrinkAddition(Drink drink) {
        this.drink = drink;
    }

    public float getPrice(){
        return drink.getPrice();
    }

    @Override
    public String getDescription() {
        return drink.getDescription();
    }
}
