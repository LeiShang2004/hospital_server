package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Specialization;

import java.util.List;

public interface SpecializationService {
    void add(Specialization specialization);

    void delete(Integer id);

    void update(Specialization specialization);

    Specialization getById(Integer id);

    List<Specialization> getAll();

    List<Specialization> getByDepartmentId(Integer departmentId);
}
