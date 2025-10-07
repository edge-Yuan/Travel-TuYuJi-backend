package com.wanderlust.travel.traveladmin.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 产品VO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ProductVO {

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品类型
     */
    private Integer productType;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer soldCount;

    /**
     * 产品状态
     */
    private Integer productStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 主图URL
     */
    private String mainImgUrl;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 产品标签
     */
    private String productTags;

    /**
     * 服务保障
     */
    private String serviceGuarantees;

    /**
     * 产品卖点
     */
    private String productSellingPoints;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 行程安排
     */
    private List<ItineraryVO> itineraries;

    /**
     * 费用说明
     */
    private CostExplanationVO costExplanation;

    /**
     * 预订须知
     */
    private BookingNoticeVO bookingNotice;

    /**
     * 产品图片列表
     */
    private List<ProductImageVO> images;

    /**
     * 景点清单
     */
    private String attractions;

    /**
     * 餐饮标准
     */
    private String mealStandard;

    /**
     * 住宿标准
     */
    private String accommodationStandard;

    /**
     * 有效期
     */
    private List<String> validityPeriod;

    /**
     * 入场时间
     */
    private String entryTime;

    /**
     * 房间类型
     */
    private String roomType;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 天数
     */
    private Integer days;

    /**
     * 设施
     */
    private List<String> facilities;

    /**
     * 行程VO
     */
    @Data
    public static class ItineraryVO {
        /**
         * 第几天
         */
        private Integer daySeq;

        /**
         * 行程详情
         */
        private List<ItineraryDetailVO> itineraries;
    }

    /**
     * 行程详情VO
     */
    @Data
    public static class ItineraryDetailVO {
        /**
         * 时间段
         */
        private String timePeriod;

        /**
         * 标题
         */
        private String title;

        /**
         * 描述
         */
        private String description;

        /**
         * 餐饮
         */
        private String meals;

        /**
         * 交通
         */
        private String traffic;

        /**
         * 住宿
         */
        private String accommodation;
    }

    /**
     * 费用说明VO
     */
    @Data
    public static class CostExplanationVO {
        /**
         * 成人价格
         */
        private BigDecimal adultPrice;

        /**
         * 儿童价格
         */
        private BigDecimal childPrice;

        /**
         * 婴儿价格
         */
        private BigDecimal infantPrice;

        /**
         * 单房差
         */
        private BigDecimal singleRoomSupplement;

        /**
         * 费用包含
         */
        private String includeItems;

        /**
         * 费用不含
         */
        private String excludeItems;

        /**
         * 可选项目
         */
        private String optionalItems;

        /**
         * 支付方式
         */
        private String paymentTerms;

        /**
         * 退款政策
         */
        private String refundPolicy;
    }

    /**
     * 预订须知VO
     */
    @Data
    public static class BookingNoticeVO {
        /**
         * 预订条件
         */
        private String bookingConditions;

        /**
         * 有效期
         */
        private String validityPeriod;

        /**
         * 预订截止时间
         */
        private String bookingDeadline;

        /**
         * 取消政策
         */
        private String cancellationPolicy;

        /**
         * 退款政策
         */
        private String refundPolicy;

        /**
         * 特殊要求
         */
        private String specialRequirements;

        /**
         * 联系信息
         */
        private String contactInfo;

        /**
         * 紧急联系
         */
        private String emergencyContact;

        /**
         * 其他注意事项
         */
        private String notes;
    }

    /**
     * 产品图片VO
     */
    @Data
    public static class ProductImageVO {
        /**
         * 图片ID
         */
        private Long imageId;

        /**
         * 产品ID
         */
        private Long productId;

        /**
         * 图片URL
         */
        private String url;

        /**
         * 图片类型
         */
        private String imageType;

        /**
         * 是否主图
         */
        private Boolean isMain;

        /**
         * 上传时间
         */
        private LocalDateTime uploadTime;
    }
}
