package com.example.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name())
                        .allowedOrigins("*")
                        .allowedHeaders(HttpHeaders.CONTENT_TYPE);
            }
        };
    }
}
