package com.cb.entity.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: mtdp
 * @Date: 2020-08-30
 */

@Data
public class Result<T> {
    // 本次请求的状态码，200表示成功
    private int code;
    // 本次请求的详情
    private String message;
    // 本次请求返回的结果集
    private T data;
}
