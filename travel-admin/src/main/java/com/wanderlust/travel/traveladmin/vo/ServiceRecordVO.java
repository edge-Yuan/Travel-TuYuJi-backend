package com.wanderlust.travel.traveladmin.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 服务记录视图对象
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ServiceRecordVO {

    /**
     * 记录ID
     */
    private Long id;

    /**
     * 服务ID
     */
    private Long serviceId;

    /**
     * 记录类型
     */
    private String recordType;

    /**
     * 记录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;

    /**
     * 操作人员
     */
    private String staffName;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 备注
     */
    private String notes;

    /**
     * 相关图片
     */
    private List<String> images;
}
