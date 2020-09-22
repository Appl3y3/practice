package com.appleye.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 三门问题：
 * 某竞猜节目,有三扇门，门后分别有两只山羊和一辆Lamborghini。
 * 选手选选择其中一扇门A，然后主持人打开剩余两扇门BC中的一扇门B，门B后是山羊。
 * 现在选手有第二次选择的机会，请问选手继续选A还是改选C，哪种选择更有机会获得Lamborghini。
 * @author Appleye
 * @createTime 2020-08-20 14:47
 */
class ThreeDoors {

    /**
     * @param input
     * @return
     */
     static String getRandomString(int input) {
        String output = null;
        switch (input) {
            case 0 : output = "A";break;
            case 1 : output = "B";break;
            case 2 : output = "C";break;
            default: output = null;
        }
        return output;
    }

    /**
     * 获取三扇门，其中随机一扇是Lamborghini，其他两扇是sheep
     * @return
     */
     static Map<String, String> getRandomThreeDoors() {
        Map<String, String> map = new HashMap<String, String>(3);
        map.put("A", "sheep");
        map.put("B", "sheep");
        map.put("C", "sheep");
        int randomInt = new Random().nextInt(3);
        String randomString = getRandomString(randomInt);
        map.put(randomString, "Lamborghini");

        return map;
    }

    /**
     * 选手改变选择
     * @return
     */
     static String[] change() {
        String[] choices = new String[2];
        Map<String, String> randomThreeDoors = getRandomThreeDoors();

        String firstChoice = randomThreeDoors.get("A");

        String secondChoice = null;
        String door1 = randomThreeDoors.get("B");
        String door2 = randomThreeDoors.get("C");

        if (door1.equals("sheep")) {
            secondChoice = door2;
        } else {
            secondChoice = door1;
        }

        choices[0] = firstChoice;
        choices[1] = secondChoice;

        return choices;
    }

}
