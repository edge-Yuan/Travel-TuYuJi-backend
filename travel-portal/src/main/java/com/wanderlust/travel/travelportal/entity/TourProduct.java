package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 旅游产品表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("tour_product")
@ApiModel(value = "TourProduct对象", description = "旅游产品表")
public class TourProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("产品唯一标识")
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    @ApiModelProperty("所属旅行商")
    private Long merchantId;

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("类型：1-旅行路线，2-酒店客房，3-景区门票")
    private Byte productType;

    @ApiModelProperty("售价")
    private BigDecimal price;

    @ApiModelProperty("原价")
    private BigDecimal originalPrice;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("产品生效日期")
    private LocalDate startDate;

    @ApiModelProperty("产品失效日期")
    private LocalDate endDate;

    @ApiModelProperty("产品详情描述")
    private String description;

    @ApiModelProperty("产品图片URL")
    private String imgUrls;

    @ApiModelProperty("审核管理员ID")
    private Long auditAdminId;

    @ApiModelProperty("审核状态：0-待审核，1-已通过，2-已驳回")
    private Byte auditStatus;

    @ApiModelProperty("上架状态：0-下架，1-上架")
    private Byte productStatus;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("产品标签（逗号分隔，如：超值,豪华）")
    private String productTags;

    @ApiModelProperty("已售数量")
    private Integer soldCount;

    @ApiModelProperty("服务保障（逗号分隔，如：退改无忧,安全可靠）")
    private String serviceGuarantees;

    @ApiModelProperty("主图URL（优先展示）")
    private String mainImgUrl;

    @ApiModelProperty("核心卖点（富文本）")
    private String productSellingPoints;

    @ApiModelProperty("所属分类ID（关联product_category）")
    private Long categoryId;

    @ApiModelProperty("供应商")
    private String supplier;

    @ApiModelProperty("产品特色")
    private String features;

    @ApiModelProperty("产品特色图片")
    private String featuresImgs;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Byte getProductType() {
        return productType;
    }

    public void setProductType(Byte productType) {
        this.productType = productType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Long getAuditAdminId() {
        return auditAdminId;
    }

    public void setAuditAdminId(Long auditAdminId) {
        this.auditAdminId = auditAdminId;
    }

    public Byte getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Byte getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Byte productStatus) {
        this.productStatus = productStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getProductTags() {
        return productTags;
    }

    public void setProductTags(String productTags) {
        this.productTags = productTags;
    }

    public Integer getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }

    public String getServiceGuarantees() {
        return serviceGuarantees;
    }

    public void setServiceGuarantees(String serviceGuarantees) {
        this.serviceGuarantees = serviceGuarantees;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public String getProductSellingPoints() {
        return productSellingPoints;
    }

    public void setProductSellingPoints(String productSellingPoints) {
        this.productSellingPoints = productSellingPoints;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getFeaturesImgs() {
        return featuresImgs;
    }

    public void setFeaturesImgs(String featuresImgs) {
        this.featuresImgs = featuresImgs;
    }

    @Override
    public String toString() {
        return "TourProduct{" +
            "productId = " + productId +
            ", merchantId = " + merchantId +
            ", productName = " + productName +
            ", productType = " + productType +
            ", price = " + price +
            ", originalPrice = " + originalPrice +
            ", stock = " + stock +
            ", startDate = " + startDate +
            ", endDate = " + endDate +
            ", description = " + description +
            ", imgUrls = " + imgUrls +
            ", auditAdminId = " + auditAdminId +
            ", auditStatus = " + auditStatus +
            ", productStatus = " + productStatus +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", productTags = " + productTags +
            ", soldCount = " + soldCount +
            ", serviceGuarantees = " + serviceGuarantees +
            ", mainImgUrl = " + mainImgUrl +
            ", productSellingPoints = " + productSellingPoints +
            ", categoryId = " + categoryId +
            ", supplier = " + supplier +
            ", features = " + features +
            "featuresImgs = " + featuresImgs +
        "}";
    }
}
