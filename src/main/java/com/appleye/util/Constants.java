package com.appleye.util;

import lombok.Getter;

/**
 * @author Appleye
 * @version 1.0
 * @date 2020/7/9
 * @time 13:47
 */
@Getter
public enum Constants {
//    DRIVER("com.mysql.cj.jdbc.Driver"),
//    URL("jdbc:mysql://localhost/test?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT"),
//    USERNAME("Appleye"),
//    PASSWORD("Yosug4");

    DRIVER("com.mysql.cj.jdbc.Driver"),
    URL("jdbc:mysql://47.95.9.21/test?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT"),
    USERNAME("Appleye"),
    PASSWORD("Yosug4");

    private String context;

    Constants(String context) {
        this.context = context;
    }
    public String getContext() {
        return context;
    }
}
