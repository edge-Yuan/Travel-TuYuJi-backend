package com.wanderlust.travel.travelportal.service.impl;

import com.wanderlust.travel.travelportal.entity.TourItinerary;
import com.wanderlust.travel.travelportal.mapper.TourItineraryMapper;
import com.wanderlust.travel.travelportal.service.ITourItineraryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 行程安排表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@Service
public class TourItineraryServiceImpl extends ServiceImpl<TourItineraryMapper, TourItinerary> implements ITourItineraryService {
    @Autowired
    private TourItineraryMapper tourItineraryMapper;
    public List<TourItinerary> getAllItinerarys() {
        return tourItineraryMapper.selectList( null);
    }

}
