package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.dto.ProductBookingNoticeDTO;
import com.wanderlust.travel.travelportal.service.IProductBookingNoticeService;
import com.wanderlust.travel.travelportal.vo.ProductBookingNoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 产品预订须知控制器
 */
@RestController
@RequestMapping("/travel-portal/productBookingNotice")
@Slf4j
public class ProductBookingNoticeController {
    
    @Autowired
    private IProductBookingNoticeService productBookingNoticeService;
    
    /**
     * 添加产品预订须知
     */
    @PostMapping("/add")
    public Result<Boolean> addProductBookingNotice(@Valid @RequestBody ProductBookingNoticeDTO bookingNoticeDTO) {
        log.info("添加产品预订须知: {}", bookingNoticeDTO);
        boolean success = productBookingNoticeService.addProductBookingNotice(bookingNoticeDTO);
        return success ? Result.success(true) : Result.error("添加预订须知失败");
    }
    
    /**
     * 获取产品预订须知
     */
    @GetMapping("/get/{productId}")
    public Result<ProductBookingNoticeVO> getProductBookingNotice(@PathVariable Long productId) {
        log.info("获取产品预订须知: productId={}", productId);
        ProductBookingNoticeVO bookingNotice = productBookingNoticeService.getProductBookingNotice(productId);
        return Result.success(bookingNotice);
    }
    
    /**
     * 更新产品预订须知
     */
    @PutMapping("/update/{noticeId}")
    public Result<Boolean> updateProductBookingNotice(
            @PathVariable Long noticeId,
            @Valid @RequestBody ProductBookingNoticeDTO bookingNoticeDTO) {
        log.info("更新产品预订须知: noticeId={}, {}", noticeId, bookingNoticeDTO);
        boolean success = productBookingNoticeService.updateProductBookingNotice(noticeId, bookingNoticeDTO);
        return success ? Result.success(true) : Result.error("更新预订须知失败");
    }
    
    /**
     * 删除产品预订须知
     */
    @DeleteMapping("/delete/{noticeId}")
    public Result<Boolean> deleteProductBookingNotice(@PathVariable Long noticeId) {
        log.info("删除产品预订须知: noticeId={}", noticeId);
        boolean success = productBookingNoticeService.deleteProductBookingNotice(noticeId);
        return success ? Result.success(true) : Result.error("删除预订须知失败");
    }
}