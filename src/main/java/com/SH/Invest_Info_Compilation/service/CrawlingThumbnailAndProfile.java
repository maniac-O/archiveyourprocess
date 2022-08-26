package com.SH.Invest_Info_Compilation.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CrawlingThumbnailAndProfile {
    public List<String> videoToData(String url){

        try{
            Document document = Jsoup.connect(url)
                    .timeout(3000)
                    .method(Connection.Method.GET)
                    .get();

            ArrayList<String> result = new ArrayList<>();
            String html = document.html();

            /* start img parsing */
            int i_left = html.indexOf("{\"owner\":{\"videoOwnerRenderer\":{\"thumbnail\":{\"thumbnails\":[{\"url\":\"");
            String tmp = html.substring(i_left + 67, i_left + 250);
            int i_right = tmp.indexOf("\"");
            result.add(tmp.substring(0, i_right));
            /* end img parsing */


            /* start channel name parsing */
            int j_left = html.indexOf("<link itemprop=\"name\" content=\"");
            String substring = html.substring(j_left+31, j_left+51);
            int j_right = substring.indexOf("\"");
            result.add(substring.substring(0, j_right));
            /* end channel name parsing */

            return result;


        }catch (Exception e){
            log.warn("Error during crawling(video) : " + e);
        }

        return null;
    }

    public List<String> channelToData(String url){

        try{
            Document document = Jsoup.connect(url)
                    .timeout(3000)
                    .method(Connection.Method.GET)
                    .get();

            ArrayList<String> result = new ArrayList<>();
            String html = document.html();

            int i1 = html.indexOf("\"width\":48,\"height\":48},{\"url\":\"");
            String substringI = html.substring(i1 + 32, i1 + 200);
            int i2 = substringI.indexOf("\"");
            result.add(substringI.substring(0, i2));

            int j1 = html.indexOf("<link itemprop=\"name\" content=\"");
            String substringJ = html.substring(j1+31, j1+200);
            int j2 = substringJ.indexOf("\"");
            result.add(substringJ.substring(0, j2));

            return result;


        }catch (Exception e){
            log.warn("Error during crawling(channel) : " + e);
        }

        return null;
    }

    public List<String> shortsToData(String url){

        try{
            Document document = Jsoup.connect(url)
                    .timeout(3000)
                    .method(Connection.Method.GET)
                    .get();

            ArrayList<String> result = new ArrayList<>();
            String html = document.html();

            // 썸네일
            int i1 = html.indexOf("\"channelThumbnail\":{\"thumbnails\":[{\"url\":\"");
            String substringI = html.substring(i1 + 42, i1+200);
            int i2 = substringI.indexOf("\"");
            result.add(substringI.substring(0, i2));


            // 채널명
            int j1 = html.indexOf("\"author\":\"");
            String substringJ = html.substring(j1 + 10, j1+100);
            int j2 = substringJ.indexOf("\"");
            result.add(substringJ.substring(0, j2));

            return result;


        }catch (Exception e){
            log.warn("Error during crawling(shorts) : " + e);
        }

        return null;
    }
}
