package com.silence;

import org.openqa.selenium.By;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            String jsFile = null;
            if (args.length < 1) jsFile = "firer.js";
            else jsFile = args[0];

            if (jsFile.equals("help")) {
                help();
                return;
            }
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            engine.eval(new InputStreamReader(App.class.getResourceAsStream("/com/silence/init.js")));
            engine.eval(new FileReader(jsFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示帮助信息
     */
    private static void help() {
        new FChromeDriver().findElement(By.id(""));
        System.out.println("$('#btn1').click()");
        System.out.println("$($('.btn')[0]).click();");
        System.out.println("$($('div')[0]).click()')");
        System.out.println("$.get('http://www.baidu.com')')");
        System.out.println("$.post('http://www.baidu.com','name=jane&age=18')')");
        System.out.println("多选项卡切换：$.goWin($.getWindows()[1]).close()");
        System.out.println("拖动组件：$('#verify_xbox').drag(800,56)");
    }
}
