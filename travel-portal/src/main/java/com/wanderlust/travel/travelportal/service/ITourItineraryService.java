package com.wanderlust.travel.travelportal.service;

import com.wanderlust.travel.travelportal.entity.TourItinerary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 行程安排表 服务类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
public interface ITourItineraryService extends IService<TourItinerary> {
    List<TourItinerary> getAllItinerarys();

}
