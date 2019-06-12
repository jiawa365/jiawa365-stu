package com.silence.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.silence.common.JSONResult;
import com.silence.entity.Param;
import com.silence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.silence.entity.Job;
import com.silence.service.JobService;

/**
 * Created by silence on 2018/3/5.
 *
 */
@Controller
@RequestMapping("job")
public class JobController {
    @Autowired
	private JobService jobService;
	@RequestMapping("list")
    public String list(Model model){
    	//数据来源于数据库
		List<Job> jobs = jobService.findAll();
		model.addAttribute("jobs", jobs);
        return "job/list";
    }

    @RequestMapping("del")
    public String del(String jobId){
       jobService.del(jobId);
        return "redirect:/job/list";
    }

    @RequestMapping("changeStatus")
    public String changeStatus(String jobId,Integer status){
        jobService.changeStatus(jobId,status);
        return "redirect:/job/list";
    }

    @RequestMapping("singleStart")
    public String singleStart(String jobId){
        jobService.singleStart(jobId);
        return "redirect:/job/list";
    }


    @PostMapping("add")
    @ResponseBody
    public JSONResult add(String moduleId, String jobName, String jobExpression,String methodName,String params){
        JSONResult jsonResult=new JSONResult();
        try {
            jobService.addJob(moduleId,jobName,jobExpression,
                    JSON.parseObject(params,Map.class),
                    methodName);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }


}


