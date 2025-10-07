package com.wanderlust.travel.traveladmin.dto;

import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * 产品搜索DTO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ProductSearchDTO {

    /**
     * 搜索关键词
     */
    private String keyword;

    /**
     * 产品类型
     */
    private Integer type;

    /**
     * 产品状态
     */
    private Integer status;

    /**
     * 最低价格
     */
    private BigDecimal minPrice;

    /**
     * 最高价格
     */
    private BigDecimal maxPrice;

    /**
     * 页码
     */
    @Min(value = 1, message = "页码必须大于0")
    private Integer page = 1;

    /**
     * 每页大小
     */
    @Min(value = 1, message = "每页大小必须大于0")
    @Max(value = 100, message = "每页大小不能超过100")
    private Integer size = 10;

    /**
     * 排序字段
     */
    private String sortBy = "createTime";

    /**
     * 排序方向
     */
    private String sortOrder = "desc";

    /**
     * 商家ID
     */
    private Long merchantId;
}
