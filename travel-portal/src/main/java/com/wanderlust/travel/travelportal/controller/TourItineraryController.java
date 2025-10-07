package com.wanderlust.travel.travelportal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.GuideExtend;
import com.wanderlust.travel.travelportal.entity.SysUser;
import com.wanderlust.travel.travelportal.entity.TourItinerary;
import com.wanderlust.travel.travelportal.entity.TourOrder;
import com.wanderlust.travel.travelportal.mapper.GuideExtendMapper;
import com.wanderlust.travel.travelportal.mapper.SysUserMapper;
import com.wanderlust.travel.travelportal.service.IGuideExtendService;
import com.wanderlust.travel.travelportal.service.ISysUserService;
import com.wanderlust.travel.travelportal.service.ITourItineraryService;
import com.wanderlust.travel.travelportal.service.ITourOrderService;
import com.wanderlust.travel.travelportal.vo.TourItinerarysScanVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 行程安排表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@RestController
@RequestMapping("/travel-portal/tourItinerary")
@Slf4j
public class TourItineraryController {
    @Autowired
    private ITourItineraryService tourItineraryService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ITourOrderService tourOrderService;
    @Autowired
    private IGuideExtendService guideExtendService;

    @GetMapping("/getAllTourItinerarysScan")
//    public Result<List<TourItinerarysScanVO>> getAllTourItinerarysScan() {
//// 1. 查询所有行程
//        List<TourItinerary> tourItinerarys = tourItineraryService.getAllItinerarys();
//        // 2. 从行程中提取导游ID（去重处理避免重复查询）
//        Set<Long> guideIdSet = new HashSet<>();
//        if (!CollectionUtils.isEmpty(tourItinerarys)) {
//            for (TourItinerary itinerary : tourItinerarys) {
//                // 假设TourItinerary中有getGuideId()方法获取导游ID
//                Long guideId = itinerary.getGuideId();
//                if (guideId != null) {
//                    guideIdSet.add(guideId);
//                }
//            }
//        }
//
//        // 3. 批量查询导游信息（转换为List便于传递）
//        List<GuideExtend> guideExtends = Collections.emptyList();
//        if (!guideIdSet.isEmpty()) {
//            List<Long> guideIds = new ArrayList<>(guideIdSet);
//            guideExtends = guideExtendService.batchFindGuideExtendByGuideIds(guideIds);
//        }
//
//        Set<Long> userIdSet = new HashSet<>();
//        if (!CollectionUtils.isEmpty(guideExtends)) {
//            for (GuideExtend guideExtend : guideExtends) {
//                if (guideExtend != null) {
//                    Long userId = guideExtend.getUserId();
//                    if (userId != null) {
//                        userIdSet.add(userId); // 自动去重
//                    }
//                }
//            }
//        }
//
//        List<Long> userIds = new ArrayList<>(userIdSet);
//        List<SysUser> sysUsers = sysUserService.batchGetUserByIds(userIds);
//        Set<String> guideNames = new HashSet<>();
//        if(!CollectionUtils.isEmpty(sysUsers)){
//            for(SysUser sysUser:sysUsers){
//                if(sysUser != null){
//                    String guideName = sysUser.getRealName();
//                    if(guideName != null){
//                        guideNames.add(guideName);
//                    }
//                }
//            }
//        }
//
//        List<String> guideNameLists = new ArrayList<>(guideNames);
//        Set<Long> orderIdSet = new HashSet<>();
//        if (!CollectionUtils.isEmpty(tourItinerarys)) {
//            for (TourItinerary itinerary : tourItinerarys) {
//                Long orderId = itinerary.getOrderId();
//                if (orderId != null) {
//                    orderIdSet.add(orderId);
//                }
//            }
//        }
//        List<Long> orderIds = new ArrayList<>(orderIdSet);
//        List<TourOrder> tourOrders = tourOrderService.batchFindTourOrdersByOrderIds(orderIds);
//        Set<BigDecimal> orderAmounts = new HashSet<>();
//        if (!CollectionUtils.isEmpty(tourOrders)) {
//            for (TourOrder tourOrder : tourOrders) {
//                BigDecimal orderAmount = tourOrder.getOrderAmount();
//                if (orderAmount != null) {
//                    orderAmounts.add(orderAmount);
//                }
//            }
//        }
//        List<BigDecimal> orderAmountList = new ArrayList<>(orderAmounts);
//
//        List<TourItinerarysScanVO> resuliList =
//    }
    public Result<List<TourItinerarysScanVO>> getAllTourItinerarysScan() {
        // 1. 查询所有行程
        List<TourItinerary> tourItinerarys = tourItineraryService.getAllItinerarys();
        if (CollectionUtils.isEmpty(tourItinerarys)) {
            return Result.success(Collections.emptyList());
        }

        // 2. 提取导游ID并批量查询导游信息
        Set<Long> guideIdSet = tourItinerarys.stream()
                .map(TourItinerary::getGuideId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Long, GuideExtend> guideExtendMap = new HashMap<>();
        if (!guideIdSet.isEmpty()) {
            List<GuideExtend> guideExtends = guideExtendService.batchFindGuideExtendByGuideIds(new ArrayList<>(guideIdSet));
            if (!CollectionUtils.isEmpty(guideExtends)) {
                guideExtendMap = guideExtends.stream()
                        .collect(Collectors.toMap(GuideExtend::getGuideId, Function.identity()));
            }
        }

        // 3. 提取用户ID并批量查询用户信息
        Set<Long> userIdSet = guideExtendMap.values().stream()
                .map(GuideExtend::getUserId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Long, SysUser> sysUserMap = new HashMap<>();
        if (!userIdSet.isEmpty()) {
            List<SysUser> sysUsers = sysUserService.batchGetUserByIds(new ArrayList<>(userIdSet));
            if (!CollectionUtils.isEmpty(sysUsers)) {
                sysUserMap = sysUsers.stream()
                        .collect(Collectors.toMap(SysUser::getUserId, Function.identity()));
            }
        }

        // 4. 提取订单ID并批量查询订单信息
        Set<Long> orderIdSet = tourItinerarys.stream()
                .map(TourItinerary::getOrderId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Long, TourOrder> tourOrderMap = new HashMap<>();
        if (!orderIdSet.isEmpty()) {
            List<TourOrder> tourOrders = tourOrderService.batchFindTourOrdersByOrderIds(new ArrayList<>(orderIdSet));
            if (!CollectionUtils.isEmpty(tourOrders)) {
                tourOrderMap = tourOrders.stream()
                        .collect(Collectors.toMap(TourOrder::getOrderId, Function.identity()));
            }
        }

        // 5. 组装VO列表
        List<TourItinerarysScanVO> resultList = new ArrayList<>();
        for (TourItinerary itinerary : tourItinerarys) {
            TourItinerarysScanVO scanVO = new TourItinerarysScanVO();

            // 设置行程基本信息
            scanVO.setItineraryDate(itinerary.getItineraryDate());
            scanVO.setDays(itinerary.getDaySeq());


            // 设置导游信息
            GuideExtend guideExtend = guideExtendMap.get(itinerary.getGuideId());
            if (guideExtend != null) {
                scanVO.setGuideId(guideExtend.getGuideId());
                // 设置导游用户信息
                SysUser sysUser = sysUserMap.get(guideExtend.getUserId());
                if (sysUser != null) {
                    scanVO.setGuideName(sysUser.getRealName());
                }
            }

            // 设置订单信息
            TourOrder tourOrder = tourOrderMap.get(itinerary.getOrderId());
            if (tourOrder != null) {
                scanVO.setOrderAmount(tourOrder.getTotalPrice());
            }

            resultList.add(scanVO);
        }

        return Result.success(resultList);
    }

    @GetMapping("/user/{userId}")
    public Result<List<TourItinerary>> getItinerariesByUser(@PathVariable Long userId) {
        // 先通过订单获取用户的行程
        List<TourOrder> userOrders = tourOrderService.lambdaQuery()
            .eq(TourOrder::getUserId, userId)
            .list();
        
        if (userOrders.isEmpty()) {
            return Result.success(Collections.emptyList());
        }
        
        List<Long> orderIds = userOrders.stream()
            .map(TourOrder::getOrderId)
            .collect(Collectors.toList());
        
        List<TourItinerary> itineraries = tourItineraryService.lambdaQuery()
            .in(TourItinerary::getOrderId, orderIds)
            .orderByDesc(TourItinerary::getItineraryDate)
            .list();
        return Result.success(itineraries);
    }

    @GetMapping("/guide/{guideId}")
    public Result<List<TourItinerary>> getItinerariesByGuide(@PathVariable Long guideId) {
        List<TourItinerary> itineraries = tourItineraryService.lambdaQuery()
            .eq(TourItinerary::getGuideId, guideId)
            .orderByDesc(TourItinerary::getItineraryDate)
            .list();
        return Result.success(itineraries);
    }

    @PostMapping("/create")
    public Result<Boolean> createItinerary(@RequestBody TourItinerary itinerary) {
        log.info("创建行程: {}", itinerary);
        boolean success = tourItineraryService.save(itinerary);
        return success ? Result.success(true) : Result.error("创建行程失败");
    }

    @PutMapping("/update")
    public Result<Boolean> updateItinerary(@RequestBody TourItinerary itinerary) {
        log.info("更新行程: {}", itinerary);
        boolean success = tourItineraryService.updateById(itinerary);
        return success ? Result.success(true) : Result.error("更新行程失败");
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteItinerary(@PathVariable Long id) {
        log.info("删除行程: {}", id);
        boolean success = tourItineraryService.removeById(id);
        return success ? Result.success(true) : Result.error("删除行程失败");
    }

    @GetMapping("/{id}")
    public Result<TourItinerary> getItineraryById(@PathVariable Long id) {
        TourItinerary itinerary = tourItineraryService.getById(id);
        return itinerary == null ? Result.error("行程不存在") : Result.success(itinerary);
    }

    /**
     * 获取用户行程数量
     */
    @GetMapping("/travelRoute/user/{userId}/count")
    public Result<Long> getUserItineraryCount(@PathVariable Long userId) {
        try {
            log.info("获取用户行程数量: userId={}", userId);
            
            // 先通过订单获取用户的行程数量
            long count = tourItineraryService.lambdaQuery()
                .eq(TourItinerary::getUserId, userId)
                .count();
            
            log.info("用户 {} 的行程数量: {}", userId, count);
            return Result.success(count);
        } catch (Exception e) {
            log.error("获取用户行程数量失败: userId={}", userId, e);
            return Result.error("获取行程数量失败：" + e.getMessage());
        }
    }

}
