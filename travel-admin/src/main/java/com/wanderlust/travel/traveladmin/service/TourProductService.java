package com.wanderlust.travel.traveladmin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanderlust.travel.traveladmin.dto.ProductCreateDTO;
import com.wanderlust.travel.traveladmin.dto.ProductUpdateDTO;
import com.wanderlust.travel.traveladmin.dto.ProductSearchDTO;
import com.wanderlust.travel.traveladmin.vo.ProductVO;

import java.util.List;
import java.util.Map;

/**
 * 旅游产品服务接口
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
public interface TourProductService {

    /**
     * 获取所有产品
     * 
     * @param merchantId 商家ID
     * @return 产品列表
     */
    List<ProductVO> getAllProducts(Long merchantId);

    /**
     * 创建产品
     * 
     * @param createDTO 创建DTO
     * @param merchantId 商家ID
     * @return 产品ID
     */
    Long createProduct(ProductCreateDTO createDTO, Long merchantId);

    /**
     * 更新产品
     * 
     * @param productId 产品ID
     * @param createDTO 更新DTO
     * @param merchantId 商家ID
     * @return 是否成功
     */
    boolean updateProduct(Long productId, ProductCreateDTO createDTO, Long merchantId);

    /**
     * 更新产品（使用更新DTO）
     * 
     * @param productId 产品ID
     * @param updateDTO 更新DTO
     * @param merchantId 商家ID
     * @return 是否成功
     */
    boolean updateProduct(Long productId, ProductUpdateDTO updateDTO, Long merchantId);

    /**
     * 删除产品
     * 
     * @param productId 产品ID
     * @param merchantId 商家ID
     * @return 是否成功
     */
    boolean deleteProduct(Long productId, Long merchantId);

    /**
     * 批量更新产品状态
     * 
     * @param productIds 产品ID列表
     * @param status 状态
     * @param merchantId 商家ID
     * @return 更新结果
     */
    Map<String, Integer> batchUpdateStatus(List<Long> productIds, Integer status, Long merchantId);

    /**
     * 获取产品详情
     * 
     * @param productId 产品ID
     * @return 产品详情
     */
    ProductVO getProductDetail(Long productId);

    /**
     * 复制产品
     * 
     * @param productId 产品ID
     * @param merchantId 商家ID
     * @return 复制结果
     */
    Map<String, Object> copyProduct(Long productId, Long merchantId);

    /**
     * 搜索产品
     * 
     * @param page 分页参数
     * @param searchDTO 搜索条件
     * @return 产品列表
     */
    IPage<ProductVO> searchProducts(Page<ProductVO> page, ProductSearchDTO searchDTO);

    /**
     * 获取产品图片列表
     * 
     * @param productId 产品ID
     * @return 图片列表
     */
    List<ProductVO.ProductImageVO> getProductImages(Long productId);

    /**
     * 获取产品主图
     * 
     * @param productId 产品ID
     * @return 主图URL
     */
    String getProductMainImage(Long productId);
}
