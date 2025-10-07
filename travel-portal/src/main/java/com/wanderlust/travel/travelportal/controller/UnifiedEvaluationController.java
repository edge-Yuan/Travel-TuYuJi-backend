package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.dto.EvaluationReplyDTO;
import com.wanderlust.travel.travelportal.dto.UnifiedEvaluationDTO;
import com.wanderlust.travel.travelportal.mapper.TourEvaluationMapper;
import com.wanderlust.travel.travelportal.service.IEvaluationCouponService;
import com.wanderlust.travel.travelportal.service.IEvaluationReplyService;
import com.wanderlust.travel.travelportal.service.IEvaluationUsefulService;
import com.wanderlust.travel.travelportal.service.IUnifiedEvaluationService;
import com.wanderlust.travel.travelportal.vo.EvaluationCouponVO;
import com.wanderlust.travel.travelportal.vo.EvaluationReplyVO;
import com.wanderlust.travel.travelportal.vo.EvaluationUsefulVO;
import com.wanderlust.travel.travelportal.vo.UnifiedEvaluationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 统一评价控制器
 * 整合套餐评价和订单管理评价的接口
 */
@RestController
@RequestMapping("/travel-portal/evaluation")
@Slf4j
public class UnifiedEvaluationController {
    
    @Autowired
    private IUnifiedEvaluationService unifiedEvaluationService;
    
    @Autowired
    private TourEvaluationMapper tourEvaluationMapper;
    
    @Autowired
    private IEvaluationReplyService evaluationReplyService;
    
    @Autowired
    private IEvaluationUsefulService evaluationUsefulService;
    
    @Autowired
    private IEvaluationCouponService evaluationCouponService;
    
    /**
     * 添加评价
     */
    @PostMapping("/add")
    public Result<Boolean> addEvaluation(@Valid @RequestBody UnifiedEvaluationDTO evaluationDTO) {
        log.info("添加评价: {}", evaluationDTO);
        boolean success = unifiedEvaluationService.addEvaluation(evaluationDTO);
        return success ? Result.success(true) : Result.error("添加评价失败");
    }
    
    /**
     * 获取产品评价列表
     */
    @GetMapping("/product/{productId}")
    public Result<List<UnifiedEvaluationVO>> getProductEvaluations(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        log.info("获取产品评价列表: productId={}, page={}, size={}", productId, page, size);
        List<UnifiedEvaluationVO> evaluations = unifiedEvaluationService.getProductEvaluations(productId, page, size);
        return Result.success(evaluations);
    }
    
    /**
     * 获取产品评价列表（带当前用户ID，支持显示完整用户名）
     */
    @GetMapping("/product/{productId}/user/{currentUserId}")
    public Result<List<UnifiedEvaluationVO>> getProductEvaluationsWithUser(
            @PathVariable Long productId,
            @PathVariable Long currentUserId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        log.info("获取产品评价列表（带用户ID）: productId={}, currentUserId={}, page={}, size={}", 
                productId, currentUserId, page, size);
        List<UnifiedEvaluationVO> evaluations = unifiedEvaluationService.getProductEvaluations(productId, page, size, currentUserId);
        return Result.success(evaluations);
    }
    
    /**
     * 获取订单评价列表
     */
    @GetMapping("/order/{orderId}")
    public Result<List<UnifiedEvaluationVO>> getOrderEvaluations(@PathVariable Long orderId) {
        log.info("获取订单评价列表: orderId={}", orderId);
        List<UnifiedEvaluationVO> evaluations = unifiedEvaluationService.getOrderEvaluations(orderId);
        return Result.success(evaluations);
    }
    
    /**
     * 获取用户评价列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<UnifiedEvaluationVO>> getUserEvaluations(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        log.info("获取用户评价列表: userId={}, page={}, size={}", userId, page, size);
        List<UnifiedEvaluationVO> evaluations = unifiedEvaluationService.getUserEvaluations(userId, page, size);
        return Result.success(evaluations);
    }
    
    /**
     * 获取用户对产品的评价
     */
    @GetMapping("/user/{userId}/product/{productId}")
    public Result<UnifiedEvaluationVO> getUserProductEvaluation(
            @PathVariable Long userId,
            @PathVariable Long productId) {
        log.info("获取用户产品评价: userId={}, productId={}", userId, productId);
        UnifiedEvaluationVO evaluation = unifiedEvaluationService.getUserProductEvaluation(userId, productId);
        return Result.success(evaluation);
    }
    
