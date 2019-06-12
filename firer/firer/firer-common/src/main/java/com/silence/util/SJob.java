package com.silence.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;


/**
 * 通过反射调用目标函数
 * 此处限制目标函数只接受单个Map<String,String>参数
 */
public class SJob implements Job {
    public static SchedulerFactory sf = null;
    public static Scheduler sched = null;

    static {
        SchedulerFactory sf = new StdSchedulerFactory();
        try {
            sched = sf.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap dataMap = context.getMergedJobDataMap();
            Object obj = dataMap.get("obj");
            Method method = (Method) dataMap.get("method");
            method.setAccessible(true);
            Object params = dataMap.get("params");
            method.invoke(obj,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加job
     *
     * @param obj        目标对象
     * @param method     目标方法
     * @param params     参数
     * @param date       执行日期
     * @param expression 表达式（表达式与日期任选，都为null表示立即执行）
     */
    public static void addJob(Object obj, Method method, Map<String,String> params, Date date, String expression, String jobId) {
        try {
            JobDataMap data = new JobDataMap();
            data.put("obj", obj);
            data.put("method", method);
            data.put("params", params);

            JobDetail job = JobBuilder.newJob(SJob.class).usingJobData(data).withIdentity(jobId, "job").build();
            Trigger trigger = null;
            if (expression == null||expression.equals("")) {
                if (date == null) date = new Date();
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(jobId, "trigger")
                        .startAt(date)
                        .build();
            } else {
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(jobId, "trigger")
                        .withSchedule(CronScheduleBuilder.cronSchedule(expression))
                        .build();
            }
            sched.scheduleJob(job, trigger);
            sched.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停job(同时暂停该job对应的触发器)
     */
    public static void pauseJob(String jobId) {
        try {
            sched.pauseJob(new JobKey(jobId, "job"));
            sched.pauseTrigger(new TriggerKey(jobId, "trigger"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复job
     *
     * @param jobId
     */
    public static void resumeJob(String jobId) {
        try {
            sched.resumeJob(new JobKey(jobId, "job"));
            sched.resumeTrigger(new TriggerKey(jobId, "trigger"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除job
     *
     * @param jobId
     */
    public static void delJob(String jobId) {
        try {
            sched.unscheduleJob(new TriggerKey(jobId, "trigger"));
            sched.deleteJob(new JobKey(jobId, "job"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取job状态
     *
     * @param jobId
     * @return
     */
    public static String getJobStatus(String jobId) {
        try {
            return sched.getTriggerState(new TriggerKey(jobId, "trigger")).toString();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }


}
