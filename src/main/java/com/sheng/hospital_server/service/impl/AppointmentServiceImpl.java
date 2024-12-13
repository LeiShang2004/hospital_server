package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.AppointmentMapper;
import com.sheng.hospital_server.mq.MyProducer;
import com.sheng.hospital_server.pojo.Appointment;
import com.sheng.hospital_server.pojo.Schedule;
import com.sheng.hospital_server.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Resource
    private MyProducer myProducer;

    /**
     * 添加挂号
     * 需要实现支付超时、支付失败、取消支付的取消订单释放资源的功能
     * 1.消息队列延迟消息 选用
     * 2.定时任务 有点唐
     * 3.分布式锁
     * 4.redis 过期回调 不稳
     *
     * @param appointment 挂号信息
     */
    @Override
    public void add(Appointment appointment) {
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
        if (byId == null) {
            throw new IllegalArgumentException("排班ID" + appointment.getScheduleId() + "，不存在");
        } else if (byId.getAvailableNumber() <= 0) {
            throw new IllegalArgumentException("该医生该时间段已约满");
        }

        // 检查是否已经挂过号
        Appointment byScheduleIdAndPatientId = appointmentMapper.getByScheduleIdAndPatientIdAndStatus(appointment.getScheduleId(), appointment.getPatientId(), AppointmentService.STATUS_CONFIRMED);
        if (byScheduleIdAndPatientId != null) {
            throw new IllegalArgumentException("该患者已经挂过该时间段的号");
        }

        // 设置挂号状态
        appointment.setStatus(AppointmentService.STATUS_PENDING_CONFIRMATION);

        appointmentMapper.add(appointment);

        // 更新排班表的可用数量 加锁占用资源 一定时间后解锁
        scheduleService.decrementAvailableNumber(appointment.getScheduleId());

        // 发送消息队列，支付倒计时
        myProducer.sendMessage(appointment.getAppointmentId());

    }

    @Override
    public void cancel(Integer appointmentId) {
        Appointment appointment = appointmentMapper.getById(appointmentId);
        if (appointment == null) {
            throw new IllegalArgumentException("挂号ID" + appointmentId + "不存在");
        }

        // 更新排班表的可用数量 解锁资源
        scheduleService.incrementAvailableNumber(appointment.getScheduleId());

        if (!Objects.equals(appointment.getStatus(), AppointmentService.STATUS_COMPLETED)) {
            // 退款
            appointmentMapper.updateStatus(appointmentId, AppointmentService.STATUS_CANCELLED);
        }

    }

    /**
     * 取消支付
     *
     * @param appointmentId 挂号ID
     */
    @Override
    public void cancelPayment(Integer appointmentId) {
        appointmentMapper.updateStatus(appointmentId, AppointmentService.STATUS_CANCELLED);
    }

    /**
     * 确认支付
     *
     * @param appointmentId 挂号ID
     */
    @Override
    public void confirmPayment(Integer appointmentId) {
        appointmentMapper.updateStatus(appointmentId, AppointmentService.STATUS_CONFIRMED);
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

    @Override
    public List<Appointment> getAll() {
        return appointmentMapper.getAll();
    }
}
