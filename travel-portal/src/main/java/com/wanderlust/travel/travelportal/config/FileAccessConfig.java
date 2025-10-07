package com.wanderlust.travel.travelportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件访问配置
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Configuration
public class FileAccessConfig implements WebMvcConfigurer {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String basePath = fileUploadConfig.getBasePath();
        if (!basePath.endsWith("/")) {
            basePath += "/";
        }
        
        System.out.println("配置静态资源访问路径: " + basePath);
        
        // 配置静态资源访问路径 - 支持所有上传文件
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + basePath)
                .setCachePeriod(3600); // 缓存1小时
        
        // 配置resources路径访问
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("file:" + basePath)
                .setCachePeriod(3600);
        
        // 配置files路径访问
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + basePath)
                .setCachePeriod(3600);

        // 配置头像访问路径
        registry.addResourceHandler("/avatars/**")
                .addResourceLocations("file:" + basePath + "avatars/")
                .setCachePeriod(3600);

        // 配置产品图片访问路径
        registry.addResourceHandler("/products/**")
                .addResourceLocations("file:" + basePath + "products/")
                .setCachePeriod(3600);

        // 配置文章图片访问路径
        registry.addResourceHandler("/articles/**")
                .addResourceLocations("file:" + basePath + "articles/")
                .setCachePeriod(3600);

        // 配置资质证明访问路径
        registry.addResourceHandler("/qualifications/**")
                .addResourceLocations("file:" + basePath + "qualifications/")
                .setCachePeriod(3600);
    }
}
