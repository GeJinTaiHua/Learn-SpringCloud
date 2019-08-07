package com.basic.domain.demo.dto;

import com.basic.domain.demo.Demo;
import lombok.Data;

/**
 * Demo 数据传输对象
 **/
@Data
public class DemoDTO extends Demo {
    private String addr;
}
