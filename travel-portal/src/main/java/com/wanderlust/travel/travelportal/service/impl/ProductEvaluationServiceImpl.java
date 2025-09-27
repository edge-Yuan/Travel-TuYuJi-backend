package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.dto.ProductEvaluationDTO;
import com.wanderlust.travel.travelportal.entity.SysUser;
import com.wanderlust.travel.travelportal.entity.TourEvaluation;
import com.wanderlust.travel.travelportal.mapper.TourEvaluationMapper;
import com.wanderlust.travel.travelportal.service.IProductEvaluationService;
import com.wanderlust.travel.travelportal.service.ISysUserService;
import com.wanderlust.travel.travelportal.vo.ProductEvaluationSummaryVO;
import com.wanderlust.travel.travelportal.vo.ProductEvaluationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 产品评价服务实现类
 */
@Service
@Slf4j
public class ProductEvaluationServiceImpl extends ServiceImpl<TourEvaluationMapper, TourEvaluation> implements IProductEvaluationService {
    
    @Autowired
    private TourEvaluationMapper tourEvaluationMapper;
    
    @Autowired
    private ISysUserService sysUserService;
    
    @Override
    @Transactional
    public boolean addProductEvaluation(ProductEvaluationDTO evaluationDTO) {
        try {
            TourEvaluation evaluation = new TourEvaluation();
            BeanUtils.copyProperties(evaluationDTO, evaluation);
            evaluation.setEvalType((byte) 1); // 1-产品评价
            evaluation.setTargetId(evaluationDTO.getProductId());
            evaluation.setEvalTime(LocalDateTime.now());
            evaluation.setCreateTime(LocalDateTime.now());
            
            return save(evaluation);
        } catch (Exception e) {
            log.error("添加产品评价失败", e);
            return false;
        }
    }
    
