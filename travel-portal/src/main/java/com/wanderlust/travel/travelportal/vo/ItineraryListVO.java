package com.wanderlust.travel.travelportal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 行程列表VO
 * @author wanderlust
 */
@Data
@ApiModel(description = "行程列表VO")
public class ItineraryListVO {

    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("订单编号")
    private String orderNumber;

    @ApiModelProperty("行程标题")
    private String title;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("开始日期")
    private LocalDate startDate;

    @ApiModelProperty("结束日期")
    private LocalDate endDate;

    @ApiModelProperty("出行人数")
    private Integer travelers;

    @ApiModelProperty("订单状态：0-待确认，1-已确认，2-已完成，3-已取消，4-退款中")
    private Byte orderStatus;

    @ApiModelProperty("支付状态：0-待支付，1-已支付，2-已退款")
    private Byte payStatus;

    @ApiModelProperty("总价格")
    private BigDecimal totalPrice;

    @ApiModelProperty("导游信息")
    private GuideBasicInfo guide;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品图片")
    private String productImage;

    @Data
    @ApiModel(description = "导游基本信息")
    public static class GuideBasicInfo {
        @ApiModelProperty("导游ID")
        private Long guideId;

        @ApiModelProperty("导游姓名")
        private String name;

        @ApiModelProperty("头像")
        private String avatar;

        @ApiModelProperty("评分")
        private BigDecimal rating;

        @ApiModelProperty("联系电话")
        private String phone;

        @ApiModelProperty("微信")
        private String wechat;

        @ApiModelProperty("服务类型")
        private String serviceType;

        @ApiModelProperty("服务语言")
        private String languages;
    }
}
