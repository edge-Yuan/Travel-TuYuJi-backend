package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.traveladmin.entity.ArticleTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 文章模板表 Mapper 接口
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Mapper
public interface ArticleTemplateMapper extends BaseMapper<ArticleTemplate> {

    /**
     * 根据分类获取启用的模板列表
     */
    @Select("SELECT * FROM article_template WHERE is_active = 1 AND category = #{category} ORDER BY create_time DESC")
    List<ArticleTemplate> selectByCategory(@Param("category") String category);

    /**
     * 获取所有启用的模板列表
     */
    @Select("SELECT * FROM article_template WHERE is_active = 1 ORDER BY create_time DESC")
    List<ArticleTemplate> selectActiveTemplates();
}
