package com.appleye.jsoup;

import com.appleye.util.Constants;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Appleye
 * @created 2020-10-18 10:46
 */
public class Reptile {

//    private static final Logger LOGGER = LoggerFactory.getLogger(Reptile.class);

    static String mainUrl = "https://www.ac63.xyz";
    static String mainUrl2 = "https://www.ac59.xyz";



    public static void main(String[] args) throws IOException {


//        try {
//            for (int i = 1; i <= 50; i++) {
//                savePage(getPageDocument(getUrl(i)),getFilePath(i));
//                System.out.println("存储完第"+ i +"页");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        for (int i = 1; i <= 50; i++) {
//            goToDB(readByLine(i));
//            System.out.println("入库完第"+ i +"页");
//        }



        String sql = "select movie_id from movie where publish_date is null or magnet is null or picture_paths is null order by insert_time limit 1";
        String movieID = null;
        int count = 0;
        for (;;) {
            movieID = getMovieID(sql);
            if (movieID == null) {
                System.out.println("结束！");break;}
            try {
                completeMovie(movieID);
            } catch (Exception e) {
                continue;
            }
            System.out.println(count++);
        }








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

    public static String getUpdateSql(Movie movie){

        StringBuffer sb = new StringBuffer();
        String[] picturePaths = movie.getPicturePaths();
        for (int i = 0; i < picturePaths.length; i++) {
            if (i != 0){sb.append("|");}
            sb.append(picturePaths[i]);
        }
        String updateSql = String.format("update movie set publish_date = '%s', magnet = '%s', picture_paths = '%s' where movie_id = '%s'"
                , movie.getPublishDate(), movie.getMagnet(), sb, movie.getMovieID());
        System.out.println(updateSql);
        return updateSql;

    }

    /**
     * 请求url返回Document
     * @param url
     * @return Document
     */
    public static Document getPageDocument(String url) throws Exception{
            return Jsoup.connect(url).get();
    }

    /**
     * 按页存储爬取list写文件
     * @param doc
     */
    public static void savePage(Document doc,String filePath){
            Elements ul = doc.getElementsByClass("list");
        try {
//            String path = filePath.substring(0, filePath.lastIndexOf("\\"));
//            System.out.println(path);
//            File file=new File(path);
//            if (!file.exists()) {file.mkdirs();}
//            //TODO

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
                    //movieID
                    String movieID = line.substring(line.indexOf("id=") + 3, line.indexOf("\" ta"));

                    //title
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
            save(sql);
        }
    }

    /**
     * 存储
     * @param sql
     */
    public static void save(String sql){

        try( Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/dsbt?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT"
                ,Constants.USERNAME.getContext()
                ,Constants.PASSWORD.getContext()
                );
             PreparedStatement ps = conn.prepareStatement(sql);
             ) {
            boolean execute = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取要完善信息（发布日期、磁力、图片）的movieID
     * @param sql
     * @return
     */
    public static String getMovieID(String sql) {
        try(Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/dsbt?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT"
                ,Constants.USERNAME.getContext()
                ,Constants.PASSWORD.getContext());
        ) {
            Statement ps = conn.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);

            resultSet.first();
            String movie_id = resultSet.getString("movie_id");
            return movie_id;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 完善movie信息
     * @param movieID
     */
    public static void completeMovie(String movieID) throws Exception{
        Document pageDocument = getPageDocument(getUrl(movieID));
        String publishDate = getPublishDate(pageDocument);
        String magnet = getMagnet(pageDocument);
        String[] picturesPath = getPicturePaths(pageDocument);
        Movie movie = new Movie(movieID, publishDate, magnet, picturesPath);

        String updateSql = getUpdateSql(movie);
        update(updateSql);

    }

    /**、
     * 执行update
     * @param sql
     */
    public static void update(String sql){
        try(Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/dsbt?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT"
                ,Constants.USERNAME.getContext()
                ,Constants.PASSWORD.getContext());
        ) {
            Statement ps = conn.createStatement();
            ps.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    /**
     * 获取发布时间
     * @param pageDocument
     * @return publishDate
     */
    public static String getPublishDate(Document pageDocument) {
        String text = pageDocument.select("p").first().text();
        //2020-12-25
        String publishDate = text.substring(text.indexOf("：") + 1, text.length());
        return publishDate;
    }

    /**
     * 获取磁力链接
     * @param pageDocument
     * @return
     */
    public static String getMagnet(Document pageDocument) {
        Pattern compile = Pattern.compile("\\<!--(.+)--\\>");
        Matcher matcher = compile.matcher(pageDocument.toString());
        while(matcher.find()) {
            if (matcher.group().contains("magnet")){
                String group = matcher.group();
                String magnet = group.substring(group.indexOf("href=\"") + 6, group.indexOf("\" tar"));
                return magnet;
            }
        }
        return "获取链接失败";
    }

    /**
     * 获取图片路径
     * @param pageDocument
     * @return
     */
    public static String[] getPicturePaths(Document pageDocument) {
        Elements pictures = pageDocument.select("div.capture");
        Elements img = pictures.select("img");
        String[] picturePaths = new String[img.size()];
        int index = 0;
        for (Element e: img) {
            picturePaths[index] = e.attr("src");
            index++;
        }
        return picturePaths;
    }




}
