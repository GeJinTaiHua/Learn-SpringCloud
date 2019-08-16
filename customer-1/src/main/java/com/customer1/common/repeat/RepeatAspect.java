package com.customer1.common.repeat;

import com.basic.common.annotation.Repeat;
import com.basic.common.exception.RepeatException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
@Component
public class RepeatAspect {

    private static Logger log = LoggerFactory.getLogger(RepeatAspect.class);

    @Resource(name = "appRedissonClient")
    private RedissonClient redissonClient;

    @Pointcut("execution(public * com.customer1.controller.*.*.*(..))")
    public void repeatPointcut() {
    }

    @Around("repeatPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws RepeatException, Throwable {
        //获取拦截的方法参数
        Object[] args = pjp.getArgs();
        //获取方法调用的类
        Class<?> clazz = getTarget(pjp.getTarget()).getClass();

        Class<?>[] paramTyps = new Class<?>[args.length];

        for (int i = 0; i < args.length; i++) {
            paramTyps[i] = args[i].getClass();
        }

        //构建方法全名称
        String methodName = clazz.getName() + "." + pjp.getSignature().getName();

        if (log.isInfoEnabled()) {
            log.info("执行方法【{}】", methodName);
        }

        //利用反射获取调用的方法实例
        Method invokeMethod = findMethod(clazz, pjp.getSignature().getName(), paramTyps);

        if (invokeMethod == null) {
            log.error("通过反射未找到【{}】方法！", methodName);
            throw new Throwable("通过反射未找到【" + methodName + "】方法！");
        }

        //获取方法上是否有Repeat注解
        Repeat repeat = invokeMethod.getAnnotation(Repeat.class);

        if (repeat == null) {
            try {
                return pjp.proceed();
            } catch (Throwable e) {
                log.error("执行方法【" + methodName + "】出错！", e);
                throw e;
            }
        }

        //生成请求token
        String requestToken = methodName + ":" + hashSignature(methodName, args);

        if (log.isInfoEnabled()) {
            log.info("请求方法【{}】,生成请求token【{}】", methodName, requestToken);
        }

        //加锁
        RLock lock = redissonClient.getLock(requestToken);

        try {
            if (!lock.tryLock()) {
                throw new RepeatException("重复请求，加锁失败！");
            }
        } catch (RepeatException e) {
            throw e;
        } catch (Throwable e) {
            log.error("加锁失败!", e);
            throw e;
        }


        try {
            return pjp.proceed();
        } catch (Throwable e) {
            log.error("加锁后,执行方法【" + methodName + "】出错！", e);
            throw e;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param clazz
     * @param methodName
     * @param paramTypes
     * @return
     * @Title: findMethod
     * @Description: 反射查找方法
     */
    public Method findMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes) {
        return ReflectionUtils.findMethod(clazz, methodName, paramTypes);
    }

    /**
     * @param methodName
     * @param objs
     * @return
     * @Title: hashSignature
     * @Description: 方法签名
     */
    public int hashSignature(String methodName, Object[] objs) {
        int hCode = 0;
        for (int i = 0; i < objs.length; i++) {
            hCode += objs[i].hashCode();
        }
        hCode += methodName.hashCode();
        return hCode;
    }

    /**
     * 获取 目标对象
     *
     * @param proxy 代理对象
     * @return 目标对象
     * @throws Exception
     */
    public static Object getTarget(Object proxy) throws Exception {
        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;//不是代理对象
        }

        if (AopUtils.isJdkDynamicProxy(proxy)) {
            return getJdkDynamicProxyTargetObject(proxy);
        } else { //cglib
            return getCglibProxyTargetObject(proxy);
        }
    }


    private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
        Field field = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        field.setAccessible(true);
        Object dynamicAdvisedInterceptor = field.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

        return target;
    }


    private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
        Field field = proxy.getClass().getSuperclass().getDeclaredField("h");
        field.setAccessible(true);
        AopProxy aopProxy = (AopProxy) field.get(proxy);

        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();

        return target;
    }
}
