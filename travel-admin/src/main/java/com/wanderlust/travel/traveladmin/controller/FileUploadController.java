package com.wanderlust.travel.traveladmin.controller;

import com.wanderlust.travel.traveladmin.entity.FileInfo;
import com.wanderlust.travel.traveladmin.service.IFileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传控制器
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Slf4j
@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final IFileUploadService fileUploadService;

    /**
     * 上传头像
     */
    @PostMapping("/avatar")
    public ResponseEntity<Map<String, Object>> uploadAvatar(
            @RequestParam("file") MultipartFile file,
            @RequestParam Long userId) {
        
        log.info("上传头像，用户ID：{}", userId);
        
        try {
            Map<String, Object> result = fileUploadService.uploadAvatar(file, userId);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 1);
            response.put("msg", "头像上传成功");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("头像上传失败", e);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 0);
            response.put("msg", "头像上传失败：" + e.getMessage());
            response.put("data", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 上传产品图片
     */
    @PostMapping("/product")
    public ResponseEntity<Map<String, Object>> uploadProductImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam Long productId,
            @RequestParam(defaultValue = "false") Boolean isMain) {
        
        log.info("上传产品图片，产品ID：{}，是否主图：{}", productId, isMain);
        
        try {
            Map<String, Object> result = fileUploadService.uploadProductImage(file, productId, isMain);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 1);
            response.put("msg", "产品图片上传成功");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("产品图片上传失败", e);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 0);
            response.put("msg", "产品图片上传失败：" + e.getMessage());
            response.put("data", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 批量上传产品图片
     */
    @PostMapping("/product/batch")
    public ResponseEntity<Map<String, Object>> uploadProductImages(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam Long productId) {
        
        log.info("批量上传产品图片，产品ID：{}，文件数量：{}", productId, files.length);
        
        try {
            List<Map<String, Object>> results = fileUploadService.uploadProductImages(files, productId);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 1);
            response.put("msg", "产品图片批量上传成功");
            response.put("data", results);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("产品图片批量上传失败", e);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 0);
            response.put("msg", "产品图片批量上传失败：" + e.getMessage());
            response.put("data", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 通用文件上传
     */
    @PostMapping("/file")
    public ResponseEntity<Map<String, Object>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam String uploadType,
            @RequestParam Long relatedId) {
        
        log.info("通用文件上传，类型：{}，关联ID：{}", uploadType, relatedId);
        
        try {
            Map<String, Object> result = fileUploadService.uploadFile(file, uploadType, relatedId);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 1);
            response.put("msg", "文件上传成功");
            response.put("data", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 0);
            response.put("msg", "文件上传失败：" + e.getMessage());
            response.put("data", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 获取产品图片列表
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<Map<String, Object>> getProductImages(@PathVariable Long productId) {
        log.info("获取产品图片列表，产品ID：{}", productId);
        
        try {
            List<FileInfo> images = fileUploadService.getProductImages(productId);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 1);
            response.put("msg", "获取成功");
            response.put("data", images);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("获取产品图片列表失败", e);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 0);
            response.put("msg", "获取产品图片列表失败：" + e.getMessage());
            response.put("data", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 获取产品主图
     */
    @GetMapping("/product/{productId}/main")
    public ResponseEntity<Map<String, Object>> getProductMainImage(@PathVariable Long productId) {
        log.info("获取产品主图，产品ID：{}", productId);
        
        try {
            FileInfo mainImage = fileUploadService.getProductMainImage(productId);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 1);
            response.put("msg", "获取成功");
            response.put("data", mainImage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("获取产品主图失败", e);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 0);
            response.put("msg", "获取产品主图失败：" + e.getMessage());
            response.put("data", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 设置产品主图
     */
    @PutMapping("/product/{productId}/main/{fileId}")
    public ResponseEntity<Map<String, Object>> setProductMainImage(
            @PathVariable Long productId,
            @PathVariable Long fileId) {
        
        log.info("设置产品主图，产品ID：{}，文件ID：{}", productId, fileId);
        
        try {
            boolean success = fileUploadService.setProductMainImage(productId, fileId);
            Map<String, Object> response = new HashMap<>();
            response.put("code", success ? 1 : 0);
            response.put("msg", success ? "设置主图成功" : "设置主图失败");
            response.put("data", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("设置产品主图失败", e);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 0);
            response.put("msg", "设置产品主图失败：" + e.getMessage());
            response.put("data", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/file/{fileId}")
    public ResponseEntity<Map<String, Object>> deleteFile(@PathVariable Long fileId) {
        log.info("删除文件，文件ID：{}", fileId);
        
        try {
            boolean success = fileUploadService.deleteFile(fileId);
            Map<String, Object> response = new HashMap<>();
            response.put("code", success ? 1 : 0);
            response.put("msg", success ? "删除成功" : "删除失败");
            response.put("data", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("删除文件失败", e);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 0);
            response.put("msg", "删除文件失败：" + e.getMessage());
            response.put("data", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 删除产品所有图片
     */
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Map<String, Object>> deleteProductImages(@PathVariable Long productId) {
        log.info("删除产品所有图片，产品ID：{}", productId);
        
        try {
            boolean success = fileUploadService.deleteProductImages(productId);
            Map<String, Object> response = new HashMap<>();
            response.put("code", success ? 1 : 0);
            response.put("msg", success ? "删除成功" : "删除失败");
            response.put("data", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("删除产品图片失败", e);
            Map<String, Object> response = new HashMap<>();
            response.put("code", 0);
            response.put("msg", "删除产品图片失败：" + e.getMessage());
            response.put("data", null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
