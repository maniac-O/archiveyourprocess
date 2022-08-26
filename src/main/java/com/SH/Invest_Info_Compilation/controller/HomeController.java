package com.SH.Invest_Info_Compilation.controller;

import com.SH.Invest_Info_Compilation.domain.Member;
import com.SH.Invest_Info_Compilation.domain.NoticeDto;
import com.SH.Invest_Info_Compilation.domain.NoticeType;
import com.SH.Invest_Info_Compilation.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final NoticeService noticeService;

    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView, HttpSession session) {

        modelAndView.setViewName("home/home");

        if ( session.getAttribute("member") != null){
            List<NoticeDto> noticeDtos = noticeService.getNoticeDto(((Member) session.getAttribute("member")).getId(),false, null);
            modelAndView.addObject("notices", noticeDtos);
        }else {
            List<NoticeDto> notices = noticeService.getAllNotice(0L, null);
            modelAndView.addObject("notices", notices);
        }

        modelAndView = noticeService.addNoticeTypes(modelAndView);


        return modelAndView;
    }
}
