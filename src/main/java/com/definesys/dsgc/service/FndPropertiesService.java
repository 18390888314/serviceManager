package com.definesys.dsgc.service;

import com.definesys.dsgc.bean.FndProperties;
import com.definesys.dsgc.dao.FndPropertiesDao;
import com.definesys.mpaas.query.db.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FndPropertiesService {

    @Autowired
    FndPropertiesDao fndPropertiesDao;

    @Transactional
    public String createFndProperties(FndProperties fndProperties) {
        return fndPropertiesDao.createFndProperties(fndProperties);
    }
    @Transactional
    public String updateFndProperties(FndProperties fndProperties) {
    return fndPropertiesDao.updateFndProperties(fndProperties);
    }

    @Transactional
    public String deleteFndPropertiesById(FndProperties fndProperties) {
        return fndPropertiesDao.deleteFndPropertiesById(fndProperties);
    }

    public PageQueryResult<FndProperties> query(FndProperties fndProperties, int pageSize, int pageIndex) {
        return fndPropertiesDao.query(fndProperties, pageSize, pageIndex);
    }

    public FndProperties findFndPropertiesById(FndProperties fndProperties) {
        return fndPropertiesDao.findFndPropertiesById(fndProperties);
    }
}
