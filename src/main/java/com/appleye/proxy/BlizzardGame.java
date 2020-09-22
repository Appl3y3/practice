package com.appleye.proxy;

/**
 * @author Appleye
 * @createTime 2020-08-13 15:46
 */
public class BlizzardGame implements Game {
    @Override
    public void operate(String companyName) {
        System.out.println("Game is operating by " + companyName);
    }
}
