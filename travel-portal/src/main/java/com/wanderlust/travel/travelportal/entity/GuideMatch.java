package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 导游匹配需求表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("guide_match")
@ApiModel(value = "GuideMatch对象", description = "导游匹配需求表")
public class GuideMatch implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("匹配需求唯一标识")
    @TableId(value = "match_id", type = IdType.AUTO)
    private Long matchId;

    @ApiModelProperty("发起需求游客")
    private Long userId;

    @ApiModelProperty("目的地")
    private String destination;

    @ApiModelProperty("出行日期")
    private LocalDate travelDate;

    @ApiModelProperty("行程天数")
    private Integer travelDays;

    @ApiModelProperty("出行人数")
    private Integer personCount;

    @ApiModelProperty("导游服务预算（元）")
    private BigDecimal budget;

    @ApiModelProperty("特殊需求")
    private String serviceRequire;

    @ApiModelProperty("匹配状态：0-待接单，1-已匹配，2-已取消")
    private Byte matchStatus;

    @ApiModelProperty("匹配成功的导游ID")
    private Long guideId;

    @ApiModelProperty("支付定金金额")
    private BigDecimal depositAmount;

    @ApiModelProperty("定金支付时间")
    private LocalDateTime depositTime;

    @ApiModelProperty("需求创建时间")
    private LocalDateTime createTime;

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public Integer getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(Integer travelDays) {
        this.travelDays = travelDays;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getServiceRequire() {
        return serviceRequire;
    }

    public void setServiceRequire(String serviceRequire) {
        this.serviceRequire = serviceRequire;
    }

    public Byte getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Byte matchStatus) {
        this.matchStatus = matchStatus;
    }

    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public LocalDateTime getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(LocalDateTime depositTime) {
        this.depositTime = depositTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "GuideMatch{" +
            "matchId = " + matchId +
            ", userId = " + userId +
            ", destination = " + destination +
            ", travelDate = " + travelDate +
            ", travelDays = " + travelDays +
            ", personCount = " + personCount +
            ", budget = " + budget +
            ", serviceRequire = " + serviceRequire +
            ", matchStatus = " + matchStatus +
            ", guideId = " + guideId +
            ", depositAmount = " + depositAmount +
            ", depositTime = " + depositTime +
            ", createTime = " + createTime +
        "}";
    }
}
