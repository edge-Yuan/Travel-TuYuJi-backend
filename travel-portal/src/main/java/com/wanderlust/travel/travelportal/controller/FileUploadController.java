package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.FileInfo;
import com.wanderlust.travel.travelportal.entity.UserAvatar;
import com.wanderlust.travel.travelportal.service.IFileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件上传控制器
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@RestController
@RequestMapping("/travel-portal/upload")
@Api(tags = "文件上传管理")
@Slf4j
public class FileUploadController {

    @Autowired
    private IFileUploadService fileUploadService;

    /**
     * 上传头像
     */
    @PostMapping("/avatar")
    @PutMapping("/avatar")
    @ApiOperation("上传头像")
    public Result<Map<String, Object>> uploadAvatar(
            @ApiParam("头像文件") @RequestParam("file") MultipartFile file,
            @ApiParam("用户ID") @RequestParam Long userId) {
        
        log.info("上传头像，用户ID：{}", userId);
        
        try {
            Map<String, Object> result = fileUploadService.uploadAvatar(file, userId);
            return Result.success(result);
        } catch (Exception e) {
            log.error("头像上传失败", e);
            return Result.error("头像上传失败：" + e.getMessage());
        }
    }

    /**
     * 上传资质证明文件
     */
    @PostMapping("/qualification")
    @PutMapping("/qualification")
    @ApiOperation("上传资质证明文件")
    public Result<Map<String, Object>> uploadQualification(
            @ApiParam("资质证明文件") @RequestParam("file") MultipartFile file,
            @ApiParam("导游ID") @RequestParam Long guideId,
            @ApiParam("资质类型") @RequestParam String qualificationType) {
        
        log.info("上传资质证明文件，导游ID：{}，类型：{}", guideId, qualificationType);
        
        try {
            Map<String, Object> result = fileUploadService.uploadQualification(file, guideId, qualificationType);
            return Result.success(result);
        } catch (Exception e) {
            log.error("资质证明文件上传失败", e);
            return Result.error("资质证明文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 上传产品图片
     */
    @PostMapping("/product")
    @PutMapping("/product")
    @ApiOperation("上传产品图片")
    public Result<Map<String, Object>> uploadProductImage(
            @ApiParam("产品图片文件") @RequestParam("file") MultipartFile file,
            @ApiParam("产品ID") @RequestParam Long productId,
            @ApiParam("图片类型") @RequestParam(defaultValue = "normal") String imageType,
            @ApiParam("是否为主图") @RequestParam(defaultValue = "false") Boolean isMain) {
        
        log.info("上传产品图片，产品ID：{}，类型：{}", productId, imageType);
        
        try {
            Map<String, Object> result = fileUploadService.uploadProductImage(file, productId, imageType, isMain);
            return Result.success(result);
        } catch (Exception e) {
            log.error("产品图片上传失败", e);
            return Result.error("产品图片上传失败：" + e.getMessage());
        }
    }

    /**
     * 上传文章图片
     */
    @PostMapping("/article")
    @PutMapping("/article")
    @ApiOperation("上传文章图片")
    public Result<Map<String, Object>> uploadArticleImage(
            @ApiParam("文章图片文件") @RequestParam("file") MultipartFile file,
            @ApiParam("文章ID") @RequestParam Long articleId) {
        
        log.info("上传文章图片，文章ID：{}", articleId);
        
        try {
            Map<String, Object> result = fileUploadService.uploadArticleImage(file, articleId);
            return Result.success(result);
        } catch (Exception e) {
            log.error("文章图片上传失败", e);
            return Result.error("文章图片上传失败：" + e.getMessage());
        }
    }

    /**
     * 通用文件上传
     */
    @PostMapping("/file")
    @PutMapping("/file")
    @ApiOperation("通用文件上传")
    public Result<Map<String, Object>> uploadFile(
            @ApiParam("文件") @RequestParam("file") MultipartFile file,
            @ApiParam("上传类型") @RequestParam String uploadType,
            @ApiParam("关联ID") @RequestParam Long relatedId) {
        
        log.info("通用文件上传，类型：{}，关联ID：{}", uploadType, relatedId);
        
        try {
            Map<String, Object> result = fileUploadService.uploadFile(file, uploadType, relatedId);
            return Result.success(result);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 获取用户头像
     */
    @GetMapping("/avatar/{userId}")
    @ApiOperation("获取用户头像")
    public Result<UserAvatar> getUserAvatar(@ApiParam("用户ID") @PathVariable Long userId) {
        try {
            UserAvatar avatar = fileUploadService.getUserAvatar(userId);
            return Result.success(avatar);
        } catch (Exception e) {
            log.error("获取用户头像失败", e);
            return Result.error("获取用户头像失败：" + e.getMessage());
        }
    }

    /**
     * 获取用户头像历史
     */
    @GetMapping("/avatar/{userId}/history")
    @ApiOperation("获取用户头像历史")
    public Result<List<UserAvatar>> getUserAvatarHistory(@ApiParam("用户ID") @PathVariable Long userId) {
        try {
            List<UserAvatar> avatars = fileUploadService.getUserAvatarHistory(userId);
            return Result.success(avatars);
        } catch (Exception e) {
            log.error("获取用户头像历史失败", e);
            return Result.error("获取用户头像历史失败：" + e.getMessage());
        }
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/file/{fileId}")
    @ApiOperation("删除文件")
    public Result<Boolean> deleteFile(@ApiParam("文件ID") @PathVariable Long fileId) {
        log.info("删除文件，文件ID：{}", fileId);
        
        try {
            Boolean result = fileUploadService.deleteFile(fileId);
            return result ? Result.success(true) : Result.error("删除文件失败");
        } catch (Exception e) {
            log.error("删除文件失败", e);
            return Result.error("删除文件失败：" + e.getMessage());
        }
    }

    /**
     * 获取文件信息
     */
    @GetMapping("/file/{fileId}")
    @ApiOperation("获取文件信息")
    public Result<FileInfo> getFileInfo(@ApiParam("文件ID") @PathVariable Long fileId) {
        try {
            FileInfo fileInfo = fileUploadService.getFileInfo(fileId);
            return fileInfo != null ? Result.success(fileInfo) : Result.error("文件不存在");
        } catch (Exception e) {
            log.error("获取文件信息失败", e);
            return Result.error("获取文件信息失败：" + e.getMessage());
        }
    }

    /**
     * 获取文件列表
     */
    @GetMapping("/files")
    @ApiOperation("获取文件列表")
    public Result<List<FileInfo>> getFileList(
            @ApiParam("上传类型") @RequestParam String uploadType,
            @ApiParam("关联ID") @RequestParam Long relatedId) {
        try {
            List<FileInfo> files = fileUploadService.getFileList(uploadType, relatedId);
            return Result.success(files);
        } catch (Exception e) {
            log.error("获取文件列表失败", e);
            return Result.error("获取文件列表失败：" + e.getMessage());
        }
    }
}
