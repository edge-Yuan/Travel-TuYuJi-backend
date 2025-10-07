package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人ID")
    private Long creatorId;

    @ApiModelProperty("状态：0-禁用，1-启用")
    private Byte status;

    @ApiModelProperty("排序序号")
    private Integer sortOrder;

    @ApiModelProperty("须知标题")
    private String title;

    @ApiModelProperty("预订截止时间说明")
    private String bookingDeadline;

    @ApiModelProperty("取消政策")
    private String cancellationPolicy;

    @ApiModelProperty("退款政策")
    private String refundPolicy;

    @ApiModelProperty("特殊要求说明")
    private String specialRequirements;

    @ApiModelProperty("联系方式")
    private String contactInfo;

    @ApiModelProperty("紧急联系方式")
    private String emergencyContact;

    @Override
    public String toString() {
        return "ProductBookingNotice{" +
            "noticeId = " + noticeId +
            ", productId = " + productId +
            ", bookingConditions = " + bookingConditions +
            ", validityPeriod = " + validityPeriod +
            ", notes = " + notes +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", creatorId = " + creatorId +
            ", status = " + status +
            ", sortOrder = " + sortOrder +
            ", title = " + title +
            ", bookingDeadline = " + bookingDeadline +
            ", cancellationPolicy = " + cancellationPolicy +
            ", refundPolicy = " + refundPolicy +
            ", specialRequirements = " + specialRequirements +
            ", contactInfo = " + contactInfo +
            ", emergencyContact = " + emergencyContact +
        "}";
    }
}
