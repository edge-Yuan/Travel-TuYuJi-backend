package com.wanderlust.travel.traveladmin.dto;

import lombok.Data;

// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.NotNull;

/**
 * 服务异常DTO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ServiceExceptionDTO {

    /**
     * 服务ID
     */
    // @NotNull(message = "服务ID不能为空")
    private Long serviceId;

    /**
     * 异常类型
     */
    // @NotBlank(message = "异常类型不能为空")
    private String exceptionType;

    /**
     * 异常描述
     */
    // @NotBlank(message = "异常描述不能为空")
    private String description;

    /**
     * 紧急程度
     */
    // @NotBlank(message = "紧急程度不能为空")
    private String priority;

    /**
     * 处理建议
     */
    private String suggestion;
}
