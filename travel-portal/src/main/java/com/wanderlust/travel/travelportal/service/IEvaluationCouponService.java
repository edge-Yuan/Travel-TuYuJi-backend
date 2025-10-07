package com.wanderlust.travel.travelportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.travelportal.entity.EvaluationCoupon;
import com.wanderlust.travel.travelportal.vo.EvaluationCouponVO;

import java.util.Map;

/**
 * 评价优惠券服务接口
 */
public interface IEvaluationCouponService extends IService<EvaluationCoupon> {

    /**
     * 领取优惠券
     */
    EvaluationCouponVO claimCoupon(Long evalId, Long userId);

    /**
     * 检查优惠券领取状态
     */
    Map<String, Object> checkClaimStatus(Long evalId, Long userId);
}
