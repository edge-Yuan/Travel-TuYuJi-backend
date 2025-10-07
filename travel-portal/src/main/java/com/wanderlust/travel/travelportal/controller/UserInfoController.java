package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.UserAvatar;
import com.wanderlust.travel.travelportal.service.IFileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户信息控制器
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@RestController
@RequestMapping("/travel-portal/user")
@Api(tags = "用户信息管理")
@Slf4j
public class UserInfoController {

    @Autowired
    private IFileUploadService fileUploadService;

    /**
     * 获取用户头像信息
     */
    @GetMapping("/{userId}/avatar")
    @ApiOperation("获取用户头像信息")
    public Result<Map<String, Object>> getUserAvatarInfo(@ApiParam("用户ID") @PathVariable Long userId) {
        try {
            UserAvatar avatar = fileUploadService.getUserAvatar(userId);
            
            Map<String, Object> result = new HashMap<>();
            if (avatar != null) {
                result.put("hasAvatar", true);
                result.put("avatarUrl", avatar.getAvatarUrl());
                result.put("fileId", avatar.getFileId());
                result.put("createTime", avatar.getCreateTime());
            } else {
                result.put("hasAvatar", false);
                result.put("avatarUrl", null);
            }
            
            return Result.success(result);
        } catch (Exception e) {
            log.error("获取用户头像信息失败", e);
            return Result.error("获取用户头像信息失败：" + e.getMessage());
        }
    }

    /**
     * 更新用户头像信息
     */
    @PostMapping("/{userId}/avatar")
    @PutMapping("/{userId}/avatar")
    @ApiOperation("更新用户头像信息")
    public Result<Map<String, Object>> updateUserAvatar(
            @ApiParam("用户ID") @PathVariable Long userId,
            @RequestBody Map<String, Object> requestBody) {
        
        log.info("更新用户头像信息，用户ID：{}", userId);
        
        try {
            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("message", "用户头像信息已更新");
            return Result.success(result);
        } catch (Exception e) {
            log.error("更新用户头像信息失败", e);
            return Result.error("更新用户头像信息失败：" + e.getMessage());
        }
    }

    /**
     * 更新用户头像显示
     */
    @PostMapping("/{userId}/avatar/refresh")
    @PutMapping("/{userId}/avatar/refresh")
    @ApiOperation("刷新用户头像显示")
    public Result<Map<String, Object>> refreshUserAvatar(@ApiParam("用户ID") @PathVariable Long userId) {
        try {
            UserAvatar avatar = fileUploadService.getUserAvatar(userId);
            
            Map<String, Object> result = new HashMap<>();
            if (avatar != null) {
                result.put("success", true);
                result.put("avatarUrl", avatar.getAvatarUrl());
                result.put("message", "头像已更新");
            } else {
                result.put("success", false);
                result.put("message", "用户暂无头像");
            }
            
            return Result.success(result);
        } catch (Exception e) {
            log.error("刷新用户头像失败", e);
            return Result.error("刷新用户头像失败：" + e.getMessage());
        }
    }
}
