package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.traveladmin.entity.ArticlePackage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * <p>
 * 文章套餐关联表 Mapper 接口
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Mapper
public interface ArticlePackageMapper extends BaseMapper<ArticlePackage> {

    /**
     * 根据文章ID获取关联套餐列表
     */
    @Select("SELECT * FROM article_package WHERE article_id = #{articleId} ORDER BY sort_order ASC")
    List<ArticlePackage> selectByArticleId(@Param("articleId") Long articleId);

    /**
     * 根据产品ID获取关联文章列表
     */
    @Select("SELECT * FROM article_package WHERE product_id = #{productId} ORDER BY sort_order ASC")
    List<ArticlePackage> selectByProductId(@Param("productId") Long productId);

    /**
     * 删除文章的所有套餐关联
     */
    @Delete("DELETE FROM article_package WHERE article_id = #{articleId}")
    int deleteByArticleId(@Param("articleId") Long articleId);

    /**
     * 删除产品的所有文章关联
     */
    @Delete("DELETE FROM article_package WHERE product_id = #{productId}")
    int deleteByProductId(@Param("productId") Long productId);

    /**
     * 批量插入文章套餐关联
     */
    int batchInsert(@Param("list") List<ArticlePackage> list);
}
