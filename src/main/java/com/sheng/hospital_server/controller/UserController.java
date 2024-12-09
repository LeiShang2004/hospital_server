package com.sheng.hospital_server.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.comnon.ResponseCode;
import com.sheng.hospital_server.pojo.User;
import com.sheng.hospital_server.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 */
@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin//跨域
@SaCheckRole("user")
public class UserController {
    @Resource
    private UserService userService;

    @PutMapping
    @SaIgnore
    public CommonResponse<User> add(@RequestBody User user) {
        log.info("用户：添加用户");
        userService.add(user);
        return CommonResponse.createForSuccess();
    }

    @DeleteMapping("/{id}")
    public CommonResponse<User> delete(@PathVariable Integer id) {
        log.info("用户：删除id为{}的用户", id);
        userService.delete(id);
        return CommonResponse.createForSuccess();
    }

    @PostMapping
    public CommonResponse<User> update(@RequestBody User user) {
        log.info("用户：更新id为{}的用户", user.getUserId());
        userService.update(user);
        return CommonResponse.createForSuccess();
    }

    @GetMapping("/{id}")
    public CommonResponse<User> getById(@PathVariable Integer id) {
        log.info("用户：查找id为{}的用户", id);
        System.out.println(StpUtil.getTokenTimeout());
        return CommonResponse.createForSuccess(userService.getById(id));
    }

//    @GetMapping("/patients/{id}")
//    public CommonResponse<User> getPatientById(@PathVariable Integer id) {
//        log.info("用户：查找id为{}的患者", id);
//        return CommonResponse.createForSuccess(userService.getPatientById(id));
//    }

    @PostMapping("/login")
    @SaIgnore
    public CommonResponse<SaTokenInfo> login(@RequestBody User user) {
        log.info("用户：id为{}登录", user.getUserId());
        Integer loginedId = userService.login(user);
        if (loginedId == null) {
            // 登录失败
            return CommonResponse.createForError(ResponseCode.USERNAME_OR_PASSWORD_ERROR.getCode(), ResponseCode.USERNAME_OR_PASSWORD_ERROR.getDescription());
        }
        // sa token 登录
        StpUtil.login(user.getUserId());
        // 获取 Token 相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return CommonResponse.createForSuccess(tokenInfo);
    }
}
