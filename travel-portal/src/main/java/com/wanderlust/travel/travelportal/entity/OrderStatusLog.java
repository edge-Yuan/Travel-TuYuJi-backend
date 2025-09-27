package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单状态变更记录表
 * </p>
 *
 * @author wanderlust
 * @since 2025-01-15
 */
@Data
@TableName("order_status_log")
@ApiModel(value = "OrderStatusLog对象", description = "订单状态变更记录表")
public class OrderStatusLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("记录ID")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("变更前状态")
    private Byte fromStatus;

    @ApiModelProperty("变更后状态")
    private Byte toStatus;

    @ApiModelProperty("变更原因")
    private String reason;

    @ApiModelProperty("操作人ID")
    private Long operatorId;

    @ApiModelProperty("操作人类型：1-用户，2-导游，3-管理员")
    private Byte operatorType;

    @ApiModelProperty("备注信息")
    private String remark;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
