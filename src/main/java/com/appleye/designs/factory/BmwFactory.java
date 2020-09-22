package com.appleye.designs.factory;

/**
 * @author Appleye
 * @createTime 2020-08-13 17:26
 */
public class BmwFactory implements Factory {

    @Override
    public Car getCar() {
        return new BmwCar();
    }

    @Override
    public Bike getBike() {
        return new BmwBike();
    }
}
