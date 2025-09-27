package com.wanderlust.travel.travelportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanderlust.travel.travelportal.dto.ProductBookingNoticeDTO;
import com.wanderlust.travel.travelportal.entity.ProductBookingNotice;
import com.wanderlust.travel.travelportal.mapper.ProductBookingNoticeMapper;
import com.wanderlust.travel.travelportal.service.IProductBookingNoticeService;
import com.wanderlust.travel.travelportal.vo.ProductBookingNoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 产品预订须知服务实现类
 */
@Service
@Slf4j
public class ProductBookingNoticeServiceImpl extends ServiceImpl<ProductBookingNoticeMapper, ProductBookingNotice> implements IProductBookingNoticeService {
    
    @Autowired
    private ProductBookingNoticeMapper productBookingNoticeMapper;
    
    @Override
    @Transactional
    public boolean addProductBookingNotice(ProductBookingNoticeDTO bookingNoticeDTO) {
        try {
            ProductBookingNotice bookingNotice = new ProductBookingNotice();
            BeanUtils.copyProperties(bookingNoticeDTO, bookingNotice);
            
            return save(bookingNotice);
        } catch (Exception e) {
            log.error("添加产品预订须知失败", e);
            return false;
        }
    }
    
    @Override
    public ProductBookingNoticeVO getProductBookingNotice(Long productId) {
        try {
            LambdaQueryWrapper<ProductBookingNotice> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ProductBookingNotice::getProductId, productId);
            
            ProductBookingNotice bookingNotice = getOne(queryWrapper);
            if (bookingNotice == null) {
                return null;
            }
            
            ProductBookingNoticeVO vo = new ProductBookingNoticeVO();
            BeanUtils.copyProperties(bookingNotice, vo);
            return vo;
            
        } catch (Exception e) {
            log.error("获取产品预订须知失败", e);
            return null;
        }
    }
    
    @Override
    @Transactional
    public boolean updateProductBookingNotice(Long noticeId, ProductBookingNoticeDTO bookingNoticeDTO) {
        try {
            ProductBookingNotice bookingNotice = getById(noticeId);
            if (bookingNotice == null) {
                return false;
            }
            
            BeanUtils.copyProperties(bookingNoticeDTO, bookingNotice);
            bookingNotice.setNoticeId(noticeId);
            
            return updateById(bookingNotice);
        } catch (Exception e) {
            log.error("更新产品预订须知失败", e);
            return false;
        }
    }
    
    @Override
    @Transactional
    public boolean deleteProductBookingNotice(Long noticeId) {
        try {
            return removeById(noticeId);
        } catch (Exception e) {
            log.error("删除产品预订须知失败", e);
            return false;
        }
    }
}