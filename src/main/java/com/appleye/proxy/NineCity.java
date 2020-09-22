package com.appleye.proxy;

/**
 * @author Appleye
 * @createTime 2020-08-13 15:46
 */
public class NineCity implements Game {

    private Game game;

    public NineCity(Game game) {
        this.game = game;
    }

    @Override
    public void operate(String companyName) {
        System.out.println("Localize……");
        game.operate(companyName);
        System.out.println("Online activities……");
    }
}
