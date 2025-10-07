package com.wanderlust.travel.traveladmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.traveladmin.entity.ArticlePackage;
import com.wanderlust.travel.traveladmin.mapper.ArticlePackageMapper;
import com.wanderlust.travel.traveladmin.service.ArticlePackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 文章套餐关联表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticlePackageServiceImpl extends ServiceImpl<ArticlePackageMapper, ArticlePackage> implements ArticlePackageService {

    @Override
    public List<ArticlePackage> getPackagesByArticleId(Long articleId) {
        log.debug("根据文章ID获取关联套餐列表，文章ID：{}", articleId);
        return baseMapper.selectByArticleId(articleId);
    }

    @Override
    public List<ArticlePackage> getArticlesByProductId(Long productId) {
        log.debug("根据产品ID获取关联文章列表，产品ID：{}", productId);
        return baseMapper.selectByProductId(productId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addArticlePackage(ArticlePackage articlePackage) {
        log.info("添加文章套餐关联，文章ID：{}，产品ID：{}", articlePackage.getArticleId(), articlePackage.getProductId());
        
        try {
            boolean success = save(articlePackage);
            if (success) {
                log.info("添加文章套餐关联成功，关联ID：{}", articlePackage.getId());
            } else {
                log.error("添加文章套餐关联失败");
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("添加文章套餐关联失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchAddArticlePackages(Long articleId, List<Long> productIds) {
        log.info("批量添加文章套餐关联，文章ID：{}，产品ID列表：{}", articleId, productIds);
        
        if (CollectionUtils.isEmpty(productIds)) {
            log.warn("产品ID列表为空，跳过批量添加");
            return true;
        }
        
        try {
            List<ArticlePackage> packages = new java.util.ArrayList<>();
            for (int i = 0; i < productIds.size(); i++) {
                ArticlePackage articlePackage = new ArticlePackage();
                articlePackage.setArticleId(articleId);
                articlePackage.setProductId(productIds.get(i));
                articlePackage.setSortOrder(i + 1);
                packages.add(articlePackage);
            }
            
            int count = baseMapper.batchInsert(packages);
            boolean success = count > 0;
            
            if (success) {
                log.info("批量添加文章套餐关联成功，添加数量：{}", count);
            } else {
                log.error("批量添加文章套餐关联失败");
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("批量添加文章套餐关联失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateArticlePackage(ArticlePackage articlePackage) {
        log.info("更新文章套餐关联，关联ID：{}", articlePackage.getId());
        
        try {
            boolean success = updateById(articlePackage);
            if (success) {
                log.info("更新文章套餐关联成功，关联ID：{}", articlePackage.getId());
            } else {
                log.error("更新文章套餐关联失败，关联ID：{}", articlePackage.getId());
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("更新文章套餐关联失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteArticlePackage(Long relId) {
        log.info("删除文章套餐关联，关联ID：{}", relId);
        
        try {
            boolean success = removeById(relId);
            if (success) {
                log.info("删除文章套餐关联成功，关联ID：{}", relId);
            } else {
                log.error("删除文章套餐关联失败，关联ID：{}", relId);
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("删除文章套餐关联失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByArticleId(Long articleId) {
        log.info("删除文章的所有套餐关联，文章ID：{}", articleId);
        
        try {
            int count = baseMapper.deleteByArticleId(articleId);
            boolean success = count >= 0; // 即使没有关联记录也认为成功
            
            if (success) {
                log.info("删除文章的所有套餐关联成功，文章ID：{}，删除数量：{}", articleId, count);
            } else {
                log.error("删除文章的所有套餐关联失败，文章ID：{}", articleId);
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("删除文章的所有套餐关联失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByProductId(Long productId) {
        log.info("删除产品的所有文章关联，产品ID：{}", productId);
        
        try {
            int count = baseMapper.deleteByProductId(productId);
            boolean success = count >= 0; // 即使没有关联记录也认为成功
            
            if (success) {
                log.info("删除产品的所有文章关联成功，产品ID：{}，删除数量：{}", productId, count);
            } else {
                log.error("删除产品的所有文章关联失败，产品ID：{}", productId);
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("删除产品的所有文章关联失败", e);
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteArticlePackages(List<Long> relIds) {
        log.info("批量删除文章套餐关联，关联ID列表：{}", relIds);
        
        if (CollectionUtils.isEmpty(relIds)) {
            log.warn("关联ID列表为空，跳过批量删除");
            return true;
        }
        
        try {
            boolean success = removeByIds(relIds);
            if (success) {
                log.info("批量删除文章套餐关联成功，删除数量：{}", relIds.size());
            } else {
                log.error("批量删除文章套餐关联失败");
            }
            
            return success;
            
        } catch (Exception e) {
            log.error("批量删除文章套餐关联失败", e);
            throw e;
        }
    }
}
