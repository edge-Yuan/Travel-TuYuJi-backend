package com.wanderlust.travel.traveladmin.util;

import com.wanderlust.travel.traveladmin.config.FileUploadConfig;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2024-01-15
 */
@Slf4j
@Component
public class FileUploadUtil {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    /**
     * 保存文件
     * 
     * @param file 上传的文件
     * @param uploadType 上传类型（avatar, product, article, qualification）
     * @return 文件相对路径
     * @throws IOException IO异常
     */
    public String saveFile(MultipartFile file, String uploadType) throws IOException {
        // 验证文件
        validateFile(file, uploadType);

        // 获取上传配置
        FileUploadConfig.UploadTypeConfig config = getUploadConfig(uploadType);

        // 生成文件名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = getFileExtension(originalFilename);
        String fileName = generateFileName(fileExtension);

        // 构建文件路径
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String relativePath = config.getPath() + "/" + datePath + "/" + fileName;
        String fullPath = fileUploadConfig.getBasePath() + relativePath;

        // 创建目录
        Path targetPath = Paths.get(fullPath);
        Files.createDirectories(targetPath.getParent());

        // 保存文件
        file.transferTo(targetPath.toFile());

        log.info("文件上传成功：{} -> {}", originalFilename, fullPath);
        return relativePath;
    }

    /**
     * 验证文件
     * 
     * @param file 上传的文件
     * @param uploadType 上传类型
     */
    private void validateFile(MultipartFile file, String uploadType) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空");
        }

        FileUploadConfig.UploadTypeConfig config = getUploadConfig(uploadType);

        // 检查文件大小
        if (file.getSize() > config.getMaxSize()) {
            throw new IllegalArgumentException("文件大小超过限制：" + config.getMaxSize() + " 字节");
        }

        // 检查文件类型
        String contentType = file.getContentType();
        if (!config.getAllowedTypes().contains(contentType)) {
            throw new IllegalArgumentException("不支持的文件类型：" + contentType);
        }
    }

    /**
     * 获取上传配置
     * 
     * @param uploadType 上传类型
     * @return 上传配置
     */
    private FileUploadConfig.UploadTypeConfig getUploadConfig(String uploadType) {
        switch (uploadType.toLowerCase()) {
            case "avatar":
                return fileUploadConfig.getAvatar();
            case "product":
                return fileUploadConfig.getProduct();
            case "article":
                return fileUploadConfig.getArticle();
            case "qualification":
                return fileUploadConfig.getQualification();
            default:
                throw new IllegalArgumentException("不支持的上传类型：" + uploadType);
        }
    }

    /**
     * 生成文件名
     * 
     * @param fileExtension 文件扩展名
     * @return 文件名
     */
    private String generateFileName(String fileExtension) {
        return UUID.randomUUID().toString().replace("-", "") + "." + fileExtension;
    }

    /**
     * 获取文件扩展名
     * 
     * @param filename 文件名
     * @return 扩展名
     */
    public String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 删除文件
     * 
     * @param filePath 文件路径
     * @return 是否删除成功
     */
    public boolean deleteFile(String filePath) {
        try {
            String fullPath = fileUploadConfig.getBasePath() + filePath;
            File file = new File(fullPath);
            if (file.exists()) {
                boolean deleted = file.delete();
                log.info("文件删除{}：{}", deleted ? "成功" : "失败", fullPath);
                return deleted;
            }
            return true;
        } catch (Exception e) {
            log.error("删除文件失败：{}", filePath, e);
            return false;
        }
    }

    /**
     * 获取文件访问URL
     * 
     * @param filePath 文件相对路径
     * @return 文件访问URL
     */
    public String getFileUrl(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return null;
        }
        return fileUploadConfig.getUrlPrefix() + "/uploads" + filePath;
    }
}
