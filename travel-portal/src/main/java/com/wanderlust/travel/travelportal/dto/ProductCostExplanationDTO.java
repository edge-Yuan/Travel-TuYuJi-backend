package com.wanderlust.travel.travelportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 产品费用说明DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCostExplanationDTO {
    
    @NotNull(message = "产品ID不能为空")
    private Long productId;
    
    @NotBlank(message = "包含费用不能为空")
    private String includeItems;
    
    @NotBlank(message = "不含费用不能为空")
    private String excludeItems;
    
    @NotBlank(message = "退改政策不能为空")
    private String refundPolicy;
}
