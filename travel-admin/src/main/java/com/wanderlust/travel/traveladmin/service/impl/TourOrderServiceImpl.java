package com.wanderlust.travel.traveladmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.traveladmin.dto.OrderSearchDTO;
import com.wanderlust.travel.traveladmin.dto.RefundProcessDTO;
import com.wanderlust.travel.traveladmin.entity.TourOrder;
import com.wanderlust.travel.traveladmin.mapper.TourOrderMapper;
import com.wanderlust.travel.traveladmin.service.TourOrderService;
import com.wanderlust.travel.traveladmin.vo.OrderStatisticsVO;
import com.wanderlust.travel.traveladmin.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 旅游订单表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2025-01-15
 */
@Slf4j
@Service
public class TourOrderServiceImpl extends ServiceImpl<TourOrderMapper, TourOrder> implements TourOrderService {

    @Override
    public IPage<OrderVO> getOrderPage(OrderSearchDTO searchDTO) {
        Page<TourOrder> page = new Page<>(searchDTO.getCurrent(), searchDTO.getSize());
        
        LambdaQueryWrapper<TourOrder> queryWrapper = new LambdaQueryWrapper<>();
        
        // 状态筛选
        if (searchDTO.getStatus() != null) {
            queryWrapper.eq(TourOrder::getOrderStatus, searchDTO.getStatus());
        }
        
        // 用户ID筛选
        if (searchDTO.getUserId() != null) {
            queryWrapper.eq(TourOrder::getUserId, searchDTO.getUserId());
        }
        
        // 产品ID筛选
        if (searchDTO.getProductId() != null) {
            queryWrapper.eq(TourOrder::getProductId, searchDTO.getProductId());
        }
        
        // 支付状态筛选
        if (searchDTO.getPayStatus() != null) {
            queryWrapper.eq(TourOrder::getPayStatus, searchDTO.getPayStatus());
        }
        
        // 支付方式筛选
        if (searchDTO.getPayType() != null) {
            queryWrapper.eq(TourOrder::getPayType, searchDTO.getPayType());
        }
        
        // 日期范围筛选
        if (searchDTO.getStartDate() != null) {
            queryWrapper.ge(TourOrder::getCreateTime, searchDTO.getStartDate().atStartOfDay());
        }
        if (searchDTO.getEndDate() != null) {
            queryWrapper.le(TourOrder::getCreateTime, searchDTO.getEndDate().atTime(23, 59, 59));
        }
        
        // 关键词搜索
        if (StringUtils.hasText(searchDTO.getKeyword())) {
            queryWrapper.and(wrapper -> wrapper
                .like(TourOrder::getOrderId, searchDTO.getKeyword())
                .or()
                .like(TourOrder::getReceiverName, searchDTO.getKeyword())
            );
        }
        
        // 排序
        if ("asc".equalsIgnoreCase(searchDTO.getOrderDirection())) {
            queryWrapper.orderByAsc(TourOrder::getCreateTime);
        } else {
            queryWrapper.orderByDesc(TourOrder::getCreateTime);
        }
        
        IPage<TourOrder> orderPage = this.page(page, queryWrapper);
        
        // 转换为VO
        IPage<OrderVO> voPage = new Page<>(orderPage.getCurrent(), orderPage.getSize(), orderPage.getTotal());
        List<OrderVO> voList = new ArrayList<>();
        
        for (TourOrder order : orderPage.getRecords()) {
            OrderVO vo = convertToOrderVO(order);
            voList.add(vo);
        }
        
        voPage.setRecords(voList);
        return voPage;
    }

