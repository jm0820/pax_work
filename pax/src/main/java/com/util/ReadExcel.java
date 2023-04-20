package com.util;
import com.bean.Record;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType._NONE;
import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.ERROR;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;


/**
 * @author wjm
 * @date 2023/4/6 17:55
 * @Descript
 */
public class ReadExcel {


//    static List status = Config.getStatus();
//    static List versions = Config.getVersion();


    public static void main(String[] args) throws Exception {
        String fileName = "C:\\Users\\wangjiameng\\Desktop\\输入文档.xlsx";
        List<Record> records = readExcel(fileName);
        System.out.println("=============");
    }

    /**
     * 读取excel文件
     *
     * @throws Exception
     */
    public static List<Record> readExcel(String fileName) throws Exception {
        InputStream inputStream = new FileInputStream(fileName);
        if (Util.isXlsx(fileName)){
            return poiExcel(inputStream);
        }else if (Util.isXls(fileName)){
//            return jlxExcel(inputStream);
        }
        return null;
    }

    /**
     * 读取 xls 文件
     */
   /* public static List<Record> jlxExcel(InputStream file){
        System.out.println("============jxl=============");
        //构造workbook  对象
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(file);

        //获取第一张sheet表
        Sheet[] sheets = workbook.getSheets();
        for (int i = 0;i < sheets.length;i++){
            //遍历每一个sheet表
            Sheet sheet = workbook.getSheet(i);
            int rows = sheet.getRows();
            int cols = sheet.getColumns();
            //读取行
            for(int j = 1;i < rows; i++){
//                log.println("========读取第"+j+"行信息");
                //读取列
                for (int k = 0;k < cols;k++){
                    Cell cell = sheet.getCell(k, j);
                    String contents = cell.getContents();
                    System.out.println(contents);
                }
            }
        }
        workbook.close();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }*/

    /**
     * 读取 xlsx 文件
     */
    public static List<Record> poiExcel(InputStream file) {
        System.out.println("============poi=============");
        ArrayList<Record> records = new ArrayList<Record>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                //读取一个sheet
                XSSFSheet sheet = workbook.getSheetAt(i);
                int rowNum = sheet.getLastRowNum();
                for (int j = 1; j < rowNum; j++) {
                    //每一行一个对象
                    Record record = new Record();
                    XSSFRow row = sheet.getRow(j);
                    if (row != null) {
                        int cols = row.getLastCellNum();
                        //遍历列
                        for (int k = 0; k < cols; k++) {
                            XSSFCell cell = row.getCell(k);
                            if (cell == null) {
//                                System.out.println("当前列为空");
                            } else {
                                CellType cellTypeEnum = cell.getCellTypeEnum();

                                //判断单元格数据类型
                                String value = null;
                                switch (cell.getCellTypeEnum()) {
                                    case STRING:
                                        value = cell.getStringCellValue();
                                        break;
                                    case _NONE:
                                        break;
                                    case NUMERIC:
                                        value = String.valueOf(cell.getNumericCellValue());
                                        break;
                                    case BLANK:
                                        break;
                                    case BOOLEAN:
                                        value = String.valueOf(cell.getBooleanCellValue());
                                        break;
                                    case ERROR:
                                        break;
                                    default:
                                        value = cell.getStringCellValue();
                                        break;
                                }
                                generateRecord(record, k, value);
                            }
                        }
                        records.add(record);
                    }
                }
            }
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    /**
     * 生成一个Record 对象
     *
     * @param record 对象
     * @param k      列
     * @param value  对应的值
     */
    public static void generateRecord(Record record, int k, String value) {
        switch (k) {
            case 0:
                record.setReceiver(value);
                break;
            case 1:
                record.setVersion(value);
                break;
            case 2:
                record.setStatus(value);
                break;
            case 3:
                record.setModifyTime(value);
                break;
            case 4:
                record.setType(value);
                break;
            case 5:
                record.setCodeNum(value);
                break;
            case 6:
                record.setOther(value);
                break;
            case 7:
                record.setModifyTitle(value);
                break;
            case 8:
                record.setSummary(value);
                break;
            case 9:
                record.setTestPlan(value);
                break;
        }
    }


    /**
     * 获取状态
     * @param path
     * @return
     */
    public static String[] getStatus(String path) {
        ArrayList<String> status = new ArrayList<String>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                //读取一个sheet
                XSSFSheet sheet = workbook.getSheetAt(i);
                int rowNum = sheet.getLastRowNum();
                for (int j = 1; j < rowNum; j++) {
                    XSSFRow row = sheet.getRow(j);
                    //列大于1
                    if (row != null && (int) row.getLastCellNum() > 1) {
                        XSSFCell cell = row.getCell(2);
                        if (cell != null && cell.getCellTypeEnum() == STRING){
                            String s = cell.getStringCellValue().trim().toUpperCase();
                            if (!s.equals("状态") && !status.contains(s)){
                                status.add(s);
                            }
                        }
                    }
                }
            }
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return status.toArray(new String[0]);
    }


    /**
     * 获取版本
     * @param path
     * @return
     */
    public static String[] getVersion(String path) {
        ArrayList<String> versions = new ArrayList<String>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {
                //读取一个sheet
                XSSFSheet sheet = workbook.getSheetAt(i);
                int rowNum = sheet.getLastRowNum();
                for (int j = 1; j < rowNum; j++) {
                    XSSFRow row = sheet.getRow(j);
                    if (row != null && (int) row.getLastCellNum() > 1) {
                        XSSFCell cell = row.getCell(1);
                        if (cell != null && cell.getCellTypeEnum() == STRING ){
                            String s = cell.getStringCellValue().trim().toUpperCase();
                            if (!s.equals("版本") && !versions.contains(s)) {
                                versions.add(s);
                            }
                        }

                    }
                }
                workbook.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return versions.toArray(new String[0]);
    }
}