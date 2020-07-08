package com.learning.design.combination;

import java.util.Iterator;
import java.util.List;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Menu
 * @Author: sanwu
 * @Date: 2020/7/8 15:38
 */
public class Menu implements MenuComponent{

    String name;
    String description;
    double price;
    boolean vegetarian;
    List<MenuItem> menuItems;

    public Menu(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void print() {
        String desc = "Menu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
        System.out.println(desc);
        Iterator iterator = menuItems.iterator();
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();
            menuItem.print();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Iterator createIterator() {
        return menuItems.iterator();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
