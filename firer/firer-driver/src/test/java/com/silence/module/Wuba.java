package com.silence.module;

import com.silence.firer.Chromes;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by silence-pc on 2018/4/1.
 */
public class Wuba {

    public void applay(Map<String,String> params){

        String file=params.get("file");
        String countName=params.get("countName");
        String countPass=params.get("countPass");
        ChromeDriver wb = new ChromeDriver();
        try {
            //登录
            login(wb,countName,countPass);
            Thread.sleep(2000L);

            FileInputStream fileInputStream = new FileInputStream(file);
            HSSFWorkbook e = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = e.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            for (int i = 1; i <= rowNum; ++i) {
                long start = System.currentTimeMillis();
                
                HSSFRow row = sheet.getRow(i);
                String xiaoQuMIngCheng = row.getCell(0).getStringCellValue();
                Integer type = Integer.valueOf(row.getCell(1).getStringCellValue().equals("整租") ? 0 : 1);
                Integer shi = Integer.valueOf((int) row.getCell(2).getNumericCellValue());
                Integer ting = Integer.valueOf((int) row.getCell(3).getNumericCellValue());
                Integer wei = Integer.valueOf((int) row.getCell(4).getNumericCellValue());
                Integer lou = Integer.valueOf((int) row.getCell(5).getNumericCellValue());
                Integer ceng = Integer.valueOf((int) row.getCell(6).getNumericCellValue());
                Integer dianti = Integer.valueOf(row.getCell(7).getStringCellValue().equals("无") ? 0 : 1);
                String chaoxiang = row.getCell(8).getStringCellValue();
                String peizhi = row.getCell(9).getStringCellValue();
                Integer mianji = Integer.valueOf((int) row.getCell(10).getNumericCellValue());
                Integer zujin = Integer.valueOf((int) row.getCell(11).getNumericCellValue());
                String biaoti = row.getCell(12).getStringCellValue();
                Integer hushu = Integer.valueOf((int) row.getCell(13).getNumericCellValue());
                Integer zhuciwo = Integer.valueOf(row.getCell(14).getStringCellValue().equals("次卧") ? 0 : 1);
                String imgUrl = row.getCell(15).getStringCellValue();
                Integer imgCount = Integer.valueOf((int) row.getCell(16).getNumericCellValue());
                String template = row.getCell(17).getStringCellValue();

                //保存房源
                try {

                    wb.get("http://vip.anjuke.com/house/publish/rent/?from=manage");
                    ((JavascriptExecutor)wb).executeScript("$(\'#chooseWebForm>input\').click();", new Object[0]);
                    if(type.equals(Integer.valueOf(0))) {
                        ((JavascriptExecutor)wb).executeScript("$(\'#hz-entire\').click();", new Object[0]);
                    } else {
                        ((JavascriptExecutor)wb).executeScript(" $(\'[name=\"flatshare\"]\').val(" +hushu + ")", new Object[0]);
                        if(zhuciwo.equals(Integer.valueOf(0))) {
                            wb.findElement(By.id("select-bedroom")).click();
                            Chromes.findElement("li", "次卧", wb).click();
                        }

                        wb.findElement(By.id("select-roomorient")).click();
                        Chromes.findElement("li", chaoxiang, wb).click();
                    }

                    WebElement xiaoqu = null;

                    try {
//                    xiaoqu = wb.findElement(By.id("community_unite"));
                        xiaoqu = wb.findElement(By.id("community-input"));
                    } catch (Exception var14) {
                        try {
                            xiaoqu = wb.findElement(By.id("GJ-village-input"));
                        } catch (Exception var13) {
                            try {
                                xiaoqu = wb.findElement(By.id("wuba-village-input"));
                            } catch (Exception ee) {
                                xiaoqu = wb.findElement(By.id("community_unite"));
                            }
                        }
                    }

                    xiaoqu.sendKeys(new CharSequence[]{xiaoQuMIngCheng});
                    Thread.sleep(3000L);
                /*
                 * 临时切换点击方式
                 * Chromes.execScript(wb, "$($(\'.auto-ul > li\')[0]).click()");
                Thread.sleep(1000L);
                
                //TODO: 小区名未确定，临时多点一次测试
                Chromes.execScript(wb, "$($(\'.auto-ul > li\')[0]).click()");*/
                    wb.findElement(By.className("auto-ul")).findElements(By.tagName("li")).get(0).click();

                    Thread.sleep(3000L);

                    WebElement tmpXiaoqu = Chromes.findElement("span", xiaoQuMIngCheng, wb);
                    if(tmpXiaoqu != null) {
                        tmpXiaoqu.click();
                    }

                    if(tmpXiaoqu == null) {
                        tmpXiaoqu = Chromes.findElement("li", xiaoQuMIngCheng, wb);
                        if(tmpXiaoqu != null) {
                            tmpXiaoqu.click();
                        }
                    }

                    ((JavascriptExecutor)wb).executeScript("$(\'[name=\"room\"]\').click()", new Object[0]);
                    ((JavascriptExecutor)wb).executeScript("$(\'[name=\"room\"]\').val(" + shi + ")", new Object[0]);
                    ((JavascriptExecutor)wb).executeScript("$(\'[name=\"hall\"]\').val(" + ting + ")", new Object[0]);
                    ((JavascriptExecutor)wb).executeScript("$(\'[name=\"bathroom\"]\').val(" + wei + ")", new Object[0]);
                    ((JavascriptExecutor)wb).executeScript("$(\'[name=\"floor\"]\').val(" + lou + ")", new Object[0]);
                    ((JavascriptExecutor)wb).executeScript("$(\'[name=\"allFloor\"]\').val(" + ceng + ")", new Object[0]);
                    if(dianti.equals(Integer.valueOf(1))) {
                        ((JavascriptExecutor)wb).executeScript("$(\'.ui-radio\')[2].click();", new Object[0]);
                    } else {
                        ((JavascriptExecutor)wb).executeScript("$(\'.ui-radio\')[3].click();", new Object[0]);
                    }

                    ((JavascriptExecutor)wb).executeScript("scrollTo(0,700)", new Object[0]);
                    wb.findElement(By.id("select-paymode")).click();
                    Thread.sleep(5000L);
                    wb.findElement(By.id("select-housetype")).click();
                    Thread.sleep(1000L);
                    Chromes.findElement("li", "普通住宅", wb).click();
                    wb.findElement(By.id("select-housefit")).click();
                    Thread.sleep(1000L);
                    Chromes.findElement("li", "精装修", wb).click();
                    if(!type.equals(Integer.valueOf(1))) {
                        wb.findElement(By.id("select-exposure")).click();
                        Chromes.findElement("li", chaoxiang, wb).click();
                    }

                    String[] peizhis = peizhi.split(",");
                    String[] j = peizhis;
                    int var10 = peizhis.length;

                    for(int var11 = 0; var11 < var10; ++var11) {
                        String tmp = j[var11];
                        Chromes.findElement("label", tmp, wb).click();
                    }

                    ((JavascriptExecutor)wb).executeScript("$(\'[name=\"roomarea\"]\').val(" + mianji + ")", new Object[0]);
                    ((JavascriptExecutor)wb).executeScript("$(\'[name=\"rentprice\"]\').val(" + zujin + ")", new Object[0]);
                    wb.findElement(By.id("select-paymode")).click();
                    Chromes.findElement("li", "付1押1", wb).click();
                    ((JavascriptExecutor)wb).executeScript("$(\'[name=\"title\"]\').val(\'" + biaoti+ "\')", new Object[0]);
                    Chromes.findElement("a", "使用房源模板", wb).click();
                    Thread.sleep(3000L);
                    Chromes.findElement("label", template, wb).click();
                    Chromes.findElement("a", "确定", wb).click();
                    ((JavascriptExecutor)wb).executeScript("scrollTo(0,1700)", new Object[0]);

                    for(int k = 1; k <= imgCount.intValue(); ++k) {
                        //上传文件
                        selectFile(wb,imgUrl,k);
                    }

                    ((JavascriptExecutor)wb).executeScript("$(\'.set-main\')[0].click()", new Object[0]);
                    Thread.sleep(2000L);
                    ((JavascriptExecutor)wb).executeScript("scrollTo(0,4000)", new Object[0]);
                    ((JavascriptExecutor)wb).executeScript("$(\'#publish_form\')[0].submit()", new Object[0]);

                    long e1 = System.currentTimeMillis();
                    long totalTime = 180000L;
                    long sleep = totalTime - (e1 - start);
                    if(sleep > 0L) {
                        Thread.sleep(sleep);
                    }

                   
                } catch (Throwable throwable) {
                    System.out.println("第" + i + "条任务执行出现异常，错误信息：" + throwable.getMessage());
                }
            }
            fileInputStream.close();
            wb.close();
            
        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }


    private void selectFile(WebDriver wb,String imgUrl , int j) throws InterruptedException {
        WebElement upload = wb.findElement(By.id("room_fileupload"));
        LocalFileDetector detector = new LocalFileDetector();
        String path = imgUrl + "\\" + j + ".jpg";
        File f = detector.getLocalFile(new CharSequence[]{path});
        ((RemoteWebElement)upload).setFileDetector(detector);
        upload.sendKeys(new CharSequence[]{f.getAbsolutePath()});
        if(wb.findElement(By.id("alert")).isDisplayed()) {
            wb.findElement(By.className("ui-dialog-close")).click();
            this.selectFile(wb, imgUrl, j);
        }

        Thread.sleep(30000L);
    }


    public void login(WebDriver wb, String countName, String countPass) {
        try {
            wb.get("http://vip.anjuke.com/logout/");
            wb.get("http://vip.anjuke.com/login/");

            //点击账号密码登录
            Chromes.execScript(wb, "$($('.login-switch')[1]).click()");
            Thread.sleep(1000);
            Chromes.execScript(wb, "$($('.login-switch')[1]).click()");
            Thread.sleep(1000);

            wb.findElement(By.id("loginName")).sendKeys(countName);
            wb.findElement(By.id("loginPwd")).sendKeys(countPass);
            wb.findElement(By.id("loginSubmit")).click();
            Thread.sleep(2000L);
        } catch (InterruptedException var5) {
            var5.printStackTrace();
        }

    }

   

    public static void main(String[] args) {

       /* Map<String,String> params=new HashMap<String, String>();
        params.put("countName","g13311678059");
        params.put("countPass","Qfy123456");
        params.put("file","D:\\tmp\\tmp1\\g13311678059.xls");
        new Wuba().applay(params);*/

    }



   


}
