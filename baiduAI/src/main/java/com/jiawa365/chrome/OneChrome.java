package com.jiawa365.chrome;

import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

/**
 * Created by silence-pc on 2019/6/27.
 */
public class OneChrome {
    static String chromePath;
    static String chromeDataPath;
    static String userName = System.getProperty("user.name");
    static ChromeOptions options = new ChromeOptions();
    static FChromeDriver wb;

    static {
        try {
            chromePath = "C:\\Users\\" + userName + "\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe";
            chromeDataPath = "C:\\Users\\"+userName+"\\AppData\\Local\\Google\\Chrome\\User Data";
            Runtime.getRuntime().exec(chromePath+" --remote-debugging-port=9222 --user-data-dir=\"" + chromeDataPath+"\"");
            options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
            wb = new FChromeDriver(options);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    static int errCount;

    public static void go(String url) {
        try {
            wb.switchTo().window(wb.getWindowHandle());
            wb.get(url);
        } catch (Exception e) {
            e.printStackTrace();
            if (errCount > 3) {
                System.err.println("浏览器打开异常");
                return;
            }
            try {
                //Runtime.getRuntime().exec("taskkill -f -im chrome.exe");
                Runtime.getRuntime().exec(chromePath+" --remote-debugging-port=9222 --user-data-dir=\"" + chromeDataPath+"\"");
                Thread.sleep(3000);
                wb = new FChromeDriver(options);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            go(url);
            errCount++;
        }
    }

    public static void main(String[] args) throws Exception {

        go("http://www.baidu.com");
        Thread.sleep(3000);
        System.out.println("next");
        go("http://www.baidu.com");
    }
}
