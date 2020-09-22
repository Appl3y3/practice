package com.appleye.designs.strategy;

/**
 * @author Appleye
 * @createTime 2020-08-14 09:54
 */
public abstract class Character {
    WeaponBehavior weaponBehavior;

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    public void fight() {
        weaponBehavior.useWeapon();
    }
}
