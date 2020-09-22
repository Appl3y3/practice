package com.appleye.designs.observer;

import java.util.ArrayList;

/**
 * @author Appleye
 * @createTime 2020-08-17 11:27
 */
public interface Subject {
    ArrayList<Observer> observers = new ArrayList<>();

    default void register(Observer observer) {
        observers.add(observer);
    }

    default void remove(Observer observer) {
        observers.remove(observer);
    }

    default void notifyAllObserver(Object object) {
        for (Observer observer : observers) {
            observer.update(this, object);
        }
    }
    //TODO
}
