package com.sheng.hospital_server.service;

import com.sheng.hospital_server.pojo.Schedule;
import com.sheng.hospital_server.pojo.ScheduleInfo;

import java.util.List;

public interface ScheduleService {
    void add(Schedule schedule);

    void delete(Integer id);

    void update(Schedule schedule);

    Schedule getById(Integer id);

    List<Schedule> getByDoctorId(Integer doctorId);

    List<Schedule> getByDate(java.sql.Date startDate, java.sql.Date endDate);

    List<Schedule> getByTime(Integer timeId);

    List<Schedule> getByDoctorIdAndDate(Integer doctorId, java.sql.Date startDate, java.sql.Date endDate);

    List<Schedule> getBySpecializationIdAndDate(Integer specializationId, java.sql.Date startDate, java.sql.Date endDate);

    List<ScheduleInfo> getInfoBySpecializationIdAndDate(Integer specializationId, java.sql.Date startDate, java.sql.Date endDate);

    Schedule getAvailableNumber(Integer scheduleId);

    void incrementAvailableNumber(Integer scheduleId);

    void decrementAvailableNumber(Integer scheduleId);
}
