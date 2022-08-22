package org.example.controller;

import org.example.domain.*;
import org.example.service.imp.*;
import org.example.util.JsoupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    private TestServiceImp testServiceImp;
    @Autowired
    private BookServiceImp bookServiceImp;
    @Autowired
    private ChapterServiceImp chapterServiceImp;
    @Autowired
    private ChapterContentServiceImp chapterContentServiceImp;
    @Autowired
    private AuthorServiceImp authorServiceImp;
    @Autowired
    private JsoupUtil jsoupUtil;
    @RequestMapping("hello")
    @ResponseBody
    public String hello(){
        List<Test> list = testServiceImp.list();
        return "HELLO";
    }
    @RequestMapping("/addBook")
    @ResponseBody
    public Book addBook(){
        //先存储作者
        Author author = new Author();
        author.setName("会说话的肘子");
        author.setDescription("阅文集团白金作家，都市题材代表性作者，被誉为“都市正能量幽默大师”。");
        authorServiceImp.save(author);
        Book book = new Book();
        book.setBookname("夜的命名术");
        book.setAuthorid(author.getId());
        book.setIntroduce("　　蓝与紫的霓虹中，浓密的钢铁苍穹下，数据洪流的前端，是科技革命之后的世界，也是现实与虚幻的分界。\n" +
                "　　钢铁与身体，过去与未来。\n" +
                "　　这里，表世界与里世界并存，面前的一切，像是时间之墙近在眼前。\n" +
                "　　黑暗逐渐笼罩。\n" +
                "　　可你要明白啊我的朋友，我们不能用温柔去应对黑暗，要用火。");
        book.setWordcount(3721600);
        book.setBookstatus("连载");
        book.setBooktype("都市,异术超能");
        bookServiceImp.save(book);
        //将这本书相关的章节目录 单个章节(vip不要) 作者多添加进去 mybatis plus 好像默认使用了useGenerateKey 引用中存在id
        String url = "https://book.qidian.com/info/1021617576/";
        String path = Thread.currentThread().getContextClassLoader().getResource("qidian/yedemingmingshu.html").getPath();
        File file = new File(path);
        Map<String, List<String>> map = jsoupUtil.chapterHandler(file, url);
        List<String> chapterUrl = map.get("chapterUrl");
        List<String> chapter = map.get("chapter");
        try {
            for (int i = 0 ;i<chapterUrl.size();i++){//性能低到爆炸
                Chaptercontent chaptercontent = new Chaptercontent();
                String chapterContent = jsoupUtil.getChapterContent(chapterUrl.get(i));
                chaptercontent.setContent(chapterContent);
                chapterContentServiceImp.save(chaptercontent);
                Chapter tempChapter = new Chapter();
                tempChapter.setName(chapter.get(i));
                tempChapter.setBookid(book.getId());
                tempChapter.setContentid(chaptercontent.getId());
                tempChapter.setChaptertype(chapterUrl.get(i).contains("vip")?"付费":"免费");
                chapterServiceImp.save(tempChapter);
            }
        }catch (Exception e){
            //可能链接超时
            int count = chapterContentServiceImp.count()+1;
            for (int i = count ;i<chapterUrl.size();i++){//性能低到爆炸
                Chaptercontent chaptercontent = new Chaptercontent();
                String chapterContent = jsoupUtil.getChapterContent(chapterUrl.get(i));
                chaptercontent.setContent(chapterContent);
                chapterContentServiceImp.save(chaptercontent);
                Chapter tempChapter = new Chapter();
                tempChapter.setName(chapter.get(i));
                tempChapter.setBookid(book.getId());
                tempChapter.setContentid(chaptercontent.getId());
                tempChapter.setChaptertype(chapterUrl.get(i).contains("vip")?"付费":"免费");
                chapterServiceImp.save(tempChapter);
            }

        }

        //数据量很多 需要用多线程
//        List<String> threadOneUrl = new ArrayList<>();
//        List<String> threadOneChapter = new ArrayList<>();
//
//        List<String> threadTwoUrl = new ArrayList<>();
//        List<String> threadTwoChapter = new ArrayList<>();
//
//        List<String> threadThreeUrl = new ArrayList<>();
//        List<String> threadThreeChapter = new ArrayList<>();
//        int index = 1;
//        for (int i = 0 ;i<chapterUrl.size();i++){
//            if (index == 1){
//                threadOneUrl.add(chapterUrl.get(i));
//                threadOneChapter.add(chapter.get(i));
//            }else if (index == 2){
//                threadTwoUrl.add(chapterUrl.get(i));
//                threadTwoChapter.add(chapter.get(i));
//            }else if (index == 3){
//                threadThreeUrl.add(chapterUrl.get(i));
//                threadThreeChapter.add(chapter.get(i));
//            }
//           if (index == 3){
//               index = 1;
//           }else {
//               ++index;
//           }
//        }
//        new Thread(new BookChapterHandler(book,threadOneUrl,threadOneChapter)).start();
//        new Thread(new BookChapterHandler(book,threadTwoUrl,threadTwoChapter)).start();
//        new Thread(new BookChapterHandler(book,threadThreeUrl,threadThreeChapter)).start();
        return book;
    }
//   private class BookChapterHandler implements Runnable{
//        Book book ;
//        List<String> chapterUrl ;
//        List<String> chapter;
//       @Override
//       public void run() {
//           for (int i = 0 ;i<chapterUrl.size();i++){//性能低到爆炸
//               Chaptercontent chaptercontent = new Chaptercontent();
//               String chapterContent = jsoupUtil.getChapterContent(chapterUrl.get(i));
//               chaptercontent.setContent(chapterContent);
//               chapterContentServiceImp.save(chaptercontent);
//               Chapter tempChapter = new Chapter();
//               tempChapter.setName(chapter.get(i));
//               tempChapter.setBookid(book.getId());
//               tempChapter.setContentid(chaptercontent.getId());
//               tempChapter.setChaptertype(chapterUrl.get(i).contains("vip")?"付费":"免费");
//               chapterServiceImp.save(tempChapter);
//           }
//       }
//       public BookChapterHandler(Book book,List<String> chapterUrl ,List<String> chapter){
//           this.book = book;
//           this.chapterUrl = chapterUrl ;
//           this.chapter = chapter;
//       }
//   }
}
