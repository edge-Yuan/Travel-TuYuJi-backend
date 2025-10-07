package com.wanderlust.travel.traveladmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 服务管理实体类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("service_management")
public class ServiceManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 客户姓名
     */
    @TableField("customer_name")
    private String customerName;

    /**
     * 客户电话
     */
    @TableField("customer_phone")
    private String customerPhone;

    /**
     * 客户身份证号
     */
    @TableField("customer_id_card")
    private String customerIdCard;

    /**
     * 紧急联系人
     */
    @TableField("emergency_contact")
    private String emergencyContact;

    /**
     * 服务类型：hotel-酒店服务，attraction-景区服务，route-路线服务
     */
    @TableField("service_type")
    private String serviceType;

    /**
     * 服务名称
     */
    @TableField("service_name")
    private String serviceName;

    /**
     * 服务图片
     */
    @TableField("service_image")
    private String serviceImage;

    /**
     * 服务地点
     */
    @TableField("service_location")
    private String serviceLocation;

    /**
     * 服务时间
     */
    @TableField("service_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime serviceTime;

    /**
     * 预计时长
     */
    @TableField("estimated_duration")
    private String estimatedDuration;

    /**
     * 服务状态：pending-待服务，in_progress-服务中，completed-已完成，exception-异常
     */
    @TableField("status")
    private String status;

    /**
     * 服务人员
     */
    @TableField("staff_name")
    private String staffName;

    /**
     * 特殊要求
     */
    @TableField("special_requirements")
    private String specialRequirements;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 是否删除：0-未删除，1-已删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
}
