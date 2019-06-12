package com.silence.controller;

import com.silence.Application;
import com.silence.common.JSONResult;
import com.silence.dao.UserRepository;
import com.silence.entity.User;
import com.silence.exception.TipException;
import com.silence.service.ConfService;
import com.silence.service.ModuleService;
import com.silence.entity.Module;
import com.silence.service.UserService;
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

import java.io.File;
import java.net.URLEncoder;

/**
 * Created by silence on 2018/3/5.
 *
 */
@Controller
@RequestMapping("module")
public class ModuleController {
    @Autowired
    private ModuleService moudleService;
    @Autowired
    private ConfService confService;
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public String list(Model model){
        model.addAttribute("modules",moudleService.findAll());
        return "module/list";
    }

    /**
     * 获取所有模块
     * @return
     */
    @RequestMapping("listAll")
    @ResponseBody //不跳转页面，直接响应json格式的数据
    public Object list(){
      return moudleService.findAll();
    }


    @RequestMapping("get")
    @ResponseBody
    public JSONResult get(String moduleId){
            JSONResult jsonResult=new JSONResult();
        try {
            jsonResult.setResult(moudleService.findById(moduleId));
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMsg(e.getMessage());
            jsonResult.setSuccess(false);
        }
        return jsonResult;
    }

    @RequestMapping(value = "upload",method = RequestMethod.GET)
    public String upload(Model model){
        return "module/upload";
    }

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String upload(Model model, Module module, @RequestParam("file")MultipartFile file){
        try {
            moudleService.upload(module, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/module/list";
    }


    /**
     * 供客户端模块下载
     * @param userName 用户名
     * @param moduleId 模块id
     * @return
     */
    @RequestMapping(value = "download")
    public ResponseEntity<byte[]> download(String userName, String moduleId){
        try {
           userService.cehckUser(userName);



            Module module = moudleService.findById(moduleId);
            File file=new File(confService.get("modulePath")+"/"+moudleService.PACKAGE_PATH+"/"+module.getModuleName()+".class");
            HttpHeaders headers = new HttpHeaders();
            String fileName=module.getModuleName()+".class";
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName,"utf-8"));
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping("del")
    public String del(String moduleId){
        moudleService.del(moduleId);
        return "redirect:/module/list";
    }





}


