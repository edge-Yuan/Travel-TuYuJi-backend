package com.wanderlust.travel.traveladmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基础测试控制器
 * 
 * @author wanderlust
 * @since 2025-01-15
 */
@RestController
@RequestMapping("/basic-test")
public class BasicTestController {

    @GetMapping("/test")
    public String test() {
        return "基础测试成功";
    }
}

