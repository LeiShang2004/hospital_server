package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.UserMapper;
import com.sheng.hospital_server.pojo.User;
import com.sheng.hospital_server.service.UserService;
import com.sheng.hospital_server.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User getById(Integer id) {
        User user = userMapper.getById(id);
        user.setPassword(null);
        return user;
    }

    @Override
    public String login(User user) {
        User logined = userMapper.login(user);
        if (logined == null) {
            return null;
        } else {
            // TODO 密码加密解密

            //要向jwt中封装的信息
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", logined.getUserId());
            claims.put("is_admin", false);

            // 生成JWT令牌
            return JwtUtil.generateJwt(claims);
        }
    }
}
