package com.silence.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;  

/* 
 * 利用HttpClient进行post请求的工具类 
 */  
public class HttpClientUtil {  
    public static String doPost(String url,Map<String,String> map,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = HttpClientBuilder.create().build(); 
            httpPost = new HttpPost(url);  
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            if(map!=null){
                Iterator iterator = map.entrySet().iterator();
                while(iterator.hasNext()){
                    Entry<String,String> elem = (Entry<String, String>) iterator.next();
                    list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
                }
            }
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }

    /**
     * 发送post请求
     * @param serverUrl
     * @param data
     * @return
     */
    public static String doPost(String serverUrl, String data) {
        StringBuilder responseBuilder = null;
        BufferedReader reader = null;
        OutputStreamWriter wr = null;

        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            responseBuilder = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line).append("\n");
            }
        } catch (IOException var22) {
        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException var21) {
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException var20) {
                }
            }

        }
        return responseBuilder.toString();
    }


    public static String doGet(String serverUrl) {
        StringBuilder responseBuilder = null;
        BufferedReader reader = null;
        OutputStreamWriter wr = null;

        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write("");
            wr.flush();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            responseBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
        } catch (IOException var22) {
        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException var21) {
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException var20) {
                }
            }

        }
        return responseBuilder.toString();
    }

    public static void main(String[] args) {

        Map<String,String> param=new HashMap<>();
        param.put("tenant_name","silence");
        System.out.println(doPost("https://api.daocloud.io/users/check-tenant-name",param,"utf-8"));;
    }
}  