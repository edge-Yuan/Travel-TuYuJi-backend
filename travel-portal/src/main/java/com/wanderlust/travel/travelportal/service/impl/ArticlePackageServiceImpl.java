package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.dto.ArticlePackageDTO;
import com.wanderlust.travel.travelportal.dto.BatchArticlePackageDTO;
import com.wanderlust.travel.travelportal.entity.ArticlePackage;
import com.wanderlust.travel.travelportal.mapper.ArticlePackageMapper;
import com.wanderlust.travel.travelportal.service.IArticlePackageService;
import com.wanderlust.travel.travelportal.vo.ArticlePackageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章关联套餐表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Slf4j
@Service
public class ArticlePackageServiceImpl extends ServiceImpl<ArticlePackageMapper, ArticlePackage> implements IArticlePackageService {

    @Autowired
    private ArticlePackageMapper articlePackageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addArticlePackage(ArticlePackageDTO articlePackageDTO) {
        log.info("添加文章套餐关联: {}", articlePackageDTO);
        
        // 检查是否已存在相同的关联
        if (checkArticlePackageExists(articlePackageDTO.getArticleId(), articlePackageDTO.getProductId())) {
            log.warn("文章套餐关联已存在: articleId={}, productId={}", 
                    articlePackageDTO.getArticleId(), articlePackageDTO.getProductId());
            return false;
        }

        ArticlePackage articlePackage = new ArticlePackage();
        BeanUtils.copyProperties(articlePackageDTO, articlePackage);
        articlePackage.setCreateTime(LocalDateTime.now());
        
        return save(articlePackage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateArticlePackage(ArticlePackageDTO articlePackageDTO) {
        log.info("更新文章套餐关联: {}", articlePackageDTO);
        
        if (articlePackageDTO.getRelId() == null) {
            log.error("更新文章套餐关联失败: relId不能为空");
            return false;
        }

        ArticlePackage articlePackage = getById(articlePackageDTO.getRelId());
        if (articlePackage == null) {
            log.error("更新文章套餐关联失败: 关联不存在, relId={}", articlePackageDTO.getRelId());
            return false;
        }

        BeanUtils.copyProperties(articlePackageDTO, articlePackage);
        return updateById(articlePackage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteArticlePackage(Long relId) {
        log.info("删除文章套餐关联: relId={}", relId);
        return removeById(relId);
    }

    @Override
    public List<ArticlePackageVO> getArticlePackages(Long articleId) {
        log.info("获取文章套餐关联列表: articleId={}", articleId);
        
        List<Map<String, Object>> packageMaps = articlePackageMapper.selectPackagesByArticleId(articleId);
        List<ArticlePackageVO> packageVOs = new ArrayList<>();
        
        for (Map<String, Object> packageMap : packageMaps) {
            ArticlePackageVO packageVO = new ArticlePackageVO();
            packageVO.setRelId((Long) packageMap.get("relId"));
            packageVO.setArticleId((Long) packageMap.get("articleId"));
            packageVO.setProductId((Long) packageMap.get("productId"));
            packageVO.setPackageName((String) packageMap.get("packageName"));
            packageVO.setPackageImage((String) packageMap.get("packageImage"));
            packageVO.setPackagePrice((java.math.BigDecimal) packageMap.get("packagePrice"));
            packageVO.setSortOrder((Integer) packageMap.get("sortOrder"));
            packageVO.setProductName((String) packageMap.get("productName"));
            packageVO.setMainImgUrl((String) packageMap.get("mainImgUrl"));
            packageVO.setPrice((java.math.BigDecimal) packageMap.get("price"));
            
            packageVOs.add(packageVO);
        }
        
        return packageVOs;
    }

    @Override
    public ArticlePackageVO getArticlePackageById(Long relId) {
        log.info("获取文章套餐关联详情: relId={}", relId);
        
        ArticlePackage articlePackage = getById(relId);
        if (articlePackage == null) {
            return null;
        }

        ArticlePackageVO packageVO = new ArticlePackageVO();
        BeanUtils.copyProperties(articlePackage, packageVO);
        
        // 获取产品信息
        List<Map<String, Object>> packageMaps = articlePackageMapper.selectPackagesByArticleId(articlePackage.getArticleId());
        for (Map<String, Object> packageMap : packageMaps) {
            if (relId.equals(packageMap.get("relId"))) {
                packageVO.setProductName((String) packageMap.get("productName"));
                packageVO.setMainImgUrl((String) packageMap.get("mainImgUrl"));
                packageVO.setPrice((java.math.BigDecimal) packageMap.get("price"));
                break;
            }
        }
        
        return packageVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchOperateArticlePackages(BatchArticlePackageDTO batchDTO) {
        log.info("批量操作文章套餐关联: {}", batchDTO);
        
        String operationType = batchDTO.getOperationType();
        List<ArticlePackageDTO> packages = batchDTO.getPackages();
        
        if (packages == null || packages.isEmpty()) {
            log.warn("批量操作文章套餐关联失败: 套餐列表为空");
            return false;
        }

        try {
            switch (operationType.toUpperCase()) {
                case "ADD":
                    for (ArticlePackageDTO packageDTO : packages) {
                        packageDTO.setArticleId(batchDTO.getArticleId());
                        if (!addArticlePackage(packageDTO)) {
                            log.warn("批量添加失败: {}", packageDTO);
                        }
                    }
                    break;
                case "UPDATE":
                    for (ArticlePackageDTO packageDTO : packages) {
                        if (!updateArticlePackage(packageDTO)) {
                            log.warn("批量更新失败: {}", packageDTO);
                        }
                    }
                    break;
                case "DELETE":
                    for (ArticlePackageDTO packageDTO : packages) {
                        if (packageDTO.getRelId() != null) {
                            if (!deleteArticlePackage(packageDTO.getRelId())) {
                                log.warn("批量删除失败: relId={}", packageDTO.getRelId());
                            }
                        }
                    }
                    break;
                default:
                    log.error("不支持的批量操作类型: {}", operationType);
                    return false;
            }
            return true;
        } catch (Exception e) {
            log.error("批量操作文章套餐关联失败", e);
            return false;
        }
    }

    @Override
    public boolean checkArticlePackageExists(Long articleId, Long productId) {
        LambdaQueryWrapper<ArticlePackage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticlePackage::getArticleId, articleId)
                   .eq(ArticlePackage::getProductId, productId);
        return count(queryWrapper) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePackageSortOrder(Long relId, Integer sortOrder) {
        log.info("更新套餐关联排序: relId={}, sortOrder={}", relId, sortOrder);
        
        ArticlePackage articlePackage = getById(relId);
        if (articlePackage == null) {
            log.error("更新套餐关联排序失败: 关联不存在, relId={}", relId);
            return false;
        }

        articlePackage.setSortOrder(sortOrder);
        return updateById(articlePackage);
    }
}
