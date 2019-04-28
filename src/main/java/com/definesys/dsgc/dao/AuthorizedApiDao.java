package com.definesys.dsgc.dao;

import com.definesys.dsgc.bean.AuthorizedApi;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorizedApiDao {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    //查询到所有授权的用户
    public List<AuthorizedApi> findAllUser() {
        return sw.buildQuery().doQuery(AuthorizedApi.class);
    }
}
