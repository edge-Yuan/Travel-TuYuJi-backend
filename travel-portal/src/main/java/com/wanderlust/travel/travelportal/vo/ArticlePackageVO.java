package com.wanderlust.travel.travelportal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章套餐关联VO
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Data
@ApiModel(value = "ArticlePackageVO", description = "文章套餐关联VO")
public class ArticlePackageVO {

    @ApiModelProperty("关联唯一标识")
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

    @ApiModelProperty("产品名称")
    private String productName;

    @ApiModelProperty("产品主图")
    private String mainImgUrl;

    @ApiModelProperty("产品价格")
    private BigDecimal price;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
