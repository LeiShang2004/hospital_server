package com.sheng.hospital_server.comnon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

/**
 * 通用响应类
 *
 * @param <T>
 */
@Getter
public class CommonResponse<T> {

    private final Integer code;
    private final String message;
    private final T data;

    //用来给静态函数的接口构造CommonResponse
    protected CommonResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    protected CommonResponse(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getDescription();
        this.data = data;
    }

    //这个不需要序列化
    @JsonIgnore
    //用于判断成功与否
    public boolean isSuccess() {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    //请求成功，无数据返回
    public static <T> CommonResponse<T> createForSuccess() {
        return new CommonResponse<>(ResponseCode.SUCCESS, null);
    }

    //请求成功，并返回响应数据
    public static <T> CommonResponse<T> createForSuccess(T data) {
        return new CommonResponse<>(ResponseCode.SUCCESS, data);
    }

    //请求错误，默认错误信息
    public static <T> CommonResponse<T> createForError() {
        return new CommonResponse<>(ResponseCode.ERROR, null);
    }

    //请求错误，返回ResponseCode的错误信息
    public static <T> CommonResponse<T> createForError(ResponseCode responseCode) {
        return new CommonResponse<>(responseCode, null);
    }

    //请求错误，指定错误信息
    public static <T> CommonResponse<T> createForError(String err_message) {
        return new CommonResponse<>(ResponseCode.ERROR.getCode(), err_message, null);
    }

    //请求错误，指定错误码和错误信息
    public static <T> CommonResponse<T> createForError(Integer code, String err_message) {
        return new CommonResponse<>(code, err_message, null);
    }

}
