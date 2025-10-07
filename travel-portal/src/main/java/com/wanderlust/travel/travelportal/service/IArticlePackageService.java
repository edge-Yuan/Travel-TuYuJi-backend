package com.wanderlust.travel.travelportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.travelportal.dto.ArticlePackageDTO;
import com.wanderlust.travel.travelportal.dto.BatchArticlePackageDTO;
import com.wanderlust.travel.travelportal.entity.ArticlePackage;
import com.wanderlust.travel.travelportal.vo.ArticlePackageVO;

import java.util.List;

/**
 * <p>
 * 文章关联套餐表 服务类
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
public interface IArticlePackageService extends IService<ArticlePackage> {

    /**
     * 添加文章套餐关联
     */
    boolean addArticlePackage(ArticlePackageDTO articlePackageDTO);

    /**
     * 更新文章套餐关联
     */
    boolean updateArticlePackage(ArticlePackageDTO articlePackageDTO);

    /**
     * 删除文章套餐关联
     */
    boolean deleteArticlePackage(Long relId);

    /**
     * 根据文章ID获取套餐关联列表
     */
    List<ArticlePackageVO> getArticlePackages(Long articleId);

    /**
     * 根据关联ID获取套餐关联详情
     */
    ArticlePackageVO getArticlePackageById(Long relId);

    /**
     * 批量操作文章套餐关联
     */
    boolean batchOperateArticlePackages(BatchArticlePackageDTO batchDTO);

    /**
     * 检查文章套餐关联是否存在
     */
    boolean checkArticlePackageExists(Long articleId, Long productId);

    /**
     * 更新套餐关联排序
     */
    boolean updatePackageSortOrder(Long relId, Integer sortOrder);
}
