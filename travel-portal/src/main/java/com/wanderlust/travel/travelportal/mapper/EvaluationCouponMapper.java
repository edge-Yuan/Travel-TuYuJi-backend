package com.wanderlust.travel.travelportal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanderlust.travel.travelportal.entity.EvaluationCoupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 评价优惠券Mapper接口
 */
@Mapper
public interface EvaluationCouponMapper extends BaseMapper<EvaluationCoupon> {

    /**
     * 根据评价ID和用户ID查询优惠券
     */
    EvaluationCoupon selectByEvalIdAndUserId(@Param("evalId") Long evalId, @Param("userId") Long userId);

    /**
     * 根据优惠券编码查询
     */
    EvaluationCoupon selectByCouponCode(@Param("couponCode") String couponCode);
}
