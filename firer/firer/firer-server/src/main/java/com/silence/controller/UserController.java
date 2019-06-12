package com.silence.controller;

import com.silence.service.UserService;
import com.silence.entity.User;
import com.silence.exception.TipException;
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

    @RequestMapping(value="register/email",method = RequestMethod.GET)
    public String registerEmail(Model model){
        return "/user/register";
    }

    /**
     * 邮箱注册
     * @param userPass
     * @param email
     */
    @RequestMapping(value="register/email",method = RequestMethod.POST)
    public String registerEmail(String userName ,String userPass,String email,Model model){
        try {
            userService.registerByEmail(userName,email,userPass);
        } catch (TipException e) {
            model.addAttribute("errMsg",e.getMessage());
            return "/user/register";
        }
        return "/user/register";
    }

    @RequestMapping(value="index",method = RequestMethod.GET)
    public String index(Model model){
        return "/user/index";
    }


    @RequestMapping(value="login",method = RequestMethod.GET)
    public String login(Model model){
        return "/user/login";
    }


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



    /**
     * 激活账号
     * @param valiCode
     * @param model
     * @return
     */
    @RequestMapping("active/{valiCode}")
    public String active(@PathVariable("valiCode") String valiCode, Model model){
        try {
            userService.active(valiCode);
        } catch (TipException e) {
            model.addAttribute("errMsg",e.getMessage());

        }
        return "/redirect/user/login";
    }

}
