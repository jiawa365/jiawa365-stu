package com.silence.common;

import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Created by silence on 2018/2/27.
 * 邮件发送工具类
 */
public class Mails {
    private static JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
    private static Properties properties=new Properties();

    static{
        mailSender.setHost("smtp.qq.com");
        properties.setProperty("mail.smtps.auth","true");
        properties.setProperty("mail.smtp.ssl.enable","true");
        properties.setProperty("mail.transport.protocol","smtps");
    }

    /**
     *  发送纯文本邮件
     * @param userName 发件人
     * @param password 密码
     * @param target 收件人
     * @param subject 主题
     * @param text 邮件内容
     */
    public static void send(String userName,String password,String target,String subject,String text){
        mailSender.setUsername(userName);
        mailSender.setPassword(password);
        mailSender.setJavaMailProperties(properties);

        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(mailSender.getUsername());
        smm.setTo(target);
        smm.setSubject(subject);
        smm.setText(text);
        // 发送邮件
        mailSender.send(smm);
    }

    /**
     * 发送带html的邮件
     * @param userName 发件人
     * @param password 密码
     * @param target 收件人
     * @param subject 主题
     * @param html 邮件内容
     */
    public static void sendHtml(String userName,String password,String target,String subject,String html){
        try {
            mailSender.setUsername(userName);
            mailSender.setPassword(password);
            mailSender.setJavaMailProperties(properties);

            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true);

            messageHelper.setTo(target);
            messageHelper.setSubject(subject);
            messageHelper.setFrom(mailSender.getUsername());

            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart bodyPart = new MimeBodyPart();
            // 设置HTML内容
            bodyPart.setContent(html, "text/html; charset=utf-8");
            mainPart.addBodyPart(bodyPart);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            mailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String userName="921595360@qq.com";
        String password="pcqkmwnjiwuvbajj";
        sendHtml(userName,password,"tanxingyi@chinasofti.com","测试1","<meta charset='UTF-8'><font color='red'>大家好</font><a href='http://www.baidu.com'>走吧</a>");

    }

    public static void main1(String[] args) {
        String userName="921595360@qq.com";
        String password="pcqkmwnjiwuvbajj";
        send(userName,password,"tanxingyi@chinasofti.com","测试1","<meta charset='UTF-8'><font color='red'>大家好</font><a href='http://www.baidu.com'>走吧</a>");

    }



}
