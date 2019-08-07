package com.basic.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
/*
    所有的实体对象需继承这个实体
 */

/**
 * 基础domain
 *
 * @Date 2019/7/26 22:47
 **/
@Slf4j
public class BaseDomain implements Serializable {
    @ApiModelProperty(value = "当前页码")
    @JSONField(serialize = false)
    private Integer currentPage;
    @ApiModelProperty(value = "单页记录数")
    @JSONField(serialize = false)
    private Integer pageSize;

    @Override
    public String toString() {
        String s = null;
        try {
            //使用fastJson打印参数基本信息
            s = JSON.toJSONString(this);
        } catch (Exception e) {
            log.error("toString error:", e);
        }
        return s;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
