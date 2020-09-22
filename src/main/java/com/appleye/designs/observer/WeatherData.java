package com.appleye.designs.observer;

/**
 *
 * @author Appleye
 * @createTime 2020-08-17 13:26
 */
public class WeatherData implements Subject {

    private double temperature;
    private double pressure;
    private double humidity;

    public double getTemperature() {
        return temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void changeData(double temperature, double pressure, double humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;

        notifyAllObserver(this);
    }
    //TODO

}
