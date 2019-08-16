package com.customer1.controller;

import com.customer1.common.annotation.IgnoreToken;
import com.customer1.service.DemoService;
import com.basic.domain.demo.dto.DemoDTO;
import com.basic.domain.demo.vo.DemoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo示例-消费者-Controller层
 **/
@Slf4j
@Api("Demo示例")
@RestController
public class DemoController extends AppBaseController {
    @Autowired
    private DemoService demoService;

    @IgnoreToken
    @ApiOperation(value = "调用示例", notes = "调用生产者简单示例")
    @GetMapping("/getDemo/{id}")
    public DemoVO getDemo(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }

        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setId(id);
        return demoService.getDemo(demoDTO);
    }
}
