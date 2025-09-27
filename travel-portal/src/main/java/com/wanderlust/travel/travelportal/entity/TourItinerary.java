package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 行程安排表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("tour_itinerary")
@ApiModel(value = "TourItinerary对象", description = "行程安排表")
public class TourItinerary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("行程唯一标识")
    @TableId(value = "itinerary_id", type = IdType.AUTO)
    private Long itineraryId;

    @ApiModelProperty("关联订单")
    private Long orderId;

    @ApiModelProperty("执行导游")
    private Long guideId;

    @ApiModelProperty("行程日期")
    private LocalDate itineraryDate;

    @ApiModelProperty("行程天数序号")
    private Integer daySeq;

    @ApiModelProperty("当日景点")
    private String spots;

    @ApiModelProperty("当日集合时间")
    private LocalTime startTime;

    @ApiModelProperty("当日结束时间")
    private LocalTime endTime;

    @ApiModelProperty("交通方式")
    private String traffic;

    @ApiModelProperty("餐饮安排")
    private String dining;

    @ApiModelProperty("住宿安排")
    private String accommodation;

    @ApiModelProperty("行程状态：0-未开始，1-进行中，2-已完成")
    private Byte status;

    @ApiModelProperty("进度备注")
    private String progressNote;

    @ApiModelProperty("行程照片")
    private String imgUrls;

    public Long getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    public LocalDate getItineraryDate() {
        return itineraryDate;
    }

    public void setItineraryDate(LocalDate itineraryDate) {
        this.itineraryDate = itineraryDate;
    }

    public Integer getDaySeq() {
        return daySeq;
    }

    public void setDaySeq(Integer daySeq) {
        this.daySeq = daySeq;
    }

    public String getSpots() {
        return spots;
    }

    public void setSpots(String spots) {
        this.spots = spots;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getDining() {
        return dining;
    }

    public void setDining(String dining) {
        this.dining = dining;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getProgressNote() {
        return progressNote;
    }

    public void setProgressNote(String progressNote) {
        this.progressNote = progressNote;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    @Override
    public String toString() {
        return "TourItinerary{" +
            "itineraryId = " + itineraryId +
            ", orderId = " + orderId +
            ", guideId = " + guideId +
            ", itineraryDate = " + itineraryDate +
            ", daySeq = " + daySeq +
            ", spots = " + spots +
            ", startTime = " + startTime +
            ", endTime = " + endTime +
            ", traffic = " + traffic +
            ", dining = " + dining +
            ", accommodation = " + accommodation +
            ", status = " + status +
            ", progressNote = " + progressNote +
            ", imgUrls = " + imgUrls +
        "}";
    }
}
