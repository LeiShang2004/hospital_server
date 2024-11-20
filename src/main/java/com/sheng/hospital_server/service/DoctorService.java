package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Doctor;

import java.util.List;

public interface DoctorService {
    public Doctor getById(Integer id);

    public List<Doctor> getBySpecializationId(Integer specializationId);

    public List<Doctor> getByDepartmentId(Integer departmentId);

    public List<Doctor> getAll();

    // 补全医生信息
    public void complete(Doctor doctor);
}
