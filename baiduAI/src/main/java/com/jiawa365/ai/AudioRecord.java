package com.jiawa365.ai;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * <h1><a href="http://www.jiawa365.com">嘉哇科技</a><br>讲师：谭兴义</h1>
 */
public class AudioRecord {
    static ByteArrayOutputStream byteArrayOutputStream;
    static boolean stopCapture;
    /**
     * 开始录音
     */
    public static void record(){

        if(stopCapture){
            throw new RuntimeException("录音中，请先停止");
        }

        new Thread(new Runnable() {
            public void run() {
                AudioFormat audioFormat =null;
                DataLine.Info dataLineInfo=null;
                TargetDataLine targetDataLine=null;
                try {
                    audioFormat = new AudioFormat(16000.0F, 16, 1, true, false);
                    dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
                    targetDataLine  = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                    targetDataLine.open(audioFormat);
                    targetDataLine.start();


                    byteArrayOutputStream  = new ByteArrayOutputStream();
                    int totaldatasize = 0;
                    // 临时数组
                    byte tempBuffer[] = new byte[10000];

                    // 循环执行，直到按下停止录音按钮
                    while (!stopCapture) {
                        // 读取10000个数据
                        int cnt = targetDataLine.read(tempBuffer, 0,
                                targetDataLine.available());
                        if (cnt > 0) {
                            // 保存该数据
                            byteArrayOutputStream.write(tempBuffer, 0, cnt);
                            totaldatasize += cnt;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e) {

                    }
                    targetDataLine.stop();
                    targetDataLine.close();
                    stopCapture=false;
                }
            }
        }).start();
    }


    /**
     * 停止录音
     * @return  音频内容
     */
    public static byte[] stop(){
        stopCapture=true;
       return  byteArrayOutputStream.toByteArray();
    }


}
