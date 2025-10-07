package com.wanderlust.travel.travelportal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.travelportal.entity.ScenicArticle;
import com.wanderlust.travel.travelportal.entity.HotDestination;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 景点推荐服务接口
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
public interface IScenicRecommendService {

    /**
     * 分页查询景点文章列表
     */
    IPage<ScenicArticle> getArticlePage(Page<ScenicArticle> page, String keyword, String region, String sortBy);

    /**
     * 获取文章详情
     */
    ScenicArticle getArticleDetail(Long articleId);

    /**
     * 获取文章关联套餐
     */
    List<Map<String, Object>> getArticlePackages(Long articleId);

    /**
     * 获取热门目的地列表
     */
    List<HotDestination> getHotDestinations(Integer limit);

    /**
     * 获取热门服务商列表
     */
    List<Map<String, Object>> getHotMerchants(Integer limit);

    /**
     * 增加文章浏览次数
     */
    void incrementArticleViewCount(Long articleId);

    /**
     * 增加目的地浏览次数
     */
    void incrementDestinationViewCount(Long destId);
}
