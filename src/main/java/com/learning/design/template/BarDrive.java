package com.learning.design.template;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: BarDrive
 * @Author: sanwu
 * @Date: 2020/7/9 0:15
 */
public class BarDrive {
    public static void main(String[] args) {
        CoffeeWithHook coffeeWithHook = new CoffeeWithHook();
        MilkWithHook milkWithHook = new MilkWithHook();
        coffeeWithHook.prepareRecipe();
        milkWithHook.prepareRecipe();
    }
}
