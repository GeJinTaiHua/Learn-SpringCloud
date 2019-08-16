package com.provider1.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * Demo示例-mapper层
 **/
@Mapper
public interface DemoDao {
    int selectCount();
}
