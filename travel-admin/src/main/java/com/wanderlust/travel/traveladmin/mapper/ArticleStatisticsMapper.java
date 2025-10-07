package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.traveladmin.entity.ArticleStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 文章统计数据表 Mapper 接口
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Mapper
public interface ArticleStatisticsMapper extends BaseMapper<ArticleStatistics> {

    /**
     * 获取指定日期的统计数据
     */
    @Select("SELECT * FROM article_statistics WHERE stat_date = #{statDate}")
    ArticleStatistics selectByDate(@Param("statDate") LocalDate statDate);

    /**
     * 获取最近N天的统计数据
     */
    @Select("SELECT * FROM article_statistics ORDER BY stat_date DESC LIMIT #{days}")
    List<ArticleStatistics> selectRecentStatistics(@Param("days") Integer days);

    /**
     * 获取指定日期范围的统计数据
     */
    @Select("SELECT * FROM article_statistics WHERE stat_date BETWEEN #{startDate} AND #{endDate} ORDER BY stat_date ASC")
    List<ArticleStatistics> selectByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
