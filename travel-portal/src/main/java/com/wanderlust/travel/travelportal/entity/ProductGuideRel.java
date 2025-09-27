package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 产品-导游关联表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("product_guide_rel")
@ApiModel(value = "ProductGuideRel对象", description = "产品-导游关联表")
public class ProductGuideRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关联唯一标识")
    @TableId(value = "rel_id", type = IdType.AUTO)
    private Long relId;

    @ApiModelProperty("关联产品")
    private Long productId;

    @ApiModelProperty("关联导游")
    private Long guideId;

    @ApiModelProperty("导游服务费")
    private BigDecimal coopFee;

    @ApiModelProperty("合作开始日期")
    private LocalDate coopStartDate;

    @ApiModelProperty("合作结束日期")
    private LocalDate coopEndDate;

    @ApiModelProperty("合作状态：0-待确认，1-已绑定，2-已拒绝，3-已结束")
    private Byte coopStatus;

    @ApiModelProperty("拒绝原因")
    private String rejectReason;

    @ApiModelProperty("合作邀请创建时间")
    private LocalDateTime createTime;

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    public BigDecimal getCoopFee() {
        return coopFee;
    }

    public void setCoopFee(BigDecimal coopFee) {
        this.coopFee = coopFee;
    }

    public LocalDate getCoopStartDate() {
        return coopStartDate;
    }

    public void setCoopStartDate(LocalDate coopStartDate) {
        this.coopStartDate = coopStartDate;
    }

    public LocalDate getCoopEndDate() {
        return coopEndDate;
    }

    public void setCoopEndDate(LocalDate coopEndDate) {
        this.coopEndDate = coopEndDate;
    }

    public Byte getCoopStatus() {
        return coopStatus;
    }

    public void setCoopStatus(Byte coopStatus) {
        this.coopStatus = coopStatus;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProductGuideRel{" +
            "relId = " + relId +
            ", productId = " + productId +
            ", guideId = " + guideId +
            ", coopFee = " + coopFee +
            ", coopStartDate = " + coopStartDate +
            ", coopEndDate = " + coopEndDate +
            ", coopStatus = " + coopStatus +
            ", rejectReason = " + rejectReason +
            ", createTime = " + createTime +
        "}";
    }
}
