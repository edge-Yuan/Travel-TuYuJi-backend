package com.wanderlust.travel.travelportal.service;

import com.wanderlust.travel.travelportal.entity.FileInfo;
import com.wanderlust.travel.travelportal.entity.UserAvatar;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件上传服务接口
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
public interface IFileUploadService {

    /**
     * 上传头像
     */
    Map<String, Object> uploadAvatar(MultipartFile file, Long userId);

    /**
     * 上传资质证明文件
     */
    Map<String, Object> uploadQualification(MultipartFile file, Long guideId, String qualificationType);

    /**
     * 上传产品图片
     */
    Map<String, Object> uploadProductImage(MultipartFile file, Long productId, String imageType, Boolean isMain);

    /**
     * 上传文章图片
     */
    Map<String, Object> uploadArticleImage(MultipartFile file, Long articleId);

    /**
     * 通用文件上传
     */
    Map<String, Object> uploadFile(MultipartFile file, String uploadType, Long relatedId);

    /**
     * 获取用户头像
     */
    UserAvatar getUserAvatar(Long userId);

    /**
     * 获取用户的所有头像历史
     */
    List<UserAvatar> getUserAvatarHistory(Long userId);

    /**
     * 删除文件
     */
    Boolean deleteFile(Long fileId);

    /**
     * 获取文件信息
     */
    FileInfo getFileInfo(Long fileId);

    /**
     * 获取文件列表
     */
    List<FileInfo> getFileList(String uploadType, Long relatedId);
}
