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
 * 文章主表
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article")
@ApiModel(value = "Article对象", description = "文章主表")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("文章标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("作者")
    @TableField("author")
    private String author;

    @ApiModelProperty("文章分类：hotel-酒店推荐，attraction-景点介绍，food-美食攻略，guide-旅游攻略")
    @TableField("category")
    private String category;

    @ApiModelProperty("封面图片URL")
    @TableField("cover_image")
    private String coverImage;

    @ApiModelProperty("文章摘要")
    @TableField("summary")
    private String summary;

    @ApiModelProperty("文章内容（支持Markdown）")
    @TableField("content")
    private String content;

    @ApiModelProperty("文章标签（JSON数组）")
    @TableField("tags")
    private String tags;

    @ApiModelProperty("地区")
    @TableField("region")
    private String region;

    @ApiModelProperty("是否精选：0-否，1-是")
    @TableField("featured")
    private Boolean featured;

    @ApiModelProperty("发布状态：draft-草稿，published-已发布，pending-待审核")
    @TableField("status")
    private String status;

    @ApiModelProperty("浏览次数")
    @TableField("views")
    private Integer views;

    @ApiModelProperty("点赞数")
    @TableField("likes")
    private Integer likes;

    @ApiModelProperty("评论数")
    @TableField("comments")
    private Integer comments;

    @ApiModelProperty("发布时间")
    @TableField("publish_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime;

    @ApiModelProperty("SEO标题")
    @TableField("seo_title")
    private String seoTitle;

    @ApiModelProperty("SEO关键词")
    @TableField("seo_keywords")
    private String seoKeywords;

    @ApiModelProperty("SEO描述")
    @TableField("seo_description")
    private String seoDescription;

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

    @ApiModelProperty("关联套餐列表（前端使用）")
    @TableField(exist = false)
    private List<ArticlePackage> relatedPackages;

    @ApiModelProperty("关联产品ID列表（前端使用，与relatedPackages对应）")
    @TableField(exist = false)
    private List<Long> relatedProducts;
}
