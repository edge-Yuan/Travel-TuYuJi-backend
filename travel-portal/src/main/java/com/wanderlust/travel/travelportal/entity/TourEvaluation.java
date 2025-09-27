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
 * 评价表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("tour_evaluation")
@ApiModel(value = "TourEvaluation对象", description = "评价表")
public class TourEvaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评价唯一标识")
    @TableId(value = "eval_id", type = IdType.AUTO)
    private Long evalId;

    @ApiModelProperty("关联订单")
    private Long orderId;

    @ApiModelProperty("评价对象：1-产品，2-导游")
    private Byte evalType;

    @ApiModelProperty("评价目标ID")
    private Long targetId;
    
    @ApiModelProperty("关联产品ID")
    private Long productId;
    
    @ApiModelProperty("评价用户ID")
    private Long userId;
    
    @ApiModelProperty("评分（1-5分）")
    private Integer rating;
    
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("总体评分（1-5分）")
    private Byte overallScore;

    @ApiModelProperty("服务评分（1-5分）")
    private Byte serviceScore;

    @ApiModelProperty("环境评分（1-5分）")
    private Byte environmentScore;

    @ApiModelProperty("性价比评分（1-5分）")
    private Byte costEffScore;

    @ApiModelProperty("评价内容")
    private String content;

    @ApiModelProperty("评价图片")
    private String imgUrls;

    @ApiModelProperty("评价提交时间")
    private LocalDateTime evalTime;

    @ApiModelProperty("回复内容")
    private String replyContent;

    @ApiModelProperty("回复时间")
    private LocalDateTime replyTime;

    public Long getEvalId() {
        return evalId;
    }

    public void setEvalId(Long evalId) {
        this.evalId = evalId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Byte getEvalType() {
        return evalType;
    }

    public void setEvalType(Byte evalType) {
        this.evalType = evalType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Byte getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Byte overallScore) {
        this.overallScore = overallScore;
    }

    public Byte getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(Byte serviceScore) {
        this.serviceScore = serviceScore;
    }

    public Byte getEnvironmentScore() {
        return environmentScore;
    }

    public void setEnvironmentScore(Byte environmentScore) {
        this.environmentScore = environmentScore;
    }

    public Byte getCostEffScore() {
        return costEffScore;
    }

    public void setCostEffScore(Byte costEffScore) {
        this.costEffScore = costEffScore;
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

    public LocalDateTime getEvalTime() {
        return evalTime;
    }

    public void setEvalTime(LocalDateTime evalTime) {
        this.evalTime = evalTime;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public LocalDateTime getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(LocalDateTime replyTime) {
        this.replyTime = replyTime;
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TourEvaluation{" +
            "evalId = " + evalId +
            ", orderId = " + orderId +
            ", evalType = " + evalType +
            ", targetId = " + targetId +
            ", overallScore = " + overallScore +
            ", serviceScore = " + serviceScore +
            ", environmentScore = " + environmentScore +
            ", costEffScore = " + costEffScore +
            ", content = " + content +
            ", imgUrls = " + imgUrls +
            ", evalTime = " + evalTime +
            ", replyContent = " + replyContent +
            ", replyTime = " + replyTime +
        "}";
    }
}
