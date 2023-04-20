package com.bean;

import java.text.SimpleDateFormat;

/**
 * @author wjm
 * @date 2023/4/6 17:55
 * @Descript 一个输入文件的对象
 */
public class Record {
    private String receiver;
    private String version;
    private String status;
    private String modifyTime;
    private String type;
    private String codeNum;
    private String other;
    private String modifyTitle;
    private String Summary;
    private String testPlan;

    public Record() {
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getModifyTitle() {
        return modifyTitle;
    }

    public void setModifyTitle(String modifyTitle) {
        this.modifyTitle = modifyTitle;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(String testPlan) {
        this.testPlan = testPlan;
    }

    @Override
    public String toString() {
        return "Record{" +
                "receiver='" + receiver + '\'' +
                ", version='" + version + '\'' +
                ", status='" + status + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", type='" + type + '\'' +
                ", codeNum='" + codeNum + '\'' +
                ", other='" + other + '\'' +
                ", modifyTitle='" + modifyTitle + '\'' +
                ", Summary='" + Summary + '\'' +
                ", testPlan='" + testPlan + '\'' +
                '}';
    }
}
