package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.MerchantExtend;
import com.wanderlust.travel.travelportal.service.IMerchantExtendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商户扩展信息表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@RestController
@RequestMapping("/travel-portal/merchantExtend")
@Slf4j
public class MerchantExtendController {
    @Autowired
    private IMerchantExtendService merchantExtendService;

    @GetMapping("/list")
    public Result<List<MerchantExtend>> listAll() {
        List<MerchantExtend> merchants = merchantExtendService.list();
        return Result.success(merchants);
    }

    @GetMapping("/{id}")
    public Result<MerchantExtend> getById(@PathVariable Long id) {
        MerchantExtend merchant = merchantExtendService.getById(id);
        return merchant == null ? Result.error("商户信息不存在") : Result.success(merchant);
    }

    @PostMapping("/create")
    public Result<Boolean> createMerchant(@RequestBody MerchantExtend merchantExtend) {
        log.info("创建商户信息: {}", merchantExtend);
        boolean success = merchantExtendService.save(merchantExtend);
        return success ? Result.success(true) : Result.error("创建商户信息失败");
    }

    @PutMapping("/update")
    public Result<Boolean> updateMerchant(@RequestBody MerchantExtend merchantExtend) {
        log.info("更新商户信息: {}", merchantExtend);
        boolean success = merchantExtendService.updateById(merchantExtend);
        return success ? Result.success(true) : Result.error("更新商户信息失败");
    }

    @PutMapping("/approve/{id}")
    public Result<Boolean> approveMerchant(@PathVariable Long id) {
        MerchantExtend merchant = merchantExtendService.getById(id);
        if (merchant == null) return Result.error("商户信息不存在");
        merchant.setAuditStatus((byte)1); // 1-已通过
        boolean success = merchantExtendService.updateById(merchant);
        return success ? Result.success(true) : Result.error("审核通过失败");
    }

    @PutMapping("/reject/{id}")
    public Result<Boolean> rejectMerchant(@PathVariable Long id, @RequestParam String reason) {
        MerchantExtend merchant = merchantExtendService.getById(id);
        if (merchant == null) return Result.error("商户信息不存在");
        merchant.setAuditStatus((byte)2); // 2-已驳回
        merchant.setRejectReason(reason);
        boolean success = merchantExtendService.updateById(merchant);
        return success ? Result.success(true) : Result.error("审核驳回失败");
    }

    @GetMapping("/search")
    public Result<List<MerchantExtend>> searchMerchants(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Byte auditStatus,
            @RequestParam(required = false) String location) {
        List<MerchantExtend> merchants = merchantExtendService.lambdaQuery()
                .like(keyword != null, MerchantExtend::getCompanyName, keyword)
                .or()
                .like(keyword != null, MerchantExtend::getContactPerson, keyword)
                .eq(auditStatus != null, MerchantExtend::getAuditStatus, auditStatus)
                .like(location != null, MerchantExtend::getLocation, location)
                .list();
        return Result.success(merchants);
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteMerchant(@PathVariable Long id) {
        log.info("删除商户信息: {}", id);
        boolean success = merchantExtendService.removeById(id);
        return success ? Result.success(true) : Result.error("删除商户信息失败");
    }
}