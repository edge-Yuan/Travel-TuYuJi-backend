package com.wanderlust.travel.travelportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件上传初始化器
 * 在应用启动时创建必要的目录
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Component
public class FileUploadInitializer implements ApplicationRunner {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initializeUploadDirectories();
    }

    /**
     * 初始化上传目录
     */
    private void initializeUploadDirectories() {
        String basePath = getWritableBasePath();
        
        try {
            // 创建基础目录
            createDirectoryIfNotExists(basePath);
            
            // 创建各类型目录
            createDirectoryIfNotExists(basePath + fileUploadConfig.getAvatar().getPath());
            createDirectoryIfNotExists(basePath + fileUploadConfig.getQualification().getPath());
            createDirectoryIfNotExists(basePath + fileUploadConfig.getProduct().getPath());
            createDirectoryIfNotExists(basePath + fileUploadConfig.getArticle().getPath());
            
            System.out.println("文件上传目录初始化完成: " + basePath);
        } catch (Exception e) {
            System.err.println("文件上传目录初始化失败: " + e.getMessage());
        }
    }

    /**
     * 获取可写的基础路径
     */
    private String getWritableBasePath() {
        // 尝试多个可能的路径
        String[] possiblePaths = {
            fileUploadConfig.getBasePath(),
            System.getProperty("java.io.tmpdir") + "/uploads",
            System.getProperty("user.home") + "/uploads",
            "./uploads"
        };
        
        for (String possiblePath : possiblePaths) {
            try {
                Path path = Paths.get(possiblePath);
                Files.createDirectories(path);
                // 测试写入权限
                Path testFile = path.resolve("test_write.tmp");
                Files.write(testFile, "test".getBytes());
                Files.deleteIfExists(testFile);
                return possiblePath;
            } catch (Exception e) {
                // 继续尝试下一个路径
                continue;
            }
        }
        
        // 如果所有路径都失败，使用临时目录
        String fallbackPath = System.getProperty("java.io.tmpdir") + "/uploads";
        try {
            Files.createDirectories(Paths.get(fallbackPath));
            return fallbackPath;
        } catch (Exception e) {
            throw new RuntimeException("无法创建任何上传目录", e);
        }
    }

    /**
     * 创建目录（如果不存在）
     */
    private void createDirectoryIfNotExists(String path) throws Exception {
        Path dirPath = Paths.get(path);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
            System.out.println("创建目录: " + path);
        }
    }
}
