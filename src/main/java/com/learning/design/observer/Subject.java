package com.learning.design.observer;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Subject
 * @Author: sanwu
 * @Date: 2020/7/1 11:06
 */
public interface Subject {
    void registerObserver(Observer o);
    void cancelObserver(Observer o);
    void notifyAllObserver();
}
