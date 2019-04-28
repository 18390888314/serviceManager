package com.definesys.dsgc.bean;

import com.definesys.mpaas.query.annotation.Column;
import com.definesys.mpaas.query.annotation.RowID;
import com.definesys.mpaas.query.annotation.RowIDType;
import com.definesys.mpaas.query.annotation.Table;
import io.swagger.annotations.ApiModel;

@Table(value = "DSGC_SERVICE_USER")
@ApiModel(value = "接口授权",description = "添加授权的用户")
public class AuthorizedApi {

    @RowID(type = RowIDType.UUID)
    @Column(value = "SERV_NO")
    private String servNo; //服务No

    @Column(value = "IS_SHOW")
    private String isShow; //可查看

    @Column(value = "IS_MODIFY")
    private String isModify; //可配置修改

    @Column(value = "REMARK")
    private String remark; //通知规则

    public String getServNo() {
        return servNo;
    }

    public void setServNo(String servNo) {
        this.servNo = servNo;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getIsModify() {
        return isModify;
    }

    public void setIsModify(String isModify) {
        this.isModify = isModify;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
