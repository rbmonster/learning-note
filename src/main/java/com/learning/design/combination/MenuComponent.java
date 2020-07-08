package com.learning.design.combination;

import java.util.Iterator;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: MenuComponent
 * @Author: sanwu
 * @Date: 2020/7/8 15:36
 */
public interface MenuComponent {

    String getName();

    double getPrice();

    String getDescription();

    Iterator createIterator();
}
