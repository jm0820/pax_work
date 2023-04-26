package com.pax;

/**
 * Create by wangjiameng
 * on 2023/3/13.
 * 设备信息
 */
public class PaxInfo {
    private String SystemModel;
    private String  SystemVersion;
    private String DeviceBrand;
    private String SDKVersion;
    private String CPU;

    public PaxInfo(String systemModel, String systemVersion, String deviceBrand, String sdkVersion, String cpu) {
        SystemModel = systemModel;
        SystemVersion = systemVersion;
        DeviceBrand = deviceBrand;
        SDKVersion = sdkVersion;
        CPU = cpu;
    }

    public PaxInfo() {
        SystemModel = null;
        SystemVersion = null;
        DeviceBrand = null;
        SDKVersion = null;
        CPU = null;
    }

    public String getSystemModel() {
        return SystemModel;
    }

    public void setSystemModel(String systemModel) {
        SystemModel = systemModel;
    }

    public String getSystemVersion() {
        return SystemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        SystemVersion = systemVersion;
    }

    public String getDeviceBrand() {
        return DeviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        DeviceBrand = deviceBrand;
    }

    public String getSDKVersion() {
        return SDKVersion;
    }

    public void setSDKVersion(String SDKVersion) {
        this.SDKVersion = SDKVersion;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }
}
