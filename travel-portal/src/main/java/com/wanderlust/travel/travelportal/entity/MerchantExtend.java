package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 旅行商扩展信息表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@TableName("merchant_extend")
@ApiModel(value = "MerchantExtend对象", description = "旅行商扩展信息表")
public class MerchantExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("旅行商唯一标识")
    @TableId(value = "merchant_id", type = IdType.AUTO)
    private Long merchantId;

    @ApiModelProperty("关联用户表")
    private Long userId;

    @ApiModelProperty("旅行商名称（唯一）")
    private String merchantName;

    @ApiModelProperty("类型：1-酒店，2-景区，3-旅行社")
    private Byte merchantType;

    @ApiModelProperty("营业执照路径")
    private String license;

    @ApiModelProperty("联系人")
    private String contactPerson;

    @ApiModelProperty("联系电话")
    private String contactPhone;

    @ApiModelProperty("地址")
    private String address;
    
    @ApiModelProperty("公司名称")
    private String companyName;
    
    @ApiModelProperty("审核状态：0-待审核，1-已通过，2-已驳回")
    private Byte auditStatus;
    
    @ApiModelProperty("所在地区")
    private String location;
    
    @ApiModelProperty("驳回原因")
    private String rejectReason;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Byte getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Byte merchantType) {
        this.merchantType = merchantType;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public Byte getAuditStatus() {
        return auditStatus;
    }
    
    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
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
        return "MerchantExtend{" +
            "merchantId = " + merchantId +
            ", userId = " + userId +
            ", merchantName = " + merchantName +
            ", merchantType = " + merchantType +
            ", license = " + license +
            ", contactPerson = " + contactPerson +
            ", contactPhone = " + contactPhone +
            ", address = " + address +
        "}";
    }
}
