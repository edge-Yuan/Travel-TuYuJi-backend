package com.wanderlust.travel.travelportal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 退款信息VO
 * @author wanderlust
 */
@Data
@ApiModel(description = "退款信息VO")
public class RefundInfoVO {

    @ApiModelProperty("距离出发天数")
    private Integer daysBeforeDeparture;

    @ApiModelProperty("退款比例")
    private Integer refundPercentage;

    @ApiModelProperty("可退金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款到账天数")
    private Integer refundDays;

    @ApiModelProperty("退款政策说明")
    private String refundPolicy;
}
