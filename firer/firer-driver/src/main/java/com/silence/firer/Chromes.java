//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.silence.firer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chromes {
    public static Map<String,Object> loadedDataChrome=new HashedMap();

    public Chromes() {
    }

    public static FChromeDriver open() {
        return open(null,null);
    }

    /**
     *
     * @param path chrome.exe的路径
     * @param userData userData路径
     * @return
     */
    public static synchronized FChromeDriver open(String path,String userData) {
        ChromeOptions chromeOptions = new ChromeOptions();
        FChromeDriver fChromeDriver=null;

        if(path!=null){
            System.setProperty("webdriver.chrome.bin", path);
        }else{
            System.clearProperty("webdriver.chrome.bin");
        }

        if(userData!=null){
            Object o = loadedDataChrome.get(userData);
            //同一配置项的chrome只能同时打开一个
            if(o!=null){
                try {
                    ((FChromeDriver)o).close();
                } catch (Exception e) {
                }
            }

            chromeOptions.addArguments("--user-data-dir="+userData);
            fChromeDriver = new FChromeDriver(chromeOptions);
            loadedDataChrome.put(userData,fChromeDriver);

        }else{
            fChromeDriver=new FChromeDriver();
        }
        return fChromeDriver;
    }

    /**
     *
     * @param userData
     * @return
     */
    public static synchronized FChromeDriver open(String userData) {
        ChromeOptions chromeOptions = new ChromeOptions();
        FChromeDriver fChromeDriver=null;

        if(userData!=null){
            Object o = loadedDataChrome.get(userData);
            //同一配置项的chrome只能同时打开一个
            if(o!=null){
                try {
                    ((FChromeDriver)o).close();
                } catch (Exception e) {
                }
            }

            chromeOptions.addArguments("--user-data-dir="+userData);
            fChromeDriver = new FChromeDriver(chromeOptions);
            loadedDataChrome.put(userData,fChromeDriver);

        }else{
            fChromeDriver=new FChromeDriver();
        }
        return fChromeDriver;
    }

    public static WebElement findElement(String tagName, String text, WebDriver wb) {
        List elementLists = null;
        elementLists = wb.findElements(By.tagName(tagName));
        Iterator var4 = elementLists.iterator();

        WebElement element;
        do {
            if(!var4.hasNext()) {
                return null;
            }

            element = (WebElement)var4.next();
        } while(!element.isDisplayed() || element.getText().indexOf(text) <= -1);

        return element;
    }

    public static void execScript(WebDriver wb, String script) {
        ((JavascriptExecutor)wb).executeScript(script, new Object[0]);
    }

    static {
        System.setProperty("webdriver.chrome.driver", PropertiesUtil.get("driver.path", "chromedriver.exe"));
    }

    public static void main(String[] args) {
/*//        System.setProperty("webdriver.chrome.bin", "D:\\temp\\chromes\\Chrome\\Application\\chrome.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--user-data-dir=C:\\Users\\silence\\AppData\\Local\\Google\\Chrome\\User Data");
//        chromeOptions.addArguments("--user-data-dir=D:\\temp\\chromes\\Chrome\\UserData");
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.get("https://dashboard.daocloud.io/build-flows");

        System.out.println(System.getProperty("x"));*/

        open("D:\\temp\\chromes\\Chrome\\Application\\chrome.exe","D:\\temp\\chromes\\Chrome\\9\\UserData").get("https://dashboard.daocloud.io/build-flows");

    }

    public static void sleep(int i) {
        try {
            Thread.sleep(Integer.valueOf(i+""));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
