package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.traveladmin.entity.PromotionProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠活动产品关联Mapper接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Mapper
public interface PromotionProductMapper extends BaseMapper<PromotionProduct> {

    /**
     * 批量插入活动产品关联
     * 
     * @param promotionId 活动ID
     * @param productIds 产品ID列表
     * @return 插入数量
     */
    int batchInsert(@Param("promotionId") Long promotionId, @Param("productIds") List<Long> productIds);

    /**
     * 根据活动ID删除关联
     * 
     * @param promotionId 活动ID
     * @return 删除数量
     */
    int deleteByPromotionId(@Param("promotionId") Long promotionId);

    /**
     * 根据活动ID查询关联的产品ID列表
     * 
     * @param promotionId 活动ID
     * @return 产品ID列表
     */
    List<Long> selectProductIdsByPromotionId(@Param("promotionId") Long promotionId);

    /**
     * 根据产品ID查询关联的活动ID列表
     * 
     * @param productId 产品ID
     * @return 活动ID列表
     */
    List<Long> selectPromotionIdsByProductId(@Param("productId") Long productId);
}
