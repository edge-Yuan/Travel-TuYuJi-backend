package com.wanderlust.travel.traveladmin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 订单搜索DTO
 * 
 * @author wanderlust
 * @since 2025-01-15
 */
@Data
@ApiModel(description = "订单搜索条件")
public class OrderSearchDTO {

    @ApiModelProperty("订单状态：0-待确认，1-已确认，2-已完成，3-已取消，4-退款中")
    private Byte status;

    @ApiModelProperty("产品类型：hotel-酒店客房，route-旅行路线，ticket-门票")
    private String productType;

    @ApiModelProperty("开始日期")
    private LocalDate startDate;

    @ApiModelProperty("结束日期")
    private LocalDate endDate;

    @ApiModelProperty("关键词搜索（订单号或客户姓名）")
    private String keyword;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("产品ID")
    private Long productId;

    @ApiModelProperty("支付状态：0-待支付，1-已支付，2-已退款")
    private Byte payStatus;

    @ApiModelProperty("支付方式：1-微信，2-支付宝，3-银行卡")
    private Byte payType;

    @ApiModelProperty("当前页码")
    private Integer current = 1;

    @ApiModelProperty("每页大小")
    private Integer size = 10;

    @ApiModelProperty("排序字段")
    private String orderBy = "createTime";

    @ApiModelProperty("排序方向：asc-升序，desc-降序")
    private String orderDirection = "desc";
}
