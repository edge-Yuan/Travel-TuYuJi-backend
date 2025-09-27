package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 产品评价汇总VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEvaluationSummaryVO {
    
    /**
     * 整体满意度百分比
     */
    private Double satisfactionRate;
    
    /**
     * 总评价数
     */
    private Integer totalReviews;
    
    /**
     * 满意度分布
     */
    private List<SatisfactionDistribution> distributionItems;
    
    /**
     * 分项评分
     */
    private List<ScoreItem> scoreItems;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SatisfactionDistribution {
        private String type;
        private String label;
        private Integer count;
        private Double percentage;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScoreItem {
        private String type;
        private Double score;
    }
}
