package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanderlust.travel.travelportal.entity.TourOrder;
import com.wanderlust.travel.travelportal.mapper.TourOrderMapper;
import com.wanderlust.travel.travelportal.service.ITourOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 旅游订单表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@Service
public class TourOrderServiceImpl extends ServiceImpl<TourOrderMapper, TourOrder> implements ITourOrderService {
    @Override
    public List<TourOrder> batchFindTourOrdersByOrderIds(List<Long> orderIds) {
        if (orderIds.isEmpty()) {
            return Collections.emptyList();
        }
        // 使用订单表主键 order_id 进行批量查询
        return baseMapper.selectList(
                new QueryWrapper<TourOrder>()
                        .in("order_id", orderIds)
        );
    }
}

