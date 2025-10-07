package com.wanderlust.travel.traveladmin.dto;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 产品更新DTO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ProductUpdateDTO {

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
     * 产品生效日期（可选）
     */
    private LocalDate startDate;

    /**
     * 产品失效日期（可选）
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
    private List<ProductCreateDTO.ItineraryDTO> itineraries;

    /**
     * 费用说明
     */
    private ProductCreateDTO.CostExplanationDTO costExplanation;

    /**
     * 预订须知
     */
    private ProductCreateDTO.BookingNoticeDTO bookingNotice;
}
