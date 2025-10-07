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
public class BuyProductsDetailVO {
    private String productName;
    private BigDecimal price;
    private String description;
    private String productTags;
    private Integer stock;
    private Integer soldCount;
    private String imgUrls;
    private String serviceGuarantees;
    private Long merchantId;
    private String productSellingPoints;
    private String supplier;
    private String features;
    private String featuresImgs;
}
