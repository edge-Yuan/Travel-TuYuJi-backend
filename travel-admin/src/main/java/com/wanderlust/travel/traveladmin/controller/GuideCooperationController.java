package com.wanderlust.travel.traveladmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.common.Result;
import com.wanderlust.travel.traveladmin.dto.GuideCooperationDTO;
import com.wanderlust.travel.traveladmin.dto.GuideSearchDTO;
import com.wanderlust.travel.traveladmin.service.GuideCooperationService;
import com.wanderlust.travel.traveladmin.vo.GuideCooperationVO;
import com.wanderlust.travel.traveladmin.vo.GuideVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * 导游合作管理控制器
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Slf4j
@RestController
@RequestMapping("/guideCooperation")
@RequiredArgsConstructor
@Validated
public class GuideCooperationController {

    private final GuideCooperationService guideCooperationService;

    /**
     * 搜索导游
     */
    @GetMapping("/searchGuides")
    public ResponseEntity<Map<String, Object>> searchGuides(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "9") Integer size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String experience,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "serviceScore") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder) {
        
        try {
            GuideSearchDTO searchDTO = new GuideSearchDTO();
            searchDTO.setPage(page);
            searchDTO.setSize(size);
            searchDTO.setKeyword(keyword);
            searchDTO.setLocation(location);
            searchDTO.setLanguage(language);
            searchDTO.setExperience(experience);
            searchDTO.setMinRating(minRating);
            searchDTO.setMaxPrice(maxPrice);
            searchDTO.setSortBy(sortBy);
            searchDTO.setSortOrder(sortOrder);
            
            Page<GuideVO> pageParam = new Page<>(page, size);
            IPage<GuideVO> guidePage = guideCooperationService.searchGuides(pageParam, searchDTO);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "搜索成功");
            result.put("data", Map.of(
                "total", guidePage.getTotal(),
                "page", guidePage.getCurrent(),
                "size", guidePage.getSize(),
                "guides", guidePage.getRecords()
            ));
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("搜索导游失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "搜索失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取导游详情
     */
    @GetMapping("/guideDetail/{guideId}")
    public ResponseEntity<Map<String, Object>> getGuideDetail(
            @PathVariable @NotNull Long guideId) {
        
        try {
            GuideVO guide = guideCooperationService.getGuideDetail(guideId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", guide);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取导游详情失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 发送合作邀请
     */
    @PostMapping("/sendCooperationRequest")
    public ResponseEntity<Map<String, Object>> sendCooperationRequest(
            @Valid @RequestBody GuideCooperationDTO cooperationDTO,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            boolean success = guideCooperationService.sendCooperationRequest(cooperationDTO, merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "合作邀请发送成功");
            result.put("data", Map.of(
                "success", success,
                "guideId", cooperationDTO.getGuideId(),
                "sendTime", java.time.LocalDateTime.now()
            ));
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("发送合作邀请失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "发送失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取合作邀请列表
     */
    @GetMapping("/cooperationList")
    public ResponseEntity<Map<String, Object>> getCooperationList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Byte status,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            Page<GuideCooperationVO> pageParam = new Page<>(page, size);
            IPage<GuideCooperationVO> cooperationPage = guideCooperationService.getCooperationList(pageParam, merchantId, status);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", Map.of(
                "total", cooperationPage.getTotal(),
                "page", cooperationPage.getCurrent(),
                "size", cooperationPage.getSize(),
                "cooperations", cooperationPage.getRecords()
            ));
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取合作邀请列表失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取合作邀请详情
     */
    @GetMapping("/cooperationDetail/{cooperationId}")
    public ResponseEntity<Map<String, Object>> getCooperationDetail(
            @PathVariable @NotNull Long cooperationId) {
        
        try {
            GuideCooperationVO cooperation = guideCooperationService.getCooperationDetail(cooperationId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", cooperation);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取合作邀请详情失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 取消合作邀请
     */
    @PutMapping("/cancelCooperation/{cooperationId}")
    public ResponseEntity<Map<String, Object>> cancelCooperation(
            @PathVariable @NotNull Long cooperationId,
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            boolean success = guideCooperationService.cancelCooperation(cooperationId, merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "取消成功");
            result.put("data", Map.of(
                "success", success,
                "cooperationId", cooperationId,
                "cancelTime", java.time.LocalDateTime.now()
            ));
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("取消合作邀请失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "取消失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }

    /**
     * 获取合作邀请统计
     */
    @GetMapping("/cooperationStats")
    public ResponseEntity<Map<String, Object>> getCooperationStats(
            @RequestHeader("X-Merchant-Id") Long merchantId) {
        
        try {
            GuideCooperationService.GuideCooperationStatsVO stats = guideCooperationService.getCooperationStats(merchantId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("code", 1);
            result.put("msg", "获取成功");
            result.put("data", stats);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("获取合作邀请统计失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("msg", "获取失败：" + e.getMessage());
            result.put("data", null);
            return ResponseEntity.badRequest().body(result);
        }
    }
}
