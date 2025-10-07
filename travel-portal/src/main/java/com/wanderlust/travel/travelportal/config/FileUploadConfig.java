package com.wanderlust.travel.travelportal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传配置
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Configuration
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadConfig {

    /**
     * 文件上传根路径
     */
    private String basePath = "/uploads";

    /**
     * 文件访问URL前缀
     */
    private String urlPrefix = "https://example.com";

    /**
     * 头像上传配置
     */
    private AvatarConfig avatar = new AvatarConfig();

    /**
     * 资质证明上传配置
     */
    private QualificationConfig qualification = new QualificationConfig();

    /**
     * 产品图片上传配置
     */
    private ProductConfig product = new ProductConfig();

    /**
     * 文章图片上传配置
     */
    private ArticleConfig article = new ArticleConfig();

    public static class AvatarConfig {
        private String path = "/avatars";
        private long maxSize = 2 * 1024 * 1024; // 2MB
        private String[] allowedTypes = {"image/jpeg", "image/png", "image/gif"};

        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public long getMaxSize() { return maxSize; }
        public void setMaxSize(long maxSize) { this.maxSize = maxSize; }
        public String[] getAllowedTypes() { return allowedTypes; }
        public void setAllowedTypes(String[] allowedTypes) { this.allowedTypes = allowedTypes; }
    }

    public static class QualificationConfig {
        private String path = "/qualifications";
        private long maxSize = 10 * 1024 * 1024; // 10MB
        private String[] allowedTypes = {"application/pdf", "image/jpeg", "image/png"};

        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public long getMaxSize() { return maxSize; }
        public void setMaxSize(long maxSize) { this.maxSize = maxSize; }
        public String[] getAllowedTypes() { return allowedTypes; }
        public void setAllowedTypes(String[] allowedTypes) { this.allowedTypes = allowedTypes; }
    }

    public static class ProductConfig {
        private String path = "/products";
        private long maxSize = 5 * 1024 * 1024; // 5MB
        private String[] allowedTypes = {"image/jpeg", "image/png", "image/gif"};

        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public long getMaxSize() { return maxSize; }
        public void setMaxSize(long maxSize) { this.maxSize = maxSize; }
        public String[] getAllowedTypes() { return allowedTypes; }
        public void setAllowedTypes(String[] allowedTypes) { this.allowedTypes = allowedTypes; }
    }

    public static class ArticleConfig {
        private String path = "/articles";
        private long maxSize = 5 * 1024 * 1024; // 5MB
        private String[] allowedTypes = {"image/jpeg", "image/png", "image/gif"};

        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public long getMaxSize() { return maxSize; }
        public void setMaxSize(long maxSize) { this.maxSize = maxSize; }
        public String[] getAllowedTypes() { return allowedTypes; }
        public void setAllowedTypes(String[] allowedTypes) { this.allowedTypes = allowedTypes; }
    }

    // Getters and Setters
    public String getBasePath() { return basePath; }
    public void setBasePath(String basePath) { this.basePath = basePath; }

    public String getUrlPrefix() { return urlPrefix; }
    public void setUrlPrefix(String urlPrefix) { this.urlPrefix = urlPrefix; }

    public AvatarConfig getAvatar() { return avatar; }
    public void setAvatar(AvatarConfig avatar) { this.avatar = avatar; }

    public QualificationConfig getQualification() { return qualification; }
    public void setQualification(QualificationConfig qualification) { this.qualification = qualification; }

    public ProductConfig getProduct() { return product; }
    public void setProduct(ProductConfig product) { this.product = product; }

    public ArticleConfig getArticle() { return article; }
    public void setArticle(ArticleConfig article) { this.article = article; }
}
