package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.AdminMapper;
import com.sheng.hospital_server.pojo.Admin;
import com.sheng.hospital_server.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public String login(Admin admin) {
        Admin loginedId = adminMapper.login(admin);
        if (loginedId == null) {
            return null;
        } else {
            return loginedId.getAdminId();
        }
    }
}
