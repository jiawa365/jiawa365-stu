package com.silence.init;


import com.silence.common.DateUtils;
import com.silence.common.HttpClientUtil;
import com.silence.entity.Job;
import com.silence.repository.JobRepository;
import com.silence.service.ConfService;
import com.silence.service.JobService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


public class Init implements ApplicationListener<ContextRefreshedEvent> {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private JobRepository jobRepository;
    private ConfService confService;
    private JobService jobService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        jdbcTemplate=event.getApplicationContext().getBean(JdbcTemplate.class);
        jobRepository=event.getApplicationContext().getBean(JobRepository.class);
        confService=event.getApplicationContext().getBean(ConfService.class);
        jobService=event.getApplicationContext().getBean(JobService.class);

        //初始化配置项
        confService.init();
        //初始化环境
        System.setProperty("webdriver.chrome.driver", confService.get("driverPath"));

        if("true".equals(confService.get("jobEbable"))){
            //启动时重新启动执行中的任务
            List<Job> jobs = jobRepository.findByStatus(Job.Status.STARTED);
            for (Job job:jobs) {
                if(job.getJobExpression()==null||job.getJobExpression().equals("")){
                    jobService.startJob(job.getJobId(), DateUtils.getDateTimeFromString(job.getBeginTime()));
                }else{
                    jobService.startJob(job.getJobId(),null);
                }
            }
        }

        //检测服务器地址
        new Thread(()->{
          while(true){
              String server=HttpClientUtil.doGet("http://laotan.site/host.php?action=getCurrentUrl");
              confService.set("server",server+"/");
              System.out.println(server);
              try {
                  Thread.sleep(60*60*1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        }).start();
    }

}
