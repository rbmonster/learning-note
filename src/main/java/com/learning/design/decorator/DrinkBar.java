package com.learning.design.decorator;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: DrinkBar
 * @Author: sanwu
 * @Date: 2020/7/8 0:44
 */
public class DrinkBar {
    public static void main(String[] args) {
        Drink coffee = new Coffee();
        SugarAddition sugar = new SugarAddition(coffee);
        ChessAddition chess = new ChessAddition(sugar);
        System.out.println(chess.getDescription());
        System.out.println("total need money is:" +chess.getPrice() + " dollar");
    }
}
