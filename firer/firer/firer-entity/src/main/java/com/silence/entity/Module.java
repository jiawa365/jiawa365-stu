package com.silence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

import java.util.List;

/**
 * Created by silence on 2018/2/2.
 *
 * 模块
 *
 */
@Entity(name = "t_module")
public class Module {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "module_id")
    private String moduleId;

    @Column(name = "module_name")
    private String moduleName;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description",columnDefinition = "text")
    private String description; //描述
    @Column(name = "status")
    private Integer status;

    @JsonIgnore
    @OneToMany(mappedBy="module",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Job> jobs;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static interface Status{
        public static Integer CREATED=0; //待审核
        public static Integer ENABLED=1; //可用
        public static Integer DISABLED=-1; //禁用
    }

    public Module() {
    }

    public Module(String moduleName, String description) {
        this.moduleName = moduleName;
        this.description = description;
    }


}
