package com.wanderlust.travel.traveladmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wanderlust.travel.traveladmin.common.Result;
import com.wanderlust.travel.traveladmin.dto.OrderSearchDTO;
import com.wanderlust.travel.traveladmin.dto.RefundProcessDTO;
import com.wanderlust.travel.traveladmin.entity.TourOrder;
import com.wanderlust.travel.traveladmin.service.TourOrderService;
import com.wanderlust.travel.traveladmin.vo.OrderStatisticsVO;
import com.wanderlust.travel.traveladmin.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单管理控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-01-15
 */
@Slf4j
@RestController
@RequestMapping("/order")
@Api(tags = "订单管理")
@Validated
public class OrderManagementController {

    @Autowired
    private TourOrderService tourOrderService;

    /**
     * 测试方法
     */
    @GetMapping("/test")
    @ApiOperation("测试方法")
    public Result<String> test() {
        try {
            log.info("测试方法被调用");
            return Result.success("测试成功");
        } catch (Exception e) {
            log.error("测试方法失败", e);
            e.printStackTrace();
            return Result.error("测试失败: " + e.getMessage());
        }
    }

    /**
     * 分页查询订单
     */
    @PostMapping("/page")
    @ApiOperation("分页查询订单")
    public Result<IPage<OrderVO>> getOrderPage(@Valid @RequestBody OrderSearchDTO searchDTO) {
        try {
            log.info("分页查询订单: {}", searchDTO);
            IPage<OrderVO> page = tourOrderService.getOrderPage(searchDTO);
            return Result.success(page);
        } catch (Exception e) {
            log.error("分页查询订单失败", e);
            return Result.error("查询订单失败: " + e.getMessage());
        }
    }

    /**
     * 获取订单详情
     */
    @GetMapping("/detail/{orderId}")
    @ApiOperation("获取订单详情")
    public Result<OrderVO> getOrderDetail(@PathVariable @NotNull Long orderId) {
        try {
            log.info("获取订单详情: {}", orderId);
            OrderVO orderDetail = tourOrderService.getOrderDetail(orderId);
            if (orderDetail == null) {
                return Result.error("订单不存在");
            }
            return Result.success(orderDetail);
        } catch (Exception e) {
            log.error("获取订单详情失败: orderId={}", orderId, e);
            return Result.error("获取订单详情失败: " + e.getMessage());
        }
    }

    /**
     * 确认订单
     */
    @PutMapping("/confirm/{orderId}")
    @ApiOperation("确认订单")
    public Result<Boolean> confirmOrder(@PathVariable @NotNull Long orderId) {
        try {
            log.info("确认订单: {}", orderId);
            boolean success = tourOrderService.confirmOrder(orderId);
            if (success) {
                return Result.success("订单确认成功");
            } else {
                return Result.error("订单确认失败");
            }
        } catch (Exception e) {
            log.error("确认订单失败: orderId={}", orderId, e);
            return Result.error("确认订单失败: " + e.getMessage());
        }
    }

    /**
     * 完成订单
     */
    @PutMapping("/complete/{orderId}")
    @ApiOperation("完成订单")
    public Result<Boolean> completeOrder(@PathVariable @NotNull Long orderId) {
        try {
            log.info("完成订单: {}", orderId);
            boolean success = tourOrderService.completeOrder(orderId);
            if (success) {
                return Result.success("订单完成成功");
            } else {
                return Result.error("订单完成失败");
            }
        } catch (Exception e) {
            log.error("完成订单失败: orderId={}", orderId, e);
            return Result.error("完成订单失败: " + e.getMessage());
        }
    }

    /**
     * 取消订单
     */
    @PutMapping("/cancel/{orderId}")
    @ApiOperation("取消订单")
    public Result<Boolean> cancelOrder(@PathVariable @NotNull Long orderId) {
        try {
            log.info("取消订单: {}", orderId);
            boolean success = tourOrderService.cancelOrder(orderId);
            if (success) {
                return Result.success("订单取消成功");
            } else {
                return Result.error("订单取消失败");
            }
        } catch (Exception e) {
            log.error("取消订单失败: orderId={}", orderId, e);
            return Result.error("取消订单失败: " + e.getMessage());
        }
    }

    /**
     * 批量确认订单
     */
    @PutMapping("/batch/confirm")
    @ApiOperation("批量确认订单")
    public Result<Boolean> batchConfirmOrders(@RequestBody @NotEmpty List<Long> orderIds) {
        try {
            log.info("批量确认订单: {}", orderIds);
            boolean success = tourOrderService.batchConfirmOrders(orderIds);
            if (success) {
                return Result.success("批量确认成功");
            } else {
                return Result.error("批量确认失败");
            }
        } catch (Exception e) {
            log.error("批量确认订单失败: orderIds={}", orderIds, e);
            return Result.error("批量确认失败: " + e.getMessage());
        }
    }

    /**
     * 批量完成订单
     */
    @PutMapping("/batch/complete")
    @ApiOperation("批量完成订单")
    public Result<Boolean> batchCompleteOrders(@RequestBody @NotEmpty List<Long> orderIds) {
        try {
            log.info("批量完成订单: {}", orderIds);
            boolean success = tourOrderService.batchCompleteOrders(orderIds);
            if (success) {
                return Result.success("批量完成成功");
            } else {
                return Result.error("批量完成失败");
            }
        } catch (Exception e) {
            log.error("批量完成订单失败: orderIds={}", orderIds, e);
            return Result.error("批量完成失败: " + e.getMessage());
        }
    }

