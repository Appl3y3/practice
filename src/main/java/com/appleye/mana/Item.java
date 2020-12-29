package com.appleye.mana;

import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Appleye
 * @time 2020-10-19 16:55
 */
public class Item<ToStrin> {
    Integer initMana;
    Integer restoreMana;
    ItemList<Equipment> itemList;

    private static final Integer INITIAL_CAPACITY = 3;

    private class ItemList<Equipment> extends ArrayList{
        public ItemList(){
            super(INITIAL_CAPACITY);
        }
    }

    public Item(ArrayList itemList){
        this.itemList = new ItemList();
        try {
            addEquipmentToItem(itemList);
        } catch (Exception e) {
            System.out.println("only one bluebuff");
            this.itemList = null;
        }
    }

    public void addEquipmentToItem(ArrayList itemList) throws Exception{
        int blueBuff = 0;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i) instanceof BlueBuff) blueBuff++;
            this.itemList.add(i,itemList.get(i));
        }
        if (blueBuff >= 2) throw new Exception();

    }


    public Integer getInitMana(){
        Integer initMana = 0;
        for (int i = 0; i < itemList.size(); i++) {
//            if (itemList.get(i) instanceof Equipment)
            initMana += ((Equipment) itemList.get(i)).getExtraMana();
        }
        return initMana;
    }


    public Integer getRestoreManaAfterAttack(){
        int falchion = 0;
        int splitBow = 0;
        int restoreMana = 0;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i) instanceof Falchion) {falchion++;restoreMana = 5;}
            if (itemList.get(i) instanceof SplitBow) splitBow++;
        }
        return 10 + (falchion * restoreMana) * (1 + splitBow);
    }

    public Integer getRestoreManaAfterUlt(){
        int blueBuff = 0;
        int restoreMana = 0;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i) instanceof BlueBuff) {blueBuff++;restoreMana = 20;}
        }
        return (blueBuff * restoreMana);
    }

    @Override
    public String toString(){
        Map detail = getDetail();
        return String.format("%s * %s, %s * %s, %s * %s",
                Falchion.NAME, detail.get(Falchion.NAME),
                BlueBuff.NAME, detail.get(BlueBuff.NAME),
                SplitBow.NAME, detail.get(SplitBow.NAME));

    }

    public Map getDetail(){
        Map<String, Integer> map = new HashMap(INITIAL_CAPACITY);
        int falchion = 0;
        int blueBuff = 0;
        int splitBow = 0;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i) instanceof Falchion) falchion++;
            if (itemList.get(i) instanceof BlueBuff) blueBuff++;
            if (itemList.get(i) instanceof SplitBow) splitBow++;
        }
        map.put(Falchion.NAME, falchion);
        map.put(BlueBuff.NAME, blueBuff);
        map.put(SplitBow.NAME, splitBow);
        return map;
    }

    public Boolean hasEquipment(String name) {
        Map<String, Integer> detail = getDetail();
        if (detail.get(BlueBuff.NAME) > 0) return true;
        return false;
    }


}

