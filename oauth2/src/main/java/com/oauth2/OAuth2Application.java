package com.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * start application
 **/
@EnableResourceServer
@EnableAuthorizationServer // 该服务作为OAuth2服务
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OAuth2Application {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2Application.class, args);
    }
}
