package com.ytrue.yadmin.modules.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ytrue
 * @date 2022/5/12 15:06
 * @description TestController
 */
@RestController
public class TestController {


    @RequestMapping("test/test")
    public String test() {
        return "test/test01";
    }

}
