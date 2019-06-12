package com.silence.service;

import com.alibaba.fastjson.JSON;
import com.silence.common.FileUtils;
import com.silence.common.HttpClientUtil;
import com.silence.common.JSONResult;
import com.silence.exception.TipException;
import com.silence.repository.ModuleRepository;
import com.silence.entity.Module;
import com.silence.util.MyClassLoader;
import com.silence.util.SJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by silence on 2018/3/5.
 */
@Service
public class ModuleService {


    private ConfService confService;

    @Autowired
    private ModuleRepository moduleRepository;

    public static final String PACKAGE="com.silence.module";
    public static final String PACKAGE_PATH=PACKAGE.replace(".","/");
    public static MyClassLoader myClassLoader=null;

    @Autowired
    public void setConfService(ConfService confService) {
        this.confService = confService;
        myClassLoader=new MyClassLoader(confService.get("modulePath"),"myClassLoader");
    }

    public List<Module> findAll() {
        return moduleRepository.findAll();
    }

    public void upload(Module module, MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String moduleName=originalFilename.substring(0,originalFilename.indexOf("."));
            module.setModuleName(moduleName);
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(confService.get("modulePath")+"/"+PACKAGE_PATH+"/"+moduleName+".class"));
            get(moduleName);
            moduleRepository.save(module);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 根据模块名称获取class对象
     * @param moduleName
     * @return
     */
    public Class get(String moduleName){
        try {
            return myClassLoader.loadClass(PACKAGE+"."+moduleName);
        } catch (ClassNotFoundException e) {
            throw new TipException("模块:"+moduleName+"加载失败,错误信息："+e.getMessage());
        }

    }


    public Module findById(String moduleId) {
        return moduleRepository.findOne(moduleId);
    }



    public List<Class> getAll(){
        List<Class> clazzs=new ArrayList<>();
        List<String> classsNames = myClassLoader.getClasssNames("\\"+PACKAGE.replace(".","\\"));
        try {
            for (int i = 0; i < classsNames.size(); i++) {
                clazzs.add(myClassLoader.loadClass(PACKAGE+"."+classsNames.get(i)));
            }
            return clazzs;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void main1(String[] args) {
        try {
            Object obj = new ModuleService().myClassLoader.loadClass("com.silence.test.Helloworld").newInstance();
            Method go=obj.getClass().getDeclaredMethod("go", Map.class);
            SJob.addJob(obj,go,null,null,null,"1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 从服务端下载模块
     * @param userName
     * @param moduleId
     */
    public void download(String userName, String moduleId) {

        Map<String,String> params=new HashMap();
        params.put("moduleId",moduleId);
        String result = HttpClientUtil.doPost(confService.get("server") + "module/get", params, "utf-8");
        JSONResult jsonResult = JSON.parseObject(result, JSONResult.class);
        if(jsonResult!=null&&jsonResult.isSuccess()){
            Module module = JSON.parseObject(jsonResult.getResult().toString(), Module.class);
             FileUtils.downloadByNIO2(confService.get("server") + "module/download?userName="+userName+"&moduleId="+moduleId,
                    confService.get("modulePath")+"/com/silence/module/",
                    module.getModuleName()+".class");
             moduleRepository.save(module);
        }



    }

    /**
     * 获取指定模块中的所有方法
     * @param moduleId
     * @return
     */
    public Method[] getMethods(String moduleId) {
        Module module = findById(moduleId);
        if(module==null){
            throw new TipException("模块不存在");
        }
        return get(module.getModuleName()).getDeclaredMethods();
    }

    public void del(String moduleId) {
        moduleRepository.delete(moduleId);
    }
}
