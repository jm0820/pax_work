package pax.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.Locale;

/**
 * Create by wangjiameng
 * on 2023/3/6.
 */
public class SystemUtil {
    public static void main(String[] args) {

        System.out.println(getCPU());
        System.out.println(getDeviceBrand());
        System.out.println(getSDKVersion());
    }
    /**
     * 获取当前系统语言
     * @return 返回当前系统语言
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList(){
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     * @return
     */
    public static String getSystemVersion() {
//        String cmd = "adb shell getprop ro.build.version.release";
//        String version = CMDExcute.execCommand(cmd);
//        return version;
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     * @return 型号
     */
    public static String getSystemModel() {
//        String cmd = "adb shell getprop ro.product.model";
//        String model = CMDExcute.execCommand(cmd);
//        return model;
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     * @return 手机厂商
     */
    public static String  getDeviceBrand(){
//        String cmd = "adb shell getprop ro.product.brand";
//        String brand = CMDExcute.execCommand(cmd);
//        return brand;
        String manufacturer = android.os.Build.MANUFACTURER;
        return manufacturer;
    }

    /**
     * 获取sdk版本号
     * @return
     */
    public static String getSDKVersion(){
//        String cmd = "adb shell getprop ro.build.version.sdk";
//        String sdk = CMDExcute.execCommand(cmd);
//        return sdk;
        return Build.VERSION.SDK;
    }

    /**
     * 获取手机cpu
     * @return
     */
    public static String getCPU(){
//        String cmd = "adb shell getprop ro.product.cpu.abilist";
//        String cpu = CMDExcute.execCommand(cmd);
//        return cpu;
//
        return android.os.Build.CPU_ABI;
    }


}
