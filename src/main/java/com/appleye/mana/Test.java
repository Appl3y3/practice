package com.appleye.mana;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Appleye
 * @time 2020-10-19 17:02
 */
public class Test {
    public static void main(String[] args) {
        Equipment falchion = new Falchion(15,5);
        Equipment blueBuff = new BlueBuff(30,20);
        Equipment splitBow = new SplitBow(0,0);


        ArrayList list = new ArrayList(3);
        list.add(falchion);
        list.add(blueBuff);
//        list.add(blueBuff);

        //修改这
        BlueBar blueBar = new BlueBar(0,35);

        int AttackTimes = 0;
        int blueBuffMana = 0;


        try {
            Item item = new Item(list);
            System.out.println(item.toString());
            System.out.println(String.format("初始蓝量为：%s",item.getInitMana()));
            System.out.println(String.format("每次攻击回蓝为：%s",item.getRestoreManaAfterAttack()));
            System.out.println(String.format("释放技能后回蓝为：%s",item.getRestoreManaAfterUlt()));

            while ((blueBar.getChampionInitMana() + item.getInitMana() + AttackTimes * item.getRestoreManaAfterAttack()) < blueBar.maxMana ){
                AttackTimes++;
            }
            System.out.println(String.format("第一次需要%s次才能放Ult",AttackTimes));
            if (item.hasEquipment(BlueBuff.NAME)) blueBuffMana = 20;
            while ((blueBuffMana + AttackTimes * item.getRestoreManaAfterAttack()) < blueBar.maxMana ){
                AttackTimes++;
            }
            System.out.println(String.format("后续需要%s次才能放Ult",AttackTimes));



        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

