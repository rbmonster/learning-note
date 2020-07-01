package com.learning.design.observer.jdkObserver;

/**
 * <pre>
 * @Description:
 * TODO
 * </pre>
 *
 * @version v1.0
 * @ClassName: WeatherJdkStation
 * @Author: sanwu
 * @Date: 2020/7/1 12:04
 */
public class WeatherJdkStation {
    public static void main(String[] args) {
        WeatherJdkData weatherJdkData = new WeatherJdkData();
        CurrentJdkDisplay currentJdkDisplay = new CurrentJdkDisplay(weatherJdkData);
        weatherJdkData.setMeasurements(11,12,123);
    }
}
