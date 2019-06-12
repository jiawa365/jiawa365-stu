package com.silence.module;

import com.silence.firer.Chromes;
import com.silence.firer.FChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

/**
 * Created by silence on 2018/3/30.
 */
public class Taobao {

    public static void login(Map<String,String> params){
        System.out.println(System.getProperty("webdriver.chrome.bin"));
        System.setProperty("webdriver.chrome.driver", "D:\\repo\\firer-driver\\chromedriver.exe");
        ChromeDriver wb = new ChromeDriver();
        System.out.println("淘宝登录模块：wb:"+wb);
    }

    public void say(Map<String,String> params){
        System.out.println("Taobao.say:测试+++++++++++");
    }


    public static void main(String[] args) {
    }

}
