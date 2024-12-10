package com.sheng.hospital_server.exception;

import cn.dev33.satoken.exception.*;
import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.comnon.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {
    // 拦截：无此资源异常
    @ExceptionHandler(NoResourceFoundException.class)
    public <T> CommonResponse<T> handlerException(NoResourceFoundException e) {
        log.error("无此资源异常：", e);
        return CommonResponse.createForError(ResponseCode.NO_RESOURCE_FOUND);
    }

    // 拦截：未登录异常
    @ExceptionHandler(NotLoginException.class)
    public <T> CommonResponse<T> handlerException(NotLoginException e) {
        // 打印堆栈，以供调试
        log.error("未登录异常：", e);
        // 返回给前端
        return CommonResponse.createForError(ResponseCode.NEED_LOGIN);
    }

    // 拦截：缺少权限异常
    @ExceptionHandler(NotPermissionException.class)
    public <T> CommonResponse<T> handlerException(NotPermissionException e) {
        log.error("缺少权限异常：", e);
        return CommonResponse.createForError(ResponseCode.PERMISSION_DENIED);
    }

    // 拦截：缺少角色异常
    @ExceptionHandler(NotRoleException.class)
    public <T> CommonResponse<T> handlerException(NotRoleException e) {
        log.error("缺少角色异常：", e);
        return CommonResponse.createForError(ResponseCode.ROLE_MISSING);
    }

    // 拦截：二级认证校验失败异常
    @ExceptionHandler(NotSafeException.class)
    public <T> CommonResponse<T> handlerException(NotSafeException e) {
        log.error("二级认证校验失败异常：", e);
        return CommonResponse.createForError(ResponseCode.SAFE_VERIFICATION_FAILED);
    }

    // 拦截：服务封禁异常
    @ExceptionHandler(DisableServiceException.class)
    public <T> CommonResponse<T> handlerException(DisableServiceException e) {
        log.error("服务封禁异常：", e);
        return CommonResponse.createForError(ResponseCode.SERVICE_DISABLED);
    }

    // 拦截：Http Basic 校验失败异常
    @ExceptionHandler(NotHttpBasicAuthException.class)
    public <T> CommonResponse<T> handlerException(NotHttpBasicAuthException e) {
        log.error("Http Basic 校验失败异常：", e);
        return CommonResponse.createForError(ResponseCode.HTTP_BASIC_AUTH_FAILED);
    }

    // 拦截：其它所有异常
    @ExceptionHandler(Exception.class)
    public <T> CommonResponse<T> handlerException(Exception e) {
        log.error("其它所有异常：", e);
        return CommonResponse.createForError(e.getMessage());
    }

}
