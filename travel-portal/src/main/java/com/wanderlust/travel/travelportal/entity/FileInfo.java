package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 文件信息表
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@TableName("file_info")
@ApiModel(value = "FileInfo对象", description = "文件信息表")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件唯一标识")
    @TableId(value = "file_id", type = IdType.AUTO)
    private Long fileId;

    @ApiModelProperty("原始文件名")
    private String originalName;

    @ApiModelProperty("存储文件名")
    private String fileName;

    @ApiModelProperty("文件存储路径")
    private String filePath;

    @ApiModelProperty("文件访问URL")
    private String fileUrl;

    @ApiModelProperty("文件大小（字节）")
    private Long fileSize;

    @ApiModelProperty("文件MIME类型")
    private String fileType;

    @ApiModelProperty("文件扩展名")
    private String fileExtension;

    @ApiModelProperty("上传类型：avatar-头像，qualification-资质证明，product-产品图片，article-文章图片")
    private String uploadType;

    @ApiModelProperty("关联ID（用户ID、产品ID等）")
    private Long relatedId;

    @ApiModelProperty("是否为主文件")
    private Boolean isMain;

    @ApiModelProperty("状态：0-已删除，1-正常")
    private Byte status;

    @ApiModelProperty("上传用户ID")
    private Long uploadUserId;

    @ApiModelProperty("上传时间")
    private LocalDateTime uploadTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public Long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    public Boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Long uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
            "fileId = " + fileId +
            ", originalName = " + originalName +
            ", fileName = " + fileName +
            ", filePath = " + filePath +
            ", fileUrl = " + fileUrl +
            ", fileSize = " + fileSize +
            ", fileType = " + fileType +
            ", fileExtension = " + fileExtension +
            ", uploadType = " + uploadType +
            ", relatedId = " + relatedId +
            ", isMain = " + isMain +
            ", status = " + status +
            ", uploadUserId = " + uploadUserId +
            ", uploadTime = " + uploadTime +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
        "}";
    }
}
