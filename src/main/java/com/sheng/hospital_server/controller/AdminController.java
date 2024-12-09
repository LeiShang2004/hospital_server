package com.sheng.hospital_server.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.comnon.ResponseCode;
import com.sheng.hospital_server.pojo.Admin;
import com.sheng.hospital_server.service.AdminService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员控制器
 */
@Slf4j
@RestController
@RequestMapping("/admins")
@CrossOrigin//跨域
@SaCheckRole("admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PostMapping("/login")
    @SaIgnore
    public CommonResponse<SaTokenInfo> login(@RequestBody Admin admin) {
        log.info("管理员{}：登录", admin.getAdminId());
        String loginedId = adminService.login(admin);
        if (loginedId == null) {
            return CommonResponse.createForError(ResponseCode.USERNAME_OR_PASSWORD_ERROR.getCode(), ResponseCode.USERNAME_OR_PASSWORD_ERROR.getDescription());
        }
        // sa token 登录
        StpUtil.login(admin.getAdminId());
        // 获取 Token 相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return CommonResponse.createForSuccess(tokenInfo);
    }
}
