package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



/**
 * Create by wangjiameng
 * on 2023/3/6.
 * 写入文件给到 excel
 */
public class WriteToExcel {
    private static final String EXEL_XLS = "xls";
    private static final String EXEL_XLSX = "xlsx";

    public static void main(String[] args) {
        List<String> header = new ArrayList<>();
        header.add("姓名");
        header.add("年龄");

        List<List> dataList = new ArrayList<List>();

        ArrayList<String> cmd1 = new ArrayList<>();
        cmd1.add("张三");
        cmd1.add("15");

        ArrayList<String> cmd2 = new ArrayList<>();
        cmd2.add("李四");
        cmd2.add("16");

        ArrayList<String> cmd3 = new ArrayList<>();
        cmd3.add("王五");
        cmd3.add("13");

        dataList.add(cmd1);
        dataList.add(cmd2);
        dataList.add(cmd3);

        String path = "F:\\test\\data.xlsx";

        writeExcel(dataList,path,header);
    }
    /**
     *
     * @param dataList 数据集
     * @param filePath 文件路径
     * @param header 标题
     */
    public static void writeExcel(List<List> dataList, String filePath, List<String> header) {
        OutputStream out = null;
        try {

            /**
             read  excel document
             */
            File file = new File(filePath);
            Workbook workbook = null;
            if (file.isDirectory()) {
                // default create .xls
                workbook = new HSSFWorkbook();
            } else if (file.getName().endsWith(".xls")) {
                workbook = new HSSFWorkbook();
            } else if (file.getName().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook();
            } else
                throw new Exception("please get  .xls .xlsx end or directory,[" + filePath + "]");
            //if only support  single sheet
            Sheet sheet = workbook.createSheet("data");
            //set excel header
            Row fisrtRow = sheet.createRow(0);
            int columnCount = header.size();
            for (int h = 0; h < columnCount; h++) {
                fisrtRow.createCell(h).setCellValue(header.get(h));
            }
            /**
             * fill up  data in excel
             */
            int i = 1;
            for (List data : dataList) {
                Row row = sheet.createRow(i);
                Object[] arrays = data.toArray();
                for (int j = 0; j < columnCount; j++) {
                    Object obj = arrays[j];
                    row.createCell(j).setCellValue(obj != null ? obj.toString() : "");
                }
                i++;
            }
            out = new FileOutputStream(file.isDirectory() ? filePath + "/" + header.get(0) + ".xls" : filePath);
            workbook.write(out);

        } catch (Exception e) {

        } finally {

            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException o) {
            }

        }
    }
}
