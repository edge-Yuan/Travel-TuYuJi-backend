package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.entity.UserAvatar;
import com.wanderlust.travel.travelportal.mapper.UserAvatarMapper;
import com.wanderlust.travel.travelportal.service.impl.UnifiedEvaluationServiceImpl;
import com.wanderlust.travel.travelportal.vo.UnifiedEvaluationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片测试控制器
 * 用于测试头像和评价图片的处理
 */
@Slf4j
@RestController
@RequestMapping("/travel-portal/test")
public class ImageTestController {

    @Autowired
    private UnifiedEvaluationServiceImpl unifiedEvaluationService;

    @Autowired
    private UserAvatarMapper userAvatarMapper;

    /**
     * 测试用户头像获取
     */
    @GetMapping("/avatar/{userId}")
    public Map<String, Object> testUserAvatar(@PathVariable Long userId) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 获取用户头像信息
            List<UserAvatar> avatars = userAvatarMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<UserAvatar>()
                    .eq(UserAvatar::getUserId, userId)
                    .eq(UserAvatar::getIsCurrent, true)
                    .orderByDesc(UserAvatar::getCreateTime)
            );
            
            result.put("success", true);
            result.put("userId", userId);
            result.put("avatarCount", avatars.size());
            
            if (!avatars.isEmpty()) {
                UserAvatar avatar = avatars.get(0);
                result.put("avatarUrl", avatar.getAvatarUrl());
                result.put("isCurrent", avatar.getIsCurrent());
                result.put("createTime", avatar.getCreateTime());
            } else {
                result.put("message", "用户没有设置头像");
                result.put("defaultAvatar", "/static/images/default-avatar.png");
            }
            
        } catch (Exception e) {
            log.error("测试用户头像失败", e);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }

    /**
     * 测试评价图片处理
     */
    @GetMapping("/evaluation/{productId}")
    public Map<String, Object> testEvaluationImages(@PathVariable Long productId) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 获取产品评价
            List<UnifiedEvaluationVO> evaluations = unifiedEvaluationService.getProductEvaluations(productId, 1, 10);
            
            result.put("success", true);
            result.put("productId", productId);
            result.put("evaluationCount", evaluations.size());
            
            for (int i = 0; i < evaluations.size(); i++) {
                UnifiedEvaluationVO eval = evaluations.get(i);
                Map<String, Object> evalInfo = new HashMap<>();
                evalInfo.put("evalId", eval.getEvalId());
                evalInfo.put("userId", eval.getUserId());
                evalInfo.put("userName", eval.getUserName());
                evalInfo.put("userAvatar", eval.getUserAvatar());
                evalInfo.put("imgUrls", eval.getImgUrls());
                evalInfo.put("content", eval.getContent());
                
                result.put("evaluation_" + i, evalInfo);
            }
            
        } catch (Exception e) {
            log.error("测试评价图片失败", e);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }

    /**
     * 测试所有用户头像
     */
    @GetMapping("/avatars/all")
    public Map<String, Object> testAllAvatars() {
        Map<String, Object> result = new HashMap<>();
        
        try {
            List<UserAvatar> allAvatars = userAvatarMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<UserAvatar>()
                    .eq(UserAvatar::getIsCurrent, true)
                    .orderByDesc(UserAvatar::getCreateTime)
            );
            
            result.put("success", true);
            result.put("totalCount", allAvatars.size());
            
            for (int i = 0; i < allAvatars.size(); i++) {
                UserAvatar avatar = allAvatars.get(i);
                Map<String, Object> avatarInfo = new HashMap<>();
                avatarInfo.put("userId", avatar.getUserId());
                avatarInfo.put("avatarUrl", avatar.getAvatarUrl());
                avatarInfo.put("createTime", avatar.getCreateTime());
                
                result.put("avatar_" + i, avatarInfo);
            }
            
        } catch (Exception e) {
            log.error("测试所有头像失败", e);
            result.put("success", false);
            result.put("error", e.getMessage());
        }
        
        return result;
    }
}
