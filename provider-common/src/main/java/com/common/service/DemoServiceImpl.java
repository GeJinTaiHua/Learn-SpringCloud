package com.common.service;

import com.basic.api.DemoService;
import com.basic.domain.demo.dto.DemoDTO;
import com.basic.domain.demo.vo.DemoVO;
import com.common.dao.DemoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo示例-提供者-Server层
 **/
@RestController
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoDao demoDao;

    @Override
    public DemoVO getDemoVo(DemoDTO demoDTO) {
        int count = demoDao.selectCount();

        DemoVO result = new DemoVO();
        result.setAge(count);
        return result;
    }
}
