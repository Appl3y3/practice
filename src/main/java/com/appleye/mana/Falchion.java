package com.appleye.mana;

import lombok.Data;

/**
 * @author Appleye
 * @time 2020-10-19 16:47
 */
@Data
public class Falchion extends Equipment{
    public static final String NAME = "Falchion";

    public Falchion(Integer extraMana, Integer restoreMana){
        this.extraMana = extraMana;
        this.restoreMana = restoreMana;
    }
}
