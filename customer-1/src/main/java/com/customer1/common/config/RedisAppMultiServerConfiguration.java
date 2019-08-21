package com.customer1.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 配置多个 StringRedisTemplate 对象,并连接不同的redis 服务器
 */
@Configuration
public class RedisAppMultiServerConfiguration {
    @Bean
    public RedissonClient appRedissonClient() throws FileNotFoundException, IOException {
        Config config = Config.fromYAML(ResourceUtils.getFile("classpath:app_redisson.yaml"));
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
  