package com.sheng.hospital_server.mapper;

import com.sheng.hospital_server.pojo.Schedule;
import com.sheng.hospital_server.pojo.ScheduleInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    @Insert("insert into schedule(doctor_id, date, time, available_number) values(#{doctorId}, #{date}, #{time}, #{availableNumber})")
    void add(Schedule schedule);

    @Delete("delete from schedule where schedule_id = #{id}")
    void delete(Integer id);

    @Update("update schedule set doctor_id = #{doctorId}, date = #{date}, time = #{time}, available_number = #{availableNumber} where schedule_id = #{scheduleId}")
    void update(Schedule schedule);

    @Select("select * from schedule where schedule_id = #{id}")
    Schedule getById(Integer id);

    @Select("select * from schedule where doctor_id = #{doctorId}")
    List<Schedule> getByDoctorId(Integer doctorId);

    @Select("select * from schedule where date between #{startDate} and #{endDate}")
    List<Schedule> getByDate(java.sql.Date startDate, java.sql.Date endDate);

    @Select("select * from schedule where time = #{timeId}")
    List<Schedule> getByTime(Integer timeId);

    // 根据医生id和日期查询排班
    @Select("select * from schedule where doctor_id = #{doctorId} and date between #{startDate} and #{endDate}")
    List<Schedule> getByDoctorIdAndDate(Integer doctorId, java.sql.Date startDate, java.sql.Date endDate);

    // 根据专业id和日期查询排班
    @Select("select * from schedule where specialization_id = #{specializationId} and date between #{startDate} and #{endDate}")
    List<Schedule> getBySpecializationIdAndDate(Integer specializationId, java.sql.Date startDate, java.sql.Date endDate);

    // 根据专业id和日期查询排班 附带医生信息
    List<ScheduleInfo> getInfoBySpecializationIdAndDate(Integer specializationId, java.sql.Date startDate, java.sql.Date endDate);

    // 根据医生id和日期查询排班 附带医生信息
    List<ScheduleInfo> getInfoByDoctorIdAndDate(Integer doctorId, java.sql.Date startDate, java.sql.Date endDate);

    // 查询所有排班信息 附带医生信息
    List<ScheduleInfo> getAllInfo();

    // 判断某个排班是否还有剩余号源
    @Select("select * from schedule where schedule_id = #{scheduleId} and available_number > 0")
    Schedule getAvailableNumber(Integer scheduleId);

    // 增加号源
    @Update("update schedule set available_number = available_number + 1 where schedule_id = #{scheduleId}")
    void incrementAvailableNumber(Integer scheduleId);

    // 减少号源
    @Update("update schedule set available_number = available_number - 1 where schedule_id = #{scheduleId}")
    void decrementAvailableNumber(Integer scheduleId);
}
