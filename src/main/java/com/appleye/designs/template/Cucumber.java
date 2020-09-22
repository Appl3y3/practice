package com.appleye.designs.template;

import java.util.Scanner;

/**
 * @author Appleye
 * @createTime 2020-08-14 09:44
 */
public class Cucumber extends Vegetables {
    @Override
    protected void cooking() {
        System.out.println("ice cucumber");
    }

    @Override
    protected boolean isPeel() {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        if ("n".equals(next)) {
            return false;
        }
        return true;
    }
}
