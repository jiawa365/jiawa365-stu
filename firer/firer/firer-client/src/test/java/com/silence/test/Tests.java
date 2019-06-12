package com.silence.test;

import com.silence.Application;
import com.silence.entity.Module;
import com.silence.repository.ModuleRepository;
import com.silence.service.JobService;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * Created by silence on 2018/2/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestCase.class)
@Import(Application.class)
public class Tests {
    @Autowired
    private JobService jobService;

    @Autowired
    private ModuleRepository moduleRepository;


    @Test
    public void addJob(){
        jobService.addJob("821cd4e6-2813-4909-95b9-2e59d4b06ddc","发布房源",null,null,"login");
    }

    @Test
    public void addJob1(){
        Map<String,String> param=new HashedMap();
        param.put("countName","921595360@qq.com");
        //jobService.addJob("821cd4e6-2813-4909-95b9-2e59d4b06ddc","发布房源1",null,param,"login");
    }

    @Test
    public void addModule() {
        Module module = new Module("58同城发布", "58同城发布房源");
        moduleRepository.save(module);
    }

    @Test
    public void startJob(){
        jobService.startJob("823b49eb-0e4d-4aa5-8974-fc80960b7d4d",null);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
