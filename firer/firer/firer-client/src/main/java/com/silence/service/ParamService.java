package com.silence.service;

import com.silence.entity.Job;
import com.silence.entity.Param;
import com.silence.repository.JobRepository;
import com.silence.repository.ParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by silence on 2018/2/22.
 */
@Service
public class ParamService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    ParamRepository paramRepository;

    /**
     * 添加参数
     * @param key
     * @param value
     * @param jobId
     * @return
     */
    public Param add(String key,String value,String jobId){
        Job job = jobRepository.findOne(jobId);
        return paramRepository.save(new Param(key,value,job));
    }

}
