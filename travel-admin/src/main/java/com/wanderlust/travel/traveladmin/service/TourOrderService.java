package com.wanderlust.travel.traveladmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.traveladmin.dto.OrderSearchDTO;
import com.wanderlust.travel.traveladmin.dto.RefundProcessDTO;
import com.wanderlust.travel.traveladmin.entity.TourOrder;
import com.wanderlust.travel.traveladmin.vo.OrderStatisticsVO;
import com.wanderlust.travel.traveladmin.vo.OrderVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 旅游订单表 服务类
 * </p>
 *
 * @author wanderlust
 * @since 2025-01-15
 */
public interface TourOrderService extends IService<TourOrder> {

    /**
     * 分页查询订单
     */
    IPage<OrderVO> getOrderPage(OrderSearchDTO searchDTO);

    /**
     * 获取订单详情
     */
    OrderVO getOrderDetail(Long orderId);

    /**
     * 确认订单
     */
    boolean confirmOrder(Long orderId);

    /**
     * 完成订单
     */
    boolean completeOrder(Long orderId);

    /**
     * 取消订单
     */
    boolean cancelOrder(Long orderId);

    /**
     * 批量确认订单
     */
    boolean batchConfirmOrders(List<Long> orderIds);

    /**
     * 批量完成订单
     */
    boolean batchCompleteOrders(List<Long> orderIds);

    /**
     * 处理退款
     */
    boolean processRefund(RefundProcessDTO refundDTO);

    /**
     * 同意退款
     */
    boolean approveRefund(Long refundId);

    /**
     * 拒绝退款
     */
    boolean rejectRefund(Long refundId);

    /**
     * 获取订单统计信息
     */
    OrderStatisticsVO getOrderStatistics();

    /**
     * 导出订单数据
     */
    List<OrderVO> exportOrders(OrderSearchDTO searchDTO);

    /**
     * 获取订单变更记录
     */
    List<Map<String, Object>> getOrderChanges(Long orderId);

    /**
     * 获取退款记录
     */
    List<Map<String, Object>> getRefundRecords(Long orderId);

    /**
     * 更新订单状态
     */
    boolean updateOrderStatus(Long orderId, Byte status);

    /**
     * 根据状态获取订单列表
     */
    List<TourOrder> getOrdersByStatus(Byte status);

    /**
     * 根据用户ID获取订单列表
     */
    List<TourOrder> getOrdersByUserId(Long userId);

    /**
     * 获取用户订单统计
     */
    Map<String, Object> getUserOrderStatistics(Long userId);
}
