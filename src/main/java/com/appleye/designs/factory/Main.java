package com.appleye.designs.factory;

/**
 * 工厂方法模式定义了一个创建对象的接口，但由子类决定要实例化的类是哪一个。
 * 工厂方法让类把实例化推到子类。
 * @author Appleye
 * @createTime 2020-08-13 17:27
 */
public class Main {
    public static void main(String[] args) {
        Factory bmwFactory = new BmwFactory();
        Vehicle bmwCar = bmwFactory.getCar();
        Vehicle bmwBike = bmwFactory.getBike();
        bmwCar.assemble();
        bmwBike.assemble();
        Car car = (Car) bmwCar;




    }
}
