package com.learning.design.decorator;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: ChessAddition
 * @Author: sanwu
 * @Date: 2020/7/8 0:43
 */
public class ChessAddition extends DrinkAddition {
    public ChessAddition(Drink drink) {
        super(drink);
    }

    @Override
    public float getPrice() {
        return super.getPrice()+ 0.5f;
    }

    @Override
    public String getDescription() {
        return super.getDescription()+ " add chess ";
    }
}
