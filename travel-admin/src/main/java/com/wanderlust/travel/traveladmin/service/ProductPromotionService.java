package com.wanderlust.travel.traveladmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.traveladmin.dto.PromotionCreateDTO;
import com.wanderlust.travel.traveladmin.entity.ProductPromotion;
import com.wanderlust.travel.traveladmin.vo.PromotionVO;

import java.util.List;

/**
 * 产品优惠活动服务接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
public interface ProductPromotionService extends IService<ProductPromotion> {

    /**
     * 创建优惠活动
     * 
     * @param createDTO 创建DTO
     * @param merchantId 商家ID
     * @return 活动ID
     */
    Long createPromotion(PromotionCreateDTO createDTO, Long merchantId);

    /**
     * 更新优惠活动
     * 
     * @param promotionId 活动ID
     * @param createDTO 更新DTO
     * @param merchantId 商家ID
     * @return 是否成功
     */
    boolean updatePromotion(Long promotionId, PromotionCreateDTO createDTO, Long merchantId);

    /**
     * 删除优惠活动
     * 
     * @param promotionId 活动ID
     * @param merchantId 商家ID
     * @return 是否成功
     */
    boolean deletePromotion(Long promotionId, Long merchantId);

    /**
     * 分页查询优惠活动列表
     * 
     * @param page 分页参数
     * @param merchantId 商家ID
     * @param status 状态
     * @return 优惠活动列表
     */
    IPage<PromotionVO> getPromotionPage(Page<PromotionVO> page, Long merchantId, Integer status);

    /**
     * 获取优惠活动详情
     * 
     * @param promotionId 活动ID
     * @return 优惠活动详情
     */
    PromotionVO getPromotionDetail(Long promotionId);

    /**
     * 获取商家的所有优惠活动
     * 
     * @param merchantId 商家ID
     * @return 优惠活动列表
     */
    List<PromotionVO> getPromotionsByMerchant(Long merchantId);

    /**
     * 获取有效的优惠活动
     * 
     * @param merchantId 商家ID
     * @return 有效优惠活动列表
     */
    List<PromotionVO> getActivePromotions(Long merchantId);

    /**
     * 启用/禁用优惠活动
     * 
     * @param promotionId 活动ID
     * @param status 状态
     * @param merchantId 商家ID
     * @return 是否成功
     */
    boolean updatePromotionStatus(Long promotionId, Integer status, Long merchantId);
}
