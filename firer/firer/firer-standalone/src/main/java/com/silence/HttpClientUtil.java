package com.silence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/* 
 * 利用HttpClient进行post请求的工具类 
 */  
public class HttpClientUtil {  


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


}  