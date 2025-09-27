package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.GuideExtend;
import com.wanderlust.travel.travelportal.service.IGuideExtendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 导游扩展信息表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@RestController
@RequestMapping("/travel-portal/guideExtend")
@Slf4j
public class GuideExtendController {
    @Autowired
    private IGuideExtendService guideExtendService;

    @GetMapping("/list")
    public Result<List<GuideExtend>> listAll() {
        List<GuideExtend> guides = guideExtendService.list();
        return Result.success(guides);
    }

    @GetMapping("/{id}")
    public Result<GuideExtend> getById(@PathVariable Long id) {
        GuideExtend guide = guideExtendService.getById(id);
        return guide == null ? Result.error("导游信息不存在") : Result.success(guide);
    }

    @PostMapping("/create")
    public Result<Boolean> createGuide(@RequestBody GuideExtend guideExtend) {
        log.info("创建导游信息: {}", guideExtend);
        boolean success = guideExtendService.save(guideExtend);
        return success ? Result.success(true) : Result.error("创建导游信息失败");
    }

    @PutMapping("/update")
    public Result<Boolean> updateGuide(@RequestBody GuideExtend guideExtend) {
        log.info("更新导游信息: {}", guideExtend);
        boolean success = guideExtendService.updateById(guideExtend);
        return success ? Result.success(true) : Result.error("更新导游信息失败");
    }

    @PutMapping("/approve/{id}")
    public Result<Boolean> approveGuide(@PathVariable Long id) {
        GuideExtend guide = guideExtendService.getById(id);
        if (guide == null) return Result.error("导游信息不存在");
        guide.setQualificationStatus((byte)1); // 1-已认证
        boolean success = guideExtendService.updateById(guide);
        return success ? Result.success(true) : Result.error("认证失败");
    }

    @PutMapping("/reject/{id}")
    public Result<Boolean> rejectGuide(@PathVariable Long id, @RequestParam String reason) {
        GuideExtend guide = guideExtendService.getById(id);
        if (guide == null) return Result.error("导游信息不存在");
        guide.setQualificationStatus((byte)2); // 2-认证失败
        guide.setRejectReason(reason);
        boolean success = guideExtendService.updateById(guide);
        return success ? Result.success(true) : Result.error("拒绝认证失败");
    }

    @GetMapping("/search")
    public Result<List<GuideExtend>> searchGuides(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Byte qualificationStatus,
            @RequestParam(required = false) String location) {
        List<GuideExtend> guides = guideExtendService.lambdaQuery()
                .like(keyword != null, GuideExtend::getRealName, keyword)
                .or()
                .like(keyword != null, GuideExtend::getPhone, keyword)
                .eq(qualificationStatus != null, GuideExtend::getQualificationStatus, qualificationStatus)
                .like(location != null, GuideExtend::getLocation, location)
                .list();
        return Result.success(guides);
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteGuide(@PathVariable Long id) {
        log.info("删除导游信息: {}", id);
        boolean success = guideExtendService.removeById(id);
        return success ? Result.success(true) : Result.error("删除导游信息失败");
    }
}