package com.wanderlust.travel.travelportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.wanderlust.travel.travelportal.common.validation.ValidTimePeriod;

/**
 * 产品每日行程DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDailyItineraryDTO {
    
    @NotNull(message = "产品ID不能为空")
    private Long productId;
    
    @NotNull(message = "天数序号不能为空")
    private Integer daySeq;
    
    @NotBlank(message = "当日标题不能为空")
    private String title;
    
    @NotBlank(message = "当日详情不能为空")
    private String description;
    
    private String meals;
    
    private String traffic;
    
    private String accommodation;
    
    @ValidTimePeriod
    private String timePeriod;
}
