package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.UserMapper;
import com.sheng.hospital_server.pojo.User;
import com.sheng.hospital_server.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void add(User user) {
        try {
            userMapper.add(user);
        } catch (Exception e) {
            throw new RuntimeException("该手机号已被注册");
        }
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
        if (user == null) {
            return null;
        }
        user.setPassword(null);
        return user;
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }


    @Override
    public Integer login(User user) {
        User logined = userMapper.login(user);
        if (logined == null) {
            return null;
        } else {
//            //要向jwt中封装的信息
//            Map<String, Object> claims = new HashMap<>();
//            claims.put("id", logined.getUserId());
//            claims.put("is_admin", false);
//
//            // 生成JWT令牌
//            return JwtUtil.generateJwt(claims);

            // 弃用jwt 改用sa token
            return logined.getUserId();
        }
    }

    @Override
    public Boolean existsById(Integer id) {
        return userMapper.existsById(id);
    }
}
