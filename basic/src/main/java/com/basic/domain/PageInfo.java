package com.basic.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页
 */
public class PageInfo<T> implements Serializable {

    @ApiModelProperty(value = "总页数")
    private Integer totalPage;
    @ApiModelProperty(value = "当前页码")
    private Integer currentPage;
    @ApiModelProperty(value = "单页记录数")
    private Integer pageSize;
    @ApiModelProperty(value = "总记录数")
    private Integer totalCount;
    @ApiModelProperty(value = "列表")
    private List<T> dataList;
    @ApiModelProperty(value = "其它数据")
    private Map<String, Object> otherData = null;

    public void addOtherData(String key, Object value) {
        if (null == otherData) {
            otherData = new HashMap<>();
        }
        otherData.put(key, value);
    }

    public PageInfo() {
        super();
    }

    public PageInfo(Integer currentPage, Integer pageSize, Integer totalCount, Integer totalPage, List<T> dataList) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.dataList = dataList;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Map<String, Object> getOtherData() {
        return otherData;
    }

    public void setOtherData(Map<String, Object> otherData) {
        this.otherData = otherData;
    }
}
