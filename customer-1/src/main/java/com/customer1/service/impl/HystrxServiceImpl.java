package com.customer1.service.impl;

import com.customer1.service.HystrxService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class HystrxServiceImpl implements HystrxService {

    /**
     * 默认1s超时熔断
     */
    @HystrixCommand
    @Override
    public String hystrixTest() {
        return "String hystrixTest() 成功";
    }

    /**
     * 自定义10s超时熔断
     */
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
            })
    @Override
    public String hystrixTest2() {
        return "String hystrixTest2() 成功";
    }

    /**
     * 后备模式
     * 超时时调用hystrixTest3_fallbacke()方法
     */
    @HystrixCommand(fallbackMethod = "hystrixTest3_fallbacke")
    @Override
    public String hystrixTest3() {
        return "String hystrixTest3() 成功";
    }

    private String hystrixTest3_fallbacke() {
        return "hystrixTest3_fallbacke";
    }

    /**
     * 舱壁模式
     * 隔离到自己的的线程池
     */
    @HystrixCommand(
            threadPoolKey = "hystrixTest4ThreadPool",// 表示建立一个新的线程池
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),// 线程池大小
                    @HystrixProperty(name = "maxQueueSize", value = "10")// 线程池等待队列大小，超过数目的请求将失败
            },
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
            }
    )
    @Override
    public String hystrixTest4() {
        return "String hystrixTest4() 成功";
    }
}
