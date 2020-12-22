package com.appleye.jsoup;

import com.appleye.util.Constants;
import com.google.protobuf.MapEntry;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Appleye
 * @created 2020-10-18 10:46
 */
public class Reptile {

    static String mainUrl = "https://www.ac63.xyz";
    static String mainUrl2 = "https://www.ac59.xyz";



    public static void main(String[] args) throws IOException {


        for (int i = 1; i <= 50; i++) {
            savePage(i);
            System.out.println("存储完第"+ i +"页");
        }

        for (int i = 1; i <= 50; i++) {
            goToDB(readByLine(i));
            System.out.println("入库完第"+ i +"页");

        }

        //1.磁力、图片、标题

    }

    public static String getFilePath(Integer pageNumber) {
        return "E:\\Program Files\\Files\\Reptile\\Page " + pageNumber + ".txt";
    }

    /**
     * 按页存储爬取list写文件
     * @param pageNumber
     */
    public static void savePage(Integer pageNumber){
        String url = mainUrl + "/index.php?page=" + pageNumber;

        try {
            Document doc = Jsoup.connect(url).get();
            Elements ul = doc.getElementsByClass("list");

            BufferedWriter out = new BufferedWriter(new FileWriter(getFilePath(pageNumber)));
            out.write(String.valueOf(ul));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件获取id,title
     * @param pageNumber
     * @return
     */
    public static Map readByLine(Integer pageNumber){
        Map map = new HashMap();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(getFilePath(pageNumber)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(" <li>[")) {
                    String movieID = line.substring(line.indexOf("id=") + 3, line.indexOf("\" ta"));
                    String ciphertext = line.substring(line.indexOf("d('") + 3, line.indexOf("')"));
                    String title = TextHandler.parseTitle(ciphertext);
                    map.put(movieID, title);

                }
            }

            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据map生成sql并调用执行SQL
     * @param map
     */
    public static void goToDB(Map map) {

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry next = iterator.next();
            String sql = String.format("insert into movie (movie_id, title) select '%s' movie_id,'%s' title from dual where not exists (select 1 from movie where movie_id = '%s');", next.getKey(), next.getValue(), next.getKey());
            System.out.println(sql);
            save(sql);
        }
    }

    /**
     * 执行sql
     * @param sql
     */
    public static void save(String sql){

        try( Connection conn = DriverManager.getConnection(
                "jdbc:mysql://47.95.9.21/dsbt?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT"
                ,Constants.USERNAME.getContext()
                ,Constants.PASSWORD.getContext()
                );

             PreparedStatement ps = conn.prepareStatement(sql);
             ) {
            boolean execute = ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



}
