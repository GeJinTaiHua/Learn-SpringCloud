package com.customer1.controller;

import com.customer1.service.EurekaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("注册中心示例")
@RestController
@RequestMapping(value = "/eureka")
public class EurekaController {
    @Autowired
    private EurekaService eurekaService;

    @ApiOperation(value = "Discover示例")
    @RequestMapping(value = "/discoverTest", method = RequestMethod.POST)
    public String discoverTest() {
        String result = eurekaService.discoverTest();
        return result;
    }
}
