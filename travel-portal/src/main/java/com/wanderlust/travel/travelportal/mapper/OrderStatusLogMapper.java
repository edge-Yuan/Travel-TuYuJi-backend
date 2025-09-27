package com.wanderlust.travel.travelportal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.travelportal.entity.OrderStatusLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单状态变更记录表 Mapper 接口
 * </p>
 *
 * @author wanderlust
 * @since 2025-01-15
 */
@Mapper
public interface OrderStatusLogMapper extends BaseMapper<OrderStatusLog> {

}
