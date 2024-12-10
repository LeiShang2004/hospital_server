package com.sheng.hospital_server.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.pojo.Doctor;
import com.sheng.hospital_server.service.DoctorService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 医生控制器
 */
@Slf4j
@RestController
@RequestMapping("/doctors")
@CrossOrigin//跨域
@SaCheckRole("user")
public class DoctorController {
    @Resource
    DoctorService doctorService;

    @GetMapping("/{id}")
    public CommonResponse<Doctor> getById(@PathVariable Integer id) {
        log.info("医生：查找id为{}的医生", id);
        return CommonResponse.createForSuccess(doctorService.getById(id));
    }

    @GetMapping("/specialization/{specialization_id}")
    public CommonResponse<List<Doctor>> getBySpecializationId(@PathVariable Integer specialization_id) {
        log.info("医生：查找专业（specialization）id为{}的医生", specialization_id);
        return CommonResponse.createForSuccess(doctorService.getBySpecializationId(specialization_id));
    }

    @GetMapping("/department/{department_id}")
    public CommonResponse<List<Doctor>> getByDepartmentId(@PathVariable Integer department_id) {
        log.info("医生：查找科室（department）id为{}的医生", department_id);
        return CommonResponse.createForSuccess(doctorService.getByDepartmentId(department_id));
    }

    @GetMapping("/all")
    public CommonResponse<List<Doctor>> getAll() {
        log.info("医生：查找所有医生");
        return CommonResponse.createForSuccess(doctorService.getAll());
    }

}
