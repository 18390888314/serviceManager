package com.definesys.dsgc.bean;

import com.definesys.mpaas.query.annotation.*;
import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.definesys.mpaas.query.model.MpaasBasePojo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * @author zhenglong
 * @Description:
 * @Date 2019/3/8 14:28
 */
@Table(value = "dsgc_category_trd")
@ApiModel(value = "三级分类信息", description = "存储三级分类信息")
public class DSGCCategoryTrd extends MpaasBasePojo {
    @RowID(type = RowIDType.UUID)
    @Column(value = "category_id")
    private String categoryId;
    @Column(value = "snd_category_id")
    private String sndCategoryId;
    @Column(value = "category_code")
    private  String categoryCode;
    @Column (value = "category_name")
    private  String categoryName;
    @Column (value = "category_desc")
    private String categoryDesc;
    @Column(value = "path_desc")
    private  String pathDesc;
    @Column(value = "language")
    private String language;

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

    public DSGCCategoryTrd() {
        super();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSndCategoryId() {
        return sndCategoryId;
    }

    public void setSndCategoryId(String sndCategoryId) {
        this.sndCategoryId = sndCategoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getPathDesc() {
        return pathDesc;
    }

    public void setPathDesc(String pathDesc) {
        this.pathDesc = pathDesc;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public DSGCCategoryTrd(String categoryId, String sndCategoryId, String categoryCode, String categoryName, String categoryDesc, String pathDesc, String language, Integer objectVersionNumber, String createdBy, Date creationDate, String lastUpdatedBy, Date lastUpdateDate) {

        this.categoryId = categoryId;
        this.sndCategoryId = sndCategoryId;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
        this.pathDesc = pathDesc;
        this.language = language;
        this.objectVersionNumber = objectVersionNumber;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdateDate = lastUpdateDate;
    }
}
