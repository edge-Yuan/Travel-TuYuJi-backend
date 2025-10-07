package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.TourOrder;
import com.wanderlust.travel.travelportal.service.ITourOrderService;
import com.wanderlust.travel.travelportal.service.IItineraryManagementService;
import com.wanderlust.travel.travelportal.service.WechatPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付相关控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-01-15
 */
@RestController
@RequestMapping("/travel-portal/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private ITourOrderService tourOrderService;

    @Autowired
    private IItineraryManagementService itineraryManagementService;

    @Autowired
    private WechatPayService wechatPayService;

    /**
     * 检查扫码状态
     * @param orderId 订单ID
     * @return 扫码状态信息
     */
    @GetMapping("/check-scan-status/{orderId}")
    public Result<Map<String, Object>> checkScanStatus(@PathVariable Long orderId) {
        log.info("检查订单扫码状态: {}", orderId);
        
        try {
            // 查询订单信息
            TourOrder order = tourOrderService.getById(orderId);
            if (order == null) {
                return Result.error("订单不存在");
            }

            // 检查微信支付二维码是否被扫描
            boolean isScanned = wechatPayService.checkQRCodeScanned(orderId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("isScanned", isScanned);
            result.put("orderId", orderId);
            result.put("scanTime", isScanned ? LocalDateTime.now() : null);
            result.put("payStatus", order.getPayStatus());
            result.put("orderStatus", order.getOrderStatus());
            
            log.info("订单 {} 扫码状态检查结果: {}", orderId, isScanned);
            return Result.success(result);
            
        } catch (Exception e) {
            log.error("检查扫码状态失败", e);
            return Result.error("检查扫码状态失败: " + e.getMessage());
        }
    }

    /**
     * 确认支付完成
     * @param request 支付确认请求
     * @return 确认结果
     */
    @PostMapping("/confirm-payment")
    public Result<Map<String, Object>> confirmPayment(@RequestBody PaymentConfirmRequest request) {
        log.info("确认支付完成: {}", request);
        
        try {
            // 查询订单信息
            TourOrder order = tourOrderService.getById(request.getOrderId());
            if (order == null) {
                return Result.error("订单不存在");
            }

            // 更新订单支付状态
            order.setPayStatus(request.getPayStatus());
            order.setPayType(request.getPayType());
            order.setPayTime(LocalDateTime.now());
            
            // 如果提供了扫码时间，记录扫码时间
            if (request.getScanTime() != null) {
                // 这里可以添加扫码时间字段，暂时记录在日志中
                log.info("订单 {} 扫码时间: {}", request.getOrderId(), request.getScanTime());
            }

            boolean updateSuccess = tourOrderService.updateById(order);
            if (!updateSuccess) {
                return Result.error("更新支付状态失败");
            }

            // 如果订单状态为已确认且已支付，创建行程记录
            if (order.getOrderStatus() == 1 && order.getPayStatus() == 1) {
                try {
                    itineraryManagementService.syncItineraryFromOrder(order.getOrderId());
                    log.info("为订单 {} 创建行程记录成功", order.getOrderId());
                } catch (Exception e) {
                    log.error("为订单 {} 创建行程记录失败", order.getOrderId(), e);
                    // 不返回错误，因为支付状态已经更新成功
                }
            }

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("orderId", order.getOrderId());
            result.put("payStatus", order.getPayStatus());
            result.put("orderStatus", order.getOrderStatus());
            
            log.info("订单 {} 支付确认完成", order.getOrderId());
            return Result.success(result);
            
        } catch (Exception e) {
            log.error("确认支付完成失败", e);
            return Result.error("确认支付完成失败: " + e.getMessage());
        }
    }

    /**
     * 更新订单支付状态
     * @param orderId 订单ID
     * @param payStatus 支付状态
     * @param payType 支付方式
     * @return 更新结果
     */
    @PutMapping("/update-payment-status")
    public Result<Map<String, Object>> updatePaymentStatus(
            @RequestParam Long orderId,
            @RequestParam Byte payStatus,
            @RequestParam Byte payType) {
        
        log.info("更新订单支付状态: orderId={}, payStatus={}, payType={}", orderId, payStatus, payType);
        
        try {
            TourOrder order = tourOrderService.getById(orderId);
            if (order == null) {
                return Result.error("订单不存在");
            }

            // 更新支付状态
            order.setPayStatus(payStatus);
            order.setPayType(payType);
            if (payStatus == 1) {
                order.setPayTime(LocalDateTime.now());
            }

            boolean updateSuccess = tourOrderService.updateById(order);
            if (!updateSuccess) {
                return Result.error("更新支付状态失败");
            }

            // 如果订单状态为已确认且已支付，创建行程记录
            if (order.getOrderStatus() == 1 && order.getPayStatus() == 1) {
                try {
                    itineraryManagementService.syncItineraryFromOrder(order.getOrderId());
                    log.info("为订单 {} 创建行程记录成功", order.getOrderId());
                } catch (Exception e) {
                    log.error("为订单 {} 创建行程记录失败", order.getOrderId(), e);
                }
            }

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("orderId", order.getOrderId());
            result.put("payStatus", order.getPayStatus());
            result.put("orderStatus", order.getOrderStatus());
            
            log.info("订单 {} 支付状态更新完成", order.getOrderId());
            return Result.success(result);
            
        } catch (Exception e) {
            log.error("更新支付状态失败", e);
            return Result.error("更新支付状态失败: " + e.getMessage());
        }
    }

    /**
     * 支付确认请求类
     */
    public static class PaymentConfirmRequest {
        private Long orderId;
        private Byte payStatus;
        private Byte payType;
        private String scanTime;

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public Byte getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(Byte payStatus) {
            this.payStatus = payStatus;
        }

        public Byte getPayType() {
            return payType;
        }

        public void setPayType(Byte payType) {
            this.payType = payType;
        }

        public String getScanTime() {
            return scanTime;
        }

        public void setScanTime(String scanTime) {
            this.scanTime = scanTime;
        }

        @Override
        public String toString() {
            return "PaymentConfirmRequest{" +
                    "orderId=" + orderId +
                    ", payStatus=" + payStatus +
                    ", payType=" + payType +
                    ", scanTime='" + scanTime + '\'' +
                    '}';
        }
    }
}
