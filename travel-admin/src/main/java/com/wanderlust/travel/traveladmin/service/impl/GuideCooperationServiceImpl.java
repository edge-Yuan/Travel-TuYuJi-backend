package com.wanderlust.travel.traveladmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanderlust.travel.traveladmin.dto.GuideCooperationDTO;
import com.wanderlust.travel.traveladmin.dto.GuideSearchDTO;
import com.wanderlust.travel.traveladmin.entity.GuideCooperation;
import com.wanderlust.travel.traveladmin.exception.BusinessException;
import com.wanderlust.travel.traveladmin.mapper.GuideCooperationMapper;
import com.wanderlust.travel.traveladmin.service.GuideCooperationService;
import com.wanderlust.travel.traveladmin.vo.GuideCooperationVO;
import com.wanderlust.travel.traveladmin.vo.GuideVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 导游合作服务实现类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GuideCooperationServiceImpl implements GuideCooperationService {

    private final GuideCooperationMapper guideCooperationMapper;
    private final ObjectMapper objectMapper;

    @Override
    public IPage<GuideVO> searchGuides(Page<GuideVO> page, GuideSearchDTO searchDTO) {
        // 这里应该调用travel-portal的导游查询接口
        // 由于我们是在travel-admin中，这里先返回模拟数据
        // 实际项目中应该通过Feign或其他方式调用travel-portal的服务
        
        log.info("搜索导游，条件：{}", searchDTO);
        
        // 模拟数据，实际应该从数据库查询
        List<GuideVO> guides = createMockGuides();
        
        // 应用搜索条件
        List<GuideVO> filteredGuides = guides.stream()
            .filter(guide -> filterByKeyword(guide, searchDTO.getKeyword()))
            .filter(guide -> filterByLocation(guide, searchDTO.getLocation()))
            .filter(guide -> filterByLanguage(guide, searchDTO.getLanguage()))
            .filter(guide -> filterByExperience(guide, searchDTO.getExperience()))
            .filter(guide -> filterByRating(guide, searchDTO.getMinRating()))
            .filter(guide -> filterByPrice(guide, searchDTO.getMaxPrice()))
            .collect(Collectors.toList());
        
        // 分页处理
        int start = (int) ((page.getCurrent() - 1) * page.getSize());
        int end = Math.min(start + (int) page.getSize(), filteredGuides.size());
        List<GuideVO> pageData = filteredGuides.subList(start, end);
        
        page.setRecords(pageData);
        page.setTotal(filteredGuides.size());
        
        return page;
    }

    @Override
    public GuideVO getGuideDetail(Long guideId) {
        log.info("获取导游详情，ID：{}", guideId);
        
        // 模拟数据，实际应该从数据库查询
        return createMockGuideDetail(guideId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean sendCooperationRequest(GuideCooperationDTO cooperationDTO, Long merchantId) {
        log.info("发送合作邀请，导游ID：{}，旅行商ID：{}", cooperationDTO.getGuideId(), merchantId);
        
        try {
            // 检查是否已经存在相同的合作邀请
            LambdaQueryWrapper<GuideCooperation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(GuideCooperation::getMerchantId, merchantId)
                       .eq(GuideCooperation::getGuideId, cooperationDTO.getGuideId())
                       .in(GuideCooperation::getStatus, Arrays.asList((byte) 0, (byte) 1)); // 待确认或已接受
            
            GuideCooperation existingCooperation = guideCooperationMapper.selectOne(queryWrapper);
            if (existingCooperation != null) {
                throw new BusinessException("已经向该导游发送过合作邀请，请勿重复发送");
            }
            
            // 创建合作邀请
            GuideCooperation cooperation = new GuideCooperation();
            cooperation.setMerchantId(merchantId);
            cooperation.setGuideId(cooperationDTO.getGuideId());
            cooperation.setCooperationType(cooperationDTO.getCooperationType());
            cooperation.setProjectIds(objectMapper.writeValueAsString(cooperationDTO.getProjectIds()));
            cooperation.setStartDate(cooperationDTO.getStartDate());
            cooperation.setEndDate(cooperationDTO.getEndDate());
            cooperation.setConditions(cooperationDTO.getConditions());
            cooperation.setNotes(cooperationDTO.getNotes());
            cooperation.setStatus((byte) 0); // 待确认
            cooperation.setCreateTime(LocalDateTime.now());
            cooperation.setUpdateTime(LocalDateTime.now());
            
            int result = guideCooperationMapper.insert(cooperation);
            return result > 0;
            
        } catch (JsonProcessingException e) {
            log.error("序列化项目ID列表失败", e);
            throw new BusinessException("处理合作项目信息失败");
        } catch (Exception e) {
            log.error("发送合作邀请失败", e);
            throw new BusinessException("发送合作邀请失败：" + e.getMessage());
        }
    }

    @Override
    public IPage<GuideCooperationVO> getCooperationList(Page<GuideCooperationVO> page, 
                                                       Long merchantId, 
                                                       Byte status) {
        log.info("查询合作邀请列表，旅行商ID：{}，状态：{}", merchantId, status);
        
        return guideCooperationMapper.selectCooperationPage(page, merchantId, status);
    }

    @Override
    public GuideCooperationVO getCooperationDetail(Long cooperationId) {
        log.info("获取合作邀请详情，ID：{}", cooperationId);
        
        return guideCooperationMapper.selectCooperationDetail(cooperationId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelCooperation(Long cooperationId, Long merchantId) {
        log.info("取消合作邀请，ID：{}，旅行商ID：{}", cooperationId, merchantId);
        
        GuideCooperation cooperation = guideCooperationMapper.selectById(cooperationId);
        if (cooperation == null) {
            throw new BusinessException("合作邀请不存在");
        }
        
        if (!cooperation.getMerchantId().equals(merchantId)) {
            throw new BusinessException("无权限操作该合作邀请");
        }
        
        if (cooperation.getStatus() != 0) {
            throw new BusinessException("只能取消待确认的合作邀请");
        }
        
        cooperation.setStatus((byte) 2); // 已拒绝
        cooperation.setRejectReason("旅行商主动取消");
        cooperation.setUpdateTime(LocalDateTime.now());
        cooperation.setResponseTime(LocalDateTime.now());
        
        int result = guideCooperationMapper.updateById(cooperation);
        return result > 0;
    }

    @Override
    public GuideCooperationStatsVO getCooperationStats(Long merchantId) {
        log.info("获取合作邀请统计，旅行商ID：{}", merchantId);
        
        GuideCooperationStatsVO stats = new GuideCooperationStatsVO();
        stats.setTotalInvitations(guideCooperationMapper.countCooperationByMerchant(merchantId, null));
        stats.setPendingInvitations(guideCooperationMapper.countCooperationByMerchant(merchantId, (byte) 0));
        stats.setAcceptedInvitations(guideCooperationMapper.countCooperationByMerchant(merchantId, (byte) 1));
        stats.setRejectedInvitations(guideCooperationMapper.countCooperationByMerchant(merchantId, (byte) 2));
        stats.setCompletedCooperations(guideCooperationMapper.countCooperationByMerchant(merchantId, (byte) 3));
        
        return stats;
    }

    // 私有方法：创建模拟导游数据
    private List<GuideVO> createMockGuides() {
        // 这里返回模拟数据，实际应该从数据库查询
        return Arrays.asList(
            createMockGuide(1L, "张明", "北京", 4.8, Arrays.asList("历史文化", "古建筑", "博物馆"), 
                          Arrays.asList("中文", "英语"), 5, 156, 500.0),
            createMockGuide(2L, "李小红", "上海", 4.6, Arrays.asList("现代都市", "购物", "美食"), 
                          Arrays.asList("中文", "日语"), 3, 89, 400.0),
            createMockGuide(3L, "王强", "广州", 4.9, Arrays.asList("自然风光", "户外运动", "摄影"), 
                          Arrays.asList("中文", "英语", "韩语"), 7, 234, 600.0)
        );
    }

    private GuideVO createMockGuide(Long guideId, String name, String location, 
                                  Double rating, List<String> specialties, 
                                  List<String> languages, Integer experience, 
                                  Integer serviceCount, Double price) {
        GuideVO guide = new GuideVO();
        guide.setGuideId(guideId);
        guide.setRealName(name);
        guide.setLocation(location);
        guide.setServiceScore(java.math.BigDecimal.valueOf(rating));
        guide.setSpecialties(specialties);
        guide.setLanguages(languages);
        guide.setExperience(experience);
        guide.setServiceCount(serviceCount);
        guide.setBaseFee(java.math.BigDecimal.valueOf(price));
        guide.setAvatar("/src/assets/logo/logo1.jpg");
        guide.setBio("专业的导游，服务热情周到");
        guide.setQualificationStatus((byte) 1);
        return guide;
    }

    private GuideVO createMockGuideDetail(Long guideId) {
        GuideVO guide = createMockGuides().stream()
            .filter(g -> g.getGuideId().equals(guideId))
            .findFirst()
            .orElse(null);
        
        if (guide != null) {
            // 添加评价和案例数据
            guide.setReviews(Arrays.asList(
                createMockReview(1L, "李女士", 5, "2024-01-15", "非常专业，讲解详细，服务态度很好！"),
                createMockReview(2L, "王先生", 4, "2024-01-10", "对历史文化的讲解很深入，学到了很多知识。")
            ));
            
            guide.setCases(Arrays.asList(
                createMockCase(1L, "故宫深度游", "为游客提供故宫深度游览服务，讲解明清历史", 
                             Arrays.asList("/src/assets/images/travel.jpg", "/src/assets/images/travel2.jpg"))
            ));
        }
        
        return guide;
    }

    private GuideVO.GuideReviewVO createMockReview(Long reviewId, String reviewerName, 
                                                  Integer rating, String date, String content) {
        GuideVO.GuideReviewVO review = new GuideVO.GuideReviewVO();
        review.setReviewId(reviewId);
        review.setReviewerName(reviewerName);
        review.setRating(rating);
        review.setDate(date);
        review.setContent(content);
        return review;
    }

    private GuideVO.GuideCaseVO createMockCase(Long caseId, String title, 
                                             String description, List<String> images) {
        GuideVO.GuideCaseVO caseItem = new GuideVO.GuideCaseVO();
        caseItem.setCaseId(caseId);
        caseItem.setTitle(title);
        caseItem.setDescription(description);
        caseItem.setImages(images);
        return caseItem;
    }

    // 过滤方法
    private boolean filterByKeyword(GuideVO guide, String keyword) {
        if (!StringUtils.hasText(keyword)) return true;
        String lowerKeyword = keyword.toLowerCase();
        return guide.getRealName().toLowerCase().contains(lowerKeyword) ||
               guide.getSpecialties().stream().anyMatch(s -> s.toLowerCase().contains(lowerKeyword));
    }

    private boolean filterByLocation(GuideVO guide, String location) {
        if (!StringUtils.hasText(location)) return true;
        return guide.getLocation().equals(location);
    }

    private boolean filterByLanguage(GuideVO guide, String language) {
        if (!StringUtils.hasText(language)) return true;
        return guide.getLanguages().contains(language);
    }

    private boolean filterByExperience(GuideVO guide, String experience) {
        if (!StringUtils.hasText(experience)) return true;
        switch (experience) {
            case "junior": return guide.getExperience() >= 1 && guide.getExperience() <= 2;
            case "intermediate": return guide.getExperience() >= 3 && guide.getExperience() <= 5;
            case "senior": return guide.getExperience() > 5;
            default: return true;
        }
    }

    private boolean filterByRating(GuideVO guide, Double minRating) {
        if (minRating == null) return true;
        return guide.getServiceScore().doubleValue() >= minRating;
    }

    private boolean filterByPrice(GuideVO guide, Double maxPrice) {
        if (maxPrice == null) return true;
        return guide.getBaseFee().doubleValue() <= maxPrice;
    }
}
