package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.Coupon;
import com.wanderlust.travel.travelportal.service.ICouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 优惠券表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-01-15
 */
@RestController
@RequestMapping("/travel-portal/coupon")
@Slf4j
public class CouponController {

    @Autowired
    private ICouponService couponService;

    @GetMapping("/list")
    public Result<List<Coupon>> listAll() {
        List<Coupon> coupons = couponService.list();
        return Result.success(coupons);
    }

    @GetMapping("/available")
    public Result<List<Coupon>> getAvailableCoupons() {
        List<Coupon> coupons = couponService.lambdaQuery()
            .eq(Coupon::getStatus, (byte)1)
            .le(Coupon::getStartDate, LocalDate.now())
            .ge(Coupon::getEndDate, LocalDate.now())
            .list();
        return Result.success(coupons);
    }

    @GetMapping("/{id}")
    public Result<Coupon> getById(@PathVariable Long id) {
        Coupon coupon = couponService.getById(id);
        return coupon == null ? Result.error("优惠券不存在") : Result.success(coupon);
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Coupon coupon) {
        log.info("创建优惠券: {}", coupon);
        boolean success = couponService.save(coupon);
        return success ? Result.success(true) : Result.error("创建优惠券失败");
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody Coupon coupon) {
        log.info("更新优惠券: {}", coupon);
        boolean success = couponService.updateById(coupon);
        return success ? Result.success(true) : Result.error("更新优惠券失败");
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        log.info("删除优惠券: {}", id);
        boolean success = couponService.removeById(id);
        return success ? Result.success(true) : Result.error("删除优惠券失败");
    }

    @GetMapping("/check/{couponId}")
    public Result<Boolean> checkCoupon(@PathVariable Long couponId, 
                                     @RequestParam BigDecimal orderAmount) {
        Coupon coupon = couponService.getById(couponId);
        if (coupon == null) {
            return Result.error("优惠券不存在");
        }
        
        if (coupon.getStatus() != 1) {
            return Result.error("优惠券已禁用");
        }
        
        LocalDate now = LocalDate.now();
        if (now.isBefore(coupon.getStartDate()) || now.isAfter(coupon.getEndDate())) {
            return Result.error("优惠券已过期");
        }
        
        if (orderAmount.compareTo(coupon.getMinSpend()) < 0) {
            return Result.error("订单金额不满足优惠券使用条件");
        }
        
        return Result.success(true);
    }
}
