package com.wanderlust.travel.travelportal.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.dto.ItineraryCancelDTO;
import com.wanderlust.travel.travelportal.dto.ItineraryModifyDTO;
import com.wanderlust.travel.travelportal.service.IItineraryManagementService;
import com.wanderlust.travel.travelportal.vo.ItineraryDetailVO;
import com.wanderlust.travel.travelportal.vo.ItineraryListVO;
import com.wanderlust.travel.travelportal.vo.RefundInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 行程管理控制器
 * @author wanderlust
 */
@RestController
@RequestMapping("/travel-portal/itinerary")
@Api(tags = "行程管理")
@Slf4j
public class ItineraryManagementController {

    @Autowired
    private IItineraryManagementService itineraryManagementService;

    @GetMapping("/list")
    @ApiOperation("获取用户行程列表")
    public Result<Page<ItineraryListVO>> getUserItineraries(
            @ApiParam("用户ID") @RequestParam(defaultValue = "1") Long userId,
            @ApiParam("状态筛选：all-全部，upcoming-待出行，completed-已完成，cancelled-已取消") 
            @RequestParam(defaultValue = "all") String status,
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer page,
            @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size) {
        
        log.info("获取用户行程列表，用户ID：{}，状态：{}，页码：{}，每页大小：{}", userId, status, page, size);
        
        try {
            Page<ItineraryListVO> result = itineraryManagementService.getUserItineraries(userId, status, page, size);
            return Result.success(result);
        } catch (Exception e) {
            log.error("获取用户行程列表失败", e);
            return Result.error("获取行程列表失败：" + e.getMessage());
        }
    }

    @GetMapping("/detail/{orderId}")
    @ApiOperation("获取行程详情")
    public Result<ItineraryDetailVO> getItineraryDetail(
            @ApiParam("订单ID") @PathVariable Long orderId,
            @ApiParam("用户ID") @RequestParam(defaultValue = "1") Long userId) {
        
        log.info("获取行程详情，订单ID：{}，用户ID：{}", orderId, userId);
        
        try {
            ItineraryDetailVO result = itineraryManagementService.getItineraryDetail(orderId, userId);
            if (result == null) {
                return Result.error("行程不存在或无权限访问");
            }
            return Result.success(result);
        } catch (Exception e) {
            log.error("获取行程详情失败", e);
            return Result.error("获取行程详情失败：" + e.getMessage());
        }
    }

    @PostMapping("/modify")
    @ApiOperation("修改行程")
    public Result<String> modifyItinerary(
            @ApiParam("修改信息") @RequestBody @Validated ItineraryModifyDTO modifyDTO,
            @ApiParam("用户ID") @RequestParam(defaultValue = "1") Long userId) {
        
        log.info("修改行程，订单ID：{}，用户ID：{}", modifyDTO.getOrderId(), userId);
        
        try {
            Boolean result = itineraryManagementService.modifyItinerary(modifyDTO, userId);
            if (result) {
                return Result.success( "行程修改请求已提交，将在导游确认后生效");
            } else {
                return Result.error("行程修改失败");
            }
        } catch (Exception e) {
            log.error("修改行程失败", e);
            return Result.error("修改行程失败：" + e.getMessage());
        }
    }

    @PostMapping("/cancel")
    @ApiOperation("取消行程")
    public Result<String> cancelItinerary(
            @ApiParam("取消信息") @RequestBody @Validated ItineraryCancelDTO cancelDTO,
            @ApiParam("用户ID") @RequestParam(defaultValue = "1") Long userId) {
        
        log.info("取消行程，订单ID：{}，用户ID：{}", cancelDTO.getOrderId(), userId);
        
        try {
            Boolean result = itineraryManagementService.cancelItinerary(cancelDTO, userId);
            if (result) {
                return Result.success( "行程已取消");
            } else {
                return Result.error("取消行程失败");
            }
        } catch (Exception e) {
            log.error("取消行程失败", e);
            return Result.error("取消行程失败：" + e.getMessage());
        }
    }

    @GetMapping("/refund-info/{orderId}")
    @ApiOperation("获取退款信息")
    public Result<RefundInfoVO> getRefundInfo(
            @ApiParam("订单ID") @PathVariable Long orderId,
            @ApiParam("用户ID") @RequestParam(defaultValue = "1") Long userId) {
        
        log.info("获取退款信息，订单ID：{}，用户ID：{}", orderId, userId);
        
        try {
            RefundInfoVO result = itineraryManagementService.getRefundInfo(orderId, userId);
            if (result == null) {
                return Result.error("订单不存在或无权限访问");
            }
            return Result.success(result);
        } catch (Exception e) {
            log.error("获取退款信息失败", e);
            return Result.error("获取退款信息失败：" + e.getMessage());
        }
    }

    @GetMapping("/can-modify/{orderId}")
    @ApiOperation("检查是否可以修改行程")
    public Result<Boolean> canModifyItinerary(
            @ApiParam("订单ID") @PathVariable Long orderId,
            @ApiParam("用户ID") @RequestParam(defaultValue = "1") Long userId) {
        
        log.info("检查是否可以修改行程，订单ID：{}，用户ID：{}", orderId, userId);
        
        try {
            Boolean result = itineraryManagementService.canModifyItinerary(orderId, userId);
            return Result.success(result);
        } catch (Exception e) {
            log.error("检查是否可以修改行程失败", e);
            return Result.error("检查失败：" + e.getMessage());
        }
    }

    @GetMapping("/can-cancel/{orderId}")
    @ApiOperation("检查是否可以取消行程")
    public Result<Boolean> canCancelItinerary(
            @ApiParam("订单ID") @PathVariable Long orderId,
            @ApiParam("用户ID") @RequestParam(defaultValue = "1") Long userId) {
        
        log.info("检查是否可以取消行程，订单ID：{}，用户ID：{}", orderId, userId);
        
        try {
            Boolean result = itineraryManagementService.canCancelItinerary(orderId, userId);
            return Result.success(result);
        } catch (Exception e) {
            log.error("检查是否可以取消行程失败", e);
            return Result.error("检查失败：" + e.getMessage());
        }
    }
}
