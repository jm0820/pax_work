package com.util;

/**
 * @author wjm
 * @date 2023/4/10 17:28
 * @Descript
 * 工具类
 */
public class Util {
    /**
     * 判断文件后缀
     * @param str
     * @return
     */
    public static boolean isXlsx(String str){
        String[] split = str.split("\\.");
        return split[split.length-1].equals("xlsx");
    }

    /**
     * 判断文件后缀
     * @param str
     * @return
     */
    public static boolean isXls(String str){
        String[] split = str.split("\\.");
        return split[split.length-1].equals("xls");
    }
}
