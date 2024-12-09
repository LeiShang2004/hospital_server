package com.sheng.hospital_server.controller;

import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.pojo.Patient;
import com.sheng.hospital_server.service.PatientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 患者控制器
 */
@Slf4j
@RestController
@RequestMapping("/patients")
@CrossOrigin//跨域
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
