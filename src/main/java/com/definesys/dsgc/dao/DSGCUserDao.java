package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.DSGCUser;
import com.definesys.mpaas.common.exception.MpaasBusinessException;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("user")
public class DSGCUserDao {

    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    public DSGCUser login(DSGCUser user) {
        DSGCUser u = sw.buildQuery()
                .eq("userName", user.getUserName())
                .eq("userPassword", user.getUserPassword())
//                .unSelect("userPassword")
                .doQueryFirst(DSGCUser.class);
        if (u == null) {
            throw new MpaasBusinessException("用户名错误或密码不存在");
        }
        return u;
    }

    public String changePwd(DSGCUser user) {
        logger.debug(user.toString());
        DSGCUser user2 = this.findUserById(user);
        user2.setUserPassword(user.getUserPassword());
        sw.buildQuery()
                .eq("userId", user2.getUserId())
                .doUpdate(user2);
        return user2.getUserId();
    }


    public String createUser(DSGCUser user) {
        logger.debug(user.toString());
        sw.buildQuery().
                doInsert(user);
        return user.getUserId();
    }

    public String updateUser(DSGCUser user) {
        logger.debug(user.toString());
        sw.buildQuery()
                .eq("userId", user.getUserId())
                .doUpdate(user);
        return user.getUserId();
    }

    public String deleteUserById(DSGCUser user) {
        logger.debug(user.toString());
        sw.buildQuery()
                .eq("userId", user.getUserId())
                .doDelete(user);
        return user.getUserId();
    }

    public DSGCUser findUserByUserName(String userName) {
       return sw.buildQuery()
                .eq("userName",userName)
                .doQueryFirst(DSGCUser.class);
    }

    public PageQueryResult<DSGCUser> query(DSGCUser user, int pageSize, int pageIndex) {
        logger.debug(user.toString());
        return sw.buildQuery()
                .like("userName", user.getUserName())
                .like("userMail", user.getUserMail())
                .like("userDept", user.getUserDept())
                .like("userPhone", user.getUserPhone())
                .unSelect("userPassword")
                .doPageQuery(pageIndex, pageSize, DSGCUser.class);
//        return user.getUserId();
    }

    public DSGCUser findUserById(DSGCUser user) {
        logger.debug(user.toString());
        return sw.buildQuery().eq("userId", user.getUserId()).doQueryFirst(DSGCUser.class);
    }

    public List<DSGCUser> findAll() {
        return sw.buildQuery()
                .unSelect("userPassword")
                .doQuery(DSGCUser.class);
    }


}
