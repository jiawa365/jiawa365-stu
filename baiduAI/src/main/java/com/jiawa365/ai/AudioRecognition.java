package com.jiawa365.ai;

import com.baidu.aip.speech.AipSpeech;
import com.jiawa365.chrome.FChromeDriver;
import com.jiawa365.chrome.OneChrome;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.HashMap;

/**
 * 语音识别
 * <h1><a href="http://www.jiawa365.com">嘉哇科技</a><br>讲师：谭兴义</h1>
 */
public class AudioRecognition implements HotkeyListener{

    //设置APPID/AK/SK
    public static final String APP_ID = "16652409";
    public static final String API_KEY = "ChqTesPt7j2EGk5ULsfjg0Gb";
    public static final String SECRET_KEY = "wmmf710EE4VjhvY22dp52bE6f64Y3UGI";


    static int errCount;
    /**
     * 识别语音
     */
    public static String recognition( ){

        try {
            //获取录音
            AudioRecord.record();
            System.out.println("请说：");
            Thread.sleep(3000);
            byte[] data = AudioRecord.stop();

            AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);
            HashMap<String,Object> options=new HashMap<String,Object>();
            /**
             * 设置语言类别
             * 1536	普通话(支持简单的英文识别)
             * 1537	普通话(纯中文识别)
             * 1737	英语
             * 1637	粤语
             * 1837	四川话
             * 1936	普通话远场
             */
            options.put("dev_pid",1837);
            JSONObject res = null;

            for (int i = 0; i < 3; i++) {
                try {
                    res = client.asr(data, "pcm", 16000, options);
                    return res.get("result")+"";
                } catch (Exception e) {
                    continue;
                }
            }
            System.err.print("网络异常，请重试");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void main(String[] args) throws Exception {
        JIntellitype.setLibraryLocation("D:\\tmp\\jiawa365-stu\\baiduAI\\src\\main\\java\\com\\melloware\\jintellitype\\JIntellitype64.dll");
        AudioRecognition audioRecognition=new AudioRecognition();
        JIntellitype.getInstance().registerHotKey(0, JIntellitype.MOD_CONTROL,0);
        JIntellitype.getInstance().addHotKeyListener(audioRecognition);



     /*   //识别语音
        String text = recognition();
        System.out.println(text);
        if(text.indexOf("记事本")>-1){
            //打开记事本
            Runtime.getRuntime().exec("notepad");
        }else  if(text.indexOf("画图")>-1){
            //打开画图工具
            Runtime.getRuntime().exec("mspaint");
        }
*/
    }

    public void onHotKey(int i) {
        Speaker.speak("你好？");
        String text = recognition();
        if(text.indexOf("电影")>-1){
            OneChrome.go("http://list.iqiyi.com/www/1/----------0---11-1-1-iqiyi--.html");
        }else if(text.indexOf("电视")>-1){
            OneChrome.go("http://list.iqiyi.com/www/2/----------0---11-1-1-iqiyi--.html");
        }

    }
}
