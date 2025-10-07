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
 * 用户头像表
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@TableName("user_avatar")
@ApiModel(value = "UserAvatar对象", description = "用户头像表")
public class UserAvatar implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("头像唯一标识")
    @TableId(value = "avatar_id", type = IdType.AUTO)
    private Long avatarId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("文件ID")
    private Long fileId;

    @ApiModelProperty("头像URL")
    private String avatarUrl;

    @ApiModelProperty("是否为当前头像")
    private Boolean isCurrent;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserAvatar{" +
            "avatarId = " + avatarId +
            ", userId = " + userId +
            ", fileId = " + fileId +
            ", avatarUrl = " + avatarUrl +
            ", isCurrent = " + isCurrent +
            ", createTime = " + createTime +
        "}";
    }
}
