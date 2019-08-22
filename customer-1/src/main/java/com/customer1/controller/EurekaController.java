package com.customer1.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("注册中心示例")
@RestController
@RequestMapping(value = "/eureka")
public class EurekaController {
}
