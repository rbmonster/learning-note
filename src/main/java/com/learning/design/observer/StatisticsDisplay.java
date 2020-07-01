package com.learning.design.observer;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: StatisticsDisplay
 * @Author: sanwu
 * @Date: 2020/7/1 11:34
 */
public class StatisticsDisplay implements Observer,DisplayElement {
    private Subject subject;
    private float temperature;
    private float humidity;
    private float pressure;

    public StatisticsDisplay(WeatherData weatherData) {
        this.subject = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("statics condition is that:"+this.temperature + this.humidity + this.pressure);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {

        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
}
