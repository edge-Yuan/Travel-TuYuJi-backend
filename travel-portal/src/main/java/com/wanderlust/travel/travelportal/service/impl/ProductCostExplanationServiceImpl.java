package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.dto.ProductCostExplanationDTO;
import com.wanderlust.travel.travelportal.entity.ProductCostExplanation;
import com.wanderlust.travel.travelportal.mapper.ProductCostExplanationMapper;
import com.wanderlust.travel.travelportal.service.IProductCostExplanationService;
import com.wanderlust.travel.travelportal.vo.ProductCostExplanationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 产品费用说明服务实现类
 */
@Service
@Slf4j
public class ProductCostExplanationServiceImpl extends ServiceImpl<ProductCostExplanationMapper, ProductCostExplanation> implements IProductCostExplanationService {
    
    @Autowired
    private ProductCostExplanationMapper productCostExplanationMapper;
    
    @Override
    @Transactional
    public boolean addProductCostExplanation(ProductCostExplanationDTO costExplanationDTO) {
        try {
            ProductCostExplanation costExplanation = new ProductCostExplanation();
            BeanUtils.copyProperties(costExplanationDTO, costExplanation);
            
            return save(costExplanation);
        } catch (Exception e) {
            log.error("添加产品费用说明失败", e);
            return false;
        }
    }
    
    @Override
    public ProductCostExplanationVO getProductCostExplanation(Long productId) {
        try {
            LambdaQueryWrapper<ProductCostExplanation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProductCostExplanation::getProductId, productId);
            
            ProductCostExplanation costExplanation = getOne(queryWrapper);
            if (costExplanation == null) {
                return null;
            }
            
            ProductCostExplanationVO vo = new ProductCostExplanationVO();
            BeanUtils.copyProperties(costExplanation, vo);
            return vo;
            
        } catch (Exception e) {
            log.error("获取产品费用说明失败", e);
            return null;
        }
    }
    
    @Override
    @Transactional
    public boolean updateProductCostExplanation(Long costId, ProductCostExplanationDTO costExplanationDTO) {
        try {
            ProductCostExplanation costExplanation = getById(costId);
            if (costExplanation == null) {
                return false;
            }
            
            BeanUtils.copyProperties(costExplanationDTO, costExplanation);
            costExplanation.setCostId(costId);
            
            return updateById(costExplanation);
        } catch (Exception e) {
            log.error("更新产品费用说明失败", e);
            return false;
        }
    }
    
    @Override
    @Transactional
    public boolean deleteProductCostExplanation(Long costId) {
        try {
            return removeById(costId);
        } catch (Exception e) {
            log.error("删除产品费用说明失败", e);
            return false;
        }
    }
}