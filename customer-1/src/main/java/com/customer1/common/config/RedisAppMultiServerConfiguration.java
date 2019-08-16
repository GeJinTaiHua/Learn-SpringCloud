package com.customer1.common.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

/**
 *
 * 配置多个 StringRedisTemplate 对象,并连接不同的redis 服务器
 *
 */

@Configuration
public class RedisAppMultiServerConfiguration {

/*	@Value("${spring.redis.host}")
	private String hostName;
	@Value("${spring.redis.database}")
	private int index;

	@Value("${spring.redis2.database}")
	private int index2;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.password}")
	private String password;
	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;
	@Value("${spring.redis.pool.max-active}")
	private int maxTotal;


	*//**
   * 用户
	 * @return
   *//*
	@Bean(name = "User_redisTemplate")
	public StringRedisTemplate sysRedisTemplateConfig() {
		StringRedisTemplate temple = new StringRedisTemplate();
		temple.setConnectionFactory(connectionFactory(hostName, port, password,
				maxIdle, maxTotal, index));

		return temple;
	}


	*//**
	 * 系统字典
	 * @return
	 *//*
	@Bean(name = "sys_redisTemplate")
	public StringRedisTemplate userRedisTemplateConfig() {
		StringRedisTemplate temple = new StringRedisTemplate();
		temple.setConnectionFactory(connectionFactory(hostName, port, password,
			maxIdle, maxTotal, index2));

		return temple;
	}

	public RedisConnectionFactory connectionFactory(String hostName, int port,
                                                  String password, int maxIdle, int maxTotal, int index) {
		JedisConnectionFactory jedis = new JedisConnectionFactory();
		jedis.setHostName(hostName);
		jedis.setPort(port);
		if (!StringUtil.isEmpty(password)) {
			jedis.setPassword(password);
		}
		if (index != 0) {
			jedis.setDatabase(index);
		}
		jedis.setPoolConfig(poolCofig(maxIdle, maxTotal));
		// 初始化连接pool
		jedis.afterPropertiesSet();
		RedisConnectionFactory factory = jedis;

		return factory;
	}

	public JedisPoolConfig poolCofig(int maxIdle, int maxTotal) {
		JedisPoolConfig poolCofig = new JedisPoolConfig();
		poolCofig.setMaxIdle(maxIdle);
		poolCofig.setMaxTotal(maxTotal);
		return poolCofig;
	}*/
	
	@Bean
	public RedissonClient appRedissonClient() throws FileNotFoundException, IOException{
		Config config = Config.fromYAML(ResourceUtils.getFile("classpath:app_redisson.yaml"));
		RedissonClient redisson = Redisson.create(config);
		return redisson;
	}

}
  