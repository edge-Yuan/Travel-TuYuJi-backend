package com.wanderlust.travel.travelportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.travelportal.dto.ProductEvaluationDTO;
import com.wanderlust.travel.travelportal.entity.TourEvaluation;
import com.wanderlust.travel.travelportal.vo.ProductEvaluationSummaryVO;
import com.wanderlust.travel.travelportal.vo.ProductEvaluationVO;

import java.util.List;

/**
 * 产品评价服务接口
 */
public interface IProductEvaluationService extends IService<TourEvaluation> {
    
    /**
     * 添加产品评价
     */
    boolean addProductEvaluation(ProductEvaluationDTO evaluationDTO);
    
    /**
     * 获取产品评价列表
     */
    List<ProductEvaluationVO> getProductEvaluations(Long productId, Integer page, Integer size);
    
    /**
     * 获取产品评价汇总信息
     */
    ProductEvaluationSummaryVO getProductEvaluationSummary(Long productId);
    
    /**
     * 回复评价
     */
    boolean replyEvaluation(Long evalId, String replyContent);
    
    /**
     * 获取用户对产品的评价
     */
    ProductEvaluationVO getUserProductEvaluation(Long userId, Long productId);
}
