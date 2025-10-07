package com.wanderlust.travel.traveladmin.dto;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 产品创建DTO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ProductCreateDTO {

    /**
     * 产品类型：1-旅行路线，2-酒店客房，3-景区门票
     */
    @NotNull(message = "产品类型不能为空")
    @Min(value = 1, message = "产品类型无效")
    @Max(value = 3, message = "产品类型无效")
    private Integer productType;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空")
    @Size(max = 200, message = "产品名称长度不能超过200个字符")
    private String productName;

    /**
     * 价格
     */
    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "0.01", message = "价格必须大于0")
    private BigDecimal price;

    /**
     * 库存
     */
    @NotNull(message = "库存不能为空")
    @Min(value = 0, message = "库存不能为负数")
    private Integer stock;

    /**
     * 产品生效日期（可选，为空时使用默认值）
     */
    private LocalDate startDate;

    /**
     * 产品失效日期（可选，为空时使用默认值）
     */
    private LocalDate endDate;

    /**
     * 产品描述
     */
    @Size(max = 2000, message = "产品描述长度不能超过2000个字符")
    private String description;

    /**
     * 产品标签
     */
    @Size(max = 500, message = "产品标签长度不能超过500个字符")
    private String productTags;

    /**
     * 服务保障
     */
    @Size(max = 500, message = "服务保障长度不能超过500个字符")
    private String serviceGuarantees;

    /**
     * 产品卖点
     */
    @Size(max = 1000, message = "产品卖点长度不能超过1000个字符")
    private String productSellingPoints;

    /**
     * 供应商
     */
    @Size(max = 200, message = "供应商名称长度不能超过200个字符")
    private String supplier;

    /**
     * 房间类型
     */
    @Size(max = 100, message = "房间类型长度不能超过100个字符")
    private String roomType;

    /**
     * 容量
     */
    @Min(value = 1, message = "容量必须大于0")
    private Integer capacity;

    /**
     * 设施
     */
    private List<String> facilities;

    /**
     * 天数
     */
    @Min(value = 1, message = "天数必须大于0")
    private Integer days;

    /**
     * 景点
     */
    @Size(max = 500, message = "景点描述长度不能超过500个字符")
    private String attractions;

    /**
     * 餐饮标准
     */
    @Size(max = 200, message = "餐饮标准长度不能超过200个字符")
    private String mealStandard;

    /**
     * 住宿标准
     */
    @Size(max = 200, message = "住宿标准长度不能超过200个字符")
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
     * 行程安排
     */
    private List<ItineraryDTO> itineraries;

    /**
     * 费用说明
     */
    private CostExplanationDTO costExplanation;

    /**
     * 预订须知
     */
    private BookingNoticeDTO bookingNotice;

    /**
     * 行程DTO
     */
    @Data
    public static class ItineraryDTO {
        /**
         * 第几天
         */
        @NotNull(message = "天数不能为空")
        @Min(value = 1, message = "天数必须大于0")
        private Integer daySeq;

        /**
         * 行程详情
         */
        @NotEmpty(message = "行程详情不能为空")
        private List<ItineraryDetailDTO> itineraries;
    }

    /**
     * 行程详情DTO
     */
    @Data
    public static class ItineraryDetailDTO {
        /**
         * 时间段
         */
        @Size(max = 20, message = "时间段长度不能超过20个字符")
        private String timePeriod;

        /**
         * 标题
         */
        @Size(max = 200, message = "标题长度不能超过200个字符")
        private String title;

        /**
         * 描述
         */
        @Size(max = 1000, message = "描述长度不能超过1000个字符")
        private String description;

        /**
         * 餐饮
         */
        @Size(max = 200, message = "餐饮描述长度不能超过200个字符")
        private String meals;

        /**
         * 交通
         */
        @Size(max = 200, message = "交通描述长度不能超过200个字符")
        private String traffic;

        /**
         * 住宿
         */
        @Size(max = 200, message = "住宿描述长度不能超过200个字符")
        private String accommodation;
    }

    /**
     * 费用说明DTO
     */
    @Data
    public static class CostExplanationDTO {
        /**
         * 成人价格
         */
        @DecimalMin(value = "0.00", message = "成人价格不能为负数")
        private BigDecimal adultPrice;

        /**
         * 儿童价格
         */
        @DecimalMin(value = "0.00", message = "儿童价格不能为负数")
        private BigDecimal childPrice;

        /**
         * 婴儿价格
         */
        @DecimalMin(value = "0.00", message = "婴儿价格不能为负数")
        private BigDecimal infantPrice;

        /**
         * 单房差
         */
        @DecimalMin(value = "0.00", message = "单房差不能为负数")
        private BigDecimal singleRoomSupplement;

        /**
         * 费用包含
         */
        @Size(max = 1000, message = "费用包含描述长度不能超过1000个字符")
        private String includeItems;

        /**
         * 费用不含
         */
        @Size(max = 1000, message = "费用不含描述长度不能超过1000个字符")
        private String excludeItems;

        /**
         * 可选项目
         */
        @Size(max = 500, message = "可选项目描述长度不能超过500个字符")
        private String optionalItems;

        /**
         * 支付方式
         */
        @Size(max = 500, message = "支付方式描述长度不能超过500个字符")
        private String paymentTerms;

        /**
         * 退款政策
         */
        @Size(max = 500, message = "退款政策描述长度不能超过500个字符")
        private String refundPolicy;
    }

    /**
     * 预订须知DTO
     */
    @Data
    public static class BookingNoticeDTO {
        /**
         * 预订条件
         */
        @Size(max = 1000, message = "预订条件描述长度不能超过1000个字符")
        private String bookingConditions;

        /**
         * 有效期
         */
        @Size(max = 200, message = "有效期描述长度不能超过200个字符")
        private String validityPeriod;

        /**
         * 预订截止时间
         */
        @Size(max = 200, message = "预订截止时间描述长度不能超过200个字符")
        private String bookingDeadline;

        /**
         * 取消政策
         */
        @Size(max = 1000, message = "取消政策描述长度不能超过1000个字符")
        private String cancellationPolicy;

        /**
         * 退款政策
         */
        @Size(max = 1000, message = "退款政策描述长度不能超过1000个字符")
        private String refundPolicy;

        /**
         * 特殊要求
         */
        @Size(max = 1000, message = "特殊要求描述长度不能超过1000个字符")
        private String specialRequirements;

        /**
         * 联系信息
         */
        @Size(max = 500, message = "联系信息描述长度不能超过500个字符")
        private String contactInfo;

        /**
         * 紧急联系
         */
        @Size(max = 500, message = "紧急联系描述长度不能超过500个字符")
        private String emergencyContact;

        /**
         * 其他注意事项
         */
        @Size(max = 1000, message = "其他注意事项描述长度不能超过1000个字符")
        private String notes;
    }
}
