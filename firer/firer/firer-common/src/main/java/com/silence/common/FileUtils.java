package com.silence.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by silence on 2018/2/26.
 */
public class FileUtils extends org.apache.commons.io.FileUtils{


    /**
     * 文件下载
     * @param url
     * @param saveDir
     * @param fileName
     */
    public static void downloadByNIO2(String url, String saveDir, String fileName) {
        try (InputStream ins = new URL(url).openStream()) {
            Path target = Paths.get(saveDir, fileName);
            Files.createDirectories(target.getParent());
            Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
