package com.wanderlust.travel.travelportal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 行程修改DTO
 * @author wanderlust
 */
@Data
@ApiModel(description = "行程修改DTO")
public class ItineraryModifyDTO {

    @ApiModelProperty("订单ID")
    @NotNull(message = "订单ID不能为空")
    private Long orderId;

    @ApiModelProperty("行程标题")
    @NotBlank(message = "行程标题不能为空")
    @Max(value = 50, message = "行程标题不能超过50个字符")
    private String title;

    @ApiModelProperty("开始日期")
    @NotNull(message = "开始日期不能为空")
    private LocalDate startDate;

    @ApiModelProperty("结束日期")
    @NotNull(message = "结束日期不能为空")
    private LocalDate endDate;

    @ApiModelProperty("出行人数")
    @NotNull(message = "出行人数不能为空")
    @Min(value = 1, message = "出行人数不能少于1人")
    @Max(value = 50, message = "出行人数不能超过50人")
    private Integer travelers;

    @ApiModelProperty("行程备注")
    @Max(value = 500, message = "行程备注不能超过500个字符")
    private String notes;

    @ApiModelProperty("修改原因")
    @NotBlank(message = "修改原因不能为空")
    private String modifyReason;
}
