package com.wanderlust.travel.traveladmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.traveladmin.entity.Article;
import com.wanderlust.travel.traveladmin.entity.ArticlePackage;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章主表 服务类
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
public interface ArticleService extends IService<Article> {

    /**
     * 分页查询文章列表
     */
    IPage<Article> getArticlePage(Page<Article> page, String keyword, String category, 
                                 String status, String region, Boolean featured);

    /**
     * 获取文章详情（包含关联套餐）
     */
    Article getArticleDetail(Long articleId);

    /**
     * 创建文章
     */
    boolean createArticle(Article article, List<Long> productIds);

    /**
     * 更新文章
     */
    boolean updateArticle(Article article, List<Long> productIds);

    /**
     * 删除文章
     */
    boolean deleteArticle(Long articleId);

    /**
     * 批量删除文章
     */
    boolean batchDeleteArticles(List<Long> articleIds);

    /**
     * 批量更新文章状态
     */
    boolean batchUpdateStatus(List<Long> articleIds, String status);

    /**
     * 发布文章
     */
    boolean publishArticle(Long articleId);

    /**
     * 下架文章
     */
    boolean unpublishArticle(Long articleId);

    /**
     * 复制文章
     */
    Article copyArticle(Long articleId);

    /**
     * 获取文章统计数据
     */
    Map<String, Object> getArticleStatistics();

    /**
     * 增加文章浏览次数
     */
    void incrementViewCount(Long articleId);

    /**
     * 增加文章点赞数
     */
    void incrementLikeCount(Long articleId);

    /**
     * 增加文章评论数
     */
    void incrementCommentCount(Long articleId);

    /**
     * 获取热门文章列表
     */
    List<Article> getHotArticles(Integer limit);

    /**
     * 获取最新文章列表
     */
    List<Article> getLatestArticles(Integer limit);

    /**
     * 获取精选文章列表
     */
    List<Article> getFeaturedArticles(Integer limit);

    /**
     * 搜索文章
     */
    List<Article> searchArticles(String keyword, String category, String region);
}