    /**
     * 获取产品评价汇总信息
     */
    @GetMapping("/product/{productId}/summary")
    public Result<Object> getProductEvaluationSummary(@PathVariable Long productId) {
        log.info("获取产品评价汇总: productId={}", productId);
        Object summary = unifiedEvaluationService.getProductEvaluationSummary(productId);
        return Result.success(summary);
    }
    
    /**
     * 编辑评价
     */
    @PutMapping("/{evalId}")
    public Result<Boolean> updateEvaluation(
            @PathVariable Long evalId,
            @Valid @RequestBody UnifiedEvaluationDTO evaluationDTO) {
        log.info("编辑评价: evalId={}, evaluationDTO={}", evalId, evaluationDTO);
        boolean success = unifiedEvaluationService.updateEvaluation(evalId, evaluationDTO);
        return success ? Result.success(true) : Result.error("编辑评价失败");
    }
    
    /**
     * 回复评价（旧版本，保持兼容性）
     */
    @PostMapping("/reply/{evalId}")
    public Result<Boolean> replyEvaluation(
            @PathVariable Long evalId,
            @RequestParam String replyContent) {
        log.info("回复评价: evalId={}, replyContent={}", evalId, replyContent);
        boolean success = unifiedEvaluationService.replyEvaluation(evalId, replyContent);
        return success ? Result.success(true) : Result.error("回复评价失败");
    }
    
    /**
     * 删除评价
     */
    @DeleteMapping("/{evalId}")
    public Result<Boolean> deleteEvaluation(@PathVariable Long evalId) {
        log.info("删除评价: evalId={}", evalId);
        boolean success = unifiedEvaluationService.deleteEvaluation(evalId);
        return success ? Result.success(true) : Result.error("删除评价失败");
    }
    
    /**
     * 获取所有评价（管理员用）
     */
    @GetMapping("/list")
    public Result<List<UnifiedEvaluationVO>> getAllEvaluations(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Byte evalType) {
        log.info("获取所有评价: page={}, size={}, evalType={}", page, size, evalType);
        List<UnifiedEvaluationVO> evaluations = unifiedEvaluationService.getAllEvaluations(page, size, evalType);
        return Result.success(evaluations);
    }
    
    /**
     * 获取用户评价数量
     */
    @GetMapping("/user/{userId}/count")
    public Result<Long> getUserEvaluationCount(@PathVariable Long userId) {
        try {
            log.info("获取用户评价数量: userId={}", userId);
            
            long count = tourEvaluationMapper.selectCount(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<com.wanderlust.travel.travelportal.entity.TourEvaluation>()
                    .eq(com.wanderlust.travel.travelportal.entity.TourEvaluation::getUserId, userId)
            );
            
            log.info("用户 {} 的评价数量: {}", userId, count);
            return Result.success(count);
        } catch (Exception e) {
            log.error("获取用户评价数量失败: userId={}", userId, e);
            return Result.error("获取评价数量失败：" + e.getMessage());
        }
    }

    // ==================== 评价回复功能 ====================

    /**
     * 添加/更新回复（新版本）
     */
    @PostMapping("/reply/{evalId}/new")
    public Result<EvaluationReplyVO> addOrUpdateReply(
            @PathVariable Long evalId,
            @Valid @RequestBody EvaluationReplyDTO replyDTO,
            @RequestParam Long replierId,
            @RequestParam String replierName,
            @RequestParam String replierRole) {
        try {
            log.info("添加/更新回复: evalId={}, replierId={}, replierRole={}", evalId, replierId, replierRole);
            EvaluationReplyVO reply = evaluationReplyService.addOrUpdateReply(evalId, replyDTO, replierId, replierName, replierRole);
            return Result.success(reply);
        } catch (Exception e) {
            log.error("添加/更新回复失败", e);
            return Result.error("操作失败: " + e.getMessage());
        }
    }

