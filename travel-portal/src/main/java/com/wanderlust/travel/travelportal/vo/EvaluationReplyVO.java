package com.wanderlust.travel.travelportal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 评价回复VO
 */
@Data
@ApiModel(value = "EvaluationReplyVO", description = "评价回复响应对象")
public class EvaluationReplyVO {

    @ApiModelProperty("回复ID")
    private Long replyId;

    @ApiModelProperty("评价ID")
    private Long evalId;

    @ApiModelProperty("回复内容")
    private String replyContent;

    @ApiModelProperty("回复时间")
    private LocalDateTime replyTime;

    @ApiModelProperty("回复者ID")
    private Long replierId;

    @ApiModelProperty("回复者姓名")
    private String replierName;

    @ApiModelProperty("回复者角色")
    private String replierRole;
}
