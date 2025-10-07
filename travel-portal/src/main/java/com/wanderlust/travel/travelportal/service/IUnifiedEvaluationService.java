package com.wanderlust.travel.travelportal.service;

import com.wanderlust.travel.travelportal.dto.UnifiedEvaluationDTO;
import com.wanderlust.travel.travelportal.vo.UnifiedEvaluationVO;

import java.util.List;

/**
 * 统一评价服务接口
 * 整合套餐评价和订单管理评价的业务逻辑
 */
public interface IUnifiedEvaluationService {
    
    /**
     * 添加评价
     */
    boolean addEvaluation(UnifiedEvaluationDTO evaluationDTO);
    
    /**
     * 获取产品评价列表
     */
    List<UnifiedEvaluationVO> getProductEvaluations(Long productId, Integer page, Integer size);
    
    /**
     * 获取产品评价列表（带当前用户ID，用于判断是否显示完整用户名）
     */
    List<UnifiedEvaluationVO> getProductEvaluations(Long productId, Integer page, Integer size, Long currentUserId);
    
    /**
     * 获取订单评价列表
     */
    List<UnifiedEvaluationVO> getOrderEvaluations(Long orderId);
    
    /**
     * 获取用户评价列表
     */
    List<UnifiedEvaluationVO> getUserEvaluations(Long userId, Integer page, Integer size);
    
    /**
     * 获取用户对产品的评价
     */
    UnifiedEvaluationVO getUserProductEvaluation(Long userId, Long productId);
    
    /**
     * 获取产品评价汇总信息
     */
    Object getProductEvaluationSummary(Long productId);
    
    /**
     * 回复评价
     */
    boolean replyEvaluation(Long evalId, String replyContent);
    
    /**
     * 编辑评价
     */
    boolean updateEvaluation(Long evalId, UnifiedEvaluationDTO evaluationDTO);
    
    /**
     * 删除评价
     */
    boolean deleteEvaluation(Long evalId);
    
    /**
     * 获取所有评价（管理员用）
     */
    List<UnifiedEvaluationVO> getAllEvaluations(Integer page, Integer size, Byte evalType);
}
