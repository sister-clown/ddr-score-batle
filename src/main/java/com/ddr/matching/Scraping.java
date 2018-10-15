package com.ddr.matching;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Scraping {
    static String cookie = "";
    static String targetUrl = "https://p.eagate.573.jp/game/ddr/ddra/p/playdata/music_recent.html";

    public static void main(String args[]) {
        String ua = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";
        String password = "test";
        try {

            Connection.Response res1 = Jsoup.connect(targetUrl).method(Connection.Method.GET).execute();
            Document welcomePage = res1.parse();
            Map welcomCookies = res1.cookies();
            welcomCookies.put("M573SSID", cookie);
            Element inputHidden = welcomePage.getElementById("csrftoken");

            Connection.Response res2 = Jsoup.connect(targetUrl).header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8").cookies(welcomCookies).method(Connection.Method.POST).execute();
            Document document = res2.parse();
            Element body = document.body();
            Elements ddrBodies = body.getElementsByAttributeValue("id", "ddr_body");
            Element ddrBody = ddrBodies.first();
            Elements trest = ddrBody.getElementsByTag("tbody");
            List<Element> nodes = trest.first().children().stream().filter(node -> node.getElementsByTag("tr").size() != 0).map(node -> {
                System.out.println(node);
                return node;
            }).collect(Collectors.toList());
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static org.w3c.dom.Document convertStringToDocument(String carProfileXml) {
        try {
            InputSource inputSource = new InputSource(new StringReader(carProfileXml));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse(inputSource);
            org.w3c.dom.Element element = document.getDocumentElement();
            return document;
        } catch (Exception e) {
            return null;
        }
    }
}
