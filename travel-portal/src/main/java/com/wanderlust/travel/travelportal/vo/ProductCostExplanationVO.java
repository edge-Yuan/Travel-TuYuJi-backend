package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品费用说明VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCostExplanationVO {
    
    private Long costId;
    
    private Long productId;
    
    private String includeItems;
    
    private String excludeItems;
    
    private String refundPolicy;
}
