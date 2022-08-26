package com.SH.Invest_Info_Compilation.controller;

import com.SH.Invest_Info_Compilation.domain.*;
import com.SH.Invest_Info_Compilation.service.CrawlingThumbnailAndProfile;
import com.SH.Invest_Info_Compilation.service.NoticeService;
import com.SH.Invest_Info_Compilation.service.ProcessingYoutubeUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteRestController {

    private final NoticeService noticeService;
    private final CrawlingThumbnailAndProfile crawling;
    private final ProcessingYoutubeUrl processingYoutubeUrl;

    @ResponseBody
    @GetMapping("/notice/write-api")
    public List<String> whos_youtube_api(String url, String type) {
        if (!(url.contains("https://www.youtube.com/") || url.contains("https://youtube.com/")))
            return null;
        List<String> result = null;


        if (type.equals("video")){
            String youtubeUrl = processingYoutubeUrl.toCrawling(url);
            result = crawling.videoToData(youtubeUrl);
        }
        else if (type.equals("channel")){
            result = crawling.channelToData(url);
        }else if (type.equals("shorts")){
            result = crawling.shortsToData(url);
        }


        return result;
    }

    @ResponseBody
    @PostMapping("/notice/delete-api")
    public Boolean delete_api(Long index, String type, HttpSession session){

        if (session.getAttribute("member") == null)
            return null;

        try{

            if (type.equals("video")){
                noticeService.deleteNotice(index, (Member) session.getAttribute("member"));
            }
            else if (type.equals("channel")){
                noticeService.deleteChannel(index, (Member) session.getAttribute("member"));
            }

            return true;
        }catch (Exception e){
            return false;
        }

    }

    @ResponseBody
    @PostMapping("/notice/modify-api")
    public Boolean modify_api(Long index, String type, String description, String noticeType, HttpSession session){

        if (session.getAttribute("member") == null)
            return null;

        try{

            if (type.equals("video")){
                noticeService.modifyNotice(index, (Member) session.getAttribute("member"), description, noticeType);
            }
            else if (type.equals("channel")){
                noticeService.modifyChannel(index, (Member) session.getAttribute("member"), description, noticeType);
            }


            return true;
        }catch (Exception e){
            return false;
        }

    }

    @ResponseBody
    @PostMapping("/notice/subscribe/on")
    public Boolean subscribe_on(Long index, HttpSession session) {
        if (session.getAttribute("member") == null)
            return null;

        if (noticeService.setSubscribe(index, true, (Member) session.getAttribute("member")))
            return true;

        return false;
    }

    @ResponseBody
    @PostMapping("/notice/subscribe/off")
    public Boolean subscribe_off(Long index, HttpSession session) {
        if (session.getAttribute("member") == null)
            return null;

        if (noticeService.setSubscribe(index, false, (Member) session.getAttribute("member")))
            return true;

        return false;
    }

    @ResponseBody
    @PostMapping("/notice/channel/subscribe/on")
    public Boolean subscribe_channel_on(Long index, HttpSession session){
        if (session.getAttribute("member") == null)
            return null;

        if (noticeService.setChannelSubscribe(index, true, (Member) session.getAttribute("member")))
            return true;

        return false;
    }

    @ResponseBody
    @PostMapping("/notice/channel/subscribe/off")
    public Boolean subscribe_channel_off(Long index, HttpSession session){
        if (session.getAttribute("member") == null)
            return null;

        if (noticeService.setChannelSubscribe(index, false, (Member) session.getAttribute("member")))
            return true;

        return false;
    }

    @ResponseBody
    @GetMapping("/notice/video/more")
    public List<NoticeDto> get_video_more(Long index, HttpSession session){

        if (session.getAttribute("member") == null){
            return noticeService.getNoticeDto_Rest(index, null);
        }else{
            return noticeService.getNoticeDto_Rest(index, (Member) session.getAttribute("member"));
        }
    }

    @ResponseBody
    @GetMapping("/notice/channel/more")
    public List<Notice_channelDto> get_channel_more(Long index, HttpSession session){

        if (session.getAttribute("member") == null){
            return noticeService.getChannelDto_Rest(index, null);
        }else{
            return noticeService.getChannelDto_Rest(index, (Member) session.getAttribute("member"));
        }
    }
}