    @Override
    public List<ProductEvaluationVO> getProductEvaluations(Long productId, Integer page, Integer size) {
        try {
            Page<TourEvaluation> pageParam = new Page<>(page, size);
            LambdaQueryWrapper<TourEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TourEvaluation::getTargetId, productId)
                       .eq(TourEvaluation::getEvalType, (byte) 1)
                       .orderByDesc(TourEvaluation::getEvalTime);
            
            Page<TourEvaluation> evaluationPage = page(pageParam, queryWrapper);
            List<TourEvaluation> evaluations = evaluationPage.getRecords();
            
            // 获取用户信息
            List<Long> userIds = evaluations.stream()
                    .map(TourEvaluation::getUserId)
                    .distinct()
                    .collect(Collectors.toList());
            
            Map<Long, SysUser> userMap = sysUserService.listByIds(userIds)
                    .stream()
                    .collect(Collectors.toMap(SysUser::getUserId, user -> user));
            
            return evaluations.stream().map(evaluation -> {
                ProductEvaluationVO vo = new ProductEvaluationVO();
                BeanUtils.copyProperties(evaluation, vo);
                
                SysUser user = userMap.get(evaluation.getUserId());
                if (user != null) {
                    vo.setUserName(maskUserName(user.getRealName()));
                    vo.setUserAvatar("https://example.com/user-avatar.png"); // 默认头像
                    vo.setUserType("情侣/朋友"); // 默认用户类型
                }
                
                vo.setSource("来自WWW站"); // 默认来源
                vo.setUsefulCount(12); // 默认有用数
                
                return vo;
            }).collect(Collectors.toList());
            
        } catch (Exception e) {
            log.error("获取产品评价列表失败", e);
            return new ArrayList<>();
        }
    }
    
    @Override
    public ProductEvaluationSummaryVO getProductEvaluationSummary(Long productId) {
        try {
            LambdaQueryWrapper<TourEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TourEvaluation::getTargetId, productId)
                       .eq(TourEvaluation::getEvalType, (byte) 1);
            
            List<TourEvaluation> evaluations = list(queryWrapper);
            
            if (evaluations.isEmpty()) {
                return ProductEvaluationSummaryVO.builder()
                        .satisfactionRate(0.0)
                        .totalReviews(0)
                        .distributionItems(new ArrayList<>())
                        .scoreItems(new ArrayList<>())
                        .build();
            }
            
            // 计算整体满意度
            double avgScore = evaluations.stream()
                    .mapToInt(eval -> eval.getOverallScore().intValue())
                    .average()
                    .orElse(0.0);
            double satisfactionRate = (avgScore / 5.0) * 100;
            
            // 计算满意度分布
            Map<Integer, Long> scoreDistribution = evaluations.stream()
                    .collect(Collectors.groupingBy(
                            eval -> eval.getOverallScore().intValue(),
                            Collectors.counting()
                    ));
            
            List<ProductEvaluationSummaryVO.SatisfactionDistribution> distributionItems = new ArrayList<>();
            int totalCount = evaluations.size();
            
            // 很满意 (5分)
            long satisfiedCount = scoreDistribution.getOrDefault(5, 0L);
            distributionItems.add(ProductEvaluationSummaryVO.SatisfactionDistribution.builder()
                    .type("satisfied")
                    .label("很满意")
                    .count((int) satisfiedCount)
                    .percentage((double) satisfiedCount / totalCount * 100)
                    .build());
            
            // 还不错 (4分)
            long moderateCount = scoreDistribution.getOrDefault(4, 0L);
            distributionItems.add(ProductEvaluationSummaryVO.SatisfactionDistribution.builder()
                    .type("moderate")
                    .label("还不错")
                    .count((int) moderateCount)
                    .percentage((double) moderateCount / totalCount * 100)
                    .build());
            
            // 不满意 (1-3分)
            long dissatisfiedCount = scoreDistribution.getOrDefault(1, 0L) +
                                   scoreDistribution.getOrDefault(2, 0L) +
                                   scoreDistribution.getOrDefault(3, 0L);
            distributionItems.add(ProductEvaluationSummaryVO.SatisfactionDistribution.builder()
                    .type("dissatisfied")
                    .label("不满意")
                    .count((int) dissatisfiedCount)
                    .percentage((double) dissatisfiedCount / totalCount * 100)
                    .build());
            
            // 计算分项评分
            List<ProductEvaluationSummaryVO.ScoreItem> scoreItems = new ArrayList<>();
            
            // 导游服务评分
            double guideScore = evaluations.stream()
                    .filter(eval -> eval.getServiceScore() != null)
                    .mapToInt(eval -> eval.getServiceScore().intValue())
                    .average()
                    .orElse(0.0);
            scoreItems.add(ProductEvaluationSummaryVO.ScoreItem.builder()
                    .type("导游服务")
                    .score(guideScore)
                    .build());
            
            // 行程规划评分
            double itineraryScore = evaluations.stream()
                    .filter(eval -> eval.getEnvironmentScore() != null)
                    .mapToInt(eval -> eval.getEnvironmentScore().intValue())
                    .average()
                    .orElse(0.0);
            scoreItems.add(ProductEvaluationSummaryVO.ScoreItem.builder()
                    .type("行程规划")
                    .score(itineraryScore)
                    .build());
            
            // 住宿体验评分
            double accommodationScore = evaluations.stream()
                    .filter(eval -> eval.getCostEffScore() != null)
                    .mapToInt(eval -> eval.getCostEffScore().intValue())
                    .average()
                    .orElse(0.0);
            scoreItems.add(ProductEvaluationSummaryVO.ScoreItem.builder()
                    .type("住宿体验")
                    .score(accommodationScore)
                    .build());
            
            // 交通出行评分
            double transportationScore = avgScore; // 使用总体评分作为交通评分
            scoreItems.add(ProductEvaluationSummaryVO.ScoreItem.builder()
                    .type("交通出行")
                    .score(transportationScore)
                    .build());
            
            return ProductEvaluationSummaryVO.builder()
                    .satisfactionRate(satisfactionRate)
                    .totalReviews(totalCount)
                    .distributionItems(distributionItems)
                    .scoreItems(scoreItems)
                    .build();
            
        } catch (Exception e) {
            log.error("获取产品评价汇总失败", e);
            return ProductEvaluationSummaryVO.builder()
                    .satisfactionRate(0.0)
                    .totalReviews(0)
                    .distributionItems(new ArrayList<>())
                    .scoreItems(new ArrayList<>())
                    .build();
        }
    }
    
    @Override
    @Transactional
    public boolean replyEvaluation(Long evalId, String replyContent) {
        try {
            TourEvaluation evaluation = getById(evalId);
            if (evaluation == null) {
                return false;
            }
            
            evaluation.setReplyContent(replyContent);
            evaluation.setReplyTime(LocalDateTime.now());
            
            return updateById(evaluation);
        } catch (Exception e) {
            log.error("回复评价失败", e);
            return false;
        }
    }
    
    @Override
    public ProductEvaluationVO getUserProductEvaluation(Long userId, Long productId) {
        try {
            LambdaQueryWrapper<TourEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TourEvaluation::getUserId, userId)
                       .eq(TourEvaluation::getTargetId, productId)
                       .eq(TourEvaluation::getEvalType, (byte) 1);
            
            TourEvaluation evaluation = getOne(queryWrapper);
            if (evaluation == null) {
                return null;
            }
            
            ProductEvaluationVO vo = new ProductEvaluationVO();
            BeanUtils.copyProperties(evaluation, vo);
            
            SysUser user = sysUserService.getById(userId);
            if (user != null) {
                vo.setUserName(maskUserName(user.getRealName()));
                vo.setUserAvatar("https://example.com/user-avatar.png");
                vo.setUserType("情侣/朋友");
            }
            
            vo.setSource("来自WWW站");
            vo.setUsefulCount(12);
            
            return vo;
        } catch (Exception e) {
            log.error("获取用户产品评价失败", e);
            return null;
        }
    }
    
    /**
     * 脱敏用户名
     */
    private String maskUserName(String realName) {
        if (realName == null || realName.length() <= 1) {
            return "***";
        }
        if (realName.length() == 2) {
            return realName.charAt(0) + "***";
        }
        return realName.charAt(0) + "***" + realName.charAt(realName.length() - 1);
    }
}
