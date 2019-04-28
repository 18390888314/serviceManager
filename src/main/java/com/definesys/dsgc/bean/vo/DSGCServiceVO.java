package com.definesys.dsgc.bean.vo;

import com.definesys.dsgc.bean.DSGCValidResutl;

import java.util.List;


/**
 * @author zhenglong
 * @Description: DSGCService 和DSGCValidREsutl接收请求的封装对象
 * @Date 2019/3/13 15:25
 */

public class DSGCServiceVO {
    private String servNo;
    private String servName;
    private String servStatus;
    private String servUrl;
    private String techType;
    private String transType;

    private String categoryLevel1;
    private String categoryLevel2;
    private String categoryLevel3;
    private String categoryLevel4;
    private String systemCode;

    private String validType;
    private String validColumn;
    private String validValue;
    private String bizDesc;
    private String techDesc;

    private String servDesc;
    private List<DSGCValidResutl> serviceBaseInfoSucType;
    private String filePath;
    private String fileName;
    private String servId;
    private String servTemplate;
    private String dataTypeCode;

    public String getServTemplate() {
        return servTemplate;
    }

    public void setServTemplate(String servTemplate) {
        this.servTemplate = servTemplate;
    }

    public String getDataTypeCode() {
        return dataTypeCode;
    }

    public void setDataTypeCode(String dataTypeCode) {
        this.dataTypeCode = dataTypeCode;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getServNo() {
        return servNo;
    }

    public void setServNo(String servNo) {
        this.servNo = servNo;
    }

    public String getServName() {

        return servName;
    }

    public void setServName(String servName) {
        this.servName = servName;
    }

    public String getServStatus() {
        return servStatus;
    }

    public void setServStatus(String servStatus) {
        this.servStatus = servStatus;
    }

    public String getServUrl() {
        return servUrl;
    }

    public void setServUrl(String servUrl) {
        this.servUrl = servUrl;
    }

    public String getTechType() {
        return techType;
    }

    public void setTechType(String techType) {
        this.techType = techType;
    }

    public String getCategoryLevel1() {
        return categoryLevel1;
    }

    public void setCategoryLevel1(String categoryLevel1) {
        this.categoryLevel1 = categoryLevel1;
    }

    public String getCategoryLevel2() {
        return categoryLevel2;
    }

    public void setCategoryLevel2(String categoryLevel2) {
        this.categoryLevel2 = categoryLevel2;
    }

    public String getCategoryLevel3() {
        return categoryLevel3;
    }

    public void setCategoryLevel3(String categoryLevel3) {
        this.categoryLevel3 = categoryLevel3;
    }

    public String getCategoryLevel4() {
        return categoryLevel4;
    }

    public void setCategoryLevel4(String categoryLevel4) {
        this.categoryLevel4 = categoryLevel4;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }

    public String getValidColumn() {
        return validColumn;
    }

    public void setValidColumn(String validColumn) {
        this.validColumn = validColumn;
    }

    public String getValidValue() {
        return validValue;
    }

    public void setValidValue(String validValue) {
        this.validValue = validValue;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public String getTechDesc() {
        return techDesc;
    }

    public void setTechDesc(String techDesc) {
        this.techDesc = techDesc;
    }

    public String getServDesc() {
        return servDesc;
    }

    public void setServDesc(String servDesc) {
        this.servDesc = servDesc;
    }

    public List<DSGCValidResutl> getServiceBaseInfoSucType() {
        return serviceBaseInfoSucType;
    }

    public void setServiceBaseInfoSucType(List<DSGCValidResutl> serviceBaseInfoSucType) {
        this.serviceBaseInfoSucType = serviceBaseInfoSucType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getServId() {
        return servId;
    }

    public void setServId(String servId) {
        this.servId = servId;
    }

    @Override
    public String toString() {
        return "DSGCServiceVO{" +
                "servNo='" + servNo + '\'' +
                ", servName='" + servName + '\'' +
                ", servStatus='" + servStatus + '\'' +
                ", servUrl='" + servUrl + '\'' +
                ", techType='" + techType + '\'' +
                ", categoryLevel1='" + categoryLevel1 + '\'' +
                ", categoryLevel2='" + categoryLevel2 + '\'' +
                ", categoryLevel3='" + categoryLevel3 + '\'' +
                ", categoryLevel4='" + categoryLevel4 + '\'' +
                ", systemCode='" + systemCode + '\'' +
                ", validType='" + validType + '\'' +
                ", validColumn='" + validColumn + '\'' +
                ", validValue='" + validValue + '\'' +
                ", bizDesc='" + bizDesc + '\'' +
                ", techDesc='" + techDesc + '\'' +
                ", servDesc='" + servDesc + '\'' +
                ", serviceBaseInfoSucType=" + serviceBaseInfoSucType +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", servId='" + servId + '\'' +
                '}';
    }
}
