package com.sheng.hospital_server.controller;

import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.pojo.Specialization;
import com.sheng.hospital_server.service.SpecializationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专业specialization控制器
 */
@Slf4j
@RestController
@RequestMapping("/specializations")
@CrossOrigin//跨域
public class SpecializationController {
    @Resource
    private SpecializationService specializationService;

    @PutMapping
    public CommonResponse<Specialization> add(@RequestBody Specialization specialization) {
        log.info("专业：添加专业{}", specialization);
        specializationService.add(specialization);
        return CommonResponse.createForSuccess();
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Specialization> delete(@PathVariable Integer id) {
        log.info("专业：删除id为{}的专业", id);
        specializationService.delete(id);
        return CommonResponse.createForSuccess();
    }

    @PostMapping
    public CommonResponse<Specialization> update(@RequestBody Specialization specialization) {
        log.info("专业：更新id为{}的专业", specialization.getSpecializationId());
        specializationService.update(specialization);
        return CommonResponse.createForSuccess();
    }

    @GetMapping("/{id}")
    public CommonResponse<Specialization> getById(@PathVariable Integer id) {
        log.info("专业：查找id为{}的专业", id);
        return CommonResponse.createForSuccess(specializationService.getById(id));
    }

    @GetMapping("/all")
    public CommonResponse<List<Specialization>> getAll() {
        log.info("专业：查找所有专业");
        return CommonResponse.createForSuccess(specializationService.getAll());
    }

    @GetMapping("/department/{departmentId}")
    public CommonResponse<List<Specialization>> getByDepartmentId(@PathVariable Integer departmentId) {
        log.info("专业：查找departmentId为{}的专业", departmentId);
        return CommonResponse.createForSuccess(specializationService.getByDepartmentId(departmentId));
    }
}
