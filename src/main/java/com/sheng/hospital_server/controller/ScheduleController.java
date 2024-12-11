package com.sheng.hospital_server.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.pojo.Schedule;
import com.sheng.hospital_server.service.ScheduleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 排班控制器
 */
@Slf4j
@RestController
@RequestMapping("/schedules")
@CrossOrigin//跨域
public class ScheduleController {
    @Resource
    ScheduleService scheduleService;

    @PutMapping()
    @SaCheckRole("admin")
    public CommonResponse<Schedule> add(@RequestBody Schedule schedule) {
        log.info("排班：新增排班");
        scheduleService.add(schedule);
        return CommonResponse.createForSuccess();
    }

    @DeleteMapping("/{id}")
    @SaCheckRole("admin")
    public CommonResponse<Schedule> delete(@PathVariable Integer id) {
        log.info("排班：删除id为{}的排班", id);
        scheduleService.delete(id);
        return CommonResponse.createForSuccess();
    }

    @PostMapping()
    @SaCheckRole("admin")
    public CommonResponse<Schedule> update(@RequestBody Schedule schedule) {
        log.info("排班：更新id为{}的排班", schedule.getScheduleId());
        scheduleService.update(schedule);
        return CommonResponse.createForSuccess();
    }

    @GetMapping("/{id}")
    @SaCheckRole("user")
    public CommonResponse<Schedule> getById(@PathVariable Integer id) {
        log.info("排班：查找id为{}的排班", id);
        return CommonResponse.createForSuccess(scheduleService.getById(id));
    }

    @GetMapping("/specialization/{specializationId}")
    @SaCheckRole("user")
    public CommonResponse<List<Schedule>> getBySpecializationId(@PathVariable Integer specializationId) {
        log.info("排班：查找专业id为{}的排班", specializationId);
        // 当前sql日期
        java.util.Date date = new java.util.Date();
        java.sql.Date startDate = new java.sql.Date(date.getTime());
        // 未来第七天的日期
        java.sql.Date endDate = new java.sql.Date(date.getTime() + 7 * 24 * 60 * 60 * 1000);
        return CommonResponse.createForSuccess(scheduleService.getBySpecializationIdAndDate(specializationId, startDate, endDate));
    }
}
