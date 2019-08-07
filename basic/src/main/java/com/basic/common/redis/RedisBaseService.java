package com.basic.common.redis;


import java.util.List;

/**
 * redis 基础接口
 *
 * @date 2019/8/6 15:05
 **/
public interface RedisBaseService {

    /**
     * 缓存对象
     *
     * @param key
     * @param obj
     * @param expire 有效时间（单位：秒）
     * @return
     */
    boolean set(String key, Object obj, long expire);

    /**
     * 缓存对象
     *
     * @param key
     * @param obj
     * @param
     * @return
     */
    boolean set(String key, Object obj);

    /**
     * 获取对象
     *
     * @param key
     * @return
     */
    Object get(final String key);

    /**
     * 设置有效时间
     *
     * @param key
     * @param expire
     * @return
     */
    boolean expire(final String key, long expire);


    /**
     * 删除
     *
     * @param key
     * @return
     */
    boolean remove(final String key);

    /**
     * 判断是否含有某个对象
     *
     * @param key
     * @return
     */
    boolean exists(final String key);

    /**
     * 模糊查找所有的key
     */
    Iterable<String> keys(final String keyRegex);

    /**
     * 删除缓存
     *
     * @param keys 可以传一个值 或多个
     */
    void delList(List<String> keys);

    /**
     * 不覆盖保存
     *
     * @param key
     * @param o
     * @param expire
     * @return
     */
    boolean setIfAbsent(String key, Object o, long expire);
}
