package com.wanderlust.travel.traveladmin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单变更记录视图对象
 * 
 * @author wanderlust
 * @since 2025-01-15
 */
@Data
@ApiModel(description = "订单变更记录")
public class OrderChangeVO {

    @ApiModelProperty("变更ID")
    private Long id;

    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("变更类型")
    private String type;

    @ApiModelProperty("变更原因")
    private String reason;

    @ApiModelProperty("变更内容")
    private String content;

    @ApiModelProperty("变更时间")
    private LocalDateTime time;

    @ApiModelProperty("变更状态：pending-待处理，approved-已同意，rejected-已拒绝")
    private String status;

    @ApiModelProperty("申请人")
    private String applicant;

    @ApiModelProperty("处理人")
    private String processor;

    @ApiModelProperty("处理时间")
    private LocalDateTime processTime;

    @ApiModelProperty("处理备注")
    private String processRemark;
}
