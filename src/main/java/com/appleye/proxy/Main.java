package com.appleye.proxy;

/**
 * @author Appleye
 * @createTime 2020-08-13 16:21
 */
public class Main {
    public static void main(String[] args) {
        Game game = new NineCity(new BlizzardGame());
        game.operate(game.getClass().getSimpleName());


    }
}
