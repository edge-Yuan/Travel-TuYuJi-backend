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
 * 产品费用说明实体类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_cost_explanation")
public class ProductCostExplanation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 费用ID
     */
    @TableId(value = "cost_id", type = IdType.AUTO)
    private Long costId;

    /**
     * 关联产品
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 成人价格
     */
    @TableField("adult_price")
    private BigDecimal adultPrice;

    /**
     * 儿童价格
     */
    @TableField("child_price")
    private BigDecimal childPrice;

    /**
     * 婴儿价格
     */
    @TableField("infant_price")
    private BigDecimal infantPrice;

    /**
     * 单房差
     */
    @TableField("single_room_supplement")
    private BigDecimal singleRoomSupplement;

    /**
     * 包含费用（如：机票、酒店）
     */
    @TableField("include_items")
    private String includeItems;

    /**
     * 不含费用（如：自费项目、景区小交通）
     */
    @TableField("exclude_items")
    private String excludeItems;

    /**
     * 可选项目
     */
    @TableField("optional_items")
    private String optionalItems;

    /**
     * 支付方式
     */
    @TableField("payment_terms")
    private String paymentTerms;

    /**
     * 退款政策
     */
    @TableField("refund_policy")
    private String refundPolicy;

    /**
     * 价格说明
     */
    @TableField("price_notes")
    private String priceNotes;

    /**
     * 取消政策
     */
    @TableField("cancellation_policy")
    private String cancellationPolicy;

    /**
     * 退款截止时间
     */
    @TableField("refund_deadline")
    private String refundDeadline;

    /**
     * 退款比例
     */
    @TableField("refund_rate")
    private String refundRate;

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
