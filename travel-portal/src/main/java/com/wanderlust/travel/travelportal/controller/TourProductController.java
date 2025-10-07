package com.wanderlust.travel.travelportal.controller;

import com.wanderlust.travel.travelportal.common.result.Result;
import com.wanderlust.travel.travelportal.entity.TourProduct;
import com.wanderlust.travel.travelportal.service.ITourProductService;
import com.wanderlust.travel.travelportal.vo.BuyProductsDetailVO;
import com.wanderlust.travel.travelportal.vo.TourProductScanVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 旅游产品表 前端控制器
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@RestController
@RequestMapping("/travel-portal/tourProduct")
@Slf4j
public class TourProductController {
    @Autowired
    private ITourProductService tourProductService;
    @GetMapping("/getAllProducts")
    public Result<List<TourProductScanVO>> getAllProducts()
    {
        List<TourProduct> tourProducts = tourProductService.getAllProducts();
        List<TourProductScanVO> productVOList = tourProducts.stream()
                .map(product -> TourProductScanVO.builder()
                        .productId(product.getProductId()) // 从数据库实体中获取产品ID（必须赋值）
                        .productName(product.getProductName()) // 从数据库实体中获取产品名（必须赋值）
                        .price(product.getPrice()) // 从数据库实体中获取价格
                        .imgUrls(product.getImgUrls()) // 从数据库实体中获取图片地址
                        .soldCount(product.getSoldCount()) // 从数据库实体中获取销量
                        .build())
                .collect(Collectors.toList());
        return Result.success(productVOList);
    }

    @GetMapping("/getAllProductsDetails")
    public Result<List<BuyProductsDetailVO>> getAllProductsDetails() {
        List<TourProduct> tourProducts = tourProductService.getAllProducts();
        List<BuyProductsDetailVO> productDetailsVOList = tourProducts.stream()
                .map(product -> BuyProductsDetailVO.builder()
                        .productName(product.getProductName()) // 从数据库实体中获取产品名（必须赋值）
                        .price(product.getPrice()) // 从数据库实体中获取价格
                        .description(product.getDescription()) // 从数据库实体中获取描述
                        .productTags(product.getProductTags()) // 从数据库实体中获取标签
                        .stock(product.getStock()) // 从数据库实体中获取库存
                        .imgUrls(product.getImgUrls())
                        .serviceGuarantees(product.getServiceGuarantees())
                        .merchantId(product.getMerchantId())
                        .productSellingPoints(product.getProductSellingPoints())
                        .supplier(product.getSupplier())
                        .features(product.getFeatures())
                        .featuresImgs(product.getFeaturesImgs())
                        .build())
                .collect(Collectors.toList());
        return Result.success(productDetailsVOList);
    }

    @GetMapping("/getProductDetail/{productId}")
    public Result<BuyProductsDetailVO> getProductDetail(@PathVariable Long productId) {
        TourProduct tourProduct = tourProductService.getById(productId);
        if (tourProduct == null) {
            return Result.error("产品不存在");
        }
        BuyProductsDetailVO productDetailsVO = BuyProductsDetailVO.builder()
                .productName(tourProduct.getProductName()) // 从数据库实体中获取产品名（必须赋值）
                .price(tourProduct.getPrice()) // 从数据库实体中获取价格
                .description(tourProduct.getDescription()) // 从数据库实体中获取描述
                .productTags(tourProduct.getProductTags()) // 从数据库实体中获取标签
                .stock(tourProduct.getStock()) // 从数据库实体中获取库存
                .soldCount(tourProduct.getSoldCount()) // 从数据库实体中获取销量
                .imgUrls(tourProduct.getImgUrls())// 从数据库实体中获取图片地址
                .serviceGuarantees(tourProduct.getServiceGuarantees()) // 从数据库实体中获取服务保证
                .merchantId(tourProduct.getMerchantId()) // 从数据库实体中获取商户ID
                .productSellingPoints(tourProduct.getProductSellingPoints()) // 从数据库实体中获取产品sellingPoints
                .supplier(tourProduct.getSupplier()) // 从数据库实体中获取供应商
                .features(tourProduct.getFeatures()) // 从数据库实体中获取产品features
                .featuresImgs(tourProduct.getFeaturesImgs())
                .build();
                return Result.success(productDetailsVO);
    }

