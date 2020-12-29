package com.appleye.leetcode;

/**
 * @author Appleye
 * @time 2020-10-12 09:20
 */
public class Solution {
    /**
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
     * 请找出数组中任意一个重复的数字。
     *
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        int[] count = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (count[nums[i]] < 1) {
                count[nums[i]] = count[nums[i]] + 1;
            } else {
                return nums[i];
            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个函数，
     * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > target) continue;
                if (matrix[i][j] == target) return true;
            }
        }
        return false;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * @param s
     * @return
     */
    public static String replaceSpace(String s) {

        return s.replace(" ","%20");
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 6, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(nums));


        int[][] matrix = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        System.out.println(findNumberIn2DArray(matrix, 20));

        String s = "We are happy.";



    }
}
