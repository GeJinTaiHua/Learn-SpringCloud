package com.customer1.common.config;

import com.basic.common.config.AbstractWebAppConfig;
import com.customer1.common.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class WebAppConfig extends AbstractWebAppConfig {

    @Resource
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void init() {
        this.setHandlerInterceptor(authorizationInterceptor);
    }
}
