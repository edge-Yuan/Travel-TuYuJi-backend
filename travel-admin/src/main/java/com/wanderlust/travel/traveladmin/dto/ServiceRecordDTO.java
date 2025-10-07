package com.wanderlust.travel.traveladmin.dto;

import lombok.Data;

// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 服务记录DTO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class ServiceRecordDTO {

    /**
     * 服务ID
     */
    // @NotNull(message = "服务ID不能为空")
    private Long serviceId;

    /**
     * 记录类型
     */
    // @NotBlank(message = "记录类型不能为空")
    private String recordType;

    /**
     * 操作内容
     */
    // @NotBlank(message = "操作内容不能为空")
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
