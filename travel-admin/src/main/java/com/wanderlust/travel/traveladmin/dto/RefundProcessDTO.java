package com.wanderlust.travel.traveladmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 退款处理DTO
 * 
 * @author wanderlust
 * @since 2025-01-15
 */
@Data
@ApiModel(description = "退款处理信息")
public class RefundProcessDTO {

    @ApiModelProperty("订单ID")
    @NotNull(message = "订单ID不能为空")
    private Long orderId;

    @ApiModelProperty("退款金额")
    @NotNull(message = "退款金额不能为空")
    @DecimalMin(value = "0.01", message = "退款金额必须大于0")
    private BigDecimal amount;

    @ApiModelProperty("退款原因")
    @NotBlank(message = "退款原因不能为空")
    private String reason;

    @ApiModelProperty("退款说明")
    private String description;

    @ApiModelProperty("退款类型：customer_cancel-客户取消，product_issue-产品问题，service_issue-服务问题，other-其他")
    @NotBlank(message = "退款类型不能为空")
    private String refundType;
}
