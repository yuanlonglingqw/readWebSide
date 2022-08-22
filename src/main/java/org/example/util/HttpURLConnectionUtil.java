package org.example.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpURLConnectionUtil {
    //首先 这是jdk 提供我们模拟浏览器发送请求的工具类  请求的数据可以在java端得到展示
    public static void main(String[] args) {
        String urlStr = "https://book.qidian.com/info/1021617576/";
        String resource = requestResource(urlStr);
        System.out.println();
    }

    public static String requestResource(String urlStr) {
        HttpURLConnection httpURLConnection = null;
        StringBuilder stringBuilder = new StringBuilder();
        URL url=null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            url = new URL(urlStr);//这个类就可以发送请求
            //打开一个连接
            httpURLConnection = (HttpURLConnection)url.openConnection();
            //但是发送请求之前需要做一些设置 比如请求的超时处理  请求响应的格式处理等
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(15000);//设置连接服务端的时间 超过就报错
            httpURLConnection.setReadTimeout(60000);//设置获取数据的时间
            httpURLConnection.setRequestProperty("Accept","application/json");
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200){
                //表示请求到了数据 响应过来的东西在流中
                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String str = null;
                while ((str = bufferedReader.readLine()).length()!=0 || str!=null){
                    stringBuilder.append(str);
                }
            }else {
                System.out.println("response code is not 200 is "+responseCode);
            }

        } catch (Exception e) {
        }finally {
            try {
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
                if (inputStream!=null){
                    inputStream.close();
                }
                httpURLConnection.disconnect();
            } catch (Exception e) {
            }

        }
        return stringBuilder.toString();
    }
}
