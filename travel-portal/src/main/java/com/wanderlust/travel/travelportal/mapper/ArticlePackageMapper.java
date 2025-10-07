package com.wanderlust.travel.travelportal.mapper;

import com.wanderlust.travel.travelportal.entity.ArticlePackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章关联套餐表 Mapper 接口
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
public interface ArticlePackageMapper extends BaseMapper<ArticlePackage> {

    /**
     * 根据文章ID获取关联套餐列表
     */
    @Select("SELECT ap.rel_id as relId, ap.article_id as articleId, ap.product_id as productId, " +
            "ap.package_name as packageName, ap.package_image as packageImage, ap.package_price as packagePrice, " +
            "ap.sort_order as sortOrder, tp.product_name as productName, tp.main_img_url as mainImgUrl, tp.price " +
            "FROM article_package ap " +
            "LEFT JOIN tour_product tp ON ap.product_id = tp.product_id " +
            "WHERE ap.article_id = #{articleId} " +
            "ORDER BY ap.sort_order ASC")
    List<Map<String, Object>> selectPackagesByArticleId(@Param("articleId") Long articleId);
}
