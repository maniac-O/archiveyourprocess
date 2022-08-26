package com.SH.Invest_Info_Compilation.service;

import org.springframework.stereotype.Component;

@Component
public class ProcessingYoutubeUrl {

    public String toCrawling(String primitive){
        String serial = parseSerial(primitive);

        return "https://www.youtube.com/watch?v="+serial;
    }

    public String toDB(String primitive){
        String serial = parseSerial(primitive);

        return "https://www.youtube.com/embed/"+serial;
    }

    protected String parseSerial(String primitive){
        int v = primitive.indexOf("v=");
        return primitive.substring(v + 2, v + 13);
    }

    public String shortsToDB(String primitive){
        String serial = shortToParseSerial(primitive);

        return "https://www.youtube.com/embed/"+serial;
    }

    protected String shortToParseSerial(String primitive){
        int v = primitive.indexOf("/shorts/");
        return primitive.substring(v + 8, v + 19);
    }
}
