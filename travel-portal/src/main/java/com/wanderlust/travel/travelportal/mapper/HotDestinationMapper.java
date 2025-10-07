package com.wanderlust.travel.travelportal.mapper;

import com.wanderlust.travel.travelportal.entity.HotDestination;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 热门目的地表 Mapper 接口
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
public interface HotDestinationMapper extends BaseMapper<HotDestination> {

    /**
     * 获取热门目的地列表
     */
    @Select("SELECT * FROM hot_destination " +
            "WHERE status = 1 " +
            "ORDER BY sort_order ASC, view_count DESC " +
            "LIMIT #{limit}")
    List<HotDestination> selectHotDestinations(@Param("limit") Integer limit);

    /**
     * 增加目的地浏览次数
     */
    @Select("UPDATE hot_destination SET view_count = view_count + 1 WHERE dest_id = #{destId}")
    void incrementViewCount(@Param("destId") Long destId);
}
