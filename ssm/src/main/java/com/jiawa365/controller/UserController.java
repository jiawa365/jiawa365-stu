package com.jiawa365.controller;

import com.jiawa365.bean.User;
import com.jiawa365.exception.LoginFailedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by silence-pc on 2019/6/19.
 */
@Controller
public class UserController {


    @GetMapping("/login")
    public String login(){
       throw new LoginFailedException();
    }



    @GetMapping("/register")
    public String register(){
        int i=1/0;
        System.out.println("UserController.register");
        return "register.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid  User user, BindingResult bindingResult, Model model){

        if(bindingResult.getErrorCount()>0){
            String errMsg = bindingResult.getFieldError().getDefaultMessage();
            model.addAttribute("errMsg",errMsg);
            return "register.jsp";
        }
        System.out.println(bindingResult);
        System.out.println("UserController.register");
        return "register.jsp";
    }


}
