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

	@PostMapping("/submit")
	public Result<Map<String, Object>> submitOrder(@RequestBody Map<String, Object> orderData) {
		log.info("提交订单: {}", orderData);
		
		try {
			// 创建订单对象
			TourOrder order = new TourOrder();
			order.setUserId(Long.valueOf(orderData.get("userId").toString()));
			order.setProductId(Long.valueOf(orderData.get("productId").toString()));
			order.setTotalPrice(new java.math.BigDecimal(orderData.get("totalPrice").toString()));
			order.setPayType(Byte.valueOf(orderData.get("payType").toString()));
			Object bookingDateVal = orderData.get("bookingDate");
			if (bookingDateVal != null) {
				String bookingDateStr = bookingDateVal.toString().trim();
				if (!bookingDateStr.isEmpty() && !"null".equalsIgnoreCase(bookingDateStr)) {
					order.setBookingDate(java.time.LocalDate.parse(bookingDateStr));
				}
			}
			// 若未提供预订日期，使用当前日期以满足数据库非空约束
			if (order.getBookingDate() == null) {
				order.setBookingDate(java.time.LocalDate.now());
			}
			order.setTravellers(Integer.valueOf(orderData.get("travellers").toString()));
			// 兼容历史字段 travellers（库约束非空），保持与 person_count 一致
			order.setTravellersLegacy(order.getTravellers());
			order.setSpecialNeeds(orderData.get("specialNeeds") != null ? orderData.get("specialNeeds").toString() : null);
			
			// 收货地址信息
			if (orderData.get("receiverName") != null) {
				order.setReceiverName(orderData.get("receiverName").toString());
				order.setReceiverPhone(orderData.get("receiverPhone").toString());
				order.setReceiverProvince(orderData.get("receiverProvince").toString());
				order.setReceiverCity(orderData.get("receiverCity").toString());
				order.setReceiverDistrict(orderData.get("receiverDistrict").toString());
				order.setReceiverAddress(orderData.get("receiverAddress").toString());
			}
			
			// 订单备注
			if (orderData.get("orderRemark") != null) {
				order.setOrderRemark(orderData.get("orderRemark").toString());
			}
			
			// 优惠券信息
			if (orderData.get("couponId") != null) {
				order.setCouponId(Long.valueOf(orderData.get("couponId").toString()));
				order.setCouponDiscount(new java.math.BigDecimal(orderData.get("couponDiscount").toString()));
			}
			
			// 运费
			if (orderData.get("shippingFee") != null) {
				order.setShippingFee(new java.math.BigDecimal(orderData.get("shippingFee").toString()));
			}
			
			// 商品数量
			if (orderData.get("productQuantity") != null) {
				order.setProductQuantity(Integer.valueOf(orderData.get("productQuantity").toString()));
			}
			
			// 设置订单状态
			order.setOrderStatus((byte)0); // 待确认
			order.setPayStatus((byte)0);  // 待支付
			order.setCreateTime(java.time.LocalDateTime.now());
			
			boolean success = tourOrderService.save(order);
			if (success) {
				Map<String, Object> result = new HashMap<>();
				result.put("orderId", order.getOrderId());
				result.put("orderNumber", "ORD" + order.getOrderId() + System.currentTimeMillis());
				result.put("totalPrice", order.getTotalPrice());
				return Result.success(result);
			} else {
				return Result.error("创建订单失败");
			}
		} catch (Exception e) {
			log.error("创建订单异常", e);
			return Result.error("创建订单失败: " + e.getMessage());
		}
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

	/**
	 * 获取用户订单数量
	 */
	@GetMapping("/order/user/{userId}/count")
	public Result<Long> getUserOrderCount(@PathVariable Long userId) {
		try {
			log.info("获取用户订单数量: userId={}", userId);
			
			long count = tourOrderService.lambdaQuery()
				.eq(TourOrder::getUserId, userId)
				.count();
			
			log.info("用户 {} 的订单数量: {}", userId, count);
			return Result.success(count);
		} catch (Exception e) {
			log.error("获取用户订单数量失败: userId={}", userId, e);
			return Result.error("获取订单数量失败：" + e.getMessage());
		}
	}
}
