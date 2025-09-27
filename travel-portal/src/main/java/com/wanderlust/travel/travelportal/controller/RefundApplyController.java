package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.RefundApply;
import com.wanderlust.travel.travelportal.service.IRefundApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * 退款申请表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@RestController
@RequestMapping("/travel-portal/refundApply")
@Slf4j
public class RefundApplyController {
    @Autowired
    private IRefundApplyService refundApplyService;

    @GetMapping("/list")
    public Result<List<RefundApply>> listAll() {
        List<RefundApply> refunds = refundApplyService.list();
        return Result.success(refunds);
    }

    @GetMapping("/{id}")
    public Result<RefundApply> getById(@PathVariable Long id) {
        RefundApply refund = refundApplyService.getById(id);
        return refund == null ? Result.error("退款申请不存在") : Result.success(refund);
    }

    @PostMapping("/create")
    public Result<Boolean> createRefundApply(@RequestBody RefundApply refundApply) {
        log.info("创建退款申请: {}", refundApply);
        boolean success = refundApplyService.save(refundApply);
        return success ? Result.success(true) : Result.error("创建退款申请失败");
    }

    @PutMapping("/update")
    public Result<Boolean> updateRefundApply(@RequestBody RefundApply refundApply) {
        log.info("更新退款申请: {}", refundApply);
        boolean success = refundApplyService.updateById(refundApply);
        return success ? Result.success(true) : Result.error("更新退款申请失败");
    }

    @PutMapping("/approve/{id}")
    public Result<Boolean> approveRefund(@PathVariable Long id, @RequestParam String remark) {
        RefundApply refund = refundApplyService.getById(id);
        if (refund == null) return Result.error("退款申请不存在");
        refund.setAuditStatus((byte)1); // 1-已通过
        refund.setAuditRemark(remark);
        boolean success = refundApplyService.updateById(refund);
        return success ? Result.success(true) : Result.error("审核通过失败");
    }

    @PutMapping("/reject/{id}")
    public Result<Boolean> rejectRefund(@PathVariable Long id, @RequestParam String remark) {
        RefundApply refund = refundApplyService.getById(id);
        if (refund == null) return Result.error("退款申请不存在");
        refund.setAuditStatus((byte)2); // 2-已驳回
        refund.setAuditRemark(remark);
        boolean success = refundApplyService.updateById(refund);
        return success ? Result.success(true) : Result.error("审核驳回失败");
    }

    @GetMapping("/search")
    public Result<List<RefundApply>> searchRefunds(
            @RequestParam(required = false) Long orderId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Byte auditStatus) {
        List<RefundApply> refunds = refundApplyService.lambdaQuery()
                .eq(orderId != null, RefundApply::getOrderId, orderId)
                .eq(userId != null, RefundApply::getUserId, userId)
                .eq(auditStatus != null, RefundApply::getAuditStatus, auditStatus)
                .list();
        return Result.success(refunds);
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteRefundApply(@PathVariable Long id) {
        log.info("删除退款申请: {}", id);
        boolean success = refundApplyService.removeById(id);
        return success ? Result.success(true) : Result.error("删除退款申请失败");
    }

    @GetMapping("/user/{userId}")
    public Result<List<RefundApply>> getRefundsByUser(@PathVariable Long userId) {
        List<RefundApply> refunds = refundApplyService.lambdaQuery()
            .eq(RefundApply::getUserId, userId)
            .orderByDesc(RefundApply::getApplyTime)
            .list();
        return Result.success(refunds);
    }

    @GetMapping("/order/{orderId}")
    public Result<List<RefundApply>> getRefundsByOrder(@PathVariable Long orderId) {
        List<RefundApply> refunds = refundApplyService.lambdaQuery()
            .eq(RefundApply::getOrderId, orderId)
            .orderByDesc(RefundApply::getApplyTime)
            .list();
        return Result.success(refunds);
    }

    @GetMapping("/status/{status}")
    public Result<List<RefundApply>> getRefundsByStatus(@PathVariable Byte status) {
        List<RefundApply> refunds = refundApplyService.lambdaQuery()
            .eq(RefundApply::getAuditStatus, status)
            .orderByDesc(RefundApply::getApplyTime)
            .list();
        return Result.success(refunds);
    }

    @GetMapping("/statistics")
    public Result<Map<String, Object>> getRefundStatistics() {
        long totalRefunds = refundApplyService.count();
        long pendingRefunds = refundApplyService.lambdaQuery()
            .eq(RefundApply::getAuditStatus, (byte)0)
            .count();
        long approvedRefunds = refundApplyService.lambdaQuery()
            .eq(RefundApply::getAuditStatus, (byte)1)
            .count();
        long rejectedRefunds = refundApplyService.lambdaQuery()
            .eq(RefundApply::getAuditStatus, (byte)2)
            .count();
        
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("totalRefunds", totalRefunds);
        statistics.put("pendingRefunds", pendingRefunds);
        statistics.put("approvedRefunds", approvedRefunds);
        statistics.put("rejectedRefunds", rejectedRefunds);
        
        return Result.success(statistics);
    }
}