    @Override
    public OrderVO getOrderDetail(Long orderId) {
        TourOrder order = this.getById(orderId);
        if (order == null) {
            return null;
        }
        
        OrderVO vo = convertToOrderVO(order);
        
        // 获取订单变更记录
        vo.setChanges(new ArrayList<>());
        
        // 获取退款记录
        vo.setRefunds(new ArrayList<>());
        
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmOrder(Long orderId) {
        TourOrder order = this.getById(orderId);
        if (order == null) {
            log.error("订单不存在: {}", orderId);
            return false;
        }
        
        if (order.getOrderStatus() != 0) {
            log.error("订单状态不正确，无法确认: {}", orderId);
            return false;
        }
        
        order.setOrderStatus((byte) 1);
        return this.updateById(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean completeOrder(Long orderId) {
        TourOrder order = this.getById(orderId);
        if (order == null) {
            log.error("订单不存在: {}", orderId);
            return false;
        }
        
        if (order.getOrderStatus() != 1) {
            log.error("订单状态不正确，无法完成: {}", orderId);
            return false;
        }
        
        order.setOrderStatus((byte) 2);
        return this.updateById(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelOrder(Long orderId) {
        TourOrder order = this.getById(orderId);
        if (order == null) {
            log.error("订单不存在: {}", orderId);
            return false;
        }
        
        order.setOrderStatus((byte) 3);
        order.setCancelTime(LocalDateTime.now());
        return this.updateById(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchConfirmOrders(List<Long> orderIds) {
        if (orderIds == null || orderIds.isEmpty()) {
            return false;
        }
        
        return baseMapper.batchUpdateOrderStatus(orderIds, (byte) 1, LocalDateTime.now()) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchCompleteOrders(List<Long> orderIds) {
        if (orderIds == null || orderIds.isEmpty()) {
            return false;
        }
        
        return baseMapper.batchUpdateOrderStatus(orderIds, (byte) 2, LocalDateTime.now()) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean processRefund(RefundProcessDTO refundDTO) {
        TourOrder order = this.getById(refundDTO.getOrderId());
        if (order == null) {
            log.error("订单不存在: {}", refundDTO.getOrderId());
            return false;
        }
        
        // 更新订单状态为退款中
        order.setOrderStatus((byte) 4);
        order.setRefundAmount(refundDTO.getAmount());
        
        return this.updateById(order);
    }

    @Override
    public boolean approveRefund(Long refundId) {
        // 这里应该调用退款记录表的更新方法
        // 暂时返回true，实际实现需要根据退款记录表结构
        log.info("同意退款: {}", refundId);
        return true;
    }

    @Override
    public boolean rejectRefund(Long refundId) {
        // 这里应该调用退款记录表的更新方法
        // 暂时返回true，实际实现需要根据退款记录表结构
        log.info("拒绝退款: {}", refundId);
        return true;
    }

    @Override
    public OrderStatisticsVO getOrderStatistics() {
        OrderStatisticsVO statistics = new OrderStatisticsVO();
        
        // 获取基础统计
        Map<String, Object> basicStats = baseMapper.getOrderStatistics();
        if (basicStats != null) {
            statistics.setPending(((Number) basicStats.get("pending")).longValue());
            statistics.setConfirmed(((Number) basicStats.get("confirmed")).longValue());
            statistics.setCompleted(((Number) basicStats.get("completed")).longValue());
            statistics.setRefunding(((Number) basicStats.get("refunding")).longValue());
            statistics.setTotal(((Number) basicStats.get("total")).longValue());
        }
        
        // 获取今日统计
        Map<String, Object> todayStats = baseMapper.getTodayStatistics();
        if (todayStats != null) {
            statistics.setTodayOrders(((Number) todayStats.get("todayOrders")).longValue());
            statistics.setTodaySales((BigDecimal) todayStats.get("todaySales"));
        }
        
        // 获取产品类型统计
        statistics.setProductTypeStats(baseMapper.getProductTypeStatistics());
        
        // 获取销售趋势
        statistics.setSalesTrend(baseMapper.getSalesTrend());
        
        // 获取热销产品
        statistics.setTopSellingProducts(baseMapper.getTopSellingProducts());
        
        return statistics;
    }

    @Override
    public List<OrderVO> exportOrders(OrderSearchDTO searchDTO) {
        // 设置较大的分页大小来获取所有数据
        searchDTO.setSize(10000);
        IPage<OrderVO> page = getOrderPage(searchDTO);
        return page.getRecords();
    }

    @Override
    public List<Map<String, Object>> getOrderChanges(Long orderId) {
        // 这里应该查询订单变更记录表
        // 暂时返回空列表，实际实现需要根据订单变更表结构
        return new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> getRefundRecords(Long orderId) {
        // 这里应该查询退款记录表
        // 暂时返回空列表，实际实现需要根据退款记录表结构
        return new ArrayList<>();
    }

    @Override
    public boolean updateOrderStatus(Long orderId, Byte status) {
        TourOrder order = this.getById(orderId);
        if (order == null) {
            return false;
        }
        
        order.setOrderStatus(status);
        return this.updateById(order);
    }

    @Override
    public List<TourOrder> getOrdersByStatus(Byte status) {
        LambdaQueryWrapper<TourOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TourOrder::getOrderStatus, status);
        queryWrapper.orderByDesc(TourOrder::getCreateTime);
        return this.list(queryWrapper);
    }

    @Override
    public List<TourOrder> getOrdersByUserId(Long userId) {
        LambdaQueryWrapper<TourOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TourOrder::getUserId, userId);
        queryWrapper.orderByDesc(TourOrder::getCreateTime);
        return this.list(queryWrapper);
    }

    @Override
    public Map<String, Object> getUserOrderStatistics(Long userId) {
        Map<String, Object> statistics = new HashMap<>();
        
        long totalOrders = this.lambdaQuery()
            .eq(TourOrder::getUserId, userId)
            .count();
        
        long pendingOrders = this.lambdaQuery()
            .eq(TourOrder::getUserId, userId)
            .eq(TourOrder::getOrderStatus, (byte) 0)
            .count();
        
        long completedOrders = this.lambdaQuery()
            .eq(TourOrder::getUserId, userId)
            .eq(TourOrder::getOrderStatus, (byte) 2)
            .count();
        
        statistics.put("totalOrders", totalOrders);
        statistics.put("pendingOrders", pendingOrders);
        statistics.put("completedOrders", completedOrders);
        
        return statistics;
    }

    /**
     * 转换为OrderVO
     */
    private OrderVO convertToOrderVO(TourOrder order) {
        OrderVO vo = new OrderVO();
        BeanUtils.copyProperties(order, vo);
        
        // 设置订单号
        vo.setOrderNo("ORD" + order.getOrderId() + System.currentTimeMillis());
        
        // 设置状态名称
        vo.setOrderStatus(getOrderStatusName(order.getOrderStatus()));
        vo.setPayStatus(getPayStatusName(order.getPayStatus()));
        vo.setPaymentMethod(getPaymentMethodName(order.getPayType()));
        
        // 设置使用时间
        if (order.getBookingDate() != null) {
            vo.setUseTime(order.getBookingDate().toString());
        }
        
        // 设置金额信息
        vo.setTotalAmount(order.getTotalPrice());
        vo.setPaidAmount(order.getTotalPrice());
        if (order.getCouponDiscount() != null) {
            vo.setDiscountAmount(order.getCouponDiscount());
        }
        
        return vo;
    }

    /**
     * 获取订单状态名称
     */
    private String getOrderStatusName(Byte status) {
        if (status == null) return "未知";
        switch (status) {
            case 0: return "待确认";
            case 1: return "已确认";
            case 2: return "已完成";
            case 3: return "已取消";
            case 4: return "退款中";
            default: return "未知";
        }
    }

    /**
     * 获取支付状态名称
     */
    private String getPayStatusName(Byte status) {
        if (status == null) return "未知";
        switch (status) {
            case 0: return "待支付";
            case 1: return "已支付";
            case 2: return "已退款";
            default: return "未知";
        }
    }

    /**
     * 获取支付方式名称
     */
    private String getPaymentMethodName(Byte type) {
        if (type == null) return "未知";
        switch (type) {
            case 1: return "微信支付";
            case 2: return "支付宝";
            case 3: return "银行卡";
            default: return "未知";
        }
    }
}
