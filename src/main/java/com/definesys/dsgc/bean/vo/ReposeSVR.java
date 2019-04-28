package com.definesys.dsgc.bean.vo;


import com.definesys.mpaas.query.annotation.Column;
import com.definesys.mpaas.query.annotation.SQL;
import com.definesys.mpaas.query.annotation.SQLQuery;


/**
 * @author zhenglong
 * @Description:
 * @Date 2019/3/13 21:09
 */

//@SQLQuery(value = {
//        @SQL(view = "vv", sql = "select  ds.* ,dr.system_code,dr.valid_type,dr.valid_column,dr.valid_value  from dsgc_services  ds ,dsgc_valid_result dr where ds.serv_no = dr.serv_no")
//})
public class ReposeSVR {
    @Column(value = "serv_no")
    private String servNo;
    @Column(value = "serv_name")
    private String servName;
    @Column(value = "serv_status")
    private String servStatus;
    @Column(value = "serv_url")
    private String servUrl;
    @Column(value = "tech_type")
    private String techType;
    @Column(value = "transType")
    private String transType;
    @Column(value = "category_level1")
    private String categoryLevel1;
    @Column(value = "category_level2")

    private String categoryLevel2;
    @Column(value = "category_level3")

    private String categoryLevel3;
    @Column(value = "category_level4")

    private String categoryLevel4;
    @Column(value = "system_code")

    private String systemCode;
    @Column(value = "valid_type")
    private String validType;
    @Column(value = "valid_column")
    private String validColumn;
    @Column(value = "valid_value")
    private String validValue;
    @Column(value = "biz_desc")
    private String bizDesc;
    @Column(value = "tech_desc")
    private String techDesc;
    @Column(value = "sery_desc")
    private String servDesc;
    @Column(value = "file_path")
    private String filePath;
    @Column(value = "file_name")
    private String fileName;
    @Column(value = "serv_id")
    private String servId;

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

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
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
}
