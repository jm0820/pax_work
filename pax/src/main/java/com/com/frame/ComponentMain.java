package com.com.frame;

import com.bean.Record;
import com.util.ReadExcel;
import com.util.Util;
import com.util.WriteExcel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wjm
 * @date 2023/4/10 16:51
 * @Descript GUI界面
 */
public class ComponentMain {

    File input = null;
    File output = null;
    String inputPath = "";
    String outPath = "";
    String[] status = {};
    String[] versions = {};

    List<Record> records = null;

    JFrame f = new JFrame("用例过滤器");


    // 定义一个按钮，并为其指定图标
    JButton inputFile = new JButton("输入文件");
    JButton outputFile = new JButton("输出文件");
    // 定义一个8行20列的多行文本域.展示选择的文件路径
    JTextArea path1 = new JTextArea(1, 20);
    JTextArea path2 = new JTextArea(1, 20);

    //版本下拉框
    JCheckBox verBox = null;
    JPanel inputArea = new JPanel();
    JPanel filtArea = new JPanel();
    JPanel statusPab = new JPanel();

    JPanel verPan = new JPanel();
    JPanel okPan = new JPanel();
    JTextArea verText = new JTextArea(1,20);
    JButton okButton = new JButton("确定");

    JLabel warn1 = new JLabel("仅支持'xlsx'格式");
    JLabel warn2= new JLabel("仅支持'xls'格式");
//    JLabel okWarn = new JLabel();
    /**
     * 初始化界面
     */
    public void init() {
        // ------------------------文件区域-----------------------
        // 创建一个装载文本框和按钮的JPanel
        inputArea.add(inputFile);
        inputArea.add(path1);
        warn1.setVisible(false);
        inputArea.add(warn1);
        inputFile.addActionListener(fileInput);
        f.add(inputArea, BorderLayout.SOUTH);

        JPanel output = new JPanel();
        output.add(outputFile);
        output.add(path2);
        warn2.setVisible(false);
        output.add(warn2);
        outputFile.addActionListener(fileOut);

        JPanel fileArea = new JPanel();
        fileArea.add(inputArea,BorderLayout.CENTER);
        fileArea.add(output,BorderLayout.CENTER);
        f.add(fileArea,BorderLayout.NORTH);
        // ------------------------过滤区域-----------------------
        filtArea.setLayout(new BorderLayout());
        filtArea.add(verPan,BorderLayout.NORTH);
        //------------------------生成文件-----------------------

        okPan.add(okButton);
        okButton.addActionListener(okAction);
        f.add(okPan,BorderLayout.SOUTH);

        // 设置关闭窗口时推出程序
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置jFrame最佳大小并可见
        f.pack();
        f.setBounds(200,200,800,250);
        f.setVisible(true);
    }

    ActionListener okAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (records == null){
                warn1.setText("输入文件为空");
                warn1.setVisible(true);
            } else if (outPath == null || outPath == ""){
                warn2.setText("输出文件为空");
                warn2.setVisible(true);
            }else{
                //选中的版本号
                String checkVersion = verText.getText();
                List<String> checkStatus = new ArrayList<String>();
                okButton.setVisible(true);
                //拿多选框的值
                //遍历面板的所有控件，找到属于JCheckBox的控件，然后
                //isSelected()方法判断是否选中
                for(Component c:statusPab.getComponents()){
                    if(c instanceof JCheckBox){
                        if(((JCheckBox)c).isSelected()){
                            checkStatus.add(((JCheckBox)c).getText());
                        }
                    }
                }
                try {
                    System.out.println("读取记录条数："+records.size());
                    //选中的状态栏
                    WriteExcel.writeExcel(outPath,records,checkVersion,checkStatus);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    };
    // 输入文件
    ActionListener fileInput = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.showDialog(new JLabel(), "选择");
            input = jFileChooser.getSelectedFile();
            inputPath = input.getAbsolutePath();
            if (!Util.isXlsx(inputPath)){
                warn1.setVisible(true);
            }else {
                warn1.setVisible(false);
                path1.setText(inputPath);
                status = ReadExcel.getStatus(inputPath);
                versions = ReadExcel.getVersion(inputPath);

                JLabel ver = new JLabel("请输入版本号");
                verPan.add(ver);
                verPan.add(verText);
                for (int i = 0;i < status.length;i++){
                    JCheckBox jCheckBox = new JCheckBox(status[i]);
                    statusPab.add(jCheckBox);
                }
                filtArea.add(statusPab,BorderLayout.CENTER);
                f.add(filtArea,BorderLayout.CENTER);

                System.out.println("状态："+status.toString());
                System.out.println(versions.toString());
                try {
                    records = ReadExcel.readExcel(inputPath);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    };

    /**
     * 输出文件
     */
    ActionListener fileOut= new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jFileChooser.showDialog(new JLabel(), "选择");
            output = jFileChooser.getSelectedFile();
            outPath = output.getAbsolutePath();
//            System.out.println("输出文件路径："+outPath);
            if (!Util.isXls(outPath)){
//                System.out.println("输出文件路径："+outPath);
                warn2.setVisible(true);
            }else {
                warn2.setVisible(false);
                path2.setText(outPath);
            }
        }
    };
    public static void main(String[] args) {
        ComponentMain componentMain = new ComponentMain();
        componentMain.init();
    }
}
