package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.UserAddress;
import com.wanderlust.travel.travelportal.service.IUserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户收货地址表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-01-15
 */
@RestController
@RequestMapping("/travel-portal/userAddress")
@Slf4j
public class UserAddressController {

    @Autowired
    private IUserAddressService userAddressService;

    @GetMapping("/list")
    public Result<List<UserAddress>> listAll() {
        List<UserAddress> addresses = userAddressService.list();
        return Result.success(addresses);
    }

    @GetMapping("/user/{userId}")
    public Result<List<UserAddress>> getAddressesByUser(@PathVariable Long userId) {
        List<UserAddress> addresses = userAddressService.lambdaQuery()
            .eq(UserAddress::getUserId, userId)
            .orderByDesc(UserAddress::getIsDefault)
            .orderByDesc(UserAddress::getCreateTime)
            .list();
        return Result.success(addresses);
    }

    @GetMapping("/{id}")
    public Result<UserAddress> getById(@PathVariable Long id) {
        UserAddress address = userAddressService.getById(id);
        return address == null ? Result.error("地址不存在") : Result.success(address);
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody UserAddress userAddress) {
        log.info("创建地址: {}", userAddress);
        boolean success = userAddressService.save(userAddress);
        return success ? Result.success(true) : Result.error("创建地址失败");
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody UserAddress userAddress) {
        log.info("更新地址: {}", userAddress);
        boolean success = userAddressService.updateById(userAddress);
        return success ? Result.success(true) : Result.error("更新地址失败");
    }

    @PutMapping("/setDefault/{id}")
    public Result<Boolean> setDefault(@PathVariable Long id) {
        UserAddress address = userAddressService.getById(id);
        if (address == null) return Result.error("地址不存在");
        
        // 先将该用户的所有地址设为非默认
        userAddressService.lambdaUpdate()
            .eq(UserAddress::getUserId, address.getUserId())
            .set(UserAddress::getIsDefault, false)
            .update();
        
        // 设置当前地址为默认
        address.setIsDefault(true);
        boolean success = userAddressService.updateById(address);
        return success ? Result.success(true) : Result.error("设置默认地址失败");
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        log.info("删除地址: {}", id);
        boolean success = userAddressService.removeById(id);
        return success ? Result.success(true) : Result.error("删除地址失败");
    }
}
