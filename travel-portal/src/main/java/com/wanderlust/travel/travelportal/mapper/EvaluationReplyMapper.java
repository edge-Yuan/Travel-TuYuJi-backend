package com.wanderlust.travel.travelportal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.travelportal.entity.EvaluationReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评价回复Mapper接口
 */
@Mapper
public interface EvaluationReplyMapper extends BaseMapper<EvaluationReply> {

    /**
     * 根据评价ID获取回复列表
     */
    List<EvaluationReply> selectByEvalId(@Param("evalId") Long evalId);

    /**
     * 根据评价ID删除回复
     */
    int deleteByEvalId(@Param("evalId") Long evalId);
}
