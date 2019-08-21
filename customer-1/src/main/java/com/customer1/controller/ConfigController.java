package com.customer1.controller;

import com.customer1.common.annotation.IgnoreToken;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@IgnoreToken
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
