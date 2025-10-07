package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.entity.EvaluationCoupon;
import com.wanderlust.travel.travelportal.entity.TourEvaluation;
import com.wanderlust.travel.travelportal.mapper.EvaluationCouponMapper;
import com.wanderlust.travel.travelportal.service.IEvaluationCouponService;
import com.wanderlust.travel.travelportal.service.ITourEvaluationService;
import com.wanderlust.travel.travelportal.vo.EvaluationCouponVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 评价优惠券服务实现类
 */
@Service
@Slf4j
public class EvaluationCouponServiceImpl extends ServiceImpl<EvaluationCouponMapper, EvaluationCoupon> implements IEvaluationCouponService {

    @Autowired
    private ITourEvaluationService tourEvaluationService;

    @Override
    @Transactional
    public EvaluationCouponVO claimCoupon(Long evalId, Long userId) {
        try {
            log.info("领取优惠券: evalId={}, userId={}", evalId, userId);

            // 检查评价是否存在
            TourEvaluation evaluation = tourEvaluationService.getById(evalId);
            if (evaluation == null) {
                throw new RuntimeException("评价不存在");
            }

            // 检查是否是评价作者
            if (!evaluation.getUserId().equals(userId)) {
                throw new RuntimeException("只有评价作者才能领取优惠券");
            }

            // 检查是否已领取
            LambdaQueryWrapper<EvaluationCoupon> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(EvaluationCoupon::getEvalId, evalId)
                       .eq(EvaluationCoupon::getUserId, userId);
            EvaluationCoupon existing = getOne(queryWrapper);

            if (existing != null) {
                throw new RuntimeException("优惠券已领取");
            }

            // 生成优惠券编码
            String couponCode = generateCouponCode();

            // 创建优惠券
            EvaluationCoupon coupon = new EvaluationCoupon();
            coupon.setEvalId(evalId);
            coupon.setUserId(userId);
            coupon.setCouponCode(couponCode);
            coupon.setAmount(new BigDecimal("20.00"));
            coupon.setStatus("active");
            coupon.setClaimTime(LocalDateTime.now());
            coupon.setExpireTime(LocalDateTime.now().plusDays(30));
            coupon.setCreatedTime(LocalDateTime.now());
            coupon.setUpdatedTime(LocalDateTime.now());

            save(coupon);

            log.info("优惠券领取成功: couponId={}, couponCode={}", coupon.getCouponId(), couponCode);

            // 转换为VO
            EvaluationCouponVO vo = new EvaluationCouponVO();
            BeanUtils.copyProperties(coupon, vo);
            return vo;

        } catch (Exception e) {
            log.error("领取优惠券失败", e);
            throw new RuntimeException("操作失败: " + e.getMessage());
        }
    }

    @Override
    public Map<String, Object> checkClaimStatus(Long evalId, Long userId) {
        try {
            log.info("检查优惠券领取状态: evalId={}, userId={}", evalId, userId);

            // 检查评价是否存在
            TourEvaluation evaluation = tourEvaluationService.getById(evalId);
            if (evaluation == null) {
                throw new RuntimeException("评价不存在");
            }

            // 检查是否是评价作者
            boolean canClaim = evaluation.getUserId().equals(userId);

            // 查询优惠券记录
            LambdaQueryWrapper<EvaluationCoupon> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(EvaluationCoupon::getEvalId, evalId)
                       .eq(EvaluationCoupon::getUserId, userId);
            EvaluationCoupon coupon = getOne(queryWrapper);

            Map<String, Object> result = new HashMap<>();
            result.put("canClaim", canClaim && coupon == null);
            result.put("claimed", coupon != null);
            result.put("couponId", coupon != null ? coupon.getCouponId() : null);
            result.put("claimTime", coupon != null ? coupon.getClaimTime() : null);

            return result;

        } catch (Exception e) {
            log.error("检查优惠券领取状态失败", e);
            Map<String, Object> result = new HashMap<>();
            result.put("canClaim", false);
            result.put("claimed", false);
            result.put("couponId", null);
            result.put("claimTime", null);
            return result;
        }
    }

    /**
     * 生成优惠券编码
     */
    private String generateCouponCode() {
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomStr = String.format("%03d", new Random().nextInt(1000));
        return "EVAL" + dateStr + randomStr;
    }
}
