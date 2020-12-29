package com.appleye.mana;

/**
 * @author Appleye
 * @time 2020-10-20 13:41
 */
public class SplitBow extends Equipment{
    public static final String NAME = "SplitBow";

    public SplitBow(Integer extraMana, Integer restoreMana){
        this.extraMana = extraMana;
        this.restoreMana = restoreMana;
    }
}
