package com.jiawa365.ai;

import java.io.IOException;

/**
 * Created by silence-pc on 2019/6/28.
 */
public class Speaker {
    public static void speak(String msg){
        try {
            Runtime.getRuntime().exec("nircmd speak text "+msg);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
