package com.jiawa365.consumer.controller;

import com.jiawa365.consumer.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by silence-pc on 2019/7/4.
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return testService.test();
    }

    public String error(){
        return "error,wait moment";
    }
}
