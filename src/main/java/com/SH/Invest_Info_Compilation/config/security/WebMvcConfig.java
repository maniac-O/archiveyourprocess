package com.SH.Invest_Info_Compilation.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthHandlerInterceptor())
                //.addPathPatterns("/**")
                .excludePathPatterns("/","/notice/channel","/notice/video/more","/notice/channel/more",
                        "/notice/name","/notice/type","/channel/name","/channel/type",
                        "/css/**","/js/**","/*.ico","/imgs/**","/member/sign_on",
                        "/setting/**");
    }
}
