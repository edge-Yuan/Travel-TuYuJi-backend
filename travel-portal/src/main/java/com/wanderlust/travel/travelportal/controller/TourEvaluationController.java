package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.TourEvaluation;
import com.wanderlust.travel.travelportal.service.ITourEvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 旅游评价表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@RestController
@RequestMapping("/travel-portal/tourEvaluation")
@Slf4j
public class TourEvaluationController {
    @Autowired
    private ITourEvaluationService tourEvaluationService;

    @GetMapping("/list")
    public Result<List<TourEvaluation>> listAll() {
        List<TourEvaluation> evaluations = tourEvaluationService.list();
        return Result.success(evaluations);
    }

    @GetMapping("/{id}")
    public Result<TourEvaluation> getById(@PathVariable Long id) {
        TourEvaluation evaluation = tourEvaluationService.getById(id);
        return evaluation == null ? Result.error("评价不存在") : Result.success(evaluation);
    }

    @PostMapping("/create")
    public Result<Boolean> createEvaluation(@RequestBody TourEvaluation tourEvaluation) {
        log.info("创建评价: {}", tourEvaluation);
        boolean success = tourEvaluationService.save(tourEvaluation);
        return success ? Result.success(true) : Result.error("创建评价失败");
    }

    @PutMapping("/update")
    public Result<Boolean> updateEvaluation(@RequestBody TourEvaluation tourEvaluation) {
        log.info("更新评价: {}", tourEvaluation);
        boolean success = tourEvaluationService.updateById(tourEvaluation);
        return success ? Result.success(true) : Result.error("更新评价失败");
    }

    @GetMapping("/byProduct/{productId}")
    public Result<List<TourEvaluation>> getByProductId(@PathVariable Long productId) {
        List<TourEvaluation> evaluations = tourEvaluationService.lambdaQuery()
                .eq(TourEvaluation::getProductId, productId)
                .orderByDesc(TourEvaluation::getCreateTime)
                .list();
        return Result.success(evaluations);
    }

    @GetMapping("/byUser/{userId}")
    public Result<List<TourEvaluation>> getByUserId(@PathVariable Long userId) {
        List<TourEvaluation> evaluations = tourEvaluationService.lambdaQuery()
                .eq(TourEvaluation::getUserId, userId)
                .orderByDesc(TourEvaluation::getCreateTime)
                .list();
        return Result.success(evaluations);
    }

    @GetMapping("/search")
    public Result<List<TourEvaluation>> searchEvaluations(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Byte overallScore) {
        List<TourEvaluation> evaluations = tourEvaluationService.lambdaQuery()
                .eq(productId != null, TourEvaluation::getProductId, productId)
                .eq(userId != null, TourEvaluation::getUserId, userId)
                .eq(overallScore != null, TourEvaluation::getOverallScore, overallScore)
                .orderByDesc(TourEvaluation::getCreateTime)
                .list();
        return Result.success(evaluations);
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteEvaluation(@PathVariable Long id) {
        log.info("删除评价: {}", id);
        boolean success = tourEvaluationService.removeById(id);
        return success ? Result.success(true) : Result.error("删除评价失败");
    }
}