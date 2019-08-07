package com.common.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * Demo示例-mapper层
 **/
@Mapper
public interface DemoDao {
    int selectCount();
}
