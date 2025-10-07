package com.wanderlust.travel.traveladmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文章模板表
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article_template")
@ApiModel(value = "ArticleTemplate对象", description = "文章模板表")
public class ArticleTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("模板ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("模板名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("模板描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("适用分类")
    @TableField("category")
    private String category;

    @ApiModelProperty("模板标签（JSON数组）")
    @TableField("tags")
    private String tags;

    @ApiModelProperty("模板内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("是否启用：0-否，1-是")
    @TableField("is_active")
    private Boolean isActive;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 非数据库字段，用于前端展示
    @ApiModelProperty("标签列表（前端使用）")
    @TableField(exist = false)
    private List<String> tagList;
}
