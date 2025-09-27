package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 产品固定每日行程表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("product_daily_itinerary")
@ApiModel(value = "ProductDailyItinerary对象", description = "产品固定每日行程表")
public class ProductDailyItinerary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("行程ID")
    @TableId(value = "itinerary_id", type = IdType.AUTO)
    private Long itineraryId;

    @ApiModelProperty("关联产品")
    private Long productId;

    @ApiModelProperty("第几天（1开始）")
    private Integer daySeq;

    @ApiModelProperty("当日标题（如：抵达三亚-海滩漫步）")
    private String title;

    @ApiModelProperty("当日详情（含时间安排、景点介绍）")
    private String description;

    @ApiModelProperty("餐饮安排（如：含早中晚餐）")
    private String meals;

    @ApiModelProperty("交通方式")
    private String traffic;

    @ApiModelProperty("住宿安排")
    private String accommodation;

    @ApiModelProperty("时间段（如：早上、中午、下午、晚上）")
    private String timePeriod;

    public Long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getDaySeq() {
        return daySeq;
    }

    public void setDaySeq(Integer daySeq) {
        this.daySeq = daySeq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeals() {
        return meals;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString() {
        return "ProductDailyItinerary{" +
            "itineraryId = " + itineraryId +
            ", productId = " + productId +
            ", daySeq = " + daySeq +
            ", title = " + title +
            ", description = " + description +
            ", meals = " + meals +
            ", traffic = " + traffic +
            ", accommodation = " + accommodation +
            ", timePeriod = " + timePeriod +
        "}";
    }
}
