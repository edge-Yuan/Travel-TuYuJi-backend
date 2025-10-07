package com.wanderlust.travel.traveladmin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 服务视图对象
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ServiceVO {

    /**
     * 服务ID
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 客户电话
     */
    private String customerPhone;

    /**
     * 客户身份证号
     */
    private String customerIdCard;

    /**
     * 紧急联系人
     */
    private String emergencyContact;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务图片
     */
    private String serviceImage;

    /**
     * 服务地点
     */
    private String serviceLocation;

    /**
     * 服务时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime serviceTime;

    /**
     * 预计时长
     */
    private String estimatedDuration;

    /**
     * 服务状态
     */
    private String status;

    /**
     * 服务人员
     */
    private String staffName;

    /**
     * 特殊要求
     */
    private String specialRequirements;

    /**
     * 服务记录列表
     */
    private List<ServiceRecordVO> records;

    /**
     * 异常记录列表
     */
    private List<ServiceExceptionVO> exceptions;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
