package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章主表 Mapper 接口
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 分页查询文章列表（带搜索条件）
     */
    IPage<Article> selectArticlePage(Page<Article> page,
                                   @Param("keyword") String keyword,
                                   @Param("category") String category,
                                   @Param("status") String status,
                                   @Param("region") String region,
                                   @Param("featured") Boolean featured);

    /**
     * 获取文章统计数据
     */
    @Select("SELECT " +
            "COUNT(*) as totalArticles, " +
            "SUM(CASE WHEN status = 'published' THEN 1 ELSE 0 END) as publishedArticles, " +
            "SUM(CASE WHEN status = 'draft' THEN 1 ELSE 0 END) as draftArticles, " +
            "SUM(CASE WHEN status = 'pending' THEN 1 ELSE 0 END) as pendingArticles, " +
            "SUM(COALESCE(views, 0)) as totalViews, " +
            "SUM(COALESCE(likes, 0)) as totalLikes, " +
            "SUM(COALESCE(comments, 0)) as totalComments " +
            "FROM article")
    Map<String, Object> selectArticleStatistics();

    /**
     * 增加文章浏览次数
     */
    @Update("UPDATE article SET views = COALESCE(views, 0) + 1 WHERE id = #{articleId}")
    void incrementViewCount(@Param("articleId") Long articleId);

    /**
     * 增加文章点赞数
     */
    @Update("UPDATE article SET likes = COALESCE(likes, 0) + 1 WHERE id = #{articleId}")
    void incrementLikeCount(@Param("articleId") Long articleId);

    /**
     * 增加文章评论数
     */
    @Update("UPDATE article SET comments = COALESCE(comments, 0) + 1 WHERE id = #{articleId}")
    void incrementCommentCount(@Param("articleId") Long articleId);

    /**
     * 批量更新文章状态
     */
    int batchUpdateStatus(@Param("ids") List<Long> ids, @Param("status") String status);

    /**
     * 批量删除文章
     */
    int batchDelete(@Param("ids") List<Long> ids);

    /**
     * 获取热门文章列表
     */
    @Select("SELECT * FROM article WHERE status = 'published' ORDER BY views DESC, likes DESC LIMIT #{limit}")
    List<Article> selectHotArticles(@Param("limit") Integer limit);

    /**
     * 获取最新文章列表
     */
    @Select("SELECT * FROM article WHERE status = 'published' ORDER BY publish_time DESC LIMIT #{limit}")
    List<Article> selectLatestArticles(@Param("limit") Integer limit);

    /**
     * 获取精选文章列表
     */
    @Select("SELECT * FROM article WHERE status = 'published' AND featured = 1 ORDER BY publish_time DESC LIMIT #{limit}")
    List<Article> selectFeaturedArticles(@Param("limit") Integer limit);
}
