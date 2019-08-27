package com.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * start application
 **/
@EnableZipkinServer
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }
}
