package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.dto.ProductCostExplanationDTO;
import com.wanderlust.travel.travelportal.service.IProductCostExplanationService;
import com.wanderlust.travel.travelportal.vo.ProductCostExplanationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 产品费用说明控制器
 */
@RestController
@RequestMapping("/travel-portal/productCostExplanation")
@Slf4j
public class ProductCostExplanationController {
    
    @Autowired
    private IProductCostExplanationService productCostExplanationService;
    
    /**
     * 添加产品费用说明
     */
    @PostMapping("/add")
    public Result<Boolean> addProductCostExplanation(@Valid @RequestBody ProductCostExplanationDTO costExplanationDTO) {
        log.info("添加产品费用说明: {}", costExplanationDTO);
        boolean success = productCostExplanationService.addProductCostExplanation(costExplanationDTO);
        return success ? Result.success(true) : Result.error("添加费用说明失败");
    }
    
    /**
     * 获取产品费用说明
     */
    @GetMapping("/get/{productId}")
    public Result<ProductCostExplanationVO> getProductCostExplanation(@PathVariable Long productId) {
        log.info("获取产品费用说明: productId={}", productId);
        ProductCostExplanationVO costExplanation = productCostExplanationService.getProductCostExplanation(productId);
        return Result.success(costExplanation);
    }
    
    /**
     * 更新产品费用说明
     */
    @PutMapping("/update/{costId}")
    public Result<Boolean> updateProductCostExplanation(
            @PathVariable Long costId,
            @Valid @RequestBody ProductCostExplanationDTO costExplanationDTO) {
        log.info("更新产品费用说明: costId={}, {}", costId, costExplanationDTO);
        boolean success = productCostExplanationService.updateProductCostExplanation(costId, costExplanationDTO);
        return success ? Result.success(true) : Result.error("更新费用说明失败");
    }
    
    /**
     * 删除产品费用说明
     */
    @DeleteMapping("/delete/{costId}")
    public Result<Boolean> deleteProductCostExplanation(@PathVariable Long costId) {
        log.info("删除产品费用说明: costId={}", costId);
        boolean success = productCostExplanationService.deleteProductCostExplanation(costId);
        return success ? Result.success(true) : Result.error("删除费用说明失败");
    }
}