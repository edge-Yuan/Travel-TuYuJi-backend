package com.wanderlust.travel.traveladmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2025-01-15
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
    private BigDecimal totalPrice;

    @ApiModelProperty("支付方式：1-微信，2-支付宝，3-银行卡")
    private Byte payType;

    @ApiModelProperty("支付状态：0-待支付，1-已支付，2-已退款")
    private Byte payStatus;

    @ApiModelProperty("订单状态：0-待确认，1-已确认，2-已完成，3-已取消，4-退款中")
    private Byte orderStatus;

    @ApiModelProperty("预订使用日期")
    private LocalDate bookingDate;

    @ApiModelProperty("出行人数")
    @TableField("person_count")
    private Integer travellers;

    // 为兼容历史库结构：同时写入旧列 travellers（非空约束，无默认值）
    @TableField("travellers")
    private Integer travellersLegacy;

    @ApiModelProperty("特殊需求")
    private String specialNeeds;

    @ApiModelProperty("收货人姓名")
    private String receiverName;

    @ApiModelProperty("收货人电话")
    private String receiverPhone;

    @ApiModelProperty("收货省份")
    private String receiverProvince;

    @ApiModelProperty("收货城市")
    private String receiverCity;

    @ApiModelProperty("收货区县")
    private String receiverDistrict;

    @ApiModelProperty("详细收货地址")
    private String receiverAddress;

    @ApiModelProperty("订单备注")
    private String orderRemark;

    @ApiModelProperty("使用的优惠券ID")
    private Long couponId;

    @ApiModelProperty("优惠券折扣金额")
    private BigDecimal couponDiscount;

    @ApiModelProperty("运费")
    private BigDecimal shippingFee;

    @ApiModelProperty("商品数量")
    private Integer productQuantity;

    @ApiModelProperty("订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty("取消时间")
    private LocalDateTime cancelTime;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("扫码时间")
    private LocalDateTime scanTime;

    @ApiModelProperty("扫码状态：0-未扫码，1-已扫码")
    private Byte scanStatus;

    @ApiModelProperty("微信支付交易号")
    private String wechatTradeNo;

    @ApiModelProperty("微信支付状态")
    private String wechatTradeStatus;

    @ApiModelProperty("扫码确认时间")
    private LocalDateTime scanConfirmTime;

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public Integer getTravellers() {
        return travellers;
    }

    public void setTravellers(Integer travellers) {
        this.travellers = travellers;
    }

    public Integer getTravellersLegacy() {
        return travellersLegacy;
    }

    public void setTravellersLegacy(Integer travellersLegacy) {
        this.travellersLegacy = travellersLegacy;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(BigDecimal couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
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

    public LocalDateTime getScanTime() {
        return scanTime;
    }

    public void setScanTime(LocalDateTime scanTime) {
        this.scanTime = scanTime;
    }

    public Byte getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(Byte scanStatus) {
        this.scanStatus = scanStatus;
    }

    public String getWechatTradeNo() {
        return wechatTradeNo;
    }

    public void setWechatTradeNo(String wechatTradeNo) {
        this.wechatTradeNo = wechatTradeNo;
    }

    public String getWechatTradeStatus() {
        return wechatTradeStatus;
    }

    public void setWechatTradeStatus(String wechatTradeStatus) {
        this.wechatTradeStatus = wechatTradeStatus;
    }

    public LocalDateTime getScanConfirmTime() {
        return scanConfirmTime;
    }

    public void setScanConfirmTime(LocalDateTime scanConfirmTime) {
        this.scanConfirmTime = scanConfirmTime;
    }

    @Override
    public String toString() {
        return "TourOrder{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", guideId=" + guideId +
                ", totalPrice=" + totalPrice +
                ", payType=" + payType +
                ", payStatus=" + payStatus +
                ", orderStatus=" + orderStatus +
                ", bookingDate=" + bookingDate +
                ", travellers=" + travellers +
                ", specialNeeds='" + specialNeeds + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", createTime=" + createTime +
                ", payTime=" + payTime +
                ", cancelTime=" + cancelTime +
                '}';
    }
}
