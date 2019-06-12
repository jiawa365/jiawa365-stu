package com.silence.module;

import com.silence.firer.Chromes;
import com.silence.firer.HttpClientUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Host {

    private static final String newUrl = "https://dashboard.daocloud.io/nodes/new";//创建新主机url

    public static void main(String[] args) {

    }

    private static void createNewHost(String path, String userData) {
        WebDriver wb=null;
        try {
            wb = Chromes.open(path,userData);
            wb.get(newUrl);
            Chromes.sleep(1000);
            Chromes.findElement("span","免费试用胶囊主机",wb).click();
            Chromes.sleep(1000);
            Chromes.findElement("span","一键接入",wb).click();
            Chromes.sleep(6*1000);
            wb.get("https://dashboard.daocloud.io/cluster");//集群管理
            Chromes.sleep(6*1000);
            wb.findElements(By.className("btn-group-justified")).get(0).findElements(By.tagName("a")).get(0).click();//点击管理主机
            Chromes.sleep(6*1000);
            List<WebElement> hosts = wb.findElements(By.className("item-link"));
            for (WebElement host: hosts ) {
                if(host.getText().indexOf("Try_DaoCloud_")>-1){
                    host.findElements(By.tagName("a")).get(0).click();
                    break;
                }
            }
            Chromes.sleep(3000);
            String hostIp = getHostIp(wb);
            String password = getHostPassword(wb);


            pushHost(hostIp, password);

            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建失败，重试。。。。");
            createNewHost(path,userData);
        }
    }

    /**
     * 推送主机信息到服务器
     * @param hostIp
     * @param password
     */
    private static void pushHost(String hostIp, String password) {
        String postUrl="http://laotan.site/host.php";
        Map<String,String> params=new HashMap<String, String>();
        params.put("action","setNextHost");
        params.put("nextHost",hostIp);
        HttpClientUtil.doPost(postUrl,params,"utf-8");

        params.put("action","setNextPassword");
        params.put("nextPassword",password);
        HttpClientUtil.doPost(postUrl,params,"utf-8");
        System.out.println(hostIp);
        System.out.println(password);
    }


    /**
     * 获取主机ip
     *
     * @param wb
     */
    private static String getHostIp(WebDriver wb) {
        String ip = null;
        List<WebElement> elementLists = wb.findElements(By.tagName("a"));
        for (WebElement element : elementLists) {
            if (element.getText().indexOf("ssh ubuntu") > -1) {
                ip = element.getText().substring("ssh ubuntu@".length());
            }
        }
        return ip;
    }

    /**
     * 获取主机密码
     *
     * @param wb
     */
    private static String getHostPassword(WebDriver wb) {
        String password = null;
        List<WebElement> elementLists = wb.findElements(By.tagName("span"));
        for (WebElement element : elementLists) {
            if (element.getText().length() == 16) {
                password = element.getText();
            }
        }
        return password;
    }



}
