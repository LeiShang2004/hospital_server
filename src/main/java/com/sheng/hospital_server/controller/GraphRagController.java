package com.sheng.hospital_server.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.sheng.hospital_server.api.GraphRagCaller;
import com.sheng.hospital_server.comnon.CommonResponse;
import com.sheng.hospital_server.pojo.QueryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * GraphRag控制器
 */
@Slf4j
@RestController
@RequestMapping("/graphRag")
@CrossOrigin//跨域
@SaCheckRole("user")
public class GraphRagController {
    @PostMapping
    public CommonResponse<String> getGraphRag(@RequestBody QueryVO queryVO) throws IOException {
        log.info("GraphRag：发送查询{}", queryVO.getQuery());
        String result = GraphRagCaller.send(queryVO.getQuery());
        return CommonResponse.createForSuccess(result);
    }
}