    /**
     * 处理退款
     */
    @PostMapping("/refund")
    @ApiOperation("处理退款")
    public Result<Boolean> processRefund(@Valid @RequestBody RefundProcessDTO refundDTO) {
        try {
            log.info("处理退款: {}", refundDTO);
            boolean success = tourOrderService.processRefund(refundDTO);
            if (success) {
                return Result.success("退款处理成功");
            } else {
                return Result.error("退款处理失败");
            }
        } catch (Exception e) {
            log.error("处理退款失败: {}", refundDTO, e);
            return Result.error("退款处理失败: " + e.getMessage());
        }
    }

    /**
     * 同意退款
     */
    @PutMapping("/refund/approve/{refundId}")
    @ApiOperation("同意退款")
    public Result<Boolean> approveRefund(@PathVariable @NotNull Long refundId) {
        try {
            log.info("同意退款: {}", refundId);
            boolean success = tourOrderService.approveRefund(refundId);
            if (success) {
                return Result.success("退款同意成功");
            } else {
                return Result.error("退款同意失败");
            }
        } catch (Exception e) {
            log.error("同意退款失败: refundId={}", refundId, e);
            return Result.error("退款同意失败: " + e.getMessage());
        }
    }

    /**
     * 拒绝退款
     */
    @PutMapping("/refund/reject/{refundId}")
    @ApiOperation("拒绝退款")
    public Result<Boolean> rejectRefund(@PathVariable @NotNull Long refundId) {
        try {
            log.info("拒绝退款: {}", refundId);
            boolean success = tourOrderService.rejectRefund(refundId);
            if (success) {
                return Result.success("退款拒绝成功");
            } else {
                return Result.error("退款拒绝失败");
            }
        } catch (Exception e) {
            log.error("拒绝退款失败: refundId={}", refundId, e);
            return Result.error("退款拒绝失败: " + e.getMessage());
        }
    }

    /**
     * 获取订单统计信息
     */
    @GetMapping("/statistics")
    @ApiOperation("获取订单统计信息")
    public Result<OrderStatisticsVO> getOrderStatistics() {
        try {
            log.info("获取订单统计信息");
            OrderStatisticsVO statistics = tourOrderService.getOrderStatistics();
            return Result.success(statistics);
        } catch (Exception e) {
            log.error("获取订单统计信息失败", e);
            e.printStackTrace();
            return Result.error("获取统计信息失败: " + e.getMessage());
        }
    }

    /**
     * 导出订单数据
     */
    @PostMapping("/export")
    @ApiOperation("导出订单数据")
    public Result<List<OrderVO>> exportOrders(@Valid @RequestBody OrderSearchDTO searchDTO) {
        try {
            log.info("导出订单数据: {}", searchDTO);
            List<OrderVO> orders = tourOrderService.exportOrders(searchDTO);
            return Result.success(orders);
        } catch (Exception e) {
            log.error("导出订单数据失败", e);
            return Result.error("导出订单失败: " + e.getMessage());
        }
    }

    /**
     * 根据状态获取订单列表
     */
    @GetMapping("/status/{status}")
    @ApiOperation("根据状态获取订单列表")
    public Result<List<TourOrder>> getOrdersByStatus(@PathVariable @NotNull Byte status) {
        try {
            log.info("根据状态获取订单列表: {}", status);
            List<TourOrder> orders = tourOrderService.getOrdersByStatus(status);
            return Result.success(orders);
        } catch (Exception e) {
            log.error("根据状态获取订单列表失败: status={}", status, e);
            return Result.error("获取订单列表失败: " + e.getMessage());
        }
    }

    /**
     * 根据用户ID获取订单列表
     */
    @GetMapping("/user/{userId}")
    @ApiOperation("根据用户ID获取订单列表")
    public Result<List<TourOrder>> getOrdersByUserId(@PathVariable @NotNull Long userId) {
        try {
            log.info("根据用户ID获取订单列表: {}", userId);
            List<TourOrder> orders = tourOrderService.getOrdersByUserId(userId);
            return Result.success(orders);
        } catch (Exception e) {
            log.error("根据用户ID获取订单列表失败: userId={}", userId, e);
            return Result.error("获取订单列表失败: " + e.getMessage());
        }
    }

    /**
     * 获取用户订单统计
     */
    @GetMapping("/user/{userId}/statistics")
    @ApiOperation("获取用户订单统计")
    public Result<Map<String, Object>> getUserOrderStatistics(@PathVariable @NotNull Long userId) {
        try {
            log.info("获取用户订单统计: {}", userId);
            Map<String, Object> statistics = tourOrderService.getUserOrderStatistics(userId);
            return Result.success(statistics);
        } catch (Exception e) {
            log.error("获取用户订单统计失败: userId={}", userId, e);
            return Result.error("获取用户统计失败: " + e.getMessage());
        }
    }

    /**
     * 更新订单状态
     */
    @PutMapping("/status/{orderId}")
    @ApiOperation("更新订单状态")
    public Result<Boolean> updateOrderStatus(@PathVariable @NotNull Long orderId, 
                                           @RequestParam @NotNull Byte status) {
        try {
            log.info("更新订单状态: orderId={}, status={}", orderId, status);
            boolean success = tourOrderService.updateOrderStatus(orderId, status);
            if (success) {
                return Result.success("订单状态更新成功");
            } else {
                return Result.error("订单状态更新失败");
            }
        } catch (Exception e) {
            log.error("更新订单状态失败: orderId={}, status={}", orderId, status, e);
            return Result.error("更新订单状态失败: " + e.getMessage());
        }
    }
}
