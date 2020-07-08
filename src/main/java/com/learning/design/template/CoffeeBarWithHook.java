package com.learning.design.template;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: CoffeBarBaverageWithHook
 * @Author: sanwu
 * @Date: 2020/7/9 0:11
 */
public abstract class CoffeeBarWithHook {
    void prepareRecipe() {
        boilWater();
        cook();
        pourInCup();
    }

    abstract void cook();

    void boilWater() {
        System.out.println("boil water for drink");
    }

    void pourInCup() {
        System.out.println("pour drink in cup");
    }
}
