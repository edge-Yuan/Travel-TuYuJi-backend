package com.wanderlust.travel.traveladmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.traveladmin.entity.ArticlePackage;

import java.util.List;

/**
 * <p>
 * 文章套餐关联表 服务类
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
public interface ArticlePackageService extends IService<ArticlePackage> {

    /**
     * 根据文章ID获取关联套餐列表
     */
    List<ArticlePackage> getPackagesByArticleId(Long articleId);

    /**
     * 根据产品ID获取关联文章列表
     */
    List<ArticlePackage> getArticlesByProductId(Long productId);

    /**
     * 添加文章套餐关联
     */
    boolean addArticlePackage(ArticlePackage articlePackage);

    /**
     * 批量添加文章套餐关联
     */
    boolean batchAddArticlePackages(Long articleId, List<Long> productIds);

    /**
     * 更新文章套餐关联
     */
    boolean updateArticlePackage(ArticlePackage articlePackage);

    /**
     * 删除文章套餐关联
     */
    boolean deleteArticlePackage(Long relId);

    /**
     * 删除文章的所有套餐关联
     */
    boolean deleteByArticleId(Long articleId);

    /**
     * 删除产品的所有文章关联
     */
    boolean deleteByProductId(Long productId);

    /**
     * 批量删除文章套餐关联
     */
    boolean batchDeleteArticlePackages(List<Long> relIds);
}
