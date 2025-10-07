package com.wanderlust.travel.travelportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 统一评价DTO
 * 整合套餐评价和订单管理评价的请求数据结构
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnifiedEvaluationDTO {
    
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
    
    // 评价类型：1-产品评价，2-导游评价
    @Builder.Default
    private Byte evalType = 1;
    
    // 评价目标ID（产品ID或导游ID）
    private Long targetId;
    
    // 用户类型（可选）
    private String userType;
    
    // 评价来源（可选）
    private String source;
}
