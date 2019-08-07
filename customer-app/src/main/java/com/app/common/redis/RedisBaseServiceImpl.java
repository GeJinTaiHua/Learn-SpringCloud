package com.app.common.redis;

import com.basic.common.redis.RedisBaseService;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisBaseServiceImpl implements RedisBaseService {

    @Resource(name = "appRedissonClient")
    private RedissonClient redissonClient;

    @Override
    public boolean set(String key, Object obj, long expire) {
        redissonClient.getBucket(key).set(obj, expire, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public boolean set(String key, Object obj) {
        redissonClient.getBucket(key).set(obj);
        return true;
    }

    @Override
    public Object get(String key) {
        return redissonClient.getBucket(key).get();
    }

    @Override
    public boolean expire(String key, long expire) {
        return redissonClient.getBucket(key).expire(expire, TimeUnit.SECONDS);
    }

    @Override
    public boolean remove(String key) {
        return redissonClient.getBucket(key).delete();
    }

    @Override
    public boolean exists(String key) {
        return redissonClient.getBucket(key).isExists();
    }

    @Override
    public Iterable<String> keys(String keyRegex) {
        return redissonClient.getKeys().getKeysByPattern(keyRegex);
    }

    @Override
    public void delList(List<String> keys) {
        redissonClient.getKeys().delete(keys.toArray(new String[0]));
    }

    @Override
    public boolean setIfAbsent(String key, Object o, long expire) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        if (bucket.isExists()) {
            return false;
        } else {
            bucket.set(o, expire, TimeUnit.SECONDS);
            return true;
        }
    }
}
