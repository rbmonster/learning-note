package com.learning.design.observer;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: CurrentConditionDisplay
 * @Author: sanwu
 * @Date: 2020/7/1 11:22
 */
public class CurrentConditionDisplay implements Observer,DisplayElement {
    private Subject subject;
    private float temperature;
    private float humidity;
    private float pressure;

    public CurrentConditionDisplay(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("current condition is that:"+this.temperature + this.humidity + this.pressure);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
}
