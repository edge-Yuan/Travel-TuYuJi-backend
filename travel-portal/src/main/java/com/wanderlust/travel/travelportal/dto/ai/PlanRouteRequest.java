package com.wanderlust.travel.travelportal.dto.ai;

import lombok.Data;

import java.util.List;

@Data
public class PlanRouteRequest {
    private String destination;
    private Integer days;
    private String budget;
    private List<String> interests;
    private String accommodation;
}


