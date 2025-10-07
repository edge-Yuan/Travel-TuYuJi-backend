package com.wanderlust.travel.traveladmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 服务记录实体类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("service_record")
public class ServiceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 服务ID
     */
    @TableField("service_id")
    private Long serviceId;

    /**
     * 记录类型：checkin-入住登记，service-服务执行，completion-服务完成，other-其他
     */
    @TableField("record_type")
    private String recordType;

    /**
     * 记录时间
     */
    @TableField("record_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordTime;

    /**
     * 操作人员
     */
    @TableField("staff_name")
    private String staffName;

    /**
     * 操作内容
     */
    @TableField("content")
    private String content;

    /**
     * 备注
     */
    @TableField("notes")
    private String notes;

    /**
     * 相关图片（JSON格式存储）
     */
    @TableField("images")
    private String images;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;
}
