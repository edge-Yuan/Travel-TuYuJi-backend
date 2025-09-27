package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.TourOrder;
import com.wanderlust.travel.travelportal.service.ITourOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * 旅游订单表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@RestController
@RequestMapping("/travel-portal/tourOrder")
@Slf4j
public class TourOrderController {
	@Autowired
	private ITourOrderService tourOrderService;

	@GetMapping("/list")
	public Result<List<TourOrder>> listAll() {
		List<TourOrder> orders = tourOrderService.list();
		return Result.success(orders);
	}

	@GetMapping("/search")
	public Result<List<TourOrder>> search(@RequestParam(required = false) Byte status,
	                                     @RequestParam(required = false) Long userId,
	                                     @RequestParam(required = false) Long productId) {
		List<TourOrder> orders = tourOrderService.lambdaQuery()
			.eq(status != null, TourOrder::getOrderStatus, status)
			.eq(userId != null, TourOrder::getUserId, userId)
			.eq(productId != null, TourOrder::getProductId, productId)
			.list();
		return Result.success(orders);
	}

	@GetMapping("/{id}")
	public Result<TourOrder> getById(@PathVariable Long id) {
		TourOrder order = tourOrderService.getById(id);
		return order == null ? Result.error("订单不存在") : Result.success(order);
	}

	@PostMapping("/create")
	public Result<Boolean> create(@RequestBody TourOrder tourOrder) {
		log.info("创建订单: {}", tourOrder);
		boolean success = tourOrderService.save(tourOrder);
		return success ? Result.success(true) : Result.error("创建订单失败");
	}

	@PutMapping("/update")
	public Result<Boolean> update(@RequestBody TourOrder tourOrder) {
		log.info("更新订单: {}", tourOrder);
		boolean success = tourOrderService.updateById(tourOrder);
		return success ? Result.success(true) : Result.error("更新订单失败");
	}

	@PutMapping("/confirm/{id}")
	public Result<Boolean> confirm(@PathVariable Long id) {
		TourOrder order = tourOrderService.getById(id);
		if (order == null) return Result.error("订单不存在");
		order.setOrderStatus((byte)1);
		return tourOrderService.updateById(order) ? Result.success(true) : Result.error("确认失败");
	}

	@PutMapping("/complete/{id}")
	public Result<Boolean> complete(@PathVariable Long id) {
		TourOrder order = tourOrderService.getById(id);
		if (order == null) return Result.error("订单不存在");
		order.setOrderStatus((byte)2);
		return tourOrderService.updateById(order) ? Result.success(true) : Result.error("完成失败");
	}

	@PutMapping("/refund/{id}")
	public Result<Boolean> refund(@PathVariable Long id) {
		TourOrder order = tourOrderService.getById(id);
		if (order == null) return Result.error("订单不存在");
		order.setOrderStatus((byte)4);
		return tourOrderService.updateById(order) ? Result.success(true) : Result.error("退款失败");
	}

	@DeleteMapping("/{id}")
	public Result<Boolean> delete(@PathVariable Long id) {
		log.info("删除订单: {}", id);
		boolean success = tourOrderService.removeById(id);
		return success ? Result.success(true) : Result.error("删除订单失败");
	}

	@GetMapping("/user/{userId}")
	public Result<List<TourOrder>> getOrdersByUser(@PathVariable Long userId) {
		List<TourOrder> orders = tourOrderService.lambdaQuery()
			.eq(TourOrder::getUserId, userId)
			.orderByDesc(TourOrder::getCreateTime)
			.list();
		return Result.success(orders);
	}

	@GetMapping("/status/{status}")
	public Result<List<TourOrder>> getOrdersByStatus(@PathVariable Byte status) {
		List<TourOrder> orders = tourOrderService.lambdaQuery()
			.eq(TourOrder::getOrderStatus, status)
			.orderByDesc(TourOrder::getCreateTime)
			.list();
		return Result.success(orders);
	}

	@PutMapping("/cancel/{id}")
	public Result<Boolean> cancelOrder(@PathVariable Long id) {
		TourOrder order = tourOrderService.getById(id);
		if (order == null) return Result.error("订单不存在");
		order.setOrderStatus((byte)3); // 已取消
		order.setCancelTime(java.time.LocalDateTime.now());
		return tourOrderService.updateById(order) ? Result.success(true) : Result.error("取消订单失败");
	}

	@GetMapping("/statistics/{userId}")
	public Result<Map<String, Object>> getOrderStatistics(@PathVariable Long userId) {
		// 获取用户订单统计信息
		long totalOrders = tourOrderService.lambdaQuery()
			.eq(TourOrder::getUserId, userId)
			.count();
		
		long pendingOrders = tourOrderService.lambdaQuery()
			.eq(TourOrder::getUserId, userId)
			.eq(TourOrder::getOrderStatus, (byte)0)
			.count();
		
		long completedOrders = tourOrderService.lambdaQuery()
			.eq(TourOrder::getUserId, userId)
			.eq(TourOrder::getOrderStatus, (byte)2)
			.count();
		
		Map<String, Object> statistics = new HashMap<>();
		statistics.put("totalOrders", totalOrders);
		statistics.put("pendingOrders", pendingOrders);
		statistics.put("completedOrders", completedOrders);
		
		return Result.success(statistics);
	}
}
