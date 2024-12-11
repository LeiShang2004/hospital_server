package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.AppointmentMapper;
import com.sheng.hospital_server.pojo.Appointment;
import com.sheng.hospital_server.pojo.Schedule;
import com.sheng.hospital_server.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Resource
    private AppointmentMapper appointmentMapper;

    @Resource
    private UserService userService;

    @Resource
    private PatientService patientService;

    @Resource
    private DoctorService doctorService;

    @Resource
    private ScheduleService scheduleService;

    /**
     * 添加挂号
     * 需要实现支付超时、支付失败、取消支付的取消订单释放资源的功能
     * 1.消息队列延迟消息 为了这个单一功能引入MQ 重
     * 2.定时任务 有点唐
     * 3.分布式锁
     * 4.redis 过期回调 选用
     *
     * @param appointment 挂号信息
     * @return 自增主键挂号ID
     */
    @Override
    public Integer add(Appointment appointment) {
        // 检查 user_id 是否存在
        if (!userService.existsById(appointment.getUserId())) {
            throw new IllegalArgumentException("用户ID" + appointment.getUserId() + "，不存在");
        }

        // 检查 doctor_id 是否存在
        if (!doctorService.existsById(appointment.getDoctorId())) {
            throw new IllegalArgumentException("医生ID" + appointment.getDoctorId() + "，不存在");
        }

        // 检查 patient_id 是否存在
        if (!patientService.existsById(appointment.getPatientId())) {
            throw new IllegalArgumentException("患者ID" + appointment.getPatientId() + "，不存在");
        }

        // 检查该医生该时间段是否约满
        Schedule byId = scheduleService.getById(appointment.getScheduleId());
        if (byId.getAvailableNumber() <= 0) {
            throw new IllegalArgumentException("该医生该时间段已约满");
        }

        // 设置挂号状态
        appointment.setStatus(AppointmentService.STATUS_PENDING_CONFIRMATION);

        appointmentMapper.add(appointment);

        // 更新排班表的可用数量 加锁占用资源 一定时间后解锁
        scheduleService.decrementAvailableNumber(appointment.getScheduleId());



        return appointment.getAppointmentId();
    }

    @Override
    public void cancel(Integer appointmentId) {

    }

    @Override
    public void delete(Integer appointmentId) {
        appointmentMapper.delete(appointmentId);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentMapper.update(appointment);
    }

    @Override
    public void updateStatus(Integer appointmentId, Integer newStatus) {
        appointmentMapper.updateStatus(appointmentId, newStatus);
    }

    @Override
    public Appointment getById(Integer appointmentId) {
        return appointmentMapper.getById(appointmentId);
    }

    @Override
    public List<Appointment> getByPatientId(Integer patientId) {
        return appointmentMapper.getByPatientId(patientId);
    }

    @Override
    public List<Appointment> getByDoctorId(Integer doctorId) {
        return appointmentMapper.getByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getByUserId(Integer userId) {
        return appointmentMapper.getByUserId(userId);
    }
}
