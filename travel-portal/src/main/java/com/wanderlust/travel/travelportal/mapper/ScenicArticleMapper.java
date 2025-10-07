package com.wanderlust.travel.travelportal.mapper;

import com.wanderlust.travel.travelportal.entity.ScenicArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 景点文章表 Mapper 接口
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
public interface ScenicArticleMapper extends BaseMapper<ScenicArticle> {

    /**
     * 分页查询文章列表（带搜索条件）
     */
    IPage<ScenicArticle> selectArticlePage(Page<ScenicArticle> page, 
                                         @Param("keyword") String keyword,
                                         @Param("region") String region,
                                         @Param("sortBy") String sortBy);

    /**
     * 获取热门服务商列表
     */
    @Select("SELECT merchant_id as merchantId, merchant_name as merchantName, COUNT(*) as articleCount " +
            "FROM scenic_article " +
            "WHERE status = 1 " +
            "GROUP BY merchant_id, merchant_name " +
            "ORDER BY articleCount DESC " +
            "LIMIT #{limit}")
    List<Map<String, Object>> selectHotMerchants(@Param("limit") Integer limit);

    /**
     * 增加文章浏览次数
     */
    @Select("UPDATE scenic_article SET view_count = view_count + 1 WHERE article_id = #{articleId}")
    void incrementViewCount(@Param("articleId") Long articleId);
}
