package com.wanderlust.travel.travelportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanderlust.travel.travelportal.dto.ProductBookingNoticeDTO;
import com.wanderlust.travel.travelportal.entity.ProductBookingNotice;
import com.wanderlust.travel.travelportal.vo.ProductBookingNoticeVO;

/**
 * 产品预订须知服务接口
 */
public interface IProductBookingNoticeService extends IService<ProductBookingNotice> {
    
    /**
     * 添加产品预订须知
     */
    boolean addProductBookingNotice(ProductBookingNoticeDTO bookingNoticeDTO);
    
    /**
     * 获取产品预订须知
     */
    ProductBookingNoticeVO getProductBookingNotice(Long productId);
    
    /**
     * 更新产品预订须知
     */
    boolean updateProductBookingNotice(Long noticeId, ProductBookingNoticeDTO bookingNoticeDTO);
    
    /**
     * 删除产品预订须知
     */
    boolean deleteProductBookingNotice(Long noticeId);
}