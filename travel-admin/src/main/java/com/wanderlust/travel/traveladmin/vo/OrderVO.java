package com.wanderlust.travel.traveladmin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单视图对象
 * 
 * @author wanderlust
 * @since 2025-01-15
 */
@Data
@ApiModel(description = "订单信息")
public class OrderVO {

    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("客户姓名")
    private String customerName;

    @ApiModelProperty("客户电话")
    private String customerPhone;

    @ApiModelProperty("客户邮箱")
    private String customerEmail;

    @ApiModelProperty("客户身份证号")
    private String customerIdCard;

    @ApiModelProperty("紧急联系人")
    private String emergencyContact;

    @ApiModelProperty("紧急联系电话")
    private String emergencyPhone;

    @ApiModelProperty("产品ID")
    private Long productId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品类型")
    private String productType;

    @ApiModelProperty("产品图片")
    private String productImage;

    @ApiModelProperty("产品描述")
    private String productDescription;

    @ApiModelProperty("导游ID")
    private Long guideId;

    @ApiModelProperty("导游姓名")
    private String guideName;

    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmount;

    @ApiModelProperty("实付金额")
    private BigDecimal paidAmount;

    @ApiModelProperty("支付方式")
    private String paymentMethod;

    @ApiModelProperty("支付状态")
    private String payStatus;

    @ApiModelProperty("订单状态")
    private String orderStatus;

    @ApiModelProperty("预订使用日期")
    private LocalDate bookingDate;

    @ApiModelProperty("使用时间")
    private String useTime;

    @ApiModelProperty("出行人数")
    private Integer travellers;

    @ApiModelProperty("商品数量")
    private Integer productQuantity;

    @ApiModelProperty("特殊需求")
    private String specialNeeds;

    @ApiModelProperty("订单备注")
    private String orderRemark;

    @ApiModelProperty("收货人姓名")
    private String receiverName;

    @ApiModelProperty("收货人电话")
    private String receiverPhone;

    @ApiModelProperty("收货地址")
    private String receiverAddress;

    @ApiModelProperty("订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty("取消时间")
    private LocalDateTime cancelTime;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("订单变更记录")
    private List<OrderChangeVO> changes;

    @ApiModelProperty("退款记录")
    private List<RefundRecordVO> refunds;
}
