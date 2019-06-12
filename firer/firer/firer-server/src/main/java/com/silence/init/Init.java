package com.silence.init;


import com.silence.common.HttpClientUtil;
import com.silence.service.ConfService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;


public class Init implements ApplicationListener<ContextRefreshedEvent> {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private ConfService confService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        jdbcTemplate=event.getApplicationContext().getBean(JdbcTemplate.class);
        confService=event.getApplicationContext().getBean(ConfService.class);

        //初始化配置项
        confService.init();


        //检测服务器地址
        new Thread(() -> {
            while (true) {

                try {
                    String server = getServerUrl();
                    confService.set("server", server + "/");
                    System.out.println(server);
                    Thread.sleep(60 * 60 * 1000);
                } catch (Exception e) {
                    System.out.println("服务器地址检测异常，异常信息："+e.getMessage());
                }
            }
        }).start();
    }

    /**
     * 获取最新url
     */
    private String getServerUrl() {
        String begin = "PublicUrl\\\":\\\"";
        String str = HttpClientUtil.doPost("http://127.0.0.1:4040", "");
        str = str.substring(str.lastIndexOf(begin) + begin.length());
        str = str.substring(0, str.indexOf("\\"));
        //将服务器地址同步
        HttpClientUtil.doPost("http://laotan.site/host.php", "action=setCurrentUrl&currentUrl=" + str);
        return str;
//        System.out.println(post("http://laotan.site/host.php?action=getCurrentUrl",""));
    }




}
