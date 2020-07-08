package com.learning.design.iterator;

import java.util.Arrays;
import java.util.Iterator;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: DinerMenu
 * @Author: sanwu
 * @Date: 2020/7/8 15:12
 */
public class DinerMenu implements Menu{
    static final int MAX_MENU_ITEMS = 2;
    MenuItem[] menuItems;
    int numOfMenu=0;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_MENU_ITEMS];
        addItem("apple", "apple is good ", true, 1.2f);
        addItem("beer", "beer is good ", true, 1.2f);
    }

    public void addItem(String name, String description, boolean vegetarian, double price ) {
        if (numOfMenu>6){
            System.out.println("menu is full~");
        } else {
            MenuItem menuItem = new MenuItem(name,description,price,vegetarian);
            menuItems[numOfMenu++] = menuItem;
        }
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return  Arrays.asList(menuItems).iterator();
    }
}
