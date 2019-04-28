package com.definesys.dsgc.bean;

import com.definesys.mpaas.query.annotation.*;
import com.definesys.mpaas.query.json.MpaasDateTimeDeserializer;
import com.definesys.mpaas.query.json.MpaasDateTimeSerializer;
import com.definesys.mpaas.query.model.MpaasBasePojo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "流量控制配置表",description = "存储接口的流量控制配置")
@Table("dsgc_SERV_FLOW_CONT_CFG")
public class DSGCServFlowContCfg extends MpaasBasePojo {
    @ApiModelProperty(value = "唯一标识",notes = "数据库是NUMBER类型")
    @RowID(sequence = "OSB_SERV_FLOW_CONT_CFG_S",type = RowIDType.AUTO)
    private String flowId;

    @ApiModelProperty(value = "接口编号",notes = "不能超过40个字符")
    @Column(value = "SERV_NO")
    private String servNo;

    @ApiModelProperty(value = "流量控制维度",notes = "不能超过40个字符")
    @Column(value = "FLOW_DIMENSION")
    private String flowDimension;

    @ApiModelProperty(value = "流量控制判断",notes = "不能超过40个字符")
    @Column(value = "FLOW_FLAG")
    private String flowFlag;

    @ApiModelProperty(value = "流量控制数量",notes = "NUMBER类型")
    @Column(value = "FLOW_NUM")
    private Integer flowNum;

    @ApiModelProperty(value = "流量控制开始生效时间",notes = "DATE类型")
    @JsonSerialize(using = MpaasDateTimeSerializer.class)
    @JsonDeserialize(using=MpaasDateTimeDeserializer.class)
    @Column(value = "FLOW_START_TIME")
    private Date flowStartTime;

    @ApiModelProperty(value = "流量控制生效结束时间",notes = "DATE类型")
    @JsonSerialize(using = MpaasDateTimeSerializer.class)
    @JsonDeserialize(using=MpaasDateTimeDeserializer.class)
    @Column(value = "FLOW_END_TIME")
    private Date flowEndTime;

    @Column("ATTRIBUTE1")
    private String attribute1;

    @Column("ATTRIBUTE2")
    private String attribute2;

    @Column("ATTRIBUTE3")
    private String attribute3;

    @Column("ATTRIBUTE4")
    private String attribute4;

    @Column("ATTRIBUTE5")
    private String attribute5;

    @ApiModelProperty(value = "生效开始时间段",notes = "DATE类型")
    @JsonSerialize(using = MpaasDateTimeSerializer.class)
    @JsonDeserialize(using=MpaasDateTimeDeserializer.class)
    @Column(value = "START_TIME")
    private Date startTime;

    @ApiModelProperty(value = "生效结束时间段",notes = "DATE类型")
    @JsonSerialize(using = MpaasDateTimeSerializer.class)
    @JsonDeserialize(using=MpaasDateTimeDeserializer.class)
    @Column(value = "END_TIME")
    private Date endTime;

    @ApiModelProperty(value = "是否有效",notes = "0表示无效,1表示有效")
    @Column("IS_EFFECTIVE")
    private Integer effecTive;

    @JsonSerialize(using = MpaasDateTimeSerializer.class)
    @JsonDeserialize(using=MpaasDateTimeDeserializer.class)
    @SystemColumn(SystemColumnType.CREATE_ON)
    @Column(value = "CREATION_DATE")
    private Date creationDate;

    @SystemColumn(SystemColumnType.CREATE_BY)
    @Column(value = "CREATED_BY")
    private String createdBy;

    @JsonSerialize(using = MpaasDateTimeSerializer.class)
    @JsonDeserialize(using = MpaasDateTimeDeserializer.class)
    @SystemColumn(SystemColumnType.LASTUPDATE_ON)
    @Column(value = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @SystemColumn(SystemColumnType.LASTUPDATE_BY)
    @Column(value = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @SystemColumn(SystemColumnType.OBJECT_VERSION)
    @Column(value = "OBJECT_VERSION_NUMBER")
    private Integer objectVersionNumber;

    @ApiModelProperty(value = "开始月份",notes = "NUMBER类型")
    @Column("START_MONTH")
    private Integer startMonth;

    @ApiModelProperty(value = "结束月份",notes = "NUMBER类型")
    @Column("END_MONTH")
    private Integer endMonth;

    @ApiModelProperty(value = "开始天数",notes = "NUMBER类型")
    @Column("START_DAY")
    private Integer startDay;

    @ApiModelProperty(value = "结束天数",notes = "NUMBER类型")
    @Column("END_DAY")
    private Integer endDay;

    @ApiModelProperty(value = "每月最后几天",notes = "NUMBER类型")
    @Column("LAST_DAY")
    private Integer lastDay;

    @ApiModelProperty(value = "控制类型",notes = "0表示时间段,1表示时间")
    @Column("DATA_SELECT")
    private Integer dataSelect;


    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getServNo() {
        return servNo;
    }

    public void setServNo(String servNo) {
        this.servNo = servNo;
    }

    public String getFlowDimension() {
        return flowDimension;
    }

    public void setFlowDimension(String flowDimension) {
        this.flowDimension = flowDimension;
    }

    public String getFlowFlag() {
        return flowFlag;
    }

    public void setFlowFlag(String flowFlag) {
        this.flowFlag = flowFlag;
    }

    public Integer getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(Integer flowNum) {
        this.flowNum = flowNum;
    }

    public Date getFlowStartTime() {
        return flowStartTime;
    }

    public void setFlowStartTime(Date flowStartTime) {
        this.flowStartTime = flowStartTime;
    }

    public Date getFlowEndTime() {
        return flowEndTime;
    }

    public void setFlowEndTime(Date flowEndTime) {
        this.flowEndTime = flowEndTime;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getEffecTive() {
        return effecTive;
    }

    public void setEffecTive(Integer effecTive) {
        this.effecTive = effecTive;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public void setStartDay(Integer startDay) {
        this.startDay = startDay;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public void setEndDay(Integer endDay) {
        this.endDay = endDay;
    }

    public Integer getLastDay() {
        return lastDay;
    }

    public void setLastDay(Integer lastDay) {
        this.lastDay = lastDay;
    }

    public Integer getDataSelect() {
        return dataSelect;
    }

    public void setDataSelect(Integer dataSelect) {
        this.dataSelect = dataSelect;
    }
}
