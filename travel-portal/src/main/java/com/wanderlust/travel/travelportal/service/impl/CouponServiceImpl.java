package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.entity.Coupon;
import com.wanderlust.travel.travelportal.mapper.CouponMapper;
import com.wanderlust.travel.travelportal.service.ICouponService;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {
}


