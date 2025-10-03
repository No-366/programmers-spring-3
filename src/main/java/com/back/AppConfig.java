package com.back;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { // Bean 을 만드는 옛날 버전 스프링 방식

    // Bean에 등록할 때는 스레드 세이프한지 확인하고 등록해야한다
    @Bean
    public Parser parser(){
        return Parser.builder().build();
    }

    @Bean
    public HtmlRenderer htmlrenderer(){
        return HtmlRenderer.builder().build();
    }


}