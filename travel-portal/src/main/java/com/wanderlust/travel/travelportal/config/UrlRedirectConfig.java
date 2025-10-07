package com.wanderlust.travel.travelportal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * URL重定向配置
 * 解决前端使用错误URL路径的问题
 * @author wanderlust
 */
@Configuration
public class UrlRedirectConfig implements WebMvcConfigurer {

    /**
     * 配置URL重定向
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 重定向错误的头像上传路径
        registry.addRedirectViewController("/travel-portal/upload/avatar", "/travelManagementSystem/travel-portal/upload/avatar");
        
        // 重定向错误的用户头像路径
        registry.addRedirectViewController("/travel-portal/user/{userId}/avatar", "/travelManagementSystem/travel-portal/user/{userId}/avatar");
        
        // 重定向错误的刷新头像路径
        registry.addRedirectViewController("/travel-portal/user/{userId}/avatar/refresh", "/travelManagementSystem/travel-portal/user/{userId}/avatar/refresh");
    }
}
