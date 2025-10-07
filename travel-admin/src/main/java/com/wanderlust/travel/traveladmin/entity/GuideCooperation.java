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
 * 导游合作邀请实体类
 * 对应数据库表：guide_cooperation
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("guide_cooperation")
public class GuideCooperation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 合作邀请唯一标识
     */
    @TableId(value = "cooperation_id", type = IdType.AUTO)
    private Long cooperationId;

    /**
     * 发起邀请的旅行商ID
     */
    @TableField("merchant_id")
    private Long merchantId;

    /**
     * 被邀请的导游ID
     */
    @TableField("guide_id")
    private Long guideId;

    /**
     * 合作类型：1-独家合作，2-合作伙伴，3-项目合作
     */
    @TableField("cooperation_type")
    private Byte cooperationType;

    /**
     * 合作项目ID列表（JSON格式）
     */
    @TableField("project_ids")
    private String projectIds;

    /**
     * 合作开始日期
     */
    @TableField("start_date")
    private LocalDate startDate;

    /**
     * 合作结束日期
     */
    @TableField("end_date")
    private LocalDate endDate;

    /**
     * 合作条件
     */
    @TableField("conditions")
    private String conditions;

    /**
     * 备注
     */
    @TableField("notes")
    private String notes;

    /**
     * 合作状态：0-待确认，1-已接受，2-已拒绝，3-已结束
     */
    @TableField("status")
    private Byte status;

    /**
     * 拒绝原因
     */
    @TableField("reject_reason")
    private String rejectReason;

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

    /**
     * 响应时间
     */
    @TableField("response_time")
    private LocalDateTime responseTime;
}
