package com.wanderlust.travel.travelportal.service;

import com.wanderlust.travel.travelportal.entity.TourProduct;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 旅游产品表 服务类
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
public interface ITourProductService extends IService<TourProduct> {
    List<TourProduct> getAllProducts();
}
