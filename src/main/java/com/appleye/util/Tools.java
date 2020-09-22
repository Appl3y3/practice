package com.appleye.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Appleye
 * @version 1.0
 * @date 2020/7/10
 * @time 11:39
 */
public class Tools {

    /**
     * 判断一个数是不是质数
     * @param x 输入的任意正整数
     * @return 是否质数
     */
    public static boolean isPrime(int x) {
        double limit = Math.sqrt(x);
        for (int i = 2; i < limit; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个数是否偶数
     * @param x 输入的任意正整数
     * @return 是否偶数
     */
    public static boolean isEven(int x) {
        return (x & 1) == 0;
    }

    /**
     * 将一个偶数拆成两个质数和
     * @param x 输入的任意偶数
     * @return 两个质数 eg. {3,7}
     */
    public static String splitEvenToTwoPrimes(int x) {
        if (isEven(x)) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < x / 2; i++) {
                if (isPrime(i) && isPrime(x - i)) {
                    sb.append("{" + i + "," + (x - i) + "}");
                }
            }
            return sb.toString();
        }
        return "请输入偶数";
    }

    /**
     * 计算x内质数密度及计算耗时
     * @param x
     * @return
     */
    public static String[] primeDensity(int x) {
        long l1 = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < x + 1; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        long l2 = System.currentTimeMillis();
        String time = (l2 - l1) / 1000.0 + "s";
        String primeDensity = 100.00 * count / x + "%";
        return new String[]{time, primeDensity};
    }

    /**
     * 获取两数的最大公约数
     * @param x
     * @param y
     * @return
     */
    public static int getGCD(int x, int y) {return y == 0 ? x : getGCD(y, x % y);}

    /**
     * 牛顿迭代法开平方
     * @param x
     * @return
     */
    public static double sqrtByNewton(double x) {
        if (x == 0) {
            return 0.0d;
        }
        double last = 0.0d;
        double result = 1.0d;
        while (result != last) {
            last = result;
            result = (result + x / result) / 2;
        }
        return result;
    }

    /**
     * 无穷级数法求PI
     * @param x
     * @return
     */
    public static  double getPI(double x) {
        double sum = 2;
        int n = 1;
        int m = 3;
        double t = 2;
        while (t > x) {
            t = t * n / m;
            sum += t;
            n++;
            m+=2;
        }
        return sum;
    }

    /**
     * 获取整数x的倒数第y位
     * @param x
     * @param y
     * @return
     */
    public static int getDescNum(int x, int y) {
        int temp = 1;
        for (int i = 0; i < y - 1; i++) {
            temp = temp * 10;
        }
        return x / temp % 10;
    }

    /**
     * 获取数字的长度（几位数）
     * @param num
     * @return
     */
    public static int getNumLength(int num) {
        int len = 1;
        for (;;) {
            if (num / (int)Math.pow(10,len) == 0) {
                break;
            } else {
                len++;
            }
        }
        return len;
    }

    /**
     * 判断给定数是否为水仙花数
     * @param num
     * @return
     */
    public static boolean isNarcissus(int num) {
        boolean flag = false;
        int length = getNumLength(num);

        int temp = 0;
        for (int i = 0; i < length + 1; i++) {
            int factor = getDescNum(num, i);
            temp = temp + factor * factor * factor;
        }

        if (temp == num) {
            flag = true;
        }
        return flag;
    }

    /**
     * 获取小于指定数x的水仙花数列表
     * @param x
     * @return
     */
    public static List<Integer> getNarcissuses(int x) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < x + 1; i++) {
            if (isNarcissus(i)) {
                integers.add(i);
            }
        }
        return integers;
    }

    /**
     * 利用java原生的摘要实现SHA256加密
     * @param str
     * @return
     */
    public static String getSHA256Str(String str){
        MessageDigest messageDigest;
        String encodeStr = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
