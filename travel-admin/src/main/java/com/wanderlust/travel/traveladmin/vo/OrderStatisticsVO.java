package com.wanderlust.travel.traveladmin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 订单统计视图对象
 * 
 * @author wanderlust
 * @since 2025-01-15
 */
@Data
@ApiModel(description = "订单统计信息")
public class OrderStatisticsVO {

    @ApiModelProperty("待确认订单数")
    private Long pending;

    @ApiModelProperty("已确认订单数")
    private Long confirmed;

    @ApiModelProperty("已完成订单数")
    private Long completed;

    @ApiModelProperty("退款中订单数")
    private Long refunding;

    @ApiModelProperty("已退款订单数")
    private Long refunded;

    @ApiModelProperty("总订单数")
    private Long total;

    @ApiModelProperty("总销售额")
    private BigDecimal totalSales;

    @ApiModelProperty("今日订单数")
    private Long todayOrders;

    @ApiModelProperty("今日销售额")
    private BigDecimal todaySales;

    @ApiModelProperty("产品类型统计")
    private List<Map<String, Object>> productTypeStats;

    @ApiModelProperty("销售趋势数据")
    private List<Map<String, Object>> salesTrend;

    @ApiModelProperty("热销产品")
    private List<Map<String, Object>> topSellingProducts;
}
