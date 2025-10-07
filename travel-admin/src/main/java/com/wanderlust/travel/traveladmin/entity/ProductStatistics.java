package com.wanderlust.travel.traveladmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 产品统计数据实体类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_statistics")
public class ProductStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统计ID
     */
    @TableId(value = "stat_id", type = IdType.AUTO)
    private Long statId;

    /**
     * 商家ID
     */
    @TableField("merchant_id")
    private Long merchantId;

    /**
     * 统计日期
     */
    @TableField("stat_date")
    private LocalDate statDate;

    /**
     * 总产品数
     */
    @TableField("total_products")
    private Integer totalProducts;

    /**
     * 上架产品数
     */
    @TableField("active_products")
    private Integer activeProducts;

    /**
     * 下架产品数
     */
    @TableField("inactive_products")
    private Integer inactiveProducts;

    /**
     * 总销量
     */
    @TableField("total_sales")
    private Integer totalSales;

    /**
     * 总收入
     */
    @TableField("total_revenue")
    private BigDecimal totalRevenue;

    /**
     * 平均价格
     */
    @TableField("average_price")
    private BigDecimal averagePrice;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
