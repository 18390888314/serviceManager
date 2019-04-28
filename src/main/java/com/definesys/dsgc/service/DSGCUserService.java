package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.DSGCServiceUser;
import com.definesys.dsgc.bean.DSGCUser;
import com.definesys.dsgc.dao.DSGCServiceDao;
import com.definesys.dsgc.dao.DSGCUserDao;
import com.definesys.mpaas.common.exception.MpaasBusinessException;
import com.definesys.mpaas.query.db.PageQueryResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("userService")
public class DSGCUserService {

    @Autowired
    DSGCUserDao user;

    @Autowired
    DSGCServiceDao dsgc_service;

    public DSGCUser login(DSGCUser user) {
        return this.user.login(user);
    }

    @Transactional
    public String createUser(DSGCUser user, List<DSGCServiceUser> dsgcServiceUsers) {
        String id = this.user.createUser(user);
        user = this.user.findUserByUserName(user.getUserName());
        String userId = user.getUserId();
        if (dsgcServiceUsers != null && dsgcServiceUsers.size() > 0) {
            for (int i = 0; i < dsgcServiceUsers.size(); i++) {
                DSGCServiceUser serviceUser = dsgcServiceUsers.get(i);
                serviceUser.setUserId(userId);
                this.dsgc_service.saveServiceUser(serviceUser);
            }
        }
        return userId;
    }

    public boolean checkUserName(String userName) {
        return this.user.findUserByUserName(userName) == null;
    }

    @Transactional
    public String updateUser(DSGCUser user, List<DSGCServiceUser> dsgcServiceUsers) {
        this.dsgc_service.deleteServiceUser(user.getUserId());
        if (dsgcServiceUsers != null && dsgcServiceUsers.size() > 0) {
            for (int i = 0; i < dsgcServiceUsers.size(); i++) {
                this.dsgc_service.saveServiceUser(dsgcServiceUsers.get(i));
            }
        }
        return this.user.updateUser(user);
    }


    @Transactional
    public String changePwd(DSGCUser user) {
        return this.user.changePwd(user);
    }

    @Transactional
    public String deleteUserById(DSGCUser user) {
        return this.user.deleteUserById(user);
    }

    public PageQueryResult<DSGCUser> query(DSGCUser user, int pageSize, int pageIndex) {
        return this.user.query(user, pageSize, pageIndex);
    }

    public JSONObject findUserById(DSGCUser user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userInfo", this.user.findUserById(user));
        jsonObject.put("serviceInfo", this.dsgc_service.findServiceUserByUserId(user.getUserId()));
        return jsonObject;
    }

    public List<DSGCUser> findAll() {
        return this.user.findAll();
    }

}
