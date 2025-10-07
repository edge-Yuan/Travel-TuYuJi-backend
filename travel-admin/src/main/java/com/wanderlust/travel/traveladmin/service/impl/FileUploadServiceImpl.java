package com.wanderlust.travel.traveladmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanderlust.travel.traveladmin.entity.FileInfo;
import com.wanderlust.travel.traveladmin.mapper.FileInfoMapper;
import com.wanderlust.travel.traveladmin.service.IFileUploadService;
import com.wanderlust.travel.traveladmin.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件上传服务实现类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Slf4j
@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> uploadAvatar(MultipartFile file, Long userId) {
        try {
            // 保存文件
            String filePath = fileUploadUtil.saveFile(file, "avatar");
            String fileUrl = fileUploadUtil.getFileUrl(filePath);

            // 保存文件信息
            FileInfo fileInfo = createFileInfo(file, filePath, fileUrl, "avatar", userId, true);
            fileInfoMapper.insert(fileInfo);

            Map<String, Object> result = new HashMap<>();
            result.put("fileId", fileInfo.getFileId());
            result.put("url", fileUrl);
            result.put("filename", fileInfo.getOriginalName());
            result.put("size", fileInfo.getFileSize());
            return result;

        } catch (Exception e) {
            log.error("头像上传失败", e);
            throw new RuntimeException("头像上传失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> uploadProductImage(MultipartFile file, Long productId, Boolean isMain) {
        try {
            // 保存文件
            String filePath = fileUploadUtil.saveFile(file, "product");
            String fileUrl = fileUploadUtil.getFileUrl(filePath);

            // 如果设置为主图，先将其他主图取消
            if (isMain != null && isMain) {
                setProductMainImage(productId, null);
            }

            // 保存文件信息
            FileInfo fileInfo = createFileInfo(file, filePath, fileUrl, "product", productId, isMain);
            fileInfoMapper.insert(fileInfo);

            Map<String, Object> result = new HashMap<>();
            result.put("fileId", fileInfo.getFileId());
            result.put("url", fileUrl);
            result.put("filename", fileInfo.getOriginalName());
            result.put("size", fileInfo.getFileSize());
            result.put("isMain", fileInfo.getIsMain());
            return result;

        } catch (Exception e) {
            log.error("产品图片上传失败", e);
            throw new RuntimeException("产品图片上传失败: " + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Map<String, Object>> uploadProductImages(MultipartFile[] files, Long productId) {
        List<Map<String, Object>> results = new ArrayList<>();
        
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            Boolean isMain = (i == 0); // 第一个文件设为主图
            Map<String, Object> result = uploadProductImage(file, productId, isMain);
            results.add(result);
        }
        
        return results;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> uploadFile(MultipartFile file, String uploadType, Long relatedId) {
        try {
            // 保存文件
            String filePath = fileUploadUtil.saveFile(file, uploadType);
            String fileUrl = fileUploadUtil.getFileUrl(filePath);

            // 保存文件信息
            FileInfo fileInfo = createFileInfo(file, filePath, fileUrl, uploadType, relatedId, false);
            fileInfoMapper.insert(fileInfo);

            Map<String, Object> result = new HashMap<>();
            result.put("fileId", fileInfo.getFileId());
            result.put("url", fileUrl);
            result.put("filename", fileInfo.getOriginalName());
            result.put("size", fileInfo.getFileSize());
            return result;

        } catch (Exception e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败: " + e.getMessage());
        }
    }

    @Override
    public List<FileInfo> getProductImages(Long productId) {
        LambdaQueryWrapper<FileInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileInfo::getRelatedId, productId)
                   .eq(FileInfo::getUploadType, "product")
                   .eq(FileInfo::getStatus, 1)
                   .orderByDesc(FileInfo::getIsMain)
                   .orderByDesc(FileInfo::getUploadTime);
        
        return fileInfoMapper.selectList(queryWrapper);
    }

    @Override
    public FileInfo getProductMainImage(Long productId) {
        LambdaQueryWrapper<FileInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileInfo::getRelatedId, productId)
                   .eq(FileInfo::getUploadType, "product")
                   .eq(FileInfo::getIsMain, true)
                   .eq(FileInfo::getStatus, 1)
                   .orderByDesc(FileInfo::getUploadTime)
                   .last("LIMIT 1");
        
        return fileInfoMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean setProductMainImage(Long productId, Long fileId) {
        try {
            // 先将该产品的所有图片取消主图状态
            LambdaQueryWrapper<FileInfo> updateWrapper = new LambdaQueryWrapper<>();
            updateWrapper.eq(FileInfo::getRelatedId, productId)
                        .eq(FileInfo::getUploadType, "product");
            
            FileInfo updateInfo = new FileInfo();
            updateInfo.setIsMain(false);
            updateInfo.setUpdateTime(LocalDateTime.now());
            fileInfoMapper.update(updateInfo, updateWrapper);

            // 如果指定了文件ID，则设置该文件为主图
            if (fileId != null) {
                FileInfo mainImage = new FileInfo();
                mainImage.setFileId(fileId);
                mainImage.setIsMain(true);
                mainImage.setUpdateTime(LocalDateTime.now());
                fileInfoMapper.updateById(mainImage);
            }

            return true;
        } catch (Exception e) {
            log.error("设置产品主图失败", e);
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteFile(Long fileId) {
        try {
            FileInfo fileInfo = fileInfoMapper.selectById(fileId);
            if (fileInfo == null) {
                return false;
            }

            // 删除物理文件
            fileUploadUtil.deleteFile(fileInfo.getFilePath());

            // 删除数据库记录
            fileInfoMapper.deleteById(fileId);

            log.info("文件删除成功：{}", fileInfo.getOriginalName());
            return true;
        } catch (Exception e) {
            log.error("删除文件失败", e);
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteProductImages(Long productId) {
        try {
            List<FileInfo> images = getProductImages(productId);
            
            for (FileInfo image : images) {
                deleteFile(image.getFileId());
            }

            log.info("产品图片删除成功，产品ID：{}", productId);
            return true;
        } catch (Exception e) {
            log.error("删除产品图片失败", e);
            return false;
        }
    }

    /**
     * 创建文件信息对象
     * 
     * @param file 上传的文件
     * @param filePath 文件路径
     * @param fileUrl 文件URL
     * @param uploadType 上传类型
     * @param relatedId 关联ID
     * @param isMain 是否为主文件
     * @return 文件信息对象
     */
    private FileInfo createFileInfo(MultipartFile file, String filePath, String fileUrl, 
                                   String uploadType, Long relatedId, Boolean isMain) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalName(file.getOriginalFilename());
        fileInfo.setFileName(filePath.substring(filePath.lastIndexOf("/") + 1));
        fileInfo.setFilePath(filePath);
        fileInfo.setFileUrl(fileUrl);
        fileInfo.setFileSize(file.getSize());
        fileInfo.setFileType(file.getContentType());
        fileInfo.setFileExtension(fileUploadUtil.getFileExtension(file.getOriginalFilename()));
        fileInfo.setUploadType(uploadType);
        fileInfo.setRelatedId(relatedId);
        fileInfo.setIsMain(isMain != null ? isMain : false);
        fileInfo.setStatus((byte) 1);
        fileInfo.setUploadUserId(relatedId); // 这里可以根据实际需求设置
        fileInfo.setUploadTime(LocalDateTime.now());
        fileInfo.setCreateTime(LocalDateTime.now());
        fileInfo.setUpdateTime(LocalDateTime.now());
        
        return fileInfo;
    }
}
