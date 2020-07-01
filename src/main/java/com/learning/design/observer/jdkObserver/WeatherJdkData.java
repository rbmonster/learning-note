package com.learning.design.observer.jdkObserver;

import java.util.Observable;

/**
 * <pre>
 * @Description:
 * T 注意这边集成的是类
 * </pre>
 *
 * @version v1.0
 * @ClassName: WeatherJdkData
 * @Author: sanwu
 * @Date: 2020/7/1 11:42
 */
public class WeatherJdkData extends Observable {

    private float temperature;
    private float humidity;
    private float pressure;

    public void measurementsChanged(){
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
}
