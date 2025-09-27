package com.wanderlust.travel.travelportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.travelportal.dto.ProductCostExplanationDTO;
import com.wanderlust.travel.travelportal.entity.ProductCostExplanation;
import com.wanderlust.travel.travelportal.vo.ProductCostExplanationVO;

/**
 * 产品费用说明服务接口
 */
public interface IProductCostExplanationService extends IService<ProductCostExplanation> {
    
    /**
     * 添加产品费用说明
     */
    boolean addProductCostExplanation(ProductCostExplanationDTO costExplanationDTO);
    
    /**
     * 获取产品费用说明
     */
    ProductCostExplanationVO getProductCostExplanation(Long productId);
    
    /**
     * 更新产品费用说明
     */
    boolean updateProductCostExplanation(Long costId, ProductCostExplanationDTO costExplanationDTO);
    
    /**
     * 删除产品费用说明
     */
    boolean deleteProductCostExplanation(Long costId);
}