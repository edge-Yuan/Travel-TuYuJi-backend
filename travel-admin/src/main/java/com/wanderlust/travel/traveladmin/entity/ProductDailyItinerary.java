package com.wanderlust.travel.traveladmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 产品固定每日行程实体类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_daily_itinerary")
public class ProductDailyItinerary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 行程ID
     */
    @TableId(value = "itinerary_id", type = IdType.AUTO)
    private Long itineraryId;

    /**
     * 关联产品
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 第几天（1开始）
     */
    @TableField("day_seq")
    private Integer daySeq;

    /**
     * 时间段：早上、中午、下午、晚上、全天
     */
    @TableField("time_period")
    private String timePeriod;

    /**
     * 当日标题（如：抵达三亚-海滩漫步）
     */
    @TableField("title")
    private String title;

    /**
     * 当日详情（含时间安排、景点介绍）
     */
    @TableField("description")
    private String description;

    /**
     * 餐饮安排（如：含早中晚餐）
     */
    @TableField("meals")
    private String meals;

    /**
     * 交通方式
     */
    @TableField("traffic")
    private String traffic;

    /**
     * 住宿安排
     */
    @TableField("accommodation")
    private String accommodation;
}
