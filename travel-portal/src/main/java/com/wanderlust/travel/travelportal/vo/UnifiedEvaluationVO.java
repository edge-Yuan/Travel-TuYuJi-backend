package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 统一评价VO
 * 整合套餐评价和订单管理评价的数据结构
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnifiedEvaluationVO {
    
    // 基础评价字段
    private Long evalId;
    private Long orderId;
    private Long productId;
    private Long userId;
    
    // 用户信息字段
    private String userName;
    private String userAvatar;
    private String userType;
    
    // 评价内容字段
    private Byte overallScore;
    private Byte serviceScore;
    private Byte environmentScore;
    private Byte costEffScore;
    private String content;
    private String imgUrls;
    
    // 时间字段
    private LocalDateTime evalTime;
    private LocalDateTime createTime;
    
    // 回复字段
    private String replyContent;
    private LocalDateTime replyTime;
    
    // 统计字段
    private Integer usefulCount;
    private String source;
    
    // 类型字段
    private Byte evalType;  // 1-产品评价，2-导游评价
    private Long targetId;  // 评价目标ID
    
    // 产品信息字段（可选）
    private String productName;
    private String productImage;
    
    // 订单信息字段（可选）
    private String orderNumber;
    private String orderStatus;
}
