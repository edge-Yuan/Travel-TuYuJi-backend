package com.wanderlust.travel.travelportal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 评价回复DTO
 */
@Data
@ApiModel(value = "EvaluationReplyDTO", description = "评价回复请求对象")
public class EvaluationReplyDTO {

    @ApiModelProperty("回复内容")
    @NotBlank(message = "回复内容不能为空")
    @Size(min = 5, max = 500, message = "回复内容长度必须在5-500个字符之间")
    private String replyContent;
}
