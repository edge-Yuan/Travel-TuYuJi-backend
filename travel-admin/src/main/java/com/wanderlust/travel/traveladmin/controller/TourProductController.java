package com.wanderlust.travel.traveladmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.dto.ProductCreateDTO;
import com.wanderlust.travel.traveladmin.dto.ProductSearchDTO;
import com.wanderlust.travel.traveladmin.service.TourProductService;
import com.wanderlust.travel.traveladmin.vo.ProductVO;
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
 * 旅游产品管理控制器
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Slf4j
@RestController
@RequestMapping("/tourProduct")
@RequiredArgsConstructor
@Validated
public class TourProductController {

    private final TourProductService tourProductService;

    /**
     * 获取所有产品
     */
    @GetMapping("/getAllProducts")
    public ResponseEntity<Map<String, Object>> getAllProducts(
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            List<ProductVO> products = tourProductService.getAllProducts(merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", products);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取产品列表失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 创建产品
     */
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createProduct(
            @Valid @RequestBody ProductCreateDTO createDTO,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            Long productId = tourProductService.createProduct(createDTO, merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "产品创建成功");
            result.put("data", Map.of(
                "productId", productId,
                "productName", createDTO.getProductName(),
                "createTime", java.time.LocalDateTime.now()
            ));
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("创建产品失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "创建失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 更新产品
     */
    @PutMapping("/update/{productId}")
    public ResponseEntity<Map<String, Object>> updateProduct(
            @PathVariable @NotNull Long productId,
            @Valid @RequestBody ProductCreateDTO createDTO,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            boolean success = tourProductService.updateProduct(productId, createDTO, merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "产品更新成功");
            result.put("data", Map.of(
                "productId", productId,
                "updateTime", java.time.LocalDateTime.now()
            ));
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("更新产品失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "更新失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 删除产品
     */
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Map<String, Object>> deleteProduct(
            @PathVariable @NotNull Long productId,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            boolean success = tourProductService.deleteProduct(productId, merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "产品删除成功");
            result.put("data", null);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("删除产品失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "删除失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 批量更新产品状态
     */
    @PutMapping("/batchUpdateStatus")
    public ResponseEntity<Map<String, Object>> batchUpdateStatus(
            @RequestBody Map<String, Object> request,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            @SuppressWarnings("unchecked")
            List<Long> productIds = (List<Long>) request.get("productIds");
            Integer status = (Integer) request.get("status");
            
            Map<String, Integer> result = tourProductService.batchUpdateStatus(productIds, status, merchantId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("code", 1);
            response.put("msg", "批量更新成功");
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("批量更新产品状态失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "批量更新失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取产品详情
     */
    @GetMapping("/detail/{productId}")
    public ResponseEntity<Map<String, Object>> getProductDetail(
            @PathVariable @NotNull Long productId) {
        
        try {
            ProductVO product = tourProductService.getProductDetail(productId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", product);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取产品详情失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 复制产品
     */
    @PostMapping("/copy/{productId}")
    public ResponseEntity<Map<String, Object>> copyProduct(
            @PathVariable @NotNull Long productId,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            Map<String, Object> result = tourProductService.copyProduct(productId, merchantId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("code", 1);
            response.put("msg", "产品复制成功");
            response.put("data", result);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("复制产品失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "复制失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 产品搜索
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchProducts(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "createTime") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            ProductSearchDTO searchDTO = new ProductSearchDTO();
            searchDTO.setPage(page);
            searchDTO.setSize(size);
            searchDTO.setKeyword(keyword);
            searchDTO.setType(type);
            searchDTO.setStatus(status);
            searchDTO.setMinPrice(minPrice != null ? java.math.BigDecimal.valueOf(minPrice) : null);
            searchDTO.setMaxPrice(maxPrice != null ? java.math.BigDecimal.valueOf(maxPrice) : null);
            searchDTO.setSortBy(sortBy);
            searchDTO.setSortOrder(sortOrder);
            searchDTO.setMerchantId(merchantId);
            
            Page<ProductVO> pageParam = new Page<>(page, size);
            IPage<ProductVO> productPage = tourProductService.searchProducts(pageParam, searchDTO);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "搜索成功");
            result.put("data", Map.of(
                "total", productPage.getTotal(),
                "page", productPage.getCurrent(),
                "size", productPage.getSize(),
                "products", productPage.getRecords()
            ));
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("搜索产品失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "搜索失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }
}
