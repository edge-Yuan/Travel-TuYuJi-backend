package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.common.utils.TimePeriodUtils;
import com.wanderlust.travel.travelportal.dto.ProductDailyItineraryDTO;
import com.wanderlust.travel.travelportal.service.IProductDailyItineraryService;
import com.wanderlust.travel.travelportal.vo.ProductDailyItineraryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 产品每日行程控制器
 */
@RestController
@RequestMapping("/travel-portal/productDailyItinerary")
@Slf4j
public class ProductDailyItineraryController {
    
    @Autowired
    private IProductDailyItineraryService productDailyItineraryService;
    
    /**
     * 添加产品每日行程
     */
    @PostMapping("/add")
    public Result<Boolean> addProductDailyItinerary(@Valid @RequestBody ProductDailyItineraryDTO itineraryDTO) {
        log.info("添加产品每日行程: {}", itineraryDTO);
        boolean success = productDailyItineraryService.addProductDailyItinerary(itineraryDTO);
        return success ? Result.success(true) : Result.error("添加每日行程失败");
    }
    
    /**
     * 获取产品每日行程列表
     */
    @GetMapping("/list/{productId}")
    public Result<List<ProductDailyItineraryVO>> getProductDailyItineraries(@PathVariable Long productId) {
        log.info("获取产品每日行程列表: productId={}", productId);
        List<ProductDailyItineraryVO> itineraries = productDailyItineraryService.getProductDailyItineraries(productId);
        return Result.success(itineraries);
    }
    
    /**
     * 更新产品每日行程
     */
    @PutMapping("/update/{itineraryId}")
    public Result<Boolean> updateProductDailyItinerary(
            @PathVariable Long itineraryId,
            @Valid @RequestBody ProductDailyItineraryDTO itineraryDTO) {
        log.info("更新产品每日行程: itineraryId={}, {}", itineraryId, itineraryDTO);
        boolean success = productDailyItineraryService.updateProductDailyItinerary(itineraryId, itineraryDTO);
        return success ? Result.success(true) : Result.error("更新每日行程失败");
    }
    
    /**
     * 删除产品每日行程
     */
    @DeleteMapping("/delete/{itineraryId}")
    public Result<Boolean> deleteProductDailyItinerary(@PathVariable Long itineraryId) {
        log.info("删除产品每日行程: itineraryId={}", itineraryId);
        boolean success = productDailyItineraryService.deleteProductDailyItinerary(itineraryId);
        return success ? Result.success(true) : Result.error("删除每日行程失败");
    }
    
    /**
     * 获取有效的时间段列表
     */
    @GetMapping("/timePeriods")
    public Result<List<String>> getValidTimePeriods() {
        log.info("获取有效时间段列表");
        List<String> timePeriods = TimePeriodUtils.getAllValidTimePeriods();
        return Result.success(timePeriods);
    }
    
    /**
     * 批量添加产品每日行程
     */
    @PostMapping("/batchAdd")
    public Result<Boolean> batchAddProductDailyItineraries(@Valid @RequestBody List<ProductDailyItineraryDTO> itineraryDTOs) {
        log.info("批量添加产品每日行程: {}", itineraryDTOs);
        boolean success = productDailyItineraryService.batchAddProductDailyItineraries(itineraryDTOs);
        return success ? Result.success(true) : Result.error("批量添加每日行程失败");
    }
}