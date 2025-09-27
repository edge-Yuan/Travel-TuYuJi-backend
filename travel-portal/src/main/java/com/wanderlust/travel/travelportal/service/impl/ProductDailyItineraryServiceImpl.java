package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.dto.ProductDailyItineraryDTO;
import com.wanderlust.travel.travelportal.entity.ProductDailyItinerary;
import com.wanderlust.travel.travelportal.mapper.ProductDailyItineraryMapper;
import com.wanderlust.travel.travelportal.service.IProductDailyItineraryService;
import com.wanderlust.travel.travelportal.vo.ProductDailyItineraryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 产品每日行程服务实现类
 */
@Service
@Slf4j
public class ProductDailyItineraryServiceImpl extends ServiceImpl<ProductDailyItineraryMapper, ProductDailyItinerary> implements IProductDailyItineraryService {
    
    @Autowired
    private ProductDailyItineraryMapper productDailyItineraryMapper;
    
    @Override
    @Transactional
    public boolean addProductDailyItinerary(ProductDailyItineraryDTO itineraryDTO) {
        try {
            ProductDailyItinerary itinerary = new ProductDailyItinerary();
            BeanUtils.copyProperties(itineraryDTO, itinerary);
            
            return save(itinerary);
        } catch (Exception e) {
            log.error("添加产品每日行程失败", e);
            return false;
        }
    }
    
    @Override
    public List<ProductDailyItineraryVO> getProductDailyItineraries(Long productId) {
        try {
            LambdaQueryWrapper<ProductDailyItinerary> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProductDailyItinerary::getProductId, productId)
                       .orderByAsc(ProductDailyItinerary::getDaySeq);
            
            List<ProductDailyItinerary> itineraries = list(queryWrapper);
            
            return itineraries.stream().map(itinerary -> {
                ProductDailyItineraryVO vo = new ProductDailyItineraryVO();
                BeanUtils.copyProperties(itinerary, vo);
                return vo;
            }).collect(Collectors.toList());
            
        } catch (Exception e) {
            log.error("获取产品每日行程列表失败", e);
            return List.of();
        }
    }
    
    @Override
    @Transactional
    public boolean updateProductDailyItinerary(Long itineraryId, ProductDailyItineraryDTO itineraryDTO) {
        try {
            ProductDailyItinerary itinerary = getById(itineraryId);
            if (itinerary == null) {
                return false;
            }
            
            BeanUtils.copyProperties(itineraryDTO, itinerary);
            itinerary.setItineraryId(itineraryId);
            
            return updateById(itinerary);
        } catch (Exception e) {
            log.error("更新产品每日行程失败", e);
            return false;
        }
    }
    
    @Override
    @Transactional
    public boolean deleteProductDailyItinerary(Long itineraryId) {
        try {
            return removeById(itineraryId);
        } catch (Exception e) {
            log.error("删除产品每日行程失败", e);
            return false;
        }
    }
    
    @Override
    @Transactional
    public boolean batchAddProductDailyItineraries(List<ProductDailyItineraryDTO> itineraryDTOs) {
        try {
            List<ProductDailyItinerary> itineraries = itineraryDTOs.stream()
                    .map(dto -> {
                        ProductDailyItinerary itinerary = new ProductDailyItinerary();
                        BeanUtils.copyProperties(dto, itinerary);
                        return itinerary;
                    })
                    .collect(Collectors.toList());
            
            return saveBatch(itineraries);
        } catch (Exception e) {
            log.error("批量添加产品每日行程失败", e);
            return false;
        }
    }
}