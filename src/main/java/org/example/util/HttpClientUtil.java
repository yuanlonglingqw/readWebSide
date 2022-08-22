package org.example.util;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.HttpEntities;

import java.io.IOException;

public class HttpClientUtil {
    public static void main(String[] args) {
        String url = "https://book.qidian.com/info/1021617576/";
        String result = HttpClientUtil.doGet(url);
        System.out.println();
    }
    //因为jdk自带的请求类 HttpURLConnection 和麻烦  需要自己获取连接 自己组装数据 自己关闭流管道
    //HttpClient别人写好的请求类 拿来用
    public static String doGet(String url) {

        //这个类就是httpClient提供的  他封装了原生HttpURLConnection 不用自己做一些处理
        CloseableHttpClient closeableHttpClient = null;
        CloseableHttpResponse execute = null;
        closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String result = null;//相当于 原生请求中的URL对象
        //还需要设置一些请求头信息 和 连接信息
        try {
            execute = closeableHttpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            result = EntityUtils.toString(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
