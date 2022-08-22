package org.example.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class JsoupUtil {
//    public static void main(String[] args) {
//        String url = "https://book.qidian.com/info/1021617576/";
//        String path = Thread.currentThread().getContextClassLoader().getResource("qidian/yedemingmingshu.html").getPath();
//        File file = new File(path);
//        Map<String,List<String>> map = chapterHandler(file,url);
//        List<String> chapterUrl = map.get("chapterUrl");
//        List<String> chapter = map.get("chapter");
//        List<String> text = new ArrayList<>();
//        for (int i = 0;i<chapterUrl.size();i++){
//            text.add(getChapterContent(chapterUrl.get(i)));
//        }
//
//    }
    public  Map<String,List<String>> chapterHandler(File file,String originUrl) {
        List<String> chapterUrlList = new ArrayList<>();
        List<String> chapterList = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        try {

            Document document = Jsoup.parse(file,"UTF-8",originUrl);
//            //夜的命名术(会说话的肘子)最新章节在线阅读-起点中文网官方正版
//            String title = document.title();
            Elements elements = document.select("#j-catalogWrap .volume-wrap .volume").select(".cf li .book_name a");
            elements.remove(elements.size()-1);
            //处理免费章节 的链接

            for (Element element :elements){
//                System.out.println(element.text()+"的链接为："+element.attr("href"));
                String href = element.attr("href");
                String chapter = element.text();
                chapterList.add(chapter);
                chapterUrlList.add("https:"+href);
            }
            map.put("chapterUrl",chapterUrlList);
            map.put("chapter",chapterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
    //暂时不知道如何封装 用到再说
    public String getChapterContent(String url){
        String result = null;
        try {
            Document document = Jsoup.connect(url).timeout(1000+60*5).get();
            result = document.select(".read-content").first().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
