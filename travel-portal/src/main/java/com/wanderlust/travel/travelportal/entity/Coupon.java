package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 优惠券表
 * </p>
 *
 * @author wanderlust
 * @since 2025-01-15
 */
@TableName("coupon")
@ApiModel(value = "Coupon对象", description = "优惠券表")
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("优惠券唯一标识")
    @TableId(value = "coupon_id", type = IdType.AUTO)
    private Long couponId;

    @ApiModelProperty("优惠券名称")
    @TableField("coupon_name")
    private String couponName;

    @ApiModelProperty("优惠券类型：1-满减券，2-折扣券")
    @TableField("coupon_type")
    private Byte couponType;

    @ApiModelProperty("优惠金额或折扣值")
    @TableField("discount_value")
    private BigDecimal discountValue;

    @ApiModelProperty("最低消费金额")
    @TableField("min_spend")
    private BigDecimal minSpend;

    @ApiModelProperty("最大折扣金额（折扣券使用）")
    @TableField("max_discount")
    private BigDecimal maxDiscount;

    @ApiModelProperty("有效期开始日期")
    @TableField("start_date")
    private LocalDate startDate;

    @ApiModelProperty("有效期结束日期")
    @TableField("end_date")
    private LocalDate endDate;

    @ApiModelProperty("发放总数")
    @TableField("total_count")
    private Integer totalCount;

    @ApiModelProperty("已使用数量")
    @TableField("used_count")
    private Integer usedCount;

    @ApiModelProperty("状态：0-禁用，1-启用")
    @TableField("status")
    private Byte status;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Byte getCouponType() {
        return couponType;
    }

    public void setCouponType(Byte couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getMinSpend() {
        return minSpend;
    }

    public void setMinSpend(BigDecimal minSpend) {
        this.minSpend = minSpend;
    }

    public BigDecimal getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(BigDecimal maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Coupon{" +
            "couponId=" + couponId +
            ", couponName=" + couponName +
            ", couponType=" + couponType +
            ", discountValue=" + discountValue +
            ", minSpend=" + minSpend +
            ", maxDiscount=" + maxDiscount +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", totalCount=" + totalCount +
            ", usedCount=" + usedCount +
            ", status=" + status +
            ", createTime=" + createTime +
        "}";
    }
}
