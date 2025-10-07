package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 文章关联套餐表
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@TableName("article_package")
@ApiModel(value = "ArticlePackage对象", description = "文章关联套餐表")
public class ArticlePackage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关联唯一标识")
    @TableId(value = "rel_id", type = IdType.AUTO)
    private Long relId;

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("产品ID")
    private Long productId;

    @ApiModelProperty("套餐名称")
    private String packageName;

    @ApiModelProperty("套餐图片")
    private String packageImage;

    @ApiModelProperty("套餐价格")
    private BigDecimal packagePrice;

    @ApiModelProperty("排序权重")
    private Integer sortOrder;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageImage() {
        return packageImage;
    }

    public void setPackageImage(String packageImage) {
        this.packageImage = packageImage;
    }

    public BigDecimal getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(BigDecimal packagePrice) {
        this.packagePrice = packagePrice;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ArticlePackage{" +
            "relId = " + relId +
            ", articleId = " + articleId +
            ", productId = " + productId +
            ", packageName = " + packageName +
            ", packageImage = " + packageImage +
            ", packagePrice = " + packagePrice +
            ", sortOrder = " + sortOrder +
            ", createTime = " + createTime +
        "}";
    }
}
