package com.jiawa365;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class App {

	public static void main(String[] args) throws Exception {
		while(true) {
			try {
				URL url = new URL("http://localhost:4040");
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
				String content=null;
				while((content=reader.readLine())!=null) {
					int index = content.indexOf("http://");
					if(index>-1) {
						content=content.substring(index);
						content=content.substring(0,content.indexOf("\\"));
						new URL("http://17723507462.cn3v.net/test.asp?key=msg&value="+content).openStream();
						break;
					}
				}
				
				
			} catch (Exception e) {
				System.out.println("ªÒ»°µÿ÷∑ ß∞‹£∫"+e.getMessage());
			} 
			
			Thread.sleep(1000*10);
		}
	}

}
