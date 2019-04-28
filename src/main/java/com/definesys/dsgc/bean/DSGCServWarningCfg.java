package com.definesys.dsgc.bean;

import com.definesys.mpaas.query.annotation.RowID;
import com.definesys.mpaas.query.annotation.RowIDType;
import com.definesys.mpaas.query.annotation.Table;
import com.definesys.mpaas.query.model.MpaasBasePojo;

/*
 *Author:xiezhongyuan
 * time:2018.10.25
 * 表名：osb_serv_warning_cfg
 * 功能：预警功能
 */
@Table("dsgc_serv_warning_cfg")
public class DSGCServWarningCfg  extends MpaasBasePojo {
    @RowID(type = RowIDType.UUID)
    private String warningId;
    private String servNo;
    private String warningDimension;
    private String warningFlag;
    private String warningNum;


    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;

    public String getWarningId() {
        return warningId;
    }

    public void setWarningId(String warningId) {
        this.warningId = warningId;
    }

    public String getServNo() {
        return servNo;
    }

    public void setServNo(String servNo) {
        this.servNo = servNo;
    }

    public String getWarningDimension() {
        return warningDimension;
    }

    public void setWarningDimension(String warningDimension) {
        this.warningDimension = warningDimension;
    }

    public String getWarningFlag() {
        return warningFlag;
    }

    public void setWarningFlag(String warningFlag) {
        this.warningFlag = warningFlag;
    }

    public String getWarningNum() {
        return warningNum;
    }

    public void setWarningNum(String warningNum) {
        this.warningNum = warningNum;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    @Override
    public String toString() {
        StringBuilder arg=new StringBuilder("");
        arg.append("WarningId : ").append(this.warningId);
        arg.append("ServNo : ").append(this.servNo);
        arg.append("WarningDimension : ").append(this.warningDimension);
        arg.append("WarningFlag : ").append(this.warningFlag);
        arg.append("WarningNum : ").append(this.warningNum);
        arg.append("Attribute1 : ").append(this.attribute1);
        arg.append("Attribute2 : ").append(this.attribute2);
        arg.append("Attribute3 : ").append(this.attribute3);
        arg.append("Attribute4 : ").append(this.attribute4);
        arg.append("Attribute5 : ").append(this.attribute5);
        return new String(arg);
    }
}
