package com.silence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by silence on 2018/2/2.
 */
@Entity(name = "t_job")
public class Job {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "job_id")
    private String jobId;
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
    private Module module;
    @Column(name = "jobName")
    private String jobName;
    @Column(name = "begin_time")
    private String beginTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "status")
    private Integer status;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "error_msg", length = 1000)
    private String errMsg;
    @Column(name = "job_expression")
    private String jobExpression;//表达式（可以是具体时间，也可以是表达式）
    @Column(name = "method_name")
    private String methodName;//执行时调用的方法

    @JsonIgnore
    @OneToMany(mappedBy = "job", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Param> params;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusStr() {
        switch (this.status) {
            case -1:
                return "已创建";
            case 0:
                return "执行中";
            case 1:
                return "成功";
            case 2:
                return "失败";
            case 3:
                return "已停止";
            case 4:
                return "已暂停";
            default:
                return "未知";
        }
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getJobExpression() {
        return jobExpression;
    }

    public void setJobExpression(String jobExpression) {
        this.jobExpression = jobExpression;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Job() {
    }

    public interface Status {
        public static final Integer CREATED = -1;//已创建
        public static final Integer STARTED = 0;//执行中
        public static final Integer SUCCESS = 1;//成功
        public static final Integer FAILED = 2;//失败
        public static final Integer STOPED = 3;//已停止
        public static final Integer PAUSED = 4;//已暂停
    }


}
