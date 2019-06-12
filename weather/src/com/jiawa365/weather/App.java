package com.jiawa365.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 *      
 * @author 嘉哇科技
 * @version 0.1
 * 2019年5月8日 上午9:51:14	
 *
 */
public class App {

	public static void main(String[] args) throws Exception {
		
		//1.创建url对象
		URL url=new URL("http://www.weather.com.cn/weather/"+args[0]+".shtml");
		//2.读取服务器端响应的数据
		InputStream is = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String content=null;
		boolean isWeather=false;
		int i=0;
		WeatherInfo [] weatherInfos=new WeatherInfo[7];
		WeatherInfo wea=null;
		while((content=reader.readLine())!=null) {
			System.out.println(content);if(true ) continue;
			
			
			
			
			if(content.startsWith("<li class=\"sky skyid")) {
				//天气信息
				isWeather=true;
				continue;
			}
			
			if(isWeather) {
				if(content.startsWith("<h1>")) {
					//日期
					content=content.replace("<h1>", "").replace("</h1>", "");
					i++;
					wea=new WeatherInfo();
					wea.setDate(content);
				}
				
				if(content.startsWith("<p title=\"")) {
					//天气
					content=content.substring(10);
					content=content.substring(0, content.indexOf("\""));
					wea.setWeather(content);
				}
				
				if(content.startsWith("<span>")) {
					//气温
					String max=content.substring(6,content.indexOf("</span>"));
					String min=content.substring(content.indexOf("<i>")+3,content.indexOf("</i>"));
					wea.setTmp(min+"-"+max);
					
				}
				
				if(content.startsWith("<span title=\"")) {
					//风向
					content=content.substring(13);
					content=content.substring(0,content.indexOf("\""));
					wea.setFengxiang(content);
				}
				
				if(content.startsWith("<i>")) {
					//风速
					content=content.replace("<i>", "").replace("</i>", "");
					wea.setSpeed(content);
					
					weatherInfos[i-1]=wea;
					
					if(i==7) {
						break;
					}
				}
			}
		}
		
		
		showWether(weatherInfos);
		
		//播放今明两天天气情况
		playWeather(weatherInfos);
		
		reader.close();
		
	}

	private static void playWeather(WeatherInfo[] weatherInfos) {
		Runtime runtime = Runtime.getRuntime();
		try {
			//音量最大
			runtime.exec("nircmd.exe mutesysvolume 0 ");
			runtime.exec("nircmd.exe setsysvolume 65535");
			Thread.sleep(1000);
			runtime.exec("nircmd speak text 今天天气"+weatherInfos[0].getWeather());
			Thread.sleep(2000);
			runtime.exec("nircmd speak text 气温"+weatherInfos[0].getTmp());
			Thread.sleep(2000);
			runtime.exec("nircmd speak text 明天天气"+weatherInfos[1].getWeather());
			Thread.sleep(2000);
			runtime.exec("nircmd speak text 气温"+weatherInfos[1].getTmp());
			Thread.sleep(8000);
			//静音
			runtime.exec("nircmd.exe mutesysvolume 1 ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 显示天气信息
	 * @param weatherInfos
	 */
	private static void showWether(WeatherInfo[] weatherInfos) {
	
		//显示标题
		System.out.println("日期\t\t天气\t气温\t风向\t风速");
		
		for (int i = 0; i < weatherInfos.length; i++) {
			
			System.out.print(weatherInfos[i].getDate()+"\t");
			System.out.print(weatherInfos[i].getWeather()+"\t");
			System.out.print(weatherInfos[i].getTmp()+"\t");
			System.out.print(weatherInfos[i].getFengxiang()+"\t");
			System.out.print(weatherInfos[i].getSpeed()+"\t\n");
		}
	}

}
