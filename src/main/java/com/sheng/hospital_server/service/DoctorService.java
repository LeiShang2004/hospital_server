package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Doctor;
import com.sheng.hospital_server.pojo.ScheduleInfo;

import java.util.List;

public interface DoctorService {
    Doctor getById(Integer id);

    List<Doctor> getBySpecializationId(Integer specializationId);

    List<Doctor> getByDepartmentId(Integer departmentId);

    List<Doctor> getAll();

    List<ScheduleInfo> getByName(String name);

    List<ScheduleInfo> getByIntroduction(String name);

    // 补全医生信息
    void complete(Doctor doctor);

    Boolean existsById(Integer id);

    void add(Doctor doctor);

    void delete(Integer id);

    void update(Doctor doctor);
}
