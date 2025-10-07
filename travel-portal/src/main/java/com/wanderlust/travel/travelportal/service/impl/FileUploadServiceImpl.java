package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanderlust.travel.travelportal.entity.FileInfo;
import com.wanderlust.travel.travelportal.entity.UserAvatar;
import com.wanderlust.travel.travelportal.mapper.FileInfoMapper;
import com.wanderlust.travel.travelportal.mapper.UserAvatarMapper;
import com.wanderlust.travel.travelportal.service.IFileUploadService;
import com.wanderlust.travel.travelportal.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件上传服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private UserAvatarMapper userAvatarMapper;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Override
    @Transactional
    public Map<String, Object> uploadAvatar(MultipartFile file, Long userId) {
        try {
            // 保存文件
            String filePath = fileUploadUtil.saveFile(file, "avatar");
            // 生成正确的访问URL，filePath已经包含了/uploads前缀
            String fileUrl = filePath;

            // 保存文件信息
            FileInfo fileInfo = new FileInfo();
            fileInfo.setOriginalName(file.getOriginalFilename());
            fileInfo.setFileName(filePath.substring(filePath.lastIndexOf("/") + 1));
            fileInfo.setFilePath(filePath);
            fileInfo.setFileUrl(fileUrl);
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFileType(file.getContentType());
            fileInfo.setFileExtension(getFileExtension(file.getOriginalFilename()));
            fileInfo.setUploadType("avatar");
            fileInfo.setRelatedId(userId);
            fileInfo.setIsMain(true);
            fileInfo.setStatus((byte) 1);
            fileInfo.setUploadUserId(userId);
            fileInfo.setUploadTime(LocalDateTime.now());
            fileInfoMapper.insert(fileInfo);

            // 更新用户头像
            updateUserAvatar(userId, fileInfo.getFileId(), fileUrl);

            Map<String, Object> result = new HashMap<>();
            result.put("fileId", fileInfo.getFileId());
            result.put("url", fileUrl);
            result.put("filename", fileInfo.getOriginalName());
            result.put("size", fileInfo.getFileSize());
            return result;

        } catch (Exception e) {
            throw new RuntimeException("头像上传失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Map<String, Object> uploadQualification(MultipartFile file, Long guideId, String qualificationType) {
        try {
            // 保存文件
            String filePath = fileUploadUtil.saveFile(file, "qualification");
            String fileUrl = fileUploadUtil.generateFileUrl(filePath);

            // 保存文件信息
            FileInfo fileInfo = new FileInfo();
            fileInfo.setOriginalName(file.getOriginalFilename());
            fileInfo.setFileName(filePath.substring(filePath.lastIndexOf("/") + 1));
            fileInfo.setFilePath(filePath);
            fileInfo.setFileUrl(fileUrl);
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFileType(file.getContentType());
            fileInfo.setFileExtension(getFileExtension(file.getOriginalFilename()));
            fileInfo.setUploadType("qualification");
            fileInfo.setRelatedId(guideId);
            fileInfo.setIsMain(false);
            fileInfo.setStatus((byte) 1);
            fileInfo.setUploadUserId(guideId);
            fileInfo.setUploadTime(LocalDateTime.now());
            fileInfoMapper.insert(fileInfo);

            Map<String, Object> result = new HashMap<>();
            result.put("fileId", fileInfo.getFileId());
            result.put("url", fileUrl);
            result.put("filename", fileInfo.getOriginalName());
            result.put("size", fileInfo.getFileSize());
            result.put("qualificationType", qualificationType);
            return result;

        } catch (Exception e) {
            throw new RuntimeException("资质证明上传失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Map<String, Object> uploadProductImage(MultipartFile file, Long productId, String imageType, Boolean isMain) {
        try {
            // 保存文件
            String filePath = fileUploadUtil.saveFile(file, "product");
            String fileUrl = fileUploadUtil.generateFileUrl(filePath);

            // 保存文件信息
            FileInfo fileInfo = new FileInfo();
            fileInfo.setOriginalName(file.getOriginalFilename());
            fileInfo.setFileName(filePath.substring(filePath.lastIndexOf("/") + 1));
            fileInfo.setFilePath(filePath);
            fileInfo.setFileUrl(fileUrl);
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFileType(file.getContentType());
            fileInfo.setFileExtension(getFileExtension(file.getOriginalFilename()));
            fileInfo.setUploadType("product");
            fileInfo.setRelatedId(productId);
            fileInfo.setIsMain(isMain);
            fileInfo.setStatus((byte) 1);
            fileInfo.setUploadUserId(productId); // 这里应该是商户ID
            fileInfo.setUploadTime(LocalDateTime.now());
            fileInfoMapper.insert(fileInfo);

            Map<String, Object> result = new HashMap<>();
            result.put("fileId", fileInfo.getFileId());
            result.put("url", fileUrl);
            result.put("filename", fileInfo.getOriginalName());
            result.put("size", fileInfo.getFileSize());
            result.put("imageType", imageType);
            result.put("isMain", isMain);
            return result;

        } catch (Exception e) {
            throw new RuntimeException("产品图片上传失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Map<String, Object> uploadArticleImage(MultipartFile file, Long articleId) {
        try {
            // 保存文件
            String filePath = fileUploadUtil.saveFile(file, "article");
            String fileUrl = fileUploadUtil.generateFileUrl(filePath);

            // 保存文件信息
            FileInfo fileInfo = new FileInfo();
            fileInfo.setOriginalName(file.getOriginalFilename());
            fileInfo.setFileName(filePath.substring(filePath.lastIndexOf("/") + 1));
            fileInfo.setFilePath(filePath);
            fileInfo.setFileUrl(fileUrl);
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFileType(file.getContentType());
            fileInfo.setFileExtension(getFileExtension(file.getOriginalFilename()));
            fileInfo.setUploadType("article");
            fileInfo.setRelatedId(articleId);
            fileInfo.setIsMain(false);
            fileInfo.setStatus((byte) 1);
            fileInfo.setUploadUserId(articleId); // 这里应该是文章作者ID
            fileInfo.setUploadTime(LocalDateTime.now());
            fileInfoMapper.insert(fileInfo);

            Map<String, Object> result = new HashMap<>();
            result.put("fileId", fileInfo.getFileId());
            result.put("url", fileUrl);
            result.put("filename", fileInfo.getOriginalName());
            result.put("size", fileInfo.getFileSize());
            return result;

        } catch (Exception e) {
            throw new RuntimeException("文章图片上传失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Map<String, Object> uploadFile(MultipartFile file, String uploadType, Long relatedId) {
        try {
            // 保存文件
            String filePath = fileUploadUtil.saveFile(file, uploadType);
            String fileUrl = fileUploadUtil.generateFileUrl(filePath);

            // 保存文件信息
            FileInfo fileInfo = new FileInfo();
            fileInfo.setOriginalName(file.getOriginalFilename());
            fileInfo.setFileName(filePath.substring(filePath.lastIndexOf("/") + 1));
            fileInfo.setFilePath(filePath);
            fileInfo.setFileUrl(fileUrl);
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFileType(file.getContentType());
            fileInfo.setFileExtension(getFileExtension(file.getOriginalFilename()));
            fileInfo.setUploadType(uploadType);
            fileInfo.setRelatedId(relatedId);
            fileInfo.setIsMain(false);
            fileInfo.setStatus((byte) 1);
            fileInfo.setUploadUserId(relatedId);
            fileInfo.setUploadTime(LocalDateTime.now());
            fileInfoMapper.insert(fileInfo);

            Map<String, Object> result = new HashMap<>();
            result.put("fileId", fileInfo.getFileId());
            result.put("url", fileUrl);
            result.put("filename", fileInfo.getOriginalName());
            result.put("size", fileInfo.getFileSize());
            result.put("uploadType", uploadType);
            return result;

        } catch (Exception e) {
            throw new RuntimeException("文件上传失败: " + e.getMessage());
        }
    }

    @Override
    public UserAvatar getUserAvatar(Long userId) {
        return userAvatarMapper.selectOne(
            new LambdaQueryWrapper<UserAvatar>()
                .eq(UserAvatar::getUserId, userId)
                .eq(UserAvatar::getIsCurrent, true)
                .orderByDesc(UserAvatar::getCreateTime)
                .last("LIMIT 1")
        );
    }

    @Override
    public List<UserAvatar> getUserAvatarHistory(Long userId) {
        return userAvatarMapper.selectList(
            new LambdaQueryWrapper<UserAvatar>()
                .eq(UserAvatar::getUserId, userId)
                .orderByDesc(UserAvatar::getCreateTime)
        );
    }

    @Override
    @Transactional
    public Boolean deleteFile(Long fileId) {
        try {
            FileInfo fileInfo = fileInfoMapper.selectById(fileId);
            if (fileInfo != null) {
                // 删除物理文件
                fileUploadUtil.deleteFile(fileInfo.getFilePath());
                
                // 更新数据库状态
                fileInfo.setStatus((byte) 0);
                fileInfoMapper.updateById(fileInfo);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public FileInfo getFileInfo(Long fileId) {
        return fileInfoMapper.selectById(fileId);
    }

    @Override
    public List<FileInfo> getFileList(String uploadType, Long relatedId) {
        return fileInfoMapper.selectList(
            new LambdaQueryWrapper<FileInfo>()
                .eq(FileInfo::getUploadType, uploadType)
                .eq(FileInfo::getRelatedId, relatedId)
                .eq(FileInfo::getStatus, 1)
                .orderByDesc(FileInfo::getUploadTime)
        );
    }

    /**
     * 更新用户头像
     */
    private void updateUserAvatar(Long userId, Long fileId, String avatarUrl) {
        // 先删除该用户的所有头像记录，避免约束冲突
        userAvatarMapper.deleteAllUserAvatars(userId);

        // 添加新头像
        UserAvatar userAvatar = new UserAvatar();
        userAvatar.setUserId(userId);
        userAvatar.setFileId(fileId);
        userAvatar.setAvatarUrl(avatarUrl);
        userAvatar.setIsCurrent(true);
        userAvatar.setCreateTime(LocalDateTime.now());
        userAvatarMapper.insert(userAvatar);
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
}
