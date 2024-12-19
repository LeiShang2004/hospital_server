package com.sheng.hospital_server.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.pojo.Appointment;
import com.sheng.hospital_server.pojo.AppointmentInfo;
import com.sheng.hospital_server.pojo.Payment;
import com.sheng.hospital_server.service.AppointmentService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 挂号控制器
 */
@Slf4j
@RestController
@RequestMapping("/appointments")
@CrossOrigin//跨域
@SaCheckRole("user")
public class AppointmentController {
    @Resource
    private AppointmentService appointmentService;

    @PutMapping
    public CommonResponse<Integer> add(@RequestBody Appointment appointment) {
        log.info("挂号：添加挂号{}", appointment);
        try {
            appointmentService.add(appointment);
            return CommonResponse.createForSuccess(appointment.getAppointmentId());
        } catch (IllegalArgumentException e) {
            log.error("挂号：添加挂号失败，参数错误", e);
            // 业务异常
            return CommonResponse.createForError(e.getMessage());
        } catch (Exception e) {
            // 处理其他异常
            log.error("挂号：添加挂号失败", e);
            return CommonResponse.createForError("挂号失败，请联系管理员");
        }
    }

    /**
     * 取消挂号
     * 退回挂号费用
     *
     * @param appointment 挂号信息
     */
    @PostMapping("/cancel")
    public CommonResponse<Appointment> cancel(@RequestBody Appointment appointment) {
        log.info("挂号：取消id为{}的挂号", appointment.getAppointmentId());
        appointmentService.cancel(appointment.getAppointmentId());
        return CommonResponse.createForSuccess();
    }

    /**
     * 支付回调
     * 应该为第三方支付回调的接口
     */
    @PostMapping("/payment")
    public CommonResponse<Appointment> PaymentCallback(@RequestBody Payment payment) {
        if (payment.getIsPaid()) {
            log.info("挂号：支付id为{}的挂号", payment.getAppointmentId());
            appointmentService.confirmPayment(payment.getAppointmentId());
            return CommonResponse.createForSuccess();
        } else {
            log.info("挂号：取消支付id为{}的挂号", payment.getAppointmentId());
            appointmentService.cancelPayment(payment.getAppointmentId());
            return CommonResponse.createForSuccess();
        }
    }

    @GetMapping("/{id}")
    public CommonResponse<Appointment> getById(@PathVariable Integer id) {
        log.info("挂号：查找id为{}的挂号", id);
        return CommonResponse.createForSuccess(appointmentService.getById(id));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Appointment> delete(@PathVariable Integer id) {
        log.info("挂号：删除id为{}的挂号", id);
        appointmentService.delete(id);
        return CommonResponse.createForSuccess();
    }

    @PostMapping
    public CommonResponse<Appointment> update(@RequestBody Appointment appointment) {
        log.info("挂号：更新id为{}的挂号", appointment.getAppointmentId());
        appointmentService.update(appointment);
        return CommonResponse.createForSuccess();
    }

    @PostMapping("/{id}/status/{status}")
    public CommonResponse<Appointment> updateStatus(@PathVariable Integer id, @PathVariable Integer status) {
        log.info("挂号：更新id为{}的挂号状态为{}", id, status);
        appointmentService.updateStatus(id, status);
        return CommonResponse.createForSuccess();
    }

    @GetMapping("/patient/{patientId}")
    public CommonResponse<List<Appointment>> getByPatientId(@PathVariable Integer patientId) {
        log.info("挂号：查找患者id为{}的挂号", patientId);
        return CommonResponse.createForSuccess(appointmentService.getByPatientId(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public CommonResponse<List<Appointment>> getByDoctorId(@PathVariable Integer doctorId) {
        log.info("挂号：查找医生id为{}的挂号", doctorId);
        return CommonResponse.createForSuccess(appointmentService.getByDoctorId(doctorId));
    }

    @GetMapping("/user/{userId}")
    public CommonResponse<List<AppointmentInfo>> getByUserId(@PathVariable Integer userId) {
        log.info("挂号：查找用户id为{}的挂号", userId);
        return CommonResponse.createForSuccess(appointmentService.getByUserId(userId));
    }

    @GetMapping("/all")
    @SaCheckRole("admin")
    public CommonResponse<List<AppointmentInfo>> getAllInfo() {
        log.info("挂号：查找所有挂号");
        return CommonResponse.createForSuccess(appointmentService.getAll());
    }

    @DeleteMapping("/sofDelete/{id}")
    public CommonResponse<Appointment> softDelete(@PathVariable Integer id) {
        log.info("挂号：软删除id为{}的挂号", id);
        appointmentService.softDelete(id);
        return CommonResponse.createForSuccess();
    }
}
