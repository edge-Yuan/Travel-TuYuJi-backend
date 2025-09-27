package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 退款申请表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("refund_apply")
@ApiModel(value = "RefundApply对象", description = "退款申请表")
public class RefundApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("退款申请唯一标识")
    @TableId(value = "refund_id", type = IdType.AUTO)
    private Long refundId;

    @ApiModelProperty("关联订单")
    private Long orderId;

    @ApiModelProperty("申请游客")
    private Long userId;

    @ApiModelProperty("申请退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款原因")
    private String refundReason;

    @ApiModelProperty("证明材料")
    private String proofUrls;

    @ApiModelProperty("审核状态：0-待审核，1-已同意，2-已拒绝")
    private Byte refundStatus;
    
    @ApiModelProperty("审核状态：0-待审核，1-已通过，2-已驳回")
    private Byte auditStatus;

    @ApiModelProperty("审核人")
    private Long auditorId;

    @ApiModelProperty("审核备注")
    private String auditRemark;

    @ApiModelProperty("申请时间")
    private LocalDateTime applyTime;

    @ApiModelProperty("审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty("退款到账时间")
    private LocalDateTime refundTime;

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getProofUrls() {
        return proofUrls;
    }

    public void setProofUrls(String proofUrls) {
        this.proofUrls = proofUrls;
    }

    public Byte getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Byte refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public LocalDateTime getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(LocalDateTime refundTime) {
        this.refundTime = refundTime;
    }
    
    public Byte getAuditStatus() {
        return auditStatus;
    }
    
    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Override
    public String toString() {
        return "RefundApply{" +
            "refundId = " + refundId +
            ", orderId = " + orderId +
            ", userId = " + userId +
            ", refundAmount = " + refundAmount +
            ", refundReason = " + refundReason +
            ", proofUrls = " + proofUrls +
            ", refundStatus = " + refundStatus +
            ", auditorId = " + auditorId +
            ", auditRemark = " + auditRemark +
            ", applyTime = " + applyTime +
            ", auditTime = " + auditTime +
            ", refundTime = " + refundTime +
        "}";
    }
}
