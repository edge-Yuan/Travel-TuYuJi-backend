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
 * 服务异常实体类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("service_exception")
public class ServiceException implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 异常ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 服务ID
     */
    @TableField("service_id")
    private Long serviceId;

    /**
     * 异常类型：equipment-设备故障，delay-服务延迟，complaint-客户投诉，safety-安全问题，other-其他
     */
    @TableField("exception_type")
    private String exceptionType;

    /**
     * 异常描述
     */
    @TableField("description")
    private String description;

    /**
     * 紧急程度：low-低，medium-中，high-高，urgent-紧急
     */
    @TableField("priority")
    private String priority;

    /**
     * 处理建议
     */
    @TableField("suggestion")
    private String suggestion;

    /**
     * 处理方案
     */
    @TableField("solution")
    private String solution;

    /**
     * 异常状态：pending-待处理，resolved-已解决，escalated-已升级
     */
    @TableField("status")
    private String status;

    /**
     * 异常时间
     */
    @TableField("exception_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime exceptionTime;

    /**
     * 处理时间
     */
    @TableField("resolve_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime resolveTime;

    /**
     * 处理人
     */
    @TableField("resolve_by")
    private String resolveBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;
}
