package com.silence.service;

import com.silence.dao.UserRepository;
import com.silence.common.CommonUtils;
import com.silence.common.DateUtils;
import com.silence.common.Mails;
import com.silence.common.PKUtil;
import com.silence.entity.User;
import com.silence.exception.TipException;
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
     * 邮箱注册
     * @param userName
     * @param email
     * @param userPass
     */
    @Transactional
    public void registerByEmail(String userName, String email, String userPass) {
        if(CommonUtils.isNullOrEmpty(userPass)||CommonUtils.isNullOrEmpty(email)){
            throw new TipException("邮箱或密码不能为空");
        }
        check(email);

        //生成验证码
        String valiCode = PKUtil.getPrimarykeyStr();


        //发送激活邮件
        String subject="请激活您的Firer用户帐号";
        String html="<table width=\"100%\" style=\"text-align: left; background: #fff; border: 1px solid #DDDDDD; font-family: 'Microsoft Yahei'; line-height: 24px;\">\n" +
                "                <tbody><tr>\n" +
                "                    <td style=\" padding: 10px;\">\n" +
                "                        <h2 style=\"font-size: 14px; color: #003c83;\"><b>尊敬的用户: <font style=\"color:#003c83\"><a href=\"mailto:921595360@qq.com\" target=\"_blank\">921595360@qq<wbr>.com</a></font></b><br></h2>\n" +
                "                        <p>您好，感谢您注册和使用Firer。<br>\n" +
                "                            <span style=\"color: #2A383F\">请点击下方链接激活您的Firer会员帐号</span><br>\n" +
                "                            <a href=\""+confService.get("server")+"/user/active/"+valiCode+"\" target=\"_blank\">"+confService.get("server")+"/user/active/"+valiCode+"</a><br>\n" +
                "                            <i>(如果以上链接无效，请将以上地址复制到您的浏览器地址栏)</i><br><br>\n" +
                "                        </p>            \n" +
                "                        <p>\n" +
                "                            如有任何疑问或遇到问题，技术支持QQ：<span style=\"border-bottom:1px dashed #ccc;z-index:1\" t=\"7\" onclick=\"return false;\" data=\"921595360\">921595360</span>；发送电子邮件至 <a href=\"mailto:921595360@qq.com\" target=\"_blank\">921595360@qq.<wbr>com</a>，请随时与我们联系！<br>感谢您的使用，期望我们能给您带来满意的体验!\n" +
                "                        </p>\n" +
                "                        <p style=\"text-align:right; border-top:1px solid #cccccc;font-size: 14px; padding-top: 16px; color: #008cd6\">\n" +
                "                            此致\n" +
                "                            <br>\n" +
                "                            Firer管理团队敬上\n" +
                "                        </p>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody></table>";

        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setUserPass(userPass);
        user.setValiCode(valiCode);
        user.setCreateTime(DateUtils.getDatetime());
        user.setStatus(User.Status.CREATED);
        userRepository.save(user);

        //发送验证邮件
        Mails.sendHtml(PropertiesUtil.get("email.username",""),PropertiesUtil.get("email.password",""),email,subject,html);
    }

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
     * 激活账号
     * @param valiCode
     */
    public void active(String valiCode) {
        User user = userRepository.findByValiCode(valiCode);
        if(user==null||(user.getStatus().equals(User.Status.CREATED)==false)) {
            throw new TipException("验证码过期或不存在，如有问题请与管理员联系");
        }

        user.setStatus(User.Status.ENABLED);
        user.setValiCode(null);
        userRepository.save(user);
    }


    /**
     * 登录
     * @param name 邮箱或手机号
     * @param userPass
     * @return
     */
    public User login(String name, String userPass) {
        User user = userRepository.findByEmailOrPhone(name, name);
        if(user!=null&&user.getUserPass().equals(userPass)){
            return user;
        }else{
            throw new TipException("用户名或密码错误");
        }
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * 检查该用户是否可用
     * @param userName
     */
    public void cehckUser(String userName) {
        if(userName==null||userName.equals("")) throw new TipException("拒绝访问");

        User user=findByUserName(userName);

        if(!user.getStatus().equals(User.Status.ENABLED)) throw new TipException("用户："+userName+"不可用，请与管理员联系");
    }
}
