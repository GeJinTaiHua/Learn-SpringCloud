package com.provider1.service;

import com.basic.api.DemoService;
import com.basic.domain.demo.dto.DemoDTO;
import com.basic.domain.demo.vo.DemoVO;
import com.provider1.dao.DemoDao;
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
        int age = demoDao.selectCount();
        DemoVO result = new DemoVO();
        result.setAge(age);
        return result;
    }
}
