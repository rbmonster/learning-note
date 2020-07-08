package com.learning.design.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: PancakeHouseMenu
 * @Author: sanwu
 * @Date: 2020/7/8 15:11
 */
public class PancakeHouseMenu implements Menu{
    List<MenuItem> menuItems;
    public PancakeHouseMenu() {
        menuItems = new ArrayList<>();
        addItem("rice", "rice is good ", true, 1.2f);
        addItem("chicken", "chicken is good ", true, 1.2f);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, price, vegetarian);
        menuItems.add(menuItem);

    }

    @Override
    public Iterator createIterator() {
        return menuItems.iterator();
    }
}
