package com.wanderlust.travel.traveladmin.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 优惠活动VO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class PromotionVO {

    /**
     * 活动ID
     */
    private Long promotionId;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 活动类型
     */
    private String type;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 折扣率
     */
    private BigDecimal discount;

    /**
     * 最低金额
     */
    private BigDecimal minAmount;

    /**
     * 减免金额
     */
    private BigDecimal discountAmount;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 关联产品数量
     */
    private Integer productCount;

    /**
     * 关联产品列表
     */
    private List<ProductSimpleVO> products;

    /**
     * 产品简单信息VO
     */
    @Data
    public static class ProductSimpleVO {
        /**
         * 产品ID
         */
        private Long productId;

        /**
         * 产品名称
         */
        private String productName;

        /**
         * 价格
         */
        private BigDecimal price;

        /**
         * 主图URL
         */
        private String mainImgUrl;
    }
}
