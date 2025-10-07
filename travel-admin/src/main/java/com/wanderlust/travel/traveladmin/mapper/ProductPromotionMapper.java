package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.entity.ProductPromotion;
import com.wanderlust.travel.traveladmin.vo.PromotionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品优惠活动Mapper接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Mapper
public interface ProductPromotionMapper extends BaseMapper<ProductPromotion> {

    /**
     * 分页查询优惠活动列表
     * 
     * @param page 分页参数
     * @param merchantId 商家ID
     * @param status 状态
     * @return 优惠活动列表
     */
    IPage<PromotionVO> selectPromotionPage(Page<PromotionVO> page, 
                                          @Param("merchantId") Long merchantId, 
                                          @Param("status") Integer status);

    /**
     * 根据ID查询优惠活动详情
     * 
     * @param promotionId 活动ID
     * @return 优惠活动详情
     */
    PromotionVO selectPromotionDetail(@Param("promotionId") Long promotionId);

    /**
     * 查询商家的所有优惠活动
     * 
     * @param merchantId 商家ID
     * @return 优惠活动列表
     */
    List<PromotionVO> selectPromotionsByMerchant(@Param("merchantId") Long merchantId);

    /**
     * 查询有效的优惠活动
     * 
     * @param merchantId 商家ID
     * @return 有效优惠活动列表
     */
    List<PromotionVO> selectActivePromotions(@Param("merchantId") Long merchantId);
}
