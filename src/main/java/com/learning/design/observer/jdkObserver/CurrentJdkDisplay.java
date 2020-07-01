package com.learning.design.observer.jdkObserver;

import com.learning.design.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: CurrentJdkDisplay
 * @Author: sanwu
 * @Date: 2020/7/1 11:45
 */
public class CurrentJdkDisplay implements DisplayElement, Observer {

    private float temperature;
    private float humidity;
    private float pressure;
    private Observable observable;

    public CurrentJdkDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }
    @Override
    public void display() {
        System.out.println("current condition is that:"+this.temperature + this.humidity + this.pressure);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherJdkData) {
            this.temperature = ((WeatherJdkData) o).getTemperature();
            this.humidity = ((WeatherJdkData) o).getHumidity();
            this.pressure = ((WeatherJdkData) o).getPressure();
            this.display();
        }
    }
}
