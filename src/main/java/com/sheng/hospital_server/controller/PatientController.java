package com.sheng.hospital_server.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.pojo.Patient;
import com.sheng.hospital_server.service.PatientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 患者控制器
 */
@Slf4j
@RestController
@RequestMapping("/patients")
@CrossOrigin//跨域
@SaCheckRole("user")
public class PatientController {
    @Resource
    PatientService patientService;

    @PutMapping()
    public CommonResponse<Patient> add(@RequestBody Patient patient) {
        log.info("患者：新增患者{}", patient);
        patientService.add(patient);
        return CommonResponse.createForSuccess();
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Patient> delete(@PathVariable Integer id) {
        log.info("患者：删除id为{}的患者", id);
        patientService.delete(id);
        return CommonResponse.createForSuccess();
    }

    @GetMapping("/userId/{userId}")
    public CommonResponse<List<Patient>> getPatientsById(@PathVariable Integer userId) {
        if (StpUtil.hasRole("admin")) {
            log.info("用户：管理员查找id为{}的用户绑定的患者", userId);
        } else {
            // 非管理员只能查找自己的信息
            userId = StpUtil.getLoginIdAsInt();
            log.info("用户：{}用户查找自己绑定的患者", userId);
        }
        return CommonResponse.createForSuccess(patientService.getByUserId(userId));
    }

    @PostMapping()
    public CommonResponse<Patient> update(@RequestBody Patient patient) {
        log.info("患者：更新id为{}的患者", patient.getPatientId());
        patientService.update(patient);
        return CommonResponse.createForSuccess();
    }

    @GetMapping("/{id}")
    public CommonResponse<Patient> getById(@PathVariable Integer id) {
        log.info("患者：查找id为{}的患者", id);
        return CommonResponse.createForSuccess(patientService.getById(id));
    }
}
