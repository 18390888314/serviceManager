package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.FndModules;
import com.definesys.dsgc.dao.FndModulesDao;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FndModulesService {

    @Autowired
    FndModulesDao fndModulesDao;

    @Transactional
    public String createFndModules(FndModules t) {
        return fndModulesDao.createFndModules(t);
    }

    @Transactional
    public String updateFndModules(FndModules t) {
        return fndModulesDao.updateFndModules(t);
    }

    @Transactional
    public String deleteFndModulesById(FndModules t) {
        return fndModulesDao.deleteFndModulesById(t);
    }

    public PageQueryResult<FndModules> query(FndModules t, int pageSize, int pageIndex) {
        return fndModulesDao.query(t, pageSize, pageIndex);
    }

    public FndModules findFndModulesById(FndModules t) {
        return fndModulesDao.findFndModulesById(t);
    }
}
