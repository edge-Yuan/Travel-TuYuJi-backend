package com.wanderlust.travel.traveladmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 产品预订须知实体类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_booking_notice")
public class ProductBookingNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 须知ID
     */
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Long noticeId;

    /**
     * 关联产品
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 预订条件（如：需提供身份证号）
     */
    @TableField("booking_conditions")
    private String bookingConditions;

    /**
     * 有效期（如：购买后30天内有效）
     */
    @TableField("validity_period")
    private String validityPeriod;

    /**
     * 预订截止时间
     */
    @TableField("booking_deadline")
    private String bookingDeadline;

    /**
     * 取消政策
     */
    @TableField("cancellation_policy")
    private String cancellationPolicy;

    /**
     * 退款政策
     */
    @TableField("refund_policy")
    private String refundPolicy;

    /**
     * 特殊要求
     */
    @TableField("special_requirements")
    private String specialRequirements;

    /**
     * 联系方式
     */
    @TableField("contact_info")
    private String contactInfo;

    /**
     * 紧急联系方式
     */
    @TableField("emergency_contact")
    private String emergencyContact;

    /**
     * 其他注意事项
     */
    @TableField("notes")
    private String notes;

    /**
     * 创建人ID
     */
    @TableField("creator_id")
    private Long creatorId;

    /**
     * 状态：0-禁用，1-启用
     */
    @TableField("status")
    private Integer status;

    /**
     * 排序
     */
    @TableField("sort_order")
    private Integer sortOrder;

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
