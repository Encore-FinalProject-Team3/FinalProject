package com.encore.AI_Postureoaching.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 리액트 연결 설정
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("api/**").allowCredentials(true).allowedOrigins("http://localhost:3000");
//    }
}
