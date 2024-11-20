package com.sheng.hospital_server.comnon;

import lombok.Getter;

@Getter
public enum ResponseCode {
    // 成功
    SUCCESS(0, "SUCCESS"),
    // 错误
    ERROR(1, "ERROR"),
    // 参数不合法
    ARGUMENT_ILLEGAL(10, "ARGUMENT_ILLEGAL"),
    // 需要登录
    NEED_LOGIN(11, "NEED_LOGIN"),
    // 文件服务错误
    FILE_SERVICE_ERROR(20, "FILE_SERVICE_ERROR"),
    // 文件不存在
    FILE_NOT_EXISTS(21, "FILE_NOT_EXISTS"),
    // 文件无权限
    FILE_NO_PERMISSION(22, "FILE_NO_PERMISSION");


    private final int code;
    private final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}