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
 * 旅游产品实体类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tour_product")
public class TourProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品唯一标识
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    /**
     * 所属旅行商
     */
    @TableField("merchant_id")
    private Long merchantId;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 类型：1-旅行路线，2-酒店客房，3-景区门票
     */
    @TableField("product_type")
    private Byte productType;

    /**
     * 售价
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 原价
     */
    @TableField("original_price")
    private BigDecimal originalPrice;

    /**
     * 库存
     */
    @TableField("stock")
    private Integer stock;

    /**
     * 产品生效日期
     */
    @TableField("start_date")
    private LocalDate startDate;

    /**
     * 产品失效日期
     */
    @TableField("end_date")
    private LocalDate endDate;

    /**
     * 产品详情描述
     */
    @TableField("description")
    private String description;

    /**
     * 产品图片URL
     */
    @TableField("img_urls")
    private String imgUrls;

    /**
     * 审核管理员ID
     */
    @TableField("audit_admin_id")
    private Long auditAdminId;

    /**
     * 审核状态：0-待审核，1-已通过，2-已驳回
     */
    @TableField("audit_status")
    private Byte auditStatus;

    /**
     * 上架状态：0-下架，1-上架
     */
    @TableField("product_status")
    private Byte productStatus;

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
     * 产品标签（逗号分隔，如：超值,豪华）
     */
    @TableField("product_tags")
    private String productTags;

    /**
     * 已售数量
     */
    @TableField("sold_count")
    private Integer soldCount;

    /**
     * 服务保障（逗号分隔，如：退改无忧,安全可靠）
     */
    @TableField("service_guarantees")
    private String serviceGuarantees;

    /**
     * 主图URL（优先展示）
     */
    @TableField("main_img_url")
    private String mainImgUrl;

    /**
     * 核心卖点（富文本）
     */
    @TableField("product_selling_points")
    private String productSellingPoints;

    /**
     * 所属分类ID（关联product_category）
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 供应商
     */
    @TableField("supplier")
    private String supplier;

    /**
     * 产品特色
     */
    @TableField("features")
    private String features;

    /**
     * 产品特色图片
     */
    @TableField("features_imgs")
    private String featuresImgs;

    /**
     * 景点清单（逗号分隔）
     */
    @TableField("attractions")
    private String attractions;

    /**
     * 餐饮标准
     */
    @TableField("meal_standard")
    private String mealStandard;

    /**
     * 住宿标准
     */
    @TableField("accommodation_standard")
    private String accommodationStandard;

    /**
     * 有效期（JSON格式）
     */
    @TableField("validity_period")
    private String validityPeriod;

    /**
     * 入场时间
     */
    @TableField("entry_time")
    private String entryTime;

    /**
     * 房间类型
     */
    @TableField("room_type")
    private String roomType;

    /**
     * 容量（人数）
     */
    @TableField("capacity")
    private Integer capacity;

    /**
     * 行程天数
     */
    @TableField("days")
    private Integer days;

    /**
     * 设施（JSON格式）
     */
    @TableField("facilities")
    private String facilities;
}
