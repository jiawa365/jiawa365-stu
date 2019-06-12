package com.silence.test;

import java.util.Map;

public class Helloworld {
    public static void main(String[] args) {
        System.out.println("helloworld");
    }

    private void go(Map<String,String> params){
        System.out.println(params);
        System.out.println("go go go!!!");
    }
}
