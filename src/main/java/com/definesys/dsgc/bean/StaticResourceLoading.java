package com.definesys.dsgc.bean;

import com.definesys.mpaas.query.annotation.*;
import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.definesys.mpaas.query.model.MpaasBasePojo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;

import java.util.Date;

@Table(value = "dsgc_system_properties")
@ApiModel(value = "静态资源", description = "存储静态资源")
public class StaticResourceLoading extends MpaasBasePojo {
    @RowID(sequence = "dsgc_system_properties_s", type = RowIDType.AUTO)
    @Column(value = "PROPERTY_ID")
    private String PROPERTY_ID;

    @Column(value = "PROPERTY_KEY")
    private String PROPERTY_KEY;
    @Column(value = "PROPERTY_VALUE")
    private String PROPERTY_VALUE;
    @Column(value = "PROPERTY_DESCRIPTION")
    private String PROPERTY_DESCRIPTION;
    @Column(value = "ALLOW_USER_DEFINED")
    private String ALLOW_USER_DEFINED;
    @Column(value = "ATTRIBUTE1")
    private String ATTRIBUTE1;
    @Column(value = "ATTRIBUTE2")
    private String ATTRIBUTE2;
    @Column(value = "ATTRIBUTE3",type = ColumnType.JAVA)
    private String ATTRIBUTE3;
    @Column(value = "ATTRIBUTE4")
    private String ATTRIBUTE4;
    @Column(value = "ATTRIBUTE5")
    private String ATTRIBUTE5;


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

    public String getPROPERTY_ID() {
        return PROPERTY_ID;
    }

    public void setPROPERTY_ID(String PROPERTY_ID) {
        this.PROPERTY_ID = PROPERTY_ID;
    }

    public String getPROPERTY_KEY() {
        return PROPERTY_KEY;
    }

    public void setPROPERTY_KEY(String PROPERTY_KEY) {
        this.PROPERTY_KEY = PROPERTY_KEY;
    }

    public String getPROPERTY_VALUE() {
        return PROPERTY_VALUE;
    }

    public void setPROPERTY_VALUE(String PROPERTY_VALUE) {
        this.PROPERTY_VALUE = PROPERTY_VALUE;
    }

    public String getPROPERTY_DESCRIPTION() {
        return PROPERTY_DESCRIPTION;
    }

    public void setPROPERTY_DESCRIPTION(String PROPERTY_DESCRIPTION) {
        this.PROPERTY_DESCRIPTION = PROPERTY_DESCRIPTION;
    }

    public String getALLOW_USER_DEFINED() {
        return ALLOW_USER_DEFINED;
    }

    public void setALLOW_USER_DEFINED(String ALLOW_USER_DEFINED) {
        this.ALLOW_USER_DEFINED = ALLOW_USER_DEFINED;
    }

    public String getATTRIBUTE1() {
        return ATTRIBUTE1;
    }

    public void setATTRIBUTE1(String ATTRIBUTE1) {
        this.ATTRIBUTE1 = ATTRIBUTE1;
    }

    public String getATTRIBUTE2() {
        return ATTRIBUTE2;
    }

    public void setATTRIBUTE2(String ATTRIBUTE2) {
        this.ATTRIBUTE2 = ATTRIBUTE2;
    }

    public String getATTRIBUTE3() {
        return ATTRIBUTE3;
    }

    public void setATTRIBUTE3(String ATTRIBUTE3) {
        this.ATTRIBUTE3 = ATTRIBUTE3;
    }

    public String getATTRIBUTE4() {
        return ATTRIBUTE4;
    }

    public void setATTRIBUTE4(String ATTRIBUTE4) {
        this.ATTRIBUTE4 = ATTRIBUTE4;
    }

    public String getATTRIBUTE5() {
        return ATTRIBUTE5;
    }

    public void setATTRIBUTE5(String ATTRIBUTE5) {
        this.ATTRIBUTE5 = ATTRIBUTE5;
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
}
