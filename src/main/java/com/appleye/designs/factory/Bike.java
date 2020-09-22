package com.appleye.designs.factory;

/**
 * @author Appleye
 * @createTime 2020-08-13 17:23
 */
public abstract class Bike implements Vehicle {
    @Override
    public void makeHead() {
        System.out.println("Bike's head is created");
    }

    @Override
    public void makeBody() {
        System.out.println("Bike's head is created");
    }

    @Override
    public void makeTail() {
        System.out.println("Bike's head is created");
    }


}
