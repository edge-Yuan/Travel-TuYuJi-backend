package com.wanderlust.travel.travelportal.service;

import com.wanderlust.travel.travelportal.dto.ai.PlanRouteRequest;
import com.wanderlust.travel.travelportal.vo.ai.PlanRouteResponse;

public interface IAiService {
    PlanRouteResponse planRoute(PlanRouteRequest request);
}


