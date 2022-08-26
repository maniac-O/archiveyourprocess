package com.SH.Invest_Info_Compilation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SettingRestController {

    @PostMapping("/setting/theme")
    public Boolean settingTheme(String theme, HttpSession session){
        session.setAttribute("theme", theme);

        return true;
    }
}
