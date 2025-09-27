package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 产品费用说明表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("product_cost_explanation")
@ApiModel(value = "ProductCostExplanation对象", description = "产品费用说明表")
public class ProductCostExplanation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("费用ID")
    @TableId(value = "cost_id", type = IdType.AUTO)
    private Long costId;

    @ApiModelProperty("关联产品")
    private Long productId;

    @ApiModelProperty("包含费用（如：机票、酒店）")
    private String includeItems;

    @ApiModelProperty("不含费用（如：自费项目、景区小交通）")
    private String excludeItems;

    @ApiModelProperty("退改政策")
    private String refundPolicy;

    public Long getCostId() {
        return costId;
    }

    public void setCostId(Long costId) {
        this.costId = costId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getIncludeItems() {
        return includeItems;
    }

    public void setIncludeItems(String includeItems) {
        this.includeItems = includeItems;
    }

    public String getExcludeItems() {
        return excludeItems;
    }

    public void setExcludeItems(String excludeItems) {
        this.excludeItems = excludeItems;
    }

    public String getRefundPolicy() {
        return refundPolicy;
    }

    public void setRefundPolicy(String refundPolicy) {
        this.refundPolicy = refundPolicy;
    }

    @Override
    public String toString() {
        return "ProductCostExplanation{" +
            "costId = " + costId +
            ", productId = " + productId +
            ", includeItems = " + includeItems +
            ", excludeItems = " + excludeItems +
            ", refundPolicy = " + refundPolicy +
        "}";
    }
}
