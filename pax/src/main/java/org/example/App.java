package org.example;

import com.bean.Record;
import com.util.Config;
import com.util.ReadExcel;
import com.util.WriteExcel;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        System.out.println("======main======");
        System.out.println(Config.getInput());
        System.out.println(Config.getOutPut());
        System.out.println(Config.getVersion());
        System.out.println(Config.getStatus());
        System.out.println("========读文件=====");
        List<Record> records = ReadExcel.readExcel(Config.getInput());
        System.out.println("========写文件=====");
//        WriteExcel.writeExcel(Config.getOutPut(),records);
    }
}
