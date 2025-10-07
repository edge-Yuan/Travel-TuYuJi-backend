package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wanderlust.travel.travelportal.entity.TourOrder;
import com.wanderlust.travel.travelportal.mapper.TourOrderMapper;
import com.wanderlust.travel.travelportal.service.ITourOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public boolean updateScanStatus(Long orderId, Byte scanStatus) {
        UpdateWrapper<TourOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", orderId)
                    .set("scan_status", scanStatus)
                    .set("scan_time", scanStatus == 1 ? LocalDateTime.now() : null);
        return update(updateWrapper);
    }

    @Override
    public boolean updateScanTime(Long orderId, LocalDateTime scanTime) {
        UpdateWrapper<TourOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", orderId)
                    .set("scan_time", scanTime);
        return update(updateWrapper);
    }

    @Override
    public List<TourOrder> getOrdersByScanStatus(Byte scanStatus) {
        return list(new QueryWrapper<TourOrder>()
                .eq("scan_status", scanStatus)
                .orderByDesc("create_time"));
    }

    @Override
    public boolean updatePaymentAndScanStatus(Long orderId, Byte payStatus, Byte payType, Byte scanStatus) {
        UpdateWrapper<TourOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_id", orderId)
                    .set("pay_status", payStatus)
                    .set("pay_type", payType)
                    .set("scan_status", scanStatus)
                    .set("pay_time", payStatus == 1 ? LocalDateTime.now() : null)
                    .set("scan_time", scanStatus == 1 ? LocalDateTime.now() : null)
                    .set("scan_confirm_time", (payStatus == 1 && scanStatus == 1) ? LocalDateTime.now() : null);
        return update(updateWrapper);
    }
}

