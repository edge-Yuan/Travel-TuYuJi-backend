package com.wanderlust.travel.traveladmin.controller;

import com.wanderlust.travel.traveladmin.service.ProductStatisticsService;
import com.wanderlust.travel.traveladmin.vo.ProductStatisticsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品统计数据控制器
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Slf4j
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@Validated
public class ProductStatisticsController {

    private final ProductStatisticsService productStatisticsService;

    /**
     * 获取产品统计数据
     */
    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getProductStatistics(
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            ProductStatisticsVO statistics = productStatisticsService.getProductStatistics(merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", statistics);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取产品统计数据失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取产品类型统计
     */
    @GetMapping("/productTypes")
    public ResponseEntity<Map<String, Object>> getProductTypeStatistics(
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            List<ProductStatisticsVO.ProductTypeStatVO> typeStatistics = 
                    productStatisticsService.getProductTypeStatistics(merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", typeStatistics);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取产品类型统计失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取热销产品列表
     */
    @GetMapping("/topSelling")
    public ResponseEntity<Map<String, Object>> getTopSellingProducts(
            @RequestHeader("X-Merchant-Id") Long merchantId,
            @RequestParam(defaultValue = "10") Integer limit) {
        
        try {
            List<ProductStatisticsVO.TopSellingProductVO> topSellingProducts = 
                    productStatisticsService.getTopSellingProducts(merchantId, limit);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", topSellingProducts);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取热销产品列表失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取销售趋势数据
     */
    @GetMapping("/salesTrend")
    public ResponseEntity<Map<String, Object>> getSalesTrend(
            @RequestHeader("X-Merchant-Id") Long merchantId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        
        try {
            List<ProductStatisticsVO.SalesTrendVO> salesTrend = 
                    productStatisticsService.getSalesTrend(merchantId, startDate, endDate);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", salesTrend);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取销售趋势数据失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 更新统计数据
     */
    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> updateStatistics(
            @RequestHeader("X-Merchant-Id") Long merchantId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate statDate) {
        
        try {
            productStatisticsService.updateStatistics(merchantId, statDate);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "统计数据更新成功");
            result.put("data", null);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("更新统计数据失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "更新失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }
}
