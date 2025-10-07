package com.wanderlust.travel.travelportal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 评价回复实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("evaluation_reply")
@ApiModel(value = "EvaluationReply对象", description = "评价回复表")
public class EvaluationReply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("回复ID")
    @TableId(value = "reply_id", type = IdType.AUTO)
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

    @ApiModelProperty("状态")
    private Byte status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;
}
