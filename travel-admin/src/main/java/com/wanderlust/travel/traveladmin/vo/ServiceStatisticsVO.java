package com.wanderlust.travel.traveladmin.vo;

import lombok.Data;

/**
 * 服务统计视图对象
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ServiceStatisticsVO {

    /**
     * 今日入住数量
     */
    private Integer hotelCheckins;

    /**
     * 今日入园数量
     */
    private Integer ticketEntries;

    /**
     * 今日行程数量
     */
    private Integer routeServices;

    /**
     * 异常处理数量
     */
    private Integer exceptions;

    /**
     * 待服务数量
     */
    private Integer pendingServices;

    /**
     * 服务中数量
     */
    private Integer inProgressServices;

    /**
     * 已完成数量
     */
    private Integer completedServices;

    /**
     * 异常服务数量
     */
    private Integer exceptionServices;
}
