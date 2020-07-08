package com.learning.design.combination;

import java.util.Iterator;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: CompositeIterator
 * @Author: sanwu
 * @Date: 2020/7/8 15:58
 */
public class CompositeIterator implements Iterator {
    Stack stack = new Stack();

    public CompositeIterator(Iterator iterator) {
        stack.push(iterator);
    }
    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        } else {
            Iterator iterator = (Iterator) stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public Object next() {
        if (hasNext()) {
            Iterator iterator = (Iterator) stack.peek();
            MenuComponent menuComponent = (MenuComponent) iterator.next();
            if (menuComponent instanceof  Menu) {
                stack.push(menuComponent);
            }
            return menuComponent;
        }
        return null;
    }

    @Override
    public void remove() {

    }
}
