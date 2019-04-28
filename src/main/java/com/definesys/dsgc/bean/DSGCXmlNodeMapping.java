package com.definesys.dsgc.bean;

import com.definesys.mpaas.query.annotation.*;

import java.util.Date;

@Table(value = "dsgc_XML_NODE_MAPPING")
public class DSGCXmlNodeMapping {
    /*
   *Author:liuxinke
   * time:2018.10.25
   * 表名：osb_xml_node_mapping  DSGCXmlNodeMapping
   * 功能：接口配置中接收系统的字段映射
   */
    // private Long mappingId;
  //  private Long routingId;

    @RowID(type = RowIDType.UUID)
    private String mappingId;

    private String mapType;

    private String nodeId;
  //  private String servNo;
    private String modelNodeNum;
    private String nodeNum;     //目标格式位置
    private String sourceNode;  //源接口字段
    private String mappingNode;  //目标字段名
    private String sourceModNum;    //源格式位置
    @Column(type = ColumnType.JAVA)
    private Date creationDate;
    @Column(type = ColumnType.JAVA)
    private String createdBy;
   // private String lastUpdateDate;  //更新时间
   @Column(type = ColumnType.JAVA)
    private String updatedBy;    //更新者
    @Column(type = ColumnType.JAVA)
    private Long versionNumber;
    @Column(type = ColumnType.JAVA)
    private String constant;    //常量值

    @Column(type = ColumnType.JAVA)
    private String lastUpdatedBy;    //更新者
    @Column(type = ColumnType.JAVA)
    private String lastUpdateDate;  //更新时间

    //private String targetDataType;  //目标字段名
    private String routingId;
    private String servNo;

    //备用字段
    @Column(type = ColumnType.JAVA)
    private String attribute1;
    @Column(type = ColumnType.JAVA)
    private String attribute2;
    @Column(type = ColumnType.JAVA)
    private String attribute3;
    @Column(type = ColumnType.JAVA)
    private String attribute4;
    @Column(type = ColumnType.JAVA)
    private String attribute5;



    //private String targetDataType;  //目标字段名


    public String getMappingId() {
        return mappingId;
    }

    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
    }

    public String getRoutingId() {
        return routingId;
    }

    public void setRoutingId(String routingId) {
        this.routingId = routingId;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getServNo() {
        return servNo;
    }

    public void setServNo(String servNo) {
        this.servNo = servNo;
    }

    public String getModelNodeNum() {
        return modelNodeNum;
    }

    public void setModelNodeNum(String modelNodeNum) {
        this.modelNodeNum = modelNodeNum;
    }

    public String getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(String nodeNum) {
        this.nodeNum = nodeNum;
    }

    public String getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(String sourceNode) {
        this.sourceNode = sourceNode;
    }

    public String getMappingNode() {
        return mappingNode;
    }

    public void setMappingNode(String mappingNode) {
        this.mappingNode = mappingNode;
    }

    public String getSourceModNum() {
        return sourceModNum;
    }

    public void setSourceModNum(String sourceModNum) {
        this.sourceModNum = sourceModNum;
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

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
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
    public String toString(){
        StringBuilder arg=new StringBuilder("");
        arg.append("MappingId : ").append(this.mappingId);
        arg.append("RoutingId : ").append(this.routingId);
        arg.append("MapType : ").append(this.mapType);
        arg.append("NodeId : ").append(this.nodeId);
        arg.append("ServNo : ").append(this.servNo);
        arg.append("ModelNodeNum : ").append(this.modelNodeNum);
        arg.append("NodeNum : ").append(this.nodeNum);
        arg.append("SourceNode : ").append(this.sourceNode);
        arg.append("MappingNode : ").append(this.mappingNode);
        arg.append("SourceModNum : ").append(this.sourceModNum);
        arg.append("CreationDate : ").append(this.creationDate);
        arg.append("CreatedBy : ").append(this.createdBy);
        arg.append("LastUpdateDate : ").append(this.lastUpdateDate);
        arg.append("LastUpdatedBy : ").append(this.updatedBy);
        arg.append("VersionNumber : ").append(this.versionNumber);
        arg.append("Attribute1 : ").append(this.attribute1);
        arg.append("Attribute2 : ").append(this.attribute2);
        arg.append("Attribute3 : ").append(this.attribute3);
        arg.append("Attribute4 : ").append(this.attribute4);
        arg.append("Attribute5 : ").append(this.attribute5);
        return new String(arg);
    }

}
