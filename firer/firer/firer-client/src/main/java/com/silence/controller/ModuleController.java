package com.silence.controller;

import com.alibaba.fastjson.JSON;
import com.silence.common.HttpClientUtil;
import com.silence.common.JSONResult;
import com.silence.entity.Module;
import com.silence.entity.User;
import com.silence.service.ConfService;
import com.silence.service.ModuleService;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by silence on 2018/3/5.
 *
 */
@Controller
@RequestMapping("module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ConfService confService;

    /**
     * 下载模块
     * @param moduleId
     * @param session
     * @return
     */
    @RequestMapping(value = "download")
    @ResponseBody
    public Object download(String moduleId, HttpSession session){
    	JSONResult jsonResult=new JSONResult();
        try {
			User user= (User) session.getAttribute("user");//获取当前登录人
            moduleService.download(user.getUserName(), moduleId);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
    }


    @RequestMapping(value = "getMethods")
    @ResponseBody
    public Object getMethods(String moduleId){
        JSONResult jsonResult=new JSONResult();
        try {
           jsonResult.setResult(moduleService.getMethods(moduleId));
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.getMessage());
        }
        //此处由于未知原因，此处选择手动转换json
        return JSON.toJSONString(jsonResult);
    }



    @RequestMapping("list")
    public String list(Model model){
    	//数据来源自服务端
    	String url = confService.get("server")+"module/listAll";
    	String result = HttpClientUtil.doPost(url, null, "utf-8");
    	//将json数据转对象
    	List<Module> modules = JSON.parseArray(result, Module.class);
        model.addAttribute("modules",modules);
        return "module/list";
    }

    @PostMapping("list")
    @ResponseBody
    public Object list(){
       return moduleService.findAll();
    }

    @RequestMapping("myModule")
    public String myModule(Model model){
    	//数据来源于数据库
    	List<Module> modules = moduleService.findAll();
        model.addAttribute("modules",modules);
        return "module/myModule";
    }

    @RequestMapping("del")
    public String del(String moduleId){
        moduleService.del(moduleId);
        return "redirect:/module/myModule";
    }



}


