package com.wanderlust.travel.travelportal.service.impl;

import com.wanderlust.travel.travelportal.entity.TourProduct;
import com.wanderlust.travel.travelportal.mapper.TourProductMapper;
import com.wanderlust.travel.travelportal.service.ITourProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 旅游产品表 服务实现类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@Service
public class TourProductServiceImpl extends ServiceImpl<TourProductMapper, TourProduct> implements ITourProductService {
    @Autowired
    private TourProductMapper tourProductMapper;
    public List<TourProduct> getAllProducts(){
        return tourProductMapper.selectList(null);
    }

}
