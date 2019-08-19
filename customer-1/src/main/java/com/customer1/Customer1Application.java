package com.customer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * start application
 **/
@EnableFeignClients(basePackages = {"com.basic.api"})// 启用feign客户端
@EnableAspectJAutoProxy() // 开启AOP
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCircuitBreaker // 使服务能够使用 Hystrix 和 Ribbon
//@EnableEurekaClient // 使用 Eureka ，可省略
//@EnableDiscoveryClient // 使用注册中心（包括 Eureka），可省略
public class Customer1Application {
    public static void main(String[] args) {
        SpringApplication.run(Customer1Application.class, args);
    }
}