    /**
     * 获取回复列表
     */
    @GetMapping("/reply/{evalId}")
    public Result<List<EvaluationReplyVO>> getReplyList(@PathVariable Long evalId) {
        try {
            log.info("获取回复列表: evalId={}", evalId);
            List<EvaluationReplyVO> replies = evaluationReplyService.getReplyList(evalId);
            return Result.success(replies);
        } catch (Exception e) {
            log.error("获取回复列表失败", e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    /**
     * 删除回复
     */
    @DeleteMapping("/reply/{replyId}")
    public Result<Boolean> deleteReply(@PathVariable Long replyId) {
        try {
            log.info("删除回复: replyId={}", replyId);
            boolean success = evaluationReplyService.deleteReply(replyId);
            return success ? Result.success(true) : Result.error("删除失败");
        } catch (Exception e) {
            log.error("删除回复失败", e);
            return Result.error("删除失败: " + e.getMessage());
        }
    }

    // ==================== 评价有用功能 ====================

    /**
     * 切换有用状态
     */
    @PostMapping("/useful/{evalId}")
    public Result<EvaluationUsefulVO> toggleUseful(
            @PathVariable Long evalId,
            @RequestParam Long userId) {
        try {
            log.info("切换有用状态: evalId={}, userId={}", evalId, userId);
            EvaluationUsefulVO useful = evaluationUsefulService.toggleUseful(evalId, userId);
            return Result.success(useful);
        } catch (Exception e) {
            log.error("切换有用状态失败", e);
            return Result.error("操作失败: " + e.getMessage());
        }
    }

    /**
     * 获取有用状态
     */
    @GetMapping("/useful/{evalId}")
    public Result<EvaluationUsefulVO> getUsefulStatus(
            @PathVariable Long evalId,
            @RequestParam Long userId) {
        try {
            log.info("获取有用状态: evalId={}, userId={}", evalId, userId);
            EvaluationUsefulVO useful = evaluationUsefulService.getUsefulStatus(evalId, userId);
            return Result.success(useful);
        } catch (Exception e) {
            log.error("获取有用状态失败", e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    /**
     * 获取有用用户列表
     */
    @GetMapping("/useful/{evalId}/users")
    public Result<Map<String, Object>> getUsefulUsers(
            @PathVariable Long evalId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        try {
            log.info("获取有用用户列表: evalId={}, page={}, size={}", evalId, page, size);
            List<Object> users = evaluationUsefulService.getUsefulUsers(evalId, page, size);
            
            Map<String, Object> result = new java.util.HashMap<>();
            result.put("total", users.size());
            result.put("page", page);
            result.put("size", size);
            result.put("users", users);
            
            return Result.success(result);
        } catch (Exception e) {
            log.error("获取有用用户列表失败", e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    // ==================== 评价优惠券功能 ====================

    /**
     * 领取评价优惠券
     */
    @PostMapping("/coupon/{evalId}")
    public Result<EvaluationCouponVO> claimCoupon(
            @PathVariable Long evalId,
            @RequestParam Long userId) {
        try {
            log.info("领取优惠券: evalId={}, userId={}", evalId, userId);
            EvaluationCouponVO coupon = evaluationCouponService.claimCoupon(evalId, userId);
            return Result.success(coupon);
        } catch (Exception e) {
            log.error("领取优惠券失败", e);
            return Result.error("操作失败: " + e.getMessage());
        }
    }

    /**
     * 检查优惠券领取状态
     */
    @GetMapping("/coupon/{evalId}/status")
    public Result<Map<String, Object>> checkCouponClaimStatus(
            @PathVariable Long evalId,
            @RequestParam Long userId) {
        try {
            log.info("检查优惠券领取状态: evalId={}, userId={}", evalId, userId);
            Map<String, Object> status = evaluationCouponService.checkClaimStatus(evalId, userId);
            return Result.success(status);
        } catch (Exception e) {
            log.error("检查优惠券领取状态失败", e);
            return Result.error("检查失败: " + e.getMessage());
        }
    }
}
