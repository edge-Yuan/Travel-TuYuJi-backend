package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.traveladmin.entity.ProductStatistics;
import com.wanderlust.travel.traveladmin.vo.ProductStatisticsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * 产品统计数据Mapper接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Mapper
public interface ProductStatisticsMapper extends BaseMapper<ProductStatistics> {

    /**
     * 获取产品统计数据
     * 
     * @param merchantId 商家ID
     * @return 统计数据
     */
    ProductStatisticsVO selectProductStatistics(@Param("merchantId") Long merchantId);

    /**
     * 获取产品类型统计
     * 
     * @param merchantId 商家ID
     * @return 类型统计数据
     */
    List<ProductStatisticsVO.ProductTypeStatVO> selectProductTypeStatistics(@Param("merchantId") Long merchantId);

    /**
     * 获取热销产品列表
     * 
     * @param merchantId 商家ID
     * @param limit 限制数量
     * @return 热销产品列表
     */
    List<ProductStatisticsVO.TopSellingProductVO> selectTopSellingProducts(@Param("merchantId") Long merchantId, 
                                                                           @Param("limit") Integer limit);

    /**
     * 获取销售趋势数据
     * 
     * @param merchantId 商家ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 销售趋势数据
     */
    List<ProductStatisticsVO.SalesTrendVO> selectSalesTrend(@Param("merchantId") Long merchantId,
                                                            @Param("startDate") LocalDate startDate,
                                                            @Param("endDate") LocalDate endDate);

    /**
     * 更新或插入统计数据
     * 
     * @param statistics 统计数据
     * @return 影响行数
     */
    int upsertStatistics(ProductStatistics statistics);
}
