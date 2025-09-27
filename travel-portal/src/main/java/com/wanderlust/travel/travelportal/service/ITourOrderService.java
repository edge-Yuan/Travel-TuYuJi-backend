package com.wanderlust.travel.travelportal.service;

import com.wanderlust.travel.travelportal.entity.TourOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 旅游订单表 服务类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
public interface ITourOrderService extends IService<TourOrder> {
    // 批量根据订单ID查询订单
    List<TourOrder> batchFindTourOrdersByOrderIds(List<Long> orderIds);

}
