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
 * 用户收藏表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("user_collection")
@ApiModel(value = "UserCollection对象", description = "用户收藏表")
public class UserCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收藏唯一标识")
    @TableId(value = "collect_id", type = IdType.AUTO)
    private Long collectId;

    @ApiModelProperty("收藏游客")
    private Long userId;

    @ApiModelProperty("收藏产品")
    private Long productId;

    @ApiModelProperty("收藏时间")
    private LocalDateTime collectTime;

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public LocalDateTime getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(LocalDateTime collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return "UserCollection{" +
            "collectId = " + collectId +
            ", userId = " + userId +
            ", productId = " + productId +
            ", collectTime = " + collectTime +
        "}";
    }
}
