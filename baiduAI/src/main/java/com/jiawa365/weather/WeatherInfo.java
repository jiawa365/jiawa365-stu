package com.jiawa365.weather;

import java.io.UnsupportedEncodingException;


public class WeatherInfo {
	private String date;
	private String weather;
	private String tmp;
	private String fengxiang;
	private String speed;
	public String getDate() {
		return date.length()==7?date:" "+date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getTmp() {
		return tmp;
	}
	public void setTmp(String tmp) {
		this.tmp = tmp;
	}
	public String getFengxiang() {
		return fengxiang;
	}
	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	@Override
	public String toString() {
		return "WeatherInfo [date=" + date + ", weather=" + weather + ", tmp=" + tmp + ", fengxiang=" + fengxiang
				+ ", speed=" + speed + "]";
	}
	
	
	
	
}
