package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.DoctorMapper;
import com.sheng.hospital_server.pojo.Doctor;
import com.sheng.hospital_server.pojo.ScheduleInfo;
import com.sheng.hospital_server.service.DoctorService;
import com.sheng.hospital_server.service.RedisService;
import com.sheng.hospital_server.service.ScheduleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private RedisService redisService;

    @Resource
    private ScheduleService scheduleService;

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

    @Override
    public List<ScheduleInfo> getByName(String name) {
        List<Doctor> byName = doctorMapper.getByName(name);
        List<ScheduleInfo> scheduleInfos = new ArrayList<>();
        // 当前sql日期
        java.util.Date date = new java.util.Date();
        java.sql.Date startDate = new java.sql.Date(date.getTime() + 24 * 60 * 60 * 1000);
        // 未来第七天的日期
        java.sql.Date endDate = new java.sql.Date(date.getTime() + 7 * 24 * 60 * 60 * 1000);
        for (Doctor doctor : byName) {
            List<ScheduleInfo> infoByDoctorIdAndDate = scheduleService.getInfoByDoctorIdAndDate(doctor.getDoctorId(), startDate, endDate);
            scheduleInfos.addAll(infoByDoctorIdAndDate);
        }
        return scheduleInfos;
    }

    @Override
    public List<ScheduleInfo> getByIntroduction(String instruction) {
        List<Doctor> byIntroduction = doctorMapper.getByIntroduction(instruction);
        List<ScheduleInfo> scheduleInfos = new ArrayList<>();
        // 当前sql日期
        java.util.Date date = new java.util.Date();
        java.sql.Date startDate = new java.sql.Date(date.getTime() + 24 * 60 * 60 * 1000);
        // 未来第七天的日期
        java.sql.Date endDate = new java.sql.Date(date.getTime() + 7 * 24 * 60 * 60 * 1000);
        for (Doctor doctor : byIntroduction) {
            List<ScheduleInfo> infoByDoctorIdAndDate = scheduleService.getInfoByDoctorIdAndDate(doctor.getDoctorId(), startDate, endDate);
            scheduleInfos.addAll(infoByDoctorIdAndDate);
        }
        return scheduleInfos;
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

    @Override
    public void add(Doctor doctor) {
        doctorMapper.add(doctor);
    }

    @Override
    public void delete(Integer id) {
        doctorMapper.deleteById(id);
    }

    @Override
    public void update(Doctor doctor) {
        doctorMapper.update(doctor);
    }
}
