package com.customer1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("配置中心示例")
@RestController
@RequestMapping(value = "/config")
public class ConfigController extends AppBaseController {
    @Value("${demo.customer.profiles}")
    private String profiles;

    @ApiOperation(value = "从配置中心获取变量")
    @PostMapping("/getProfiles")
    public String getProfiles() {
        return profiles;
    }
}
