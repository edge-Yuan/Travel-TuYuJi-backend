package com.wanderlust.travel.travelportal.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.travelportal.dto.ItineraryCancelDTO;
import com.wanderlust.travel.travelportal.dto.ItineraryModifyDTO;
import com.wanderlust.travel.travelportal.vo.ItineraryDetailVO;
import com.wanderlust.travel.travelportal.vo.ItineraryListVO;
import com.wanderlust.travel.travelportal.vo.RefundInfoVO;

import java.util.List;

/**
 * 行程管理服务接口
 * @author wanderlust
 */
public interface IItineraryManagementService {

    /**
     * 获取用户的行程列表
     * @param userId 用户ID
     * @param status 状态筛选：all-全部，upcoming-待出行，completed-已完成，cancelled-已取消
     * @param page 页码
     * @param size 每页大小
     * @return 行程列表
     */
    Page<ItineraryListVO> getUserItineraries(Long userId, String status, Integer page, Integer size);

    /**
     * 获取行程详情
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 行程详情
     */
    ItineraryDetailVO getItineraryDetail(Long orderId, Long userId);

    /**
     * 修改行程
     * @param modifyDTO 修改信息
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean modifyItinerary(ItineraryModifyDTO modifyDTO, Long userId);

    /**
     * 取消行程
     * @param cancelDTO 取消信息
     * @param userId 用户ID
     * @return 是否成功
     */
    Boolean cancelItinerary(ItineraryCancelDTO cancelDTO, Long userId);

    /**
     * 获取退款信息
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 退款信息
     */
    RefundInfoVO getRefundInfo(Long orderId, Long userId);

    /**
     * 检查是否可以修改行程
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 是否可以修改
     */
    Boolean canModifyItinerary(Long orderId, Long userId);

    /**
     * 检查是否可以取消行程
     * @param orderId 订单ID
     * @param userId 用户ID
     * @return 是否可以取消
     */
    Boolean canCancelItinerary(Long orderId, Long userId);

    /**
     * 根据订单状态同步行程记录
     * @param orderId 订单ID
     */
    void syncItineraryFromOrder(Long orderId);
}
