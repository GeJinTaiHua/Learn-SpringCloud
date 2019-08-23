package com.customer1.controller;

import com.customer1.service.HystrxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("断路器示例")
@RestController
@RequestMapping(value = "/hystrix")
public class HystrixController extends AppBaseController {

    @Autowired
    private HystrxService hystrxService;

    @ApiOperation(value = "hystrix示例")
    @RequestMapping(value = "/hystrixTest", method = RequestMethod.POST)
    public String hystrixTest() {
        String result = hystrxService.hystrixTest();
        return result;
    }

    @ApiOperation(value = "hystrix示例-自定义超时时间")
    @RequestMapping(value = "/hystrixTest2", method = RequestMethod.POST)
    public String hystrixTest2() {
        String result = hystrxService.hystrixTest2();
        return result;
    }

    @ApiOperation(value = "hystrix示例-后备模式")
    @RequestMapping(value = "/hystrixTest3", method = RequestMethod.POST)
    public String hystrixTest3() {
        String result = hystrxService.hystrixTest3();
        return result;
    }

    @ApiOperation(value = "hystrix示例-舱壁模式")
    @RequestMapping(value = "/hystrixTest4", method = RequestMethod.POST)
    public String hystrixTest4() {
        String result = hystrxService.hystrixTest4();
        return result;
    }
}
