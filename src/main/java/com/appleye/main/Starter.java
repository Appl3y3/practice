package com.appleye.main;

import com.appleye.jsoup.Reptile;
import com.appleye.jsoup.TextHandler;
import com.appleye.modifier.DefaultDemo;
import com.appleye.modifier.PrivateDemo;
import com.appleye.modifier.ProtectedDemo;
import com.appleye.modifier.PublicDemo;
import com.appleye.util.DataCreation;
import com.mysql.cj.util.StringUtils;
import com.sun.xml.internal.bind.v2.TODO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Appleye
 * @version 1.0
 * @date 2020/7/9
 * @time 10:01
 */
public class Starter {
    public static void main(String[] args) throws Exception{
//        DataCreation.createData("D:\\Files\\sql_million.sql",1_000_000,false);
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




    }


}