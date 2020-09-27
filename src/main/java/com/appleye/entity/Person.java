package com.appleye.entity;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Appleye
 * @createdtime 2020-09-27 13:58
 */
@Getter
@ToString
public class Person {
    String name;
    Integer age;
    String gender;

    public Person(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
