import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

/**
 * Created by silence-pc on 2019/6/26.
 */
public class TestBaidiAI {

    //设置APPID/AK/SK
    public static final String APP_ID = "16652409";
    public static final String API_KEY = "ChqTesPt7j2EGk5ULsfjg0Gb";
    public static final String SECRET_KEY = "wmmf710EE4VjhvY22dp52bE6f64Y3UGI";

    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);



        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("aip.log4j.conf", "D:\\repo\\test\\src\\test\\java\\log4j.properties");

        //使用完美录音机录制16位8000采样率
        // 调用接口
        JSONObject res = client.asr("C:\\Users\\silence-pc\\Desktop\\1.wav", "wav", 8000, null);
        System.out.println(res.toString(2));

    }
}
