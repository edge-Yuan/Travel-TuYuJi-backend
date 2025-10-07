package com.wanderlust.travel.traveladmin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 服务异常视图对象
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ServiceExceptionVO {

    /**
     * 异常ID
     */
    private Long id;

    /**
     * 服务ID
     */
    private Long serviceId;

    /**
     * 异常类型
     */
    private String exceptionType;

    /**
     * 异常描述
     */
    private String description;

    /**
     * 紧急程度
     */
    private String priority;

    /**
     * 处理建议
     */
    private String suggestion;

    /**
     * 处理方案
     */
    private String solution;

    /**
     * 异常状态
     */
    private String status;

    /**
     * 异常时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime exceptionTime;

    /**
     * 处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime resolveTime;

    /**
     * 处理人
     */
    private String resolveBy;
}
