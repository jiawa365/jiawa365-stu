package com.silence.util;

import com.silence.exception.TipException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silence on 2018/1/8.
 */
public class MyClassLoader extends ClassLoader{
    private String name; // 类加载器的名字
    private String path;
    private final String fileType = ".class"; // .class文件扩展名

    public MyClassLoader(String path, String name) {
        super();// 让系统加载器成为该类的加载器的父类加载器
        this.name = name;
        this.path=path;
    }

    public MyClassLoader(ClassLoader parent, String name) {
        super(parent); // 显示指定该类加载器的父加载器
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * 读取class文件作为二进制流放入到byte数组中去
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        InputStream in = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            name = name.replace(".", "\\");
            in = new BufferedInputStream(new FileInputStream(new File(path+"\\"+ name + fileType)));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = in.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Throwable e) {
            throw new TipException(e.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    /**
     * JVM调用的加载器的方法
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            byte[] data = this.loadClassData(name);
            return this.defineClass(name, data, 0, data.length);
        } catch (Throwable e) {
           return Class.forName(name);
        }
    }

    public List<String> getClasssNames(String packagePath){
        File[] files = new File(path+packagePath).listFiles();
        List<String> names=new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            names.add( files[i].getName().replace(".class",""));
        }
        return names;
    }
}
