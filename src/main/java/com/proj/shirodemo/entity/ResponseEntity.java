package com.proj.shirodemo.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 *
 * @author wuge
 * @date 2019/12/20
 */
public class ResponseEntity<T> implements Serializable {
    /**
     * 状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据体
     */
    private T data;

    /**
     * 设置状态码
     *
     * @param responseCode
     * @return
     */
    public ResponseEntity setCode(int responseCode) {
        this.code = responseCode;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 设置返回信息
     *
     * @param message
     * @return
     */
    public ResponseEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    /**
     * 设置返回数据体
     *
     * @param data
     * @return
     */
    public ResponseEntity setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
