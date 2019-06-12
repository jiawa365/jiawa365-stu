package com.silence.service;

import com.silence.common.DateUtils;
import com.silence.entity.Job;
import com.silence.entity.Module;
import com.silence.entity.Param;
import com.silence.exception.TipException;
import com.silence.repository.JobRepository;
import com.silence.repository.ModuleRepository;
import com.silence.util.SJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by silence on 2018/2/2.
 */
@Service
public class JobService {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ParamService paramService;


    /**
     * 创建job
     *
     * @param moduleId
     * @param jobName
     * @param jobExpression
     * @param param
     * @param methodName
     */
    public Job addJob(String moduleId, String jobName, String jobExpression, Map<String, String> param, String methodName) {
        Module module = moduleRepository.findOne(moduleId);
        if (module == null) {
            throw new TipException("模块:" + moduleId + "不存在");
        }

        Job job = new Job();
        job.setStatus(Job.Status.CREATED);
        job.setJobName(jobName);
        job.setCreateTime(DateUtils.getDatetime());
        job.setModule(module);
        job.setJobExpression(jobExpression);
        job.setMethodName(methodName);

        if(param!=null&&param.size()>0){
            List<Param> paramTmp=new ArrayList<>();
            job.setParams(paramTmp);
            //添加参数
            Set<String> keys = param.keySet();
            for (String key :keys) {
                paramTmp.add(new Param(key,param.get(key),job));
            }
        }

        job = jobRepository.save(job);
        return job;
    }

    /**
     * 启动job
     *
     * @param jobId
     * @param beginTime 执行时间，可为null
     */
    public void startJob(String jobId, Date beginTime) {
        try {
            Job job = jobRepository.findOne(jobId);
            startJob(jobId, beginTime,job.getJobExpression());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param jobId
     * @param beginTime  开始时间，表达式均为空则立即执行
     * @param expression
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void startJob(String jobId, Date beginTime, String expression) {
        try {
            Job job = jobRepository.findOne(jobId);
            Module module = job.getModule();
            Class clazz = moduleService.get(module.getModuleName());
            Method method = clazz.getDeclaredMethod(job.getMethodName(), Map.class);
            Object obj = clazz.newInstance();
            Map<String, String> params = getParams(jobId);
            //启动job
            SJob.addJob(obj, method, params, beginTime, expression, jobId);
            job.setStatus(Job.Status.STARTED);
            job.setBeginTime(DateUtils.getDatetime());
            jobRepository.save(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取job对应的参数
     *
     * @param jobId
     * @return
     */
    private Map<String, String> getParams(String jobId) {
        Job job = jobRepository.findOne(jobId);
        List<Param> params = job.getParams();
        Map<String, String> result = new HashMap<>();
        for (Param p : params) {
            result.put(p.getParamKey(), p.getParamValue());
        }
        return result;
    }

	public List<Job> findAll() {
		List<Job> jobs= jobRepository.findAll();
		return jobs;
	}


    public void del(String jobId) {
        //删除
        SJob.delJob(jobId);
        jobRepository.delete(jobId);
    }


    public void changeStatus(String jobId, Integer status) {


        Job job = jobRepository.findOne(jobId);

        switch (status) {
            case -1:
                break;
            case 0:
                //恢复或启动
                if(job.getStatus().equals(Job.Status.PAUSED)){
                    SJob.resumeJob(jobId);//恢复
                }else{
                    //启动
                    startJob((String) jobId, (Date) null);
                }


                break;
            case 1:

                break;
            case 2:break;
            case 3:
                SJob.delJob(jobId);//停止
                break;
            case 4:
                SJob.pauseJob(jobId);//暂停
                break;
            default:break;
        }

        job.setStatus(status);
        jobRepository.save(job);

    }


    /**
     * 单次立即执行
     * @param jobId
     */
    public void singleStart(String jobId) {
        startJob(jobId,null,null);
    }
}
