package com.appleye.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * @author Appleye
 * @createTime 2020-08-18 16:46
 */
public class HttpClient {
    public static void main(String[] args) throws IOException {
        //1.打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();


        //2.声明get请求
        HttpGet httpGet = new HttpGet("http://47.95.9.21:8080/brand/listAll");
        httpGet.setHeader("Accept","application/json");
        httpGet.setHeader("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE2MDEyNjE0NzQwMjMsImV4cCI6MTYwMTg2NjI3NH0.LcRV3GEDGZ0wtYebOXpva6-a_D9ecmfLmSQ44_TpqtsoI9B9jUCFy80Y7PlbfF0hqtVxEIGViFlkYxDQ3WF4oQ");

        //3.发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //4.判断状态码
        int successCode = 200;
        if (response.getStatusLine().getStatusCode() == successCode) {
            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity, "utf-8");
            System.out.println(string);
        }

        //5.关闭资源
        response.close();
        httpClient.close();


    }
}
