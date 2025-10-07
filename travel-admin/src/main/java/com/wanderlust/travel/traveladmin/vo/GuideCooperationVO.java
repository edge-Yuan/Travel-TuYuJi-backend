package com.wanderlust.travel.traveladmin.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 导游合作邀请VO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class GuideCooperationVO {

    /**
     * 合作邀请ID
     */
    private Long cooperationId;

    /**
     * 旅行商ID
     */
    private Long merchantId;

    /**
     * 旅行商名称
     */
    private String merchantName;

    /**
     * 导游ID
     */
    private Long guideId;

    /**
     * 导游姓名
     */
    private String guideName;

    /**
     * 导游头像
     */
    private String guideAvatar;

    /**
     * 合作类型：1-独家合作，2-合作伙伴，3-项目合作
     */
    private Byte cooperationType;

    /**
     * 合作类型名称
     */
    private String cooperationTypeName;

    /**
     * 合作项目列表
     */
    private List<CooperationProjectVO> projects;

    /**
     * 合作开始日期
     */
    private LocalDate startDate;

    /**
     * 合作结束日期
     */
    private LocalDate endDate;

    /**
     * 合作条件
     */
    private String conditions;

    /**
     * 备注
     */
    private String notes;

    /**
     * 合作状态：0-待确认，1-已接受，2-已拒绝，3-已结束
     */
    private Byte status;

    /**
     * 合作状态名称
     */
    private String statusName;

    /**
     * 拒绝原因
     */
    private String rejectReason;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 响应时间
     */
    private LocalDateTime responseTime;

    /**
     * 合作项目VO
     */
    @Data
    public static class CooperationProjectVO {
        private Long productId;
        private String productName;
        private String productImage;
    }
}
