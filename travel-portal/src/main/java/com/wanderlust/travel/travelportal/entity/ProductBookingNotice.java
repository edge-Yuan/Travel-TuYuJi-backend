package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 产品预订须知表
 * </p>
 *
 * @author wanderlust
 * @since 2025-09-15
 */
@Data
@TableName("product_booking_notice")
@ApiModel(value = "ProductBookingNotice对象", description = "产品预订须知表")
public class ProductBookingNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("须知ID")
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Long noticeId;

    @ApiModelProperty("关联产品")
    private Long productId;

    @ApiModelProperty("预订条件（如：需提供身份证号）")
    private String bookingConditions;

    @ApiModelProperty("有效期（如：购买后30天内有效）")
    private String validityPeriod;

    @ApiModelProperty("其他注意事项")
    private String notes;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getBookingConditions() {
        return bookingConditions;
    }

    public void setBookingConditions(String bookingConditions) {
        this.bookingConditions = bookingConditions;
    }

    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ProductBookingNotice{" +
            "noticeId = " + noticeId +
            ", productId = " + productId +
            ", bookingConditions = " + bookingConditions +
            ", validityPeriod = " + validityPeriod +
            ", notes = " + notes +
        "}";
    }
}
