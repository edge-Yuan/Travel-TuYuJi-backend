package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.dto.ai.PlanRouteRequest;
import com.wanderlust.travel.travelportal.service.IAiService;
import com.wanderlust.travel.travelportal.vo.ai.PlanRouteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel-portal/ai")
@Slf4j
public class AiController {

    @Autowired
    private IAiService aiService;

    @PostMapping("/planRoute")
    public Result<PlanRouteResponse> planRoute(@RequestBody PlanRouteRequest request) {
        log.info("AI 规划路线请求: {}", request);
        try {
            PlanRouteResponse resp = aiService.planRoute(request);
            return Result.success(resp);
        } catch (Exception e) {
            log.warn("AI 规划失败，将由前端回退: {}", e.getMessage());
            return Result.error("AI 规划失败: " + e.getMessage());
        }
    }
}


