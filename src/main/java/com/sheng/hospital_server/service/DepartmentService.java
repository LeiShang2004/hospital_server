package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Department;

import java.util.List;

public interface DepartmentService {
    void add(Department department);

    void delete(Integer id);

    void update(Department department);

    Department getById(Integer id);

    List<Department> getAll();
}
