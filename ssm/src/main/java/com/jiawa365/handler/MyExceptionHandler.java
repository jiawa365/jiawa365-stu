package com.jiawa365.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by silence-pc on 2019/6/25.
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public String test(Exception e,Model model){
        model.addAttribute("errMsg",e.getMessage());
        return "/error.jsp";
    }
}
