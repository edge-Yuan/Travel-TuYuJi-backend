package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourItinerarysScanVO {
    private String productName;
    private LocalDate itineraryDate;
    private Integer days;
    private Long guideId;
    private String guideName;
    private BigDecimal orderAmount;
    private Integer peopleNums;


}
