package com.wanderlust.travel.traveladmin.dto;

import lombok.Data;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 优惠活动创建DTO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class PromotionCreateDTO {

    /**
     * 活动类型：discount-折扣，buy2get1-买二送一，coupon-满减券，earlybird-早鸟价
     */
    @NotBlank(message = "活动类型不能为空")
    @Pattern(regexp = "^(discount|buy2get1|coupon|earlybird)$", message = "活动类型无效")
    private String type;

    /**
     * 活动名称
     */
    @NotBlank(message = "活动名称不能为空")
    @Size(max = 200, message = "活动名称长度不能超过200个字符")
    private String name;

    /**
     * 折扣率（如：20.00表示8折）
     */
    @DecimalMin(value = "0.01", message = "折扣率必须大于0")
    @DecimalMax(value = "99.99", message = "折扣率不能超过99.99")
    private BigDecimal discount;

    /**
     * 满减条件-最低金额
     */
    @DecimalMin(value = "0.01", message = "最低金额必须大于0")
    private BigDecimal minAmount;

    /**
     * 满减条件-减免金额
     */
    @DecimalMin(value = "0.01", message = "减免金额必须大于0")
    private BigDecimal discountAmount;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    @Future(message = "开始时间必须是未来时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空")
    @Future(message = "结束时间必须是未来时间")
    private LocalDateTime endTime;

    /**
     * 关联产品ID列表
     */
    @NotEmpty(message = "关联产品不能为空")
    private List<Long> productIds;

    /**
     * 活动描述
     */
    @Size(max = 1000, message = "活动描述长度不能超过1000个字符")
    private String description;
}
