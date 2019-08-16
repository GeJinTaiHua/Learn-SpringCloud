package com.basic.api;

import com.basic.domain.demo.dto.DemoDTO;
import com.basic.domain.demo.vo.DemoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Demo示例-接口定义
 **/
@FeignClient("provider-1")
public interface DemoService {
    @PostMapping("/fegin/test/getDemo")
    DemoVO getDemoVo(@RequestBody DemoDTO demoDTO);
}
