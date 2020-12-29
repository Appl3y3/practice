package com.appleye.mana;

import lombok.Data;

/**
 * @author Appleye
 * @time 2020-10-20 15:16
 */
@Data
public class BlueBar {
    Integer championInitMana;
    Integer maxMana;

    public BlueBar(Integer championInitMana, Integer maxMana) {
        this.championInitMana = championInitMana;
        this.maxMana = maxMana;
    }
}
