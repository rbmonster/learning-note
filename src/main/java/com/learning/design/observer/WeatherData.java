package com.learning.design.observer;

import java.util.ArrayList;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: WatherData
 * @Author: sanwu
 * @Date: 2020/7/1 11:15
 */
public class WeatherData implements Subject {
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;
    private boolean status;

    public WeatherData() {
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void cancelObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyAllObserver() {
        if (status){
            // 用于控制更新的条件，才不会导致频繁更新
        }
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }

    public void setStatus(){
        status = true;
    }

    /**
     * 增加一个处理方法 增加拓展性
     */
    public void measurementChanged() {
        notifyAllObserver();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }

}
