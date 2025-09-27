package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 导游扩展信息表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("guide_extend")
@ApiModel(value = "GuideExtend对象", description = "导游扩展信息表")
public class GuideExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("导游唯一标识")
    @TableId(value = "guide_id", type = IdType.AUTO)
    private Long guideId;

    @ApiModelProperty("关联用户表")
    private Long userId;

    @ApiModelProperty("导游证号（唯一）")
    private String guideCard;

    @ApiModelProperty("健康证明路径")
    private String healthCert;

    @ApiModelProperty("无不良记录证明路径")
    private String noCrimeProve;

    @ApiModelProperty("资质状态：0-待审核，1-已通过，2-已驳回")
    private Byte qualificationStatus;

    @ApiModelProperty("服务评分（0-5分）")
    private BigDecimal serviceScore;

    @ApiModelProperty("擅长目的地")
    private String goodAtArea;

    @ApiModelProperty("服务语言")
    private String serviceLang;

    @ApiModelProperty("基础服务费")
    private BigDecimal baseFee;

    @ApiModelProperty("节假日加价比例")
    private BigDecimal holidayFeeRate;

    @ApiModelProperty("结算银行卡号")
    private String bankCard;

    @ApiModelProperty("开户银行")
    private String bankName;
    
    @ApiModelProperty("真实姓名")
    private String realName;
    
    @ApiModelProperty("手机号")
    private String phone;
    
    @ApiModelProperty("所在地区")
    private String location;
    
    @ApiModelProperty("驳回原因")
    private String rejectReason;

    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGuideCard() {
        return guideCard;
    }

    public void setGuideCard(String guideCard) {
        this.guideCard = guideCard;
    }

    public String getHealthCert() {
        return healthCert;
    }

    public void setHealthCert(String healthCert) {
        this.healthCert = healthCert;
    }

    public String getNoCrimeProve() {
        return noCrimeProve;
    }

    public void setNoCrimeProve(String noCrimeProve) {
        this.noCrimeProve = noCrimeProve;
    }

    public Byte getQualificationStatus() {
        return qualificationStatus;
    }

    public void setQualificationStatus(Byte qualificationStatus) {
        this.qualificationStatus = qualificationStatus;
    }

    public BigDecimal getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(BigDecimal serviceScore) {
        this.serviceScore = serviceScore;
    }

    public String getGoodAtArea() {
        return goodAtArea;
    }

    public void setGoodAtArea(String goodAtArea) {
        this.goodAtArea = goodAtArea;
    }

    public String getServiceLang() {
        return serviceLang;
    }

    public void setServiceLang(String serviceLang) {
        this.serviceLang = serviceLang;
    }

    public BigDecimal getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(BigDecimal baseFee) {
        this.baseFee = baseFee;
    }

    public BigDecimal getHolidayFeeRate() {
        return holidayFeeRate;
    }

    public void setHolidayFeeRate(BigDecimal holidayFeeRate) {
        this.holidayFeeRate = holidayFeeRate;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getRejectReason() {
        return rejectReason;
    }
    
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Override
    public String toString() {
        return "GuideExtend{" +
            "guideId = " + guideId +
            ", userId = " + userId +
            ", guideCard = " + guideCard +
            ", healthCert = " + healthCert +
            ", noCrimeProve = " + noCrimeProve +
            ", qualificationStatus = " + qualificationStatus +
            ", serviceScore = " + serviceScore +
            ", goodAtArea = " + goodAtArea +
            ", serviceLang = " + serviceLang +
            ", baseFee = " + baseFee +
            ", holidayFeeRate = " + holidayFeeRate +
            ", bankCard = " + bankCard +
            ", bankName = " + bankName +
        "}";
    }
}
