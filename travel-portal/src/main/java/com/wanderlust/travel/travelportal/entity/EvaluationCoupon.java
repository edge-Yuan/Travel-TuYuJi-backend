package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 评价优惠券实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("evaluation_coupon")
@ApiModel(value = "EvaluationCoupon对象", description = "评价优惠券表")
public class EvaluationCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("优惠券ID")
    @TableId(value = "coupon_id", type = IdType.AUTO)
    private Long couponId;

    @ApiModelProperty("评价ID")
    private Long evalId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("优惠券编码")
    private String couponCode;

    @ApiModelProperty("优惠券金额")
    private BigDecimal amount;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("领取时间")
    private LocalDateTime claimTime;

    @ApiModelProperty("过期时间")
    private LocalDateTime expireTime;

    @ApiModelProperty("使用时间")
    private LocalDateTime usedTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;
}
