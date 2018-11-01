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

public class Scraping1 {
    static String targetUrl = "https://p.eagate.573.jp/game/ddr/ddra/p/playdata/music_recent.html";

    public static void main(String args[]) {
        try {
            Connection.Response res2 = Jsoup.connect(targetUrl).header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8").method(Connection.Method.GET).execute();
            Document document = res2.parse();
            System.out.println(document);
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
