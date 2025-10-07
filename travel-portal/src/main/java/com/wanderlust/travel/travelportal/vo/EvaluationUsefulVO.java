package com.wanderlust.travel.travelportal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 评价有用VO
 */
@Data
@ApiModel(value = "EvaluationUsefulVO", description = "评价有用响应对象")
public class EvaluationUsefulVO {

    @ApiModelProperty("评价ID")
    private Long evalId;

    @ApiModelProperty("是否标记为有用")
    private Boolean isUseful;

    @ApiModelProperty("有用数量")
    private Integer usefulCount;

    @ApiModelProperty("用户ID")
    private Long userId;
}

/**
 * 有用用户列表VO
 */
@Data
@ApiModel(value = "UsefulUserListVO", description = "有用用户列表响应对象")
class UsefulUserListVO {

    @ApiModelProperty("总数")
    private Long total;

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页大小")
    private Integer size;

    @ApiModelProperty("用户列表")
    private List<UsefulUserVO> users;
}

/**
 * 有用用户VO
 */
@Data
@ApiModel(value = "UsefulUserVO", description = "有用用户信息")
class UsefulUserVO {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("标记时间")
    private String usefulTime;
}