    @PostMapping("/create")
    public Result<Boolean> createProduct(@RequestBody TourProduct tourProduct) {
        log.info("创建产品: {}", tourProduct);
        boolean success = tourProductService.save(tourProduct);
        return success ? Result.success(true) : Result.error("创建产品失败");
    }

    @PutMapping("/update")
    public Result<Boolean> updateProduct(@RequestBody TourProduct tourProduct) {
        log.info("更新产品: {}", tourProduct);
        boolean success = tourProductService.updateById(tourProduct);
        return success ? Result.success(true) : Result.error("更新产品失败");
    }

    @DeleteMapping("/{productId}")
    public Result<Boolean> deleteProduct(@PathVariable Long productId) {
        log.info("删除产品: {}", productId);
        boolean success = tourProductService.removeById(productId);
        return success ? Result.success(true) : Result.error("删除产品失败");
    }

    @GetMapping("/search")
    public Result<List<TourProductScanVO>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Byte productType,
            @RequestParam(required = false) Long merchantId) {
        List<TourProduct> products = tourProductService.lambdaQuery()
                .like(keyword != null, TourProduct::getProductName, keyword)
                .eq(productType != null, TourProduct::getProductType, productType)
                .eq(merchantId != null, TourProduct::getMerchantId, merchantId)
                .eq(TourProduct::getProductStatus, (byte)1) // 只查询上架产品
                .list();
        
        List<TourProductScanVO> productVOList = products.stream()
                .map(product -> TourProductScanVO.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .imgUrls(product.getImgUrls())
                        .soldCount(product.getSoldCount())
                        .build())
                .collect(Collectors.toList());
        return Result.success(productVOList);
    }

    @GetMapping("/getProductsByType/{productType}")
    public Result<List<TourProductScanVO>> getProductsByType(@PathVariable Byte productType) {
        List<TourProduct> products = tourProductService.lambdaQuery()
                .eq(TourProduct::getProductType, productType)
                .eq(TourProduct::getProductStatus, (byte)1) // 只查询上架产品
                .orderByDesc(TourProduct::getSoldCount) // 按销量排序
                .list();
        
        List<TourProductScanVO> productVOList = products.stream()
                .map(product -> TourProductScanVO.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .imgUrls(product.getImgUrls())
                        .soldCount(product.getSoldCount())
                        .build())
                .collect(Collectors.toList());
        return Result.success(productVOList);
    }

    @GetMapping("/getHotProducts")
    public Result<List<TourProductScanVO>> getHotProducts(@RequestParam(defaultValue = "10") Integer limit) {
        List<TourProduct> products = tourProductService.lambdaQuery()
                .eq(TourProduct::getProductStatus, (byte)1) // 只查询上架产品
                .orderByDesc(TourProduct::getSoldCount) // 按销量排序
                .last("LIMIT " + limit)
                .list();
        
        List<TourProductScanVO> productVOList = products.stream()
                .map(product -> TourProductScanVO.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .imgUrls(product.getImgUrls())
                        .soldCount(product.getSoldCount())
                        .build())
                .collect(Collectors.toList());
        return Result.success(productVOList);
    }

    @GetMapping("/getProductsByMerchant/{merchantId}")
    public Result<List<TourProductScanVO>> getProductsByMerchant(@PathVariable Long merchantId) {
        List<TourProduct> products = tourProductService.lambdaQuery()
                .eq(TourProduct::getMerchantId, merchantId)
                .eq(TourProduct::getProductStatus, (byte)1) // 只查询上架产品
                .orderByDesc(TourProduct::getCreateTime)
                .list();
        
        List<TourProductScanVO> productVOList = products.stream()
                .map(product -> TourProductScanVO.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .imgUrls(product.getImgUrls())
                        .soldCount(product.getSoldCount())
                        .build())
                .collect(Collectors.toList());
        return Result.success(productVOList);
    }
}
