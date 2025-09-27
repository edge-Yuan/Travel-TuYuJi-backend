package com.wanderlust.travel.travelportal.service.impl;

import com.wanderlust.travel.travelportal.entity.UserCollection;
import com.wanderlust.travel.travelportal.mapper.UserCollectionMapper;
import com.wanderlust.travel.travelportal.service.IUserCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收藏表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@Service
public class UserCollectionServiceImpl extends ServiceImpl<UserCollectionMapper, UserCollection> implements IUserCollectionService {

}
