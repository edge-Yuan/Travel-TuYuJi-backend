package com.wanderlust.travel.travelportal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 批量文章套餐关联DTO
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Data
@ApiModel(value = "BatchArticlePackageDTO", description = "批量文章套餐关联DTO")
public class BatchArticlePackageDTO {

    @ApiModelProperty(value = "文章ID", required = true)
    @NotNull(message = "文章ID不能为空")
    private Long articleId;

    @ApiModelProperty(value = "套餐关联列表", required = true)
    @NotNull(message = "套餐关联列表不能为空")
    private List<ArticlePackageDTO> packages;

    @ApiModelProperty("操作类型：ADD-添加，UPDATE-更新，DELETE-删除")
    private String operationType = "ADD";
}
