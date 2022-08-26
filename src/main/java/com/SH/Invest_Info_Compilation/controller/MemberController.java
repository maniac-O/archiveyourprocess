package com.SH.Invest_Info_Compilation.controller;

import com.SH.Invest_Info_Compilation.domain.Member;
import com.SH.Invest_Info_Compilation.domain.Role;
import com.SH.Invest_Info_Compilation.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/sign_on")
    public ModelAndView sign_on(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("member/sign_on");
        modelAndView.addObject("member", new Member());
        modelAndView.addObject("prevUrl", request.getHeader("Referer"));

        return modelAndView;
    }

    @GetMapping("/member/sign_out")
    public ModelAndView sign_out(ModelAndView modelAndView, HttpServletRequest request, HttpSession session) {
        modelAndView.setViewName("member/sign_out");
        modelAndView.addObject("prevUrl", request.getHeader("Referer"));

        if(session.getAttribute("member") != null)
            session.setAttribute("member", null);


        return modelAndView;
    }

    @PostMapping("/member/sign_on")
    public String sign_on_try(Member member, String prevUrl, HttpSession session) {

        System.out.println("prevUrl = " + prevUrl);

        // 만약 회원가입이 아니라면 로그인 정보 검증 후 세션에 저장

        member.setRole(Role.ANONY);
        Member validMember = memberService.validMember(member);

        if( validMember != null)
            session.setAttribute("member", validMember);

        return "redirect:"+prevUrl;
    }
}
