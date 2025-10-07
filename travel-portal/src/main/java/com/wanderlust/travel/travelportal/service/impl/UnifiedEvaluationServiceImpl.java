package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.dto.UnifiedEvaluationDTO;
import com.wanderlust.travel.travelportal.entity.SysUser;
import com.wanderlust.travel.travelportal.entity.TourEvaluation;
import com.wanderlust.travel.travelportal.entity.UserAvatar;
import com.wanderlust.travel.travelportal.mapper.TourEvaluationMapper;
import com.wanderlust.travel.travelportal.mapper.UserAvatarMapper;
import com.wanderlust.travel.travelportal.service.IUnifiedEvaluationService;
import com.wanderlust.travel.travelportal.service.ISysUserService;
import com.wanderlust.travel.travelportal.vo.UnifiedEvaluationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 统一评价服务实现类
 */
@Service
@Slf4j
public class UnifiedEvaluationServiceImpl extends ServiceImpl<TourEvaluationMapper, TourEvaluation> implements IUnifiedEvaluationService {
    
    @Autowired
    private TourEvaluationMapper tourEvaluationMapper;
    
    @Autowired
    private ISysUserService sysUserService;
    
    @Autowired
    private UserAvatarMapper userAvatarMapper;
    
    @Override
    @Transactional
    public boolean addEvaluation(UnifiedEvaluationDTO evaluationDTO) {
        try {
            log.info("=== 开始处理评价提交 ===");
            log.info("订单ID: {}", evaluationDTO.getOrderId());
            log.info("产品ID: {}", evaluationDTO.getProductId());
            log.info("用户ID: {}", evaluationDTO.getUserId());
            log.info("原始图片URLs: '{}'", evaluationDTO.getImgUrls());
            log.info("图片URLs是否为空: {}", evaluationDTO.getImgUrls() == null || evaluationDTO.getImgUrls().trim().isEmpty());
            
            TourEvaluation evaluation = new TourEvaluation();
            BeanUtils.copyProperties(evaluationDTO, evaluation);
            
            // 处理图片URL
            String processedImgUrls = processImageUrls(evaluationDTO.getImgUrls());
            evaluation.setImgUrls(processedImgUrls);
            log.info("处理后的图片URLs: '{}'", processedImgUrls);
            
            // 设置评价类型和目标ID
            evaluation.setEvalType(evaluationDTO.getEvalType() != null ? evaluationDTO.getEvalType() : (byte) 1);
            evaluation.setTargetId(evaluationDTO.getTargetId() != null ? evaluationDTO.getTargetId() : evaluationDTO.getProductId());
            evaluation.setEvalTime(LocalDateTime.now());
            evaluation.setCreateTime(LocalDateTime.now());
            
            log.info("评价实体创建完成，图片URLs: '{}'", evaluation.getImgUrls());
            
            boolean result = save(evaluation);
            
            if (result) {
                log.info("评价保存成功，评价ID: {}, 图片URLs: '{}'", evaluation.getEvalId(), evaluation.getImgUrls());
            } else {
                log.error("评价保存失败");
            }
            
            return result;
        } catch (Exception e) {
            log.error("添加评价失败", e);
            return false;
        }
    }
    
