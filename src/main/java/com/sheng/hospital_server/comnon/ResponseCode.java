package com.sheng.hospital_server.comnon;

import lombok.Getter;

@Getter
public enum ResponseCode {
    // 成功状态码
    SUCCESS(0, "SUCCESS"),
    // 通用错误状态码
    ERROR(1, "ERROR"),
    // 用户名或密码错误状态码
    USERNAME_OR_PASSWORD_ERROR(2, "USERNAME_OR_PASSWORD_ERROR"),

    // 参数不合法状态码
    ARGUMENT_ILLEGAL(10, "ARGUMENT_ILLEGAL"),
    // 需要登录状态码
    NEED_LOGIN(11, "NEED_LOGIN"),
    // 缺少权限状态码
    PERMISSION_DENIED(12, "PERMISSION_DENIED"),
    // 缺少角色状态码
    ROLE_MISSING(13, "ROLE_MISSING"),
    // 二级认证校验失败状态码
    SAFE_VERIFICATION_FAILED(14, "SAFE_VERIFICATION_FAILED"),
    // 服务封禁状态码
    SERVICE_DISABLED(15, "SERVICE_DISABLED"),
    // Http Basic 校验失败状态码
    HTTP_BASIC_AUTH_FAILED(16, "HTTP_BASIC_AUTH_FAILED"),

    // 文件服务错误状态码
    FILE_SERVICE_ERROR(20, "FILE_SERVICE_ERROR"),
    // 文件不存在状态码
    FILE_NOT_EXISTS(21, "FILE_NOT_EXISTS"),
    // 文件无权限状态码
    FILE_NO_PERMISSION(22, "FILE_NO_PERMISSION"),

    // 未知错误状态码
    UNKNOWN_ERROR(99, "UNKNOWN_ERROR");

    private final int code;
    private final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}