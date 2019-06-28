package com.jiawa365.weather;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * @author 嘉哇科技
 * @version 0.1
 *          2019年5月8日 上午9:51:14
 */
public class App {

    public static void main(String[] args) throws Exception {

        WeatherInfo[] weatherInfos = getWeather("101020100");

        //播放

        playWeather(weatherInfos);

    }

    public static WeatherInfo[] getWeather(String cityCode) throws Exception {

        WeatherInfo[] weatherInfos = new WeatherInfo[7];
        Elements elements = Jsoup.connect("http://www.weather.com.cn/weather/" + cityCode + ".shtml").get().select(".sky");
        for (int i = 0; i < elements.size(); i++) {
            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setWeather(elements.get(i).select(".wea").text());
            weatherInfo.setTmp(elements.get(i).select(".tem").text().replace("/","到"));
            weatherInfo.setDate(elements.get(i).select("h1").text());
            weatherInfos[i] = weatherInfo;
        }
        return weatherInfos;
    }

    private static void playWeather(WeatherInfo[] weatherInfos) {
        Runtime runtime = Runtime.getRuntime();
        try {
            //音量最大
            runtime.exec("nircmd.exe mutesysvolume 0 ");
            runtime.exec("nircmd.exe setsysvolume 65535");
            Thread.sleep(1000);
            runtime.exec("nircmd speak text 今天天气" + weatherInfos[0].getWeather());
            Thread.sleep(2000);
            runtime.exec("nircmd speak text 气温" + weatherInfos[0].getTmp());
            Thread.sleep(2000);
            runtime.exec("nircmd speak text 明天天气" + weatherInfos[1].getWeather());
            Thread.sleep(2000);
            runtime.exec("nircmd speak text 气温" + weatherInfos[1].getTmp());
            Thread.sleep(2000);
            runtime.exec("nircmd speak text 后天天气" + weatherInfos[2].getWeather());
            Thread.sleep(2000);
            runtime.exec("nircmd speak text 气温" + weatherInfos[2].getTmp());
            Thread.sleep(12000);
            //静音
            runtime.exec("nircmd.exe mutesysvolume 1 ");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 显示天气信息
     *
     * @param weatherInfos
     */
    private static void showWether(WeatherInfo[] weatherInfos) {

        //显示标题
        System.out.println("日期\t\t天气\t气温\t风向\t风速");

        for (int i = 0; i < weatherInfos.length; i++) {

            System.out.print(weatherInfos[i].getDate() + "\t");
            System.out.print(weatherInfos[i].getWeather() + "\t");
            System.out.print(weatherInfos[i].getTmp() + "\t");
            System.out.print(weatherInfos[i].getFengxiang() + "\t");
            System.out.print(weatherInfos[i].getSpeed() + "\t\n");
        }
    }

}
