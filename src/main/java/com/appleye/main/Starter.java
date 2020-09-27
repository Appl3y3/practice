package com.appleye.main;

import com.appleye.entity.Person;
import com.appleye.util.Constants;
import com.appleye.util.DataCreation;

import java.util.*;

/**
 * @author Appleye
 * @version 1.0
 * @date 2020/7/9
 * @time 10:01
 */
public class Starter {
    public static void main(String[] args) {
        DataCreation.createData("D:\\Files\\sql_million.sql",1_000_000,false);
//        DataCreation.insert("D:\\Files\\sql.sql");
//        System.out.println(Tools.isEven(6));

//        Tools tools = new Tools();
//        System.out.println(tools);

//        System.out.println(Tools.getSHA256Str("qfq"));

//        TxtOperation.modifyFileContent("D:\\Files\\加密测试数据.txt");


        //利用反射修改不可变String
//        try {
//            String str = "大苏打";
//            Field field = String.class.getDeclaredField("value");
//            field.setAccessible(true);
//            char[] value;
//            value = (char[]) field.get(str);
//            value[0] ='小';
//            System.out.println(str);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }


//        PublicDemo.hello();
//        ProtectedDemo.hello();
//        DefaultDemo.hello();
//        PrivateDemo.hello();
//
//        Map concurrentHashMap = new ConcurrentHashMap();
//        final HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
//


        List<Integer> list = new LinkedList<Integer>();

        Constants.PASSWORD.getContext();
        String str1 = new String("sda");
        String str2 = str1.intern();
        String str3 = "sda";
        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str1 == str3);


        Person p = new Person("张三", 24, "男");
        System.out.println(p.toString());

    }


}