package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pax.PaxInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pax.util.CMDExcute;
import pax.util.SystemUtil;
import utils.WriteToExcel;

public class MainActivity extends AppCompatActivity {
    private TextView cmdResult;
    private Button myButton;
    private TextView myText;
    private TextView cmdInput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件对象实例化
        myButton = (Button)findViewById(R.id.button1);
        myText = (TextView) findViewById(R.id.deviceMessage);
        cmdResult = (TextView) findViewById(R.id.cmdresult);
        cmdInput = (TextView) findViewById(R.id.cmdInput);

        PaxInfo paxInfo = new PaxInfo(SystemUtil.getSystemModel(),SystemUtil.getSystemVersion(),SystemUtil.getDeviceBrand(),SystemUtil.getSDKVersion(),SystemUtil.getCPU());

        //获取设备信息
        String deviceMessage = "手机型号:"+ paxInfo.getSystemModel()+"\n";
        deviceMessage = deviceMessage + "系统版本："+ paxInfo.getSystemVersion()+ "\n";
        deviceMessage = deviceMessage + "厂商："+paxInfo.getDeviceBrand() + "\n";
        deviceMessage = deviceMessage + "SDK 版本："+paxInfo.getSDKVersion()+"\n";
        deviceMessage = deviceMessage + "CPU："+paxInfo.getCPU()+"\n";
        System.out.println(deviceMessage);
        myText.setText(deviceMessage);

        //执行cmd命令
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("================================onclick=========================");
                // 读取txt文件
                String cmd = (String) cmdInput.getText();
                System.out.println("准备执行命令："+cmd);
                // 执行cmd命令
                String result = CMDExcute.execCommand(cmd);
                System.out.println("执行cmd命令生成结果:"+result);
                cmdResult.setText(result);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }
}