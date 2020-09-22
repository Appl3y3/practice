package com.appleye.designs.strategy;

/**
 * @author Appleye
 * @createTime 2020-08-14 10:04
 */
public class SwordBehavior implements WeaponBehavior {
    @Override
    public void useWeapon() {
        System.out.println("Brandish with a sword");
    }
}
