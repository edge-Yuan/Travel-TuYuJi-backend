package com.wanderlust.travel.travelportal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 评价优惠券VO
 */
@Data
@ApiModel(value = "EvaluationCouponVO", description = "评价优惠券响应对象")
public class EvaluationCouponVO {

    @ApiModelProperty("优惠券ID")
    private Long couponId;

    @ApiModelProperty("优惠券编码")
    private String couponCode;

    @ApiModelProperty("优惠券金额")
    private BigDecimal amount;

    @ApiModelProperty("过期时间")
    private LocalDateTime expireTime;

    @ApiModelProperty("状态")
    private String status;
}

/**
 * 优惠券领取状态VO
 */
@Data
@ApiModel(value = "CouponClaimStatusVO", description = "优惠券领取状态响应对象")
class CouponClaimStatusVO {

    @ApiModelProperty("是否可以领取")
    private Boolean canClaim;

    @ApiModelProperty("是否已领取")
    private Boolean claimed;

    @ApiModelProperty("优惠券ID")
    private Long couponId;

    @ApiModelProperty("领取时间")
    private LocalDateTime claimTime;
}
