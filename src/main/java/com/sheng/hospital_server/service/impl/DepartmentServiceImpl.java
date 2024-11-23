package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.DepartmentMapper;
import com.sheng.hospital_server.pojo.Department;
import com.sheng.hospital_server.service.DepartmentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public void add(Department department) {
        departmentMapper.add(department);
    }

    @Override
    public void delete(Integer id) {
        departmentMapper.delete(id);
    }

    @Override
    public void update(Department department) {
        departmentMapper.update(department);
    }

    @Override
    public Department getById(Integer id) {
        return departmentMapper.getById(id);
    }

    @Override
    public List<Department> getAll() {
        return departmentMapper.getAll();
    }
}
