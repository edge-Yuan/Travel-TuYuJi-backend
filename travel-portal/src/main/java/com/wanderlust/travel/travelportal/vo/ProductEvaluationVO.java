package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 产品评价VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvaluationVO {
    
    private Long evalId;
    
    private Long orderId;
    
    private Long productId;
    
    private Long userId;
    
    private String userName;
    
    private String userAvatar;
    
    private String userType;
    
    private Byte overallScore;
    
    private Byte serviceScore;
    
    private Byte environmentScore;
    
    private Byte costEffScore;
    
    private String content;
    
    private String imgUrls;
    
    private LocalDateTime evalTime;
    
    private String replyContent;
    
    private LocalDateTime replyTime;
    
    private Integer usefulCount;
    
    private String source;
}
