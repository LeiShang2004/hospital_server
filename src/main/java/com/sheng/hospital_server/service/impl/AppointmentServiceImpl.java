package com.sheng.hospital_server.service.impl;

import com.sheng.hospital_server.mapper.AppointmentMapper;
import com.sheng.hospital_server.pojo.Appointment;
import com.sheng.hospital_server.service.AppointmentService;
import com.sheng.hospital_server.service.DoctorService;
import com.sheng.hospital_server.service.PatientService;
import com.sheng.hospital_server.service.UserService;
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


        appointmentMapper.add(appointment);
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
