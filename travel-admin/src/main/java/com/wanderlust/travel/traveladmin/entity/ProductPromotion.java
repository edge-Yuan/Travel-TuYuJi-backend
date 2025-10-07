package com.wanderlust.travel.traveladmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 产品优惠活动实体类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_promotion")
public class ProductPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @TableId(value = "promotion_id", type = IdType.AUTO)
    private Long promotionId;

    /**
     * 商家ID
     */
    @TableField("merchant_id")
    private Long merchantId;

    /**
     * 活动类型：discount-折扣，buy2get1-买二送一，coupon-满减券，earlybird-早鸟价
     */
    @TableField("type")
    private String type;

    /**
     * 活动名称
     */
    @TableField("name")
    private String name;

    /**
     * 折扣率（如：20.00表示8折）
     */
    @TableField("discount")
    private BigDecimal discount;

    /**
     * 满减条件-最低金额
     */
    @TableField("min_amount")
    private BigDecimal minAmount;

    /**
     * 满减条件-减免金额
     */
    @TableField("discount_amount")
    private BigDecimal discountAmount;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 状态：0-禁用，1-启用
     */
    @TableField("status")
    private Integer status;

    /**
     * 活动描述
     */
    @TableField("description")
    private String description;

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
