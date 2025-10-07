package com.wanderlust.travel.traveladmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.dto.ProductCreateDTO;
import com.wanderlust.travel.traveladmin.dto.ProductUpdateDTO;
import com.wanderlust.travel.traveladmin.dto.ProductSearchDTO;
import com.wanderlust.travel.traveladmin.entity.TourProduct;
import com.wanderlust.travel.traveladmin.entity.FileInfo;
import com.wanderlust.travel.traveladmin.entity.ProductCostExplanation;
import com.wanderlust.travel.traveladmin.entity.ProductBookingNotice;
import com.wanderlust.travel.traveladmin.entity.ProductDailyItinerary;
import com.wanderlust.travel.traveladmin.mapper.TourProductMapper;
import com.wanderlust.travel.traveladmin.mapper.FileInfoMapper;
import com.wanderlust.travel.traveladmin.mapper.ProductCostExplanationMapper;
import com.wanderlust.travel.traveladmin.mapper.ProductBookingNoticeMapper;
import com.wanderlust.travel.traveladmin.mapper.ProductDailyItineraryMapper;
import com.wanderlust.travel.traveladmin.service.TourProductService;
import com.wanderlust.travel.traveladmin.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson2.JSON;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 旅游产品服务实现类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TourProductServiceImpl implements TourProductService {

    private final TourProductMapper tourProductMapper;
    private final FileInfoMapper fileInfoMapper;
    private final ProductCostExplanationMapper costExplanationMapper;
    private final ProductBookingNoticeMapper bookingNoticeMapper;
    private final ProductDailyItineraryMapper dailyItineraryMapper;

    @Override
    public List<ProductVO> getAllProducts(Long merchantId) {
        LambdaQueryWrapper<TourProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TourProduct::getMerchantId, merchantId)
                   .orderByDesc(TourProduct::getCreateTime);
        
        List<TourProduct> products = tourProductMapper.selectList(queryWrapper);
        return products.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createProduct(ProductCreateDTO createDTO, Long merchantId) {
        TourProduct product = new TourProduct();
        BeanUtils.copyProperties(createDTO, product);
        product.setMerchantId(merchantId);
        product.setProductStatus((byte) 1); // 默认上架
        product.setAuditStatus((byte) 0); // 默认待审核
        product.setSoldCount(0);
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        
        // 设置产品类型
        product.setProductType(createDTO.getProductType().byteValue());
        
        // 设置日期字段，如果为空则设置默认值
        product.setStartDate(createDTO.getStartDate() != null ? 
            createDTO.getStartDate() : LocalDate.now());
        product.setEndDate(createDTO.getEndDate() != null ? 
            createDTO.getEndDate() : LocalDate.now().plusYears(1));
        
        // 处理JSON字段
        // 处理有效期字段
        if (createDTO.getValidityPeriod() != null && !createDTO.getValidityPeriod().isEmpty()) {
            product.setValidityPeriod(JSON.toJSONString(createDTO.getValidityPeriod()));
        }
        
        // 处理设施字段
        if (createDTO.getFacilities() != null && !createDTO.getFacilities().isEmpty()) {
            product.setFacilities(JSON.toJSONString(createDTO.getFacilities()));
        }
        
        tourProductMapper.insert(product);
        
        log.info("创建产品成功，产品ID：{}，商家ID：{}", product.getProductId(), merchantId);
        return product.getProductId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProduct(Long productId, ProductCreateDTO createDTO, Long merchantId) {
        // 检查产品是否存在且属于该商家
        TourProduct existingProduct = tourProductMapper.selectById(productId);
        if (existingProduct == null || !existingProduct.getMerchantId().equals(merchantId)) {
            throw new RuntimeException("产品不存在或无权限操作");
        }
        
        TourProduct product = new TourProduct();
        BeanUtils.copyProperties(createDTO, product);
        product.setProductId(productId);
        product.setMerchantId(merchantId);
        product.setProductType(createDTO.getProductType().byteValue());
        product.setUpdateTime(LocalDateTime.now());
        
        // 设置日期字段，如果为空则保持原有值
        if (createDTO.getStartDate() != null) {
            product.setStartDate(createDTO.getStartDate());
        }
        if (createDTO.getEndDate() != null) {
            product.setEndDate(createDTO.getEndDate());
        }
        
        // 处理JSON字段
        // 处理有效期字段
        if (createDTO.getValidityPeriod() != null && !createDTO.getValidityPeriod().isEmpty()) {
            product.setValidityPeriod(JSON.toJSONString(createDTO.getValidityPeriod()));
        }
        
        // 处理设施字段
        if (createDTO.getFacilities() != null && !createDTO.getFacilities().isEmpty()) {
            product.setFacilities(JSON.toJSONString(createDTO.getFacilities()));
        }
        
        boolean result = tourProductMapper.updateById(product) > 0;
        
        // 保存复杂对象
        if (result) {
            saveProductComplexData(productId, createDTO);
        }
        
        log.info("更新产品成功，产品ID：{}，商家ID：{}", productId, merchantId);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProduct(Long productId, ProductUpdateDTO updateDTO, Long merchantId) {
        // 检查产品是否存在且属于该商家
        TourProduct existingProduct = tourProductMapper.selectById(productId);
        if (existingProduct == null || !existingProduct.getMerchantId().equals(merchantId)) {
            throw new RuntimeException("产品不存在或无权限操作");
        }
        
        TourProduct product = new TourProduct();
        BeanUtils.copyProperties(updateDTO, product);
        product.setProductId(productId);
        product.setMerchantId(merchantId);
        product.setProductType(updateDTO.getProductType().byteValue());
        product.setUpdateTime(LocalDateTime.now());
        
        // 设置日期字段，如果为空则保持原有值
        if (updateDTO.getStartDate() != null) {
            product.setStartDate(updateDTO.getStartDate());
        }
        if (updateDTO.getEndDate() != null) {
            product.setEndDate(updateDTO.getEndDate());
        }
        
        // 处理JSON字段
        // 处理有效期字段
        if (updateDTO.getValidityPeriod() != null && !updateDTO.getValidityPeriod().isEmpty()) {
            product.setValidityPeriod(JSON.toJSONString(updateDTO.getValidityPeriod()));
        }
        
        // 处理设施字段
        if (updateDTO.getFacilities() != null && !updateDTO.getFacilities().isEmpty()) {
            product.setFacilities(JSON.toJSONString(updateDTO.getFacilities()));
        }
        
        boolean result = tourProductMapper.updateById(product) > 0;
        
        // 保存复杂对象
        if (result) {
            saveProductComplexDataFromUpdateDTO(productId, updateDTO);
        }
        
        log.info("更新产品成功，产品ID：{}，商家ID：{}", productId, merchantId);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteProduct(Long productId, Long merchantId) {
        // 检查产品是否存在且属于该商家
        TourProduct existingProduct = tourProductMapper.selectById(productId);
        if (existingProduct == null || !existingProduct.getMerchantId().equals(merchantId)) {
            throw new RuntimeException("产品不存在或无权限操作");
        }
        
        // 检查产品是否正在使用中（有订单等）
        // TODO: 这里可以添加业务逻辑检查
        
        boolean result = tourProductMapper.deleteById(productId) > 0;
        log.info("删除产品成功，产品ID：{}，商家ID：{}", productId, merchantId);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Integer> batchUpdateStatus(List<Long> productIds, Integer status, Long merchantId) {
        int successCount = 0;
        int failedCount = 0;
        
        for (Long productId : productIds) {
            try {
                TourProduct product = new TourProduct();
                product.setProductId(productId);
                product.setProductStatus(status.byteValue());
                product.setUpdateTime(LocalDateTime.now());
                
                // 检查权限
                TourProduct existingProduct = tourProductMapper.selectById(productId);
                if (existingProduct != null && existingProduct.getMerchantId().equals(merchantId)) {
                    if (tourProductMapper.updateById(product) > 0) {
                        successCount++;
                    } else {
                        failedCount++;
                    }
                } else {
                    failedCount++;
                }
            } catch (Exception e) {
                log.error("批量更新产品状态失败，产品ID：{}", productId, e);
                failedCount++;
            }
        }
        
        Map<String, Integer> result = new HashMap<>();
        result.put("successCount", successCount);
        result.put("failedCount", failedCount);
        
        log.info("批量更新产品状态完成，成功：{}，失败：{}", successCount, failedCount);
        return result;
    }

    @Override
    public ProductVO getProductDetail(Long productId) {
        TourProduct product = tourProductMapper.selectById(productId);
        if (product == null) {
            throw new RuntimeException("产品不存在");
        }
        return convertToVO(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> copyProduct(Long productId, Long merchantId) {
        // 获取原产品
        TourProduct originalProduct = tourProductMapper.selectById(productId);
        if (originalProduct == null) {
            throw new RuntimeException("原产品不存在");
        }
        
        // 检查权限
        if (!originalProduct.getMerchantId().equals(merchantId)) {
            throw new RuntimeException("无权限复制该产品");
        }
        
        // 创建新产品
        TourProduct newProduct = new TourProduct();
        BeanUtils.copyProperties(originalProduct, newProduct);
        newProduct.setProductId(null);
        newProduct.setProductName(originalProduct.getProductName() + " (副本)");
        newProduct.setProductStatus((byte) 0); // 默认下架
        newProduct.setAuditStatus((byte) 0); // 默认待审核
        newProduct.setSoldCount(0);
        newProduct.setCreateTime(LocalDateTime.now());
        newProduct.setUpdateTime(LocalDateTime.now());
        
        tourProductMapper.insert(newProduct);
        
        Map<String, Object> result = new HashMap<>();
        result.put("newProductId", newProduct.getProductId());
        result.put("productName", newProduct.getProductName());
        
        log.info("复制产品成功，原产品ID：{}，新产品ID：{}，商家ID：{}", productId, newProduct.getProductId(), merchantId);
        return result;
    }

    @Override
    public IPage<ProductVO> searchProducts(Page<ProductVO> page, ProductSearchDTO searchDTO) {
        // 创建TourProduct的分页对象
        Page<TourProduct> productPage = new Page<>(page.getCurrent(), page.getSize());
        LambdaQueryWrapper<TourProduct> queryWrapper = new LambdaQueryWrapper<>();
        
        // 商家ID
        if (searchDTO.getMerchantId() != null) {
            queryWrapper.eq(TourProduct::getMerchantId, searchDTO.getMerchantId());
        }
        
        // 关键词搜索
        if (StringUtils.hasText(searchDTO.getKeyword())) {
            queryWrapper.and(wrapper -> wrapper
                .like(TourProduct::getProductName, searchDTO.getKeyword())
                .or()
                .like(TourProduct::getDescription, searchDTO.getKeyword())
                .or()
                .like(TourProduct::getProductTags, searchDTO.getKeyword())
            );
        }
        
        // 产品类型
        if (searchDTO.getType() != null) {
            queryWrapper.eq(TourProduct::getProductType, searchDTO.getType());
        }
        
        // 产品状态
        if (searchDTO.getStatus() != null) {
            queryWrapper.eq(TourProduct::getProductStatus, searchDTO.getStatus());
        }
        
        // 价格范围
        if (searchDTO.getMinPrice() != null) {
            queryWrapper.ge(TourProduct::getPrice, searchDTO.getMinPrice());
        }
        if (searchDTO.getMaxPrice() != null) {
            queryWrapper.le(TourProduct::getPrice, searchDTO.getMaxPrice());
        }
        
        // 排序
        if ("price".equals(searchDTO.getSortBy())) {
            if ("asc".equals(searchDTO.getSortOrder())) {
                queryWrapper.orderByAsc(TourProduct::getPrice);
            } else {
                queryWrapper.orderByDesc(TourProduct::getPrice);
            }
        } else if ("sales".equals(searchDTO.getSortBy())) {
            if ("asc".equals(searchDTO.getSortOrder())) {
                queryWrapper.orderByAsc(TourProduct::getSoldCount);
            } else {
                queryWrapper.orderByDesc(TourProduct::getSoldCount);
            }
        } else {
            if ("asc".equals(searchDTO.getSortOrder())) {
                queryWrapper.orderByAsc(TourProduct::getCreateTime);
            } else {
                queryWrapper.orderByDesc(TourProduct::getCreateTime);
            }
        }
        
        IPage<TourProduct> productPageResult = tourProductMapper.selectPage(productPage, queryWrapper);
        
        // 转换为VO
        IPage<ProductVO> voPage = new Page<>(page.getCurrent(), page.getSize(), productPageResult.getTotal());
        List<ProductVO> voList = productPageResult.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        voPage.setRecords(voList);
        
        return voPage;
    }

    /**
     * 转换为VO
     */
    private ProductVO convertToVO(TourProduct product) {
        ProductVO vo = new ProductVO();
        BeanUtils.copyProperties(product, vo);
        vo.setProductType(product.getProductType().intValue());
        vo.setProductStatus(product.getProductStatus().intValue());
        
        // 加载产品图片
        List<ProductVO.ProductImageVO> images = getProductImages(product.getProductId());
        vo.setImages(images);
        
        // 设置主图URL
        String mainImageUrl = getProductMainImage(product.getProductId());
        vo.setMainImgUrl(mainImageUrl);
        
        // 加载费用说明
        ProductVO.CostExplanationVO costExplanation = getProductCostExplanation(product.getProductId());
        vo.setCostExplanation(costExplanation);
        
        // 加载预订须知
        ProductVO.BookingNoticeVO bookingNotice = getProductBookingNotice(product.getProductId());
        vo.setBookingNotice(bookingNotice);
        
        // 加载行程安排
        List<ProductVO.ItineraryVO> itineraries = getProductItineraries(product.getProductId());
        vo.setItineraries(itineraries);
        
        // 处理JSON字段
        // 处理有效期字段
        if (StringUtils.hasText(product.getValidityPeriod())) {
            try {
                List<String> validityPeriodList = JSON.parseArray(product.getValidityPeriod(), String.class);
                vo.setValidityPeriod(validityPeriodList);
            } catch (Exception e) {
                log.warn("解析有效期JSON失败，产品ID：{}，原始值：{}", product.getProductId(), product.getValidityPeriod());
                vo.setValidityPeriod(null);
            }
        }
        
        // 处理设施字段
        if (StringUtils.hasText(product.getFacilities())) {
            try {
                List<String> facilitiesList = JSON.parseArray(product.getFacilities(), String.class);
                vo.setFacilities(facilitiesList);
            } catch (Exception e) {
                log.warn("解析设施JSON失败，产品ID：{}，原始值：{}", product.getProductId(), product.getFacilities());
                vo.setFacilities(null);
            }
        }
        
        return vo;
    }

    @Override
    public List<ProductVO.ProductImageVO> getProductImages(Long productId) {
        LambdaQueryWrapper<FileInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileInfo::getRelatedId, productId)
                   .eq(FileInfo::getUploadType, "product")
                   .eq(FileInfo::getStatus, 1)
                   .orderByDesc(FileInfo::getIsMain)
                   .orderByDesc(FileInfo::getUploadTime);
        
        List<FileInfo> fileInfos = fileInfoMapper.selectList(queryWrapper);
        
        return fileInfos.stream().map(fileInfo -> {
            ProductVO.ProductImageVO imageVO = new ProductVO.ProductImageVO();
            imageVO.setImageId(fileInfo.getFileId());
            imageVO.setProductId(productId);
            imageVO.setUrl(fileInfo.getFileUrl());
            imageVO.setImageType(fileInfo.getFileType());
            imageVO.setIsMain(fileInfo.getIsMain());
            imageVO.setUploadTime(fileInfo.getUploadTime());
            return imageVO;
        }).collect(Collectors.toList());
    }

    @Override
    public String getProductMainImage(Long productId) {
        LambdaQueryWrapper<FileInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FileInfo::getRelatedId, productId)
                   .eq(FileInfo::getUploadType, "product")
                   .eq(FileInfo::getIsMain, true)
                   .eq(FileInfo::getStatus, 1)
                   .orderByDesc(FileInfo::getUploadTime)
                   .last("LIMIT 1");
        
        FileInfo mainImage = fileInfoMapper.selectOne(queryWrapper);
        return mainImage != null ? mainImage.getFileUrl() : null;
    }

    /**
     * 获取产品费用说明
     */
    private ProductVO.CostExplanationVO getProductCostExplanation(Long productId) {
        LambdaQueryWrapper<ProductCostExplanation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductCostExplanation::getProductId, productId)
                   .orderByDesc(ProductCostExplanation::getCreateTime)
                   .last("LIMIT 1");
        
        ProductCostExplanation costExplanation = costExplanationMapper.selectOne(queryWrapper);
        if (costExplanation == null) {
            return null;
        }
        
        ProductVO.CostExplanationVO vo = new ProductVO.CostExplanationVO();
        BeanUtils.copyProperties(costExplanation, vo);
        return vo;
    }

    /**
     * 获取产品预订须知
     */
    private ProductVO.BookingNoticeVO getProductBookingNotice(Long productId) {
        LambdaQueryWrapper<ProductBookingNotice> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductBookingNotice::getProductId, productId)
                   .eq(ProductBookingNotice::getStatus, 1)
                   .orderByAsc(ProductBookingNotice::getSortOrder)
                   .orderByDesc(ProductBookingNotice::getCreateTime)
                   .last("LIMIT 1");
        
        ProductBookingNotice bookingNotice = bookingNoticeMapper.selectOne(queryWrapper);
        if (bookingNotice == null) {
            return null;
        }
        
        ProductVO.BookingNoticeVO vo = new ProductVO.BookingNoticeVO();
        BeanUtils.copyProperties(bookingNotice, vo);
        return vo;
    }

    /**
     * 获取产品行程安排
     */
    private List<ProductVO.ItineraryVO> getProductItineraries(Long productId) {
        LambdaQueryWrapper<ProductDailyItinerary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductDailyItinerary::getProductId, productId)
                   .orderByAsc(ProductDailyItinerary::getDaySeq)
                   .orderByAsc(ProductDailyItinerary::getTimePeriod);
        
        List<ProductDailyItinerary> itineraries = dailyItineraryMapper.selectList(queryWrapper);
        if (itineraries.isEmpty()) {
            return null;
        }
        
        // 按天数分组
        Map<Integer, List<ProductDailyItinerary>> groupedByDay = itineraries.stream()
                .collect(Collectors.groupingBy(ProductDailyItinerary::getDaySeq));
        
        return groupedByDay.entrySet().stream()
                .map(entry -> {
                    ProductVO.ItineraryVO itineraryVO = new ProductVO.ItineraryVO();
                    itineraryVO.setDaySeq(entry.getKey());
                    
                    List<ProductVO.ItineraryDetailVO> details = entry.getValue().stream()
                            .map(detail -> {
                                ProductVO.ItineraryDetailVO detailVO = new ProductVO.ItineraryDetailVO();
                                BeanUtils.copyProperties(detail, detailVO);
                                return detailVO;
                            })
                            .collect(Collectors.toList());
                    
                    itineraryVO.setItineraries(details);
                    return itineraryVO;
                })
                .sorted((a, b) -> Integer.compare(a.getDaySeq(), b.getDaySeq()))
                .collect(Collectors.toList());
    }

    /**
     * 保存产品复杂数据（从ProductCreateDTO）
     */
    private void saveProductComplexData(Long productId, ProductCreateDTO createDTO) {
        // 保存费用说明
        if (createDTO.getCostExplanation() != null) {
            saveCostExplanation(productId, createDTO.getCostExplanation());
        }
        
        // 保存预订须知
        if (createDTO.getBookingNotice() != null) {
            saveBookingNotice(productId, createDTO.getBookingNotice());
        }
        
        // 保存行程安排
        if (createDTO.getItineraries() != null && !createDTO.getItineraries().isEmpty()) {
            saveItineraries(productId, createDTO.getItineraries());
        }
    }

    /**
     * 保存产品复杂数据（从ProductUpdateDTO）
     */
    private void saveProductComplexDataFromUpdateDTO(Long productId, ProductUpdateDTO updateDTO) {
        // 保存费用说明
        if (updateDTO.getCostExplanation() != null) {
            saveCostExplanation(productId, updateDTO.getCostExplanation());
        }
        
        // 保存预订须知
        if (updateDTO.getBookingNotice() != null) {
            saveBookingNotice(productId, updateDTO.getBookingNotice());
        }
        
        // 保存行程安排
        if (updateDTO.getItineraries() != null && !updateDTO.getItineraries().isEmpty()) {
            saveItineraries(productId, updateDTO.getItineraries());
        }
    }

    /**
     * 保存费用说明
     */
    private void saveCostExplanation(Long productId, ProductCreateDTO.CostExplanationDTO costExplanationDTO) {
        // 先删除旧的费用说明
        LambdaQueryWrapper<ProductCostExplanation> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.eq(ProductCostExplanation::getProductId, productId);
        costExplanationMapper.delete(deleteWrapper);
        
        // 保存新的费用说明
        ProductCostExplanation costExplanation = new ProductCostExplanation();
        BeanUtils.copyProperties(costExplanationDTO, costExplanation);
        costExplanation.setProductId(productId);
        costExplanation.setCreateTime(LocalDateTime.now());
        costExplanation.setUpdateTime(LocalDateTime.now());
        
        costExplanationMapper.insert(costExplanation);
    }

    /**
     * 保存预订须知
     */
    private void saveBookingNotice(Long productId, ProductCreateDTO.BookingNoticeDTO bookingNoticeDTO) {
        // 先删除旧的预订须知
        LambdaQueryWrapper<ProductBookingNotice> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.eq(ProductBookingNotice::getProductId, productId);
        bookingNoticeMapper.delete(deleteWrapper);
        
        // 保存新的预订须知
        ProductBookingNotice bookingNotice = new ProductBookingNotice();
        BeanUtils.copyProperties(bookingNoticeDTO, bookingNotice);
        bookingNotice.setProductId(productId);
        bookingNotice.setStatus(1);
        bookingNotice.setSortOrder(0);
        bookingNotice.setCreateTime(LocalDateTime.now());
        bookingNotice.setUpdateTime(LocalDateTime.now());
        
        bookingNoticeMapper.insert(bookingNotice);
    }

    /**
     * 保存行程安排
     */
    private void saveItineraries(Long productId, List<ProductCreateDTO.ItineraryDTO> itineraryDTOs) {
        // 先删除旧的行程安排
        LambdaQueryWrapper<ProductDailyItinerary> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.eq(ProductDailyItinerary::getProductId, productId);
        dailyItineraryMapper.delete(deleteWrapper);
        
        // 保存新的行程安排
        for (ProductCreateDTO.ItineraryDTO itineraryDTO : itineraryDTOs) {
            if (itineraryDTO.getItineraries() != null) {
                for (ProductCreateDTO.ItineraryDetailDTO detailDTO : itineraryDTO.getItineraries()) {
                    ProductDailyItinerary itinerary = new ProductDailyItinerary();
                    BeanUtils.copyProperties(detailDTO, itinerary);
                    itinerary.setProductId(productId);
                    itinerary.setDaySeq(itineraryDTO.getDaySeq());
                    
                    dailyItineraryMapper.insert(itinerary);
                }
            }
        }
    }
}
