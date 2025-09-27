package com.wanderlust.travel.travelportal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 行程详情VO
 * @author wanderlust
 */
@Data
@ApiModel(description = "行程详情VO")
public class ItineraryDetailVO {

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

    @ApiModelProperty("产品信息")
    private ProductInfo productInfo;

    @ApiModelProperty("导游信息")
    private GuideInfo guideInfo;

    @ApiModelProperty("行程时间线")
    private List<TimelineEvent> timeline;

    @ApiModelProperty("每日行程安排")
    private List<DailySchedule> dailySchedule;

    @ApiModelProperty("重要提示")
    private String notes;

    @Data
    @ApiModel(description = "产品信息")
    public static class ProductInfo {
        @ApiModelProperty("产品ID")
        private Long productId;

        @ApiModelProperty("产品名称")
        private String productName;

        @ApiModelProperty("产品描述")
        private String description;

        @ApiModelProperty("产品图片")
        private String imgUrls;
    }

    @Data
    @ApiModel(description = "导游信息")
    public static class GuideInfo {
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
        private List<String> languages;
    }

    @Data
    @ApiModel(description = "时间线事件")
    public static class TimelineEvent {
        @ApiModelProperty("事件时间")
        private LocalDateTime time;

        @ApiModelProperty("事件内容")
        private String content;

        @ApiModelProperty("事件类型")
        private String type;

        @ApiModelProperty("事件颜色")
        private String color;
    }

    @Data
    @ApiModel(description = "每日行程安排")
    public static class DailySchedule {
        @ApiModelProperty("日期")
        private LocalDate date;

        @ApiModelProperty("活动列表")
        private List<Activity> activities;
    }

    @Data
    @ApiModel(description = "活动信息")
    public static class Activity {
        @ApiModelProperty("时间")
        private String time;

        @ApiModelProperty("活动名称")
        private String name;

        @ApiModelProperty("活动描述")
        private String description;

        @ApiModelProperty("活动地点")
        private String location;
    }
}
