package com.wanderlust.travel.travelportal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 行程取消DTO
 * @author wanderlust
 */
@Data
@ApiModel(description = "行程取消DTO")
public class ItineraryCancelDTO {

    @ApiModelProperty("订单ID")
    @NotNull(message = "订单ID不能为空")
    private Long orderId;

    @ApiModelProperty("取消原因")
    @NotBlank(message = "取消原因不能为空")
    private String reason;

    @ApiModelProperty("详细说明")
    @Max(value = 200, message = "详细说明不能超过200个字符")
    private String description;
}
