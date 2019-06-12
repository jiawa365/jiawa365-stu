package com.silence.service;

import com.silence.entity.Conf;
import com.silence.repository.ConfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by silence on 2018/2/22.
 */
@Service
public class ConfService {

    @Autowired
    private ConfRepository confRepository;

    /**
     * 初始化系统配置项
     */
    public void init(){
        if(confRepository.count()<1){
            //初始化
            Conf conf = new Conf("jobEbable", "false");//是否启动job
            confRepository.save(conf);
            conf = new Conf("modulePath", new File("modules").getAbsolutePath());//模块存放位置
            confRepository.save(conf);
            conf = new Conf("server", "http://web.laotan.site/");//服务端地址
            confRepository.save(conf);
            conf = new Conf("driverPath", "chromedriver.exe");//驱动
            confRepository.save(conf);
        }
    }

    /**
     * 获取配置值
     * @param key 配置键
     * @return 值
     */
    public String get(String key){
        Conf conf = confRepository.findOne(key);
        if(conf==null) return null;
        return conf.getValue();
    }


    public void set(String key, String value) {
        Conf conf = new Conf(key, value);
        confRepository.save(conf);
    }
}


