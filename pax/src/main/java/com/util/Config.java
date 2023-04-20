package com.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Config {
    private static String path ="./src/main/resources/config.properties";
    private static String path2 = "config.properties";
    static InputStreamReader inputStreamReader;


    static {
        try {
            File directly = new File(path2);
            System.out.println("配置文件绝对路径："+directly.getAbsolutePath());
            System.out.println("判断文件是否存在");
            if(!WriteExcel.fileExist(path2)){
                System.out.println("配置文件不存在，请放config.properties 文件在当前目录下");
                System.exit(0);
            }
            System.out.println("========配置文件存在，请继续==========");
            inputStreamReader = new InputStreamReader(new FileInputStream(path2), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static Properties properties = new Properties();
    public static void main(String[] args) throws IOException {
        /*String path = "";
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path), "utf-8");
        Properties properties = new Properties();
        properties.load(inputStreamReader);*/

    }

    public static String getInput(){
        try {
            properties.load(inputStreamReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty("input");
    }
    public static String getOutPut(){
        try {
            properties.load(inputStreamReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty("output");
    }

    public static List getVersion(){
        try {
            properties.load(inputStreamReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(properties.getProperty("version").trim().split("/"));
    }

    public static List getStatus(){
        try {
            properties.load(inputStreamReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(properties.getProperty("status").trim().split("/"));
    }

}