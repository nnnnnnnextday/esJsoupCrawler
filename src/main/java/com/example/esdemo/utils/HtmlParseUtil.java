package com.example.esdemo.utils;

import com.example.esdemo.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtil {
//    放弃main方法，直接@Com丢进Spring,然后@AutoWire注入即可
//    public static void main(String[] args) throws IOException {
//        new HtmlParseUtil().parseJD("python").forEach(System.out::println);
//    }

    public List<Content> parseJD(String keywords) throws IOException {
        //获取请求
        //ajax资源不能获取，需要模拟浏览器
        //String url = "https://search.jd.com/Search?keyword=java";
        String url = "https://search.jd.com/Search?keyword="+keywords;
        //解析网页，使用原生api即可
        //jsoup返回的document对象，就是返回的浏览器页面。那么JS中可以使用的方法，都可以使用
        Document document = Jsoup.parse(new URL(url),300000);
        //System.out.println(document);
        Element element = document.getElementById("J_goodsList");
        System.out.println(element);

        //根据网页分析，获取所有li元素，方便进一步处理
        Elements elements = element.getElementsByTag("li");
        ArrayList<Content> goodList = new ArrayList<>();

        //获取元素中的内容
        for(Element el:elements){
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            Content content = new Content();
            content.setTitle(title);
            content.setPrice(price);
            content.setImg(img);
            goodList.add(content);
        }
        return goodList;
    }
}
