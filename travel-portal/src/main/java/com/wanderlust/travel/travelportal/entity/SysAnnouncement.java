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
 * 系统公告表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("sys_announcement")
@ApiModel(value = "SysAnnouncement对象", description = "系统公告表")
public class SysAnnouncement implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公告唯一标识")
    @TableId(value = "announce_id", type = IdType.AUTO)
    private Long announceId;

    @ApiModelProperty("公告标题")
    private String title;

    @ApiModelProperty("公告内容")
    private String content;

    @ApiModelProperty("发布人")
    private Long publisherId;

    @ApiModelProperty("生效时间")
    private LocalDateTime startTime;

    @ApiModelProperty("失效时间")
    private LocalDateTime endTime;

    @ApiModelProperty("状态：0-未生效，1-展示中，2-已失效")
    private Byte announceStatus;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    public Long getAnnounceId() {
        return announceId;
    }

    public void setAnnounceId(Long announceId) {
        this.announceId = announceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Byte getAnnounceStatus() {
        return announceStatus;
    }

    public void setAnnounceStatus(Byte announceStatus) {
        this.announceStatus = announceStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysAnnouncement{" +
            "announceId = " + announceId +
            ", title = " + title +
            ", content = " + content +
            ", publisherId = " + publisherId +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", announceStatus = " + announceStatus +
            ", createTime = " + createTime +
        "}";
    }
}
