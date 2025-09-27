package com.wanderlust.travel.travelportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.travelportal.dto.ProductDailyItineraryDTO;
import com.wanderlust.travel.travelportal.entity.ProductDailyItinerary;
import com.wanderlust.travel.travelportal.vo.ProductDailyItineraryVO;

import java.util.List;

/**
 * 产品每日行程服务接口
 */
public interface IProductDailyItineraryService extends IService<ProductDailyItinerary> {
    
    /**
     * 添加产品每日行程
     */
    boolean addProductDailyItinerary(ProductDailyItineraryDTO itineraryDTO);
    
    /**
     * 获取产品每日行程列表
     */
    List<ProductDailyItineraryVO> getProductDailyItineraries(Long productId);
    
    /**
     * 更新产品每日行程
     */
    boolean updateProductDailyItinerary(Long itineraryId, ProductDailyItineraryDTO itineraryDTO);
    
    /**
     * 删除产品每日行程
     */
    boolean deleteProductDailyItinerary(Long itineraryId);
    
    /**
     * 批量添加产品每日行程
     */
    boolean batchAddProductDailyItineraries(List<ProductDailyItineraryDTO> itineraryDTOs);
}