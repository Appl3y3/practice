package com.appleye.jsoup;

import com.appleye.util.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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


//        for (int i = 1; i <= 50; i++) {
//            savePage(getPageDocument(getUrl(i)),getFilePath(i));
//            System.out.println("存储完第"+ i +"页");
//        }
//
//        for (int i = 1; i <= 50; i++) {
//            goToDB(readByLine(i));
//            System.out.println("入库完第"+ i +"页");
//
//        }

        readByLine(1);

        //1.磁力、图片、标题

    }

    public static String getFilePath(Integer pageNumber) {
        return String.format("E:\\Program Files\\Files\\Reptile\\Page %s.txt", pageNumber);
    }


    public static String getFilePath(Integer pageNumber, String movieID) {
        String dir = String.format("E:\\Program Files\\Files\\Reptile\\Page %s", pageNumber);
        return String.format("E:\\Program Files\\Files\\Reptile\\Page %s\\%s.txt", pageNumber, movieID);
    }






    public static String getUrl(Integer pageNumber) {
        return mainUrl + "/index.php?page=" + pageNumber;
    }

    public static String getUrl(String movieID) {
        return mainUrl + "/movie.php?id=" + movieID;
    }

    /**
     * 请求url返回Document
     * @param url
     * @return Document
     */
    public static Document getPageDocument(String url) {

        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 按页存储爬取list写文件
     * @param doc
     */
    public static void savePage(Document doc,String filePath){
            Elements ul = doc.getElementsByClass("list");
        try {
            String path = filePath.substring(0, filePath.lastIndexOf("\\"));
            System.out.println(path);
            File file=new File(path);
            if (!file.exists()) {file.mkdirs();}

            //TODO
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
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

                    String url = getUrl(movieID);
                    Document pageDocument = getPageDocument(url);
                    String filePath = getFilePath(pageNumber, movieID);

                    savePage(pageDocument, filePath);


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
