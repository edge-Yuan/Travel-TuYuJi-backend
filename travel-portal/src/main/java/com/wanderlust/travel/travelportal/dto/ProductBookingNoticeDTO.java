package com.wanderlust.travel.travelportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 产品预订须知DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBookingNoticeDTO {
    
    @NotNull(message = "产品ID不能为空")
    private Long productId;
    
    @NotBlank(message = "预订条件不能为空")
    private String bookingConditions;
    
    @NotBlank(message = "有效期不能为空")
    private String validityPeriod;
    
    private String notes;
}
