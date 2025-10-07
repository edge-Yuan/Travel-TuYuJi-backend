package com.wanderlust.travel.traveladmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.dto.GuideCooperationDTO;
import com.wanderlust.travel.traveladmin.dto.GuideSearchDTO;
import com.wanderlust.travel.traveladmin.vo.GuideCooperationVO;
import com.wanderlust.travel.traveladmin.vo.GuideVO;

import java.util.List;

/**
 * 导游合作服务接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
public interface GuideCooperationService {

    /**
     * 搜索导游
     * 
     * @param page 分页参数
     * @param searchDTO 搜索条件
     * @return 导游列表
     */
    IPage<GuideVO> searchGuides(Page<GuideVO> page, GuideSearchDTO searchDTO);

    /**
     * 获取导游详情
     * 
     * @param guideId 导游ID
     * @return 导游详情
     */
    GuideVO getGuideDetail(Long guideId);

    /**
     * 发送合作邀请
     * 
     * @param cooperationDTO 合作邀请DTO
     * @param merchantId 旅行商ID
     * @return 是否成功
     */
    boolean sendCooperationRequest(GuideCooperationDTO cooperationDTO, Long merchantId);

    /**
     * 分页查询合作邀请列表
     * 
     * @param page 分页参数
     * @param merchantId 旅行商ID
     * @param status 合作状态
     * @return 合作邀请列表
     */
    IPage<GuideCooperationVO> getCooperationList(Page<GuideCooperationVO> page, 
                                                Long merchantId, 
                                                Byte status);

    /**
     * 获取合作邀请详情
     * 
     * @param cooperationId 合作邀请ID
     * @return 合作邀请详情
     */
    GuideCooperationVO getCooperationDetail(Long cooperationId);

    /**
     * 取消合作邀请
     * 
     * @param cooperationId 合作邀请ID
     * @param merchantId 旅行商ID
     * @return 是否成功
     */
    boolean cancelCooperation(Long cooperationId, Long merchantId);

    /**
     * 获取合作邀请统计
     * 
     * @param merchantId 旅行商ID
     * @return 统计信息
     */
    GuideCooperationStatsVO getCooperationStats(Long merchantId);

    /**
     * 合作邀请统计VO
     */
    class GuideCooperationStatsVO {
        private Long totalInvitations;
        private Long pendingInvitations;
        private Long acceptedInvitations;
        private Long rejectedInvitations;
        private Long completedCooperations;

        // Getters and Setters
        public Long getTotalInvitations() { return totalInvitations; }
        public void setTotalInvitations(Long totalInvitations) { this.totalInvitations = totalInvitations; }
        public Long getPendingInvitations() { return pendingInvitations; }
        public void setPendingInvitations(Long pendingInvitations) { this.pendingInvitations = pendingInvitations; }
        public Long getAcceptedInvitations() { return acceptedInvitations; }
        public void setAcceptedInvitations(Long acceptedInvitations) { this.acceptedInvitations = acceptedInvitations; }
        public Long getRejectedInvitations() { return rejectedInvitations; }
        public void setRejectedInvitations(Long rejectedInvitations) { this.rejectedInvitations = rejectedInvitations; }
        public Long getCompletedCooperations() { return completedCooperations; }
        public void setCompletedCooperations(Long completedCooperations) { this.completedCooperations = completedCooperations; }
    }
}
