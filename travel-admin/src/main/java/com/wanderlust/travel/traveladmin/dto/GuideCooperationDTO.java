package com.wanderlust.travel.traveladmin.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

/**
 * 导游合作邀请DTO
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class GuideCooperationDTO {

    /**
     * 导游ID
     */
    @NotNull(message = "导游ID不能为空")
    private Long guideId;

    /**
     * 合作类型：1-独家合作，2-合作伙伴，3-项目合作
     */
    @NotNull(message = "合作类型不能为空")
    private Byte cooperationType;

    /**
     * 合作项目ID列表
     */
    @NotEmpty(message = "合作项目不能为空")
    private List<Long> projectIds;

    /**
     * 合作开始日期
     */
    @NotNull(message = "合作开始日期不能为空")
    private LocalDate startDate;

    /**
     * 合作结束日期
     */
    @NotNull(message = "合作结束日期不能为空")
    private LocalDate endDate;

    /**
     * 合作条件
     */
    private String conditions;

    /**
     * 备注
     */
    private String notes;
}
