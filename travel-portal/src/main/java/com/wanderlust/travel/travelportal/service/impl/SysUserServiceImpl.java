package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanderlust.travel.travelportal.common.constant.MessageConstant;
import com.wanderlust.travel.travelportal.common.constant.StatusConstant;
import com.wanderlust.travel.travelportal.common.exception.AccountNotFoundException;
import com.wanderlust.travel.travelportal.common.utils.MD5Util;
import com.wanderlust.travel.travelportal.dto.UserLoginDTO;
import com.wanderlust.travel.travelportal.dto.UserRegisterDTO;
import com.wanderlust.travel.travelportal.entity.GuideExtend;
import com.wanderlust.travel.travelportal.entity.SysUser;
import com.wanderlust.travel.travelportal.mapper.GuideExtendMapper;
import com.wanderlust.travel.travelportal.mapper.SysUserMapper;
import com.wanderlust.travel.travelportal.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        // 根据用户名查询用户
        SysUser sysUser = getBaseMapper().selectOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SysUser>().eq("username", username));

        if(sysUser == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);

        }

        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!sysUser.getPassword().equals(password)){
            throw new AccountNotFoundException(MessageConstant.PASSWORD_ERROR);
        }
        if(Objects.equals(sysUser.getStatus(), StatusConstant.DISABLE)){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_LOCKED);
        }
        return sysUser;
    }

    public int Register(UserRegisterDTO userRegisterDTO) {
        String username = userRegisterDTO.getUsername();
        String password = userRegisterDTO.getPassword(); // 前端传入的明文密码
        String realName = userRegisterDTO.getRealName();
        String phone = userRegisterDTO.getPhone();
        String email = userRegisterDTO.getEmail();
        int userRole = userRegisterDTO.getUserRole();

        // 对明文密码进行MD5加密（无盐值）
        String encryptedPassword = MD5Util.encrypt(password);

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(encryptedPassword); // 存储加密后的密码
        sysUser.setRealName(realName);
        sysUser.setPhone(phone);
        sysUser.setEmail(email);
        sysUser.setUserRole(userRole);

        int rows = getBaseMapper().insert(sysUser);
        return rows;
    }

//    public List<SysUser> getGuideUsersByGuideIds(List<Long> guideIds) {
//        // 1. 先通过多个guideId批量查询关联的userId
//        List<GuideExtend> guideExtends = guideExtendMapper.selectList(
//                new QueryWrapper<GuideExtend>()
//                        .in("guide_id", guideIds)  // 用in条件匹配多个guideId
//                        .select("user_id")         // 只查询需要的userId字段
//        );
//
//        // 2. 提取所有userId并去重
//        List<Long> userIds = guideExtends.stream()
//                .map(GuideExtend::getUserId)
//                .filter(Objects::nonNull)
//                .distinct()
//                .collect(Collectors.toList());
//
//        // 3. 如果没有匹配的userId，直接返回空列表
//        if (userIds.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        // 4. 通过userId批量查询用户信息
//        return sysUserMapper.selectList(
//                new QueryWrapper<SysUser>()
//                        .in("user_id", userIds)  // 用in条件匹配多个userId
//        );
    public SysUser getUserByUserId(Long userId) {
        return sysUserMapper.selectOne(
                new QueryWrapper<SysUser>()
                        .eq("user_id", userId)
        );
    }

    public List<SysUser> batchGetUserByIds(List<Long> userIds) {
        return sysUserMapper.selectList(
                new QueryWrapper<SysUser>()
                        .in("user_id", userIds)
        );
    }
}
