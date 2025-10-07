package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.travelportal.dto.ItineraryCancelDTO;
import com.wanderlust.travel.travelportal.dto.ItineraryModifyDTO;
import com.wanderlust.travel.travelportal.entity.*;
import com.wanderlust.travel.travelportal.mapper.*;
import com.wanderlust.travel.travelportal.entity.TourItinerary;
import com.wanderlust.travel.travelportal.service.IItineraryManagementService;
import com.wanderlust.travel.travelportal.vo.ItineraryDetailVO;
import com.wanderlust.travel.travelportal.vo.ItineraryListVO;
import com.wanderlust.travel.travelportal.vo.RefundInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 行程管理服务实现类
 * @author wanderlust
 */
@Service
@Slf4j
public class ItineraryManagementServiceImpl implements IItineraryManagementService {

    @Autowired
    private TourOrderMapper tourOrderMapper;

    @Autowired
    private TourProductMapper tourProductMapper;

    @Autowired
    private GuideExtendMapper guideExtendMapper;

    @Autowired
    private TourItineraryMapper tourItineraryMapper;

    @Autowired
    private ProductDailyItineraryMapper productDailyItineraryMapper;

    @Autowired
    private OrderStatusLogMapper orderStatusLogMapper;

    @Override
    public Page<ItineraryListVO> getUserItineraries(Long userId, String status, Integer page, Integer size) {
        Page<TourItinerary> itineraryPage = new Page<>(page, size);
        
        LambdaQueryWrapper<TourItinerary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TourItinerary::getUserId, userId);
        
        // 根据状态筛选
        if (!"all".equals(status)) {
            switch (status) {
                case "upcoming":
                    // 待出行：订单已确认(1) 且 预订日期大于今天
                    queryWrapper.eq(TourItinerary::getOrderStatus, 1) // 已确认
                               .gt(TourItinerary::getBookingDate, LocalDate.now());
                    break;
                case "completed":
                    // 已完成：订单已完成(2) 且 预订日期小于等于今天
                    queryWrapper.eq(TourItinerary::getOrderStatus, 2) // 已完成
                               .le(TourItinerary::getBookingDate, LocalDate.now());
                    break;
                case "cancelled":
                    queryWrapper.eq(TourItinerary::getOrderStatus, 3); // 已取消
                    break;
            }
        }
        
        queryWrapper.orderByDesc(TourItinerary::getCreateTime);
        
        Page<TourItinerary> itineraries = tourItineraryMapper.selectPage(itineraryPage, queryWrapper);
        
        // 转换为VO
        Page<ItineraryListVO> result = new Page<>(page, size);
        BeanUtils.copyProperties(itineraries, result, "records");
        
        List<ItineraryListVO> voList = itineraries.getRecords().stream().map(itinerary -> {
            ItineraryListVO vo = new ItineraryListVO();
            BeanUtils.copyProperties(itinerary, vo);
            
            // 设置订单编号
            vo.setOrderNumber("TRIP" + itinerary.getOrderId());
            
            // 设置行程标题（使用产品名称）
            TourProduct product = tourProductMapper.selectById(itinerary.getProductId());
            if (product != null) {
                vo.setProductName(product.getProductName());
                vo.setProductImage(product.getMainImgUrl());
                vo.setTitle(product.getProductName());
                vo.setStartDate(product.getStartDate());
                vo.setEndDate(product.getEndDate());
            }
            
            // 设置导游信息
            if (itinerary.getGuideId() != null) {
                GuideExtend guide = guideExtendMapper.selectById(itinerary.getGuideId());
                if (guide != null) {
                    ItineraryListVO.GuideBasicInfo guideInfo = new ItineraryListVO.GuideBasicInfo();
                    guideInfo.setGuideId(guide.getGuideId());
                    guideInfo.setName(guide.getRealName());
                    guideInfo.setRating(guide.getServiceScore());
                    guideInfo.setPhone(guide.getPhone());
                    guideInfo.setServiceType(getServiceTypeText(guide.getServiceLang()));
                    guideInfo.setLanguages(guide.getServiceLang());
                    vo.setGuide(guideInfo);
                }
            }
            
            return vo;
        }).collect(Collectors.toList());
        
