package com.wanderlust.travel.traveladmin.controller;

import com.wanderlust.travel.traveladmin.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 简单订单控制器（用于测试）
 * 
 * @author wanderlust
 * @since 2025-01-15
 */
@Slf4j
@RestController
@RequestMapping("/simple-order")
public class SimpleOrderController {

    /**
     * 测试方法
     */
    @GetMapping("/test")
    public Result<String> test() {
        log.info("简单订单控制器测试方法被调用");
        return Result.success("简单测试成功");
    }

    /**
     * 获取简单统计
     */
    @GetMapping("/statistics")
    public Result<Object> getSimpleStatistics() {
        try {
            log.info("获取简单统计信息");
            // 返回模拟数据
            java.util.Map<String, Object> stats = new java.util.HashMap<>();
            stats.put("pending", 5);
            stats.put("confirmed", 10);
            stats.put("completed", 15);
            stats.put("refunding", 2);
            return Result.success(stats);
        } catch (Exception e) {
            log.error("获取简单统计信息失败", e);
            e.printStackTrace();
            return Result.error("获取简单统计信息失败: " + e.getMessage());
        }
    }
}
