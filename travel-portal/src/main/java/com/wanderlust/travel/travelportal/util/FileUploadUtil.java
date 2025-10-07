package com.wanderlust.travel.travelportal.util;

import com.wanderlust.travel.travelportal.config.FileUploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 文件上传工具类
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Component
public class FileUploadUtil {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    /**
     * 生成文件存储路径
     */
    public String generateFilePath(String uploadType, String originalFilename) {
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String extension = getFileExtension(originalFilename);
        String fileName = generateFileName(uploadType, extension);
        
        // 生成相对路径，不包含basePath
        return getTypePath(uploadType) + "/" + datePath + "/" + fileName;
    }

    /**
     * 生成文件访问URL
     */
    public String generateFileUrl(String filePath) {
        // 确保filePath以/开头
        if (!filePath.startsWith("/")) {
            filePath = "/" + filePath;
        }
        return fileUploadConfig.getUrlPrefix() + filePath;
    }

    /**
     * 保存文件到本地
     */
    public String saveFile(MultipartFile file, String uploadType) throws IOException {
        // 验证文件
        validateFile(file, uploadType);
        
        // 生成文件路径
        String filePath = generateFilePath(uploadType, file.getOriginalFilename());
        String fullPath = getWritablePath(filePath);
        
        // 创建目录
        Path path = Paths.get(fullPath);
        Files.createDirectories(path.getParent());
        
        // 保存文件
        file.transferTo(path.toFile());
        
        // 返回相对路径，用于URL访问
        return "/uploads/" + filePath;
    }

    /**
     * 获取可写的文件路径
     */
    private String getWritablePath(String filePath) throws IOException {
        // 获取可写的基础路径
        String basePath = getWritableBasePath();
        String fullPath = basePath + filePath;
        
        // 创建目录
        Path path = Paths.get(fullPath);
        Files.createDirectories(path.getParent());
        
        return fullPath;
    }

    /**
     * 获取可写的基础路径
     */
    private String getWritableBasePath() throws IOException {
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
        
        throw new IOException("无法找到可写的文件存储路径");
    }

    /**
     * 验证文件
     */
    public void validateFile(MultipartFile file, String uploadType) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空");
        }

        // 检查文件大小
        long maxSize = getMaxSize(uploadType);
        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("文件大小不能超过 " + (maxSize / 1024 / 1024) + "MB");
        }

        // 检查文件类型
        String[] allowedTypes = getAllowedTypes(uploadType);
        String contentType = file.getContentType();
        boolean isAllowed = false;
        for (String allowedType : allowedTypes) {
            if (allowedType.equals(contentType)) {
                isAllowed = true;
                break;
            }
        }
        if (!isAllowed) {
            throw new IllegalArgumentException("不支持的文件类型: " + contentType);
        }
    }

    /**
     * 删除文件
     */
    public boolean deleteFile(String filePath) {
        try {
            File file = new File(fileUploadConfig.getBasePath() + filePath);
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取文件扩展名
     */
    private String getFileExtension(String filename) {
        if (filename == null || filename.lastIndexOf(".") == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * 生成文件名
     */
    private String generateFileName(String uploadType, String extension) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return uploadType + "_" + timestamp + "_" + uuid + extension;
    }

    /**
     * 获取类型路径
     */
    private String getTypePath(String uploadType) {
        switch (uploadType) {
            case "avatar":
                return fileUploadConfig.getAvatar().getPath();
            case "qualification":
                return fileUploadConfig.getQualification().getPath();
            case "product":
                return fileUploadConfig.getProduct().getPath();
            case "article":
                return fileUploadConfig.getArticle().getPath();
            default:
                return "/others";
        }
    }

    /**
     * 获取最大文件大小
     */
    private long getMaxSize(String uploadType) {
        switch (uploadType) {
            case "avatar":
                return fileUploadConfig.getAvatar().getMaxSize();
            case "qualification":
                return fileUploadConfig.getQualification().getMaxSize();
            case "product":
                return fileUploadConfig.getProduct().getMaxSize();
            case "article":
                return fileUploadConfig.getArticle().getMaxSize();
            default:
                return 5 * 1024 * 1024; // 5MB
        }
    }

    /**
     * 获取允许的文件类型
     */
    private String[] getAllowedTypes(String uploadType) {
        switch (uploadType) {
            case "avatar":
                return fileUploadConfig.getAvatar().getAllowedTypes();
            case "qualification":
                return fileUploadConfig.getQualification().getAllowedTypes();
            case "product":
                return fileUploadConfig.getProduct().getAllowedTypes();
            case "article":
                return fileUploadConfig.getArticle().getAllowedTypes();
            default:
                return new String[]{"image/jpeg", "image/png"};
        }
    }
}
