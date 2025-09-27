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
 * 反馈投诉表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("tour_feedback")
@ApiModel(value = "TourFeedback对象", description = "反馈投诉表")
public class TourFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("反馈唯一标识")
    @TableId(value = "feedback_id", type = IdType.AUTO)
    private Long feedbackId;

    @ApiModelProperty("反馈游客")
    private Long userId;

    @ApiModelProperty("关联订单")
    private Long orderId;

    @ApiModelProperty("类型：1-投诉，2-建议")
    private Byte feedbackType;

    @ApiModelProperty("反馈内容")
    private String content;

    @ApiModelProperty("凭证图片")
    private String imgUrls;

    @ApiModelProperty("处理状态：0-已收到，1-处理中，2-已解决，3-已驳回")
    private Byte feedbackStatus;

    @ApiModelProperty("处理人")
    private Long handlerId;

    @ApiModelProperty("处理结果")
    private String handleContent;

    @ApiModelProperty("反馈提交时间")
    private LocalDateTime createTime;

    @ApiModelProperty("处理完成时间")
    private LocalDateTime handleTime;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Byte getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(Byte feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Byte getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(Byte feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(LocalDateTime handleTime) {
        this.handleTime = handleTime;
    }

    @Override
    public String toString() {
        return "TourFeedback{" +
            "feedbackId = " + feedbackId +
            ", userId = " + userId +
            ", orderId = " + orderId +
            ", feedbackType = " + feedbackType +
            ", content = " + content +
            ", imgUrls = " + imgUrls +
            ", feedbackStatus = " + feedbackStatus +
            ", handlerId = " + handlerId +
            ", handleContent = " + handleContent +
            ", createTime = " + createTime +
            ", handleTime = " + handleTime +
        "}";
    }
}
