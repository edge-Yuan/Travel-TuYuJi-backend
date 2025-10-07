package com.wanderlust.travel.traveladmin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

// import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 服务搜索DTO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ServiceSearchDTO {

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 服务状态
     */
    private String status;

    /**
     * 服务日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime serviceDate;

    /**
     * 关键词搜索
     */
    private String keyword;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 服务人员
     */
    private String staffName;

    /**
     * 页码
     */
    // @NotNull(message = "页码不能为空")
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    // @NotNull(message = "每页大小不能为空")
    private Integer pageSize = 10;
}
