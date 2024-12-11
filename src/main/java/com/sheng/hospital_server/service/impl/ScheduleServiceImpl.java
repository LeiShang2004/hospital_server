package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.ScheduleMapper;
import com.sheng.hospital_server.pojo.Schedule;
import com.sheng.hospital_server.service.ScheduleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    public void add(Schedule schedule) {
        scheduleMapper.add(schedule);
    }

    @Override
    public void delete(Integer id) {
        scheduleMapper.delete(id);
    }

    @Override
    public void update(Schedule schedule) {
        scheduleMapper.update(schedule);
    }

    @Override
    public Schedule getById(Integer id) {
        return scheduleMapper.getById(id);
    }

    @Override
    public List<Schedule> getByDoctorId(Integer doctorId) {
        return scheduleMapper.getByDoctorId(doctorId);
    }

    @Override
    public List<Schedule> getByDate(java.sql.Date startDate, java.sql.Date endDate) {
        return scheduleMapper.getByDate(startDate, endDate);
    }

    @Override
    public List<Schedule> getByTime(Integer timeId) {
        return scheduleMapper.getByTime(timeId);
    }

    @Override
    public List<Schedule> getByDoctorIdAndDate(Integer doctorId, java.sql.Date startDate, java.sql.Date endDate) {
        return scheduleMapper.getByDoctorIdAndDate(doctorId, startDate, endDate);
    }

    @Override
    public List<Schedule> getBySpecializationIdAndDate(Integer specializationId, java.sql.Date startDate, java.sql.Date endDate) {
        return scheduleMapper.getBySpecializationIdAndDate(specializationId, startDate, endDate);
    }

    @Override
    public Schedule getAvailableNumber(Integer scheduleId) {
        return scheduleMapper.getAvailableNumber(scheduleId);
    }

    @Override
    public void incrementAvailableNumber(Integer scheduleId) {
        scheduleMapper.incrementAvailableNumber(scheduleId);
    }

    @Override
    public void decrementAvailableNumber(Integer scheduleId) {
        scheduleMapper.decrementAvailableNumber(scheduleId);
    }

}
