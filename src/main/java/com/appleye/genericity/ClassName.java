package com.appleye.genericity;

/**
 * @author Appleye
 * @time 2020-08-27 17:24
 */
public class ClassName<T> {
    void show_1(T t){
        System.out.println("show_1 " + t.toString());
    }

    <E> void show_2(E e) {
        System.out.println("show_2 " + e.toString());
    }

    <T> void show_3(T t) {
        System.out.println("show_3 " + t.toString());
    }

    public static void main(String[] args) {
        ClassName<Fruit> o = new ClassName<Fruit>();
        Fruit fruit = new Fruit();
        Apple apple = new Apple();
        Person person = new Person();
        System.out.println("show_1 演示——————————————————————————");
        o.show_1(fruit);
        o.show_1(apple);
//        o.show_1(person);因为在ClassName<Fruit>中已经限定了全局的T为Fruit，所以不能再加入Person;
        System.out.println("show_2 演示——————————————————————————");
        o.show_2(fruit);
        o.show_2(apple);
        o.show_2(person);
        System.out.println("show_3 演示——————————————————————————");
        o.show_3(fruit);
        o.show_3(apple);
        o.show_3(person);
    }


}
