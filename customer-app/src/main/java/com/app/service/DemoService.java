package com.app.service;

import com.basic.domain.demo.dto.DemoDTO;
import com.basic.domain.demo.vo.DemoVO;

public interface DemoService {
    DemoVO getDemo(DemoDTO demoDTO);
}
