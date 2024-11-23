package com.sheng.hospital_server.controller;

import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.pojo.Department;
import com.sheng.hospital_server.service.DepartmentService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 科室department控制器
 */
@Slf4j
@RestController
@RequestMapping("/departments")
@CrossOrigin//跨域
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @PutMapping
    public CommonResponse<Department> add(@RequestBody Department department) {
        log.info("科室：添加科室");
        departmentService.add(department);
        return CommonResponse.createForSuccess();
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Department> delete(@PathVariable Integer id) {
        log.info("科室：删除id为{}的科室", id);
        departmentService.delete(id);
        return CommonResponse.createForSuccess();
    }

    @PostMapping
    public CommonResponse<Department> update(@RequestBody Department department) {
        log.info("科室：更新id为{}的科室", department.getDepartmentId());
        departmentService.update(department);
        return CommonResponse.createForSuccess();
    }

    @GetMapping("/{id}")
    public CommonResponse<Department> getById(@PathVariable Integer id) {
        log.info("科室：查找id为{}的科室", id);
        return CommonResponse.createForSuccess(departmentService.getById(id));
    }

    @GetMapping("/all")
    public CommonResponse<List<Department>> getAll() {
        log.info("科室：查找所有科室");
        return CommonResponse.createForSuccess(departmentService.getAll());
    }
}
