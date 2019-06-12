package com.silence.controller;

import com.silence.entity.User;
import com.silence.exception.TipException;
import com.silence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by silence on 2018/2/28.
 */
@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;




    @RequestMapping(value="index",method = RequestMethod.GET)
    public String index(Model model){
        return "/user/index";
    }


    /**
     * 跳转到登录页面
     * @param model
     * @return
     */
    @RequestMapping(value="login",method = RequestMethod.GET)
    public String login(Model model){
        return "/user/login";
    }


    /**
     * 处理登录请求
     * @param name
     * @param userPass
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value="login",method = RequestMethod.POST)
    public String login(String name , String userPass, Model model, HttpSession session){
        try {
            User user = userService.login(name, userPass);
            session.setAttribute("user",user);
        } catch (TipException e) {
            model.addAttribute("errMsg",e.getMessage());
            return "/user/login";
        }
        return "redirect:/user/index";
    }

}
