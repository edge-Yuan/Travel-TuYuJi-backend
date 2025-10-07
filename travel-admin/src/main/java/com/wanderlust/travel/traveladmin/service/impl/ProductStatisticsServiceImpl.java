package com.wanderlust.travel.traveladmin.service.impl;

import com.wanderlust.travel.traveladmin.entity.ProductStatistics;
import com.wanderlust.travel.traveladmin.mapper.ProductStatisticsMapper;
import com.wanderlust.travel.traveladmin.service.ProductStatisticsService;
import com.wanderlust.travel.traveladmin.vo.ProductStatisticsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 产品统计数据服务实现类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductStatisticsServiceImpl implements ProductStatisticsService {

    private final ProductStatisticsMapper productStatisticsMapper;

    @Override
    public ProductStatisticsVO getProductStatistics(Long merchantId) {
        ProductStatisticsVO statistics = productStatisticsMapper.selectProductStatistics(merchantId);
        
        // 获取热销产品列表
        List<ProductStatisticsVO.TopSellingProductVO> topSellingProducts = 
                productStatisticsMapper.selectTopSellingProducts(merchantId, 10);
        statistics.setTopSellingProducts(topSellingProducts);
        
        // 获取最近30天的销售趋势
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);
        List<ProductStatisticsVO.SalesTrendVO> salesTrend = 
                productStatisticsMapper.selectSalesTrend(merchantId, startDate, endDate);
        statistics.setSalesTrend(salesTrend);
        
        return statistics;
    }

    @Override
    public List<ProductStatisticsVO.ProductTypeStatVO> getProductTypeStatistics(Long merchantId) {
        return productStatisticsMapper.selectProductTypeStatistics(merchantId);
    }

    @Override
    public List<ProductStatisticsVO.TopSellingProductVO> getTopSellingProducts(Long merchantId, Integer limit) {
        return productStatisticsMapper.selectTopSellingProducts(merchantId, limit);
    }

    @Override
    public List<ProductStatisticsVO.SalesTrendVO> getSalesTrend(Long merchantId, LocalDate startDate, LocalDate endDate) {
        return productStatisticsMapper.selectSalesTrend(merchantId, startDate, endDate);
    }

    @Override
    public void updateStatistics(Long merchantId, LocalDate statDate) {
        // 获取统计数据
        ProductStatisticsVO statistics = productStatisticsMapper.selectProductStatistics(merchantId);
        
        // 创建统计数据实体
        ProductStatistics statEntity = new ProductStatistics();
        statEntity.setMerchantId(merchantId);
        statEntity.setStatDate(statDate);
        statEntity.setTotalProducts(statistics.getTotalProducts());
        statEntity.setActiveProducts(statistics.getActiveProducts());
        statEntity.setInactiveProducts(statistics.getInactiveProducts());
        statEntity.setTotalSales(statistics.getTotalSales());
        statEntity.setTotalRevenue(statistics.getTotalRevenue());
        statEntity.setAveragePrice(statistics.getAveragePrice());
        statEntity.setCreateTime(LocalDateTime.now());
        statEntity.setUpdateTime(LocalDateTime.now());
        
        // 更新或插入统计数据
        productStatisticsMapper.upsertStatistics(statEntity);
        
        log.info("更新统计数据成功，商家ID：{}，统计日期：{}", merchantId, statDate);
    }
}
