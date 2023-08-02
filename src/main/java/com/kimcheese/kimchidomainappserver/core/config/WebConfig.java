package com.kimcheese.kimchidomainappserver.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/oauth2/authorize/**")
                .allowedOriginPatterns("http://localhost:3000") // React 애플리케이션의 도메인을 허용해야 합니다.
                .allowedMethods("GET") // 허용할 HTTP 메서드를 지정합니다.
                .allowCredentials(true);
    }
}
