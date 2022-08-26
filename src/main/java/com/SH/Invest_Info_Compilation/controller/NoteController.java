package com.SH.Invest_Info_Compilation.controller;

import com.SH.Invest_Info_Compilation.domain.*;
import com.SH.Invest_Info_Compilation.service.CrawlingThumbnailAndProfile;
import com.SH.Invest_Info_Compilation.service.NoticeService;
import com.google.api.Http;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoteController {

    private final NoticeService noticeService;

    @GetMapping("/note_write")
    public ModelAndView note_write(ModelAndView modelAndView) {
        modelAndView.setViewName("note/note_write_form");
        modelAndView.addObject("notice", new Notice());
        modelAndView.addObject("notice_channel", new Notice_channel());

        modelAndView = noticeService.addNoticeTypes(modelAndView);

        return modelAndView;
    }

    @PostMapping("/notice/write")
    public String post_note_write(Notice notice, Notice_channel notice_channel, HttpSession session) {

        try {
            String result = noticeService.setNotice(notice, notice_channel, (Member) session.getAttribute("member"));
            if(result.equals("channel"))
                return "redirect:/notice/channel";
            else
                return "redirect:/";
        } catch (Exception e) {
            log.warn("Error in Notice Writing : " + e);
            return "redirect:/";
        }
    }

    @GetMapping("/notice/channel")
    public ModelAndView channel(ModelAndView modelAndView, HttpSession session) {
        modelAndView.setViewName("note/note_channel");

        if (session.getAttribute("member") != null) {
            modelAndView.addObject("channels", noticeService.getSubscribeChannel(0L, (Member) session.getAttribute("member")));
        } else {
            modelAndView.addObject("channels", noticeService.getChannelDto(0L, false, null));
        }
        modelAndView = noticeService.addNoticeTypes(modelAndView);

        return modelAndView;
    }

    @GetMapping("/notice/bookmark")
    public ModelAndView bookmark(ModelAndView modelAndView, HttpSession session) {
        modelAndView.setViewName("note/note_bookmark");

        List<NoticeDto> findSubscribe = noticeService.getSubscribeNotice((Member) session.getAttribute("member"));
        modelAndView.addObject("notices", findSubscribe);
        modelAndView = noticeService.addNoticeTypes(modelAndView);

        return modelAndView;
    }

    @GetMapping("/notice/bookmark/channel")
    public ModelAndView bookmark_channel(ModelAndView modelAndView, HttpSession session) {
        modelAndView.setViewName("note/note_bookmark_channel");

        List<Notice_channelDto> findChannels = noticeService.getChannelSubscribe((Member) session.getAttribute("member"));
        modelAndView.addObject("channels", findChannels);
        modelAndView = noticeService.addNoticeTypes(modelAndView);

        return modelAndView;
    }

    @GetMapping("/notice/my_notice")
    public ModelAndView my_notice(ModelAndView modelAndView, HttpSession session){

        if(session.getAttribute("member") == null)
            return null;

        modelAndView.setViewName("note/my_notice");
        try{
            List<NoticeDto> noticeDtos = noticeService.getNoticeDto(((Member) session.getAttribute("member")).getId(), true, null);
            modelAndView.addObject("notices", noticeDtos);

        }catch (Exception e){
            log.warn("Error in get My Notice");
        }

        return modelAndView;
    }

    @GetMapping("/notice/my_channel")
    public ModelAndView my_channel(ModelAndView modelAndView, HttpSession session){

        if(session.getAttribute("member") == null)
            return null;

        modelAndView.setViewName("note/my_channel");
        try{
            List<Notice_channelDto> notice_channelDtos = noticeService.getChannelDto(((Member) session.getAttribute("member")).getId(), true, null);
            modelAndView.addObject("channels", notice_channelDtos);

        }catch (Exception e){
            log.warn("Error in get My Channel");
        }

        return modelAndView;
    }

    @GetMapping("/notice/name")
    public ModelAndView notice_name(@RequestParam(name="id") Long id, ModelAndView modelAndView, HttpSession session){
        modelAndView.setViewName("home/home");

        modelAndView.addObject("notices", noticeService.getNoticeDto(id, true, null));
        modelAndView = noticeService.addNoticeTypes(modelAndView);

        return modelAndView;
    }

    @GetMapping("/notice/type")
    public ModelAndView notice_noticeType(@RequestParam(name = "noticeType") String noticeType, ModelAndView modelAndView, HttpSession session){
        modelAndView.setViewName("home/home");

        if ( session.getAttribute("member") != null){
            modelAndView.addObject("notices", noticeService.getNoticeDto(((Member) session.getAttribute("member")).getId(), true, noticeType));
        } else {
            modelAndView.addObject("notices", noticeService.getNoticeDto(0L, true, noticeType));
        }
        modelAndView = noticeService.addNoticeTypes(modelAndView);

        return modelAndView;
    }

    @GetMapping("/channel/name")
    public ModelAndView channel_name(@RequestParam(name="id") Long id, ModelAndView modelAndView){
        modelAndView.setViewName("note/note_channel");

        modelAndView.addObject("channels", noticeService.getChannelDto(id, true, null));
        modelAndView = noticeService.addNoticeTypes(modelAndView);

        return modelAndView;
    }

    @GetMapping("/channel/type")
    public ModelAndView channel_noticeType(@RequestParam(name = "noticeType") String noticeType, ModelAndView modelAndView, HttpSession session){
        modelAndView.setViewName("note/note_channel");

        if ( session.getAttribute("member") != null){
            modelAndView.addObject("channels", noticeService.getChannelDto(((Member) session.getAttribute("member")).getId(), true, noticeType));
        } else {
            modelAndView.addObject("channels", noticeService.getChannelDto(0L, true, noticeType));
        }
        modelAndView = noticeService.addNoticeTypes(modelAndView);

        return modelAndView;
    }
}
