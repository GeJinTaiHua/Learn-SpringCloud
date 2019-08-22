package com.provider2.service;

import com.basic.api.DemoService;
import com.basic.domain.demo.dto.DemoDTO;
import com.basic.domain.demo.vo.DemoVO;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo示例-提供者2-Server层
 **/
@RestController
public class Demo2ServiceImpl implements DemoService {
    @Override
    public DemoVO getDemoVo(DemoDTO demoDTO) {
        DemoVO result = new DemoVO();
        result.setAge(20);
        return result;
    }

    @Override
    public String feignTest() {
        return "feignTest 成功";
    }
}
