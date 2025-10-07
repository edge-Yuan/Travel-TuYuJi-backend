package com.wanderlust.travel.traveladmin.controller;

import com.wanderlust.travel.traveladmin.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试订单控制器
 * 
 * @author wanderlust
 * @since 2025-01-15
 */
@RestController
@RequestMapping("/test-order")
public class TestOrderController {

    @GetMapping("/test")
    public Result<String> test() {
        return Result.success("测试成功");
    }
}

