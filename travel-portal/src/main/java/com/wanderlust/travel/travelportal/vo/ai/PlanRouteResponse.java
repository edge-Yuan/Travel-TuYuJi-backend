package com.wanderlust.travel.travelportal.vo.ai;

import lombok.Data;

import java.util.List;

@Data
public class PlanRouteResponse {
    private List<DayPlan> days;

    @Data
    public static class DayPlan {
        private List<String> activities; // 顺序：上午、中午、下午、晚上
    }
}


