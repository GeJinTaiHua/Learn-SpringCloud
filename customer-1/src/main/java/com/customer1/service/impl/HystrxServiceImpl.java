package com.customer1.service.impl;

import com.customer1.common.filter.UserContext;
import com.customer1.common.filter.UserContextHolder;
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
        UserContext userInfo = new UserContext();
        userInfo.setUserId("11111");
        UserContextHolder.setContext(userInfo);

        // 异常
        int i = 1, j = 0;
        i = i / j;
        return "String hystrixTest3() 成功";
    }

    /**
     * Hystrix默认thread模式，以独立与福线程的另一个线程运行，无法使用上下文！
     */
    private String hystrixTest3_fallbacke() {
        String userUuid = UserContextHolder.getContext().getUserId();
        return "hystrixTest3_fallbacke" + userUuid;
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
            }
    )
    @Override
    public String hystrixTest4() {
        return "String hystrixTest4() 成功";
    }

    /**
     * 定义窗口
     */
    @HystrixCommand(
            threadPoolKey = "hystrixTest5ThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            },
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 跳闸前，在10s内必须发生的连续调用数量
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),// 失败百分比阈值，超过就跳闸
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),// 跳闸后，另一个调用通过以便查看服务是否恢复健康之前的休眠时间
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),// 用来监视服务调用问题的窗口大小
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")// 在定义的滚动窗口中收集统计信息的次数
            }
    )
    @Override
    public String hystrixTest5() {
        return "String hystrixTest5() 成功";
    }
}
