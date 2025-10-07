package com.wanderlust.travel.traveladmin.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 当前页码
     */
    private Long page;

    /**
     * 每页大小
     */
    private Long size;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 数据列表
     */
    private List<T> records;

    public PageResult() {}

    public PageResult(Long total, Long page, Long size, List<T> records) {
        this.total = total;
        this.page = page;
        this.size = size;
        this.pages = (total + size - 1) / size;
        this.records = records;
    }

    /**
     * 创建分页结果
     */
    public static <T> PageResult<T> of(Long total, Long page, Long size, List<T> records) {
        return new PageResult<>(total, page, size, records);
    }
}
