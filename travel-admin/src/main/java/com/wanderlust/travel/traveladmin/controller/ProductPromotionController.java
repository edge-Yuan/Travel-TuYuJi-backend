package com.wanderlust.travel.traveladmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.dto.PromotionCreateDTO;
import com.wanderlust.travel.traveladmin.service.ProductPromotionService;
import com.wanderlust.travel.traveladmin.vo.PromotionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品优惠活动管理控制器
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Slf4j
@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
@Validated
public class ProductPromotionController {

    private final ProductPromotionService productPromotionService;

    /**
     * 创建优惠活动
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPromotion(
            @Valid @RequestBody PromotionCreateDTO createDTO,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            Long promotionId = productPromotionService.createPromotion(createDTO, merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "优惠活动创建成功");
            result.put("data", Map.of(
                "promotionId", promotionId,
                "name", createDTO.getName(),
                "createTime", java.time.LocalDateTime.now()
            ));
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("创建优惠活动失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "创建失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取优惠活动列表
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getPromotionList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer status,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            Page<PromotionVO> pageParam = new Page<>(page, size);
            IPage<PromotionVO> promotionPage = productPromotionService.getPromotionPage(pageParam, merchantId, status);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", promotionPage.getRecords());
            result.put("total", promotionPage.getTotal());
            result.put("page", promotionPage.getCurrent());
            result.put("size", promotionPage.getSize());
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取优惠活动列表失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取优惠活动详情
     */
    @GetMapping("/detail/{promotionId}")
    public ResponseEntity<Map<String, Object>> getPromotionDetail(
            @PathVariable @NotNull Long promotionId) {
        
        try {
            PromotionVO promotion = productPromotionService.getPromotionDetail(promotionId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", promotion);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取优惠活动详情失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 更新优惠活动
     */
    @PutMapping("/update/{promotionId}")
    public ResponseEntity<Map<String, Object>> updatePromotion(
            @PathVariable @NotNull Long promotionId,
            @Valid @RequestBody PromotionCreateDTO createDTO,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            boolean success = productPromotionService.updatePromotion(promotionId, createDTO, merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "优惠活动更新成功");
            result.put("data", Map.of(
                "promotionId", promotionId,
                "updateTime", java.time.LocalDateTime.now()
            ));
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("更新优惠活动失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "更新失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 删除优惠活动
     */
    @DeleteMapping("/delete/{promotionId}")
    public ResponseEntity<Map<String, Object>> deletePromotion(
            @PathVariable @NotNull Long promotionId,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            boolean success = productPromotionService.deletePromotion(promotionId, merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "优惠活动删除成功");
            result.put("data", null);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("删除优惠活动失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "删除失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 更新优惠活动状态
     */
    @PutMapping("/status/{promotionId}")
    public ResponseEntity<Map<String, Object>> updatePromotionStatus(
            @PathVariable @NotNull Long promotionId,
            @RequestParam @NotNull Integer status,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            boolean success = productPromotionService.updatePromotionStatus(promotionId, status, merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "状态更新成功");
            result.put("data", Map.of(
                "promotionId", promotionId,
                "status", status
            ));
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("更新优惠活动状态失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "状态更新失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取商家的所有优惠活动
     */
    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<Map<String, Object>> getPromotionsByMerchant(
            @PathVariable @NotNull Long merchantId) {
        
        try {
            List<PromotionVO> promotions = productPromotionService.getPromotionsByMerchant(merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", promotions);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取商家优惠活动失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取有效的优惠活动
     */
    @GetMapping("/active/{merchantId}")
    public ResponseEntity<Map<String, Object>> getActivePromotions(
            @PathVariable @NotNull Long merchantId) {
        
        try {
            List<PromotionVO> promotions = productPromotionService.getActivePromotions(merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", promotions);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取有效优惠活动失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }
}
