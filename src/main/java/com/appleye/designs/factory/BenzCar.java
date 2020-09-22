package com.appleye.designs.factory;

/**
 * @author Appleye
 * @createTime 2020-08-13 17:38
 */
public class BenzCar extends Car {
    @Override
    public void assemble() {
        System.out.println("本子车");
    }
}
