package com.wanderlust.travel.traveladmin.service;

import com.wanderlust.travel.traveladmin.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 文件上传服务接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
public interface IFileUploadService {

    /**
     * 上传头像
     * 
     * @param file 头像文件
     * @param userId 用户ID
     * @return 上传结果
     */
    Map<String, Object> uploadAvatar(MultipartFile file, Long userId);

    /**
     * 上传产品图片
     * 
     * @param file 产品图片文件
     * @param productId 产品ID
     * @param isMain 是否为主图
     * @return 上传结果
     */
    Map<String, Object> uploadProductImage(MultipartFile file, Long productId, Boolean isMain);

    /**
     * 批量上传产品图片
     * 
     * @param files 产品图片文件列表
     * @param productId 产品ID
     * @return 上传结果
     */
    List<Map<String, Object>> uploadProductImages(MultipartFile[] files, Long productId);

    /**
     * 通用文件上传
     * 
     * @param file 文件
     * @param uploadType 上传类型
     * @param relatedId 关联ID
     * @return 上传结果
     */
    Map<String, Object> uploadFile(MultipartFile file, String uploadType, Long relatedId);

    /**
     * 获取产品图片列表
     * 
     * @param productId 产品ID
     * @return 图片列表
     */
    List<FileInfo> getProductImages(Long productId);

    /**
     * 获取产品主图
     * 
     * @param productId 产品ID
     * @return 主图信息
     */
    FileInfo getProductMainImage(Long productId);

    /**
     * 设置产品主图
     * 
     * @param productId 产品ID
     * @param fileId 文件ID
     * @return 是否成功
     */
    boolean setProductMainImage(Long productId, Long fileId);

    /**
     * 删除文件
     * 
     * @param fileId 文件ID
     * @return 是否成功
     */
    boolean deleteFile(Long fileId);

    /**
     * 删除产品所有图片
     * 
     * @param productId 产品ID
     * @return 是否成功
     */
    boolean deleteProductImages(Long productId);
}
