package com.customer1.service.impl;

import com.basic.api.DemoService;
import com.basic.domain.demo.dto.DemoDTO;
import com.basic.domain.demo.vo.DemoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Demo示例-Service层
 **/
@Component
@Slf4j
public class DemoServiceImpl implements com.customer1.service.DemoService {
    @Autowired
    private DemoService demoService;

    @Override
    public DemoVO getDemo(DemoDTO demoDTO) {
        DemoVO result = demoService.getDemoVo(demoDTO);
        return result;
    }
}
