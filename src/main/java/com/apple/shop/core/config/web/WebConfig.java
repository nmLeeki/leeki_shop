// src/main/java/com/apple/shop/core/config/web/WebConfig.java
package com.apple.shop.core.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /assets/** → classpath:/static/dist/assets/
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static/dist/assets/")
                .setCachePeriod(3600);

        // 만약 JS/CSS/SVG 파일들이 dist 루트에 섞여 있으면
        registry.addResourceHandler("/*.js","/*.css","/*.svg")
                .addResourceLocations("classpath:/static/dist/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/dist/index.html");
        // ViewController 에서 /assets/** 을 제외
        registry.addViewController("/{path:^(?!api|assets|static|dist)[^\\.]*}")
                .setViewName("forward:/dist/index.html");
        registry.addViewController("/**/{path:^(?!api|assets|static|dist)[^\\.]*}")
                .setViewName("forward:/dist/index.html");
    }
}