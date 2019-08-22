package com.provider2.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Eureka2ServiceImpl {
    @PostMapping("/discoverTest")
    String discoverTest() {
        return "discoverTest 成功";
    }
}
