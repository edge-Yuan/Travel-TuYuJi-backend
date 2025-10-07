package com.wanderlust.travel.travelportal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * <p>
 * 文章套餐关联DTO
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Data
@ApiModel(value = "ArticlePackageDTO", description = "文章套餐关联DTO")
public class ArticlePackageDTO {

    @ApiModelProperty("关联唯一标识（更新时必填）")
    private Long relId;

    @ApiModelProperty(value = "文章ID", required = true)
    @NotNull(message = "文章ID不能为空")
    private Long articleId;

    @ApiModelProperty(value = "产品ID", required = true)
    @NotNull(message = "产品ID不能为空")
    private Long productId;

    @ApiModelProperty("套餐名称")
    private String packageName;

    @ApiModelProperty("套餐图片")
    private String packageImage;

    @ApiModelProperty("套餐价格")
    private BigDecimal packagePrice;

    @ApiModelProperty("排序权重")
    private Integer sortOrder;
}
