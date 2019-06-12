package com.silence.controller;

import com.silence.common.JSONResult;
import com.silence.entity.User;
import com.silence.exception.TipException;
import com.silence.service.ConfService;
import com.silence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by silence on 2018/2/28.
 */
@Controller
@RequestMapping("conf")
public class ConfController {


    @Autowired
    private ConfService confService;

    @RequestMapping
    @ResponseBody
    public JSONResult set(String key, String value){
        JSONResult jsonResult=new JSONResult();
        try {
            confService.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping("get")
    @ResponseBody
    public JSONResult get(String key){
        JSONResult jsonResult=new JSONResult();
        try {
            jsonResult.setResult(confService.get(key));
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }




}
