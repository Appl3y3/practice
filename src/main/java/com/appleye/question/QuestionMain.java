package com.appleye.question;

import java.util.Map;

import static com.appleye.question.StickDividedTriangle.getThreeLength;
import static com.appleye.question.StickDividedTriangle.isTriangle;

/**
 * @author Appleye
 * @createTime 2020-08-20 15:03
 */
public class QuestionMain {
    public static void main(String[] args) {

        //木棍拆分问题
//        int len = 1_000_000;
//        int count = 0;
//        for (int i = 0; i < len; i++) {
//            int[] threeLength = getThreeLength(len);
//            boolean isTriangle = isTriangle(threeLength[0], threeLength[1], threeLength[2]);
//            if (isTriangle) {
//                count++;
//            }
//        }
//        System.out.println(100.0 * count / len + "%");
//
//
//        //三门问题
//        int count1 = 1_000_000;
//        int lucky = 0;
//        for (int i = 0; i < count1; i++) {
//            if ("Lamborghini".equals(ThreeDoors.change()[1])) {
//                lucky++;
//            }
//        }
//        System.out.println(100.0 * lucky / count1 + "%");

        //Collatz猜想(3n+1)
        int x = 27;
        Map<String, Integer> record = Collatz.record(x);
        System.out.println(record.get("maxValue"));
        System.out.println(record.get("times"));


    }
}
