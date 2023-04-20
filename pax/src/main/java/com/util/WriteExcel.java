package com.util;

import com.bean.Record;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author wjm
 * @date 2023/4/6 18:55
 * @Descript
 */
public class WriteExcel {
    /**
     * 以追加形式去写入文件
     * @param file 文件路径
     * @param records 输入数据集
     * @param versions 版本（筛选条件）
     * @param status 状态（筛选条件）
     * @throws Exception
     */
    public static void writeExcel(String file, List<Record> records,String versions,List<String> status) throws Exception {
        System.out.println("writeExcel");
        if (!fileExist(file)) {
//            System.out.println("输出文件不存在："+file);
            File file1 = new File(file);
            if (file1.createNewFile()){
//                System.out.println("创建输出文件成功");
            }

        }
//        System.out.println("=========文件已存在=======");
        // 创建Excel的工作sheet,对应到一个excel文档的tab
        FileInputStream is = new FileInputStream(file);
        POIFSFileSystem ps=new POIFSFileSystem(is);  //使用POI提供的方法得到excel的信息
        HSSFWorkbook wb=new HSSFWorkbook(ps);
        HSSFSheet sheet=wb.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();//总行数
//        System.out.println("输出文件当前总行数："+lastRowNum);

        FileOutputStream os = new FileOutputStream(file);
        for (int i = 0;i < records.size();i++,lastRowNum++) {
            Record record = records.get(i);
            if (status.contains(record.getStatus()) && versions.equals(record.getVersion())){
                HSSFRow row = sheet.createRow((short) (lastRowNum + 1));//在现有行号后追加数据
                row.createCell(0).setCellValue(String.valueOf(i));
                row.createCell(1).setCellValue(record.getReceiver());
                row.createCell(2).setCellValue(record.getVersion());
                row.createCell(3).setCellValue(record.getStatus());
                row.createCell(4).setCellValue(record.getModifyTime());
                row.createCell(5).setCellValue(record.getType());
                row.createCell(6).setCellValue(record.getCodeNum());
                row.createCell(7).setCellValue(record.getOther());
                row.createCell(8).setCellValue(record.getSummary());
                row.createCell(9).setCellValue(record.getTestPlan());
                os.flush();
            }
        }
            wb.write(os);
            os.close();
    }


    /**
     * 文件是否存在
     * @param file 文件路径
     * @return
     */
    public static boolean fileExist(String file){
//        System.out.println("=====fileExist=========");
        boolean flag = false;
        File file1 = new File(file);
        boolean exists = file1.exists();
        return exists;
    }

    /**
     * 当前文件有多少行
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static int getRow(String file) throws Exception {
        Workbook workbook = null;
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            workbook = Workbook.getWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //获取第一张sheet表
        Sheet[] sheets = workbook.getSheets();
        int rows= workbook.getSheet(0).getRows();
//        System.out.println("当前文件共有"+rows+"行");
        return rows;
    }
}
