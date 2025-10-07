package com.wanderlust.travel.traveladmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.entity.GuideCooperation;
import com.wanderlust.travel.traveladmin.vo.GuideCooperationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 导游合作邀请Mapper接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Mapper
public interface GuideCooperationMapper extends BaseMapper<GuideCooperation> {

    /**
     * 分页查询合作邀请列表
     * 
     * @param page 分页参数
     * @param merchantId 旅行商ID
     * @param status 合作状态
     * @return 合作邀请列表
     */
    IPage<GuideCooperationVO> selectCooperationPage(Page<GuideCooperationVO> page, 
                                                   @Param("merchantId") Long merchantId,
                                                   @Param("status") Byte status);

    /**
     * 根据ID查询合作邀请详情
     * 
     * @param cooperationId 合作邀请ID
     * @return 合作邀请详情
     */
    GuideCooperationVO selectCooperationDetail(@Param("cooperationId") Long cooperationId);

    /**
     * 查询旅行商发送的合作邀请数量
     * 
     * @param merchantId 旅行商ID
     * @param status 合作状态
     * @return 邀请数量
     */
    Long countCooperationByMerchant(@Param("merchantId") Long merchantId, 
                                   @Param("status") Byte status);
}
