package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor getById(Integer id);

    List<Doctor> getBySpecializationId(Integer specializationId);

    List<Doctor> getByDepartmentId(Integer departmentId);

    List<Doctor> getAll();

    // 补全医生信息
    void complete(Doctor doctor);

    Boolean existsById(Integer id);
}
