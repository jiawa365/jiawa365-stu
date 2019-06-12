package com.silence.firer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;
import java.util.Map.Entry;

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

    public static void main(String[] args) {

        Map<String,String> param=new HashMap<String,String>();
        param.put("tenant_name","silence");
        System.out.println(doPost("https://api.daocloud.io/users/check-tenant-name",param,"utf-8"));;
    }
}  