package com.customer1.controller;

import com.basic.domain.demo.dto.DemoDTO;
import com.basic.domain.demo.vo.DemoVO;
import com.customer1.common.annotation.IgnoreToken;
import com.customer1.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Demo示例-消费者-Controller层
 **/
@IgnoreToken
@Api("示例")
@RestController
@RequestMapping(value = "/api/demo")
public class DemoController extends AppBaseController {
    @Autowired
    private DemoService demoService;

    @ApiOperation(value = "示例Demo")
    @GetMapping("/getDemo/{id}")
    public DemoVO getDemo(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }

        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setId(id);
        return demoService.getDemo(demoDTO);
    }

    @ApiOperation(value = "示例1.5")
    @RequestMapping(value = "/{firstName}/{lastName}", method = RequestMethod.GET)
    public String hello(@PathVariable("firstName") String firstName,
                        @PathVariable("lastName") String lastName) {
        return String.format("{\"message\":\"Hello %s %s\"}", firstName, lastName);
    }
}
