package com.jiawa365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by silence-pc on 2019/7/4.
 */
@Controller
public class TestController {

    @RequestMapping("test")
    @ResponseBody
    public String test(){
        return "test";
    }
}
