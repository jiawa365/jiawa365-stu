package com.jiawa365.bean;

import javax.validation.constraints.*;

/**
 * Created by silence-pc on 2019/6/19.
 */
public class User {
    @NotEmpty
    private String userName;

    @NotBlank(message = "密码不能包含空格")
    @NotEmpty(message = "密码不能为空")
    @Size(max = 20,min = 6,message = "长度6-20")
    private String userPass;

    @Max(value = 130,message = "年龄不能大于130")
    @Min(value = 0,message = "年能不能小于0")
    private Integer age;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
