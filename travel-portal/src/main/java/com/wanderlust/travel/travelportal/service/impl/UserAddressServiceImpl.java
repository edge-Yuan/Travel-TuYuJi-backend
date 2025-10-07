package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.entity.UserAddress;
import com.wanderlust.travel.travelportal.mapper.UserAddressMapper;
import com.wanderlust.travel.travelportal.service.IUserAddressService;
import org.springframework.stereotype.Service;

@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {
}


