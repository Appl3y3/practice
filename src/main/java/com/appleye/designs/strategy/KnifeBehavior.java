package com.appleye.designs.strategy;

/**
 * @author Appleye
 * @createTime 2020-08-14 10:05
 */
public class KnifeBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("Stabbing with a knife");
    }
}
