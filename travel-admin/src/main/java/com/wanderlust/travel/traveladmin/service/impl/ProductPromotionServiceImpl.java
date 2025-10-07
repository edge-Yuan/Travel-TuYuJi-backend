package com.wanderlust.travel.traveladmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.traveladmin.dto.PromotionCreateDTO;
import com.wanderlust.travel.traveladmin.entity.ProductPromotion;
import com.wanderlust.travel.traveladmin.entity.PromotionProduct;
import com.wanderlust.travel.traveladmin.mapper.ProductPromotionMapper;
import com.wanderlust.travel.traveladmin.mapper.PromotionProductMapper;
import com.wanderlust.travel.traveladmin.service.ProductPromotionService;
import com.wanderlust.travel.traveladmin.vo.PromotionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 产品优惠活动服务实现类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductPromotionServiceImpl extends ServiceImpl<ProductPromotionMapper, ProductPromotion> 
        implements ProductPromotionService {

    private final ProductPromotionMapper productPromotionMapper;
    private final PromotionProductMapper promotionProductMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPromotion(PromotionCreateDTO createDTO, Long merchantId) {
        // 创建优惠活动
        ProductPromotion promotion = new ProductPromotion();
        BeanUtils.copyProperties(createDTO, promotion);
        promotion.setMerchantId(merchantId);
        promotion.setStatus(1);
        promotion.setCreateTime(LocalDateTime.now());
        promotion.setUpdateTime(LocalDateTime.now());
        
        productPromotionMapper.insert(promotion);
        
        // 批量插入活动产品关联
        if (createDTO.getProductIds() != null && !createDTO.getProductIds().isEmpty()) {
            promotionProductMapper.batchInsert(promotion.getPromotionId(), createDTO.getProductIds());
        }
        
        log.info("创建优惠活动成功，活动ID：{}，商家ID：{}", promotion.getPromotionId(), merchantId);
        return promotion.getPromotionId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePromotion(Long promotionId, PromotionCreateDTO createDTO, Long merchantId) {
        // 检查活动是否存在且属于该商家
        ProductPromotion existingPromotion = getById(promotionId);
        if (existingPromotion == null || !existingPromotion.getMerchantId().equals(merchantId)) {
            throw new RuntimeException("优惠活动不存在或无权限操作");
        }
        
        // 更新优惠活动
        ProductPromotion promotion = new ProductPromotion();
        BeanUtils.copyProperties(createDTO, promotion);
        promotion.setPromotionId(promotionId);
        promotion.setMerchantId(merchantId);
        promotion.setUpdateTime(LocalDateTime.now());
        
        boolean result = updateById(promotion);
        
        // 更新活动产品关联
        if (createDTO.getProductIds() != null) {
            // 删除原有关联
            promotionProductMapper.deleteByPromotionId(promotionId);
            // 插入新关联
            if (!createDTO.getProductIds().isEmpty()) {
                promotionProductMapper.batchInsert(promotionId, createDTO.getProductIds());
            }
        }
        
        log.info("更新优惠活动成功，活动ID：{}，商家ID：{}", promotionId, merchantId);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deletePromotion(Long promotionId, Long merchantId) {
        // 检查活动是否存在且属于该商家
        ProductPromotion existingPromotion = getById(promotionId);
        if (existingPromotion == null || !existingPromotion.getMerchantId().equals(merchantId)) {
            throw new RuntimeException("优惠活动不存在或无权限操作");
        }
        
        // 删除活动产品关联
        promotionProductMapper.deleteByPromotionId(promotionId);
        
        // 删除优惠活动
        boolean result = removeById(promotionId);
        
        log.info("删除优惠活动成功，活动ID：{}，商家ID：{}", promotionId, merchantId);
        return result;
    }

    @Override
    public IPage<PromotionVO> getPromotionPage(Page<PromotionVO> page, Long merchantId, Integer status) {
        return productPromotionMapper.selectPromotionPage(page, merchantId, status);
    }

    @Override
    public PromotionVO getPromotionDetail(Long promotionId) {
        return productPromotionMapper.selectPromotionDetail(promotionId);
    }

    @Override
    public List<PromotionVO> getPromotionsByMerchant(Long merchantId) {
        return productPromotionMapper.selectPromotionsByMerchant(merchantId);
    }

    @Override
    public List<PromotionVO> getActivePromotions(Long merchantId) {
        return productPromotionMapper.selectActivePromotions(merchantId);
    }

    @Override
    public boolean updatePromotionStatus(Long promotionId, Integer status, Long merchantId) {
        // 检查活动是否存在且属于该商家
        ProductPromotion existingPromotion = getById(promotionId);
        if (existingPromotion == null || !existingPromotion.getMerchantId().equals(merchantId)) {
            throw new RuntimeException("优惠活动不存在或无权限操作");
        }
        
        ProductPromotion promotion = new ProductPromotion();
        promotion.setPromotionId(promotionId);
        promotion.setStatus(status);
        promotion.setUpdateTime(LocalDateTime.now());
        
        boolean result = updateById(promotion);
        log.info("更新优惠活动状态成功，活动ID：{}，状态：{}，商家ID：{}", promotionId, status, merchantId);
        return result;
    }
}
