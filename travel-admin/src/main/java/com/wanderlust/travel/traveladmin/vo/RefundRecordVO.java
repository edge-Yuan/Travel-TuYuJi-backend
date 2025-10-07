package com.wanderlust.travel.traveladmin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 退款记录视图对象
 * 
 * @author wanderlust
 * @since 2025-01-15
 */
@Data
@ApiModel(description = "退款记录")
public class RefundRecordVO {

    @ApiModelProperty("退款ID")
    private Long id;

    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("退款金额")
    private BigDecimal amount;

    @ApiModelProperty("退款原因")
    private String reason;

    @ApiModelProperty("退款说明")
    private String description;

    @ApiModelProperty("退款类型")
    private String refundType;

    @ApiModelProperty("退款时间")
    private LocalDateTime time;

    @ApiModelProperty("退款状态：pending-待处理，approved-已同意，rejected-已拒绝，completed-已完成")
    private String status;

    @ApiModelProperty("申请人")
    private String applicant;

    @ApiModelProperty("处理人")
    private String processor;

    @ApiModelProperty("处理时间")
    private LocalDateTime processTime;

    @ApiModelProperty("处理备注")
    private String processRemark;

    @ApiModelProperty("退款流水号")
    private String refundNo;
}
