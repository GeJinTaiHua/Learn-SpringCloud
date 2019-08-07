package com.basic.common.util;

import com.github.pagehelper.Page;
import com.basic.domain.PageInfo;

import java.util.List;


/**
 *
 * @date 2018/11/27 15:22
 **/
public class PageUtil {
    public static <T> PageInfo<T> generate(PageInfo pageInfo, List<T> list){
        int total = 0;
        int pageNum = 0;
        int pageSize = 0;
        int totalPage = 0;
        if(list instanceof Page){
            Page page  = (Page) list;
            long t = page.getTotal();
            total = Integer.valueOf(Long.toString(t));
            pageNum = page.getPageNum();
            pageSize = page.getPageSize();
            totalPage = page.getPages();
        }
        pageInfo.setTotalCount(total);
        pageInfo.setDataList(list);
        pageInfo.setCurrentPage(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotalPage(totalPage);
        return pageInfo;
    }
}
