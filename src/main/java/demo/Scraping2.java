package demo;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Scraping2 {
    static String cookie = "";
    static String targetUrl = "https://p.eagate.573.jp/game/ddr/ddra/p/playdata/music_recent.html";

    public static void main(String args[]) {
        try {
            Map<String, String> cookies = new TreeMap<>();
            cookies.put("M573SSID", cookie);
            Connection.Response res2 = Jsoup.connect(targetUrl).header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8").cookies(cookies).method(Connection.Method.GET).execute();
            Document document = res2.parse();
            System.out.println(document);
            Element body = document.body();
            Elements tables = body.getElementsByTag("tbody").first().children();
            tables.remove(0);
            List<String> info = tables.stream().map(node -> {
                Elements children = node.children();
                String musicTitle = children.get(0).text();
                String diff = children.get(1).text();
                String score = children.get(3).text();
                return musicTitle + "(" + diff + ") : " +  score;
            }).collect(Collectors.toList());
            info.forEach(System.out::println);
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
