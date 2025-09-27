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
 * 旅游订单表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("tour_order")
@ApiModel(value = "TourOrder对象", description = "旅游订单表")
public class TourOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单唯一标识")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @ApiModelProperty("下单游客")
    private Long userId;

    @ApiModelProperty("购买产品")
    private Long productId;

    @ApiModelProperty("绑定导游")
    private Long guideId;

    @ApiModelProperty("订单总金额")
    private BigDecimal orderAmount;

    @ApiModelProperty("支付方式：1-微信，2-支付宝")
    private Byte payType;

    @ApiModelProperty("支付状态：0-待支付，1-已支付，2-已退款")
    private Byte payStatus;

    @ApiModelProperty("订单状态：0-待确认，1-已确认，2-已完成，3-已取消，4-退款中")
    private Byte orderStatus;

    @ApiModelProperty("预订使用日期")
    private LocalDate bookingDate;

    @ApiModelProperty("出行人数")
    private Integer personCount;

    @ApiModelProperty("特殊需求")
    private String specialNeeds;

    @ApiModelProperty("订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty("取消时间")
    private LocalDateTime cancelTime;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

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

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public LocalDateTime getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(LocalDateTime cancelTime) {
        this.cancelTime = cancelTime;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    @Override
    public String toString() {
        return "TourOrder{" +
            "orderId = " + orderId +
            ", userId = " + userId +
            ", productId = " + productId +
            ", guideId = " + guideId +
            ", orderAmount = " + orderAmount +
            ", payType = " + payType +
            ", payStatus = " + payStatus +
            ", orderStatus = " + orderStatus +
            ", bookingDate = " + bookingDate +
            ", personCount = " + personCount +
            ", specialNeeds = " + specialNeeds +
            ", createTime = " + createTime +
            ", payTime = " + payTime +
            ", cancelTime = " + cancelTime +
            ", refundAmount = " + refundAmount +
        "}";
    }
}
