package com.wanderlust.travel.traveladmin.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 导游信息VO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class GuideVO {

    /**
     * 导游ID
     */
    private Long guideId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 所在地区
     */
    private String location;

    /**
     * 服务评分
     */
    private BigDecimal serviceScore;

    /**
     * 擅长目的地
     */
    private List<String> specialties;

    /**
     * 服务语言
     */
    private List<String> languages;

    /**
     * 服务经验（年）
     */
    private Integer experience;

    /**
     * 服务次数
     */
    private Integer serviceCount;

    /**
     * 基础服务费
     */
    private BigDecimal baseFee;

    /**
     * 节假日加价比例
     */
    private BigDecimal holidayFeeRate;

    /**
     * 个人简介
     */
    private String bio;

    /**
     * 资质状态：0-待审核，1-已通过，2-已驳回
     */
    private Byte qualificationStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 评价列表
     */
    private List<GuideReviewVO> reviews;

    /**
     * 服务案例
     */
    private List<GuideCaseVO> cases;

    /**
     * 导游评价VO
     */
    @Data
    public static class GuideReviewVO {
        private Long reviewId;
        private String reviewerName;
        private Integer rating;
        private String content;
        private String date;
    }

    /**
     * 导游案例VO
     */
    @Data
    public static class GuideCaseVO {
        private Long caseId;
        private String title;
        private String description;
        private List<String> images;
    }
}
