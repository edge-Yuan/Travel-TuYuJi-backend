package com.wanderlust.travel.travelportal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.travelportal.entity.EvaluationUseful;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评价有用Mapper接口
 */
@Mapper
public interface EvaluationUsefulMapper extends BaseMapper<EvaluationUseful> {

    /**
     * 根据评价ID和用户ID查询有用记录
     */
    EvaluationUseful selectByEvalIdAndUserId(@Param("evalId") Long evalId, @Param("userId") Long userId);

    /**
     * 根据评价ID统计有用数量
     */
    int countByEvalId(@Param("evalId") Long evalId);

    /**
     * 根据评价ID获取有用用户列表（分页）
     */
    Page<EvaluationUseful> selectUsefulUsersByEvalId(Page<EvaluationUseful> page, @Param("evalId") Long evalId);

    /**
     * 根据评价ID获取有用用户列表
     */
    List<EvaluationUseful> selectUsefulUsersByEvalId(@Param("evalId") Long evalId);
}
