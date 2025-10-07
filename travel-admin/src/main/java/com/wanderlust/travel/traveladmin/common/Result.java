package com.wanderlust.travel.traveladmin.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应结果类
 * 
 * @author wanderlust
 * @since 2024-01-15
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码：1-成功，0-失败
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public Result() {}

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功响应
     */
    public static <T> Result<T> success() {
        return new Result<>(1, "操作成功", null);
    }

    /**
     * 成功响应
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(1, "操作成功", data);
    }

    /**
     * 成功响应
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(1, msg, data);
    }

    /**
     * 成功响应（带消息）
     */
    public static <T> Result<T> success(String msg) {
        return new Result<>(1, msg, null);
    }

    /**
     * 失败响应
     */
    public static <T> Result<T> error() {
        return new Result<>(0, "操作失败", null);
    }

    /**
     * 失败响应
     */
    public static <T> Result<T> error(String msg) {
        return new Result<>(0, msg, null);
    }

    /**
     * 失败响应
     */
    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }
}
