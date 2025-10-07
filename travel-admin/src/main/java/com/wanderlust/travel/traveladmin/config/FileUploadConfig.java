package com.wanderlust.travel.traveladmin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 文件上传配置类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadConfig {

    /**
     * 文件上传根路径
     */
    private String basePath;

    /**
     * 文件访问URL前缀
     */
    private String urlPrefix;

    /**
     * 头像上传配置
     */
    private UploadTypeConfig avatar;

    /**
     * 资质证明上传配置
     */
    private UploadTypeConfig qualification;

    /**
     * 产品图片上传配置
     */
    private UploadTypeConfig product;

    /**
     * 文章图片上传配置
     */
    private UploadTypeConfig article;

    @Data
    public static class UploadTypeConfig {
        /**
         * 上传路径
         */
        private String path;

        /**
         * 最大文件大小（字节）
         */
        private Long maxSize;

        /**
         * 允许的文件类型
         */
        private List<String> allowedTypes;
    }
}
