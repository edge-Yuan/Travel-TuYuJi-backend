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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章统计数据表
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article_statistics")
@ApiModel(value = "ArticleStatistics对象", description = "文章统计数据表")
public class ArticleStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("统计ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("统计日期")
    @TableField("stat_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate statDate;

    @ApiModelProperty("总文章数")
    @TableField("total_articles")
    private Integer totalArticles;

    @ApiModelProperty("已发布文章数")
    @TableField("published_articles")
    private Integer publishedArticles;

    @ApiModelProperty("草稿文章数")
    @TableField("draft_articles")
    private Integer draftArticles;

    @ApiModelProperty("待审核文章数")
    @TableField("pending_articles")
    private Integer pendingArticles;

    @ApiModelProperty("总阅读量")
    @TableField("total_views")
    private Integer totalViews;

    @ApiModelProperty("总点赞数")
    @TableField("total_likes")
    private Integer totalLikes;

    @ApiModelProperty("总评论数")
    @TableField("total_comments")
    private Integer totalComments;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
