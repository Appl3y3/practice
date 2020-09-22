package com.appleye.designs.strategy;

/**
 * @author Appleye
 * @createdTime 2020-08-14 10:06
 */
public class Main {
    public static void main(String[] args) {
        Knight knight = new Knight(new KnifeBehavior());
        knight.fight();
        knight.setWeaponBehavior(new SwordBehavior());
        knight.fight();
    }
}
