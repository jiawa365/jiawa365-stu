package com.silence.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * Created by silence on 2018/2/2.
 * job参数
 */
@Entity(name = "t_param")
public class Param {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "param_id")
    private String paramId;
    @Column(name = "param_key")
    private String paramKey;//参数键
    @Column(name = "param_value")
    private String paramValue;//参数值
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(name="job_id",referencedColumnName="job_id")
    private Job job;//所属job

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Param() {
    }

    public Param(String paramKey, String paramValue, Job job) {
        this.paramKey = paramKey;
        this.paramValue = paramValue;
        this.job = job;
    }
}
