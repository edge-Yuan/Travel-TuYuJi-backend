package com.wanderlust.travel.travelportal.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourProductScanVO implements java.io.Serializable{
    private Long productId;
    private String productName;
    private BigDecimal price;
    private String imgUrls;
    private Integer soldCount;

}
