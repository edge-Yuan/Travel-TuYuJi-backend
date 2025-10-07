package com.wanderlust.travel.traveladmin.service;

import com.wanderlust.travel.traveladmin.vo.ProductStatisticsVO;

import java.time.LocalDate;
import java.util.List;

/**
 * 产品统计数据服务接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
public interface ProductStatisticsService {

    /**
     * 获取产品统计数据
     * 
     * @param merchantId 商家ID
     * @return 统计数据
     */
    ProductStatisticsVO getProductStatistics(Long merchantId);

    /**
     * 获取产品类型统计
     * 
     * @param merchantId 商家ID
     * @return 类型统计数据
     */
    List<ProductStatisticsVO.ProductTypeStatVO> getProductTypeStatistics(Long merchantId);

    /**
     * 获取热销产品列表
     * 
     * @param merchantId 商家ID
     * @param limit 限制数量
     * @return 热销产品列表
     */
    List<ProductStatisticsVO.TopSellingProductVO> getTopSellingProducts(Long merchantId, Integer limit);

    /**
     * 获取销售趋势数据
     * 
     * @param merchantId 商家ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 销售趋势数据
     */
    List<ProductStatisticsVO.SalesTrendVO> getSalesTrend(Long merchantId, LocalDate startDate, LocalDate endDate);

    /**
     * 更新统计数据
     * 
     * @param merchantId 商家ID
     * @param statDate 统计日期
     */
    void updateStatistics(Long merchantId, LocalDate statDate);
}