    @Override
    public List<UnifiedEvaluationVO> getProductEvaluations(Long productId, Integer page, Integer size) {
        try {
            Page<TourEvaluation> pageParam = new Page<>(page, size);
            LambdaQueryWrapper<TourEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TourEvaluation::getTargetId, productId)
                       .eq(TourEvaluation::getEvalType, (byte) 1)
                       .orderByDesc(TourEvaluation::getEvalTime);
            
            Page<TourEvaluation> evaluationPage = page(pageParam, queryWrapper);
            List<TourEvaluation> evaluations = evaluationPage.getRecords();
            
            return convertToUnifiedVO(evaluations);
        } catch (Exception e) {
            log.error("获取产品评价列表失败", e);
            return new ArrayList<>();
        }
    }
    
    @Override
    public List<UnifiedEvaluationVO> getProductEvaluations(Long productId, Integer page, Integer size, Long currentUserId) {
        try {
            Page<TourEvaluation> pageParam = new Page<>(page, size);
            LambdaQueryWrapper<TourEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TourEvaluation::getTargetId, productId)
                       .eq(TourEvaluation::getEvalType, (byte) 1)
                       .orderByDesc(TourEvaluation::getEvalTime);
            
            Page<TourEvaluation> evaluationPage = page(pageParam, queryWrapper);
            List<TourEvaluation> evaluations = evaluationPage.getRecords();
            
            // 使用带当前用户ID的转换方法，支持显示完整用户名
            return convertToUnifiedVO(evaluations, currentUserId);
        } catch (Exception e) {
            log.error("获取产品评价列表失败", e);
            return new ArrayList<>();
        }
    }
    
    @Override
    public List<UnifiedEvaluationVO> getOrderEvaluations(Long orderId) {
        try {
            LambdaQueryWrapper<TourEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TourEvaluation::getOrderId, orderId)
                       .orderByDesc(TourEvaluation::getEvalTime);
            
            List<TourEvaluation> evaluations = list(queryWrapper);
            return convertToUnifiedVO(evaluations);
        } catch (Exception e) {
            log.error("获取订单评价列表失败", e);
            return new ArrayList<>();
        }
    }
    
    @Override
    public List<UnifiedEvaluationVO> getUserEvaluations(Long userId, Integer page, Integer size) {
        try {
            Page<TourEvaluation> pageParam = new Page<>(page, size);
            LambdaQueryWrapper<TourEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TourEvaluation::getUserId, userId)
                       .orderByDesc(TourEvaluation::getEvalTime);
            
            Page<TourEvaluation> evaluationPage = page(pageParam, queryWrapper);
            List<TourEvaluation> evaluations = evaluationPage.getRecords();
            
            // 当用户查看自己的评价时，显示完整用户名
            return convertToUnifiedVO(evaluations, userId);
        } catch (Exception e) {
            log.error("获取用户评价列表失败", e);
            return new ArrayList<>();
        }
    }
    
    @Override
    public UnifiedEvaluationVO getUserProductEvaluation(Long userId, Long productId) {
        try {
            LambdaQueryWrapper<TourEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TourEvaluation::getUserId, userId)
                       .eq(TourEvaluation::getTargetId, productId)
                       .eq(TourEvaluation::getEvalType, (byte) 1);
            
            TourEvaluation evaluation = getOne(queryWrapper);
            if (evaluation == null) {
                return null;
            }
            
            // 当用户查看自己的产品评价时，显示完整用户名
            List<UnifiedEvaluationVO> voList = convertToUnifiedVO(List.of(evaluation), userId);
            return voList.isEmpty() ? null : voList.get(0);
        } catch (Exception e) {
            log.error("获取用户产品评价失败", e);
            return null;
        }
    }
    
    @Override
    public Object getProductEvaluationSummary(Long productId) {
        try {
            LambdaQueryWrapper<TourEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TourEvaluation::getTargetId, productId)
                       .eq(TourEvaluation::getEvalType, (byte) 1);
            
            List<TourEvaluation> evaluations = list(queryWrapper);
            
            if (evaluations.isEmpty()) {
                return Map.of(
                    "satisfactionRate", 0.0,
                    "totalReviews", 0,
                    "distributionItems", new ArrayList<>(),
                    "scoreItems", new ArrayList<>()
                );
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
            
            List<Map<String, Object>> distributionItems = new ArrayList<>();
            int totalCount = evaluations.size();
            
            // 很满意 (5分)
            long satisfiedCount = scoreDistribution.getOrDefault(5, 0L);
            distributionItems.add(Map.of(
                "type", "satisfied",
                "label", "很满意",
                "count", (int) satisfiedCount,
                "percentage", (double) satisfiedCount / totalCount * 100
            ));
            
            // 还不错 (4分)
            long moderateCount = scoreDistribution.getOrDefault(4, 0L);
            distributionItems.add(Map.of(
                "type", "moderate",
                "label", "还不错",
                "count", (int) moderateCount,
                "percentage", (double) moderateCount / totalCount * 100
            ));
            
            // 不满意 (1-3分)
            long dissatisfiedCount = scoreDistribution.getOrDefault(1, 0L) +
                                   scoreDistribution.getOrDefault(2, 0L) +
                                   scoreDistribution.getOrDefault(3, 0L);
            distributionItems.add(Map.of(
                "type", "dissatisfied",
                "label", "不满意",
                "count", (int) dissatisfiedCount,
                "percentage", (double) dissatisfiedCount / totalCount * 100
            ));
            
            // 计算分项评分
            List<Map<String, Object>> scoreItems = new ArrayList<>();
            
            // 导游服务评分
            double guideScore = evaluations.stream()
                    .filter(eval -> eval.getServiceScore() != null)
                    .mapToInt(eval -> eval.getServiceScore().intValue())
                    .average()
                    .orElse(0.0);
            scoreItems.add(Map.of("type", "导游服务", "score", guideScore));
            
            // 行程规划评分
            double itineraryScore = evaluations.stream()
                    .filter(eval -> eval.getEnvironmentScore() != null)
                    .mapToInt(eval -> eval.getEnvironmentScore().intValue())
                    .average()
                    .orElse(0.0);
            scoreItems.add(Map.of("type", "行程规划", "score", itineraryScore));
            
            // 住宿体验评分
            double accommodationScore = evaluations.stream()
                    .filter(eval -> eval.getCostEffScore() != null)
                    .mapToInt(eval -> eval.getCostEffScore().intValue())
                    .average()
                    .orElse(0.0);
            scoreItems.add(Map.of("type", "住宿体验", "score", accommodationScore));
            
            // 交通出行评分
            scoreItems.add(Map.of("type", "交通出行", "score", avgScore));
            
            return Map.of(
                "satisfactionRate", satisfactionRate,
                "totalReviews", totalCount,
                "distributionItems", distributionItems,
                "scoreItems", scoreItems
            );
            
        } catch (Exception e) {
            log.error("获取产品评价汇总失败", e);
            return Map.of(
                "satisfactionRate", 0.0,
                "totalReviews", 0,
                "distributionItems", new ArrayList<>(),
                "scoreItems", new ArrayList<>()
            );
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
    @Transactional
    public boolean updateEvaluation(Long evalId, UnifiedEvaluationDTO evaluationDTO) {
        try {
            log.info("=== 开始编辑评价 ===");
            log.info("评价ID: {}", evalId);
            log.info("编辑数据: {}", evaluationDTO);
            
            // 检查评价是否存在
            TourEvaluation existingEvaluation = getById(evalId);
            if (existingEvaluation == null) {
                log.error("评价不存在: {}", evalId);
                return false;
            }
            
            // 检查用户是否有权限编辑（只能编辑自己的评价）
            if (!existingEvaluation.getUserId().equals(evaluationDTO.getUserId())) {
                log.error("用户无权限编辑评价: userId={}, evalUserId={}", 
                          evaluationDTO.getUserId(), existingEvaluation.getUserId());
                return false;
            }
            
            // 处理图片URL
            String processedImgUrls = processImageUrls(evaluationDTO.getImgUrls());
            log.info("处理后的图片URLs: '{}'", processedImgUrls);
            
            // 更新评价信息
            existingEvaluation.setOverallScore(evaluationDTO.getOverallScore());
            existingEvaluation.setServiceScore(evaluationDTO.getServiceScore());
            existingEvaluation.setEnvironmentScore(evaluationDTO.getEnvironmentScore());
            existingEvaluation.setCostEffScore(evaluationDTO.getCostEffScore());
            existingEvaluation.setContent(evaluationDTO.getContent());
            existingEvaluation.setImgUrls(processedImgUrls);
            existingEvaluation.setEvalTime(LocalDateTime.now()); // 更新评价时间
            
            boolean result = updateById(existingEvaluation);
            
            if (result) {
                log.info("评价编辑成功，评价ID: {}, 图片URLs: '{}'", evalId, processedImgUrls);
            } else {
                log.error("评价编辑失败");
            }
            
            return result;
            
        } catch (Exception e) {
            log.error("编辑评价失败", e);
            return false;
        }
    }
    
    @Override
    @Transactional
    public boolean deleteEvaluation(Long evalId) {
        try {
            return removeById(evalId);
        } catch (Exception e) {
            log.error("删除评价失败", e);
            return false;
        }
    }
    
    @Override
    public List<UnifiedEvaluationVO> getAllEvaluations(Integer page, Integer size, Byte evalType) {
        try {
            Page<TourEvaluation> pageParam = new Page<>(page, size);
            LambdaQueryWrapper<TourEvaluation> queryWrapper = new LambdaQueryWrapper<>();
            
            if (evalType != null) {
                queryWrapper.eq(TourEvaluation::getEvalType, evalType);
            }
            
            queryWrapper.orderByDesc(TourEvaluation::getEvalTime);
            
            Page<TourEvaluation> evaluationPage = page(pageParam, queryWrapper);
            List<TourEvaluation> evaluations = evaluationPage.getRecords();
            
            return convertToUnifiedVO(evaluations);
        } catch (Exception e) {
            log.error("获取所有评价失败", e);
            return new ArrayList<>();
        }
    }
    
    /**
     * 转换为统一VO（带当前用户ID，用于判断是否显示完整用户名）
     */
    private List<UnifiedEvaluationVO> convertToUnifiedVO(List<TourEvaluation> evaluations, Long currentUserId) {
        if (evaluations.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 获取用户信息
        List<Long> userIds = evaluations.stream()
                .map(TourEvaluation::getUserId)
                .distinct()
                .collect(Collectors.toList());
        
        Map<Long, SysUser> userMap = sysUserService.listByIds(userIds)
                .stream()
                .collect(Collectors.toMap(SysUser::getUserId, user -> user));
        
        // 获取用户头像信息
        Map<Long, UserAvatar> avatarMap = getUserAvatars(userIds);
        
        return evaluations.stream().map(evaluation -> {
            UnifiedEvaluationVO vo = new UnifiedEvaluationVO();
            BeanUtils.copyProperties(evaluation, vo);
            
            SysUser user = userMap.get(evaluation.getUserId());
            UserAvatar userAvatar = avatarMap.get(evaluation.getUserId());
            
            if (user != null) {
                // 如果是当前用户查看自己的评价，显示完整用户名；否则脱敏
                if (currentUserId != null && currentUserId.equals(evaluation.getUserId())) {
                    vo.setUserName(user.getRealName());
                    log.info("用户 {} 查看自己的评价，显示完整用户名: {}", currentUserId, user.getRealName());
                } else {
                    vo.setUserName(maskUserName(user.getRealName()));
                    log.info("用户 {} 查看他人评价，显示脱敏用户名: {}", currentUserId, maskUserName(user.getRealName()));
                }
                vo.setUserType("情侣/朋友"); // 默认用户类型
                
                // 获取用户个人中心上传的头像
                if (userAvatar != null && userAvatar.getAvatarUrl() != null && !userAvatar.getAvatarUrl().trim().isEmpty()) {
                    String avatarUrl = userAvatar.getAvatarUrl();
                    // 处理个人中心头像URL
                    avatarUrl = processPersonalAvatarUrl(avatarUrl);
                    vo.setUserAvatar(avatarUrl);
                    log.info("用户 {} 使用个人中心头像: {}", evaluation.getUserId(), avatarUrl);
                } else {
                    // 用户没有设置头像时使用默认头像
                    vo.setUserAvatar("/static/images/default-avatar.png");
                    log.info("用户 {} 使用默认头像", evaluation.getUserId());
                }
            } else {
                // 用户不存在时使用默认值
                vo.setUserAvatar("/static/images/default-avatar.png");
                vo.setUserName("匿名用户");
                vo.setUserType("游客");
                log.info("用户不存在，使用默认头像");
            }
            
            vo.setSource("来自WWW站"); // 默认来源
            vo.setUsefulCount(12); // 默认有用数
            
            // 处理评价图片URL
            if (evaluation.getImgUrls() != null && !evaluation.getImgUrls().trim().isEmpty()) {
                vo.setImgUrls(processImageUrls(evaluation.getImgUrls()));
            }
            
            return vo;
        }).collect(Collectors.toList());
    }
    
    /**
     * 转换为统一VO（默认脱敏处理）
     */
    private List<UnifiedEvaluationVO> convertToUnifiedVO(List<TourEvaluation> evaluations) {
        // 调用带当前用户ID的方法，传入null表示不显示完整用户名
        return convertToUnifiedVO(evaluations, null);
    }
    
    /**
     * 获取用户头像信息
     */
    private Map<Long, UserAvatar> getUserAvatars(List<Long> userIds) {
        try {
            if (userIds.isEmpty()) {
                return new HashMap<>();
            }
            
            LambdaQueryWrapper<UserAvatar> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(UserAvatar::getUserId, userIds)
                       .eq(UserAvatar::getIsCurrent, true)
                       .orderByDesc(UserAvatar::getCreateTime);
            
            List<UserAvatar> avatars = userAvatarMapper.selectList(queryWrapper);
            
            log.info("获取到用户头像数量: {}, 用户IDs: {}", avatars.size(), userIds);
            
            return avatars.stream()
                    .collect(Collectors.toMap(UserAvatar::getUserId, avatar -> avatar, (existing, replacement) -> existing));
        } catch (Exception e) {
            log.error("获取用户头像失败，用户IDs: {}", userIds, e);
            return new HashMap<>();
        }
    }
    
    /**
     * 处理个人中心上传的头像URL
     */
    private String processPersonalAvatarUrl(String avatarUrl) {
        if (avatarUrl == null || avatarUrl.trim().isEmpty() || 
            avatarUrl.equals("null") || avatarUrl.equals("undefined")) {
            return "/static/images/default-avatar.png";
        }
        
        String trimmedUrl = avatarUrl.trim();
        
        // 如果是完整URL，直接返回
        if (trimmedUrl.startsWith("http")) {
            return trimmedUrl;
        }
        
        // 个人中心头像通常存储在 /uploads/avatar/ 目录下
        if (!trimmedUrl.startsWith("/")) {
            return "/uploads/avatar/" + trimmedUrl;
        }
        
        // 如果已经是相对路径，直接返回
        return trimmedUrl;
    }
    
    /**
     * 处理订单管理上传的评价图片URL
     */
    private String processImageUrls(String imgUrls) {
        if (imgUrls == null || imgUrls.trim().isEmpty()) {
            return "";
        }
        
        String[] urls = imgUrls.split(",");
        List<String> processedUrls = new ArrayList<>();
        
        for (String url : urls) {
            String trimmedUrl = url.trim();
            if (!trimmedUrl.isEmpty() && !trimmedUrl.equals("null") && !trimmedUrl.equals("undefined")) {
                // 如果是完整URL，直接使用
                if (trimmedUrl.startsWith("http")) {
                    processedUrls.add(trimmedUrl);
                } else {
                    // 订单管理上传的评价图片通常存储在 /uploads/evaluation/ 目录下
                    if (!trimmedUrl.startsWith("/")) {
                        processedUrls.add("/uploads/evaluation/" + trimmedUrl);
                    } else {
                        processedUrls.add(trimmedUrl);
                    }
                }
            }
        }
        
        log.info("处理评价图片URL: 原始={}, 处理后={}", imgUrls, String.join(",", processedUrls));
        return String.join(",", processedUrls);
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
