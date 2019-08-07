package com.basic.common.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 操作bean工具类
 *
 * @author
 * @date 2018/11/29 10:36
 **/
public class BeanUtil {
    /**
     * 单对象属性拷贝
     *
     * @param o   被拷贝对象
     * @param c   拷入对象
     * @param <T>
     * @return
     */
    public static <T> T copyPropertiesToT(Object o, Class<T> c) {
        T t = null;
        try {
            t = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assert t != null;
        if(o == null){
            return null;
        }
        BeanUtils.copyProperties(o, t);
        return t;
    }

    /**
     * list类型转化
     * @param oList 待转list
     * @param c 实体类型
     * @param <T>
     * @return
     */
    public static <T> List<T> copyPropertiesToList(List<?> oList, Class<T> c) {
        if(oList == null){
            return new ArrayList<>();
        }
        return oList.stream().map(o -> {
            T t = null;
            try {
                t = c.newInstance();
                BeanUtils.copyProperties(o, t);
                return t;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
    }

}
