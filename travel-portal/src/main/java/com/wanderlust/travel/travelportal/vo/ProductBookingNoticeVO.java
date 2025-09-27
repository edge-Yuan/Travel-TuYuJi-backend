package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品预订须知VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductBookingNoticeVO {
    
    private Long noticeId;
    
    private Long productId;
    
    private String bookingConditions;
    
    private String validityPeriod;
    
    private String notes;
}
