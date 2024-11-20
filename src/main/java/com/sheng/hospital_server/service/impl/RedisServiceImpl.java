package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.DepartmentMapper;
import com.sheng.hospital_server.pojo.Department;
import com.sheng.hospital_server.service.RedisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis服务类
 * 用于将数据库中的科室、专业、职称信息加载到Redis中
 * 数据量较小，选择在单个类中实现
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private DepartmentMapper departmentMapper;

    // redis过期时间
    @Value("${redis.expiration}")
    private long expiration;

    /* department相关*/

    /**
     * 判断Redis中是否已经加载了某个科室信息
     *
     * @param departmentId 科室id
     * @return 是否已加载
     */
    @Override
    public boolean isDepartmentsLoaded(Integer departmentId) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(RedisService.getDepartmentKey(departmentId)));
    }

    /**
     * 将科室信息加载到Redis中，过期时间为expiration 配置文件中
     * key: departId:1
     * value: 内科
     */
    @Override
    public void loadDepartments() {
        List<Department> departments = departmentMapper.getAll();
        for (Department department : departments) {
            // 换了另一个四个入参的SDK方法 ！！！！！
            // https://blog.csdn.net/wushengjun753/article/details/108199088
            stringRedisTemplate.opsForValue().set(RedisService.getDepartmentKey(department.getDepartmentId()), department.getName(), expiration, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 由科室id从Redis中获取科室名
     *
     * @param departmentId 科室id
     * @return 科室名
     */
    @Override
    public String getDepartmentName(Integer departmentId) {
        // 如果没有信息，先加载
        if (!isDepartmentsLoaded(departmentId)) {
            log.info("Redis中没有department信息，正在加载...");
            loadDepartments();
        }
        return stringRedisTemplate.opsForValue().get(RedisService.getDepartmentKey(departmentId));
    }
}