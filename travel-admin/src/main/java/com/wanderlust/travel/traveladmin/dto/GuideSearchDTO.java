package com.wanderlust.travel.traveladmin.dto;

import lombok.Data;

/**
 * 导游搜索DTO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class GuideSearchDTO {

    /**
     * 搜索关键词
     */
    private String keyword;

    /**
     * 服务地区
     */
    private String location;

    /**
     * 语言能力
     */
    private String language;

    /**
     * 经验等级：junior-新手(1-2年)，intermediate-中级(3-5年)，senior-高级(5年以上)
     */
    private String experience;

    /**
     * 最低评分
     */
    private Double minRating;

    /**
     * 最高价格
     */
    private Double maxPrice;

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer size = 9;

    /**
     * 排序字段
     */
    private String sortBy = "serviceScore";

    /**
     * 排序方向：asc-升序，desc-降序
     */
    private String sortOrder = "desc";
}
