package com.appleye.designs.factory;

/**
 * @author Appleye
 * @createTime 2020-08-13 17:27
 */
public class BenzFactory implements Factory {

    @Override
    public Car getCar() {
        return new BenzCar();
    }

    @Override
    public Bike getBike() {
        return new BenzBike();
    }
}
