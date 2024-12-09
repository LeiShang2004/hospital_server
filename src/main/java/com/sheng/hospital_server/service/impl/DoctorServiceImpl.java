package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.DoctorMapper;
import com.sheng.hospital_server.pojo.Doctor;
import com.sheng.hospital_server.service.DoctorService;
import com.sheng.hospital_server.service.RedisService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private RedisService redisService;

    @Override
    public Doctor getById(Integer id) {
        Doctor doctor = doctorMapper.getById(id);
        // 补全医生信息
        this.complete(doctor);
        return doctor;
    }

    @Override
    public List<Doctor> getBySpecializationId(Integer specializationId) {
        List<Doctor> doctors = doctorMapper.getBySpecializationId(specializationId);
        for (Doctor doctor : doctors) {
            // 补全医生信息
            this.complete(doctor);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getByDepartmentId(Integer departmentId) {
        List<Doctor> doctors = doctorMapper.getByDepartmentId(departmentId);
        for (Doctor doctor : doctors) {
            // 补全医生信息
            this.complete(doctor);
        }
        return doctors;
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = doctorMapper.getAll();
        for (Doctor doctor : doctors) {
            // 补全医生信息
            this.complete(doctor);
        }
        return doctors;
    }

    /**
     * 补全医生信息
     * 通过RedisService获取科室名等，补全医生信息
     *
     * @param doctor 不完全的医生信息
     */
    @Override
    public void complete(Doctor doctor) {
        doctor.setDepartmentName(redisService.getDepartmentName(doctor.getDepartmentId()));
        doctor.setSpecializationName(redisService.getSpecializationName(doctor.getSpecializationId()));
        doctor.setTitleName(redisService.getTitleName(doctor.getTitleId()));
        doctor.setTitleFee(redisService.getTitleFee(doctor.getTitleId()));
    }

    @Override
    public Boolean existsById(Integer id) {
        return doctorMapper.existsById(id);
    }
}
