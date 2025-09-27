package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.constant.JwtClaimsConstant;
import com.wanderlust.travel.travelportal.common.properties.JwtProperties;
import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.common.utils.JwtUtil;
import com.wanderlust.travel.travelportal.dto.UserLoginDTO;
import com.wanderlust.travel.travelportal.dto.UserRegisterDTO;
import com.wanderlust.travel.travelportal.entity.SysUser;
import com.wanderlust.travel.travelportal.service.ISysUserService;
import com.wanderlust.travel.travelportal.vo.UserLoginVO;
import com.wanderlust.travel.travelportal.vo.UserRegisterVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@RestController
@RequestMapping("/travel-portal/sysUser")
@Slf4j
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户登录：{}", userLoginDTO);

        SysUser sysUser = sysUserService.login(userLoginDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID,sysUser.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .userId(sysUser.getUserId())
                .username(sysUser.getUsername())
                .realName(sysUser.getRealName())
                .phone(sysUser.getPhone())
                .email(sysUser.getEmail())
                .status(sysUser.getStatus())
                .userRole(sysUser.getUserRole())
                .token(token)
                .build();

        return Result.success(userLoginVO);

    }

    @PostMapping("/register")
    public Result<UserRegisterVO> register(@RequestBody @Validated UserRegisterDTO userRegisterDTO) {
        log.info("用户注册", userRegisterDTO);
        int rows = sysUserService.Register(userRegisterDTO);
        if (rows == 1) {
            return Result.success(UserRegisterVO.builder()
                    .username(userRegisterDTO.getUsername())
                    .realName(userRegisterDTO.getRealName())
                    .phone(userRegisterDTO.getPhone())
                    .email(userRegisterDTO.getEmail())
                    .userRole(userRegisterDTO.getUserRole())
                    .createTime(LocalDateTime.now())
                    .build());
        }else{
            return Result.error("用户已存在");
        }
    }

    @GetMapping("/list")
    public Result<List<SysUser>> listAll() {
        List<SysUser> users = sysUserService.list();
        return Result.success(users);
    }

    @GetMapping("/{userId}")
    public Result<SysUser> getById(@PathVariable Long userId) {
        SysUser user = sysUserService.getById(userId);
        return user == null ? Result.error("用户不存在") : Result.success(user);
    }

    @PutMapping("/update")
    public Result<Boolean> updateUser(@RequestBody SysUser sysUser) {
        log.info("更新用户: {}", sysUser);
        boolean success = sysUserService.updateById(sysUser);
        return success ? Result.success(true) : Result.error("更新用户失败");
    }

    @DeleteMapping("/{userId}")
    public Result<Boolean> deleteUser(@PathVariable Long userId) {
        log.info("删除用户: {}", userId);
        boolean success = sysUserService.removeById(userId);
        return success ? Result.success(true) : Result.error("删除用户失败");
    }

    @GetMapping("/search")
    public Result<List<SysUser>> searchUsers(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer userRole,
            @RequestParam(required = false) Byte status) {
        List<SysUser> users = sysUserService.lambdaQuery()
                .like(keyword != null, SysUser::getUsername, keyword)
                .or()
                .like(keyword != null, SysUser::getRealName, keyword)
                .or()
                .like(keyword != null, SysUser::getPhone, keyword)
                .eq(userRole != null, SysUser::getUserRole, userRole)
                .eq(status != null, SysUser::getStatus, status)
                .list();
        return Result.success(users);
    }

    @PutMapping("/updateStatus/{userId}")
    public Result<Boolean> updateUserStatus(@PathVariable Long userId, @RequestParam Byte status) {
        SysUser user = sysUserService.getById(userId);
        if (user == null) return Result.error("用户不存在");
        user.setStatus(status);
        boolean success = sysUserService.updateById(user);
        return success ? Result.success(true) : Result.error("更新用户状态失败");
    }

    @GetMapping("/profile/{userId}")
    public Result<SysUser> getUserProfile(@PathVariable Long userId) {
        SysUser user = sysUserService.getById(userId);
        if (user == null) return Result.error("用户不存在");
        // 隐藏敏感信息
        user.setPassword(null);
        return Result.success(user);
    }

    @PutMapping("/updateProfile")
    public Result<Boolean> updateUserProfile(@RequestBody SysUser sysUser) {
        log.info("更新用户资料: {}", sysUser);
        // 不允许通过此接口更新密码和状态
        sysUser.setPassword(null);
        sysUser.setStatus(null);
        boolean success = sysUserService.updateById(sysUser);
        return success ? Result.success(true) : Result.error("更新用户资料失败");
    }

    @GetMapping("/statistics/{userId}")
    public Result<Map<String, Object>> getUserStatistics(@PathVariable Long userId) {
        // 这里可以添加用户统计信息，如订单数、收藏数等
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("userId", userId);
        statistics.put("joinTime", sysUserService.getById(userId).getCreateTime());
        return Result.success(statistics);
    }

}
