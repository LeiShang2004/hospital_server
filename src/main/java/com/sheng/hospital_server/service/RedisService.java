package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Department;

import java.util.List;

public interface RedisService {
    /* department相关*/

    /**
     * 获取Redis中科室信息的key
     *
     * @param departmentId 科室id
     * @return key redis中的key
     */
    public static String getDepartmentKey(Integer departmentId) {
        return "departmentId:" + departmentId;
    }

    public boolean isDepartmentsLoaded(Integer departmentId);

    public void loadDepartments();

    public String getDepartmentName(Integer departmentId);
}
