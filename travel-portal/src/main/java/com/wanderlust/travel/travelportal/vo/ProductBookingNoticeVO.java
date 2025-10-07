package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    
    private LocalDateTime createTime;
    
    private LocalDateTime updateTime;
    
    private Long creatorId;
    
    private Byte status;
    
    private Integer sortOrder;
    
    private String title;
    
    private String bookingDeadline;
    
    private String cancellationPolicy;
    
    private String refundPolicy;
    
    private String specialRequirements;
    
    private String contactInfo;
    
    private String emergencyContact;
}
