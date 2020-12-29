package com.appleye.mana;

/**
 * @author Appleye
 * @time 2020-10-20 14:06
 */
public class BlueBuff extends Equipment{
    public static final String NAME = "BlueBuff";

    public BlueBuff(Integer extraMana, Integer restoreMana){
        this.extraMana = extraMana;
        this.restoreMana = restoreMana;
    }
}

