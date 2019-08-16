package com.customer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * start application
 **/
@EnableFeignClients(basePackages = {"com.basic.api"})
@EnableAspectJAutoProxy()
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Customer1Application {
    public static void main(String[] args) {
        SpringApplication.run(Customer1Application.class, args);
    }
}