        result.setRecords(voList);
        return result;
    }

    @Override
    public ItineraryDetailVO getItineraryDetail(Long orderId, Long userId) {
        // 获取行程信息
        TourItinerary itinerary = tourItineraryMapper.selectOne(
            new LambdaQueryWrapper<TourItinerary>()
                .eq(TourItinerary::getOrderId, orderId)
                .eq(TourItinerary::getUserId, userId)
        );
        
        if (itinerary == null) {
            return null;
        }
        
        ItineraryDetailVO vo = new ItineraryDetailVO();
        BeanUtils.copyProperties(itinerary, vo);
        
        // 设置订单编号
        vo.setOrderNumber("TRIP" + itinerary.getOrderId());
        
        // 设置产品信息
        TourProduct product = tourProductMapper.selectById(itinerary.getProductId());
        if (product != null) {
            ItineraryDetailVO.ProductInfo productInfo = new ItineraryDetailVO.ProductInfo();
            productInfo.setProductId(product.getProductId());
            productInfo.setProductName(product.getProductName());
            productInfo.setDescription(product.getDescription());
            productInfo.setImgUrls(product.getImgUrls());
            vo.setProductInfo(productInfo);
            vo.setTitle(product.getProductName());
            vo.setStartDate(product.getStartDate());
            vo.setTravellers(itinerary.getTravellers());
        }
        
        // 设置导游信息
        if (itinerary.getGuideId() != null) {
            GuideExtend guide = guideExtendMapper.selectById(itinerary.getGuideId());
            if (guide != null) {
                ItineraryDetailVO.GuideInfo guideInfo = new ItineraryDetailVO.GuideInfo();
                guideInfo.setGuideId(guide.getGuideId());
                guideInfo.setName(guide.getRealName());
                guideInfo.setRating(guide.getServiceScore());
                guideInfo.setPhone(guide.getPhone());
                guideInfo.setServiceType(getServiceTypeText(guide.getServiceLang()));
                guideInfo.setLanguages(Arrays.asList(guide.getServiceLang().split(",")));
                vo.setGuideInfo(guideInfo);
            }
        }
        
        // 设置时间线
        vo.setTimeline(getOrderTimeline(orderId));
        
        // 设置每日行程安排
        vo.setDailySchedule(getDailySchedule(orderId));
        
        // 设置重要提示
        vo.setNotes(itinerary.getSpecialNeeds());
        
        return vo;
    }

    @Override
    @Transactional
    public Boolean modifyItinerary(ItineraryModifyDTO modifyDTO, Long userId) {
        // 检查行程是否存在且属于当前用户
        TourItinerary itinerary = tourItineraryMapper.selectOne(
            new LambdaQueryWrapper<TourItinerary>()
                .eq(TourItinerary::getOrderId, modifyDTO.getOrderId())
                .eq(TourItinerary::getUserId, userId)
        );
        
        if (itinerary == null) {
            throw new RuntimeException("行程不存在");
        }
        
        // 检查是否可以修改
        if (!canModifyItinerary(modifyDTO.getOrderId(), userId)) {
            throw new RuntimeException("当前行程状态不允许修改");
        }
        
        // 更新行程信息
        itinerary.setBookingDate(modifyDTO.getStartDate());
        itinerary.setTravellers(modifyDTO.getTravelers());
        itinerary.setSpecialNeeds(modifyDTO.getNotes());
        itinerary.setOrderStatus((byte) 4); // 设置为修改待确认状态
        
        int updateResult = tourItineraryMapper.updateById(itinerary);
        
        if (updateResult > 0) {
            // 记录状态变更日志
            OrderStatusLog log = new OrderStatusLog();
            log.setOrderId(modifyDTO.getOrderId());
            log.setFromStatus((byte) 1); // 从已确认状态
            log.setToStatus((byte) 4); // 到修改待确认状态
            log.setReason("用户申请修改行程");
            log.setOperatorId(userId);
            log.setOperatorType((byte) 1); // 用户操作
            log.setRemark(modifyDTO.getModifyReason());
            orderStatusLogMapper.insert(log);
            
            return true;
        }
        
        return false;
    }

    @Override
    @Transactional
    public Boolean cancelItinerary(ItineraryCancelDTO cancelDTO, Long userId) {
        // 检查行程是否存在且属于当前用户
        TourItinerary itinerary = tourItineraryMapper.selectOne(
            new LambdaQueryWrapper<TourItinerary>()
                .eq(TourItinerary::getOrderId, cancelDTO.getOrderId())
                .eq(TourItinerary::getUserId, userId)
        );
        
        if (itinerary == null) {
            throw new RuntimeException("行程不存在");
        }
        
        // 检查是否可以取消
        if (!canCancelItinerary(cancelDTO.getOrderId(), userId)) {
            throw new RuntimeException("当前行程状态不允许取消");
        }
        
        // 计算退款金额
        RefundInfoVO refundInfo = getRefundInfo(cancelDTO.getOrderId(), userId);
        
        // 更新行程状态
        itinerary.setOrderStatus((byte) 3); // 已取消
        itinerary.setCancelTime(LocalDateTime.now());
        if (refundInfo.getRefundPercentage() > 0) {
            itinerary.setPayStatus((byte) 2); // 已退款
        }
        
        int updateResult = tourItineraryMapper.updateById(itinerary);
        
        if (updateResult > 0) {
            // 记录状态变更日志
            OrderStatusLog log = new OrderStatusLog();
            log.setOrderId(cancelDTO.getOrderId());
            log.setFromStatus(itinerary.getOrderStatus());
            log.setToStatus((byte) 3); // 已取消
            log.setReason("用户取消行程");
            log.setOperatorId(userId);
            log.setOperatorType((byte) 1); // 用户操作
            log.setRemark(cancelDTO.getReason() + "：" + cancelDTO.getDescription());
            orderStatusLogMapper.insert(log);
            
            return true;
        }
        
        return false;
    }

    @Override
    public RefundInfoVO getRefundInfo(Long orderId, Long userId) {
        TourItinerary itinerary = tourItineraryMapper.selectOne(
            new LambdaQueryWrapper<TourItinerary>()
                .eq(TourItinerary::getOrderId, orderId)
                .eq(TourItinerary::getUserId, userId)
        );
        
        if (itinerary == null) {
            return null;
        }
        
        RefundInfoVO refundInfo = new RefundInfoVO();
        
        // 计算距离出发的天数
        LocalDate today = LocalDate.now();
        LocalDate startDate = itinerary.getBookingDate();
        long days = ChronoUnit.DAYS.between(today, startDate);
        refundInfo.setDaysBeforeDeparture((int) days);
        
        // 根据距离出发的天数计算退款比例
        int refundPercentage;
        if (days >= 7) {
            refundPercentage = 100;
        } else if (days >= 4) {
            refundPercentage = 80;
        } else if (days >= 1) {
            refundPercentage = 50;
        } else {
            refundPercentage = 0;
        }
        refundInfo.setRefundPercentage(refundPercentage);
        
        // 计算退款金额
        BigDecimal refundAmount = itinerary.getTotalPrice().multiply(
            BigDecimal.valueOf(refundPercentage).divide(BigDecimal.valueOf(100))
        );
        refundInfo.setRefundAmount(refundAmount);
        
        // 设置退款到账天数
        refundInfo.setRefundDays(refundPercentage > 0 ? 3 : 0);
        
        // 设置退款政策
        refundInfo.setRefundPolicy("出发前7天及以上取消，全额退款；出发前4-6天取消，退款80%；出发前1-3天取消，退款50%；当天取消，不予退款。");
        
        return refundInfo;
    }

    @Override
    public Boolean canModifyItinerary(Long orderId, Long userId) {
        TourItinerary itinerary = tourItineraryMapper.selectOne(
            new LambdaQueryWrapper<TourItinerary>()
                .eq(TourItinerary::getOrderId, orderId)
                .eq(TourItinerary::getUserId, userId)
        );
        
        if (itinerary == null) {
            return false;
        }
        
        // 只有已确认且未过期的行程可以修改
        if (itinerary.getOrderStatus() != 1) {
            return false;
        }
        
        LocalDate startDate = itinerary.getBookingDate();
        LocalDate today = LocalDate.now();
        // 出发前1天以上可以修改
        return startDate.isAfter(today.plusDays(1));
    }

    @Override
    public Boolean canCancelItinerary(Long orderId, Long userId) {
        TourItinerary itinerary = tourItineraryMapper.selectOne(
            new LambdaQueryWrapper<TourItinerary>()
                .eq(TourItinerary::getOrderId, orderId)
                .eq(TourItinerary::getUserId, userId)
        );
        
        if (itinerary == null) {
            return false;
        }
        
        // 只有已确认且未过期的行程可以取消
        if (itinerary.getOrderStatus() != 1) {
            return false;
        }
        
        LocalDate startDate = itinerary.getBookingDate();
        LocalDate today = LocalDate.now();
        // 出发当天及之前可以取消（但可能不退款）
        return !startDate.isBefore(today);
    }

    /**
     * 根据订单状态同步行程记录
     * 当订单状态为已确认(1)时，创建行程记录
     * 当订单状态为已完成(2)时，更新行程状态为已完成
     */
    @Transactional
    public void syncItineraryFromOrder(Long orderId) {
        // 获取订单信息
        TourOrder order = tourOrderMapper.selectById(orderId);
        if (order == null) {
            return;
        }
        
        if (order.getOrderStatus() == 1 && order.getPayStatus() == 1) { // 已确认且已支付
            // 检查是否已存在行程记录
            TourItinerary existingItinerary = tourItineraryMapper.selectOne(
                new LambdaQueryWrapper<TourItinerary>()
                    .eq(TourItinerary::getOrderId, orderId)
            );
            
            if (existingItinerary == null) {
                // 创建新的行程记录
                TourItinerary itinerary = new TourItinerary();
                itinerary.setOrderId(order.getOrderId());
                itinerary.setUserId(order.getUserId());
                itinerary.setProductId(order.getProductId());
                itinerary.setGuideId(order.getGuideId());
                itinerary.setOrderStatus(order.getOrderStatus());
                itinerary.setPayStatus(order.getPayStatus());
                itinerary.setPayType(order.getPayType());
                itinerary.setTotalPrice(order.getTotalPrice());
                itinerary.setTravellers(order.getTravellers());
                itinerary.setBookingDate(order.getBookingDate());
                itinerary.setSpecialNeeds(order.getSpecialNeeds());
                itinerary.setReceiverName(order.getReceiverName());
                itinerary.setReceiverPhone(order.getReceiverPhone());
                itinerary.setReceiverAddress(order.getReceiverAddress());
                itinerary.setCreateTime(order.getCreateTime());
                itinerary.setUpdateTime(LocalDateTime.now());
                itinerary.setCancelTime(order.getCancelTime());
                itinerary.setItineraryDate(order.getBookingDate());
                itinerary.setDaySeq(1);
                itinerary.setStatus((byte) 0); // 未开始
                
                tourItineraryMapper.insert(itinerary);
            }
        } else if (order.getOrderStatus() == 2) { // 已完成
            // 更新现有行程记录状态
            TourItinerary itinerary = tourItineraryMapper.selectOne(
                new LambdaQueryWrapper<TourItinerary>()
                    .eq(TourItinerary::getOrderId, orderId)
            );
            
            if (itinerary != null) {
                itinerary.setOrderStatus(order.getOrderStatus());
                itinerary.setUpdateTime(LocalDateTime.now());
                tourItineraryMapper.updateById(itinerary);
            }
        }
    }

    /**
     * 获取订单时间线
     */
    private List<ItineraryDetailVO.TimelineEvent> getOrderTimeline(Long orderId) {
        List<OrderStatusLog> logs = orderStatusLogMapper.selectList(
            new LambdaQueryWrapper<OrderStatusLog>()
                .eq(OrderStatusLog::getOrderId, orderId)
                .orderByAsc(OrderStatusLog::getCreateTime)
        );
        
        List<ItineraryDetailVO.TimelineEvent> timeline = new ArrayList<>();
        
        for (OrderStatusLog log : logs) {
            ItineraryDetailVO.TimelineEvent event = new ItineraryDetailVO.TimelineEvent();
            event.setTime(log.getCreateTime());
            event.setContent(log.getRemark());
            event.setType(getTimelineEventType(log.getToStatus()));
            event.setColor(getTimelineEventColor(log.getToStatus()));
            timeline.add(event);
        }
        
        return timeline;
    }

    /**
     * 获取每日行程安排
     */
    private List<ItineraryDetailVO.DailySchedule> getDailySchedule(Long orderId) {
        TourOrder order = tourOrderMapper.selectById(orderId);
        if (order == null) {
            return new ArrayList<>();
        }
        
        // 获取产品的每日行程安排
        List<ProductDailyItinerary> productItineraries = productDailyItineraryMapper.selectList(
            new LambdaQueryWrapper<ProductDailyItinerary>()
                .eq(ProductDailyItinerary::getProductId, order.getProductId())
                .orderByAsc(ProductDailyItinerary::getDaySeq)
        );
        
        List<ItineraryDetailVO.DailySchedule> dailySchedules = new ArrayList<>();
        
        for (ProductDailyItinerary productItinerary : productItineraries) {
            ItineraryDetailVO.DailySchedule schedule = new ItineraryDetailVO.DailySchedule();
            schedule.setDate(order.getBookingDate().plusDays(productItinerary.getDaySeq() - 1));
            
            // 解析活动信息（这里简化处理，实际可能需要更复杂的解析逻辑）
            List<ItineraryDetailVO.Activity> activities = new ArrayList<>();
            ItineraryDetailVO.Activity activity = new ItineraryDetailVO.Activity();
            activity.setName(productItinerary.getTitle());
            activity.setDescription(productItinerary.getDescription());
            activity.setTime("09:00"); // 默认时间
            activities.add(activity);
            
            schedule.setActivities(activities);
            dailySchedules.add(schedule);
        }
        
        return dailySchedules;
    }

    /**
     * 获取服务类型文本
     */
    private String getServiceTypeText(String serviceLang) {
        if (serviceLang == null || serviceLang.isEmpty()) {
            return "当地向导";
        }
        
        String[] languages = serviceLang.split(",");
        if (languages.length > 2) {
            return "定制旅游";
        } else if (languages.length == 2) {
            return "翻译导游";
        } else {
            return "当地向导";
        }
    }

    /**
     * 获取时间线事件类型
     */
    private String getTimelineEventType(Byte status) {
        switch (status) {
            case 0: return "primary";
            case 1: return "success";
            case 2: return "success";
            case 3: return "error";
            case 4: return "warning";
            default: return "primary";
        }
    }

    /**
     * 获取时间线事件颜色
     */
    private String getTimelineEventColor(Byte status) {
        switch (status) {
            case 0: return "#409eff";
            case 1: return "#67c23a";
            case 2: return "#67c23a";
            case 3: return "#f56c6c";
            case 4: return "#e6a23c";
            default: return "#409eff";
        }
    }
}
