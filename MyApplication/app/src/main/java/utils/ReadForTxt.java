package utils;


import android.os.Environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Create by wangjiameng
 * on 2023/3/8.
 * 从 txt 读取数据
 */
public class ReadForTxt {

    public static void main(String[] args) {
        String read = null;
//        try {
//            read = readOfWindows("F:\\test\\cmd.txt");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(read);
        System.out.println("ReadForTxt");
        readOfAndroid("");
    }

    /**
     * 读取txt文件内容，适用于android
     * @param dir
     * @return
     */
    public static String readOfAndroid(String dir){
        //获取SD卡的路径
        String path = Environment.getExternalStorageDirectory().getPath();
        System.out.println(path);

        return path;
    }


    /**
     * 读取数据从dir地址中，适用于windows
     * @param dir
     * @return
     */
    public static String readOfWindows(String dir) throws IOException {
        if (dir.endsWith("txt")){
            FileInputStream in = null;
            try {
                in = new FileInputStream(dir);

                byte[] bytes = new byte[50];
                int i = 0;
                int index = 0;
                while((i = in.read()) != -1){
                    bytes[index] = (byte) i;
                    index++;
                }
                String result = new String(bytes);
                System.out.println("读取命令从txt:"+result.trim());
                return result.trim();
            } catch (FileNotFoundException e) {
                in.close();
                throw new RuntimeException(e);
            } catch (IOException e) {
                in.close();
                throw new RuntimeException(e);
            }
        }else {
            System.out.println(dir + "is not txt");
        }
        return "error";
    }

}