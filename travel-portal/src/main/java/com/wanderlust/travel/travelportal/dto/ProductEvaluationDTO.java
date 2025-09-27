package com.wanderlust.travel.travelportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 产品评价DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvaluationDTO {
    
    @NotNull(message = "订单ID不能为空")
    private Long orderId;
    
    @NotNull(message = "产品ID不能为空")
    private Long productId;
    
    @NotNull(message = "用户ID不能为空")
    private Long userId;
    
    @NotNull(message = "总体评分不能为空")
    @Min(value = 1, message = "评分不能小于1")
    @Max(value = 5, message = "评分不能大于5")
    private Byte overallScore;
    
    @Min(value = 1, message = "服务评分不能小于1")
    @Max(value = 5, message = "服务评分不能大于5")
    private Byte serviceScore;
    
    @Min(value = 1, message = "环境评分不能小于1")
    @Max(value = 5, message = "环境评分不能大于5")
    private Byte environmentScore;
    
    @Min(value = 1, message = "性价比评分不能小于1")
    @Max(value = 5, message = "性价比评分不能大于5")
    private Byte costEffScore;
    
    private String content;
    
    private String imgUrls;
}
