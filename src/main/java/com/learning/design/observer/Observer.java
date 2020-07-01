package com.learning.design.observer;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: Observer
 * @Author: sanwu
 * @Date: 2020/7/1 11:07
 */
public interface Observer {
    void update(float temperature, float humidity, float pressure);
}
