package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 热门目的地表
 * </p>
 *
 * @author wanderlust
 * @since 2024-12-19
 */
@TableName("hot_destination")
@ApiModel(value = "HotDestination对象", description = "热门目的地表")
public class HotDestination implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("目的地唯一标识")
    @TableId(value = "dest_id", type = IdType.AUTO)
    private Long destId;

    @ApiModelProperty("目的地名称")
    private String destinationName;

    @ApiModelProperty("所属地区")
    private String region;

    @ApiModelProperty("目的地图片")
    private String imageUrl;

    @ApiModelProperty("目的地描述")
    private String description;

    @ApiModelProperty("浏览次数")
    private Integer viewCount;

    @ApiModelProperty("排序权重")
    private Integer sortOrder;

    @ApiModelProperty("状态：0-下架，1-上架")
    private Byte status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    public Long getDestId() {
        return destId;
    }

    public void setDestId(Long destId) {
        this.destId = destId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "HotDestination{" +
            "destId = " + destId +
            ", destinationName = " + destinationName +
            ", region = " + region +
            ", imageUrl = " + imageUrl +
            ", description = " + description +
            ", viewCount = " + viewCount +
            ", sortOrder = " + sortOrder +
            ", status = " + status +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
        "}";
    }
}
