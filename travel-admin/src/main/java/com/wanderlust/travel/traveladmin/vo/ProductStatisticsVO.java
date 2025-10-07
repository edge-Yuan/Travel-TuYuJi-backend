package com.wanderlust.travel.traveladmin.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品统计数据VO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ProductStatisticsVO {

    /**
     * 总产品数
     */
    private Integer totalProducts;

    /**
     * 上架产品数
     */
    private Integer activeProducts;

    /**
     * 下架产品数
     */
    private Integer inactiveProducts;

    /**
     * 总销量
     */
    private Integer totalSales;

    /**
     * 总收入
     */
    private BigDecimal totalRevenue;

    /**
     * 平均价格
     */
    private BigDecimal averagePrice;

    /**
     * 热销产品列表
     */
    private List<TopSellingProductVO> topSellingProducts;

    /**
     * 销售趋势
     */
    private List<SalesTrendVO> salesTrend;

    /**
     * 产品类型统计VO
     */
    @Data
    public static class ProductTypeStatVO {
        /**
         * 产品类型
         */
        private Integer productType;

        /**
         * 类型名称
         */
        private String typeName;

        /**
         * 数量
         */
        private Integer count;

        /**
         * 销量
         */
        private Integer sales;

        /**
         * 收入
         */
        private BigDecimal revenue;
    }

    /**
     * 热销产品VO
     */
    @Data
    public static class TopSellingProductVO {
        /**
         * 产品ID
         */
        private Long productId;

        /**
         * 产品名称
         */
        private String productName;

        /**
         * 销量
         */
        private Integer salesCount;

        /**
         * 收入
         */
        private BigDecimal revenue;
    }

    /**
     * 销售趋势VO
     */
    @Data
    public static class SalesTrendVO {
        /**
         * 日期
         */
        private String date;

        /**
         * 销量
         */
        private Integer sales;

        /**
         * 收入
         */
        private BigDecimal revenue;
    }
}
