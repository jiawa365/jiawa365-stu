package com.silence.service;

import com.silence.common.CommonUtils;
import com.silence.common.DateUtils;
import com.silence.common.Mails;
import com.silence.common.PKUtil;
import com.silence.entity.User;
import com.silence.exception.TipException;
import com.silence.repository.UserRepository;
import com.silence.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by silence on 2018/2/28.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfService confService;


    /**
     * 检查该名称是否能够使用
     * @param name
     */
    private void check(String name) {
        User user=userRepository.findByEmailOrPhone(name,name);
        if(user!=null){
            throw new TipException("该账户已存在");
        }
    }




    /**
     * 登录
     * @param name 邮箱或手机号
     * @param userPass
     * @return
     */
    public User login(String name, String userPass) {
        if(userRepository.count()<1){
            //首次登录直接添加该用户
            User newUser=new User();
            newUser.setUserName(name);
            newUser.setEmail(name);
            newUser.setUserPass(userPass);
            newUser.setStatus(User.Status.ENABLED);
            userRepository.save(newUser);
        }
        User user = userRepository.findByEmailOrPhone(name, name);
        if(user!=null&&user.getUserPass().equals(userPass)){
            return user;
        }else{
            throw new TipException("用户名或密码错误");
        }
    }
}
