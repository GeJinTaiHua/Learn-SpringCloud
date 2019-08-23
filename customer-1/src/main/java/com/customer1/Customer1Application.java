package com.customer1;

import com.customer1.common.filter.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RefreshScope // 配置中心动态刷新
@EnableDiscoveryClient // 激活Discovery客户端
@EnableFeignClients(basePackages = {"com.basic.api"})// 激活feign客户端
@EnableAspectJAutoProxy() // 开启AOP
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 引导类，排除掉DB
@EnableCircuitBreaker // 使服务能够使用 Hystrix 和 Ribbon
//@EnableEurekaClient // 使用 Eureka ，可省略
//@EnableDiscoveryClient // 使用注册中心（包括 Eureka），可省略
public class Customer1Application {

    @Bean
    @LoadBalanced // 告诉springcloud创建一个支持ribbon的RestTemplate类
    public RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }

        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(Customer1Application.class, args);
    }
}
