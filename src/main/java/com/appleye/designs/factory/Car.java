package com.appleye.designs.factory;

/**
 * @author Appleye
 * @createTime 2020-08-13 17:21
 */
public abstract class Car implements Vehicle {
    @Override
    public void makeHead() {
        System.out.println("Car's head is created");
    }

    @Override
    public void makeBody() {
        System.out.println("Car's body is created");
    }

    @Override
    public void makeTail() {
        System.out.println("Car's tail is created");
    }
}
