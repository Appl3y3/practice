package com.appleye.question;

/**
 * 一个木棍任意截成三段，围成三角形的概率？
 * @author Appleyed
 * @createdTime 2020-08-17 14:09
 */
class StickDividedTriangle {

     static int getBig(int x, int y) {
        if (x - y >= 0) return x;
        return y;
    }

     static int getSmall(int x, int y) {
        if (x - y >= 0) return y;
        return x;
    }

     static boolean isTriangle(int x, int y, int z) {
         return x + y > z && x + z > y && y + z > x;
     }

     static int[] getThreeLength(int len) {
        int[] lens = new int[3];

        int number1 = (int) (Math.random()*len);
        int number2 = (int) (Math.random()*len);

        int big = getBig(number1, number2);
        int small = getSmall(number1, number2);

        lens[0] = small;
        lens[1] = big - small;
        lens[2] = len - big;
        return lens;
    }


}
