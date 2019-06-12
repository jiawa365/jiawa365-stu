package com.silence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 配置项
 */
@Entity(name = "t_conf")
public class Conf {
    @Id
    @Column(name = "c_key")
    private String key;

    @Column(name = "c_value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Conf() {
    }

    public Conf(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
