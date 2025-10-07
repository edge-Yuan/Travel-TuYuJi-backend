package com.wanderlust.travel.traveladmin.controller;

import com.wanderlust.travel.traveladmin.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 健康检查
     */
    @GetMapping("/health")
    public Result<Map<String, Object>> health() {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "UP");
        data.put("timestamp", LocalDateTime.now());
        data.put("message", "旅游管理系统后台管理模块运行正常");
        
        return Result.success(data);
    }

    /**
     * 系统信息
     */
    @GetMapping("/info")
    public Result<Map<String, Object>> info() {
        Map<String, Object> data = new HashMap<>();
        data.put("application", "travel-admin");
        data.put("version", "1.0.0");
        data.put("description", "旅游产品管理系统后台管理模块");
        data.put("startupTime", LocalDateTime.now());
        
        return Result.success(data);
    }
}
