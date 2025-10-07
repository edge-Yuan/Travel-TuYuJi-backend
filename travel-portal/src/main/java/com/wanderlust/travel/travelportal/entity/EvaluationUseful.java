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
 * 评价有用实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("evaluation_useful")
@ApiModel(value = "EvaluationUseful对象", description = "评价有用表")
public class EvaluationUseful implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("评价ID")
    private Long evalId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("标记时间")
    private LocalDateTime usefulTime;

    @ApiModelProperty("状态")
    private Byte status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;
}
