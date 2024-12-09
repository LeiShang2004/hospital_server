package com.sheng.hospital_server.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.pojo.Title;
import com.sheng.hospital_server.service.TitleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 职称控制器
 */
@Slf4j
@RestController
@RequestMapping("/titles")
@CrossOrigin//跨域
@SaCheckRole("admin")
public class TitleController {
    @Resource
    private TitleService titleService;

    @PutMapping
    public CommonResponse<Title> add(@RequestBody Title title) {
        log.info("职称：添加职称{}", title);
        titleService.add(title);
        return CommonResponse.createForSuccess();
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Title> delete(@PathVariable Integer id) {
        log.info("职称：删除id为{}的职称", id);
        titleService.delete(id);
        return CommonResponse.createForSuccess();
    }

    @PostMapping
    public CommonResponse<Title> update(@RequestBody Title title) {
        log.info("职称：更新id为{}的职称", title.getTitleId());
        titleService.update(title);
        return CommonResponse.createForSuccess();
    }

    @GetMapping("/{id}")
    public CommonResponse<Title> getById(@PathVariable Integer id) {
        log.info("职称：查找id为{}的职称", id);
        return CommonResponse.createForSuccess(titleService.getById(id));
    }

    @GetMapping("/all")
    public CommonResponse<List<Title>> getAll() {
        log.info("职称：查找所有职称");
        return CommonResponse.createForSuccess(titleService.getAll());
    }
}
