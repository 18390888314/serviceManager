package com.definesys.dsgc.bean;

import com.definesys.mpaas.query.annotation.*;
import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.definesys.mpaas.query.json.MpaasDateTimeDeserializer;
import com.definesys.mpaas.query.json.MpaasDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Table(value = "dsgc_log_instance")
public class DSGCLogInstance {

    @RowID(type = RowIDType.UUID)
    private String trackId;  //日志实例ID
    private String token;  //token名
    private String servNo; //服务编码
    private String servName; //服务名称
    private String reqFrom;  //请求方
    private String servOper;   //服务执行的方法

    private String startTime;  //请求时间
    private String endTime;   //结束时间
    private String clientIp;  //请求方IP
    private String instStatus;//实例状态
    private String bizStatus;
    private String bizStatusDtl; //发送详情
    private Integer runTimes;  //执行次数
    private String resTime;   //同步响应时间
    private String invokeResult; //出栈反馈结果
    private String results;  //出栈响应结果
    private String ibLob;   //请求报文
    private Integer msgNum;//报文数量
    private Integer msgSize;//报文大小
    private String obLob;
    private String plCompress;
    private String plStoreType;
    private String server;   //运行的服务器
    private Long sequencesId;
    private String billGuid;
    private String aysnStatus;



    @SystemColumn(SystemColumnType.OBJECT_VERSION)
    @Column(value = "object_version_number")
    private Integer objectVersionNumber;
    @SystemColumn(SystemColumnType.CREATE_BY)
    @Column(value = "created_by")
    private String createdBy;
    @JsonSerialize(using = MpaasDateSerializer.class)
    @JsonDeserialize(using = MpaasDateDeserializer.class)
    @SystemColumn(SystemColumnType.CREATE_ON)
    @Column(value = "creation_date")
    private Date creationDate;
    @SystemColumn(SystemColumnType.LASTUPDATE_BY)
    @Column(value = "last_updated_by")
    private String lastUpdatedBy;
    @JsonSerialize(using = MpaasDateSerializer.class)
    @JsonDeserialize(using = MpaasDateDeserializer.class)
    @SystemColumn(SystemColumnType.LASTUPDATE_ON)
    @Column(value = "last_update_date")
    private Date lastUpdateDate;


    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInstStatus() {
        return instStatus;
    }

    public void setInstStatus(String instStatus) {
        this.instStatus = instStatus;
    }

    public Integer getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(Integer runTimes) {
        this.runTimes = runTimes;
    }

    public Integer getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(Integer msgNum) {
        this.msgNum = msgNum;
    }

    public Integer getMsgSize() {
        return msgSize;
    }

    public void setMsgSize(Integer msgSize) {
        this.msgSize = msgSize;
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

    public String getReqFrom() {
        return reqFrom;
    }

    public void setReqFrom(String reqFrom) {
        this.reqFrom = reqFrom;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getStartTime() {
        return startTime;
    }

    public Date getStartTimeDate() throws ParseException {
        if(startTime == null){
            return null;
        }
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(startTime);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getEndTime() {
        return endTime;
    }

    public Date getEndTimeDate() throws Exception{
        if(endTime == null){
            return null;
        }
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(endTime);
    }


    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getResTime() {
        return resTime;
    }

    public void setResTime(String resTime) {
        this.resTime = resTime;
    }

    public String getInvokeResult() {
        return invokeResult;
    }

    public void setInvokeResult(String invokeResult) {
        this.invokeResult = invokeResult;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getBizStatusDtl() {
        return bizStatusDtl;
    }

    public void setBizStatusDtl(String bizStatusDtl) {
        this.bizStatusDtl = bizStatusDtl;
    }

    public String getIbLob() {
        return ibLob;
    }

    public void setIbLob(String ibLob) {
        this.ibLob = ibLob;
    }

    public String getServOper() {
        return servOper;
    }

    public void setServOper(String servOper) {
        this.servOper = servOper;
    }

    public String getBizStatus() {
        return bizStatus;
    }

    public void setBizStatus(String bizStatus) {
        this.bizStatus = bizStatus;
    }

    public String getObLob() {
        return obLob;
    }

    public void setObLob(String obLob) {
        this.obLob = obLob;
    }

    public String getPlCompress() {
        return plCompress;
    }

    public void setPlCompress(String plCompress) {
        this.plCompress = plCompress;
    }

    public String getPlStoreType() {
        return plStoreType;
    }

    public void setPlStoreType(String plStoreType) {
        this.plStoreType = plStoreType;
    }

    public Long getSequencesId() {
        return sequencesId;
    }

    public void setSequencesId(Long sequencesId) {
        this.sequencesId = sequencesId;
    }

    public String getBillGuid() {
        return billGuid;
    }

    public void setBillGuid(String billGuid) {
        this.billGuid = billGuid;
    }

    public Integer getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Integer objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getAysnStatus() {
        return aysnStatus;
    }

    public void setAysnStatus(String aysnStatus) {
        this.aysnStatus = aysnStatus;
    }

    @Override
    public String toString() {
        return "DSGCLogInstance{" +
                "trackId='" + trackId + '\'' +
                ", token='" + token + '\'' +
                ", servNo='" + servNo + '\'' +
                ", servName='" + servName + '\'' +
                ", reqFrom='" + reqFrom + '\'' +
                ", servOper='" + servOper + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", instStatus='" + instStatus + '\'' +
                ", bizStatus='" + bizStatus + '\'' +
                ", bizStatusDtl='" + bizStatusDtl + '\'' +
                ", runTimes=" + runTimes +
                ", resTime='" + resTime + '\'' +
                ", invokeResult='" + invokeResult + '\'' +
                ", results='" + results + '\'' +
                ", ibLob='" + ibLob + '\'' +
                ", msgNum=" + msgNum +
                ", msgSize=" + msgSize +
                ", obLob='" + obLob + '\'' +
                ", plCompress='" + plCompress + '\'' +
                ", plStoreType='" + plStoreType + '\'' +
                ", server='" + server + '\'' +
                ", sequencesId=" + sequencesId +
                ", billGuid='" + billGuid + '\'' +
                ", aysnStatus='" + aysnStatus + '\'' +
                ", objectVersionNumber=" + objectVersionNumber +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
