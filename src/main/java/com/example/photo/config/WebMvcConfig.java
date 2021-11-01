package com.example.photo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Value("${images.location}")
    String location;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler 设置访问路径前缀，addResourceLocations 设置资源路径
        registry.addResourceHandler("/images/**").addResourceLocations(location);

    }
}
