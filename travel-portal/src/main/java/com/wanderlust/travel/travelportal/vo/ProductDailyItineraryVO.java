package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品每日行程VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDailyItineraryVO {
    
    private Long itineraryId;
    
    private Long productId;
    
    private Integer daySeq;
    
    private String title;
    
    private String description;
    
    private String meals;
    
    private String traffic;
    
    private String accommodation;
    
    private String timePeriod;
}
