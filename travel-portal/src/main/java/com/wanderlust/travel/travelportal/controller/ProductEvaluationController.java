package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.dto.ProductEvaluationDTO;
import com.wanderlust.travel.travelportal.service.IProductEvaluationService;
import com.wanderlust.travel.travelportal.vo.ProductEvaluationSummaryVO;
import com.wanderlust.travel.travelportal.vo.ProductEvaluationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 产品评价控制器
 */
@RestController
@RequestMapping("/travel-portal/productEvaluation")
@Slf4j
public class ProductEvaluationController {
    
    @Autowired
    private IProductEvaluationService productEvaluationService;
    
    /**
     * 添加产品评价
     */
    @PostMapping("/add")
    public Result<Boolean> addProductEvaluation(@Valid @RequestBody ProductEvaluationDTO evaluationDTO) {
        log.info("添加产品评价: {}", evaluationDTO);
        boolean success = productEvaluationService.addProductEvaluation(evaluationDTO);
        return success ? Result.success(true) : Result.error("添加评价失败");
    }
    
    /**
     * 获取产品评价列表
     */
    @GetMapping("/list/{productId}")
    public Result<List<ProductEvaluationVO>> getProductEvaluations(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        log.info("获取产品评价列表: productId={}, page={}, size={}", productId, page, size);
        List<ProductEvaluationVO> evaluations = productEvaluationService.getProductEvaluations(productId, page, size);
        return Result.success(evaluations);
    }
    
    /**
     * 获取产品评价汇总信息
     */
    @GetMapping("/summary/{productId}")
    public Result<ProductEvaluationSummaryVO> getProductEvaluationSummary(@PathVariable Long productId) {
        log.info("获取产品评价汇总: productId={}", productId);
        ProductEvaluationSummaryVO summary = productEvaluationService.getProductEvaluationSummary(productId);
        return Result.success(summary);
    }
    
    /**
     * 回复评价
     */
    @PostMapping("/reply/{evalId}")
    public Result<Boolean> replyEvaluation(
            @PathVariable Long evalId,
            @RequestParam String replyContent) {
        log.info("回复评价: evalId={}, replyContent={}", evalId, replyContent);
        boolean success = productEvaluationService.replyEvaluation(evalId, replyContent);
        return success ? Result.success(true) : Result.error("回复评价失败");
    }
    
    /**
     * 获取用户对产品的评价
     */
    @GetMapping("/user/{userId}/product/{productId}")
    public Result<ProductEvaluationVO> getUserProductEvaluation(
            @PathVariable Long userId,
            @PathVariable Long productId) {
        log.info("获取用户产品评价: userId={}, productId={}", userId, productId);
        ProductEvaluationVO evaluation = productEvaluationService.getUserProductEvaluation(userId, productId);
        return Result.success(evaluation);
    }
}
