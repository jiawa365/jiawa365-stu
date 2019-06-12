package com.silence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by silence on 2018/3/24.
 */
@Controller
public class IndexController {
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(Model model){
        return "/user/index";
    }
}
