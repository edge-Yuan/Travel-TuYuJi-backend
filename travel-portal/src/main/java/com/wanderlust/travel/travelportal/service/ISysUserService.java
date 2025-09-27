package com.wanderlust.travel.travelportal.service;

import com.wanderlust.travel.travelportal.dto.UserLoginDTO;
import com.wanderlust.travel.travelportal.dto.UserRegisterDTO;
import com.wanderlust.travel.travelportal.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser login(UserLoginDTO userLoginDTO);
    int Register(UserRegisterDTO userRegisterDTO);
    SysUser getUserByUserId(Long userId);
    // 批量查询用户
    List<SysUser> batchGetUserByIds(List<Long> userIds);

}
